# Kotlin (JVM) — Java-Oriented Notes

> Purpose: Learn Kotlin **as a better Java**, not as a beginner language.

---

## 0. Positioning (Mental Anchor)

**One-line anchor:**  
Kotlin is a **JVM language** that enforces good Java practices by default.

- Compiles to JVM bytecode
- Uses JDK + JVM + Java libraries
- Static typing, compile-time safety
- Not JavaScript, not scripting

---

## 1. Variables: Mutability Is Explicit (Core Difference)

### Java baseline
```java
int x = 10;
x = 20;
```

- Mutable by default
- `final` is optional

### Kotlin model
```kotlin
val x = 10
// x = 20 ❌

var y = 10
y = 20
```

### Mapping
| Java | Kotlin |
|----|------|
| `int x` | `var x` |
| `final int x` | `val x` |

**Rule (non-negotiable):**
> Use `val` unless mutation is required.

**Why this exists:**  
Prevents accidental state changes → safer concurrency → clearer intent.

---

## 2. Types: Still Static, Less Noise

### Java
```java
int x = 10;
```

### Kotlin (type inference)
```kotlin
val x = 10
```

Explicit when needed:
```kotlin
val x: Int = 10
```

- Compiler infers type
- No dynamic typing
- Same compile-time guarantees as Java

**Mental model:**  
Type inference ≠ weak typing.

---

## 3. Primitives: JVM Reality (Important)

### Java
```java
int a = 5;
Integer b = 5;
```

### Kotlin
```kotlin
val a: Int = 5
```

Facts:
- Kotlin has **no primitive syntax**
- JVM **optimizes** to primitives internally
- No performance penalty

**Mental anchor:**  
High-level syntax, low-level execution.

---

## 4. Null Safety: Compile-Time Enforcement

### Java reality
```java
String s = null;
s.length(); // Runtime NPE
```

### Kotlin rule
```kotlin
val s: String = null ❌
```

Must be explicit:
```kotlin
val s: String? = null
```

Safe access:
```kotlin
s?.length
```

### Mapping
| Java | Kotlin |
|----|------|
| Nullable by default | Non-null by default |
| Runtime NPE | Compile-time check |

**Why this matters:**  
Moves bugs from production → compiler.

---

## 5. `if` Is an Expression (Not a Statement)

### Java
```java
int max;
if (a > b) {
    max = a;
} else {
    max = b;
}
```

### Kotlin
```kotlin
val max = if (a > b) a else b
```

- Returns a value
- No ternary operator needed

**Mental anchor:**  
Control flow can produce values.

---

## 6. Semicolons (Pure Syntax Cleanup)

```kotlin
val x = 10
println(x)
```

- Optional
- No semantic impact

---

## 7. Summary (Lock This)

- Kotlin = Java + enforced best practices
- JVM-first, not JS-first
- `val` over `var`
- Null safety is the real upgrade
- Same runtime, safer compile-time

---

## Next Logical Step (Do NOT Skip Order)

→ [[Functions]] (Java methods → Kotlin functions)

