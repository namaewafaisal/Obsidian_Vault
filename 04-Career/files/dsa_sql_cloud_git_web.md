# DSA Concepts, SQL, Cloud, Git/SDLC, Web & APIs — Interview Answers

## How to Answer
Same formula: definition first, mechanism/why second, example only if asked to elaborate.

---

# DSA — Explaining Concepts Out Loud

## Array vs Linked List
**Array** — contiguous memory block, direct index access (`arr[5]` jumps straight there via base address + offset calculation). Fast access, fixed size — resizing means allocating new memory and copying everything.

**Linked List** — chain of nodes, each holding data + a pointer to the next. Not contiguous; reaching element 5 means walking through 1-4 first, no shortcut. Insertion/deletion is cheap (rewire pointers); arrays might need to shift everything.

**One-liner trade-off:** arrays — fast random access, expensive resize/insert. Linked lists — slow access, cheap insert/delete.

## Stack vs Queue
- **Stack** — LIFO (Last In, First Out). Real-life: a stack of plates, take from the top. Real system: browser back button.
- **Queue** — FIFO (First In, First Out). Real-life: a ticket line. Real system: a printer's print queue.

## Time Complexity & Big-O
Describes how runtime *grows* as input size grows — not actual seconds, but the shape of scaling. Big-O is the formal notation for that growth, focused on worst case.

- **O(1)** — constant (array index access).
- **O(log n)** — grows very slowly, halves the problem each step (binary search).
- **O(n)** — proportional to input (simple loop).
- **O(n log n)** — typical efficient sorting (merge sort, average-case quicksort).
- **O(n²)** — grows as the square of input (nested loops, bubble sort).

## Binary Search — why O(log n)
Only works on sorted data. Checks the middle; discards the half that can't contain the target; repeats on the remaining half. Every comparison eliminates half of what's left — hence log₂(n).

## Choosing an efficient algorithm
Look at how runtime scales as input grows; prefer the lower growth curve when input could get large. O(n²) is fine for 10 items, bad for 10 million — this is why merge sort/quicksort beat bubble sort for large datasets.

---

# SQL — Writing Queries

## Query structure & execution order
Written order: `SELECT → FROM → WHERE → GROUP BY → HAVING → ORDER BY`
Actual execution order: `FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY`

## WHERE vs HAVING
**WHERE** filters individual rows, before grouping. **HAVING** filters groups, after `GROUP BY`, on aggregate results.

- "Students with marks above 80" → `WHERE marks > 80` (row-level).
- "Departments where average marks > 80" → `GROUP BY department HAVING AVG(marks) > 80` (can't use WHERE here — AVG doesn't exist until grouping happens).

## GROUP BY
Collapses rows sharing a column value into a single summary row, enabling aggregate functions per group.
```sql
SELECT department, COUNT(*) FROM employees GROUP BY department;
```

## Aggregate functions
`COUNT()`, `SUM()`, `AVG()`, `MIN()`, `MAX()` — collapse many rows into one value. These are what make `HAVING` necessary.

## Second highest salary
```sql
SELECT MAX(salary) AS second_highest
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```
Logic: find the max overall, then the max excluding that value — by definition the second highest. Correctly skips ties at the top.

Alternative:
```sql
SELECT salary FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;
```

## Removing duplicates
```sql
SELECT DISTINCT column_name FROM table_name;
```
`DISTINCT` removes duplicates from output only, doesn't touch the table. Deleting actual duplicate rows needs a subquery keeping one copy per group (often by lowest ID) and deleting the rest — fine to explain the idea even without perfect syntax.

## Primary key vs Foreign key (query context)
Foreign key is what makes a JOIN meaningful: `JOIN departments ON employees.dept_id = departments.id` works because `dept_id` (FK in employees) points to `id` (PK in departments).

---

# Cloud Computing

## What it is, and why it took over
Renting computing resources (storage, processing, databases) over the internet instead of buying/maintaining physical servers — pay for what you use, scale on demand instead of provisioning for peak load upfront. Already lived directly via Railway/Render deployments for CodeDash.

## IaaS vs PaaS vs SaaS
- **IaaS** — raw VMs, storage, networking; you manage OS/runtime yourself. (AWS EC2)
- **PaaS** — provider handles OS/runtime/infrastructure; you push code. (Render/Railway — exactly what's used for CodeDash's backend)
- **SaaS** — fully finished application, nothing managed by the user. (Gmail, Spotify)

**One-liner:** "IaaS gives you the building blocks, PaaS gives you a platform to deploy on without managing servers, and SaaS gives you the finished product."

## Deployment models
- **Public** — shared infrastructure, multiple customers (AWS/Azure/GCP standard). Cheaper, easy to scale.
- **Private** — dedicated infrastructure for one org. More control/security, more expensive (banks, govt).
- **Hybrid** — mix of both. Common in large enterprises — relevant to TCS specifically, since much of their client work is enterprise hybrid-cloud migration.

## Key benefits over on-premises
Scalability (resources on demand), cost (pay-as-you-go vs upfront capex), maintenance (provider handles hardware/patching), accessibility (available from anywhere).

## "How would you deploy a simple web app to the cloud?"
1. Push code to a Git repo.
2. Connect repo to a PaaS platform (Render).
3. Configure env variables/secrets and build settings.
4. Platform builds and deploys, gives a public URL.
5. Connect a managed database (Neon/Supabase) rather than self-hosting one.

---

# Git & SDLC

## Why Git
Version control system — tracks every code change over time, lets multiple people work without overwriting each other, allows rollback to any previous state.

## Git vs GitHub
**Git** — the actual version control tool, runs locally. **GitHub** — cloud platform that hosts Git repos, adds collaboration features (PRs, issues, review). Git works without GitHub.

## Branch
An independent line of development — modify code without touching the main/stable version. Create branch → work in isolation → merge back once tested.

## Pull Request
A request to merge one branch into another, reviewable before merging — the formal "ready to merge" checkpoint where code review happens.

## Merge Conflict
Happens when two branches change the same lines of the same file differently; Git can't auto-resolve, so a human picks which version to keep (or combines both), then commits.

## SDLC phases
Requirement gathering → Design → Implementation/Coding → Testing → Deployment → Maintenance.

**Mapped to CodeDash:** requirements (students need a way to register/validate CP handles) → design (schema + API structure) → implementation (Spring Boot backend) → testing (validating against live LeetCode/Codeforces data) → deployment (Railway/Render) → maintenance (ongoing, e.g. the Railway credit issue).

## Agile
Iterative short cycles ("sprints," 1-2 weeks) — build a piece, get feedback, adjust, repeat — instead of one long linear plan like Waterfall. Honest answer: no formal team-based Agile/sprint experience, but the build-test-iterate cycle naturally describes solo CodeDash development.

---

# Web & APIs

## What an API is
A defined contract letting one piece of software talk to another — rules for what requests you can send and what responses you'll get, without needing to know the other side's internals.

## REST
Architectural style for designing APIs around resources, using standard HTTP methods, with each request **stateless** — server doesn't remember previous requests, so every request carries everything it needs. (This is why JWT fits REST well — the token itself carries identity.)

## HTTP methods
- **GET** — retrieve, no side effects.
- **POST** — create new.
- **PUT** — update/replace entirely.
- **PATCH** — partial update.
- **DELETE** — remove.

## JSON
Lightweight, human-readable key-value/array data format — the default for API data exchange, simpler than XML and maps directly onto how most languages represent objects.

## Request-response cycle (tied to CodeDash)
Client sends request (method + endpoint + maybe body) → server runs logic (validate handle, check JWT auth, hit DB) → server responds (status code + JSON body).

## Status codes actually used
- **200** success
- **201** created (after successful POST)
- **400** bad request
- **401** unauthorized (missing/invalid JWT)
- **403** forbidden (valid auth, insufficient permission — RBAC)
- **404** not found
- **500** server error

**Example answer:** "In CodeDash's RBAC system, I return 401 when a JWT is missing or invalid, and 403 when someone's authenticated but lacks permission — like a student hitting an admin-only export endpoint."
