# Classes and Objects

## What is it?
A **class** is a blueprint — it defines what data something holds and what it can do. An **object** is a concrete thing created from that blueprint that actually exists in memory and has its own values.

You write the class once. You can create as many objects from it as you want, each with their own independent data.

---

## Real-World Analogy
A class is an architectural floor plan. The plan itself is not a house — it has no rooms you can walk into. Every time a contractor builds from that plan, they produce a real house (object). Ten houses can be built from the same plan, each with different paint colors and furniture, but all sharing the same structure.

---

## How it works in Java

```java
// Defining the class (the blueprint)
class Dog {
    // Fields — data the object holds
    String name;
    int age;

    // Method — behavior the object has
    void bark() {
        System.out.println(name + " says: Woof!");
    }
}

// Creating objects from the class
public class Main {
    public static void main(String[] args) {
        Dog d1 = new Dog();   // object 1
        d1.name = "Bruno";
        d1.age = 3;

        Dog d2 = new Dog();   // object 2 — independent from d1
        d2.name = "Max";
        d2.age = 5;

        d1.bark(); // Bruno says: Woof!
        d2.bark(); // Max says: Woof!
    }
}
```

**Key mechanics:**
- `new Dog()` allocates memory on the heap and returns a reference
- `d1` and `d2` are references (like addresses), not the objects themselves
- Each object has its own copy of `name` and `age`

---

## Java Built-ins / Defaults that use this
| Built-in | Why it's relevant |
|---|---|
| `String` | A class. Every string literal like `"hello"` is a `String` object |
| `Scanner` | A class — you do `new Scanner(System.in)` to create an object |
| `System.out` | `out` is a static field of type `PrintStream` — itself an object |
| `Object` | Every class in Java implicitly extends `Object` — the root class of all classes |
| `ArrayList` | A class you instantiate with `new ArrayList<>()` |

---

## What it CAN do
- Bundle related data and behavior together in one unit
- Be instantiated multiple times — each object is independent
- Serve as a type — you can declare variables, parameters, return types using your class name
- Contain other objects as fields (composition)
- Be extended by other classes (inheritance)
- Implement interfaces

---

## What it CANNOT do / Limitations
- A class is not executable on its own — you need a `main` method or an entry point
- Fields are not automatically initialized to meaningful values unless you set them (they get defaults: `0`, `null`, `false`)
- Java does not support multiple class inheritance — a class can only `extend` one other class
- A `.java` file can only have one `public` class, and it must match the filename

---

## When to use it
- Use a class when you have a concept with both **data** (what it knows) and **behavior** (what it does)
- Use it when you need multiple independent instances of the same structure
- Use it when you want to model a real-world or domain entity — `User`, `Order`, `Product`, `Invoice`

---

## When NOT to use it / Common misuse
- Don't create a class just to hold utility/helper methods — use a class with all-static methods or just think about whether it belongs elsewhere
- Don't put everything in one class (God Class) — if a class has 20+ fields and 30+ methods, it's doing too much
- Don't use a class when a simple primitive or existing type is enough — you don't need a `class Age` to store someone's age

---

## How it's used in real projects
- **Spring Boot**: Every `@Service`, `@Controller`, `@Repository` is a class. Spring creates objects (beans) from them and manages their lifecycle
- **JPA/Hibernate**: `@Entity` classes map directly to database tables — each object = one row
- **DTOs**: Classes like `UserResponseDto` are plain classes used purely to carry data between layers
- **Design patterns**: Singleton, Factory, Build
er — all implemented as classes with controlled instantiation

---

## Prefer this → Switch when

| Start with | Switch to | When |
|---|---|---|
| Concrete class | Abstract class | You realize some methods have no sensible default and must be forced on subclasses |
| Concrete class | Interface | You want multiple unrelated classes to share a contract without sharing code |
| One big class | Multiple smaller classes | A class starts owning data and behavior that logically belongs to a different concept |

---

## Common Mistakes

**Mistake 1: Confusing the reference with the object**

```java
// BROKEN thinking
Dog d1 = new Dog();
Dog d2 = d1;       // beginners think this copies the object
d2.name = "Max";
System.out.println(d1.name); // prints "Max" — d1 and d2 point to the SAME object
```

```java
// CORRECT — if you want a separate object, create one
Dog d2 = new Dog();
d2.name = "Max";
```

**Mistake 2: Accessing fields before setting them**

```java
Dog d = new Dog();
System.out.println(d.name.length()); // NullPointerException — name is null by default
```

```java
// Fix — always initialize before use
d.name = "Bruno";
System.out.println(d.name.length());
```

---

## Interactions with other concepts
- **`static` keyword**: Static fields and methods belong to the *class*, not any object. `Dog.count` vs `d1.name` — one is shared, one is per-object
- **Constructors**: The mechanism used to properly initialize an object at creation time — directly tied to classes
- **Inheritance**: A class can extend another class, inheriting its fields and methods
- **`null`**: A reference variable that hasn't been pointed at any object holds `null`. Calling any method on it gives `NullPointerException`
- **Garbage Collection**: When no reference points to an object, Java automatically frees its memory — you don't delete objects manually

---

## Placement / Interview traps

**Trap 1: What is the output?**
```java
Dog a = new Dog();
Dog b = a;
a.name = "Rocky";
b.name = "Tommy";
System.out.println(a.name);
```
**Answer: `Tommy`** — `a` and `b` point to the same object. The second assignment overwrites the first.

---

**Trap 2: How many objects are created?**
```java
Dog d1 = new Dog();
Dog d2 = new Dog();
Dog d3 = d1;
```
**Answer: 2 objects** — `d3 = d1` copies the reference, not the object. Only `new` creates objects.

---

**Trap 3: What are the default values of fields?**
```java
class Box {
    int size;
    String label;
    boolean active;
}
Box b = new Box();
System.out.println(b.size);   // ?
System.out.println(b.label);  // ?
System.out.println(b.active); // ?
```
**Answer: `0`, `null`, `false`** — Java initializes instance fields to their type defaults. Local variables inside methods do NOT get defaults and won't compile if used uninitialized.

---

## Quick Reference (code)

```java
// ─── Class Definition ───────────────────────────────────────────
class Car {
    // Instance fields — each object gets its own copy
    String brand;
    int speed;

    // Method using instance fields
    void describe() {
        System.out.println(brand + " going at " + speed + " km/h");
    }

    void accelerate(int amount) {
        speed += amount;
    }
}

// ─── Main ────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {

        // Creating two independent objects
        Car c1 = new Car();
        c1.brand = "Toyota";
        c1.speed = 60;

        Car c2 = new Car();
        c2.brand = "BMW";
        c2.speed = 100;

        c1.describe();       // Toyota going at 60 km/h
        c2.describe();       // BMW going at 100 km/h

        c1.accelerate(20);
        c1.describe();       // Toyota going at 80 km/h
        // c2 is unaffected — objects are independent

        // Reference copy — NOT object copy
        Car c3 = c1;
        c3.brand = "Honda";
        System.out.println(c1.brand); // Honda — same object
    }
}
```

---

Say **next** for topic 2 — Constructors.