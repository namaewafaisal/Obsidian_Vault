---
topic: queue_array_naive
category: linear
structure: contiguous
data: homogeneous_or_generic
mutability: fixed
access: FIFO
priority: medium
difficulty: basic
status: conceptual
tags:
  - dsa
  - queue
  - array
---

# Naive Array Queue — The Flawed Model

## Structural Model

State:

```
E[] arr
int front
int rear
```

Enqueue:
```
arr[++rear] = value
```

Dequeue:
```
value = arr[++front]
```

---

## The Problem

After multiple dequeues:

```
[ X X X 4 5 6 ]
```

Unused space exists at beginning,
but rear eventually reaches end → overflow.

This wastes memory.

---

## Why It Fails

Because:

- Indices only move forward.
- No reuse of freed slots.
- No wrap-around logic.

---

## Conclusion

Naive array queue is inefficient and not used in practice.

---

## Linked Notes

- [Queue_Circular_Array](Queue_Circular_Array.md)