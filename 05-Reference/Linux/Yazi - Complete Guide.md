# Yazi — Complete Guide
#tools #terminal #linux #filemanager

> Rust-based async terminal file manager. Vim keybindings. Built-in image preview on Kitty. Lua plugin system. The modern replacement for ranger.

---

## Installation

```bash
paru -S yazi
# Optional but recommended dependencies
sudo pacman -S ffmpegthumbnailer imagemagick poppler fd ripgrep fzf zoxide
```

You already have most of these. `ffmpegthumbnailer` adds video thumbnail preview. `poppler` adds PDF preview. `imagemagick` improves image handling.

---

## Shell Wrapper (important — do this first)

By default, quitting yazi doesn't change your terminal's directory. This wrapper fixes that — when you quit yazi, your shell `cd`s to wherever you were browsing.

Add to `~/.zshrc`:

```zsh
function y() {
  local tmp="$(mktemp -t "yazi-cwd.XXXXXX")"
  yazi "$@" --cwd-file="$tmp"
  if cwd="$(cat -- "$tmp")" && [ -n "$cwd" ] && [ "$cwd" != "$PWD" ]; then
    cd -- "$cwd"
  fi
  rm -f -- "$tmp"
}
```

Now use `y` instead of `yazi`. Quit with `q` and your shell is in that directory.

---

## Layout

Three-pane view:

```
[ Parent dir ] [ Current dir ] [ Preview ]
```

- **Left** — parent directory, shows where you came from
- **Middle** — current directory, where you navigate
- **Right** — live preview of the hovered file (text, image, video thumbnail, PDF page, archive contents)

---

## Navigation

| Key | Action |
|-----|--------|
| `h` | Go to parent directory |
| `j` | Move down |
| `k` | Move up |
| `l` or `Enter` | Enter directory / open file |
| `gg` | Jump to top |
| `G` | Jump to bottom |
| `H` | Go back in history |
| `L` | Go forward in history |
| `~` | Go to home directory |
| `/` | Search in current directory |
| `n` / `N` | Next / previous search result |
| `z` | Jump with zoxide (frecency-based) |
| `f` | Fuzzy find with fzf |

---

## File Operations

| Key | Action |
|-----|--------|
| `y` | Yank (copy) |
| `x` | Cut |
| `p` | Paste |
| `P` | Paste and overwrite |
| `D` | Move to trash |
| `d` | (no action by default — use `D` for delete) |
| `r` | Rename file |
| `a` | Create new file (end with `/` to create directory) |
| `Space` | Toggle select file |
| `v` | Visual select mode |
| `V` | Unselect all |
| `%` | Select all files |

---

## Opening Files

### How yazi decides what app to use

1. Yazi checks `~/.config/yazi/yazi.toml` for opener rules
2. Rules match by **file extension** (e.g. `*.pdf`) or **MIME type** (e.g. `image/*`)
3. If no custom rule matches, it falls back to `xdg-open`
4. `xdg-open` reads your system's default apps from `~/.config/mimeapps.list`

So ultimately, **your xdg/system defaults control what opens** unless you override in `yazi.toml`.

### Open with menu

| Key | Action |
|-----|--------|
| `l` or `Enter` | Open with default opener |
| `O` | Open with... (interactive picker showing all openers for this file type) |
| `Tab` | Spot file — shows MIME type and metadata |

### Check a file's MIME type

Press `Tab` on any file. This tells you exactly what MIME type yazi sees, which helps you write the right opener rule.

---

## Setting Default Apps

### Method 1 — xdg-mime (system-wide, affects everything)

```bash
# Set mpv as default for all video files
xdg-mime default mpv.desktop video/mp4
xdg-mime default mpv.desktop video/x-matroska

# Set zathura for PDF
xdg-mime default org.pwmt.zathura.desktop application/pdf

# Set nvim for text
xdg-mime default nvim.desktop text/plain

# Check current default
xdg-mime query default video/mp4
```

This affects yazi, dolphin, and everything else system-wide.

### Method 2 — yazi.toml openers (yazi-specific overrides)

Create `~/.config/yazi/yazi.toml` and add opener rules:

```toml
[opener]
# PDF → zathura
view-pdf = [
  { run = 'zathura "$1"', orphan = true, desc = "Zathura", for = "linux" }
]

# Video → mpv
play-video = [
  { run = 'mpv "$1"', orphan = true, desc = "mpv", for = "linux" }
]

# Image → imv
view-image = [
  { run = 'imv "$1"', orphan = true, desc = "imv", for = "linux" }
]

# Edit in neovim
edit = [
  { run = 'nvim "$@"', block = true, desc = "nvim", for = "linux" }
]

[open]
# Rules: which opener to use for which file type
prepend_rules = [
  { mime = "application/pdf",        use = ["view-pdf", "edit"] },
  { mime = "video/*",                use = ["play-video"] },
  { mime = "image/*",                use = ["view-image"] },
  { mime = "text/*",                 use = ["edit"] },
  { mime = "application/epub+zip",   use = ["view-pdf"] },  # zathura handles epub too
]
```

**orphan = true** → app keeps running after yazi closes (for GUI apps like imv, mpv)
**block = true** → yazi pauses and waits for the app to close (for terminal apps like nvim)

---

## File Preview (what shows in the right pane)

| File type | What you see | Requires |
|-----------|-------------|----------|
| Text / code | Syntax-highlighted content | built-in |
| Images | Actual image rendered | Kitty (you have it) |
| Video | Thumbnail frame | `ffmpegthumbnailer` |
| PDF | First page rendered | `poppler` |
| Archives (.zip .tar etc) | File listing inside | built-in |
| JSON | Pretty-printed | built-in |
| EPUB | Text content | built-in |

Since you use Kitty, image preview works natively with no extra setup — Kitty Graphics Protocol is the best image rendering method available.

---

## Tabs

| Key | Action |
|-----|--------|
| `t` | New tab (current directory) |
| `1`–`9` | Switch to tab by number |
| `[` / `]` | Previous / next tab |
| `Ctrl+c` | Close current tab |

---

## Bookmarks / Quick Jump

Yazi uses `g` as a prefix for jumps (like vim's `g`):

| Key | Action |
|-----|--------|
| `gh` | Go to `~` (home) |
| `gc` | Go to `~/.config` |
| `gd` | Go to `~/Downloads` |
| `gr` | Go to git root (with custom keymap) |

You set these in `keymap.toml` — see Configuration section below.

---

## Shell & Lazygit Integration

| Key | Action |
|-----|--------|
| `!` | Open a shell in current directory |
| `Ctrl+z` | Suspend yazi, return to shell (use `fg` to come back) |

Add this to `~/.config/yazi/keymap.toml` for lazygit:

```toml
[[mgr.prepend_keymap]]
on = ["<C-g>"]
run = "shell 'lazygit' --block"
desc = "Open lazygit"
```

---

## Set Wallpaper from Yazi (Hyprland)

Add to `~/.config/yazi/yazi.toml`:

```toml
[[opener.set-wallpaper]]
run = "hyprctl hyprpaper reload ,%s1"
for = "linux"
desc = "Set as wallpaper"

[[open.prepend_rules]]
mime = "image/*"
use = ["set-wallpaper", "open"]
```

Now pressing `O` on an image gives you "Set as wallpaper" as an option.

---

## Configuration Files

All config lives in `~/.config/yazi/`:

```
~/.config/yazi/
├── yazi.toml      ← main config (openers, layout, preview)
├── keymap.toml    ← custom keybindings
├── theme.toml     ← colors (or use a flavor instead)
├── init.lua       ← plugin initialization
├── plugins/       ← installed plugins
└── flavors/       ← installed themes
```

Create the directory and files:

```bash
mkdir -p ~/.config/yazi
touch ~/.config/yazi/yazi.toml
touch ~/.config/yazi/keymap.toml
```

You only need to write what you want to **override** — yazi merges your config on top of its defaults.

---

## Recommended keymap.toml starter

```toml
# Quick directory jumps
[[mgr.prepend_keymap]]
on = ["g", "h"]
run = "cd ~"
desc = "Go home"

[[mgr.prepend_keymap]]
on = ["g", "c"]
run = "cd ~/.config"
desc = "Go to config"

[[mgr.prepend_keymap]]
on = ["g", "d"]
run = "cd ~/Downloads"
desc = "Go to Downloads"

[[mgr.prepend_keymap]]
on = ["g", "p"]
run = "cd ~/Projects"
desc = "Go to Projects"

[[mgr.prepend_keymap]]
on = ["g", "D"]
run = "cd ~/Documents"
desc = "Go to Documents"

# Lazygit
[[mgr.prepend_keymap]]
on = ["<C-g>"]
run = "shell 'lazygit' --block"
desc = "Open lazygit"

# Shell here
[[mgr.prepend_keymap]]
on = ["!"]
run = "shell '$SHELL' --block"
desc = "Open shell here"
```

---

## Catppuccin Mocha Theme

Since you use Catppuccin Mocha everywhere:

```bash
ya pkg add yazi-rs/flavors#catppuccin-mocha
```

Then add to `~/.config/yazi/theme.toml`:

```toml
[flavor]
use = "catppuccin-mocha"
```

---

## Useful Keybindings Cheatsheet

```
Navigation          File Ops            View
──────────          ────────            ────
h  parent dir       y  yank/copy        Tab  spot (MIME info)
j  down             x  cut              z    zoxide jump
k  up               p  paste            f    fzf search
l  open/enter       D  trash            .    toggle hidden files
gg top              r  rename           ~    home
G  bottom           a  create           1-9  switch tabs
H  back             Space select        t    new tab
L  forward          %  select all       [/]  prev/next tab
/  search           v  visual select
```

---

## Related
- [[Neovim]] — editor opened from yazi with `l` on text files
- [[Lazygit]] — git TUI launched from yazi with `Ctrl+g`
- [[zoxide]] — frecency jump integrated with `z` inside yazi
- [[fzf]] — fuzzy search integrated with `f` inside yazi
- [[Kitty]] — terminal that enables native image preview
