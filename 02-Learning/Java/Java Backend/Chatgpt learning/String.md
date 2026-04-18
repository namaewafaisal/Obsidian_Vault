---
tags:
  - java
  - string
  - cheatsheet
  - core-java
date-time: 2026-02-01T11:19:00
status: revision
---

# String — Core Java Cheatsheet

This note is for **fast recall before tests**.  
Strings are **immutable**.

---

## 1️⃣ String Basics

### Creation
```java
String s1 = "abc";                 // String pool
String s2 = new String("abc");     // Heap
```

- Literals → String Pool
- `new String()` → Heap (new object always)

---

## 2️⃣ Equality (EXAM CRITICAL)

```java
String a = "abc";
String b = "abc";
String c = new String("abc");
```

| Check | Result |
|----|----|
| `a == b` | `true` |
| `a == c` | `false` |
| `a.equals(c)` | `true` |

⚠️ == → reference  
⚠️ `equals()` → content

---

## 3️⃣ Length & Characters

| Method | Params | Returns | Example |
|----|----|----|----|
| `length()` | — | `int` | `"abc".length()` → `3` |
| `charAt()` | `int` | `char` | `"abc".charAt(1)` → `b` |

⚠️ Index starts at `0`

---

## 4️⃣ Substrings & Search

| Method | Params | Returns | Example |
|----|----|----|----|
| `substring()` | `int` | `String` | `"abcd".substring(2)` → `cd` |
| `substring()` | `int,int` | `String` | `"abcd".substring(1,3)` → `bc` |
| `indexOf()` | `char/String` | `int` | `"abc".indexOf('b')` → `1` |
| `lastIndexOf()` | `char/String` | `int` | `"abca".lastIndexOf('a')` → `3` |

⚠️ End index is **exclusive**

---

## 5️⃣ Comparison & Checks

| Method | Params | Returns | Example |
|----|----|----|----|
| `equals()` | `Object` | `boolean` | `"a".equals("a")` |
| `equalsIgnoreCase()` | `String` | `boolean` | `"A".equalsIgnoreCase("a")` |
| `compareTo()` | `String` | `int` | `"a".compareTo("b")` |
| `contains()` | `CharSequence` | `boolean` | `"abc".contains("b")` |
| `startsWith()` | `String` | `boolean` | `"abc".startsWith("a")` |
| `endsWith()` | `String` | `boolean` | `"abc".endsWith("c")` |

---

## 6️⃣ Modification (Creates NEW String)

| Method | Example | Result |
|----|----|----|
| `toUpperCase()` | `"ab".toUpperCase()` | `"AB"` |
| `toLowerCase()` | `"AB".toLowerCase()` | `"ab"` |
| `trim()` | `" a ".trim()` | `"a"` |
| `replace()` | `"aba".replace('a','x')` | `"xbx"` |
| `replaceAll()` | `"a1".replaceAll("\\d","")` | `"a"` |

⚠️ Original string **unchanged**

---

## 7️⃣ Splitting & Joining

### `split()`
```java
String s = "a,b,c";
String[] arr = s.split(",");
```

- Param → regex
- Returns → `String[]`

### `join()`
```java
String s = String.join("-", "a","b","c");
```

---

## 8️⃣ Conversion

| Method | Example |
|----|----|
| `valueOf()` | `String.valueOf(10)` → `"10"` |
| `toCharArray()` | `"abc".toCharArray()` |
| `getBytes()` | `"abc".getBytes()` |

---

## 9️⃣ Concatenation Rules (MCQ GOLD)

```java
String s = "a" + 1 + 2;   // "a12"
String s = 1 + 2 + "a";  // "3a"
```

- Left to right
- If first operand is `String` → concat

---

## 🔟 Immutability (CORE RULE)

```java
String s = "abc";
s.concat("d");
System.out.println(s);
```

**Output**
```
abc
```

Because:
- `concat()` returns new String
- Result not stored

---

## 1️⃣1️⃣ Performance Rule

❌ Bad
```java
String s = "";
for(...) s += "x";
```

✅ Good
```java
StringBuilder sb = new StringBuilder();
sb.append("x");
```

See: [[StringBuilder]]

---

## 1️⃣2️⃣ Common MCQ Traps

- `String` is immutable
- `length()` is a **method**
- `split()` uses **regex**
- ` ==` checks reference
- `new String()` always creates new object

---

## 🔗 Related Notes
- [[StringBuilder]]
- [[Wrapper Classes]]
- [[Object Class]]
- [[Memory Model]]

---

## 🔒 One-Line Recall

> **All String methods return a new String.  
> If you don’t store it, it’s lost.**

---
