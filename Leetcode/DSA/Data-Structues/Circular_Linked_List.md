---
topic: circular_linked_list
category: linear
structure: pointer_based
termination: cyclic
memory: non_contiguous

priority: medium
difficulty: intermediate
status: foundation

depends_on:
  - singly_linked_list

used_in:
  - round_robin
  - circular_buffer
  - queue_implementation

tags:
  - dsa
  - linked_list
  - circular
---

# Circular Singly Linked List — Cyclic Structure

## 0. The real problem this structure solves

Standard linked list ends with null.

Circular list removes null termination.

**One-line anchor:**  
> No null termination — traversal stops at head.

---

## 1. Core Structure

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

## 2. Core Invariant

For non-empty list:

```
tail.next == head
```

If this is false, structure is broken.

---

## 3. Traversal Pattern

Must use `do-while`:

```java
Node<E> curr = head;
do {
    curr = curr.next;
} while(curr != head);
```

---

## 4. Time Complexity

Same as singly linked list:
- Insert at head → O(1)
- Insert at tail → O(1) (if tail stored)
- Traversal → O(n)

---

## 5. Edge Cases

- Single node: `head.next == head`
- Deleting last node resets both head and tail to null

---

## 6. Practical Importance

Used in:
- Round robin scheduling
- Circular queues
- Cycle detection problems

---

## Linked Notes

- [Singly_Linked_List](Singly_Linked_List)
- [Doubly_Linked_List](Doubly_Linked_List.md)
