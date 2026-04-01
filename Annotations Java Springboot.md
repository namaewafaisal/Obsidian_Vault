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

## Maven Dependency Resolution

- Adding dependency ≠ automatically available
- Must reload Maven project

---

## mvn clean install
- Downloads dependencies
- Compiles project
- Fixes missing imports

---

## mvn dependency:tree
- Shows all dependencies
- Used to verify libraries are loaded

---

## JJWT Libraries

- jjwt-api → interfaces
- jjwt-impl → implementation (runtime)
- jjwt-jackson → JSON parsing

---

## Secret Key Handling

Two approaches:
1. Base64 encoded → use Decoders.BASE64
2. Plain string → use getBytes()

---

## Key Insight

Red import = dependency not resolved, not code issue

## Maven Dependency Version Rule

Spring Boot starters:
- Version managed automatically
- DO NOT specify version

External libraries (like jjwt):
- Must specify version manually

---

## JJWT Version
- Current stable: 0.12.5

---

## Common Error

"dependency.version is missing"

Reason:
- Library not managed by Spring Boot

Fix:
- Add `<version>` manually

---

## Key Insight

Dependency management depends on parent BOM  
→ not all libraries are covered

## JWT Secret Types

### Plain String Secret
Example:
"mysecret123"

Usage:
secret.getBytes()

---

### Base64 Encoded Secret
Example:
"c2VjcmV0MTIz..."

Usage:
Decoders.BASE64.decode(secret)

---

## Why Base64 is Preferred
- Fixed length
- Safe encoding for binary data
- Works reliably with crypto libraries

---

## Common Mistake
Using BASE64 decode on plain string

→ leads to invalid key or runtime error

---

## Key Insight

Secret format must match decoding method

## How to Identify Secret Type

### Plain String
- Random readable characters
- Example:
  "tyu0XaD5HCtPWZj7..."

Usage:
secret.getBytes()

---

### Base64 Encoded
- Often ends with "="
- Looks encoded
- Example:
  "c2VjcmV0MTIz..."

Usage:
Decoders.BASE64.decode(secret)

---

## Why This Matters

Wrong decoding:
- produces invalid key
- breaks JWT signing/verification

---

## Key Insight

Secret format determines how it must be processed

## JWT Purpose
- Stateless authentication
- No server-side session storage

---

## JWT Structure
HEADER.PAYLOAD.SIGNATURE

---

## Payload (Claims)
- sub → userId
- role → authorization
- email → optional

---

## Login Flow

1. User sends credentials
2. Server verifies
3. Server generates JWT
4. Client stores token

---

## Request Flow

1. Client sends token
2. Server verifies signature
3. Extracts claims
4. Authorizes request

---

## Why JWT is Secure

- Signed using secret key
- Cannot be modified without invalidating signature

---

## Stateless Architecture

- No session stored on server
- Token contains all required data

---

## Key Insight

JWT = identity + authorization + security in one token

## JWT vs Encryption

JWT is NOT encryption

- Payload is NOT hidden
- Anyone can decode and read it
- JWT only ensures data is NOT modified

---

## JWT Structure

HEADER.PAYLOAD.SIGNATURE

Example:
aaa.bbb.ccc

- HEADER → algorithm info
- PAYLOAD → actual data (claims)
- SIGNATURE → verification proof

---

## What is inside Payload (Claims)

Example:
{
  "sub": "userId",
  "role": "STUDENT",
  "email": "test@..."
}

- sub → subject (user identity)
- role → authorization
- email → optional metadata

---

## How JWT Signing Works

Step 1:
data = BASE64(header) + "." + BASE64(payload)

Step 2:
signature = HMAC(secret, data)

Step 3:
token = data + "." + signature

---

## HMAC (Important)

HMAC = Hash + Secret Key

signature = HMAC(secret, data)

- Not just hashing
- Requires secret key
- Cannot be recreated without secret

---

## Verification Process (Exact)

Input:
HEADER.PAYLOAD.SIGNATURE

Step 1:
Extract:
- data = HEADER.PAYLOAD
- signature_from_token

Step 2:
Recompute:
expected_signature = HMAC(secret, data)

Step 3:
Compare:
expected_signature == signature_from_token

---

## If Verification Passes

- Token is valid
- Data is trusted
- Claims can be used

---

## If Verification Fails

- Token rejected
- Exception thrown
- Request denied

---

## Why Attacker Cannot Forge Token

Attacker can:
- read payload
- modify payload

BUT cannot:
- generate valid signature

Because:
- secret key is unknown

---

## Important Security Insight

JWT security depends on:

secret key secrecy

If secret is leaked:
- attacker can generate valid tokens
- system is compromised

---

## JWT is NOT Encryption

- Payload is visible
- Only integrity is protected
- Not confidentiality

---

## Password vs JWT Difference

Password:
- hashed (one-way)
- cannot retrieve original

JWT:
- data is visible
- signature ensures integrity

---

## JwtUtil Responsibilities

- generateToken() → create signed token
- extractClaims() → verify + read token

---

## @Component

Marks JwtUtil as Spring-managed bean

- allows injection into services

---

## @Value

Injects values from application.yml

Example:
@Value("${app.jwt.secret}")

---

## @PostConstruct

Runs after bean creation

Used for:
- initializing SecretKey

---

## Secret Handling

Two types:

1. Plain string:
   use → secret.getBytes()

2. Base64 encoded:
   use → Decoders.BASE64.decode(secret)

---

## Your Case

Secret:
tyu0XaD5HCtPWZj7dWRGEJuPszdaZ6zrny0KTRwQZIy

- Plain string
- NOT base64

Correct usage:
Keys.hmacShaKeyFor(secret.getBytes())

---

## Token Expiration

.expiration(new Date(...))

- defines validity time
- prevents long-term misuse

---

## PasswordEncoder.matches()

Used during login

- compares raw vs hashed password
- does NOT decrypt password

---

## Login Flow (Detailed)

1. User sends email + password
2. Find user in DB
3. Compare password using matches()
4. If valid → generate JWT
5. Return token + user info

---

## Why Same Error Message

"Invalid credentials"

Reason:
- prevent user enumeration
- attacker cannot know:
  - email exists or not
  - password is wrong

---

## Role Storage in JWT

Stored as:
user.getRole().name()

Why:
- stable value
- not affected by toString override

---

## .name() vs .toString()

.name()
- returns enum constant
- safe

.toString()
- can be overridden
- unsafe for JWT

---

## ROLE_ Prefix (Spring Security)

Spring expects:
ROLE_ADMIN

But:

hasRole("ADMIN")
→ internally becomes ROLE_ADMIN

---

## Two Approaches

1. Store "ADMIN"
   use hasRole("ADMIN") ✅

2. Store "ROLE_ADMIN"
   use hasAuthority("ROLE_ADMIN")

---

## Current Approach (Correct)

- Store "ADMIN"
- Use hasRole()

---

## Stateless Authentication

- Server does NOT store session
- Token contains all required data

---

## Request Flow (After JWT)

Client sends:
Authorization: Bearer `<token>`

Server:
1. Extract token
2. Verify signature
3. Extract claims
4. Identify user
5. Authorize request

---

## Key Insight (Final)

JWT = signed proof of identity + authorization

Server trusts token ONLY because:
- signature is valid
- secret is known only to server

## JWT Readability

- JWT payload is Base64 encoded, not encrypted
- Anyone can decode and read it
- Example:
  payload = BASE64 → JSON

---

## Decoding JWT

Steps:
1. Split token → HEADER.PAYLOAD.SIGNATURE
2. Take PAYLOAD
3. Base64 decode → JSON

---

## Important Distinction

Decoding ≠ Verification

- Decoding → just reading data
- Verification → checking if data is authentic

---

## Verification Requirement

Server must verify token using:

expected_signature = HMAC(secret, data)

Compare with:
signature_from_token

---

## Why Secret is Required

- Same secret is used for:
  - signing (token creation)
  - verification (token validation)

Without secret:
- cannot generate valid signature
- cannot forge token

---

## Attack Scenario Prevention

Attacker:
- modifies payload
- creates fake signature

Fails because:
- server recomputes signature using secret
- mismatch → token rejected

---

## JWT Security Guarantee

JWT ensures:
- integrity (data not changed)
- authenticity (issued by server)

JWT does NOT ensure:
- confidentiality (data is visible)

---

## Token Lifecycle

Login:
- server generates token

Request:
- client sends token

Backend:
- verifies token
- extracts claims
- processes request

---

## Claims Extraction

Using:
jwtUtil.extractClaims(token)

Internally:
- verifies signature
- checks expiration
- returns payload

---

## Authentication Object Creation

UsernamePasswordAuthenticationToken

Represents:
- authenticated user

Contains:
- principal (userId)
- authorities (roles)

---

## Authority Mapping

role = "ADMIN"

Converted to:
"ROLE_ADMIN"

Reason:
- Spring Security expects ROLE_ prefix

---

## SecurityContext

SecurityContextHolder.getContext().setAuthentication(...)

Purpose:
- stores current user info
- makes user available across request lifecycle

---

## Filter Responsibility

JWT Filter:
- runs on every request
- extracts token
- verifies token
- sets authentication

---

## Request Authorization Flow

Request →
Filter →
Verify JWT →
Set SecurityContext →
Controller →
Service

---

## Protected Routes

Without token:
- request denied

With valid token:
- request allowed

---

## Key Insight

JWT is useful only when:
- it is verified
- and linked to request context (SecurityContext)

## permitAll vs Filter Execution

permitAll():
- skips authorization
- does NOT skip filters

---

## Filter Order

Request →
Filter →
Authentication →
Authorization →
Controller

---

## Problem Scenario

Public endpoint (/login) with invalid token:
- filter tries to validate
- fails → returns 401
- controller never reached

---

## Solution

Skip filter for public endpoints:

if (path.startsWith("/api/auth")) {
    doFilter()
}

---

## Correct Behavior

Public endpoints:
- ignore token

Protected endpoints:
- enforce token validation

---

## Validation Layer

Validation should be applied in DTO, not entity

---

## @NotBlank
- ensures string is not null and not empty

---

## @NotNull
- ensures value is not null

---

## @Valid

Used in controller to trigger validation

Example:
@Valid @RequestBody ProfileRequest

---

## Validation Flow

Request →
DTO validation →
Controller →
Service

---

## Key Insight

Security and validation are separate concerns:
- JWT → authentication
- DTO validation → input correctness

## Environment Variables in Spring Boot

Use:
${VAR_NAME}

Example:
secret: ${JWT_SECRET}

---

## Why Use Env Variables

- prevents secrets in code
- safer for GitHub
- required in production

---

## Common Mistake

Env variables not available because:
- set in different terminal
- not exported properly

---

## .env File

Stores variables locally

Must be added to:
.gitignore

---

## Key Insight

Secrets should never be hardcoded in config files


## Environment Variables with Fallback

Syntax:
${VAR_NAME:default_value}

Behavior:
- If VAR_NAME exists → use it
- If NOT → use default_value

---

## Important Clarification

Fallback is NOT validation

- Spring does NOT check if fallback works
- It only checks if env variable exists

---

## Example

url: ${DB_URL:jdbc:postgresql://localhost:5432/identity}

Cases:

1. DB_URL not set:
→ fallback used

2. DB_URL set but wrong:
→ fallback ignored
→ wrong value used

---

## Key Insight

Env variable presence ≠ correctness

---

## Environment Variable Scope

Env variables exist per shell session

Correct:
source ~/.zshrc
mvn spring-boot:run

Incorrect:
source ~/.zshrc
(open new terminal)
mvn spring-boot:run

---

## Checking Env Variables

echo $DB_URL
echo $JWT_SECRET

Empty output:
→ variable not set

---

## Debugging Tip

Remove fallback:

url: ${DB_URL}

If env missing:
→ app fails immediately (good for debugging)

---

## JWT Filter vs permitAll

permitAll():
- skips authorization
- does NOT skip filters

---

## Filter Execution Order

Request →
Filter →
Authentication →
Authorization →
Controller

---

## Problem Scenario

Public endpoint (/login) with invalid token:

- filter runs
- tries to validate token
- fails → returns 401
- controller not reached

---

## Solution

Skip filter for public endpoints:

if (path.startsWith("/api/auth")) {
    filterChain.doFilter(...)
    return;
}

---

## Correct Behavior

Public endpoints:
- ignore token completely

Protected endpoints:
- require valid token

---

## HTTP Status Meaning

401:
- authentication failed
- invalid or missing token

403:
- authenticated but not allowed
- OR unhandled exceptions (temporary behavior)

---

## Exception Behavior (Current)

RuntimeException in service:
→ not handled
→ results in 403 or inconsistent response

---

## Proper Handling (Future)

Use global exception handler:

@RestControllerAdvice

→ convert exceptions into proper responses

---

## DTO Validation

Validation should be in DTO, not entity

---

## @NotBlank

- not null
- not empty
- not whitespace

---

## @NotNull

- must not be null
- allows empty string

---

## @Valid

Used in controller to trigger validation

@Valid @RequestBody

---

## Validation Flow

Request →
DTO validation →
Controller →
Service

---

## Security vs Validation

JWT:
- verifies identity

Validation:
- verifies input correctness

---

## GitHub Security (Config)

application.yml must NOT contain:

- JWT secret
- DB password

---

## Correct Config Pattern

secret: ${JWT_SECRET}
password: ${DB_PASSWORD}

---

## Secret Storage

Use:
- OS env variables (.zshrc)

Avoid:
- hardcoded values in repo

---

## .gitignore Essentials

target/
.env
*.log

---

## Why target/ is ignored

- contains compiled code
- not needed in version control

---

## Git Safety Check

grep -r "secret" .
grep -r "password" .

Only env placeholders should appear

---

## Spring Profiles (Concept)

application.yml → default

application-local.yml → local overrides

application-prod.yml → production config

---

## Your Setup

Using .zshrc env variables:
→ clean enough
→ profiles optional

---

## Maven Cleanup

mvn clean

Removes:
- build artifacts
- target directory contents

---

## Minimal pom.xml Principle

- only required dependencies
- no duplicates
- no unused libraries

---

## License Choice

MIT License recommended

Reasons:
- simple
- widely used
- minimal restrictions
- good for portfolio projects

---

## Key Insight

Clean repo = code + config separation + no secrets

---

## Final System State

- JWT auth working
- filter working
- profile system working
- env-based config working

→ backend is production-ready foundation

## Profile API Design Evolution

Built endpoints:
- POST /profile → create
- GET /profile → fetch
- PATCH /profile → update

---

## Identity Source

User identity is always derived from:

authentication.getPrincipal()

- comes from JWT
- NOT from request body
- ensures user can only access own data

---

## DTO Usage Strategy

Current design:
- ProfileRequest → create + update
- ProfileResponse → get

---

## DTO Separation Principle

Request DTO:
- represents client input

Response DTO:
- represents server output

---

## DTO Reuse Decision

DTO reuse is allowed only if:
- fields identical
- validation identical
- behavior identical

Otherwise:
- separate DTOs required

---

## Partial Update Design

PATCH endpoint introduced:
- allows updating only selected fields

---

## Primitive vs Wrapper Types

int:
- default = 0
- cannot detect "not provided"

Integer:
- can be null
- null = field not sent

---

## Partial Update Logic

Manual approach:

if (field != null) → update

Problems:
- repetitive
- not scalable
- violates clean design

---

## API Design Insight

PUT:
- full replacement

PATCH:
- partial update (preferred here)

---

## Design Improvement Trigger

Observation:
- adding fields requires updating service logic

Conclusion:
- need automated mapping

---

## Separation of Concerns

DTO:
- data transport

Service:
- business logic

Mapper:
- data transformation

---

## Clean Architecture Insight

Avoid:
- mixing update logic with service logic

Use:
- dedicated mapping layer

---

## Data Control Principle

User can only modify:
- fields exposed in DTO

User cannot modify:
- hidden/internal fields (e.g., user relation)

---

## Security Reinforcement

Even during update:
- userId comes from JWT
- prevents cross-user modification

---

## Key Insight

Design should evolve when:
- repetition appears
- scaling becomes difficult

Not:
- after bugs appear

---

## Final Design Direction

Move from:
manual field updates

To:
automated mapping (MapStruct)

## MapStruct Overview

MapStruct is a compile-time mapper

- generates code automatically
- no reflection
- type-safe

---

## @Mapper

Marks interface for MapStruct

- generates implementation class
- no manual coding required

---

## componentModel = "spring"

- registers mapper as Spring Bean
- allows dependency injection

---

## Mapping Types

1. Entity → DTO
2. DTO → Entity
3. DTO → Existing Entity (update)

---

## @BeanMapping

Used to configure mapping behavior

---

## nullValuePropertyMappingStrategy = IGNORE

- null fields in source are ignored
- prevents overwriting existing values

---

## @MappingTarget

Indicates:
- update existing object
- not create new one

---

## Generated Code Behavior

MapStruct generates:

if (source.field != null) {
    target.field = source.field;
}

---

## Benefits

- removes boilerplate code
- improves readability
- reduces bugs
- scales with fields

---

## Performance

- compile-time generation
- no runtime reflection
- very fast

---

## Field Matching

- matches fields by name
- same name → automatic mapping
- different name → requires configuration

---

## Separation of Concerns

DTO → data transport  
Entity → database  
Mapper → transformation  

---

## When to Use

- multiple DTOs
- partial updates
- large models

---

## Key Insight

MapStruct replaces repetitive mapping logic with generated, maintainable code

## PATCH /profile — Partial Update Design

Used PATCH instead of PUT

Reason:
- PUT → full replacement
- PATCH → partial update (only provided fields)

---

## Problem with Initial Approach

Manual update:

if (field != null) {
    setField(...)
}

Issues:
- repetitive
- not scalable
- every new field → change service logic

---

## Solution: MapStruct

MapStruct:
- generates mapping code at compile time
- removes boilerplate
- improves maintainability

---

## Mapper Setup

@Mapper(componentModel = "spring")

Meaning:
- MapStruct generates implementation
- Spring registers it as a bean

---

## Update Mapping Method

@BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
void updateProfileFromDto(UpdateProfileRequest dto,
                          @MappingTarget StudentProfile entity);

---

## @BeanMapping + IGNORE

nullValuePropertyMappingStrategy = IGNORE

Meaning:
- if DTO field == null → do not update entity
- enables partial update

---

## @MappingTarget

Indicates:
- update existing entity
- do NOT create new object

Without it:
- MapStruct creates new object ❌

With it:
- updates existing entity ✔

---

## Generated Code Behavior

MapStruct generates:

if (dto.field != null) {
    entity.field = dto.field;
}

But automatically

---

## DTO for Partial Update

Used wrapper types:

Integer instead of int

Reason:
- int → default 0 (cannot detect missing field)
- Integer → null means "not provided"

---

## Service Layer After Refactor

mapper.updateProfileFromDto(request, profile);

profileRepository.save(profile);

Service now:
- contains only business logic
- no field-level update logic

---

## Response Mapping

ProfileResponse toResponse(StudentProfile profile);

Purpose:
- convert entity → response DTO
- avoid exposing internal entity

---

## Constructor Issue (MapStruct)

Error:
- no default constructor

Fix:
- add @NoArgsConstructor

Reason:
- MapStruct uses:
  new Object() + setters

---

## Lombok + MapStruct Issue

Problem:
- DTO fields always null
- mapper not updating anything

Cause:
- MapStruct could not see Lombok-generated getters

---

## Root Cause

Annotation processing order:

Lombok → generates getters  
MapStruct → needs those getters  

If order wrong or binding missing:
- MapStruct sees no getters
- assumes all fields null

---

## Fix: lombok-mapstruct-binding

Added:

lombok-mapstruct-binding

Purpose:
- bridge between Lombok and MapStruct
- ensures MapStruct recognizes Lombok-generated methods

---

## Annotation Processor Order

Correct order:

1. lombok
2. lombok-mapstruct-binding
3. mapstruct-processor

---

## Why Order Matters

Processors run during compilation

Correct flow:
- Lombok generates methods
- MapStruct reads them
- MapStruct generates mapper

Wrong order:
- MapStruct runs first → sees nothing → null mapping

---

## Debugging Insight

Observed:
Before: 4
After: 4

Meaning:
- mapper didn’t update entity
- no DB update triggered

---

## JPA Behavior Insight

JPA only updates if:
- entity field actually changes

If no change:
- no UPDATE query executed

---

## Separation of Concerns (Final Design)

DTO:
- data transfer

Mapper:
- transformation logic

Service:
- business logic

Repository:
- persistence

---

## Security Insight

User identity always comes from:

authentication.getPrincipal()

Never from request body

Ensures:
- user can only update own profile

---

## Final Architecture Improvement

Before:
- service handled mapping ❌

After:
- mapper handles mapping ✔
- service is clean ✔

---

## Key Takeaway

MapStruct + Lombok requires:
- correct processor order
- binding dependency

Otherwise:
- silent failures (null mapping)

---

## Design Maturity Achieved

- Partial update support ✔
- Clean service layer ✔
- DTO separation ✔
- Scalable mapping ✔
- Secure user handling ✔

## Validation Layer Introduction

Validation ensures:
- invalid data is rejected before reaching service layer
- protects database integrity
- improves API reliability

---

## @Valid

Used in controller:

@Valid @RequestBody DTO

Meaning:
- triggers validation annotations in DTO
- without it → validation is ignored

---

## Validation Flow

Request → Controller → Validation → Service → Repository

If validation fails:
- request never reaches service

---

## Validation Annotations Used

@NotBlank:
- not null
- not empty
- not whitespace

@Min / @Max:
- numeric range validation

@Size(min=1):
- string must not be empty
- allows null (important for PATCH)

---

## Create vs Update Validation

Create (POST):
- all fields required
- strict validation

Update (PATCH):
- fields optional
- validate only if present

---

## PATCH Validation Design

Goal:
- allow partial updates
- prevent invalid values

---

## Problem

DTO without validation:

"" (empty string) → accepted ❌

---

## Solution

Use:

@Size(min = 1)

Behavior:
- null → allowed (field not sent)
- "" → rejected
- valid string → accepted

---

## Why Not @NotBlank for PATCH

@NotBlank:
- rejects null
- breaks partial update

Example:

{} → invalid ❌

---

## Wrapper Types for PATCH

Use:

Integer instead of int

Reason:
- int → default 0 (cannot detect missing field)
- Integer → null means "not provided"

---

## Domain Constraint

Year field:

@Min(1)
@Max(4)

Reason:
- reflects real-world constraint (college years)

---

## Important Design Principle

Validation should reflect:
- business rules
- not just technical constraints

---

## Key Insight

Validation differs based on operation:
- Create → strict
- Update → flexible but controlled

---

## Security Note

Validation works alongside:
- authentication (who user is)
- authorization (what user can do)

---

## Final Behavior Achieved

PATCH:
- allows partial updates ✔
- rejects invalid values ✔
- keeps existing data safe ✔

---

## Common Mistake Avoided

Using same validation for:
- create ❌
- update ❌

Instead:
- tailored validation ✔

## Profile Completion Flag

Added field in User entity:

private boolean profileCompleted;

---

## Purpose

Tracks whether user has completed profile setup

Used for:
- enforcing onboarding flow
- restricting features
- backend business logic

---

## Design Decision

profileCompleted is:
- NOT part of DTO
- NOT user-controlled
- managed only by server

---

## Security Principle

Client:
- controls request DTO

Server:
- controls internal state

---

## Why Not Include in DTO

If included:

{
  "profileCompleted": true
}

User could:
- bypass onboarding
- fake system state

---

## Correct Flow

User:
- registers
- creates profile

Server:
- sets profileCompleted = true

---

## Service Layer Responsibility

Business rule:

user.setProfileCompleted(true);

Handled inside service layer:
- not controller
- not DTO

---

## Transaction Management

Used:

@Transactional

---

## What @Transactional Does

Wraps method in a database transaction

Behavior:
- all operations succeed → commit
- any operation fails → rollback

---

## Example in createProfile

Operations:

1. save user
2. save profile

---

## Without Transaction

Possible state:

user updated ✔  
profile not saved ❌  

→ inconsistent database

---

## With Transaction

Either:

both succeed ✔  
OR  
both fail ✔  

---

## Atomicity

Transaction ensures:

"All or nothing"

---

## When to Use Transactions

Use when:
- multiple DB operations
- data consistency required

---

## Where to Place @Transactional

Service layer ✔

Not:
- controller
- repository

---

## Code Smell Found

Duplicate dependency:

private final UserRepository userRepo;
private final UserRepository userRepository;

---

## Fix

Keep only one instance

---

## Final Design Achieved

- secure internal state handling ✔
- proper layering ✔
- transactional safety ✔
- no client trust for critical flags ✔

---

## Key Insight

Never trust client for:
- system state
- security flags
- business rules

Always enforce in backend

## Database Reset During Development

Problem:
- Entity updated (new fields)
- Database schema not updated automatically

---

## Solution Used

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

---

## Effect

- removes all tables
- removes all constraints
- resets database completely

---

## When to Use

- development phase ✔
- schema evolving frequently ✔

---

## Warning

- deletes ALL data permanently
- never use in production

---

## Why Errors Happened

Entity change:

private boolean profileCompleted;

But DB:
- did not have column

Result:
- SQL error: column does not exist

---

## Hibernate Behavior

ddl-auto: update

- tries to update schema
- not always reliable
- may miss column changes

---

## Key Insight

Entity ≠ Database

Schema must be:
- recreated
OR
- manually updated

---

## System Design Clarification

Project is NOT:
- identity generator ❌

Project IS:
- student data storage system ✔

---

## Core Concept

Student identity already exists (college given)

System stores:
- latest student information
- retrievable by staff

---

## Main Entity Responsibility

User:
- authentication
- login system

StudentProfile:
- student identity data

---

## Identity Definition

registerNumber:
- unique identifier
- primary lookup field

---

## Schema Design Principle

Design based on:

"What queries will system support?"

---

## Primary Use Case

Given register number:
→ retrieve student data

---

## Core Fields

registerNumber
fullName
department
year

---

## Extended Fields (optional)

section
phoneNumber
dateOfBirth
address

---

## System Fields

createdAt
updatedAt

---

## Relationship Design

User → StudentProfile (One-to-One)

Reason:
- separate auth from data
- clean architecture

---

## Avoid Over-Engineering

Do NOT:
- split into too many tables
- add unnecessary relationships
- build complex systems early

---

## Query-Driven Design

Design DB based on:

Search patterns:
- findByRegisterNumber
- filter by department + year

---

## Repository Strategy

Use method naming:

findByRegisterNumber(...)
findByDepartmentAndYear(...)

---

## Unique Constraint

registerNumber must be:

@Column(unique = true)

---

## Validation Insight

registerNumber:
- required
- unique

---

## Development Workflow

1. Change entity
2. DB mismatch occurs
3. Reset DB
4. Restart app

---

## Architecture Clarity

User → authentication layer

StudentProfile → business data layer

---

## Key Insight

Schema design should reflect:
- real-world data
- actual system usage
- not hypothetical features

---

## Design Maturity

Moved from:
- feature-first thinking ❌

To:
- data-first design ✔

