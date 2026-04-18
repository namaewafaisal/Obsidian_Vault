# Logical and Short Circuit Operators

Logical operators combine multiple Boolean results (or conditions) to produce a single Boolean result. They are typically used within conditional statements (e.g., `if-else`).

|Operator|Name|Symbol(s)|Function|Citation|
|:--|:--|:--|:--|:--|
|**Logical AND**|AND|`&` or `&&`|Returns `true` only if **both** conditions are true.||
|**Logical OR**|OR|`|`or`|`|
|**Logical NOT**|NOT|`!`|Reverses the Boolean result (e.g., `true` becomes `false`).||

### Short Circuit Operators (`&&` and `||`)

The short circuit versions (`&&` and `||`) are generally preferred over the single operators (`&` and `|`).

- **Short Circuit AND (`&&`):** If the first condition is `false`, Java immediately knows the entire expression must be `false` and **stops checking the remaining conditions** (it "short circuits").
- **Short Circuit OR (`||`):** If the first condition is `true`, Java immediately knows the entire expression must be `true` and **stops checking the remaining conditions**.

This mechanism is beneficial because it saves computation time and resources.

---
