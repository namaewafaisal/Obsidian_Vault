---
title: Syllogism — Complete Study Note
tags: [reasoning, syllogism, placement]
priority: Must
status: final
---

# 🧠 Syllogism

---

## 🔴 CORE IDEA

Given 2–3 statements → check if conclusions are **100% guaranteed**.
Real-world knowledge is irrelevant. Pure diagram logic only.

---

## 🔴 THE 4 STATEMENT TYPES

| Statement | Venn Diagram |
|-----------|-------------|
| All A are B | Circle A fully inside B |
| No A is B | Circles fully separate |
| Some A are B | Circles partially overlapping |
| Some A are not B | Part of A is outside B |

---

## 🔴 COMBINATION RULES (memorize all 7)

| Statement 1 | Statement 2 | Conclusion |
|------------|------------|-----------|
| All A→B | All B→C | All A→C ✅ |
| All A→B | No B is C | No A is C ✅ |
| Some A→B | All B→C | Some A→C ✅ |
| Some A→B | No B is C | Some A are not C ✅ |
| All A→B | Some B→C | No conclusion ❌ |
| Some A→B | Some B→C | No conclusion ❌ |
| No A→B | No B→C | No conclusion ❌ |

---

## 🔴 CONVERSION RULES

| Statement | Can you reverse it? | Result |
|-----------|--------------------|----|
| All A are B | No | — |
| No A is B | Yes | No B is A |
| Some A are B | Yes | Some B are A |
| Some A are not B | No | — |

> This is how you get conclusions like "Some B are A" from "Some A are B"

---

## 🔴 COMPLEMENTARY PAIR TRICK

When neither conclusion alone follows, check if they form a complementary pair:
- "Some A are B" + "No A is B" → complementary ✅
- "Some A are B" + "Some A are not B" → complementary ✅
- "All A are B" + "No A is B" → NOT complementary ❌

If complementary → answer is **Either I or II follows**

---

## 🟠 SOLVED EXAMPLES

### Example 1
> All flowers are trees. No tree is a rock.
> I: No flower is a rock. II: Some rocks are flowers.

All A→B + No B→C = No A→C ✅
I follows. II is reversal of No (which reverses to No, not Some). II ❌
**Only I follows.**

---

### Example 2
> Some cars are bikes. All bikes are trucks.
> I: Some cars are trucks. II: All trucks are cars. III: Some bikes are cars.

Some A→B + All B→C = Some A→C → I ✅
All trucks are cars — no rule gives this → II ❌
Some bikes are cars = reversal of "Some cars are bikes" → III ✅
**I and III follow.**

---

### Example 3 — Complementary pair
> All tables are chairs. Some chairs are wooden.
> I: Some tables are wooden. II: No table is wooden.

All + Some = No conclusion. Neither I nor II individually follows.
I = "Some tables wooden" and II = "No table wooden" → complementary pair ✅
**Either I or II follows.**

---

### Example 4 — Three statements
> All birds are animals. All animals are living. No living thing is a stone.
> I: No bird is a stone. II: All birds are living. III: Some living are birds.

All birds→animals + All animals→living = All birds→living (II ✅)
All birds→living + No living→stone = No bird→stone (I ✅)
All birds→living → Some living→birds (conversion) (III ✅)
**All three follow.**

---

### Example 5 — No conclusion trap
> Some rivers are lakes. Some lakes are ponds.
> I: Some rivers are ponds. II: No river is a pond.

Some + Some = No conclusion. Neither I nor II definitively follows.
But I + II are complementary → **Either I or II follows.**

---

## 🟠 SOLVING APPROACH

1. Draw Venn diagram mentally for each statement
2. Apply combination rules in sequence
3. For each conclusion — check if it's 100% guaranteed by the diagram
4. If neither follows AND they're complementary → Either/Or
5. Watch for conversions — Some A→B also means Some B→A

---

## 🟡 PRACTICE PROBLEMS

**P1.** All pens are books. All books are pencils.
I: All pens are pencils. II: Some pencils are pens. III: Some books are pens.

**P2.** No dog is a cat. All cats are animals.
I: No dog is an animal. II: Some animals are not dogs.

**P3.** Some boxes are bags. No bag is a bottle.
I: Some boxes are not bottles. II: No box is a bottle.

**P4.** All men are mortal. Socrates is a man.
I: Socrates is mortal. II: Some mortals are men.

**P5.** Some A are B. Some B are C. No C is D.
I: Some A are not D. II: Some B are not D. III: No B is D.

**P6.** All teachers are educated. Some educated are honest.
I: Some teachers are honest. II: All honest are teachers.
III: Some educated are teachers.

---

## ❌ COMMON MISTAKES

- Assuming "All A are B" → "All B are A" (WRONG — can't reverse All)
- Using real-world knowledge ("dogs can't be cats")
- Forgetting that Some A→B also gives Some B→A (conversion valid for Some)
- Missing the complementary pair when neither conclusion individually follows
