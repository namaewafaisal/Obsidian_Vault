# Dell 7390 — Linux Troubleshooting Log

---

## 🔋 Battery Not Charging

### Symptom
- `state: charging` but `energy-rate ≈ 0 W`
- Battery percentage not increasing despite charger plugged in
- Occasionally shows absurd "Time to Full" estimates (e.g. 1962 hours)

### Root Cause
Firmware / Embedded Controller (EC) mis-state after AC transitions. The Dell SMBIOS/WMI charging logic gets stuck. **Not a TLP bug, not a kernel driver bug** — pure EC/firmware state issue.

### Fix (run in this order)

**1. Set charging mode to Standard**
```bash
sudo smbios-battery-ctl --set-charging-mode=standard
```

**2. Reset charge thresholds to default**
```bash
echo 0 | sudo tee /sys/class/power_supply/BAT0/charge_control_start_threshold
echo 100 | sudo tee /sys/class/power_supply/BAT0/charge_control_end_threshold
```

**3. Full Dell driver stack reset**
```bash
# Unload (order matters)
sudo modprobe -r dell_wmi_sysman
sudo modprobe -r dell_smm_hwmon
sudo modprobe -r dell_pc
sudo modprobe -r dell_rbtn
sudo modprobe -r dell_laptop
sudo modprobe -r dell_wmi
sudo modprobe -r dell_smbios
sudo modprobe -r dcdbas

# Reload (reverse order)
sudo modprobe dcdbas
sudo modprobe dell_smbios
sudo modprobe dell_wmi
sudo modprobe dell_laptop
sudo modprobe dell_pc
sudo modprobe dell_smm_hwmon
sudo modprobe dell_wmi_sysman
sudo modprobe dell_rbtn
```

**4. Verify**
```bash
upower -i /org/freedesktop/UPower/devices/battery_BAT0 \
  | grep -E "state|energy-rate|percentage"
```
Expected: `energy-rate: ~16 W`, `state: charging`

### If Software Reset Doesn't Work — Full EC Reset
1. `sudo poweroff` — wait for full shutdown
2. Unplug AC charger
3. Wait **60 seconds** (capacitors drain, EC loses state)
4. Plug AC back in
5. Power on

### Notes
- **Standard mode = 16W** with stock barrel adapter. This is normal and correct.
- **Express mode** charges faster but is harder on battery cells long-term. Stay on Standard.
- **USB-C PD charging**: Realme 80W phone charger does NOT work — it doesn't output 20V which Dell requires. A proper 65W USB-C PD charger (Dell/Anker) would charge faster than the stock adapter.
- TLP was active with 50% threshold during first occurrence — threshold was NOT root cause, the EC reset was.
- `modprobe` changes are **not permanent** — they reset on reboot. The fix needs to be re-applied if the issue recurs after a reboot. (Consider automating via systemd service if this recurs often.)

---

## 📶 WiFi Disconnecting Repeatedly

### Symptom
- WiFi randomly disconnects and reconnects
- Happens mostly with phone hotspot, not always with router
- Reconnects automatically but interrupts ongoing connections

### Root Cause
Intel 8265 driver (`iwlwifi`) disconnects voluntarily when it misses too many beacon frames from the access point. Phone hotspots are less reliable at sending beacons than routers, triggering this more often.

**Key dmesg signature:**
```
iwlwifi: missed_beacons exceeds threshold, but receiving data. Stay connected, Expect bugs.
wlan0: deauthenticating from <MAC> by local choice (Reason: 3=DEAUTH_LEAVING)
```
`DEAUTH_LEAVING` = laptop chose to disconnect, not the hotspot dropping it.

### Fix

**Create iwlwifi config:**
```bash
sudo nano /etc/modprobe.d/iwlwifi.conf
```

Add:
```
options iwlwifi power_save=0 beacon_timeout=0
```

**Reload the driver:**
```bash
# First try
sudo modprobe -r iwlmvm iwlwifi && sudo modprobe iwlwifi

# If still "module in use", stop NetworkManager first
sudo systemctl stop NetworkManager
sudo modprobe -r iwlmvm iwlwifi
sudo modprobe iwlwifi
sudo systemctl start NetworkManager
```

Reconnect to hotspot after NM restarts.

**Verify fix:**
```bash
sudo dmesg | grep -i "missed_beacons\|deauth\|leaving" | tail -20
```
After fix: missed_beacons may still appear but no new `DEAUTH_LEAVING` lines.

### Notes
- `power_save=0` — disables driver-level power saving (deeper than `iwconfig` power mgmt). Negligible power cost.
- `beacon_timeout=0` — stops disconnecting when beacons are missed as long as data is still flowing.
- This config **persists across reboots** (unlike modprobe manual reloads) since it's written to `/etc/modprobe.d/`.
- The fix is standard for Intel 8265 + hotspot combination. No known side effects.

---

*Last updated: 2026-03-30*