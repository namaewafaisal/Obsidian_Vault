# What is a Build Tool

### 1️⃣ Quick intuition

A build tool is like an **assembly line** that takes raw parts (source code + libraries) and produces a **ready-to-run product** (JAR/WAR), reliably and repeatedly.

---

### 2️⃣ What a build tool actually does

A **build tool** automates everything needed to turn source code into a runnable artifact:

It can:

* Compile source code
* Download and manage dependencies
* Run tests
* Package output (JAR/WAR)
* Prepare the app for deployment

Without a build tool, you would do all of this **manually**.

---

### 3️⃣ Why build tools exist (the real problem)

In real projects:

* There are **many dependencies**
* Versions must be consistent
* Builds must work the same on every machine

Manual builds:

* Break easily
* Are not reproducible
* Don’t scale to teams or CI/CD

Build tools solve **repeatability and consistency**.

---

### 4️⃣ Where Maven fits

**Maven** is a build tool that is:

* Convention-based (standard project structure)
* Declarative (you describe *what*, not *how*)
* Dependency-aware (handles libraries automatically)

You don’t tell Maven *how* to compile —
you tell it *what project this is*.

---

### 5️⃣ What Maven produces

Maven typically produces:

* `jar` → standalone Java application
* `war` → web application
* `pom.xml` → project definition (not output, but core)

Spring Boot ultimately runs a **Maven-built artifact**.

---

### 6️⃣ Backend relevance

As a backend developer, Maven controls:

* Which libraries are used
* Which versions are loaded at runtime
* How your app is packaged
* How Spring Boot starts

If Maven is wrong → app may not even start.

---

### 7️⃣ Common misconception

> Maven is not just “for downloading dependencies”.

Dependency management is only **one part**.
The bigger role is **build orchestration**.

---

### 8️⃣ One-line mental model

> **A build tool automates compilation, dependency management, testing, and packaging into a repeatable process.**

