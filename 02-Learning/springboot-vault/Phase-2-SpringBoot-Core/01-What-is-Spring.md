# What is Spring?
**Phase:** [[README|Phase 2 — Spring Boot Core]]
**Back:** [[../🏠 Home]]

---

## Definition

> **Spring** is a Java application framework that provides the **infrastructure** to build backend applications without managing low-level plumbing yourself.

Spring lets you focus on **business logic**, while it handles **object creation, wiring, configuration, and integration**.

---

## Why Spring Exists

### Before Spring
- You manually created objects
- Tightly coupled classes
- Configuration was scattered
- Code was hard to test and scale

### Spring Introduced
- **IoC** (Inversion of Control)
- **Dependency Injection**
- **Centralized configuration**
- **Modular architecture**

---

## Core Idea — Two Pillars

```
1. Inversion of Control (IoC)
2. Dependency Injection (DI)
```

Spring controls:
- When objects are created
- How they are connected
- How long they live

Your code **does not manage dependencies**.

---

## What Spring Is NOT

- ❌ Not just for web apps
- ❌ Not a web server
- ❌ Not only Spring Boot
- ❌ Not magic (it's structured automation)

Spring can be used for: web apps, REST APIs, batch jobs, microservices.

---

## Spring vs Spring Boot

| Spring | Spring Boot |
|--------|-------------|
| Framework | Framework + auto-configuration |
| Requires manual setup | Minimal setup |
| More control | Faster development |
| Used under the hood | Built on top of Spring |

**Spring Boot uses Spring. They are not separate things.**

---

## What Spring Provides

- IoC container + DI
- Web / REST support (Spring MVC)
- Data access (JPA, JDBC)
- Transaction management
- Security
- AOP (Aspect-Oriented Programming)

You use only what you need.

---

## Real-World Flow

```
Controller → Service → Repository → Database
```

Spring creates each layer, injects dependencies, manages lifecycle.
You write **logic**, not wiring.

---

## One-Line Summary

> **Spring is a Java framework that manages object creation and wiring so you can build clean, scalable backend applications.**

---

**Next →** [[02-What-is-Spring-Boot|What is Spring Boot]]
