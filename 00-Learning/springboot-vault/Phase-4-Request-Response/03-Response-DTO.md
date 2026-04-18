# Response DTO
**Phase:** [[README|Phase 4 — Request & Response]]
**Back:** [[../🏠 Home]]

---

## What It Is

The Response DTO holds data that **your API sends back to the client**.

---

## Example

```java
// What the client receives after creating a student:
// { "id": 1, "name": "Arjun", "dept": "CS" }

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private String dept;
    // No password, no createdAt, no internal fields
}
```

---

## Mapping Entity → Response DTO

```java
// In your Service layer:
public StudentResponse toResponse(Student student) {
    StudentResponse dto = new StudentResponse();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setDept(student.getDept());
    return dto;
}
```

---

## Note on MapStruct (Later Topic)

Manually mapping entity → DTO is tedious for large classes.
**MapStruct** generates this code automatically — covered in a later phase.

---

**Prev →** [[02-Request-DTO|Request DTO]]
**Next →** [[04-ResponseEntity|ResponseEntity]]
