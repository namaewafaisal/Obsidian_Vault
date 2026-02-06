---
topic: tree
category: non-linear
structure: non-contiguous
data: hierarchical
mutability: dynamic
access: sequential

priority: high

importance_reason:
  - first structure that breaks linear thinking
  - base for binary trees, BSTs, heaps, graphs

used_in:
  - recursion
  - dfs
  - bfs
  - hierarchical modeling

difficulty: intermediate
status: foundation

tags:
  - dsa
  - tree
  - non-linear
---

# Tree — General Concept

## What a Tree Is (From Scratch)

A **tree** is a way to store data where:
- One element is the **root**
- Every other element is connected **under** another element
- There are **no cycles**
- There is **exactly one path** from the root to any node

This is **not about left/right yet**.  
This is about **hierarchy**.

---

## Core Insight

> Trees organize data by **parent–child relationships**, not by index or position.

Arrays → position-based  
Trees → relationship-based

---

## Mental Model / Invariant

> Every node (except the root) has **exactly one parent**,  
> and there is **only one unique path** from the root to that node.

If:
- a node has two parents → ❌ not a tree  
- a cycle exists → ❌ not a tree  

---

## Basic Terminology (Must Be Clear)

- **Root**: topmost node
- **Parent**: node directly above
- **Child**: node directly below
- **Leaf**: node with no children
- **Subtree**: any node + its descendants
- **Depth**: distance from root
- **Height**: longest downward path to a leaf

---

## How a Tree Is Represented in Code

### Generic Tree Node (Most Fundamental Form)

```java
class TreeNode {
    int val;
    List<TreeNode> children;

    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
```

This is the **pure tree**.
- Any number of children
- No left/right restriction
- This is what “tree” means before specialization

---

## Creating a Tree (Manual Construction)

```java
TreeNode root = new TreeNode(1);

TreeNode child1 = new TreeNode(2);
TreeNode child2 = new TreeNode(3);

root.children.add(child1);
root.children.add(child2);

child1.children.add(new TreeNode(4));
child1.children.add(new TreeNode(5));
```

This creates:

```
        1
      /   \
     2     3
   /   \
  4     5
```

---

## How You *Work With* a Tree

### Key Rule

> You **never** access a tree by index.  
> You always move **node → child → child**.

---

## Traversing a Tree (From Scratch Thinking)

Traversal = visiting **every node exactly once**.

For a general tree, the only natural way is **recursion**.

### Depth-First Traversal (Generic)

```java
void traverse(TreeNode node) {
    if (node == null) return;

    System.out.println(node.val);   // process current node

    for (TreeNode child : node.children) {
        traverse(child);
    }
}
```

Why recursion works:
- Each subtree is itself a tree
- Same logic applies everywhere

---

## Why Trees Are Naturally Recursive

Because the **definition is recursive**:

> A tree is a node + zero or more trees.

This is why:
- loops feel awkward
- recursion feels natural

---

## Searching in a Tree (Conceptual)

There is **no ordering guarantee** in a general tree.

So:
- To find a value → you may need to check **all nodes**
- Time complexity → **O(n)**

```java
boolean contains(TreeNode node, int target) {
    if (node.val == target) return true;

    for (TreeNode child : node.children) {
        if (contains(child, target)) return true;
    }
    return false;
}
```

---

## Time & Space Complexity (General Tree)

Let `n` = number of nodes

- Traversal: **O(n)**
- Search: **O(n)**
- Insert (as child): **O(1)** if parent is known
- Space (storage): **O(n)**
- Recursion stack: **O(height)**

---

## How Trees Exist in Memory (Important)

- Nodes are **not contiguous**
- Each node is a separate object
- Connected via **references**
- Shape is logical, not physical

This is why:
- cache locality is worse than arrays
- random access is impossible

---

## Inbuilt Java Support (Important Reality)

Java does **NOT** have a built-in generic `Tree` class.

You must build trees using:
- custom node classes
- or specialized trees (`TreeMap`, `TreeSet`) later

There is:
- ❌ no `Tree` interface
- ❌ no generic traversal API

---

## What Trees Are Good At

- Representing hierarchy (file systems, org charts)
- Recursive processing
- Flexible shape
- Modeling parent–child relationships

---

## What Trees Are Bad At

- Random access
- Simple indexing
- Cache efficiency
- Iterative-only logic

---

## Common Beginner Mistakes

- Trying to access by index
- Confusing tree with binary tree
- Forgetting recursion base case
- Assuming order exists

---

## When You Should Use a Tree

- Data is hierarchical
- Relationships matter more than position
- Structure changes dynamically

---

## When You Should NOT Use a Tree

- Simple sequential data
- Heavy random access
- Tight memory constraints

---

## One-Line Recall

> A tree is a **cycle-free hierarchical structure** where data is accessed by **relationships**, not indices, and processed naturally using **recursion**.
