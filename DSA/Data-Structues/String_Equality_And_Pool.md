---
topic: string_equality
category: linear
concept: identity_vs_content
memory: pooled
mutability: immutable

priority: high
difficulty: basic
status: foundation

depends_on:
  - string

used_in:
  - condition_checks
  - hashing_keys
  - authentication_logic

tags:
  - dsa
  - string
  - equality
  - memory
---

# String Equality — ` ==` vs `.equals()` & String Pool

## 0. The real problem this concept solves

Two strings can **look identical** but behave differently in comparisons.
Misunderstanding this leads to **logic bugs**, not compile errors.

**One-line anchor:**  
> String equality depends on whether you compare identity or content.

---

## 1. What ` ==` actually checks

```java
a == b
```

Checks:
- whether `a` and `b` refer to the **same object in memory**

It does **not** compare characters.

---

## 2. What `.equals()` checks for String

```java
a.equals(b)
```

Checks:
- whether both strings contain the **same sequence of characters**

For `String`, `.equals()` is overridden to perform **content comparison**.

---

## 3. The String Pool (core reason for confusion)

String literals are stored in a **String Pool**.

```java
String a = "hello";
String b = "hello";
```

Memory:
```
a ─┐
   ├──> "hello" (single pooled object)
b ─┘
```

So:
```java
a == b      // true
a.equals(b) // true
```

Pooling is safe only because Strings are immutable.

---

## 4. `new String()` bypasses the pool

```java
String a = "hello";
String b = new String("hello");
```

Memory:
```
a ──> pooled "hello"
b ──> heap "hello"
```

So:
```java
a == b      // false
a.equals(b) // true
```

Rule:
> `new String()` almost always wastes memory.

---

## 5. Compile-time vs runtime concatenation

### Compile-time (pooled)
```java
String s = "he" + "llo";
```

Becomes:
```
"hello" (pooled)
```

So:
```java
s == "hello" // true
```

---

### Runtime (not pooled)
```java
String x = "he";
String y = "llo";
String z = x + y;
```

Creates a new object:
```java
z == "hello" // false
```

---

## 6. `intern()` (manual pooling)

```java
String z = (x + y).intern();
```

- checks pool
- returns pooled reference if present

Use only when:
- canonical strings are required
- memory is carefully controlled

---

## 7. Correct comparison rule (non-negotiable)

```java
"yes".equals(input);
```

Never:
```java
input == "yes";
```

Also avoids `NullPointerException`.

---

## 8. Final mental model

> ` ==` compares memory identity, `.equals()` compares meaning.

---

## Linked notes

- [[Strings_Fundamentals]]
- [[StringBuilder_And_StringBuffer]]
- [[Common_String_Bugs]]
