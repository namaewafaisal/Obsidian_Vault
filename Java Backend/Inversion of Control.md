Understood.
We will **finish all Spring Core subtopics first**, then do **one consolidated Anki pass** at the end.

Proceeding to the next subtopic.

---

# 📘 Backend Learning Note — Inversion of Control (IoC)

---

## 0. Problem

In plain Java:

* classes **create** their dependencies
* object creation logic spreads everywhere
* swapping implementations is hard
* testing requires complex setup

The problem is **who controls object creation and wiring**.

---

## 1. Core idea (definition)

**Inversion of Control means your code does not create its dependencies.
The framework does.**

Control of object creation is inverted:

* from your code
* to the container (Spring)

---

## 2. What is being “inverted”

### Without IoC

```text
Class A → creates Class B → creates Class C
```

### With IoC

```text
Spring → creates A, B, C → wires them together
```

Your classes:

* declare dependencies
* do not decide how to build them

---

## 3. What IoC enables (only essentials)

* loose coupling
* replaceable implementations
* centralized configuration
* testability
* lifecycle control

IoC is the **foundation**, not a feature.

---

## 4. Important clarification

* IoC is a **principle**
* Spring is an **implementation** of IoC
* Dependency Injection is **how IoC is achieved**

Do not mix these up.

---

## 5. Mental model (lock this)

> **Your code asks for what it needs.
> Spring decides how to provide it.**

---

## 6. Stop check (2)

You should be able to answer:

1. What control is inverted in IoC?
2. Why does IoC reduce coupling?
