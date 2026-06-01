Here's the full workflow:

---

## Dotfiles Workflow with Stow

### Philosophy
- Track only **hand-edited config files** (text, you could write from scratch)
- Skip **app data, caches, auto-generated, binary, secrets**
- Dotfiles folder mirrors `$HOME` structure exactly

---

### What to track vs skip

**Track ✅**
- Config files you've edited (mpv.conf, user.zsh, waybar config, etc.)
- Shell configs, aliases, exports
- Tool configs (btop, lazygit, starship, etc.)

**Skip ❌**
- Auto-generated files (watch_later, .zcompdump, monitor.kdl)
- App data/state (discord, chromium, obsidian, JetBrains)
- Binary files (dconf)
- Secrets/passwords (use secrets.zsh pattern)
- KDE leftovers if on Niri now
- Backups (nvim-backup, hypr_backup)

---

### Before touching any app's config

**1. Look inside first**
```bash
ls ~/.config/appname/
# check what's there — config vs data vs auto-generated
```

**2. Copy only what you want to track**
```bash
cd ~/dotfiles
mkdir -p appname/.config/appname

# Copy only the files you want, not the whole folder
cp ~/.config/appname/config.file dotfiles/appname/.config/appname/
```

**3. For files with secrets** — remove secrets, use secrets pattern:
```bash
# In dotfiles/zsh/.config/zsh/user.zsh — remove passwords, add:
[[ -f "$ZDOTDIR/secrets.zsh" ]] && source "$ZDOTDIR/secrets.zsh"

# On your machine only (never in dotfiles):
cat > ~/.config/zsh/secrets.zsh << 'EOF'
export DB_PASSWORD=yourpassword
EOF

# In dotfiles, create example for reference:
cat > dotfiles/zsh/.config/zsh/secrets.zsh.example << 'EOF'
export DB_PASSWORD=your_password
EOF

# Gitignore the real secrets file:
echo "zsh/.config/zsh/secrets.zsh" >> ~/dotfiles/.gitignore
```

---

### Stow workflow (always in this order)

**1. Delete originals** (stow can't overwrite plain files)
```bash
rm ~/.config/appname/config.file
```

**2. Simulate first — never skip this**
```bash
cd ~/dotfiles
stow --simulate --verbose appname
```
- No output / only `WARNING: in simulation mode` = clean, safe to proceed
- Conflicts listed = fix them first (usually means original file still exists)

**3. Actually stow**
```bash
stow appname
```

**4. Verify symlinks**
```bash
ls -la ~/.config/appname/
# Should show: config.file -> ../../dotfiles/appname/.config/appname/config.file
```

---

### Common issues

**Conflict error** = original file still exists, `rm` it first

**watch_later / cache folders accidentally tracked** = remove from dotfiles:
```bash
rm -rf dotfiles/appname/.config/appname/cache_folder
echo "appname/.config/appname/cache_folder/" >> ~/dotfiles/.gitignore
```

**Mixed folder** (some files to track, some to skip) = only copy the files you want, leave the rest as plain files. Stow links individual files, not whole folders.

**Auto-generated file** (like monitor.kdl) = leave it as plain file, don't copy to dotfiles

---

### After stowing — commit

```bash
cd ~/dotfiles
git add -A
git commit -m "appname: track config files"
```

---

### On a fresh machine

```bash
git clone your-dotfiles-repo ~/dotfiles
cd ~/dotfiles
stow appname1 appname2 appname3
# Then manually create secrets.zsh from secrets.zsh.example
```

---

### Current state (already done)
- `mpv` ✅ — mpv.conf, input.conf, autoload.lua
- `niri` ✅ — config.kdl
- `zsh` ✅ — .zshrc, user.zsh, flutter-android, fzf completions, bat/eza/fzf functions, secrets pattern set up