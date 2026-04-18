# 📂 File & Data CLI Tools — jq, awk, sed, grep, find

> [!abstract] Overview
> Power tools for searching, filtering, transforming, and processing text and JSON data on the command line. Master these and you'll rarely need a script for data wrangling.

**Tags:** #cli #linux #tools #data #jq #awk #sed #grep #find

---

## 🔍 grep — Search Text

> Search for patterns in files or stdin. Most used tool in the Unix toolkit.

### Basic Usage

```bash
# Search for pattern in file
grep "error" logfile.txt

# Case insensitive
grep -i "error" logfile.txt

# Show line numbers
grep -n "error" logfile.txt

# Invert match (lines that DON'T match)
grep -v "DEBUG" logfile.txt

# Count matches
grep -c "error" logfile.txt

# Show only matching part (not full line)
grep -o "[0-9]\+" logfile.txt
```

### Recursive & Multiple Files

```bash
# Search recursively in directory
grep -r "TODO" src/

# Recursive + show filename
grep -rn "NullPointerException" src/

# Only show filenames that match
grep -rl "TODO" src/

# Search multiple files
grep "error" *.log

# Exclude files/dirs
grep -r "error" src/ --exclude="*.class"
grep -r "error" src/ --exclude-dir=".git"
```

### Extended Regex

```bash
# Use extended regex (-E) or egrep
grep -E "error|warn|fatal" logfile.txt

# Match lines starting with "GET" or "POST"
grep -E "^(GET|POST)" access.log

# Match email pattern
grep -E "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" file.txt

# Match IP address
grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" logfile.txt
```

### Context Lines

```bash
# Show 3 lines before match
grep -B 3 "Exception" logfile.txt

# Show 3 lines after match
grep -A 3 "Exception" logfile.txt

# Show 3 lines before AND after
grep -C 3 "Exception" logfile.txt
```

### Practical Combos

```bash
# Find all Java files with TODO
grep -rn "TODO" src/ --include="*.java"

# Find all failed HTTP requests in access log
grep " 5[0-9][0-9] " access.log

# Count errors per hour (pipe with awk)
grep "ERROR" app.log | awk '{print $2}' | cut -d: -f1 | sort | uniq -c

# Search inside compressed files
zgrep "error" app.log.gz
```

---

## ✂️ sed — Stream Editor

> Non-interactive text transformation. Best for find-and-replace, deletion, and insertion.

### Substitution

```bash
# Basic find and replace (first occurrence per line)
sed 's/old/new/' file.txt

# Replace ALL occurrences per line (global)
sed 's/old/new/g' file.txt

# Case-insensitive replace
sed 's/old/new/gi' file.txt

# In-place editing (modify file directly)
sed -i 's/old/new/g' file.txt

# In-place with backup
sed -i.bak 's/old/new/g' file.txt

# Delimiter can be anything (useful when pattern has /)
sed 's|/old/path|/new/path|g' file.txt
```

### Line Operations

```bash
# Delete lines matching pattern
sed '/^#/d' file.txt           # delete comments
sed '/^$/d' file.txt           # delete empty lines

# Delete specific line numbers
sed '3d' file.txt              # delete line 3
sed '2,5d' file.txt            # delete lines 2-5
sed '$d' file.txt              # delete last line

# Print specific lines
sed -n '5p' file.txt           # print line 5
sed -n '2,8p' file.txt         # print lines 2-8
sed -n '/start/,/end/p' file.txt  # print between patterns

# Insert line before/after
sed '3i\new line here' file.txt   # insert before line 3
sed '3a\new line here' file.txt   # append after line 3
```

### Practical Examples

```bash
# Remove trailing whitespace
sed 's/[[:space:]]*$//' file.txt

# Remove leading whitespace
sed 's/^[[:space:]]*//' file.txt

# Add line number prefix
sed = file.txt | sed 'N;s/\n/\t/'

# Replace in multiple files
sed -i 's/localhost:8080/api.example.com/g' src/main/resources/*.properties

# Extract lines between two patterns
sed -n '/START/,/END/p' file.txt

# Comment out a line matching pattern
sed '/spring.datasource.url/s/^/# /' application.properties

# Double-space a file
sed 'G' file.txt
```

---

## 🔧 awk — Text Processing

> A full programming language for column-based data processing. Think of it as "per-line logic."

### Mental Model

```
awk 'pattern { action }' file
```

Built-in variables:
- `$0` — entire line
- `$1`, `$2`, `$NF` — fields (NF = last field)
- `NR` — current line number
- `NF` — number of fields
- `FS` — field separator (default: whitespace)
- `OFS` — output field separator

### Basic Usage

```bash
# Print second column of each line
awk '{print $2}' file.txt

# Print first and last column
awk '{print $1, $NF}' file.txt

# Custom field separator (CSV)
awk -F',' '{print $1, $3}' data.csv

# Custom separator (colon — useful for /etc/passwd)
awk -F: '{print $1, $3}' /etc/passwd

# Print with custom output separator
awk -F',' 'OFS="\t" {print $1, $2, $3}' data.csv
```

### Filtering

```bash
# Print lines where field 3 > 100
awk '$3 > 100' file.txt

# Print lines matching pattern
awk '/ERROR/' logfile.txt

# Print lines where field 1 equals value
awk '$1 == "GET"' access.log

# Multiple conditions
awk '$1 == "POST" && $9 >= 400' access.log
```

### Math & Aggregation

```bash
# Sum a column
awk '{sum += $3} END {print sum}' file.txt

# Average
awk '{sum += $3; count++} END {print sum/count}' file.txt

# Max value
awk 'BEGIN{max=0} $3 > max {max=$3} END{print max}' file.txt

# Count lines matching pattern
awk '/ERROR/{count++} END{print count}' logfile.txt

# Word count
awk '{words += NF} END {print words}' file.txt
```

### BEGIN and END Blocks

```bash
# Header + footer
awk 'BEGIN{print "Name\tAge"} {print $1, $2} END{print "---"}' file.txt

# Process CSV and add header
awk -F',' 'BEGIN{OFS=","} NR==1{print "ID,Name,Score"} NR>1{print $0}' data.csv
```

### Practical One-Liners

```bash
# Top 10 IPs from access log
awk '{print $1}' access.log | sort | uniq -c | sort -rn | head 10

# Sum request sizes from nginx log
awk '{sum += $10} END {print sum " bytes"}' access.log

# Print lines between patterns
awk '/START/,/END/' file.txt

# Remove duplicate lines (preserving order)
awk '!seen[$0]++' file.txt

# Print every other line
awk 'NR%2==0' file.txt

# Reverse field order
awk '{for(i=NF;i>=1;i--) printf "%s ", $i; print ""}' file.txt

# Extract HTTP 5xx errors
awk '$9 ~ /^5/' access.log
```

---

## 🔎 find — File Search

> Find files and directories, then optionally act on them.

### Basic Search

```bash
# Find by name
find . -name "*.java"
find /etc -name "*.conf"

# Case insensitive name
find . -iname "readme*"

# Find directories only
find . -type d -name "target"

# Find files only
find . -type f -name "*.log"

# Find symlinks
find . -type l
```

### By Size

```bash
# Larger than 100MB
find . -size +100M

# Smaller than 10KB
find . -size -10k

# Exactly 512 bytes
find . -size 512c

# Find large files in /var
find /var -type f -size +50M -ls
```

### By Time

```bash
# Modified in last 7 days
find . -mtime -7

# Modified more than 30 days ago
find . -mtime +30

# Accessed in last 1 day
find . -atime -1

# Modified in last 10 minutes
find . -mmin -10
```

### By Permissions

```bash
# Find SUID files (security check)
find / -perm -4000 -type f 2>/dev/null

# World-writable files
find / -perm -o+w -type f 2>/dev/null

# Find files owned by user
find /home -user mohamed
```

### Actions on Results

```bash
# Delete found files
find . -name "*.class" -delete
find . -type d -name "target" -exec rm -rf {} +

# Execute command on each file
find . -name "*.log" -exec gzip {} \;

# Use xargs (faster for many files)
find . -name "*.java" | xargs grep "TODO"
find . -name "*.log" -print0 | xargs -0 gzip

# Copy all .java files preserving structure
find src/ -name "*.java" -exec cp --parents {} /backup/ \;

# Find and chmod
find . -name "*.sh" -exec chmod +x {} \;
```

### Combining Conditions

```bash
# AND (default)
find . -name "*.java" -size +1k

# OR
find . -name "*.java" -o -name "*.xml"

# NOT
find . -not -name "*.class"
find . ! -name "*.class"

# Exclude directory
find . -not -path "./.git/*" -name "*.java"
find . -path "./.git" -prune -o -name "*.java" -print
```

---

## 🔷 jq — JSON Processor

> The sed/awk of JSON. Essential when working with APIs and curl output.

### Basic Usage

```bash
# Pretty print JSON
curl -s https://api.example.com/data | jq .

# Get a field
echo '{"name":"Mohamed","role":"dev"}' | jq '.name'

# Nested field
echo '{"user":{"name":"Mohamed"}}' | jq '.user.name'

# Array element
echo '{"items":[1,2,3]}' | jq '.items[0]'
echo '{"items":[1,2,3]}' | jq '.items[-1]'   # last element

# Array slice
jq '.items[1:3]' file.json
```

### Array Operations

```bash
# All elements of an array
jq '.[]' data.json

# Array length
jq '.items | length' data.json

# Get field from each object in array
jq '.[].name' data.json
jq '[.[].name]' data.json       # wrap back into array

# Filter array by condition
jq '[.[] | select(.age > 18)]' data.json

# Map (transform each element)
jq '[.[] | .name]' data.json
jq 'map(.age * 2)' data.json

# Sort array
jq 'sort_by(.name)' data.json
jq 'sort_by(.age) | reverse' data.json
```

### Building New Objects

```bash
# Construct new object
jq '{id: .id, title: .title}' movie.json

# From array
jq '[.[] | {id, title, year}]' movies.json

# Add a field
jq '. + {"platform": "OTT"}' movie.json

# Delete a field
jq 'del(.password)' user.json
```

### String Operations

```bash
# String interpolation
jq '"Title: \(.title) (\(.year))"' movie.json

# Split/join
jq '.tags | join(", ")' file.json
jq '"a,b,c" | split(",")' <<<'""'

# Test contains
jq 'select(.title | contains("Dark"))' movies.json

# Lowercase/uppercase (via @base64 is a workaround)
jq '.name | ascii_downcase' user.json
```

### Practical Patterns

```bash
# Extract token from login response
TOKEN=$(curl -s -X POST https://api.example.com/login \
  -d '{"user":"admin","pass":"secret"}' | jq -r '.token')

# Pretty print and save
curl -s https://api.example.com/movies | jq . > movies.json

# Get all IDs from list
curl -s https://api.example.com/movies | jq '[.[].id]'

# Filter movies by genre
cat movies.json | jq '[.[] | select(.genre == "Sci-Fi")]'

# Count items
curl -s https://api.example.com/movies | jq 'length'

# First result only
curl -s https://api.example.com/movies | jq '.[0]'

# Raw output (no quotes)
jq -r '.name' user.json

# Multiple outputs to CSV
jq -r '.[] | [.id, .title, .year] | @csv' movies.json

# Compact output (one line)
jq -c '.' data.json

# Read from file
jq '.title' movie.json
```

### jq with curl — Full Workflow

```bash
# Login → extract token → use token
BASE="http://localhost:8080/api"

TOKEN=$(curl -s -X POST "$BASE/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"secret"}' \
  | jq -r '.accessToken')

# Get all movies, extract just titles
curl -s -H "Authorization: Bearer $TOKEN" "$BASE/movies" \
  | jq '[.[] | {id, title, genre}]'

# Get a specific movie
curl -s -H "Authorization: Bearer $TOKEN" "$BASE/movies/1" \
  | jq '{id, title, rating}'
```

---

## 🔗 Power Combos

```bash
# Find all Java files with TODO and show context
find src/ -name "*.java" | xargs grep -n "TODO" -A 2

# Top 10 largest files in a directory
find . -type f -printf '%s %p\n' | sort -rn | head 10

# Count lines of code by file type
find src/ -name "*.java" | xargs wc -l | sort -n

# Watch a log for errors in real time
tail -f app.log | grep --line-buffered "ERROR"

# Extract unique IPs from log, count, sort
grep -oE "([0-9]{1,3}\.){3}[0-9]{1,3}" access.log \
  | sort | uniq -c | sort -rn | head 20

# Parse JSON logs
cat app.log | jq -r 'select(.level=="ERROR") | "\(.timestamp) \(.message)"'

# Replace env placeholder in config
sed "s/\${DB_HOST}/${DB_HOST}/g" config.template > config.yaml

# Find and delete files older than 30 days
find /var/log -name "*.log" -mtime +30 -delete
```

---

## 🔗 Related Notes

- [[curl — HTTP Client]]
- [[Networking CLI Tools]]
- [[Shell Scripting Basics]]
- [[Spring Boot Logs]]

---
*Last updated: 2026-03 | Tools: grep, sed, awk, find, jq*
