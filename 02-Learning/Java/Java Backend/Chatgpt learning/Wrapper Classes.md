---
tags:
  - java
  - wrapper-classes
  - cheatsheet
  - core-java
date-time: 2026-02-01T11:45:00
status: revision
---

# Wrapper Classes — Core Java Cheatsheet

Wrap **primitives as objects**.

---

## 1️⃣ Common Wrapper Classes

| Primitive | Wrapper |
|--------|--------|
| `int` | `Integer` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |
| `long` | `Long` |

---

## 2️⃣ Creation Syntax

```java
Integer a = Integer.valueOf(10);
Integer b = 10;                 // autoboxing
```

⚠️ `new Integer()` is deprecated (avoid)

---

## 3️⃣ Conversion Methods

| Method | Returns | Example |
|------|--------|--------|
| `parseInt()` | primitive | `Integer.parseInt("10")` |
| `valueOf()` | wrapper | `Integer.valueOf("10")` |
| `intValue()` | primitive | `a.intValue()` |

Same pattern for `Double`, `Long`, etc.

---

## 4️⃣ Autoboxing / Unboxing

```java
Integer x = 10;   // boxing
int y = x;        // unboxing
```

- Automatic
- Happens at compile time

---

## 5️⃣ Important Rules (MCQ Gold)

- Wrapper classes are **immutable**
- Passed by value (reference copy)
- `equals()` compares **value**
- == may fail due to different objects

---

## 6️⃣ Integer Cache (Awareness)

```java
Integer a = 100;
Integer b = 100;
a == b;   // true

Integer c = 200;
Integer d = 200;
c == d;   // false
```

- Cache range: **-128 to 127**
- Use `equals()` always

---

## 🔗 Related Notes
- [[Memory Model]]
- [[Object Class]]
- [[Collections Framework]]

---

## 🔒 One-Line Recall

> **Wrappers are immutable objects around primitives.  
> Use equals(), not reference comparison.**

---
