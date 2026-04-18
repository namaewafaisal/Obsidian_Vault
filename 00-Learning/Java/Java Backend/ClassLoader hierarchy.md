# ClassLoader hierarchy — First‑Principles Version

> **Goal:** Understand *how the JVM finds classes*, *who is allowed to load them*, and *why this prevents bugs and security issues* — explained mechanically.

---

## 0. The problem ClassLoaders solve

A Java program does not live in a single file.
It consists of:

* Core Java classes (`String`, `List`, `Thread`)
* Platform libraries (`SQL`, `XML`)
* Your application code
* Third‑party libraries

The JVM must answer two questions **every time a class is needed**:

1. **Where do I find this class?**
2. **Which version should I trust?**

ClassLoaders exist to answer these questions **safely and predictably**.

**One‑line:**

> ClassLoaders decide *where a class comes from* and *whether it is allowed to be used*.

---

## 1. What a ClassLoader actually is

A **ClassLoader** is a JVM component that:

* Locates `.class` bytecode
* Loads it into memory
* Defines the class inside the JVM

Until a class is loaded by a ClassLoader:

* The JVM does not know it exists
* It cannot be used or instantiated

**Important:**

> A class is not “real” to the JVM until a ClassLoader defines it.

**One‑line:**

> A ClassLoader turns bytecode files into usable runtime classes.

---

## 2. Why there are *multiple* ClassLoaders

If there were only one loader:

* Application code could replace core Java classes
* Libraries could break each other
* Security would be impossible

So Java uses **multiple ClassLoaders arranged in levels**.
Each level has **different trust and responsibility**.

**One‑line:**

> Multiple ClassLoaders separate trusted code from untrusted code.

---

## 3. The ClassLoader hierarchy (top → bottom)

```
Bootstrap ClassLoader
        ↓
Platform ClassLoader
        ↓
Application ClassLoader
```

Think of this as **authority levels**, not load order.

**One‑line:**

> Higher loaders are more trusted; lower loaders are more flexible.

---

## 4. Bootstrap ClassLoader (highest authority)

### What it loads

* Core Java classes
* Examples: `java.lang`, `java.util`, `java.io`

### Characteristics

* Implemented in native code
* Has **no parent**
* Loaded first

### Why it exists

* Core Java must be **untouchable**
* No application should replace `String` or `Object`

**One‑line:**

> Bootstrap loads core Java classes and cannot be overridden.

---

## 5. Platform ClassLoader (middle authority)

### What it loads

* Java platform modules
* Examples: `java.sql`, `java.xml`

### Why it exists

* Separates **core JVM internals** from **optional platform features**

**One‑line:**

> Platform loader handles standard Java libraries beyond the core.

---

## 6. Application ClassLoader (lowest authority)

### What it loads

* Your application classes
* Third‑party libraries
* Anything on the **classpath**

This is where:

* Spring Boot
* Hibernate
* Your code

are loaded.

**One‑line:**

> Application ClassLoader loads your app and its dependencies.

---

## 7. Parent‑delegation model (MOST IMPORTANT)

When a class is needed, the JVM follows this rule:

1. Ask the **parent** ClassLoader
2. If parent cannot load it → try itself

This is called **parent‑first delegation**.

### Why this rule exists

* Prevents overriding core classes
* Ensures consistent behavior
* Enforces security boundaries

Example:

* App tries to load `java.lang.String`
* Application loader asks parent
* Bootstrap already loaded it
* Application version is ignored

**One‑line:**

> A child ClassLoader cannot replace a class already loaded by its parent.

---

## 8. ClassLoader hierarchy vs classpath order

These are **different concepts**.

### ClassLoader hierarchy

* Decides **which loader is asked first**
* About *authority and trust*

### Classpath order

* Decides **which class file is chosen**
* Only applies **within the same ClassLoader**

Mixing these two causes many bugs.

**One‑line:**

> Hierarchy chooses the loader; classpath chooses the class file.

---

## 9. Same class name ≠ same class

In the JVM:

> A class is uniquely identified by:
> **(fully‑qualified name + ClassLoader)**

That means:

* Same class name
* Loaded by different ClassLoaders
* → treated as completely different types

This explains:

* ClassCastException surprises
* Plugin system isolation

**One‑line:**

> Same name + different loader = different class.

---

## 10. Spring Boot and custom ClassLoaders

### The problem

Spring Boot packages apps as **fat JARs** with nested JARs.

Default Application ClassLoader:

* Cannot load classes from inside nested JARs

### The solution

Spring Boot:

* Uses a **custom ClassLoader**
* Knows how to read classes from inside the fat JAR

This is why Spring Boot apps are:

* Self‑contained
* Runnable with `java -jar`

**One‑line:**

> Spring Boot uses a custom ClassLoader to load classes from fat JARs.

---

## 11. Why backend developers must care

ClassLoader knowledge helps you:

* Debug dependency conflicts
* Understand startup failures
* Reason about hot reload
* Avoid classpath hell

**One‑line:**

> Many backend bugs are ClassLoader problems in disguise.

---

## 12. Final mental picture

Tell yourself this:

> “When the JVM needs a class, it asks trusted loaders first, then less‑trusted ones, and once a class is loaded, it cannot be replaced by children.”

---

## Links

* [[JVM lifecycle]]
* [[Heap vs Stack]]
* [[Reflection in frameworks]]
* [[Spring Boot startup sequence]]
