## 1. Encapsulation (OOP Principle)

Encapsulation is one of the three core principles of Object-Oriented Programming (OOP), alongside inheritance and polymorphism.

### Definition and Purpose

1. **Binding Code and Data:** Encapsulation is the mechanism that binds together the code and the data it manipulates, keeping both safe from outside interference and misuse.
2. **Protective Wrapper:** It can be conceptualized as a protective wrapper that prevents arbitrary access to the code and data defined outside the wrapper.
3. **Controlled Access:** Access to the code and data inside this wrapper is strictly controlled through a **well-defined interface**.
4. **Development Approach:** Encapsulation is a development approach that imposes **access restrictions** to ensure that an object's properties are accessed safely.
5. **Interface to Data:** By encapsulating data and the code that manipulates it, the methods define a consistent and **controlled interface** to the class’s data. Using the class through its methods means you don't have to worry about the details of its internal implementation.

### Implementation via Access Control (Scope)

Encapsulation relies heavily on Java's concept of **scope**. Variables defined within a class (instance variables) typically have their access controlled using access modifiers.

|Scope Classification (Access Modifier)|Access Allowed|Purpose/Detail|
|:--|:--|:--|
|**Private**|Only by code within its own class.|This is the primary way to restrict external access to variables, which is recommended practice.|
|**Protected**|By code within its own package; in other packages, only by a subclass.|Useful when a variable or method should be accessed only by subclasses in other packages.|
|**Public**|By code from anywhere.|Often used for methods so they can be accessed widely.|
|**Default** (Package-Protected)|Only from within its current package.|Java assumes this scope if none is explicitly declared. It should generally be avoided, favoring explicit `private`, `public`, or `protected`.|

### Accessing Private Data (Getters and Setters)

When instance variables are marked `private`, the only way to access or modify them from outside the class is through specially defined public methods.

- **Binding Data and Methods:** Encapsulation is achieved by binding the data with these methods, ensuring that access to the variables occurs only through the methods.
- **Getters (Accessors):** These methods are used to **fetch** the value of a variable. By convention, they usually start with `get` followed by the variable name (e.g., `getAge`).
- **Setters (Mutators):** These methods are used to **set** or modify the value of a variable. By convention, they usually start with `set` followed by the variable name (e.g., `setAge`).
- **Controlling Modifications:** From a programmer's perspective, controlling access via getters and setters is advantageous for debugging, as determining how or where a property is being changed requires checking only the setter method.

### Summary Insight

Encapsulation allows you to migrate implementations of a class over time without breaking the code that depends on the **public interface** defined by its methods.
