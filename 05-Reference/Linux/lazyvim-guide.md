# LazyVim Complete Guide — IDE-Like Setup

> **Target**: LazyVim with default config, making it behave like a full IDE.
> All keymaps listed are **default LazyVim bindings** unless noted.

---

## Table of Contents

1. [What is LazyVim?](#1-what-is-lazyvim)
2. [Installation & First Boot](#2-installation--first-boot)
3. [Understanding the UI Layout](#3-understanding-the-ui-layout)
4. [Opening Projects (The Right Way)](#4-opening-projects-the-right-way)
5. [File Explorer — Neo-tree](#5-file-explorer--neo-tree)
   - Showing hidden files
   - Navigation
   - File operations
6. [Buffers, Windows & Tabs](#6-buffers-windows--tabs)
7. [Fuzzy Finding — Telescope](#7-fuzzy-finding--telescope)
8. [LSP — Language Intelligence](#8-lsp--language-intelligence)
   - Go to definition, references, hover
   - Diagnostics
   - Code actions
9. [Autocompletion — nvim-cmp](#9-autocompletion--nvim-cmp)
10. [Treesitter — Syntax & Motions](#10-treesitter--syntax--motions)
11. [Git Integration](#11-git-integration)
12. [Terminal Inside Neovim](#12-terminal-inside-neovim)
13. [Which-Key — Discovering Keymaps](#13-which-key--discovering-keymaps)
14. [Status Bar & Breadcrumbs](#14-status-bar--breadcrumbs)
15. [Search & Replace](#15-search--replace)
16. [Folding](#16-folding)
17. [Useful LazyVim Extras to Enable](#17-useful-lazyvim-extras-to-enable)
18. [Plugin Manager — lazy.nvim](#18-plugin-manager--lazynvim)
19. [Quick Config Location Reference](#19-quick-config-location-reference)
20. [Cheatsheet — Most Used Keymaps](#20-cheatsheet--most-used-keymaps)

---

## 1. What is LazyVim?

LazyVim is a **Neovim configuration framework** built on top of `lazy.nvim` (the plugin manager). Instead of building your config from scratch, LazyVim gives you a sane, well-structured default setup with:

- LSP (Language Server Protocol) — autocomplete, diagnostics, go-to-def
- Treesitter — advanced syntax highlighting
- Telescope — fuzzy finder
- Neo-tree — file explorer
- Which-key — keymap discovery
- Git signs and blame
- A beautiful status line (lualine)
- Dozens of optional "extras" you can enable

Your config lives at `~/.config/nvim/`. LazyVim's own source is installed as a plugin — you never edit it directly. You only touch files in `~/.config/nvim/lua/`.

---

## 2. Installation & First Boot

### Prerequisites

```bash
# Neovim 0.9+ (ideally nightly or 0.10+)
nvim --version

# Required tools
git --version
node --version       # for many LSPs
npm --version
python3 --version
pip3 --version

# Optional but strongly recommended
fd --version         # faster file finding (fd-find)
ripgrep --version    # rg — faster grep for Telescope
lazygit --version    # git TUI integration
```

Install missing ones (Arch Linux):

```bash
sudo pacman -S fd ripgrep lazygit
```

### Install LazyVim

```bash
# Back up old config if any
mv ~/.config/nvim ~/.config/nvim.bak
mv ~/.local/share/nvim ~/.local/share/nvim.bak

# Clone LazyVim starter
git clone https://github.com/LazyVim/starter ~/.config/nvim

# Remove the .git folder so you can version control your own config
rm -rf ~/.config/nvim/.git
```

Now open Neovim:

```bash
nvim
```

On first launch, `lazy.nvim` will automatically download and install all plugins. Wait for it to finish. You'll see a progress UI. After it completes, press `q` to close the lazy window, then restart Neovim.

---

## 3. Understanding the UI Layout

When you open a project in LazyVim, a typical IDE layout looks like this:

```
┌─────────────────────────────────────────────────────┐
│  bufferline (open file tabs at top)                 │
├──────────────┬──────────────────────────────────────┤
│              │                                      │
│   Neo-tree   │         Editor Window(s)             │
│  (file tree) │                                      │
│              │                                      │
│              │                                      │
├──────────────┴──────────────────────────────────────┤
│  lualine (status bar — mode, file, git, LSP errors) │
└─────────────────────────────────────────────────────┘
```

**Key concepts:**

| Term      | Meaning                                                     |
|-----------|-------------------------------------------------------------|
| Buffer    | An open file in memory (not necessarily visible)            |
| Window    | A viewport showing a buffer (you can split these)           |
| Tab       | A collection of windows (rarely needed with bufferline)     |
| Neo-tree  | Left sidebar file explorer                                  |
| Telescope | Floating popup for fuzzy searching everything               |

---

## 4. Opening Projects (The Right Way)

### Method 1 — Open from terminal (recommended)

Always `cd` into your project root before opening Neovim. This sets the working directory correctly and makes Telescope, LSP, and Neo-tree all work relative to your project.

```bash
cd ~/projects/codedash
nvim .
```

Opening `nvim .` or just `nvim` from the project root is the standard workflow.

### Method 2 — From inside Neovim

If you're already in Neovim and want to switch projects:

```
<leader>fp    → Recently opened files (`:Telescope oldfiles`)
```

Or change the working directory manually:

```vim
:cd ~/projects/other-project
```

Then reopen Neo-tree: `<leader>e`

### Method 3 — Dashboard (startup screen)

When you open `nvim` with no arguments, LazyVim shows a **dashboard**. From here you can:

| Key  | Action                   |
|------|--------------------------|
| `f`  | Find file (Telescope)    |
| `r`  | Recent files             |
| `g`  | Find in files (grep)     |
| `c`  | Open config (`~/.config/nvim`) |
| `q`  | Quit                     |

> **Best practice**: Always open Neovim from the project root in your terminal. Don't think of it like VS Code where you "Open Folder" from inside the app.

---

## 5. File Explorer — Neo-tree

Neo-tree is the file tree sidebar (like VS Code's Explorer panel).

### Opening and Closing

| Keymap      | Action                                 |
|-------------|----------------------------------------|
| `<leader>e` | Toggle Neo-tree (focus or open)        |
| `<leader>E` | Focus Neo-tree on the current file     |

`<leader>` is **Space** by default in LazyVim.

So: **Space → e** opens the file tree.

### Navigating the Tree

| Key         | Action                                           |
|-------------|--------------------------------------------------|
| `j` / `k`   | Move down / up                                   |
| `h`         | Collapse folder / go to parent                   |
| `l`         | Expand folder / open file                        |
| `Enter`     | Open file in current window                      |
| `<C-s>`     | Open file in horizontal split                    |
| `<C-v>`     | Open file in vertical split                      |
| `<C-t>`     | Open file in new tab                             |
| `P`         | Preview file (peek without switching focus)      |

### Showing Hidden Files (Dotfiles)

By default, Neo-tree hides dotfiles (`.env`, `.git`, `.gitignore`, etc.).

**Toggle hidden files while inside Neo-tree:**

Press `H` (capital H) inside the Neo-tree panel.

This toggles visibility of:
- Files starting with `.` (dotfiles)
- Files in the `.gitignore`

> Press `H` again to hide them again.

**Make hidden files visible permanently** (add to your config):

Create or edit `~/.config/nvim/lua/plugins/neo-tree.lua`:

```lua
return {
  "nvim-neo-tree/neo-tree.nvim",
  opts = {
    filesystem = {
      filtered_items = {
        visible = true,       -- Show hidden files by default
        hide_dotfiles = false,
        hide_gitignored = false,
        hide_hidden = false,  -- Linux: files starting with .
      },
    },
  },
}
```

Restart Neovim. Hidden files will now always be visible (shown in a dimmed color).

### File Operations Inside Neo-tree

| Key   | Action                              |
|-------|-------------------------------------|
| `a`   | Add / create a new file             |
| `A`   | Add / create a new directory        |
| `d`   | Delete file (with confirmation)     |
| `r`   | Rename file                         |
| `c`   | Copy file                           |
| `m`   | Move file (cut)                     |
| `p`   | Paste                               |
| `y`   | Copy filename to clipboard          |
| `Y`   | Copy absolute path to clipboard     |
| `R`   | Refresh the tree                    |
| `?`   | Show all Neo-tree keymaps           |

### Navigate to the Current File in the Tree

If you're editing a file deep in your project and want to find it in the tree:

```
<leader>E
```

This opens Neo-tree and **focuses on the currently open file** in the tree.

### Switching Between Tree and Editor

- Press `<leader>e` or click in the tree to focus it
- Press `<C-w>w` or `<C-w>l` to jump back to the editor
- Or simply click in the editor window

---

## 6. Buffers, Windows & Tabs

### Buffers (Open Files)

LazyVim shows open buffers as tabs in the **bufferline** at the top.

| Keymap         | Action                          |
|----------------|---------------------------------|
| `<S-h>`        | Go to previous buffer           |
| `<S-l>`        | Go to next buffer               |
| `<leader>bd`   | Delete (close) current buffer   |
| `<leader>bo`   | Close all other buffers         |
| `<leader>bp`   | Pin buffer                      |
| `<leader>bb`   | Switch to other/last buffer     |

`<S-h>` means **Shift+h**.

### Splitting Windows

| Keymap       | Action                          |                                 |
| ------------ | ------------------------------- | ------------------------------- |
| `<leader>    | `                               | Split vertically (side by side) |
| `<leader>-`  | Split horizontally (top/bottom) |                                 |
| `<leader>wd` | Delete/close current window     |                                 |
| `<leader>ww` | Jump to other window            |                                 |

### Moving Between Windows

| Keymap     | Action                   |
|------------|--------------------------|
| `<C-h>`    | Move to left window      |
| `<C-j>`    | Move to bottom window    |
| `<C-k>`    | Move to top window       |
| `<C-l>`    | Move to right window     |

### Resizing Windows

| Keymap         | Action               |
|----------------|----------------------|
| `<C-Up>`       | Increase height      |
| `<C-Down>`     | Decrease height      |
| `<C-Left>`     | Decrease width       |
| `<C-Right>`    | Increase width       |

---

## 7. Fuzzy Finding — Telescope

Telescope is the Swiss Army knife of navigation. It opens a floating popup where you type and results filter in real time.

### Finding Files

| Keymap         | Action                                        |
|----------------|-----------------------------------------------|
| `<leader><space>` | Find files in project (respects .gitignore) |
| `<leader>ff`   | Find files (same as above)                    |
| `<leader>fr`   | Recent files                                  |
| `<leader>fR`   | Recent files (cwd only)                       |

Inside the Telescope popup:
- Type to filter
- `<C-j>` / `<C-k>` — move up/down
- `Enter` — open selected file
- `<C-s>` — open in horizontal split
- `<C-v>` — open in vertical split
- `<C-t>` — open in new tab
- `<Esc>` — close

### Searching File Contents (Grep)

| Keymap       | Action                                       |
|--------------|----------------------------------------------|
| `<leader>fg` | Live grep — search text across all files     |
| `<leader>/`  | Grep in current file (fuzzy)                 |
| `<leader>sw` | Search word under cursor across project      |

This uses **ripgrep** (`rg`) under the hood — make sure it's installed.

### Searching Everything Else

| Keymap         | Action                              |
|----------------|-------------------------------------|
| `<leader>fb`   | Open buffers                        |
| `<leader>fh`   | Help tags (Neovim docs)             |
| `<leader>fc`   | Find config files                   |
| `<leader>sk`   | Search keymaps                      |
| `<leader>sc`   | Search commands                     |
| `<leader>sd`   | Search diagnostics (LSP errors)     |
| `<leader>ss`   | Search document symbols (LSP)       |
| `<leader>sS`   | Search workspace symbols (LSP)      |

---

## 8. LSP — Language Intelligence

LSP gives you IDE features: autocompletion, go-to-definition, find references, inline errors, code actions.

### Installing Language Servers — Mason

LazyVim uses **Mason** to manage language servers, linters, and formatters.

```
<leader>cm   → Open Mason UI
```

Inside Mason, press `i` on any server to install it. Common ones:

| Language   | Server name         |
|------------|---------------------|
| Java       | `jdtls`             |
| Python     | `pyright`           |
| TypeScript | `ts_ls`             |
| JavaScript | `ts_ls`             |
| Lua        | `lua_ls`            |
| Bash       | `bashls`            |
| CSS/HTML   | `cssls`, `html`     |
| JSON       | `jsonls`            |
| YAML       | `yamlls`            |
| Rust       | `rust_analyzer`     |
| Go         | `gopls`             |

LazyVim auto-installs many of these when you first open a file of that type (via `mason-lspconfig`).

### LSP Keymaps (active when LSP is attached)

| Keymap       | Action                                          |
|--------------|-------------------------------------------------|
| `gd`         | Go to definition                                |
| `gD`         | Go to declaration                               |
| `gr`         | Go to references (opens list)                   |
| `gI`         | Go to implementation                            |
| `gy`         | Go to type definition                           |
| `K`          | Hover docs (show type, signature, docstring)    |
| `<C-k>`      | Signature help (in insert mode)                 |
| `<leader>ca` | Code action (fix, import, refactor)             |
| `<leader>cr` | Rename symbol (renames across all files)        |
| `<leader>cf` | Format file / selection                         |
| `<leader>cd` | Show diagnostic for current line (float)        |
| `]d`         | Next diagnostic                                 |
| `[d`         | Previous diagnostic                             |
| `]e`         | Next error                                      |
| `[e`         | Previous error                                  |
| `]w`         | Next warning                                    |
| `[w`         | Previous warning                                |

### Diagnostics Panel

```
<leader>xx   → Open Trouble (diagnostics list for entire project)
<leader>xd   → Diagnostics for current document only
<leader>xw   → Workspace diagnostics
<leader>xl   → Location list
<leader>xq   → Quickfix list
```

Trouble is a panel at the bottom showing all LSP errors and warnings — like VS Code's Problems panel.

---

## 9. Autocompletion — nvim-cmp

Autocompletion appears automatically as you type. It's powered by `nvim-cmp` with sources from LSP, snippets (LuaSnip), and buffer words.

| Key          | Action                                         |
|--------------|------------------------------------------------|
| `<C-Space>`  | Force open completion menu                     |
| `<Tab>`      | Select next item / expand snippet              |
| `<S-Tab>`    | Select previous item                           |
| `<CR>`       | Confirm selected item                          |
| `<C-e>`      | Close completion menu                          |
| `<C-d>`      | Scroll docs down                               |
| `<C-u>`      | Scroll docs up                                 |

When a completion item has a **snippet** (e.g. a function with parameters), `<Tab>` jumps between the snippet placeholders.

---

## 10. Treesitter — Syntax & Motions

Treesitter provides deep, accurate syntax highlighting and text objects based on the actual AST of your code.

### Installing Language Parsers

```vim
:TSInstall java python typescript javascript lua bash
```

Or check what's installed:

```vim
:TSInstallInfo
```

LazyVim auto-installs parsers for many languages when you open files.

### Text Objects (very powerful)

These work with operators like `d`, `c`, `v`, `y`:

| Keymap   | Selects                                  |
|----------|------------------------------------------|
| `vaf`    | Select around function (whole function)  |
| `vif`    | Select inside function (body only)       |
| `vac`    | Select around class                      |
| `vic`    | Select inside class                      |
| `vaa`    | Select around argument                   |
| `via`    | Select inside argument                   |

Example: `daf` — delete entire function. `cif` — change inside function body.

### Incremental Selection

| Keymap       | Action                             |
|--------------|------------------------------------|
| `<C-space>`  | Start / expand selection by node   |
| `<BS>`       | Shrink selection                   |

Press `<C-space>` repeatedly to expand selection up the AST (word → expression → statement → block → function → file).

---

## 11. Git Integration

LazyVim includes **gitsigns** (inline hunks) and integrates with **lazygit**.

### Gitsigns (inline git changes)

Modified lines show colored signs in the left gutter:
- `│` green — added line
- `│` red — deleted line
- `│` orange — modified line

| Keymap         | Action                                   |
|----------------|------------------------------------------|
| `]h`           | Next hunk (change)                       |
| `[h`           | Previous hunk                            |
| `<leader>ghs`  | Stage hunk under cursor                  |
| `<leader>ghr`  | Reset hunk (undo change)                 |
| `<leader>ghS`  | Stage entire file                        |
| `<leader>ghR`  | Reset entire file                        |
| `<leader>ghp`  | Preview hunk (floating diff)             |
| `<leader>ghb`  | Blame current line (inline)              |
| `<leader>ghB`  | Full blame for file                      |
| `<leader>ghd`  | Diff this file against index             |
| `<leader>ghD`  | Diff against last commit                 |

### LazyGit (full Git TUI)

```
<leader>gg    → Open LazyGit (full-screen git interface)
<leader>gG    → Open LazyGit for current file
```

LazyGit must be installed separately:

```bash
sudo pacman -S lazygit   # Arch
```

Inside LazyGit:
- `s` — stage file
- `c` — commit
- `P` — push
- `p` — pull
- `q` — quit

---

## 12. Terminal Inside Neovim

LazyVim uses **toggleterm** or the built-in terminal.

### Floating Terminal

| Keymap       | Action                           |
|--------------|----------------------------------|
| `<C-/>`      | Toggle floating terminal         |
| `<C-\>`      | Toggle floating terminal (alt)   |

In terminal mode, to go back to normal mode: press `<C-\><C-n>` or just close with `<C-/>`

### Multiple Terminals

```
<leader>ft   → New terminal (floating)
<leader>fT   → New terminal in current directory
```

Inside the terminal, it behaves like a normal terminal. You can run:

```bash
# your shell commands, start servers, compile, etc.
```

### Splitting a Terminal

```vim
:split | term      " horizontal split with terminal
:vsplit | term     " vertical split with terminal
```

Or use the keymaps:

```
<leader>-   split window, then <C-/> for terminal there
```

---

## 13. Which-Key — Discovering Keymaps

**Which-key** is the keymap discovery system. When you press `<leader>` (Space) and wait ~1 second, a popup appears showing all available next keys and what they do.

```
Press Space → wait → popup shows all leader keymaps
Press Space g → shows all git keymaps
Press Space c → shows all code/LSP keymaps
```

This is how you explore and learn keymaps without memorizing everything upfront. Use it constantly when starting out.

You can also search keymaps:

```
<leader>sk   → Search all keymaps with Telescope
```

---

## 14. Status Bar & Breadcrumbs

### Lualine (bottom status bar)

The status bar shows (left to right):
- **Mode** — NORMAL / INSERT / VISUAL / etc.
- **Git branch** — current branch name
- **Git diff** — +added / ~changed / -deleted
- **Filename** — with modified indicator
- **LSP diagnostics** — error/warning counts
- **Filetype**
- **Progress** — line:column, percentage

### Breadcrumbs (barbecue)

At the top of each buffer, LazyVim shows a **breadcrumb** like:

```
src > main > java > com > codedash > service > UserService > getUserById
```

This tells you exactly where your cursor is in the code structure. It updates as you move around.

---

## 15. Search & Replace

### Search in Current File

| Keymap   | Action                                 |
|----------|----------------------------------------|
| `/`      | Search forward                         |
| `?`      | Search backward                        |
| `n`      | Next match                             |
| `N`      | Previous match                         |
| `*`      | Search word under cursor (forward)     |
| `#`      | Search word under cursor (backward)    |
| `<Esc>`  | Clear search highlight                 |

LazyVim auto-clears highlights when you move.

### Search & Replace in File

```vim
:%s/old_word/new_word/g      " replace all in file
:%s/old_word/new_word/gc     " replace with confirmation each time
:s/old/new/g                 " replace in current line only
```

In visual mode, first select lines, then:

```vim
:s/old/new/g                 " replace only in selected lines
```

### Project-Wide Search & Replace

Using Telescope + quickfix:

1. `<leader>fg` — live grep for the word
2. `<C-q>` inside Telescope — send all results to quickfix list
3. `:cfdo %s/old/new/g | update` — replace in all quickfix files

Or use the **grug-far** plugin (included in LazyVim extras):

```
<leader>sr   → Open search & replace panel (grug-far)
```

---

## 16. Folding

LazyVim uses **ufo** (ultra fold) for smart folding based on LSP/Treesitter.

| Keymap   | Action                              |
|----------|-------------------------------------|
| `zc`     | Close fold under cursor             |
| `zo`     | Open fold under cursor              |
| `za`     | Toggle fold under cursor            |
| `zM`     | Close ALL folds in file             |
| `zR`     | Open ALL folds in file              |
| `zK`     | Peek fold contents without opening  |

`zR` is very useful after opening a file — it expands everything.

---

## 17. Useful LazyVim Extras to Enable

LazyVim has optional "extras" — pre-configured plugin bundles for specific use cases. Enable them in `~/.config/nvim/lua/config/lazy.lua`.

### How to enable extras

Open `~/.config/nvim/lua/config/lazy.lua`, find the `spec` table, and add imports:

```lua
{ import = "lazyvim.plugins.extras.lang.java" },
{ import = "lazyvim.plugins.extras.lang.typescript" },
{ import = "lazyvim.plugins.extras.lang.python" },
{ import = "lazyvim.plugins.extras.ui.mini-animate" },
{ import = "lazyvim.plugins.extras.editor.aerial" },
```

### Recommended Extras

| Extra                              | What it adds                                    |
|------------------------------------|-------------------------------------------------|
| `lang.java`                        | Full Java support (jdtls, test runner)          |
| `lang.typescript`                  | TypeScript/JS (ts_ls, prettier)                 |
| `lang.python`                      | Python (pyright, black, isort)                  |
| `lang.markdown`                    | Markdown preview, rendering                     |
| `lang.sql`                         | SQL syntax, formatting                          |
| `lang.docker`                      | Dockerfile support                              |
| `lang.yaml`                        | YAML with schema validation                     |
| `editor.aerial`                    | Code outline sidebar (symbols tree)             |
| `editor.harpoon2`                  | Mark and jump between frequent files            |
| `editor.illuminate`                | Highlight other uses of word under cursor       |
| `editor.refactoring`               | Refactoring operations                          |
| `ui.mini-animate`                  | Smooth cursor animations                        |
| `formatting.prettier`              | Prettier formatter for JS/TS/CSS/HTML           |
| `linting.eslint`                   | ESLint integration                              |

### Browse all available extras

```vim
:LazyExtras
```

This opens an interactive UI where you can toggle extras on/off without editing config files manually.

---

## 18. Plugin Manager — lazy.nvim

LazyVim's plugin manager is `lazy.nvim`. Access it with:

```
<leader>l    → Open lazy.nvim UI
```

### Inside the lazy.nvim UI

| Key   | Action                         |
|-------|--------------------------------|
| `I`   | Install missing plugins        |
| `U`   | Update all plugins             |
| `S`   | Sync (install + update)        |
| `X`   | Clean (remove unused plugins)  |
| `C`   | Check for updates              |
| `L`   | Show changelog for plugin      |
| `?`   | Help                           |
| `q`   | Close                          |

### Checking LazyVim version / health

```vim
:LazyHealth     " check all plugin health
:checkhealth    " general Neovim health check
```

Run `:checkhealth` after setup to find any missing dependencies.

---

## 19. Quick Config Location Reference

```
~/.config/nvim/
├── lua/
│   ├── config/
│   │   ├── autocmds.lua      ← Custom autocommands
│   │   ├── keymaps.lua       ← Your custom keymaps
│   │   ├── lazy.lua          ← Plugin list + extras
│   │   └── options.lua       ← Vim options (line numbers, etc.)
│   └── plugins/
│       ├── neo-tree.lua      ← Override Neo-tree config
│       ├── telescope.lua     ← Override Telescope config
│       └── (any-plugin).lua  ← Add/override any plugin
└── init.lua                  ← Entry point (don't touch usually)
```

### Adding custom keymaps

Edit `~/.config/nvim/lua/config/keymaps.lua`:

```lua
local map = vim.keymap.set

-- Example: save with Ctrl+S
map("n", "<C-s>", "<cmd>w<cr>", { desc = "Save file" })
map("i", "<C-s>", "<Esc><cmd>w<cr>", { desc = "Save file" })

-- Example: close buffer with Alt+w
map("n", "<A-w>", "<cmd>bd<cr>", { desc = "Close buffer" })
```

### Setting Vim options

Edit `~/.config/nvim/lua/config/options.lua`:

```lua
local opt = vim.opt

opt.relativenumber = true    -- Relative line numbers (great for jumps)
opt.scrolloff = 8            -- Keep 8 lines visible above/below cursor
opt.wrap = false             -- No line wrapping
opt.tabstop = 4              -- 4-space tabs
opt.shiftwidth = 4
opt.colorcolumn = "100"      -- Show column guide at 100 chars
```

---

## 20. Cheatsheet — Most Used Keymaps

### Navigation

| Keymap          | Action                            |
|-----------------|-----------------------------------|
| `<leader><space>` | Find file in project            |
| `<leader>fg`    | Grep across project               |
| `<leader>e`     | Toggle file explorer              |
| `<leader>E`     | Focus file in explorer            |
| `<S-h>` / `<S-l>` | Previous / next buffer         |
| `<C-h/j/k/l>`   | Move between windows              |
| `gd`            | Go to definition                  |
| `gr`            | Find references                   |
| `K`             | Hover documentation               |

### Editing

| Keymap        | Action                              |
|---------------|-------------------------------------|
| `<leader>ca`  | Code action                         |
| `<leader>cr`  | Rename symbol                       |
| `<leader>cf`  | Format file                         |
| `gcc`         | Toggle line comment                 |
| `gc` (visual) | Toggle comment on selection         |
| `<C-space>`   | Force autocomplete / expand selection |

### Git

| Keymap        | Action                  |
|---------------|-------------------------|
| `<leader>gg`  | Open LazyGit            |
| `]h` / `[h`   | Next / previous hunk    |
| `<leader>ghp` | Preview hunk            |
| `<leader>ghs` | Stage hunk              |

### UI Toggles

| Keymap         | Action                        |
|----------------|-------------------------------|
| `<leader>uf`   | Toggle auto-format on save    |
| `<leader>us`   | Toggle spell check            |
| `<leader>uw`   | Toggle word wrap              |
| `<leader>ul`   | Toggle line numbers           |
| `<leader>ud`   | Toggle diagnostics            |
| `<leader>uh`   | Toggle inlay hints (LSP)      |
| `<C-/>`        | Toggle terminal               |

### Utility

| Keymap        | Action                        |
|---------------|-------------------------------|
| `<leader>l`   | Open lazy.nvim (plugin mgr)   |
| `<leader>cm`  | Open Mason (LSP installer)    |
| `<leader>xx`  | Open Trouble (diagnostics)    |
| `<leader>sk`  | Search keymaps                |
| `<leader>qq`  | Quit all                      |

---

## Tips for Efficient Workflow

**1. Stay in Normal mode as much as possible.**
Learn motions: `w`, `b`, `e`, `f`, `t`, `%`, `{`, `}`, `gg`, `G`, `<C-d>`, `<C-u>`.

**2. Use `<leader>` + wait to discover.**
Never guess keymaps — press Space and read the which-key popup.

**3. `gd` + `<C-o>` is your best friend.**
`gd` to jump to definition, `<C-o>` to jump back. This is the core navigation loop.

**4. Prefer Telescope over file explorer for opening files.**
`<leader><space>` is faster than browsing the tree once you know filenames.

**5. Keep Neo-tree open for project structure, Telescope for navigation.**
They serve different purposes — use both.

**6. Use `:checkhealth` when things break.**
It shows exactly what's missing or misconfigured.

---

*Guide covers LazyVim with default configuration as of 2024–2025. Keymaps are defaults and may differ if you've customized `keymaps.lua`.*
