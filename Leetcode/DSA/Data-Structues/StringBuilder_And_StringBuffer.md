---
topic: string_builder
category: linear
structure: growable_array
mutability: mutable
thread_safety: none_by_default

priority: high
difficulty: basic
status: foundation

depends_on:
  - string

used_in:
  - string_construction
  - loops
  - formatting

tags:
  - dsa
  - string
  - stringbuilder
---

# StringBuilder & StringBuffer — Controlled Mutability

## 0. The real problem this concept solves

Strings cannot be modified efficiently.
Building text step by step with `String` causes **excessive object creation**.

**One-line anchor:**  
> StringBuilder exists to build strings efficiently and explicitly.

---

## 1. What StringBuilder really is

Conceptually:

```java
class StringBuilder {
    char[] value;
    int count;
}
```

- mutable character storage
- logical length tracked separately
- internal array can grow

---

## 2. Append operation (what actually happens)

```java
sb.append('a');
```

Steps:
1. ensure capacity
2. write character into array
3. increment count

No new String created.

---

## 3. Capacity vs length

```java
sb.length();   // used characters
sb.capacity(); // allocated storage
```

Same idea as array:
- capacity ≠ logical size

---

## 4. Resizing (amortized cost)

When capacity is exceeded:
- new array allocated
- old characters copied

Growth strategy:
```
newCapacity ≈ old * 2 + 2
```

Append is **amortized O(1)**.

---

## 5. Converting to String

```java
String result = sb.toString();
```

Creates:
- a new immutable String
- with copied characters

This is the **handoff point** from mutable → immutable.

---

## 6. Why StringBuilder is not thread-safe

- no synchronization
- faster by default
- unsafe if shared across threads

---

## 7. StringBuffer (rarely needed)

- same API as StringBuilder
- methods are synchronized
- thread-safe but slower

Rule:
> Prefer StringBuilder unless shared mutation is required.

---

## 8. Common misuse patterns

- Reusing one StringBuilder across unrelated logic
- Returning StringBuilder from public APIs

Always return `String`.

---

## 9. Final mental model

> StringBuilder is a growable char array with a cursor.

---

## Linked notes

- [[Strings_Fundamentals]]
- [[String_Equality_And_Pool]]
- [[Common_String_Bugs]]
