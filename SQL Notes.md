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