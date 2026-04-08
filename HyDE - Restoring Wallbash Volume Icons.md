# HyDE – Restoring Wallbash Volume/Notification Icons

tags: #linux #hyde #hyprland #arch #troubleshooting #wallbash

---

## Problem

Volume change / brightness change notifications were broken — the animated knob/slider icons were missing from the OSD popups. No icon theme reinstall would fix it.

---

## How HyDE's Volume OSD Actually Works

This is **not** a standard icon theme issue. HyDE uses a custom pipeline:

```
wallpaper image
     ↓
color.set.sh  (reads dominant colors from wallpaper)
     ↓
processes *.dcol templates in ~/.local/share/wallbash/always/00-icons/
     ↓
generates colored SVGs into ~/.local/share/icons/Wallbash-Icon/media/
     ↓
volumecontrol.sh passes those SVGs to notify-send as icons
```

Every time you change wallpaper, the SVGs are regenerated with colors matching the wallpaper. That's why they're called **Wallbash** icons — they're wallpaper-colored.

---

## Key Files & What They Do

| File/Path | What it is |
|---|---|
| `~/.local/lib/hyde/volumecontrol.sh` | Called by keybindings on volume keypress. Sends notification with icon path |
| `~/.local/share/icons/Wallbash-Icon/media/` | Where the generated SVGs live (e.g. `knob-50.svg`, `muted-speaker.svg`) |
| `~/.local/share/wallbash/always/00-icons/*.dcol` | Template files. First line = output SVG path. Rest = SVG content with color variables |
| `~/.local/lib/hyde/color.set.sh` | The engine. Reads wallpaper → extracts colors → processes all `.dcol` templates → writes SVGs |
| `~/.local/share/wallbash/dunst.sh` | Separate script. Regenerates `dunstrc` config (fonts, colors, corner radius). Does NOT generate icons |
| `~/.local/bin/hyde-shell` | Wrapper that dispatches to the lib scripts |

### How volumecontrol.sh finds the icons

```bash
iconsDir="${iconsDir:-$XDG_DATA_HOME/icons}"
icodir="$iconsDir/Wallbash-Icon/media"
# uses files like: $icodir/knob-50.svg, $icodir/muted-speaker.svg
```

### How .dcol templates work

```
~/.local/share/wallbash/always/00-icons/vol-50.dcol
├── Line 1:  ${iconsDir}/Wallbash-Icon/media/knob-50.svg   ← output path
└── Rest:    SVG XML with wallbash color variables          ← template content
```

`color.set.sh` reads each `.dcol`, substitutes the wallbash color variables, and writes the result to the path on line 1.

---

## What Was Deleted

The entire `~/.local/share/icons/Wallbash-Icon/media/` directory — the **generated SVG output folder**. The templates (`.dcol` files) were still intact, just the generated output was gone.

Additionally `~/.local/share/wallbash/dunst.sh` was also missing (separate issue — this script regenerates dunst config, not icons).

---

## The Fix

### Step 1 — Restore missing dunst.sh from HyDE repo

```bash
cp ~/HyDE/Configs/.local/share/wallbash/scripts/dunst.sh ~/.local/share/wallbash/dunst.sh
```

### Step 2 — Regenerate all Wallbash icons by re-running color.set.sh

```bash
bash ~/.local/lib/hyde/color.set.sh ~/.cache/hyde/wall.set.png
```

`wall.set.png` is the cached copy of your current wallpaper. After this, the `Wallbash-Icon/media/` directory gets recreated with all SVGs.

### Step 3 — Verify

```bash
ls ~/.local/share/icons/Wallbash-Icon/media/
```

You should see files like `knob-0.svg`, `knob-5.svg` ... `knob-100.svg`, `muted-speaker.svg`, `unmuted-mic.svg`, etc.

---

## Things That Did NOT Work (and Why)

| What was tried | Why it failed |
|---|---|
| `sudo pacman -S papirus-icon-theme` | Wrong — these aren't from any pacman package |
| `./restore_cfg.sh` / `./restore_fnt.sh` | These restore dotfiles and fonts/cursors, not generated icon outputs |
| `hyde-shell wallbash dunst` | This only regenerates `dunstrc` config, not the SVG icons |
| `hyde-shell wallbash dunst.sh` (full path) | hyde-shell only accepts script name, not path |
| `bash ~/.local/lib/hyde/color.set.sh` (no args) | Needs the wallpaper path explicitly |

---

## If This Happens Again

Icons get auto-regenerated on every wallpaper change. So the fastest fix is just:

```bash
# Option 1: trigger via wallpaper switch (any wallpaper)
hyde-shell wallpaper

# Option 2: manually run with current wallpaper
bash ~/.local/lib/hyde/color.set.sh ~/.cache/hyde/wall.set.png
```

---

## Related Paths Quick Reference

```
~/.cache/hyde/
├── wall.set.png          ← current wallpaper (cached)
├── wall.dcol             ← extracted color palette (binary)
└── wallbash/
    └── dunst.conf        ← wallbash-generated dunst color overrides

~/.local/share/wallbash/
├── always/
│   └── 00-icons/         ← .dcol templates for volume/mute icons
├── dunst.sh              ← regenerates dunstrc
├── kitty.sh
└── qtct.sh

~/.local/share/icons/
└── Wallbash-Icon/
    └── media/            ← GENERATED SVGs (safe to delete, will be recreated)

~/.local/lib/hyde/
├── color.set.sh          ← main wallbash engine
├── volumecontrol.sh      ← volume keybind handler
└── wallbash.sh           ← wallbash helper
```
