# ⚡ ripgrep & fd — Fast grep and find

> [!abstract] Overview
> `ripgrep` (`rg`) is a faster, smarter grep that respects `.gitignore`. `fd` is a faster, friendlier `find`. Both are Rust-based drop-in replacements you'll never go back from.

**Tags:** #cli #linux #tools #ripgrep #fd #rust #productivity

---

## ⚡ ripgrep (rg)

### Why ripgrep over grep?
- Faster (uses regex engine from Rust)
- Respects `.gitignore` by default — won't search `target/`, `node_modules/`, `.git/`
- Searches binary files smarter
- Better default output (colored, grouped by file)

### Basic Usage

```bash
# Search for pattern
rg "TODO"

# Search in specific directory
rg "TODO" src/

# Search in specific file
rg "TODO" Main.java

# Case insensitive
rg -i "error"

# Show line numbers (on by default)
rg "pattern" file.txt

# Count matches per file
rg -c "TODO" src/

# Show only filenames
rg -l "TODO"

# Show only matches (not full lines)
rg -o "[0-9]+" file.txt
```

### File Type Filtering

```bash
# Search only Java files
rg "TODO" -t java

# Search only in specific extension
rg "TODO" -g "*.java"

# Exclude a file type
rg "TODO" -T java

# List supported types
rg --type-list
```

### Context Lines

```bash
# 3 lines before and after match
rg -C 3 "Exception"

# 3 lines before
rg -B 3 "Exception"

# 3 lines after
rg -A 3 "Exception"
```

### Search Options

```bash
# Fixed string (no regex)
rg -F "some.method()"

# Search hidden files too
rg --hidden "TODO"

# Include files ignored by .gitignore
rg --no-ignore "TODO"

# Search inside compressed files
rg -z "pattern" file.gz

# Multi-line match
rg -U "pattern\npattern"

# Invert match
rg -v "DEBUG"

# Word boundary match
rg -w "error"    # matches "error" but not "errors"
```

### Replacement (non-destructive)

```bash
# Preview replacement
rg "oldMethod" --replace "newMethod" src/

# Replace and write to file (pipe to sed for in-place)
rg -l "oldMethod" | xargs sed -i 's/oldMethod/newMethod/g'
```

### Output Formatting

```bash
# No color
rg --no-color "TODO"

# JSON output
rg --json "TODO" | jq .

# Group results by file (default)
rg "TODO"

# No grouping (grep-like)
rg --no-heading "TODO"

# Compact output
rg -H "TODO"
```

### Practical Examples

```bash
# Find all TODOs in Java project (ignores target/ automatically)
rg "TODO" src/

# Find all usages of a method
rg "movieService\.getAll" src/

# Find all Spring annotations
rg "@(GetMapping|PostMapping|RestController)" -t java src/

# Find hardcoded passwords/secrets
rg -i "password\s*=\s*['\"]" src/

# Count lines of code by type
rg -c "" -t java | awk -F: '{sum += $2} END {print sum " lines of Java"}'

# Search and open results in nvim
rg -l "TODO" | xargs nvim
```

---

## 🔍 fd — Fast Find

### Why fd over find?
- Simple syntax — `fd PATTERN` instead of `find . -name "*PATTERN*"`
- Respects `.gitignore` by default
- Colored output
- Faster

### Basic Usage

```bash
# Find files matching pattern
fd main

# Find in specific directory
fd main src/

# Find by exact name
fd -e java Main    # files named Main.java

# Case insensitive (default unless pattern has uppercase)
fd main
fd Main    # case sensitive

# Force case insensitive
fd -i MAIN
```

### File Type Filtering

```bash
# Files only
fd -t f main

# Directories only
fd -t d target

# Symlinks only
fd -t l

# Executables only
fd -t x
```

### Extension Filtering

```bash
# Find by extension
fd -e java
fd -e xml
fd -e properties

# Find Java files named Main
fd -e java Main
```

### Hidden & Ignored Files

```bash
# Include hidden files
fd -H .env

# Include gitignored files
fd -I target

# Both
fd -HI .class
```

### Execute on Results

```bash
# Execute command on each result
fd -e java -x wc -l    # count lines of each Java file

# Execute in parallel (faster)
fd -e log -X gzip      # gzip all .log files

# Pipe to xargs
fd -e class | xargs rm    # delete all .class files
```

### Depth Control

```bash
# Max depth
fd -d 2 main    # only look 2 levels deep

# Min depth
fd --min-depth 2 main
```

### Practical Examples

```bash
# Find all Java files
fd -e java

# Find all config files
fd -e properties -e yaml -e yml

# Delete all .class files
fd -e class -X rm

# Find all test files
fd "Test" -e java

# Find recently modified files
fd -e java --changed-within 1day

# Find large files
fd -e log --size +10m

# Open all TODOs (combine with rg)
fd -e java | xargs rg -l "TODO" | xargs nvim
```

---

## 🔗 Related Notes
- [[fzf — Fuzzy Finder]]
- [[grep — Text Search]]
- [[find — File Search]]
- [[bat — Better cat]]

---
*Last updated: 2026-03 | Tools: ripgrep 15.1, fd 10.4*
