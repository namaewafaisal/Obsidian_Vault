# @Transactional
**Phase:** [[README|Phase 6 — Service Layer]]
**Back:** [[../🏠 Home]]

---

## What It Does

Wraps a method in a **database transaction**.

If anything fails → the entire operation is **rolled back**.
If everything succeeds → all changes are **committed**.

---

## Example

```java
@Transactional
public void transferStudent(Long fromClassId, Long toClassId) {
    classRepo.removeStudent(fromClassId);   // step 1
    classRepo.addStudent(toClassId);         // step 2
    // if step 2 throws exception → step 1 is rolled back automatically
}
```

Without `@Transactional`:
- Step 1 removes student
- Step 2 fails
- Student is now in neither class — **data corruption**

---

## Where to Put It

```
✅ On @Service methods that do multiple DB writes
❌ NOT on @Controller methods
❌ NOT on @Repository methods (Spring Data handles transactions automatically)
```

---

## Read-Only Optimization

```java
@Transactional(readOnly = true)
public List<StudentResponse> getAllStudents() { ... }
```

Tells Spring: no writes expected.
Hibernate can skip dirty checking → **performance boost**.

---

## Rollback Behaviour

By default, `@Transactional` only rolls back on **unchecked exceptions** (`RuntimeException`).

```java
// Rolls back automatically
throw new RuntimeException("Something went wrong");

// Does NOT roll back by default
throw new Exception("Checked exception");

// Force rollback on checked exception
@Transactional(rollbackFor = Exception.class)
```

---

## One-Line Summary

> `@Transactional` = if anything fails inside this method, undo all DB changes made in it.

---

**Prev →** [[02-Service-Annotation|@Service]]
**Next Phase →** [[../Phase-7-Database-Config/README|Phase 7 — Database Config]]
