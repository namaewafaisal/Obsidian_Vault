---
topic: pivot-to-coding-dashboard
date-time: invalid-date-time
time-taken(min): invalid-number
---

# Pivot: ERP → Coding Profile Aggregator

## 🧠 Reason for Pivot

- ERP clone → no real adoption (trust + duplication issue)
- student-entered data → unreliable
- staff won’t maintain multiple systems

## 🎯 New Direction

System becomes:

```text
Coding Profile Aggregator
```

Focus:
- external data (LeetCode, etc.)
- backend-controlled stats (not user input)
- analytics (heatmap, trends)

---

# 🧠 Core Design Shift

```text
Before:
Profile = everything

After:
Profile = identity
Handle = external identity
Stats = dynamic data
DailyStats = timeline
```

---

# 🧱 Final Data Model

```text
Profile (1)
   ↓
Handle (many)
   ↓
PlatformStats (1 per handle)
   ↓
DailyStats (many per handle)
```

---

# 🧠 Handle Design Fix

## Changes

- `@OneToOne` → ❌ wrong
- `@ManyToOne` → ✅ correct

Reason:
```text
1 profile → multiple handles
```

---

## Constraint Added

```text
(profile_id, platform) → UNIQUE
```

Prevents:
```text
duplicate platform handles per user
```

---

## Enum Fix

```text
Handle → ❌ wrong meaning
Platform → ✅ correct
```

---

# 🧠 PlatformStats Design

## Purpose

```text
Stores latest snapshot only
```

## Behavior

```text
✔ overwritten daily
✔ no history
✔ 1 row per handle
```

## Key Insight

```text
Stats ≠ history
```

---

# 🧠 DailyStats Design

## Purpose

```text
Store timeline (heatmap + trends)
```

---

## Final Fields

```text
✔ problemsSolvedToday
✔ easySolvedToday
✔ mediumSolvedToday
✔ hardSolvedToday
✔ totalSolved (snapshot)
```

---

## Constraint

```text
(handle_id, date) → UNIQUE
```

---

## Insert Strategy

```text
✔ insert new row daily
❌ no updates
❌ no deletion
```

---

# 🧠 Critical Design Decisions

## 1. No Rolling Window

Rejected:

```text
delete oldest, keep 7 days ❌
```

Reason:
```text
loses history
breaks analytics
```

---

## 2. No Problem IDs (for now)

Rejected:

```text
store submissions ❌
```

Reason:
```text
too heavy
unnecessary
not core requirement
```

---

## 3. Stats Separation

```text
Handle → identity
PlatformStats → current state
DailyStats → history
```

---

# 🧠 Storage Strategy

## Decision

```text
Append-only history
```

Reason:

```text
✔ small data size
✔ enables analytics
✔ no complexity
```

---

# 🧠 Cron Concept (planned)

Flow:

```text
1. fetch latest totalSolved
2. compare with previous
3. compute delta
4. insert DailyStats
5. update PlatformStats
```

---

# 🧠 Key Insight from Pivot

```text
Stop storing user data
Start ingesting external data
```

---

# 🎯 System Identity Now

```text
Not CRUD app
Not ERP clone

→ Data Aggregation + Analytics System
```

---

# 🧠 Engineering Value Added

```text
✔ scheduled jobs
✔ external API integration
✔ time-series data modeling
✔ normalization
✔ constraint design
✔ scalable schema
```

---

# 🧠 Current State

```text
✔ Profile fixed
✔ Handle fixed
✔ PlatformStats designed
✔ DailyStats designed
⬜ cron logic (next)
```
