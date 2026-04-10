# HyDE Update Incident — 2026-04-10

#hyde #hyprland #arch #fix #incident

## Summary

Running HyDE's `install.sh` (update/deploy) caused two issues:
1. `hyprland.conf` got silently overwritten by a deploy hook
2. Theme icons broke due to a `libhyprutils` version mismatch with HyDE's `hyq` binary

Both were fully resolved without reinstalling anything.

---

## What `install.sh` Actually Did

HyDE's deploy script has three behaviors for each file:

| Behavior | Meaning |
|----------|---------|
| `[preserved]` | Your version kept, HyDE backed up its own copy |
| `[sync]` | Directory fully overwritten with HyDE's version |
| `[overwrite]` | Binary/script forcibly replaced (hyde-shell, hydectl, lib) |

### Files Where YOUR Version Was Kept (Safe)
```
hypr/hyprland.conf          ← initially preserved, but see hook below
hypr/animations.conf
hypr/shaders.conf
hypr/workflows.conf
hypr/windowrules.conf
hypr/keybindings.conf
hypr/userprefs.conf
hypr/monitors.conf
hypr/themes/theme.conf
hypr/themes/wallbash.conf
hypr/themes/colors.conf
hypr/hypridle.conf
hypr/hyprlock.conf
hyde/config.toml
waybar/config.jsonc
waybar/style.css, theme.css, user-style.css
kitty/kitty.conf
zsh/.zshrc, user.zsh, prompt.zsh, plugin.zsh
starship/starship.toml
vim/vimrc
Code/User/settings.json
Code - OSS/User/settings.json
xdg-terminals.list
code-flags.conf
baloofilerc
dolphinstaterc
```

### Directories That Were Synced (HyDE's Version Written)
```
~/.config/systemd/user/
~/.config/uwsm/
~/.config/gtk-3.0/
~/.config/nwg-look/
~/.config/xsettingsd/
~/.config/Kvantum/
~/.config/qt5ct/
~/.config/qt6ct/
~/.config/hyde/wallbash/
~/.config/hypr/animations/   ← folder, not animations.conf
~/.config/hypr/workflows/
~/.config/hypr/shaders/
~/.config/hypr/hyprlock/
~/.config/wlogout/
~/.config/dunst/
~/.config/rofi/              ← theme.rasi only
~/.local/share/hypr/
~/.local/share/hyde/
~/.local/share/wallbash/
~/.local/share/waybar/
~/.local/share/fastfetch/presets/hyde/
~/.local/share/dolphin/
~/.local/share/kio/servicemenus/
~/.local/share/kxmlgui5/dolphin/
~/.local/state/hyde/hyprland.conf
~/.zshenv
~/.gtkrc-2.0
~/.config/zsh/conf.d/hyde/
~/.config/zsh/completions/
~/.config/zsh/functions/     ← partial
```

### Files Forcibly Overwritten
```
~/.local/bin/hyde-shell
~/.local/bin/hydectl
~/.local/share/hyde/         ← full overwrite
~/.local/lib/hyde/           ← all scripts replaced
~/.local/share/wallbash/     ← full overwrite
```

### Skipped (Missing Dependencies)
```
nvidia, fish, lsd, duf, spotify, vscodium,
electron, mangohud, swaylock-effects,
libinput-gestures, powerlevel10k, pyprland
```

---

## Issue 1 — hyprland.conf Overwritten

### What Happened

The deploy initially preserved `hyprland.conf`, but then a post-deploy hook ran:

```
[deploy] [hook] hyprland :: No HYDE_HYPRLAND variable found in
/home/faisal/.config/hypr/hyprland.conf, restoring default HyDE marker...
[deploy] [restore] :: /home/faisal/HyDE/Configs/.config/hypr/hyprland.conf
→ /home/faisal/.config/hypr/hyprland.conf
```

HyDE checks for a `HYDE_HYPRLAND` marker variable inside `hyprland.conf`. If it's missing, it assumes the file is not HyDE-managed and overwrites it with the default.

### Fix

```bash
cp ~/.config/cfg_backups/260410_08h28m00s/.config/hypr/hyprland.conf \
   ~/.config/hypr/hyprland.conf

hyprctl reload
```

### Prevention

After restoring, verify the marker exists:

```bash
grep "HYDE_HYPRLAND" ~/.config/hypr/hyprland.conf
```

If missing, check what it looks like in HyDE's default and add it:

```bash
grep "HYDE_HYPRLAND" ~/HyDE/Configs/.config/hypr/hyprland.conf
```

Copy that line into your `hyprland.conf` so future deploys don't trigger the hook again.

---

## Issue 2 — Theme Icons Broke / hyq Binary Failing

### Symptom

Dolphin and other apps showed generic dark folder icons instead of wallbash-colored ones. Theme was not applying properly.

### Root Cause

```
hyq: error while loading shared libraries: libhyprutils.so.10:
cannot open shared object file: No such file or directory
```

`hyq` is HyDE's IPC binary, compiled against `libhyprutils.so.10`. But `hyprutils` had been updated on the system to a newer version:

```bash
ls /usr/lib/libhyprutils*
# /usr/lib/libhyprutils.so
# /usr/lib/libhyprutils.so.0.12.0
# /usr/lib/libhyprutils.so.11       ← only this exists
```

The `.so.10` soname was gone. The dynamic linker couldn't find it, so `hyq` refused to start, which broke HyDE's entire IPC/theming pipeline.

### Fix

Create a symlink from the old soname to the new library:

```bash
sudo ln -s /usr/lib/libhyprutils.so.11 /usr/lib/libhyprutils.so.10
```

Verify `hyq` works:

```bash
~/.local/lib/hyde/hyq
# Expected: "--query is required" (not a library error)
```

Reload HyDE:

```bash
hydectl reload
```

### Why the Symlink Works

`hyq` was compiled with a specific soname (`libhyprutils.so.10`) baked into it. The symlink tells the dynamic linker to serve `.so.11` when `.so.10` is requested. Since it's a minor version bump, the ABI is compatible — same functions, same signatures.

### ⚠️ Future Warning

This symlink will break again on the next major `hyprutils` version bump (e.g. `.so.12`). When icons break again after a system update, check:

```bash
ldd ~/.local/lib/hyde/hyq | grep hyprutils
ls /usr/lib/libhyprutils*
```

If the sonames don't match, recreate the symlink:

```bash
sudo ln -s /usr/lib/libhyprutils.so.NEW /usr/lib/libhyprutils.so.OLD
```

The permanent fix is HyDE upstream rebuilding `hyq` against the current hyprutils version.

---

## Backup Location

HyDE automatically backs up all configs before every deploy to:

```
~/.config/cfg_backups/<timestamp>/
```

The full pre-update backup from this incident:

```
~/.config/cfg_backups/260410_08h28m00s/
```

Contains complete snapshots of `~/.config/`, `~/.local/`, `~/.gtkrc-2.0`, and `~/.zshenv`.

---

## Final State After Fixes

| Item | Status |
|------|--------|
| `hyprland.conf` | ✅ Restored from backup |
| `hyq` binary | ✅ Working via symlink |
| Theme icons | ✅ Back to wallbash colors |
| All other configs | ✅ Were never touched |

---

## Related

- [[HyDE]]
- [[Hyprland]]
- [[Arch Linux]]