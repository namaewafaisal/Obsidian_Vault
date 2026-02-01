---
tags:
  - java
  - cheatsheet
  - core-java
  - revision
date-time: 2026-02-01T10:51:00
status: revision
---

# 📘 Core Java Cheatsheet (Test Revision)

This note is for **fast recall**, not learning.
If something feels unclear → open the linked note.

---

## [[Java Basics]]

### `main` method
```java
public static void main(String[] args)
```
- Entry point
- `String[] args` → command-line args

---

## [[String]]

### Properties
- Immutable
- Stored in String pool (literals)

### Common methods
| Method | Params | Returns | Example |
|------|------|--------|--------|
| `length()` | — | `int` | `"abc".length()` → `3` |
| `charAt()` | `int` | `char` | `"abc".charAt(1)` → `b` |
| `substring()` | `int, int` | `String` | `"abcd".substring(1,3)` → `bc` |
| `split()` | `String` | `String[]` | `"a,b".split(",")` |
| `trim()` | — | `String` | `" a ".trim()` |
| `equals()` | `Object` | `boolean` | `"a".equals("a")` |
| `equalsIgnoreCase()` | `String` | `boolean` | `"A".equalsIgnoreCase("a")` |
| `contains()` | `CharSequence` | `boolean` | `"abc".contains("b")` |
| `replace()` | `char,char` | `String` | `"aba".replace('a','x')` |
| `indexOf()` | `char/String` | `int` | `"abc".indexOf('b')` |

⚠️ == compares **references**, not content.

---

## [[StringBuilder]] / [[StringBuffer]]

### Difference
- `StringBuilder` → not thread-safe (faster)
- `StringBuffer` → thread-safe

### Common methods
| Method | Example |
|------|--------|
| `append()` | `sb.append("x")` |
| `insert()` | `sb.insert(1,"x")` |
| `delete()` | `sb.delete(1,3)` |
| `reverse()` | `sb.reverse()` |
| `toString()` | `sb.toString()` |

---

## [[Arrays]]

### Basics
```java
int[] a = new int[3];        // default 0
int[] b = {1,2,3};
```

### Properties
- `length` → field, not method

### `Arrays` utility
| Method | Example |
|------|--------|
| `sort()` | `Arrays.sort(a)` |
| `binarySearch()` | `Arrays.binarySearch(a,2)` |
| `equals()` | `Arrays.equals(a,b)` |
| `toString()` | `Arrays.toString(a)` |
| `copyOf()` | `Arrays.copyOf(a,5)` |

---

## [[Wrapper Classes]]

### Common ones
- `Integer`, `Double`, `Boolean`, `Character`

### Methods
| Method | Example |
|------|--------|
| `parseInt()` | `Integer.parseInt("10")` |
| `valueOf()` | `Integer.valueOf("10")` |
| `intValue()` | `Integer i=10; i.intValue()` |

⚠️ Immutable  
⚠️ Autoboxing applies

---

## [[Object Class]]

Inherited by all classes.

| Method | Purpose |
|------|--------|
| `toString()` | String representation |
| `equals()` | Logical equality |
| `hashCode()` | Hash-based collections |
| `getClass()` | Runtime class |
| `finalize()` | GC hook (legacy) |

---

## [[OOP Rules]]

- Variables → resolved by **reference**
- Overridden methods → resolved by **object**
- Overloading → compile-time
- Overriding → runtime

---

## [[Constructors]]

### Rules
- No return type
- Same name as class
- Not inherited

### Chaining
```java
this();   // same class
super();  // parent class
```

⚠️ `this()` must be first line

---

## [[Access Modifiers]]

| Modifier | Same Class | Same Package | Subclass | Everywhere |
|--------|-----------|--------------|----------|------------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

---

## [[final Keyword]]

- `final variable` → assigned once
- `final method` → cannot override
- `final class` → cannot inherit

---

## [[static Keyword]]

- Belongs to class
- Shared across objects

```java
static int x;
static void foo(){}
static { }
```

⚠️ Static methods can’t access instance data directly

---

## [[Abstract Class]]

- Cannot instantiate
- Can have constructors
- Can have concrete methods

```java
abstract class A {
    abstract void f();
}
```

---

## [[Interface]]

### Implicit rules
- Methods → `public abstract`
- Fields → `public static final`

```java
interface I {
    int X = 10;
    void run();
}
```

Java 8+:
- `default` methods
- `static` methods

---

## [[Exception Handling]]

### Keywords
- `try`
- `catch`
- `finally`
- `throw`
- `throws`

### Rules
- Checked → must handle
- Unchecked → runtime
- `System.exit()` skips `finally`

---

## [[Custom Exceptions]]

```java
class MyEx extends Exception { }
class MyRunEx extends RuntimeException { }
```

---

## [[Memory Model]]

- Stack → method calls, locals
- Heap → objects
- Java is **pass-by-value**
  - Object references are copied

---

## [[Collections Framework]]

### List
- Ordered, duplicates allowed
- `ArrayList`

| Method | Example |
|------|--------|
| `add()` | `list.add(10)` |
| `get()` | `list.get(0)` |
| `set()` | `list.set(0,5)` |
| `remove()` | `list.remove(0)` |
| `size()` | `list.size()` |

---

### Set
- No duplicates
- `HashSet`

```java
set.add(1);
```

---

### Map
- Key–value
- `HashMap`

| Method | Example |
|------|--------|
| `put()` | `map.put("a",1)` |
| `get()` | `map.get("a")` |
| `containsKey()` | `map.containsKey("a")` |
| `keySet()` | `map.keySet()` |
| `values()` | `map.values()` |

---

## [[Iteration]]

```java
for(int x : list) { }
```

```java
Iterator it = list.iterator();
while(it.hasNext()) it.next();
```

---

## [[Packages & Imports]]

```java
package p1;
import java.util.*;
import static java.lang.System.out;
```

- One package per file
- Multiple classes allowed (one public)

---

## [[Legacy Awareness]]

- Applets → obsolete
- Servlets → server-side
- JDBC drivers → Type 1–4 (names only)

---

## 🔒 Final Recall Rule

> If you recognize the method and its return type,  
> you’re exam-ready.  
> If not → open the linked note.

---
