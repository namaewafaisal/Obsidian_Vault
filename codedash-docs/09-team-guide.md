# 09 — Team Guide

## Team of 4 — how to split without diluting anyone

### The wrong split (avoid this)

"You do frontend, you do backend, you do database, you do deployment."

This gives one person shallow React experience and another shallow API
experience and nobody deeply understands the interesting parts. In interviews
you can only speak confidently about what YOU built. "I did the frontend"
is a weak answer. "I built the sync engine with per-platform rate limiting
and graceful failure handling" is a strong answer.

### The right split — vertical ownership

Each person owns one complete feature slice from backend to frontend.
Nobody is just "the frontend person" or "the backend person."

---

## Ownership split

### Person 1 (Mohamed — repo owner): Sync Engine Core

**You own the hardest and most impressive part.**

- BullMQ job queue setup — per-platform queues, concurrency settings
- Cron scheduler — enqueue logic, platform interval design
- The two-write pattern — upsert to platform_data + $set summary in students
- Rate limiter wrapper — delay, jitter, exponential backoff
- Failure handling — consecutive_failures, dead letter, admin alert logic
- Redis cache middleware — key design, TTL, invalidation after sync
- MongoDB index strategy — all compound indexes

**What you can say in an interview:**
"I designed the async sync architecture — a multi-queue BullMQ system with
per-platform concurrency control, rate limiting with jitter, and a two-write
pattern that updates both a full data store and a denormalized summary
document atomically. I also designed the failure handling strategy and the
Redis cache layer."

---

### Person 2: LeetCode + Codeforces adapters + Leaderboard UI

**Owns the two most reliable platforms end-to-end, plus the primary dashboard view.**

Backend:
- LeetCode GraphQL adapter — query design, response transformation, error handling
- Codeforces REST adapter — endpoints, rate limiting, response transformation
- Summary extractor for both platforms (the function that converts raw API
  response to `lc.*` and `cf.*` fields)

Frontend:
- Main leaderboard table — TanStack Table, columns, sorting
- Platform filter toggle (LeetCode / Codeforces / CodeChef etc.)
- Year and department filter dropdowns
- Min solved count filter input
- Sort controls (hard, medium, total, rating)

**What they can say in an interview:**
"I built the LeetCode and Codeforces data pipeline end-to-end — adapter that
hits LeetCode's GraphQL API, transforms the response, and feeds both a full
data store and summary fields. I also built the leaderboard UI with server-side
filtering and multi-column sorting."

---

### Person 3: Scraping adapters + Failure handling + Admin sync panel

**Owns the technically tricky platforms and the operational visibility.**

Backend:
- CodeChef Cheerio scraper — selector design, UA rotation, error handling
- GFG Cheerio scraper — same approach
- HackerRank Playwright scraper — headless browser, wait strategy
- Separate Render deployment for Playwright worker
- Sync log writes — structured error logging with enough context to debug
  broken selectors

Frontend:
- Admin sync panel — table of all students, last sync time per platform,
  sync status badges (ok / failed / stale)
- Failed jobs view — list of failed syncs with error messages
- Force re-sync button per student per platform

**What they can say in an interview:**
"I built the scraping adapters for three platforms — Cheerio-based for
CodeChef and GFG, Playwright headless browser for HackerRank which requires
JS rendering. I implemented anti-detection strategies (UA rotation, jitter
delays), designed the failure logging schema, and built the admin observability
panel for monitoring sync health."

---

### Person 4: Student management + Auth + Detail page + Docker + Deploy

**Owns the user-facing reliability and the open source deployability.**

Backend:
- Student CRUD API — create, update, deactivate, bulk import from CSV
- Handle management — add/update/remove platform handles per student
- Auth — JWT-based, two roles: admin (faculty) and viewer (student/public)
- Admin-only middleware for protected routes

Frontend:
- Student registration form — add student, assign handles
- Student detail page — all platforms displayed, last 20 submissions for LC/CF,
  topic tag breakdown, sync status per platform
- Stale data warning badge (last synced > 3 days ago)
- Student self-view (if student logs in, sees their own profile)

DevOps:
- `docker-compose.yml` — backend + MongoDB + Redis, works with `docker compose up`
- `DEPLOY.md` — step-by-step for institutions: Atlas setup, Render deploy,
  Vercel deploy, env vars
- Environment variable documentation
- GitHub Actions CI — run tests on PR

**What they can say in an interview:**
"I owned the student management system, JWT auth with role-based access control,
and the student detail page that aggregates all platform data. I also wrote
the Docker setup and deploy documentation that lets any institution self-host
in under 10 minutes."

---

## Git workflow

### Branching strategy

```
main          ← protected, production-ready only
dev           ← integration branch, all PRs merge here first
feature/*     ← individual feature branches

example branches:
  feature/sync-engine-core
  feature/leetcode-adapter
  feature/leaderboard-ui
  feature/student-crud
```

### PR rules (set as branch ruleset on GitHub)

- No direct push to `main` or `dev`
- Every PR requires at least 1 review (from another team member)
- PR must pass CI checks before merge
- Mohamed (repo owner) does final merge to `main`

### Commit message convention

```
feat: add BullMQ queue setup for leetcode sync
fix: handle null rating in codeforces adapter
chore: add docker-compose for local dev
docs: update DEPLOY.md with Atlas setup steps
```

Use `feat`, `fix`, `chore`, `docs`, `test` prefixes. Keeps the git log readable.

---

## Communication

- Daily async standups in a group chat — what you did, what you're doing, any blockers
- Weekly 1-hour sync call — demo what you built, discuss design decisions
- All technical decisions documented in `docs/decisions/` folder (ADR format)
- Don't go more than 3 days without a commit if you're in active development

### ADR (Architecture Decision Record) format

When a significant decision is made (why MongoDB, why BullMQ, why two collections
instead of one), write a short ADR:

```markdown
# ADR-001: Use two MongoDB collections instead of one

## Status: Accepted

## Context
Leaderboard queries need to be fast and avoid joins. Student detail page
needs full platform data including submission history.

## Decision
Two collections: students (summary, small) and platform_data (full, large).
Summary fields denormalized into students document.

## Consequences
- Leaderboard queries hit one collection — fast
- Two writes per sync (upsert platform_data + $set students)
- Detail page needs two lookups but detail pages are infrequent
```

---

## What kills team projects — and how to avoid it

**Unequal contribution:** Set clear ownership from day one. Each person has
named features they are responsible for. No ambiguity. If someone isn't
delivering, the repo's contribution graph is public — interviewers can see it.

**Integration delays:** Agree on API contracts (request/response shapes)
early. Person 2 and Person 3 both need to call the sync engine — agree on
the queue job shape before building the adapters.

**"I'll do it later":** Never works. Use GitHub Issues to track tasks.
Every feature is an issue, assigned to an owner, with a due date.

**Merge conflicts on the same file:** With vertical ownership splits, each
person mostly touches different files. The main conflict zone is shared
utilities (DB connection, rate limiter). Put these in a `shared/` folder
and communicate before editing them.

**No one writes docs:** Docs are Person 4's explicit responsibility for
external docs (DEPLOY.md, README). But each person documents their own
code — at minimum a comment block explaining what each adapter does and
what fields it returns.

---

## Impact on resume — team vs solo

**Does working in a team reduce your resume impact?**

Depends entirely on how you split. With this vertical ownership split:
- GitHub contribution graph shows your commits are on the sync engine and
  Redis cache — the technical core
- You can describe your specific contribution precisely in interviews
- The project having 4 contributors actually looks more credible than solo —
  it signals the project was real enough to involve a team

**The risk:** If teammates don't deliver, you end up doing their parts and
getting no credit for it. Set deadlines, hold each other accountable. Don't
rescue someone's feature at the last minute without noting it.

**Golden rule:** Make sure YOUR part is the sync engine (Person 1). That's
the interview-worthy piece. The frontend table and student CRUD are useful
but generic. The queue design, rate limiting, scraping resilience, and
cache invalidation are what differentiate you.
