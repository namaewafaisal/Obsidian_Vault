---
topic: two_d_array
category: linear
structure: array_of_arrays
dimensionality: 2d
mutability: fixed

priority: medium
difficulty: basic
status: foundation

depends_on:
  - array

used_in:
  - matrix_problems
  - grid_traversal
  - dp_tables

tags:
  - dsa
  - array
  - 2d
---

# 2D Arrays — Memory Model (Array of Arrays)

## 0. The real problem this concept solves

You need to represent **grid-like data**:
- matrices
- tables
- boards
- DP grids

Using a flat array makes reasoning and access error-prone.

**One-line anchor:**  
> 2D arrays organize data row-by-row for grid-based problems.

---

## 1. What a 2D array really is (critical truth)

In Java, a 2D array is **NOT a matrix**.

It is:
> an array of references to other arrays

This single fact explains every behavior and bug.

---

## 2. Memory layout (step-by-step)

```java
int[][] mat = new int[3][4];
```

### Step 1 — outer array
```
mat ──> [ ref ][ ref ][ ref ]
```

Length = number of rows.

---

### Step 2 — inner arrays
Each reference points to a separate `int[]`:

```
mat[0] ──> [0][0][0][0]
mat[1] ──> [0][0][0][0]
mat[2] ──> [0][0][0][0]
```

There is **no single contiguous 2D block**.

---

## 3. Indexing is a two-step operation

```java
mat[i][j]
```

Means:
1. `mat[i]` → get row reference
2. `[j]` → index into that row

So:
- two bounds checks
- possible `NullPointerException` at step 1

---

## 4. Traversal (correct and safe)

```java
for (int i = 0; i < mat.length; i++) {
    for (int j = 0; j < mat[i].length; j++) {
        process(mat[i][j]);
    }
}
```

Rules:
- `mat.length` → rows
- `mat[i].length` → columns of that row

Never assume uniform column length.

---

## 5. Jagged arrays (non-rectangular)

```java
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[5];
jagged[2] = new int[1];
```

Valid structure:
```
[2][5][1]  // row lengths
```

Java allows this by design.

---

## 6. Common aliasing trap

```java
int[] row = new int[3];
int[][] mat = new int[3][];

mat[0] = row;
mat[1] = row;
mat[2] = row;
```

All rows point to the **same array**.

Changing one row changes all.

---

## 7. Assignment is NOT copying

```java
int[][] b = mat;
```

This copies only:
- the outer reference

All rows and data are shared.

---

## 8. When 2D arrays are a bad choice

Avoid when:
- rows resize frequently
- sparse data
- very large grids

Better alternatives appear later (lists, maps).

---

## 9. Final mental model

> Java 2D arrays are collections of row arrays, not matrices.

---

## Linked notes

- [[Arrays_Fundamentals]]
- [[Common_Array_Bugs]]
- [[Matrix_Mental_Models]]
