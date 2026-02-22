---
topic: queue_linkedlist
category: linear
structure: pointer_based
data: homogeneous_or_generic
mutability: dynamic
access: FIFO
priority: high
difficulty: intermediate
status: foundation
used_in:
  - unbounded_queue
  - streaming_systems
tags:
  - dsa
  - queue
  - linked_list
---

# Queue Using Linked List — Head & Tail Model

## Structural Model

State:

```
Node<E> head  (front)
Node<E> tail  (rear)
int size
```

---

## Invariants

Empty:
```
head == null
tail == null
size == 0
```

Non-empty:
```
tail.next == null
```

---

## Enqueue

```
tail.next = newNode
tail = newNode
size++
```

If empty:
```
head = tail = newNode
```

---

## Dequeue

```
value = head.data
head = head.next
size--
```

If last element removed:
```
tail = null
```

---

## Strengths

- No fixed capacity
- Simple pointer logic
- O(1) operations

---

## Weaknesses

- Extra object per element
- Poor cache locality
- More GC pressure

---

## Linked Notes

- [[Queue]]
- [Singly_Linked_List](Leetcode/DSA/Data-Structues/Singly_Linked_List.md)