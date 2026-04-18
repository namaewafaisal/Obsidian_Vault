# JVM Lifecycle — **First-Principles Version (Learning, not Revision)**

> **Goal:** Understand *exactly* how Java code turns into a running backend program and how it stops — without guessing.

---

## 0. First: what problem does the JVM solve?

Different computers have:

* different CPUs
* different operating systems
* different memory layouts

If Java ran machine code directly, you would need **different builds per OS**.

### JVM’s solution

Java code is compiled into **bytecode**, which is:

* NOT machine code
* NOT tied to any OS
* Meant to be run by the JVM

The JVM acts like a **virtual computer** inside your real computer.

**One-line:**

> JVM is a virtual machine that runs Java bytecode the same way on every OS.

---

## 1. Before JVM starts: compilation (very important)

Java execution **never starts from `.java`**.

### What really happens

```text
.java  --(javac)-->  .class  --(JVM)--> running program
```

* `javac` = compiler
* `.class` = bytecode
* JVM can execute **only `.class`**

If `.class` does not exist → JVM cannot run anything.

**One-line:**

> JVM executes bytecode, not source code.

---

## 2. Step 1 — OS starts the JVM process

When you run:

```bash
java -jar app.jar
```

### What the OS does

* Creates a **new process**
* Loads the JVM executable into memory
* Gives it RAM, CPU time, and system resources

At this moment:

* ❌ your Java code has not started
* ❌ no `main()` yet
* ❌ no objects yet

This is just like:

> Starting Chrome.exe before opening any website.

**One-line:**

> OS first starts the JVM process, not your application.

---

## 3. Step 2 — JVM prepares its internal systems

Before touching your code, the JVM must prepare itself.

### JVM initializes:

1. **ClassLoader system**
   (to find and load `.class` files)

2. **Runtime memory areas**

   * Heap → objects
   * Stack → method execution
   * Metaspace → class metadata

3. **Garbage Collector threads**

   * Created
   * Idle (not collecting yet)

Nothing from your app runs yet.

**Analogy:**
Like setting up a factory before production starts.

**One-line:**

> JVM sets up memory and loaders before running any application code.

---

## 4. Step 3 — JVM finds the entry point (main class)

Now JVM needs to know **where to start execution**.

### What JVM looks for

* The class you specified on the command line
* Inside it, a method with this exact signature:

```java
public static void main(String[] args)
```

### Important detail

* JVM loads **only this class**
* It does **not** load your entire project
* Other classes are ignored for now

**Imports do nothing here.**
Imports are already resolved at compile time.

**One-line:**

> JVM loads only the main class first, nothing else.

---

## 5. Step 4 — Class loading (per class, not global)

When JVM loads a class, it does **three things in order**.

### Phase 1: Loading

* Reads `.class` bytecode
* Creates an internal class representation

### Phase 2: Linking

* **Verify** → bytecode safety
* **Prepare** → allocate static variables
* **Resolve** → symbolic references (may be delayed)

### Phase 3: Initialization

* Executes `static {}` blocks
* Initializes static fields

This happens:

* for the main class first
* later for other classes **only when used**

**One-line:**

> Each class is verified and initialized only when first needed.

---

## 6. Step 5 — JVM creates the main thread

Now JVM is ready to run code.

### What it does

* Creates a thread called **main thread**
* Assigns it a stack
* Starts executing bytecode inside `main()`

At this point:

* Your Java program is officially running

**One-line:**

> JVM runs your program by executing `main()` on the main thread.

---

## 7. Step 6 — Runtime execution (normal life of the app)

While the program runs:

### Method calls

* Each method call → new stack frame
* Stack frame holds:

  * local variables
  * parameters
  * references

### Objects

* Created on the heap using `new`
* Live independently of method calls

### Threads

* Additional threads may be created
* All threads share the same heap

### Garbage Collection

* Runs **only when JVM decides**
* Reclaims unreachable objects
* Not predictable
* Not tied to method exit

**One-line:**

> Stack controls execution; heap controls object lifetime.

---

## 8. Step 7 — How the JVM shuts down

The JVM does **not** stop automatically after `main()` finishes.

### JVM exits when:

* `main()` finishes **and**
* no non-daemon threads are running
  **OR**
* `System.exit()` is explicitly called
  **OR**
* OS kills the process

### What happens before exit

* Shutdown hooks run
* Threads are stopped
* JVM prepares to terminate

**One-line:**

> JVM exits only when execution ends or is explicitly terminated.

---

## 9. Spring Boot inside the JVM lifecycle

Spring Boot does **not** control the JVM.
It runs **inside** it.

### During startup

* `main()` calls `SpringApplication.run()`
* Spring creates ApplicationContext
* Beans are instantiated

### During shutdown

* Spring registers a shutdown hook
* JVM calls it before exiting
* Beans are destroyed
* Resources are closed

Spring lives **and dies** with the JVM.

**One-line:**

> Spring Boot lifecycle is nested inside JVM lifecycle.

---

## 10. Final mental model (this is the one to keep)

```
javac creates .class
↓
OS starts JVM process
↓
JVM initializes loaders & memory
↓
Main class loaded and initialized
↓
Main thread starts
↓
Application code runs
↓
Objects live on heap, methods on stack
↓
GC runs when needed
↓
Threads finish / exit triggered
↓
Spring shuts down
↓
JVM exits
↓
OS reclaims memory
```

---
