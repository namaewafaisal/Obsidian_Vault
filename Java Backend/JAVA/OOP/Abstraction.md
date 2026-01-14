## 3. Abstraction (OOP Principle)

Abstraction is one of the core principles of Object-Oriented Programming (OOP), alongside encapsulation, inheritance, and polymorphism. It functions primarily to show essential information while hiding complex implementation details.

In Java, abstraction is realized through two main mechanisms: **Abstract Classes** and **Interfaces**.

### 3.1. Implementation via Abstract Classes

An **Abstract Class** is used when defining an **abstract concept** or entity, such as a `Car`.

| Detail                          | Description                                                                                                                                                                                                                                                                                     | Citations |
| :------------------------------ | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------- |
| **Conceptual Role**             | A class can represent an abstract concept, like `Car`. No one actually buys a generic `Car`; they buy a **real car** like `WagonR`.                                                                                                                                                             |           |
| **Declaration**                 | To represent this abstract nature, the class keyword is preceded by the **`abstract` keyword**.                                                                                                                                                                                                 |           |
| **Abstract Methods**            | An abstract class can contain one or more **abstract methods**. An abstract method is **declared but not defined** (it has no body). For example, the `Car` class might declare an `abstract` `drive()` method.                                                                                 |           |
| **Purpose of Abstract Methods** | Declaring a method as `abstract` gives the abstract idea that the car _should_ have that feature.                                                                                                                                                                                               |           |
| **Instantiation Restriction**   | An abstract class cannot be instantiated directly (you cannot create an object of an abstract class).                                                                                                                                                                                           |           |
| **Subclass Responsibility**     | If a class contains an abstract method, the class must be declared `abstract`. It becomes the responsibility of the concrete subclass (like `WagonR`) that `extends` the abstract class (`Car`) to **define** or **implement** that abstract method (e.g., implementing the `drive()` feature). |           |

### 3.2. Implementation via Interfaces

An **Interface** is another primary tool for achieving abstraction, focusing on defining a contract or a capability that a class must fulfill.

| Detail                              | Description                                                                                                                                                                                                                                                               | Citations |
| :---------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :-------- |
| **Core Function**                   | Interfaces are used widely in Java to define components and framework capabilities, such as those related to threading (`Runnable`), collections (`Collection`, `List`, `Set`, `Map`), or resource management (`AutoCloseable`).                                          |           |
| **`AutoCloseable` Example**         | If a class or interface implements `AutoCloseable`, it means that resource will be automatically closed when used within a **try-with-resources statement**.                                                                                                              |           |
| **Functional Interfaces (SAM)**     | An interface that contains **only one method** is known as a **Functional Interface** or a **Single Abstract Method (SAM) interface**.                                                                                                                                    |           |
| **Lambda Expression Compatibility** | Functional Interfaces are critical because they allow the use of **Lambda Expressions**. For example, the `Consumer` interface, which takes an object and performs an action (`accept(T objRef)`), is a functional interface often implemented using a lambda expression. |           |
| **Comparison to Normal Interfaces** | Normal interfaces typically have two or more methods, whereas functional interfaces have only one.                                                                                                                                                                        |           |

---