# 🎛️ Equations: Placement Speedrun Notes

## 📌 Quick Links
- **Module:** Quantitative Aptitude
- **Subtopics:** [[#1. Linear Systems Stability]], [[#2. Quadratic Roots Shortcuts]], [[#3. High-Speed Word Problems]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #equations

---

## 1. Linear Systems Stability Matrix

When evaluating two linear lines ($a_1x + b_1y = c_1$ and $a_2x + b_2y = c_2$), check the structural coefficient ratios immediately instead of calculating values:

| Ratio Condition | Geometric Trajectory | Number of Solutions |
| :--- | :--- | :--- |
| $\frac{a_1}{a_2} \neq \frac{b_1}{b_2}$ | Intersecting Lines | **Unique Solution** |
| $\frac{a_1}{a_2} = \frac{b_1}{b_2} = \frac{c_1}{c_2}$ | Coincident / Overlapping | **Infinite Solutions** |
| $\frac{a_1}{a_2} = \frac{b_1}{b_2} \neq \frac{c_1}{c_2}$ | Parallel Tracking Lines | **No Solution** |

---

## 2. Quadratic Roots Shortcuts
For any equation matching $ax^2 + bx + c = 0$ with roots $\alpha$ and $\beta$:
- **Sum Engine:** $\alpha + \beta = -\frac{b}{a}$
- **Product Engine:** $\alpha \cdot \beta = \frac{c}{a}$

### ⚡ Absolute Shortcut Identitites
- **Reciprocal Roots Condition:** If $\alpha = \frac{1}{\beta}$, then $\frac{c}{a} = 1 \implies \mathbf{a = c}$.
- **Equal Magnitude, Opposite Signs:** If $\alpha = -\beta$, then $-\frac{b}{a} = 0 \implies \mathbf{b = 0}$.

---

## 3. High-Speed Word Problems

### 🔹 Two-Digit Digit Reversal Identity
- Sum of a number and its reverse $= 11(x + y) \implies \text{Sum of digits} = \frac{\text{Total Sum}}{11}$
- Difference of a number and its reverse $= 9(x - y) \implies \text{Difference of digits} = \frac{\text{Total Difference}}{9}$