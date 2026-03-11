# 3-Layer Architecture
**Phase:** [[README|Phase 6 — Service Layer]]
**Back:** [[🏠 Home]]

---

## The Standard

Every professional Spring Boot app is built in **3 layers**. This is not optional — it is industry standard.

```
HTTP Request
     ↓
@Controller   (receives request, calls service, returns response)
     ↓
@Service      (business logic, validation, orchestration)
     ↓
@Repository   (database access only)
     ↓
Database
```

---

## What Each Layer Knows

| Layer | Knows About | Does NOT Know About |
|-------|------------|---------------------|
| Controller | HTTP (requests, responses, status codes) | Database |
| Service | Business rules, DTOs, entities | HTTP |
| Repository | Database queries | Business rules |

---

## Why This Matters

- Controller is independently testable (mock the service)
- Service is independently testable (mock the repository)
- Repository is independently testable (use in-memory DB)
- Each layer is **replaceable** without touching the others

---

**Next →** [[02-Service-Annotation|@Service]]
