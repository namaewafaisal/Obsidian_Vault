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

# 📄 Excel Export (Spring Boot + JPA + Apache POI) — Complete Notes

---

# 🧠 1. Goal

Export student profile data (including related handle data) from database → Excel file.

```text
DB → Entity → Service → Excel → HTTP Response → Download
```

---

# 🧱 2. Dependencies

Add Apache POI (Excel library):

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

---

# 🧠 3. High-Level Flow

### Request

```
GET /api/profile/export
```

### Backend Flow

```
Controller → Service → Repository → Excel Creation → Return File
```

---

# 🧱 4. Service Layer (Core Logic)

### Step 1: Fetch Data

```java
List<StudentProfile> profiles = profileRepository.findAll();
```

👉 JPA automatically handles:

- `@OneToOne` → fetch handle if accessed
    
- No manual SQL JOIN needed
    

---

### Step 2: Create Workbook

```java
Workbook workbook = new XSSFWorkbook();
Sheet sheet = workbook.createSheet("Profiles");
```

---

### Step 3: Create Header Row

```java
Row header = sheet.createRow(0);

header.createCell(0).setCellValue("Register Number");
header.createCell(1).setCellValue("Full Name");
header.createCell(2).setCellValue("Department");
header.createCell(3).setCellValue("Year");
header.createCell(4).setCellValue("Github");
header.createCell(5).setCellValue("Leetcode");
```

---

### Step 4: Fill Data Rows

```java
int rowNum = 1;

for (StudentProfile profile : profiles) {

    Row row = sheet.createRow(rowNum++);

    row.createCell(0).setCellValue(profile.getRegisterNumber());
    row.createCell(1).setCellValue(profile.getFullName());
    row.createCell(2).setCellValue(profile.getDepartment());
    row.createCell(3).setCellValue(profile.getYear());

    StudentHandle handle = profile.getHandle();

    row.createCell(4).setCellValue(
        handle != null ? handle.getGithub() : ""
    );

    row.createCell(5).setCellValue(
        handle != null ? handle.getLeetcode() : ""
    );
}
```

---

### 🧠 Important Insight

```text
profile.getHandle()
```

👉 Triggers JPA relationship resolution  
👉 Internally behaves like JOIN  
👉 No manual query needed

---

### Step 5: Auto-size Columns

```java
for (int i = 0; i < 6; i++) {
    sheet.autoSizeColumn(i);
}
```

---

### Step 6: Convert to Byte Stream

```java
ByteArrayOutputStream out = new ByteArrayOutputStream();
workbook.write(out);
workbook.close();

return out.toByteArray();
```

---

# 🧱 5. Controller Layer

```java
@GetMapping("/export")
public ResponseEntity<byte[]> exportProfiles() {

    byte[] excelData = profileService.exportProfiles();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=profiles.xlsx");

    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(excelData);
}
```

---

# 🧠 6. Why Headers Matter

```java
Content-Disposition: attachment; filename=profiles.xlsx
```

👉 Tells client:

```text
"This is a downloadable file"
```

---

# 🧪 7. Testing

## HTTPie

```bash
http --download GET http://localhost:8080/api/profile/export \
Authorization:"Bearer TOKEN"
```

## Output

```
Downloading to "profiles.xlsx"
```

---

## Postman

- Click **Send**
    
- Click **Save Response**
    
- Save as `.xlsx`
    

---

## Browser

```
http://localhost:8080/api/profile/export
```

👉 File downloads automatically

---

# 🧠 8. Handling Relations (IMPORTANT)

### Entity Mapping

```java
@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
private StudentHandle handle;
```

---

### What this means

|Concept|Meaning|
|---|---|
|mappedBy|FK stored in `StudentHandle`|
|cascade = ALL|Saving profile saves handle|
|getHandle()|JPA fetches related data|

---

### DB Reality

```
student_profile (id)
student_handle (profile_id FK)
```

👉 Relationship exists in DB  
👉 Object exists in Java via mapping

---

# ⚠️ 9. Null Safety

Always handle:

```java
if (profile.getHandle() != null)
```

Otherwise → NullPointerException

---

# 🧠 10. Why NOT DTO here?

You used:

```java
StudentProfile → Excel
```

✔ Correct

Because:

- Excel needs raw structured data
    
- DTO adds unnecessary mapping overhead
    

---

# 🚫 11. Common Mistakes

### ❌ Forgetting null check

→ crash

### ❌ Not setting Content-Disposition

→ file won’t download properly

### ❌ Returning Workbook directly

→ must convert to byte[]

### ❌ Trying to use DTO for Excel

→ unnecessary complexity

---

# 🧠 12. Performance Consideration (Future)

Current:

```java
findAll()
```

👉 Loads ALL data → memory heavy

---

### Future solution:

```text
✔ Pagination
✔ Streaming (SXSSFWorkbook)
✔ Filters before export
```

---

# 🚀 13. What You Achieved

```text
✔ JPA relationships used correctly
✔ No manual SQL joins
✔ Binary file response handled
✔ Backend-driven Excel generation
✔ Production-level feature base
```

---

# 🔥 14. Next Evolution

We will upgrade to:

```text
✔ Export with filters
✔ Export selected columns only
✔ Dynamic Excel (staff chooses fields)
✔ Large dataset handling
```

---

# 🧠 Final Mental Model

```text
Entity = Data Source
Mapper = API layer
Excel = Direct extraction layer
```

---

# ✅ Summary

```text
You built a full data pipeline:

Database → JPA → Service → Excel → Download
```

This is real backend engineering, not just CRUD.

---
## Excel Export — Key Concepts (Concise)

---

### 🧠 1. Why POST + RequestBody

```text
Export needs multiple inputs:
✔ fields (list)
✔ filters (department, year, section)
```

→ Query params become messy → use `RequestBody`

---

### 🧠 2. Dynamic Columns (Core Idea)

Instead of:

```text
if(field == "name") ...
```

We use:

```text
field → function → value
```

---

### 🔧 Field Extractor Pattern

```text
Map<String, Function<Entity, Object>>
```

* Key → field name from user
* Value → logic to extract that field

---

### 🧠 Why this matters

```text
✔ removes if-else chains
✔ supports nested fields
✔ scalable (just add new entry)
```

---

### 🧠 Example mental flow

```text
"github" → go to handle → get github
```

---

## 🧠 3. Column Order Logic

```text
fields = ["fullName", "github"]

→ column 0 = fullName
→ column 1 = github
```

👉 Index of list = column position

---

## 🧠 4. Header Mapping (UI layer)

Separate:

```text
data name ≠ display name
```

Example:

```text
fullName → "Full Name"
```

---

### Why separate?

```text
✔ backend stays clean
✔ UI becomes readable
```

---

## 🧠 5. Filtering Strategy

### Mandatory

```text
department + year
```

### Optional

```text
section
```

---

### Mental model

```text
Filter FIRST → then export
```

---

## 🧠 6. Specification Usage

```text
spec = department AND year AND (optional section)
```

👉 Builds DB query dynamically

---

## 🧠 7. DB-Level Sorting

```text
Sort must happen in database, not Java
```

Why:

```text
✔ avoids loading unnecessary data
✔ more efficient
```

---

### Rule

```text
Always push filtering + sorting to DB
```

---

## 🧠 8. Handling Nested Data

```text
profile → handle → github
```

Handled via:

```text
extractor function
```

---

### Key insight

```text
ORM handles JOIN automatically
```

---

## 🧠 9. Empty Result Handling

```text
No data → throw error
```

Why:

```text
✔ prevents useless Excel
✔ better UX
```

---

## 🧠 10. Separation of Concerns

| Concern         | Responsibility     |
| --------------- | ------------------ |
| Filters         | DB (Specification) |
| Sorting         | DB                 |
| Field selection | Service (Map)      |
| Excel structure | Service            |

---

## 🧠 11. Overall Flow

```text
User request
   ↓
Validate inputs
   ↓
Build DB query (filter + sort)
   ↓
Fetch data
   ↓
Map fields dynamically
   ↓
Generate Excel
```

---

## 🧠 Final Insight

```text
This is NOT just export logic

It is:
→ dynamic data projection system
→ controlled data access layer
```

---

## 🎯 One-line takeaway

```text
User chooses WHAT,
Backend decides HOW,
Database handles WHERE
```

---

## Search API — Pagination, Sorting, and Safety (Concise Notes)

---

## 🧠 1. Goal

```text
Provide fast, filtered, paginated student search for staff
```

---

## 🧠 2. Core Components

```text
✔ Specification → filtering
✔ Pageable → pagination + sorting
✔ Service logic → safety + defaults
```

---

## 🧠 3. Pagination Defaults

### Controller level

```java
@PageableDefault(page = 0, size = 30)
```

---

### Meaning

```text
If user sends nothing:
→ page = 0
→ size = 30
```

---

## 🧠 4. Pagination Safety (Service Layer)

```text
Never trust user input directly
```

---

### Fix invalid values

```java
page = page < 0 → 0
```

---

### Cap size

```java
size > maxSize → maxSize
```

---

### Example

|Input|Used|
|---|---|
|page=-6|0|
|size=60|50|

---

## 🧠 5. Config-driven limits

### application.yml

```yaml
app:
  pagination:
    max-size: 50
```

---

### Inject

```java
@Value("${app.pagination.max-size}")
private int maxSize;
```

---

### Why

```text
✔ no hardcoding
✔ easy to change
✔ environment flexible
```

---

## 🧠 6. Sorting Logic

### Problem

```text
User may or may not send sort
```

---

### Solution

```java
if (pageable.getSort().isUnsorted()) {
    apply default sort
}
```

---

### Default

```text
registerNumber ASC
```

---

## 🧠 7. Important Rule

```text
Modify Pageable → NOT repository call
```

---

## 🧠 8. DB-Level Sorting

```java
findAll(spec, pageable)
```

---

### Happens in DB

```sql
ORDER BY register_number ASC
```

---

### Why

```text
✔ efficient
✔ avoids in-memory sorting
✔ scalable
```

---

## 🧠 9. Filtering (Specification)

```text
department → optional
year → optional
name → optional
```

---

### Combined as

```text
spec = A AND B AND C
```

---

## 🧠 10. Final Flow

```text
Request
   ↓
Spring builds Pageable
   ↓
Service sanitizes Pageable
   ↓
Specification builds query
   ↓
DB executes (filter + sort + limit)
   ↓
Page returned
```

---

## 🧠 11. Response Structure

```json
{
  "content": [...],
  "pageable": {...},
  "totalElements": ...,
  "totalPages": ...,
  "size": ...,
  "number": ...
}
```

---

## 🧠 12. Design Choice

### Current approach

```text
Auto-correct invalid input
```

---

### Alternative

```text
Reject invalid input
```

---

### Decision

```text
Auto-correct → better UX for this project
```

---

## 🧠 13. Key Insights

```text
✔ Pagination is NOT just limit → it's control
✔ Sorting belongs to DB
✔ Never trust user-provided size/page
✔ Defaults should be predictable
```

---

## 🎯 One-line Summary

```text
User controls query,
Backend controls safety,
Database executes efficiently
```

---
## Excel Export — Dynamic Fields, Enum Safety, and Ordering

---

## 🧠 1. Goal

```text
Export filtered student data → Excel
User controls:
✔ which fields
✔ filters (department, year, section)
✔ order of columns
```

---

## 🧠 2. Core Design

```text
DTO → request structure
Enum → type-safe fields
Map → data extraction logic
Service → filtering + export
Apache POI → Excel generation
```

---

## 🧠 3. Field Handling Evolution

### ❌ Before

```text
List<String> fields
→ required manual validation
→ prone to typos
```

---

### ✅ Now

```java
List<ProfileField> fields;
```

```text
✔ compile-time safety
✔ automatic validation by Spring
✔ no invalid strings
```

---

## 🧠 4. Enum Purpose

```java
public enum ProfileField {
    REGISTER_NUMBER,
    FULL_NAME,
    DEPARTMENT,
    YEAR,
    SECTION,
    BATCH,
    GITHUB,
    LEETCODE,
    HACKERRANK,
    CODE_FORCES,
    LINKEDIN
}
```

---

### Meaning

```text
Enum = allowed set of fields
```

---

## 🧠 5. Field Extractors

```java
Map<ProfileField, Function<StudentProfile, Object>>
```

---

### Purpose

```text
Maps:
FIELD → how to extract value from entity
```

---

### Example

```java
ProfileField.FULL_NAME → StudentProfile::getFullName
```

---

### Nested fields

```java
ProfileField.GITHUB →
p -> p.getHandle() != null ? p.getHandle().getGithub() : ""
```

---

## 🧠 6. Why LinkedHashMap

```java
new LinkedHashMap<>()
```

---

### Reason

```text
✔ preserves insertion order
✔ controls default Excel column order
```

---

### Behavior

|Case|Order Source|
|---|---|
|fields = null|backend (LinkedHashMap order)|
|fields given|user-defined order|

---

## 🧠 7. Field Headers

```java
Map<ProfileField, String>
```

---

### Purpose

```text
Convert enum → human readable header
```

---

### Example

```java
FULL_NAME → "Full Name"
```

---

## 🧠 8. Fallback Logic

```java
fieldHeaders.getOrDefault(field, field.name())
```

---

### Meaning

```text
If header exists → use it
Else → use enum name
```

---

### Example

|Case|Output|
|---|---|
|FULL_NAME|Full Name|
|UNKNOWN_FIELD|UNKNOWN_FIELD|

---

### Why needed

```text
✔ prevents null
✔ avoids crash
✔ flexible during development
```

---

## 🧠 9. Handling `fields`

```java
List<ProfileField> fields = request.getFields();
```

---

### Logic

```java
if (fields == null) {
    fields = new ArrayList<>(fieldExtractors.keySet());
} else if (fields.isEmpty()) {
    throw BadRequestException
}
```

---

### Behavior

|Input|Result|
|---|---|
|null|ALL fields|
|[]|error|
|[FIELDS]|selected fields|

---

## 🧠 10. Filtering Logic

```java
Specification<StudentProfile>
```

---

### Mandatory

```java
department AND year
```

---

### Optional

```java
section (if provided)
```

---

## 🧠 11. DB Query

```java
profileRepository.findAll(spec, Sort.by("registerNumber"))
```

---

### Happens in DB

```sql
WHERE department = ? AND year = ?
ORDER BY register_number
```

---

## 🧠 12. Excel Generation (Apache POI)

### Workbook

```java
Workbook workbook = new XSSFWorkbook();
```

---

### Sheet

```java
Sheet sheet = workbook.createSheet("Profiles");
```

---

### Header row

```java
Row header = sheet.createRow(0);
```

---

### Data rows

```java
for each profile → create row
for each field → extract value → set cell
```

---

### Cell value

```java
value != null ? value.toString() : ""
```

---

## 🧠 13. Styling

```java
CellStyle + Font (bold)
```

---

### Purpose

```text
Make header visually distinct
```

---

## 🧠 14. Column Auto-size

```java
sheet.autoSizeColumn(i);
```

---

### Effect

```text
Columns adjust to content width
```

---

## 🧠 15. Output

```java
ByteArrayOutputStream → byte[]
```

---

### Returned as

```text
application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
```

---

## 🧠 16. Error Handling

```text
No data → ResourceNotFoundException
Invalid input → BadRequestException
Failure → RuntimeException (fallback)
```

---

## 🧠 17. Key Design Decisions

```text
✔ Enum instead of String → safety
✔ LinkedHashMap → predictable order
✔ null fields → export all
✔ empty fields → reject
✔ fallback headers → flexibility
```

---

## 🧠 18. Final Flow

```text
Request
   ↓
DTO validation
   ↓
Service:
   → resolve fields
   → build spec
   → fetch data
   → generate Excel
   ↓
Return byte[]
```

---

## 🎯 One-line Summary

```text
User chooses WHAT → backend controls HOW → database executes efficiently → Excel is generated dynamically
```

---

## 🧩 Authentication + JWT Flow (Deep Understanding)

### What JWT actually is
- JWT is NOT encryption → it is **signed data**
- Anyone can read payload (base64 decoded)
- But cannot modify it without secret key

### Structure
`<token>` = header.payload.signature

### Signature logic
signature = HMAC(secret, header + payload)

### Verification flow (backend)
1. Receive `<token>`
2. Extract signature from token
3. Recompute signature using secret
4. Compare both
   - match → valid
   - mismatch → tampered

👉 No DB lookup needed → stateless auth

---

## 🔐 Why we store role + email in JWT

- Avoid DB hit on every request
- Role used for authorization
- Email useful for frontend display

BUT:
- Backend still trusts ONLY verified token
- Never trust frontend data

---

## 🔑 Password Handling

- Stored using BCrypt
- Never stored as plain text

```
passwordEncoder.encode(password)
passwordEncoder.matches(raw, encoded)
```

---

## 🧱 DTO vs Entity (Important design)

### Why DTO
- Control exposed fields
- Prevent sensitive leaks
- Separate API contract from DB

### Flow
DTO → Entity → DB  
DB → Entity → DTO

---

## 🔁 MapStruct (Core Idea)

### What it does
- Generates mapping code at compile time

### Why use it
- Avoid manual setter boilerplate
- Clean and maintainable

---

## ⚙️ Partial Update Logic (Very Important)

```
@BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
```

### What happens internally

Generated code behaves like:

```
if (dto.getFullName() != null) {
    entity.setFullName(dto.getFullName());
}
```

### Meaning
- Only provided fields update
- Others remain unchanged

👉 This is PATCH behavior

---

## ⚠️ Important Fix You Did

```
@Mapping(target = "handle", ignore = true)
```

### Why needed
- Without this → handle becomes null
- Because DTO.handle = null overwrites entity

---

## 🔗 OneToOne Mapping (Profile ↔ Handle)

### Actual DB storage
- `student_handle` table has `profile_id`

### This line
```
@OneToOne(mappedBy = "profile", cascade = ALL)
private StudentHandle handle;
```

### Meaning
- Profile does NOT own relation
- Handle owns relation
- Profile just references it

---

## 🔄 CascadeType.ALL

### What it does
Saving profile → also saves handle

No need:
```
handleRepository.save()
```

---

## 🧠 Update Flow (Important)

### Step-by-step

1. Fetch profile from DB
2. Apply mapper update
3. JPA tracks changes (dirty checking)
4. Save triggers SQL update

---

## 🔍 Filtering (Specification)

### Why not multiple queries
Because we build ONE SQL query

```
Specification`<StudentProfile>` spec = (root, query, cb) -> null;
```

### Then chain:

```
spec = spec.and(...)
```

### Final:
```
SELECT * FROM student_profile 
WHERE department=? AND year=? AND name LIKE ?
```

👉 Single query

---

## ⚠️ Your Bug (Important learning)

```
department is null and year is null
```

### Cause
You added spec without checking null

### Fix
```
if (department != null && !department.isBlank())
```

---

## 📄 Pagination (Important)

### Pageable contains:
- page
- size
- sort

### DB query becomes:
```
LIMIT size OFFSET page*size
```

---

## 🧠 Default + Limit Control

```
PageRequest.of(
  page,
  min(size, maxSize),
  defaultSortIfNeeded
)
```

### Why
- Prevent huge queries
- Protect DB

---

## 🔄 Mapping List / Page

```
profiles.stream().map(mapper::toResponse)
```

### Important understanding

- DB returns entities
- Mapping happens AFTER DB fetch
- Not N queries → only N object mappings

👉 Cheap operation

---

## 🔎 Search + Filter Design

You implemented:

- exact filter → department/year
- partial search → nameContains

👉 Good separation

---

## 📊 Excel Export (Core Idea)

### Flow

1. Receive request (fields + filters)
2. Validate fields
3. Fetch data (Specification)
4. Create workbook
5. Map fields dynamically
6. Return byte[]

---

## 🧠 Field Selection Design

### You used:
- Enum → ProfileField
- Map → extractor functions

```
`Map<ProfileField, Function<StudentProfile, Object>>`
```

### Why this is powerful
- No if-else chain
- Dynamic column selection
- Easy to extend

---

## 🔁 Default Fields Logic

```
if (fields == null)
    use all fields
```

### Why
- Better UX
- Flexible API

---

## 📌 LinkedHashMap (Important)

### Why not HashMap

- HashMap → random order
- LinkedHashMap → preserves order

👉 Required for Excel column order

---

## 🔄 Dynamic Column Build

Loop:
```
for (field : fields)
```

Each:
- create column
- extract value via map

---

## ⚠️ Validation Design

### Before
Manual:
```
if (department == null)
```

### After
```
@NotBlank
@Min @Max
```

👉 Cleaner + automatic

---

## 🧠 GlobalExceptionHandler

### What it does
- Converts exceptions → API response

### Without it
- Errors only in console

### With it
- JSON response to frontend

---

## 📌 ErrorResponse structure

```
timestamp
status
error
message
path
```

---

## 🔐 Environment Variables

```
${JWT_SECRET:default}
```

### Meaning
- use env if exists
- else fallback

---

## 🧠 Clean Architecture Check

You achieved:

- Controller → thin
- Service → logic
- Mapper → transformation
- Repository → DB
- DTO → API contract

👉 Proper layering


## 📘 Swagger Integration (Controller Level)

### Purpose
- Converts API into a **self-documented contract**
- Used by frontend/devs to understand API without backend access

---

## 🧩 Controller-Level Documentation

### @Tag
- Groups endpoints in Swagger UI
- Improves readability when multiple controllers exist

---

### @Operation
- Defines:
  - summary → short description
  - description → detailed explanation

👉 Used to explain behavior (e.g., PATCH only updates non-null fields)

---

### @ApiResponses
- Defines possible responses

Example:
- 200 → success
- 400 → validation error
- 404 → not found
- 409 → conflict

👉 Important for frontend error handling

---

## 🔍 Query Parameter Documentation

### @Parameter

Used for:
- @RequestParam
- Pageable

Example:
```
@Parameter(description = "Department filter", example = "CSE")
```

### Why needed
Without it:
- Swagger shows generic types only

With it:
- Clear meaning + example

---

## 📄 Pageable Documentation

```
@Parameter(description = "Pagination info (page, size, sort)")
@PageableDefault(page = 0, size = 30)
```

### Behavior
- Default page = 0
- Default size = 30

### Important
- If user doesn't pass → defaults used
- If user passes → overridden

---

## 📦 DTO-Level Swagger

### @Schema(example = "...")

Adds:
- Example values in Swagger UI
- Improves API usability

---

### Multi-line description

Used in HandleRequest:

Explains:
- We store usernames
- URLs are generated later

👉 This is design-level documentation (very good practice)

---

## 🧠 Request vs Query Design (Clarified)

### RequestBody (POST/PATCH)
- Used for structured data
- Swagger shows JSON editor

### RequestParam (GET)
- Used for filters/search
- Swagger shows input fields

---

## 📁 File Response (Excel Export)

Headers used:

```
Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
Content-Disposition: attachment; filename=profiles.xlsx
```

### Why both needed
- Content-Type → tells it's Excel
- Content-Disposition → forces download

---

## 🧠 Authentication Parameter Handling

```
Authentication authentication
```

### Internal use only
- Extracts userId from JWT

### Swagger improvement
```
@Parameter(hidden = true)
```

👉 Hides it from UI (cleaner API)

---

## ⚠️ Controller Design Clarification

Concern:
"Controller has too much code"

### Reality
Controller should contain:
✔ Routing  
✔ Validation trigger  
✔ Documentation  

Controller should NOT contain:
❌ Business logic  
❌ DB operations  

👉 Your controller is correctly designed

---

## 🧠 API Contract Maturity

Your API now defines:

✔ Input (DTO)  
✔ Output (Response DTO)  
✔ Validation rules  
✔ Error responses  
✔ Query behavior  
✔ Pagination  
✔ Export format  

👉 This is a **complete backend contract**

---

## 📌 Small Design Decisions You Finalized

### 1. Handle storage
- Store usernames only
- Generate URLs later

### 2. Export fields
- null → export all fields
- empty → error

### 3. Pagination safety
- default size applied
- max size capped

---

## 🧠 Current System State

You now have:

✔ Auth system (JWT)  
✔ Profile CRUD  
✔ Nested relation (Handle)  
✔ Dynamic filtering  
✔ Pagination  
✔ Excel export (dynamic fields)  
✔ Validation system  
✔ Global exception handling  
✔ Swagger documentation  

👉 This is **feature-complete backend (MVP level)**
## 🛠️ Swagger Response Fix (Important)

### Problem
Swagger showed same response (ProfileResponse) for:
- 200 ✅ correct
- 401 ❌ wrong
- 404 ❌ wrong

### Cause
Swagger infers response type from method return type

---

### Fix

Explicitly define response schema:

```
@ApiResponse(
    responseCode = "404",
    description = "Not found",
    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
)
```

---

### Pattern

- 200 → actual response DTO (e.g., ProfileResponse)
- 4xx/5xx → ErrorResponse

---

### Extra

```
@Parameter(hidden = true)
Authentication authentication
```

→ hides internal param from Swagger UI

---

### Result

✔ Accurate API contract  
✔ Frontend-safe error handling  
✔ No misleading responses  