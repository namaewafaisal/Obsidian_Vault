---
topic: string_bugs
category: linear
error_type: logical_and_performance
mutability: immutable_core
safety: manual

priority: high
difficulty: basic
status: foundation

depends_on:
  - string
  - string_builder

tags:
  - dsa
  - string
  - bugs
---
# Common String Bugs — Logic & Performance Traps

## 0. The real problem this concept solves

Most String bugs:
- compile successfully
- pass small tests
- fail under load or edge cases

**One-line anchor:**  
> String bugs come from ignoring immutability and allocation cost.

---

## 1. Using `+` inside loops

```java
for (int i = 0; i < n; i++) {
    s = s + i;
}
```

Creates:
- many temporary objects
- quadratic time complexity

Correct:
```java
StringBuilder sb = new StringBuilder();
```

---

## 2. Using ` ==` instead of `.equals()`

```java
if (input == "ok") { }
```

Works only by accident due to pooling.

Correct:
```java
"ok".equals(input);
```

---

## 3. Null-unsafe comparisons

```java
input.equals("yes"); // ❌ if input is null
```

Correct:
```java
"yes".equals(input);
```

---

## 4. Reusing StringBuilder incorrectly

```java
sb.append(x);
results.add(sb.toString());
```

Leads to accumulated content.

Fix:
- reset
- or create a new builder

---

## 5. Assuming substring is cheap

Modern Java:
- substring **copies characters**
- cost is linear in substring length

Avoid in hot loops.

---

## 6. Overusing `intern()`

- pool is global
- memory may not be reclaimed

Use only with strong justification.

---

## 7. Final mental model

> Strings are safe but expensive; builders are fast but require discipline.

---

## Linked notes

- [[Strings_Fundamentals]]
- [[String_Equality_And_Pool]]
- [[Substring_Memory_Costs]]
