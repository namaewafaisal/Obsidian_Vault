# Adding a Related Entity in Spring Boot — Full Flow
#spring-boot #jpa #mapstruct #backend

---

## The Example

`StudentProfile` has a one-to-one relationship with `StudentHandle`.
`StudentHandle` stores platform usernames — LeetCode, GitHub, etc.

This note explains every file you touch when adding a new related entity and why.

---

## 1. The Entities

### StudentProfile (parent)
```java
@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private UUID id;

    private String fullName;
    private String department;
    private Integer year;
    // ... other flat fields

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private StudentHandle handle;  // ← child relationship

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

### StudentHandle (child)
```java
@Entity
public class StudentHandle {
    @Id
    @GeneratedValue
    private UUID id;

    private String leetcode;
    private String github;
    private String hackerrank;
    private String codeforces;
    private String linkedin;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private StudentProfile profile;  // ← back-reference to parent
}
```

**Key JPA concepts here:**
- `mappedBy = "profile"` — tells JPA that `StudentHandle` owns the foreign key column, not `StudentProfile`
- `cascade = CascadeType.ALL` — when you save a profile, JPA also saves the handle automatically
- `orphanRemoval = true` — if you remove the handle from the profile, JPA deletes it from DB
- Both sides must be wired for the relationship to persist correctly

---

## 2. The DTOs

You need **separate DTOs** for every direction. Entities never leave the service layer.

```
Frontend sends  →  Request DTO  →  Service  →  Entity (saved to DB)
Frontend gets   ←  Response DTO ←  Service  ←  Entity (read from DB)
```

### HandleRequest (incoming)
```java
@Data
public class HandleRequest {
    private String leetcode;
    private String github;
    private String hackerrank;
    private String codeforces;
    private String linkedin;
}
```

### HandleResponse (outgoing)
```java
@Data
public class HandleResponse {
    private String leetcode;
    private String github;
    private String hackerrank;
    private String codeforces;
    private String linkedin;
}
```

### ProfileRequest (incoming — create)
```java
@Data
public class ProfileRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String department;
    // ... other fields

    private HandleRequest handle;  // ← nested DTO, NOT entity
}
```

### UpdateProfileRequest (incoming — update/patch)
```java
@Data
public class UpdateProfileRequest {
    private String fullName;       // all optional for PATCH
    private String department;
    // ... other fields

    private HandleRequest handle;  // ← same DTO, NOT entity
}
```

### ProfileResponse (outgoing)
```java
@Data
public class ProfileResponse {
    private String fullName;
    private String department;
    // ... other fields

    private HandleResponse handle;  // ← nested response DTO
}
```

**Rule:** Request and Response DTOs never contain JPA entities. If you find yourself putting `StudentHandle` inside `UpdateProfileRequest`, that's wrong — use `HandleRequest` instead.

---

## 3. The Mapper (MapStruct)

MapStruct generates the copy logic at **compile time**. You write an interface, it writes the implementation.

```java
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    // CREATE — DTO → new Entity
    StudentProfile toEntity(ProfileRequest request);
    StudentHandle toHandleEntity(HandleRequest request);

    // READ — Entity → Response DTO
    @Mapping(target = "handle", source = "handle")
    ProfileResponse toResponse(StudentProfile profile);
    HandleResponse toHandleResponse(StudentHandle handle);

    // UPDATE — DTO → existing Entity (PATCH behavior)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(UpdateProfileRequest dto, @MappingTarget StudentProfile entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHandleFromDto(HandleRequest dto, @MappingTarget StudentHandle entity);
}
```

### What each method does

| Method | Direction | Behavior |
|---|---|---|
| `toEntity` | ProfileRequest → StudentProfile | Creates new entity, copies flat fields |
| `toHandleEntity` | HandleRequest → StudentHandle | Creates new handle entity |
| `toResponse` | StudentProfile → ProfileResponse | Reads entity, produces response DTO. Auto-calls `toHandleResponse` for nested handle |
| `toHandleResponse` | StudentHandle → HandleResponse | Called automatically by MapStruct inside `toResponse` |
| `updateProfileFromDto` | UpdateProfileRequest → StudentProfile | Updates existing entity, **skips null fields** |
| `updateHandleFromDto` | HandleRequest → StudentHandle | Updates existing handle, **skips null fields** |

### Why `toEntity` does NOT handle the relationship

`toEntity(request)` copies flat fields automatically. But it cannot wire the bidirectional relationship because:
1. It doesn't know the parent profile object yet when creating the handle
2. `handle.setProfile(profile)` — the back-reference — requires both objects to exist first
3. MapStruct has no way to know to call this

So you always wire the relationship **manually in the service** after calling `toEntity`.

### `NullValuePropertyMappingStrategy.IGNORE`

This is what makes PATCH work. Without it, every null field in the request DTO would overwrite the existing entity value with null.

```java
// Student sends only: { "department": "ECE" }
// Without IGNORE → fullName, year, etc. all become null in DB
// With IGNORE    → only department updates, everything else unchanged
```

---

## 4. The Service

This is where the relationship wiring happens manually.

### Create — new profile + optional handle

```java
@Transactional
public void createProfile(ProfileRequest request, String userId) {

    // 1. fetch the logged-in user
    User user = userRepository.findById(UUID.fromString(userId))
        .orElseThrow(() -> new RuntimeException("User not found"));

    // 2. guard: one profile per user
    if (profileRepository.findByUserId(user.getId()).isPresent()) {
        throw new RuntimeException("Profile already exists");
    }

    // 3. guard: register number must be unique
    if (profileRepository.existsByRegisterNumber(request.getRegisterNumber())) {
        throw new RuntimeException("Register number already exists");
    }

    // 4. map flat fields only — handle NOT included yet
    StudentProfile profile = mapper.toEntity(request);
    profile.setUser(user);
    user.setProfileCompleted(true);

    // 5. handle is optional — only wire if provided
    if (request.getHandle() != null) {
        StudentHandle handle = mapper.toHandleEntity(request.getHandle());
        handle.setProfile(profile);   // child → parent (back-reference)
        profile.setHandle(handle);    // parent → child
    }

    // 6. save — cascade saves handle too
    userRepository.save(user);
    profileRepository.save(profile);
}
```

**Why both `handle.setProfile(profile)` AND `profile.setHandle(handle)`?**

JPA needs both sides wired because:
- `handle.setProfile(profile)` → sets the actual foreign key column in DB (`profile_id`)
- `profile.setHandle(handle)` → keeps the in-memory object graph consistent

If you only do one side, either the FK doesn't get written or your returned response has a null handle even though it saved correctly.

---

### Update — PATCH behavior

```java
@Transactional
public ProfileResponse updateProfile(String userId, UpdateProfileRequest request) {

    // 1. fetch existing profile
    StudentProfile profile = profileRepository
        .findByUserId(UUID.fromString(userId))
        .orElseThrow(() -> new RuntimeException("Profile not found"));

    // 2. apply non-null fields from request onto existing entity
    mapper.updateProfileFromDto(request, profile);

    // 3. handle update — three cases
    if (request.getHandle() != null) {

        if (profile.getHandle() == null) {
            // Case A: student adding handles for the first time via update
            StudentHandle handle = mapper.toHandleEntity(request.getHandle());
            handle.setProfile(profile);
            profile.setHandle(handle);

        } else {
            // Case B: student updating existing handles
            // IGNORE strategy means only sent fields get updated
            mapper.updateHandleFromDto(request.getHandle(), profile.getHandle());
        }
    }
    // Case C: request.getHandle() == null → handle untouched

    profileRepository.save(profile);
    return mapper.toResponse(profile);
}
```

**The three handle cases in update:**

| Case | Condition | Action |
|---|---|---|
| A | `request.handle != null` AND `profile.handle == null` | Create new handle, wire relationship |
| B | `request.handle != null` AND `profile.handle != null` | Update existing handle fields (IGNORE nulls) |
| C | `request.handle == null` | Do nothing, handle stays as is |

---

### Read — toResponse handles the nested mapping automatically

```java
public ProfileResponse getProfile(String userId) {
    StudentProfile profile = profileRepository
        .findByUserId(UUID.fromString(userId))
        .orElseThrow(() -> new RuntimeException("Profile not found"));

    return mapper.toResponse(profile);  // MapStruct calls toHandleResponse internally
}
```

MapStruct sees `ProfileResponse` has a `HandleResponse handle` field and `StudentProfile` has a `StudentHandle handle` field. It calls `toHandleResponse` automatically. You don't do anything extra for reads.

---

## 5. The Filter (Specifications + Pagination)

```java
public Page<ProfileResponse> filter(String department, Integer year, String name, Pageable pageable) {

    Specification<StudentProfile> spec = (root, query, cb) -> null;  // no-op start

    if (department != null && !department.isBlank()) {
        spec = spec.and(ProfileSpecifications.hasDepartment(department));
    }
    if (year != null) {
        spec = spec.and(ProfileSpecifications.hasYear(year));
    }
    if (name != null && !name.isBlank()) {
        spec = spec.and(ProfileSpecifications.nameContains(name));
    }

    return profileRepository.findAll(spec, pageable).map(mapper::toResponse);
}
```

```java
public class ProfileSpecifications {

    public static Specification<StudentProfile> hasDepartment(String dept) {
        return (root, query, cb) ->
            dept == null ? null : cb.equal(root.get("department"), dept);
    }

    public static Specification<StudentProfile> hasYear(Integer year) {
        return (root, query, cb) ->
            year == null ? null : cb.equal(root.get("year"), year);
    }

    public static Specification<StudentProfile> nameContains(String name) {
        return (root, query, cb) ->
            name == null ? null : cb.like(
                cb.lower(root.get("fullName")),
                "%" + name.toLowerCase() + "%"
            );
    }
}
```

**Frontend calls:**
```
GET /api/profile/filter?department=CSE&year=3&page=0&size=10&sort=fullName,asc
```

Spring reads `page`, `size`, `sort` into `Pageable` automatically — you don't parse them manually.

---

## 6. The Controller

```java
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<Void> createProfile(
            @Valid @RequestBody ProfileRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        profileService.createProfile(request, userId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ProfileResponse getProfile(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return profileService.getProfile(userId);
    }

    @PatchMapping
    public ProfileResponse updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return profileService.updateProfile(userId, request);
    }

    @GetMapping("/filter")
    public Page<ProfileResponse> filter(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String name,
            Pageable pageable) {
        return profileService.filter(department, year, name, pageable);
    }
}
```

---

## 7. When You Add Another Related Entity

Same pattern every time. Example: adding `StudentAcademics`.

**Checklist:**
- [ ] Create `StudentAcademics` entity with `@OneToOne` back-reference to `StudentProfile`
- [ ] Add `@OneToOne(cascade = CascadeType.ALL) StudentAcademics academics` to `StudentProfile`
- [ ] Create `AcademicsRequest` DTO (incoming)
- [ ] Create `AcademicsResponse` DTO (outgoing)
- [ ] Add `AcademicsRequest academics` field to `ProfileRequest` and `UpdateProfileRequest`
- [ ] Add `AcademicsResponse academics` field to `ProfileResponse`
- [ ] Add mapper methods: `toAcademicsEntity`, `toAcademicsResponse`, `updateAcademicsFromDto`
- [ ] In service `createProfile` — wire both sides if `request.getAcademics() != null`
- [ ] In service `updateProfile` — handle the three cases (null/new/existing)

The pattern is identical to handle. Once you understand this once, every new related entity is mechanical.

---

## Summary — Who Does What

| Layer | Responsibility |
|---|---|
| Entity | JPA mapping, relationships, DB columns |
| DTO | What goes in/out of the API. Never contains entities |
| Mapper | Converts between DTOs and entities. Handles flat fields automatically |
| Service | Business logic, relationship wiring, transactions |
| Controller | HTTP mapping, extract userId from auth, delegate to service |
| Specifications | Dynamic filter conditions, each one independent |
| Repository | DB queries, must extend `JpaSpecificationExecutor` for `findAll(spec)` |

## One-to-One Relationship (JPA Deep Understanding)

### Relationship Example

```text
User → StudentProfile → StudentHandle
```

- `StudentProfile` has `user_id`
- `StudentHandle` has `profile_id`

---

### Owning vs Inverse Side

#### Owning side (actual DB relation)

```java
@OneToOne
@JoinColumn(name = "profile_id")
private StudentProfile profile;
```

- This creates the **foreign key in DB**
- Table: `student_handle.profile_id`

---

#### Inverse side (mappedBy)

```java
@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
private StudentHandle handle;
```

- Does NOT create any DB column
- Only acts as **reference in Java**

---

### Key Concept

```text
Java field ≠ Database column
```

- `profile.handle` → exists in memory
- `handle.profile_id` → exists in DB

---

### What `mappedBy` means

```text
"I do not own the relationship, look at the other side"
```

- No column created
- No FK stored here
- Just mirrors the relation

---

### How Hibernate fetches nested data

```java
profile.getHandle();
```

Internally:

```sql
SELECT * FROM student_handle WHERE profile_id = ?
```

---

### Why we need both sides

Without:

```java
profile.getHandle()
```

👉 You cannot access handle from profile

---

### Cascade Behavior

```java
cascade = CascadeType.ALL
```

Means:

```text
Operation on parent → applied to child
```

Example:

```java
profileRepository.save(profile);
```

👉 Automatically saves `handle`

---

### Important Clarification

```text
handle is a REAL object in memory
BUT not a column in student_profile
```

---

### Mental Model

```text
StudentProfile.handle → reference (Java side)
StudentHandle.profile_id → actual relationship (DB side)
```

---

### Common Mistake

❌ Expecting:

```text
student_profile.handle_id column
```

✔ Correct:

```text
student_handle.profile_id column
```

---

### Why relation stored in one table

```text
Only ONE side owns the relation → avoids duplication/conflicts
```

---

## MapStruct – Update vs Create

### Create

```java
mapper.toEntity(dto)
```

- Creates NEW object
- All fields mapped

---

### Update

```java
mapper.updateXFromDto(dto, entity)
```

- Updates EXISTING object
- Only non-null fields updated

---

### How partial update works

Generated logic:

```java
if (dto.getField() != null) {
    entity.setField(dto.getField());
}
```

---

### Important Rule

```text
MapStruct does NOT:
✔ fetch DB data
✔ manage relationships
✔ decide create vs update
```

👉 Service layer must handle those

---

## Nested Object Update Pattern

### Problem

- Nested entity (`handle`) must:
  - be created if absent
  - be updated if present

---

### Correct pattern

```java
if (request.getHandle() != null) {

    if (profile.getHandle() == null) {
        // create
    } else {
        // update existing
    }
}
```

---

### Why not replace object?

```text
Replacing object = data loss risk
```

Example:

```text
old → github + leetcode
new → only github

leetcode gets erased ❌
```

---

### Correct approach

```text
Update existing object, not replace it
```

---

## DTO vs Entity Separation

### Rule

```text
DTO must NEVER use Entity classes
```

---

### Wrong

```java
private StudentHandle handle;
```

---

### Correct

```java
private HandleRequest handle;
```

---

### Why?

```text
✔ prevents accidental persistence
✔ keeps API layer clean
✔ avoids tight coupling
```

---

## Final Mental Model

```text
Entity → DB structure
DTO → API contract
Mapper → field transformer
Service → logic + lifecycle control
```

---

### Golden Rule

```text
Create → build new object
Update → modify existing object
```

---

### Relationship Summary

```text
Profile → knows handle (reference)
Handle → stores profile_id (actual relation)
```

