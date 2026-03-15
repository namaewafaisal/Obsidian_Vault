---
topic: circular_linked_list
category: linear
structure: pointer_based_cyclic
data: homogeneous_or_generic
mutability: dynamic_structure
access: cyclic_sequential
priority: medium
difficulty: intermediate
status: foundation
used_in:
  - round_robin_scheduling
  - circular_queue
  - multiplayer_turn_systems
tags:
  - dsa
  - linked_list
  - circular
---

# Circular Singly Linked List — Fundamentals (Cyclic Termination, Invariants)

## 0. The real problem this structure solves

Standard linked list ends with null.

Circular linked list removes null termination and creates a cycle.

**One-line anchor:**  
> Circular list replaces null termination with head termination.

---

## 1. What a circular linked list really is

It is a singly linked list where:

```
tail.next == head
```

There is no null at the end.

---

## 2. Node structure

```java
private static class Node<E> {
    E data;
    Node<E> next;
}
```

List maintains:

```java
private Node<E> head;
private Node<E> tail;
```

---

## 3. Structural invariant (most important)

For non-empty list:

```
tail.next == head
```

If this fails, structure is broken.

Single-node case:

```
head == tail
head.next == head
```

---

## 4. Traversal pattern

Cannot use:

```java
while(curr != null)
```

Must use:

```java
do {
    curr = curr.next;
} while(curr != head);
```

---

## 5. Insert at Beginning (O(1))

```java
newNode.next = head;
tail.next = newNode;
head = newNode;
```

---

## 6. Insert at End (O(1))

```java
tail.next = newNode;
newNode.next = head;
tail = newNode;
```

---

## 7. Delete at Beginning (O(1))

Single-node case:

```java
head = tail = null;
```

Multi-node case:

```java
head = head.next;
tail.next = head;
```

---

## 8. Strengths

- Efficient circular queue logic
- No null checks in traversal
- Natural cycle modeling

---

## 9. Weaknesses

- Infinite loop risk
- Harder debugging
- Less intuitive termination
- Rarely used directly in business systems

---

## 10. Common bugs

- Forgetting to update `tail.next`
- Incorrect single-node handling
- Using `while` instead of `do-while`
- Infinite traversal

---

## 11. When to use

Used in:

- Round robin scheduling
- Multiplayer turn systems
- Circular buffers
- Simulation systems

---

## 12. When NOT to use

Avoid when:

- Clear termination needed
- Simpler singly list suffices
- Debugging complexity must be low

---

## 13. Backend relevance

Often used conceptually in:

- Circular queue implementation
- Task schedulers
- Operating system time slicing

---

## 14. Final mental model

> Circular linked list removes null and replaces it with structural cycle.

---

## 15. Linked notes

- [[Singly_Linked_List]]
- [[Doubly_Linked_List]]
- [[Circular_Queue_Implementation]]
- [[Fast_Slow_Pointer_Technique]]
