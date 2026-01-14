# Java Backend (Spring Boot) — Obsidian Wiki-Link Checklist

> Purpose: **Obsidian-ready checklist** using wiki links.
> Each item links to a note you can create instantly.

---

## 00 JVM & Java Backend Basics

* [ ] [[JVM lifecycle]] (startup, class loading)
* [ ] [[ClassLoader hierarchy]] (bootstrap, extension, application)
* [ ] [[Heap vs Stack]] (memory model)
* [ ] [[Object lifecycle & GC]] (allocation, reachability, collection)
* [ ] [[Exception propagation]] (checked vs unchecked, stack trace)
* [ ] [[Threads basics]] (thread vs process, concurrency intro)
* [ ] [[Java IO vs NIO]] (blocking vs non-blocking)
* [ ] [[Reflection in frameworks]] (why Spring uses reflection)
* [ ] [[Annotations at runtime]] (retention, processing)

---

## 01 Build & Dependency Management (Maven)

* [ ] [[What is a build tool]]
* [ ] [[Maven vs Gradle]]
* [ ] [[pom.xml structure]]
* [ ] [[Dependency resolution]]
* [ ] [[Transitive dependencies]]
* [ ] [[Dependency scopes]]
* [ ] [[Maven lifecycle phases]]
* [ ] [[Plugins vs dependencies]]
* [ ] [[Spring Boot parent POM]]
* [ ] [[Fat JAR & classpath]]

---

## 02 Backend & HTTP Fundamentals

* [ ] [[Client–Server model]]
* [ ] [[HTTP protocol basics]]
* [ ] [[Request–Response lifecycle]]
* [ ] [[Stateless vs Stateful systems]]
* [ ] [[REST principles]]
* [ ] [[HTTP methods semantics]]
* [ ] [[HTTP status codes]]
* [ ] [[Headers vs Body]]
* [ ] [[JSON serialization]]

---

## 03 Spring Core

* [ ] [[What is Spring]]
* [ ] [[Why Spring exists]]
* [ ] [[Inversion of Control]]
* [ ] [[Dependency Injection]]
* [ ] [[Bean definition]]
* [ ] [[Bean lifecycle]]
* [ ] [[Bean scopes]]
* [ ] [[ApplicationContext]]
* [ ] [[BeanFactory vs ApplicationContext]]
* [ ] [[Component scanning]]

---

## 04 Spring Boot

* [ ] [[What Spring Boot adds]]
* [ ] [[Auto-configuration]]
* [ ] [[Starter dependencies]]
* [ ] [[Embedded server]]
* [ ] [[@SpringBootApplication]]
* [ ] [[Spring Boot startup sequence]]
* [ ] [[Configuration properties]]
* [ ] [[Profiles]]
* [ ] [[Externalized configuration]]

---

## 05 Spring MVC

* [ ] [[What is Spring MVC]]
* [ ] [[DispatcherServlet]]
* [ ] [[Front Controller pattern]]
* [ ] [[Request mapping flow]]
* [ ] [[Controller role]]
* [ ] [[Handler mapping & adapter]]
* [ ] [[Method argument resolution]]
* [ ] [[JSON binding with Jackson]]
* [ ] [[Exception flow in MVC]]

---

## 06 Backend Architecture Layers

* [ ] [[Controller responsibilities]]
* [ ] [[Service layer design]]
* [ ] [[Repository layer design]]
* [ ] [[DTO vs Entity]]
* [ ] [[Separation of concerns]]
* [ ] [[Transaction boundaries]]

---

## 07 Validation & Error Handling

* [ ] [[Input validation flow]]
* [ ] [[Bean Validation API]]
* [ ] [[Validation annotations]]
* [ ] [[Global exception handling]]
* [ ] [[Custom exceptions]]
* [ ] [[Error response design]]

---

## 08 Persistence & Database Fundamentals

* [ ] [[Relational vs NoSQL]]
* [ ] [[ACID properties]]
* [ ] [[Indexes & query cost]]

---

## 09 MongoDB & Spring Data

* [ ] [[MongoDB core model]]
* [ ] [[MongoDB CRUD & queries]]
* [ ] [[Aggregation pipeline]]
* [ ] [[MongoDB index types]]
* [ ] [[Schema validation]]
* [ ] [[Data modeling rules]]
* [ ] [[Spring Data Mongo repositories]]
* [ ] [[MongoTemplate]]

---

## 10 Serialization & Data Flow

* [ ] [[JSON to Object flow]]
* [ ] [[Object to JSON flow]]
* [ ] [[Jackson customization]]
* [ ] [[Date & time handling]]

---

## 11 Spring Security

* [ ] [[Why security exists]]
* [ ] [[Authentication vs Authorization]]
* [ ] [[Security filter chain]]
* [ ] [[Request interception]]
* [ ] [[Stateless vs session authentication]]
* [ ] [[Password encoding]]
* [ ] [[JWT structure & flow]]
* [ ] [[Securing endpoints]]
* [ ] [[Common security mistakes]]

---

## 12 Configuration & Environment

* [ ] [[application.properties vs yml]]
* [ ] [[Profile management]]
* [ ] [[Secret management]]
* [ ] [[Configuration precedence]]

---

## 13 Logging & Observability

* [ ] [[Logging levels]]
* [ ] [[Request logging]]
* [ ] [[Error logging]]
* [ ] [[Debugging production issues]]

---

## 14 Testing Backend

* [ ] [[Unit vs integration tests]]
* [ ] [[Controller tests]]
* [ ] [[Repository tests]]
* [ ] [[Mocking & test context]]

---

## 15 Runtime & Deployment Awareness

* [ ] [[Running fat JAR]]
* [ ] [[Port binding]]
* [ ] [[Environment-specific configuration]]
* [ ] [[Reverse proxy basics]]

---

> Completion rule: a topic is complete **only if you can explain it, code it, and debug it**.
