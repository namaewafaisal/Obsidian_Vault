---
topic: dynamic-programming
difficulty: easy
status: done
patterns:
  - dp
  - row-construction
tags:
  - dsa
  - array
  - dp
  - pascals-triangle
date: 2026-07-03
---
# 118. Pascal's Triangle

## Problem

**Input:**
- Integer `numRows`

**Output:**
- First `numRows` of Pascal's Triangle

```
Input:  numRows = 5

Output:
[
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
]
```

---

## Pattern

**Dynamic Programming (Build from Previous Row)**

---

## Core Idea

- Every row starts and ends with `1`.
- Each middle element is the sum of the two elements directly above it.

```text
curr[j] = prev[j-1] + prev[j]
```

Each row depends only on the previous row.

---

## Dry Run

```
Row 0:
[1]

Row 1:
[1,1]

Row 2:
1
1+1 = 2
1
→ [1,2,1]

Row 3:
1
1+2 = 3
2+1 = 3
1
→ [1,3,3,1]
```

---

## Code

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if(numRows == 0) return result;

        List<Integer> row0 = new ArrayList<>();
        row0.add(1);
        result.add(row0);

        if(numRows == 1) return result;

        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(1);
        result.add(row1);

        for(int i = 2; i < numRows; i++) {
            List<Integer> prev = result.get(i - 1);
            List<Integer> curr = new ArrayList<>();

            curr.add(1);

            for(int j = 1; j < prev.size(); j++) {
                curr.add(prev.get(j) + prev.get(j - 1));
            }

            curr.add(1);
            result.add(curr);
        }

        return result;
    }
}
```

---

## Things to Remember

- First and last element of every row is always `1`.
- Only the previous row is needed to construct the current row.
- Middle elements follow:

```text
prev[j-1] + prev[j]
```

---

## One-Line Recall

> Build each row from the previous one: edges are `1`, and every middle element is the sum of the two elements above it.