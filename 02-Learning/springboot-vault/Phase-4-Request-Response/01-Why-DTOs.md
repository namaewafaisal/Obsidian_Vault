# Why DTOs?
**Phase:** [[README|Phase 4 — Request & Response]]
**Back:** [[../🏠 Home]]

---

## What is a DTO?

A **DTO (Data Transfer Object)** is a plain Java class used to carry data between layers.

Specifically: between the **API and the client**.

---

## The Core Rule

> **Never expose your `@Entity` directly in your API.**

### Why

Your `@Entity` is your **database model**. It may contain:
- Hashed passwords
- Internal audit fields (`createdAt`, `updatedAt`)
- Foreign key IDs
- Fields the client should never see

Your DTO is your **API model**. It contains only what the client needs.

---

## ❌ Bad — Entity Exposed Directly

```java
@GetMapping("/{id}")
public Student getStudent(@PathVariable Long id) {
    return studentRepo.findById(id).get();
    // Leaks DB structure, password field, etc.
}
```

---

## ✅ Good — Using DTO

```java
@GetMapping("/{id}")
public StudentResponse getStudent(@PathVariable Long id) {
    Student student = studentRepo.findById(id).get();
    return new StudentResponse(student.getId(), student.getName());
    // Client only sees what you choose to expose
}
```

---

## The Full Flow

```
Client sends JSON
       ↓
   StudentRequest (DTO)   ← what comes IN
       ↓
   @Service processes it
       ↓
   Student (@Entity)      ← DB model, internal only
       ↓
   StudentResponse (DTO)  ← what goes OUT
       ↓
Client receives JSON
```

---

## Request DTO vs Response DTO

| | Request DTO | Response DTO |
|--|------------|--------------|
| Direction | Client → Server | Server → Client |
| Contains | Fields client sends | Fields client receives |
| Example | `StudentRequest` | `StudentResponse` |
| Validation | Yes (`@NotBlank`, etc.) | No |

---

**Next →** [[02-Request-DTO|Request DTO]]
