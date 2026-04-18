# BeanFactory (XML)
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[../🏠 Home]]

---

## What This Demonstrates

Manual Spring IoC using XML configuration — the old way before Spring Boot.

Spring: creates the object → stores it → returns it when requested.

---

## Key Classes

### BeanFactory
- Lightweight IoC container
- Creates beans and returns them

### XmlBeanFactory
- Implementation of BeanFactory
- Reads bean definitions from an XML file

### FileSystemResource("spring.xml")
- Tells Spring where the XML config file is
- If file not found → `FileNotFoundException`

---

## Code Walkthrough

```java
// 1. Create the container — reads spring.xml
BeanFactory factory =
    new XmlBeanFactory(new FileSystemResource("spring.xml"));

// 2. Get the object — Spring creates Alien and returns it
Alien obj = (Alien) factory.getBean("alien");
```

You did **not** use `new Alien()`. Spring did it.

---

## spring.xml

```xml
<bean id="alien" class="com.telusko.SpringDemo.Alien"/>
```

Tells Spring:
- What object to create → `Alien`
- What name to store it as → `"alien"`

---

## Why Type Casting Is Needed

```java
(Alien) factory.getBean("alien");
```

`getBean()` returns `Object` — Java needs explicit casting.
Spring Boot avoids this with generics: `context.getBean(Alien.class)`.

---

## BeanFactory vs ApplicationContext

| BeanFactory | ApplicationContext |
|-------------|-------------------|
| Lazy — creates beans on demand | Eager — creates beans at startup |
| Basic features | Advanced features (events, AOP, i18n) |
| Rarely used today | Used by Spring Boot |

---

## One-Line Summary

> **BeanFactory reads XML, creates objects, and returns them instead of using `new`.**
> Spring Boot does all of this automatically.

---

**Prev →** [[05-Singleton|Singleton]]
**Next Phase →** [[../Phase-2-SpringBoot-Core/README|Phase 2 — Spring Boot Core]]
