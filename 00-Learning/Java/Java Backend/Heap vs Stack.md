# Heap vs Stack — First‑Principles Version

> **Goal:** Understand *where data lives at runtime*, *why Java uses two different memory areas*, and *how this affects backend bugs and performance*.

---

## 0. The problem memory must solve

When a program runs, it must handle two very different needs:

1. **Execute code step by step** (method calls, returns)
2. **Store data that may live longer than a single method call**

Using one memory area for both would be slow, unsafe, and hard to manage.

So the JVM separates memory into **Stack** and **Heap**.

**One‑line:**

> Stack is for *execution*, Heap is for *data*.

---

## 1. What the Stack is (execution memory)

The **Stack** exists to support *method execution*.

### Key properties

* Each **thread** has its **own stack**
* Stack memory is created when a thread starts
* Stack memory is destroyed when the thread ends

This means stack data is:

* Private to a thread
* Never shared

**One‑line:**

> The stack belongs to a thread and controls how code runs.

---

## 2. What a stack frame is (very important)

Every time a method is called:

* A **stack frame** is created

A stack frame contains:

* Method parameters
* Local variables
* Object references
* Return address (where to go back after method ends)

When the method returns:

* Its stack frame is removed

**Important rule:**

> Stack frames are created and destroyed automatically.

**One‑line:**

> A stack frame represents one active method call.

---

## 3. What the Stack does NOT store

The stack does **not** store:

* Objects
* Arrays
* Large data

It stores only:

* References (addresses) to objects

Objects live elsewhere.

**One‑line:**

> The stack never stores objects, only references.

---

## 4. What the Heap is (object memory)

The **Heap** exists to store *objects and arrays*.

### Key properties

* Shared across **all threads**
* Created when the JVM starts
* Destroyed when the JVM exits

Objects on the heap:

* Are created using <code>new</code>
* Can outlive the method that created them
* May be accessed by multiple threads

**One‑line:**

> The heap stores objects whose lifetime is independent of method calls.

---

## 5. Why objects must live on the Heap

Objects:

* May be returned from methods
* May be stored in fields
* May be shared across threads

If objects lived on the stack:

* They would disappear when methods return
* Sharing would be impossible

So Java places all objects on the heap.

**One‑line:**

> Objects live on the heap because they must survive method execution.

---

## 6. How Stack and Heap work together

Example:

```java
User u = new User();
```

What happens:

1. A <code>User</code> object is created on the **heap**
2. A reference to it (<code>u</code>) is stored in the **stack frame**

If the method returns:

* The reference may disappear
* The object stays alive **if referenced elsewhere**

**One‑line:**

> Stack holds references; heap holds the actual data.

---

## 7. Errors related to Stack and Heap

### StackOverflowError

Occurs when:

* Too many stack frames are created
* Usually due to deep or infinite recursion

Increasing heap size does **not** fix this.

### OutOfMemoryError

Occurs when:

* Heap cannot allocate more objects
* GC cannot free enough memory

Increasing stack size does **not** fix this.

**One‑line:**

> StackOverflow = execution problem, OutOfMemory = data problem.

---

## 8. Performance characteristics

### Stack

* Very fast
* Memory allocation is automatic
* No garbage collection

### Heap

* Slower than stack
* Managed by Garbage Collector
* Requires synchronization for shared access

**One‑line:**

> Stack is fast and simple; heap is flexible and shared.

---

## 9. Why backend developers must care

Understanding stack vs heap helps you:

* Debug recursion bugs
* Diagnose memory leaks
* Understand thread safety issues
* Tune JVM memory

Many production issues trace back to confusing these two.

**One‑line:**

> Most memory bugs come from misunderstanding stack vs heap.

---

## 10. Final mental model

Tell yourself:

> "The stack manages *how code runs*. The heap manages *how long data lives*."

---

## Links

* [[JVM lifecycle]]
* [[Object lifecycle & GC]]
* [[Threads basics]]
