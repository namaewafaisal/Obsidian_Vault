## Annotations at Runtime (Java)

### 1️⃣ Quick intuition

Annotations are **labels on code** that frameworks **read at runtime** to decide behavior—your code doesn’t call them; frameworks **interpret** them.

---

### 2️⃣ What annotations actually are

Annotations are **metadata**, not logic.
They attach information to:

* classes
* methods
* fields
* parameters

They do **nothing by themselves**.

---

### 3️⃣ Retention policy (critical)

Retention decides **when** an annotation is available.

* `SOURCE` → compiler only (e.g., linting)
* `CLASS` → bytecode, not runtime
* `RUNTIME` → available via reflection (**what frameworks need**)

Spring works **only** with `RUNTIME` annotations.

---

### 4️⃣ How frameworks use annotations

At startup, frameworks:

1. Scan classes
2. Read annotations via reflection
3. Apply behavior conditionally

Examples:

* `@Component` → create bean
* `@Autowired` → inject dependency
* `@RestController` → map HTTP requests

No annotation → no behavior.

---

### 5️⃣ Example (simplified)

```java
if (clazz.isAnnotationPresent(Service.class)) {
    createAndRegisterBean(clazz);
}
```

This is essentially what Spring does internally.

---

### 6️⃣ Annotation vs configuration

Annotations replace:

* XML config
* boilerplate wiring

They make configuration:

* closer to code
* easier to maintain
* harder to misuse

---

### 7️⃣ Backend relevance

Annotations drive:

* IoC & DI
* REST routing
* Transactions
* Security rules
* ORM mapping[^1]

---

### 8️⃣ One-line mental model

> **Annotations are metadata that frameworks read at runtime to decide behavior.**


[^1]: ORM maps Java objects to database tables (e.g., JPA/Hibernate).

