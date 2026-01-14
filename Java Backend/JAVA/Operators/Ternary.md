# Ternary Operator

### Definition and Syntax

The ternary operator (`? :`) is a shortcut used to write a conditional statement in a single line, often replacing short `if-else` structures where a value is being assigned based on a condition.

- **Syntax:** `condition ? value_if_true : value_if_false`.

### Usage

The process is:

1. Evaluate the `condition`.
2. If the condition is `true`, the expression returns the value listed after the question mark (`?`).
3. If the condition is `false`, the expression returns the value listed after the colon (`:`).

**Example Use Case:** Checking if a number is even or odd, and assigning a status or value based on the remainder.

```java
// Assign 10 if even (n % 2 == 0), otherwise assign 20.
result = (n % 2 == 0) ? 10 : 20;
```

This single line replaces multiple lines of `if/else` code.