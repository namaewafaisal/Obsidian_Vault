# Unary Operators

### Definition and Usage

Unary operators operate on **a single operand** to perform actions like negation, increment, or logical inversion.

|Operator|Purpose|Example|Description|
|---|---|---|---|
|`+`|Unary plus|`+num`|Indicates a positive value (rarely used explicitly).|
|`-`|Unary minus|`-num`|Negates the value (changes sign).|
|`++`|Increment|`++num` or `num++`|Increases the value by 1.|
|`--`|Decrement|`--num` or `num--`|Decreases the value by 1.|
|`!`|Logical NOT|`!flag`|Reverses a Boolean value (`true` → `false`, `false` → `true`).|
|`~`|Bitwise Complement|`~a`|Flips all bits (e.g., `~5` gives `-6`).|
### Example

```java
int a = 5;
System.out.println(-a);   // -5

boolean flag = true;
System.out.println(!flag); // false

int x = 10;
System.out.println(++x);   // 11 (pre-increment)
System.out.println(x--);   // 11 (post-decrement, then becomes 10)
```