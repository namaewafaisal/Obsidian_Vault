# CodeDash — Full MVP Implementation Plan
> Use this document to continue building without losing context.
> Each phase has enough detail to implement without needing to ask questions.

---

## Project Stack

| Layer       | Choice                                        |
| ----------- | --------------------------------------------- |
| Framework   | Spring Boot 3.x                               |
| Language    | Java 17                                       |
| Database    | PostgreSQL                                    |
| ORM         | Spring Data JPA + Hibernate                   |
| Auth        | JWT (JJWT 0.12.x)                             |
| Mapping     | MapStruct                                     |
| Validation  | Jakarta Validation                            |
| Export      | Apache POI                                    |
| HTTP Client | RestTemplate or WebClient (for platform APIs) |
| Scheduler   | Spring @Scheduled                             |
| API Docs    | Springdoc OpenAPI (Swagger)                   |
| Deploy      | Railway                                       |

---

## Folder Structure

```
src/main/java/com/codedash/
├── auth/
│   ├── AuthController.java
│   ├── AuthService.java
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── RegisterRequest.java
│   ├── JwtUtil.java
│   └── JwtFilter.java
├── institution/
│   ├── Institution.java              (entity)
│   ├── InstitutionStatus.java        (enum: PENDING, APPROVED, REJECTED)
│   ├── InstitutionRepository.java
│   ├── InstitutionService.java
│   ├── InstitutionController.java
│   └── dto/
│       ├── InstitutionRegisterRequest.java
│       └── InstitutionResponse.java
├── user/
│   ├── User.java                     (entity)
│   ├── Role.java                     (enum: MASTER, INSTITUTION_ADMIN, STAFF, STUDENT)
│   ├── UserRepository.java
│   ├── UserService.java
│   └── UserController.java
├── profile/
│   ├── StudentProfile.java           (entity)
│   ├── StudentProfileRepository.java
│   ├── ProfileService.java
│   ├── ProfileController.java
│   ├── ProfileMapper.java
│   └── dto/
│       ├── ProfileRequest.java
│       ├── UpdateProfileRequest.java
│       └── ProfileResponse.java
├── handle/
│   ├── StudentHandle.java            (entity)
│   ├── Platform.java                 (enum)
│   ├── HandleRepository.java
│   ├── HandleService.java
│   ├── HandleController.java
│   ├── HandleMapper.java
│   └── dto/
│       ├── HandleRequest.java
│       └── HandleResponse.java
├── stats/
│   ├── HandleStats.java              (entity)
│   ├── HandleStatsRepository.java
│   ├── StatsService.java
│   ├── sync/
│   │   ├── PlatformSyncService.java  (interface)
│   │   ├── LeetcodeSyncService.java
│   │   ├── CodeforcesSyncService.java
│   │   └── GithubSyncService.java
│   └── dto/
│       └── StatsResponse.java
├── dashboard/
│   ├── DashboardService.java
│   ├── DashboardController.java
│   └── dto/
│       ├── DashboardRequest.java
│       └── DashboardResponse.java
├── export/
│   ├── ExportService.java
│   ├── ExportController.java
│   ├── ExportRequest.java
│   └── ProfileField.java             (enum)
├── scheduler/
│   └── StatsSyncScheduler.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── AlreadyExistsException.java
│   ├── BadRequestException.java
│   ├── ForbiddenException.java
│   └── ErrorResponse.java
└── config/
    ├── SecurityConfig.java
    └── SwaggerConfig.java
```

---

## Entities (write these first, everything else depends on them)

### Institution.java
```java
@Entity
@Table(name = "institutions")
public class Institution {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String domain;                    // e.g. trp.srmtrichy.edu.in

    @Enumerated(EnumType.STRING)
    private InstitutionStatus status;         // PENDING, APPROVED, REJECTED

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() { this.createdAt = LocalDateTime.now(); }
}
```

### User.java
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;                  // bcrypt

    @Enumerated(EnumType.STRING)
    private Role role;                        // MASTER, INSTITUTION_ADMIN, STAFF, STUDENT

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;          // null for MASTER

    private boolean emailVerified;

    private String verificationToken;        // UUID token sent in email

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() { this.createdAt = LocalDateTime.now(); }
}
```

### StudentProfile.java
```java
@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String fullName;

    @Column(unique = true)
    private String registerNumber;

    private String department;
    private Integer year;
    private String section;
    private String trainingBatch;
    private String phone;
    private String personalEmail;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<StudentHandle> handles;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist public void prePersist() { this.createdAt = LocalDateTime.now(); }
    @PreUpdate  public void preUpdate()  { this.updatedAt = LocalDateTime.now(); }
}
```

### StudentHandle.java
```java
@Entity
@Table(
    name = "student_handles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "platform"})
)
public class StudentHandle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private StudentProfile profile;

    @Enumerated(EnumType.STRING)
    private Platform platform;               // LEETCODE, CODEFORCES, GITHUB, etc.

    private String username;

    private boolean verified;

    private LocalDateTime usernameUpdatedAt;

    @OneToOne(mappedBy = "handle", cascade = CascadeType.ALL)
    private HandleStats stats;
}
```

### HandleStats.java
```java
@Entity
@Table(name = "handle_stats")
public class HandleStats {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "handle_id", unique = true, nullable = false)
    private StudentHandle handle;

    // Structured — for sorting and filtering
    private Integer problemsSolved;
    private Integer easySolved;
    private Integer mediumSolved;
    private Integer hardSolved;
    private Integer rating;
    private Integer globalRank;
    private LocalDateTime lastSubmissionAt;  // for activity indicator

    // Flexible — full API response
    @Column(columnDefinition = "jsonb")
    private String rawData;

    private LocalDateTime lastSyncedAt;
}
```

### Platform.java (enum)
```java
public enum Platform {
    LEETCODE, CODEFORCES, GITHUB, HACKERRANK, GFG, HACKEREARTH
}
```

### Role.java (enum)
```java
public enum Role {
    MASTER, INSTITUTION_ADMIN, STAFF, STUDENT
}
```

### InstitutionStatus.java (enum)
```java
public enum InstitutionStatus {
    PENDING, APPROVED, REJECTED
}
```

---

## application.yml

```yaml
spring:
  application:
    name: codedash

  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/codedash}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  jackson:
    default-property-inclusion: non_null

  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_APP_PASSWORD}
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

app:
  jwt:
    secret: ${JWT_SECRET:your-local-dev-secret-must-be-long-enough-32chars}
    expiration-ms: 86400000
  pagination:
    max-size: 100
  master:
    email: ${MASTER_EMAIL:your@email.com}
  base-url: ${BASE_URL:http://localhost:8080}
```

---

## Phase 1 — Institution + Auth (Days 1–6)

### What you're building
Institution registration with Master approval, user registration with email domain
validation, email verification, JWT login.

---

### Step 1.1 — Institution Registration

**InstitutionRegisterRequest.java**
```java
@Data
public class InstitutionRegisterRequest {
    @NotBlank private String institutionName;
    @NotBlank private String domain;          // must not be gmail.com, yahoo.com etc
    @Email @NotBlank private String adminEmail;
    @NotBlank @Size(min=8, max=64) private String adminPassword;
}
```

**InstitutionService.java — register method**
```java
public void registerInstitution(InstitutionRegisterRequest request) {

    // 1. Block generic domains
    List<String> blockedDomains = List.of("gmail.com","yahoo.com","hotmail.com","outlook.com");
    if (blockedDomains.contains(request.getDomain().toLowerCase())) {
        throw new BadRequestException("Generic email domains are not allowed");
    }

    // 2. Check domain not already registered
    if (institutionRepository.existsByDomain(request.getDomain())) {
        throw new AlreadyExistsException("Domain already registered");
    }

    // 3. Create institution with PENDING status
    Institution institution = new Institution();
    institution.setName(request.getInstitutionName());
    institution.setDomain(request.getDomain());
    institution.setStatus(InstitutionStatus.PENDING);
    institutionRepository.save(institution);

    // 4. Create admin user (not yet active — institution pending)
    User admin = new User();
    admin.setEmail(request.getAdminEmail());
    admin.setPassword(passwordEncoder.encode(request.getAdminPassword()));
    admin.setRole(Role.INSTITUTION_ADMIN);
    admin.setInstitution(institution);
    admin.setEmailVerified(false);
    userRepository.save(admin);

    // 5. Email Master for approval
    emailService.sendInstitutionApprovalRequest(
        institution, admin, masterEmail
    );
}
```

**Master approval endpoint (secured — MASTER role only)**
```
PATCH /api/master/institutions/{id}
Body: { "action": "APPROVE" or "REJECT" }
```

On APPROVE:
- Set institution status to APPROVED
- Set admin user emailVerified to true
- Send welcome email to admin

On REJECT:
- Set institution status to REJECTED
- Send rejection email to admin

---

### Step 1.2 — User Registration

**RegisterRequest.java**
```java
@Data
public class RegisterRequest {
    @Email @NotBlank private String email;
    @NotBlank @Size(min=8, max=64) private String password;
    @NotNull private Long institutionId;
}
```

**AuthService.java — register method**
```java
public void register(RegisterRequest request) {

    // 1. Find institution
    Institution institution = institutionRepository.findById(request.getInstitutionId())
        .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

    // 2. Institution must be APPROVED
    if (institution.getStatus() != InstitutionStatus.APPROVED) {
        throw new BadRequestException("Institution is not active");
    }

    // 3. Email domain must match institution domain
    String emailDomain = request.getEmail().split("@")[1];
    if (!emailDomain.equalsIgnoreCase(institution.getDomain())) {
        throw new BadRequestException("Email domain does not match institution");
    }

    // 4. Email not already registered
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new AlreadyExistsException("Email already registered");
    }

    // 5. Create user (not verified yet)
    String verificationToken = UUID.randomUUID().toString();
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.STUDENT);
    user.setInstitution(institution);
    user.setEmailVerified(false);
    user.setVerificationToken(verificationToken);
    userRepository.save(user);

    // 6. Send verification email
    emailService.sendVerificationEmail(user, verificationToken);
}
```

**Email verification endpoint**
```
GET /api/auth/verify?token=abc123
```
- Find user by verificationToken
- Set emailVerified = true
- Clear verificationToken
- Return 200

**Login — only allow verified users**
```java
if (!user.isEmailVerified()) {
    throw new BadRequestException("Please verify your email first");
}
```

---

### Step 1.3 — JWT

Same pattern as your previous project. Put in JWT claims:
- `sub` — userId (UUID as string)
- `role` — role name
- `institutionId` — institution id (for scoping queries)
- `email`

The `institutionId` in the token is important — every query must be scoped
to this institution. Never return data from another institution.

**JwtFilter — extract institutionId too:**
```java
String userId = claims.getSubject();
String role = claims.get("role", String.class);
Long institutionId = claims.get("institutionId", Long.class);
```

Store all three in the Authentication principal or use a custom UserPrincipal object.

**Custom UserPrincipal (recommended):**
```java
@Data
@AllArgsConstructor
public class UserPrincipal {
    private UUID userId;
    private String role;
    private Long institutionId;
}
```

Set as principal in JwtFilter:
```java
UsernamePasswordAuthenticationToken authToken =
    new UsernamePasswordAuthenticationToken(
        new UserPrincipal(UUID.fromString(userId), role, institutionId),
        null,
        List.of(new SimpleGrantedAuthority("ROLE_" + role))
    );
```

In controllers, extract like this:
```java
UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
UUID userId = principal.getUserId();
Long institutionId = principal.getInstitutionId();
```

---

### Step 1.4 — SecurityConfig

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .requestMatchers("/api/institutions/register").permitAll()
    .requestMatchers("/api/master/**").hasRole("MASTER")
    .requestMatchers("/api/admin/**").hasRole("INSTITUTION_ADMIN")
    .requestMatchers("/api/export/**").hasAnyRole("STAFF","INSTITUTION_ADMIN")
    .requestMatchers("/api/dashboard/**").hasAnyRole("STAFF","INSTITUTION_ADMIN")
    .anyRequest().authenticated()
)
```

---

## Phase 2 — Profile + Handles (Days 7–11)

### What you're building
Student creates their profile. Student adds platform handles.
Each handle addition triggers a verification attempt.

---

### Step 2.1 — Profile

Same as what you already have — the pattern is identical.

**Key difference from old code:**
- Profile is scoped to institution via user → institution relationship
- Register number uniqueness is scoped per institution, not global

```java
// Uniqueness check scoped to institution
if (profileRepository.existsByRegisterNumberAndUserInstitutionId(
        request.getRegisterNumber(), institutionId)) {
    throw new AlreadyExistsException("Register number already exists in this institution");
}
```

Add this to `StudentProfileRepository`:
```java
boolean existsByRegisterNumberAndUserInstitutionId(
    String registerNumber, Long institutionId);
```

**ProfileRequest.java**
```java
@Data
public class ProfileRequest {
    @NotBlank private String fullName;
    @NotBlank private String registerNumber;
    @NotBlank private String department;
    @Min(1) @Max(4) private Integer year;
    private String section;
    private String trainingBatch;
    @Size(min=10, max=15) private String phone;
    @Email private String personalEmail;
}
```

No handles in ProfileRequest — handles are added separately via `/api/handles`.

---

### Step 2.2 — Handles

**HandleRequest.java**
```java
@Data
public class HandleRequest {
    @NotNull private Platform platform;
    @NotBlank private String username;
}
```

**HandleService.java — addHandle method**
```java
@Transactional
public HandleResponse addHandle(HandleRequest request, UUID userId) {

    StudentProfile profile = profileRepository.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

    // One handle per platform per student
    if (handleRepository.existsByProfileAndPlatform(profile, request.getPlatform())) {
        throw new AlreadyExistsException("Handle already exists for this platform");
    }

    StudentHandle handle = new StudentHandle();
    handle.setProfile(profile);
    handle.setPlatform(request.getPlatform());
    handle.setUsername(request.getUsername());
    handle.setVerified(false);
    handle.setUsernameUpdatedAt(LocalDateTime.now());
    handleRepository.save(handle);

    // Attempt verification (non-blocking — if it fails, handle still saved)
    verificationService.tryVerify(handle);

    return handleMapper.toResponse(handle);
}
```

**Handle verification — per platform:**

LeetCode (free GraphQL):
```java
// POST https://leetcode.com/graphql
// Body: {"query":"{ matchedUser(username:\"USERNAME\") { username } }"}
// If matchedUser is null → username doesn't exist
```

GitHub (free REST):
```java
// GET https://api.github.com/users/USERNAME
// 404 → doesn't exist, 200 → exists
```

Codeforces (free REST):
```java
// GET https://codeforces.com/api/user.info?handles=USERNAME
// status: "FAILED" → doesn't exist
```

GFG — no reliable free API → skip verification, store as-is.

HackerRank — check their public API or skip.

HackerEarth — check their public API or skip.

If verification API call throws any exception → catch, log, leave verified=false.
Never fail the handle creation because of a verification failure.

---

### Step 2.3 — Handle Endpoints

```
POST   /api/handles              add a handle
GET    /api/handles/me           get my handles
PATCH  /api/handles/{platform}   update username for a platform
DELETE /api/handles/{platform}   remove a handle
```

PATCH triggers re-verification with the new username.

---

## Phase 3 — Stats Sync (Days 12–16)

### What you're building
Nightly CRON job that fetches stats for every student handle
and stores them in HandleStats.

---

### Step 3.1 — PlatformSyncService interface

```java
public interface PlatformSyncService {
    Platform getPlatform();
    HandleStats fetchStats(String username);
}
```

Each platform gets its own implementation.

---

### Step 3.2 — LeetcodeSyncService

```java
@Service
public class LeetcodeSyncService implements PlatformSyncService {

    @Override
    public Platform getPlatform() { return Platform.LEETCODE; }

    @Override
    public HandleStats fetchStats(String username) {

        String query = """
            {
              "query": "{ matchedUser(username:\\"%s\\") {
                submitStats: submitStatsGlobal {
                  acSubmissionNum { difficulty count }
                }
                profile { ranking }
                recentAcSubmissionList(limit: 10) {
                  title timestamp
                }
              }}"
            }
            """.formatted(username);

        // POST to https://leetcode.com/graphql
        // Parse response into HandleStats

        HandleStats stats = new HandleStats();
        // parse acSubmissionNum for easy/medium/hard/total
        // parse ranking for globalRank
        // parse recentAcSubmissionList[0].timestamp for lastSubmissionAt
        // store full response as rawData
        stats.setLastSyncedAt(LocalDateTime.now());
        return stats;
    }
}
```

**Parsing the LeetCode response:**
```
acSubmissionNum is an array:
[
  { difficulty: "All",    count: 234 },
  { difficulty: "Easy",   count: 100 },
  { difficulty: "Medium", count: 90  },
  { difficulty: "Hard",   count: 44  }
]
```
Map `All` → problemsSolved, `Easy` → easySolved, etc.

`lastSubmissionAt` — parse `timestamp` from `recentAcSubmissionList[0]`.
LeetCode returns Unix timestamp (seconds) → convert to LocalDateTime:
```java
LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)
```

---

### Step 3.3 — CodeforcesSyncService

```java
// GET https://codeforces.com/api/user.info?handles=USERNAME
// Returns: { result: [{ rating, maxRating, rank, problemsSolved }] }

// GET https://codeforces.com/api/user.status?handle=USERNAME&from=1&count=1
// Returns most recent submission — check verdict="OK" for accepted
```

Codeforces does not give easy/medium/hard breakdown — leave those null.
Store rating and rank in structured columns.

---

### Step 3.4 — GithubSyncService

```java
// GET https://api.github.com/users/USERNAME
// Returns: public_repos, followers

// GET https://api.github.com/users/USERNAME/events/public
// Returns recent events — look for PushEvent for lastSubmissionAt equivalent
```

GitHub has no problemsSolved equivalent — leave those null.
Store public_repos as problemsSolved (optional, just for leaderboard sorting).
Store last PushEvent date as lastSubmissionAt.

Add GitHub token to avoid rate limiting:
```yaml
app:
  github:
    token: ${GITHUB_TOKEN:}
```

---

### Step 3.5 — StatsSyncScheduler

```java
@Component
@RequiredArgsConstructor
public class StatsSyncScheduler {

    private final HandleRepository handleRepository;
    private final HandleStatsRepository statsRepository;
    private final List<PlatformSyncService> syncServices;

    // Build a map at startup: Platform → SyncService
    private Map<Platform, PlatformSyncService> serviceMap;

    @PostConstruct
    public void init() {
        serviceMap = syncServices.stream()
            .collect(Collectors.toMap(
                PlatformSyncService::getPlatform,
                s -> s
            ));
    }

    @Scheduled(cron = "0 0 2 * * *")  // 2:00 AM every night
    public void syncAll() {
        List<StudentHandle> handles = handleRepository.findAll();

        for (StudentHandle handle : handles) {
            try {
                PlatformSyncService service = serviceMap.get(handle.getPlatform());
                if (service == null) continue;  // platform not supported yet

                HandleStats freshStats = service.fetchStats(handle.getUsername());
                freshStats.setHandle(handle);

                // Upsert — find existing or create new
                HandleStats existing = statsRepository
                    .findByHandle(handle)
                    .orElse(freshStats);

                if (existing != freshStats) {
                    // Update existing row
                    existing.setProblemsSolved(freshStats.getProblemsSolved());
                    existing.setEasySolved(freshStats.getEasySolved());
                    existing.setMediumSolved(freshStats.getMediumSolved());
                    existing.setHardSolved(freshStats.getHardSolved());
                    existing.setRating(freshStats.getRating());
                    existing.setGlobalRank(freshStats.getGlobalRank());
                    existing.setLastSubmissionAt(freshStats.getLastSubmissionAt());
                    existing.setRawData(freshStats.getRawData());
                    existing.setLastSyncedAt(LocalDateTime.now());
                    statsRepository.save(existing);
                } else {
                    statsRepository.save(freshStats);
                }

            } catch (Exception e) {
                // Log and continue — one failure should not stop the whole sync
                log.error("Sync failed for handle {}: {}", handle.getId(), e.getMessage());
            }
        }
    }
}
```

Enable scheduling in main class:
```java
@SpringBootApplication
@EnableScheduling
public class CodeDashApplication { ... }
```

---

## Phase 4 — Dashboard + Leaderboard (Days 17–20)

### What you're building
Filtered + sorted leaderboard query with JOIN across three tables.
Staff selects platform and filters. Results sorted by stats.

---

### Step 4.1 — DashboardRequest

```java
@Data
public class DashboardRequest {
    @NotNull private Platform platform;
    private String department;
    private Integer year;
    private String section;
    private Integer activeWithinDays;   // default 7 if null
}
```

---

### Step 4.2 — DashboardResponse

```java
@Data
public class DashboardResponse {
    // Profile fields
    private String fullName;
    private String registerNumber;
    private String department;
    private Integer year;
    private String section;

    // Handle
    private String username;
    private boolean verified;

    // Stats
    private Integer problemsSolved;
    private Integer easySolved;
    private Integer mediumSolved;
    private Integer hardSolved;
    private Integer rating;
    private Integer globalRank;
    private LocalDateTime lastSubmissionAt;
    private boolean active;              // computed: lastSubmissionAt >= now - X days

    // Raw data for click-through
    private String rawData;              // JSON string, frontend parses
}
```

---

### Step 4.3 — DashboardService

```java
public Page<DashboardResponse> getDashboard(
        DashboardRequest request,
        Long institutionId,
        int page, int size,
        String sortBy, String direction) {

    // Page + size only, no sort in Pageable (sorting on joined table)
    Pageable pageable = PageRequest.of(
        Math.max(page, 0),
        Math.min(size <= 0 ? 30 : size, 100)
    );

    Set<String> allowedSortFields = Set.of(
        "problemsSolved","easySolved","mediumSolved",
        "hardSolved","rating","globalRank","lastSubmissionAt"
    );
    String sortField = (sortBy != null && allowedSortFields.contains(sortBy))
        ? sortBy : "problemsSolved";

    Specification<StudentProfile> spec = (root, query, cb) -> {
        query.distinct(true);

        // JOIN chain: StudentProfile → StudentHandle → HandleStats
        Join<StudentProfile, StudentHandle> handleJoin =
            root.join("handles", JoinType.INNER);
        Join<StudentHandle, HandleStats> statsJoin =
            handleJoin.join("stats", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        // REQUIRED: scope to institution
        predicates.add(cb.equal(
            root.get("user").get("institution").get("id"), institutionId
        ));

        // REQUIRED: platform filter
        predicates.add(cb.equal(handleJoin.get("platform"), request.getPlatform()));

        // OPTIONAL filters
        if (request.getDepartment() != null && !request.getDepartment().isBlank())
            predicates.add(cb.equal(root.get("department"), request.getDepartment()));

        if (request.getYear() != null)
            predicates.add(cb.equal(root.get("year"), request.getYear()));

        if (request.getSection() != null && !request.getSection().isBlank())
            predicates.add(cb.equal(root.get("section"), request.getSection()));

        // Sort on stats field
        Path<?> sortPath = statsJoin.get(sortField);
        query.orderBy(
            direction.equalsIgnoreCase("asc")
                ? cb.asc(sortPath)
                : cb.desc(sortPath)
        );

        return cb.and(predicates.toArray(new Predicate[0]));
    };

    Page<StudentProfile> profiles = profileRepository.findAll(spec, pageable);

    int activeDays = request.getActiveWithinDays() != null
        ? request.getActiveWithinDays() : 7;

    return profiles.map(p -> mapToDashboard(p, request.getPlatform(), activeDays));
}

private DashboardResponse mapToDashboard(
        StudentProfile profile, Platform platform, int activeDays) {

    // Find the handle for this platform
    StudentHandle handle = profile.getHandles().stream()
        .filter(h -> h.getPlatform() == platform)
        .findFirst()
        .orElse(null);

    DashboardResponse response = new DashboardResponse();
    response.setFullName(profile.getFullName());
    response.setRegisterNumber(profile.getRegisterNumber());
    response.setDepartment(profile.getDepartment());
    response.setYear(profile.getYear());
    response.setSection(profile.getSection());

    if (handle != null) {
        response.setUsername(handle.getUsername());
        response.setVerified(handle.isVerified());

        HandleStats stats = handle.getStats();
        if (stats != null) {
            response.setProblemsSolved(stats.getProblemsSolved());
            response.setEasySolved(stats.getEasySolved());
            response.setMediumSolved(stats.getMediumSolved());
            response.setHardSolved(stats.getHardSolved());
            response.setRating(stats.getRating());
            response.setGlobalRank(stats.getGlobalRank());
            response.setLastSubmissionAt(stats.getLastSubmissionAt());
            response.setRawData(stats.getRawData());

            boolean active = stats.getLastSubmissionAt() != null &&
                stats.getLastSubmissionAt().isAfter(
                    LocalDateTime.now().minusDays(activeDays)
                );
            response.setActive(active);
        }
    }

    return response;
}
```

---

### Step 4.4 — DashboardController

```java
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('STAFF','INSTITUTION_ADMIN')")
public class DashboardController {

    private final DashboardService dashboardService;

    @PostMapping
    public Page<DashboardResponse> getDashboard(
            @Valid @RequestBody DashboardRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            Authentication authentication) {

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return dashboardService.getDashboard(
            request, principal.getInstitutionId(),
            page, size, sortBy, direction
        );
    }

    @GetMapping("/student/{profileId}")
    public DashboardResponse getStudentDetail(
            @PathVariable Long profileId,
            @RequestParam Platform platform,
            Authentication authentication) {

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return dashboardService.getStudentDetail(
            profileId, platform, principal.getInstitutionId()
        );
    }
}
```

---

## Phase 5 — Export (Days 21–23)

Same pattern as your existing export. Add stats fields to `ProfileField` enum
and `fieldExtractors` map.

New fields to add:
```java
map.put(ProfileField.PROBLEMS_SOLVED,
    p -> getStatsForPlatform(p, platform).getProblemsSolved());
map.put(ProfileField.EASY_SOLVED, ...);
map.put(ProfileField.MEDIUM_SOLVED, ...);
map.put(ProfileField.HARD_SOLVED, ...);
map.put(ProfileField.RATING, ...);
map.put(ProfileField.LAST_SUBMISSION, ...);
```

Export endpoint takes platform as mandatory field in ExportRequest.

---

## Phase 6 — Admin Endpoints (Days 24–25)

```
GET   /api/admin/users                    list all users in institution
PATCH /api/admin/users/{id}/role          promote to STAFF
DELETE /api/admin/institution             soft delete or hard delete
```

All endpoints scoped to `principal.getInstitutionId()` — admin
can only manage their own institution's users.

---

## Phase 7 — Polish + Deploy (Days 26–28)

### Before deploy checklist
- [ ] Fix `RuntimeException` handler in GlobalExceptionHandler
      (use `Exception.class` as fallback, not `RuntimeException.class`)
- [ ] Add `@PreAuthorize` to all endpoints that need role checks
- [ ] Disable Swagger introspection in prod profile
- [ ] Add content-type header to Excel export response
- [ ] Make sure all queries are scoped by institutionId
- [ ] Test with two institutions — confirm data isolation

### Railway deploy
1. Push to GitHub
2. New project on railway.app → deploy from GitHub
3. Add PostgreSQL plugin
4. Set env vars:
   ```
   DB_URL           (Railway provides this automatically)
   JWT_SECRET       (generate: openssl rand -base64 64)
   MAIL_USERNAME    (your Gmail)
   MAIL_APP_PASSWORD (Gmail app password, not account password)
   MASTER_EMAIL     (your email to receive institution requests)
   GITHUB_TOKEN     (GitHub personal access token for API)
   BASE_URL         (your Railway app URL)
   ```
5. `ddl-auto: update` handles schema creation on first run

---

## Important Implementation Notes

### Institution scoping — never forget this
Every query that returns student data must filter by institutionId.
Use this pattern everywhere:
```java
UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
Long institutionId = principal.getInstitutionId();
// pass institutionId to every service method that queries students
```

### N+1 query warning
When loading profiles with handles and stats, use fetch joins or
`@EntityGraph` to avoid N+1:
```java
@EntityGraph(attributePaths = {"handles", "handles.stats"})
Optional<StudentProfile> findByUserId(UUID userId);
```

### Exception handling order
In GlobalExceptionHandler, specific exceptions must be listed before generic ones.
`ResourceNotFoundException`, `AlreadyExistsException`, `BadRequestException`
each get their own handler. The generic `Exception` handler goes last.

### Sync job failures
Never let one failed sync stop the entire job.
Wrap each handle sync in try/catch and log the error.
The scheduler continues to the next handle regardless.

### rawData size
LeetCode returns a lot of data. Limit what you store in rawData —
only recent submissions (last 10) and essential profile data.
Don't store the entire API response blindly.

---

## Open Items to Resolve Before Starting

1. Which platforms for MVP sync? Suggested: LeetCode + Codeforces + GitHub
   GFG has no reliable free API — add as handle-only (no stats sync) for now

2. Email service — use Gmail SMTP with app password for MVP.
   Replace with SendGrid or similar in production.

3. Master approval flow — for MVP, master has a private endpoint.
   No UI needed — use Postman or HTTPie to approve.

4. Handle verification on update — when student changes username,
   reset verified=false and re-verify with new username.

5. Register number extraction from email — implement only if your college
   format matches. Make it configurable per institution via a flag:
   `Institution.registerNumberInEmail: Boolean`
