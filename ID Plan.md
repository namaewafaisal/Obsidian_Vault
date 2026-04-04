## Identity System — Progress Tracker

---

## 🔐 Authentication & Security

* [x] User registration (email + password)
* [x] Password hashing (BCrypt)
* [x] Login API
* [x] JWT generation (id + role + email)
* [x] JWT validation filter
* [x] Secured endpoints (authenticated access)
* [x] Role included in token
* [x] Basic Spring Security config

---

## 👤 User Entity & Core Fields

* [x] User entity (email, password, role, verified)
* [x] UUID as primary key
* [x] createdAt with @PrePersist
* [x] profileCompleted flag
* [ ] Email verification flow (future)

---

## 🧾 Profile System

* [x] StudentProfile entity
* [x] One-to-one mapping with User
* [x] Profile creation (once per user)
* [x] Profile update
* [x] Profile fetch (self)
* [x] Profile fetch by register number (staff use)
* [x] Unique register number constraint
* [x] DTO separation (Request / Response / Update)

---

## 🔗 Handles (Social Profiles)

* [x] Separate StudentHandle table
* [x] One-to-one mapping with Profile
* [x] Nested JSON input for handles
* [x] Create + Update logic (mapper + manual relation)
* [x] Safe null handling
* [ ] Add more platforms (optional)
* [ ] Normalize platforms (future design)

---

## 🔁 Mapping Layer (MapStruct)

* [x] Entity → DTO mapping
* [x] DTO → Entity mapping
* [x] Partial update using @MappingTarget
* [x] Ignore null fields strategy
* [x] Separate mapper methods for nested objects
* [x] Fixed handle overwrite bug (ignore mapping)

---

## ✅ Validation

* [x] DTO validation annotations (@NotBlank, @Min, @Max)
* [x] @Valid in controller
* [x] Custom validation messages
* [x] Fixed null handling (@NotNull + @Min/@Max)
* [x] Conditional validation (fields logic in service)

---

## ⚠️ Exception Handling

* [x] GlobalExceptionHandler
* [x] Structured error response (timestamp, status, message, path)
* [x] Validation error handling (field-level errors)
* [x] ResourceNotFoundException → 404
* [x] ResourceAlreadyExistsException → 409
* [x] BadRequestException → 400
* [x] Removed generic RuntimeException usage (mostly)
* [ ] Custom error codes (future)
* [ ] Exception hierarchy refinement (future)

---

## 🔍 Search & Filtering

* [x] Filter by department + year
* [x] Optional filters (section, name)
* [x] Specification-based dynamic queries
* [x] Combined filtering (AND conditions)
* [x] DB-level filtering (not in-memory)

---

## 📄 Pagination & Sorting

* [x] Pageable integration
* [x] Default page + size
* [x] Max size restriction (config-driven)
* [x] Negative page handling
* [x] Default sorting (registerNumber)
* [x] User-provided sorting override
* [ ] Sort field whitelist (future safety)

---

## 📊 Excel Export (Major Feature)

* [x] Export endpoint
* [x] Apache POI integration
* [x] Dynamic column selection
* [x] Enum-based field selection (type-safe)
* [x] Field extractors (Map<Enum, Function>)
* [x] Nested field extraction (handles)
* [x] Dynamic headers
* [x] Header styling (bold)
* [x] Auto column sizing
* [x] Byte stream response
* [x] Mandatory filters (department, year)
* [x] Optional filters (section)
* [x] No-data handling (exception)
* [x] Field fallback logic (enum name)

---

## 🧠 Design Improvements

* [x] Removed string-based fields → replaced with enum
* [x] Removed manual validation loops
* [x] Introduced LinkedHashMap for ordering
* [x] Config-driven limits (YAML)
* [x] Clean separation: DTO vs Service vs Entity
* [x] Business logic vs validation separation
* [x] Flexible export logic (null → all fields)

---

## 🗄️ Database Design

* [x] Users table
* [x] StudentProfile table
* [x] StudentHandle table
* [x] Proper relationships (OneToOne)
* [x] Cascade handling
* [x] Schema reset understanding
* [ ] Advanced schema (academics, events, etc.)
* [ ] Indexing optimization (future)

---

## 🚀 API Design

* [x] REST structure
* [x] Proper HTTP methods (GET, POST, PATCH)
* [x] Clean response DTOs
* [x] Authentication via JWT header
* [x] Export API (binary response)
* [ ] Versioning (future)
* [ ] Rate limiting (future)

---

## 🧪 Testing & Usage

* [x] HTTPie usage
* [x] CLI-based testing
* [x] Debugging SQL queries
* [x] Error testing (validation, not found, etc.)
* [ ] Unit tests (JUnit, Mockito)
* [ ] Integration tests

---

## 🔮 Future Enhancements (Planned)

* [ ] Field whitelist for sorting
* [ ] Enum mapping from "fullName" → FULL_NAME
* [ ] Export templates (PLACEMENT, BASIC, etc.)
* [ ] Dynamic Excel formatting (colors, sections)
* [ ] Bulk import (Excel → DB)
* [ ] Profile sections split into multiple tables
* [ ] Caching (for heavy queries)
* [ ] Dockerize app
* [ ] Deploy (AWS / VPS)

---

## 🎯 Overall Progress

```text
Core backend system:        ✅ COMPLETED
Advanced querying:          ✅ COMPLETED
Excel export system:        ✅ COMPLETED
Production readiness:       ⚠️ PARTIALLY DONE
Scalability features:       ❌ NOT YET
```

---

## 🧠 Final Insight

```text
You have moved from:
"Building features"
→ "Designing systems"
```

---
