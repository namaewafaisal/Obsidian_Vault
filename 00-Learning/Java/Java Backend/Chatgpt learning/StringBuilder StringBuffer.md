---
tags:
  - java
  - stringbuilder
  - stringbuffer
  - cheatsheet
  - core-java
date-time: 2026-02-01T11:45:00
status: revision
---

# [[StringBuilder]] / [[StringBuffer]] — Cheatsheet

Used for **mutable strings**.

---

## 1️⃣ Core Difference

| Feature | StringBuilder | StringBuffer |
|------|--------------|--------------|
| Thread-safe | ❌ No | ✅ Yes |
| Performance | Faster | Slower |
| Synchronization | ❌ | ✅ |

👉 Default choice: **StringBuilder**

---

## 2️⃣ Creation Syntax

```java
StringBuilder sb = new StringBuilder();
StringBuilder sb2 = new StringBuilder("abc");

StringBuffer sf = new StringBuffer();
StringBuffer sf2 = new StringBuffer("abc");
```

---

## 3️⃣ Common Methods (Same for Both)

| Method | Params | Returns | Example |
|------|------|--------|--------|
| `append()` | any | self | `sb.append("x")` |
| `insert()` | int, any | self | `sb.insert(1,"x")` |
| `delete()` | int, int | self | `sb.delete(1,3)` |
| `deleteCharAt()` | int | self | `sb.deleteCharAt(0)` |
| `reverse()` | — | self | `sb.reverse()` |
| `length()` | — | int | `sb.length()` |
| `charAt()` | int | char | `sb.charAt(0)` |
| `toString()` | — | String | `sb.toString()` |

⚠️ Methods modify the **same object**

---

## 4️⃣ Capacity (Occasional MCQ)

```java
StringBuilder sb = new StringBuilder();
sb.capacity();   // default: 16
```

- Capacity grows automatically
- Length ≠ capacity

---

## 5️⃣ When to Use

- Loop concatenation → StringBuilder
- Multi-threaded shared string → StringBuffer
- Otherwise → String

---

## 🔗 Related Notes
- [[String]]
- [[Memory Model]]

---

## 🔒 One-Line Recall

> **StringBuilder = fast, mutable  
> StringBuffer = thread-safe, mutable**

---
