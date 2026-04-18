# Maven — Practical Notes (Backend-Focused)

## 1. What Maven Is (1 line)

Maven is a **build + dependency management tool** for Java projects.

---

## 2. Problems Maven Solves

Without Maven:

* Manually download JARs
* Handle version conflicts yourself
* Write custom build scripts

With Maven:

* Dependencies auto-downloaded
* Standard project structure
* Reproducible builds

---

## 3. Core Concepts (Must Know)

### a) `pom.xml`

* Central configuration file
* Defines:

  * Project metadata
  * Dependencies
  * Build configuration

You **must** be comfortable reading this file.

---

### b) Project Coordinates

Every Maven project has:

```
groupId    → company / domain
artifactId → project name
version    → release version
```

Example:

```
com.example : taskmanager : 1.0.0
```

Used for:

* Dependency identification
* Publishing artifacts

---

### c) Standard Directory Structure

```
src/
 ├── main/
 │   ├── java/
 │   └── resources/
 └── test/
     └── java/
```

Maven **expects this**.
Do not fight it.

---

## 4. Dependencies (Most Important Part)

### What a Dependency Is

An external library your project uses.

Defined inside:

```
<dependencies>
```

Maven will:

* Download it
* Download its dependencies (transitive)
* Put them on classpath

---

### Dependency Scope (Only These Matter)

* `compile` → default, used in app
* `test` → used only in tests
* `runtime` → needed at runtime, not compile

You don’t need others initially.

---

## 5. Maven Repository

* Central repository: Maven Central
* Local cache: `.m2` folder

Once downloaded:

* No internet needed again
* Shared across projects

---

## 6. Build Lifecycle (Know This Exactly)

Main phases you care about:

```
clean → compile → test → package → install
```

What they do:

* `clean`   → deletes old build
* `compile` → compiles source code
* `test`    → runs tests
* `package` → creates JAR/WAR
* `install` → puts artifact in local repo

Spring Boot mostly uses `package`.

---

## 7. Maven Plugins (Minimal Understanding)

Plugins extend Maven.

Important ones:

* Compiler plugin → Java version
* Surefire plugin → testing
* Spring Boot plugin → run/package app

You usually **configure**, not write plugins.

---

## 8. Maven vs Gradle (Quick Clarity)

| Maven               | Gradle             |
| ------------------- | ------------------ |
| XML                 | Groovy/Kotlin      |
| Verbose             | Concise            |
| Easy to read        | Easy to write      |
| Enterprise standard | Popular in Android |

Knowing Maven is enough.

---

## 9. Maven in Spring Boot (Critical)

Spring Boot:

* Uses Maven as default
* Auto-configures plugins
* Manages dependency versions (BOM)

You mostly:

* Add dependencies
* Run the app
* Package the app

---

## 10. What You Can Ignore (For Now)

* Multi-module builds
* Custom repositories
* Plugin development
* Parent POM creation
* Maven profiles (advanced)

Learn only when required.

---

## 11. Real-World Usage Pattern

You will mostly:

1. Create project
2. Add dependencies
3. Run application
4. Package application

That’s 90% usage.

---

## One-Line Summary (Memorize This)

> Maven standardizes **how Java projects are built and how dependencies are managed** so you don’t do it manually.

---

These are solid foundational notes. To make them truly "backend-ready" for a Spring Boot role, you need to add the **lifecycle commands**, the **Maven Wrapper**, and the specific way Spring Boot handles **versioning**.

Here is the "Phase 2" expansion to make your notes complete for professional work.

---

## 9. The "Maven Wrapper" (Crucial for Teams)

Most modern Spring Boot projects use the **Maven Wrapper** instead of a local Maven installation.

* **Files:** You will see `mvnw` (Linux/Mac) and `mvnw.cmd` (Windows).
* **Why:** It ensures every developer and the CI/CD server use the *exact same* Maven version without needing to install it manually.
* **Usage:** Simply replace `mvn` with `./mvnw`.

---

## 10. Common Backend Commands

In a real job, you’ll use these 5-6 commands daily:

| Command | What it actually does for you |
| --- | --- |
| `mvn clean install` | Wipes old build, runs tests, and puts your JAR in `.m2`. |
| `mvn spring-boot:run` | Starts your application immediately from the terminal. |
| `mvn package -DskipTests` | Builds the JAR but **skips tests** (use only when debugging!). |
| `mvn dependency:tree` | **The Debugger's Friend:** Shows exactly why a library version was picked. |
| `mvn clean compile` | Just checks if your code (and MapStruct/Lombok) compiles. |

---

## 11. Maven in Spring Boot: The "Magic"

Spring Boot uses Maven differently than a standard Java app.

### a) The Starter Parent (`<parent>`)

Almost every project starts with this:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.x.x</version>
</parent>

```

**Why it matters:** This acts as a "Global Settings" file. It defines the Java version, encoding, and—most importantly—**dependency versions**.

### b) Version-less Dependencies

Because of the Parent POM above, you **do not** write versions for starters:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

```

> **Pro Tip:** If you add a version manually, you might break compatibility. Only add versions for libraries *not* managed by Spring.

### c) Overriding Versions

Need a newer version of a specific library (e.g., Hibernate) than what Spring provides? Use **Properties**:

```xml
<properties>
    <hibernate.version>6.4.1.Final</hibernate.version>
</properties>

```

---

## 12. Handling Multiple Environments (Profiles)

Backend devs often need different builds for `dev`, `test`, or `prod`.

* **Command:** `mvn clean install -Pprod`
* **Result:** Maven activates the `<profile>` with id `prod` in your `pom.xml`, which might switch your database URL or API keys.

---

## Summary Checklist for your next Interview:

* [ ] Can I explain why `mvnw` is better than `mvn`?
* [ ] Do I know how to find a version conflict using `dependency:tree`?
* [ ] Do I understand that the `parent` POM is what allows me to omit versions in my dependencies?

---

**Would you like me to show you a "Before vs After" of a `pom.xml` to see how Spring Boot simplifies it?**