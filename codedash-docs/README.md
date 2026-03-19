# CodeDash — Competitive Coding Dashboard for Institutions

> A self-hostable, open source dashboard for colleges to monitor student
> competitive coding activity across LeetCode, Codeforces, CodeChef,
> HackerRank, and GeeksForGeeks — with auto-sync, leaderboards, filters,
> and per-student detail views.

---

## Documents in this folder

| File | What it covers |
|---|---|
| `README.md` | This file — overview and index |
| `01-idea-and-scope.md` | Problem statement, why this doesn't exist yet, risks |
| `02-architecture.md` | Full system design — all layers, decisions, tradeoffs |
| `03-database-design.md` | MongoDB schema, indexing strategy, query patterns |
| `04-sync-engine.md` | Platform adapters, queue design, rate limiting, failure handling |
| `05-platform-guide.md` | Per-platform: API vs scrape, reliability, interval, gotchas |
| `06-caching.md` | Redis strategy, cache keys, TTL, invalidation |
| `07-timeline-and-complexity.md` | Build phases, learning curve, honest effort estimate |
| `08-resume-and-salary.md` | What this proves, interview talking points, salary expectations |
| `09-team-guide.md` | Team split, ownership, Git workflow, how to avoid common team failures |
| `diagrams/` | All architecture and schema diagrams as standalone HTML files |

---

## What this app does

1. Admin registers students with their coding platform handles (LeetCode username, Codeforces handle etc.)
2. A background sync engine fetches stats from each platform on a schedule
3. Stats are stored in MongoDB — summary numbers in the student document, full detail in a separate collection
4. The dashboard lets faculty filter by year, department, platform, and sort by problems solved, rating, hard/medium count
5. Clicking a student row opens a detail view with all platforms and full stats

---

## Who uses it

- Placement cells tracking student coding activity before recruitment season
- Coding clubs running internal leaderboards
- HODs or faculty wanting a quick view of department-wide progress
- Students themselves — a public leaderboard motivates consistent practice

---

## What makes this different from paid tools

Paid tools like HackerRank for Work or HackerEarth for Business:
- Cost per student (unaffordable for most Indian colleges)
- Lock you into their platform ecosystem
- Don't aggregate data from platforms students already use (LeetCode, CF etc.)

This tool:
- Free to deploy — MongoDB Atlas free tier + Render free tier + Vercel free tier
- Aggregates platforms students already have accounts on
- Self-hostable — college owns its own data
- Open source — anyone can extend it, fix broken adapters, add new platforms
