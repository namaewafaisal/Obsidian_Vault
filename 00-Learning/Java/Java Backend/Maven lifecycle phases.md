---

# 📘 Backend Learning Note — Maven Lifecycle Phases

---
# Maven lifecycle phases
## 0. The real problem this concept solves

A backend build is **not one action**.

It involves:

* validating project structure
* compiling code
* running tests
* packaging artifacts
* installing or deploying outputs

If these steps are run **out of order** or **inconsistently**, builds become:

* fragile
* non-reproducible
* environment-dependent

The real problem is:

> **How do we guarantee that every build follows the same ordered steps, every time, everywhere?**

**One-line anchor:**

> Maven lifecycle phases enforce a fixed, predictable build order.

---

## 1. What this concept really is (plain English)

A **Maven lifecycle** is a **predefined sequence of phases** that describe *what should happen* when you build a project.

Each phase represents:

* a logical stage in the build
* not an action itself

Actual work is done by **plugins**, which are **bound to phases**.

You don’t tell Maven *how* to build.
You tell it *how far* to build.

**One-line anchor:**

> A lifecycle defines *when* things happen, not *how* they happen.

---

## 2. How it actually works (step-by-step)

When you run a command like:

```bash
mvn package
```

Maven does **not** run only `package`.

It runs **every phase before it**, in order:

1. validate
2. compile
3. test
4. package

Each phase:

* triggers plugins bound to it
* runs only after the previous phase succeeds

If any phase fails → the build stops.

**One-line anchor:**

> Running a phase executes all earlier phases automatically.

---

## 3. Key internal pieces (only after understanding)

### a) The three main lifecycles

You mainly care about **one**, but must know all three exist.

#### 1️⃣ Default lifecycle (most important)

Used for:

* building
* testing
* packaging

Key phases (simplified):

* `validate` → project structure is correct
* `compile` → source code compiled
* `test` → tests executed
* `package` → JAR/WAR created
* `verify` → integration checks
* `install` → artifact placed in local repo
* `deploy` → artifact sent to remote repo

---

#### 2️⃣ Clean lifecycle

Used to **reset state**.

* `clean` → deletes `target/`

This avoids stale or corrupted builds.

---

#### 3️⃣ Site lifecycle (awareness only)

Used for:

* documentation
* reports

Not relevant for backend runtime behavior.

---

**One-line anchor:**

> The default lifecycle is the backbone of backend builds.

---

### b) Phases are ordered and immutable

You cannot:

* reorder phases
* skip earlier phases
* partially execute later phases

You can only choose **how far** to go.

---

### c) Phases vs commands (important distinction)

`compile`, `test`, `package` are **phases**, not commands.

The command is always:

```bash
mvn <phase>
```

Maven interprets this as:

> “Run everything up to this phase.”

---

**One-line anchor:**

> Maven commands are requests to advance through the lifecycle.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “Running mvn test only runs tests”

Wrong.
It runs:

* validate
* compile
* test

---

### ❌ “Phases do the actual work”

Wrong.
**Plugins** do the work.
Phases only define *when* plugins run.

---

### ❌ “I can skip phases safely”

Wrong.
Skipping phases breaks build guarantees and reproducibility.

---

**One-line anchor:**

> Phases coordinate work; plugins perform work.

---

## 5. Failure modes / edge cases

### a) Tests fail after packaging attempt

Cause:

* misunderstanding that tests run **before** packaging

---

### b) Works locally, fails in CI

Cause:

* local builds skipped clean or install
* CI runs full lifecycle

---

### c) Unexpected plugin execution

Cause:

* plugin bound to an earlier phase than expected

---

**One-line anchor:**

> Lifecycle misunderstandings surface as CI and deployment failures.

---

## 6. Backend relevance (non-negotiable)

Lifecycle phases affect:

* **CI/CD pipelines**
* **Build reproducibility**
* **Artifact correctness**
* **Deployment confidence**

Every backend engineer must know:

* what `mvn package` really does
* why `install` vs `package` matters
* when CI should stop a build

**One-line anchor:**

> Backend delivery pipelines are built directly on Maven lifecycles.

---

## 7. Final mental model

> Maven builds are a controlled progression through fixed phases, with plugins attached at specific points.

If you understand:

* “run up to this phase”
* “plugins do work, phases order work”

you understand Maven lifecycles.

---

## 8. Links (for Obsidian)

* [[Dependency resolution]]
* [[Transitive dependencies]]
* [[Dependency scopes]]
* [[Plugins vs dependencies]]
* [[Fat JAR & classpath]]
