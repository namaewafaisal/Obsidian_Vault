# 02 — System Architecture

## Overview

Three distinct concerns, reasoned about and deployable separately:

```
┌─────────────────────────────────────────────────┐
│              CLIENT TIER                         │
│  Next.js frontend  │  Admin dashboard  │  Student profile view  │
└─────────────────────────────────────────────────┘
                        │
                        ▼
┌─────────────────────────────────────────────────┐
│              API TIER  (Spring Boot / Node.js)   │
│  Student endpoints  │  Filter+query  │  Cache layer (Redis)  │
└─────────────────────────────────────────────────┘
                        │
                        ▼
┌─────────────────────────────────────────────────┐
│              DATA TIER                           │
│  MongoDB (primary)  │  Redis (cache+queue)  │  Sync log  │
└─────────────────────────────────────────────────┘

━━━━━━━━━━━━━━━━━━━ background sync engine ━━━━━━━━━━━━━━━━━━━

┌─────────────────────────────────────────────────┐
│  Cron scheduler  │  Rate limiter  │  Platform adapters (LC/CF/CC/HR/GFG)  │
└─────────────────────────────────────────────────┘
                        │
                        ▼ writes to MongoDB
```

See `diagrams/system-overview.html` for the interactive version.

---

## Tech stack decisions

| Layer | Choice | Why |
|---|---|---|
| Frontend | Next.js (React) | SSR for fast first load, API routes built in |
| Backend API | Spring Boot OR Node/Express | Both work — Spring Boot if team knows Java |
| Database | MongoDB Atlas (free M2 tier) | Schema-flexible, good for mixed platform data |
| Cache | Redis via Upstash (free tier) | Query-key TTL cache, fits serverless |
| Sync engine | Node.js + BullMQ | Built-in retry, rate limiting, job queues |
| Hosting | Vercel (frontend) + Render/Railway (backend) | Free tiers sufficient for college scale |

---

## Why NOT other choices

### Why not PostgreSQL?
Each platform returns different shaped data. LeetCode has topic tags, Codeforces
has max rating, HackerRank has badge categories. Forcing all of this into a
relational schema means either a giant nullable table or a JSON column — at which
point you've lost the benefit of relational anyway. MongoDB's document model fits
naturally.

### Why not Cassandra?
Cassandra is for write-heavy time-series at millions of events per second.
Your write load is 2000 students × 5 platforms × once per day = 10,000 writes
per day. MongoDB Atlas free tier handles this without breaking a sweat.

### Why not microservices (one service per platform)?
Overkill for college scale. One backend process with modular adapters is correct.
You'd spend more time on inter-service communication than on actual features.
The only exception is HackerRank's Playwright scraper which is extracted to a
separate worker because Playwright binaries are 200MB+ (too large for Vercel).

### Why not GraphQL for the API?
REST is simpler for a team project and easier to test. The query patterns are
well-defined (filter by year + platform + min score, sort by field). GraphQL
would add complexity without meaningful benefit here.

---

## Deployment topology

```
Vercel (free)
└── Next.js frontend + API routes

Render or Railway (free tier)
└── Node.js backend
    ├── REST API (Express)
    ├── BullMQ workers (cron + job processing)
    ├── Adapters: LC, CF, CC, GFG (all lightweight HTTP)
    └── Adapter: HackerRank (Playwright — reason for separate process)

MongoDB Atlas (free M2 — 512MB)
└── students collection
└── platform_data collection
└── sync_log collection

Upstash Redis (free tier — 10k commands/day)
└── Query result cache
```

512MB on Atlas free tier is plenty — 2000 students × 20KB average platform data
= 40MB. You have headroom.

---

## Request flow — leaderboard query

```
Browser
  → GET /api/students?year=3&platform=leetcode&min_solved=200&sort=lc.hard
  → API checks Redis cache (key: hash of query params)
      HIT  → return cached result immediately
      MISS → query MongoDB students collection
             (index: { year: 1, "lc.solved": -1 })
           → store result in Redis with 1hr TTL
           → return result
```

## Request flow — student detail page

```
Browser clicks student row
  → GET /api/students/:id/detail
  → API runs two parallel queries:
      Promise.all([
        Student.findById(id),                          // basic info
        PlatformData.find({ student_id: id })          // all platforms, full data
      ])
  → Merge and return
  → No cache needed — opened infrequently, data freshness matters here
```

## Sync engine flow

```
Cron fires (2am for scrape platforms, every 6h for API platforms)
  → Load all active platform_profiles from DB
  → Enqueue one job per student per platform into BullMQ
  → Workers consume jobs with per-platform concurrency limits
  → Each worker:
      1. Calls the platform adapter (API fetch or scrape)
      2. Upserts full data into platform_data collection
      3. $set summary fields in students document (lc.solved, lc.rating etc.)
      4. Updates sync_log with status and timestamp
      5. On failure: log error, increment consecutive_failures,
         alert admin if threshold reached
```

---

## Vercel + MongoDB Atlas — what they handle automatically

### Vercel handles:
- Load balancing across edge network — fully automatic, zero config
- CDN for static assets (JS, CSS, images)
- Scaling serverless functions on demand

### Vercel does NOT handle:
- DB response caching — you add Redis for this
- Rate limiting — add via middleware
- Persistent processes (cron jobs) — use Render/Railway for this

### MongoDB Atlas handles:
- High availability — 3-node replica set, automatic failover
- Backups — configurable
- Connection management at driver level — partially

### The serverless + MongoDB cold start problem:
When a Vercel function cold starts it opens a new DB connection. Under traffic
spikes this can overwhelm Atlas connection limits. Fix with connection reuse:

```js
// cache the mongoose connection at module level
let cached = global.mongoose
if (!cached) cached = global.mongoose = { conn: null, promise: null }

export async function connectDB() {
  if (cached.conn) return cached.conn
  if (!cached.promise) {
    cached.promise = mongoose.connect(process.env.MONGODB_URI)
  }
  cached.conn = await cached.promise
  return cached.conn
}
```

Without this you will hit `MongoServerSelectionError` under load.
