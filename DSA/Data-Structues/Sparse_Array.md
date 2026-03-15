---
topic: sparse_array
category: linear
structure: index_value_pairs
data: homogeneous_or_generic
mutability: dynamic
access: direct
priority: medium
difficulty: basic
status: foundation
used_in:
  - ml_weight_matrices
  - graph_adjacency_matrices
  - document_term_frequency
  - scientific_computing
tags:
  - dsa
  - sparse_array
  - hashmap
---

# Sparse Array

## 0. The Real Problem This Structure Solves

Large arrays where most values are zero waste memory storing information that carries no meaning.

A 1,000,000 element array with 5 actual values allocates 999,995 slots of nothing.

One-line anchor:
> Sparse Array stores only what exists, treating everything else as default.

## 1. What It Is

A sparse array is a logical array of size n backed by a map of only the non-default entries.

Two components:
- Logical size — what the array appears to be externally
- Backing store — only non-zero index→value pairs

Any index absent from the backing store implicitly returns the default value (zero).

## 2. Core Invariants

- Logical size is fixed at construction.
- Only non-default values are stored.
- Missing index → default value. Never an error.
- Index validation is against logical size, not backing store size.

## 3. The Trade-off

Dense array: O(1) access, O(n) memory always.

Sparse array: O(1) average access (HashMap), O(k) memory where k = non-zero count.

Sparse wins when k << n.
Sparse loses when data is dense — index+value overhead exceeds just storing the array.

## 4. Backing Structure Options

| Structure | Access | Use When |
|---|---|---|
| HashMap | O(1) average | Random access dominates |
| TreeMap / sorted pairs | O(log k) | Range queries or ordered iteration needed |
| Unsorted list of pairs | O(k) | k is tiny, write-once read-many |

## 5. Implementation (Conceptual)
```java
public class SparseArray<T> {
    private HashMap<Integer, T> store;
    private int logicalSize;
    private T defaultValue;

    public SparseArray(int size, T defaultValue) {
        this.logicalSize = size;
        this.defaultValue = defaultValue;
        store = new HashMap<>();
    }

    public void set(int index, T value) {
        if (index < 0 || index >= logicalSize) throw new IndexOutOfBoundsException();
        store.put(index, value);
    }

    public T get(int index) {
        if (index < 0 || index >= logicalSize) throw new IndexOutOfBoundsException();
        return store.getOrDefault(index, defaultValue);
    }

    public void delete(int index) { store.remove(index); }
    public int size() { return logicalSize; }
    public int count() { return store.size(); }
    public void display() { store.forEach((k, v) -> System.out.println(k + " -> " + v)); }
}
```

## 6. Performance Characteristics

| Operation | Time |
|---|---|
| get | O(1) average |
| set | O(1) average |
| delete | O(1) average |
| Memory | O(k) where k = non-zero count |

## 7. When To Use

- Array is large and mostly default values.
- ML weight matrices, graph adjacency matrices, document term frequency.
- Memory is a hard constraint.

## 8. When Not To Use

- Data is dense — most values are non-zero.
- Frequent ordered range traversal needed (use TreeMap or sorted pairs instead).
- O(1) worst-case access required (HashMap is O(1) average, not guaranteed).

## 9. Sparse Array vs Adjacency List

Both avoid storing what doesn't exist, but they are different structures:

- Sparse Array simulates indexed array access without allocating all slots.
- Adjacency List stores neighbor relationships per node — its own structure, covered in Phase 7.

Same motivation, different problems.

## 10. Linked Notes

- [[Arrays_Fundamentals]] — dense array this optimizes
- [[Hash_Map]] — backing structure
- [[Graph_Representations]] — adjacency list, related motivation
- [[Frequency_Array]] — next in Phase 3