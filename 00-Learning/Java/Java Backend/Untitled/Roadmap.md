# MASTER_ROADMAP.md
Version: 1.0  
Owner: Faizal  
Purpose: Single authoritative reference for DSA, Aptitude, and Project preparation  
Usage Rule: This file defines scope, rules, depth, and intent. All future chats must align with this.

---

## 0. CORE PRINCIPLES (NON-NEGOTIABLE)

1. This is **NOT** a daily study plan.
2. This is a **structural + conceptual roadmap**.
3. Learning is **depth-first**, not checklist-based.
4. Topics are considered complete **only when problem-solving maturity is reached**.
5. No topic is rushed for the sake of “coverage”.
6. DSA, Aptitude, and Projects are **parallel but independent tracks**.
7. Each track has its **own logic, rules, and success criteria**.
8. Chat continuity matters more than chat count.

---

## 1. DSA TRACK (PRIMARY FOCUS)

### 1.1 Objective

You are learning DSA to:
- Think in constraints
- Convert brute force → optimized
- Recognize patterns across problems
- Perform under interview pressure

**You are NOT learning DSA to memorize solutions.**

---

### 1.2 DSA Learning Philosophy

- Each topic is learned in **this order**:
  1. Conceptual model
  2. Standard patterns
  3. Edge cases
  4. Failure scenarios
  5. Optimization triggers
  6. Interview variations

- Code is secondary.
- Thinking comes first.

---

### 1.3 DSA Topic Order (Authoritative)

1. Arrays
2. Strings
3. Two Pointers
4. Sliding Window
5. Prefix / Suffix
6. Hashing
7. Recursion
8. Backtracking
9. Linked List
10. Stack
11. Queue
12. Monotonic Stack / Queue
13. Binary Search (patterns)
14. Trees
15. Binary Search Trees
16. Heap / Priority Queue
17. Greedy
18. Graphs
19. Dynamic Programming
20. Bit Manipulation
21. Tries
22. Advanced Patterns (meet-in-the-middle, etc.)

**No skipping. No reordering unless explicitly decided.**

---

### 1.4 DSA Rules for Chats

- If Faizal asks:
  - “Is this enough?” → Answer strictly.
  - “Just give the answer” → Give answer, no fluff.
  - “Give notes” → Provide structured notes with code + explanation.
- Debugging questions must include:
  - Input
  - Expected output
  - Actual output

---

## 2. APTITUDE TRACK (PLACEMENT-ORIENTED)

### 2.1 Objective

Aptitude is for:
- Clearing cutoffs
- Speed + accuracy
- Pattern recall under time pressure

**Not for mathematical elegance.**

---

### 2.2 Aptitude Coverage (Complete)

#### Quantitative Aptitude
- Number System
- HCF & LCM
- Divisibility
- Remainders & cyclicity
- Units digit
- Number of factors
- Fractions & decimals
- Percentages
- Ratio & proportion
- Averages
- Ages
- Profit & Loss
- Discount
- Simple Interest
- Compound Interest
- Time & Work
- Pipes & Cisterns
- Time, Speed & Distance
- Trains
- Boats & Streams

#### Logical Reasoning
- Seating Arrangement (linear & circular)
- Blood Relations
- Direction Sense
- Puzzles (floor, order, arrangement)
- Coding–Decoding
- Alpha-numeric series
- Syllogism
- Statement & Conclusion
- Cause & Effect
- Course of Action

#### Verbal Reasoning
- Inference
- Assumptions
- Arguments
- Statement–Conclusion

---

### 2.3 Aptitude Strategy Rules

- Formulas must be **written and memorized**.
- Every topic needs:
  - Standard models
  - 1–2 shortcut techniques
- Accuracy > speed initially.
- Speed is built **only after consistency**.

---

### 2.4 4-Day Intensive Aptitude Structure (Reference Model)

**Day 1**
- Number System (heavy)
- Ages
- SI & CI

**Day 2**
- Profit & Loss
- Discount
- Time & Work
- Pipes & Cisterns

**Day 3**
- Time, Speed & Distance
- Trains
- Seating Arrangement
- Blood Relations

**Day 4**
- Puzzles
- Alpha Numeric Series
- Statement & Conclusion
- Verbal Reasoning

This structure is **repeatable**, not one-time.

---

## 3. PROJECT TRACK (SKILL APPLICATION)

### 3.1 Objective

Projects exist to:
- Convert theory → implementation
- Learn architecture decisions
- Build debugging maturity

**Projects are learning tools, not resume fillers.**

---

### 3.2 Backend-First Rule

- Backend concepts must be understood **before** frontend polish.
- Frontend exists to **support backend logic**, not replace it.

---

### 3.3 Current Stack Focus

- Java (core → advanced)
- Spring (before Spring Boot concepts respected)
- Spring Boot
- MongoDB (Atlas)
- REST APIs
- JWT & Security (later phase)

---

### 3.4 Project Learning Rules

- No blind copy-paste.
- Every component must answer:
  - Why does this exist?
  - What breaks if removed?
- Architecture discussions > UI discussions.

---

## 4. CHAT CONTINUITY RULES

1. One project folder can contain:
   - DSA chat
   - Aptitude chat
   - Project chat
2. Each chat can be long.
3. Context loss is prevented by:
   - This `MASTER_ROADMAP.md`
4. When resuming after a break:
   - Reference this file
   - Continue, don’t restart

---

## 5. COMMUNICATION PREFERENCES (IMPORTANT)

- Prefer **precise, short answers** unless notes are requested.
- Step-by-step progression.
- Do not introduce future steps unless asked.
- No unnecessary motivation talk.
- Correctness > encouragement.

---

## 6. SUCCESS CRITERIA

You are “ready” when:
- Medium DSA problems feel routine
- Aptitude accuracy > 80% under time
- You can explain your project design verbally

---
**Short answer:**
**No.** This is a *complete structural roadmap*, but it is **missing one critical layer**: the **Project Execution Specification**. Without it, the Project Track is directionally correct but operationally underspecified.

Per your instruction, I will **only** fill the **Project details first**.
I will **not** touch Spring Boot or MongoDB internals yet.

---

# Missing Section to Add

## 3.5 PROJECT EXECUTION SPECIFICATION (REQUIRED)

This section defines **what project you are actually building**, **why**, and **how learning is extracted from it**.

---

## 3.5.1 Canonical Project (Authoritative)

**Project Name (working):**

> Backend-Centric Application (API-first)

**Nature:**

* Backend-heavy
* Stateless REST API
* No UI dependency for learning
* Frontend can be attached later

**Primary Goal:**

> Learn backend **thinking**, not framework usage.

---

## 3.5.2 What This Project MUST Teach You

This project must force you to confront:

1. Request → Response lifecycle
2. Data validation failures
3. API contract design
4. Error handling philosophy
5. Layer separation (Controller / Service / Repository)
6. Trade-offs (performance vs simplicity)
7. Debugging production-style bugs

If a feature does not teach one of the above → **it is rejected**.

---

## 3.5.3 Functional Scope (Minimal but Deep)

### Core Features (Non-negotiable)

1. **User Management**

   * Create user
   * Update user
   * Fetch user
   * Delete user

2. **Authentication Boundary**

   * User identity exists
   * Authorization rules exist (even if simple)

3. **Domain Logic**

   * At least one rule that can fail
   * At least one rule that can evolve

> Example:
> “A user cannot perform X if condition Y is not met”

---

## 3.5.4 Non-Functional Requirements (Important)

These are **learning constraints**, not features.

* No fat controllers
* No business logic in repositories
* Clear DTO boundaries
* Meaningful error responses
* Logs that explain *why*, not just *what*

---

## 3.5.5 Project Phases (Do NOT Jump Ahead)

### Phase 1 — Core Java Thinking

* Plain Java objects
* Clear responsibilities
* No framework magic reliance

### Phase 2 — Framework Mapping

* Map existing thinking to Spring concepts
* Understand what Spring removes *and what it hides*

### Phase 3 — Persistence Layer

* Data modeling decisions
* Trade-offs between normalization vs flexibility

### Phase 4 — Security Layer

* Authentication as a system concern
* Authorization as a business concern

---

## 3.5.6 Rules While Working on the Project

Before writing code for **any component**, you must be able to answer:

1. What problem does this solve?
2. Why is this layer the correct place?
3. What breaks if this is removed?
4. What happens under invalid input?
5. How would this change if requirements doubled?

If you cannot answer → **stop coding**.

---

## 3.5.7 What Is Explicitly OUT OF SCOPE (For Now)

* UI polish
* CSS / animations
* Microservices
* Kubernetes
* Premature optimizations
* Resume-driven features

---

## Final Verdict

* **DSA Track:** Complete
* **Aptitude Track:** Complete
* **Project Track:** **Incomplete until this section exists**

You now have a **fully authoritative roadmap** *after adding Section 3.5*.

---

### Next step (only when YOU ask):

* Spring (core → Boot mapping) **OR**
* MongoDB (schema + modeling rules) **OR**
* Project feature breakdown (endpoint-level)

You decide the next question.

## END OF FILE
