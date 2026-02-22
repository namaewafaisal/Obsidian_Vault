---
topic: queue
category: linear
structure: restricted_access
data: homogeneous_or_generic
mutability: dynamic
access: FIFO
priority: high
difficulty: basic
status: foundation
used_in:
  - bfs
  - scheduling
  - buffering
  - producer_consumer
tags:
  - dsa
  - queue
  - fifo
---

# Queue — Abstract Data Type (FIFO Discipline)

## 0. The real problem this structure solves

Some systems require:

- First inserted element removed first.
- Order preservation under processing.

This behavior is:

**FIFO — First In, First Out**

**One-line anchor:**  
> Queue preserves chronological order of insertion.

---

## 1. Queue Is Behavioral, Not Structural

Defined by operations:

- enqueue(E)
- dequeue()
- peek()
- isEmpty()
- size()

Access only allowed at:
- rear (insertion)
- front (removal)

---

## 2. Core Invariants

- Insert at rear only.
- Remove from front only.
- No middle access.
- If empty → dequeue invalid.

---

## 3. Time Complexity Requirements

| Operation | Required Time |
|------------|--------------|
| enqueue | O(1) |
| dequeue | O(1) |
| peek | O(1) |

---

## 4. Real-World Usage

Used in:

- BFS traversal
- CPU scheduling
- Print spooling
- Network buffering
- Task pipelines

---

## 5. Implementation Variants

- [Queue_Array_Naive](Queue_Array_Naive.md)
- [Queue_Circular_Array](Queue_Circular_Array.md)
- [Queue_LinkedList](Queue_LinkedList.md)
- [Queue_Java_Collections](Queue_Java_Collections)

---

## 6. Final Mental Model

> Queue enforces order by restricting both ends separately.