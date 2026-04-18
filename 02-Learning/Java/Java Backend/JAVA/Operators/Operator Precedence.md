# **Operator Precedence in Java**

### Definition

**Operator precedence** determines the **order in which Java evaluates operators** in an expression.  
Operators with **higher precedence** are evaluated **before** those with lower precedence.  
When operators have the **same precedence**, evaluation follows **associativity** (usually left to right).

---

### 🧩 **1. Highest → Lowest Precedence Table**

| Precedence | Operator Type             | Symbols                                | Associativity | Description                                     |
| ---------- | ------------------------- | -------------------------------------- | ------------- | ----------------------------------------------- |
| 1          | **Postfix**               | `expr++`, `expr--`                     | Left to Right | Post-increment/decrement                        |
| 2          | **Unary**                 | `++expr`, `--expr`, `+`, `-`, `~`, `!` | Right to Left | Unary increment, decrement, logical/bitwise NOT |
| 3          | **Multiplicative**        | `*`, `/`, `%`                          | Left to Right | Multiplication, division, modulus               |
| 4          | **Additive**              | `+`, `-`                               | Left to Right | Addition, subtraction, string concatenation     |
| 5          | **Shift**                 | `<<`, `>>`, `>>>`                      | Left to Right | Bitwise shifts                                  |
| 6          | **Relational**            | `<`, `<=`, `>`, `>=`, `instanceof`     | Left to Right | Comparison and type checking                    |
| 7          | **Equality**              | `==`, `!=`                             | Left to Right | Equality and inequality                         |
| 8          | **Bitwise AND**           | `&`                                    | Left to Right | Bitwise AND                                     |
| 9          | **Bitwise XOR**           | `^`                                    | Left to Right | Bitwise XOR                                     |
| 10         | **Bitwise OR**            | `                                      | `             | Left to Right                                   |
| 11         | **Logical AND**           | `&&`                                   | Left to Right | Short-circuit AND                               |
| 12         | **Logical OR**            | `                                      |               | `                                               |
| 13         | **Ternary (Conditional)** | `? :`                                  | Right to Left | Conditional evaluation                          |
| 14         | **Assignment**            | `=`, `+=`, `-=`, `*=`, `/=`, `%=`      | Right to Left | Assign or modify-and-assign                     |
| 15         | **Comma** _(rare)_        | `,`                                    | Left to Right | Evaluates left to right                         |

---
### 2. Example of Precedence in Action
```java
int result = 10 + 5 * 2; 
// Multiplication (*) has higher precedence than addition (+)
// So: result = 10 + (5 * 2) = 20
int result = (10 + 5) * 2;
// Parentheses override precedence
// So: result = 15 * 2 = 30
```

---

### 🧭 **3. Associativity Rules**

- **Left to Right:** Most operators (e.g., `+`, `-`, `*`, `/`, `%`, `<`, `>`, `==`, etc.)
- **Right to Left:** Unary operators, assignment, and ternary operators.

**Example:** 
```java
int a = b = c = 10; // evaluated as: a = (b = (c = 10))
```

---
### **4. Parentheses for Clarity**

Always use **parentheses** to make expressions easier to read and to **avoid ambiguity**.

**Example:**
```java
System.out.println((a + b) * (c - d));
```
Even if you know precedence rules, parentheses help maintain **code clarity** and **prevent logic errors**.

---