---
topic: frequency_array
category: linear
structure: value_indexed_counting
data: non_negative_integers
mutability: static
access: direct
priority: high
difficulty: basic
status: foundation
used_in:
  - counting_sort
  - anagram_detection
  - histogram_generation
  - duplicate_detection
  - missing_number
tags:
  - dsa
  - frequency_array
  - counting
---

# Frequency Array

## 0. The Real Problem This Structure Solves

Repeated frequency queries on a collection are O(n) each naively.

One-line anchor:
> Pay O(n) once upfront. Answer any frequency query in O(1) forever.

## 1. What It Is

A counting array where the value is used as the index and the slot stores the occurrence count.
```
arr:  [3, 1, 2, 3, 1, 3]
freq: [0, 2, 1, 3]
       ↑  ↑  ↑  ↑
       0  1  2  3
```

`freq[v]` = number of times value v appears in the input.

## 2. The Key Mental Shift

Every structure so far: use index to find value.

Frequency Array flips it — use value to find count.

## 3. Core Invariants

- Values must be non-negative integers.
- Array size = maxValue + 1.
- Size is determined by value range, not input size.
- Missing values implicitly have count 0.
- Built in two passes: find max, then populate counts.

## 4. Constraints

Works only when:
- Values are whole numbers
- Values are non-negative
- Value range is bounded and reasonably small

Breaks when:
- Values are floats, strings, or objects — not valid indices
- Values are negative — negative indices don't exist
- Value range is huge (e.g. 0 to 10,000,000) — memory becomes impractical

For negative values — offset trick: shift all values by Math.abs(min), size = max - min + 1.
For unbounded or non-integer values — use HashMap instead.

## 5. Implementation
```java
public class FrequencyArray {
    private int[] freq;
    private int mostFreq;

    FrequencyArray(int[] arr) {
        int maxRange = 0;
        for (int t : arr) maxRange = Math.max(maxRange, t);
        freq = new int[maxRange + 1];
        mostFreq = arr[0];
        for (int t : arr) {
            freq[t]++;
            if (freq[t] > freq[mostFreq]) mostFreq = t;
        }
    }

    public int getFrequency(int i) {
        if (i < 0 || i >= freq.length) throw new IndexOutOfBoundsException();
        return freq[i];
    }

    public int getMostFrequent() { return mostFreq; }

    public boolean contains(int i) {
        if (i < 0 || i >= freq.length) throw new IndexOutOfBoundsException();
        return freq[i] > 0;
    }

    public void display() {
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) System.out.printf("%d : %d\n", i, freq[i]);
        }
    }
}
```

## 6. Edge Cases

| Scenario | Behavior |
|---|---|
| Value not in input | freq[v] = 0, contains returns false |
| Value exceeds max | IndexOutOfBoundsException |
| Negative value queried | IndexOutOfBoundsException |
| All elements identical | mostFreq = that element, freq[v] = n |
| Single element array | Builds correctly, mostFreq = arr[0] |

## 7. Performance Characteristics

| Operation | Time |
|---|---|
| Build | O(n + k) where k = max value |
| getFrequency | O(1) |
| getMostFrequent | O(1) |
| contains | O(1) |
| Space | O(k) where k = max value |

## 8. When To Use

- Counting occurrences of bounded non-negative integers.
- Anagram detection (character frequencies).
- Counting sort backing structure.
- Finding duplicates, missing numbers, majority elements.
- Histogram generation.

## 9. When Not To Use

- Values are unbounded or very large — use HashMap.
- Values are negative — use offset trick or HashMap.
- Values are non-integer — use HashMap.
- Array mutates frequently — full rebuild required.

## 10. Common Bugs

1. Initializing mostFreq to -1 and handling with null-check — initialize to arr[0] instead.
2. display() printing zero-count entries — guard with if (freq[i] > 0).
3. Allocating freq of size maxValue instead of maxValue + 1 — off by one, last value has no slot.
4. Forgetting two-pass build — can't allocate before knowing max.

## 11. Linked Notes

- [[Arrays_Fundamentals]] — underlying structure
- [Hash_Map](Hash_Map) — fallback for unbounded or non-integer values
- [[Counting_Sort]] — algorithm built entirely on this structure
- [Sparse_Array](Sparse_Array.md) — related: both avoid storing what doesn't exist