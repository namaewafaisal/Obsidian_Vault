# 🕰️ Clocks and Calendars: Ultimate Aptitude Guide

## 📌 Concept Maps & Quick Links
- **Module:** Quantitative / Analytical Aptitude
- **Subtopics:** [[#1. Clocks Theory & Mechanics]], [[#2. Calendars & Odd Days]], [[#3. Master Formulas]], [[#4. High-Yield Shortcuts]], [[#5. Solved Canonical Problems]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant

---

## 1. Clocks Theory & Mechanics

A standard clock face is a circle of $360^\circ$ divided into **12 major hours intervals** and **60 minor minute intervals**.



### 🏃‍♂️ Speed & Angular Velocity
- **Minute Hand:** - Traverses $360^\circ$ in 60 minutes.
  - $\text{Speed} = \frac{360^\circ}{60} = \mathbf{6^\circ / \text{min}}$
- **Hour Hand:** - Traverses $360^\circ$ in 12 hours ($720\text{ mins}$).
  - $\text{Speed} = \frac{360^\circ}{720} = \mathbf{0.5^\circ / \text{min}}$
- **Relative Speed:**
  - The minute hand gains ground on the hour hand at a constant rate.
  - $\text{Relative Speed} = 6^\circ - 0.5^\circ = \mathbf{5.5^\circ / \text{min} \left(\frac{11}{2}^\circ / \text{min}\right)}$

### 🧩 Spatial Positions Per Day
| Property / Alignment | Angle ($\theta$) | Frequency per 12 Hours | Frequency per 24 Hours (Day) | Crucial Exception Window |
| :--- | :---: | :---: | :---: | :--- |
| **Coincide / Overlap** | $0^\circ$ | 11 times | **22 times** | Overlaps only once between 11:00 and 1:00 (exactly at 12:00) |
| **Opposite / Straight Line** | $180^\circ$ | 11 times | **22 times** | Occurs only once between 5:00 and 7:00 (exactly at 6:00) |
| **Perpendicular / Right Angle**| $90^\circ$ | 22 times | **44 times** | Only 3 times instead of 4 between the 2:00-4:00 and 8:00-10:00 windows |

---

## 2. Calendars & Odd Days

The fundamental unit of tracking calendar mechanics is the **Odd Day**—the remainder left over when total elapsed days are evaluated modulo 7.

$$\text{Odd Days} = \text{Total Days} \pmod 7$$

### 📅 Year Classification
- **Ordinary Year (365 days):** - $365 \div 7 = 52 \text{ weeks} + 1 \text{ day} \implies \mathbf{1\text{ Odd Day}}$
- **Leap Year (366 days):** - $366 \div 7 = 52 \text{ weeks} + 2 \text{ days} \implies \mathbf{2\text{ Odd Days}}$

### 🔍 Leap Year Validation Rules
1. **Non-Centurial Years:** Must be perfectly divisible by $4$. (e.g., $2024$, $2028$)
2. **Centurial Years (ending in `00`):** Must be perfectly divisible by $\mathbf{400}$. 
   - *Leap Centuries:* $1600$, $2000$, $2400$
   - *Ordinary Centuries:* $1700$, $1800$, $1900$, $2100$

### ⏳ Century Odd Day Anchor Matrix
Over a 100-year block, there are exactly 76 Ordinary Years and 24 Leap Years. 
$\text{Total Odd Days} = (76 \times 1) + (24 \times 2) = 124 \text{ days} \equiv 124 \pmod 7 = \mathbf{5 \text{ odd days}}$.

| Year Scale | Accumulation Formula | Base Code (Odd Days) | Day Anchor |
| :--- | :--- | :---: | :--- |
| **100 Years** | $5$ | **5** | Friday |
| **200 Years** | $5 \times 2 = 10 \equiv 3 \pmod 7$ | **3** | Wednesday |
| **300 Years** | $5 \times 3 = 15 \equiv 1 \pmod 7$ | **1** | Monday |
| **400 Years** | $(5 \times 4) + 1 \text{ (Leap Day)} = 21 \equiv 0 \pmod 7$ | **0** | Sunday |

---

## 3. Master Formulas

### 🧮 Clocks: The Angle Formula
To calculate the absolute acute angle $\theta$ between the hour hand and minute hand at any specific configuration of **H hours** and **M minutes**:

$$\theta = \left| 30H - \frac{11}{2}M \right|$$

> [!WARNING] Reflex Angle Rule
> If $\theta > 180^\circ$, it represents the reflex angle. To find the standard inner angle, calculate: $\theta_{\text{inner}} = 360^\circ - \theta$.

### ⏱️ Clocks: Time of Specific Angles
To find the exact minute $M$ at which a target angle $\theta$ occurs within a given hour block $H$:
$$M = \frac{2}{11} \left( 30H \pm \theta \right)$$

---

## 4. High-Yield Shortcuts

### ⚡ Calendar Modular Day Matching
- **Ordinary Year Step:** A calendar year begins and ends on the exact same day of the week.
  - *Example:* If Jan 1st is Monday, Dec 31st of that same ordinary year is Monday. 
  - Shifting exactly 1 year ahead moves the calendar by **+1 day**.
- **Leap Year Step:** If shifting exactly 1 year ahead crosses February 29th, the calendar day moves forward by **+2 days**.

### 📉 Faulty Clocks (Gaining/Losing Time)
When a clock runs fast or slow, establish a linear true-to-false time ratio:
$$\frac{\text{True Time Interval}}{\text{Incorrect Time Interval}} = \frac{\text{Standard True Duration}}{\text{Standard True Duration} \pm \text{Error}}$$

---

## 5. Solved Canonical Problems

### 🧩 Problem 1: Minute Overlaps
**Question:** At what exact time between 3 o'clock and 4 o'clock will the hands of a clock coincide?
**Solution:**
1. At coincidence, target angle $\theta = 0^\circ$. The base hour $H = 3$.
2. Use the time formula:
   $$M = \frac{2}{11}(30 \times 3 \pm 0) = \frac{2}{11}(90) = \frac{180}{11} = 16\frac{4}{11}\text{ minutes}$$
**Result:** $3:16\frac{4}{11}\text{ PM}$

---

### 🧩 Problem 2: Fixed Historical Date Decoding
**Question:** Find the day of the week for August 15, 1947.
**Solution:**
1. Split the timeline into completed periods up to December 31, 1946, plus the running days of 1947.
2. **Breakdown:** - $1600 \text{ Years} = 0 \text{ odd days}$
   - $300 \text{ Years} = 1 \text{ odd day}$
   - $46 \text{ Years} = \lfloor 46 / 4 \rfloor = 11 \text{ leap years} \implies 46 + 11 = 57 \text{ days} \equiv 57 \pmod 7 = 1 \text{ odd day}$.
3. **Running Days of 1947 (Jan 1 to Aug 15):**
   $$\text{Jan}(3) + \text{Feb}(0) + \text{Mar}(3) + \text{Apr}(2) + \text{May}(3) + \text{Jun}(2) + \text{Jul}(3) + \text{Aug}(15 \equiv 1) = 17 \text{ days} \equiv 3 \text{ odd days}$$
4. **Sum of Odd Days:** $0 + 1 + 1 + 3 = 5 \text{ odd days}$.
5. Day code $5$ corresponds to Friday.
**Result:** Friday

---

### 🧩 Problem 3: The Broken Oscillator
**Question:** A clock gains 15 minutes every 24 hours. If it is set correctly at 8:00 AM on Monday, what is the true time when the clock displays 8:00 PM on the subsequent Wednesday?
**Solution:**
1. Compute the time elapsed on the faulty clock face:
   - Monday 8:00 AM $\to$ Wednesday 8:00 PM = **60 hours**.
2. Determine the scaling factor:
   - In 24 hours of true time, the clock ticks for $24 \text{ hours} + 15 \text{ mins} = 24.25 \text{ hours}$.
   $$\frac{\text{True Duration}}{\text{Faulty Duration}} = \frac{24}{24.25} = \frac{96}{97}$$
3. Compute the true elapsed time:
   $$\text{True Hours} = 60 \times \frac{96}{97} = 59.3814\text{ hours}$$
   $$\text{Total Error Offset} = 60 - 59.3814 = 0.6186\text{ hours} \approx 37\text{ minutes and } 7\text{ seconds}$$
4. Deduct the error offset from the faulty display reading (8:00 PM):
**Result:** $7:22:53\text{ PM}$