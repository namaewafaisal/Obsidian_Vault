---
topic: linked_list_cycle_detection
category: linear
structure: pointer_movement_pattern
data: node_references
mutability: traversal_only
access: sequential
priority: high
difficulty: intermediate
status: core_pattern
used_in:
  - cycle_detection
  - cycle_start_detection
  - fast_slow_pointer
  - interview_questions
tags:
  - dsa
  - linked_list
  - fast_slow
  - floyd
---

# Linked List Cycle Detection & Cycle Start — Fast–Slow Pointer Technique

## 0. The real problem this pattern solves

A linked list may contain a cycle:

```
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ← ←
```

We need to:

1. Detect whether a cycle exists.
2. Find the exact node where the cycle begins.

Using extra memory (HashSet) works, but optimal solution uses O(1) space.

**One-line anchor:**  
> Use speed difference to detect, equal speed to align.

---

# 1. Detecting a Cycle (Floyd’s Algorithm)

## Core Idea

Use two pointers:

- `slow` → moves 1 step
- `fast` → moves 2 steps

If there is a cycle:
- fast will eventually "lap" slow
- they will meet inside the cycle

If no cycle:
- fast reaches null

---

## Implementation

```java
public boolean hasCycle(Node head) {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;
        }
    }

    return false;
}
```

---

## Why It Works

Inside a cycle:

- slow moves 1 step
- fast moves 2 steps
- speed difference = 1 step per iteration

Fast closes the gap by 1 each iteration.

Eventually gap becomes 0 → they meet.

This is like two runners on a circular track.

---

# 2. Finding the Start of the Cycle

After slow and fast meet:

1. Move one pointer to head.
2. Keep the other at meeting point.
3. Move both 1 step at a time.
4. They meet at cycle start.

---

## Implementation

```java
public Node detectCycleStart(Node head) {
    Node slow = head;
    Node fast = head;

    // Phase 1: detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            break;
        }
    }

    if (fast == null || fast.next == null) {
        return null; // no cycle
    }

    // Phase 2: find start
    slow = head;

    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }

    return slow; // cycle start
}
```

---

# 3. Why Phase 2 Works

Let:

- L = distance from head to cycle start
- C = cycle length
- x = distance from cycle start to meeting point

When they meet:

```
L + x = kC
```

Which implies:

```
L = kC - x
```

Meaning:

Distance(head → cycle start)
=
Distance(meeting → cycle start)

So if both move at same speed:
- they reach start simultaneously.

---

# 4. Why Equal Speed in Phase 2 Is Required

Phase 1 uses speed difference to detect cycle.

Phase 2 requires equal speed to preserve alignment.

If speeds differ:
- synchronization breaks
- they may not meet at cycle start.

---

# 5. HashSet Alternative (Comparison)

Alternative approach:

```java
Set<Node> visited = new HashSet<>();
```

### Pros:
- Easy to reason about

### Cons:
- O(n) extra space
- More allocations
- More GC pressure
- Slower constant factors

Fast–slow pointer:
- O(1) space
- Cleaner
- Interview standard

---

# 6. Time & Space Complexity

| Method | Time | Space |
|--------|------|--------|
| Fast–Slow | O(n) | O(1) |
| HashSet | O(n) | O(n) |

---

# 7. Common Bugs

- Forgetting `fast.next != null` check
- Using `while (fast != null)` only
- Not resetting slow to head in Phase 2
- Comparing data instead of node reference

Correct comparison:

```java
if (slow == fast)
```

Not:

```java
slow.data == fast.data
```

---

# 8. When to Use

Use fast–slow pointer when:

- Detecting cycle
- Finding middle of list
- Removing nth from end
- Checking palindrome
- Partitioning list

This is a core linked list pattern.

---

# 9. Final Mental Model

> Phase 1 uses speed difference to reveal structure.  
> Phase 2 uses equal speed to align structure.

---

## Linked Notes

- [[Singly_Linked_List]]
- [[Circular_Linked_List]]
- [Fast_Slow_Pointer_Technique](Fast_Slow_Pointer_Technique)
- [[Two_Pointers_Pattern]]