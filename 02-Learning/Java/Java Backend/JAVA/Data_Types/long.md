# `long` Data Type in Java

The **`long`** data type is used for very large whole numbers that exceed the `int` range.

---

### Overview

- **Size:** 8 bytes (64 bits)
- **Range:** -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
- **Default Value:** 0L
- **Type Category:** Primitive
- **When to Use:**
    - For storing large integer values like population counts, timestamps, etc.

---

### Example

```java
long distance = 12345678900L;
System.out.println(distance);
```

---

### Notes

- The suffix **L** (uppercase) or **l** (lowercase) is required for literals.    
- Automatically promoted from `int` when the value exceeds `int` range.
- Often used in time-related calculations (`System.currentTimeMillis()`).    

---

**Related Notes:**

- [Data Types in Java](Data%20Types%20in%20Java.md)    
- Type Casting