---
tags:
  - linux
  - arch
  - bluetooth
  - audio
  - pipewire
  - wireplumber
  - fix
date: 2025-04-12
---

# Bluetooth audio auto-switch on Arch Linux

By default, PipeWire/WirePlumber is conservative about switching the default sink when a new device connects — to avoid disrupting active audio streams. This fix adds config rules that enable automatic switching.

## Symptoms

- Bluetooth headphones connect successfully but audio keeps playing from speaker/built-in
- Have to manually change output via `pavucontrol` or system tray every time

## Environment

- Arch Linux + Hyprland
- PipeWire 1.6.2 (`pactl info | grep "Server Name"` → `PulseAudio (on PipeWire)`)
- WirePlumber as session manager

---

## Fix

### 1. Create WirePlumber config directory

```bash
mkdir -p ~/.config/wireplumber/wireplumber.conf.d
```

### 2. Bluetooth auto-connect rule

`~/.config/wireplumber/wireplumber.conf.d/51-bluetooth-auto-switch.conf`

```ini
monitor.bluez.rules = [
  {
    matches = [{ device.name = "~bluez_card.*" }]
    actions = {
      update-props = {
        bluez5.auto-connect = [ hfp_hf hsp_hs a2dp_sink ]
      }
    }
  }
]
```

This ensures the A2DP sink profile is auto-connected when a BT device pairs.

### 3. Restart audio services

```bash
systemctl --user restart wireplumber pipewire pipewire-pulse
```

No reboot needed.

---

## Verify it works

Connect a BT device, then:

```bash
# Check BT sink appeared
pactl list sinks short

# Check it became the default
pactl get-default-sink
# Should show: bluez_output.<MAC>.1
```

When a second device connects, the previous one goes `SUSPENDED` and the new one becomes the default automatically.

---

## How it works

WirePlumber reads `wireplumber.conf.d/*.conf` files as policy overrides. The `monitor.bluez.rules` block pattern-matches on any `bluez_card.*` device and sets `bluez5.auto-connect` to prefer A2DP sink (high quality audio) over HFP/HSP (call profiles). Once the A2DP node is up, WirePlumber's default-node policy moves the default sink to it.

---

## Related

- [[PipeWire]] / [[WirePlumber]]
- `~/.config/wireplumber/wireplumber.conf.d/` — all custom policy overrides live here
- `pactl`, `pavucontrol` — manual sink switching tools if needed
