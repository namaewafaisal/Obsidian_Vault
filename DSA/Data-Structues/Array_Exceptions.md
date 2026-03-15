---
topic: array_exceptions
category: linear
error_type: runtime
safety: jvm_enforced
failure_mode: immediate

priority: high
difficulty: basic
status: foundation

depends_on:
  - array
  - array_operations
  - two_d_array

tags:
  - dsa
  - array
  - exceptions
---

# Array Exceptions & Runtime Failures — What Breaks and Why

## 0. The real problem this concept solves

Array code often **compiles perfectly** but fails **at runtime**.
Understanding *which* exception occurs tells you *exactly* what rule you violated.

**One-line anchor:**  
> Array exceptions are precise signals of broken invariants.

---

## 1. `ArrayIndexOutOfBoundsException`

### What it means
You accessed an index **outside**:
```
0 <= index < length
```

### Typical causes
```java
arr[arr.length] = 10;      // off-by-one
arr[-1] = 5;               // negative index
```

### Why JVM throws it
- Prevents memory corruption
- Enforces bounds on every access

### Fix mindset
> Re-derive valid index range before fixing code.

---

## 2. `NullPointerException` (arrays of objects / 2D arrays)

### Case A — object array slot is null
```java
String[] a = new String[3];
a[0].length(); // ❌
```

Why:
- Slot exists
- Object does not

---

### Case B — 2D array row is null
```java
int[][] mat = new int[3][];
mat[0][0] = 1; // ❌
```

Why:
- `mat[0]` is `null`
- You indexed into nothing

### Fix mindset
> Always ask: “Does this reference point to an object?”

---

## 3. `NegativeArraySizeException`

### What it means
You attempted to create an array with negative size.

```java
int n = -3;
int[] arr = new int[n]; // ❌
```

### Why JVM throws it
- Size must be known and non-negative at allocation
- No recovery possible

### Fix mindset
> Validate sizes *before* allocation.

---

## 4. `ArrayStoreException`

### What it means
You tried to store an **incompatible type** in an array.

```java
Object[] arr = new String[3];
arr[0] = 10; // ❌
```

Why:
- Runtime type of array is `String[]`
- JVM enforces type safety

### Why this exists
- Arrays are **covariant**
- JVM adds runtime checks to compensate

### Fix mindset
> Trust compile-time types less with arrays of references.

---

## 5. Silent logical failures (no exception)

These are **more dangerous** than crashes.

### Example: default values leak
```java
int[] arr = new int[5];
int sum = 0;

for (int i = 0; i < arr.length; i++) {
    sum += arr[i]; // includes unintended zeros
}
```

No exception.
Wrong logic.

### Fix mindset
> Separate logical size from physical capacity.

---

## 6. Aliasing-induced corruption (no exception)

```java
int[] a = {1,2,3};
int[] b = a;

b[0] = 99;
```

No exception.
Unexpected shared mutation.

### Fix mindset
> Assignment copies references, never data.

---

## 7. Order of checks matters

Wrong:
```java
if (arr[i] != 0 && i < arr.length) { } // ❌
```

Right:
```java
if (i < arr.length && arr[i] != 0) { }
```

Why:
- Left side is evaluated first
- Bounds must be checked before access

---

## 8. How to debug array exceptions systematically

1. Identify exception type
2. Map it to violated invariant:
   - bounds
   - null reference
   - type mismatch
3. Re-check memory model
4. Fix logic, not symptoms

---

## 9. Final mental model

> Every array exception corresponds to exactly one broken rule.

---

## Linked notes

- [[Arrays_Fundamentals]]
- [[Array_Operations_Insert_Delete]]
- [[Common_Array_Bugs]]
- [[2D_Arrays_Memory_Model]]
