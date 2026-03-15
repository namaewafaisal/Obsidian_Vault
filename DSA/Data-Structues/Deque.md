---
topic: deque
category: linear
structure: double_ended
data: homogeneous_or_generic
mutability: dynamic
access: bidirectional_restricted
priority: high
difficulty: intermediate
status: foundation
used_in:
  - sliding_window
  - bfs_variants
  - stack_queue_unification
  - caching
tags:
  - dsa
  - deque
  - double_ended
---

# Deque — Double-Ended Queue

## 0. The real problem this structure solves

Sometimes we need:

- Insert/remove at both ends
- Flexible boundary access
- To behave as either stack or queue

Deque provides this.

**One-line anchor:**  
> Deque = Queue + Stack in one structure.

---

## 1. Core Operations

Front side:
- addFirst(E)
- removeFirst()
- peekFirst()

Rear side:
- addLast(E)
- removeLast()
- peekLast()

---

## 2. Behavioral Power

Stack behavior:
```
addFirst()
removeFirst()
```

Queue behavior:
```
addLast()
removeFirst()
```

Both supported without structural change.

---

## 3. Structural Requirements

Deque needs:

- Two boundaries
- Either:
  - Circular array
  - Doubly linked list

Singly linked list is insufficient (no reverse traversal).

---

## 4. Array-Based Deque (Circular)

State:

```
E[] arr
int front
int rear
int size
```

Add front:

```
front = (front - 1 + capacity) % capacity
arr[front] = value
size++
```

Add rear:

```
arr[rear] = value
rear = (rear + 1) % capacity
size++
```

Remove front:

```
value = arr[front]
front = (front + 1) % capacity
size--
```

Remove rear:

```
rear = (rear - 1 + capacity) % capacity
value = arr[rear]
size--
```

---

## 5. Why Circular Array Is Ideal

- Contiguous memory
- O(1) both ends
- Cache-friendly
- Minimal object overhead

This is how `ArrayDeque` works internally.

---

## 6. Linked List Deque

Requires:

- Doubly linked list
- head & tail pointers

Singly list cannot efficiently remove from tail.

---

## 7. Performance Comparison

| Structure | Insert Front | Insert Rear | Remove Front | Remove Rear |
|------------|--------------|-------------|--------------|-------------|
| Stack | O(1) | — | O(1) | — |
| Queue | — | O(1) | O(1) | — |
| Deque | O(1) | O(1) | O(1) | O(1) |

Deque is strictly more powerful.

---

## 8. Java Usage

Preferred:

```java
Deque<E> dq = new ArrayDeque<>();
```

Methods:

- addFirst()
- addLast()
- removeFirst()
- removeLast()
- peekFirst()
- peekLast()

Avoid:
- `LinkedList` unless required
- `Stack` class

---

## 9. Deep Structural Insight

Stack → 1 boundary  
Queue → 2 boundaries (restricted direction)  
Deque → 2 boundaries (fully accessible)

Deque is the most general linear boundary structure.

---

## 10. When to Use

- Need both stack and queue behavior
- Sliding window problems (later)
- LRU cache (with map)
- Task scheduling variations

---

## Linked Notes

- [[Stack]]
- [[Queue]]
- [[Doubly_Linked_List]]