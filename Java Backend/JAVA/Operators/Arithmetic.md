# Arithmetic Operators

### Definition and Usage

Arithmetic operators are used to perform mathematical calculations.

| Operator | Operation           | Notes                                                | Citation |
| :------- | :------------------ | :--------------------------------------------------- | :------- |
| `+`      | Addition            | Adds two numbers. Also to concatenate Strings        |          |
| `-`      | Subtraction         | Subtracts one number from another.                   |          |
| `*`      | Multiplication      | Multiplies two numbers.                              |          |
| `/`      | Division            | **Returns the quotient** when dividing two integers. |          |
| `%`      | Modulus (Remainder) | **Returns the remainder** of a division operation.   |          |

**Example:**

- To get the quotient of 7 divided by 5, use `7 / 5` (Result: 1).
- To get the remainder of 7 divided by 5, use `7 % 5` (Result: 2).

### Notes
- Division between **two integers** always yields an **integer result** (fraction discarded).  
If one operand is a floating-point, result becomes `double`.
```java
System.out.println(5 / 2);   // 2
System.out.println(5 / 2.0); // 2.5

System.out.println("Hi " + 56); // Hi 56
```

