# Spring Boot Initialization (start.spring.io)
**Phase:** [[README|Phase 2 — Spring Boot Core]]
**Back:** [[🏠 Home]]

---

## Step 1 — Project Generation (start.spring.io)

When you click **Generate**, Spring Initializr creates a ready-to-run project with:
- `pom.xml` with chosen starters
- Standard directory structure
- Main application class
- `application.properties`

No code is running yet — this is just scaffolding.

---

## Files Created

### pom.xml
- Spring Boot parent (version management)
- Your chosen starter dependencies
- Java version + build plugin

### Main Application Class
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### application.properties
Central config file for everything (port, DB, logging, etc.)

---

## Step 2 — What Happens When You RUN the App

### Flow (in order)

```
1. JVM starts → main() runs
       ↓
2. SpringApplication.run() called
       ↓
3. ApplicationContext created (IoC container)
       ↓
4. Component scanning (finds @Component, @Service, @Repository, @Controller)
       ↓
5. Auto-Configuration runs (based on classpath)
       ↓
6. Dependency Injection happens (beans wired)
       ↓
7. Embedded Tomcat starts → port 8080 opens
       ↓
8. Console: "Started Application in X seconds"
```

---

## What Each Step Means

| Step | What Spring Does |
|------|-----------------|
| ApplicationContext | Creates IoC container, prepares bean registry |
| Component Scanning | Finds all annotated classes in the package tree |
| Auto-Configuration | Reads classpath, applies matching configurations |
| DI | Resolves and injects all dependencies |
| Tomcat | Opens port, starts accepting HTTP requests |

---

## What You Control vs What Spring Controls

| You Control | Spring Controls |
|-------------|----------------|
| Business logic | Object creation |
| API design | Dependency wiring |
| Configuration overrides | Tomcat lifecycle |
| Bean definitions | JSON mapping |

---

## One-Line Summary

> Starting a Spring Boot app = creating an IoC container, auto-configuring it, injecting beans, and starting an embedded server.

---

**Prev →** [[02-What-is-Spring-Boot|What is Spring Boot]]
**Next →** [[04-Autowired|@Autowired]]
