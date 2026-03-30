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

## @Email
- Checks basic format only
- Does NOT enforce domain (.com, .edu, etc.)
- Allows values like "a@b"

---

## Business Validation (Custom)

Example:
```java
email.endsWith("@trp.srmtrichy.edu.in")
```

- Enforces real requirement
- More important than format validation

---

## Why not strict regex?
- Emails have many valid formats
- Regex becomes complex and unreliable
- Business rules are clearer and safer

---

## Best Practice

Use both:
- @Email → format check
- custom logic → business rule

---

## Key Insight

Validation has 2 levels:
1. Syntax validation (annotations)
2. Business validation (service layer)

## AuthResponse (DTO)
- Used for sending login response
- Contains:
  - token
  - role
  - userId

---

## PasswordEncoder.matches()
- Compares raw password with hashed password
- Does NOT decrypt
- Secure comparison method

---

## Login Flow

1. Find user by email
2. If not found → error
3. Compare password
4. If mismatch → error
5. Return token + user data

---

## Security Practice

Use same error message:
- "Invalid credentials"

Reason:
- prevents user enumeration attacks
## JWT Payload (Claims)
- Stores user data inside token
- Common fields:
  - sub (userId)
  - role
  - email

---

## Why store role in JWT?
- Avoid DB lookup on every request
- Enables stateless authentication
- Improves performance

---

## Frontend vs Backend Trust

Frontend:
- receives role for UI logic
- NOT trusted

Backend:
- extracts role from JWT
- fully trusted (signed token)

---

## Stateless Authentication

- Server does NOT store session
- JWT contains all required data
- Each request is self-contained

---

## Key Insight

JWT = identity + authorization in one token

## JWT (JSON Web Token)
- Token used for authentication
- Structure: HEADER.PAYLOAD.SIGNATURE

---

## JwtUtil
- Handles token creation and parsing
- Central place for JWT logic

---

## Claims
- Data stored inside JWT
- Example:
  - userId (subject)
  - role
  - email

---

## @Value
Injects values from application.yml

---

## @PostConstruct
Runs after bean is created

- Used to initialize secret key

---

## Secret Key
- Used to sign JWT
- Must be secure and private

---

## Token Expiration
- Prevents long-term misuse
- Forces re-login after time limit

---

## Key Insight

JWT = signed data → can be trusted without DB lookup

## JwtUtil
- Handles JWT creation and parsing
- Central class for token logic

---

## @Component
- Marks class as Spring bean
- Allows injection into services

---

## @Value
- Injects values from application.yml

---

## @PostConstruct
- Runs after bean initialization
- Used to prepare secret key

---

## generateToken()
- Creates JWT
- Includes:
  - userId (subject)
  - role
  - email

---

## extractClaims()
- Reads JWT
- Verifies signature
- Returns stored data

---

## Key Insight

JWT is signed → data can be trusted without DB query

## spring-boot-starter-web
- Correct dependency for building REST APIs
- Includes:
  - Spring MVC
  - Tomcat
  - Jackson

---

## Why not webmvc?
- Internal module
- Not intended for direct use

---

## spring-boot-starter-test
- Unified testing dependency
- Includes:
  - JUnit
  - Mockito
  - Spring Test

---

## Key Insight

Always use Spring Boot starters  
→ They manage dependencies correctly