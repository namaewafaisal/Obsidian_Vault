---
title: Time & Work + Pipes & Cisterns — Complete Study Note
tags: [aptitude, time-work, pipes, placement]
priority: Must
status: final
---

# ⏱️ Time & Work + Pipes & Cisterns

---

## 🔴 CORE CONCEPT

Work = Rate × Time

If someone finishes in N days → their rate = 1/N per day.
**Never add days. Always add rates.**

---

## 🔴 FORMULAS (IMPORTANCE ORDER)

### 1. Two people together
$$\text{Together} = \frac{xy}{x+y} \text{ days}$$
where x and y are days taken individually.

### 2. LCM Method (preferred for 3+ people)
- Total work = LCM of all given days
- Each person's per-day work = LCM ÷ their days
- Add rates → time = Total work ÷ combined rate

### 3. Three pairs given (A+B, B+C, A+C) → find A+B+C
$$\text{A+B+C rate} = \frac{(A+B) + (B+C) + (A+C)}{2}$$
Time = 1 ÷ (A+B+C rate)

### 4. One person leaves midway
- Work done before leaving = rate × days worked
- Remaining work = Total − done
- Time for remaining = Remaining ÷ remaining rate

### 5. Efficiency Ratio
If A is n times efficient than B:
- A takes 1/n the time of B
- Ratio of times = inverse of ratio of efficiencies

### 6. Men × Days = Constant work
$$M_1 \times D_1 = M_2 \times D_2$$
(Same work, different men/days)

With hours: $M_1 \times D_1 \times H_1 = M_2 \times D_2 \times H_2$

---

## 🔴 PIPES & CISTERNS (same logic, just signs)

- Inlet pipe (fills) → **positive rate**
- Outlet pipe (empties) → **negative rate**

$$\text{Net rate} = \frac{1}{x} - \frac{1}{y}$$

Time to fill = 1 ÷ Net rate

> If net rate is negative → tank never fills (outlet faster than inlet)

---

## 🟠 SOLVED EXAMPLES

### Example 1 — Basic together
> A does work in 10 days, B in 15 days. Together?

Together = (10×15)/(10+15) = 150/25 = **6 days**

Or LCM method: LCM(10,15) = 30
A's rate = 3/day, B's rate = 2/day, Together = 5/day
Time = 30/5 = **6 days** ✓

---

### Example 2 — Three pairs
> A+B = 12 days, B+C = 15 days, A+C = 20 days. All three together?

Rates: 1/12 + 1/15 + 1/20
LCM = 60 → 5/60 + 4/60 + 3/60 = 12/60 = 1/5
2(A+B+C) = 1/5 → A+B+C = 1/10
Together = **10 days**

---

### Example 3 — Joining midway
> A can do in 20 days. After 5 days B joins. They finish in 10 more days. B alone?

Work done by A alone in 5 days = 5/20 = 1/4
Remaining = 3/4
A+B together for 10 days → rate = (3/4)/10 = 3/40
A's rate = 1/20 = 2/40
B's rate = 3/40 − 2/40 = 1/40
B alone = **40 days**

---

### Example 4 — Alternate working
> A and B work on alternate days. A starts. A does in 6 days, B in 8 days. Total?

2-day cycle: A does 1/6, B does 1/8. Together in 2 days = 1/6 + 1/8 = 7/24
In 6 days (3 cycles) = 7/24 × 3 = 21/24 = 7/8 done
Remaining = 1/8, it's A's turn: 1/8 ÷ 1/6 = 6/8 = 3/4 day
Total = 6 + 3/4 = **6.75 days**

---

### Example 5 — Pipes
> Pipe A fills in 12 hrs, Pipe B empties in 18 hrs. Both open. Time to fill?

Net rate = 1/12 − 1/18 = 3/36 − 2/36 = 1/36
Time = **36 hours**

---

### Example 6 — Pipe with leak
> A pipe fills in 6 hrs. Due to a leak, takes 8 hrs. Leak alone empties in?

Without leak rate = 1/6, With leak rate = 1/8
Leak rate = 1/6 − 1/8 = 4/24 − 3/24 = 1/24
Leak empties in **24 hours**

---

## 🟠 SOLVING APPROACH

1. Convert days → rates immediately
2. Use LCM method if 3+ people (avoids fractions)
3. For A+B, B+C, A+C type → sum all three rates, divide by 2
4. For joining/leaving → track work done in phases
5. For pipes → inlet positive, outlet negative, apply same rate logic

---

## 🟡 PRACTICE PROBLEMS

**P1.** A and B together do work in 8 days. A alone in 12 days. B alone in?

**P2.** 12 men do a work in 15 days. How many men needed to finish in 10 days?

**P3.** A is twice as efficient as B. Together they finish in 14 days. A alone?

**P4.** A+B = 10 days, B+C = 12 days, A+C = 15 days. Find A, B, C individually.

**P5.** A can do work in 18 days. He works for 6 days then B joins. They finish in 8 more days. B alone?

**P6.** 3 pipes fill a tank in 6, 8, 12 hours. All open together. Time to fill?

**P7.** Two pipes fill in 15 and 20 hrs. One outlet empties in 25 hrs. All open. Time to fill?

**P8.** A and B work on alternate days starting with A. A does in 10 days, B in 15 days. Done in?

---

## ❌ COMMON MISTAKES

- Adding days instead of rates
- Forgetting to convert "B is 3 times more efficient" → B's time = A's time ÷ 3
- In pipes, not putting minus for outlet
- In alternate working, not tracking which person's turn is last
