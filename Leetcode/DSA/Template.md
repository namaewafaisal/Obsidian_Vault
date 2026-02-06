````notes
---
topic: ONE_MAIN_DS_ONLY            # array, linked-list, stack, tree, graph, etc. (exactly one)
category: linear | non-linear
structure: contiguous | non-contiguous
data: homogeneous | heterogeneous
mutability: fixed | dynamic
access: direct | sequential | restricted

priority: high | medium | low     # recall order (high = revise first)

importance_reason:
  - why this DS matters (1–3 bullets max)
  - practical relevance to DSA/problem solving

used_in:
  - major patterns or techniques this DS enables
  - eg: sliding window, recursion, bfs, dp, etc.

difficulty: basic | intermediate | advanced
status: foundation | stable | needs-review

tags:
  - dsa
  - <ds-name>
  - linear | non-linear
---

> [!NOTE] FOR CHATGPT (NON-NEGOTIABLE INSTRUCTIONS)
> - Enclose this ENTIRE note (from frontmatter to the end) in **ONE single 4-backtick code block**.
> - Do NOT use 4-backticks anywhere else inside.
> - Use normal **triple backticks** only for actual code blocks.
> - Follow **Obsidian Markdown** syntax strictly.
> - This is a **DSA CONCEPT NOTE**, not a LeetCode problem note.
> - Be concise, precise, and invariant-driven.
> - Prefer mental models over tutorials.
> - No motivational text. No filler.

# <DATA STRUCTURE NAME>

## Definition
- 1–2 lines
- Precise, technical, no fluff

---

## Core Insight

> The single trade-off or idea that defines this data structure

---

## Mental Model / Invariant

> One invariant or rule that is **always true**
> and explains why operations have their time complexity

If this invariant is unclear → set `status: needs-review`.

---

## Characteristics

- Key properties (memory, ordering, access)
- No more than 5 bullets

---

## Basic Operations & Time Complexity

| Operation | What happens internally | Time |
|---------|--------------------------|------|
|         |                          |      |

Focus on **why** the time complexity exists.

---

## Space Complexity

- Storage cost
- Auxiliary space (if any)

---

## Implementation (From Scratch – Java)

### Create / Initialize
```java
// minimal, canonical example
```

---

### Insert
```java
// only if meaningful for this DS
```

---

### Delete
```java
// only if meaningful for this DS
```

---

### Traverse / Access
```java
// core traversal logic
```

---

## Inbuilt / Library Usage (Java)

- Mention only **relevant** standard classes
- Explain briefly what abstraction they provide

```java
// example usage
```

---

## What Actually Matters (For Problems)

- What must be preserved (order, structure, indices, references)
- What does NOT matter
- What naive approaches usually get wrong

(3–5 bullets max.)

---

## Common Pitfalls

- Conceptual mistakes
- Implementation traps
- Boundary-condition errors

---

## Typical Problem Patterns

- Pattern → how this DS enables it
- No problem lists, only patterns

---

## When NOT to Use

- Clear anti-cases
- Situations where another DS is superior

---

## One-Line Recall

> One sentence that lets you reconstruct the entire data structure and its trade-offs from memory

````