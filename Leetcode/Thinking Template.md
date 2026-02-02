# 🧠 Problem Solving Thinking Template (LOCKED)

## 1. Problem Restatement (Own words)
- What is being asked?
- What must be preserved?
- What is allowed to change?

## 2. Constraints That Matter
- Input size?
- In-place?
- Order matters?
- Exact match vs optimization?

(Explicitly say what DOES NOT matter)

## 3. Brute Force Idea
- What would I try first?
- Why is it too slow / invalid?

## 4. State Definition
- What variables change as I progress?
- Pointers? indices? counts? stack? recursion?

## 5. Invariant (MOST IMPORTANT)
> After every step, what must ALWAYS be true?

Write this in **one sentence**.

If you can’t write this → STOP.

## 6. Transition Logic
- When does state expand?
- When does it shrink?
- What triggers movement / pop / recursion?

## 7. Edge Cases (Invariant Stress Test)
- Empty?
- One element?
- All same?
- Worst-case ordering?

## 8. Final Implementation Plan
- Structure used
- Loop / recursion shape
- Why it matches invariant

## 9. Post-Solve Reflection
- What wrong idea did I have initially?
- What invariant fixed it?
- Where else can this apply?