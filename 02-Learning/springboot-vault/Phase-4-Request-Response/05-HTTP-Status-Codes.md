# HTTP Status Codes
**Phase:** [[README|Phase 4 — Request & Response]]
**Back:** [[../🏠 Home]]

---

## The Ones You Must Know

| Code | Name | When to Use |
|------|------|-------------|
| `200` | OK | GET / PUT / PATCH succeeded |
| `201` | Created | POST succeeded, resource created |
| `204` | No Content | DELETE succeeded, no body to return |
| `400` | Bad Request | Client sent invalid/malformed data |
| `401` | Unauthorized | Not authenticated (no token / wrong token) |
| `403` | Forbidden | Authenticated but not allowed (wrong role) |
| `404` | Not Found | Resource with that ID doesn't exist |
| `409` | Conflict | Duplicate data (e.g. email already exists) |
| `422` | Unprocessable Entity | Validation failed (alternative to 400) |
| `500` | Internal Server Error | Unhandled exception on your side |

---

## In Code

```java
HttpStatus.OK                    // 200
HttpStatus.CREATED               // 201
HttpStatus.NO_CONTENT            // 204
HttpStatus.BAD_REQUEST           // 400
HttpStatus.UNAUTHORIZED          // 401
HttpStatus.FORBIDDEN             // 403
HttpStatus.NOT_FOUND             // 404
HttpStatus.CONFLICT              // 409
HttpStatus.INTERNAL_SERVER_ERROR // 500
```

---

## 401 vs 403 (Common Confusion)

| 401 Unauthorized | 403 Forbidden |
|-----------------|---------------|
| "Who are you?" | "I know who you are, but no." |
| No/invalid auth token | Token is valid, but wrong role |
| Fix: log in | Fix: get proper permissions |

---

**Prev →** [[04-ResponseEntity|ResponseEntity]]
**Next Phase →** [[../Phase-5-JPA-Hibernate/README|Phase 5 — JPA & Hibernate]]
