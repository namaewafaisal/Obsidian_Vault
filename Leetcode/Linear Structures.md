# Linear Structures Mastery Plan (Pre-Tree Phase)

Focus:
- Structural invariants
- Boundary discipline
- Pointer correctness
- Circular logic
- Stack/Queue behavioral clarity

---

## Phase A — Arrays (Contiguous Memory Discipline)

- [[26. Remove Duplicates from Sorted Array]]
- [[../../27. Remove Element]]
- [[283. Move Zeroes]]
- [[88. Merge Sorted Array]]
- [[189. Rotate Array]]
- [[35. Search Insert Position]]
- [[66. Plus One]]

Goal:
- In-place updates
- Index safety
- Logical size vs physical capacity
- Boundary invariants

---

## Phase B — Strings (Array-Backed Behavior)

- [[125. Valid Palindrome]]
- [[344. Reverse String]]
- [[14. Longest Common Prefix]]
- [[28. Find the Index of the First Occurrence in a String]]
- [[58. Length of Last Word]]

Goal:
- Character-level control
- Immutability awareness
- Avoid unnecessary object creation
- Safe pointer/index movement

---

## Phase C — Linked Lists (Pointer Discipline)

- [[206. Reverse Linked List]]
- [[21. Merge Two Sorted Lists]]
- [[83. Remove Duplicates from Sorted List]]
- [[Problems/203. Remove Linked List Elements]]
- [[876. Middle of the Linked List]]
- [[141. Linked List Cycle]]
- [[142. Linked List Cycle II]]

Goal:
- Pointer reassignment correctness
- Head/tail edge cases
- Fast–slow pointer reasoning
- Null safety discipline
- Structural invariants preservation

---

## Phase D — Stack (LIFO Behavior Enforcement)

- [[20. Valid Parentheses]]
- [[1047. Remove All Adjacent Duplicates In String]]
- [[1441. Build an Array With Stack Operations]]

Goal:
- LIFO reasoning
- Proper stack usage
- Avoid misuse of Stack class
- Prefer ArrayDeque in Java

---

## Phase E — Queue (FIFO Discipline & Circular Logic)

- [[933. Number of Recent Calls]]
- [[225. Implement Stack using Queues]]
- [[232. Implement Queue using Stacks]]

Goal:
- FIFO invariants
- Circular reasoning reinforcement
- Structural duality understanding
- Behavior vs implementation clarity

---

## Phase F — Deque (Double Boundary Control)

- [[641. Design Circular Deque]]

Goal:
- Front/rear manipulation mastery
- Circular arithmetic confidence
- Unified stack/queue abstraction understanding

---

# Exit Criteria Before Moving to Trees

You should be able to:

- Implement stack and queue from memory.
- Explain circular queue invariants confidently.
- Reverse a linked list without hesitation.
- Explain fast–slow pointer without formulas.
- Distinguish ArrayDeque vs LinkedList in production.

Only then move to trees.