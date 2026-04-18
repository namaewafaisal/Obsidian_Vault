Below are **complete, clean notes on Spring Bean and XML bean configuration**, written **once, without repetition**, and limited to **what actually matters**.

---

# Spring Bean – Complete Notes

## 1. What Is a Bean

A **Bean** is:

> An **object created, managed, and controlled by the Spring IoC container**.

If Spring creates the object → it is a **bean**.
If you use `new` → it is **not** a bean.

---

## 2. Why Beans Exist

Beans allow:

* Inversion of Control (IoC)
* Dependency Injection (DI)
* Loose coupling
* Centralized object management

You don’t manage objects; **Spring does**.

---

## 3. Where Beans Are Defined (XML)

In old-style Spring, beans are defined in an **XML configuration file** (commonly `spring.xml`).

Example:

```xml
<bean id="alien" class="com.telusko.SpringDemo.Alien"/>
```

This tells Spring:

* **What to create** → `Alien` class
* **What name to store it with** → `alien`
* **Who creates it** → Spring container

---

## 4. Role of the XML File

The XML file:

* Acts as **instructions** for Spring
* Contains bean definitions
* Is read at application startup

Spring does **nothing magical** without this configuration.

---

## 5. Bean ID (Very Important)

```xml
<bean id="alien" class="..."/>
```

* `id` is the **unique name** of the bean
* Used to retrieve the bean

```java
factory.getBean("alien");
```

ID must be unique inside the container.

---

## 6. Bean Class

```xml
class="com.telusko.SpringDemo.Alien"
```

* Fully qualified class name
* Spring uses **reflection** to create the object
* Constructor is called automatically

---

## 7. How a Bean Is Created (Flow)

1. Spring reads XML
2. Finds `<bean>` definitions
3. Creates object using the class
4. Stores it inside the container
5. Returns it when requested

You never call the constructor directly.

---

## 8. Getting a Bean

```java
Alien obj = (Alien) factory.getBean("alien");
```

What happens:

* Spring looks up bean by ID
* Returns the managed object
* Same instance returned (singleton by default)

---

## 9. Default Bean Scope

By default:

```xml
<bean ... />
```

Means:

* **Singleton**
* One object per container
* Same object reused

No extra configuration needed.

---

## 10. Bean with Properties (DI via XML)

```xml
<bean id="alien" class="...Alien">
    <property name="age" value="21"/>
</bean>
```

Spring:

* Creates object
* Calls setter method
* Injects value

This is **Dependency Injection**.

---

## 11. Constructor Injection (XML)

```xml
<bean id="alien" class="...Alien">
    <constructor-arg value="21"/>
</bean>
```

Spring:

* Calls constructor with arguments
* No setters required

---

## 12. Bean Lifecycle (Minimal)

Spring:

1. Creates bean
2. Injects dependencies
3. Bean is ready for use
4. Destroys bean on shutdown

You usually don’t manage this manually.

---

## 13. Common Errors (Awareness)

* Wrong class path → `ClassNotFoundException`
* Wrong XML path → `FileNotFoundException`
* Wrong bean id → `NoSuchBeanDefinitionException`

---

## 14. Modern Context (One Line)

* XML-based beans are **legacy**
* Spring Boot uses **annotations**
* Concept of **bean remains the same**

---

## One-Line Summary (Memorize)

> **A Spring bean is an object created and managed by the Spring container, defined using configuration like XML instead of `new`.**

If you want next (short):

* Bean scope (`singleton` vs `prototype`)
* XML vs annotations
* How Spring Boot creates beans automatically
