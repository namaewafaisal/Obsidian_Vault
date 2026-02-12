---
topic: singly_linked_list
category: linear
structure: pointer_based
direction: forward_only
memory: non_contiguous

priority: high
difficulty: basic
status: foundation

depends_on:
  - array

used_in:
  - stack_linked
  - queue_linked
  - adjacency_list
  - hash_table_chaining

tags:
  - dsa
  - linked_list
  - pointer
---

# Singly Linked List — Forward Pointer Structure

## 0. The real problem this structure solves

Arrays require:
- contiguous memory
- shifting during insertion/deletion

Singly linked list removes contiguity requirement.

**One-line anchor:**  
> Order without contiguous memory.

---

## 1. Core Structure

```java
private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
    }
}
```

List maintains:

```java
private Node<E> head;
```

Each node:
- stores data
- stores reference to next node
- no backward reference

---

## 2. Memory Model

Nodes:
- live independently in heap
- are connected via references
- not stored sequentially

Traversal is required to reach position.

---

## 3. Core Invariants

- If `head == null` → list is empty
- Last node: `node.next == null`
- Losing a reference loses entire subchain

---

## 4. Time Complexity

| Operation | Time |
|------------|------|
| Access by index | O(n) |
| Insert at head | O(1) |
| Insert at end | O(n) |
| Delete at head | O(1) |
| Delete at position | O(n) |

---

## 5. Key Operations (Essential Implementation)

### Insert at Beginning

```java
newNode.next = head;
head = newNode;
```

### Insert at End

```java
while(curr.next != null)
    curr = curr.next;
curr.next = newNode;
```

### Delete at Beginning

```java
head = head.next;
```

### Delete at Position

```java
prev.next = curr.next;
```

---

## 6. Strengths

- No shifting required
- O(1) head insertion
- Simple structure

---

## 7. Weaknesses

- No backward traversal
- No random access
- Poor cache locality
- O(n) search

---

## 8. When Used

- Stack (linked implementation)
- Hash table chaining
- Graph adjacency list

---

## Linked Notes

- [Doubly_Linked_List](Doubly_Linked_List.md)
- [Circular_Linked_List](Circular_Linked_List.md)
- [Arrays_Fundamentals](Arrays_Fundamentals.md)
