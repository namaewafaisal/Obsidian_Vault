# CodeDash — Requirements Document
> Version: MVP (v1.0)
> Last updated: 10 April 2026

---

## What is CodeDash

A backend system that aggregates coding platform data for students and provides
institutions with filtering, ranking, and export capabilities for performance analysis.

Not an ERP. Not a portfolio builder. Not a social platform.
It is a data aggregation and analytics tool for coding performance, scoped per institution.

---

## Actors

### Master (Platform Owner — you)
- Approves or rejects institution registration requests
- Manages platform-level operations
- No direct access to student data of any institution
- Receives institution registration requests via email

### Institution Admin
- Registers the institution (becomes admin automatically)
- Promotes users to Staff role
- Can delete the institution (full withdrawal from platform)
- Access to all student data within their institution
- Can view dashboard, leaderboard, export

### Staff
- Views student dashboard and leaderboard
- Filters and sorts students
- Exports data to Excel
- Cannot manage users or institution settings

### Student
- Registers with institution email
- Creates their own profile
- Adds and updates their own platform handles
- Cannot modify stats (system controlled)
- Cannot view other students' full profiles directly

---

## Core Flow

```
Master approves institution registration request
            ↓
Institution exists in system
            ↓
User registers with institution email (domain verified)
            ↓
Email verification sent → user confirms ownership
            ↓
User created with default role: STUDENT
            ↓
Admin can promote user to STAFF
            ↓
Student creates profile (name, register number, dept, year, section)
            ↓
Student adds platform handles (LeetCode, Codeforces, GitHub, etc.)
            ↓
Handle verification attempted (if platform allows, free tier only)
            ↓
Nightly CRON job fetches stats for all handles
            ↓
Stats stored as latest snapshot per student per platform
            ↓
Staff views dashboard → filters, sorts, exports
```

---

## Institution Registration Flow

1. Anyone with a non-generic domain (not gmail.com, yahoo.com, hotmail.com etc.)
   can submit a registration request
2. Request includes: institution name, email domain, admin email, admin password
3. System sends request details to Master's email
4. Master reviews and approves or rejects
5. On approval — institution is created, admin account is activated
6. On rejection — admin email receives rejection notice

> Implementation note: A simple pending/approved status flag on Institution table.
> Master has a private endpoint to approve/reject.

---

## User Registration Flow

1. User submits: email, password, institution (by name or domain)
2. System checks email domain against institution's registered domain
3. If domain matches → verification email sent to user
4. User clicks verification link → account activated
5. Default role assigned: STUDENT
6. Admin can promote to STAFF via admin endpoint

### Register Number Extraction (SRM-style institutions)
If institution's email format embeds register number
(e.g. `814723104089@trp.srmtrichy.edu.in`), system extracts and pre-fills
register number during profile creation. Configurable per institution.

### Password Rules
- Minimum 8 characters
- Maximum 64 characters
- At least 1 uppercase letter
- At least 1 numeric character

---

## Data Ownership

| Data | Owner | Can edit |
|---|---|---|
| Profile (name, dept, year) | Student | Student only |
| Handles (usernames) | Student | Student only |
| Stats | System (CRON) | Nobody manually |
| User role | Admin | Admin only |
| Institution settings | Admin | Admin only |

---

## Platform Handle Verification (MVP)

- Student adds a handle username
- System attempts to verify the username exists on the platform
- Verification method: call platform's public API or GraphQL
- If platform charges for API access → skip verification for that platform
- Free verification only — no paid APIs
- Unverified handles are still stored and synced
- Verified status shown as a flag on the handle

---

## Stats Sync (CRON Job)

- Runs nightly (e.g. 2:00 AM)
- For each student handle → fetch latest stats from platform API
- Overwrites the existing `HandleStats` row (latest snapshot, no history)
- Stores `lastSyncedAt` timestamp
- Stores `lastSubmissionAt` — date and time of student's most recent submission
  (used for activity indicator)
- Full API response stored in `rawData` JSONB column for flexibility

### Activity Indicator Logic
```
lastSubmissionAt >= now - X days  →  active (green)
lastSubmissionAt <  now - X days  →  inactive (red)
```
X is configurable per institution or per staff preference (default: 7 days).

---

## Dashboard (Staff View)

### Leaderboard
- Scoped to the institution — no cross-institution data
- Filterable by: department, year, section, department+year+section combined
- Sortable by: problemsSolved, easySolved, mediumSolved, hardSolved, rating, globalRank
- Platform selector: staff picks which platform to view (LeetCode, Codeforces, etc.)
- Each row shows: name, register number, stats for selected platform, activity indicator
- Clicking a student → full profile view with recent submissions from rawData

### Activity Indicator
- Green: submitted within configured threshold (default 7 days)
- Red: no submission within threshold

---

## Export (Excel)

- Staff selects filters (department, year, section — department + year mandatory)
- Staff selects fields to include (register number, name, platform stats, handles)
- System generates and downloads Excel file
- Sorted by register number ascending by default
- Filename includes date: `profiles_YYYY-MM-DD.xlsx`

---

## Schema (MVP)

### users
```
id              UUID
email           String (unique, validated against institution domain)
password        String (bcrypt hashed)
role            Enum: MASTER, INSTITUTION_ADMIN, STAFF, STUDENT
institutionId   FK → institutions (nullable for MASTER)
createdAt       DateTime
```

### institutions
```
id              Long (auto increment)
name            String
domain          String (unique — e.g. trp.srmtrichy.edu.in)
status          Enum: PENDING, APPROVED, REJECTED
adminId         FK → users
createdAt       DateTime
```

### student_profile
```
id              Long (auto increment)
userId          FK → users (unique)
fullName        String
registerNumber  String (unique within institution)
department      String
year            Integer (1-4)
section         String
trainingBatch   String
phone           String
personalEmail   String
createdAt       DateTime
updatedAt       DateTime
```

### student_handle
```
id              Long (auto increment)
profileId       FK → student_profile
platform        Enum: LEETCODE, CODEFORCES, GITHUB, HACKERRANK, GFG, HACKEREARTH
username        String
verified        Boolean (default false)
usernameUpdatedAt DateTime
UNIQUE (profileId, platform)
```

### handle_stats
```
id              Long (auto increment)
handleId        FK → student_handle (unique — one stats row per handle)
problemsSolved  Integer
easySolved      Integer
mediumSolved    Integer
hardSolved      Integer
rating          Integer
globalRank      Integer
lastSubmissionAt DateTime
rawData         JSONB
lastSyncedAt    DateTime
```

---

## API Endpoints (MVP)

### Auth
```
POST /api/auth/register
POST /api/auth/login
GET  /api/auth/verify?token=...
POST /api/auth/forgot-password      (v2)
```

### Institution (Master only)
```
GET    /api/master/institutions          list all pending/approved
PATCH  /api/master/institutions/{id}     approve or reject
```

### Institution Admin
```
GET    /api/admin/users                  list users in institution
PATCH  /api/admin/users/{id}/role        promote to STAFF
DELETE /api/admin/institution            delete institution
```

### Student Profile
```
POST   /api/profile                      create profile
GET    /api/profile/me                   get own profile
PATCH  /api/profile/me                   update own profile
```

### Handles
```
POST   /api/handles                      add a handle
GET    /api/handles/me                   get own handles
PATCH  /api/handles/{platform}           update a handle username
DELETE /api/handles/{platform}           remove a handle
```

### Dashboard (Staff + Admin)
```
POST   /api/dashboard                    filtered + sorted leaderboard
GET    /api/dashboard/student/{id}       full student detail
POST   /api/export/excel                 export filtered data
```

---

## What is NOT in MVP

These are intentionally excluded from v1:

- Password reset flow
- Handle verification (added only if free API exists per platform)
- Daily stats history / trend charts
- Cross-institution comparisons
- Student graduation / archiving
- Bulk student import
- Subscription / payment system
- Contest tracking
- GitHub contribution graph

---

## v2 Planned Features

- Password reset via email
- Daily stats snapshots for 7-day trend view
- GitHub contribution stats
- Contest history per student
- Institution-level analytics (avg rating, active % etc.)
- Bulk import students via Excel upload
- Handle verification via email confirmation

---

## v3 / Future (SaaS)

- Subscription tiers (free / paid)
- Paid handle verification features
- Cross-institution leaderboard (opt-in)
- Public student profile page
- API for third-party integrations

---

## Open Decisions (resolve before building)

- [ ] Which platforms to support in MVP? Suggested: LeetCode, Codeforces, GitHub
- [ ] Which platforms have free public APIs? Verify before building sync job
- [ ] Does GFG have a usable public API or does it require scraping?
- [ ] Register number extraction from email — is this SRM-specific or configurable?
- [ ] Activity threshold (7 days) — hardcoded or per-institution config?
