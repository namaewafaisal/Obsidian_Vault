# 🚀 zoxide — Smarter cd

> [!abstract] Overview
> zoxide is a smarter `cd` that learns your most-visited directories and lets you jump to them with minimal typing. Think of it as autojump/z but faster and written in Rust.

**Tags:** #cli #linux #tools #zoxide #productivity #terminal

---

## 🧠 Mental Model

```
z foo        → jump to the highest-ranked directory matching "foo"
zi foo       → fuzzy interactive pick (uses fzf)
```

zoxide tracks every directory you `cd` into and ranks them by frequency + recency. The more you visit a directory, the faster you can jump back to it.

---

## 🚀 Setup

```bash
# Install (already installed)
sudo pacman -S zoxide

# Add to ~/.zshrc (or ~/.bashrc)
eval "$(zoxide init zsh)"     # for zsh
eval "$(zoxide init bash)"    # for bash

# Replace cd entirely (optional but recommended)
eval "$(zoxide init zsh --cmd cd)"
```

After sourcing, restart your shell or `source ~/.zshrc`.

---

## Basic Usage

```bash
# Jump to a directory matching "ott"
z ott

# Jump to directory matching "spring" under "projects"
z projects spring

# Multiple terms (all must match)
z src main java

# Jump to exact path (fallback)
z /home/faisal/projects/ottplatform

# Interactive fuzzy pick
zi

# Interactive with query
zi ott
```

---

## Commands

```bash
# Show zoxide database (ranked list)
zoxide query --list

# Show top matches for a query
zoxide query ott

# Add a directory manually
zoxide add /home/faisal/projects/ottplatform

# Remove a directory from database
zoxide remove /some/old/path

# Print path without cd-ing
zoxide query spring
```

---

## ⚙️ Config

```bash
# Change score decay rate (default: 0.99 per day)
export _ZO_DECAY=0.99

# Exclude paths from tracking
export _ZO_EXCLUDE_DIRS="/tmp:$HOME"

# Max db entries (default: none)
export _ZO_MAXAGE=10000

# Use fzf for zi (default, set custom flags)
export _ZO_FZF_OPTS="--height 40% --reverse --border"
```

---

## 💡 Tips

```bash
# z with no args goes to home (like cd)
z

# If z picks the wrong dir, be more specific
z ottplatform src    # matches path containing both terms

# After a while, one letter often works
z o    # → /home/faisal/projects/ottplatform (if you visit it most)

# Use zi when unsure — shows all matches to pick from
zi
```

---

## 🔗 Related Notes
- [[fzf — Fuzzy Finder]]
- [[fd — Fast Find]]
- [[eza — Better ls]]

---
*Last updated: 2026-03 | Tool: zoxide 0.9.9*
