---
topic: array_bugs
category: linear
error_type: logical
safety: manual
runtime_checks: partial

priority: high
difficulty: basic
status: foundation

depends_on:
  - array
  - array_operations

tags:
  - dsa
  - array
  - bugs
---

# Common Array Bugs — Silent Failures Explained

## 0. The real problem this concept solves

Most array bugs:
- compile fine
- look logically correct
- fail at runtime or silently corrupt data

**One-line anchor:**  
> Arrays give no semantic protection — only memory safety.

---

## 1. Off-by-one errors

```java
for (int i = 0; i <= arr.length; i++) { // ❌
    use(arr[i]);
}
```

Correct:
```java
i < arr.length
```

Invariant:
```
0 <= index < length
```

---

## 2. Confusing capacity with size

```java
int[] arr = new int[10];
int size = 2;

for (int i = 0; i < arr.length; i++) { // ❌
    process(arr[i]);
}
```

Default values leak into logic.

Rule:
> Arrays do not know which elements are meaningful.

---

## 3. Partial initialization

```java
String[] names = new String[3];
names[0] = "A";
names[2].length(); // ❌
```

Why:
- Array slots exist
- Objects may not

---

## 4. Aliasing (reference sharing)

```java
int[] a = {1, 2, 3};
int[] b = a;

b[0] = 99;
```

Both `a` and `b` see the change.

Rule:
> Assignment copies references, not data.

---

## 5. Shifting past bounds

```java
for (int i = k; i < size; i++) {
    arr[i] = arr[i + 1]; // ❌
}
```

Correct:
```java
i < size - 1
```

---

## 6. Forgetting to update logical size

Delete without `size--` causes:
- duplicates
- stale values
- incorrect future inserts

---

## 7. Misusing enhanced for-loop

```java
for (int x : arr) {
    // cannot track index
}
```

Rule:
> Enhanced for-loop is read-only by design.

---

## 8. Final mental model

> Arrays manage memory; you manage meaning.

---

## Linked notes

- [[Arrays_Fundamentals]]
- [[Array_Operations_Insert_Delete]]
- [[2D_Arrays_Memory_Model]]
