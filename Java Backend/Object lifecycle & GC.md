# Object lifecycle & Garbage Collection — First-Principles Version

> **Goal:** Understand *how objects are created, stay alive, become unreachable, and are reclaimed*, and why this matters for backend correctness and performance.

---

## 0. The real problem GC solves

A running backend continuously creates objects:

* request objects
* DTOs
* database results
* strings
* temporary helpers

Manually freeing memory would be:

* error‑prone
* slow
* unsafe

Java solves this with **automatic memory management** using **Garbage Collection (GC)**.

**One-line:**

> GC automatically frees memory so developers don’t have to manage it manually.

---

## 1. Object creation (where life begins)

An object is created when:

```java
new User()
```

What actually happens:

1. JVM allocates memory on the **heap**
2. Object fields are initialized
3. Constructor runs
4. A reference to the object is returned

The object now exists **independently of the method** that created it.

**One-line:**

> Objects are created on the heap and can outlive the method that created them.

---

## 2. References vs objects (critical distinction)

* An **object** lives on the heap
* A **reference** lives in:

  * stack frames
  * fields
  * static variables

References are just *handles* pointing to objects.

If all references disappear, the object becomes unreachable.

**One-line:**

> Objects and references are different; only references control reachability.

---

## 3. Reachability (how JVM decides life or death)

The JVM does **not** count references.

Instead, it asks:

> “Can this object be reached from somewhere important?”

Those important starting points are called **GC Roots**.

Examples of GC Roots:

* local variables in active stack frames
* static fields
* running threads
* JNI references

If an object is reachable from a GC Root → it is alive.

**One-line:**

> An object is alive if it is reachable from a GC Root.

---

## 4. Becoming unreachable (object death condition)

An object becomes unreachable when:

* no GC Root can reach it
* all references to it are gone

Common cases:

* method ends and reference goes out of scope
* reference is overwritten or set to `null`
* owning object becomes unreachable

Important:

> Unreachable does NOT mean immediately deleted.

**One-line:**

> Objects die when no live reference can reach them.

---

## 5. Garbage Collection (what GC actually does)

When GC runs, it:

1. Finds unreachable objects
2. Reclaims their memory
3. Optionally compacts the heap

GC:

* runs automatically
* runs unpredictably
* may pause application threads

You cannot control *when* GC runs.

**One-line:**

> GC finds unreachable objects and frees their memory when needed.

---

## 6. `null` does NOT delete objects

Setting a reference to `null`:

```java
user = null;
```

This:

* removes one reference
* does NOT delete the object

The object is collected **only if no other references exist**, and only when GC runs.

**One-line:**

> `null` removes a reference, not the object itself.

---

## 7. Logical memory leaks (very important)

In Java, memory leaks are usually **logical**, not technical.

A logical memory leak happens when:

* objects are no longer needed
* but references to them still exist

Because they are still reachable:

* GC cannot reclaim them
* heap usage keeps growing

Common causes:

* static collections
* caches without eviction
* listeners never removed

**One-line:**

> Memory leaks happen when objects stay reachable longer than needed.

---

## 8. GC does NOT manage external resources

GC manages:

* heap memory only

GC does NOT manage:

* files
* sockets
* database connections

Those must be closed explicitly.

Correct approach:

* try‑with‑resources
* explicit close methods

**One-line:**

> GC frees memory, not external resources.

---

## 9. Why backend developers must care

Understanding object lifecycle helps you:

* diagnose memory leaks
* design caches safely
* avoid OOM crashes
* understand GC pauses

Most production memory bugs are logic bugs.

**One-line:**

> Backend stability depends on understanding object reachability.

---

## 10. Final mental model

Tell yourself:

> “Objects live on the heap. References decide reachability. GC frees only unreachable objects, only when it decides to.”

---

## Links

* [[Heap vs Stack]]
* [[JVM lifecycle]]
* [[Threads basics]]
