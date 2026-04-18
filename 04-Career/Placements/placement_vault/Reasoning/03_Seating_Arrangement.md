---
title: Seating Arrangement — Complete Study Note
tags: [reasoning, seating-arrangement, placement]
priority: Must
status: final
---

# 💺 Seating Arrangement

---

## 🔴 TWO TYPES

| Type | Key Difference |
|------|---------------|
| **Linear** | Ends exist. Left/right are absolute. |
| **Circular** | No ends. Left/right are relative to facing direction. |

---

## 🔴 LINEAR ARRANGEMENT — RULES

- "Immediate left/right" = the person directly next to
- "2nd from left" = position counting from left end
- "3rd from right" = position counting from right end
- N people in a row: position from left + position from right = N+1

**Conversion:**
If 7 people, someone is 3rd from left → 7+1−3 = **5th from right**

---

## 🔴 CIRCULAR ARRANGEMENT — RULES

- **Facing centre** (default unless stated): 
  - Person's left = clockwise direction
  - Person's right = anti-clockwise direction
- **Facing outside**:
  - Opposite of above
- "Opposite" in circle of N people = N/2 seats away

> Always draw the circle. Place one anchor person first, then use clues.

---

## 🟠 SOLVED EXAMPLES — LINEAR

### Example 1
> 5 people A,B,C,D,E in a row. A is 2nd from left. B is immediately right of A. D is at one end. Who is in the middle?

Positions: _ A B _ _
D is at an end → D is at position 1 or 5
A=2, B=3 (immediately right of A)
Remaining: C, D, E for positions 1, 4, 5
D at end → D=1 or D=5
Middle = position 3 = **B** ✓

---

### Example 2
> 7 people in a row. P is 3rd from left. Q is 3rd from right. R is exactly between P and Q. S is immediately left of P.

P = position 3 (from left)
Q = position 5 (from right) = 7+1−3 = 5 (from left)
R = exactly between P(3) and Q(5) = position 4
S = immediately left of P = position 2

Layout: _ S P R Q _ _

Between S(2) and Q(5): positions 3 and 4 = P and R → **2 people between S and Q**

---

### Example 3 — Facing direction matters
> 8 people in a row facing North. A is 3rd from the right end.

Facing North doesn't change left/right for linear — it's just extra info.
A = 8+1−3 = **6th from left**

---

## 🟠 SOLVED EXAMPLES — CIRCULAR

### Example 1
> 6 people A,B,C,D,E,F in a circle facing centre.
> A sits opposite D. B is 2nd to the left of A. C is immediate right of D. E is between F and D.

In a 6-person circle, opposite = 3 seats away.
Place A at top (position 1). D is opposite = position 4.

B is 2nd to left of A. Facing centre, left = clockwise.
Going clockwise from A: position 2, 3, 4(D)...
B = 2nd clockwise from A = position 3 (clockwise) → B at position 3? 
Wait: 2nd to LEFT means 2 positions to the left.
Facing centre, my left = clockwise direction. So 2nd to left of A = 2 clockwise from A.

Let's number clockwise: A=1, 2, 3, D=4, 5, 6

B is 2nd to left of A (clockwise) = position 3
C is immediately right of D. D=4. Right of D = anticlockwise = position 3? But B=3.
Let's try: C immediately right of D = position 5 (anticlockwise from D... wait)

**Key:** facing centre, your RIGHT is anticlockwise.
D at position 4. D's right = anticlockwise = position 3. But B=3.
D's right = anticlockwise → if anticlockwise from 4 = 3 = B. Conflict.

Try placing: D's right (anticlockwise) = 5 instead.
Renumber: Clockwise = 1(A), 2, 3, 4(D), 5, 6
D facing centre: D's right = anticlockwise = position 3.
C = position 3. B = position 3 also? Conflict.

**Practical approach: draw it.** Place A, place D opposite. Place others relative to them.

---

### Example 2 — Standard circular
> 5 people A,B,C,D,E in a circle. A is to the immediate right of B. C is 2nd to the right of A. D is to the immediate left of E. B is 2nd to the left of D.

Place B. A is immediately right of B.
Going clockwise (= right when facing centre? — depends on setup. Let's say right = anticlockwise for facing centre. Careful here — this varies by problem source.)

**Safest method:** Draw circle, pick arbitrary seat for first anchor, place others using clue-by-clue.

---

## 🟠 SOLVING APPROACH

**Linear:**
1. Draw N blank slots
2. Place definite positions first (e.g., "3rd from left")
3. Use relative clues next ("immediately right of X")
4. Use elimination for remaining positions
5. Cross-check with end/middle clues

**Circular:**
1. Draw circle with N dots
2. Place one anchor person anywhere
3. Facing centre: YOUR right = anticlockwise, YOUR left = clockwise
4. Place each person relative to anchor
5. Verify all clues at end

---

## 🟡 PRACTICE PROBLEMS

**Linear:**

**P1.** 6 people A–F in a row. A is 4th from right. B is 3rd from left. C is between A and B. D is immediately right of B. Where is E?

**P2.** P, Q, R, S, T sit in a row. P is not at an end. Q is 2nd from the right. R is immediately left of P. S is at the right end. Where is T?

**P3.** 8 people sit in a row. M is 3rd from left, N is 5th from right. How many people sit between M and N?

**Circular:**

**P4.** A, B, C, D, E sit in a circle facing centre. A is 2nd to the right of C. B is 3rd to the left of D. E is to the immediate right of A. B is to the immediate left of C. Find the person sitting opposite A.

**P5.** 6 people in a circle. P is opposite Q. R is 2nd to the left of P. S is immediate right of Q. T is between P and S (going clockwise). Who is between Q and R?

---

## ❌ COMMON MISTAKES

- Circular: confusing which direction is left/right (always set facing direction first)
- Linear: forgetting to use (N+1) to convert from-left to from-right
- Not drawing — trying to solve in head leads to errors
- Circular "opposite" = N/2 seats away (not 1 seat)
