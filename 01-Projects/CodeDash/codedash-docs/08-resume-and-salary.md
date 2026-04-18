# 08 — Resume Value and Salary Expectations

## What this project proves

### In a resume bullet:
> "Designed and built a multi-platform competitive coding dashboard used by
> [College Name] to monitor 2000+ students across LeetCode, Codeforces, and
> CodeChef. Built async job queue with per-platform rate limiting, denormalized
> MongoDB schema optimized for leaderboard queries with compound indexes, and
> scraping adapters with graceful failure handling and stale-data UX."

That single bullet contains: system design decision (schema denormalization),
backend engineering (job queues, rate limiting), data engineering (indexes),
reliability engineering (graceful failure), and real users.

### Interview talking points you can own:

**"Why MongoDB over Postgres?"**
Each platform returns different shaped data. LeetCode has topic tags, Codeforces
has max rating history, HackerRank has badge categories. Forcing this into
relational tables means either a giant nullable table or a JSON column — at
which point you lose the benefit of relational. MongoDB's document model fits
naturally. And since the primary read pattern is "give me all students for this
year with this filter sorted by this field," a single document with embedded
summary stats and a compound index is faster than any join.

**"How did you handle concurrent platform syncs?"**
Per-platform BullMQ queues with different concurrency settings. API platforms
(LeetCode, Codeforces) run at 3–5 concurrent workers. Scraping platforms run
serially (concurrency: 1) with jitter to avoid detection. All queues drain in
parallel with each other — LeetCode doesn't wait for CodeChef. Wall-clock time
is the slowest queue, not the sum.

**"How did you handle scraping failures?"**
Never fail silently, never show zeros. `consecutive_failures` counter on each
platform record. After 3 consecutive failures, mark the platform as degraded
and show an admin alert. On the dashboard, show last known data with a "last
synced X days ago" warning instead of empty cells. The sync log records every
attempt with error messages so issues are diagnosable.

**"How did you design for scale?"**
College scale — up to 5000 students. Not startup scale. The decisions reflect
this: single MongoDB instance, Redis on free tier, one backend process. The
right call is not to over-engineer. If this ever needed to scale to 100k
students, you'd separate the sync engine, add read replicas, move to dedicated
Redis. But that's a different problem.

---

## Salary expectations

You're in 6th semester, graduating May 2027, targeting placements in late 2026.

| Route | Expected CTC |
|---|---|
| Service companies (TCS, Infosys, Wipro) | 3.5–7 LPA |
| Mid-tier product (Zoho, Freshworks, Chargebee) | 10–18 LPA |
| Good product startups (Series B/C funded) | 12–20 LPA |
| Top Indian product (Swiggy, Zepto, Meesho, CRED) | 18–30 LPA |
| FAANG / Google / Microsoft / Adobe India | 30–50 LPA |

**With this project + decent DSA + good communication:**
Comfortably into the 10–20 LPA range. Serious candidate for good product
startups. The project gives you vocabulary and real experience — most freshers
have neither.

---

## The honest gap to 30+ LPA

**DSA:** FAANG screens on this regardless of projects. LeetCode medium
consistently, some hard. ~150–200 problems with real understanding, not just
memorized patterns.

**System design:** This project gives you one deep design. For 30+ LPA
interviews you need to discuss 5–6 different systems and their tradeoffs
(URL shortener, rate limiter, notification system, ride sharing, etc.)

**One more project:** Something showing high throughput or distributed concepts.
Your OTT platform could be this if you add real-time features (watch party,
live event, recommendation engine).

**College tier:** NIT/IIT gets into the FAANG room faster. From SRM TRP you
clear the OA first — which is purely DSA. The project helps in the interview
room, not in the screening filter. So DSA is non-negotiable.

---

## Realistic target

If you finish this project properly, keep OTT platform going, solve DSA
consistently from now until placement season, and interview well:

**12–22 LPA is a genuinely achievable and honest target.**

25+ is possible but requires either cracking a top product company's OA
(DSA-heavy and competitive) or getting a referral through the open source
project gaining visibility.

**The project is a multiplier, not a guarantee. DSA is still the filter.**

---

## Open source strategy

Don't just dump the code. Structure the repo for adoption:

```
README.md          ← what it is, screenshot, live demo link
DEPLOY.md          ← step by step: Atlas setup, env vars, Render, Vercel
PLATFORMS.md       ← which platforms work, known issues, how to add new adapter
CONTRIBUTING.md    ← how to update a broken scraper, PR guidelines
docker-compose.yml ← one command local setup
```

The `docker-compose.yml` is the killer feature for adoption. An institution's
tech coordinator should run `docker compose up` and have a working local
instance in under 5 minutes. That's what makes open source tools actually get
used.

Write a short technical blog post on Hashnode or dev.to explaining the schema
design decision and the sync architecture. That post + the GitHub repo together
is a very strong portfolio piece for backend roles.
