## Exception Handling in Java

**Exception Handling** is a powerful mechanism in Java to manage runtime errors and abnormal conditions, ensuring the smooth and robust execution of a program.

### 1. What are Exceptions?

An **exception** is an event, which occurs during the execution of a program, that disrupts the normal flow of the program's instructions.

|Key Detail|Description|
|:--|:--|
|**Runtime Errors**|Exceptions represent errors or unusual conditions that occur during the execution phase, unlike compile-time errors (syntax errors) which prevent compilation.|
|**Abnormal Conditions**|They indicate that something unexpected or problematic has happened, such as trying to divide by zero, accessing an array out of bounds, or a file not being found.|
|**Graceful Termination**|Proper exception handling allows a program to deal with these abnormal conditions gracefully, preventing abrupt crashes and providing user-friendly feedback.|

### 2. The Exception Hierarchy

All exceptions and errors in Java are subclasses of the `java.lang.Throwable` class.

```
Throwable
├── Error
└── Exception
    ├── IOException
    ├── SQLException
    └── RuntimeException
        ├── ArithmeticException
        ├── NullPointerException
        ├── IndexOutOfBoundsException
        └── ... (many others)
```

|Category|Description|Examples|Handling Requirement|
|:--|:--|:--|:--|
|**`Error`**|Represents serious problems that a reasonable application should not try to catch. These are typically external to the application and indicate unrecoverable conditions.|`OutOfMemoryError`, `StackOverflowError`|Not typically handled by applications; indicate fatal issues.|
|**`Exception`**|Represents conditions that an application might want to catch. These are divided into two main types: checked and unchecked.|`IOException`, `SQLException` (Checked)<br>`RuntimeException`, `NullPointerException` (Unchecked)|**Checked:** Must be handled or declared. <br>**Unchecked:** Optional to handle.|
|**Checked Exceptions**|Exceptions that are checked at compile time. If a method can throw a checked exception, it must either handle the exception (using `try-catch`) or declare it (using `throws`).|`FileNotFoundException`, `IOException`, `SQLException`|**Mandatory** to handle or declare.|
|**Unchecked Exceptions**|Exceptions that are not checked at compile time. These are typically programming errors. They are subclasses of `RuntimeException`.|`ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`|**Optional** to handle; usually indicates a bug in the code.|

### 3. `try-catch` Block

The `try-catch` block is used for handling exceptions. The `try` block contains the code that might throw an exception, and the `catch` block handles the exception if it occurs.

|Component|Description|
|:--|:--|
|**`try` Block**|Encloses the code segment that is susceptible to throwing an exception. If an exception occurs within this block, the normal flow is aborted, and control is transferred to the appropriate `catch` block.|
|**`catch` Block**|Follows a `try` block. It specifies the type of exception it can handle and contains the code to execute when that exception is caught. A `try` block can have multiple `catch` blocks for different exception types.|
|**Multiple `catch` Blocks**|When multiple `catch` blocks are present, they are evaluated in order. The first `catch` block whose exception type matches or is a superclass of the thrown exception is executed. **More specific exception types must come before more general ones.**|
|**Catching Multiple Exceptions (Java 7+)**|A single `catch` block can handle multiple exception types, separated by a vertical bar (`|`). This reduces code duplication.|`catch (IOException | SQLException e)`|

**Example:**
```java
try {
    int result = 10 / 0; // This will throw ArithmeticException
} catch (ArithmeticException e) {
    System.err.println("Cannot divide by zero: " + e.getMessage());
} catch (Exception e) { // General catch should be last
    System.err.println("An unexpected error occurred: " + e.getMessage());
}
```

### 4. `finally` Block

The `finally` block is an optional block that follows a `try-catch` block.

|Key Detail|Description|
|:--|:--|
|**Execution Guarantee**|Code inside the `finally` block is *always* executed, regardless of whether an exception occurred or was caught in the `try` block, or even if `return` is called within `try` or `catch`.|
|**Purpose**|Primarily used for cleanup operations that must be performed, such as closing files, database connections, or releasing system resources.|
|**Placement**|The `finally` block executes after the `try` block and any `catch` block, but before the code following the entire `try-catch-finally` construct.|

**Example:**
```java
FileWriter writer = null;
try {
    writer = new FileWriter("output.txt");
    writer.write("Hello, Java!");
} catch (IOException e) {
    System.err.println("Error writing to file: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close(); // Ensure writer is closed
        } catch (IOException e) {
            System.err.println("Error closing writer: " + e.getMessage());
        }
    }
}
```

### 5. `throw` Keyword

The `throw` keyword is used to explicitly throw an instance of an exception.

|Key Detail|Description|
|:--|:--|
|**Explicit Exception**|Allows a programmer to create and throw a new exception object, signaling an error condition.|
|**Syntax**|`throw new ExceptionType("Error message");`|
|**Flow**|When an exception is `throw`n, the normal flow of the program stops, and the Java runtime attempts to find an appropriate `catch` block.|

### 6. `throws` Keyword

The `throws` keyword is used in a method's signature to declare that the method might throw one or more specified checked exceptions.

|Key Detail|Description|
|:--|:--|
|**Declaring Exceptions**|It informs the caller of the method that they must either handle the declared exception(s) or re-declare them themselves.|
|**Syntax**|`public void myMethod() throws IOException, SQLException { ... }`|
|**Purpose**|Used for checked exceptions to ensure that the calling code is aware of and properly handles potential problems.|
|**Unchecked Exceptions**|It is not mandatory to declare unchecked exceptions (`RuntimeException` and its subclasses) using `throws`, as they are assumed to be handled by program logic or indicate a bug.|

### 7. Custom Exceptions

Developers can create their own custom exception classes to represent specific error conditions unique to their applications.

|Key Detail|Description|
|:--|:--|
|**Creating Custom Exceptions**|To create a custom checked exception, extend `java.lang.Exception`. To create a custom unchecked exception, extend `java.lang.RuntimeException`.|
|**Constructor**|Custom exceptions typically have constructors that call the superclass constructor, often taking a message string.|
|**Usage**|Allows for more meaningful error reporting and specialized handling logic in complex applications.|

**Example:**
```java
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Usage
public void checkAge(int age) throws InvalidAgeException {
    if (age < 0 || age > 120) {
        throw new InvalidAgeException("Age must be between 0 and 120");
    }
}
```

### 8. `try-with-resources` (Java 7+)

This statement is a `try` statement that declares one or more resources. A resource is an object that must be closed after the program is finished with it. The `try-with-resources` statement ensures that each resource is closed at the end of the statement.

|Key Detail|Description|
|:--|:--|
|**Automatic Resource Management**|Automatically closes resources (like file streams, database connections) that implement the `java.lang.AutoCloseable` interface.|
|**Cleaner Code**|Eliminates the need for explicit `finally` blocks to close resources, making code cleaner and less error-prone.|
|**Syntax**|The resource declaration is placed inside the parentheses of the `try` statement.|

**Example:**
```java
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    System.err.println("Error reading file: " + e.getMessage());
}
// 'br' is automatically closed here
```