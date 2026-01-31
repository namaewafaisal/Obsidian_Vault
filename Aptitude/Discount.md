---
tags:
  - aptitude
  - placement
  - Math
topic: Discount
date-time: 2026-01-20T20:56:00
---
# 💸 Discount — Aptitude Master Notes (Exam-Oriented)

> **Scope:** Placement aptitude (TCS, Accenture, Infosys, Wipro, Capgemini)
> **Goal:** Convert MP–SP–CP relations instantly, avoid percentage traps

---

## 1. Core Definitions

* **Marked Price (MP):** Price marked by the seller
* **Selling Price (SP):** Actual price paid by buyer
* **Discount:** Reduction on MP

### Basic Relations

$$
\text{Discount} = MP - SP
$$

$$
\text{Discount %} = \frac{MP - SP}{MP} \times 100
$$

---

## 2. Selling Price & Marked Price Formulas

### Selling Price after Discount

$$
SP = MP\left(1 - \frac{d}{100}\right)
$$

### Marked Price from Selling Price

$$
MP = \frac{SP}{1 - \frac{d}{100}}
$$

or equivalently:

$$
MP = SP \times \frac{100}{100 - d}
$$

📌 **Used in:** reverse discount, missing MP questions

---

## 3. Successive Discount (Very High Frequency)

### Two Successive Discounts

If discounts are (a%) and (b%):

$$
\text{Net Discount %} = a + b - \frac{ab}{100}
$$

### Three Successive Discounts (Rare but Possible)

$$
\text{Net Discount %}
= a + b + c

- \frac{ab + bc + ca}{100}

- \frac{abc}{10000}
  $$

📌 **Used in:** “shopkeeper gives successive discounts” questions

---

## 4. Discount ↔ Profit / Loss Link (MOST IMPORTANT MODEL)

### Core Relation

$$
MP\left(1 - \frac{d}{100}\right)
=
CP\left(1 + \frac{p}{100}\right)
$$

This single equation connects:

* Marked Price
* Discount
* Cost Price
* Profit / Loss

---

### Derived Forms

**Cost Price**

$$
CP =
\frac{MP\left(1 - \frac{d}{100}\right)}
{1 + \frac{p}{100}}
$$

**Profit %**

$$
\text{Profit %}
=

\frac{SP - CP}{CP} \times 100
$$

📌 **Used in:** “sold at x% discount and y% profit” questions

---

## 5. Markup–Discount Net Effect (Trap Area)

If:

* Markup = (m%)
* Discount = (d%)

Then:

$$
\text{Net % Effect}
=
m - d - \frac{md}{100}
$$

* Positive → **Profit**
* Negative → **Loss**

📌 **Used in:** “marked above cost price and discount given” questions

---

## 6. Same Selling Price – Different Discounts (Classic Trap)

### Scenario

* Same **Marked Price**
* Two different discounts
* Difference in Selling Prices is given

### Formula

If discount difference = (x%) and SP difference = (\Delta):

$$
MP = \frac{\Delta}{x/100}
$$

📌 **Used in:** fast elimination questions

---

## 7. Equivalent Discount Concept

Two successive discounts (a%) and (b%) are equivalent to:

$$
D = a + b - \frac{ab}{100}
$$

📌 **Used in:** converting multiple discounts into one

---

## 8. Mental Percentage Anchors (Speed Boost)

| Discount | Multiply SP by |
| -------- | -------------- |
| 10%      | 0.9            |
| 20%      | 0.8            |
| 25%      | 0.75           |
| 33⅓%     | 2/3            |
| 40%      | 0.6            |
| 50%      | 0.5            |

📌 **Used in:** mental math, no-calculator rounds

---

## 9. Standard Question Models (What Exams Repeat)

1. Find SP after given discount
2. Find MP when SP and discount % are given
3. Successive discount → net discount
4. Discount + profit combined questions
5. Same MP, different discounts
6. Markup–discount net gain/loss
7. Reverse discount (SP given)

---

## 10. Common Traps & Exam Notes

* Discount is **always on MP**, not CP
* Profit / loss is **always on CP**
* Never mix bases (MP vs CP)
* Successive discount ≠ simple addition
* Use fraction equivalents whenever possible

---

## Exam Takeaways

* Convert % to multipliers early
* Use linkage formula instead of steps
* Skip algebra if mental shortcut exists
* Pattern recognition ≤ **5 seconds**

> **Coverage:** ~95% of Discount questions
> **Status:** COMPLETE

