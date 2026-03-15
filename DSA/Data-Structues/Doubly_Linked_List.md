---
topic: doubly_linked_list
category: linear
structure: pointer_based
data: homogeneous_or_generic
mutability: dynamic_structure
access: bidirectional_sequential
priority: high
difficulty: intermediate
status: foundation
used_in:
  - java_linkedlist
  - deque_structure
  - lru_cache
  - undo_redo_systems
tags:
  - dsa
  - linked_list
  - pointer
  - bidirectional
---

# Doubly Linked List — Fundamentals (Bidirectional Links, Invariants, Trade-offs)

## 0. The real problem this structure solves

Singly linked lists cannot:
- Traverse backward
- Delete a node efficiently without searching for its previous node

Doubly linked lists add backward reference.

**One-line anchor:**  
> Doubly linked list enables local deletion and bidirectional traversal.

---

## 1. What a doubly linked list really is

Each node stores:
- data
- reference to next node
- reference to previous node

List maintains:
- reference to first node
- reference to last node

**One-line anchor:**  
> Every node knows both its neighbors.

---

## 2. Node structure (minimal form)

```java
private static class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;

    Node(E data) {
        this.data = data;
    }
}
```

List:

```java
private Node<E> first;
private Node<E> last;
```

---

## 3. Memory model

Heap reality:

```
null <- A <-> B <-> C -> null
```

Each node:
- independent heap object
- connected in two directions

Memory is still non-contiguous.

---

## 4. Structural invariants (critical)

For non-empty list:

```
first.prev == null
last.next == null
node.next.prev == node
node.prev.next == node
```

Breaking either forward or backward link corrupts structure.

**One-line anchor:**  
> Forward and backward integrity must both hold.

---

## 5. Insert operations

### Insert at Beginning (O(1))

```java
newNode.next = first;
first.prev = newNode;
first = newNode;
```

### Insert at End (O(1))

```java
last.next = newNode;
newNode.prev = last;
last = newNode;
```

---

## 6. Delete operations

### Delete Middle Node (O(1) if node known)

```java
curr.prev.next = curr.next;
curr.next.prev = curr.prev;
```

No need to search for previous node.

---

## 7. Time complexity summary

| Operation | Time |
|------------|------|
| Insert at head | O(1) |
| Insert at tail | O(1) |
| Delete head | O(1) |
| Delete tail | O(1) |
| Delete given node | O(1) |
| Search | O(n) |

---

## 8. Strengths

- Bidirectional traversal
- Efficient removal when node known
- Efficient deque operations
- Cleaner deletion logic

---

## 9. Weaknesses

- Extra memory per node (prev pointer)
- More pointer updates per operation
- Higher bug risk
- Still no random access

---

## 10. Common bugs

- Forgetting to update `prev`
- Updating `next` but not `prev`
- Incorrect handling of head/tail
- Breaking invariant during insert/delete

Example bug:

```java
curr.prev.next = curr.next;
// forgot: curr.next.prev = curr.prev
```

Backward traversal breaks silently.

---

## 11. When to use

Use doubly linked list when:

- Need bidirectional traversal
- Implementing deque
- Building LRU cache
- Undo/redo systems

---

## 12. When NOT to use

Avoid when:

- Memory usage is critical
- Random access is frequent
- High-performance iteration needed

ArrayList often faster due to cache locality.

---

## 13. Backend relevance

Java `LinkedList<E>` is implemented as a doubly linked list.

Also used in:
- LRU cache implementations
- OS task scheduling lists
- Browser history

---

## 14. Final mental model

> Doubly linked list trades extra memory for deletion flexibility and bidirectional traversal.

---

## 15. Linked notes

- [[Singly_Linked_List]]
- [[Circular_Linked_List]]
- [[Deque_Concept]]
- [[LRU_Cache_Design]]
