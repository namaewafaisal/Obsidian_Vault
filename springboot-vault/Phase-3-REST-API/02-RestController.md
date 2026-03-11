# @RestController
**Phase:** [[README|Phase 3 — REST API]]
**Back:** [[🏠 Home]]

---

## What It Does

Tells Spring: **this class handles HTTP requests and returns data directly** (not an HTML page).

---

## What @RestController Actually Is

```
@RestController = @Controller + @ResponseBody
```

| Annotation | Role |
|-----------|------|
| `@Controller` | Registers class as a Spring MVC controller |
| `@ResponseBody` | Writes return value directly as HTTP response body (JSON) |

> You do NOT need both. `@RestController` alone is enough.

---

## Basic Example

```java
@RestController
public class StudentController {

    @GetMapping("/students")
    public String getStudents() {
        return "Hello from students";
    }

}
```

### What happens when GET /students is called:
1. Spring receives the HTTP request
2. Finds the matching `@GetMapping` method
3. Calls `getStudents()`
4. Returns the string as the HTTP response body

---

## Returning Objects (JSON)

Spring automatically converts Java objects to JSON using **Jackson**.

```java
@RestController
public class StudentController {

    @GetMapping("/students/1")
    public Student getStudent() {
        return new Student(1L, "Arjun", "CS");
        // Jackson converts this to:
        // { "id": 1, "name": "Arjun", "dept": "CS" }
    }

}
```

You don't write any JSON manually — Jackson handles it.

---

## @Controller vs @RestController

| @Controller | @RestController |
|-------------|-----------------|
| Returns view names (HTML pages) | Returns data (JSON) |
| Used in MVC / Thymeleaf apps | Used in REST APIs |
| Needs `@ResponseBody` on each method | Already includes `@ResponseBody` |

> For REST APIs, **always use `@RestController`**.

---

## One-Line Summary

> `@RestController` marks a class as an HTTP request handler that returns JSON, not HTML.

---

**Prev →** [[01-What-is-REST|What is REST]]
**Next →** [[03-Request-Mappings|Request Mappings]]
