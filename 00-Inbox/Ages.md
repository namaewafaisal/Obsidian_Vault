# ⏳ Problems on Ages: Ultimate Placement Note

## 📌 Concept Maps & Quick Links
- **Module:** Quantitative Aptitude (Arithmetic Ability)
- **Subtopics:** [[#1. Structural Variable Translation]], [[#2. The Ratio Gap Balancing Engine (The Master Shortcut)]], [[#3. Specialized Linear & Symmetric Systems]], [[#4. Average Age Group Influx Shortcuts]], [[#5. Edge Cases & Traps]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #ages #r-s-aggarwal

---

## 1. Structural Variable Translation

To build equations accurately without making logic errors, use the **Anchor-Present Method**. Always assume the current unknown age is a baseline variable (e.g., $x$) and scale forward or backward from it.

```
      [ Past Era ]  ◄───────  [ PRESENT ERA ]  ───────►  [ Future Era ]
     (x - n) years            Anchor Age: (x)             (x + m) years
     "n years ago"                                        "m years hence"
     "n years back"                                       "m years later"
```

### 🧮 Linguistic-to-Algebraic Mapping Table

| Verbal Statement | Algebraic Representation |
| :--- | :--- |
| Present age of a person is $A$. | Baseline $= A$ |
| Person's age $n$ years later / hence / after. | $= A + n$ |
| Person's age $m$ years ago / back / before. | $= A - m$ |
| Current ages are in the ratio $X : Y$. | Ages are represented as $Xk$ and $Yk$ |
| $A$ is $n$ times as old as $B$. | $A = n \times B$ |
| $A$ is as much younger to $B$ as he is older to $C$. | $B - A = A - C \implies 2A = B + C$ |

---

## 2. The Ratio Gap Balancing Engine (The Master Shortcut)

Avoid setting up cross-multiplication equations like $\frac{ax + n}{bx + n} = \frac{p}{q}$. Instead, use the **Gap Method**, which relies on a simple real-world fact: **The absolute difference between the ages of two people never changes.**

### 🛠️ Execution Algorithm
1. Write down the structural ratios for **Era 1** and **Era 2** vertically.
2. Compute the horizontal internal difference (the gap) for each row: $\Delta_1 = |A_1 - B_1|$ and $\Delta_2 = |A_2 - B_2|$.
3. If $\Delta_1 \neq \Delta_2$, make them equal by multiplying each ratio by the other row's gap value (or scale them to their $\text{LCM}$).
4. Calculate the vertical unit step difference: $\Delta_{\text{units}} = \text{New } A_2 - \text{New } A_1$.
5. Determine the value of one unit: 
   $$1 \text{ Unit} = \frac{\text{Real Time Elapsed between Eras}}{\Delta_{\text{units}}}$$



---

## 3. Specialized Linear & Symmetric Systems

### 🎯 Pattern A: Symmetrical Linear Offsets
**Question Structure:** $A$ is as much younger to $B$ as he is older to $C$. The sum of the ages of $B$ and $C$ is $S$. Find $A$'s age.
- **Shorthand:** $A = \frac{S}{2}$
- **Proof:** $B - A = A - C \implies 2A = B + C \implies 2A = S \implies A = \frac{S}{2}$

### 🎯 Pattern B: Non-Symmetrical Cross Ratio Matrix
When dealing with fractional changes over complex timelines:
**Example:** *Raman's age after 15 years will be 5 times his age 5 years back. Find his present age.*
- **System Setup:** Let Present Age $= x$.
  - Future condition: $(x + 15)$
  - Past condition: $(x - 5)$
- **Equation:** $$x + 15 = 5(x - 5) \implies x + 15 = 5x - 25 \implies 4x = 40 \implies x = \mathbf{10}$$

---

## 4. Average Age Group Influx Shortcuts

A common question type involves a group's average age changing when a single individual (like a teacher or captain) joins.

### ⚡ The 3-Second Influx Formula
If the addition of $1$ new member to a group of $N$ items increases the average age from $A_{\text{old}}$ to $A_{\text{new}}$ by exactly $1$ unit:
$$\text{New Member's Age} = N + A_{\text{old}} + 1$$

*If the average increases by a value $\Delta$ other than 1:*
$$\text{New Member's Age} = A_{\text{old}} + \Big( (N + 1) \times \Delta \Big)$$

#### 🧩 Quick Verification
*The average age of 20 students is 16. When the teacher joins, the average increases by 1. Find the teacher's age.*
- **Traditional Method:** $(21 \times 17) - (20 \times 16) = 357 - 320 = 37$
- **Shortcut Trick:** $20 + 16 + 1 = \mathbf{37 \text{ years}}$

---

## 5. Edge Cases & Traps

### 🚨 Trap 1: The Product Equation Trap
When a problem gives you the **product of ages**, do *not* drop the variable multiplier component.
- **Example:** The ages of $A$ and $B$ are in the ratio $4 : 5$, and the product of their ages is $240$.
- **Correct Setup:** $(4k) \times (5k) = 240 \implies 20k^2 = 240 \implies k^2 = 12 \implies k = \sqrt{12}$.
- **Common Error:** Forgetting the $k^2$ and writing $20k = 240 \implies k = 12$, which gives the wrong answer.

### 🚨 Trap 2: Time Travel Shifts on Ratios
You cannot multiply or modify a ratio unless you shift it to the correct era first. If a ratio is from "5 years ago," you must subtract 5 years from any present values before plugging them into the ratio.

---

## 6. Real Solved Examples

### 🧩 Example 1: Gap Method with a Post-Calculation Offset (Bank P.O.)
**Question:** The present ages of Amit and his father are in the ratio $2 : 5$. Four years from now, the ratio becomes $5 : 11$. What was the father’s age five years ago?
**Solution:**
1. **List the ratios and find the differences:**
   - Present: $2 : 5$ ($\Delta = 3$)
   - Future (+4 years): $5 : 11$ ($\Delta = 6$)
2. **Balance the gaps:** The LCM of 3 and 6 is 6. Multiply the present ratio by 2:
   $$\text{Balanced Present Ratio} = (2 \times 2) : (5 \times 2) = \mathbf{4 : 10}$$
   $$\text{Balanced Future Ratio} = \mathbf{5 : 11}$$
3. **Find the unit shift:** The ratio value increases by 1 unit ($4 \to 5$ and $10 \to 11$). This 1-unit increase represents 4 real years.
   $$1 \text{ Unit} = 4 \text{ Years}$$
4. **Calculate ages:**
   - Father's Present Age $= 10 \text{ units} \times 4 = 40 \text{ years}$.
   - Father's Age 5 Years Ago $= 40 - 5 = \mathbf{35 \text{ years}}$.

### 🧩 Example 2: Sum of Intervals System
**Question:** The sum of the ages of 5 children born at intervals of 3 years each is 50 years. What is the age of the youngest child?
**Solution:**
1. Let the age of the youngest child be $x$.
2. Write out the ages of the 5 children: $x, (x+3), (x+6), (x+9), (x+12)$.
3. Sum the ages and solve for $x$:
   $$x + (x+3) + (x+6) + (x+9) + (x+12) = 50$$
   $$5x + 30 = 50 \implies 5x = 20 \implies x = \mathbf{4 \text{ years old}}$$