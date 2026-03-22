# Securin Technical Assessment — Complete Prep Guide
> Java 21 · Spring Boot · MariaDB · Jackson · Lombok  
> Assessment 3 (CVE project) is your reference — everything here is built on top of what you already did.

---

## 0. What to expect tomorrow

You get a spec. It will likely say:
- Here is a CSV / JSON / XML file (or an external API URL)
- Store it in a database
- Expose these endpoints with filters, pagination, sorting
- Build a simple UI

You have 3–4 hours. **First endpoint returning real data = survival.** Everything else is bonus.

---

## 1. Project setup from scratch — do this in the first 5 minutes

### Spring Initializr (start.spring.io)
```
Project:   Maven
Language:  Java
Boot:      3.3.x
Java:      21
Packaging: Jar

Dependencies to add:
- Spring Web
- Spring Data JPA
- MySQL Driver  (use this for MariaDB too)
- Lombok
```

Download, unzip, open in IntelliJ.

---

### pom.xml — only add Jackson if not already there
Spring Boot includes Jackson by default via `spring-boot-starter-web`. You only need to add it manually if you're using `tools.jackson` (the newer API). For standard usage, nothing extra needed.

For XML parsing add this one dependency:
```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

For CSV parsing:
```xml
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.9</version>
</dependency>
```

---

### application.properties — fill this in first, don't forget it
```properties
spring.application.name=assessment

# Database
spring.datasource.url=jdbc:mariadb://localhost:3306/assessmentdb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect

# Allow large file uploads if needed
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```

> `ddl-auto=update` means Hibernate will create/update tables from your entity class automatically. You don't need to write SQL.

Create the database manually once:
```sql
CREATE DATABASE assessmentdb;
```

---

## 2. Entity class — the skeleton of everything

This maps directly to a DB table. Get this right first.

```java
package com.example.assessment;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data               // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // generates no-arg constructor (required by JPA)
@AllArgsConstructor // generates constructor with all fields
@Entity
@Table(name = "records")
public class Record {

    @Id
    private String id;           // use String if IDs come from the data (like CVE-2024-xxxx)

    private String name;
    private String description;
    private Double score;        // Double (not double) so it can be null
    private String status;
    private LocalDateTime publishedAt;
    private LocalDateTime lastModified;
}
```

**Rules:**
- `@Entity` = this class is a DB table
- `@Table(name = "records")` = table name (optional, defaults to class name)
- `@Id` = primary key
- Use wrapper types (`Double`, `Integer`) not primitives (`double`, `int`) for nullable fields
- `LocalDateTime` for dates — JPA handles the conversion automatically

---

## 3. Repository — almost nothing to write

```java
package com.example.assessment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface RecordRepo extends JpaRepository<Record, String> {

    // Pagination — built in, just pass Pageable
    Page<Record> findAll(Pageable pageable);

    // Filter by a field — Spring generates the SQL automatically
    Page<Record> findByStatus(String status, Pageable pageable);

    // Filter by score
    Page<Record> findByScoreGreaterThanEqual(Double score, Pageable pageable);

    // Custom query with JPQL
    @Query("SELECT r FROM Record r WHERE YEAR(r.publishedAt) = :year")
    Page<Record> findByYear(@Param("year") int year, Pageable pageable);

    // Modified since N days
    @Query("SELECT r FROM Record r WHERE r.lastModified >= :cutoff")
    Page<Record> findModifiedSince(@Param("cutoff") LocalDateTime cutoff, Pageable pageable);
}
```

`JpaRepository<Record, String>` — first type is your entity, second is your ID type.  
`findBy[FieldName][Condition]` — Spring Data generates the SQL from the method name. No SQL needed.

---

## 4. Service — business logic lives here

```java
package com.example.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RecordService {

    @Autowired
    private RecordRepo repo;

    // Build pageable — reuse this pattern everywhere
    private Pageable pageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    public Page<Record> getAll(int page, int size) {
        return repo.findAll(pageable(page, size));
    }

    public Record getById(String id) {
        return repo.findById(id).orElse(null);
    }

    public Page<Record> getByStatus(String status, int page, int size) {
        return repo.findByStatus(status, pageable(page, size));
    }

    public Page<Record> getByScore(Double score, int page, int size) {
        return repo.findByScoreGreaterThanEqual(score, pageable(page, size));
    }

    public Page<Record> getByYear(int year, int page, int size) {
        return repo.findByYear(year, pageable(page, size));
    }

    public Page<Record> getModifiedSince(int days, int page, int size) {
        return repo.findModifiedSince(LocalDateTime.now().minusDays(days), pageable(page, size));
    }
}
```

---

## 5. Controller — expose the endpoints

```java
package com.example.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService service;

    // GET /records?page=0&size=10
    @GetMapping
    public ResponseEntity<Page<Record>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    // GET /records/CVE-2024-1234
    @GetMapping("/{id}")
    public ResponseEntity<Record> getById(@PathVariable String id) {
        Record r = service.getById(id);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(r);
    }

    // GET /records/year?year=2024&page=0&size=10
    @GetMapping("/year")
    public ResponseEntity<Page<Record>> getByYear(
            @RequestParam int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getByYear(year, page, size));
    }

    // GET /records/score?score=7.5&page=0&size=10
    @GetMapping("/score")
    public ResponseEntity<Page<Record>> getByScore(
            @RequestParam Double score,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getByScore(score, page, size));
    }

    // GET /records/modified?days=7&page=0&size=10
    @GetMapping("/modified")
    public ResponseEntity<Page<Record>> getModified(
            @RequestParam int days,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getModifiedSince(days, page, size));
    }

    // POST /records/fetch — triggers data load
    @PostMapping("/fetch")
    public ResponseEntity<String> fetch() {
        try {
            service.fetchAndStore();
            return ResponseEntity.ok("Done");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed: " + e.getMessage());
        }
    }
}
```

---

## 6. Parsing data — the three formats

### JSON from external API (like NVD)

```java
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

RestTemplate restTemplate = new RestTemplate();
ObjectMapper mapper = new ObjectMapper();

String url = "https://api.example.com/data";
String response = restTemplate.getForObject(url, String.class);
JsonNode root = mapper.readTree(response);

// Navigate the JSON tree
JsonNode items = root.get("items");              // array
JsonNode first = items.get(0);                   // first element
String id = first.get("id").asText();            // string field, safe (returns "" if null)
Double score = first.get("score").asDouble();    // double field, safe (returns 0.0 if null)
int count = root.get("totalResults").asInt();    // int field

// Null check before accessing nested nodes
JsonNode metrics = first.get("metrics");
if (metrics != null) {
    JsonNode v3 = metrics.get("cvssMetricV31");
    if (v3 != null && v3.size() > 0) {
        double baseScore = v3.get(0).get("cvssData").get("baseScore").asDouble();
    }
}
```

**Key rule:** `.get("field")` returns null if field doesn't exist.  
Always null-check before calling `.get()` on a nested node.  
`.asText()`, `.asInt()`, `.asDouble()` never throw — they return empty/0 on null.

---

### JSON from uploaded file / local file

```java
import java.io.File;
ObjectMapper mapper = new ObjectMapper();
JsonNode root = mapper.readTree(new File("data.json"));
// same navigation as above
```

---

### CSV parsing with OpenCSV

```java
import com.opencsv.CSVReader;
import java.io.FileReader;

try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
    String[] headers = reader.readNext(); // first line = headers
    String[] line;
    while ((line = reader.readNext()) != null) {
        Record r = new Record();
        r.setId(line[0]);
        r.setName(line[1]);
        r.setScore(line[2].isEmpty() ? null : Double.parseDouble(line[2]));
        repo.save(r);
    }
}
```

**Key rule:** CSV fields are always Strings. Parse manually. Guard empty strings before `Double.parseDouble()` or it throws `NumberFormatException`.

---

### XML parsing with Jackson XML

```java
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

XmlMapper xmlMapper = new XmlMapper();
JsonNode root = xmlMapper.readTree(new File("data.xml"));
// Navigate exactly like JSON — same API
String id = root.get("id").asText();
```

Or map directly to a class:
```java
MyClass obj = xmlMapper.readValue(new File("data.xml"), MyClass.class);
```

---

### Reading an uploaded file from the request

```java
@PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
    // For JSON
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(file.getInputStream());

    // For CSV
    CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));

    // For XML
    XmlMapper xmlMapper = new XmlMapper();
    JsonNode root = xmlMapper.readTree(file.getInputStream());

    return ResponseEntity.ok("Processed");
}
```

---

## 7. Pagination — what the response looks like

When you return `Page<Record>`, Spring automatically gives:

```json
{
  "content": [ ...20 records... ],
  "totalElements": 2000,
  "totalPages": 100,
  "number": 0,
  "size": 20,
  "numberOfElements": 20,
  "first": true,
  "last": false
}
```

- `content` — the actual data for this page
- `totalElements` — total records in DB matching the query
- `totalPages` — how many pages exist
- `number` — current page (0-indexed)

---

## 8. Frontend — drop into src/main/resources/static/

Spring Boot serves anything in this folder at `/`.  
`index.html` → `http://localhost:8080/`

### Minimal working table with pagination

**index.html**
```html
<!DOCTYPE html>
<html>
<head>
  <title>Assessment</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <h1>Records</h1>

  <div>
    <label>Per page:
      <select id="sizeSelect">
        <option value="10">10</option>
        <option value="50">50</option>
        <option value="100">100</option>
      </select>
    </label>
    <span>Total: <strong id="total">—</strong></span>
  </div>

  <table id="table" border="1">
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Score</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody id="tbody"></tbody>
  </table>

  <div id="pagination"></div>

  <script src="app.js"></script>
</body>
</html>
```

**app.js**
```javascript
let page = 0;
let size = 10;

async function load(p) {
  page = p;
  const res = await fetch(`/records?page=${page}&size=${size}`);
  const data = await res.json();

  document.getElementById('total').textContent = data.totalElements;

  const tbody = document.getElementById('tbody');
  tbody.innerHTML = data.content.map(r => `
    <tr onclick="location.href='/detail.html?id=${r.id}'" style="cursor:pointer">
      <td>${r.id}</td>
      <td>${r.name ?? '—'}</td>
      <td>${r.score ?? '—'}</td>
      <td>${r.status ?? '—'}</td>
    </tr>
  `).join('');

  renderPagination(data.number, data.totalPages);
}

function renderPagination(current, total) {
  let html = `<button onclick="load(${current - 1})" ${current === 0 ? 'disabled' : ''}>Prev</button>`;
  html += ` Page ${current + 1} of ${total} `;
  html += `<button onclick="load(${current + 1})" ${current === total - 1 ? 'disabled' : ''}>Next</button>`;
  document.getElementById('pagination').innerHTML = html;
}

document.getElementById('sizeSelect').addEventListener('change', e => {
  size = parseInt(e.target.value);
  load(0);
});

load(0);
```

**detail.html**
```html
<!DOCTYPE html>
<html>
<head><title>Detail</title></head>
<body>
  <a href="/">← Back</a>
  <div id="content">Loading...</div>
  <script>
    const id = new URLSearchParams(location.search).get('id');
    fetch(`/records/${id}`)
      .then(r => r.json())
      .then(data => {
        document.getElementById('content').innerHTML = `
          <h2>${data.id}</h2>
          <p>Name: ${data.name}</p>
          <p>Score: ${data.score}</p>
          <p>Status: ${data.status}</p>
        `;
      });
  </script>
</body>
</html>
```

---

## 9. Explaining decisions — what to say

They will ask "why did you do X". Here are your answers:

**"Why `Page<T>` instead of `List<T>`?"**  
"Returning a List sends all records at once — that's thousands of rows. `Page<T>` lets the client request a specific page and size, which is faster and doesn't overload the client. It also gives `totalElements` and `totalPages` for free, which the frontend needs for pagination controls."

**"Why `ddl-auto=update`?"**  
"For development, it lets Hibernate create and update the schema automatically from the entity class, so I don't have to write DDL SQL. In production I'd switch to `validate` or use a migration tool like Flyway."

**"Why `RestTemplate` for the external API?"**  
"It's built into Spring, no extra dependency. For production I'd use `WebClient` which is non-blocking, but for a synchronous fetch task `RestTemplate` is simpler and sufficient."

**"Why null checks before `.get()` on JsonNode?"**  
"`.get()` returns null if the field doesn't exist in the JSON. Calling methods on null throws NullPointerException, which crashes the entire fetch loop. Fields like CVSS scores don't exist for every record, so they need explicit null guards."

**"Why `Double` not `double` for score?"**  
"Primitive `double` can't be null. Some records don't have a score. Using the wrapper type `Double` lets the field be null in both Java and the database, which is the correct representation."

**"Why `@RequestParam(defaultValue = "0") int page`?"**  
"Without a default, `@RequestParam` is required — if the caller doesn't send it, Spring returns 400 automatically. `defaultValue` makes it optional with a sensible fallback, so the caller doesn't have to send page/size every time."

**"What does `spring.jpa.hibernate.ddl-auto=update` do?"**  
"It tells Hibernate to compare the current entity classes against the existing DB schema and run ALTER/CREATE statements automatically on startup to bring the schema in sync."

---

## 10. Don't forget checklist

Before you start coding:
- [ ] Create the database in MariaDB: `CREATE DATABASE assessmentdb;`
- [ ] Fill `application.properties` — datasource URL, username, password, dialect
- [ ] Add dependencies to `pom.xml` if needed (CSV, XML)
- [ ] Run once to confirm the table is created before writing any logic

While coding:
- [ ] Entity first — get the fields right, run it, confirm table appears
- [ ] Repo next — just the interface, no code
- [ ] Service — `getAll`, `getById`, one filter
- [ ] Controller — wire it up, test with curl/Postman
- [ ] Fetch/parse logic — only after basic endpoints work
- [ ] Frontend — only after API is confirmed working

Common mistakes to avoid:
- Don't use `int`/`double` for nullable fields — use `Integer`/`Double`
- Don't forget `@Entity` and `@Id` on the entity
- Don't forget `CREATE DATABASE` before running
- Don't hardcode page size — make it a `@RequestParam` with a default
- Don't `System.exit()` or anything that kills the app if one record fails to parse — use `try/catch` inside the loop and skip bad records
- Don't return `null` from a controller — return `ResponseEntity.notFound().build()` for missing records

---

## 11. Quick reference — annotations

| Annotation | Where | What it does |
|---|---|---|
| `@Entity` | class | maps class to DB table |
| `@Table(name="x")` | class | sets table name |
| `@Id` | field | marks primary key |
| `@GeneratedValue` | field | auto-generate ID (use if ID is not from data) |
| `@Column(nullable=false)` | field | adds NOT NULL constraint |
| `@Data` | class | Lombok: generates getters/setters/etc |
| `@NoArgsConstructor` | class | Lombok: no-arg constructor (JPA needs this) |
| `@AllArgsConstructor` | class | Lombok: all-args constructor |
| `@Service` | class | Spring-managed service bean |
| `@RestController` | class | handles HTTP, returns JSON |
| `@RequestMapping("/x")` | class | base URL for all methods |
| `@GetMapping("/y")` | method | handles GET /x/y |
| `@PostMapping("/y")` | method | handles POST /x/y |
| `@PathVariable` | param | reads from URL path `/records/{id}` |
| `@RequestParam` | param | reads from query string `?page=0` |
| `@RequestBody` | param | reads JSON body |
| `@Autowired` | field | Spring injects the dependency |

---

## 12. The fastest path to a working first endpoint

Do exactly this, in this order:

1. Create project at start.spring.io (Web, JPA, MySQL Driver, Lombok)
2. Create DB: `CREATE DATABASE assessmentdb;`
3. Fill `application.properties`
4. Create entity with `@Entity`, `@Id`, 3-4 fields
5. Create repo interface extending `JpaRepository`
6. Create service with `getAll(int page, int size)` returning `Page<Entity>`
7. Create controller with `GET /records?page=0&size=10`
8. Run → hit `http://localhost:8080/records?page=0&size=10`
9. Should return `{"content":[],"totalElements":0,...}`
10. Now write the fetch/parse logic and call `repo.save()`
11. Re-hit the endpoint — data should appear

Everything else (more filters, frontend, detail page) comes after step 10 is working.
