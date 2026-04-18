Here is your **full Obsidian note**, structured clearly so future-you understands exactly what was wrong and what was changed.

You can paste this directly into Obsidian.

---

# Libvirt VM No Internet – NAT Backend Fix

```yaml
topic: libvirt-networking
date-time: INVALID-DATE-TIME
time-taken(min): INVALID-TIME
```

---

## 🔍 Problem

VM created using:

* qemu
* libvirt
* dnsmasq
* virt-manager
* iptables-nft

Inside VM:

* DHCP worked
* VM received IP `192.168.122.x`
* Could NOT ping `8.8.8.8`
* Could NOT ping `google.com`

---

## 🧠 Observation

Inside VM:

```bash
ip a
```

Showed:

```
inet 192.168.122.24/24
```

This means:

* Bridge (`virbr0`) working
* DHCP (dnsmasq) working
* VM attached properly

But internet still failed → So issue was NOT DHCP.

---

## 🔬 Host Investigation

Checked NAT rules:

```bash
sudo iptables -t nat -L -n -v
```

Result:

Only Docker rule existed:

```
MASQUERADE  172.17.0.0/16
```

Missing:

```
MASQUERADE  192.168.122.0/24
```

This confirmed:

> NAT rule for libvirt network was NOT created.

---

## 🧠 Root Cause

Arch Linux default firewall = nftables.

System had:

* nftables
* iptables
* iptables-nft (compatibility layer)
* Docker (inserting its own rules)

Libvirt failed to insert NAT rules properly when using nft backend.

Result:

* VM packets left VM
* But were NOT source-NAT translated
* Router dropped packets
* No internet

---

## ✅ Fix Applied

Edited:

```bash
sudo nano /etc/libvirt/network.conf
```

Added:

```conf
firewall_backend = "iptables"
```

---

## 🔁 Restart Procedure

Shutdown VM completely.

Then:

```bash
sudo systemctl restart libvirtd
sudo virsh net-destroy default
sudo virsh net-start default
```

---

## 🔎 Verification

Checked NAT table again:

```bash
sudo iptables -t nat -L -n -v
```

Now shows:

```
Chain LIBVIRT_PRT
MASQUERADE  192.168.122.0/24
```

This confirms:

Libvirt now inserts proper NAT rules.

---

## 🚀 Result

Inside VM:

```bash
ping 8.8.8.8
ping google.com
```

Both worked.

Networking fully functional.

---

## 📚 What I Learned

### Difference Between:

* DHCP working ≠ Internet working
* Bridge working ≠ NAT working
* IP forwarding ≠ MASQUERADE rule

### Libvirt NAT Architecture:

```
VM → virbr0 → host iptables MASQUERADE → physical NIC → internet
```

If MASQUERADE missing → internet fails.

---

## ⚠️ Why Docker Didn’t Break

Docker inserts its own NAT rule:

```
172.17.0.0/16
```

But libvirt network:

```
192.168.122.0/24
```

Docker does NOT manage libvirt network.

They are separate.

---

## 🛡 Final Stable Configuration

```
firewall_backend = "iptables"
```

Ensures libvirt reliably inserts iptables NAT rules on Arch.

---

## 🧩 Key Diagnostic Commands (Future Debugging)

```bash
virsh net-list --all
ip a | grep virbr0
cat /proc/sys/net/ipv4/ip_forward
sudo iptables -t nat -L -n -v
```

---

## 🔥 Real Understanding Achieved

You debugged:

* DHCP vs NAT distinction
* libvirt networking architecture
* iptables vs nft backend behavior
* Docker + libvirt interaction
* MASQUERADE rule mechanics

---

If you want, next I can create a second note:

**“How Libvirt Networking Actually Works Internally”**
(with packet flow diagrams and chain explanation)

That will level you up further.
