---
title: Algebra (AP, GP, HP) — Aptitude Master Notes
tags: [aptitude, quantitative, algebra, ap, gp, hp]
status: active
exam: Placement Aptitude
priority: Medium
---
# 🧮 Algebra — AP, GP & HP (Exam-Oriented Master Notes)

> **Scope:** Placement aptitude (TCS, Accenture, Infosys, Wipro, Capgemini)  
> **Goal:** Identify progressions quickly and apply direct logic/formulas

---

## 1️⃣ Arithmetic Progression (AP)

### Definition
A sequence in which the **difference between consecutive terms is constant**.

Example:
```
2, 5, 8, 11, 14
```

---

### Key Terms
- First term = \(a\)
- Common difference = \(d\)
- Number of terms = \(n\)

---

### General Form of AP
```
a, a + d, a + 2d, a + 3d, ...
```

---

### nth Term of AP
$$
a_n = a + (n - 1)d
$$

---

### Sum of n Terms of AP
$$
S_n = \frac{n}{2}[2a + (n - 1)d]
$$

or
$$
S_n = \frac{n}{2}(a + l)
$$
(where \(l\) = last term)

---

### Important Properties
- Average of all terms = middle term
- AP can be increasing, decreasing, or constant
- Negative common difference is allowed

---

### Common AP Models in Exams
- Find nth term
- Find number of terms
- Find sum of terms
- Find missing term
- Natural / even / odd numbers as AP

---

## 2️⃣ Geometric Progression (GP)

### Definition
A sequence in which the **ratio between consecutive terms is constant**.

Example:
```
3, 6, 12, 24
```

---

### Key Terms
- First term = \(a\)
- Common ratio = \(r\)

---

### General Form of GP
```
a, ar, ar², ar³, ...
```

---

### nth Term of GP
$$
a_n = ar^{n - 1}
$$

---

### Sum of n Terms of GP
If \(r \neq 1\):
$$
S_n = \frac{a(r^n - 1)}{r - 1}
$$

---

### Infinite GP (Very Important)
If:
$$
|r| < 1
$$
Then sum of infinite GP exists:
$$
S_\infty = \frac{a}{1 - r}
$$

---

### Important Properties
- GP can grow or decay
- Fractional and negative ratios are allowed
- Later terms dominate the sum when \(r > 1\)

---

### Common GP Models in Exams
- Find nth term
- Find common ratio
- Identify GP
- Infinite GP sum
- Missing term

---

## 3️⃣ Harmonic Progression (HP)

### Definition
A sequence is in HP if the **reciprocals of its terms form an AP**.

Example:
```
1, 1/2, 1/3, 1/4
```

Reciprocals:
```
1, 2, 3, 4  → AP
```

---

### Key Rule (MOST IMPORTANT)
> HP has **no direct formula**.  
> Always convert HP → AP using reciprocals.

---

### Relation Between AP, GP & HP (Very Important)

For three positive numbers \(a, b, c\):

If they are in AP, GP, or HP:
$$
b^2 = ac
$$

This relation is heavily tested.

---

### Common HP Models in Exams
- Find middle term
- Find missing term
- Identify HP
- Relation-based MCQs

---

## 4️⃣ Means (Very Important)

### Arithmetic Mean (AM)
$$
AM = \frac{a + b}{2}
$$

---

### Geometric Mean (GM)
$$
GM = \sqrt{ab}
$$

---

### Harmonic Mean (HM)
$$
HM = \frac{2ab}{a + b}
$$

---

### Important Inequality
$$
AM \ge GM \ge HM
$$

Equality holds only when \(a = b\).

---

## 5️⃣ Insertion of Means

### Insert one AM between \(a\) and \(b\)
$$
\frac{a + b}{2}
$$

### Insert one GM between \(a\) and \(b\)
$$
\sqrt{ab}
$$

### Insert one HM between \(a\) and \(b\)
$$
\frac{2ab}{a + b}
$$

---

## 6️⃣ Common Exam Traps

- Confusing difference (AP) with ratio (GP)
- Using AP sum formula for GP
- Forgetting reciprocal step in HP
- Applying infinite GP when \(|r| \ge 1\)

---

## 7️⃣ Standard Question Models (What Exams Repeat)

1. Identify AP / GP / HP
2. Find nth term
3. Find sum of n terms
4. Insert AM / GM / HM
5. Use relation \(b^2 = ac\)
6. Infinite GP
7. Missing term problems

---

## Final Takeaways

- AP → difference
- GP → ratio
- HP → reciprocal AP
- Memorize formulas, apply logically
- Pattern recognition ≤ 5 seconds

> **Coverage:** ~90% of Algebra (AP–GP–HP) questions  
> **Status:** COMPLETE
