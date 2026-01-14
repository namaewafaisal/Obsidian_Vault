Below is a **clean, recall-focused Obsidian note** for **Inner Classes & Anonymous Inner Classes**.
This matches Telusko-style explanation and is sufficient for relearning + backend usage.

---

## Inner Classes (Java)

### What is an Inner Class

A class **defined inside another class**.

```java
class Outer {
    class Inner {
        void show() {
            System.out.println("Inner class");
        }
    }
}
```

Used when a class is **tightly coupled** to its outer class.

---

### Types of Inner Classes

1. **Non-static Inner Class**
2. **Static Nested Class**
3. **Anonymous Inner Class**
4. **Local Inner Class** (inside a method)

---

## 1. Non-static Inner Class

* Has access to **all members** of outer class (even `private`)
* Requires an **instance of outer class**

```java
Outer o = new Outer();
Outer.Inner i = o.new Inner();
i.show();
```

Use when inner logic depends on outer object state.

---

## 2. Static Nested Class

* Declared with `static`
* Does **not** need outer class object
* Can access only **static members** of outer class

```java
class Outer {
    static class Inner {
        void show() {
            System.out.println("Static inner");
        }
    }
}

Outer.Inner i = new Outer.Inner();
```

More memory-efficient than non-static inner class.

---

## 3. Anonymous Inner Class (very important)

### What it is

* A class **without a name**
* Used for **one-time implementation**
* Common with **interfaces** and **abstract classes**

---

### Anonymous class with interface

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running");
    }
};
```

---

### Anonymous class with abstract class

```java
abstract class A {
    abstract void show();
}

A obj = new A() {
    void show() {
        System.out.println("Implemented");
    }
};
```

---

### Key rules

* No class name
* Cannot define constructors
* Used only **once**
* Creates an object at the same time

---

## Why anonymous inner classes exist

Before Java 8:

* They reduced boilerplate
  After Java 8:
* Mostly replaced by **lambda expressions** (for functional interfaces)

---

## Lambda vs Anonymous Class

```java
// Anonymous
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// Lambda
Runnable r = () -> System.out.println("Hello");
```

Lambda:

* Only for **functional interfaces**
* Cleaner and preferred

---

## Backend relevance (Spring)

* Anonymous classes → rarely used now
* Lambdas → used in:

  * Streams
  * Callbacks
* Inner classes → used internally in frameworks

---

### One-line recall

> Inner class = tightly coupled helper
> Anonymous class = one-time implementation
> Lambda = modern replacement

---

Send the **next topic name only** when ready.
Short answer: **sometimes yes, sometimes no** — it depends on *what* you implemented.

Use this **one-rule check**:

### It was a **lambda** if:

* You implemented an **interface with exactly one abstract method** (functional interface)
* Example:

```java
Runnable r = () -> System.out.println("Run");
```

This is **lambda**, not anonymous inner class.

---

### It was an **anonymous inner class** if:

* You wrote `new Interface() { ... }`
* OR you implemented an **abstract class**
* OR the interface had **more than one method**

Example:

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Run");
    }
};
```

This is **anonymous inner class**.

---

### Key takeaway (remember this)

> **Lambda is just a shorter form of an anonymous inner class — but only for functional interfaces.**

If you want, you can paste **one example you remember using**, and I’ll tell you exactly which one it was.
