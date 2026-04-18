Below are **short, clean notes on Singleton**, exactly at the level you need for Spring.

---

# Singleton (Core Notes)

## What Singleton Means

A **Singleton** means:

> **Only one object (instance) of a class exists in the container.**

---

## Singleton in Plain Java

```java
class A {
    private static A obj = new A();

    private A() {}

    public static A getInstance() {
        return obj;
    }
}
```

* Only one object
* Same object returned every time

---

## Singleton in Spring (Important Difference)

In **Spring**:

* Singleton is a **scope**, not a design pattern you write
* Spring creates **one bean per container**

Default scope = **singleton**

---

## What Happens in Spring

```java
@Bean
public Alien alien() {
    return new Alien();
}
```

* Spring creates **one Alien object**
* Stores it in the IoC container
* Every `getBean()` returns the **same object**

---

## Proof (Conceptually)

```java
Alien a1 = context.getBean(Alien.class);
Alien a2 = context.getBean(Alien.class);
```

Result:

```
a1 == a2  → true
```

Same object.

---

## Why Spring Uses Singleton by Default

* Memory efficient
* Faster object access
* Suitable for **stateless services**
* Ideal for backend apps

Controllers, Services, Repositories → singleton by default

---

## When Singleton Is a Problem

Singleton is bad **only if**:

* Bean holds user-specific state
* Bean stores request data

Example (bad):

```java
@Service
class UserService {
    String username; // shared across users ❌
}
```

---

## Other Bean Scope (Just Awareness)

* `singleton` → one object (default)
* `prototype` → new object every time

You mostly use **singleton**.

---

## One-Line Summary (Memorize)

> **In Spring, Singleton means one bean instance per container, reused everywhere by default.**

That’s all you need **for now**.
