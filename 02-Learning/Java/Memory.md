# How Java Actually Works in Memory

## What is it?

When your Java program runs, the JVM splits memory into distinct regions and stores different things in each. Understanding where each thing lives — class definitions, objects, local variables, method code — is what separates someone who debugs by guessing from someone who knows exactly what's happening.

This note covers: **Method Area / Metaspace, Heap, Stack, and how method calls actually work.**

---

## Real-World Analogy

Think of a restaurant:

- The **recipe book** (Method Area) — exists once, belongs to the restaurant, has all instructions. No food is in it.
- The **plates of food** (Heap) — actual instances. Each plate has its own ingredients (instance variables). The recipe is not on the plate.
- The **waiter's notepad** (Stack) — temporary. Waiter writes down the current order, delivers it, tears off the page. Gone when done.

When a customer orders, the waiter (Stack) references the recipe (Method Area) and produces a plate (Heap). The recipe is never duplicated per plate.

---

## The Three Memory Regions

### 1. Method Area / Metaspace
Loaded **once** when the class is first used. Never duplicated.

Contains:
- Bytecode of **all methods** (static + non-static both)
- **Static variables**
- Class-level metadata (class name, parent class, interfaces)

```
Method Area
──────────────────────────────
Car class
  static int wheels = 4
  bytecode: drive() { ... }
  bytecode: info() { ... }
  bytecode: <init>() { ... }   ← constructor
```

### 2. Heap
Where **objects live**. Every `new` keyword puts something here.

Contains:
- **Instance variables only**
- A hidden pointer back to the class in Method Area

```
Heap
──────────────────────────────
Object c1
  speed = 60
  brand = "Toyota"
  [class ref → Car in Method Area]

Object c2
  speed = 100
  brand = "BMW"
  [class ref → Car in Method Area]
```

Methods are **not here**. The object only stores data + a reference to its class.

### 3. Stack
One stack per thread. Every method call pushes a **frame**. Frame is destroyed when method returns.

Contains:
- Local variables
- Method parameters
- Reference variables (the variable holding the address of a heap object)

```
Stack (main thread)
──────────────────────────────
main() frame
  c1 → [address of heap object]
  c2 → [address of heap object]
```

---

## How a Method Call Actually Works

When you write:

```java
Car c1 = new Car();
c1.drive();
```

JVM does this:

```
1. c1 is on the Stack — holds address of object on Heap
2. JVM follows that address to the object on the Heap
3. Object has a hidden class reference → points to Car in Method Area
4. JVM finds drive() bytecode in Method Area
5. Executes it — using c1's instance variables from the Heap
```

The method code runs **once from Method Area** but operates on **c1's specific data from the Heap**. That's how two objects can call the same method but get different results — same code, different data.

---

## Static vs Instance — The Real Difference in Memory Terms

```java
class Car {
    static int wheels = 4;    // lives in Method Area — ONE copy
    int speed;                 // lives in Heap — one per object

    static void info() { }    // bytecode in Method Area, no object needed
    void drive() { }          // bytecode in Method Area, but needs object's data to run
}
```

| Thing | Where it lives | How many copies |
|---|---|---|
| Static variable | Method Area | One, shared by all objects |
| Instance variable | Heap (inside each object) | One per object |
| Static method bytecode | Method Area | One |
| Instance method bytecode | Method Area | One (not per object) |
| Reference variable (e.g. `c1`) | Stack | One per scope |
| Object itself | Heap | One per `new` |

**Key insight:** There is no such thing as "method stored inside object." All method code is always in Method Area. What differs is *whose data* the method runs on.

---

## Your Intuition — What Was Right and What Needed Fixing

**You said:** *"Object holds the address of methods"*
**Reality:** Object holds a hidden reference to its **class**, and the class has the methods. The object doesn't store method code directly.

---

**You said:** *"Class is like an object with memory"*
**Reality:** Closer than you think. Internally Java represents every class as a `java.lang.Class` object in Metaspace. That's how `Car.class` works. At your level, think of it as: class metadata lives in a separate memory region loaded once, behaves like a shared resource.

---

**You said:** *"Object calls parent class memory to get static value"*
**Reality:** Exactly right. When you write `c1.wheels`, JVM ignores `c1` entirely, goes straight to `Car` in Method Area and reads `wheels` from there. The object is just a misleading path. This is why it's bad practice — it implies the value is per-object when it isn't.

---

## What it CAN do (knowing this)
- Explain why `NullPointerException` happens — you're following a Stack reference that points to nothing on the Heap
- Explain why static variables are shared — there's only one copy in Method Area
- Explain why calling a static method via object works but is misleading — JVM discards the object reference and goes to Method Area directly
- Explain why objects don't get "heavier" with more methods — methods aren't stored per object

---

## What it CANNOT do / Limitations
- Stack memory is limited — deep recursion causes `StackOverflowError` because too many frames pile up
- Heap memory is limited — creating too many objects without releasing references causes `OutOfMemoryError`
- You cannot manually free heap memory in Java — GC handles it, but only when no references point to the object
- Metaspace (Method Area) can also run out if you dynamically load huge numbers of classes (rare, but real in frameworks)
- Instance method have implied parameter `this` in the method signature. Without the object there is no `this` to sent so parameter mismatch. Hence instance method cannot be called without Objects

---

## Common Mistakes

**Mistake 1: Thinking instance methods are stored inside the object**

```java
// Wrong mental model:
// c1 = { speed: 60, brand: "Toyota", drive: [code] }  ← NO

// Correct:
// c1 = { speed: 60, brand: "Toyota", classRef → Car }
// Car in Method Area = { drive bytecode, info bytecode, wheels }
```

---

**Mistake 2: Thinking `c2 = c1` copies the object**

```java
Car c1 = new Car();
c1.speed = 60;

Car c2 = c1;      // copies the Stack reference, NOT the Heap object
c2.speed = 99;

System.out.println(c1.speed); // 99 — same object, two references pointing to it
```

Both `c1` and `c2` on the Stack point to the same address on the Heap.

---

**Mistake 3: Accessing static via object and thinking it's per-object**

```java
Car c1 = new Car();
Car c2 = new Car();

c1.wheels = 6;                        // bad practice but legal
System.out.println(c2.wheels);        // 6 — same variable, JVM used Car.wheels
```

---

## Interactions with other concepts

- **Garbage Collection**: When no Stack reference points to a Heap object, GC can collect it. The class in Method Area is never GC'd while the classloader is alive.
- **`null`**: A reference variable on the Stack holding `null` means it points to no Heap address. Dereferencing it (`null.method()`) → `NullPointerException`.
- **`static`**: Static things live and die with the class in Method Area, not with any object.
- **Constructors**: `<init>` bytecode is stored in Method Area. `new Car()` allocates space on the Heap for instance variables, then runs `<init>` to fill them.
- **Recursion / Stack Overflow**: Every recursive call pushes a new frame on the Stack. Infinite recursion fills the Stack → `StackOverflowError`.

---

## Placement / Interview Traps

**Trap 1:**
```java
Car c1 = new Car();
Car c2 = new Car();
c1.wheels = 6;
System.out.println(Car.wheels);
```
**Answer: `6`** — `c1.wheels` and `Car.wheels` are the same thing. Static variable lives once in Method Area. Modifying via object reference still modifies the one shared copy.

---

**Trap 2: How many objects are created?**
```java
Car a = new Car();
Car b = new Car();
Car c = a;
a = null;
```
**Answer: 2 objects on the Heap.** `c = a` copied the reference. `a = null` only cleared `a`'s Stack slot. The object is still alive because `c` still points to it. GC will not touch it.

---

**Trap 3:**
```java
class Test {
    int x = 10;
    static int y = 20;

    void show() {
        int z = 30;
        System.out.println(x + y + z);
    }
}
```
**Where does each variable live?**
- `x` → Heap (instance variable, inside object)
- `y` → Method Area (static variable, inside class)
- `z` → Stack (local variable, inside method frame)

**Answer: `60`** — but the real trap is knowing *where* each lives, which is the actual interview question.

---

## Quick Reference — Full Memory Picture

```
┌─────────────────────────────────────────────────────┐
│                    METHOD AREA                       │
│  Car class                                           │
│    static int wheels = 4                             │
│    bytecode: drive(), info(), <init>()               │
│                                                      │
│  String class, Object class, etc.                    │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│                       HEAP                           │
│                                                      │
│  Object c1                Object c2                  │
│  ┌─────────────┐          ┌─────────────┐            │
│  │ speed = 60  │          │ speed = 100 │            │
│  │ brand = "T" │          │ brand = "B" │            │
│  │ classRef ───┼──────────┼──► Car      │            │
│  └─────────────┘          └─────────────┘            │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│                      STACK                           │
│  main() frame                                        │
│    c1 → [0x001 address of heap object 1]             │
│    c2 → [0x002 address of heap object 2]             │
│                                                      │
│  drive() frame  ← pushed when c1.drive() called      │
│    this → [0x001]   ← same address as c1             │
└─────────────────────────────────────────────────────┘
```

```java
class Car {
    static int wheels = 4;      // Method Area — shared
    String brand;               // Heap — per object
    int speed;                  // Heap — per object

    void drive() {
        int boost = 10;         // Stack — local, gone after method ends
        System.out.println(brand + " at " + (speed + boost));
    }

    static void info() {
        // Cannot use brand or speed here — no object context
        System.out.println("All cars have " + wheels + " wheels");
    }
}

public class Main {
    public static void main(String[] args) {
        Car c1 = new Car();     // c1 reference on Stack, object on Heap
        c1.brand = "Toyota";
        c1.speed = 60;

        Car c2 = new Car();     // separate object on Heap
        c2.brand = "BMW";
        c2.speed = 100;

        c1.drive();  // JVM: Stack(c1) → Heap(object) → Method Area(drive bytecode)
        c2.drive();  // same bytecode, different object's data

        Car.info();  // JVM: straight to Method Area, no object involved
        c1.info();   // works but bad practice — JVM ignores c1, uses Car.info()
    }
}
```

---

Say **next** when you want to continue with topic 2 — Constructors, or name any topic you want to jump to.