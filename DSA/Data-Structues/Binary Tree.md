---
topic: binary_tree
category: hierarchical
structure: node_edge
data: homogeneous_or_generic
mutability: dynamic
access: hierarchical
priority: high
difficulty: intermediate
status: foundation
used_in:
  - bst
  - heap
  - avl_tree
  - expression_trees
  - huffman_encoding
tags:
  - dsa
  - binary_tree
  - tree
  - recursion
---

# Binary Tree

## 0. The Real Problem This Structure Solves

General trees have unbounded children per node — hard to implement and reason about.

Binary Tree constrains every node to at most 2 children — left and right. Position matters. Left is not interchangeable with right.

One-line anchor:
> Binary Tree is the foundation of all tree-based structures — two children max, position meaningful.

## 1. What It Is

A tree where every node has at most two children, explicitly named left and right.

Node structure:
```java
class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;
}
```

Both children can be null. Both null → leaf node.

## 2. Types of Binary Trees

| Type | Rule |
|---|---|
| Full | Every node has 0 or 2 children — never 1 |
| Complete | All levels filled except last, last fills left to right |
| Perfect | All internal nodes have 2 children, all leaves at same level |
| Degenerate | Every node has one child — effectively a linked list |

Perfect binary tree with height h → 2^(h+1) - 1 nodes.

## 3. Core Invariants

- Every node has at most 2 children.
- Left and right are distinct positions — not interchangeable.
- Height of balanced tree → O(log n).
- Height of degenerate tree → O(n).

## 4. Traversals

Four valid traversal orders — all defined recursively except level order.

**Inorder — Left → Root → Right**
```java
private void inOrder(Node<T> node) {
    if (node == null) return;
    inOrder(node.left);
    System.out.print(node.data + " ");
    inOrder(node.right);
}
```

**Preorder — Root → Left → Right**
```java
private void preOrder(Node<T> node) {
    if (node == null) return;
    System.out.print(node.data + " ");
    preOrder(node.left);
    preOrder(node.right);
}
```

**Postorder — Left → Right → Root**
```java
private void postOrder(Node<T> node) {
    if (node == null) return;
    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.data + " ");
}
```

**Level Order — level by level, left to right (uses Queue)**
```java
private void levelOrder(Node<T> node) {
    if (node == null) return;
    Queue<Node<T>> q = new ArrayDeque<>();
    q.offer(node);
    while (!q.isEmpty()) {
        Node<T> n = q.poll();
        System.out.print(n.data + " ");
        if (n.left != null) q.offer(n.left);
        if (n.right != null) q.offer(n.right);
    }
}
```

Traversal use cases:
- Inorder → sorted output on BST
- Preorder → copy or serialize tree
- Postorder → delete tree, evaluate expression trees
- Level order → BFS, find shortest path

## 5. Implementation
```java
public class BinaryTree<T> {
    private Node<T> root;
    private Queue<Node<T>> insertQueue;

    public void insert(T data) {
        if (insertQueue == null) {
            insertQueue = new ArrayDeque<>();
            root = new Node<>(data);
            insertQueue.offer(root);
            return;
        }
        Node<T> newNode = new Node<>(data);
        while (!insertQueue.isEmpty()) {
            if (insertQueue.peek().left == null) {
                insertQueue.peek().left = newNode;
                insertQueue.offer(newNode);
                return;
            } else if (insertQueue.peek().right == null) {
                insertQueue.peek().right = newNode;
                insertQueue.offer(newNode);
                return;
            } else {
                insertQueue.poll();
            }
        }
    }

    public int height() { return height(root); }
    private int height(Node<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int size() { return size(root); }
    private int size(Node<T> node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }
}
```

## 6. Public/Private Wrapper Pattern

Internal Node is private. Recursive methods are private. Public methods take no node parameters.
```java
public void inOrder()            // caller uses this
private void inOrder(Node<T> n)  // recursion happens here
```

Caller always works with data values. Never with node references. This is the standard pattern used in Java's TreeMap and TreeSet internally.

## 7. Edge Cases

| Scenario | Behavior |
|---|---|
| Empty tree | All traversals return immediately |
| Single node | Traversals print root, height = 0, size = 1 |
| Perfect binary tree | Height = log₂(n+1) - 1 |
| Degenerate tree | Height = n-1, behaves like linked list |

## 8. Performance Characteristics

| Operation | Balanced | Degenerate |
|---|---|---|
| Insert (level order) | O(log n) | O(n) |
| Traversal | O(n) | O(n) |
| Height | O(n) | O(n) |
| Size | O(n) | O(n) |
| Space | O(n) | O(n) |

## 9. When To Use

- Data has hierarchical relationships.
- Foundation needed for BST, Heap, AVL.
- Expression parsing and evaluation.
- Huffman encoding.

## 10. When Not To Use

- Ordered search needed without BST property — use BST.
- Priority-based access needed — use Heap.
- Data is linear — use Array or LinkedList.

## 11. Common Bugs

1. Forgetting null check base case in recursive methods — causes NullPointerException.
2. Printing newline inside recursive method — prints newline after every node.
3. Exposing Node in public API — breaks encapsulation.
4. Insert condition inverted — checking left != null instead of left == null.
5. levelOrder helper left public instead of private.

## 12. Linked Notes

- [[Tree General Concept]] — parent concept
- [[Binary Search Tree]] — binary tree with ordering invariant, next in Phase 4
- [[Balanced Trees]] — height-controlled binary trees, Phase 4
- [[Heap]] — complete binary tree with priority invariant, Phase 5