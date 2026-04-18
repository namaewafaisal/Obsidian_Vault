# 😴 lazygit — Git TUI

> [!abstract] Overview
> lazygit is a terminal UI for git. Stage hunks, resolve conflicts, rebase interactively, and manage branches — all without memorizing git commands. Complement to your git CLI knowledge, not a replacement.

**Tags:** #cli #linux #tools #git #lazygit #devops #tui

---

## 🚀 Launch

```bash
# Open in current repo
lazygit

# Open in specific path
lazygit -p ~/projects/ottplatform

# Alias
alias lg='lazygit'
```

---

## 🗺️ Layout

```
┌─────────────────┬──────────────────────────────┐
│   Status/Files  │                              │
├─────────────────│         Main Panel           │
│    Branches     │       (diff / log / etc)     │
├─────────────────│                              │
│    Commits      │                              │
├─────────────────┤                              │
│    Stash        │                              │
└─────────────────┴──────────────────────────────┘
```

Navigate panels with number keys `1-5` or `Tab`.

---

## ⌨️ Key Bindings

### Navigation
| Key | Action |
|-----|--------|
| `1-5` | Switch panels |
| `Tab` | Next panel |
| `h/l` or `←/→` | Switch tabs within panel |
| `j/k` | Move up/down in list |
| `[` / `]` | Previous/next tab |
| `?` | Show all keybindings |
| `q` | Quit |
| `x` | Open command menu |

### Files Panel (1)
| Key | Action |
|-----|--------|
| `Space` | Stage/unstage file |
| `a` | Stage/unstage all files |
| `c` | Commit staged changes |
| `C` | Commit with editor |
| `d` | View diff of file |
| `e` | Open file in editor |
| `i` | Add to .gitignore |
| `D` | Discard changes to file |
| `Enter` | View file diff / stage hunks |

### Inside File Diff (Hunk Staging)
| Key | Action |
|-----|--------|
| `Space` | Stage/unstage hunk |
| `1` | Stage single line |
| `a` | Stage all hunks |
| `d` | Discard hunk |
| `Esc` | Go back |

### Branches Panel (3)
| Key | Action |
|-----|--------|
| `Space` | Checkout branch |
| `n` | New branch |
| `d` | Delete branch |
| `r` | Rebase current onto selected |
| `M` | Merge selected into current |
| `f` | Fetch branch |
| `Enter` | View commits on branch |

### Commits Panel (4)
| Key | Action |
|-----|--------|
| `Space` | Checkout commit |
| `g` | Reset to commit |
| `r` | Reword commit message |
| `e` | Edit commit (amend) |
| `d` | Drop commit |
| `s` | Squash into previous |
| `f` | Fixup into previous |
| `c` | Cherry-pick |
| `i` | Interactive rebase from here |
| `Enter` | View commit diff |

### Stash Panel (5)
| Key | Action |
|-----|--------|
| `Space` | Apply stash |
| `g` | Pop stash (apply + remove) |
| `d` | Drop stash |
| `n` | New stash |

---

## 🔀 Interactive Rebase

1. Go to Commits panel (`4`)
2. Navigate to the commit you want to rebase from
3. Press `i` — opens interactive rebase mode
4. Use `d` (drop), `s` (squash), `r` (reword), `e` (edit) on commits
5. Press `m` to start rebase

---

## 🔧 Conflict Resolution

When you have merge conflicts:
1. Conflicted files show in Files panel
2. Press `Enter` on a conflicted file
3. Use `←/→` to pick left/right/both
4. Press `Space` to select a hunk
5. Once resolved, stage the file and commit

---

## ⚙️ Config (~/.config/lazygit/config.yml)

```yaml
gui:
  theme:
    activeBorderColor:
      - green
      - bold
    selectedLineBgColor:
      - blue
  showFileTree: true
  mouseEvents: true

git:
  paging:
    colorArg: always
    pager: delta   # if you have delta installed

keybinding:
  universal:
    quit: q
    return: <esc>
```

---

## 💡 Tips

```bash
# Open lazygit from nvim terminal split
# In nvim: :term lazygit

# Use with tmux — dedicate a window to lazygit
# Window 1: editor
# Window 2: lazygit (always open)
# Window 3: terminal

# Stage specific lines (not whole file)
# Enter file → navigate to hunk → press 1 to stage individual lines
```

---

## 🔗 Related Notes
- [[git — Advanced]]
- [[tmux — Terminal Multiplexer]]
- [[neovim — Text Editor]]

---
*Last updated: 2026-03 | Tool: lazygit 0.60*
