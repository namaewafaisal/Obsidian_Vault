---
tags: [java, object-class, equals, hashcode, cheatsheet, core-java]
date-time: INVALID_DATETIME
status: revision
---

# [[Object Class]] + [[equals()]] / [[hashCode()]] — Cheatsheet

Everything in Java **extends Object**.

---

## 1️⃣ Object Class Methods (Recall)

| Method | Purpose |
|-----|-------|
| `toString()` | String representation |
| `equals()` | Logical equality |
| `hashCode()` | Hash-based collections |
| `getClass()` | Runtime class |
| `clone()` | Object copy (rare) |
| `finalize()` | GC hook (legacy) |

---

## 2️⃣ `equals()` vs `==` (EXAM CRITICAL)

- `==` → reference comparison
- `equals()` → content comparison (if overridden)

```java
String a = new String("x");
String b = new String("x");

a == b;        // false
a.equals(b);   // true
```

---

## 3️⃣ Default `equals()` Behavior

```java
class A {
    int x;
}
```

```java
A a1 = new A();
A a2 = new A();
a1.equals(a2);   // false
```

Why:
- Default `equals()` = same as `==`

---

## 4️⃣ Overriding `equals()`

```java
class A {
    int x;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A)) return false;
        A other = (A) o;
        return this.x == other.x;
    }
}
```

### Rules
- Must take `Object` as parameter
- Must return `boolean`
- Should be reflexive, symmetric, transitive

---

## 5️⃣ `hashCode()` Rule (VERY IMPORTANT)

> If `equals()` is overridden,  
> `hashCode()` **must also be overridden**.

Why:
- Hash-based collections (`HashMap`, `HashSet`)

---

## 6️⃣ Overriding `hashCode()`

```java
public int hashCode() {
    return Integer.hashCode(x);
}
```

---

## 7️⃣ Collections Trap

```java
HashSet<A> set = new HashSet<>();
set.add(new A(10));
set.contains(new A(10)); // false if hashCode not overridden
```

---

## 8️⃣ `toString()` (Quick Recall)

```java
public String toString() {
    return "x=" + x;
}
```

Used when:
- Printing object
- String concatenation

---

## 🔗 Related Notes
- [[Collections Framework]]
- [[Wrapper Classes]]
- [[Memory Model]]

---

## 🔒 One-Line Recall

> **== compares references  
> equals compares content  
> equals + hashCode go together**

---
