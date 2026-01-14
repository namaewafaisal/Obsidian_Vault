## 2. Inheritance (OOP Principle)

Inheritance is one of the cornerstones of Object-Oriented Programming (OOP) and is fundamental to Java.

### Definition and Purpose

1. **Acquiring Properties:** Inheritance is the process by which one object acquires the properties (methods and data) of another object.

2. **Hierarchical Classification:** It supports the concept of hierarchical (top-down) classification, making knowledge manageable.

3. **Specialization:** It allows the creation of a general class defining traits common to a set of related items, which can then be inherited by other, more specific classes that add only unique elements.

4. **Code Reuse:** A major advantage is that it allows the **reuse of code** and saves time by preventing the re-creation of features found in the superclass. It prevents the repetition of method definitions and values across classes.

5. **Complexity Management:** This mechanism allows object-oriented programs to grow in complexity **linearly rather than geometrically**.

### Terminology and Relationship

- **Parent/Super/Base Class:** The class that is inherited is called the **superclass**, **parent class**, or **base class**.
- **Child/Sub/Derived Class:** The class that does the inheriting is called the **subclass**, **child class**, or **derived class**.
- **Is-A Relationship:** Inheritance represents a specific relationship, typically referred to as the "**is a**" relationship (e.g., an `AdvancedCalculator` is a `Calculator`).

### Implementation Details

#### A. The `extends` Keyword

- In Java, a class incorporates the definition of another class using the **`extends`** keyword.
- A subclass is a specialized version of its superclass; it inherits all members defined by the superclass and adds its own unique elements.
- A superclass can be used independently as a stand-alone class.

#### B. The `Object` Class (Ultimate Superclass)

- **Every class in Java extends the `Object` class**.
- The `Object` class is the superclass of all other classes.
- Even if `extends Object` is not explicitly mentioned, it is implicitly extended (it's "there, you can't see it").
- The `Object` class defines methods available to every object, such as `equals()`, `toString()`, `clone()`, `getClass()`, `hashCode()`, `wait()`, and `notify()`.

#### C. Visibility and Access Control

- A subclass includes all members of its superclass, but it **cannot access members of the superclass declared as `private`**.
- Private members remain private to their defining class and are not accessible by code outside that class, including subclasses.

### The `super` Keyword

The `super` keyword allows a subclass to refer to its **immediate superclass**.

It has two general forms:

1. **Calling the Superclass Constructor:**
    
    - The form `super(arg-list)` calls the constructor defined by the superclass.
    - This call **must always be the first statement executed** inside a subclass' constructor.
    - If a superclass constructor requires parameters, all subclasses must pass those parameters "up the line," regardless of whether the subclass needs those parameters itself.
    - In a multilevel hierarchy, `super()` always refers to the constructor in the closest superclass.
    - A superclass variable can be used to reference any object derived from that class, allowing a subclass object (e.g., `BoxWeight`) to be passed to the superclass constructor (e.g., `Box(Box ob)`). When a class uses `super()`, it is implicitly calling the constructor of its superclass, which might ultimately be the `Object` class.
2. **Accessing a Hidden Member:**
    
    - The second use of `super` is to access a member of the superclass that has been hidden by a member of the subclass (e.g., during method overriding).

### Types of Inheritance

- **Single Level Inheritance:** Occurs when there are only two classes in the hierarchy (one superclass and one subclass).
- **Multi-Level Hierarchy/Inheritance:** Occurs when you build hierarchies with multiple layers, where a subclass also acts as a superclass for another class (e.g., A extends B, and C extends A). A subclass inherits all traits found in all of its superclasses.

### Restrictions in Java

- **Multiple Inheritance is Not Supported:** Java does not support the inheritance of multiple superclasses into a single subclass. Attempting to use a syntax like `extends A, B` results in an error. This feature was removed to avoid the **Ambiguity Problem** (a confusion over which parent's features to choose if both define the same method).

### Interaction with `final` Keyword

The `final` keyword has specific applications related to inheritance:

- **Preventing Inheritance of a Class:** Preceding a class declaration with `final` prevents that class from being inherited (no one can extend it). Declaring a class as `final` implicitly declares all its methods as `final` too.
- **Preventing Method Overriding:** Specifying `final` as a modifier at the start of a method's declaration disallows that method from being overridden by a subclass.

### Interaction with Polymorphism and Casting

- **Superclass Reference:** A reference variable of a superclass can be assigned a reference to any subclass derived from that superclass.
- **Upcasting:** Assigning a subclass object to a parent class reference (e.g., `Parent p = new Child();`) is referred to as **upcasting**.
- **Downcasting:** Explicitly casting a parent reference back to a child reference (e.g., `Child c = (Child) p;`) is referred to as **downcasting**.
- These are generally categorized as **typecasting**.
- Inheritance is required for dynamic method dispatch, which forms the basis for **runtime polymorphism**.

---
