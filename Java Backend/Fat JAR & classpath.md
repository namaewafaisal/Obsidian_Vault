Proceeding with **Fat JAR & Classpath**.

Below is the **authoritative backend note**, written **strictly using your locked Backend Learning Note template**, Obsidian-ready, and focused on **runtime reality**, not Maven trivia.

---

# 📘 Backend Learning Note — Fat JAR & Classpath

---

## 0. The real problem this concept solves

A backend application is not just your compiled code.
It also needs:

* frameworks
* libraries
* configuration
* a predictable startup environment

If you ship **only your compiled classes**, the app will fail with missing libraries.
If you rely on external classpaths, deployments become:

* fragile
* environment-dependent
* hard to reproduce

The real problem is:

> **How do we package a backend application so it can run anywhere with zero external setup?**

**One-line anchor:**

> Fat JARs package the application and all its dependencies into a single runnable unit.

---

## 1. What this concept really is (plain English)

### Classpath

The **classpath** is the list of locations where the JVM looks for classes at runtime.

It can include:

* directories
* JAR files
* multiple external paths

If a class is **not on the classpath**, the JVM cannot load it.

---

### Fat JAR

A **fat JAR** (also called an executable or uber JAR) is a **single JAR file** that contains:

* your application classes
* all runtime dependencies
* a bootstrap mechanism to load them

You run it with:

```bash
java -jar app.jar
```

No external classpath needed.

---

**One-line anchor:**

> Classpath defines *where classes are found*; a fat JAR eliminates external classpaths.

---

## 2. How it actually works (step-by-step)

### Without a fat JAR (traditional)

1. Your code is compiled into classes
2. Dependencies exist as separate JARs
3. JVM is started with a long classpath:

   ```bash
   java -cp lib/*:app.jar com.example.Main
   ```
4. If any JAR is missing → runtime failure

---

### With a fat JAR (Spring Boot style)

1. Maven resolves runtime dependencies
2. Spring Boot plugin repackages:

   * app classes
   * dependency JARs
3. A launcher class is added
4. JVM is started with:

   ```bash
   java -jar app.jar
   ```
5. The launcher builds the classpath internally

Everything needed is already inside the JAR.

---

**One-line anchor:**

> Fat JARs move classpath construction inside the application package.

---

## 3. Key internal pieces (only after understanding)

### a) JVM class loading (important)

At runtime:

* JVM asks classloaders for classes
* classloaders search the classpath

If the classpath is wrong → `ClassNotFoundException`.

Fat JARs ensure the classloader can always find required classes.

---

### b) Spring Boot’s launcher mechanism

Spring Boot does **not** dump all classes into one flat JAR.

Instead:

* dependencies remain as nested JARs
* a custom classloader loads them

This avoids:

* file conflicts
* resource overwrites
* class shadowing

---

### c) Why plain Maven JAR is not enough

Default Maven packaging creates:

* a thin JAR
* without dependencies

This is why:

* plain JARs fail in production
* Spring Boot adds a plugin step

---

**One-line anchor:**

> A fat JAR is a runtime packaging strategy, not just a larger file.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “Fat JARs are just big JARs”

Wrong.
They include **custom class loading logic**.

---

### ❌ “Classpath issues are compile-time problems”

Wrong.
Classpath issues are **runtime problems**.

---

### ❌ “Docker replaces fat JARs”

Wrong.
Docker packages environments; fat JARs package applications.
They solve different layers.

---

**One-line anchor:**

> Most production startup failures are classpath failures.

---

## 5. Failure modes / edge cases

### a) `ClassNotFoundException` at startup

Cause:

* dependency missing from runtime classpath
* thin JAR deployed accidentally

---

### b) Works in IDE, fails in production

Cause:

* IDE builds dynamic classpaths
* production relies on packaged artifact

---

### c) Duplicate resources

Cause:

* conflicting dependencies without proper packaging strategy

---

**One-line anchor:**

> Fat JARs exist to eliminate environment-dependent classpath errors.

---

## 6. Backend relevance (non-negotiable)

Fat JARs directly affect:

* **Deployment simplicity**
* **Startup reliability**
* **CI/CD pipelines**
* **Container behavior**
* **Cloud portability**

Every backend engineer must understand:

* what `java -jar` actually does
* why Spring Boot apps start reliably
* why missing dependencies crash servers

**One-line anchor:**

> Fat JARs are the backbone of modern Java backend deployment.

---

## 7. Final mental model

> The classpath is how the JVM finds code.
> A fat JAR ensures the JVM never has to look anywhere else.

If you understand that, deployment problems stop being mysterious.

---

## 8. Links (for Obsidian)

* [[Plugins vs dependencies]]
* [[Maven lifecycle phases]]
* [[Dependency scopes]]
* [[Spring Boot parent POM]]
* [[pom.xml structure]]
