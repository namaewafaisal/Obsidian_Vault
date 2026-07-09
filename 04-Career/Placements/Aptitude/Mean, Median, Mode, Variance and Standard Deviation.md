# 📊 Statistics: Central Tendency and Dispersion

## 📌 Concept Maps & Quick Links
- **Module:** Quantitative Aptitude / Data Interpretation
- **Subtopics:** [[#1. Measures of Central Tendency]], [[#2. Measures of Dispersion (Spread)]], [[#3. The Empirical Bridge Theorem]], [[#4. Coefficient of Variation (CV)]], [[#5. High-Yield Practice Walkthroughs]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #statistics

---

## 1. Measures of Central Tendency

Central tendency identifies the single central value that best represents an entire dataset.

### 🔹 A. Arithmetic Mean
The sum of all observations divided by the total number of observations.
$$\mu = \frac{\sum_{i=1}^{N} x_i}{N}$$

> [!TIP] Linear Shift Property
> If every data point in a set is modified by an operation $(x_i \pm K)$ or $(x_i \times K)$, the overall mean shifts by that exact same constant value $K$.

### 🔹 B. Median (The Middle Anchor)
The exact geometric middle of a dataset when sorted in ascending or descending order.
- **If $N$ is Odd:** The median is the value at index:
  $$\text{Median} = \left(\frac{N + 1}{2}\right)^{\text{th}} \text{ position}$$
- **If $N$ is Even:** The median is the average of the two middle terms:
  $$\text{Median} = \frac{\left(\frac{N}{2}\right)^{\text{th}} \text{ term} + \left(\frac{N}{2} + 1\right)^{\text{th}} \text{ term}}{2}$$

### 🔹 C. Mode (The Peak Frequency)
The value that appears with the highest frequency in the dataset. 
- A dataset can be **unimodal** (one mode), **bimodal** (two modes), or have **no mode** (all values appear with equal frequency).

---

## 2. Measures of Dispersion (Spread)

Dispersion measures how spread out the data points are from their central mean.



### 🔹 A. Variance ($\sigma^2$)
The average of the squared differences from the Mean. It measures the area of the spread, but changes the units to squares.
$$\sigma^2 = \frac{\sum_{i=1}^{N} (x_i - \mu)^2}{N}$$

### 🔹 B. Standard Deviation ($\sigma$)
The positive square root of the Variance. It represents the **average absolute spread** of data points away from the mean, expressed in the dataset's original unit.
$$\text{Standard Deviation } (\sigma) = \sqrt{\text{Variance}}$$

---

## 3. The Empirical Bridge Theorem

For moderately skewed distributions, the Mean, Median, and Mode are linked by this highly tested placement formula:

$$\text{Mode} = 3(\text{Median}) - 2(\text{Mean})$$

### 🛠️ Shorthand Derivations
- $\text{Mean} - \text{Mode} = 3(\text{Mean} - \text{Median})$

---

## 4. Coefficient of Variation (CV)

The Coefficient of Variation measures relative dispersion. It allows you to compare the volatility or consistency of two datasets with completely different units (e.g., comparing a height dataset in cm to a weight dataset in kg).

$$\text{CV} = \left( \frac{\sigma}{\mu} \right) \times 100\%$$

- **Lower CV %** $\implies$ The data is more stable, consistent, and uniform.
- **Higher CV %** $\implies$ The data is more volatile, spread out, and less consistent.

---

## 5. High-Yield Practice Walkthroughs

### 🧩 Problem 1: The Asymmetrical Distribution Loop
**Question:** In an asymmetrical distribution, the Mean and Median values are 30 and 32 respectively. Find the approximate value of the Mode.
**Solution:**
1. Identify the core components: $\text{Mean} = 30$, $\text{Median} = 32$.
2. Apply the Empirical Relationship formula:
   $$\text{Mode} = 3(\text{Median}) - 2(\text{Mean})$$
   $$\text{Mode} = 3(32) - 2(30) = 96 - 60 = \mathbf{36}$$
**Result:** 36

### 🧩 Problem 2: Even-Count Median Sorting Trap
**Question:** Find the median of this dataset: **15, 22, 18, 10, 12, 17, 25, 20**
**Solution:**
1. **Crucial First Step:** Sort the dataset in ascending order.
   $$\text{Sorted Array} = [10, 12, 15, 17, 18, 20, 22, 25]$$
2. Count the total elements ($N$): Here, $N = 8$ (Even).
3. Identify the two middle terms at positions $\frac{N}{2}$ and $\frac{N}{2} + 1$ (the 4th and 5th terms):
   - 4th term $= 17$
   - 5th term $= 18$
4. Calculate the average of these two middle values:
   $$\text{Median} = \frac{17 + 18}{2} = \frac{35}{2} = \mathbf{17.5}$$
**Result:** 17.5

### 🧩 Problem 3: Variance Identity Verification
**Question:** If the standard deviation ($\sigma$) of a distribution is 4.5, what is its Variance?
**Solution:**
1. Understand the mathematical identity: $\text{Variance} = \sigma^2$
2. Square the Standard Deviation value:
   $$\text{Variance} = (4.5)^2 = \mathbf{20.25}$$
**Result:** 20.25

---
## 🗂️ Advanced Grouped Frequency Data

When data is distributed across class intervals instead of raw scalar sets, use linear interpolation boundary tracking:

### 🔹 Grouped Mean
$$\mu = \frac{\sum (f_i \cdot x_i)}{\sum f_i} \quad \text{[where } x_i = \text{interval midpoint]}$$

### 🔹 Grouped Median
$$\text{Median} = L + \left( \frac{\frac{N}{2} - CF}{f} \right) \times h$$

### 🔹 Grouped Mode
$$\text{Mode} = L + \left( \frac{f_1 - f_0}{2f_1 - f_0 - f_2} \right) \times h$$