---
topic: substring_memory
category: linear
operation: slicing
memory_behavior: copying
mutability: immutable

priority: medium
difficulty: basic
status: foundation

depends_on:
  - string
  - string_equality

used_in:
  - parsing
  - tokenization
  - string_processing

tags:
  - dsa
  - string
  - substring
  - memory
---

# Substring & Memory Costs — What Actually Happens

## 0. The real problem this concept solves

`substring()` *looks* cheap and harmless, but its **memory behavior changed across Java versions**.
Misunderstanding this leads to:
- hidden performance issues
- unexpected memory retention
- wrong time complexity assumptions

**One-line anchor:**  
> Substring is conceptually slicing, but operationally it involves memory decisions.

---

## 1. What substring logically does

```java
String s = "abcdefgh";
String sub = s.substring(2, 5); // "cde"
```

Logical intent:
- extract a portion of the string
- treat it as an independent string

The confusion comes from **how this was implemented** historically.

---

## 2. Old Java behavior (≤ Java 6) — shared backing array

Internally, `String` used to store:
- `char[] value`
- `offset`
- `count`

So:
```java
String big = hugeString();
String small = big.substring(0, 10);
```

What actually happened:
- `small` **shared the same char[]** as `big`
- only `offset` and `count` differed

### Consequence (very dangerous)

If `big` was very large:
- `small` kept the **entire char[] alive**
- garbage collector could not free it

This caused **silent memory leaks**.

---

## 3. Why Java changed substring behavior

The old design was:
- fast
- memory-efficient for small strings

But in real systems:
- extremely hard to debug
- caused large memory retention
- violated developer expectations

Java chose **safety and predictability** over micro-optimizations.

---

## 4. Modern Java behavior (Java 7+)

Today, `substring()` works like this:

```java
String sub = s.substring(2, 5);
```

What happens:
- a **new char[] is allocated**
- characters are **copied**
- `sub` owns its own memory

No shared backing array.

---

## 5. Time and space cost (important)

Modern Java guarantees:
- Time complexity → **O(length of substring)**
- Space complexity → **O(length of substring)**

So substring is **not O(1)** anymore.

This matters in:
- tight loops
- large strings
- repeated slicing

---

## 6. Common misuse patterns

### Misuse 1 — Substring in a loop

```java
for (int i = 0; i < s.length(); i++) {
    String sub = s.substring(0, i);
}
```

Each iteration:
- allocates new memory
- copies characters

Total cost → **O(n²)**.

---

### Misuse 2 — Using substring instead of index tracking

```java
while (!s.isEmpty()) {
    process(s.substring(1));
    s = s.substring(1);
}
```

Creates many short-lived String objects.

Better approach:
- track an index
- avoid repeated slicing

---

## 7. When substring is perfectly fine

Substring is safe when:
- string sizes are small
- used sparingly
- not inside hot loops

Clarity can outweigh micro-optimizations.

---

## 8. Relationship to earlier concepts

This ties directly to:
- array copying cost
- String immutability
- StringBuilder usage

Everything follows the same rule:
> Copying contiguous memory costs linear time.

---

## 9. Final mental model (lock this)

> In modern Java, `substring()` always copies characters — treat it as a linear-cost operation.

---

## Linked notes

- [[Strings_Fundamentals]]
- [[String_Equality_And_Pool]]
- [[Common_String_Bugs]]
- [[StringBuilder_And_StringBuffer]]
