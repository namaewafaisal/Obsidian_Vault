## 5. Class and Object in Java

Java is fundamentally an **object-oriented language** (OOP), meaning virtually everything in the program revolves around objects. Object-Oriented Programming organizes a program around its **data (objects)** and the well-defined interfaces used to access that data.

### 5.1. The Class: The Blueprint

A **Class** is the basic building block of OOP. It serves as a **template** or **blueprint** for an object that is intended to be created later.

|Key Detail|Description|Citations|
|:--|:--|:--|
|**Logical Construct**|A class creates a **logical framework** that defines the relationships between its members. It is a conceptual construct that defines a new data type.||
|**Declaration**|A class is declared using the **`class` keyword**. The entire class definition must reside within curly braces (`{}`).||
|**Compilation**|When compiled, the Java compiler automatically puts each class into its own output file, resulting in a **`.class` file**. This `.class` file contains the bytecode version of the program.||
|**Naming Convention**|By convention, the name of the main class should match the name of the file that holds the program, and Java is **case-sensitive**. Classes are typically named using **camel case, starting with a capital letter**.||

#### Class Structure (Members)

A class defines the exact form and nature of an object by specifying the **data** it contains and the **code** that operates on that data.

1. **Instance Variables (Data/Properties):** These are the data items defined within a class. They are sometimes referred to as **member variables**.
    - The values stored in them determine what the object **knows**.
    - Variables defined within a class are called instance variables because **each instance (object) contains its own copy** of these variables.
2. **Methods (Code/Behavior):** These are the code blocks that operate on the instance data.
    - The methods define the interface to the class’s data and dictate what the object **does**.
    - Methods and variables together are referred to collectively as **members** of the class.

### 5.2. The Object: The Instance

An **Object** is an instance of a class. Declaring a class template does not create an actual object; objects are only created when explicitly instantiated.

|Key Detail|Description|Citations|
|:--|:--|:--|
|**Physical Reality**|An object has **physical reality** because it occupies space in memory.||
|**Properties and Behavior**|Every object has **two things**: it knows something (properties) and it does something (behavior).||

### 5.3. Object Creation and Referencing

Acquiring an object is generally a **two-step process**:

#### Step 1: Declaring the Reference Variable

A variable of the class type is declared (e.g., `Box mybox`). This variable **does not define the object itself** but is merely a **reference variable** that can hold a reference to an object.

#### Step 2: Instantiation using `new`

The actual, physical copy of the object is allocated **dynamically (at run time)** using the **`new` operator**.

- The `new` operator allocates memory for the object and returns a **reference** to it. This reference is, essentially, the memory address of the actual object.
- The reference is then stored in the declared reference variable.
- The class name followed by parentheses (e.g., `new Box()`) specifies the **constructor** for the class, which is called automatically.

**Example of Separation:**

```
Box mybox; // Declares the reference (Step 1)
mybox = new Box(); // Allocates the object and assigns reference (Step 2)
```

#### Reference Behavior (No Copying)

When one object reference variable is assigned to another (e.g., `Box b2 = b1;`), **no memory is allocated, and no copy of the original object is created**. Instead, both reference variables simply refer to the **exact same object** in memory. Any changes made through one reference variable will affect the object referred to by the other.

#### Accessing Members

The **dot operator (`.`)** is used to access the instance variables and methods within an object. It links the name of the object (the reference variable) with the name of the member.

### 5.4. Memory and Storage (Stack vs. Heap)

In the Java Virtual Machine (JVM), two major areas of memory govern object storage:

|Memory Area|Stores|Detail|Citations|
|:--|:--|:--|:--|
|**Heap**|**Objects** themselves (the physical instances) and all **Instance Variables** (properties) belonging to those objects.|The Heap memory is typically an open area that expands.||
|**Stack**|**Reference Variables** and all **Local Variables** (including method parameters).|Every method call (like `main()` or an instance method `add()`) has its own dedicated **stack**.||

**Garbage Collection:** Since objects are dynamically allocated using `new`, they will continue to exist as long as there is a reference to them somewhere in the program. When an object has no references pointing to it, it is eventually reclaimed by **garbage collection**.

### 5.5. Constructors (Object Initialization)

A **Constructor** is a special type of method used to **initialize an object immediately upon creation**.

1. **Automatic Calling:** The constructor is automatically called when the object is created, before the `new` operator completes. It will be called every time a new object is created.
2. **Name and Return Type:** A constructor **must have the same name as its class**. It has **no return type**, not even `void`.
3. **Purpose:** The constructor's job is to set up the internal state of the object so that it is fully initialized and ready for use immediately upon instantiation.
4. **Default Constructor:** If a class does not explicitly define any constructors, Java automatically supplies a **default constructor**. This default constructor initializes all instance variables to their default values (zero for numeric types, `null` for reference types, and `false` for booleans). Once you define your own constructor, the default constructor is no longer used.
5. **Parameterized Constructors:** Multiple constructors can be defined (constructor overloading) to allow an object to be initialized in different ways. These are called **parameterized constructors**.

### 5.6. The `this` Keyword

The **`this` keyword** is defined by Java to allow a method to refer to the object that invoked it.

- `this` is always a **reference to the current object** upon which the method was called.
- It is often used inside a constructor or method when a local variable (parameter) has the same name as an instance variable, to explicitly refer to the instance variable (`this.age = age`).
- `this` can also be used to call one constructor from another constructor within the same class (e.g., in a chained constructor call).