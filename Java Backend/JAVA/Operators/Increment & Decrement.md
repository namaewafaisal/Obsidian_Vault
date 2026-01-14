# Increment and Decrement Operators (Pre vs Post)

The increment (`++`) and decrement (`--`) operators are shortcuts used to add or subtract 1 from a variable.

### Post-Increment/Decrement (`num++` or `num--`)

- **Syntax:** The operator is placed _after_ the variable (e.g., `num1++`).
- **Behavior:** When used in a statement where the value is being _fetched or assigned_ (e.g., `result = num++`), **the original value is used first**, and then the variable is incremented.

### Pre-Increment/Decrement (`++num` or `--num`)

- **Syntax:** The operator is placed _before_ the variable (e.g., `++num1`).
- **Behavior:** When used in a statement where the value is being _fetched or assigned_ (e.g., `result = ++num`), **the variable is incremented first**, and then the new, incremented value is used.

**Key Distinction:** Both operators perform the increment/decrement regardless. The difference lies in the value they return when part of a larger expression (like an assignment statement).

---

### Example
```java
int a = 5;
int b = 10;

if (a > 0 && b++ > 5) { } 
System.out.println(b); // b = 11

if (a < 0 && b++ > 5) { } 
System.out.println(b); // b = 11 (b not incremented, short-circuited)
```

