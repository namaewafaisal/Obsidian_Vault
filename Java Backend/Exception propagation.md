# Exception Propagation — First-Principles Version

> **Goal:** Understand **what actually happens when something goes wrong**, how the JVM reacts step-by-step, and how backend code should respond safely.

---

## 0. The real problem exceptions solve

Programs assume things go right:

* files exist
* input is valid
* DB is reachable

But reality breaks assumptions.

Java needs a way to:

* stop normal execution
* report failure
* clean up safely
* avoid corrupting state

That mechanism is **exceptions**.

**One-line:**

> Exceptions are Java’s way of saying “this assumption just broke”.

---

## 1. What an exception really is

An exception is **not magic**.

In Java, an exception is:

* a normal **object**
* created on the **heap**
* an instance of `Throwable`

It only becomes special when it is **thrown**.

**One-line:**

> An exception is just an object until it is thrown.

---

## 2. What “throwing” an exception actually means

When an exception is thrown:

```java
throw new IOException();
```

or when JVM throws one (e.g. divide by zero):

1. The current method **immediately stops**
2. Remaining lines in that method are skipped
3. JVM starts looking for someone who can handle the problem

No return value.
No normal flow.

**One-line:**

> Throwing an exception aborts the current method instantly.

---

## 3. Why the stack matters (key insight)

Every method call has a **stack frame**.

When an exception is thrown:

* The current stack frame is **invalid**
* The method cannot continue
* The JVM removes (pops) that frame

This is not optional — execution cannot resume safely.

**One-line:**

> Exceptions destroy the current method’s stack frame.

---

## 4. Exception propagation (core mechanism)

If the current method does **not** handle the exception:

1. Its stack frame is popped
2. The exception moves to the **caller**
3. JVM checks: “Can *this* method handle it?”

This repeats **up the call stack**.

This process is called **exception propagation**.

**One-line:**

> Exceptions climb the call stack until someone handles them.

---

## 5. What a stack trace really shows

A stack trace is **not an error message** — it’s a **history**.

It shows:

* where the exception was thrown
* which methods were active
* how the exception traveled

Important orientation:

* **Top** → point of failure
* **Bottom** → program entry (`main`, thread start)

**One-line:**

> A stack trace is the path the exception took through the stack.

---

## 6. Checked vs Unchecked — the real reason

This is a **design decision**, not hierarchy trivia.

### Checked exceptions

Used when:

* failure is **expected**
* caller can realistically recover

Examples:

* file not found
* network unavailable
* DB temporarily down

Java **forces** you to acknowledge them.

**One-line:**

> Checked exceptions represent recoverable conditions.

---

### Unchecked exceptions

Used when:

* program logic is broken
* continuing execution is unsafe

Examples:

* null access
* invalid arguments
* impossible state

Forcing handling would hide bugs.

**One-line:**

> Unchecked exceptions represent programmer mistakes.

---

## 7. `throws` does NOT handle anything

This is commonly misunderstood.

```java
void readFile() throws IOException
```

Means:

* “I will not handle this”
* “My caller must decide”

No try.
No recovery.
Just delegation.

**One-line:**

> `throws` pushes responsibility upward.

---

## 8. Catching an exception (what really happens)

When a `catch` block matches:

1. Propagation stops
2. Stack frames above are preserved
3. You regain control

Now you can:

* recover
* log
* translate
* fail gracefully

Bad practice:

* catching `Exception`
* swallowing errors

**One-line:**

> Catching an exception stops propagation and regains control.

---

## 9. Exception translation (backend critical)

Backend layers must **not leak internal details**.

Low-level exceptions are:

* too specific
* too technical
* dangerous to expose

So we translate:

```text
SQLException → RepositoryException → ServiceException
```

Each layer speaks its own language.

**One-line:**

> Exception translation preserves abstraction boundaries.

---

## 10. `finally` vs try-with-resources

`finally`:

* always runs
* even if exception occurs
* even if method returns early

But modern Java prefers:

* `try-with-resources`
* because it is safer and clearer

**One-line:**

> Use `finally` for logic cleanup, try-with-resources for I/O.

---

## 11. Unhandled exceptions (important backend behavior)

If an exception reaches the top:

* JVM prints stack trace
* **thread terminates**

In backend servers:

* one request thread dies
* server process usually survives

This is why global exception handling exists.

**One-line:**

> Unhandled exceptions kill threads, not the JVM.

---

## 12. Why backend developers must care

Bad exception handling causes:

* partial updates
* broken invariants
* silent failures
* security leaks

Good exception handling:

* keeps systems stable
* produces meaningful errors
* protects data integrity

**One-line:**

> Exception handling is about system correctness, not syntax.

---

## 13. Final mental model

Tell yourself:

> “An exception aborts a method, destroys its stack frame, and climbs upward until someone handles it — or the thread dies.”

---

## Links

* [[Heap vs Stack]]
* [[Object lifecycle & GC]]
* [[Threads basics]]
* [[JVM lifecycle]]

---
