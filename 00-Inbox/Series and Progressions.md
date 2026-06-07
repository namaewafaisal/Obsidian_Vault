# 📈 Series and Progressions: Ultimate Aptitude Guide

## 📌 Concept Maps & Quick Links
- **Module:** Quantitative Aptitude
- **Subtopics:** [[#1. Arithmetic Progression (AP)]], [[#2. Geometric Progression (GP)]], [[#3. Harmonic Progression (HP)]], [[#4. Special Power Series]], [[#5. Pattern Recognition Frameworks]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #sequences

---

## 1. Arithmetic Progression (AP)

An Arithmetic Progression is a sequence of numbers in which the difference between any two consecutive terms remains a constant value throughout the sequence. This constant is called the **common difference ($d$)**.

$$\text{Common Difference } (d) = T_n - T_{n-1}$$

### 🧮 Core AP Formulas
- **General Form:** $a, a+d, a+2d, a+3d, \dots$ (where $a$ is the first term)
- **$n^{\text{th}}$ Term ($T_n$):**
  $$T_n = a + (n - 1)d$$
- **Sum of First $n$ Terms ($S_n$):**
  $$S_n = \frac{n}{2} [2a + (n - 1)d] \quad \text{or} \quad S_n = \frac{n}{2} [a + L]$$
  *(where $L$ is the last term of the sequence, $T_n$)*
- **Arithmetic Mean (AM):** If $a, b, c$ are in AP, then the middle term is the average of its neighbors:
  $$b = \frac{a + c}{2}$$

> [!TIP] Symmetric Term Selection Shortcut
> If a problem requires you to assume an odd number of terms in an AP whose sum is known, pick them symmetrically around $a$ to cancel out $d$:
> - **3 terms:** $(a - d), a, (a + d)$
> - **5 terms:** $(a - 2d), (a - d), a, (a + d), (a + 2d)$

---

## 2. Geometric Progression (GP)

A Geometric Progression is a sequence where each term after the first is found by multiplying the preceding term by a fixed, non-zero number called the **common ratio ($r$)**.

$$\text{Common Ratio } (r) = \frac{T_n}{T_{n-1}}$$

### 🧮 Core GP Formulas
- **General Form:** $a, ar, ar^2, ar^3, \dots$
- **$n^{\text{th}}$ Term ($T_n$):**
  $$T_n = a \cdot r^{(n-1)}$$
- **Sum of First $n$ Terms ($S_n$):**
  $$S_n = \frac{a(r^n - 1)}{r - 1} \quad \text{(when } r > 1\text{)}$$
  $$S_n = \frac{a(1 - r^n)}{1 - r} \quad \text{(when } r < 1\text{)}$$
- **Sum of an Infinite GP ($S_\infty$):** This convergent sum behaves predictably *only* when the ratio falls strictly between $-1$ and $1$ (i.e., $|r| < 1$):
  $$S_\infty = \frac{a}{1 - r}$$
- **Geometric Mean (GM):** If $a, b, c$ are in GP, then:
  $$b = \sqrt{a \cdot c}$$

---

## 3. Harmonic Progression (HP)

A sequence of numbers forms a Harmonic Progression if the **reciprocals** of all its elements form a valid Arithmetic Progression.

- **General Form:** $\frac{1}{a}, \frac{1}{a+d}, \frac{1}{a+2d}, \frac{1}{a+3d}, \dots$
- **$n^{\text{th}}$ Term ($T_n$):** Calculate the $n^{\text{th}}$ term of the baseline AP, then take its inverse:
  $$T_n = \frac{1}{a + (n-1)d}$$
- **Harmonic Mean (HM):** If $a, b, c$ are in HP, the central anchor value is:
  $$b = \frac{2ac}{a + c}$$

> [!NOTE] The Fundamental Inequality
> For any positive, distinct real numbers, their mathematical means always maintain this strict order:
> $$\text{Arithmetic Mean (AM)} > \text{Geometric Mean (GM)} > \text{Harmonic Mean (HM)}$$
> $$\text{Relationship Balance: } GM^2 = AM \times HM$$

---

## 4. Special Power Series

These classic operational summations appear constantly as core components inside complex aptitude problem tracking:

### 🔢 Sum of First $n$ Natural Numbers
$$\sum_{i=1}^{n} i = 1 + 2 + 3 + \dots + n = \frac{n(n + 1)}{2}$$

### 🟦 Sum of Squares of First $n$ Natural Numbers
$$\sum_{i=1}^{n} i^2 = 1^2 + 2^2 + 3^2 + \dots + n^2 = \frac{n(n + 1)(2n + 1)}{6}$$

### 🟪 Sum of Cubes of First $n$ Natural Numbers
$$\sum_{i=1}^{n} i^3 = 1^3 + 2^3 + 3^3 + \dots + n^3 = \left[ \frac{n(n + 1)}{2} \right]^2 = \left( \sum_{i=1}^{n} i \right)^2$$

---

## 5. Pattern Recognition Frameworks

When a question asks you to find the missing or wrong number in a sequence, run through this logical checklist down the structural tiers:

```
[Input Number Series]
       │
       ├──► Tier 1: Check Arithmetic Spacing (Differences)
       │       └── Common differences, changing differences (+2, +4, +6), or prime offsets
       │
       ├──► Tier 2: Check Power Layers (Perfect Squares & Cubes)
       │       └── Look for matches close to squares or cubes: (n² ± k) or (n³ ± n)
       │
       ├──► Tier 3: Check Geometric Scaling (Multiplication/Division)
       │       └── Rapid exponential scaling factor growth: (×1, ×2, ×3) or (×0.5, ×1, ×1.5)
       │
       └──► Tier 4: Check Interleaved Indexes (Twin / Alternate Series)
               └── Even positions form one distinct sequence, odd positions form another
```

### ⚡ Canonical Placement Examples

#### 🧩 Example 1: Multi-Step AP Setup
**Question:** Find the total sum of all two-digit integers that yield a remainder of 2 when divided by 4.
**Solution:**
1. Identify the boundaries: Lowest 2-digit value matching is $14$ ($4 \times 3 + 2$). The highest matching value is $98$ ($4 \times 24 + 2$).
2. This creates an AP: $14, 18, 22, \dots, 98$, where $a = 14$, $d = 4$, and $L = 98$.
3. Compute the number of terms ($n$):
   $$98 = 14 + (n - 1)4 \implies 84 = (n - 1)4 \implies 21 = n - 1 \implies n = 22$$
4. Compute total sum via shorthand equation:
   $$S_{22} = \frac{22}{2} [14 + 98] = 11 \times 112 = \mathbf{1232}$$

#### 🧩 Example 2: The Multiplicative Square Hybrid
**Question:** Decode the hidden logic to identify the missing value: **2, 3, 10, 39, ?, 885**
**Solution:**
1. Observe the highly aggressive scaling rate across values, ruling out standard linear steps.
2. Check for an alternating functional scaling model matching index changes:
   - $2 \times 1 + 1^2 = 3$
   - $3 \times 2 + 2^2 = 10$
   - $10 \times 3 + 3^2 = 39$
3. Apply the verified logic pattern to find the missing index step:
   $$\text{Target Value} = 39 \times 4 + 4^2 = 156 + 16 = \mathbf{172}$$
4. Crosscheck against the final bounds element to confirm consistency:
   $$172 \times 5 + 5^2 = 860 + 25 = 885 \quad \text{(Matches Perfectly)}$$

