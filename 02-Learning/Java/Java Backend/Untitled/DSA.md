Below is the **authoritative, drop-in file content** you can place inside your **MASTER_ROADMAP project** (for example:
`DSA_WORKFLOW_AND_METHOD.md`).

This is **not a plan** and **not topic notes**.
This document captures **how DSA is done**, **how you operate**, **how I assist**, and **the invariants that must never be violated**, so future chats can re-align instantly without losing context.

---

# DSA — Operating Model & Collaboration Contract

*(Authoritative Project Context File)*

## 1. Purpose of This File

This document defines the **method**, **rules**, and **interaction contract** for all DSA work inside the MASTER_ROADMAP project.

It exists to ensure that:

* Context is never lost across chats
* Progress is cumulative, not repetitive
* The same mental model is enforced consistently
* DSA work remains interview-grade, not academic or tutorial-driven

This file is **referenced**, not re-explained, in future DSA conversations.

---

## 2. Core Objective (Invariant)

DSA is learned **only** to achieve the following end state:

* Medium problems → solved consistently
* Hard problems → partial or full solutions with correct reasoning
* Ability to **explain why a solution works**, not just how to code it

Knowing topics, patterns, or tricks **without reasoning** is considered failure.

---

## 3. Roles & Responsibilities

### 3.1 Your Role (Primary Actor)

You are responsible for:

* Actively attempting problems before asking for help
* Articulating your current thinking, even if incomplete
* Respecting the fixed topic order
* Treating confusion as a signal to slow down, not skip ahead
* Maintaining post-analysis notes for every solved problem

You are **not** expected to:

* Know the optimal solution immediately
* Memorize patterns
* Jump to advanced topics early

---

### 3.2 My Role (System Guide)

I function as:

* A **thinking validator**, not a solution dispenser
* A **pattern extractor**, not a shortcut provider
* A **post-analysis enforcer**, not just a code helper

I will:

* Force explicit reasoning before solutions
* Correct flawed mental models early
* Tie each problem back to a reusable invariant
* Preserve and reinforce your established method

I will **not**:

* Provide full solutions prematurely
* Allow pattern memorization without understanding
* Let problems “count” without proper analysis

---

## 4. Skill Level Framework (Used Everywhere)

Each topic and problem is evaluated using this scale:

* **L0 – Aware**: Knows the name only
* **L1 – Can implement**: Codes after seeing pattern
* **L2 – Can derive**: Reaches solution through reasoning
* **L3 – Can generalize**: Adapts pattern to variants

**Rule:**
A topic is considered *complete* only at **L2 minimum**.

Anything below L2 is treated as **in progress**, regardless of problem count.

---

## 5. Fixed Topic Order (Non-Negotiable)

Topics are learned strictly in this order:

### Phase 1 — Foundations

Arrays → Strings → Recursion → Hashing → Two Pointers → Sliding Window

### Phase 2 — Linear Structures

Linked List → Stack → Queue → Monotonic Stack/Queue → Prefix Sum & Difference Array

### Phase 3 — Trees

Binary Tree → BST → Traversals → LCA → Tree DP basics

### Phase 4 — Searching & Ordering

Binary Search → Sorting → Greedy

### Phase 5 — Graphs

Graph Representation → BFS/DFS → Topological Sort → Shortest Path → DSU

### Phase 6 — Dynamic Programming

1D DP → 2D DP → Knapsack → DP on Strings → DP on Trees → State Optimization

Skipping or reordering **breaks the model**.

---

## 6. Mandatory Problem-Solving Protocol

Every problem must explicitly go through **all four steps**:

1. **Input constraints**
2. **Brute force approach + time complexity**
3. **Optimization lever**
   (What is too slow? What must be reduced?)
4. **Pattern identification**
   (Known, hybrid, or new)

If any step is skipped, the problem **does not count**, even if solved.

---

## 7. Coding Standards (Interview-Grade)

All code must follow these rules:

* Language: **Java**
* No unnecessary classes or abstractions
* Clear, intention-revealing variable names
* One function = one responsibility
* Edge cases handled explicitly
* Readability > cleverness

This mirrors real interview expectations.

---

## 8. Problem Lifecycle (How a Problem Is Treated)

A problem is considered **fully learned** only when:

1. You solved or partially solved it through reasoning
2. A post-analysis note exists (thinking log + invariant)
3. You can re-solve it **without hints after ~7 days**

Only then is the problem considered **locked**.

---

## 9. Anti-Patterns (Hard Stops)

The following invalidate learning:

* Jumping to DP early
* Memorizing solutions
* Watching solution videos first
* Skipping post-analysis
* Measuring progress by number of problems solved

If detected, we **pause and correct**, not proceed.

---

## 10. Note-Taking Philosophy

Notes are:

* **Analytical**, not descriptive
* Focused on *why the solution works*
* Structured around:

  * Key invariant
  * Failure of naive approaches
  * Decision points

Problem notes (e.g., LC 560) are treated as **canonical references** for that pattern.

---

## 11. Relationship Between DSA and Projects

DSA is not isolated:

* Arrays / Hashing → API data handling
* Trees / Graphs → system design thinking
* DP → optimization mindset
* Stack / Queue → concurrency & async reasoning

This is why DSA remains **inside the MASTER_ROADMAP**, not a separate effort.

---

## 12. Change Control Rule

This DSA operating model:

* Changes **only when you explicitly request**
* Is treated as the **source of truth**
* Is referenced in future chats instead of being re-defined

---

## 13. Operating Principle

Progress is defined as:

> “I understand *why* this works, and I can derive it again.”

Anything else is noise.

---

If you want, next we can:

* Condense this into a **one-page invariant checklist**
* Create a **problem note template** that strictly enforces this method
* Add a **self-review gate** before moving between topics

You decide the next controlled step.
