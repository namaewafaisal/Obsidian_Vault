# @RequestParam
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[../🏠 Home]]

---

## What It Does

Extracts values from the **query string** (the part after `?` in the URL).

---

## Example

```java
// URL: GET /students?dept=CS&page=1

@GetMapping
public String getStudents(
        @RequestParam String dept,
        @RequestParam(defaultValue = "0") int page) {
    return "Dept: " + dept + ", Page: " + page;
}
```

---

## Optional Parameters

```java
@RequestParam(required = false) String dept
```

If not provided, `dept` will be `null`.

---

## @PathVariable vs @RequestParam

| | @PathVariable | @RequestParam |
|--|--------------|---------------|
| **URL format** | `/students/42` | `/students?id=42` |
| **Use for** | Resource identity (which one?) | Filtering, pagination, options |
| **Required?** | Yes (part of path) | Can be optional |

**Rule of thumb:**
- Identifying a specific resource → `@PathVariable`
- Filtering / sorting / pagination → `@RequestParam`

---

**Prev →** [[04-PathVariable|@PathVariable]]
**Next →** [[06-RequestBody|@RequestBody]]
