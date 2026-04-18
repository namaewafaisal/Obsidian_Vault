# @PathVariable
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[../🏠 Home]]

---

## What It Does

Extracts a value from the **URL path** and binds it to a method parameter.

---

## Example

```java
// URL: GET /students/42

@GetMapping("/{id}")
public String getStudent(@PathVariable Long id) {
    return "Student ID: " + id;   // id = 42
}
```

The `{id}` in the URL is a placeholder. `@PathVariable` captures it.

---

## Multiple Path Variables

```java
// URL: GET /departments/CS/students/42

@GetMapping("/departments/{dept}/students/{id}")
public String getStudent(
        @PathVariable String dept,
        @PathVariable Long id) {
    return dept + " - Student " + id;
}
```

---

## Custom Name

If your variable name differs from the path placeholder:

```java
@GetMapping("/{student_id}")
public String getStudent(@PathVariable("student_id") Long id) { ... }
```

---

**Prev →** [[03-Request-Mappings|Request Mappings]]
**Next →** [[05-RequestParam|@RequestParam]]
