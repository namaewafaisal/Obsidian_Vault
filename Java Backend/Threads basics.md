# Threads Basics — First-Principles Version

> **Goal:** Understand **what threads actually are**, why servers need them, how they relate to stack/heap, and what can go wrong in backend systems.

---

## 0. The real problem threads solve

A backend server must:

* handle **multiple requests at the same time**
* avoid blocking when one request is slow (DB, network)
* keep CPU busy instead of idle

If a program could run **only one task at a time**, the server would be unusable.

Threads exist to solve **concurrency**.

**One-line anchor:**

> Threads allow a single program to do multiple things at the same time.

---

## 1. What a thread really is (plain English)

A **thread** is:

* an independent **path of execution**
* inside a single process
* scheduled by the OS / JVM

Important distinction:

* **Process** = container (memory, resources)
* **Thread** = execution line inside the container

A Java backend app is:

* **1 process**
* **many threads**

**One-line anchor:**

> A thread is a single line of execution inside a process.

---

## 2. How threads relate to Stack and Heap (critical)

This is where everything connects.

### Each thread has:

* its **own Stack**
* its own method calls
* its own local variables

### All threads share:

* the **same Heap**
* the same objects

So:

* stack = private
* heap = shared

**One-line anchor:**

> Threads have private stacks but share the heap.

---

## 3. What happens when a thread starts

When a thread is created:

1. JVM asks OS to create a thread
2. A **new stack** is allocated
3. Execution begins at a specific method

In Java:

* `main()` runs on the **main thread**
* new threads start at `run()`

**One-line anchor:**

> Starting a thread means starting a new stack with its own execution path.

---

## 4. Why backend servers rely heavily on threads

In a backend server:

* each request is handled by a **thread**
* slow I/O should not block other requests

Example:

* Request A waits for DB
* Request B must still run

Threads make this possible.

**One-line anchor:**

> Backend scalability depends on handling many requests concurrently.

---

## 5. Thread lifecycle (simplified, practical)

A thread typically goes through:

1. **New** – created but not started
2. **Runnable** – eligible to run
3. **Running** – executing on CPU
4. **Blocked / Waiting** – waiting for I/O, lock, or sleep
5. **Terminated** – finished execution

Threads move between states frequently.

**One-line anchor:**

> Threads constantly move between running and waiting states.

---

## 6. Why concurrency is dangerous (shared heap problem)

Because threads share the heap:

* two threads can access the same object
* at the same time
* with no automatic protection

This can cause:

* corrupted data
* inconsistent state
* hard-to-reproduce bugs

This is the **core concurrency problem**.

**One-line anchor:**

> Shared heap + multiple threads = potential data races.

---

## 7. Thread safety (what it really means)

Code is **thread-safe** if:

* it behaves correctly
* even when multiple threads run it simultaneously

Thread safety is **not automatic**.

It must be designed using:

* immutability
* synchronization
* confinement

We will cover these later.

**One-line anchor:**

> Thread safety means correctness under concurrent execution.

---

## 8. Exceptions and threads (important detail)

Exceptions are **thread-scoped**.

If a thread throws an unhandled exception:

* that **thread dies**
* other threads keep running
* JVM usually stays alive

In backend servers:

* one request fails
* server continues serving others

**One-line anchor:**

> Unhandled exceptions kill threads, not the JVM.

---

## 9. Performance myths about threads

Common wrong assumptions:

* ❌ “More threads = faster”
* ❌ “Threads are free”

Reality:

* threads consume memory (stack)
* context switching is expensive
* too many threads can slow the system

This leads to **thread pools** (later topic).

**One-line anchor:**

> Too many threads can hurt performance.

---

## 10. Why backend developers must care

Misunderstanding threads leads to:

* race conditions
* deadlocks
* memory leaks
* server crashes under load

Correct thread handling leads to:

* scalable APIs
* predictable behavior
* stable production systems

**One-line anchor:**

> Most backend bugs are concurrency bugs.

---

## 11. Final mental model

Repeat this:

> “A thread is an execution path with its own stack.
> All threads share the heap.
> Concurrency problems come from shared data.”

---

## Links

* [[Heap vs Stack]]
* [[Exception propagation]]
* [[Object lifecycle & GC]]
* [[JVM lifecycle]]

---
