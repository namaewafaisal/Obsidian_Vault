# Spring Bean
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[🏠 Home]]

---

## 1. What Is a Bean

> A **Bean** is an object **created, managed, and controlled by the Spring IoC container**.

- Spring creates it → it is a **bean**
- You use `new` → it is **not** a bean

---

## 2. Why Beans Exist

Beans allow:
- Inversion of Control (IoC)
- Dependency Injection (DI)
- Loose coupling
- Centralized object management

You don't manage objects — **Spring does**.

---

## 3. Defining a Bean (XML — Legacy)

```xml
<bean id="alien" class="com.telusko.SpringDemo.Alien"/>
```

Tells Spring:
- **What to create** → `Alien` class
- **What name** → `alien`
- **Who creates it** → Spring container

---

## 4. Bean ID

```xml
<bean id="alien" class="..."/>
```

- `id` = unique name in the container
- Used to retrieve the bean:

```java
factory.getBean("alien");
```

---

## 5. How a Bean Is Created (Flow)

```
1. Spring reads XML (or scans annotations)
2. Finds bean definitions
3. Creates object using reflection
4. Stores it in the container
5. Returns it when requested
```

You never call the constructor directly.

---

## 6. Getting a Bean

```java
Alien obj = (Alien) factory.getBean("alien");
```

- Spring looks up bean by ID
- Returns the managed object
- Same instance returned (singleton by default)

---

## 7. Default Bean Scope = Singleton

One object per container. Reused everywhere.

---

## 8. DI via XML — Property Injection

```xml
<bean id="alien" class="...Alien">
    <property name="age" value="21"/>
</bean>
```

Spring calls the setter. This is **Dependency Injection**.

---

## 9. DI via XML — Constructor Injection

```xml
<bean id="alien" class="...Alien">
    <constructor-arg value="21"/>
</bean>
```

Spring calls the constructor with args. No setters needed.

---

## 10. Common Errors

| Error | Cause |
|-------|-------|
| `ClassNotFoundException` | Wrong class path in XML |
| `FileNotFoundException` | XML file not found |
| `NoSuchBeanDefinitionException` | Wrong bean id |

---

## 11. Modern Context

- XML-based beans = **legacy**
- Spring Boot uses **annotations** (`@Component`, `@Service`, etc.)
- Concept of **bean remains the same**

---

## One-Line Summary

> **A Spring bean is an object created and managed by the Spring container, defined using configuration instead of `new`.**

---

**Prev →** [[01-IoC-and-DI|IoC & DI]]
**Next →** [[03-ApplicationContext|ApplicationContext]]
