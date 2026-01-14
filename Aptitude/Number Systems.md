---
tags: [placement, quant, math]
topic: Number Systems
---
# 🔢 Number System — Aptitude Master Notes (Exam-Oriented)

> **Scope:** Placement aptitude (TCS, Accenture, Infosys, Wipro, Capgemini)  
> **Goal:** Pattern recognition + speed (not theory depth)

---

## 1. Classification of Numbers

### Types
- **Natural (N):** 1, 2, 3, …
- **Whole (W):** 0, 1, 2, 3, …
- **Integers (Z):** … −2, −1, 0, 1, 2 …
- **Even / Odd**
- **Prime:** Exactly two factors  
  - 2 is the only even prime  
  - 1 is neither prime nor composite
- **Composite:** More than two factors
- **Co-prime:** HCF = 1
- **Rational:** Can be written as \( \frac{p}{q} \)
- **Irrational:** Cannot be written as \( \frac{p}{q} \) (√2, π)

**Exam Traps**
- 1 is not prime
- 0 is neither prime nor composite
- Negative numbers are not prime/composite

---

## 2. Standard Formulas (Memory Core)

### Sums
- First \(n\) natural numbers  
  $$\frac{n(n+1)}{2}$$
- First \(n\) odd numbers  
  $$n^2$$
- First \(n\) even numbers  
  $$n(n+1)$$
- Sum of squares  
  $$\frac{n(n+1)(2n+1)}{6}$$
- Sum of cubes  
  $$\left(\frac{n(n+1)}{2}\right)^2$$

---

## 3. Divisibility Rules

| Number | Rule |
|------|------|
| 2 | Last digit even |
| 3 | Sum of digits divisible by 3 |
| 4 | Last 2 digits divisible by 4 |
| 5 | Last digit 0 or 5 |
| 6 | Divisible by 2 and 3 |
| 8 | Last 3 digits divisible by 8 |
| 9 | Sum of digits divisible by 9 |
| 11 | Difference of odd & even place sums is 0 or multiple of 11 |
| 12 | Divisible by 3 and 4 |

---

## 4. Remainders (High Frequency)

### Core Rules
$$
n \equiv r \pmod m \Rightarrow n^k \equiv r^k \pmod m
$$

- Reduce **before** power
- Work fully in modulo

### Standard Properties
- \( (a+b) \bmod n = (a\bmod n + b\bmod n)\bmod n \)
- \( (a\times b) \bmod n = (a\bmod n \times b\bmod n)\bmod n \)

### Fermat’s Little Theorem
If \(p\) is prime and \(\gcd(a,p)=1\):
$$
a^{p-1} \equiv 1 \pmod p
$$

### Negative Remainder Trick
If remainder is \(n-1\), treat as **−1**  
Example:
$$
17^{200} \bmod 18 = (-1)^{200} = 1
$$

---

## 5. Unit Digit / Cyclicity

### Cycles

| Base | Cycle |
|----|----|
| 0 | 0 |
| 1 | 1 |
| 2 | 2, 4, 8, 6 |
| 3 | 3, 9, 7, 1 |
| 4 | 4, 6 |
| 5 | 5 |
| 6 | 6 |
| 7 | 7, 9, 3, 1 |
| 8 | 8, 4, 2, 6 |
| 9 | 9, 1 |

### Method
1. Identify cycle
2. Power mod cycle length
3. Pick digit

### Special Factorial Facts
- Unit digit of \(n!\) for \(n \ge 5\) is **0**
- Unit digit of \(1! + 2! + 3! + \dots + n!\) (for \(n \ge 4\)) is **3**

---

## 6. HCF & LCM

### Definitions
- **HCF:** Greatest number dividing all exactly
- **LCM:** Least number divisible by all

### Core Formula
$$
\text{Product of two numbers} = \text{HCF} \times \text{LCM}
$$

### Fractions
- $$HCF = \frac{HCF(\text{numerators})}{LCM(\text{denominators})}$$
- $$LCM = \frac{LCM(\text{numerators})}{HCF(\text{denominators})}$$

### Word Problem Logic
- “Smallest number / together” → **LCM**
- “Largest size / maximum” → **HCF**
- Same remainder → **LCM ± remainder**

---

## 7. Factorisation (Exam Method)

If:
$$
N = p^a \times q^b \times r^c
$$

- **Total factors:**  
  $$(a+1)(b+1)(c+1)$$
- **Odd factors:** Ignore power of 2
- **Even factors:** Total − odd
- **Perfect square:** Odd number of factors

### Example
$$
360 = 2^3 \times 3^2 \times 5
$$

---

## 8. Trailing Zeros (Factorial)

$$
\text{Trailing zeros in } n! =
\left\lfloor\frac{n}{5}\right\rfloor +
\left\lfloor\frac{n}{25}\right\rfloor +
\left\lfloor\frac{n}{125}\right\rfloor + \dots
$$

---

## 9. Simplification & Indices

### Laws
- $$\sqrt{a}\times\sqrt{b}=\sqrt{ab}$$
- $$a^{m/n} = (\sqrt[n]{a})^m$$
- Convert everything to the **same base**

---

## 10. Standard Question Models (What Exams Repeat)

1. Remainder of powers
2. Unit digit of large expressions
3. Smallest number satisfying conditions
4. Factor counting
5. Trailing zeros
6. HCF–LCM word problems
7. Consecutive numbers
8. Perfect square / cube checks
9. Divisibility by digit replacement

---

## Exam Takeaways

- Reduce **before** power
- Use modulo aggressively
- Never brute-force
- Pattern recognition ≤ **5 seconds**

> **Coverage:** ~80–90% of placement Number System questions  
> **Status:** COMPLETE for this test


## 11. Advanced Gap Cases (The Last 10%)

### A. Successive Division
- **Scenario:** Divisors $d_1, d_2$; Remainders $r_1, r_2$.
- **Constant Difference Case:** If $(d_1 - r_1) = (d_2 - r_2) = K$.
  - **Formula:** $LCM(d_1, d_2, \dots) - K$
- **Successive Logic:** If $N$ is divided by $x$ then the *quotient* is divided by $y$.
  - **Formula:** $N = d_1(d_2q + r_2) + r_1$

### B. Factor Sum & Product
- **Sum of Factors:** $\prod \frac{p^{a+1}-1}{p-1}$
- **Product of Factors:** $N^{\frac{\text{Total Factors}}{2}}$
- **Perfect Square Property:** Only perfect squares have an **odd** number of factors.

### C. Digital Sum
- The digital sum of a perfect square can only be **1, 4, 7, or 9**.
- If a number ends in 2, 3, 7, or 8, it can **never** be a perfect square.

### D. Euler's Totient ($\phi$)
- For a prime $p$, $\phi(p) = p-1$.
- For $N = p^a \cdot q^b$, $\phi(N) = N(1 - \frac{1}{p})(1 - \frac{1}{q})$.
- **Remainder Rule:** $a^{\phi(n)} \equiv 1 \pmod n$.