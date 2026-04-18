---
title: Profit & Loss — Complete Study Note
tags: [aptitude, profit-loss, placement]
priority: Must
status: final
---

# 💰 Profit & Loss

---

## 🔴 CORE DEFINITIONS

- **CP** = Cost Price (what you paid)
- **SP** = Selling Price (what you sold for)
- **MP** = Marked Price (tag price before discount)
- **Discount** = reduction on MP

SP < CP → Loss
SP > CP → Profit

---

## 🔴 FORMULAS (IMPORTANCE ORDER)

### 1. Profit % and Loss %
$$\text{Profit \%} = \frac{SP - CP}{CP} \times 100$$
$$\text{Loss \%} = \frac{CP - SP}{CP} \times 100$$
> Always on CP, never on SP

### 2. SP from CP
$$SP = CP \times \frac{100 + P\%}{100} \quad \text{(profit)}$$
$$SP = CP \times \frac{100 - L\%}{100} \quad \text{(loss)}$$

### 3. CP from SP
$$CP = \frac{SP \times 100}{100 + P\%} \quad \text{(profit)}$$
$$CP = \frac{SP \times 100}{100 - L\%} \quad \text{(loss)}$$

### 4. MP → SP after Discount
$$SP = MP \times \frac{100 - D\%}{100}$$

### 5. Markup → Discount → Net Profit (THE BIG ONE)
$$SP = CP \times \frac{100 + \text{markup}}{100} \times \frac{100 - \text{discount}}{100}$$

### 6. Successive Profit/Loss (two changes)
$$\text{Net \%} = a + b + \frac{ab}{100}$$
(+ for profit, − for loss)

### 7. Same % profit and loss on same SP → always net loss
$$\text{Net Loss \%} = \frac{x^2}{100}$$

### 8. False Weight Gain %
$$\text{Gain \%} = \frac{\text{True weight} - \text{False weight}}{\text{False weight}} \times 100$$

---

## 🟠 SOLVED EXAMPLES

### Example 1 — Markup + Discount
> Goods marked 40% above CP, sold at 20% discount. Profit %?

SP = CP × (140/100) × (80/100) = CP × 1.12
Profit = **12%**

**Shortcut using successive formula:**
Net = 40 + (−20) + (40 × −20)/100 = 40 − 20 − 8 = **12%** ✓

---

### Example 2 — Find CP from SP
> Sold at ₹850 at 15% profit. Find CP.

CP = (850 × 100) / 115 = **₹739.13**

---

### Example 3 — Two items, same SP, one profit one loss
> Two items sold for ₹1200 each. One at 20% profit, one at 20% loss. Net?

Net Loss = x²/100 = 400/100 = **4%**
(Always a loss when same % on same SP)

---

### Example 4 — False weight
> Shopkeeper uses 900g weight instead of 1kg. Gain %?

Gain = (1000 − 900)/900 × 100 = 100/900 × 100 = **11.11%**

---

### Example 5 — CP of 12 = SP of X, find profit
> CP of 12 oranges = SP of 9 oranges. Profit %?

Let CP per orange = 1 → CP of 12 = 12
SP of 9 = 12 → SP per orange = 12/9 = 4/3
Profit % = (4/3 − 1)/1 × 100 = (1/3) × 100 = **33.33%**

**Shortcut: CP of m = SP of n → Profit % = (m−n)/n × 100**

---

### Example 6 — Selling X at price of Y
> Selling 12 for price of 15. Profit %?

Profit % = (15−12)/12 × 100 = 3/12 × 100 = **25%**

---

## 🟠 SOLVING APPROACH

1. **Identify what's given** — CP/SP/MP/Profit%/Loss%/Discount%
2. **Find the unknown** using relevant formula
3. For markup + discount questions → always multiply factors (use successive %)
4. For two item sold at same SP same %→ directly apply x²/100 loss
5. For false weight → denominator is the false weight

---

## 🟡 PRACTICE PROBLEMS

**P1.** A man buys a watch at ₹1200, spends ₹200 on repair, sells at ₹1600. Profit %?

**P2.** Sold at 25% profit. If CP was ₹400 less, profit would be 50%. Find CP.

**P3.** A trader marks goods 30% above CP and allows 10% discount. Profit %?

**P4.** CP of 20 articles = SP of 16 articles. Profit %?

**P5.** A person sells two TVs for ₹9900 each. On one he gains 10%, on other he loses 10%. Net profit/loss?

**P6.** Shopkeeper gives 800g instead of 1kg and also charges 10% extra. Overall gain %?

**P7.** SP = ₹720 after successive discounts of 10% and 20%. Find MP.

**P8.** A sold to B at 20% profit. B sold to C at 15% loss. C paid ₹2040. What did A pay?

---

## ❌ COMMON MISTAKES

- Calculating % on SP instead of CP
- Forgetting net is always a LOSS when equal % profit and loss on same SP
- Adding % directly instead of using successive formula for markup + discount
