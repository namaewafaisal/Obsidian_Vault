## 6. Reference Data Types (Non-Primitive Types)

In Java, all variable types are classified as either **primitive types** (like `int` or `float`) or **reference types**. Reference types form the backbone of Object-Oriented Programming (OOP) in Java, as they deal exclusively with objects.

### Definition and Core Concept

1. **Storage Mechanism:** Unlike primitive types, which store their actual literal value directly in memory, a reference type variable does not store the object itself. Instead, it stores a **reference**, which acts as an **address**, **link**, or **pointer** to the location where the actual object resides in memory.
2. **Origin:** A reference type is defined by a **class**, **interface**, **enum**, or **array**.
3. **Objects:** Reference types are typically used to refer to **objects**. An object is a physical reality because it occupies space in memory.
4. **Instantiation:** Objects referred to by reference variables are instantiated dynamically at run time using the **`new` operator**.

### Memory Allocation (Stack and Heap)

Reference types fundamentally rely on the two main memory areas managed by the Java Virtual Machine (JVM):

|Memory Component|Location|Content Stored|Citations|
|:--|:--|:--|:--|
|**Reference Variable**|**Stack Memory**|The **address** (pointer) pointing to the object.||
|**Object Data**|**Heap Memory**|The actual object instance and its **instance variables**.||
|**Relationship**|Link|The address in the Stack creates a **link** that allows the program to jump to the object in the Heap.||

### Key Characteristics and Behaviors

#### A. The `null` Value

- Reference variables can hold the value **`null`**.
- The `null` value indicates that the reference variable is currently **not referring to any object** in the Heap.
- If a class variable (instance variable) of a reference type is not explicitly assigned a value, its default value will be `null`.

#### B. Reference Assignment and Equality

- When one reference variable is assigned to another (e.g., `Car c2 = c1;`), memory is **not allocated**, and **no copy of the object is created**.
- Both reference variables point to the **exact same object** in the Heap.
- Consequently, any change made to the object through one reference (e.g., modifying a property via `c1`) will be visible through the other reference (`c2`).
- Reference variables are similar to C/C++ pointers but are strictly controlled for safety; they cannot be manipulated like integers or forced to point to arbitrary memory locations.
- When comparing two reference variables using the relational operator **`==`**, Java compares their **references (memory addresses/pointers)**, not the content of the objects they point to.

#### C. Passing to Methods

- Java uses **call-by-value** to pass all arguments, regardless of type.
- However, when a reference type is passed, the _value_ being copied is the **memory address** of the object.
- Therefore, a method can use the copied reference to access and modify the original object in the Heap.

### Common Reference Types

#### 1. The `String` Class

- The `String` type is fundamental but is **not a primitive type**.
- `String` is a **class** (specifically, an immutable class), and objects of type `String` are **immutable** once created.
- `String` variables store a **pointer** to the string's location in memory. This is why simple equality checks (`==`) often fail for strings, necessitating the use of the `equals()` method (which checks content).
- For cases where mutable (changeable) strings are required, Java provides the non-primitive classes `StringBuffer` and `StringBuilder`.

#### 2. Array Variables

- An **array** is a group of like-typed variables referenced by a common name.
- Arrays of any type (including primitive types or other reference types) can be created.
- Even when an array holds primitive data, the **array variable itself is a reference type**. It must be dynamically allocated memory using the `new` operator.

#### 3. Type Wrapper Classes

- To allow primitive types to be treated as objects (often necessary for use with collections, which only store references), Java provides **type wrappers**.
- These wrapper classes encapsulate a primitive type within an object.
- Examples include `Integer` (wrapper for `int`), `Double` (wrapper for `double`), `Character` (wrapper for `char`), and `Boolean` (wrapper for `boolean`).
- **Autoboxing and Unboxing** make the process seamless, as Java automatically performs the necessary wrapping when storing primitive values into an object context, and unwrapping when retrieving them.