This is one of the **most important units** in Data Warehousing. Nearly every exam asks you to **draw schemas, compare them, or design one**.

I'll cover:

1. What is a schema?
2. Star Schema
3. Snowflake Schema
4. Fact Constellation (Galaxy) Schema
5. Snowflake Normalization and Layers
6. Star vs Snowflake Comparison
7. Design Questions (How to propose a schema)
8. Exam Tips

---

# Unit 4 – Schemas & Data Modeling

# What is a Schema?

A **schema** is the logical design or blueprint of how data is organized inside a data warehouse.

It defines:

* Fact tables
* Dimension tables
* Relationships between them

Think of it like the blueprint of a house.

The blueprint doesn't contain the furniture—it only shows how rooms are connected.

Similarly,

A schema doesn't store data.

It only shows **how data is organized.**

---

# Fact Table

Before learning schemas, understand this.

A **Fact Table** stores

* Numerical values
* Measurements
* Business metrics

Examples

```text
Sales Amount
Quantity Sold
Profit
Discount
Revenue
```

Fact tables also contain **foreign keys** that connect to dimension tables.

Example

| Product_ID | Customer_ID | Time_ID | Sales |
| ---------- | ----------- | ------- | ----- |
| P101       | C15         | T20     | ₹5000 |

Sales is the **fact**.

---

# Dimension Table

Dimension tables provide descriptive information about facts.

Examples

Customer Table

| Customer_ID | Name  | City    |
| ----------- | ----- | ------- |
| C15         | Rahul | Chennai |

Product Table

| Product_ID | Product | Category    |
| ---------- | ------- | ----------- |
| P101       | Laptop  | Electronics |

Time Table

| Time_ID | Month | Year |
| ------- | ----- | ---- |
| T20     | March | 2025 |

Dimensions answer questions like

* Who?
* What?
* Where?
* When?

---

# Star Schema

## Definition

A **Star Schema** consists of one central fact table connected directly to multiple dimension tables.

Since the diagram resembles a star, it is called a **Star Schema**.

---

## Diagram

```text
               Customer
                   |
                   |
Product ----- Fact Table ----- Time
                   |
                   |
               Store
                   |
                   |
              Employee
```

Fact table is at the center.

Dimension tables surround it.

Looks like a star.

---

## Example

Suppose a supermarket wants to analyze sales.

Fact Table

```text
Sales_Fact

Product_ID
Customer_ID
Store_ID
Time_ID

Quantity
Sales
Profit
```

Dimension Tables

### Product

```text
Product_ID
Product Name
Category
Brand
```

### Customer

```text
Customer_ID
Customer Name
City
Age
```

### Time

```text
Time_ID
Date
Month
Year
```

### Store

```text
Store_ID
Store Name
Location
```

---

## Characteristics

* Simple design
* Denormalized dimensions
* Fast querying
* Easy to understand
* Less joins

---

## Advantages

✔ Faster queries

✔ Simple structure

✔ Easy reporting

✔ Better OLAP performance

---

## Disadvantages

❌ Data redundancy

Example

Suppose 500 products belong to

"Electronics"

The word Electronics appears 500 times.

---

# Snowflake Schema

## Definition

A **Snowflake Schema** is an extension of the Star Schema where dimension tables are **normalized** into multiple related tables.

Instead of storing repeated information, it splits dimensions into smaller tables.

---

## Diagram

```text
                 Category
                     |
                     |
Product ------ Fact Table ------ Time
   |                 |              |
Brand             Customer       Month
                    |
                  City
```

Notice

Product is divided into

* Product
* Category
* Brand

Customer becomes

* Customer
* City

This branching structure resembles a snowflake.

---

## Example

Instead of

Product Table

| Product | Category | Brand |
| ------- | -------- | ----- |

It becomes

### Product

| Product_ID | Product_Name | Brand_ID | Category_ID |

### Brand

| Brand_ID | Brand |

### Category

| Category_ID | Category |

---

# Snowflake Normalization

Normalization means

> Splitting one large table into smaller related tables to eliminate duplicate data and improve data integrity.

Example (Before Normalization)

| Product | Brand | Category |
| ------- | ----- | -------- |
| iPhone  | Apple | Mobile   |
| MacBook | Apple | Laptop   |
| iPad    | Apple | Tablet   |

Notice

Apple repeats.

After normalization

### Product

| Product | Brand_ID |

### Brand

| Brand_ID | Brand |

Now "Apple" is stored only once.

This reduces redundancy.

---

# Snowflake Layers

Think of Snowflake as layers expanding outward from the fact table.

```
Fact Table

↓

Dimension

↓

Sub-dimension

↓

Further normalized tables
```

Example

```text
Fact

↓

Customer

↓

City

↓

State

↓

Country
```

Each layer stores more detailed information.

---

# Characteristics

* Normalized dimensions
* Less redundancy
* More joins
* More storage efficiency
* Slightly slower queries

---

# Advantages

✔ Saves storage

✔ Less duplication

✔ Better consistency

✔ Easier maintenance

---

# Disadvantages

❌ Complex

❌ More joins

❌ Slower query execution

---

# Fact Constellation Schema (Galaxy Schema)

## Definition

A **Fact Constellation Schema** contains **multiple fact tables** that share one or more common dimension tables.

It is also called the **Galaxy Schema** because it resembles a collection of stars.

---

## Diagram

```text
              Product
                 |
                 |
Sales Fact ---- Time ---- Inventory Fact
     |                         |
Customer                  Warehouse
     |
Promotion
```

Notice

There are two fact tables

* Sales Fact
* Inventory Fact

Both use

Time

and

Product.

---

## Example

A retail company wants to analyze

Sales

and

Inventory.

Instead of one fact table,

it creates

Sales Fact

```text
Sales
Revenue
Quantity Sold
```

Inventory Fact

```text
Current Stock
Incoming Stock
Returned Stock
```

Shared Dimensions

* Product
* Time
* Store

---

## Characteristics

* Multiple fact tables
* Shared dimensions
* Supports complex business analysis
* Large enterprises

---

## Advantages

✔ Supports multiple business processes

✔ Flexible

✔ Highly scalable

✔ Enterprise-wide analysis

---

## Disadvantages

❌ Difficult to design

❌ Complex queries

❌ High maintenance

---

# Comparison: Star vs Snowflake

| Feature          | Star Schema                         | Snowflake Schema                                     |
| ---------------- | ----------------------------------- | ---------------------------------------------------- |
| Structure        | Central fact with direct dimensions | Fact with normalized dimensions                      |
| Normalization    | No (Denormalized)                   | Yes (Normalized)                                     |
| Number of Tables | Fewer                               | More                                                 |
| Query Speed      | Faster                              | Slower                                               |
| Storage          | Higher                              | Lower                                                |
| Redundancy       | More                                | Less                                                 |
| Maintenance      | Easier                              | More complex                                         |
| Joins            | Fewer                               | More                                                 |
| Design           | Simple                              | Complex                                              |
| Best For         | Fast reporting and OLAP             | Large warehouses with many repeated dimension values |

---

# Design Question (Very Common)

### Question

Design a Star Schema for an Online Shopping System.

### Step 1

Identify the business process.

Example

Sales.

---

### Step 2

Find the facts.

```text
Sales Amount

Quantity

Discount

Profit
```

These become the Fact Table.

---

### Step 3

Find the dimensions.

```text
Customer

Product

Store

Time

Payment

Employee
```

---

### Step 4

Draw the schema.

```text
             Customer
                 |
Product ---- Sales Fact ---- Time
                 |
              Payment
                 |
               Store
```

That's all the examiner expects.

---

# How to Convert Star → Snowflake

Suppose Star Schema has

```text
Product

Product Name

Category

Brand

Manufacturer
```

Normalize it.

Create

```text
Product

↓

Category

↓

Brand

↓

Manufacturer
```

Similarly,

Customer

↓

City

↓

State

↓

Country

This becomes a Snowflake Schema.

---

# When to Use Which?

**Star Schema**

* Small to medium data warehouses
* Fast OLAP queries
* Easy reporting
* Simpler design and maintenance

**Snowflake Schema**

* Very large warehouses
* Frequent updates to dimension data
* Reduced storage through normalization
* Better data consistency

**Fact Constellation**

* Multiple business processes (e.g., Sales, Inventory, Shipping)
* Enterprise-wide data warehouses
* Shared dimensions across different fact tables

---

# 2-Mark Revision

### Star Schema

* One fact table connected directly to multiple denormalized dimension tables.
* Fast, simple, fewer joins, but more redundancy.

### Snowflake Schema

* Dimension tables are normalized into multiple related tables.
* Less redundancy, more joins, slower queries.

### Fact Constellation (Galaxy)

* Multiple fact tables share common dimension tables.
* Used for complex enterprise data warehouses.

### Normalization in Snowflake

* Splits large dimension tables into smaller related tables.
* Reduces duplicate data, improves consistency, but increases joins.

### Rule to Remember

* **Star = Simple + Fast**
* **Snowflake = Normalized + Space Efficient**
* **Fact Constellation = Multiple Facts + Shared Dimensions**
