# 04 — Sync Engine Design

## Architecture

The sync engine is NOT separate services. It lives inside the same backend
process as the REST API — just a different module that runs on a timer.

```
backend/
  src/
    api/              ← REST endpoints
    adapters/         ← one file per platform
      leetcode.js
      codeforces.js
      codechef.js
      hackerrank.js
      gfg.js
    scheduler/        ← cron jobs that enqueue sync work
      index.js
    workers/          ← BullMQ workers that process jobs
      syncWorker.js
    models/           ← MongoDB schemas
```

---

## Cron is not threads

Cron just triggers job creation on a schedule. The actual work goes into a
BullMQ queue. Workers pull jobs from the queue and process them with controlled
concurrency. This is important — you are not spawning raw threads per student.

```
2am cron fires
  → loads all active platform_profiles (e.g. 2000 × 5 = 10,000 profiles)
  → enqueues 10,000 jobs into 5 platform-specific queues
  → workers drain the queues concurrently but rate-limited per platform
```

---

## Per-platform queues and concurrency

Each platform gets its own queue with a different concurrency setting based on
how aggressively it can be hit.

```js
const queues = {
  leetcode:    new Queue('lc',  { connection: redis }),
  codeforces:  new Queue('cf',  { connection: redis }),
  codechef:    new Queue('cc',  { connection: redis }),
  hackerrank:  new Queue('hr',  { connection: redis }),
  gfg:         new Queue('gfg', { connection: redis }),
}

// Workers — concurrency is how many jobs run simultaneously
new Worker('lc',  processJob, { connection: redis, concurrency: 3 })
new Worker('cf',  processJob, { connection: redis, concurrency: 5 })
new Worker('cc',  processJob, { connection: redis, concurrency: 1 })  // scrape — serial
new Worker('hr',  processJob, { connection: redis, concurrency: 1 })  // scrape — serial
new Worker('gfg', processJob, { connection: redis, concurrency: 1 })  // scrape — serial
```

All 5 queues drain in parallel with each other. LeetCode is not waiting for
CodeChef. The total wall-clock time is the slowest queue, not the sum of all.

---

## Time math — 2000 students

| Platform | Method | Concurrency | Delay/req | Total time |
|---|---|---|---|---|
| LeetCode | GraphQL API | 3 | 1s | ~11 minutes |
| Codeforces | REST API | 5 | 0.2s | ~1.5 minutes |
| CodeChef | Scrape | 1 (serial) | 10s | ~5.5 hours |
| HackerRank | Scrape | 1 (serial) | 15s | ~8 hours |
| GFG | Scrape | 1 (serial) | 10s | ~5.5 hours |

Wall-clock time = 8 hours (HackerRank, the slowest).
Sync starts at 2am → finishes by 10am → fresh data for college hours. Fine.

If realistically only 500 students register handles (not all 2000 will):
HackerRank finishes in ~2 hours. Very comfortable.

---

## The two-write pattern

After fetching a platform's data, the sync engine does two writes:

```js
async function processSyncJob(job) {
  const { studentId, platform, username } = job.data

  try {
    // 1. fetch from platform adapter
    const payload = await adapters[platform].fetch(username)

    // 2. upsert full data into platform_data collection
    await PlatformData.findOneAndUpdate(
      { student_id: studentId, platform },
      {
        data: payload,
        fetched_at: new Date(),
        last_successful_sync: new Date(),
        sync_status: 'ok',
        consecutive_failures: 0
      },
      { upsert: true }
    )

    // 3. punch summary back into students document
    const summary = extractSummary(platform, payload)
    await Student.updateOne(
      { _id: studentId },
      { $set: summary }   // e.g. { "lc.solved": 234, "lc.hard": 45, ... }
    )

    // 4. log success
    await SyncLog.create({ student_id: studentId, platform, status: 'ok', ... })

  } catch (err) {
    // 5. log failure, increment counter
    await PlatformData.findOneAndUpdate(
      { student_id: studentId, platform },
      {
        sync_status: 'failed',
        $inc: { consecutive_failures: 1 }
      },
      { upsert: true }
    )
    await SyncLog.create({ student_id: studentId, platform, status: 'failed',
                           error_message: err.message, ... })
  }
}
```

**No race condition:** LeetCode sync writes `lc.*` fields. CodeChef sync writes
`cc.*` fields. They never touch the same fields. MongoDB `$set` is field-level.

---

## Rate limiter

Every adapter is wrapped in a rate limiter that adds delay + jitter before
each request. Jitter is important — it makes requests look less like a bot.

```js
async function rateLimitedFetch(fn, baseDelayMs) {
  const jitter = Math.random() * baseDelayMs * 0.3   // ±30% jitter
  await sleep(baseDelayMs + jitter)
  return fn()
}

// usage in adapter
await rateLimitedFetch(() => fetchLeetCodeProfile(username), 1000)
```

---

## Failure handling

```
Job fails (network error, HTML changed, rate limited)
  → BullMQ retries automatically (3 attempts, exponential backoff)
  → All 3 fail → job moves to dead letter queue
  → consecutive_failures incremented on platform_data document
  → if consecutive_failures >= 3 → admin alert (email or dashboard badge)
  → sync_status = "failed" shown on dashboard with last known data
```

Never show zeros on the dashboard when a sync fails. Show the last known
data with a "last updated X days ago" badge. Stale data with a warning is
far better UX than an empty cell.

---

## Cron schedule

```js
import cron from 'node-cron'

// API platforms — every 6 hours
cron.schedule('0 */6 * * *', () => enqueuePlatform(['leetcode', 'codeforces']))

// Scrape platforms — once per day at 2am
cron.schedule('0 2 * * *', () => enqueuePlatform(['codechef', 'hackerrank', 'gfg']))

async function enqueuePlatform(platforms) {
  const profiles = await Student.find({ is_active: true })
  for (const student of profiles) {
    for (const platform of platforms) {
      if (student.handles[platform]) {
        await queues[platform].add('sync', {
          studentId: student._id,
          platform,
          username: student.handles[platform]
        })
      }
    }
  }
}
```

---

## Admin force re-sync

```js
// POST /api/admin/sync/:studentId/:platform
router.post('/sync/:studentId/:platform', adminAuth, async (req, res) => {
  const { studentId, platform } = req.params
  const student = await Student.findById(studentId)
  await queues[platform].add('sync', {
    studentId,
    platform,
    username: student.handles[platform]
  }, { priority: 1 })   // high priority — goes to front of queue
  res.json({ message: 'Sync job queued' })
})
```
