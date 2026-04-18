# 📝 neovim — Text Editor

> [!abstract] Overview
> Neovim is a hyperextensible Vim-based editor. Modal editing, insanely fast, scriptable with Lua. Steep learning curve but highest ceiling of any editor. You have it installed — worth knowing the basics even if you mainly use VS Code.

**Tags:** #cli #linux #tools #neovim #vim #editor #productivity

---

## 🧠 Modal Editing — The Core Concept

Neovim has modes. Everything flows from this:

| Mode | How to Enter | Purpose |
|------|-------------|---------|
| **Normal** | `Esc` | Navigate, run commands |
| **Insert** | `i`, `a`, `o` | Type text |
| **Visual** | `v`, `V`, `Ctrl+v` | Select text |
| **Command** | `:` | Run editor commands |
| **Search** | `/`, `?` | Search |

> [!tip] When lost, always press `Esc` to get back to Normal mode.

---

## 🚀 Launch

```bash
nvim file.txt          # open file
nvim .                 # open directory (file browser)
nvim +10 file.txt      # open at line 10
nvim +/pattern file.txt  # open and search for pattern
nvim -d file1 file2    # diff mode
```

---

## ⌨️ Normal Mode — Navigation

### Basic Movement
| Key | Action |
|-----|--------|
| `h/j/k/l` | Left/Down/Up/Right |
| `w` / `b` | Next/previous word |
| `W` / `B` | Next/previous WORD (whitespace-separated) |
| `e` | End of word |
| `0` | Start of line |
| `$` | End of line |
| `^` | First non-blank character |
| `gg` | First line |
| `G` | Last line |
| `5G` | Go to line 5 |
| `Ctrl+d` | Scroll down half page |
| `Ctrl+u` | Scroll up half page |
| `Ctrl+f` | Scroll down full page |
| `Ctrl+b` | Scroll up full page |
| `zz` | Center current line on screen |

### Jumps
| Key | Action |
|-----|--------|
| `{` / `}` | Previous/next blank line |
| `%` | Jump to matching bracket |
| `gd` | Go to definition |
| `Ctrl+o` | Jump back |
| `Ctrl+i` | Jump forward |
| `''` | Jump to last position |

---

## ✏️ Inserting Text

| Key | Action |
|-----|--------|
| `i` | Insert before cursor |
| `I` | Insert at start of line |
| `a` | Append after cursor |
| `A` | Append at end of line |
| `o` | New line below |
| `O` | New line above |
| `s` | Delete char and insert |
| `S` / `cc` | Delete line and insert |
| `Esc` | Return to Normal |

---

## 🗑️ Deleting & Changing

| Key | Action |
|-----|--------|
| `x` | Delete character |
| `dd` | Delete line |
| `dw` | Delete word |
| `d$` / `D` | Delete to end of line |
| `d0` | Delete to start of line |
| `dG` | Delete to end of file |
| `cc` | Change entire line |
| `cw` | Change word |
| `c$` / `C` | Change to end of line |
| `r` | Replace single character |
| `R` | Replace mode |

---

## 📋 Copy & Paste (Yank & Put)

| Key | Action |
|-----|--------|
| `yy` | Yank (copy) line |
| `yw` | Yank word |
| `y$` | Yank to end of line |
| `p` | Paste after cursor |
| `P` | Paste before cursor |
| `"*p` | Paste from system clipboard |
| `"+y` | Yank to system clipboard |

---

## 🔍 Search & Replace

```vim
/pattern        " search forward
?pattern        " search backward
n               " next match
N               " previous match
*               " search word under cursor

" Replace in file
:%s/old/new/g       " replace all
:%s/old/new/gc      " confirm each
:5,10s/old/new/g    " replace in lines 5-10
```

---

## 👁️ Visual Mode

| Key | Action |
|-----|--------|
| `v` | Character-wise visual |
| `V` | Line-wise visual |
| `Ctrl+v` | Block-wise visual |
| `d` | Delete selection |
| `y` | Yank selection |
| `>` / `<` | Indent/dedent |
| `~` | Toggle case |
| `c` | Change selection |

---

## 💾 Saving & Quitting

```vim
:w              " save
:wq / ZZ        " save and quit
:q              " quit (if no changes)
:q!             " force quit (discard changes)
:wa             " save all
:qa!            " quit all force
```

---

## 🪟 Splits & Tabs

```vim
:split / :sp        " horizontal split
:vsplit / :vs       " vertical split
:tabnew             " new tab
:tabnext / gt       " next tab
:tabprev / gT       " previous tab
```

```
Ctrl+w + h/j/k/l    " navigate between splits
Ctrl+w + q          " close split
Ctrl+w + =          " equal size splits
Ctrl+w + >/<        " resize horizontally
```

---

## ⚙️ Config (~/.config/nvim/init.lua)

```lua
-- Basic settings
vim.opt.number = true           -- line numbers
vim.opt.relativenumber = true   -- relative line numbers
vim.opt.tabstop = 4
vim.opt.shiftwidth = 4
vim.opt.expandtab = true        -- spaces not tabs
vim.opt.smartindent = true
vim.opt.wrap = false
vim.opt.ignorecase = true
vim.opt.smartcase = true
vim.opt.clipboard = "unnamedplus"  -- system clipboard

-- Keymaps
vim.g.mapleader = " "           -- space as leader key
vim.keymap.set("n", "<leader>w", ":w<CR>")    -- space+w to save
vim.keymap.set("n", "<leader>q", ":q<CR>")    -- space+q to quit
vim.keymap.set("i", "jk", "<Esc>")            -- jk to exit insert
```

---

## 💡 Most Useful Commands to Learn First

```
Esc          → get back to normal
i / a        → start typing
dd           → delete line
yy / p       → copy/paste line
u            → undo
Ctrl+r       → redo
/pattern     → search
:w :q :wq    → save/quit
gg / G       → top/bottom of file
Ctrl+d/u     → scroll
.            → repeat last action (powerful)
```

---

## 🔗 Related Notes
- [[tmux — Terminal Multiplexer]]
- [[lazygit — Git TUI]]
- [[Shell Scripting Basics]]

---
*Last updated: 2026-03 | Tool: neovim 0.11.6*
