# `short` Data Type in Java

The **`short`** data type is a 16-bit signed integer type used for small numerical values.

---

### Overview

- **Size:** 2 bytes (16 bits)
- **Range:** -32,768 to 32,767
- **Default Value:** 0
- **Type Category:** Primitive
- **When to Use:**
    - When memory saving is important, and the range of values fits within limits.
    - Useful for large arrays on memory-constrained systems.

---

### Example

```java
short temperature = 250;
System.out.println(temperature);
```

---

### Notes

- Arithmetic operations automatically promote `short` to `int`.    
- Rarely used in modern Java because `int` is more efficient on most systems.

---

**Related Notes:**

- [Data Types in Java](Data%20Types%20in%20Java.md)    
- Type Casting