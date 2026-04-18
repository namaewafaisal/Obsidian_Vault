# Bitwise Operators

### Definition and Usage

Bitwise operators work on the **binary representation** of numbers (bit level).  
They are mainly used in low-level programming, masks, and optimizations.

|Operator|Name|Example|Description|
|---|---|---|---|
|`&`|Bitwise AND|`a & b`|Sets each bit to 1 **only if both bits are 1**.|
|`|`|Bitwise OR|`a|
|`^`|Bitwise XOR|`a ^ b`|Sets each bit to 1 **if bits are different**.|
|`~`|Bitwise Complement|`~a`|Flips all bits (1 → 0, 0 → 1).|
|`<<`|Left Shift|`a << 2`|Shifts bits **left** by given positions (adds zeros on right).|
|`>>`|Right Shift|`a >> 2`|Shifts bits **right**, keeping sign bit.|
|`>>>`|Unsigned Right Shift|`a >>> 2`|Shifts right and fills **0** on the left (ignores sign bit).|

---

### Example

```java
int a = 5;   // Binary: 0101
int b = 3;   // Binary: 0011

System.out.println(a & b);  // 1  -> 0001
System.out.println(a | b);  // 7  -> 0111
System.out.println(a ^ b);  // 6  -> 0110
System.out.println(~a);     // -6 (bitwise complement)

System.out.println(a << 1); // 10 -> 1010
System.out.println(a >> 1); // 2  -> 0010
```

### Notes

- **`>>`** keeps the sign bit (for negative numbers).
- **`>>>`** always shifts in zeros — used for unsigned bit shifting.
- Commonly used in:
    - Permissions & masks
    - Low-level arithmetic
    - Cryptography & compression