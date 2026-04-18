# 03 — Database Design

## The core principle: denormalize for your read pattern

The leaderboard query is: "give me all 3rd year students, LeetCode solved > 200,
sorted by hard count descending."

In SQL thinking you'd join students + leetcode_stats. In MongoDB the right
approach is to store the summary numbers directly on the student document so
the leaderboard query hits ONE collection with ONE indexed scan. No join.

The full platform data (last 20 submissions, topic tags, badge history) lives in
a separate collection and is only fetched when someone opens a student's detail
page.

---

## Two collections

### Collection 1: `students`

One document per student. Small (~2KB). Used for every leaderboard and filter
query. Contains embedded summary stats from all platforms.

```js
{
  _id: ObjectId,
  roll_number: "21CSE001",
  name: "Mohamed Faisal",
  email: "faisal@college.edu",
  department: "CSE",
  year: 3,
  batch: 2022,
  section: "A",
  is_active: true,

  // platform handles
  handles: {
    leetcode: "faisal_lc",
    codeforces: "faisal_cf",
    codechef: "faisal_cc",
    hackerrank: "faisal_hr",
    gfg: "faisal_gfg"
  },

  // denormalized summary — written by sync engine after each platform sync
  // these are the fields used for ALL leaderboard queries and filters
  lc: {
    solved: 234,
    easy: 69,
    medium: 120,
    hard: 45,
    rating: 1820,
    contest_rank: 45230,
    last_synced: ISODate,
    sync_status: "ok"    // "ok" | "failed" | "never"
  },
  cf: {
    rating: 1450,
    max_rating: 1512,
    rank: "specialist",
    problems_solved: 312,
    last_synced: ISODate,
    sync_status: "ok"
  },
  cc: {
    rating: 1580,
    stars: 3,
    problems_solved: 89,
    last_synced: ISODate,
    sync_status: "failed"
  },
  hr: {
    badges: 12,
    gold_badges: 3,
    last_synced: ISODate,
    sync_status: "ok"
  },
  gfg: {
    problems_solved: 145,
    score: 620,
    last_synced: ISODate,
    sync_status: "ok"
  }
}
```

### Collection 2: `platform_data`

One document per student per platform. Larger (~5–20KB for LeetCode with
submissions). Only fetched on student detail page open.

```js
{
  _id: ObjectId,
  student_id: ObjectId,   // ref to students._id
  platform: "leetcode",   // "leetcode" | "codeforces" | "codechef" | "hackerrank" | "gfg"
  username: "faisal_lc",
  fetched_at: ISODate,
  last_successful_sync: ISODate,
  sync_status: "ok",
  consecutive_failures: 0,

  // the entire raw payload from API/scrape stored as-is
  // shape differs per platform — MongoDB handles this naturally
  data: {
    // LeetCode example:
    problems_solved: 234,
    easy: 69, medium: 120, hard: 45,
    rating: 1820,
    recent_submissions: [    // last 20
      { title: "Two Sum", difficulty: "Easy", timestamp: ISODate, status: "Accepted" },
      // ...
    ],
    topic_tags: { "dynamic-programming": 34, "graphs": 12, "trees": 28 },
    badges: ["50 Days Badge", "Annual Badge 2024"],
    contest_history: [
      { contest: "Weekly 385", rank: 2341, rating_change: +23 },
      // ...
    ]
  }
}
```

### Collection 3: `sync_log`

Append-only log of every sync attempt. Used by admin panel.

```js
{
  _id: ObjectId,
  student_id: ObjectId,
  platform: "codechef",
  status: "failed",           // "ok" | "failed" | "skipped"
  error_message: "HTML structure changed — selector .rating-number not found",
  ran_at: ISODate,
  duration_ms: 3240
}
```

---

## Why flat fields and not nested objects for the summary?

Both work in MongoDB. Flat prefix (`lc_solved`, `lc_hard`) vs nested (`lc.solved`,
`lc.hard`) are both indexable. We use nested because it groups platform data
visually in the document and makes the sync engine's `$set` calls cleaner:

```js
// clean — updates only lc fields, never touches cf or cc
await Student.updateOne({ _id: id }, {
  $set: {
    "lc.solved": 234,
    "lc.hard": 45,
    "lc.medium": 120,
    "lc.easy": 69,
    "lc.rating": 1820,
    "lc.last_synced": new Date(),
    "lc.sync_status": "ok"
  }
})
```

No conflict with concurrent CodeChef sync writing `cc.*` fields on the same
document. MongoDB `$set` is field-level, not document-level.

---

## Indexes

```js
// leaderboard: filter year + sort by lc.solved — covers 80% of queries
db.students.createIndex({ year: 1, "lc.solved": -1 })

// filter year + sort by hard count
db.students.createIndex({ year: 1, "lc.hard": -1 })

// filter year + sort by cf rating
db.students.createIndex({ year: 1, "cf.rating": -1 })

// filter by department + year
db.students.createIndex({ department: 1, year: 1 })

// platform detail lookup — unique per student per platform
db.platform_data.createIndex({ student_id: 1, platform: 1 }, { unique: true })

// sync log queries (admin panel — "show all failed CC syncs today")
db.sync_log.createIndex({ platform: 1, status: 1, ran_at: -1 })
```

With these indexes, "3rd year students, LeetCode solved > 200, sort by hard desc"
scans zero extra documents — it goes straight to the result using the
`{ year: 1, "lc.solved": -1 }` index.

---

## Storage estimate

| Collection | Per document | 2000 students | Notes |
|---|---|---|---|
| students | ~2KB | ~4MB | All summary fields embedded |
| platform_data (LeetCode) | ~15KB | ~30MB | Last 20 submissions + topic tags |
| platform_data (CF) | ~8KB | ~16MB | Contest history |
| platform_data (others) | ~3KB each | ~18MB | Less data available |
| sync_log | ~0.5KB | ~5MB/month | Grows over time, prune monthly |

**Total: ~75MB** — well within MongoDB Atlas free tier (512MB).

---

## Query examples for the API

```js
// leaderboard: 3rd year, LC > 200, sort by hard
Student.find({
  year: 3,
  "lc.solved": { $gte: 200 },
  "lc.sync_status": "ok"
})
.sort({ "lc.hard": -1 })
.select("name roll_number department lc cf cc")
.limit(100)

// student detail page — two parallel fetches
const [student, platforms] = await Promise.all([
  Student.findById(id),
  PlatformData.find({ student_id: id })
])

// admin: all failed syncs in last 24h
SyncLog.find({
  status: "failed",
  ran_at: { $gte: new Date(Date.now() - 86400000) }
}).sort({ ran_at: -1 })
```

---

## Platform switching on the leaderboard

When faculty switches from "LeetCode" to "Codeforces" view on the dashboard,
you do NOT re-query the database. All summary numbers (`lc.*`, `cf.*`, `cc.*`)
are already in the same `students` documents. The frontend re-sorts the
already-fetched data client-side, or re-hits the same collection with a
different sort key. Either way it's fast — the data is already there.
