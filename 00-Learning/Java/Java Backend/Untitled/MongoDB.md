These notes are designed for a **backend developer** (like your Maven notes) to get you from "I've used it" to "I can architect it."
Good catch. You’re right — **the absolute MongoDB shell basics were missing**.
Below is the **FOUNDATION layer** that sits **before** everything you already have.

This completes MongoDB **from zero → architect level**, cleanly.

---

# MongoDB — Shell Basics (Foundation Layer)

These are the **first commands every backend developer must know**.
All are done in **`mongosh`**.

---

## 0. Start Mongo Shell

```bash
mongosh
```

Connects to the local MongoDB instance (or Atlas if configured).

---

## 1. Show Databases

```js
show dbs
```

* Lists all databases
* A database appears **only after it has data**

---

## 2. Switch / Create Database

```js
use myapp
```

Important:

* If `myapp` exists → switches to it
* If it doesn’t → MongoDB **creates it lazily** (on first insert)

Check current DB:

```js
db
```

---

## 3. Show Collections

```js
show collections
```

Lists all collections in the current database.

---

## 4. Create Collection (Explicit)

```js
db.createCollection("users")
```

Normally you **don’t need this**, because Mongo creates collections automatically on insert.

Used when:

* Adding schema validation
* Creating capped collections

---

## 5. Insert First Document (Implicit Creation)

```js
db.users.insertOne({ name: "Alice", age: 25 })
```

What happens:

* Database is created (if missing)
* Collection is created (if missing)
* Document is inserted
* `_id` is auto-generated

This is the **MongoDB creation flow**.

---

## 6. Show All Documents

```js
db.users.find()
```

Pretty print:

```js
db.users.find().pretty()
```

---

## 7. Drop Collection

```js
db.users.drop()
```

Deletes:

* Collection
* All documents inside it

---

## 8. Drop Database

```js
db.dropDatabase()
```

Deletes:

* Entire database
* All collections

⚠️ Irreversible.

---

## 9. Count Documents

```js
db.users.countDocuments()
```

Preferred over deprecated `count()`.

---

## 10. Rename Collection

```js
db.users.renameCollection("customers")
```

---

## 11. Check Indexes

```js
db.users.getIndexes()
```

Default:

```js
{ _id: 1 }
```

---

## 12. Remove All Documents (Keep Collection)

```js
db.users.deleteMany({})
```

Useful during development.

---

## Mental Model (Important)

MongoDB creation is **data-driven**, not schema-driven.

```
use db
→ insert document
→ Mongo creates everything automatically
```

No DDL ceremony like SQL.

---

## Where This Fits in Your Notes

Your MongoDB notes now have **three complete layers**:

1. **Shell Basics** ← (this section)
2. **Core Developer MongoDB** ← (CRUD, queries, aggregation, indexing)
3. **Completion Layer** ← (transactions, validation, performance, Spring)

This is **complete backend coverage**.

---

## One-Line Summary (Remember This)

> **If you can navigate mongosh, CRUD data, aggregate it, and integrate it with Spring Boot — you fully know MongoDB as a backend developer.**

Now your notes are truly **end-to-end**.

---

# MongoDB — Comprehensive Backend Notes

## 1. Core Architecture (The Mental Model)

In RDBMS (MySQL/Postgres), you have **Tables → Rows → Columns**.
In MongoDB, you have **Databases → Collections → Documents**.

* **Document-Oriented:** Data is stored in **BSON** (Binary JSON).
* **Schema-less:** One document in a collection can have 5 fields, and the next can have 50.
* **The `_id` Field:** Every document **must** have a unique `_id` (usually an `ObjectId`).

---

## 2. Essential CRUD Commands

You’ll mostly use these in your terminal (`mongosh`) or through a driver.

| Action | Command | Example |
| --- | --- | --- |
| **Create** | `insertOne` / `insertMany` | `db.users.insertOne({ name: "Alice", age: 25 })` |
| **Read** | `find(query, projection)` | `db.users.find({ age: { $gt: 20 } }, { name: 1 })` |
| **Update** | `updateOne` / `updateMany` | `db.users.updateOne({ name: "Alice" }, { $set: { age: 26 } })` |
| **Delete** | `deleteOne` / `deleteMany` | `db.users.deleteMany({ status: "inactive" })` |

> **Pro Tip:** In updates, **always** use `$set`. If you omit it, MongoDB will **replace** the entire document with your new object.

---

## 3. Query Operators (The "Logic" of Mongo)

Unlike SQL's `WHERE age > 20`, Mongo uses operator objects:

* **Comparison:** `$gt` (>), `$lt` (<), `$gte` (>=), `$lte` (<=), `$ne` (!=), `$in` (in array).
* **Logical:** `$and`, `$or`, `$not`, `$nor`.
* **Element:** `$exists: true` (finds docs where a specific field is present).

---

## 4. The Aggregation Framework (The Powerhouse)

If `find()` is a simple SELECT, **Aggregation** is your `GROUP BY`, `JOIN`, and data processing pipeline.

**Common Pipeline Stages:**

1. `$match`: Filter documents (like a WHERE clause).
2. `$group`: Group by a field and calculate totals/averages.
3. `$sort`: Order the results (1 for Asc, -1 for Desc).
4. `$project`: Choose which fields to pass to the next stage.
5. `$lookup`: Perform a **Left Outer Join** with another collection.
6. `$unwind`: Flatten an array (creates a document for each element in the array).

---

## 5. Indexing (For Performance)

Without indexes, Mongo does a **Collection Scan** (reads every single document).

* **Single Field:** `db.users.createIndex({ email: 1 })`
* **Compound Index:** `db.orders.createIndex({ userId: 1, date: -1 })`
* **TTL Index:** Automatically deletes documents after a certain time (great for logs/sessions).
* **ESR Rule for Compound Indexes:** Follow the order: **E**quality, then **S**ort, then **R**ange.

---

## 6. MongoDB in Spring Boot (Critical)

As a Java developer, you rarely write raw BSON. You use **Spring Data MongoDB**.

### Key Annotations

* `@Document`: Marks a class as a MongoDB collection.
* `@Id`: Maps the Java field to the `_id` in Mongo.
* `@Indexed`: Tells Spring to create an index on startup (good for dev, risky for prod).
* `@DBRef`: References another document (Avoid this! Use manual IDs for performance).

### Repository vs MongoTemplate

* **MongoRepository:** High-level abstraction (e.g., `findByEmail(String email)`). Great for 90% of tasks.
* **MongoTemplate:** Low-level control. Use this when you need complex **Aggregations** or dynamic queries that repositories can't handle.

---

## 7. Data Modeling: Embed vs. Reference

This is the #1 question in MongoDB design.

* **Embedding (Denormalization):** Put everything in one document.
* *Best for:* One-to-Few relationships (e.g., a User and their 3 Addresses).
* *Benefit:* Fast reads (1 query gets everything).


* **Referencing (Normalization):** Store an ID of the other document.
* *Best for:* One-to-Many or Many-to-Many (e.g., a Post and its 10,000 Comments).
* *Benefit:* Avoids the 16MB document size limit.



---

## 8. Real-World Commands You’ll Need

* `db.collection.explain("executionStats")`: Shows if your query is using an index or scanning the whole DB.
* `db.collection.stats()`: Shows storage size and index sizes.
* `db.getProfilingLevel()`: Used to find slow queries in production.

---

### One-Line Summary

> MongoDB is a **flexible, document-based NoSQL database** that trades rigid relations for **horizontal scale** and **fast development cycles**.

**Would you like me to show you a practical Spring Boot `MongoRepository` example vs. an `Aggregation` pipeline?**


Good question. I’ll do this **properly and cleanly**, without bloating your notes.

Below is **the remaining MongoDB backend-relevant material**, **with syntax**, and then a **clear answer about mongosh**.

---

# MongoDB — Remaining Backend Notes (Completion Layer)

These are **add-ons** to what you already have.
They **complete developer-level MongoDB**, not ops/admin level.

---

## 9. Transactions (When You Need ACID)

MongoDB supports **multi-document transactions** (replica set required).

### When to use

* Money transfer
* Inventory updates
* Multi-collection consistency

### Syntax (mongosh)

```js
const session = db.getMongo().startSession();

session.startTransaction();

try {
  db.orders.insertOne({ item: "book", qty: 1 }, { session });
  db.inventory.updateOne(
    { item: "book" },
    { $inc: { stock: -1 } },
    { session }
  );

  session.commitTransaction();
} catch (e) {
  session.abortTransaction();
}
```

### Backend rule

> Mongo prefers **single-document atomicity**.
> Use transactions **only when unavoidable**.

---

## 10. Write Concern & Read Concern (Consistency Control)

These control **data safety vs speed**.

### Write Concern

```js
db.orders.insertOne(
  { item: "pen" },
  { writeConcern: { w: "majority" } }
);
```

* `w: 1` → primary only (fast)
* `w: "majority"` → replicated (safe)

---

### Read Concern

```js
db.orders.find().readConcern("majority");
```

Controls how “stable” data must be before reading.

---

## 11. Schema Validation (Important for Backend Design)

Mongo is schema-less, **not schema-free**.

### JSON Schema Validation

```js
db.createCollection("users", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["email", "age"],
      properties: {
        email: { bsonType: "string" },
        age: { bsonType: "int", minimum: 18 }
      }
    }
  }
});
```

### Why backend devs need this

* Prevents garbage data
* Replaces SQL constraints partially
* Enforces API contracts

---

## 12. Bulk Operations (Performance)

Used when inserting/updating many documents.

```js
db.users.bulkWrite([
  { insertOne: { document: { name: "A" } } },
  { updateOne: { filter: { name: "B" }, update: { $set: { age: 30 } } } },
  { deleteOne: { filter: { inactive: true } } }
]);
```

Used in:

* Migrations
* Batch jobs
* Sync operations

---

## 13. Advanced Aggregation (Missing but Useful)

### `$facet` – multiple pipelines

```js
db.orders.aggregate([
  {
    $facet: {
      totalSales: [{ $group: { _id: null, sum: { $sum: "$amount" } } }],
      byUser: [{ $group: { _id: "$userId", sum: { $sum: "$amount" } } }]
    }
  }
]);
```

---

### `$merge` – write aggregation result

```js
db.sales.aggregate([
  { $group: { _id: "$item", total: { $sum: "$amount" } } },
  { $merge: "sales_summary" }
]);
```

Used for:

* Analytics
* Materialized views

---

## 14. TTL Index (With Full Syntax)

```js
db.sessions.createIndex(
  { createdAt: 1 },
  { expireAfterSeconds: 3600 }
);
```

MongoDB automatically deletes expired documents.

Perfect for:

* Sessions
* OTPs
* Logs

---

## 15. MongoDB + Spring Boot (Advanced Awareness)

### Native Queries

```java
@Query("{ age: { $gt: ?0 } }")
List<User> findOlderThan(int age);
```

---

### Aggregation in Spring

```java
Aggregation agg = Aggregation.newAggregation(
    match(Criteria.where("age").gt(20)),
    group("city").count().as("count")
);

mongoTemplate.aggregate(agg, "users", Result.class);
```

---

## 16. Production Awareness (No Deep Dive)

You should **know these exist**, not master them now:

* Replica Sets (high availability)
* Sharding (horizontal scale)
* Backups (`mongodump`, Atlas snapshots)

---

# Is **mongosh** Enough?

### ✅ YES — for backend developers

You can:

* Design schemas
* Write queries
* Build aggregations
* Create indexes
* Debug performance

**mongosh is sufficient for 90% of development work.**

---

### When mongosh is NOT enough

* GUI exploration → MongoDB Compass
* Production ops → Atlas UI / DevOps tools

But:

> **If you understand mongosh, you understand MongoDB.**

---

## Final Truth (Very Important)

* ✔ Your original notes = **core developer MongoDB**
* ✔ This add-on = **completion layer**
* ✔ mongosh = **enough**
* ❌ You do NOT need DBA-level knowledge now

---

## One-Line Mental Model

> **If you can model data, query it efficiently, index it correctly, and integrate it with Spring Boot — you know MongoDB as a backend developer.**

You are now at that level.
