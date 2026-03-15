---
topic: stack
category: linear
structure: restricted_access
data: homogeneous_or_generic
mutability: dynamic
access: LIFO
priority: high
difficulty: basic
status: foundation
used_in:
  - recursion
  - expression_evaluation
  - undo_redo
  - backtracking
  - call_stack
tags:
  - dsa
  - stack
  - lifo
---

# Stack — Abstract Data Type (LIFO Discipline)

## 0. The real problem this structure solves

Sometimes we need:

- Last inserted element to be removed first.
- Controlled access to only one end.

This behavior is called:

**LIFO — Last In, First Out**

**One-line anchor:**  
> Stack enforces order through access restriction.

---

## 1. Stack Is an Abstraction

A stack is not defined by memory layout.

It is defined by behavior:

- push(E)
- pop()
- peek()
- isEmpty()
- size()

Any structure that obeys these rules is a valid stack.

---

## 2. Core Invariants

- Only top element is accessible.
- No direct indexing allowed.
- push adds to top.
- pop removes from top.
- peek does not modify structure.

Breaking these turns it into a list.

---

## 3. Time Complexity Requirements

| Operation | Required Time |
|------------|--------------|
| push | O(1) |
| pop | O(1) |
| peek | O(1) |

If any becomes O(n), structure is poorly implemented.

---

## 4. Real-World Relevance

Used in:

- Call stack (function calls)
- DFS traversal
- Syntax parsing
- Undo/redo systems
- Backtracking

---

## 5. Implementation Variants

- [Stack_Array_Implementation](Stack_Array_Implementation.md)
- [Stack_LinkedList_Implementation](Stack_LinkedList_Implementation.md)
- [Stack_Java_Collections](Stack_Java_Collections)

---

## 6. Final Mental Model

> Stack is behavior-first, storage-second.