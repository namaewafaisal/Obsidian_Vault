# HyDE – Cursor and Icon Theme Not Applying on Theme Switch

**Tags:** #linux #hyprland #HyDE #debugging #arch  
**Date:** 2025-05-10  
**Status:** ✅ Resolved

---

## The Symptom

After switching themes with `hydectl theme select` or `hydectl reload`, the cursor and icon theme would not change — they always showed:

- Cursor: `Bibata-Modern-Ice`
- Icon: `Tela-circle-dracula`

Even though the active theme's `hypr.theme` had different values defined. GTK apps (like Dolphin) were the most visibly broken. Hyprland-native rendering was unaffected.

---

## The Investigation Trail

### Step 1 — Suspected `theme.conf` / `wallbash.conf`

Both files correctly had:
```
$CURSOR_THEME = Bibata-Original-Ice
```
And `userprefs.conf` correctly forwarded them:
```
env = XCURSOR_THEME,$CURSOR_THEME
env = HYPRCURSOR_THEME,$CURSOR_THEME
```
So Hyprland itself had the right cursor. GTK didn't.

### Step 2 — Found the dconf cache was stale

```
~/.cache/hyde/dconf
```
This file was being written with wrong values and loaded into dconf on every reload. Manually editing it fixed the cursor temporarily — but the next `hydectl reload` rebuilt it with wrong values again.

### Step 3 — Traced the dconf rebuild to `dconf.sh`

The script responsible for the `[dconf] populated` step:
```
~/.local/lib/hyde/color/dconf.sh
```

This script:
1. Sources `~/.local/share/hyde/env-theme` (global defaults — `Bibata-Modern-Ice`)
2. Tries to override using `hyq` to read the active `theme.conf` values
3. Falls back to defaults if `hyq` fails

### Step 4 — Found `hyq` was silently broken

Every `hydectl` operation printed:
```
hyq: error while loading shared libraries: libhyprutils.so.10: cannot open shared object file: No such file or directory
```

`hyq` is the binary HyDE uses to query Hyprland config variables. Because it was crashing on every call, the override block in `dconf.sh` never ran, so dconf always got the global defaults.

### Step 5 — Confirmed the library mismatch

```bash
ldd ~/.local/lib/hyde/hyq
```

Output:
```
libhyprutils.so.10 => not found
libhyprutils.so.12 => /usr/lib/libhyprutils.so.12
```

`hyq` was compiled against `libhyprutils.so.10` but the system had upgraded to `.so.12`. The package wasn't rebuilt/updated to match.

---

## Root Cause

**`hyq` was broken due to a `libhyprutils` ABI version mismatch.**

The full override chain that should happen on theme switch:

```
hypr.theme ($CURSOR_THEME = X)
    ↓
hyq reads theme.conf variables
    ↓
dconf.sh overrides env-theme defaults with theme-specific values
    ↓
dconf cache rebuilt correctly
    ↓
dconf loaded → GTK apps get correct cursor/icon
```

Because `hyq` was crashing at step 2, the chain broke and dconf always fell back to:
```
~/.local/share/hyde/env-theme → Bibata-Modern-Ice, Tela-circle-dracula
```

---

## The Fix

Create a symlink so `hyq` can find the library it expects:

```bash
sudo ln -s /usr/lib/libhyprutils.so.12 /usr/lib/libhyprutils.so.10
```

Verify `hyq` works:
```bash
~/.local/lib/hyde/hyq --version && echo "hyq works"
```

Then do a theme switch — cursor and icon should now apply correctly per theme.

---

## Why Other Attempts Failed

| Attempt | Why it didn't work |
|---|---|
| Editing `~/.cache/hyde/dconf` manually | Gets rebuilt on next reload |
| Adding `exec` lines to `hypr.theme` | HyDE sanitizes (strips) exec lines from theme files |
| Editing `~/.local/share/hyde/env-theme` | Gets regenerated from schema defaults |
| Setting `cursor_theme` in `~/.config/hyde/config.toml` | Global override, not per-theme |

---

## How the dconf Pipeline Actually Works

```bash
# dconf.sh flow (simplified):
source env-theme                     # load global defaults
eval "$(hyq ... -Q 'hyde:cursor-theme' ...)"  # read per-theme overrides
CURSOR_THEME=${_hyde_cursor_theme:-$CURSOR_THEME}  # prefer theme value, fallback to default
dconf_populate > ~/.cache/hyde/dconf # write cache
dconf load < ~/.cache/hyde/dconf     # apply to GTK
hyprctl setcursor "$CURSOR_THEME" "$CURSOR_SIZE"  # apply to Hyprland
```

`hyq` queries the active Hyprland config (which sources `theme.conf`) and exports the variables. Without it, every theme switch silently falls back to defaults.

---

## Recurring Risk

This symlink fix is fragile — if `libhyprutils` upgrades again (e.g. to `.so.13`), `hyq` will break again in the same way. The proper fix is for the HyDE package to ship `hyq` compiled against the current system library version.

**Watch for this after system updates:**
```bash
ldd ~/.local/lib/hyde/hyq | grep "not found"
```

If anything shows `not found`, recreate the symlink for the new version.

---

## Related Files

| File | Role |
|---|---|
| `~/.local/lib/hyde/color/dconf.sh` | Rebuilds dconf cache on theme switch |
| `~/.local/lib/hyde/hyq` | Binary that reads Hyprland config variables |
| `~/.local/share/hyde/env-theme` | Global default env vars (fallback source) |
| `~/.cache/hyde/dconf` | dconf cache file written and loaded each reload |
| `~/.config/hyde/themes/*/hypr.theme` | Per-theme variable definitions |
| `/usr/lib/libhyprutils.so.*` | Hyprutils shared library (version must match hyq) |
