# 🔍 fzf — Fuzzy Finder

> [!abstract] Overview
> fzf is an interactive fuzzy finder for the terminal. Pipe anything into it and get an instant searchable list. Supercharges file search, command history, git, and more.

**Tags:** #cli #linux #tools #fzf #productivity #terminal

---

## 🧠 Mental Model

```
any command that outputs a list | fzf → interactive filter → selected output
```

fzf reads stdin, shows an interactive search UI, and prints the selected item to stdout.

---

## 🚀 Basic Usage

```bash
# Fuzzy find files in current directory
fzf

# Open selected file in editor
nvim $(fzf)

# Preview file contents while searching
fzf --preview 'cat {}'

# Better preview with bat
fzf --preview 'bat --color=always {}'

# Multi-select (Tab to select, Enter to confirm)
fzf -m

# Start with a query
fzf -q "main"

# Case-sensitive search
fzf +i

# Exact match (disable fuzzy)
fzf -e
```

---

## ⌨️ Key Bindings (inside fzf UI)

| Key | Action |
|-----|--------|
| `Ctrl+j` / `Ctrl+k` | Move down / up |
| `Tab` | Toggle select (multi-select mode) |
| `Shift+Tab` | Deselect |
| `Enter` | Confirm selection |
| `Ctrl+c` / `Esc` | Cancel |
| `Ctrl+/` | Toggle preview |
| `Ctrl+r` | History search (shell integration) |
| `Alt+c` | cd into directory (shell integration) |
| `Ctrl+t` | Paste file path (shell integration) |

---

## 🐚 Shell Integration

Add to `~/.zshrc` or `~/.bashrc`:

```bash
# Source fzf keybindings and completion
source /usr/share/fzf/key-bindings.zsh      # zsh
source /usr/share/fzf/completion.zsh        # zsh
# or for bash:
source /usr/share/fzf/key-bindings.bash
source /usr/share/fzf/completion.bash
```

This enables:
- `Ctrl+r` → fuzzy search command history
- `Ctrl+t` → fuzzy find file, paste path into command
- `Alt+c` → fuzzy find directory, cd into it

---

## 📁 File & Directory Search

```bash
# Search files (uses fd if available, faster)
export FZF_DEFAULT_COMMAND='fd --type f --hidden --follow --exclude .git'

# Find and cd into directory
cd $(fd --type d | fzf)

# Find file and open in nvim
nvim $(fd --type f | fzf --preview 'bat --color=always {}')

# Search only in src/
fzf --walker-root src/
```

---

## 📜 History Search

```bash
# Fuzzy search bash/zsh history (better than Ctrl+r default)
history | fzf --tac --no-sort | awk '{$1=""; print $0}' | xargs
```

---

## 🌿 Git Integration

```bash
# Switch git branch interactively
git branch | fzf | xargs git checkout

# Checkout with preview of log
git branch | fzf --preview 'git log --oneline --graph {}' | xargs git checkout

# Interactive git log
git log --oneline | fzf --preview 'git show {1}' | awk '{print $1}'

# Add files interactively
git diff --name-only | fzf -m | xargs git add

# Interactive git stash apply
git stash list | fzf | awk -F: '{print $1}' | xargs git stash apply
```

---

## 🐳 Docker Integration

```bash
# Stop a container interactively
docker ps | fzf --header-lines=1 | awk '{print $1}' | xargs docker stop

# Remove image interactively
docker images | fzf --header-lines=1 | awk '{print $3}' | xargs docker rmi

# Exec into a container
docker ps | fzf --header-lines=1 | awk '{print $1}' | xargs -I{} docker exec -it {} bash
```

---

## ⚙️ FZF_DEFAULT_OPTS

Set in `~/.zshrc` or `~/.bashrc`:

```bash
export FZF_DEFAULT_OPTS="
  --height 40%
  --layout=reverse
  --border
  --preview-window=right:50%
  --bind='ctrl-/:toggle-preview'
  --color=fg:#cdd6f4,bg:#1e1e2e,hl:#89b4fa
  --color=fg+:#cdd6f4,bg+:#313244,hl+:#89dceb
  --color=info:#cba6f7,prompt:#89b4fa,pointer:#f38ba8
  --color=marker:#a6e3a1,spinner:#f5c2e7,header:#94e2d5
"
# ^ Catppuccin Mocha theme — fits Hyprland aesthetic
```

---

## 🔧 Useful Aliases

```bash
# Add to ~/.zshrc

# Interactive kill process
fkill() {
  ps aux | fzf --header-lines=1 | awk '{print $2}' | xargs kill -9
}

# cd with fzf
fcd() {
  cd "$(fd --type d | fzf --preview 'eza --tree --level=2 {}')"
}

# Open recent files (requires a recent files list)
frecent() {
  nvim "$(fzf --preview 'bat --color=always {}')"
}

# fzf pacman search + install
finstall() {
  pacman -Slq | fzf --multi --preview 'pacman -Si {}' | xargs -ro sudo pacman -S
}

# fzf pacman remove
fremove() {
  pacman -Qq | fzf --multi --preview 'pacman -Qi {}' | xargs -ro sudo pacman -Rns
}
```

---

## 🔗 Related Notes
- [[fd — Fast Find]]
- [[ripgrep — Fast Grep]]
- [[bat — Better cat]]
- [[lazygit — Git TUI]]
- [[zoxide — Smart cd]]

---
*Last updated: 2026-03 | Tool: fzf 0.70*
