---
topic: stack_array_implementation
category: linear
structure: contiguous
data: homogeneous_or_generic
mutability: fixed_or_resizable
access: LIFO
priority: high
difficulty: intermediate
status: foundation
used_in:
  - dynamic_array
  - heap_structure
  - circular_buffer
tags:
  - dsa
  - stack
  - array
---

# Stack Using Array — Fixed Capacity Model

## 0. Structural Model

State variables:

```java
E[] arr;
int top;
int capacity;
```

Invariant:

```
top == -1 → empty
0 ≤ top < capacity → valid
top == capacity - 1 → full
```

---

## 1. Push

```java
arr[++top] = value;
```

Time → O(1)

---

## 2. Pop

```java
value = arr[top];
arr[top] = null;
top--;
```

Null assignment prevents memory leak.

---

## 3. Strengths

- Cache-friendly
- Compact memory
- Simple invariant
- Fast operations

---

## 4. Weaknesses

- Fixed capacity (unless resizing added)
- Requires generic array workaround

---

## 5. When to Use

- Performance critical contexts
- Predictable maximum size
- Underlying implementation of other structures

---

## 6. When Not to Use

- Unknown size growth
- Frequent resizing required

---

## Linked Notes

- [Stack](Stack.md)
- [Dynamic_Array](Dynamic_Array)