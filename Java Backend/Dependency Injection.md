Proceeding.

---

# 📘 Backend Learning Note — Dependency Injection (DI)

---

## 0. Problem

With IoC alone:

* Spring controls object creation
* but objects still need **their dependencies**

The problem is **how dependencies are supplied without tight coupling**.

---

## 1. Core idea (definition)

**Dependency Injection is the mechanism by which dependencies are provided to an object instead of being created by it.**

DI is **how IoC is implemented** in practice.

---

## 2. What DI actually means (concise)

* An object **declares what it needs**
* Spring **injects those dependencies**
* The object never uses `new` for collaborators

Dependencies are **external inputs**, not internal decisions.

---

## 3. Forms of DI (only what matters)

* **Constructor injection** → required dependencies
* **Setter injection** → optional dependencies
* **Field injection** → discouraged (harder to test)

Spring supports all three, but **constructor injection is preferred**.

---

## 4. Why DI matters (no fluff)

DI enables:

* loose coupling
* easy testing (mocking)
* clear dependencies
* safe immutability

Without DI, IoC has limited value.

---

## 5. DI vs IoC (clarity check)

* **IoC** → *who controls creation*
* **DI** → *how dependencies are supplied*

DI is not optional in Spring — it is fundamental.

---

## 6. Mental model (lock this)

> **Classes should not look for dependencies.
> Dependencies should be given to them.**

---

## 7. Stop check (2)

You should be able to answer:

1. Why is constructor injection preferred?
2. How does DI reduce coupling?

---

Say:

* **“Next: Bean definition”**
* **“Pause / recap Spring so far”**
