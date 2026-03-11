# Spring Boot — A to Z 🚀
> Nothing skipped. Nothing abstract. Everything explained with code.

---

## 📋 Curriculum Roadmap

| Phase | Topic | What You'll Master | Status |
|-------|-------|--------------------|--------|
| [[Phase-1-Foundations/README\|Phase 1]] | Java + Spring Foundations | IoC, DI, Beans, ApplicationContext, Maven | ✅ Done |
| [[Phase-2-SpringBoot-Core/README\|Phase 2]] | Spring Boot Core | @SpringBootApplication, auto-config, starters, embedded server | ✅ Done |
| [[Phase-3-REST-API/README\|Phase 3]] | REST API Fundamentals | @RestController, @GetMapping, @PostMapping, @RequestBody, @PathVariable | 🔲 Next |
| [[Phase-4-Request-Response/README\|Phase 4]] | Request & Response | DTOs, ResponseEntity, HTTP status codes | 🔲 Upcoming |
| [[Phase-5-JPA-Hibernate/README\|Phase 5]] | Data Layer — JPA & Hibernate | @Entity, @Repository, JPQL, relationships, lazy loading | ✅ Partial |
| [[Phase-6-Service-Layer/README\|Phase 6]] | Service Layer | @Service, @Transactional, 3-layer architecture | 🔲 Upcoming |
| [[Phase-7-Database-Config/README\|Phase 7]] | Database Configuration | application.properties, datasource, Flyway | 🔲 Upcoming |
| [[Phase-8-Exception-Handling/README\|Phase 8]] | Exception Handling | @ControllerAdvice, @ExceptionHandler, custom errors | 🔲 Upcoming |
| [[Phase-9-Validation/README\|Phase 9]] | Validation | @Valid, @NotNull, @Size, BindingResult | 🔲 Upcoming |
| [[Phase-10-Security/README\|Phase 10]] | Spring Security | Auth, JWT, roles, filters, SecurityFilterChain | 🔲 Upcoming |
| [[Phase-11-Testing/README\|Phase 11]] | Testing | @SpringBootTest, @WebMvcTest, MockMvc, Mockito | 🔲 Upcoming |
| [[Phase-12-Profiles/README\|Phase 12]] | Profiles & Configuration | @Profile, application-dev.yml, env config | 🔲 Upcoming |
| [[Phase-13-Logging/README\|Phase 13]] | Logging & Monitoring | SLF4J, Logback, Spring Actuator | 🔲 Upcoming |
| [[Phase-14-Docker/README\|Phase 14]] | Docker & Deployment | Dockerfile, docker-compose, JAR packaging | 🔲 Upcoming |
| [[Phase-15-Real-Project/README\|Phase 15]] | Real Project | Full REST API — auth, DB, validation, error handling | 🔲 Final |

---

## 🧠 Master Mental Model

```
Client → HTTP Request
         ↓
   @RestController  (receives, validates, delegates)
         ↓
   @Service         (business logic)
         ↓
   @Repository      (database access)
         ↓
   Entity → DTO     (map, never expose entity)
         ↓
   ResponseEntity   (status code + body)
         ↓
Client ← JSON Response

If exception thrown anywhere:
   @ControllerAdvice → @ExceptionHandler → ErrorResponse JSON
```

---

## 📁 Your Existing Notes Live In
- [[Phase-1-Foundations/01-IoC-and-DI|IoC & Dependency Injection]]
- [[Phase-1-Foundations/02-Spring-Bean|Spring Bean]]
- [[Phase-1-Foundations/03-ApplicationContext|ApplicationContext]]
- [[Phase-1-Foundations/04-Maven|Maven]]
- [[Phase-1-Foundations/05-Singleton|Singleton]]
- [[Phase-2-SpringBoot-Core/01-What-is-Spring|What is Spring]]
- [[Phase-2-SpringBoot-Core/02-What-is-Spring-Boot|What is Spring Boot]]
- [[Phase-2-SpringBoot-Core/03-Spring-Boot-Initialization|Spring Boot Initialization]]
- [[Phase-5-JPA-Hibernate/01-JPA-Annotations|JPA Relationship Annotations]]
