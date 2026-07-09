# 📊 Averages: Comprehensive Placement Guide

## 📌 Quick Links
- **Module:** Quantitative Aptitude
- **Subtopics:** [[#1. Structural Math Laws]], [[#2. Dynamic Influx Engine]], [[#3. Replacement Deviations]], [[#4. Arithmetic Progression Equidistance]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #averages

---

## 1. Structural Math Laws

### 🔹 The Core Balance
$$\text{Average } (A) = \frac{\text{Sum of all elements } (S)}{\text{Total Count } (N)} \iff \mathbf{S = A \times N}$$

### 🔹 Weighted Averages
When compounding independent groups with distinct sizes, do not average the averages directly. Use:
$$A_w = \frac{n_1A_1 + n_2A_2 + \dots + n_kA_k}{n_1 + n_2 + \dots + n_k}$$

---

## 2. Dynamic Influx Engine (New Member Joins)

When a new element enters a bounded system, it must support its own weight *plus* provide the excess change across all slots to shift the baseline average.

### 🧮 Deviation Formula
$$\text{New Value} = \text{Old Average} + \Big( \text{New Total Count} \times \Delta_{\text{Average}} \Big)$$
*(Note: Use $+\Delta$ if the average rose; use $-\Delta$ if the average fell).*

### 🧩 Case Walkthrough
*The average weight of 24 students is 35 kg. Adding a teacher raises the average by 0.4 kg. Find the teacher's weight.*
- **Calculation:** $\text{Teacher} = 35 + (25 \times 0.4) = 35 + 10 = \mathbf{45 \text{ kg}}$.

---

## 3. Replacement Deviations (Count Stays Constant)

When a person leaves a group and is replaced by another, the total system count ($N$) remains unchanged. The change in the average is driven entirely by the difference between the two values.

### 🧮 Identity
$$\text{New Value(s)} = \text{Sum of Departed Value(s)} + \Big( \text{Total Group Count} \times \Delta_{\text{Average}} \Big)$$

### 🧩 Case Walkthrough
*Average age of 8 men increases by 2 years when two men aged 21 and 23 are replaced by two new entries. Find the average of the new entries.*
1. $\text{Sum of New Entries} = (21 + 23) + (8 \times 2) = 44 + 16 = 60 \text{ years}$.
2. $\text{Average of New Entries} = \frac{60}{2} = \mathbf{30 \text{ years}}$.

---

## 4. Arithmetic Progression Equidistance

For any dataset that follows a constant, symmetric difference interval (e.g., consecutive integers, evens, odds, multiples):

$$\text{Average} = \frac{\text{First Term} + \text{Last Term}}{2} = \text{Exact Geometric Middle Term}$$

### 🧩 Case Walkthrough
*The average of 7 consecutive numbers is 20. Find the largest.*
- **Layout:** Since $N=7$, 20 sits directly in the 4th slot.
- $\text{Positions: } [\_,\, \_,\, \_,\, \mathbf{20},\, \_,\, \_,\, \_]$
- Counting forward by 1s across 3 steps yields: $20 + 3 = \mathbf{23}$.