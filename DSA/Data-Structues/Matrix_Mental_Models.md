---
topic: matrix_mental_models
category: linear
representation: grid
focus: traversal_logic
index_safety: critical

priority: medium
difficulty: basic
status: foundation

depends_on:
  - two_d_array

used_in:
  - grid_dfs_bfs
  - island_problems
  - dp_on_grids
  - shortest_path_grids

tags:
  - dsa
  - matrix
  - traversal
---

# Matrix Mental Models — How to Think Before Coding

## 0. The real problem this concept solves

Matrix problems fail not because of logic,
but because **movement and boundaries are mishandled**.

**One-line anchor:**  
> Matrix problems are about movement + bounds, not storage.

---

## 1. The four fundamental traversal models

Every matrix problem reduces to one of these.

---

## 2. Full scan (row-major)

```java
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        visit(i, j);
    }
}
```

Used for:
- counting
- transformation
- validation

Mental model:
> Touch every cell exactly once.

---

## 3. Directional movement (grid walking)

Directions:
```java
int[] dr = {1, -1, 0, 0};
int[] dc = {0, 0, 1, -1};
```

Movement:
```java
nr = r + dr[k];
nc = c + dc[k];
```

Used for:
- DFS / BFS
- flood fill
- path problems

Mental model:
> Stand on a cell and walk.

---

## 4. Layer-based traversal

Used in:
- spiral traversal
- boundary peeling

Concept:
- top row
- right column
- bottom row
- left column

Mental model:
> Peel the matrix layer by layer.

---

## 5. State-based traversal

Each cell stores extra meaning:
- visited
- distance
- region id

Used in:
- island counting
- shortest path
- connected components

Mental model:
> Each cell remembers something.

---

## 6. Boundary checks (non-negotiable)

Every access must satisfy:
```java
0 <= r < rows
0 <= c < cols
```

Order matters:
- check bounds **before** access
- negative indices are as dangerous as overflow

---

## 7. Why matrix problems feel hard

They combine:
- nested loops
- index math
- direction logic
- state tracking

Strong solvers separate:
- **movement logic**
- **work logic**

---

## 8. Final mental model

> If index safety is correct, matrix logic usually works.

---

## Linked notes

- [[2D_Arrays_Memory_Model]]
- [[Grid_DFS_BFS]]
- [[DP_On_Grids]]
