---
topic: stack_linkedlist_implementation
category: linear
structure: pointer_based
data: homogeneous_or_generic
mutability: dynamic
access: LIFO
priority: high
difficulty: intermediate
status: foundation
used_in:
  - unbounded_stack
  - memory_sensitive_contexts
tags:
  - dsa
  - stack
  - linked_list
---

# Stack Using Linked List — Head-Based Model

## 0. Structural Model

State variables:

```java
Node<E> head;
int size;
```

Invariant:

```
head == null → empty
head always represents top
```

---

## 1. Push

```java
newNode.next = head;
head = newNode;
size++;
```

---

## 2. Pop

```java
value = head.data;
head = head.next;
size--;
```

---

## 3. Strengths

- No capacity limit
- Simple pointer logic
- O(1) push/pop

---

## 4. Weaknesses

- Extra memory per node
- Poor cache locality
- More GC pressure

---

## 5. Structural Insight

Stack via linked list should use head.

Using tail:
- Adds complexity
- Violates minimal design principle

---

## Linked Notes

- [[Stack]]
- [[Singly_Linked_List]]