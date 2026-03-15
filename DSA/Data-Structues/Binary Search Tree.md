---
topic: binary search tree
category: hierarchical
structure: node_edge
data: comparable
mutability: dynamic
access: ordered
priority: high
difficulty: intermediate
status: foundation
used_in:
  - treemap
  - treeset
  - database indexing
  - dictionary lookup
  - sorted dynamic data
tags:
  - dsa
  - binary search tree
  - bst
  - recursion
---

# Binary Search Tree

## 0. The Real Problem This Structure Solves

Plain Binary Tree has no rules about value placement — search requires visiting every node. O(n).

BST adds one ordering rule that makes search O(log n) on a balanced tree.

One-line anchor:
> BST is a binary tree where left subtree < node < right subtree, at every node.

## 1. The Ordering Invariant

At every node:
- All values in left subtree are less than the node
- All values in right subtree are greater than the node

This is not just parent vs child — it applies to the entire subtree.

Consequence: inorder traversal of a BST always produces sorted ascending output.

## 2. Core Invariants

- Left subtree contains only values less than current node
- Right subtree contains only values greater than current node
- No duplicates (by convention — compareTo returns 0, neither branch taken)
- Invariant holds at every node, not just root

## 3. Operations

**Search** — compare target with current node, go left if smaller, go right if larger.

**Insert** — traverse like search, place new node where search falls off the tree.

**Delete — three cases:**
- Leaf node → remove directly
- One child → replace node with its child
- Two children → replace with inorder successor (min of right subtree), delete successor

Why inorder successor works: it is greater than everything in the left subtree and smaller than everything else in the right subtree. BST invariant preserved.

Inorder predecessor (max of left subtree) works equally — both are valid.

## 4. Implementation
```java
public class BinarySearchTree<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        Node(T data) { this.data = data; }
    }

    private Node<T> root;

    public void insert(T data) { root = insert(root, data); }
    private Node<T> insert(Node<T> node, T data) {
        if (node == null) return new Node<>(data);
        if (data.compareTo(node.data) < 0) node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0) node.right = insert(node.right, data);
        return node;
    }

    public boolean search(T data) { return search(root, data); }
    private boolean search(Node<T> node, T data) {
        if (node == null) return false;
        if (data.compareTo(node.data) < 0) return search(node.left, data);
        if (data.compareTo(node.data) > 0) return search(node.right, data);
        return true;
    }

    public void delete(T data) { root = delete(root, data); }
    private Node<T> delete(Node<T> node, T data) {
        if (node == null) return null;
        if (data.compareTo(node.data) < 0) node.left = delete(node.left, data);
        else if (data.compareTo(node.data) > 0) node.right = delete(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            T successor = min(node.right);
            node.data = successor;
            node.right = delete(node.right, successor);
        }
        return node;
    }

    public T min() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return min(root);
    }
    private T min(Node<T> node) {
        if (node.left == null) return node.data;
        return min(node.left);
    }

    public T max() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return max(root);
    }
    private T max(Node<T> node) {
        if (node.right == null) return node.data;
        return max(node.right);
    }

    public void inOrder() { inOrder(root); System.out.println(); }
    private void inOrder(Node<T> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
}
```

## 5. Edge Cases

| Scenario | Behavior |
|---|---|
| Search on empty tree | Returns false |
| Delete non-existent value | Tree unchanged |
| Delete root with two children | Inorder successor replaces root |
| Insert duplicates | Ignored — compareTo returns 0 |
| Sorted insertion | Degenerate right or left skewed tree, height = n-1 |
| min/max on empty tree | RuntimeException |

## 6. Performance Characteristics

| Operation | Balanced | Degenerate |
|---|---|---|
| Search | O(log n) | O(n) |
| Insert | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| Min/Max | O(log n) | O(n) |
| Space | O(n) | O(n) |

## 7. The Core Weakness

Insertion order determines shape. Sorted input → degenerate tree → O(n) everything.

This is why Balanced Trees exist — they restructure automatically to maintain O(log n) height regardless of insertion order.

## 8. When To Use

- Sorted dynamic data with frequent search, insert, delete.
- Need min/max efficiently.
- Need sorted iteration.
- Input order is random or unknown.

## 9. When Not To Use

- Input is sorted or nearly sorted — use balanced BST (AVL, Red-Black).
- No ordering needed — use HashMap for O(1) average.
- Data is static and sorted — binary search on array is simpler.

## 10. Common Bugs

1. Checking compareTo against wrong sign — go left when < 0, right when > 0.
2. Forgetting to return node in recursive insert/delete — tree loses connections.
3. Delete with two children — forgetting to delete the successor after copying its value.
4. min/max on empty tree — NullPointerException without empty check.

## 11. Java Built-in
```java
TreeMap<Integer, String> map = new TreeMap<>(); // Red-Black Tree backed
TreeSet<Integer> set = new TreeSet<>();          // Red-Black Tree backed
set.first(); // min
set.last();  // max
// Always O(log n) — self-balancing
```

## 12. Linked Notes

- [[Binary Tree]] — parent structure
- [[Balanced Trees]] — solves BST's degenerate case, next in Phase 4
- [[Tree General Concept]] — foundational terminology
- [[Heap]] — binary tree with different invariant, Phase 5