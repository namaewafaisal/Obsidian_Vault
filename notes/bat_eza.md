# 🦇 bat & eza — Better cat and ls

> [!abstract] Overview
> `bat` is a `cat` replacement with syntax highlighting, line numbers, and git integration. `eza` is a modern `ls` replacement with colors, icons, and git status. Both are drop-in upgrades.

**Tags:** #cli #linux #tools #bat #eza #productivity

---

## 🦇 bat — Better cat

### Basic Usage

```bash
# View a file (syntax highlighted)
bat file.py
bat src/main/java/MovieService.java

# Show line numbers (on by default)
bat -n file.txt

# No line numbers
bat --style=plain file.txt

# Show specific line range
bat -r 10:25 file.py

# Show multiple files
bat file1.py file2.py

# Pipe output (auto-disables decorations)
cat file.py | bat

# Force color output even when piping
bat --color=always file.py | less -R
```

### Styles

```bash
# Full style (default): line numbers + git + header + grid
bat --style=full file.py

# Minimal
bat --style=plain file.py

# Just numbers
bat --style=numbers file.py

# Numbers + git changes
bat --style=numbers,changes file.py
```

### Language Override

```bash
# Force syntax highlighting language
bat -l json response.txt
bat -l yaml config.txt
bat -l java Main.txt

# List supported languages
bat --list-languages
```

### Themes

```bash
# List themes
bat --list-themes

# Use a theme
bat --theme="Catppuccin Mocha" file.py

# Set default theme in config
mkdir -p ~/.config/bat
echo '--theme="Catppuccin Mocha"' >> ~/.config/bat/config
```

### Config File (~/.config/bat/config)

```
--theme="Catppuccin Mocha"
--style=numbers,changes,header
--italic-text=always
--map-syntax "*.conf:INI"
--map-syntax "*.env:Dotenv"
```

### Aliases

```bash
# Add to ~/.zshrc
alias cat='bat'
alias catp='bat --style=plain'   # no decorations

# Use bat as man pager
export MANPAGER="sh -c 'col -bx | bat -l man -p'"
```

### bat with Other Tools

```bash
# bat as fzf preview
fzf --preview 'bat --color=always {}'

# bat as git diff pager
git diff | bat

# Highlight specific lines
bat --highlight-line 15 file.py
bat --highlight-line 10:20 file.py
```

---

## 📂 eza — Better ls

### Basic Usage

```bash
# List files
eza

# Long format
eza -l

# Show hidden files
eza -a

# Long + hidden
eza -la

# Sort by modified time
eza -l --sort=modified

# Sort by size
eza -l --sort=size

# Reverse sort
eza -l --sort=size --reverse
```

### Tree View

```bash
# Tree view (like tree command)
eza --tree

# Tree with depth limit
eza --tree --level=2

# Tree long format
eza --tree -l --level=3

# Tree ignoring .git
eza --tree --git-ignore
```

### Icons & Colors

```bash
# Show file type icons (needs Nerd Font)
eza --icons

# Long with icons
eza -la --icons

# No colors
eza --no-color
```

### Git Integration

```bash
# Show git status for each file
eza -l --git

# Git + icons
eza -la --git --icons
```

### Headers & Extra Info

```bash
# Show column headers
eza -l --header

# Show file size in bytes
eza -l --bytes

# Show created/modified/accessed times
eza -l --time-style=long-iso

# Show all timestamps
eza -l --accessed --created --modified
```

### Aliases

```bash
# Add to ~/.zshrc
alias ls='eza --icons'
alias ll='eza -la --icons --git'
alias lt='eza --tree --level=2 --icons'
alias la='eza -a --icons'
alias l='eza -l --icons'
```

---

## 🔗 Related Notes
- [[fzf — Fuzzy Finder]]
- [[ripgrep — Fast Grep]]
- [[fd — Fast Find]]

---
*Last updated: 2026-03 | Tools: bat 0.26, eza 0.23*
