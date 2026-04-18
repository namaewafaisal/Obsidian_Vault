---
topic: singly_linked_list
category: linear
structure: pointer_based
data: homogeneous_or_generic
mutability: dynamic_structure
access: sequential
priority: high
difficulty: basic
status: foundation
used_in:
  - stack_linked
  - hash_table_chaining
  - adjacency_list_graph
  - memory_pool_structures
tags:
  - dsa
  - linked_list
  - pointer
  - linear
---

# Singly Linked List — Fundamentals (Memory, Guarantees, Invariants)

## 0. The real problem this structure solves

Arrays require:
- contiguous memory
- shifting elements during insertion/deletion
- fixed size

When:
- frequent insertion/deletion is required
- memory size is unpredictable

Arrays become inefficient.

**One-line anchor:**  
> Singly linked list removes contiguity to gain flexible mutation.

---

## 1. What a singly linked list really is

A singly linked list is a collection of nodes where:

- each node stores:
  - data
  - reference to the next node
- nodes are not stored contiguously
- order is defined purely by references

**One-line anchor:**  
> Order is defined by links, not memory layout.

---

## 2. Node structure (minimal form)

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

---

## 3. Memory model (critical)

Heap reality:

```
NodeA -> NodeB -> NodeC -> null
```

Nodes:
- live independently in heap
- may be anywhere in memory
- connected via references

There is:
- no index math
- no contiguous allocation

---

## 4. Structural invariants

For non-empty list:

- `head != null`
- last node: `node.next == null`
- losing a node reference makes the rest unreachable

**One-line anchor:**  
> If you lose a reference, you lose the chain.

---

## 5. Access behavior

Accessing element at index `i`:

- must traverse from head
- move `i` steps
- cost = O(n)

No random access exists.

---

## 6. Insert operations (conceptual)

### Insert at Beginning (O(1))

```java
newNode.next = head;
head = newNode;
```

No traversal needed.

---

### Insert at End (O(n))

```java
while(curr.next != null)
    curr = curr.next;
curr.next = newNode;
```

Traversal required.

---

## 7. Delete operations (conceptual)

### Delete at Beginning (O(1))

```java
head = head.next;
```

---

### Delete at Position (O(n))

Requires:
- finding previous node
- relinking

```java
prev.next = curr.next;
```

---

## 8. Time complexity summary

| Operation | Time |
|------------|------|
| Access by index | O(n) |
| Insert at head | O(1) |
| Insert at end | O(n) |
| Delete at head | O(1) |
| Search | O(n) |

---

## 9. Strengths

- No shifting
- Dynamic size
- O(1) insertion at head
- Simple memory ownership

---

## 10. Weaknesses

- No random access
- Poor cache locality
- Higher memory overhead (extra pointer per node)
- Traversal always required

---

## 11. Common bugs

- Forgetting to update head
- Overwriting `next` before saving it
- Losing reference to sublist
- NullPointerException during traversal

Example dangerous mistake:

```java
curr.next = curr.next.next;
```

Without checking if `curr.next` is null.

---

## 12. When to use

Use singly linked list when:

- Frequent head insertion
- Implementing stack (linked)
- Building adjacency lists
- Hash table chaining

---

## 13. When NOT to use

Avoid when:

- Heavy random access required
- Frequent `get(i)`
- Memory efficiency is critical
- Cache performance matters

ArrayList often outperforms in real systems.

---

## 14. Backend / System relevance

Used internally in:

- Graph representations
- Collision chains in hash tables
- Memory allocators
- Streaming pipelines

---

## 15. Final mental model (lock this)

> Singly linked list trades contiguous speed for mutation flexibility.

---

## 16. Linked notes

- [[Doubly_Linked_List]]
- [[Circular_Linked_List]]
- [[../../02-Learning/Java/Java Backend/JAVA/Array/Array]]
- [[Stack_Linked_Implementation]]
- [[Hash_Table_Chaining]]
