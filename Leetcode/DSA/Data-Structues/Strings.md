---
topic: string
category: linear
structure: contiguous
data: homogeneous
mutability: immutable
access: direct

priority: high

importance_reason:
  - most problems reduce to array-of-characters thinking
  - immutability changes how operations behave internally

used_in:
  - two pointers
  - sliding window
  - hashing
  - pattern matching

difficulty: basic
status: foundation

tags:
  - dsa
  - string
  - linear
---

# Strings

## Definition
A string is a **sequence of characters** stored in contiguous memory, conceptually similar to an array, but typically **immutable** in high-level languages like Java.

---

## Core Insight

> Strings behave like arrays for access, but **immutability makes modification expensive**.

---

## Mental Model / Invariant

> A string’s length and character positions **never change after creation**.

Any “modification” creates a **new string**.

If characters can be changed in-place → it is **not** an immutable string.

---

## Characteristics

- Contiguous character storage
- Fixed length after creation
- Direct index access
- Immutable (Java, Python)
- Often backed by a character array internally

---

## Operations, Implementation & Time Complexity

| Operation | Implementation / Method | Time |
|----------|--------------------------|------|
| Create | [[#Create String]] | O(n) |
| Access | [[#Access Character]] | O(1) |
| Traverse | [[#Traverse]] | O(n) |
| Concatenation | [[#Concatenation]] | O(n) |
| Substring | [[#Substring]] | O(n) |
| Compare | [[#Compare Strings]] | O(n) |
| Convert to Array | [[#To Character Array]] | O(n) |

---

## Space Complexity

- Storage: **O(n)**
- Extra space: **O(n)** for most transformations (immutability)

---

## Implementation (From Scratch – Java)

### Create String
```java
String s1 = "hello";                 // string literal
String s2 = new String("hello");     // explicit object
```

---

### Access Character
```java
char c = s1.charAt(1); // 'e'
```

---

### Traverse
```java
for (int i = 0; i < s1.length(); i++) {
    System.out.println(s1.charAt(i));
}
```

---

### Concatenation
```java
String s = s1 + " world";   // creates new string
```

---

### Substring
```java
String sub = s1.substring(1, 4); // "ell"
```

---

### Compare Strings
```java
s1.equals(s2);   // content comparison
s1 == s2;        // reference comparison (avoid)
```

---

### To Character Array
```java
char[] arr = s1.toCharArray();
```

---

## Inbuilt / Library Usage (Java)

### String Methods
```java
s.length();
s.charAt(i);
s.substring(a, b);
s.indexOf('a');
s.contains("el");
```

### Mutable Alternatives
```java
StringBuilder sb = new StringBuilder("hello");
sb.append(" world");
```

> `StringBuilder` allows **in-place modification** with amortized O(1) appends.

---

## What Actually Matters (For Problems)

- Immutability vs mutability
- Index-based access is cheap
- Repeated concatenation is expensive
- Conversion to `char[]` is often required
- Equality must use `.equals()`

---

## Common Pitfalls

- Using ` == ` instead of `.equals()`
- Building strings in loops with `+`
- Forgetting substring cost
- Assuming modification is in-place

---

## Typical Problem Patterns

- Two pointers → palindrome, reversal
- Sliding window → longest substring problems
- Frequency counting → anagrams
- Prefix matching → dictionary problems

---

## When NOT to Use

- Heavy in-place modification
- Frequent insertions/deletions
- Large-scale string building in loops

---

## One-Line Recall

> Strings allow **O(1) character access** like arrays, but **immutability makes every modification create a new string**, costing **O(n)**.
