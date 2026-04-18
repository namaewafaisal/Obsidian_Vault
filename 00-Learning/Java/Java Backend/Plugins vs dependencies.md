# Plugins vs Dependencies (Maven).

## 0. The real problem this concept solves

In a backend build, **two very different kinds of things happen**:

1. Code is **executed** (compile, test, package, repackage, etc.)
2. Code is **used** by your application at runtime

Beginners (and many intermediates) confuse these and end up:

* adding build tools as dependencies
* bloating runtime classpaths
* misconfiguring builds
* misunderstanding Spring Boot behavior

The real problem is:

> **How do we clearly separate “code that runs during the build” from “code that runs inside the application”?**

**One-line anchor:**

> Plugins execute build logic; dependencies provide application code.

---

## 1. What this concept really is (plain English)

### Dependencies

Dependencies are **libraries your application code uses**.

They:

* end up on the **classpath**
* are loaded by the **JVM at runtime**
* are referenced in your Java code (`import`)

Your application **cannot run without them**.

---

### Plugins

Plugins are **tools Maven runs during the build**.

They:

* execute tasks like compiling, testing, packaging
* run **inside Maven**, not inside your app
* do **not** go on the runtime classpath

Your application **never sees plugins**.

---

**One-line anchor:**

> Dependencies live in your app; plugins live in your build.

---

## 2. How it actually works (step-by-step)

When you run:

```bash
mvn package
```

What happens internally:

1. Maven advances through lifecycle phases
2. For each phase:

   * it looks for **plugins bound to that phase**
3. Plugins execute:

   * compile Java
   * run tests
   * package JARs
4. During compilation:

   * dependencies are placed on the compile classpath
5. At runtime:

   * only dependencies are available
   * plugins are gone

Plugins finish their job **before your app ever starts**.

**One-line anchor:**

> Plugins act during the build; dependencies act during execution.

---

## 3. Key internal pieces (only after understanding)

### a) Where they are declared

#### Dependencies

Declared under:

```xml
<dependencies>
```

They affect:

* compile classpath
* test classpath
* runtime classpath

---

#### Plugins

Declared under:

```xml
<build>
  <plugins>
```

They affect:

* how Maven builds
* which tasks are executed
* when those tasks run

---

### b) Lifecycle binding (critical difference)

* Dependencies are **always passive**
* Plugins are **actively executed**

Plugins are bound to lifecycle phases such as:

* `compile`
* `test`
* `package`

This is why:

* phases alone do nothing
* plugins do everything

---

### c) Spring Boot example (important)

* `spring-boot-starter-web` → **dependency**
* `spring-boot-maven-plugin` → **plugin**

The plugin:

* repackages the JAR
* creates executable fat JARs

The dependency:

* provides Spring MVC code at runtime

---

**One-line anchor:**

> If something runs code during the build, it is a plugin—not a dependency.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “Plugins are just special dependencies”

Wrong.
Plugins are **executables run by Maven**, not libraries used by your app.

---

### ❌ “If it’s in pom.xml, it’s on the classpath”

Wrong.
Only dependencies affect the runtime classpath.

---

### ❌ “Removing a plugin can’t affect runtime”

Wrong.
Removing a plugin can change **how the artifact is built**, which affects runtime behavior indirectly.

---

**One-line anchor:**

> Plugins don’t run at runtime—but they shape what runtime looks like.

---

## 5. Failure modes / edge cases

### a) Application won’t start as executable JAR

Cause:

* missing or misconfigured build plugin (e.g., Spring Boot plugin)

---

### b) Bloated runtime classpath

Cause:

* build tools incorrectly added as dependencies

---

### c) CI build behaves differently

Cause:

* plugin configuration differs from local expectations

---

**One-line anchor:**

> Plugin mistakes cause build failures; dependency mistakes cause runtime failures.

---

## 6. Backend relevance (non-negotiable)

Understanding this distinction affects:

* **Artifact correctness**
* **Startup behavior**
* **CI/CD reliability**
* **Debugging clarity**

Senior backend engineers can always answer:

* “Is this a plugin or a dependency?”
* “Does this affect build-time or runtime?”

**One-line anchor:**

> Clean backend builds depend on a strict separation between build logic and application logic.

---

## 7. Final mental model

> Dependencies are code your application runs.
> Plugins are code Maven runs to build your application.

If you ever wonder where something belongs, ask:
**“Who executes this—my app or Maven?”**

---

## 8. Links (for Obsidian)

* [[Maven lifecycle phases]]
* [[Dependency scopes]]
* [[Spring Boot parent POM]]
* [[Fat JAR & classpath]]
* [[pom.xml structure]]

