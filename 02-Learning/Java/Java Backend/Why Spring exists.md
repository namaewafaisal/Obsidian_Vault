Good. We move forward with the **new concise style**.

---

# 📘 Backend Learning Note — Why Spring Exists (Spring Core)

---

## 0. Problem (why Spring exists)

Large Java backend systems suffer from:

* tightly coupled classes
* hard-to-test code
* manual object creation
* fragile configuration

Changing one class often forces changes everywhere.

**Anchor:**

> The real problem is **object coupling and lifecycle management**.

---

## 1. Core idea (one sentence)

**Spring separates object creation from object usage.**

You stop *building* dependencies and start *receiving* them.

---

## 2. What Spring actually provides (essentials only)

* **IoC (Inversion of Control)**
  → Spring creates and wires objects

* **DI (Dependency Injection)**
  → Objects receive dependencies instead of creating them

* **ApplicationContext**
  → Central container that manages object lifecycles

---

## 3. Before vs After Spring (mental contrast)

**Without Spring**

* `new` everywhere
* hard-coded dependencies
* manual lifecycle
* poor testability

**With Spring**

* dependencies injected
* replaceable implementations
* centralized lifecycle
* test-friendly design

---

## 4. What Spring is NOT

* Not a web framework (MVC is built on top)
* Not magic
* Not required for HTTP or REST

Spring solves **design problems**, not protocol problems.

---

## 5. One mental model (lock this)

> **Your code describes what it needs.
> Spring decides how and when to provide it.**

---

## 6. Stop check (only 2)

You should be able to answer:

1. What problem does Spring solve that plain Java does not?
2. Why is `new` a design smell in large systems?

---
