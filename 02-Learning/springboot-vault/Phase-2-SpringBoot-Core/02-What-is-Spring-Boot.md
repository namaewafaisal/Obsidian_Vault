# What is Spring Boot?
**Phase:** [[README|Phase 2 — Spring Boot Core]]
**Back:** [[../🏠 Home]]

---

## Definition

> **Spring Boot** is a framework built on top of Spring that **removes manual configuration** and lets you build production-ready backend applications quickly.

```
Spring Boot = Spring + auto-configuration + embedded server + conventions
```

---

## Why Spring Boot Exists

### Using plain Spring
- Too much XML / manual configuration
- External server setup required (Tomcat)
- Manage dependency versions yourself

### Spring Boot gives you
- Auto-configured components
- Embedded Tomcat (no server install)
- Dependency version management via Parent POM
- Sensible defaults you can override

---

## What Spring Boot Does Automatically

- Detects libraries on classpath
- Configures beans based on what it finds
- Starts embedded Tomcat
- Sets default port (8080), logging, JSON mapper, etc.

---

## Embedded Server

Spring Boot includes embedded **Tomcat** by default.

```
No manual server installation.
Run app as a normal Java program.
One JAR = app + server.
```

---

## Starter Dependencies

Starters are **dependency bundles** — one line pulls in everything you need.

| Starter | What It Includes |
|---------|-----------------|
| `spring-boot-starter-web` | Spring MVC + Jackson + Tomcat |
| `spring-boot-starter-data-jpa` | Hibernate + JPA + DB connection |
| `spring-boot-starter-security` | Spring Security |
| `spring-boot-starter-validation` | Bean Validation (JSR-303) |
| `spring-boot-starter-test` | JUnit + Mockito + MockMvc |

---

## Auto-Configuration

Spring Boot scans the classpath and applies configuration automatically.

```
JPA starter present    → configures EntityManager
Web starter present    → configures DispatcherServlet + Tomcat
Security present       → configures SecurityFilterChain
```

Controlled by `@SpringBootApplication` → `@EnableAutoConfiguration`.

---

## @SpringBootApplication (3 in 1)

```java
@SpringBootApplication
// = @Configuration + @ComponentScan + @EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

| Annotation | What it does |
|-----------|-------------|
| `@Configuration` | This class provides bean definitions |
| `@ComponentScan` | Scan for @Component, @Service, @Repository in this package |
| `@EnableAutoConfiguration` | Apply auto-config based on classpath |

---

## Configuration

```properties
# application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost/mydb
spring.jpa.hibernate.ddl-auto=update
```

---

## Spring Boot vs Spring

| Spring | Spring Boot |
|--------|------------|
| Manual setup | Auto-setup |
| External server | Embedded server |
| More boilerplate | Minimal boilerplate |
| Steep learning | Fast startup |

---

## One-Line Summary

> **Spring Boot simplifies Spring by auto-configuring the application and embedding the server so you can focus on building APIs and business logic.**

---

**Prev →** [[01-What-is-Spring|What is Spring]]
**Next →** [[03-Spring-Boot-Initialization|Spring Boot Initialization]]
