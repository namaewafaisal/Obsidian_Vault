# Conditional Statements Index

Conditional statements control the flow of execution, allowing the program to perform different actions based on different conditions.

|Control Structure|Purpose|Key Symbols|Related Concepts|
|:--|:--|:--|:--|
|**If-Else**|Executes a block of code if a condition is `true`, and an optional alternate block if `false`.|`if`, `else`, `else if`|Conditions must evaluate to a Boolean result.|
|**Switch**|Executes code based on matching an expression's value against discrete cases.|`switch`, `case`, `break`, `default`|Supported types typically include integral types and String (implied by use cases like matching day numbers).|
|**Ternary Operator**|A single-line shortcut for simple conditional assignment.|`? :`|Often replaces short `if-else` structures.|

---

#### Atomic Note: The `if-else` Structure

##### Definition and Usage

The `if` statement evaluates a condition, and if the condition is **`true`**, a specific block of statements is executed. An optional `else` block can be used to execute statements when the `if` condition is `false`.

|Key Detail|Description|Citation|
|:--|:--|:--|
|**Condition Type**|The expression within the `if` statement's parentheses must always evaluate to a **Boolean value** (`true` or `false`).||
|**`else if` Chains**|Used when evaluating multiple conditions sequentially. If one `if` or `else if` condition is met, the subsequent `else if` and `else` blocks are skipped.||
|**Blocks and Brackets**|If an `if` or `else` statement controls **only a single subsequent statement**, curly brackets (`{}`) are technically optional. If it controls **multiple statements**, curly brackets are mandatory to define the block.||
|**Indentation**|Java does not rely on indentation to define code blocks (unlike Python); brackets are used instead.||

---

#### Atomic Note: The `switch` Statement

##### Definition and Usage

The `switch` statement is used to execute specific code based on matching a variable's value against multiple discrete `case` values. This structure is preferred when checking for equality against constants, such as matching a number to a day of the week.

|Key Keyword|Behavior|Citation|
|:--|:--|:--|
|**Matching**|The `switch` statement attempts to match the expression value with a `case` value.||
|**Flow/Fall-through**|Once a `case` matches, the code executes from that point onward, **executing all subsequent statements** in the `switch` block unless stopped.||
|**`break`**|The `break` keyword is essential to exit the `switch` block immediately after the desired `case` code has run, preventing unintended "fall-through" into other cases.||
|**`default`**|The `default` case is executed if **none of the other `case` values match** the expression.||
|**Enum Support**|The `switch` statement supports working with `enum` types. When using an `enum` in a `switch`, you use the constant name directly in the `case` without needing to reference the `enum` type itself (e.g., `case RUNNING` instead of `case Status.RUNNING`).||

---

#### Atomic Note: The Ternary Operator

##### Definition and Syntax

The ternary operator (`? :`) is a concise way to replace short `if-else` structures, especially when the goal is to assign a value based on a condition.

|Element|Syntax and Purpose|Citation|
|:--|:--|:--|
|**Structure**|`condition ? value_if_true : value_if_false`.||
|**Evaluation**|1. The `condition` is evaluated first.||
|**True Result**|2. If `true`, the expression returns the `value_if_true` (the value after the question mark `?`).||
|**False Result**|3. If `false`, the expression returns the `value_if_false` (the value after the colon `:`).||
|**Use Case**|Commonly used for checking simple remainder results (e.g., even/odd) and assigning a status or value accordingly.||

---

#### Atomic Note: Relational and Logical Operators (Conditions)

Conditional statements rely entirely on operators that perform comparisons or combine Boolean results.

##### Relational Operators (Comparison)

Relational operators **compare two values** and always return a **Boolean result** (`true` or `false`).

|Operator|Purpose|Detail|Citation|
|:--|:--|:--|:--|
|`==`|Equal to|Compares if two values are exactly equal. Must use **double equals**; a single `=` is the assignment operator.||
|`!=`|Not equal to|Checks if two values are different.||
|`>` or `<`|Greater than or Less than|Used for simple magnitude comparison.||
|`>=` or `<=`|Greater/Less than or Equal to|Used for combined comparisons.||

##### Logical Operators (Combining Conditions)

Logical operators are used to **combine multiple Boolean results** (conditions) to produce a single Boolean outcome.

|Operator|Type|Behavior/Detail|Citation|
|:--|:--|:--|:--|
|`&&`|**Short Circuit AND** (Preferred)|Returns `true` only if both conditions are `true`. **Optimization:** If the first condition is `false`, Java immediately stops checking the second condition.||
|`|`|**Short Circuit OR** (Preferred)|Returns `true` if at least one condition is `true`. **Optimization:** If the first condition is `true`, Java immediately stops checking the remaining conditions.|
|`&` / `|`|Standard Logical AND/OR|These operators perform the comparison without short-circuiting; they **always evaluate both sides**.|
|`!`|Logical NOT|Reverses the Boolean result (`true` becomes `false`, and vice versa).||

---

#### Atomic Note: Arithmetic Details Impacting Conditions (Requested Detail)

Specific nuances in arithmetic operations often influence resulting values used in conditional logic:

|Operation|Behavior Detail|Citation|
|:--|:--|:--|
|**Integer Division (`/`)**|When dividing two integer values (e.g., `7 / 5`), the result is always the **quotient** (e.g., 1), with any remainder discarded. This result is an integer, regardless of the variable type used to store it.||
|**Modulus (`%`)**|The modulus operator returns the **remainder** of a division operation (e.g., `7 % 5` results in 2).||

---

#### Atomic Note: Unary Increment/Decrement Behavior (Requested Detail)

The behavior of unary operators (`++`, `--`) is critical when used within a comparison or assignment statement:

|Operator Type|Syntax/Placement|Behavior When Used in an Expression (e.g., Assignment)|Citation|
|:--|:--|:--|:--|
|**Post-Increment/Decrement**|Operator placed _after_ the variable (e.g., `num++`).|**The original value is used first** in the expression, and _then_ the variable is incremented/decremented.||
|**Pre-Increment/Decrement**|Operator placed _before_ the variable (e.g., `++num`).|**The variable is incremented/decremented first**, and _then_ the new, resulting value is used in the expression.||
|**Key Distinction**|Both versions perform the increment/decrement, but the difference lies in the value returned/used in the larger statement.||
Yes — your **Conditional Statements** note is **nearly complete and very well-balanced**, but to make it _truly comprehensive_ (like a full reference-quality Obsidian note), it could benefit from **a few optional additions**.

Here’s a detailed breakdown 👇

---

### ✅ Already Perfectly Covered

You’ve _already_ included everything that makes this note solid:

- **All main control structures** (`if-else`, `switch`, `ternary`)
    
- **Operator dependency** (relational, logical, arithmetic)
    
- **Behavioral details** (flow, short-circuiting, fall-through, increment nuances)
    
- **Boolean condition rules** (required `true`/`false` expressions)
    
- **Formatting and block rules** (indentation, brackets)
    
- **Enum & String switch support**
    
- **Practical use cases** (even/odd, conditional assignment, etc.)
    

That covers **100%** of what’s needed for conceptual + technical understanding.

---

### ⚙️ Optional Additions (for full mastery / completeness)

If you want this note to feel _100% exhaustive_ — like something from an advanced reference — add these **optional atomic notes** at the end:

#### 1. **Nested Conditional Statements**

> Conditionals inside other conditionals (common in decision trees or multi-level logic).

|Key Point|Description|
|:--|:--|
|**Definition**|An `if` or `switch` placed inside another conditional block.|
|**Use Case**|Used for multi-step decisions (e.g., check age, then role).|
|**Readability Tip**|Deeply nested structures can reduce clarity — prefer `else if` or logical combinations.|

---

#### 2. **Switch Expression (Java 12+)**

> Modern enhancement of the `switch` statement, introduced to make it an **expression** that returns a value.

|Feature|Description|
|:--|:--|
|**Syntax**|`String result = switch (value) { case 1 -> "One"; default -> "Unknown"; };`|
|**No Fall-through**|Each arrow `->` acts like a `break`; no accidental fall-through.|
|**Value Return**|Can return directly to a variable.|
|**`yield` Keyword**|Used when returning from multi-line case blocks.|

_(This helps your notes stay relevant to modern Java ≥ 12.)_

---

#### 3. **Best Practices / Common Pitfalls**

> Helps you recall what to avoid during coding tests or debugging.

|Practice|Why|
|:--|:--|
|Always use `break` in old-style `switch`.|Avoids unintended execution of later cases.|
|Prefer `switch expressions` in newer Java.|Cleaner and safer syntax.|
|Avoid assignment in conditions (e.g., `if (x = 5)`).|It compiles, but leads to logic errors — use `==`.|
|Use parentheses for complex logical conditions.|Improves readability and avoids precedence confusion.|

---

#### 4. **Operator Precedence Reminder**

> A small link to your **Operator Precedence** note to show how combined conditions evaluate.

| Reference                                                 | Purpose            |
| :-------------------------------------------------------- | :----------------- |
| [Operator Precedence](Operators/Operator%20Precedence.md) | To recall how `&&` |

---
