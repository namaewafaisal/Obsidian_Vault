---
topic: array_operations
category: linear
structure: contiguous
operation_costs: explicit
mutability: fixed
access: direct

priority: high
difficulty: basic
status: foundation

depends_on:
  - array

used_in:
  - arraylist_internal_logic
  - in_place_algorithms

tags:
  - dsa
  - array
  - operations
---

# Array Operations — Insert, Delete, Shift (Cost-Aware)

## 0. The real problem this concept solves

You need to **modify data stored in arrays** while understanding:
- what is cheap
- what is expensive
- what is unavoidable

Arrays do not manage this for you.

**One-line anchor:**  
> Arrays allow fast access but make structural changes expensive.

---

## 1. Logical size vs physical capacity

```java
int[] arr = new int[5];
int size = 3;
```

Heap:
```
[10][20][30][ ][ ]
```

- `arr.length` → capacity
- `size` → number of valid elements

Arrays do **not** track `size`.

---

## 2. Read & write (baseline)

```java
int x = arr[i];
arr[i] = 42;
```

- Direct address calculation
- No shifting
- **O(1)**

---

## 3. Insert at the end (best case)

```java
arr[size] = 40;
size++;
```

Cost:
- **O(1)**

Why:
- No existing elements move

---

## 4. Insert at the beginning (worst case)

Before:
```
[10][20][30][ ][ ]
```

After:
```
[5][10][20][30][ ]
```

Code:
```java
for (int i = size; i > 0; i--) {
    arr[i] = arr[i - 1];
}
arr[0] = 5;
size++;
```

Cost:
- **O(n)** due to shifting

---

## 5. Insert in the middle

Insert at index `k`:

```java
for (int i = size; i > k; i--) {
    arr[i] = arr[i - 1];
}
arr[k] = value;
size++;
```

Cost:
- **O(n - k)** → worst case **O(n)**

---

## 6. Delete at the end (best case)

```java
size--;
```

Cost:
- **O(1)**

---

## 7. Delete at the beginning (worst case)

```java
for (int i = 0; i < size - 1; i++) {
    arr[i] = arr[i + 1];
}
size--;
```

Cost:
- **O(n)**

---

## 8. Delete in the middle

```java
for (int i = k; i < size - 1; i++) {
    arr[i] = arr[i + 1];
}
size--;
```

Cost:
- **O(n - k)**

---

## 9. Why shifting is unavoidable

Arrays are **contiguous memory**.

You cannot:
- create gaps
- grow in place
- skip indices

Every structural change requires **copying elements**.

---

## 10. Final mental model

> If an operation requires shifting elements, its cost is linear.

---

## Linked notes

- [[Arrays_Fundamentals]]
- [[Common_Array_Bugs]]
- [[ArrayList_Memory_Model]]
