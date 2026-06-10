# 📐 Mensuration 2D: Area, Shapes, Perimeter & Triangles

## 📌 Quick Links
- **Module:** Quantitative Aptitude
- **Subtopics:** [[#1. Quadrilateral Matrices]], [[#2. Circle Sectors]], [[#3. The Ultimate Triangle Guide]], [[#4. Speedrun Geometry Shortcuts]]
- **Tags:** #aptitude #tcs-nqt #placement-prep #quant #geometry #mensuration

---

## 1. Quadrilateral Matrices

### 🔹 Rectangle
- $\text{Area} = l \times w$
- $\text{Perimeter} = 2(l + w)$
- $\text{Diagonal } (d) = \sqrt{l^2 + w^2}$

### 🔹 Square
- $\text{Area} = a^2 \quad \text{or} \quad \frac{d^2}{2}$
- $\text{Perimeter} = 4a$
- $\text{Diagonal } (d) = a\sqrt{2}$

---

## 2. Circle Sectors

- $\text{Area} = \pi r^2$
- $\text{Circumference} = 2\pi r$
- $\text{Area of a Sector} = \frac{\theta}{360^{\circ}} \times \pi r^2$
- $\text{Arc Length} = \frac{\theta}{360^{\circ}} \times 2\pi r$

---

## 3. The Ultimate Triangle Guide

### 📊 Structural Equations by Type

| Triangle Type | Defining Property | Key Formula |
| :--- | :--- | :--- |
| **General / Scalene** | $a \neq b \neq c$ | $\text{Area} = \sqrt{s(s-a)(s-b)(s-c)} \quad [s = \frac{a+b+c}{2}]$ |
| **Equilateral** | $a = b = c$ | $\text{Height} = \frac{\sqrt{3}}{2}a \quad \boldsymbol{\Big\vert} \quad \text{Area} = \frac{\sqrt{3}}{4}a^2$ |
| **Isosceles** | $a = b$ | $\text{Area} = \frac{b}{4}\sqrt{4a^2 - b^2}$ |
| **Right-Angled** | One angle $= 90^{\circ}$ | $\text{Area} = \frac{1}{2} \times \text{Base} \times \text{Height} \quad [a^2 + b^2 = c^2]$ |

### 🎯 Central Coordinate Anchors
- **Median:** Connects vertex to opposite midpoint. Splits triangle into two equal areas.
- **Altitude:** Perpendicular height dropped from a vertex ($90^{\circ}$ to base).
- **Centroid ($G$):** Intersection of 3 medians. Divides medians in a **$2:1$ ratio**.
  $$G = \left( \frac{x_1 + x_2 + x_3}{3}, \, \frac{y_1 + y_2 + y_3}{3} \right)$$
- **Orthocenter:** Intersection of 3 altitudes. Sits exactly on the $90^{\circ}$ vertex in right triangles.

---

## 4. Speedrun Geometry Shortcuts

### ⚡ Path Formulas for Rectangular Gardens
- **External Path of width $w$:** $\text{Area} = 2w(l + b + 2w)$
- **Internal Path of width $w$:** $\text{Area} = 2w(l + b - 2w)$
- **Central Cross Paths of width $w$:** $\text{Area} = w(l + b - w)$

### ⚡ Wire Transformation Rule
When a wire is bent from a **Circle** into a **Square**, the perimeter is conserved. Their enclosed areas hold a fixed ratio:
$$\frac{\text{Area of Circle}}{\text{Area of Square}} = \frac{14}{11}$$

### ⚡ Dimensional Scale Rule
If the linear dimensions (side, radius) of a 2D shape scale up by $x\%$, the perimeter increases by $x\%$, while the area jumps by:
$$\text{Net Area \% Increase} = \left( 2x + \frac{x^2}{100} \right)\%$$