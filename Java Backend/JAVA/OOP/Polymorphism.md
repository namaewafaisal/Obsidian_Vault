## 4. Polymorphism (OOP Principle)

Polymorphism, alongside encapsulation and inheritance, is fundamental to Object-Oriented Programming (OOP) methodology, helping to organize complex programs. The term literally means "many forms" and refers to the ability of code, data, or methods to take on different behaviors or definitions depending on the context.

### A. Dynamic Polymorphism (Runtime Behavior)

The most common form of polymorphism in an inheritance hierarchy is realized at runtime through method overriding. This is sometimes referred to as **dynamic polymorphism**.

1. **Method Overriding Definition:** Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass.
2. **Specialized Behavior:** This allows a parent class to define a general method, while subclasses redefine that method to suit their specialized needs.
3. **Examples:**
    - If a base `Car` class defines an `openDoor()` method, a `McLarenP1` subclass must **override** this method because its doors open "upward" instead of the standard "outward". A `DeLorean` class would similarly need to override `openDoor()` again.
    - A subclass of a `Calculator` might override the `add` method to include an extra step, such as adding one to the sum of the two numbers. This new `add` method is said to be **overriding** the `add` method of the superclass.

### B. Implicit Polymorphism (Static Behavior)

Polymorphism can also be seen in the ability to define multiple methods or constructors within the same scope that share the same name but accept different parameters (often called method overloading).

1. **Constructors:** Classes often define multiple constructors to handle different initialization requirements. For example, the `Semaphore` class has constructors that allow you to specify just the initial permit count (`int num`), or the permit count and whether waiting threads are granted a permit in order (`int num, boolean how`).
2. **Thread Initialization:** The `Thread` class itself demonstrates this by defining seven different constructors, allowing for initialization with various combinations of `Runnable` objects, thread names, and `ThreadGroup` objects.
3. **Collection Methods:** Utility classes often define multiple methods with the same name to handle different types of data or arguments (e.g., the `Scanner` class defines multiple `hasNext` methods that check for different data types like `hasNextInt()`, `hasNextDouble()`, etc.).

### C. Polymorphism and Type Flexibility

Polymorphism enables flexibility in how objects are handled, particularly concerning variable types and collections.

- **Handling Diverse Objects:** One key use case is the ability to build **lists of base classes that can hold different types of objects** derived from that base class. This allows homogenous handling of heterogeneous types through the superclass interface.

---
