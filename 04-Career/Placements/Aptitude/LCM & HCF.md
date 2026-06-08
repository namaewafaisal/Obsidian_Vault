# 🔢 Highest Common Factor (HCF) & Least Common Multiple (LCM)

## 📌 Concept Maps & Quick Links
- **Module:** Quantitative Aptitude (Number Theory)
- **Subtopics:** [[#1. Definitions & Direct Extraction]], [[#2. Fractions & Decimals]], [[#3. Operational Theorems & Identities]], [[#4. Real-World Translation Shorthands]], [[#5. Advanced Problem-Solving Frameworks]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #number-theory

---

## 1. Definitions & Direct Extraction

### 🔹 Highest Common Factor (HCF / GCD / GCM)
The greatest number that divides two or more given numbers exactly without leaving a remainder.



#### 🛠️ Extraction Techniques
1. **Prime Factorization Method:** Express numbers as products of prime powers. Multiply the **lowest powers** of all common prime factors.
2. **Long Division Method (Euclidean Algorithm):** Divide the larger number by the smaller. Take the resulting remainder and make it the new divisor; make the old divisor the new dividend. Repeat until the remainder drops to zero. **The final divisor is the HCF.**
3. **Multi-Number Chain Rule:** To compute the HCF of three numbers $(A, B, C)$:
   $$\text{HCF}(A, B, C) = \text{HCF}\Big( \text{HCF}(A, B),\, C \Big)$$

### 🔹 Least Common Multiple (LCM)
The smallest positive non-zero integer that is exactly divisible by each of the given numbers.

#### 🛠️ Extraction Techniques
1. **Prime Factorization Method:** Factor elements into prime power components. Multiply the **highest powers** of every prime factor present across any of the numbers.
2. **Common Division Method (Shorthand):** Arrange numbers horizontally. Divide progressively by prime factors that divide at least two numbers cleanly, carrying forward non-divisible items until no two elements share a common factor except 1.

---

## 2. Fractions & Decimals

### 🧮 Fractional Matrices
Before applying these structural formulas, **always reduce the fractions to their lowest terms first**.

$$\text{HCF of Fractions} = \frac{\text{HCF of Numerators}}{\text{LCM of Denominators}}$$

$$\text{LCM of Fractions} = \frac{\text{LCM of Numerators}}{\text{HCF of Denominators}}$$

### 🧮 Decimal Processing Shortcut
1. Count the max decimal places among all numbers. Balance out the remaining numbers by appending trailing zeros.
2. Strip out the decimal points entirely to convert them into standard integers.
3. Calculate the target HCF or LCM on these integers.
4. Shift the decimal point back to the left by the same number of places established in step 1.

---

## 3. Operational Theorems & Identities

### ⚡ The Product Identity
Valid **strictly for two numbers** ($A$ and $B$):
$$A \times B = \text{HCF}(A, B) \times \text{LCM}(A, B)$$

### ⚡ Co-Prime Mechanics
Two integers are co-prime if they share no common factors other than 1 ($\text{HCF} = 1$).
- If $A$ and $B$ are co-prime, then $\text{LCM}(A, B) = A \times B$.

### ⚡ Ratio Constraints
If two numbers are in the reduced ratio $a : b$, then their structural factor values can be written as $ax$ and $bx$, where **$x = \text{HCF}$**.
$$\text{LCM} = a \times b \times \text{HCF}$$

---

## 4. Real-World Translation Shorthands

| Scenario Indicators | Core Objective | Mathematical Operation |
| :--- | :--- | :--- |
| Finding *maximal* measuring length, *highest* container capacities, or tiling surfaces evenly. | Find the largest exact common divisor. | **Calculate HCF** |
| Cyclic synchronization loops (synchronized traffic light shifts, runners on a track, bells tolling together). | Find the first shared future repetition point. | **Calculate LCM** |
| Fractions requiring size ranking or sorting. | Normalize the fractional bases. | **Convert denominators to LCM** |

---

## 5. Advanced Problem-Solving Frameworks

### 🎯 Case 1: Division Leaving Variable Remainders
**Scenario:** Find the largest number that divides $A, B, C$ leaving remainders $a, b, c$ respectively.
$$\text{Target Divisor} = \text{HCF}(A - a,\, B - b,\, C - c)$$

### 🎯 Case 2: Division Leaving a Constant Remainder ($r$)
**Scenario:** Find the largest number that divides $A, B, C$ leaving the exact same remainder $r$ in each case (where $r$ is unknown).
$$\text{Target Divisor} = \text{HCF}(|A - B|,\, |B - C|,\, |C - A|)$$

### 🎯 Case 3: Finding Target Bounds (Fixed Common Difference)
**Scenario:** Find the smallest number which when divided by $A, B, C$ leaves remainders $a, b, c$, where the difference between the divisor and remainder is constant: $(A - a) = (B - b) = (C - c) = K$.
$$\text{Target Number} = \text{LCM}(A, B, C) - K$$

### 🎯 Case 4: The Algebraic Loop Test (Non-Zero Remainder Conditions)
**Scenario:** Find the smallest number that leaves a remainder $r$ when divided by $A, B, C$, but is perfectly divisible by a prime engine $X$.
$$\text{Structural Form} = [\text{LCM}(A, B, C) \cdot k] + r$$
*Technique:* Iterate integer values for $k$ ($1, 2, 3 \dots$) until the expression evaluates cleanly modulo $X \equiv 0$.

---

## 6. Unique Solved Patterns

### 🧩 Pattern A: Constrained Matrix Coordinates (The Multi-Pair Filter)
**Question:** The sum of two numbers is 462 and their HCF is 22. Find the total number of unique valid pairs that satisfy this condition.
**Solution:**
1. Express the two numbers using the HCF as a base factor: $22a$ and $22b$.
2. Set up the equation based on the given constraint: 
   $$22a + 22b = 462 \implies a + b = 21$$
3. Filter the integer pairs for $a$ and $b$ that sum to 21. **Crucial Constraint:** $a$ and $b$ *must* be co-prime ($\text{HCF}(a, b) = 1$) to keep the overall HCF exactly 22.
4. Valid co-prime pairs: $(1, 20), (2, 19), (4, 17), (5, 16), (8, 13), (10, 11)$.
**Result:** 6 valid pairs

### 🧩 Pattern B: Bounded Multi-Digit Extrapolations
**Question:** Identify the largest 5-digit number that is cleanly divisible by 15, 21, and 36.
**Solution:**
1. Determine the base divisor by finding the LCM of the target values:
   $$\text{LCM}(15, 21, 36) = 1260$$
2. Set the maximum baseline boundary for 5 digits: $99999$.
3. Divide the baseline boundary by the LCM to find the excess remainder:
   $$99999 \div 1260 \implies \text{Remainder} = 459$$
4. Subtract the excess remainder from the boundary to find the highest valid multiple:
   $$\text{Target Number} = 99999 - 459 = \mathbf{99540}$$
**Result:** 99540