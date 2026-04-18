# @RequestBody
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[../🏠 Home]]

---

## What It Does

Reads the **HTTP request body** and converts it from JSON to a Java object.

---

## Example

```java
// POST /students
// Request body: { "name": "Arjun", "age": 21 }

@PostMapping
public String createStudent(@RequestBody Student student) {
    return "Created: " + student.getName();
}
```

### What happens:
1. Client sends JSON in the request body
2. Spring uses **Jackson** to deserialize JSON → Java object
3. Your method receives the populated object

---

## Jackson Does the Work

You don't write any parsing code.

```
JSON string  →  Jackson  →  Java object
{ "name": "Arjun" }  →  Student { name = "Arjun" }
```

For this to work, your class needs **getters and setters** (or use Lombok `@Data`).

---

## Common Mistake

```java
// ❌ This won't work — no @RequestBody
@PostMapping
public String create(Student student) { ... }

// ✅ Correct
@PostMapping
public String create(@RequestBody Student student) { ... }
```

Without `@RequestBody`, Spring looks for form fields, not JSON body.

---

## One-Line Summary

> `@RequestBody` tells Spring to read the JSON from the request body and convert it to a Java object automatically.

---

**Prev →** [[05-RequestParam|@RequestParam]]
**Next →** [[07-Full-Controller-Example|Full Controller Example]]
