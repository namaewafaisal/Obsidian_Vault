# Print Methods in Java

There are three main print methods in `System.out`:

---

### 1. `print()`
Prints the value without adding a new line.
```java
System.out.print("Hello " + 4);
```
**Output** : "Hello 4"
### 2. `println()`
Prints the value and adds a new line (`\n`) at the end automatically.
```java
System.out.println("Hello " + 4);
```
**Output** : "Hello 4"
### 3. `printf()`

Prints formatted text using placeholders (format specifiers).  
For example, `%d` for integers, `%s` for strings, and `%f` for floating numbers.
```java
System.out.printf("Hello %d", 4);
```

**Related Notes:**

- [Variables](Variables.md)
- [What is Program](What%20is%20Program.md)