## Methods in Java

Methods are blocks of code that perform a specific task or set of operations. They are a fundamental concept in Java programming, promoting code reusability and modularity.

### 1. What are Methods?

A **method** is a collection of statements that are grouped together to perform an operation. In Java, methods are associated with classes and objects.

|Key Detail|Description|
|:--|:--|
|**Modularity**|Methods break down complex programs into smaller, manageable, and organized units. Each method focuses on a single, well-defined task.|
|**Reusability**|Once a method is written, it can be called and executed multiple times from different parts of the program without rewriting the code.|
|**Abstraction**|Methods allow us to think about *what* an operation does, without necessarily worrying about *how* it does it.|

### 2. Method Declaration

The general form of a method declaration is:

```java
accessModifier static/non-static returnType methodName(parameterList) {
    // method body
    // statements;
    // return value; (if returnType is not void)
}
```

|Component|Description|
|:--|:--|
|**`accessModifier`**|Specifies the visibility of the method (e.g., `public`, `private`, `protected`, default).|
|**`static/non-static`**|`static` indicates a class method (belongs to the class), `non-static` indicates an instance method (belongs to an object).|
|**`returnType`**|The data type of the value the method returns. If the method does not return a value, its return type is `void`.|
|**`methodName`**|A unique identifier for the method. By convention, method names start with a lowercase letter and use camel case.|
|**`parameterList`**|A comma-separated list of input parameters, each with its data type and name. If a method has no parameters, an empty pair of parentheses `()` is used.|
|**Method Signature**|The combination of the `methodName` and `parameterList` (in order and type) uniquely identifies a method within a class. The return type and access modifiers are NOT part of the signature.|

### 3. Return Types

The `returnType` specifies the type of data a method will send back to the caller.

|Return Type|Description|Example|
|:--|:--|:--|
|**`void`**|Indicates that the method does not return any value. It simply performs its task.|`public void printMessage() { ... }`|
|**Primitive Type**|The method returns a value of a primitive data type (e.g., `int`, `double`, `boolean`).|`public int calculateSum(int a, int b) { return a + b; }`|
|**Reference Type**|The method returns an object or an array.|`public String getName() { return "Java"; }`|

### 4. Parameters

Parameters are variables that receive values passed to a method.

|Type of Parameter|Description|
|:--|:--|
|**Formal Parameters**|The variables declared in the method signature, which receive the values when the method is called. (e.g., `int a, int b` in `calculateSum` method).|
|**Actual Parameters (Arguments)**|The actual values passed to the method when it is invoked. (e.g., `5, 10` when calling `calculateSum(5, 10)`).|
|**Pass-by-Value**|In Java, all primitive data types (and object references) are passed to methods **by value**. This means a copy of the argument's value is passed. Changes to the parameter inside the method do not affect the original argument outside the method.|
|**Variable-Length Arguments (Varargs)**|A method can take a variable number of arguments of a specified type. Declared using an ellipsis (`...`) after the type (e.g., `void printNumbers(int... numbers)`).|

### 5. Method Invocation (Calling a Method)

To execute a method, you must call or invoke it.

*   **For non-static (instance) methods:** You need an object of the class to call the method.
    ```java
    ClassName objectName = new ClassName();
    objectName.methodName(arguments);
    ```
*   **For static (class) methods:** You can call the method directly using the class name.
    ```java
    ClassName.staticMethodName(arguments);
    ```

### 6. Method Overloading

**Method overloading** occurs when a class has multiple methods with the **same name** but **different method signatures**.

|Key Detail|Description|
|:--|:--|
|**Same Name**|All overloaded methods share the same name.|
|**Different Signatures**|Their parameter lists must differ in terms of: <br> - Number of parameters <br> - Data types of parameters <br> - Order of data types of parameters <br> (Return type alone is not sufficient to overload a method).|
|**Compile-time Polymorphism**|Java determines which overloaded method to call at compile time based on the arguments provided. This is also known as static polymorphism.|

**Example:**
```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```

### 7. The `return` Statement

The `return` statement is used to exit from a method and optionally return a value.

|Aspect|Description|
|:--|:--|
|**Exiting a Method**|When a `return` statement is encountered, the execution of the method immediately terminates, and control returns to the caller.|
|**Returning a Value**|If a method has a non-`void` return type, the `return` statement *must* be followed by an expression whose type is compatible with the method's declared return type.|

### 8. Static Methods

A `static` method belongs to the class itself rather than to any specific instance (object) of the class.

|Key Detail|Description|
|:--|:--|
|**Class-level Access**|Static methods can be called directly using the class name, without creating an object of the class.|
|**No `this` Keyword**|Static methods cannot use the `this` keyword because `this` refers to an instance, and static methods are not tied to an instance.|
|**Instance Member Restriction**|A static method can directly access only other static members (variables or methods) of the class. It cannot directly access instance variables or call non-static methods without an object reference.|
|**Utility Methods**|Often used for utility functions that don't rely on the state of an object (e.g., `Math.max()`, `System.out.println()`).|
