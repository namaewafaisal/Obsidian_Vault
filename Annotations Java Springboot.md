---
topic: JPA Annotations Basics
date-time: INVALID_DATETIME
time-taken(min): INVALID_TIME
---

# JPA Annotations (Basics)

## @Entity
Marks a class as a database table.

- Required for Hibernate to track the class
- Without it → no table created

---

## @Table(name = "users")
Specifies table name.

- Used to avoid default naming
- Important when:
  - default name is wrong
  - conflicts with reserved keywords (e.g., `user` in PostgreSQL)

---

## @Id
Marks primary key.

- Every entity must have one
- Used to uniquely identify rows

---

## @GeneratedValue(strategy = GenerationType.UUID)
Auto-generates ID.

- Uses UUID instead of auto-increment
- Why:
  - avoids collisions
  - better for distributed systems

---

## @Column
Defines column constraints.

Example:
```java
@Column(unique = true, nullable = false)
```

* `unique` → no duplicates
* `nullable = false` → cannot be null

Why use:

* Enforces rules at DB level (not just Java)

---

## @Enumerated(EnumType.STRING)

Controls how enums are stored.

* `STRING` → stores text (SAFE)
* `ORDINAL` → stores numbers (DANGEROUS)

Why STRING:

* readable
* safe if enum order changes

---

## @PrePersist

Lifecycle hook.

* Runs before INSERT
* Used for:

  * timestamps
  * default values

Example:

```java
@PrePersist
void prePersist() {
    this.createdAt = now();
}
```

---

## Field Types Mapping

| Java Type     | PostgreSQL Type |
| ------------- | --------------- |
| UUID          | uuid            |
| String        | text/varchar    |
| boolean       | boolean         |
| LocalDateTime | timestamp       |

---

## Lombok

### @Data

* Generates getters/setters
* Also adds toString, equals, hashCode

⚠️ Not ideal for entities (can cause issues later)

---

### @NoArgsConstructor

* Required by JPA
* Hibernate needs a default constructor

---

## Key Takeaways

* Annotations define how Java ↔ DB mapping works
* Always enforce constraints at DB level
* Prefer UUID + STRING enums for safety
* Lifecycle hooks reduce manual logic

## @Service
Marks a class as a Spring service component.

- Managed by Spring container
- Used for business logic layer

---

## @RequiredArgsConstructor (Lombok)
Generates constructor for all `final` fields.

- Enables constructor injection
- Avoids using @Autowired

---

## @Bean
Defines a method that returns an object managed by Spring.

- Used inside @Configuration classes
- Example: PasswordEncoder

---

## @Configuration
Marks a class as a configuration class.

- Contains @Bean methods
- Used to define Spring-managed components

## @RestController
Marks class as REST API controller.

- Returns JSON/text
- Combines @Controller + @ResponseBody

---

## @RequestMapping
Defines base URL for all endpoints in class.

---

## @PostMapping
Handles HTTP POST requests.

- Used for creating data

---

## @RequestBody
Maps JSON request → Java object.

---

## SecurityFilterChain
Defines security rules for requests.

- Controls access (permit, restrict)
- Replaces older WebSecurityConfigurerAdapter

---

## HttpSecurity
Used to configure web security.

- CSRF
- authorization rules
- filters

## @Valid
Triggers validation on request object.

- Used in controller layer
- Required to activate @Email, @NotBlank, etc.

---

## @Email
Validates email format.

- Only works when @Valid is present
- Does NOT check if email exists (only format)

---

## @NotBlank
Ensures field is not null or empty.

---

## Validation Flow
- Define rules in DTO
- Trigger with @Valid
- Spring automatically handles errors