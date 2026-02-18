# JPA Relationship Annotations – CP Analytics Project Notes

---

# 1️⃣ @Entity

## What It Does
Marks a class as a JPA entity.
This means the class will be mapped to a database table.

## Example
```java
@Entity
public class Student { }
```

## What Happens
- Table will be created (if ddl-auto=update)
- Fields become columns
- Class becomes persistent

## Use Case
Every table in relational DB must have a corresponding @Entity class.

---

# 2️⃣ @Table

## What It Does
Defines table-level configuration.

## Example
```java
@Table(
    name = "students",
    indexes = {
        @Index(name = "idx_dept", columnList = "dept")
    }
)
```

## Why Use It
- Custom table name
- Add indexes
- Add unique constraints

## Use Case
Used when you need:
- Performance optimization
- Unique combinations
- Explicit naming

---

# 3️⃣ @Id

## What It Does
Marks primary key.

```java
@Id
private Long id;
```

Without @Id → entity invalid.

Every entity must have one primary key.

---

# 4️⃣ @GeneratedValue

## What It Does
Auto-generates primary key.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

## IDENTITY Strategy
Database auto-increments ID.

Use case:
Most common for MySQL/MariaDB.

---

# 5️⃣ @Column

## What It Does
Configures column properties.

Example:
```java
@Column(nullable = false, unique = true)
```

## Properties

nullable = false → NOT NULL constraint  
unique = true → UNIQUE constraint  

Use case:
Enforce database-level validation.

---

# 6️⃣ @Index

## What It Does
Creates DB index for faster search.

```java
@Index(name = "idx_dept", columnList = "dept")
```

## Why Important
Without index:
Database scans entire table.

With index:
Database jumps directly to matching rows.

## Use Case in Your Project
- Filtering by dept
- Sorting by rating
- Filtering by lastSubmission

---

# 7️⃣ @OneToMany

## What It Means
One entity has many related entities.

Example:
```java
@OneToMany(mappedBy = "student")
private List<StudentPlatformProfile> platformProfiles;
```

## Meaning
One Student → Many Platform Profiles.

## Important
`mappedBy` tells JPA:
Other side owns the relationship.

This side does NOT create foreign key.

---

# 8️⃣ @ManyToOne

## What It Means
Many entities belong to one parent.

Example:
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_id")
private Student student;
```

## Meaning
Many profiles → One student.

This side creates the foreign key.

---

# 9️⃣ @JoinColumn

## What It Does
Defines foreign key column.

```java
@JoinColumn(name = "student_id", nullable = false)
```

## What Happens
Creates column:
student_id

Acts as foreign key.

---

# 🔟 FetchType.LAZY

## What It Means
Do NOT load related entity automatically.

Example:
```java
@ManyToOne(fetch = FetchType.LAZY)
```

## Why Important
If not LAZY:
Loading one student loads all profiles automatically.

This causes:
- Performance issues
- N+1 query problem

Always use LAZY unless required.

---

# 1️⃣1️⃣ @UniqueConstraint

## What It Does
Prevents duplicate combinations.

Example:
```java
@UniqueConstraint(
    columnNames = {"student_id", "platform_id"}
)
```

## Meaning
One student cannot have two CodeChef entries.

Use case:
Ensures data integrity.

---

# 1️⃣2️⃣ CascadeType.ALL

## What It Does
Operations propagate to child.

Example:
```java
@OneToMany(cascade = CascadeType.ALL)
```

If you:
- Save student → profiles also saved.
- Delete student → profiles deleted.

Use carefully.

---

# 1️⃣3️⃣ orphanRemoval = true

## What It Does
If child removed from list → delete from DB.

Use case:
Maintain clean relational integrity.

---

# Relationship Structure in This Project

Student
  ↓ OneToMany
StudentPlatformProfile
  ↑ ManyToOne
Platform

This is how we solve many-to-many with extra fields.

---

# Why We Use Separate StudentPlatformProfile

Because we need:

- rating
- totalSolved
- lastSubmission
- updatedAt

If it was simple many-to-many without extra data:
We could use @ManyToMany.

But here we need additional columns.

So we create join entity manually.

---

# Engineering Summary

Student → Core identity table  
Platform → Static reference table  
StudentPlatformProfile → Analytics + join table  

This is normalized relational design.

---

# Important Backend Rule

Never expose entities directly in controller.
Use DTOs later.

Entities = Database model.
DTO = API model.

---

# Final Mental Model

Think of:

StudentPlatformProfile as:
"Student's identity on a platform + current analytics snapshot"

That is the heart of your system.
