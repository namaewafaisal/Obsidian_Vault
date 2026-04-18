# SQL Learning Roadmap

> **Goal:** Go from zero to confidently writing production-level SQL — with proof you can back it up.
> **Stack assumption:** PostgreSQL (transferable to MySQL/MariaDB with minor syntax differences)

---

## How to use this roadmap

- Work through phases in order — each one builds on the last.
- Each phase has a **"Test your skills"** section — don't move on until you can do those tasks cold.
- The final section tells you **what "good at SQL" actually looks like** and how to prove it.

---

## Phase 0 — Setup

- [ ] Install PostgreSQL locally (or use [neon.tech](https://neon.tech) / [supabase.com](https://supabase.com) free tier)
- [ ] Install a GUI client: DBeaver or pgAdmin
- [ ] Learn how to run `.sql` files from the terminal (`psql -f file.sql`)
- [ ] Bookmark: [PostgreSQL official docs](https://www.postgresql.org/docs/current/)

---

## Phase 1 — Foundations

### Core Concepts
- [ ] What is a relational database? Tables, rows, columns, schema
- [ ] Primary keys and foreign keys
- [ ] Data types: `INT`, `VARCHAR`, `TEXT`, `BOOLEAN`, `DATE`, `TIMESTAMP`, `NUMERIC`
- [ ] NULL vs empty string — why it matters

### DDL (Data Definition Language)
- [ ] `CREATE TABLE` with constraints (`NOT NULL`, `UNIQUE`, `DEFAULT`, `CHECK`)
- [ ] `ALTER TABLE` — add/drop/rename columns, change types
- [ ] `DROP TABLE`, `TRUNCATE` vs `DELETE`
- [ ] `CREATE INDEX` basics

### DML (Data Manipulation Language)
- [ ] `INSERT INTO` — single row, multi-row, `INSERT ... SELECT`
- [ ] `UPDATE` with `WHERE`
- [ ] `DELETE` with `WHERE`
- [ ] `SELECT` basics — `*`, specific columns, aliases (`AS`)

### Filtering & Sorting
- [ ] `WHERE` clause — ` =`, `!=`, `>`, `<`, `BETWEEN`, `IN`, `NOT IN`
- [ ] `LIKE` and `ILIKE` — pattern matching with `%` and `_`
- [ ] `IS NULL` / `IS NOT NULL`
- [ ] `ORDER BY` — `ASC`, `DESC`, multiple columns
- [ ] `LIMIT` and `OFFSET`

### ✅ Phase 1 Test
> Without looking anything up, write SQL to:
> 1. Create a `students` table with id, name, email (unique), age, enrolled_at
> 2. Insert 5 rows
> 3. Fetch all students older than 20, ordered by name
> 4. Update the email of one student
> 5. Delete students where age is NULL

---

## Phase 2 — Aggregation & Grouping

- [ ] Aggregate functions: `COUNT`, `SUM`, `AVG`, `MIN`, `MAX`
- [ ] `GROUP BY` — how it works, what goes in SELECT
- [ ] `HAVING` — filtering after aggregation (vs `WHERE`)
- [ ] `DISTINCT` — deduplication
- [ ] `COUNT(*)` vs `COUNT(column)` — know the difference
- [ ] `ROUND`, `CEIL`, `FLOOR` for numeric output

### ✅ Phase 2 Test
> Using an `orders` table (order_id, customer_id, amount, status, created_at):
> 1. Count total orders per customer
> 2. Find customers whose total spend exceeds 10,000
> 3. Get the average order amount per status, rounded to 2 decimal places
> 4. Find the top 5 customers by total spend

---

## Phase 3 — Joins

This is where most people struggle. Spend the most time here.

- [ ] `INNER JOIN` — only matching rows
- [ ] `LEFT JOIN` — all from left, NULLs from right where no match
- [ ] `RIGHT JOIN` — all from right (less common, prefer LEFT)
- [ ] `FULL OUTER JOIN` — all rows from both
- [ ] `CROSS JOIN` — cartesian product (rare but good to know)
- [ ] Self-join — joining a table to itself
- [ ] Joining on multiple conditions
- [ ] Multi-table joins (3+ tables)
- [ ] The difference between `ON` and `USING`

### ✅ Phase 3 Test
> Tables: `users(id, name)`, `orders(id, user_id, amount)`, `products(id, name)`, `order_items(order_id, product_id, qty)`
> 1. Get all users and their order count (include users with zero orders)
> 2. Find products that have never been ordered
> 3. List each user with their most recent order amount
> 4. Get total quantity ordered per product with product name

---

## Phase 4 — Subqueries & CTEs

- [ ] Subquery in `WHERE` — `IN`, `EXISTS`, `NOT EXISTS`
- [ ] Correlated subquery — references outer query
- [ ] Subquery in `FROM` (derived table / inline view)
- [ ] Subquery in `SELECT` (scalar subquery)
- [ ] `WITH` clause — Common Table Expressions (CTEs)
- [ ] Chaining multiple CTEs
- [ ] Recursive CTEs — for tree/graph traversal (hierarchy, org charts)
- [ ] When to use CTE vs subquery vs temp table

### ✅ Phase 4 Test
> 1. Find all users who placed more orders than the average number of orders per user
> 2. Rewrite #1 as a CTE — make it readable
> 3. Given an `employees(id, name, manager_id)` table, use a recursive CTE to print the full hierarchy under a given manager

---

## Phase 5 — Window Functions

Window functions are what separate intermediate SQL from advanced SQL.

- [ ] What is a window function? `OVER()` clause basics
- [ ] `PARTITION BY` — scoping the window
- [ ] `ORDER BY` inside `OVER()`
- [ ] Ranking functions: `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`
- [ ] `NTILE(n)` — bucket rows into n groups
- [ ] Offset functions: `LAG()`, `LEAD()` — access previous/next row
- [ ] `FIRST_VALUE()`, `LAST_VALUE()`, `NTH_VALUE()`
- [ ] Aggregate window functions: `SUM() OVER`, `AVG() OVER`, `COUNT() OVER`
- [ ] Frame clause: `ROWS BETWEEN`, `RANGE BETWEEN`
- [ ] Running totals, moving averages

### ✅ Phase 5 Test
> Using `sales(salesperson_id, region, amount, sale_date)`:
> 1. Rank salespersons by total sales within each region
> 2. Calculate a 7-day rolling average of daily sales
> 3. For each sale, show the difference from the previous sale by the same salesperson
> 4. Show each salesperson's contribution as a % of their region's total

---

## Phase 6 — Indexes & Performance

- [ ] What is an index? How B-tree indexes work (conceptually)
- [ ] `CREATE INDEX` — single column, composite
- [ ] `UNIQUE INDEX`
- [ ] `EXPLAIN` and `EXPLAIN ANALYZE` — read query plans
- [ ] Seq Scan vs Index Scan vs Index Only Scan
- [ ] When indexes hurt (write overhead, small tables)
- [ ] Partial indexes (`WHERE` clause on index)
- [ ] Expression indexes
- [ ] The N+1 problem — what it is and how SQL solves it
- [ ] Query optimization basics: avoid `SELECT *`, push filters early, avoid functions on indexed columns in WHERE

### ✅ Phase 6 Test
> 1. Take a slow query from Phase 3 or 4. Run `EXPLAIN ANALYZE` on it. Identify the bottleneck.
> 2. Add an appropriate index. Run again. Observe the difference.
> 3. Write a query that demonstrates the N+1 problem, then fix it with a JOIN.

---

## Phase 7 — Transactions & Integrity

- [ ] `BEGIN`, `COMMIT`, `ROLLBACK`
- [ ] ACID properties — know what each means concretely
- [ ] `SAVEPOINT` and partial rollback
- [ ] Isolation levels: Read Uncommitted, Read Committed, Repeatable Read, Serializable
- [ ] Dirty reads, phantom reads, non-repeatable reads
- [ ] `ON CONFLICT` (upsert) — `DO NOTHING`, `DO UPDATE`
- [ ] Foreign key constraints and cascades: `ON DELETE CASCADE`, `SET NULL`, `RESTRICT`
- [ ] `CHECK` constraints and domain integrity

### ✅ Phase 7 Test
> 1. Simulate a bank transfer between two accounts in a transaction — ensure atomicity
> 2. Write an upsert that inserts a user or updates their last_login if they already exist
> 3. Explain what happens with `ON DELETE CASCADE` vs `RESTRICT` in your own words with an example

---

## Phase 8 — Schema Design

- [ ] Normalization: 1NF, 2NF, 3NF (understand, don't memorize definitions)
- [ ] When to denormalize deliberately
- [ ] One-to-one, one-to-many, many-to-many relationships
- [ ] Junction/bridge tables
- [ ] Surrogate keys vs natural keys
- [ ] Soft deletes (`deleted_at` timestamp pattern)
- [ ] Audit columns: `created_at`, `updated_at` with defaults and triggers
- [ ] Naming conventions: snake_case, plural table names
- [ ] Designing for querying — think about how data will be read

### ✅ Phase 8 Test
> Design a schema for a simple e-commerce system from scratch:
> - Users, Products, Categories (many-to-many), Orders, Order Items
> - Include constraints, indexes, and audit columns
> - Write the `CREATE TABLE` statements in order (respect FK deps)

---

## Phase 9 — PostgreSQL-Specific & Production Patterns

- [ ] `SERIAL` vs `BIGSERIAL` vs `gen_random_uuid()`
- [ ] `JSONB` column — store and query semi-structured data
- [ ] Array columns
- [ ] `COALESCE`, `NULLIF`, `GREATEST`, `LEAST`
- [ ] String functions: `CONCAT`, `TRIM`, `LOWER`, `UPPER`, `SPLIT_PART`, `REGEXP_REPLACE`
- [ ] Date functions: `NOW()`, `CURRENT_DATE`, `AGE()`, `DATE_TRUNC`, `EXTRACT`, `INTERVAL`
- [ ] `CASE WHEN` expressions — conditional logic in SELECT
- [ ] Views — `CREATE VIEW`, updatable views
- [ ] Materialized views — when and why
- [ ] Stored procedures and functions (basics)
- [ ] Triggers (basics — audit logging use case)
- [ ] `COPY` command for bulk import/export (CSV)

---

## Practice Platforms

| Platform | What to use it for |
|---|---|
| [SQLZoo](https://sqlzoo.net) | Phase 1–3 drilling |
| [LeetCode SQL](https://leetcode.com/problemset/database/) | Phase 3–5 problem solving |
| [StrataScratch](https://www.stratascratch.com) | Real-world interview questions |
| [pgexercises.com](https://pgexercises.com) | PostgreSQL-specific practice |
| [Mode SQL Tutorial](https://mode.com/sql-tutorial/) | Window functions deep dive |
| [HackerRank SQL](https://www.hackerrank.com/domains/sql) | Beginner–intermediate range |

**LeetCode SQL problems to target (in order):**
- [ ] 175 – Combine Two Tables
- [ ] 176 – Second Highest Salary
- [ ] 177 – Nth Highest Salary
- [ ] 178 – Rank Scores
- [ ] 180 – Consecutive Numbers
- [ ] 184 – Department Highest Salary
- [ ] 185 – Department Top Three Salaries
- [ ] 197 – Rising Temperature
- [ ] 262 – Trips and Users
- [ ] 569 – Median Employee Salary
- [ ] 601 – Human Traffic of Stadium

---

## What "Good at SQL" Actually Looks Like

You're not just good at SQL when you can write a correct query. You're good when:

**Speed & Fluency**
- [ ] You can write a multi-table JOIN with aggregation without trial and error
- [ ] You reach for CTEs to break complex problems into readable steps automatically
- [ ] You know which aggregate/window function to use without Googling

**Correctness Instincts**
- [ ] You instinctively check for NULLs in joins and aggregations
- [ ] You know when `COUNT(*)` and `COUNT(col)` give different answers — and why
- [ ] You can spot whether a query will return duplicates before running it

**Performance Awareness**
- [ ] You read `EXPLAIN ANALYZE` output and know what Seq Scan means
- [ ] You know which columns deserve indexes in a schema you design
- [ ] You don't write correlated subqueries where a JOIN would be faster

**Design Sense**
- [ ] You can design a normalized schema for a real feature from scratch
- [ ] You think about query patterns when designing tables, not just storage

---

## How to Prove It

### For interviews
- Solve 30+ LeetCode SQL (Easy + Medium) — hit Medium comfortably
- Be able to whiteboard any join type and explain with an example
- Explain your College Identity System's schema — the joins, the indexes, why you made certain decisions

### For your portfolio / resume
- [ ] **College Identity System** — the schema you've already built is proof. Document it: ER diagram, key queries used for filtering/export, indexes added and why.
- [ ] Write a blog post or Obsidian note: "How I optimized the student filter query" — show before/after `EXPLAIN ANALYZE`
- [ ] Put a `schema.sql` file in your GitHub repo with proper constraints, comments, indexes

### Milestone checkpoints
- [ ] **Phase 3 done** → You're ahead of most CS students
- [ ] **Phase 5 done** → You can handle most backend engineering SQL interviews
- [ ] **Phase 6 done** → You can have a real conversation about production databases
- [ ] **All phases + 30 LeetCode SQL** → You can confidently put "PostgreSQL" on your resume without anxiety

---

> Start with Phase 1. Don't skip the tests. The test is the point.