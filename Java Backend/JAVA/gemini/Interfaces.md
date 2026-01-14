## Interfaces in Java

In Java, an **interface** is a blueprint of a class. It specifies a contract that implementing classes must adhere to. Interfaces are used to achieve abstraction and multiple inheritance of type.

### 1. What is an Interface?

An **interface** is a collection of abstract methods and static constant fields. It defines a set of methods that a class must implement.

|Key Detail|Description|
|:--|:--|
|**Blueprint / Contract**|An interface acts as a formal contract. Any class that `implements` an interface agrees to provide implementations for all the abstract methods declared in that interface.|
|**Pure Abstraction**|Historically, interfaces could only contain abstract methods and public static final fields, offering a higher degree of abstraction than abstract classes.|
|**Achieves Abstraction**|Hides the implementation details from the user.|
|**Achieves Multiple Inheritance (of Type)**|Unlike classes, a class can implement multiple interfaces, allowing it to inherit multiple behaviors.|

### 2. Interface Declaration

An interface is declared using the `interface` keyword.

```java
[access modifier] interface InterfaceName {
    // fields (implicitly public static final)
    // abstract methods (implicitly public abstract)
    // default methods (Java 8+)
    // static methods (Java 8+)
    // private methods (Java 9+)
}
```

### 3. Abstract Methods

Abstract methods are the core components of an interface's contract.

|Key Detail|Description|
|:--|:--|
|**Implicitly `public abstract`**|Prior to Java 8, all methods in an interface were implicitly `public abstract`. Even if you omit these keywords, the compiler adds them.|
|**No Method Body**|Abstract methods have no implementation; they end with a semicolon.|
|**Purpose**|Define the behavior that implementing classes must provide.|

**Example:**
```java
interface Drawable {
    void draw(); // implicitly public abstract
    // int getArea();
}
```

### 4. Constants

Fields declared in an interface are implicitly `public static final`.

|Key Detail|Description|
|:--|:--|
|**Implicitly `public static final`**|All variables declared inside an interface are constants. They are automatically `public`, `static`, and `final`.|
|**Initialization**|They must be initialized at the time of declaration.|

**Example:**
```java
interface Constants {
    int MAX_VALUE = 100; // implicitly public static final
    String DEFAULT_NAME = "Unnamed";
}
```

### 5. Implementing Interfaces

A class uses the `implements` keyword to indicate that it will adhere to the contract defined by an interface.

|Key Detail|Description|
|:--|:--|
|**`implements` Keyword**|Used by a class to indicate that it provides implementations for the abstract methods of an interface.|
|**Multiple Implementations**|A class can implement multiple interfaces, separating them with commas (e.g., `class MyClass implements InterfaceA, InterfaceB`).|
|**Mandatory Implementation**|Any concrete (non-abstract) class implementing an interface must provide an implementation for all abstract methods declared in that interface. If it doesn't, the class itself must be declared `abstract`.|

**Example:**
```java
class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```

### 6. Default Methods (Java 8+)

Default methods allow new functionality to be added to interfaces without breaking existing implementations.

|Key Detail|Description|
|:--|:--|
|**`default` Keyword**|Allows an interface to provide a default implementation for a method. Classes implementing the interface can use this default implementation or override it.|
|**Backward Compatibility**|Enables adding methods to interfaces without forcing all existing implementing classes to change.|
|**Purpose**|Provides flexibility to extend interfaces while maintaining compatibility.|

**Example:**
```java
interface Logger {
    void log(String message);
    default void logInfo(String message) {
        log("INFO: " + message);
    }
}
```

### 7. Static Methods (Java 8+)

Static methods in interfaces are utility methods that belong to the interface itself.

|Key Detail|Description|
|:--|:--|
|**`static` Keyword**|Similar to static methods in classes, they can be called directly on the interface name.|
|**Purpose**|Often used for utility functions that are logically related to the interface but don't require an implementing object.|
|**Not Inherited/Overridden**|Static interface methods are not inherited by implementing classes and cannot be overridden.|

**Example:**
```java
interface Utility {
    static int add(int a, int b) {
        return a + b;
    }
}
// Usage: int sum = Utility.add(5, 3);
```

### 8. Private Methods (Java 9+)

Private methods in interfaces allow for code reuse among default and static methods within the same interface.

|Key Detail|Description|
|:--|:--|
|**`private` Keyword**|Allows an interface to contain private helper methods that can be called by `default` or `static` methods within that interface.|
|**Code Reusability**|Helps to refactor common code from multiple default/static methods into a single private method, improving modularity within the interface.|
|**Not Accessible Outside**|Private methods are not inherited or accessible from implementing classes or outside the interface.|

### 9. Functional Interfaces (Java 8+)

A **functional interface** is an interface that contains exactly one abstract method.

|Key Detail|Description|
|:--|:--|
|**Single Abstract Method (SAM)**|The key characteristic is having only one abstract method. It can have any number of default, static, and private methods.|
|**`@FunctionalInterface` Annotation**|An optional annotation that can be used to mark an interface as functional. The compiler will enforce the single abstract method rule if this annotation is present.|
|**Lambda Expressions**|Functional interfaces are crucial for working with Lambda Expressions and the Stream API in Java 8+, as they provide the target type for lambda expressions.|

**Example:**
```java
@FunctionalInterface
interface MyFunction {
    int apply(int x);
}
```

### 10. Marker Interfaces

A **marker interface** is an interface that contains no methods or fields.

|Key Detail|Description|
|:--|:--|
|**Empty Interface**|It serves to "mark" a class, indicating that the class possesses a certain capability or property.|
|**Runtime Information**|Provides runtime type information about objects, allowing tools and frameworks to apply special handling to classes that implement them.|
|**Examples**|`Serializable` (indicates that a class's objects can be written to a stream), `Cloneable` (indicates that an object can be safely cloned).|

### 11. Interface vs. Abstract Class

While both interfaces and abstract classes are used to achieve abstraction, they have key differences.

|Feature|Interface|Abstract Class|
|:--|:--|:--|
|**Type**|`interface`|`abstract class`|
|**Multiple Inheritance**|A class can `implement` multiple interfaces.|A class can `extend` only one abstract class.|
|**Constructors**|Cannot have constructors.|Can have constructors.|
|**Fields**|All fields are implicitly `public static final`.|Can have `public`, `private`, `protected` fields, `static` or `non-static`, `final` or `non-final`.|
|**Methods**|Before Java 8: only `public abstract`. <br> Java 8+: `default`, `static`, `public abstract`. <br> Java 9+: `private` methods.|Can have abstract and non-abstract (concrete) methods.|
|**State**|Cannot have instance variables (state).|Can have instance variables (state).|
|**Purpose**|Defines a contract/behavior that implementing classes must follow, focusing on "what" a class can do.|Provides a common base for related classes, allowing some common implementation and some abstract methods to be implemented by subclasses, focusing on "is-a" relationship.|