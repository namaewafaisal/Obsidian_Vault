---
topic: linux-learning-plan
status: active
distro: arch-linux
wm: hyprland
config: hyde
goal: learn-linux-from-scratch-to-advanced
---

# Linux Learning Plan (Arch + Hyprland + HyDE)

This vault documents my end-to-end understanding of Linux:
- what it does
- how it works internally
- how to troubleshoot
- what is safe to customize
- what should NEVER be touched blindly

---

## Layer 1 — Core Linux Foundations (DO NOT SKIP)

> Without this layer, Linux becomes guesswork and copy-paste fixes.

- [[01 - What Is Linux Really (Kernel vs Userland)]]
- [[02 - Boot Process (UEFI → Bootloader → Kernel → init)]]
- [[03 - Linux Filesystem Hierarchy Explained]]
- [[04 - Users, Groups, Permissions, Ownership]]
- [[05 - Processes, Signals, Jobs, Daemons]]
- [[06 - Environment Variables & Shell Basics]]

---

## Layer 2 — Arch Linux Internals

> Arch gives control. This layer teaches responsibility.

- [[07 - What Makes Arch Linux Different]]
- [[08 - pacman Deep Dive]]
- [[09 - AUR, yay, and PKGBUILD Safety]]
- [[10 - systemd Fundamentals]]
- [[11 - systemctl, services, targets]]
- [[12 - Logs & journalctl]]
- [[13 - Arch Updates, Breakages, and Recovery]]

---

## Layer 3 — Display Stack & Graphics

> This explains *why Hyprland behaves the way it does*.

- [[14 - Linux Graphics Stack Overview]]
- [[15 - X11 vs Wayland (Why Hyprland Uses Wayland)]]
- [[16 - DRM, KMS, Mesa, GPU Drivers]]
- [[17 - Input Devices (libinput, keyboards, touchpads)]]

---

## Layer 4 — Hyprland Core (Window Manager Level)

> Learn Hyprland itself before touching HyDE.

- [[18 - What Hyprland Is (Architecture)]]
- [[19 - Hyprland Startup Flow]]
- [[20 - hyprland.conf Explained]]
- [[21 - Keybinds, Dispatchers, and Actions]]
- [[22 - Window Rules, Workspace Rules]]
- [[23 - Monitor Configuration & Scaling]]
- [[24 - Animations, Performance, and Latency]]

---

## Layer 5 — HyDE Configuration (Distribution Layer)

> HyDE is **not** Hyprland. This layer explains what HyDE adds.

- [[25 - What HyDE Changes in Hyprland]]
- [[26 - HyDE Directory Structure]]
- [[27 - Startup Scripts & Autostart]]
- [[28 - HyDE Themes, Scripts, and Presets]]
- [[29 - Safe vs Unsafe Changes in HyDE]]
- [[30 - Updating HyDE Without Breaking Setup]]

---

## Layer 6 — Desktop Components

> These are *services*, not magic.

- [[31 - Status Bars (Waybar)]]
- [[32 - App Launchers (wofi, rofi)]]
- [[33 - Notifications (dunst / swaync)]]
- [[34 - Clipboard, Screenshots, Portals]]
- [[35 - Audio Stack (PipeWire)]]
- [[36 - Power, Battery, Brightness]]

---

## Layer 7 — Customization (Controlled)

> Customization without understanding = instability.

- [[37 - GTK Theming]]
- [[38 - Qt Theming]]
- [[39 - Fonts, Rendering, Hinting]]
- [[40 - Icons, Cursors, Themes]]
- [[41 - Wallpapers & Effects]]
- [[42 - What Customizations Break After Updates]]

---

## Layer 8 — Troubleshooting Like a Linux User

> This is where you stop being dependent on forums.

- [[43 - Reading Logs Properly]]
- [[44 - Debugging Boot Failures]]
- [[45 - Graphics & Wayland Issues]]
- [[46 - Audio, Network, Bluetooth Failures]]
- [[47 - Package Conflicts & Dependency Hell]]
- [[48 - Using chroot to Fix a Broken System]]

---

## Layer 9 — Stability, Performance & Discipline

> Long-term Linux usage mindset.

- [[49 - Update Strategy for Arch]]
- [[50 - Backups Before Risky Changes]]
- [[51 - Resource Monitoring & Optimization]]
- [[52 - Cleaning Orphaned Packages]]
- [[53 - When to Reinstall vs Repair]]
- [[54 - Maintaining a Clean Linux System]]

---

## Completion Goal

By the end of this plan, I should be able to:
- Explain *why* something works or breaks
- Fix issues without random commands
- Customize confidently
- Recover from system failures
- Treat Linux as an understandable system, not a mystery
