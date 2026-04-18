Good question to nail down. Here's how I'd structure your week:

**CodeDash — 3 days a week, ~2 hrs each**
This is your main thing right now. Treat it like a job. The goal isn't to build forever, it's to ship a deployed version you can demo. Backend first — get the handle registry, platform API integration, cron sync, and export endpoint working before touching React. Once backend is solid, frontend sprint. A working deployed project matters more than any cert or extra learning right now given you have no shipped projects.

**LeetCode — 5 days a week, 1 problem per day**
5 problems a week is the right target at your stage. Not 2-a-day grind, not one a week. Consistent daily contact matters more than volume. Stick to medium difficulty, revisit your weak topics (you've done graphs, keep going — trees, DP basics, sliding window). The raw DSA study you're doing alongside this is exactly right — understanding *why* a segment tree or a Union-Find works before you code it beats memorising patterns blindly. Keep that going.

**SQL — 2 sessions a week, ~1 hr each**
You already started the roadmap. Don't drop it. Window functions and CTEs are the actual gaps. Do one concept session + one LeetCode SQL problem per session. This is low effort for high interview ROI.

**Spring depth — not a separate track right now**
CodeDash will teach you Spring properly. You'll hit `@Scheduled`, API clients with RestTemplate/WebClient, service layering, proper exception handling — all in context. Learning Hibernate internals in isolation before you need them is wasted effort. Let the project surface the gaps, then fill them.

**That's it.** No React Native, no Docker deep dive, no system design study sessions separately. The only exception — spend one weekend setting up Docker for CodeDash when the backend is mostly done. That gives you a deployed project and Docker experience in one shot.

Rough weekly shape: Monday/Wednesday/Friday → CodeDash. Daily → 1 LC problem. Tuesday/Thursday → SQL. That's maybe 12-14 hours a week total, which is sustainable alongside college.