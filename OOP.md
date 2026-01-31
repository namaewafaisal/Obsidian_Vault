---
tags: [java, oop, core-java]
date-time: INVALID_DATETIME
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
