---
topic: prefix_storage_structures
category: linear
structure: precomputed_cumulative
data: numeric
mutability: static
access: range_query
priority: high
difficulty: basic
status: foundation
used_in:
  - range_sum_queries
  - range_average_queries
  - sliding_window
  - image_processing
tags:
  - dsa
  - prefix_sum
  - precomputation
---

# Prefix Storage Structures

## 0. The Real Problem This Structure Solves

Repeated range queries on a static array are O(n) each naively.

10,000 queries on a 100,000 element array — catastrophically slow.

One-line anchor:
> Pay O(n) once upfront. Answer every range query in O(1) forever.

## 1. What It Is

A precomputed array where each slot holds the cumulative sum from index 0 to that index.
```
arr:    [3,  1,  4,  1,  5]
prefix: [3,  4,  8,  9,  14]
```

`prefix[i]` = sum of all elements from index 0 to i inclusive.

## 2. Core Invariants

- prefix[0] = arr[0]
- prefix[i] = prefix[i-1] + arr[i]
- Any range sum l→r = prefix[r] - prefix[l-1]
- When l = 0, subtract 0 (no prefix[l-1] exists)
- prefix array length == original array length

## 3. The Range Sum Formula
```
rangeSum(l, r) = prefix[r] - (l == 0 ? 0 : prefix[l-1])
```

Why it works:
- prefix[r] contains sum of 0→r
- prefix[l-1] contains sum of 0→(l-1)
- Subtracting removes everything before l, leaving exactly l→r

## 4. The Trade-off

Naive range query: O(n) per query, O(1) space.

Prefix array: O(n) build once, O(1) per query, O(n) space.

Prefix wins when queries outnumber updates.
Prefix loses when the array changes frequently — every update forces full recompute.

## 5. Implementation
```java
public class PrefixSum {
    int[] prefix;

    public PrefixSum(int[] arr) {
        prefix = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            prefix[i] = arr[i] + (i == 0 ? 0 : prefix[i - 1]);
        }
    }

    private void validateRange(int l, int r) {
        if (l < 0 || l >= prefix.length) throw new IndexOutOfBoundsException();
        if (r < 0 || r >= prefix.length) throw new IndexOutOfBoundsException();
        if (l > r) throw new IllegalArgumentException();
    }

    public int rangeSum(int l, int r) {
        validateRange(l, r);
        return prefix[r] - (l == 0 ? 0 : prefix[l - 1]);
    }

    public double rangeAvg(int l, int r) {
        validateRange(l, r);
        return rangeSum(l, r) / (double) (r - l + 1);
    }
}
```

## 6. Edge Cases

| Scenario | Behavior |
|---|---|
| l = 0 | prefix[-1] avoided by ternary, subtract 0 |
| l = r | Single element — returns arr[l] correctly |
| l > r | IllegalArgumentException — logically invalid range |
| Full array (l=0, r=n-1) | Returns prefix[n-1] — correct |
| Single element array | prefix[0] = arr[0], rangeSum(0,0) = arr[0] |

## 7. Performance Characteristics

| Operation | Time |
|---|---|
| Build prefix array | O(n) |
| rangeSum | O(1) |
| rangeAvg | O(1) |
| Space | O(n) |

## 8. When To Use

- Multiple range queries on a static array.
- Cumulative metrics, analytics dashboards.
- Image processing (2D prefix sums).
- Any problem where ranges are queried repeatedly.

## 9. When Not To Use

- Array updates frequently — recompute cost kills the benefit.
- Only one query ever needed — precomputation overhead not worth it.
- Memory is tightly constrained — prefix array doubles space usage.

## 10. Common Bugs

1. Forgetting l > r guard — returns silent negative value.
2. Duplicating bounds check and formula across methods — extract validateRange().
3. Using prefix[l] instead of prefix[l-1] — off by one, includes one extra element.
4. Integer overflow on large arrays — consider long[] for large sums.

## 11. Linked Notes

- [[Arrays_Fundamentals]] — underlying structure
- [[Sliding_Window]] — related range technique
- [[Sparse_Array]] — next in Phase 3
- [[Segment_Tree]] — handles mutable range queries, Phase 8
