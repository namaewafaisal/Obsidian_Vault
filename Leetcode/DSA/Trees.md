---
tags:
  - trees
  - dfs
  - bfs
  - recursion
  - dsa
status: learning-foundation
---

## Trees – Core Concepts (Foundation Notes)

### What a Tree Is

A tree is a **recursive data structure**:
- A node
- With zero or more child nodes
- Binary tree → at most two children (`left`, `right`)

A tree has:
- No cycles
- One root
- Many subtrees

---

### Traversal vs Operation (Critical Distinction)

**Traversal** answers:
> In what order do I visit nodes?

**Operation timing** answers:
> When do I perform my logic?

These two are **independent**.

---

### DFS (Depth First Search)

DFS means:
> Go deep before going wide.

DFS has **three valid forms**:

#### Preorder (top-down)
```
do(node)
dfs(left)
dfs(right)
```

#### Postorder (bottom-up)
```
dfs(left)
dfs(right)
do(node)
```

#### Inorder (structure-based)
```
dfs(left)
do(node)
dfs(right)
```

DFS does **not** force bottom-up logic.
Operation timing is your choice.

---

### BFS (Breadth First Search)

BFS means:
> Visit level by level.

Properties:
- Uses a queue
- Root first
- Level order traversal
- Operation always happens **top-down**

Good for:
- Level-based logic
- Shortest path
- Distance problems

---

### When to Use DFS vs BFS

| Problem Depends On | Use |
|-------------------|-----|
| Subtrees | DFS |
| Children results | DFS (postorder) |
| Parent state | DFS (preorder) |
| Levels | BFS |
| Shortest path | BFS |

---

### Where My Thinking Went Wrong

❌ Assumed DFS = bottom-up only  
❌ Assumed BFS is natural because “layers” exist  
❌ Borrowed matrix-layer intuition into trees  

---

### Correct Mental Shift

> Trees are about **subtrees**, not layers.

Matrix → spatial  
Tree → structural  

---

### One-Sentence Lock

> DFS decides how deep you go; **you decide when to act**.

