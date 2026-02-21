---
title: LeetCode Validation Automation – Debugging & Final Architecture
topic: python-automation
---

# 🎯 Objective

Validate LeetCode usernames from a CSV file and generate a clean output file containing:

```
Name, RegisterNumber, Year, Section, Username
```

The script evolved through multiple debugging stages. This note documents:

- What failed
- Why it failed
- What fixed it
- Why the final solution works

---

# 🧱 Stage 1 – Bash + curl (Initial Approach)

### What We Did
- Used `curl` to request:
  ```
  https://leetcode.com/u/<username>/
  ```
- Checked for `"Page Not Found"` in HTML.

### Why It Worked Initially
- `curl` was not immediately blocked.
- Requests were slow and sequential.
- LeetCode didn’t trigger bot protection immediately.

### Limitations
- HTML scraping is fragile.
- Depends on page content structure.
- Can be blocked anytime.
- Not scalable.

---

# 🧱 Stage 2 – Python + requests (HTML Scraping)

### What We Did
Used:

```python
requests.get("https://leetcode.com/u/username/")
```

### What Failed
Got:

```
HTTP 403
```

### Why It Failed

LeetCode uses:
- Cloudflare bot protection
- TLS fingerprinting
- Request pattern detection

Even after adding browser-like headers:

```python
User-Agent: Mozilla/5.0
```

Still blocked.

### Root Cause
`requests` has:
- Detectable TLS fingerprint
- Automated traffic signature
- Faster repeated requests

Result → Blocked as bot.

---

# 🧱 Stage 3 – Understanding the Core Mistake

We moved to GraphQL API (correct direction), but introduced a bug.

### The Bug

We passed:

```
https://leetcode.com/u/username/
```

Into GraphQL instead of:

```
username
```

GraphQL expects:

```json
{
  "username": "rajvenkatr"
}
```

Not a full URL.

This caused:

```
User does NOT exist
```

Even for valid users.

---

# ✅ Final Correct Solution – GraphQL API

### Why GraphQL Is Correct

Instead of scraping HTML:

We use LeetCode’s official API endpoint:

```
https://leetcode.com/graphql
```

Query:

```graphql
query userPublicProfile($username: String!) {
    matchedUser(username: $username) {
        username
    }
}
```

### Why This Works

- It is an official backend API.
- Used by LeetCode frontend itself.
- Returns JSON.
- No Cloudflare blocking.
- No HTML scraping.
- No 403 errors.
- Accurate existence check.

---

# 🧠 Key Fixes Made

## 1️⃣ Fixed Username Passing

Changed from:

```python
check_profile("https://leetcode.com/u/username/")
```

To:

```python
check_profile("username")
```

---

## 2️⃣ Extracted Username From Link Properly

```python
extracted = lclink.rstrip("/").split("/")[-1]
```

This ensures:

```
https://leetcode.com/u/ramya_2/
```

Becomes:

```
ramya_2
```

---

## 3️⃣ Fixed CSV Column Indexing

Original issue:
- Two different CSV structures.
- Incorrect index mapping.
- Rows skipped due to wrong `len(row)` checks.

Final mapping for `studentnew.csv`:

| Index | Column |
|--------|--------|
| 0 | ID |
| 1 | Name |
| 4 | LeetCode Link |
| 5 | LeetCode Username |

---

## 4️⃣ Added Year & Section Prompt

Instead of storing only:

```
Name, RegisterNumber, Username
```

We now prompt:

```python
year = input("Enter Year: ")
section = input("Enter Section: ")
```

And write:

```
Name, RegisterNumber, Year, Section, Username
```

---

# 🧪 Final Execution Flow

For each row:

1. Read CSV row.
2. Extract:
   - Register number
   - Name
   - LeetCode username
   - LeetCode link
3. If username exists:
   - Check via GraphQL.
4. If invalid:
   - Extract username from link.
   - Validate via GraphQL.
5. If valid:
   - Write to output with year & section.
6. If invalid:
   - Skip row.

---

# 📊 Architecture Comparison

| Method | Scraping | Block Risk | Reliability |
|--------|----------|------------|------------|
| Bash + curl | Yes | Medium | Fragile |
| Python + requests | Yes | High | Blocked |
| Python + GraphQL | No | Very Low | Stable |

---

# 🎓 What This Project Taught

## 🔹 1. Scraping is fragile
HTML changes, bot detection triggers.

## 🔹 2. APIs are superior
Always prefer official APIs when available.

## 🔹 3. Debug systematically
We diagnosed:
- CSV structure mismatch
- Incorrect column indexing
- HTTP 403 causes
- Wrong parameter passing

Each fix moved us closer.

## 🔹 4. Network debugging matters
- 403 ≠ invalid user
- HTTP status interpretation is critical
- TLS fingerprinting affects automation

---

# 🏁 Final Result

You now have:

- A stable LeetCode validator
- CSV transformation pipeline
- Dynamic Year/Section input
- API-based verification
- Clean output file generation

This is production-level automation logic.

---

# 🔗 Possible Next Improvements

- Add rate limiting (`time.sleep`)
- Add retry mechanism
- Convert to CLI tool (argparse)
- Add logging instead of prints
- Add async for faster validation
- Automatically detect duplicate usernames

---

# 🧠 Final Takeaway

The biggest improvement was:

Moving from scraping → to API querying.

That shift made the system:

- Cleaner
- More accurate
- More professional
- More scalable

This is how real backend engineers solve validation problems.
