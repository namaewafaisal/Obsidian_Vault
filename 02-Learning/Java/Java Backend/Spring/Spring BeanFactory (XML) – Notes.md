Below are **short, clean notes** explaining **exactly what is happening in that code**.
Only the **main ideas**, no extras.

![Image](https://media.geeksforgeeks.org/wp-content/uploads/20210702121645/m3.png)

![Image](https://docs.spring.io/spring-framework/docs/2.5.x/reference/images/eclipse-setup-3.png)

![Image](https://i0.wp.com/javaconceptoftheday.com/wp-content/uploads/2023/08/Spring_IoC_Container.png?resize=1015%2C507\&ssl=1)

---

# Spring BeanFactory (XML) – Notes

## What This Code Demonstrates

This is **manual Spring IoC using XML configuration**, before Spring Boot.

Spring:

* Creates the object
* Stores it in the container
* Returns it when requested

---

## Key Classes Used

### `BeanFactory`

* **Lightweight IoC container**
* Responsible for:

  * Creating beans
  * Returning beans

Used for basic Spring concepts.

---

### `XmlBeanFactory`

* Implementation of `BeanFactory`
* Reads bean definitions from an **XML file**

---

### `FileSystemResource("spring.xml")`

* Tells Spring **where the XML config file is**
* Looks for `spring.xml` in the file system path

If file not found → `FileNotFoundException`

---

## Code Breakdown (Line by Line Logic)

### 1. Create the container

```java
BeanFactory factory =
    new XmlBeanFactory(new FileSystemResource("spring.xml"));
```

What happens:

* Spring reads `spring.xml`
* Parses bean definitions
* Prepares objects (beans)

---

### 2. Get the object (bean)

```java
Alien obj = (Alien) factory.getBean("alien");
```

What happens:

* Spring searches for bean with id `"alien"`
* Creates the `Alien` object (if not already)
* Injects dependencies (if any)
* Returns the object

You did **not** use `new Alien()`.

---

## Role of `spring.xml`

`spring.xml` contains:

```xml
<bean id="alien" class="com.telusko.SpringDemo.Alien"/>
```

This tells Spring:

* What object to create
* Which class to instantiate
* What name (`id`) to store it with

---

## Why Type Casting Is Needed

```java
(Alien) factory.getBean("alien");
```

Because:

* `getBean()` returns `Object`
* Java requires explicit casting

(Spring Boot avoids this using generics)

---

## Why Error Occurs (`FileNotFoundException`)

```
spring.xml not found
```

Reasons:

* File not in project root
* Wrong path
* Using `FileSystemResource` incorrectly

(Usually solved by using `ClassPathResource`)

---

## Important Takeaway (Core Concept)

> Spring creates objects **outside your code**, stores them in a container, and gives them to you when requested.

This is **IoC + Dependency Injection** in action.

---

## Modern Context (Just One Line)

* This is **old-style Spring**
* Spring Boot does this automatically
* XML is replaced by annotations

---

## One-Line Summary (Remember This)

> **BeanFactory reads XML, creates objects, and returns them instead of using `new`.**

That’s all you need for this topic.

If you want next (short):

* BeanFactory vs ApplicationContext
* Why XML was replaced
* How Spring Boot hides all this
* What `getBean()` really does internally
