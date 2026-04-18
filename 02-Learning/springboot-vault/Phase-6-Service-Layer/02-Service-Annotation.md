# @Service
**Phase:** [[README|Phase 6 — Service Layer]]
**Back:** [[../🏠 Home]]

---

## What It Does

Marks a class as a **service layer component**.
Spring creates and manages it as a bean.

---

## Example

```java
@Service
public class StudentService {

    private final StudentRepository studentRepo;

    // Constructor injection — always preferred
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepo.findAll()
            .stream()
            .map(s -> new StudentResponse(s.getId(), s.getName()))
            .collect(Collectors.toList());
    }

    public StudentResponse createStudent(StudentRequest req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        Student saved = studentRepo.save(student);
        return new StudentResponse(saved.getId(), saved.getName());
    }

    public void deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepo.deleteById(id);
    }

}
```

---

## What Service Layer Does (and Doesn't)

| Does | Doesn't |
|------|---------|
| Business logic | HTTP handling |
| Entity ↔ DTO mapping | Forming HTTP responses |
| Calling repository | Direct DB queries |
| Throwing custom exceptions | Catching exceptions (that's @ControllerAdvice) |

---

**Prev →** [[01-Three-Layer-Architecture|3-Layer Architecture]]
**Next →** [[03-Transactional|@Transactional]]
