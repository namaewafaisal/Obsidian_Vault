---
topic: fast_slow_pointer
category: pointer_pattern
structure: sequential_traversal
pattern_type: differential_speed
priority: high
difficulty: intermediate
status: core_pattern
used_in:
  - cycle_detection
  - cycle_start_detection
  - middle_of_list
  - nth_from_end
  - palindrome_linked_list
tags:
  - dsa
  - pointer
  - fast_slow
  - floyd
---

# Fast–Slow Pointer Technique — Differential Speed Pattern

## 0. The real problem this pattern solves

When traversing a sequential structure (usually a linked list), we sometimes need to:

- Detect a structural anomaly (cycle)
- Find a midpoint
- Measure relative position without knowing length

Using two pointers moving at different speeds allows us to extract structural information in one pass.

**One-line anchor:**  
> Use speed difference to reveal hidden structure.

---

# 1. Core Idea

Maintain two pointers:

- `slow` → moves 1 step
- `fast` → moves 2 steps

```java
slow = slow.next;
fast = fast.next.next;
```

The difference in speed is the key.

---

# 2. Pattern Variants

## A. Detect Cycle

If structure contains a loop:

- Fast eventually laps slow.
- They meet inside cycle.

If no loop:
- Fast reaches null.

---

## B. Find Middle of Linked List

If no cycle:

When fast reaches end:
- Slow is at midpoint.

```java
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;
```

---

## C. Find Nth Node from End (Variant)

Advance fast by `n` steps first.

Then move both pointers at same speed.

When fast reaches end:
- Slow is at desired position.

---

# 3. Why It Works (Intuition, Not Just Formula)

Inside a cycle:

- Slow moves 1 step.
- Fast moves 2 steps.
- Speed difference = 1 step per iteration.

This guarantees:
- Fast closes the gap by 1 each iteration.
- Eventually gap becomes zero.

Like two runners on circular track.

---

# 4. Phase 2 Alignment (Cycle Start Detection)

After slow and fast meet:

1. Move one pointer to head.
2. Keep the other at meeting point.
3. Move both 1 step at a time.
4. They meet at cycle start.

Why equal speed?

Because:

Distance(head → cycle start)
=
Distance(meeting → cycle start)

Equal speed preserves that alignment.

---

# 5. Mathematical Insight (Minimal Necessary)

Let:

- L = distance from head to cycle start
- C = cycle length
- x = distance from cycle start to meeting point

At meeting:

```
L + x = kC
```

Which implies:

```
L = kC - x
```

So:

Distance from head to start  
equals  
Remaining distance in cycle from meeting point.

---

# 6. Time & Space Complexity

| Operation | Time | Space |
|------------|------|--------|
| Detection | O(n) | O(1) |
| Start Find | O(n) | O(1) |

No extra memory required.

---

# 7. When to Use This Pattern

Use when:

- Structure is sequential
- No random access
- Memory must remain O(1)
- Length unknown

Common problems:

- Cycle detection
- Find middle
- Remove nth from end
- Palindrome check
- Linked list partitioning

---

# 8. When NOT to Use

Avoid when:

- Random access available (arrays)
- Extra memory acceptable and simpler (HashSet)
- Structure not sequential

---

# 9. Common Mistakes

- Forgetting `fast.next != null`
- Comparing values instead of node references
- Moving fast only 1 step accidentally
- Not resetting slow to head in phase 2
- Using different speeds in phase 2

---

# 10. Deep Structural Insight

Phase 1:
> Speed difference reveals existence of structure.

Phase 2:
> Equal speed preserves structural alignment.

Different speeds for different goals.

---

# 11. Final Mental Model

> Fast–Slow pointer is not a trick.  
> It is differential traversal used to infer hidden structure.

---

## Linked Notes

- [[Singly_Linked_List]]
- [[Circular_Linked_List]]
- [[Linked_List_Cycle_Detection]]
- [[Two_Pointers_Pattern]]