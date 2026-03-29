---
topic: JPA Annotations Basics
date-time: INVALID_DATETIME
time-taken(min): INVALID_TIME
---

# JPA Annotations (Basics)

## @Entity
Marks a class as a database table.

- Required for Hibernate to track the class
- Without it → no table created

---

## @Table(name = "users")
Specifies table name.

- Used to avoid default naming
- Important when:
  - default name is wrong
  - conflicts with reserved keywords (e.g., `user` in PostgreSQL)

---

## @Id
Marks primary key.

- Every entity must have one
- Used to uniquely identify rows

---

## @GeneratedValue(strategy = GenerationType.UUID)
Auto-generates ID.

- Uses UUID instead of auto-increment
- Why:
  - avoids collisions
  - better for distributed systems

---

## @Column
Defines column constraints.

Example:
```java
@Column(unique = true, nullable = false)
```

* `unique` → no duplicates
* `nullable = false` → cannot be null

Why use:

* Enforces rules at DB level (not just Java)

---

## @Enumerated(EnumType.STRING)

Controls how enums are stored.

* `STRING` → stores text (SAFE)
* `ORDINAL` → stores numbers (DANGEROUS)

Why STRING:

* readable
* safe if enum order changes

---

## @PrePersist

Lifecycle hook.

* Runs before INSERT
* Used for:

  * timestamps
  * default values

Example:

```java
@PrePersist
void prePersist() {
    this.createdAt = now();
}
```

---

## Field Types Mapping

| Java Type     | PostgreSQL Type |
| ------------- | --------------- |
| UUID          | uuid            |
| String        | text/varchar    |
| boolean       | boolean         |
| LocalDateTime | timestamp       |

---

## Lombok

### @Data

* Generates getters/setters
* Also adds toString, equals, hashCode

⚠️ Not ideal for entities (can cause issues later)

---

### @NoArgsConstructor

* Required by JPA
* Hibernate needs a default constructor

---

## Key Takeaways

* Annotations define how Java ↔ DB mapping works
* Always enforce constraints at DB level
* Prefer UUID + STRING enums for safety
* Lifecycle hooks reduce manual logic

