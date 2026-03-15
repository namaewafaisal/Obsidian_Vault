---
topic: segment-tree
category: non-linear
structure: tree-on-array | pointer-tree
data: range-based
mutability: dynamic
access: sequential

priority: high

importance_reason:
  - efficient range queries with updates
  - bridges arrays and trees
  - common in exams and competitive programming

used_in:
  - range minimum query (RMQ)
  - range sum / max queries
  - interval problems

difficulty: advanced
status: foundation

tags:
  - dsa
  - segment-tree
  - tree
  - range-query
---

# Segment Tree

## What This Note Is
This is a **progressively built note**.  
Only concepts and methods that are **fully understood and implemented** are filled.  
Remaining parts are listed explicitly and will be added later.

---

## Implementation & Concept Coverage Status

| Part | Status | Notes |
|----|------|------|
| Concept & Motivation | ✅ Done | Why prefix sum fails |
| Tree Mental Model | ✅ Done | Range-based hierarchy |
| Node Structure | ✅ Done | Pointer-based node |
| Build Tree | ✅ Done | Recursive divide & merge |
| Range Query (Min) | ✅ Done | Overlap-based querying |
| Point Update | ⏳ Pending | Next mandatory step |
| Lazy Propagation | ⏳ Pending | Advanced |
| Variants (Sum / Max) | ⏳ Pending | Easy after core |
| Iterative Segment Tree | ⏳ Pending | Optional |
| Time & Space Analysis | ⏳ Pending | To be added |
| Comparison vs Prefix Sum | ⏳ Pending | To be added |

---

## Core Insight

> A segment tree stores **precomputed answers for ranges**, allowing **logarithmic queries and updates** on an array.

---

## Mental Model / Invariant

> Each node represents a **continuous range** `[start, end]`  
> and stores the **answer for that range**.

- Root → entire array
- Leaves → single indices
- Internal nodes → merge of children

---

## Node Representation (Pointer-Based)

```java
class SegmentNode {
    int minValue;
    int start;
    int end;
    SegmentNode left;
    SegmentNode right;

    SegmentNode() {}

    SegmentNode(int minValue, int start, int end) {
        this.minValue = minValue;
        this.start = start;
        this.end = end;
    }
}
```

This representation:
- Makes ranges explicit
- Is easy to reason about
- Is ideal for learning and exams

---

## Build Segment Tree (Range Min)

### Concept
- If `start == end` → leaf
- Else:
  - split range
  - build children
  - merge using `min`

### Implementation

```java
SegmentNode build(int[] arr, int start, int end) {

    if (start == end) {
        return new SegmentNode(arr[start], start, end);
    }

    int mid = (start + end) / 2;

    SegmentNode node = new SegmentNode();
    node.start = start;
    node.end = end;

    node.left = build(arr, start, mid);
    node.right = build(arr, mid + 1, end);

    node.minValue = Math.min(node.left.minValue, node.right.minValue);

    return node;
}
```

---

## Range Query — Minimum (RMQ)

### Concept (Critical)
Every query is classified into **three cases**:
1. No overlap
2. Complete overlap
3. Partial overlap

### Identity Value
- For **min query** → `Integer.MAX_VALUE`

### Implementation

```java
int queryMin(SegmentNode node, int ql, int qr) {

    // No overlap
    if (qr < node.start || ql > node.end) {
        return Integer.MAX_VALUE;
    }

    // Complete overlap
    if (ql <= node.start && node.end <= qr) {
        return node.minValue;
    }

    // Partial overlap
    int leftMin = queryMin(node.left, ql, qr);
    int rightMin = queryMin(node.right, ql, qr);

    return Math.min(leftMin, rightMin);
}
```

---

## Usage Example

```java
int[] arr = {3, 4, 7, 6, 1, 9};

SegmentNode tree = new SegmentNode();
SegmentNode root = tree.build(arr, 0, arr.length - 1);

int ans = tree.queryMin(root, 1, 4); // returns 1
```

---

## Inbuilt / Collections Support (Important Reality)

### ❌ Java does NOT have a built-in Segment Tree

There is:
- ❌ no `SegmentTree` class
- ❌ no standard library support

### What Java DOES provide (alternatives)

| Structure | Use Case | Limitation |
|--------|---------|-----------|
| `TreeMap` | Ordered keys | No range aggregation |
| `PriorityQueue` | Min/Max | No range queries |
| `Arrays.prefixSum` (manual) | Static queries | O(n) updates |

👉 For **range query + update**, **Segment Tree must be implemented manually**.

---

## What Is Intentionally NOT Added Yet

- Point Update logic
- Lazy propagation
- Iterative tree
- Complexity analysis
- Variants (sum / max)

These will be appended **only after learning**, not guessed.

---

## One-Line Recall

> A segment tree is a **range-based binary tree** built over an array that enables **logarithmic queries and updates**.

