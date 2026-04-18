---
topic: stack_dynamic_array
category: linear
structure: contiguous_resizable
data: homogeneous_or_generic
mutability: dynamic
access: LIFO
priority: high
difficulty: intermediate
status: foundation
used_in:
  - arraylist_internal
  - heap_structure
  - circular_queue
tags:
  - dsa
  - stack
  - dynamic_array
  - amortized
---

# Stack Using Dynamic Array — Resizable Model

## 0. The real problem this solves

Fixed-capacity stack wastes memory or overflows.

Dynamic resizing allows:
- Automatic growth
- Amortized O(1) push

---

## 1. Structural Model

State:

```java
E[] arr;
int top;
int capacity;
```

When full:
- Create new array of size `2 * capacity`
- Copy elements
- Reassign reference

---

## 2. Push with Resize

```
if (isFull())
    resize();

arr[++top] = value;
```

Resize:

```
newCapacity = capacity * 2
copy old elements
capacity = newCapacity
```

---

## 3. Why Amortized O(1)?

Resizing happens rarely.

If capacity doubles:

Total copies over n pushes ≈ 2n

So average cost per push ≈ constant.

---

## 4. Shrinking (Optional)

If size drops below 25% capacity:
- halve capacity

Prevents memory waste.

---

## 5. Strengths

- No overflow
- Cache-friendly
- Efficient growth

---

## 6. Weaknesses

- Occasional expensive resize
- Requires careful capacity management

---

## Linked Notes

- [[Stack_Array_Implementation]]
- [[Amortized_Analysis]]
- [[../../00-Learning/Java/Java Backend/JAVA/Array/Array]]