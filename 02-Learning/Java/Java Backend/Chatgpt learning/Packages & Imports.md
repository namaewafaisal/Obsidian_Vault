---
tags:
  - java
  - imports
  - cheatsheet
  - revision
  - core-java
date-time: 2026-02-01T12:01:00
status: revision
---

# [[Java Imports & Commonly Forgotten Stuff]] — Cheatsheet

This note exists because **Java tests punish memory slips**.

---

## 1️⃣ Default Imports (NO NEED TO IMPORT)

These are **always available**:

```java
java.lang.*
```

Includes:
- `String`
- `System`
- `Math`
- `Object`
- `Integer`, `Double`, etc.
- `Exception`, `RuntimeException`

⚠️ Never write:
```java
import java.lang.String; // unnecessary
```

---

## 2️⃣ Most-Used Imports (REMEMBER THESE)

### Collections
```java
import java.util.*;
```

Covers:
- `List`, `ArrayList`
- `Set`, `HashSet`
- `Map`, `HashMap`
- `Iterator`
- `Collections`, `Arrays`

---

### Specific Collection Imports (if not using `*`)
```java
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
```

---

### Scanner (input)
```java
import java.util.Scanner;
```

---

### Date / Time (old)
```java
import java.util.Date;
```

---

## 3️⃣ Static Import (RARE BUT TESTED)

### Syntax
```java
import static java.lang.System.out;
```

Usage:
```java
out.println("Hello");
```

⚠️ Only works for **static members**

---

## 4️⃣ Arrays vs Collections Utilities (CONFUSION ZONE)

### Arrays utility class
```java
import java.util.Arrays;
```

Used for:
- Arrays only

```java
Arrays.sort(arr);
Arrays.toString(arr);
```

---

### Collections utility class
```java
import java.util.Collections;
```

Used for:
- Collection objects

```java
Collections.sort(list);
Collections.reverse(list);
```

---

## 5️⃣ Things People OFTEN Forget (VERY IMPORTANT)

### `length` vs `length()`
- Array → `arr.length`
- String → `str.length()`
- Collection → `list.size()`

---

### `==` vs `equals()`
- `==` → reference
- `equals()` → content (if overridden)

---

### `remove()` Trap (List)
```java
list.remove(1);                // index
list.remove(Integer.valueOf(1)); // object
```

---

### Overloading vs Overriding
- Overloading → compile time
- Overriding → runtime

---

### Static Rules
- Static methods **not overridden**
- Static methods **can’t access instance members**

---

### Constructors
- If **any constructor exists**, default is NOT added
- Constructors are NOT inherited

---

## 6️⃣ Access Modifier Slip-ups

| Modifier | Same Class | Same Package | Subclass | Outside |
|-------|-----------|-------------|----------|---------|
| public | ✅ | ✅ | ✅ | ✅ |
| protected | ✅ | ✅ | ✅ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| private | ✅ | ❌ | ❌ | ❌ |

---

## 7️⃣ Interface Defaults People Forget

- Fields → `public static final`
- Methods → `public abstract`
- Implemented methods must be `public`

---

## 8️⃣ `main` Method Gotchas

Valid:
```java
public static void main(String[] args)
public static void main(String args[])
```

Invalid:
```java
static public void main()   // no args
```

---

## 9️⃣ Keywords People Confuse

- `throw` → throws exception
- `throws` → declares exception
- `final` ≠ `finally` ≠ `finalize`

---

## 🔒 Final Recall Block

> **If it’s in `java.lang`, don’t import.  
> If it’s a collection, import `java.util`.  
> If it’s an array helper, use `Arrays`.  
> If it’s a collection helper, use `Collections`.**

---
