---
title: Coding-Decoding & Series — Complete Study Note
tags: [reasoning, coding-decoding, series, placement]
priority: High
status: final
---

# 🔐 Coding-Decoding & Series

---

# PART 1: CODING-DECODING

## 🔴 TYPES OF CODING

### Type 1 — Letter Shift
Each letter is shifted by a fixed number.
**A=1, B=2, C=3 ... Z=26**

If BOOK → CPPL: each letter +1 (B→C, O→P, O→P, K→L)

**How to find the shift:** Compare first letter of word to first letter of code.
B(2) → C(3) = +1 shift.

Reverse shift works same way. If code → word, subtract the shift.

### Type 2 — Reverse Alphabet
Z=1, Y=2, X=3 ... A=26 (opposite mapping)
Or: A↔Z, B↔Y, C↔X (mirror mapping)

Check: if A→Z and Z→A, it's mirror coding.

### Type 3 — Number Codes
Letters replaced by numbers. Usually position-based.
CAT=24 means C+A+T = 3+1+20 = 24 (sum of positions)
DOG=26 means 4+15+7 = 26 ✓

### Type 4 — Symbol/Word Codes
Given: "15 means good boy, 59 means good morning, 96 means morning walk"
Find the code for a word:
- "good" appears in both 15 and 59 → common digit is 5 → good=5
- "morning" appears in 59 and 96 → common digit is 9 → morning=9
- Therefore "boy"=1, "walk"=6

### Type 5 — Conditional Codes (with rules)
Rules like:
- Vowel at start → reverse the word first
- If 3rd letter is consonant → shift +2
Read the rules carefully, apply in sequence.

---

## 🔴 SOLVING APPROACH — CODING

1. Find the **pattern** first (shift? reverse? sum? mirror?)
2. Verify with all given examples
3. Apply same pattern to the new word
4. For number codes — look for common numbers across shared words

---

## 🟠 SOLVED EXAMPLES — CODING

### Example 1 — Letter shift
> FRIEND is coded as HUMJTK. Code CANDLE?

F(6)→H(8): +2, R(18)→U(21): +3? No that's not consistent.
F→H(+2), R→U(+3)? Not same shift.
Try: F→H(skip 1), R→U(skip 2)? No.
Actually: F+2=H, R+3=U, I+4=M, E+5=J, N+6=T, D+7=K
Shifts are: 2,3,4,5,6,7 (incrementing by 1)

CANDLE: C+2=E, A+3=D, N+4=R, D+5=I, L+6=R, E+7=L → **EDRIRL**

### Example 2 — Sum code
> If CAT=24, DOG=26, COW=?

C+A+T = 3+1+20 = 24 ✓
D+O+G = 4+15+7 = 26 ✓
C+O+W = 3+15+23 = **41**

### Example 3 — Word code
> 15 = good boy, 59 = good morning, 96 = morning walk
> What does "walk" mean?

good appears in 15 and 59 → common digit = 5 → good=5
morning appears in 59 and 96 → common = 9 → morning=9
boy=1 (in 15, 5=good so 1=boy)
walk=6 (in 96, 9=morning so 6=walk)
**walk = 6**

---

## 🟡 PRACTICE — CODING

**P1.** If MANGO is coded as NBNHP, how is APPLE coded?
**P2.** If 23 = hot day, 35 = cold night, 57 = hot night, what is the code for "day"?
**P3.** If A=2, B=4, C=6 (each letter = 2×position), what is CAT?
**P4.** ROAD is coded as URDG. Code STAR?

---

# PART 2: NUMBER & LETTER SERIES

## 🔴 COMMON SERIES PATTERNS

### Pattern 1 — Constant difference (AP)
2, 5, 8, 11, 14... → difference = 3
Next = 14+3 = **17**

### Pattern 2 — Increasing difference
1, 2, 4, 7, 11, 16... → differences: 1,2,3,4,5
Next difference = 6 → 16+6 = **22**

### Pattern 3 — Multiplying factor (GP)
3, 6, 12, 24... → ×2 each time
Or: 2, 6, 18, 54... → ×3

### Pattern 4 — Squares/Cubes
1, 4, 9, 16, 25... → n²
1, 8, 27, 64, 125... → n³
2, 5, 10, 17, 26... → n²+1

### Pattern 5 — Two alternating series
2, 3, 6, 7, 18, 19, 54... 
Odd terms: 2, 6, 18, 54 → ×3
Even terms: 3, 7, 19... → (×3+1)? Check: 3→7: ×3−2? No. 3→7(+4), 7→19(+12). Hmm — treat as separate interleaved series.

### Pattern 6 — n²+n or n(n+1)
2, 6, 12, 20, 30... → 1×2, 2×3, 3×4, 4×5, 5×6 → next = 6×7 = **42**

### Pattern 7 — Previous two terms added (Fibonacci-type)
1, 1, 2, 3, 5, 8, 13... → each = sum of previous two

---

## 🔴 LETTER SERIES

Map letters to numbers (A=1, B=2...) and find the numeric pattern.

A, C, F, J, O... → gaps: +2, +3, +4, +5 → next gap = +6 → O+6 = **U**

---

## 🟠 SOLVED EXAMPLES — SERIES

### Example 1
> 3, 8, 15, 24, 35, ?
Differences: 5, 7, 9, 11, 13 → next = 13
35+13 = **48**

### Example 2
> 2, 3, 5, 9, 17, ?
Pattern: each term = previous term × 2 − 1
3=2×2−1, 5=3×2−1, 9=5×2−1, 17=9×2−1
Next = 17×2−1 = **33**

### Example 3
> Find next: A, C, F, J, O, ?
A(1)+2=C(3), C(3)+3=F(6), F(6)+4=J(10), J(10)+5=O(15), O(15)+6=U(21)
Next = **U**

---

## 🟡 PRACTICE — SERIES

**P1.** 1, 4, 9, 16, 25, 36, ?
**P2.** 5, 10, 17, 26, 37, ?
**P3.** 1, 2, 4, 8, 16, ?
**P4.** 3, 5, 9, 17, 33, ?
**P5.** B, D, G, K, P, ?
**P6.** 2, 12, 36, 80, 150, ?
**P7.** 7, 13, 25, 49, 97, ?

---

## ❌ COMMON MISTAKES

- Checking only addition pattern — look for multiplication too
- Missing alternating series (treat odd/even positions separately)
- Letter series: mapping wrong (A=1, B=2, not A=0)
- Coding: not verifying pattern on all given examples before applying
