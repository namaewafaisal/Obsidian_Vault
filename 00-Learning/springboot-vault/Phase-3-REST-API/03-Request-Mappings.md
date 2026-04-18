# Request Mappings (@GetMapping, @PostMapping, etc.)
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[../🏠 Home]]

---

## What They Do

These annotations **map an HTTP method + URL to a Java method**.

---

## All Mapping Annotations

| Annotation | HTTP Method | Typical Use |
|-----------|-------------|-------------|
| `@GetMapping` | GET | Fetch data |
| `@PostMapping` | POST | Create data |
| `@PutMapping` | PUT | Replace data |
| `@PatchMapping` | PATCH | Partially update |
| `@DeleteMapping` | DELETE | Delete data |
| `@RequestMapping` | Any (specify method) | Base path / generic |

---

## @RequestMapping on the Class (Base Path)

```java
@RestController
@RequestMapping("/students")   // ← base path for ALL methods below
public class StudentController {

    @GetMapping            // → GET /students
    public List<Student> getAll() { ... }

    @GetMapping("/{id}")   // → GET /students/1
    public Student getOne(@PathVariable Long id) { ... }

    @PostMapping           // → POST /students
    public Student create(@RequestBody Student s) { ... }

    @PutMapping("/{id}")   // → PUT /students/1
    public Student update(@PathVariable Long id, @RequestBody Student s) { ... }

    @DeleteMapping("/{id}") // → DELETE /students/1
    public void delete(@PathVariable Long id) { ... }

}
```

> **Rule:** `@RequestMapping` on class sets the base path. Method annotations extend it.
> `GET /students/1` = `@RequestMapping("/students")` + `@GetMapping("/{id}")`

---

## Without @RequestMapping on Class

```java
@GetMapping("/students")       // full path on each method
@PostMapping("/students")      // repeated — messy for large controllers
```

Not wrong, but verbose. Always use `@RequestMapping` on the class.

---

## @RequestMapping Generic Usage

```java
@RequestMapping(value = "/students", method = RequestMethod.GET)
public List<Student> getAll() { ... }
```

This is the old way. The shorthand annotations above are cleaner and preferred.

---

## One-Line Summary

> Mapping annotations connect a URL + HTTP method to a Java method. `@RequestMapping` on the class sets the shared base path.

---

**Prev →** [[02-RestController|@RestController]]
**Next →** [[04-PathVariable|@PathVariable]]
