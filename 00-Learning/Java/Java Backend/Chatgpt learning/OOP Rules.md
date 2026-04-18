---
tags: [java, oop, cheatsheet, core-java]
date-time: INVALID_DATETIME
status: revision
---

# [[OOP in Java]] — Unified Cheatsheet

This note is for **fast recall of OOP rules**, not learning.

---

## 1️⃣ Pillars of OOP (Recall)

- **Encapsulation** → data hiding
- **Inheritance** → reuse
- **Polymorphism** → multiple behavior
- **Abstraction** → hide implementation

---

## 2️⃣ Encapsulation

### What it is
- Bundle data + methods
- Restrict direct access

### Syntax
```java
class A {
    private int x;
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
}
```

### Rules
- Fields usually `private`
- Access via methods

---

## 3️⃣ Inheritance

### Syntax
```java
class B extends A { }
```

### Rules
- Single class inheritance
- Multiple inheritance via interfaces only
- Child inherits accessible members

---

## 4️⃣ `this` and `super`

### `this`
- Refers to current object
- Used to resolve name conflict
- Calls same-class constructor

```java
this.x = x;
this();
```

### `super`
- Refers to parent
- Calls parent constructor
- Access parent members

```java
super();
super.show();
```

⚠️ `this()` and `super()` must be **first statement**  
⚠️ Cannot use both together

---

## 5️⃣ Constructors (OOP Context)

### Rules
- Same name as class
- No return type
- Not inherited
- Can be overloaded

### Chaining order
- Parent constructor → Child constructor

---

## 6️⃣ Polymorphism (MOST IMPORTANT)

### Runtime Polymorphism (Overriding)

```java
class A {
    void show() { System.out.println("A"); }
}
class B extends A {
    void show() { System.out.println("B"); }
}

A obj = new B();
obj.show();
```

**Output**
```
B
```

### Rule
- Method resolved by **object**
- Reference type doesn’t matter

---

## 7️⃣ Compile-Time Polymorphism (Overloading)

```java
void add(int a, int b) {}
void add(double a, double b) {}
```

### Rules
- Same name
- Different parameter list
- Decided at compile time

---

## 8️⃣ Variable Hiding (COMMON TRAP)

```java
class A { int x = 10; }
class B extends A { int x = 20; }

A obj = new B();
System.out.println(obj.x);
```

**Output**
```
10
```

### Rule
- Variables resolved by **reference**

---

## 9️⃣ Static Members (OOP Rule)

```java
class A {
    static void show() {}
}
```

### Rules
- Belong to class
- Not polymorphic
- Static methods are **hidden**, not overridden

---

## 🔟 `final` in OOP

### Final Variable
- Assigned once

### Final Method
- Cannot be overridden

### Final Class
- Cannot be inherited

---

## 1️⃣1️⃣ Abstract Classes

### Syntax
```java
abstract class A {
    abstract void run();
    void show() {}
}
```

### Rules
- Cannot instantiate
- Can have constructors
- Can mix abstract + concrete methods

---

## 1️⃣2️⃣ Interfaces (Java 8+)

### Syntax
```java
interface I {
    int X = 10;
    void run();
    default void show() {}
}
```

### Rules
- Fields → `public static final`
- Methods → `public abstract` (by default)
- Supports multiple inheritance
- Class must resolve default method conflicts

---

## 1️⃣3️⃣ Interface vs Abstract Class (Quick)

| Feature | Abstract | Interface |
|------|--------|----------|
| Multiple inheritance | ❌ | ✅ |
| Constructors | ✅ | ❌ |
| Instance variables | ✅ | ❌ |
| Default methods | ❌ | ✅ |

---

## 1️⃣4️⃣ Method Resolution Summary (LOCK THIS)

| Member | Decided By |
|-----|----------|
| Instance method | Object |
| Variable | Reference |
| Static method | Reference |
| Overloaded method | Compile time |

---

## 1️⃣5️⃣ Common OOP MCQ Traps

- Static methods are not overridden
- Variables are not polymorphic
- Overloading ≠ overriding
- Return type alone cannot overload
- Parent reference can hold child object

---

## 🔗 Related Notes
- [[Object Class]]
- [[Constructors]]
- [[Exception Handling]]
- [[Collections Framework]]

---

## 🔒 Final Recall Block

> **Object decides methods  
> Reference decides variables  
> Static is compile-time  
> Interfaces enable multiple inheritance**

---
