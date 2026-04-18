# 🖥️ tmux — Terminal Multiplexer

> [!abstract] Overview
> tmux lets you run multiple terminal sessions inside one window, detach from them, and reattach later. Essential for remote SSH work, long-running processes, and organized workflows.

**Tags:** #cli #linux #tools #tmux #terminal #productivity

---

## 🧠 Mental Model

```
tmux
├── Sessions     (a workspace — e.g. "ottplatform", "personal")
│   ├── Windows  (tabs inside a session — e.g. "server", "logs", "db")
│   │   └── Panes  (splits inside a window — side by side terminals)
```

**Prefix key:** `Ctrl+b` — press this before every tmux command.

---

## 🚀 Sessions

```bash
# Start new session
tmux

# Start named session
tmux new -s ottplatform

# List sessions
tmux ls

# Attach to last session
tmux attach

# Attach to named session
tmux attach -t ottplatform

# Detach from session (keep it running)
# Prefix + d

# Kill a session
tmux kill-session -t ottplatform

# Kill all sessions
tmux kill-server
```

---

## ⌨️ Key Bindings (all require Prefix = Ctrl+b first)

### Sessions
| Keys | Action |
|------|--------|
| `d` | Detach from session |
| `s` | List and switch sessions |
| `$` | Rename current session |
| `(` / `)` | Previous / next session |

### Windows (Tabs)
| Keys | Action |
|------|--------|
| `c` | Create new window |
| `,` | Rename current window |
| `n` / `p` | Next / previous window |
| `0-9` | Switch to window by number |
| `&` | Kill current window |
| `w` | List windows interactively |

### Panes (Splits)
| Keys | Action |
|------|--------|
| `%` | Split vertically (side by side) |
| `"` | Split horizontally (top/bottom) |
| Arrow keys | Navigate between panes |
| `x` | Kill current pane |
| `z` | Zoom/unzoom pane (toggle fullscreen) |
| `{` / `}` | Swap pane left/right |
| `Space` | Cycle through pane layouts |
| `q` | Show pane numbers |
| `!` | Break pane into its own window |

### Misc
| Keys | Action |
|------|--------|
| `?` | Show all keybindings |
| `t` | Show clock |
| `:` | Enter command mode |
| `[` | Enter scroll/copy mode (use arrow keys, q to exit) |

---

## 📐 Pane Resizing

```
Prefix + Ctrl + Arrow   → resize pane in arrow direction
Prefix + Alt + Arrow    → resize in larger steps
```

Or in command mode (`:`)
```
resize-pane -D 5    # down
resize-pane -U 5    # up
resize-pane -L 5    # left
resize-pane -R 5    # right
```

---

## 📋 Copy Mode (Scrollback)

```
Prefix + [          → enter copy mode
Arrow keys / PgUp   → scroll
Space               → start selection
Enter               → copy selection
Prefix + ]          → paste
q                   → exit copy mode
```

---

## ⚙️ Config File (~/.tmux.conf)

```bash
# ~/.tmux.conf

# Change prefix to Ctrl+a (like screen)
unbind C-b
set-option -g prefix C-a
bind-key C-a send-prefix

# Enable mouse support
set -g mouse on

# Split panes with | and -
bind | split-window -h
bind - split-window -v
unbind '"'
unbind %

# Reload config
bind r source-file ~/.tmux.conf \; display "Config reloaded!"

# Start windows and panes at 1
set -g base-index 1
setw -g pane-base-index 1

# Better colors
set -g default-terminal "screen-256color"

# Increase scrollback
set -g history-limit 10000

# Status bar
set -g status-style bg=black,fg=white
set -g window-status-current-style bg=blue,fg=white,bold
```

Apply changes:
```bash
tmux source-file ~/.tmux.conf
# or inside tmux: Prefix + r (if you added the bind above)
```

---

## 🔧 Useful Commands

```bash
# Run command in new session
tmux new -s logs -d 'tail -f /var/log/syslog'

# Send keys to a pane programmatically
tmux send-keys -t ottplatform:1.1 'mvn spring-boot:run' Enter

# Create a scripted layout on startup
tmux new-session -d -s dev -n editor
tmux send-keys -t dev 'nvim .' Enter
tmux split-window -h -t dev
tmux send-keys -t dev 'lazygit' Enter
tmux new-window -t dev -n server
tmux send-keys -t dev 'mvn spring-boot:run' Enter
tmux attach -t dev
```

---

## 💡 Practical Workflows

```bash
# OTT platform dev session
tmux new -s ott
# Window 1: editor
# Prefix+c → Window 2: server (mvn spring-boot:run)
# Prefix+c → Window 3: logs (tail -f)
# Prefix+c → Window 4: db (mysql -u root -p)
# Detach with Prefix+d, come back anytime with:
tmux attach -t ott
```

---

## 🔗 Related Notes
- [[neovim — Text Editor]]
- [[ssh — Remote Access]]
- [[Shell Scripting Basics]]

---
*Last updated: 2026-03 | Tool: tmux 3.6*
