---
tags:
  - java
  - oop
  - abstraction
difficulty: core
status: revise
date-time: 2026-01-31T08:58:00
---
# Abstraction (OOP)

## Definition
Abstraction = exposing **what a class does** while hiding **how it does it**.

---

## Why it exists
- Reduce coupling *(code depends on abstraction, not concrete class)*
- Allow implementation changes without affecting client code
- Enable flexible designs and frameworks

---

## Reduce coupling (precise meaning)
Code depends on an **abstract type**, not a **specific class**  
→ implementations can be swapped without code changes.

```java
Car car = new WagonR(); // low coupling
```

*(Not freedom to do anything in child; freedom to change implementation)*

---

## How Java provides abstraction
- **Abstract Class**
- **Interface**

---

## Abstract Class

### Use when
- Classes are closely related
- You need shared state or base behavior

### Syntax
```java
abstract class Car {
    abstract void drive();
}
```

### Can have
- Abstract + concrete methods
- Instance variables
- Constructors
- Access modifiers

### Rules
- Cannot be instantiated
- Subclass must implement abstract methods
- Single inheritance only (`extends` one class)

---

## Interface

### Use when
- You want to define a **capability / contract**
- Multiple inheritance is required
- No shared state needed

### Syntax
```java
interface Engine {
    void start();
}
```

### Can have
- Abstract methods
- `default` methods
- `static` methods
- Constants (`public static final`)
- No constructors
- No instance variables

### Implementation
```java
class Car implements Engine {
    public void start() {}
}
```

---

## Multiple implementations
- Abstract class → multiple subclasses
- Interface → multiple implementations + multiple inheritance

---

## Functional Interface
Interface with **exactly one abstract method**  
→ enables lambdas

```java
Runnable r = () -> System.out.println("run");
```

---

## Keywords to recall (tests)
- `abstract`
- `interface`
- `extends`
- `implements`
- `default`
- `static`
- `@Override`
- `@FunctionalInterface`

---

## Common built-ins
- `Runnable`
- `Callable`
- `Comparable`
- `Comparator`
- `List`, `Set`, `Map`
- `AutoCloseable`

---

## Interview focus points
- Abstract class vs Interface
- Constructors in abstract class (yes)
- Default methods in interface
- Multiple inheritance via interface
- Functional interface meaning

---

## One-line recall
> Abstraction lets code depend on behavior, not implementation.

---

## Links
- [[Encapsulation]]
- [[Inheritance]]
- [[Polymorphism]]