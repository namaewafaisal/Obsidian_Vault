It’s great to see that **16W** flow! You’ve successfully cleared a hardware-level "brain freeze." Since we touched a few different layers of the system, here is the breakdown of what changed and how to handle it if the "1962 hours" ghost returns.

---

## What did we actually change?

We moved from basic OS troubleshooting to deep firmware communication. Here is the stack:

* **Charging Thresholds:** We reset the "Start" and "Stop" markers in the Linux kernel (`/sys/class/power_supply/BAT0/...`). Previously, your system was told not to even start charging until the battery hit 75%.
* **Charging Mode:** This was the big one. We used `smbios-battery-ctl` to change your BIOS setting from **Express** to **Standard**.
* **Kernel Handshake:** By using `modprobe`, we "uninstalled and reinstalled" the Dell laptop driver in real-time, forcing the Linux kernel to re-identify the charger.

## Do you have to worry about these changes?

**Short answer: No.**

* **Standard Mode is safer:** "Express" charging is harder on the battery cells because it uses higher voltage/heat. Switching to "Standard" is actually better for the long-term health of your laptop.
* **Thresholds:** Setting them to 0/100 just means "charge whenever plugged in, all the way to the top." This is the default behavior for most laptops.
* **Kernel Modules:** `modprobe` changes are temporary; they reset every time you reboot, so you haven't "broken" any permanent drivers.

---

## Future Playbook: If it happens again

If you see **0 W** or that massive "Time to Full" estimate again, follow this specific order:

### 1. The "Wiggle" & Re-plug

Before typing anything, unplug the charger, wait 5 seconds, and plug it back in. Sometimes the Dell ID pin just needs a second seat.

### 2. Force the Standard Mode (The "Dell Fix")

If the hardware is stuck, run the command that worked today:

```bash
sudo smbios-battery-ctl --set-charging-mode=standard

```

### 3. Reset the Thresholds

Make sure the OS isn't artificially blocking the charge:

```bash
echo 0 | sudo tee /sys/class/power_supply/BAT0/charge_control_start_threshold
echo 100 | sudo tee /sys/class/power_supply/BAT0/charge_control_end_threshold

```

### 4. The Cold Boot

If the commands above don't jumpstart the `energy-rate`, a full **Shutdown** (not Restart) is required to let the Embedded Controller reset its logic gates.

---

## One final check for you

Since Arch is a "DIY" distro, check if you have a service called `tlp` enabled. If `tlp` is running, it might try to change your thresholds back to 75/80 on the next boot.

Run: `systemctl status tlp`

**If it says "active," would you like me to show you how to configure it so it doesn't lock your battery again?**

### ❗️Dell 7390 Charging Debug — Resolved (2026-02-15)

**Symptom:**  
Laptop shows `state: charging` but `energy-rate ≈ ~0 W` (effectively not charging) at ~50 %.

**Root cause:**  
Firmware / EC charging logic (Dell SMBIOS / WMI) stuck after AC state transitions. Not a TLP bug.

---

### 🔧 What ultimately fixes it

1. **Set charging mode to Standard**
   ```bash
   sudo smbios-battery-ctl --set-charging-mode=standard
   ```

2. **Ensure full charge thresholds**
   ```bash
   echo 0 | sudo tee /sys/class/power_supply/BAT0/charge_control_start_threshold
   echo 100 | sudo tee /sys/class/power_supply/BAT0/charge_control_end_threshold
   ```

3. **Full Dell driver stack reset**
   ```bash
   sudo modprobe -r dell_wmi_sysman
   sudo modprobe -r dell_smm_hwmon
   sudo modprobe -r dell_pc
   sudo modprobe -r dell_rbtn
   sudo modprobe -r dell_laptop
   sudo modprobe -r dell_wmi
   sudo modprobe -r dell_smbios
   sudo modprobe -r dcdbas

   sudo modprobe dcdbas
   sudo modprobe dell_smbios
   sudo modprobe dell_wmi
   sudo modprobe dell_laptop
   sudo modprobe dell_pc
   sudo modprobe dell_smm_hwmon
   sudo modprobe dell_wmi_sysman
   sudo modprobe dell_rbtn
   ```

   *This forces the Linux kernel to re-query BIOS charging state.*

4. **Verify**
   ```bash
   upower -i /org/freedesktop/UPower/devices/battery_BAT0 \
     | grep -E "state|energy-rate|percentage"
   ```

   After reload, charging resumed properly:
   ```
   energy-rate: ~16 W
   state: charging
   percentage: ~49%
   ```

---

### 🧠 Notes

- TLP was active and applied charge thresholds (50%), but threshold was **not the root cause** — reset was.
- The symptom is **firmware/EC mis-state**, not a Linux driver bug.
- The Dell modules reset forces re-negotiation of power/charge logic.

---

### ⚠️ If it happens again

#### Quick software reset
1. Repeat Dell driver stack unload/ reload.
2. Confirm thresholds at 0/100.
3. Verify charging resumes.

#### Full hardware reset (if needed)
1. Shutdown (`sudo poweroff`)
2. Unplug AC
3. Hold power button **15–30 s**
4. Plug AC
5. Power on — this clears EC residual state.

---

### ✅ End Result

Charging correctly resumes at normal power draw (~16 W) after the reset procedure.
