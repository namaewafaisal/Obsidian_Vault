---
topic: doubly_linked_list
category: linear
structure: pointer_based
direction: bidirectional
memory: non_contiguous

priority: high
difficulty: intermediate
status: foundation

depends_on:
  - singly_linked_list

used_in:
  - java_linkedlist
  - lru_cache
  - deque

tags:
  - dsa
  - linked_list
  - pointer
  - bidirectional
---

# Doubly Linked List — Bidirectional Pointer Structure

## 0. The real problem this structure solves

Singly linked list cannot:
- traverse backward
- delete efficiently without previous reference

Doubly linked list fixes that.

**One-line anchor:**  
> Local deletion without searching for previous node.

---

## 1. Core Structure

```java
private static class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;
}
```

List maintains:

```java
private Node<E> first;
private Node<E> last;
```

---

## 2. Core Invariant

For non-empty list:

```
first.prev == null
last.next == null
node.next.prev == node
node.prev.next == node
```

Breaking either side corrupts structure.

---

## 3. Time Complexity

| Operation | Time |
|------------|------|
| Insert at head | O(1) |
| Insert at tail | O(1) |
| Delete at head | O(1) |
| Delete at tail | O(1) |
| Delete at position | O(n) (to find node) |

---

## 4. Key Operations

### Insert at Beginning

```java
newNode.next = first;
first.prev = newNode;
first = newNode;
```

### Insert at End

```java
last.next = newNode;
newNode.prev = last;
last = newNode;
```

### Delete Middle Node

```java
curr.prev.next = curr.next;
curr.next.prev = curr.prev;
```

---

## 5. Strengths

- Bidirectional traversal
- O(1) deletion when node known
- Efficient tail operations

---

## 6. Weaknesses

- Extra memory per node
- More pointer updates
- Higher bug risk

---

## 7. Real-World Usage

Java `LinkedList<E>` is implemented as a doubly linked list.

---

## Linked Notes

- [[Singly_Linked_List]]
- [[Circular_Linked_List]]
