# Zen Browser Opens Two Windows After Update (Arch Linux)

## Problem

After updating `zen-browser-bin` via `yay`, launching Zen Browser always opened **two windows** instead of one.

This happened:

* consistently
* immediately on launch
* even after reinstalling and clearing configs

---

## Initial Assumptions (that turned out false)

We first suspected common causes:

* Zen cache or profile corruption
* Autostart entries (`~/.config/autostart`)
* Hyprland config launching Zen twice
* Wayland / Firefox single-instance bugs
* Broken wrapper script

Each of these was tested and **ruled out**:

* Configs were deleted
* Zen was reinstalled cleanly
* Wrapper was modified
* Wayland flags and `--no-remote` were tried
  → **Issue persisted**

This confirmed the problem was **not inside Zen itself**.

---

## Key Diagnostic Step (Turning Point)

We inspected **desktop entry usage**, because launchers (rofi / HyDE) rely entirely on `.desktop` files.

Command used:

```bash
grep -R "Exec=" /usr/share/applications | grep zen
grep -R "Exec=" ~/.local/share/applications | grep zen
```

### Discovery

There were **two active `zen.desktop` files**:

1. `/usr/share/applications/zen.desktop`
2. `~/.local/share/applications/zen.desktop`

Both desktop files:

* pointed **directly to `zen-bin`**
* were indexed by the launcher
* resulted in **two identical launches**

This explained **everything**:

* why reinstall didn’t help
* why wrapper fixes didn’t work
* why exactly *two* windows always opened
* why it started after updates (desktop cache rebuild)

---

## Root Cause (Final)

**Duplicate `.desktop` files + launcher indexing**

* rofi / HyDE indexed **both desktop entries**
* both executed `zen-bin`
* Zen was launched **twice at the OS level**
* Zen itself behaved correctly

➡️ This was **not a Zen bug**, but a **desktop-entry duplication issue**

---

## Correct Fix

### 1. Remove the duplicate local desktop entry

```bash
rm -f ~/.local/share/applications/zen.desktop
```

---

### 2. Fix the system desktop entry to use the wrapper

Edit:

```bash
sudo nano /usr/share/applications/zen.desktop
```

Replace **all** `Exec=` lines to use `zen-browser` (the wrapper), not `zen-bin`.

Final correct file:

```ini
[Desktop Entry]
Name=Zen Browser
Comment=Experience tranquillity while browsing the web without people tracking you!
Exec=zen-browser %u
Icon=zen-browser
Type=Application
MimeType=text/html;text/xml;application/xhtml+xml;x-scheme-handler/http;x-scheme-handler/https;application/x-xpinstall;application/pdf;application/json;
StartupWMClass=zen
Categories=Network;WebBrowser;
StartupNotify=true
Terminal=false
X-MultipleArgs=false
Keywords=Internet;WWW;Browser;Web;Explorer;
Actions=new-window;new-private-window;profilemanager;

[Desktop Action new-window]
Name=Open a New Window
Exec=zen-browser %u

[Desktop Action new-private-window]
Name=Open a New Private Window
Exec=zen-browser --private-window %u

[Desktop Action profilemanager]
Name=Open the Profile Manager
Exec=zen-browser --ProfileManager %u
```

---

### 3. Rebuild desktop database

```bash
sudo update-desktop-database /usr/share/applications
```

---

## Result

* Zen launches **one window**
* Desktop actions work correctly
* Wrapper logic is respected
* Issue does **not** return on relaunch

---

## Why This Worked (Summary)

* Only **one desktop entry** exists
* Launcher indexes Zen **once**
* All launches go through the wrapper
* No duplicate OS-level execution occurs

---

## Lesson Learned

When an app opens **exactly twice**:

* suspect **desktop entries**, not the app
* always check **both**:

  * `/usr/share/applications`
  * `~/.local/share/applications`

Especially important on:

* Arch Linux
* AUR packages
* rofi / wofi / HyDE environments

