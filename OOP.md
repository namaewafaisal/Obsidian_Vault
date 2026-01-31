---
tags:
  - java
  - oop
  - core-java
date-time:
status: foundation
---

# Java OOP — Class vs Abstract Class vs Interface (With Code)

---

## 1️⃣ Class

### What a Class **HAS**
- Instance variables
- Static variables
- Concrete methods
- Constructors
- Static blocks

### What a Class **DOES NOT HAVE**
- Abstract methods (unless class is abstract)
- Multiple inheritance via `extends`

### What a Class **CAN DO**
- Be instantiated
- Extend **one** class
- Implement **multiple** interfaces

### What a Class **CANNOT DO**
- Extend more than one class
- Have unimplemented abstract methods

---

### Example: Normal Class

```java
class Car {
    int speed;              // instance variable
    static int wheels = 4;  // static variable

    Car(int speed) {
        this.speed = speed;
    }

    void drive() {
        System.out.println("Driving at " + speed);
    }
}
```

### Implicit rules
- Methods are **non-abstract by default**
- Fields are **package-private by default**
- Constructors are **not inherited**

---

## 2️⃣ Abstract Class

### What an Abstract Class **HAS**
- Abstract methods (0 or more)
- Concrete methods
- Instance variables
- Constructors
- Static methods

### What an Abstract Class **DOES NOT HAVE**
- Objects (cannot instantiate)
- Multiple inheritance

### What an Abstract Class **CAN DO**
- Extend one class
- Implement interfaces
- Provide partial implementation

### What an Abstract Class **CANNOT DO**
- Be instantiated
- Be `final`
- Have abstract constructors

---

### Example: Abstract Class

```java
abstract class Animal {
    int age; // instance variable

    Animal(int age) {
        this.age = age;
    }

    abstract void sound(); // must be implemented

    void sleep() {
        System.out.println("Sleeping");
    }
}
```

```java
class Dog extends Animal {
    Dog(int age) {
        super(age);
    }

    @Override
    public void sound() {
        System.out.println("Bark");
    }
}
```

---

### Implicit rules (EXAM IMPORTANT)
- Abstract methods are **implicitly `public` or `protected`**
- ❌ Abstract methods **cannot be `private`**
- Abstract class can have **zero abstract methods** (valid MCQ)

---

## 3️⃣ Interface

### What an Interface **HAS**
- Abstract methods
- `default` methods
- `static` methods
- Constants (fields)

### What an Interface **DOES NOT HAVE**
- Constructors
- Instance variables
- Object creation

### What an Interface **CAN DO**
- Extend multiple interfaces
- Be implemented by many classes

### What an Interface **CANNOT DO**
- Extend a class
- Store state

---

### Example: Interface

```java
interface Flyable {

    int MAX_HEIGHT = 1000; 
    // implicitly: public static final

    void fly();  
    // implicitly: public abstract
}
```

```java
class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying up to " + MAX_HEIGHT);
    }
}
```

---

### Default Method Example

```java
interface Logger {

    default void log() {
        System.out.println("Logging...");
    }
}
```

```java
class AppLogger implements Logger {
    // log() inherited
}
```

---

### Implicit rules (VERY IMPORTANT)

#### Interface methods
- Implicitly: `public abstract`
- ❌ Cannot be `protected` or `private` (except Java 9+ private helpers)

#### Interface fields
- Implicitly:  
  `public static final`
- ❌ No instance variables

---

## 4️⃣ Direct Comparison Table

| Feature | Class | Abstract Class | Interface |
|------|------|---------------|----------|
| Can create object | ✅ | ❌ | ❌ |
| Multiple inheritance | ❌ | ❌ | ✅ |
| Constructors | ✅ | ✅ | ❌ |
| Instance fields | ✅ | ✅ | ❌ |
| Abstract methods | ❌ | ✅ | ✅ |
| Default implementation | ✅ | ✅ | `default` only |

---

## 5️⃣ Common MCQ Traps

```java
interface A {
    int x = 10;
}

class Test {
    public static void main(String[] args) {
        // A.x = 20; ❌ compile-time error (final)
        System.out.println(A.x);
    }
}
```

```java
abstract class Test {
    abstract private void run(); // ❌ INVALID
}
```

```java
abstract class Test {
    // valid: no abstract methods
}
```

---

## 6️⃣ Final Mental Model (Lock This)

- **Class** → full object
- **Abstract class** → is-a + shared logic
- **Interface** → can-do / capability

If you remember this, **design + MCQs become easy**.

---

---
tags: [java, oop, core-java]
date-time: INVALID_DATETIME
status: foundation
---

# Java OOP — Encapsulation, Inheritance, Polymorphism, Abstraction

---

## 1️⃣ Encapsulation

### What Encapsulation **IS**
- Wrapping **data + methods** together
- Controlling **access**, not hiding completely
- Achieved using **access modifiers**

### What Encapsulation **HAS**
- `private` fields
- `public` / `protected` methods
- Getter / Setter methods

### What Encapsulation **DOES NOT MEAN**
- ❌ Data hiding = security (it’s control, not security)
- ❌ Just using getters/setters blindly

### What Encapsulation **CAN DO**
- Prevent invalid state
- Control write access
- Add validation logic

---

### Example: Encapsulation

```java
class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

### Implicit rules (MCQ traps)
- Fields should almost always be `private`
- Logic goes in **methods**, not setters blindly
- Encapsulation ≠ immutability

---

## 2️⃣ Inheritance

### What Inheritance **IS**
- One class acquiring behavior of another
- Represents **is-a** relationship

### What Inheritance **HAS**
- `extends` keyword
- Method overriding
- Access to `protected` members

### What Inheritance **CANNOT DO**
- Multiple class inheritance
- Access `private` members directly
- Override `final` methods

---

### Example: Inheritance

```java
class Vehicle {
    void move() {
        System.out.println("Vehicle moving");
    }
}

class Car extends Vehicle {
    void honk() {
        System.out.println("Car honking");
    }
}
```

---

### Important rules
- Constructors are **not inherited**
- `super()` is called implicitly
- `protected` allows subclass access

---

## 3️⃣ Polymorphism (MOST IMPORTANT FOR OUTPUT QUESTIONS)

### What Polymorphism **IS**
- Same method name, **different behavior**
- Resolved at **runtime** (dynamic binding)

### What Polymorphism **REQUIRES**
- Inheritance
- Method overriding
- Parent reference → child object

### What Polymorphism **DOES NOT APPLY TO**
- Static methods
- Variables
- Constructors

---

### Example: Runtime Polymorphism

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();
    }
}
```

**Output**
```
Bark
```

### Why?
- Method call resolved at **runtime**
- Based on **object**, not reference

---

### MCQ Trap: Variables are NOT polymorphic

```java
class A {
    int x = 10;
}

class B extends A {
    int x = 20;
}

class Test {
    public static void main(String[] args) {
        A obj = new B();
        System.out.println(obj.x);
    }
}
```

**Output**
```
10
```

Variables depend on **reference type**, not object.

---

## 4️⃣ Compile-Time Polymorphism (Method Overloading)

### What it **IS**
- Same method name
- Different parameter list
- Resolved at **compile time**

### What it **DOES NOT CONSIDER**
- Return type only
- Method body

---

### Example: Method Overloading

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

---

### MCQ Trap: Overloading vs Overriding

```java
class A {
    void test(int x) {}
}

class B extends A {
    void test(double x) {}
}
```

✅ This is **overloading**, NOT overriding.

---

## 5️⃣ Abstraction (Ties Everything Together)

### What Abstraction **IS**
- Showing **what**, not **how**
- Achieved using:
  - Abstract classes
  - Interfaces

### What Abstraction **DOES**
- Hides implementation details
- Exposes behavior

### What Abstraction **IS NOT**
- ❌ Encapsulation
- ❌ Just interfaces

---

### Example: Abstraction via Interface

```java
interface Payment {
    void pay();
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paying via UPI");
    }
}
```

---

## 6️⃣ One-Glance Summary (LOCK THIS)

| Concept | Core Idea |
|------|---------|
| Encapsulation | Control access |
| Inheritance | Reuse behavior |
| Polymorphism | One call, many behaviors |
| Abstraction | Hide implementation |

---

## 7️⃣ Ultimate MCQ Rule (Very Important)

> **Reference decides variables**  
> **Object decides overridden methods**

If you remember this → **90% output questions are solved**.

---
