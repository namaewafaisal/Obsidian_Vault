---
title: Algebra (AP, GP, HP) — Aptitude Master Notes
tags: [aptitude, quantitative, algebra, ap, gp, hp]
status: active
exam: Placement Aptitude
priority: Medium
---

# 📐 Algebra — AP, GP & HP (Aptitude Master Notes)

> **Scope:** Placement aptitude (TCS, Accenture, Infosys, Wipro, Capgemini)  
> **Goal:** Quickly identify sequences and apply direct formulas (no derivations)

---

## 1. Arithmetic Progression (AP)

### Definition
A sequence where the **difference between consecutive terms is constant**.

Example:
2, 5, 8, 11, …

---

### Key Terms
- First term = \(a\)
- Common difference = \(d\)
- Number of terms = \(n\)

---

### nth Term of AP
$$
a_n = a + (n-1)d
$$

---

### Sum of n Terms of AP
$$
S_n = \frac{n}{2}[2a + (n-1)d]
$$

or
$$
S_n = \frac{n}{2}(a + l)
$$
(where \(l\) = last term)

---

### Common AP Models in Exams
- Find nth term
- Find number of terms
- Find missing terms
- AP formed by natural numbers, even numbers, odd numbers

---

### Special AP Averages
- Average of AP = middle term
- Average of first n natural numbers = \(\frac{n+1}{2}\)
- Average of first n even numbers = \(n+1\)
- Average of first n odd numbers = \(n\)

---

## 2. Geometric Progression (GP)

### Definition
A sequence where the **ratio between consecutive terms is constant**.

Example:
3, 6, 12, 24, …

---

### Key Terms
- First term = \(a\)
- Common ratio = \(r\)

---

### nth Term of GP
$$
a_n = ar^{n-1}
$$

---

### Sum of n Terms of GP
If \(r \neq 1\):
$$
S_n = \frac{a(r^n - 1)}{r - 1}
$$

---

### Infinite GP (IMPORTANT)
If \(|r| < 1\):
$$
S_\infty = \frac{a}{1 - r}
$$

---

### Common GP Exam Models
- Find common ratio
- Find missing term
- Identify GP pattern
- Infinite series questions

---

## 3. Harmonic Progression (HP)

### Definition
A sequence is in HP if the **reciprocals form an AP**.

Example:
1, 1/2, 1/3, 1/4, …

---

### Key Idea (MOST IMPORTANT)
If:
$$
\frac{1}{a}, \frac{1}{b}, \frac{1}{c}
$$
are in AP, then:
$$
a, b, c \text{ are in HP}
$$

---

### Relation Between AP, GP, HP
For three numbers \(a, b, c\):

If:
- \(a, b, c\) are in AP
- \(a, b, c\) are in GP
- \(a, b, c\) are in HP

Then:
$$
b^2 = ac
$$

(This relation is frequently tested)

---

## 4. Mean Relations (VERY IMPORTANT)

### Arithmetic Mean (AM)
$$
AM = \frac{a+b}{2}
$$

---

### Geometric Mean (GM)
$$
GM = \sqrt{ab}
$$

---

### Harmonic Mean (HM)
$$
HM = \frac{2ab}{a+b}
$$

---

### Important Identity
$$
AM \ge GM \ge HM
$$

Equality holds **only when \(a = b\)**.

---

## 5. Insertion of Means

### Insert one AM between a and b
$$
AM = \frac{a+b}{2}
$$

### Insert one GM between a and b
$$
GM = \sqrt{ab}
$$

### Insert one HM between a and b
$$
HM = \frac{2ab}{a+b}
$$

---

## 6. Common Exam Traps

- Confusing difference (AP) with ratio (GP)
- Using AP sum formula for GP
- Forgetting reciprocal logic in HP
- Missing infinite GP condition \(|r|<1\)

---

## 7. Standard Question Models

1. Identify AP / GP / HP
2. Find nth term
3. Find sum of n terms
4. Insert AM / GM / HM
5. Relation between AM, GM, HM
6. Infinite GP sum
7. Missing term problems

---

## Final Takeaways

- AP → difference
- GP → ratio
- HP → reciprocal AP
- Memorize formulas; do not derive
- AM ≥ GM ≥ HM is a favourite MCQ

> **Coverage:** ~90% of Algebra (AP, GP, HP) questions  
> **Status:** COMPLETE
