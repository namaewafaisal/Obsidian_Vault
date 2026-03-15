---
topic: tree_general_concept
category: hierarchical
structure: node_edge
data: homogeneous_or_generic
mutability: dynamic
access: hierarchical
priority: high
difficulty: intermediate
status: foundation
used_in:
  - file_systems
  - database_indexing
  - dom
  - compilers
  - decision_trees
  - network_routing
tags:
  - dsa
  - tree
  - hierarchical
  - non_linear
---

# Tree — General Concept

## 0. The Real Problem This Structure Solves

Linear structures model sequences. Some data is not sequential — it is hierarchical.

File systems, org charts, HTML DOM, family trees — all have one-to-many relationships that a line cannot represent without losing structure.

One-line anchor:
> Tree models hierarchy — one parent, many children, no cycles.

## 1. What It Is

A collection of nodes connected by edges, with one designated root and a strict parent rule.

Every node has exactly one parent, except the root which has none.

## 2. Core Rules (What Makes It A Tree)

1. Exactly one root.
2. Every non-root node has exactly one parent.
3. No cycles — you cannot follow edges and return to the same node.
4. N nodes → exactly N-1 edges. Always.

Why N-1 edges: every node except root has exactly one edge to its parent. Edges = nodes - 1.

Violate any rule → it becomes a graph, not a tree.

## 3. Terminology

| Term | Definition |
|---|---|
| Root | Top node, no parent |
| Leaf | Node with no children |
| Edge | Connection between parent and child |
| Depth of node | Edges from root down to that node |
| Height of node | Edges from that node down to deepest leaf in its subtree |
| Height of tree | Height of root node |
| Subtree | A node and all its descendants treated as its own tree |
| Degree of node | Number of children it has |

Depth and height are opposites in direction:
- Depth — top down from root to node
- Height — bottom up from node to deepest leaf below it

A leaf always has height 0.

## 4. Types By Degree

| Type | Max Children Per Node |
|---|---|
| General Tree | Unlimited |
| Binary Tree | 2 |
| Ternary Tree | 3 |
| N-ary Tree | N |

Binary Tree is the most important variant — the foundation for BST, Heap, and AVL.

## 5. Edge Cases and Violations

| Structure | Is It A Tree? | Why |
|---|---|---|
| Single node, no edges | Yes | Valid — root is also a leaf |
| Node with two parents | No | Violates one-parent rule, creates cycle |
| Disconnected nodes | No | Not all nodes reachable from root |
| Cycle between nodes | No | Violates no-cycle rule |

## 6. Real-World Usage

- File systems — folders contain folders contain files
- Database indexing — B-trees
- Browser DOM — html → body → div → p
- Compilers — abstract syntax trees
- AI — decision trees
- Network routing tables

## 7. When Not To Use

- Relationships are many-to-many — use Graph
- Order of elements matters linearly — use Array or Linked List
- Multiple parents per node needed — use Graph

## 8. Common Bugs (Conceptual)

1. Confusing depth and height — depth is top-down, height is bottom-up.
2. Assuming height equals number of levels — height counts edges, not nodes.
3. Calling a structure with cycles a tree — any cycle disqualifies it.
4. Forgetting a single node is a valid tree — root with no children is legal.

## 9. Linked Notes

- [[Binary_Tree]] — most important specialization, next in Phase 4
- [[Binary_Search_Tree]] — binary tree with ordering invariant
- [[Balanced_Trees]] — height-controlled binary trees
- [[Graph_Concept]] — generalization where tree rules are relaxed