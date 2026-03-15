---
topic: queue_circular_array
category: linear
structure: contiguous_circular
data: homogeneous_or_generic
mutability: fixed_or_resizable
access: FIFO
priority: high
difficulty: intermediate
status: foundation
used_in:
  - ring_buffer
  - arraydeque_internal
tags:
  - dsa
  - queue
  - circular
---

# Circular Queue — Wrap-Around Model

## 0. Core Idea

Reuse array space by wrapping indices using modulo.

```
index = (index + 1) % capacity
```

---

## 1. Structural Model

State:

```
E[] arr
int front
int rear
int size
int capacity
```

---

## 2. Invariants

```
0 ≤ size ≤ capacity
rear = (front + size) % capacity
```

Empty:
```
size == 0
```

Full:
```
size == capacity
```

---

## 3. Enqueue

```
arr[rear] = value
rear = (rear + 1) % capacity
size++
```

---

## 4. Dequeue

```
value = arr[front]
front = (front + 1) % capacity
size--
```

---

## 5. Strengths

- No wasted space
- O(1) operations
- Cache-friendly

---

## 6. Weaknesses

- Slightly more complex invariants
- Needs careful modulo arithmetic

---

## Linked Notes

- [[Queue]]
- [[Stack_Array_Implementation]]