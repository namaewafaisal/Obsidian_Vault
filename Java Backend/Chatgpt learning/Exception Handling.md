---
tags: [java, exception-handling, cheatsheet, core-java]
date-time: INVALID_DATETIME
status: revision
---

# [[Exception Handling]] — Core Java Cheatsheet

This note is for **syntax + flow recall**, not theory.

---

## 1️⃣ Exception Hierarchy (Recall)

- `Throwable`
  - `Exception`
    - Checked Exceptions
    - `RuntimeException` (Unchecked)
  - `Error`

---

## 2️⃣ Checked vs Unchecked

### Checked
- Checked at compile time
- Must handle or declare

Examples:
- `IOException`
- `SQLException`

### Unchecked
- Runtime
- No need to handle

Examples:
- `NullPointerException`
- `ArrayIndexOutOfBoundsException`

---

## 3️⃣ try–catch–finally Syntax

```java
try {
    risky();
} catch (Exception e) {
    handle();
} finally {
    cleanup();
}
```

### Rules
- `finally` runs even if exception occurs
- ❌ `finally` does NOT run if JVM exits

---

## 4️⃣ Multiple catch

```java
try {
    risky();
} catch (IOException e) {
} catch (Exception e) {
}
```

⚠️ Child first, parent later

---

## 5️⃣ throw vs throws

### `throw`
- Used to explicitly throw exception

```java
throw new Exception("error");
```

### `throws`
- Declares exception

```java
void read() throws IOException { }
```

---

## 6️⃣ finally + return (MCQ GOLD)

```java
try {
    return 1;
} finally {
    return 2;
}
```

**Output**
```
2
```

- `finally` overrides return

---

## 7️⃣ System.exit() Trap

```java
try {
    System.exit(0);
} finally {
    System.out.println("hi");
}
```

**Output**
```
(no output)
```

---

## 8️⃣ Custom Exceptions (Syntax Only)

```java
class MyEx extends Exception {
    MyEx(String msg) {
        super(msg);
    }
}
```

- Extend `Exception` → checked
- Extend `RuntimeException` → unchecked

---

## 9️⃣ Common MCQ Rules (LOCK)

- Checked → must handle
- Unchecked → runtime
- `finally` usually runs
- `System.exit()` skips `finally`

---

## 🔗 Related Notes
- [[Custom Exceptions]]
- [[Memory Model]]

---

## 🔒 One-Line Recall

> **Checked = compile-time,  
> Unchecked = runtime,  
> finally usually runs.**

---
