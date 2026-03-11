# @ControllerAdvice & @ExceptionHandler
**Phase:** [[README|Phase 8 — Exception Handling]]
**Back:** [[🏠 Home]]

---

## The Problem Without Global Error Handling

Without it, Spring returns a raw 500 error with a stack trace when something goes wrong.
The client gets nothing useful. Your internals are exposed.

---

## The Solution: @ControllerAdvice

`@ControllerAdvice` is a **global interceptor** that catches exceptions thrown from any controller.

```
Any Controller throws Exception
             ↓
    @ControllerAdvice catches it
             ↓
    @ExceptionHandler formats it
             ↓
    Clean JSON ErrorResponse → Client
```

---

## Step 1 — Custom Exception

```java
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student not found with id: " + id);
    }
}
```

---

## Step 2 — ErrorResponse DTO

```java
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
```

---

## Step 3 — Global Exception Handler

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles StudentNotFoundException specifically
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(StudentNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Handles validation errors (@Valid fails)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
            errors.put(e.getField(), e.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    // Catch-all — handles any other exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(500, "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
```

---

## What the Client Receives

For a `StudentNotFoundException`:
```json
{
  "status": 404,
  "message": "Student not found with id: 99",
  "timestamp": "2025-01-01T10:30:00"
}
```

For a validation error:
```json
{
  "name": "Name is required",
  "email": "Email must be valid"
}
```

---

## Rules

- `@ControllerAdvice` applies to **all controllers** in the app
- Order matters: more specific handlers (`StudentNotFoundException`) before generic (`Exception.class`)
- Never expose raw stack traces to the client
- Always use your `ErrorResponse` DTO — never a plain string

---

## One-Line Summary

> `@ControllerAdvice` = global try-catch for all controllers. `@ExceptionHandler` = what to do when a specific exception is thrown.

---

**Next Phase →** [[../Phase-9-Validation/README|Phase 9 — Validation]]
