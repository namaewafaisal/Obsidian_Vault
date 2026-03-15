# Data Structures and Algorithms (DSA) in Java

Learning **Data Structures and Algorithms (DSA)** is fundamental for anyone in computer science, from students to industry professionals, as it forms the **building blocks of programming** and is crucial for developing **efficient and optimized software**.

---

## What is DSA?

- **Algorithm**: A **set of steps or instructions for completing a task**.  
  In CS, it’s the sequence of steps a program takes to finish a task.  
  👉 Example: recipes, driving directions, sorting steps.

- **Data Structure**: A **meaningful way of arranging and storing data** in memory.  
  It defines not just data but also the **relationships** and **operations** possible.  

---

## Why Use DSA?

- **Efficiency** → Save **time** and **memory**.  
- Create software that runs **fast** and **scales**.  

---

## Data Structures: Organizing Information

Data structures are chosen based on **problem requirements**.  
They can be categorized as:

### Linear Data Structures
- **Fixed-size**: Arrays
- **Dynamic**: ArrayList, Linked List, Stacks, Queues

### Non-linear Data Structures
- Trees, Graphs

### Associative Data Structures
- Key-value based (HashMaps, HashSets)

---

### Common Data Structures

- **Arrays**
  - Stored in **contiguous memory**  
  - Access: O(1)  
  - Insert/Delete mid-way: O(N)  

- **Linked Lists**
  - Nodes connected with references  
  - Types: Singly, Doubly  
  - Insert/Delete at ends: O(1)  
  - Random access: O(N)  

- **Stacks (LIFO)**
  - Operations: `push()`, `pop()`, `peek()`  

- **Queues (FIFO)**
  - Operations: `enqueue()`, `dequeue()`, `peek()`  

- **Deques**
  - Insert/Delete at **both ends**  

- **Priority Queues**
  - Based on **priority** (usually a heap)  

- **Hash Tables (Maps/Sets)**
  - Key-value pairs  
  - Average O(1) ops  
  - Handle collisions (e.g., chaining)  

- **Trees**
  - Hierarchical nodes  
  - Binary Tree, BSTs  
  - Balanced: AVL, Red-Black, Splay  

- **Graphs**
  - Vertices + edges  
  - Directed / Undirected  

---

## Algorithms: Solving Problems Efficiently

Algorithms are judged by:  
- **Time Complexity**  
- **Space Complexity**  
(using **Big-O notation**)

### Common Algorithm Types

- **Searching**
  - Linear Search: O(N)  
  - Binary Search: O(log N)  

- **Sorting**
  - Selection Sort: O(N²)  
  - Insertion Sort: O(N²) worst, O(N) best  
  - Merge Sort: O(N log N)  
  - Quick Sort: O(N log N) avg, O(N²) worst  
  - Heap Sort: O(N log N)  
  - Bubble Sort: O(N²)  

- **Graph Traversal**
  - DFS (stack/recursion)  
  - BFS (queue)  

- **Other Paradigms**
  - **Recursion**: self-calling functions  
  - **Divide & Conquer**: split into subproblems (Merge Sort, Quick Sort)  
  - **Dynamic Programming (DP)**: store subproblem results (e.g., Floyd-Warshall)  
  - **Backtracking**: try → reject if invalid → backtrack  

---

## Java-Specific DSA Implementation

Java provides **OOP features** + **Collections Framework** that simplify DSA.

### Key Java Features
- **Classes & Objects** → ADTs
- **Interfaces** → enforce structure (e.g., `List`, `Set`)
- **Abstract Classes**
- **Inheritance & Polymorphism**
- **Generics** → Type safety (`ArrayList<E>`)
- **Recursion** → JVM call stack

---

## Java Collections Framework (`java.util`)

### Arrays
- `java.util.Arrays` → utility methods like `Arrays.sort()`

### Lists
- **`ArrayList`**
  - Dynamic array  
  - Random access O(1), insert mid O(N)  
- **`LinkedList`**
  - Doubly linked  
  - Implements `List`, `Queue`, `Deque`  

### Stacks & Queues
- **`Stack`** → LIFO (`push`, `pop`, `peek`)  
- **`Queue` (Interface)** → FIFO (`offer`, `poll`, `peek`)  
- **`ArrayDeque`** → Double-ended queue  

### Priority Queues
- **`PriorityQueue`** → Heap-based, uses `Comparator`

### Sets
- **`HashSet`** → unordered, O(1) average ops  
- **`LinkedHashSet`** → maintains insertion order  
- **`TreeSet`** → sorted (Red-Black tree), O(log N)  

### Maps
- **`HashMap`** → O(1) average ops  
- **`LinkedHashMap`** → maintains insertion order  
- **`TreeMap`** → sorted by key, O(log N)  

---

## Common DSA Concepts in Java

- **Sorting**
  - `Arrays.sort()` / `Collections.sort()`  
  - Custom order → `Comparator`  

- **Searching**
  - `contains()`, `indexOf()`  
  - Implement linear/binary search manually  

- **Trees**
  - Custom `Node` classes (left, right, parent refs)  
  - Traversals → preorder, inorder, postorder, level-order  

- **Graphs**
  - Represent with adjacency list or adjacency matrix  
  - Traversal with DFS/BFS  

---

## Summary

Learning DSA in Java requires:
1. **Understanding theory** (Big-O, efficiency)  
2. **Learning structures & algorithms**  
3. **Implementing them in Java**  
4. **Leveraging Collections Framework** for real-world coding  

---
