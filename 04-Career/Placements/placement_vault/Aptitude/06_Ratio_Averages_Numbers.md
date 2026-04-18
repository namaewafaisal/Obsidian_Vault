---
title: Ratio, Averages & Number Systems — Complete Study Note
tags: [aptitude, ratio, averages, numbers, placement]
priority: High
status: final
---

# ⚖️ Ratio, Averages & Number Systems

---

# PART 1: RATIO & PROPORTION

## 🔴 FORMULAS

### Basic Ratio Division
If total = T, ratio a:b:c
$$\text{Each share} = \frac{\text{their part}}{a+b+c} \times T$$

### Combined Ratio (A:B and B:C → A:B:C)
Make B common using LCM:
- A:B = 2:3, B:C = 4:5
- LCM of B values (3,4) = 12
- A:B = 8:12, B:C = 12:15 → A:B:C = **8:12:15**

### Proportion: a:b = c:d → ad = bc (cross multiply)

### Alligation (Mixtures)
$$\text{Ratio} = \frac{d - \text{mean}}{\text{mean} - c}$$
where c and d are the two values and mean is the target.

---

## 🟠 SOLVED EXAMPLES — RATIO

### Example 1 — Division in ratio
> ₹6000 divided among A:B:C = 1/2 : 1/3 : 1/4

Convert to same denominator: LCM = 12
Ratio = 6:4:3 (multiply by 12)
Total parts = 13
A = 6/13 × 6000 = ₹2769, B = 4/13 × 6000 = **₹1846**, C = 3/13 × 6000 = ₹1385

---

### Example 2 — Combined ratio
> A:B = 3:5, B:C = 2:3. Find A:B:C.

LCM(5,2) = 10. A:B = 6:10, B:C = 10:15. A:B:C = **6:10:15**

---

### Example 3 — Alligation
> Milk at ₹20/litre mixed with water (₹0). Result at ₹12/litre. Ratio milk:water?

Ratio = (12−0):(20−12) = 12:8 = **3:2**

---

## 🟡 PRACTICE — RATIO

**P1.** Salaries of A and B are 3:5. A gets ₹3600. B gets?
**P2.** A:B = 4:7 and B:C = 5:9. Find A:C.
**P3.** A mixture has milk and water in 4:1. 10 litres of water added, ratio becomes 2:1. Original mixture?
**P4.** ₹5200 split in ratio 3:4:6. Largest share?

---

# PART 2: AVERAGES & MIXTURES

## 🔴 FORMULAS

### Basic Average
$$\text{Average} = \frac{\text{Sum}}{\text{Count}}$$

### Replacement in Group
$$\text{New person's value} = \text{Old person's value} \pm (n \times \text{change in average})$$
(+ if average increases, − if decreases)

### Weighted Average
$$\text{Avg} = \frac{n_1 x_1 + n_2 x_2}{n_1 + n_2}$$

### Average of first n natural numbers = (n+1)/2
### Average of first n even numbers = n+1
### Average of first n odd numbers = n

---

## 🟠 SOLVED EXAMPLES — AVERAGES

### Example 1 — Replacement
> Average of 5 numbers = 20. One number removed, average becomes 18. Removed number?

Sum of 5 = 100, sum of 4 = 72. Removed = **28**

### Example 2 — New person joins
> Average age of 30 students = 14. Teacher joins, average becomes 15. Teacher's age?

New person's age = Old person's age + (n × change)
= 14 + (31 × 1) = **45** (wait: n = 30, teacher is 31st person)
= Old average + n×change = 14 + 30×1 = **44**

### Example 3 — Wrong value correction
> Average of 10 numbers = 40. One number read as 30 instead of 50. Correct average?

Sum was 400. Error = 50−30 = 20 short. Correct sum = 420. Correct avg = 420/10 = **42**

---

## 🟡 PRACTICE — AVERAGES

**P1.** Average of 8 numbers is 25. If one number is excluded, average becomes 23. Excluded number?
**P2.** Average marks of 40 students = 65. Top 10 scorers average = 90. Bottom 30 average?
**P3.** Average of 5 consecutive odd numbers = 31. Largest number?
**P4.** A class has 25 boys (avg 52kg) and 15 girls (avg 45kg). Class average weight?

---

# PART 3: NUMBER SYSTEMS

## 🔴 DIVISIBILITY RULES

| Divisor | Rule |
|---------|------|
| 2 | Last digit even |
| 3 | Sum of digits divisible by 3 |
| 4 | Last 2 digits divisible by 4 |
| 5 | Last digit 0 or 5 |
| 6 | Divisible by both 2 and 3 |
| 7 | Complex — skip in exams |
| 8 | Last 3 digits divisible by 8 |
| 9 | Sum of digits divisible by 9 |
| 11 | (Sum of odd-place digits) − (Sum of even-place digits) = 0 or multiple of 11 |

---

## 🔴 LCM & HCF

$$\text{LCM} \times \text{HCF} = \text{Product of two numbers}$$

HCF divides LCM always.

**HCF of fractions** = HCF of numerators / LCM of denominators
**LCM of fractions** = LCM of numerators / HCF of denominators

---

## 🔴 UNIT DIGIT & CYCLICITY

| Base digit | Cycle | Period |
|------------|-------|--------|
| 0, 1, 5, 6 | Fixed | 1 |
| 4, 9 | 4,6 / 9,1 | 2 |
| 2 | 2,4,8,6 | 4 |
| 3 | 3,9,7,1 | 4 |
| 7 | 7,9,3,1 | 4 |
| 8 | 8,4,2,6 | 4 |

**Method:** Find power mod 4 → look up cycle

---

## 🔴 TRAILING ZEROS (Factorials)

Count pairs of (2×5) in n!
$$\text{Zeros in n!} = \left\lfloor \frac{n}{5} \right\rfloor + \left\lfloor \frac{n}{25} \right\rfloor + \left\lfloor \frac{n}{125} \right\rfloor + \ldots$$

50! → 50/5 + 50/25 = 10 + 2 = **12 zeros**

---

## 🔴 REMAINDER TRICKS

### Same remainder from multiple divisors:
Number = LCM(divisors) × k + remainder

### Constant difference (gap method):
If (divisor − remainder) is same for all → Number = LCM − that constant

---

## 🟠 SOLVED EXAMPLES — NUMBERS

### Example 1 — Unit digit
> Unit digit of 7^45?

7 cycle: 7,9,3,1 (period 4). 45 mod 4 = 1 → unit digit = **7**

### Example 2 — Trailing zeros
> Zeros at end of 100!?

100/5 = 20, 100/25 = 4, 100/125 = 0. Total = **24**

### Example 3 — HCF/LCM
> LCM of 36 and 48 = 144. HCF?

HCF = (36 × 48) / 144 = 1728/144 = **12**

### Example 4 — Remainder
> Number when divided by 6 leaves remainder 3. Remainder when square of number divided by 6?

Number = 6k+3. Square = 36k²+36k+9 = 6(6k²+6k+1) + 3. Remainder = **3**

---

## 🟡 PRACTICE — NUMBER SYSTEMS

**P1.** Unit digit of 3^100?
**P2.** How many zeros in 75!?
**P3.** A number when divided by 5 gives remainder 3, by 7 gives remainder 3. Smallest such number?
**P4.** HCF of 84 and 108?
**P5.** LCM of 1/3, 2/9, 5/6?

---

## ❌ COMMON MISTAKES

- Using wrong base for ratio division
- Adding speeds/averages directly instead of using weighted average
- Unit digit: computing power mod 4 wrong (remainder 0 means 4th in cycle, not 0th)
- Trailing zeros: forgetting powers of 25, 125 etc also contribute
