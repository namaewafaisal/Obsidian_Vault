---
tags:
  - java
  - core-java
  - cheatsheet
  - revision
date-time: 2026-02-01T23:53:00
status: revision
---
# [[Small Java Keywords & Rules]] — Cheatsheet

This note groups **small, rule-based Java topics** that don’t need deep notes but **do appear in tests**.

---

## [[this]] Keyword

### Uses
- Refer instance variable
- Call another constructor
- Pass current object

```java
class A {
    int x;
    A(int x) {
        this.x = x;
    }
}
```

### Rules
- Refers to **current object**
- Cannot be used in static context
- `this()` calls same-class constructor

---

## [[super]] Keyword

### Uses
- Access parent members
- Call parent constructor

```java
class B extends A {
    B() {
        super();
    }
}
```

### Rules
- Refers to **parent object**
- `super()` is inserted automatically if missing
- Must be **first statement** in constructor

⚠️ `this()` and `super()` cannot be used together

---

## [[static]] Keyword

### What it means
- Belongs to **class**, not object
- Shared among all objects

### Usage

```java
static int x;
static void foo() {}
static { }
```

### Rules (MCQ GOLD)
- Static methods can’t access instance members directly
- Static block runs **once**, at class loading
- `main` must be static

---

## [[final]] Keyword

### Final Variable
```java
final int x = 10;
```
- Assigned only once
- Can be initialized later (once)

### Final Method
```java
final void show() {}
```
- Cannot be overridden

### Final Class
```java
final class A {}
```
- Cannot be inherited

---

## [[Initialization Order]] (VERY IMPORTANT)

### When object is created

Order:
1. Static variables / blocks (parent → child)
2. Instance variables / blocks (parent → child)
3. Constructor (parent → child)

---

### Example

```java
class A {
    static { System.out.println("A static"); }
    { System.out.println("A instance"); }
    A() { System.out.println("A constructor"); }
}

class B extends A {
    static { System.out.println("B static"); }
    { System.out.println("B instance"); }
    B() { System.out.println("B constructor"); }
}
```

Creating `new B()` prints in order:
```
A static
B static
A instance
A constructor
B instance
B constructor
```

---

## [[Common MCQ Traps]]

- Static methods are **not overridden**
- Variables are **hidden**, not overridden
- `this` / `super` not allowed in static context
- Final methods cannot be overridden
- Static blocks run before `main`

---

## 🔗 Related Notes
- [[Constructors]]
- [[../JAVA/OOP/Inheritance]]
- [[../JAVA/OOP/Polymorphism]]
- [[Memory Model]]

---

## 🔒 One-Line Recall

> **this = current object  
> super = parent object  
> static = class-level  
> final = cannot change**

---
