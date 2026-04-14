- `Integer` has a cache **[-128 to 127]**
- `a = 100`, `b = 100` → reused from cache → **same object**
- `x = 200`, `y = 200` → outside cache → **new objects**
- ✅ Cached Integers are **still objects**, but **reused references**
- ❌ No unboxing here
- ` ==` compares **reference**, unless one side is primitive
- `Integer == Integer` → reference compare
- `Integer == int` → unboxing happens

```java
int b = Integer.MAX_VALUE; // 2147483647 binary = 011...1 (MSB is sign bit)
System.out.println(b + 1); // -2147483648 (overflow, wraps around)
```

- No automatic conversion to Long
- Java uses 2’s complement → overflow wraps around silently
- `Math.addExact(a, 1); // throws ArithmeticException`

---

### String
- `String s1 = "Hello"` → uses a special heap area called **String Pool**
- Creates a new object in String Pool (if not already present)
- Strings are reused across entire program if they are same
- `String s2 = "Hello"` → checks pool → reuse if exists
- `String s3 = new String("Hello")` → creates a new object in **heap**, not pool

- `String a = "hello" + "world"` → compile-time → converted to `"helloworld"` → stored in pool

⚠️ Correction:
- `String b = a + "world"` ❌ (your version is slightly off)
- Correct concept:
  `String b = "hello";`
  `String c = b + "world"` → runtime → new object in heap (NOT pool)

---

## 🔍 Case 2

```java
String b = "hello";
String c = b + "world";
String d = (b + "world").intern();
```

### What actually happens:

- Runtime concatenation:
```java
new StringBuilder()
    .append(b)
    .append("world")
    .toString();
```

### Result:
- `"hello"` → pool
- `"world"` → pool
- `"helloworld"` → **NEW object in heap**
- `intern()` → moves/returns reference from String Pool

---

### Key Understanding:
- Runtime strings are **NOT auto-pooled**
- Pooling at runtime is avoided → expensive + unpredictable
- Only:
  - compile-time constants OR
  - `.intern()`
  go into pool

---

👉 Final rule:
Runtime strings are not placed in the String Pool automatically, but can be added using `intern()`.

---

### Pass by Value

👉 Java is always pass-by-value

- For primitives → actual value is copied
- For objects → **reference value is copied**

⚠️ Correction:
❌ “reference is sent”
✅ “copy of reference value is passed”

---

### PROOF

```java
void change(int[] x) {
    x = new int[]{99};
}
```

Output stays `10`

👉 Because you changed the **reference copy**, not original

---

### Type Casting

```java
byte b = 127;
b += 1;
```

- Works because:
```java
b = (byte)(b + 1);
```

---

### Why this fails:

```java
b = b + 1;
```

- `b + 1` → promoted to `int`
- Java won’t auto-cast back → possible data loss


## Evaluation Order (Critical)

- Java evaluates expressions **left → right**
- Each operand is evaluated fully before moving to next

### Example:
```java
int i = 1;
int j = i++ + ++i + i++;
```

Step-by-step:
- `i++` → use 1 → i = 2
- `++i` → i = 3 → use 3
- `i++` → use 3 → i = 4

Result:
```java
j = 7
i = 4
```

---

## Type Promotion Rules

- `byte`, `short`, `char` → promoted to **int** in expressions

### Example:
```java
char c = 'A';
c = c + 1; // ❌ compile error
```

- `c + 1` becomes `int`
- cannot assign int → char without cast

### Fix:
```java
c = (char)(c + 1);
```

---

## Compound Assignment (+=)

```java
c += 1; // ✔ works
```

- Internally:
```java
c = (char)(c + 1);
```

👉 `+=` performs **implicit narrowing conversion**

---

## Wrapper vs Primitive (Null Danger)

```java
Integer a = null;
int b = 10;

System.out.println(a + b); // ❌ NullPointerException
```

- Expression forces **unboxing**
```java
a.intValue()
```
- `a` is null → crash

---

## equals vs == (Wrapper Types)

```java
Integer a = 10;
Integer b = null;
```

- `a == b` → false (reference compare)
- `a.equals(b)` → ❌ NullPointerException

👉 `equals()` may internally use value comparison → triggers unboxing

---

## Integer Cache Edge

```java
Integer a = 127;
Integer b = 127;
Integer c = 128;
Integer d = 128;
```

Results:
```java
a == b → true   (cached)
c == d → false  (new objects)
a.equals(b) → true
c.equals(d) → true
```

👉 Cache range: **[-128 to 127]**

---

## Variable Scope & Shadowing

```java
class Test {
    int x = 10;

    void print() {
        int x = 20;
        System.out.println(x);      // 20
        System.out.println(this.x); // 10
    }
}
```

- Local variable **shadows** instance variable
- Closest scope wins

---

## Default Values

- Instance variables → auto-initialized
- Local variables → must be initialized

```java
int a; // ❌ error if used
```

---

## Final Keyword

```java
final int a = 10;
a = 20; // ❌ error
```

```java
final int[] arr = {1,2};
arr[0] = 99; // ✔ allowed
```

- `final` → reference cannot change
- object content can change

---

## Array Initialization Trap

```java
add({1,2}); // ❌ invalid
```

```java
add(new int[]{1,2}); // ✔ correct
```

- `{}` only allowed during declaration
