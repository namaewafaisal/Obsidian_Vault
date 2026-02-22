---
topic: stack_memory_analysis
category: linear
structure: performance_layer
data: generic
mutability: dynamic
access: LIFO
priority: medium
difficulty: intermediate
status: optimization_layer
tags:
  - dsa
  - stack
  - performance
  - cache
---

# Stack — Memory & Performance Analysis

## 0. Array vs Linked Stack Performance

Array stack:
- Contiguous memory
- Better cache locality
- Lower memory overhead

Linked stack:
- Pointer chasing
- Extra object per element
- More GC pressure

---

## 1. Real-World Performance

Array-based stack is almost always faster.

Even resizing cost is negligible compared to pointer chasing.

---

## 2. Memory Overhead

Linked node:
- Object header
- Reference fields
- Data reference

Array:
- Single object
- Packed references

---

## 3. Engineering Insight

Big-O ignores:
- Cache hierarchy
- Memory latency
- GC overhead

In real systems:
> Contiguous memory wins.

---

## Linked Notes

- [[Stack_Array_Implementation]]
- [[Stack_LinkedList_Implementation]]
- [[Cache_Locality]]