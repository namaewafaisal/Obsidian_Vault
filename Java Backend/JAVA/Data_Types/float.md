# `float` Data Type in Java

The **`float`** data type represents single-precision floating-point numbers (decimal values).

---

### Overview

- **Size:** 4 bytes (32 bits)
    
- **Range:** Approximately ±3.40282347E+38 (6–7 decimal digits precision)
    
- **Default Value:** 0.0f
    
- **Type Category:** Primitive
    
- **When to Use:**
    
    - For fractional numbers where precision isn’t critical (like graphics or approximations).
        

---

### Example

```java
float price = 19.99f;
System.out.println(price);
```

---

### Notes

- The literal must end with **f** or **F**, otherwise Java treats it as a `double`.
    
- Less precise than `double`.
    
- Use for memory efficiency when high precision isn’t required.
    

---

**Related Notes:**

- Data Types in Java
    
- Type Casting