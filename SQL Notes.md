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
ORDER BY column ASC/DESC;
LIMIT 
```
