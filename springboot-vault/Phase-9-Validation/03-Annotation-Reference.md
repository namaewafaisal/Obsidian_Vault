# Validation
**Phase:** [[README|Phase 9 — Validation]]
**Back:** [[🏠 Home]]

---

## Why Validation Exists

> You cannot trust client input. Validate before it reaches your service or database.

Without validation:
- `null` names get saved to DB
- Invalid emails pass through
- Negative ages become valid

---

## Step 1 — Add Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## Step 2 — Annotate Your Request DTO

```java
@Data
public class StudentRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 100, message = "Age must be under 100")
    private int age;

    @NotBlank(message = "Department is required")
    private String dept;

}
```

---

## Step 3 — Trigger Validation in Controller

```java
@PostMapping
public ResponseEntity<StudentResponse> create(
        @Valid @RequestBody StudentRequest req) {
    //   ↑ @Valid triggers all annotation checks on req
    StudentResponse created = service.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}
```

---

## Step 4 — Handle Validation Errors in @ControllerAdvice

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidation(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return ResponseEntity.badRequest().body(errors);
}
```

Client receives:
```json
{
  "name": "Name is required",
  "email": "Email must be valid"
}
```

---

## Annotation Reference

| Annotation | What It Checks |
|-----------|---------------|
| `@NotNull` | Field is not null |
| `@NotBlank` | String is not null or empty (use for Strings) |
| `@NotEmpty` | String/collection is not empty |
| `@Email` | Valid email format |
| `@Min(n)` | Number >= n |
| `@Max(n)` | Number <= n |
| `@Size(min, max)` | String/collection length in range |
| `@Pattern(regexp)` | Matches regex |
| `@Positive` | Number > 0 |
| `@PositiveOrZero` | Number >= 0 |
| `@Past` | Date is in the past |
| `@Future` | Date is in the future |

---

## @NotNull vs @NotBlank

```java
@NotNull    // passes for "" (empty string) — only checks for null
@NotBlank   // fails for null, "", "   " — use this for Strings
```

> Always use `@NotBlank` for `String` fields, not `@NotNull`.

---

## One-Line Summary

> Add annotations to DTO → use `@Valid` in controller → Spring throws `MethodArgumentNotValidException` → your `@ControllerAdvice` catches and formats it.

---

**Prev Phase →** [[../Phase-8-Exception-Handling/README|Phase 8 — Exception Handling]]
**Next Phase →** [[../Phase-10-Security/README|Phase 10 — Security]]
