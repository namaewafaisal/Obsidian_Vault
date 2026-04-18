# **`instanceof` Operator**

### Definition and Usage

The `instanceof` operator checks whether an object is an **instance of a specific class** or implements a certain interface.

| Operator     | Example                    | Description                                                               |
| ------------ | -------------------------- | ------------------------------------------------------------------------- |
| `instanceof` | `obj instanceof ClassName` | Returns `true` if `obj` is an instance of `ClassName`. Otherwise `false`. |

---

### Example
```java
String text = "Hello";
System.out.println(text instanceof String);  // true
System.out.println(text instanceof Object);  // true
System.out.println(text instanceof Integer); // false
```

### Usage Notes

- **Purpose:** Ensures **type safety** before casting objects.
- Commonly used when working with **inheritance** or **polymorphism**.

```java
class Animal {}
class Dog extends Animal {}

Animal a = new Dog();

if (a instanceof Dog) {
    Dog d = (Dog) a; // Safe downcasting
    System.out.println("a is a Dog");
}

```