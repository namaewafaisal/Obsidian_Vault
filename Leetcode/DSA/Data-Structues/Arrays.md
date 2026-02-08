---
topic: array
category: linear
structure: contiguous
data: homogeneous
mutability: fixed
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

# Arrays

## Definition
A linear data structure that stores elements of the **same type** in **contiguous memory locations**, allowing **direct access via index**.

---

## Core Insight

> Arrays trade **fixed size** for **O(1) random access**.

---

## Mental Model / Invariant

> Given a base address `B`, element at index `i` is always located at  
> **`B + i × size_of_element`**

This invariant is what guarantees **O(1)** access.

If this invariant breaks → the structure is **not** an array.

---

## Characteristics

- Contiguous memory layout
- Fixed size (classical arrays)
- Homogeneous data
- Zero-based indexing (Java)
- Cache-friendly traversal

---

## Operations, Implementation & Time Complexity

| Operation | Implementation / Method | Time |
|---------|--------------------------|------|
| Create | [[#Create Array]] | O(1) |
| Access | [[#Access Element]] | O(1) |
| Traverse | [[#Traverse]] | O(n) |
| Insert (End) | [[#Insert at End]] | O(1) |
| Insert (Middle) | [[#Insert at Middle]] | O(n) |
| Delete (End) | [[#Delete at End]] | O(1) |
| Delete (Middle) | [[#Delete at Middle]] | O(n) |
| Linear Search | [[#Linear Search]] | O(n) |
| Binary Search | [[#Binary Search]] | O(log n) |

---

## Space Complexity

- Storage: **O(n)**
- Auxiliary space: **O(1)** (excluding resizing/copying)

---

## Implementation (From Scratch – Java)

### Create Array
```java
int[] arr1 = new int[5];          // fixed size, default values
int[] arr2 = {10, 20, 30};       // initialized with values
```

---

### Access Element
```java
int value = arr2[1]; // direct index access
```

---

### Traverse
```java
for (int i = 0; i < arr2.length; i++) {
    System.out.println(arr2[i]);
}
```

---

### Insert at End
```java
int[] arr = new int[10];
int size = 0;

arr[size] = 10;
size++;

arr[size] = 20;
size++;
```

---

### Insert at Middle
```java
int pos = 1;
int value = 15;

for (int i = size; i > pos; i--) {
    arr[i] = arr[i - 1];
}
arr[pos] = value;
size++;
```

---

### Delete at End
```java
size--; // logical deletion
```

---

### Delete at Middle
```java
int pos = 2;

for (int i = pos; i < size - 1; i++) {
    arr[i] = arr[i + 1];
}
size--;
```

---

### Linear Search
```java
int target = 30;
boolean found = false;

for (int i = 0; i < size; i++) {
    if (arr[i] == target) {
        found = true;
        break;
    }
}
```

---

### Binary Search
```java
int low = 0, high = size - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (arr[mid] == target) break;
    else if (arr[mid] < target) low = mid + 1;
    else high = mid - 1;
}
```

---

## Inbuilt / Library Usage (Java)

### Arrays Utility Class
```java
Arrays.sort(arr);
Arrays.binarySearch(arr, target);
Arrays.fill(arr, 0);
```

### Dynamic Alternative
```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.remove(0);
```

> Internally, `ArrayList` still uses an array with resizing.

---

## What Actually Matters (For Problems)

- Index vs value distinction
- Whether **order must be preserved**
- Whether modification must be **in-place**
- Whether the array is **sorted**
- Whether extra space is allowed

---

## Common Pitfalls

- Off-by-one errors (`i < n` vs `i <= n`)
- Confusing logical size with capacity
- Assuming insertion is cheap everywhere
- Ignoring immutability constraints in problems

---

## Typical Problem Patterns

- Two pointers → ordered traversal
- Sliding window → contiguous subarrays
- Prefix sum → range queries
- Difference array → range updates

---

## When NOT to Use

- Frequent insertions/deletions in the middle
- Highly dynamic size requirements
- When random access is not required

---

## One-Line Recall

> Arrays provide **O(1) index access** due to contiguous memory, but any operation requiring shifting elements costs **O(n)**.
