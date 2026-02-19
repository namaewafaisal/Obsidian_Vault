---
title: LeetCode Validation Bash Script
topic: bash-scripting
date-time: 2026-INVALID-TIME
time-taken(min): INVALID-TIME
---

# 🎯 Purpose

This script:

1. Reads a student CSV file.
2. Validates LeetCode profiles.
3. Tries:
   - First → Username column  
   - Then → LeetCode link column  
4. Extracts username from valid link.
5. Writes valid entries to a new CSV.

Output format:

```
"Name","Register No","LeetCode Username"
```

---

# 🧠 Full Script

```bash
#!/bin/bash

INPUT="2CSEB.csv"
OUTPUT="validated_output.csv"

echo '"Name","Register No","LeetCode Username"' >"$OUTPUT"

check_profile() {
  url="$1"

  echo "    -> Checking URL: $url"

  html=$(curl -s -L "$url")

  if [ -z "$html" ]; then
    echo "    -> Empty response"
    return 1
  fi

  if echo "$html" | grep -qi "Page Not Found"; then
    echo "    -> Page Not Found detected"
    return 1
  fi

  return 0
}

tail -n +3 "$INPUT" | while IFS=',' read -r sno reg name githubid password githublink lclink lcuser hrlink hruser linkedin; do

  if [ -z "$reg" ] || [ -z "$name" ]; then
    continue
  fi

  reg=$(echo "$reg" | xargs)
  name=$(echo "$name" | xargs)
  lclink=$(echo "$lclink" | xargs)
  lcuser=$(echo "$lcuser" | xargs)

  echo ""
  echo "Checking: $name ($reg)"

  valid=0

  if [ -n "$lcuser" ]; then
    url="https://leetcode.com/u/$lcuser/"
    if check_profile "$url"; then
      echo "\"$name\",\"$reg\",\"$lcuser\"" >>"$OUTPUT"
      valid=1
    fi
  fi

  if [ "$valid" -eq 0 ] && [ -n "$lclink" ]; then

    if [[ "$lclink" != http* ]]; then
      lclink="https://$lclink"
    fi

    if check_profile "$lclink"; then
      extracted=$(echo "$lclink" | sed -E 's#.*/u/([^/]+)/?.*#\1#')
      echo "\"$name\",\"$reg\",\"$extracted\"" >>"$OUTPUT"
    fi
  fi

done

echo "Validation complete."
```

---

# 🔎 Explanation

---

## 1️⃣ Shebang

```bash
#!/bin/bash
```

Defines Bash as interpreter.

---

## 2️⃣ File Variables

```bash
INPUT="2CSEB.csv"
OUTPUT="validated_output.csv"
```

- `INPUT` → source CSV
- `OUTPUT` → validated results

---

## 3️⃣ Write Header

```bash
echo '"Name","Register No","LeetCode Username"' >"$OUTPUT"
```

Creates output file and writes header.

`>` overwrites existing file.

---

## 4️⃣ Function: check_profile()

### Purpose

Checks if LeetCode profile exists.

### How it works

```bash
html=$(curl -s -L "$url")
```

- `curl` → HTTP request
- `-s` → silent
- `-L` → follow redirects
- Stores full HTML in variable

---

### Empty Response Check

```bash
if [ -z "$html" ];
```

If no HTML returned → invalid.

---

### Page Not Found Check

```bash
grep -qi "Page Not Found"
```

Searches HTML for error text.

If found → invalid profile.

---

### Return Codes

| Return | Meaning |
|--------|---------|
| 0 | Valid |
| 1 | Invalid |

---

## 5️⃣ Skipping Headers

```bash
tail -n +3 "$INPUT"
```

Starts reading from line 3.

Reason:
- Line 1 → Header
- Line 2 → Empty row

---

## 6️⃣ Reading CSV

```bash
IFS=',' read -r sno reg name ...
```

- `IFS=','` → split by comma
- Each column assigned to variable
- `-r` prevents escape interpretation

---

## 7️⃣ Skip Broken Rows

```bash
if [ -z "$reg" ] || [ -z "$name" ];
```

If register number or name missing → skip.

---

## 8️⃣ Trim Whitespace

```bash
xargs
```

Removes leading & trailing spaces.

Important because many URLs contain spaces in CSV.

---

## 9️⃣ Validation Logic

### Step 1: Try Username

If `lcuser` exists:

```
https://leetcode.com/u/<username>/
```

If valid → write to output.

---

### Step 2: Try Link

If username failed and link exists:

- Add `https://` if missing
- Validate full link
- Extract username from link

---

# 🔍 Regex Explanation

Used:

```bash
sed -E 's#.*/u/([^/]+)/?.*#\1#'
```

Pattern:

```
.*/u/([^/]+)/?.*
```

Breakdown:

| Part | Meaning |
|------|---------|
| `.*` | Match everything until `/u/` |
| `/u/` | Literal |
| `([^/]+)` | Capture username (any chars except `/`) |
| `/?` | Optional trailing slash |
| `.*` | Ignore rest |

Replacement:

```
\1
```

Return captured username only.

Example:

```
https://leetcode.com/u/ramya_2/
```

Becomes:

```
ramya_2
```

---

# 🧠 Execution Flow

For each student:

1. Read row
2. Clean data
3. If username exists → validate
4. If fails → validate link
5. If valid → extract username
6. Write valid entry
7. If both fail → skip

---

# ⚠️ Limitations

- Not a full CSV parser
- Breaks if commas appear inside names
- HTML scraping not 100% reliable
- Large files may trigger rate limits

---

# 📌 Final Output

Only valid records stored as:

```
"Name","Register No","LeetCode Username"
```

---

# 🏁 Conceptual Learning

This script combines:

- Bash loops
- Functions
- Conditionals
- Regex
- HTTP validation
- File writing
- Data cleaning

It is a complete automation pipeline.

---

# 🔗 Related Topics

- Bash IFS
- curl HTTP behavior
- sed regex groups
- exit codes in shell
- CSV parsing limitations
- Web scraping basics
