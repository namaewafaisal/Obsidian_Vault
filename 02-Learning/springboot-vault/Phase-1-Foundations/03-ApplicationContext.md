# ApplicationContext
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[../🏠 Home]]

---

## What It Is

**ApplicationContext** is Spring's IoC container that:
- Creates objects (beans)
- Injects dependencies
- Manages lifecycle

---

## Why It's Used

Instead of:
```java
new Object();
```

You do:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
MyBean obj = context.getBean(MyBean.class);
```

Spring creates and manages the object.

---

## Core Responsibilities

1. Reads configuration (XML / annotations / Java config)
2. Creates beans
3. Injects dependencies
4. Returns beans when requested

---

## Common Ways to Create ApplicationContext

| Type | When Used |
|------|-----------|
| `ClassPathXmlApplicationContext` | XML-based config (legacy) |
| `AnnotationConfigApplicationContext` | Annotation-based config |
| `SpringApplication.run()` | Spring Boot (auto-creates context) |

---

## `new` vs ApplicationContext

| `new` | ApplicationContext |
|-------|--------------------|
| You manage object | Spring manages object |
| Tight coupling | Loose coupling |
| No lifecycle | Full lifecycle |

---

## One-Line Summary

> **ApplicationContext is the Spring container that creates, wires, and manages objects instead of you using `new`.**

---

**Prev →** [[02-Spring-Bean|Spring Bean]]
**Next →** [[04-Maven|Maven]]
