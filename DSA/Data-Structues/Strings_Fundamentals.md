---
topic: string
category: linear
structure: contiguous
data: characters
mutability: immutable
access: indexed

priority: high
difficulty: basic
status: foundation

used_in:
  - hashing_keys
  - parsing
  - text_processing
  - identifiers

tags:
  - dsa
  - string
  - immutable
---

# Strings — Fundamentals (Immutability & Memory)

## 0. The real problem this concept solves

Programs need to represent **textual data** safely:
- identifiers
- paths
- user input
- keys in data structures

This data must be:
- shareable
- predictable
- safe across threads

**One-line anchor:**  
> Strings represent text with strong safety guarantees.

---

## 1. What a String really is (plain English)

A `String` is an **object that wraps a character array** whose contents are **guaranteed to never change** after creation.

This is not a convention.  
It is a **language-level guarantee**.

**One-line anchor:**  
> A String is read-only text stored in contiguous memory.

---

## 2. Internal mental model (simplified)

Conceptually:

```java
class String {
    private final char[] value;
}
```

Key properties:
- characters live in heap
- array reference is `final`
- no API allows mutation

---

## 3. What immutability actually means

Immutability means:

- ❌ You cannot change characters **inside** a String
- ✅ You can reassign the variable to a **different String**

```java
String s = "hello";
s = "world";        // allowed (new object)
```

But this is impossible:
```java
s.charAt(0) = 'H';  // ❌ cannot mutate
```

---

## 4. Why Java enforces immutability (non-negotiable)

### Reason 1 — Safe sharing
Multiple references can point to the same String without risk.

### Reason 2 — Hash stability
Strings are used as keys in hash-based structures.
Keys must not change.

### Reason 3 — Thread safety
Strings can be shared across threads with no synchronization.

### Reason 4 — Security
Critical values (paths, class names, credentials) cannot be altered after validation.

---

## 5. String creation (two distinct paths)

### A. String literal
```java
String a = "hello";
```

- stored in **String Pool**
- reused if identical literal exists

---

### B. Explicit object creation
```java
String b = new String("hello");
```

- forces a new heap object
- bypasses pooling
- almost never needed

Both create **immutable objects**.

---

## 6. Strings vs char arrays (important distinction)

| Aspect | String | char[] |
|------|-------|--------|
| Mutability | ❌ | ✅ |
| Safe to share | ✅ | ❌ |
| Pooling | ✅ | ❌ |
| Modification | Replacement | In-place |

Rule:
> Use `String` for meaning, `char[]` for mechanics.

---

## 7. Cost of immutability (where problems start)

```java
String s = "";
s = s + "a";
s = s + "b";
```

Each `+`:
- creates a new object
- copies characters
- discards old object

This is correct behavior, but **not cheap**.

---

## 8. Indexing and length

```java
s.charAt(i);   // O(1)
s.length();    // O(1)
```

Why:
- Strings store length explicitly
- underlying array is contiguous

---

## 9. What Strings are bad at

Strings are bad at:
- incremental building
- frequent modification
- loops with concatenation

This is **by design**, not a flaw.

---

## 10. Final mental model (lock this)

> Strings are immutable, shareable text objects optimized for safety, not mutation.

---

## Linked notes

- [[String_Equality_And_Pool]]
- [[StringBuilder_And_StringBuffer]]
- [[Common_String_Bugs]]
- [[Substring_Memory_Costs]]
