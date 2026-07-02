#pattern #dsa #divide-and-conquer #memoization

# Divide & Conquer — "Generate All Structures" Pattern

## Problem Index

- [x] [[Problems/95. Unique Binary Search Trees II]] ✅ 2026-07-02
- [x] [[241. Different Ways to Add Parentheses]] ✅ 2026-07-02
- [ ] [[894. All Possible Full Binary Trees]]
- [ ] [[1130. Minimum Cost Tree From Leaf Values]]
- [ ] [[1039. Minimum Score Triangulation of Polygon]]
- [ ] [[96. Unique Binary Search Trees]] (counting variant — sibling pattern)
- [ ] [[312. Burst Balloons]] (interval DP, same "pick a split/last action" idea)
- [ ] [[329. Longest Increasing Path in a Matrix]] (memoized DFS, different shape but same caching instinct)

---

## When to recognize this pattern

Ask yourself: **"can I split this problem at any point/position, solve each side independently, and combine the results?"**

Trigger phrases in the problem statement:
- "Generate **all** possible..."
- "Return **all** results/structures/ways..."
- Something can be broken at multiple valid points (a range of numbers, an expression, a string, a polygon)
- The pieces on either side of the break point don't interact with each other — they're **independent subproblems**

This is different from a plain DP counting problem ("how many ways") — here you must **construct and return every actual structure/value**, not just a count.

---

## The Skeleton

```java
Map<KeyType, List<ResultType>> dp = new HashMap<>();

List<ResultType> helper(RangeOrString input) {

    // 1. Memo check — always first
    if (dp.containsKey(input)) return dp.get(input);

    List<ResultType> results = new ArrayList<>();

    // 2. Base case — smallest unit, cannot be split further
    if (isBaseCase(input)) {
        results.add(buildBaseResult(input));
        dp.put(input, results);
        return results;
    }

    // 3. Try every valid split point
    for (splitPoint : allPossibleSplits(input)) {

        List<ResultType> leftResults  = helper(leftPartOf(input, splitPoint));
        List<ResultType> rightResults = helper(rightPartOf(input, splitPoint));

        // 4. Cartesian product — combine every left with every right
        for (ResultType left : leftResults) {
            for (ResultType right : rightResults) {
                results.add(combine(left, right, splitPoint));
            }
        }
    }

    dp.put(input, results);
    return results;
}
```

That's the entire template. Every problem in the index above is a reskin of this.

---

## The Four Pillars (map these for every new problem)

| Pillar | Question to ask | BST II | Add Parentheses |
|---|---|---|---|
| **State** | What identifies a subproblem? | `(start, end)` range of values | substring itself |
| **Split point** | What are the valid places to break? | every value in `[start, end]` as root | every operator character |
| **Base case** | What can't be split further? | empty range (`start > end`) | pure number (no operator found) |
| **Combine** | How do left + right + split-point become one result? | `new TreeNode(root)` with `.left`/`.right` set | apply the operator: `l+r`, `l-r`, `l*r` |

When you hit a new problem, fill this table out **before** writing code. It forces you to nail the recursion shape before worrying about syntax.

---

## Gotchas (the actual bugs you'll hit)

### 1. Empty base case still needs a placeholder in the list
If a subproblem can legitimately be "nothing" (e.g. empty subtree), you must return a **list containing one sentinel value** (like `[null]`), not an empty list `[]`.

Why: the combine step does `for (left : leftResults) for (right : rightResults)`. If `leftResults` is truly empty, that inner loop **never runs**, and you silently lose every combination that should have included an empty left side.

```java
if (start > end) {
    trees.add(null);   // NOT: return trees; (empty)
    return trees;
}
```

### 2. Memoize the *state*, not something incidental
Cache key must uniquely represent the subproblem:
- Range problems → `start + "," + end` or a combined int key
- String problems → the substring itself works fine as a key (Java Strings are hashable, and substrings of a fixed input are naturally memo-friendly)

### 3. Cache before you return, on every exit path — including the base case
It's easy to add memoization only in the "main" path and forget to `dp.put()` in the base case. This doesn't cause wrong answers, but it silently defeats the purpose of caching for that branch and can still leave exponential blowup depending on where the base case sits in the recursion tree.

### 4. Don't try to "flatten" the problem into pre-parsed arrays first
Instinct: split the input into a `numbers[]` array and an `operators[]` array upfront, then index into both with `start`/`end`.
Reality: unnecessary. Recursing directly on the **substring** (or the **range**) already gives you everything you need — the base case naturally falls out (no operator found = pure number; `start > end` = no values left). Pre-parsing adds bookkeeping without adding power.

### 5. This is Cartesian product, not sum
Counting DP (LeetCode 96, Catalan numbers) sums or multiplies **counts**. This pattern nests two loops and **actually builds** `leftCount × rightCount` distinct results. That's *why* these problems blow up fast (exponential/Catalan-scale output) — and also why memoization only helps the number of *subproblems* computed, not the final output size.

---

## Mental checklist before coding

1. What is my **state** — a range `(start, end)` or a **string**?
2. What are the **valid split points** — every index? every value? every operator?
3. What's the **base case** — and does it need a sentinel value in the returned list?
4. What does **combine** actually construct — a new tree node? an arithmetic result? a concatenated structure?
5. Is my memo key actually unique per state?

If you can answer all five before touching the keyboard, the code basically writes itself — same as it did for BST II once you'd internalized it.
