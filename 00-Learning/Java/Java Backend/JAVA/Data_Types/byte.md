# `byte` Data Type in Java

The **`byte`** data type is used to store very small whole numbers.  
It’s mainly used to save memory in large arrays where the memory saving is important.

---

### Overview

- **Size:** 1 byte (8 bits)
- **Range:** -128 to 127 (-2<sup>7</sup> to 2<sup>7</sup> – 1)
- **Default Value:** 0
- **Type Category:** Primitive
- **When to Use:**
    - To store small integer values.
    - Useful when dealing with data streams, files, or network transfers.

---

### Example

```java
byte age = 25;
System.out.println(age);
```

---

### Notes

- Saves memory compared to `int` or `short`.
- Can be used in place of `int` if the value range is small.
- Type casting is often required when performing arithmetic with bytes because they are promoted to `int`.

---

**Related Notes:**

- [Data Types in Java](Data%20Types%20in%20Java.md)
- Type Casting