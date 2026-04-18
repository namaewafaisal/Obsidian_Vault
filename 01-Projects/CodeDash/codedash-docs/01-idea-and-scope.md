# 01 — Idea, Scope, and Honest Assessment

## The problem

Colleges have no easy way to monitor competitive coding progress across their
students. Faculty have to manually check individual profiles, platforms don't
talk to each other, and there's no aggregated view across a batch or department.

Paid tools exist but are expensive. Nothing open source and self-hostable solves
this cleanly.

---

## What exists today (and why it falls short)

| Tool | What it does | Why it's not enough |
|---|---|---|
| LeetCode Stats Card | README badge for one user | Single user, single platform |
| Coding tracker Chrome extensions | Personal tracking | Single user only |
| Various college GitHub projects | Partial solutions | Abandoned, one platform, or manual data entry |
| HackerRank for Work | Institution-wide tracking | Paid SaaS, per-student cost, locked ecosystem |
| HackerEarth for Business | Same | Same problems |

**The gap:** nothing open source does multi-platform, auto-sync, institution-wide
filtering by year/department, with a self-hostable deploy. This is a real gap.

---

## Scope — what we are building

### In scope
- 5 platforms: LeetCode, Codeforces, CodeChef, HackerRank, GeeksForGeeks
- Auto-sync with platform-appropriate schedules
- Leaderboard with filters: year, department, platform, min solved count
- Sort by: total solved, hard count, medium count, rating
- Student detail page: all platforms, full stats, last 20 submissions
- Admin panel: register students, manage handles, force re-sync, view failed syncs
- Docker compose for local setup
- Deploy documentation for institutions

### Out of scope (intentionally)
- Real-time sync (not needed, once-per-day is sufficient for this use case)
- Contest scheduling or hosting
- Code submission or judge functionality
- Mobile app
- Email notifications (can be added later)

---

## Honest ratings

### Idea: 8.5 / 10

The gap is real. The use case is clear. The target users (placement cells, coding
clubs, HODs) have a genuine pain. Self-hostable with zero cost is a strong
differentiator.

**What brings it down from 10:** dependency on scraping. CodeChef, GFG, and
HackerRank can break your adapters any time they update their frontend. This is
ongoing maintenance, not a one-time build.

### Technical design: 8 / 10

Two-collection MongoDB schema with denormalized summary fields is correct for
this read pattern. Per-platform queues with different concurrency is correct.
Separation of leaderboard view from detail view is clean.

**What would make it a 9:** a proper admin observability page (queue depth,
failed jobs per platform, last sync time), and a WebSocket notification to the
frontend when a sync completes instead of polling.

### Complexity: mid-senior backend concepts, mid frontend

The frontend (React table, filters, sort) is mid-level. The REST API is mid.
The sync engine (job queues, rate limiting, scraping, graceful failure, two-write
atomicity) is what mid-senior backend engineers deal with professionally. You'll
be touching these concepts for the first time but building them from scratch with
time to think — that's different from owning them in production, but it gives you
real vocabulary.

---

## The risks

### Scraping legality
Scraping public profiles is a grey area. You are not logging in as another user
or bypassing a paywall — you are reading publicly visible data. No platform has
gone after a college internal tool for this. Practically this is fine, but worth
knowing.

### Scraping brittleness
This is the real operational risk. CodeChef, GFG, and HackerRank update their
frontends without notice. Your adapter breaks silently. Mitigation:
- `sync_status` and `last_successful_sync` fields on every student-platform record
- Admin alert if `consecutive_failures >= 3`
- Show "data may be outdated" badge on dashboard when `last_successful_sync` is
  older than 3 days
- Never show zeros — show stale data with a warning

### Adoption cold start
Your own college will be the first user. Getting a second institution requires
someone there to trust a student's open source project. What overcomes this:
- A working live demo URL
- A `docker-compose up` that works in under 5 minutes
- A clean README with screenshots

### Team maintenance
Once other colleges file issues (adapter broke, new platform request, deploy
problem), you become the maintainer. This is good for your profile but real
ongoing work.

---

## What this is NOT

- Not a replacement for actual DSA practice — it tracks, it doesn't teach
- Not infinitely scalable — designed for college scale (up to ~5000 students),
  not a startup product
- Not a real-time system — syncs on schedule, not on every submission
