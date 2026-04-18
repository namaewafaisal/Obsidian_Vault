# ResponseEntity
**Phase:** [[README|Phase 4 — Request & Response]]
**Back:** [[../🏠 Home]]

---

## What It Does

`ResponseEntity` lets you control the **full HTTP response**:
- Status code
- Headers
- Body

Without it, Spring always returns `200 OK`. With it, you return the right status.

---

## Basic Usage

```java
// 200 OK with body
return ResponseEntity.ok(dto);

// 201 Created
return ResponseEntity.status(HttpStatus.CREATED).body(dto);

// 204 No Content (e.g. delete)
return ResponseEntity.noContent().build();

// 404 Not Found
return ResponseEntity.notFound().build();

// 400 Bad Request with error message
return ResponseEntity.badRequest().body("Invalid input");
```

---

## Full Controller Examples

```java
// GET — 200 OK or 404
@GetMapping("/{id}")
public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)                    // found → 200
        .orElse(ResponseEntity.notFound().build()); // not found → 404
}

// POST — 201 Created
@PostMapping
public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest req) {
    StudentResponse created = service.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}

// DELETE — 204 No Content
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
}
```

---

## Why Not Just Return the Object Directly?

```java
// This works but always sends 200 — even for created/deleted resources
@PostMapping
public StudentResponse create(@RequestBody StudentRequest req) { ... }
```

Using `ResponseEntity` makes your API semantically correct.
Clients and monitoring tools depend on accurate status codes.

---

## One-Line Summary

> `ResponseEntity` = wrapper that lets you set status code + body + headers before sending the HTTP response.

---

**Prev →** [[03-Response-DTO|Response DTO]]
**Next →** [[05-HTTP-Status-Codes|HTTP Status Codes]]
