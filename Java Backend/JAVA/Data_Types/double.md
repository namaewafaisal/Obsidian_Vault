# `double` Data Type in Java

The **`double`** data type is the default choice for decimal (floating-point) values in Java.

---

### Overview

- **Size:** 8 bytes (64 bits)
    
- **Range:** Approximately ±1.79769313486231570E+308 (15 decimal digits precision)
    
- **Default Value:** 0.0d
    
- **Type Category:** Primitive
    
- **When to Use:**
    
    - For mathematical, scientific, or financial calculations where precision matters.
        

---

### Example

```java
double pi = 3.1415926535;
System.out.println(pi);
```

---

### Notes

- More precise than `float`.
- By default Java takes double if any calculation involves a decimal point.
    
- Floating-point arithmetic may lead to rounding errors (use `BigDecimal` for exact calculations).
    

---

**Related Notes:**

- Data Types in Java
    
- Type Casting