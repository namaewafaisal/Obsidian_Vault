# What is REST?
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[🏠 Home]]

---

## Definition

> **REST** (Representational State Transfer) is an architectural style for building APIs over HTTP.

> A REST API is a backend that receives HTTP requests (GET, POST, PUT, DELETE) and returns structured data — usually JSON.

---

## HTTP Methods

| Method | Purpose | Example URL |
|--------|---------|-------------|
| `GET` | Read / fetch data | `GET /students` → returns all students |
| `POST` | Create new data | `POST /students` → creates one student |
| `PUT` | Replace full record | `PUT /students/1` → replaces student 1 |
| `PATCH` | Update partial record | `PATCH /students/1` → updates one field |
| `DELETE` | Delete a record | `DELETE /students/1` → deletes student 1 |

---

## HTTP Status Codes (Must Know)

| Code | Meaning | When to Use |
|------|---------|-------------|
| `200 OK` | Success | GET / PUT / PATCH succeeded |
| `201 Created` | Resource created | POST succeeded |
| `204 No Content` | Success, no body | DELETE succeeded |
| `400 Bad Request` | Invalid client data | Validation failed |
| `401 Unauthorized` | Not authenticated | No/invalid token |
| `403 Forbidden` | Authenticated, not allowed | Wrong role |
| `404 Not Found` | Resource doesn't exist | Wrong ID |
| `500 Internal Server Error` | Bug on server side | Unhandled exception |

---

## REST URL Design Rules

```
GET    /students          → get all
GET    /students/1        → get one by ID
POST   /students          → create new
PUT    /students/1        → replace student 1
PATCH  /students/1        → partially update student 1
DELETE /students/1        → delete student 1
```

**Rules:**
- URLs are **nouns**, not verbs (`/students` not `/getStudents`)
- Plural names (`/students` not `/student`)
- IDs go in the path (`/students/1` not `/students?id=1`)

---

**Next →** [[02-RestController|@RestController]]
