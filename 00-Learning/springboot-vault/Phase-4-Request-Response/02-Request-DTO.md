# Request DTO
**Phase:** [[README|Phase 4 — Request & Response]]
**Back:** [[../🏠 Home]]

---

## What It Is

The Request DTO holds data that the **client sends to your API**.

---

## Example

```java
// What the client sends when creating a student:
// POST /students
// Body: { "name": "Arjun", "email": "arjun@example.com", "dept": "CS" }

public class StudentRequest {

    private String name;
    private String email;
    private String dept;

    // Getters and Setters (or use Lombok @Data)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // ... etc
}
```

---

## Using in Controller

```java
@PostMapping
public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest req) {
    StudentResponse created = service.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}
```

---

## Lombok Shortcut (saves boilerplate)

```java
import lombok.Data;

@Data   // generates getters, setters, equals, hashCode, toString
public class StudentRequest {
    private String name;
    private String email;
    private String dept;
}
```

Add to pom.xml:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

---

**Prev →** [[01-Why-DTOs|Why DTOs]]
**Next →** [[03-Response-DTO|Response DTO]]
