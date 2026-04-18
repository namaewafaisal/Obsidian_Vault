---
title: Simple & Compound Interest — Complete Study Note
tags: [aptitude, si, ci, placement]
priority: Must
status: final
---

# 💰 Simple & Compound Interest

---

## 🔴 SIMPLE INTEREST (SI)

$$SI = \frac{P \times R \times T}{100}$$

$$A = P + SI$$

Reverse formulas:
$$P = \frac{SI \times 100}{R \times T}, \quad R = \frac{SI \times 100}{P \times T}, \quad T = \frac{SI \times 100}{P \times R}$$

**Key pattern:**
- Money doubles in SI: SI = P → T = 100/R
- Money triples: SI = 2P → T = 200/R

---

## 🔴 COMPOUND INTEREST (CI)

$$A = P \times \left(1 + \frac{R}{100}\right)^T$$

$$CI = A - P$$

---

## 🔴 COMPOUNDING PERIODS

| Period | Formula |
|--------|---------|
| Annual | $P(1 + R/100)^T$ |
| Half-yearly | $P(1 + R/200)^{2T}$ |
| Quarterly | $P(1 + R/400)^{4T}$ |

---

## 🔴 CI − SI DIFFERENCE (HIGH FREQUENCY)

### For 2 years:
$$CI - SI = P \times \left(\frac{R}{100}\right)^2$$

### For 3 years:
$$CI - SI = P \times \left(\frac{R}{100}\right)^2 \times \left(3 + \frac{R}{100}\right)$$

> Memorize the 2-year formula — it comes in almost every test.

---

## 🟠 POPULATION / DEPRECIATION

### Growth:
$$A = P \times \left(1 + \frac{R}{100}\right)^T$$

### Depreciation:
$$A = P \times \left(1 - \frac{R}{100}\right)^T$$

### Population n years ago (given current):
$$\text{Past} = \frac{P}{\left(1 + \frac{R}{100}\right)^n}$$

---

## 🟠 SUCCESSIVE RATE CHANGE

If rate changes year to year:
$$A = P \times \left(1 + \frac{R_1}{100}\right) \times \left(1 + \frac{R_2}{100}\right) \times \ldots$$

---

## 🟠 SOLVED EXAMPLES

### Example 1 — SI: money doubles
> At SI, a sum doubles in 8 years. Rate?

SI = P → P = P×R×8/100 → R = 100/8 = **12.5%**

---

### Example 2 — CI vs SI difference
> ₹10000 at 10% for 2 years. CI − SI?

CI − SI = 10000 × (10/100)² = 10000 × 0.01 = **₹100**

---

### Example 3 — Half-yearly CI
> ₹8000 at 10% p.a. compounded half-yearly for 1 year.

A = 8000 × (1 + 5/100)² = 8000 × 1.05² = 8000 × 1.1025 = **₹8820**
CI = 8820 − 8000 = **₹820**

---

### Example 4 — Depreciation
> Machine worth ₹1,00,000. Depreciates 10% per year. Value after 3 years?

A = 1,00,000 × (0.9)³ = 1,00,000 × 0.729 = **₹72,900**

---

### Example 5 — Population problem
> Town population 50,000 grows at 4% p.a. After 2 years?

A = 50000 × (1.04)² = 50000 × 1.0816 = **54,080**

---

### Example 6 — Find principal from CI
> CI on a sum for 2 years at 10% = ₹2100. Find principal.

A = P(1.1)² = 1.21P
CI = 0.21P = 2100 → P = **₹10,000**

---

## 🟠 SOLVING APPROACH

1. Check if question says SI or CI — don't mix up
2. Check compounding period — adjust rate and time accordingly
3. For difference questions — directly use CI−SI formula (faster)
4. Population/machine value → always CI pattern
5. If "doubles/triples" → set SI = P or 2P and solve

---

## 🟡 PRACTICE PROBLEMS

**P1.** SI on ₹4000 at 8% for 3 years?

**P2.** A sum at SI becomes ₹6000 in 3 years and ₹7000 in 5 years. Principal and rate?

**P3.** CI on ₹5000 at 12% per annum for 2 years (annual compounding)?

**P4.** Difference between CI and SI on ₹8000 for 2 years at 5%?

**P5.** A car bought for ₹5,00,000 depreciates 20% per year. Value after 2 years?

**P6.** A sum triples in 20 years at SI. In how many years does it become 5 times?

**P7.** CI on a sum for 2 years at 10% = ₹420. What is the sum?

**P8.** Population of a city is 2,00,000. It decreases 5% each year due to migration. Population after 2 years?

---

## ❌ COMMON MISTAKES

- Confusing SI "doubles in T years" → rate = 100/T (not 200/T)
- Forgetting to adjust rate and time for half-yearly/quarterly compounding
- Using SI formula when question says "compound interest"
- CI−SI difference: only use the direct formula; don't compute full CI then subtract
