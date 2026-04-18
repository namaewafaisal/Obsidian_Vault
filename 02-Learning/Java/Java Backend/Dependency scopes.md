# Dependency scopes
## 0. The real problem this concept solves

Not every library is needed **all the time**.

Some libraries are needed:

* only to **compile**
* only to **run**
* only during **tests**
* sometimes provided by the **runtime environment itself**

If every dependency were always included:

* builds would be bloated
* runtime classpaths would be polluted
* production failures would be harder to debug

The real problem is:

> **How do we control *when* a dependency participates in the build and runtime lifecycle?**

**One-line anchor:**

> Dependency scopes control when and where a dependency is available.

---

## 1. What this concept really is (plain English)

A **dependency scope** tells Maven **in which phases** a dependency should exist.

Think of scope as **visibility across time**:

* compile time
* test time
* runtime
* deployment environment

Scopes do **not** change *what* a dependency is.
They change **when Maven puts it on the classpath**.

**One-line anchor:**

> Scope defines the lifecycle visibility of a dependency.

---

## 2. How it actually works (step-by-step)

When Maven builds your project:

1. It resolves all dependencies
2. For each dependency, it reads its **scope**
3. Depending on the current phase:

   * some dependencies are included
   * some are ignored
4. Maven assembles:

   * compile classpath
   * test classpath
   * runtime classpath
5. Only dependencies valid for that phase are used

This is why:

* code can compile
* tests can pass
* yet production can still fail

**One-line anchor:**

> Maven builds different classpaths for different phases using scopes.

---

## 3. Key internal pieces (only after understanding)

### a) `compile` (default)

* Available at compile time
* Available at runtime
* Included in the final artifact

Used for:

* core libraries
* business logic dependencies

This is the **default scope** if none is specified.

---

### b) `test`

* Available only during testing
* Not included in production runtime

Used for:

* JUnit
* Mockito
* test utilities

If this leaks into production → configuration error.

---

### c) `runtime`

* Not needed to compile
* Needed to run

Used when:

* code references interfaces
* implementation is required only at runtime

Common example:

* database drivers

---

### d) `provided`

* Needed to compile
* **Not packaged**
* Expected to be supplied by the runtime environment

Used for:

* servlet APIs
* application servers

Wrong use of this scope is a **classic production bug**.

---

### e) `system` (awareness only)

* Points to a local JAR
* Not portable
* Almost always a bad idea

Know it exists. Do not use it.

---

**One-line anchor:**

> Scope mismatches are a leading cause of “works locally, fails in prod”.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “Scope is just an optimization”

Wrong.
Scope directly affects **runtime correctness**.

---

### ❌ “If tests pass, runtime is safe”

Wrong.
Test classpath ≠ runtime classpath.

---

### ❌ “Provided means optional”

Wrong.
Provided means **mandatory but externally supplied**.

---

**One-line anchor:**

> Scope mistakes don’t show up early—they show up in production.

---

## 5. Failure modes / edge cases

### a) ClassNotFoundException in production

Cause:

* dependency marked `test` or `provided`
* not available at runtime

---

### b) Bloated fat JAR

Cause:

* test or provided dependencies included incorrectly

---

### c) Server-specific crashes

Cause:

* mismatch between assumed provided libraries and actual environment

---

**One-line anchor:**

> Scope errors surface late and fail hard.

---

## 6. Backend relevance (non-negotiable)

Dependency scopes directly affect:

* **Artifact size**
* **Startup success**
* **Memory usage**
* **Production stability**
* **Deployment portability**

Every backend engineer must be able to explain:

* why a dependency is `compile`
* why another is `provided`
* why drivers are often `runtime`

**One-line anchor:**

> Correct scoping is a deployment-level responsibility, not just build hygiene.

---

## 7. Final mental model

> A dependency’s scope defines *when* it exists, not *whether* it exists.

If you can mentally separate:

* compile classpath
* test classpath
* runtime classpath

you understand Maven scopes.

---

## 8. Links (for Obsidian)

* [[Dependency resolution]]
* [[Transitive dependencies]]
* [[Maven lifecycle phases]]
* [[Fat JAR & classpath]]
* [[Spring Boot parent POM]]
