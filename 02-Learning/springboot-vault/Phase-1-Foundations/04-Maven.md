# Maven
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[../🏠 Home]]

---

## 1. What Maven Is

> Maven is a **build + dependency management tool** for Java projects.

---

## 2. Problems Maven Solves

| Without Maven | With Maven |
|---------------|------------|
| Manually download JARs | Dependencies auto-downloaded |
| Handle version conflicts yourself | Versions managed centrally |
| Write custom build scripts | Standard lifecycle commands |

---

## 3. pom.xml

Central configuration file. Defines:
- Project metadata (groupId, artifactId, version)
- Dependencies
- Build configuration

---

## 4. Project Coordinates

Every Maven project has:
```
groupId    → company / domain    (com.example)
artifactId → project name        (taskmanager)
version    → release version     (1.0.0)
```

---

## 5. Standard Directory Structure

```
src/
 ├── main/
 │   ├── java/         ← your source code
 │   └── resources/    ← application.properties, etc.
 └── test/
     └── java/         ← test classes
```

Maven **expects** this. Don't fight it.

---

## 6. Dependency Scopes

| Scope | When Used |
|-------|-----------|
| `compile` | Default — used in production app |
| `test` | Only in tests, not in final JAR |
| `runtime` | Needed at runtime, not compile time |

---

## 7. Build Lifecycle

```
clean → compile → test → package → install
```

| Command | What It Does |
|---------|-------------|
| `clean` | Deletes old build output |
| `compile` | Compiles source code |
| `test` | Runs tests |
| `package` | Creates JAR/WAR file |
| `install` | Puts artifact in local `.m2` repo |

---

## 8. Daily Commands You'll Use

| Command | Use |
|---------|-----|
| `mvn clean install` | Full build + tests |
| `mvn spring-boot:run` | Start the app |
| `mvn package -DskipTests` | Build JAR, skip tests |
| `mvn dependency:tree` | Debug version conflicts |
| `./mvnw` | Use Maven Wrapper (preferred) |

---

## 9. Maven Wrapper (mvnw)

Modern Spring Boot projects include `mvnw` and `mvnw.cmd`.

**Why:** Ensures everyone uses the same Maven version without installing it manually.

```bash
./mvnw spring-boot:run     # instead of mvn spring-boot:run
```

---

## 10. Spring Boot Parent POM

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.x.x</version>
</parent>
```

**Why it matters:**
- Manages dependency versions for you
- You don't write versions for starters

```xml
<!-- No version needed — parent handles it -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

> ⚠️ Don't add versions manually for Spring-managed libs — it breaks compatibility.

---

## 11. Overriding a Version

```xml
<properties>
    <hibernate.version>6.4.1.Final</hibernate.version>
</properties>
```

Only do this for libraries **not** managed by Spring's parent.

---

## One-Line Summary

> Maven standardizes **how Java projects are built and how dependencies are managed** so you don't do it manually.

---

**Prev →** [[03-ApplicationContext|ApplicationContext]]
**Next →** [[05-Singleton|Singleton]]
