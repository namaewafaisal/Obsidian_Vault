# JPA Relationship Annotations
**Phase:** [[README|Phase 5 — JPA & Hibernate]]
**Back:** [[🏠 Home]]

> These are your existing notes, reformatted for Obsidian.

---

## @Entity

Marks a class as a JPA entity — it maps to a database table.

```java
@Entity
public class Student { }
```

- Table is created (if `ddl-auto=update`)
- Fields become columns
- Class becomes persistent

---

## @Table

Defines table-level configuration.

```java
@Table(
    name = "students",
    indexes = {
        @Index(name = "idx_dept", columnList = "dept")
    },
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "platform_id"})
    }
)
```

Use when you need: custom table name, indexes, unique constraints.

---

## @Id

Marks the primary key. Every entity must have one.

```java
@Id
private Long id;
```

---

## @GeneratedValue

Auto-generates the primary key.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

`IDENTITY` → database auto-increments. Most common for MySQL/MariaDB.

---

## @Column

Configures column properties.

```java
@Column(nullable = false, unique = true)
private String email;
```

| Property | Meaning |
|----------|---------|
| `nullable = false` | NOT NULL constraint |
| `unique = true` | UNIQUE constraint |
| `name = "col_name"` | Custom column name |
| `length = 100` | VARCHAR length |

---

## @Index

Creates a DB index for faster queries.

```java
@Index(name = "idx_dept", columnList = "dept")
```

**Without index:** DB scans entire table.
**With index:** DB jumps directly to matching rows.

Use for columns you frequently filter or sort by.

---

## @OneToMany

One entity has many related entities.

```java
@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
private List<StudentPlatformProfile> platformProfiles;
```

- `mappedBy` → other side owns the relationship (this side doesn't create FK)
- `cascade = CascadeType.ALL` → save/delete student → also saves/deletes profiles
- `orphanRemoval = true` → remove from list → deletes from DB

---

## @ManyToOne

Many entities belong to one parent. **This side creates the foreign key.**

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_id", nullable = false)
private Student student;
```

---

## @JoinColumn

Defines the foreign key column.

```java
@JoinColumn(name = "student_id", nullable = false)
```

Creates column `student_id` as a foreign key.

---

## FetchType.LAZY

Do NOT automatically load the related entity.

```java
@ManyToOne(fetch = FetchType.LAZY)
```

**Why:** Without LAZY, loading one student loads ALL their profiles automatically → N+1 query problem.

> Always use LAZY unless you have a specific reason not to.

---

## @UniqueConstraint

Prevents duplicate combinations.

```java
@UniqueConstraint(columnNames = {"student_id", "platform_id"})
```

One student cannot have two CodeChef entries.

---

## CascadeType.ALL

Operations propagate to child entities.

```java
@OneToMany(cascade = CascadeType.ALL)
```

Save student → profiles also saved.
Delete student → profiles deleted.

---

## orphanRemoval = true

If a child is removed from the parent's list → it is deleted from the DB.

---

## Relationship Structure in This Project

```
Student
  ↓ @OneToMany
StudentPlatformProfile
  ↑ @ManyToOne
Platform
```

This is a **many-to-many with extra fields**, implemented as a join entity.

---

## Why Use a Join Entity Instead of @ManyToMany

Simple `@ManyToMany` only maps the relationship.
`StudentPlatformProfile` adds extra columns:
- `rating`
- `totalSolved`
- `lastSubmission`
- `updatedAt`

When you need extra data on the relationship → create the join entity manually.

---

## Engineering Summary

| Entity | Role |
|--------|------|
| `Student` | Core identity table |
| `Platform` | Static reference table |
| `StudentPlatformProfile` | Analytics + join table |

---

**Next →** [[02-Repository|@Repository & Spring Data JPA]]
