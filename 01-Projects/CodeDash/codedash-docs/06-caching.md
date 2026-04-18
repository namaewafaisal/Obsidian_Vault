# 06 — Caching Strategy

## Two levels of caching

### Level 1 — The students collection itself

This is your primary "cache." You are never hitting external platforms on an
API request. Every query hits your own MongoDB which has a last-known snapshot.
The sync engine refreshes that snapshot on schedule. This is the fundamental
architecture that makes the app fast regardless of Redis.

### Level 2 — Redis query cache

For leaderboard queries that faculty run repeatedly (same year, same platform,
same filter), cache the result in Redis with a TTL.

---

## Redis cache design

**Key pattern:** hash the query parameters into a cache key.

```js
// cache key = deterministic hash of the query
function buildCacheKey(params) {
  const sorted = JSON.stringify(
    Object.keys(params).sort().reduce((acc, k) => {
      acc[k] = params[k]
      return acc
    }, {})
  )
  return `leaderboard:${Buffer.from(sorted).toString('base64')}`
}

// example keys:
// leaderboard:eyJ5ZWFyIjozLCJwbGF0Zm9ybSI6ImxlZXRjb2RlIn0=
// leaderboard:eyJkZXBhcnRtZW50IjoiQ1NFIiwicGxhdGZvcm0iOiJjb2RlZm9yY2VzIn0=
```

**TTL values:**

| Query type | TTL | Reason |
|---|---|---|
| Leaderboard (any filter) | 1 hour | Faculty view same board repeatedly in a session |
| Student list (no filter) | 30 minutes | Changes when new students are added |
| Admin sync status | 2 minutes | Needs to be fairly fresh |
| Student detail page | No cache | Only opened occasionally, freshness matters |

---

## Cache middleware

```js
// middleware/cache.js
export function cacheMiddleware(ttlSeconds) {
  return async (req, res, next) => {
    const key = buildCacheKey(req.query)
    const cached = await redis.get(key)

    if (cached) {
      return res.json({ ...JSON.parse(cached), cached: true })
    }

    // intercept res.json to store result before sending
    const originalJson = res.json.bind(res)
    res.json = async (data) => {
      await redis.setex(key, ttlSeconds, JSON.stringify(data))
      return originalJson(data)
    }

    next()
  }
}

// usage in route
router.get('/students', cacheMiddleware(3600), getStudentsHandler)
```

---

## Cache invalidation

When should the cache be busted?

```
Sync completes for a platform
  → invalidate all leaderboard cache keys containing that platform
  → simple approach: use Redis key patterns

// after LeetCode sync batch completes:
const keys = await redis.keys('leaderboard:*')
const lcKeys = keys.filter(k => /* decode and check if platform=leetcode */)
await redis.del(...lcKeys)
```

For simplicity at college scale, you can skip fine-grained invalidation
and just let TTL expire naturally. 1 hour TTL means at worst faculty see
data that's 1 hour old after a sync completes. Acceptable.

**When a new student is added by admin:**
```js
// after creating a student document
await redis.del(await redis.keys('leaderboard:*'))  // bust all leaderboard cache
```

---

## Upstash Redis — free tier limits

Upstash free tier: 10,000 commands per day.

Your usage estimate:
- Leaderboard queries: faculty might run ~50 queries per day × 2 commands
  (GET + SET) = 100 commands
- Cache misses trigger DB query + SET = 2 commands per miss
- Sync invalidation: 5 platforms × 1 DEL = 5 commands per sync cycle

**Total: well under 10,000 commands/day.** Free tier is sufficient.

If you later outgrow this (unlikely for college scale), Redis on Render costs
$7/month or you can run it on the same Render instance as your backend.

---

## What NOT to cache

- Student detail page — fetched infrequently, freshness matters
- Admin panel data (failed jobs, sync status) — needs to be live
- Any data that changes per-user (if you add auth and students see their own
  profiles) — don't cache personalized responses

---

## Without Redis — is it fine?

Yes, for this scale. If you skip Redis entirely:
- Every leaderboard query hits MongoDB
- With proper indexes, this is ~5–20ms per query
- 2000 students, 100 concurrent faculty users — MongoDB handles this fine

Redis just reduces that 5–20ms to <1ms for repeated queries and reduces
MongoDB load. It's a nice-to-have, not a requirement for college scale.
Add it early anyway because it's a good learning experience and costs nothing.
