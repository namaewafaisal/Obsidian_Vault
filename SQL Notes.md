Learned from [SQLBOLT](sqlbolt.com)
# Intro
SQL or structured query language is most common way of interacting with database. Its used in SQLite, MySQL, Postgres, Oracle etc
A database is like a collection of tables or relations which are in someway might be linked or have a relation between them.
A table is basically like an Excel sheet with fixed, named columns and any number of rows or records

## SELECT

Select is the basic sql statement. its also called query cuz we get data by saying how, what and where to get data.
```sql
SELECT name,age
FROM human;
```

`Select` selects the column specified 
`FROM` gets the table from where to take the records from.
`SELECT *` takes all columns

## Where
Where clause is a condition which is applied to filter results

```sql
SELECT column, another_column, …
FROM mytable
WHERE condition
    AND/OR another_condition
    AND/OR …;
```

|                     |                                                      |                               |
| ------------------- | ---------------------------------------------------- | ----------------------------- |
| Operator            | Condition                                            | SQL Example                   |
| =, !=, <, <=, >, >= | Standard numerical operators                         | col_name != 4                 |
| BETWEEN … AND …     | Number is within range of two values (inclusive)     | col_name BETWEEN 1.5 AND 10.5 |
| NOT BETWEEN … AND … | Number is not within range of two values (inclusive) | col_name NOT BETWEEN 1 AND 10 |
| IN (…)              | Number exists in a list                              | col_name IN (2, 4, 6)         |
| NOT IN (…)          | Number does not exist in a list                      | col_name NOT IN (1, 3, 5)     |

> [!NOTE] Logical Precendence
> AND takes priority normally so use () to enclose if needed precedence in AND and OR operations


| Operator   | Condition                                                                                             | Example                                                                 |
| ---------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| =          | Case sensitive exact string comparison (_notice the single equals_)                                   | col_name = "abc"                                                        |
| != or <>   | Case sensitive exact string inequality comparison                                                     | col_name != "abcd"                                                      |
| LIKE       | Case insensitive exact string comparison                                                              | col_name LIKE "ABC"                                                     |
| NOT LIKE   | Case insensitive exact string inequality comparison                                                   | col_name NOT LIKE "ABCD"                                                |
| %          | Used anywhere in a string to match a sequence of zero or more characters (only with LIKE or NOT LIKE) | col_name LIKE "%AT%"  <br>(matches "AT", "ATTIC", "CAT" or even "BATS") |
| _          | Used anywhere in a string to match a single character (only with LIKE or NOT LIKE)                    | col_name LIKE `"AN\_"`  <br>(matches "AND", but not "AN")               |
| IN (…)     | String exists in a list                                                                               | col_name IN ("A", "B", "C")                                             |
| NOT IN (…) | String does not exist in a list                                                                       | col_name NOT IN ("D", "E", "F")                                         |

> [Apache Lucene](http://lucene.apache.org/ "Apache Lucene") or [Sphinx](http://sphinxsearch.com/ "Sphinx Search") are better in full text search as they are designed for it

## Distinct 
GET only unique values
## ORDER BY
It takes a column to sort by and gets ASC or DESC
## LIMIT
limits the number of rows returned (like size in backend pagination)
## OFFSET
Skip first n values (like page but not a index but index times the size like)


```sql
SELECT column, another_column, …
FROM mytable
WHERE condition(s)
ORDER BY column ASC/DESC
LIMIT num_limit OFFSET num_offset;
```


# Normalization
Storing less duplicate data across tables so each grow separately independent of each other while still holding a relation. 
queries become more complex.

## Inner Join
Take only the common in both tables. can be used as INNER JOIN or JOIN. refer to same

# Left Join
All records, columns from left, matching records in right

## Right
Vice versa of left join
## Full join or Outer Join
both tables regardless what what matches

we have to manage how to deal with null

```sql
SELECT column, another_column, …
FROM mytable
INNER/LEFT/RIGHT/FULL JOIN another_table 
    ON mytable.id = another_table.matching_id
WHERE condition(s)
ORDER BY column, … ASC/DESC
LIMIT num_limit OFFSET num_offset;
```

# SQL JOIN (append)

## 1. INNER JOIN
- Only matching rows (intersection)

Example:
```sql
SELECT b.building_name, e.role
FROM buildings b
JOIN employees e
ON b.building_name = e.building;
```

Use when:
- You only want data that exists in both tables

---

## 2. LEFT JOIN
- All rows from left + matching from right
- No match → NULL

Example:
```sql
SELECT b.building_name, e.role
FROM buildings b
LEFT JOIN employees e
ON b.building_name = e.building;
```

Use when:
- You want ALL buildings (even empty ones)

---

## 3. RIGHT JOIN
- All rows from right + matching from left

Example:
```sql
SELECT b.building_name, e.role
FROM buildings b
RIGHT JOIN employees e
ON b.building_name = e.building;
```

Use when:
- Rare → usually just swap tables and use LEFT JOIN

---

## 4. FULL JOIN
- All rows from both sides
- No match → NULL

Example:
```sql
SELECT *
FROM buildings
FULL JOIN employees
ON buildings.building_name = employees.building;
```

Use when:
- Need everything from both tables

---

## Notes
- JOIN without type = INNER JOIN
- Use DISTINCT if duplicates appear
- Always use ON (else cartesian product)
  
  ---
## IS NULL/ IS NOT NULL

```sql
SELECT column, another_column, …
FROM mytable
WHERE column IS/IS NOT NULL
AND/OR another_condition
AND/OR …;
```

# SQL Expressions (append)

## Idea
- Perform calculations on column values (row-by-row)
- Does NOT combine rows (not aggregates)

---

## Syntax
```sql
SELECT expression AS alias
FROM table;
```

---

## Common operations

### Arithmetic
```sql
col1 + col2
col1 / 1000000
col * 10
```

---

### Conditions (with expressions)
```sql
WHERE col % 2 = 0   -- even numbers
```

---

## Alias (important)
```sql
SELECT col * 10 AS new_col
```
- Renames output column
- Improves readability

---

## Key rule
- Expression → transforms each row
- Aggregate → combines multiple rows (not used here)

---

## Pattern (this exercise)

### Combine columns
```sql
col1 + col2
```

### Convert values
```sql
col * 10
col / 1000000
```

### Filter using logic
```sql
WHERE col % 2 = 0
```

---

## Notes
- Works per row
- No GROUP BY needed
- Avoid SELECT * when modifying columns


## Aggregate

| Function                            | Description                                                                                                                                                                                     |
| ----------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **COUNT(*)**, **COUNT(**column**)** | A common function used to counts the number of rows in the group if no column name is specified. Otherwise, count the number of rows in the group with non-NULL values in the specified column. |
| **MIN(**column**)**                 | Finds the smallest numerical value in the specified column for all rows in the group.                                                                                                           |
| **MAX(**column**)**                 | Finds the largest numerical value in the specified column for all rows in the group.                                                                                                            |
| **AVG(**column)                     | Finds the average numerical value in the specified column for all rows in the group.                                                                                                            |
| **SUM(**column**)**                 | Finds the sum of all numerical values in the specified column for the rows in the group.                                                                                                        |
## GROUP BY (Quick Note)

* Used to **group rows with same values** in a column
* Always used with **aggregate functions** (`COUNT`, `SUM`, `AVG`, etc.)

---

## Basic Syntax

```sql
SELECT column, AGG_FUNC(column)
FROM table
GROUP BY column;
```

---

## Key Rules

* Every column in `SELECT` must be:

  * either in `GROUP BY`, OR
  * used inside an aggregate function

* `WHERE` → filters **before grouping**

* `HAVING` → filters **after grouping**

---

## Example

```sql
SELECT habitat_id, COUNT(*) AS species_count
FROM AlienSpecies
GROUP BY habitat_id
HAVING COUNT(*) > 1;
```

👉 Groups species by habitat
👉 Keeps only habitats with more than 1 species

---

## Mental Model

* `GROUP BY` = “make buckets”
* Aggregate = “calculate per bucket”
* `HAVING` = “filter buckets”

```sql
SELECT group_by_column, AGG_FUNC(column_expression) AS aggregate_result_alias, …
FROM mytable
WHERE condition
GROUP BY column
HAVING group_condition;
```


```sql
SELECT DISTINCT column, AGG_FUNC(column_or_expression), …
FROM mytable
    JOIN another_table
      ON mytable.column = another_table.column
    WHERE constraint_expression
    GROUP BY column
    HAVING constraint_expression
    ORDER BY column ASC/DESC
    LIMIT count OFFSET COUNT;
```


# Query order of execution

## 1. `FROM` and `JOIN`s

The `FROM` clause, and subsequent `JOIN`s are first executed to determine the total working set of data that is being queried. This includes subqueries in this clause, and can cause temporary tables to be created under the hood containing all the columns and rows of the tables being joined.

## 2. `WHERE`

Once we have the total working set of data, the first-pass `WHERE` constraints are applied to the individual rows, and rows that do not satisfy the constraint are discarded. Each of the constraints can only access columns directly from the tables requested in the `FROM` clause. Aliases in the `SELECT` part of the query are not accessible in most databases since they may include expressions dependent on parts of the query that have not yet executed.

## 3. `GROUP BY`

The remaining rows after the `WHERE` constraints are applied are then grouped based on common values in the column specified in the `GROUP BY` clause. As a result of the grouping, there will only be as many rows as there are unique values in that column. Implicitly, this means that you should only need to use this when you have aggregate functions in your query.

## 4. `HAVING`

If the query has a `GROUP BY` clause, then the constraints in the `HAVING` clause are then applied to the grouped rows, discard the grouped rows that don't satisfy the constraint. Like the `WHERE` clause, aliases are also not accessible from this step in most databases.

## 5. `SELECT`

Any expressions in the `SELECT` part of the query are finally computed.

## 6. `DISTINCT`

Of the remaining rows, rows with duplicate values in the column marked as `DISTINCT` will be discarded.

## 7. `ORDER BY`

If an order is specified by the `ORDER BY` clause, the rows are then sorted by the specified data in either ascending or descending order. Since all the expressions in the `SELECT` part of the query have been computed, you can reference aliases in this clause.

## 8. `LIMIT` / `OFFSET`

Finally, the rows that fall outside the range specified by the `LIMIT` and `OFFSET` are discarded, leaving the final set of rows to be returned from the query.

So far query is over for now

# Inserting data

```sql
INSERT INTO mytable
VALUES (value_or_expr, another_value_or_expr, …),
       (value_or_expr_2, another_value_or_expr_2, …),
       …;
```

In some cases, if you have incomplete data and the table contains columns that support default values, you can insert rows with only the columns of data you have by specifying them explicitly.
Insert statement with specific columns

```sql
INSERT INTO mytable
(column, another_column, …)
VALUES (value_or_expr, another_value_or_expr, …),
      (value_or_expr_2, another_value_or_expr_2, …),
      …;
```

In addition, you can use mathematical and string expressions with the values that you are inserting.
This can be useful to ensure that all data inserted is formatted a certain way.
Example Insert statement with expressions

```sql
INSERT INTO boxoffice
(movie_id, rating, sales_in_millions)
VALUES (1, 9.9, 283742034 / 1000000);
```

# Creating a relation
```sql
INSERT INTO boxoffice (movie_id, rating, domestic_sales, international_sales)
SELECT 
    id,
    8.7,
    340000000,
    270000000
FROM movies
WHERE title = 'Toy Story 4';
```

## Update

```sql
UPDATE mytable
SET column = value_or_expr, 
    other_column = another_value_or_expr, 
    …
WHERE condition;
```

## Delete

Delete statement with condition
```sql
DELETE FROM mytable
WHERE condition;
```

If you decide to leave out the WHERE constraint, then all rows are removed, which is a quick and easy way to clear out a table completely (if intentional).

## Create
```sql
CREATE TABLE IF NOT EXISTS mytable (
    column DataType TableConstraint DEFAULT default_value,
    another_column DataType TableConstraint DEFAULT default_value,
    …
);
```


| Data type                                            | Description                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| ---------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `INTEGER`, `BOOLEAN`                                 | The integer datatypes can store whole integer values like the count of a number or an age. In some implementations, the boolean value is just represented as an integer value of just 0 or 1.                                                                                                                                                                                                                                                          |
| `FLOAT`, `DOUBLE`, `REAL`                            | The floating point datatypes can store more precise numerical data like measurements or fractional values. Different types can be used depending on the floating point precision required for that value.                                                                                                                                                                                                                                              |
| `CHARACTER(num_chars)`, `VARCHAR(num_chars)`, `TEXT` | The text based datatypes can store strings and text in all sorts of locales. The distinction between the various types generally amount to underlaying efficiency of the database when working with these columns.<br><br>Both the CHARACTER and VARCHAR (variable character) types are specified with the max number of characters that they can store (longer values may be truncated), so can be more efficient to store and query with big tables. |
| `DATE`, `DATETIME`                                   | SQL can also store date and time stamps to keep track of time series and event data. They can be tricky to work with especially when manipulating data across timezones.                                                                                                                                                                                                                                                                               |
| `BLOB`                                               | Finally, SQL can store binary data in blobs right in the database. These values are often opaque to the database, so you usually have to store them with the right metadata to requery them.                                                                                                                                                                                                                                                           |


| Constraint           | Description                                                                                                                                                                                                                                                                                                                                                                                        |
| -------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `PRIMARY KEY`        | This means that the values in this column are unique, and each value can be used to identify a single row in this table.                                                                                                                                                                                                                                                                           |
| `AUTOINCREMENT`      | For integer values, this means that the value is automatically filled in and incremented with each row insertion. Not supported in all databases.                                                                                                                                                                                                                                                  |
| `UNIQUE`             | This means that the values in this column have to be unique, so you can't insert another row with the same value in this column as another row in the table. Differs from the `PRIMARY KEY` in that it doesn't have to be a key for a row in the table.                                                                                                                                            |
| `NOT NULL`           | This means that the inserted value can not be `NULL`.                                                                                                                                                                                                                                                                                                                                              |
| `CHECK (expression)` | This allows you to run a more complex expression to test whether the values inserted are valid. For example, you can check that values are positive, or greater than a specific size, or start with a certain prefix, etc.                                                                                                                                                                         |
| `FOREIGN KEY`        | This is a consistency check which ensures that each value in this column corresponds to another value in a column in another table.  <br>  <br>For example, if there are two tables, one listing all Employees by ID, and another listing their payroll information, the `FOREIGN KEY` can ensure that every row in the payroll table corresponds to a valid employee in the master Employee list. |

`ALTER TABLE mytable ADD column _DataType_ _OptionalTableConstraint_ DEFAULT default_value;`

# Removing columns

Dropping columns is as easy as specifying the column to drop, however, some databases (including SQLite) don't support this feature. Instead you may have to create a new table and migrate the data over.

Altering table to remove column(s)

`ALTER TABLE mytable DROP column_to_be_deleted;`

# Renaming the table

If you need to rename the table itself, you can also do that using the `RENAME TO` clause of the statement.

Altering table name

`ALTER TABLE mytable RENAME TO new_table_name;`

## Drop 
```sql
DROP TABLE IF EXISTS mytable;```