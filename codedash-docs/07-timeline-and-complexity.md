# 07 — Timeline and Complexity

## Build phases (solo estimate)

Assuming coding on weekends and some weeknights alongside college.

| Phase | What | Estimated time |
|---|---|---|
| 1 | Student CRUD + MongoDB setup + basic REST API | 1 week |
| 2 | LeetCode + Codeforces adapters (both have clean APIs) | 1 week |
| 3 | BullMQ queue + cron + two-write sync pattern | 1.5 weeks |
| 4 | Frontend — leaderboard table, filters, sort | 1.5 weeks |
| 5 | Student detail page | 4 days |
| 6 | Redis cache layer | 3 days |
| 7 | CodeChef + GFG scraping adapters | 1 week |
| 8 | HackerRank Playwright adapter | 1 week |
| 9 | Admin panel — force sync, failed jobs, handle management | 1 week |
| 10 | Docker compose + deploy docs + polish | 4 days |

**Solo total: 3–4 months** at realistic pace.
**Team of 4 total: 6–8 weeks** with clean ownership split.

---

## What you need to learn

### Things you likely already know or are close to:
- Spring Boot / Node.js REST API — you've built this
- MongoDB CRUD — basic familiarity assumed
- React — basic components, state, props

### Things that require dedicated learning time:

**BullMQ (2–3 days)**
Job queues, workers, concurrency, failed job handling, dead letter queues.
The BullMQ docs are good. Build a small test project (queue that fetches
weather data for 100 cities) before using it in CodeDash.
Resource: bullmq.io/guide

**Playwright (1 day)**
Headless browser automation. Launch browser, navigate to URL, wait for
selector, extract text, close. Not hard, just new.
Resource: playwright.dev/docs/intro

**Redis basics (half a day)**
SET, GET, SETEX (set with TTL), DEL, KEYS. That's 90% of what you need.
Resource: redis.io/docs/getting-started

**Docker + docker-compose (1 weekend)**
Enough to write a compose file for your app (backend + MongoDB + Redis).
You don't need to understand Docker internals deeply.
Resource: docs.docker.com/compose/gettingstarted

**React table with server-side filtering (2 days)**
TanStack Table is the standard. Well documented, handles sort/filter/pagination.
Resource: tanstack.com/table

---

## Honest complexity rating

| Part | Complexity | Notes |
|---|---|---|
| REST API endpoints | Mid | Standard CRUD + filter queries |
| MongoDB schema design | Mid | Denormalization concept is the main learning |
| BullMQ queue + workers | Mid-Senior | Concurrency, retry, dead letter — real backend work |
| LeetCode/CF adapters | Mid | Clean APIs, straightforward |
| CodeChef/GFG scraping | Mid-Senior | Fragility, selector maintenance, failure handling |
| HackerRank Playwright | Mid-Senior | Headless browser, anti-bot, separate deployment |
| Redis cache layer | Mid | Key design, TTL strategy |
| Frontend leaderboard | Mid | TanStack Table, filter UI |
| Docker + deploy docs | Mid | One-time setup |
| Admin panel | Mid | Boring but important |

**Overall: mid-level with senior backend concepts touched.** Not FAANG-hard,
but not a CRUD tutorial. A student who completes this has real vocabulary for
backend interviews.

---

## The honest time killers

Phase 3 (BullMQ) has a learning curve — give yourself extra time here.
Don't rush it. The queue design decision affects everything downstream.

Phases 7–8 (scraping) always take 3× longer than estimated. Websites break
your assumptions. CodeChef's HTML will not match what you saw in the browser
inspector when you actually run the scraper. Plan for this.

Phase 9 (admin tooling) is boring but essential. Skipping it means you can't
diagnose why a student's data is wrong. Build it.

---

## Build order recommendation

Do NOT try to build all 5 platforms at once. Build in this order:

1. LeetCode sync end-to-end (API → queue → DB → leaderboard UI). One complete
   working slice.
2. Add Codeforces (same pattern, different API).
3. Add CodeChef scraping.
4. Build the student detail page.
5. Add Redis cache.
6. Add HackerRank (separate worker deployment).
7. Add GFG.
8. Admin panel, Docker, docs.

By step 2 you have a working, demonstrable product. That matters.
