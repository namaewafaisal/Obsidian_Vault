# Kotlin (JVM) — Functions (Java-Oriented)

> Purpose: Map **Java methods → Kotlin functions** with zero beginner repetition.

---

## 1. Basic Mapping (Method → Function)

### Java
```java
int add(int a, int b) {
    return a + b;
}
```

### Kotlin
```kotlin
fun add(a: Int, b: Int): Int {
    return a + b
}
```

### Differences (only what matters)
- `fun` replaces return-type-first syntax
- Return type comes **after** parameters
- Same static typing, same JVM behavior

---

## 2. Expression Functions (Return Simplification)

### Java (still verbose)
```java
int add(int a, int b) {
    return a + b;
}
```

### Kotlin
```kotlin
fun add(a: Int, b: Int): Int = a + b
```

Type inference allowed:
```kotlin
fun add(a: Int, b: Int) = a + b
```

**Mental anchor:**  
If a function is a single expression, the expression *is* the return.

---

## 3. `Unit` vs `void`

### Java
```java
void log(String msg) {
    System.out.println(msg);
}
```

### Kotlin
```kotlin
fun log(msg: String) {
    println(msg)
}
```

Explicit (rarely needed):
```kotlin
fun log(msg: String): Unit {
    println(msg)
}
```

### Mapping
| Java | Kotlin |
|----|------|
| `void` | `Unit` |
| no value | value that means “nothing” |

**Why this exists:**  
Everything is an expression → consistent type system.

---

## 4. Default Parameters (Goodbye Overloading Noise)

### Java (overloading)
```java
void connect(String host) {
    connect(host, 80);
}

void connect(String host, int port) {
    // ...
}
```

### Kotlin
```kotlin
fun connect(host: String, port: Int = 80) {
    // ...
}
```

Call sites:
```kotlin
connect("localhost")
connect("localhost", 8080)
```

**Mental anchor:**  
Defaults replace most overloads.

---

## 5. Named Arguments (Call-Site Clarity)

```kotlin
connect(port = 8080, host = "localhost")
```

- Order no longer matters
- Extremely useful for readability
- No Java equivalent

---

## 6. Functions Are First-Class (Carefully)

### Java (via interfaces / lambdas)
```java
Runnable r = () -> System.out.println("Hi");
```

### Kotlin
```kotlin
val r = { println("Hi") }
```

Invoke:
```kotlin
r()
```

**Important constraint:**  
This is still **JVM-safe**, not JS-style dynamic behavior.

---

## 7. Top-Level Functions (No Utility Classes)

### Java
```java
class MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
}
```

### Kotlin
```kotlin
fun add(a: Int, b: Int) = a + b
```

- Compiles to static methods under the hood
- Cleaner API surface
- Same JVM performance

**Mental anchor:**  
Kotlin removes ceremony, not structure.

---

## 8. Summary (Lock This)

- Functions replace methods
- Expression bodies reduce noise
- Defaults + named args replace overloads
- `Unit` replaces `void`
- Top-level functions remove utility classes

---

## Next Logical Step (Strict Order)

→ Classes & constructors (where Kotlin actually changes design)

