# `int` Data Type in Java

The **`int`** data type is one of Java’s most commonly used types for storing whole numbers.

---

### Overview

- **Size:** 4 bytes (32 bits)
- **Range:** –2,147,483,648 to 2,147,483,647 (`-2³¹` to `2³¹ – 1`)
- **Default Value:** 0
- **Type Category:** Primitive
- **When to Use:** For most integer calculations where values fit within the 32-bit range.

---

### Example

```java
int age = 21;
System.out.println(age);
```

---

### Notes

- Use `long` if your value exceeds `int` range.
- `int` can be automatically promoted to `long` or `float` in expressions.
- `_` can be added **between** int values to enhance readability `100_00_001`. 
- Casting may be needed when converting from larger to smaller types:

```java
long big = 100000L;
int small = (int) big; // explicit cast
```

---

**Related Notes:**

- [Data Types in Java](Data%20Types%20in%20Java.md)
- Type Casting