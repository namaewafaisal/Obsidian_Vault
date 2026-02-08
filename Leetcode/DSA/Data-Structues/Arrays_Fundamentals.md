---
topic: array
category: linear
structure: contiguous
data: homogeneous
mutability: Java Backend fixed
access: direct
priority: high
difficulty: basic
status: foundation
used_in:
  - sliding_window
  - two_pointers
  - prefix_sum
  - hashing_backing_storage
tags:
  - dsa
  - array
  - linear
---
# Arrays — Fundamentals (Memory, Guarantees, Invariants)

## 0. The real problem this concept solves

You need to store **multiple values of the same type** such that:
- access is fast
- memory layout is predictable
- iteration is simple

Using separate variables fails immediately at scale.

**One-line anchor:**  
> Arrays solve bulk storage with direct indexed access.

---

## 1. What an array really is (plain English)

An array is a **fixed-size block of contiguous memory** that stores elements of the **same type**, accessed using an index.

It is not a list, not dynamic, not smart — just memory with rules.

**One-line anchor:**  
> Array = fixed-size contiguous memory + index math.

---

## 2. How it actually works (step-by-step)

### Creation
```java
int[] arr = new int[5];
```

What happens:

1. Heap allocates memory for **5 integers**
2. Memory is contiguous
3. Each slot is initialized to a default value (`0`)
4. Stack variable `arr` stores a **reference** to that block

Heap view:
```
[0][0][0][0][0]
```

Stack view:
```
arr -> heap address
```

---

## 3. Indexing (the core mechanism)

```java
arr[i]
```

Indexing means:
```
address = base_address + (i * size_of_element)
```

For `int`:
- size = 4 bytes

This is why:
- access is **O(1)**
- no traversal is needed

**One-line anchor:**  
> Index = offset, not position.

---

## 4. Why array size is fixed

Once allocated:
- memory must remain contiguous
- resizing would require:
  - new allocation
  - copying all elements
  - updating references

Java does **not** do this implicitly.

**One-line anchor:**  
> Arrays trade flexibility for speed.

---

## 5. Stack vs Heap (important distinction)

```java
int[] arr = new int[5];
```

- Stack:
  - reference variable `arr`
- Heap:
  - actual array object
  - element storage

If `arr = null;`:
- only the stack reference is cleared
- heap memory remains (until GC)

---

## 6. Default values (often misunderstood)

| Type | Default |
|----|----|
| int | 0 |
| boolean | false |
| object reference | null |

Arrays do **not** know what “empty” means.

**One-line anchor:**  
> Default ≠ unused.

---

## 7. Arrays of objects (critical difference)

```java
String[] names = new String[3];
```

Heap reality:
```
[ null ][ null ][ null ]
```

Objects are **not** auto-created.

You must explicitly assign:
```java
names[0] = "Alice";
```

---

## 8. Mutability and overwriting

```java
arr[2] = 10;
arr[2] = 20;
```

- overwrite happens in-place
- no new memory allocated

Arrays are **mutable**.

---

## 9. Bounds checking and safety

```java
arr[5] = 10; // ❌
```

Valid indices:
```
0 <= index < arr.length
```

Java enforces bounds at runtime to prevent memory corruption.

---

## 10. Code: basic safe traversal

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

Invariant:
```
0 <= i < length
```

---

## 11. Common misconceptions (explicitly corrected)

- ❌ Arrays resize automatically  
  ✔ They never do

- ❌ `length` = number of valid elements  
  ✔ `length` = capacity

- ❌ Arrays copy on assignment  
  ✔ Only references copy

---

## 12. Backend / DSA relevance

Arrays are the foundation for:
- strings (char arrays)
- ArrayList internals
- DP tables
- buffers
- matrices

Misunderstanding arrays causes bugs **everywhere else**.

---

## 13. Final mental model (lock this)

> Arrays give you speed by forcing you to manage size, bounds, and meaning yourself.

---

## 14. Linked notes

- [[Array_Operations_Insert_Delete]]
- [[Common_Array_Bugs]]
- [[Array_Exceptions]]
- [[2D_Arrays_Memory_Model]]
- [[Matrix_Mental_Models]]
