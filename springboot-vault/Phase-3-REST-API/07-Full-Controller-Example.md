# Full Controller Example
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[🏠 Home]]

---

## Complete REST Controller

```java
@RestController
@RequestMapping("/api/students")
public class StudentController {

    // GET /api/students
    @GetMapping
    public List<String> getAll() {
        return List.of("Arjun", "Priya", "Kiran");
    }

    // GET /api/students/1
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        return "Student #" + id;
    }

    // GET /api/students?dept=CS
    @GetMapping("/search")
    public String search(@RequestParam String dept) {
        return "Students in dept: " + dept;
    }

    // POST /api/students
    // Body: { "name": "Arjun", "dept": "CS" }
    @PostMapping
    public String create(@RequestBody Student student) {
        return "Created: " + student.getName();
    }

    // PUT /api/students/1
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Student student) {
        return "Updated student " + id + " with name: " + student.getName();
    }

    // DELETE /api/students/1
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Deleted student " + id;
    }

}
```

---

## What Each Annotation Does (Summary)

| Annotation | Where | What It Does |
|-----------|-------|--------------|
| `@RestController` | Class | Marks as REST controller, returns JSON |
| `@RequestMapping` | Class | Sets base path for all methods |
| `@GetMapping` | Method | Maps GET request to this method |
| `@PostMapping` | Method | Maps POST request to this method |
| `@PutMapping` | Method | Maps PUT request to this method |
| `@DeleteMapping` | Method | Maps DELETE request to this method |
| `@PathVariable` | Parameter | Extracts value from URL path |
| `@RequestParam` | Parameter | Extracts value from query string |
| `@RequestBody` | Parameter | Converts JSON body to Java object |

---

## Phase 3 Mental Model

```
HTTP Request arrives
       ↓
Spring finds matching @GetMapping / @PostMapping
       ↓
Extracts data:
   - URL path vars  → @PathVariable
   - Query params   → @RequestParam
   - JSON body      → @RequestBody
       ↓
Calls your method
       ↓
Returns Java object → Jackson converts to JSON → sent to client
```

---

**Prev →** [[06-RequestBody|@RequestBody]]
**Next Phase →** [[../Phase-4-Request-Response/README|Phase 4 — Request & Response]]
