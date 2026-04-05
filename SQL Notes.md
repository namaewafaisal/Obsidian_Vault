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
