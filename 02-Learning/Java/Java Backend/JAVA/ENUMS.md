Below is a **clean, recall-focused Obsidian note** for **Enums in Java**.
This is sufficient for relearning and backend usage.

---

## Enums (Java)

### What is an Enum

An `enum` is a **special data type** used to define a **fixed set of constants**.

```java
enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}
```

Use when values are **known, limited, and constant**.

---

### Why enums exist

* Prevent **invalid values**
* Improve **readability**
* Provide **type safety**
* Replace:

  * `int` constants
  * `String` flags

---

### Enum usage

```java
Day today = Day.MON;
```

Switch support:

```java
switch (today) {
    case MON -> System.out.println("Work");
    case SUN -> System.out.println("Holiday");
}
```

---

### Enums are classes (important)

* Enums can have:

  * Fields
  * Methods
  * Constructors

```java
enum Status {
    SUCCESS(200), ERROR(500);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
```

---

### Enum methods you actually use

```java
Status.SUCCESS.name();      // "SUCCESS"
Status.SUCCESS.ordinal();  // 0 (avoid using)
Status.valueOf("SUCCESS"); // String → Enum
Status.values();           // all enum constants
```

⚠️ Avoid `ordinal()` (breaks if order changes).

---

### Enum in comparisons

* Use `==` (safe)

```java
if (status == Status.SUCCESS) { }
```

Enums are singletons.

---

### Enum with interfaces

Enums can implement interfaces.

```java
enum Operation implements Runnable {
    ADD {
        public void run() { }
    };
}
```

---

### Backend relevance (Spring)

* Use enums for:

  * User roles
  * Status fields
  * API response states
* Stored as:

  * `STRING` (recommended)
  * not `ORDINAL`

```java
@Enumerated(EnumType.STRING)
private Status status;
```

---

### One-line recall

> **Enum = type-safe constant + behavior**

---

Send the **next topic name only** when ready.
