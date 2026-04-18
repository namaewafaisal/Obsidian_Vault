Below is a **clean, recall-focused Obsidian note** for **Wrapper Classes (int, etc.)**.
This is sufficient for relearning and practical use.

---

## Wrapper Classes (Java)

### What they are

Wrapper classes convert **primitive types** into **objects**.

| Primitive | Wrapper     |
| --------- | ----------- |
| `int`     | `Integer`   |
| `double`  | `Double`    |
| `float`   | `Float`     |
| `char`    | `Character` |
| `boolean` | `Boolean`   |
| `long`    | `Long`      |
| `short`   | `Short`     |
| `byte`    | `Byte`      |

---

### Why they exist (very important)

Primitives:

* Are **not objects**
* Cannot be used in:

  * Collections (`List`, `Map`)
  * Generics
  * Frameworks (Spring, Hibernate, etc.)

Wrappers:

* Are **objects**
* Can be `null`
* Provide utility methods

---

### Autoboxing & Unboxing

Java automatically converts between primitives and wrappers.

```java
int a = 10;
Integer x = a;      // autoboxing

Integer y = 20;
int b = y;          // unboxing
```

⚠️ **Risk**: Unboxing `null` → `NullPointerException`

```java
Integer z = null;
int c = z; // ❌ NPE
```

---

### Common methods (you actually use these)

```java
Integer.parseInt("123")   // String → int
Integer.valueOf("123")   // String → Integer
Integer.toString(10)     // int → String
```

---

### Equality trap (important interview + bug source)

```java
Integer a = 100;
Integer b = 100;
System.out.println(a == b); // true (cached)

Integer x = 200;
Integer y = 200;
System.out.println(x == y); // false
```

Reason:

* Integers from **-128 to 127** are cached
* Use `.equals()` for object comparison

```java
x.equals(y); // ✅ correct
```

---

### When to use what

* Use **primitive (`int`)**:

  * Calculations
  * Performance-critical code

* Use **wrapper (`Integer`)**:

  * Collections
  * Database entities
  * Spring request/response
  * When `null` is meaningful

---

### Backend relevance (Spring Boot)

* DTOs & entities → **wrapper types**
* Request parameters → **wrapper types**
* Allows:

  * Optional values
  * Validation
  * Null handling

---

### One-line memory hook

> **Primitive = value only**
> **Wrapper = value + object behavior**

---

If you want, next we can do:

* `String vs StringBuilder vs StringBuffer`
* `equals() vs ==`
* `Optional`
* `Collections vs Arrays`

Send **next topic name only**.
Got it. Here is a **very short add-on Obsidian note**, focused **only on autoboxing / unboxing**, aligned with how Telusko explains it.

---

## Autoboxing & Unboxing (Java)

### Autoboxing

Automatic conversion of **primitive → wrapper**

```java
int a = 10;
Integer i = a;   // autoboxing
```

What really happens:

```java
Integer i = Integer.valueOf(a);
```

---

### Unboxing

Automatic conversion of **wrapper → primitive**

```java
Integer x = 20;
int y = x;   // unboxing
```

What really happens:

```java
int y = x.intValue();
```

---

### Why Java added this

Before Java 5:

* Manual conversion was required
* Collections could not work smoothly with primitives

Autoboxing makes:

* Collections
* Generics
* Framework code
  clean and readable

---

### ⚠️ Critical pitfall (remember this)

```java
Integer n = null;
int v = n;   // ❌ NullPointerException
```

Reason:

* Unboxing calls `intValue()` on `null`

---

### Performance note (backend relevance)

* Autoboxing creates objects
* Avoid in:

  * Tight loops
  * Heavy computations

Use primitives where possible.

---

### One-line recall

> **Autoboxing = compiler convenience, not magic**

---

If you want, say **next topic name** (just the topic).
Below is an **expanded but still compact Obsidian note** covering the **other important Wrapper class methods** you should remember.
This is **relearning-grade**, not overkill.

---

## Wrapper Class Methods (Java)

### 1. Parsing methods (String → primitive / wrapper)

Used a lot in backend (request params, config values).

```java
int a = Integer.parseInt("123");        // String → int
Integer b = Integer.valueOf("123");     // String → Integer
```

Notes:

* `parseXxx()` → **primitive**
* `valueOf()` → **wrapper object**
* `valueOf()` uses caching (more efficient)

---

### 2. Type conversion methods

Convert wrapper → other primitive types.

```java
Integer x = 100;

x.intValue();
x.doubleValue();
x.longValue();
```

Used internally during **unboxing**.

---

### 3. Comparison methods

Safer than `==`.

```java
Integer a = 10;
Integer b = 20;

a.compareTo(b);   // returns -1, 0, or 1
```

Rules:

* `< 0` → a < b
* `0` → equal
* `> 0` → a > b

Used in sorting, TreeMap, TreeSet.

---

### 4. equals()

Always use this for wrapper comparison.

```java
Integer a = 200;
Integer b = 200;

a.equals(b);  // true
```

Never rely on `==` for wrappers.

---

### 5. toString()

Convert value to String.

```java
Integer x = 50;
String s = x.toString();
```

Also:

```java
String s = Integer.toString(50);
```

---

### 6. Constants (useful sometimes)

```java
Integer.MAX_VALUE;
Integer.MIN_VALUE;
```

Example:

```java
if (value > Integer.MAX_VALUE) { ... }
```

---

### 7. isXxx methods (Boolean)

```java
Boolean.parseBoolean("true");  // true
Boolean.TRUE;
Boolean.FALSE;
```

---

### Backend-specific reminder

* **Entities / DTOs** → wrappers (`Integer`)
* **Business logic / loops** → primitives (`int`)
* **Request params** → wrappers (can be `null`)

---

### One-line recall

> `parse` → primitive
> `valueOf` → object
> `equals` → correctness
> `compareTo` → ordering

---

Send the **next topic name only** when ready.
