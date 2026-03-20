# 🔌 Networking CLI Tools — Complete Reference

> [!abstract] Overview
> Essential networking tools for diagnosis, discovery, and analysis. Covers `nmap`, `ss`, `netstat`, `dig`, `traceroute`, `ping`, `ip`, and more.

**Tags:** #networking #cli #linux #tools #nmap #dns

---

## 🗺️ nmap — Network Scanner

> The go-to tool for host discovery, port scanning, and service fingerprinting.

### Basic Syntax

```bash
nmap [OPTIONS] [TARGET]
# TARGET can be: IP, hostname, CIDR range, file
```

### Host Discovery

```bash
# Ping scan — just check which hosts are up (no port scan)
nmap -sn 192.168.1.0/24

# Scan a single host
nmap 192.168.1.1

# Scan multiple hosts
nmap 192.168.1.1 192.168.1.50 10.0.0.1

# Scan a CIDR range
nmap 192.168.1.0/24

# Scan from a file list
nmap -iL targets.txt
```

### Port Scanning

```bash
# Scan default 1000 ports (TCP)
nmap 192.168.1.1

# Scan ALL 65535 ports
nmap -p- 192.168.1.1

# Scan specific ports
nmap -p 22,80,443,8080 192.168.1.1

# Scan a port range
nmap -p 1-1024 192.168.1.1

# Fast scan (top 100 ports)
nmap -F 192.168.1.1

# UDP scan (slower, requires root)
sudo nmap -sU 192.168.1.1

# TCP SYN scan (stealth, default with root)
sudo nmap -sS 192.168.1.1

# TCP Connect scan (no root needed)
nmap -sT 192.168.1.1
```

### Service & OS Detection

```bash
# Detect service versions
nmap -sV 192.168.1.1

# Detect OS (requires root)
sudo nmap -O 192.168.1.1

# Aggressive scan (OS + version + scripts + traceroute)
sudo nmap -A 192.168.1.1

# Combine version intensity
nmap -sV --version-intensity 9 192.168.1.1
```

### NSE Scripts

```bash
# Run default scripts
nmap -sC 192.168.1.1

# Run a specific script
nmap --script=http-title 192.168.1.1

# Run script category
nmap --script=vuln 192.168.1.1
nmap --script=auth 192.168.1.1
nmap --script=discovery 192.168.1.1

# Scan HTTP headers
nmap --script=http-headers -p 80,443 192.168.1.1

# Check for SMB vulnerabilities
nmap --script=smb-vuln* -p 445 192.168.1.1

# Banner grabbing
nmap --script=banner -p 22,80 192.168.1.1
```

### Output Formats

```bash
# Normal output to file
nmap -oN scan.txt 192.168.1.1

# XML output
nmap -oX scan.xml 192.168.1.1

# Grepable output
nmap -oG scan.gnmap 192.168.1.1

# All formats at once
nmap -oA scan_results 192.168.1.1
```

### Timing & Evasion

```bash
# Timing templates (T0=paranoid, T5=insane)
nmap -T4 192.168.1.1   # Aggressive — good for CTFs/labs
nmap -T2 192.168.1.1   # Polite — avoid detection

# Fragment packets (IDS evasion)
nmap -f 192.168.1.1

# Randomize host order
nmap --randomize-hosts 192.168.1.0/24

# Spoof source IP (needs root, advanced)
sudo nmap -S 10.0.0.1 192.168.1.1
```

### Common nmap One-Liners

```bash
# Quick full recon
sudo nmap -sS -sV -sC -O -p- -T4 -oA full_scan TARGET

# Web server check
nmap -p 80,443,8080,8443 --script=http-title,http-headers TARGET

# Find all SSH hosts in subnet
nmap -p 22 --open 192.168.1.0/24
```

---

## 🔌 ss — Socket Statistics (modern netstat)

> `ss` is faster and more detailed than `netstat`. Use this by default.

```bash
# All sockets
ss -a

# TCP sockets
ss -t

# UDP sockets
ss -u

# Listening sockets only
ss -l

# Listening TCP
ss -lt

# Listening with process info (needs root)
sudo ss -tlnp

# All established connections
ss -tn state established

# Show port numbers (no DNS resolution)
ss -n

# Filter by port
ss -tn sport = :80
ss -tn dport = :443

# Show sockets for a specific process
ss -tp | grep "nginx"

# Summary statistics
ss -s
```

### ss Output Fields

| Field | Meaning |
|-------|---------|
| `Netid` | Socket type (tcp, udp, unix) |
| `State` | LISTEN, ESTABLISHED, TIME-WAIT, etc. |
| `Local Address:Port` | Your side |
| `Peer Address:Port` | Remote side |
| `Process` | PID and program name |

---

## 📟 netstat — Network Statistics (legacy)

> Still available and useful. Prefer `ss` but know this for older systems.

```bash
# All listening ports with PID
sudo netstat -tlnp

# All connections
netstat -an

# Routing table
netstat -r

# Interface stats
netstat -i

# Continuous output
netstat -c
```

---

## 🌐 dig — DNS Lookup

> The best tool for DNS querying and troubleshooting.

```bash
# Basic A record lookup
dig example.com

# Short answer only
dig +short example.com

# Query specific record type
dig example.com A
dig example.com AAAA      # IPv6
dig example.com MX        # Mail server
dig example.com NS        # Name servers
dig example.com TXT       # TXT records (SPF, DKIM, etc.)
dig example.com CNAME     # Alias
dig example.com SOA       # Start of authority

# Use a specific DNS server
dig @8.8.8.8 example.com
dig @1.1.1.1 example.com

# Reverse DNS (IP to hostname)
dig -x 93.184.216.34

# Trace the full DNS resolution path
dig +trace example.com

# No recursion (ask root servers directly)
dig +norecurse example.com

# All records
dig example.com ANY

# Query with no extra info
dig +noall +answer example.com
```

### DNS Troubleshooting Flow

```bash
# 1. Check what your system resolves
dig example.com

# 2. Check against a known-good resolver
dig @8.8.8.8 example.com

# 3. Trace the delegation chain
dig +trace example.com

# 4. Check authoritative NS
dig example.com NS
```

---

## 🔍 nslookup — Simple DNS Lookup

```bash
# Basic lookup
nslookup example.com

# Reverse lookup
nslookup 93.184.216.34

# Use specific DNS server
nslookup example.com 8.8.8.8

# Interactive mode
nslookup
> set type=MX
> example.com
> exit
```

---

## 🗺️ traceroute / tracepath — Route Tracing

```bash
# Trace route to host
traceroute example.com

# Use ICMP instead of UDP
traceroute -I example.com

# Use TCP (better through firewalls)
traceroute -T -p 443 example.com

# Max hops
traceroute -m 20 example.com

# No DNS resolution (faster)
traceroute -n example.com

# tracepath (no root needed)
tracepath example.com
```

---

## 📡 ping — Connectivity Test

```bash
# Basic ping
ping example.com

# Ping count
ping -c 4 example.com

# Ping with interval
ping -i 0.5 example.com

# Flood ping (root, stress test)
sudo ping -f example.com

# Set packet size
ping -s 1400 example.com

# Ping IPv6
ping6 example.com
```

---

## 🗂️ ip — Interface & Route Management

> Replaces `ifconfig` and `route`. Modern Linux standard.

```bash
# Show all interfaces
ip addr
ip a

# Show specific interface
ip addr show eth0

# Show routing table
ip route
ip r

# Add/remove IP address
sudo ip addr add 192.168.1.100/24 dev eth0
sudo ip addr del 192.168.1.100/24 dev eth0

# Bring interface up/down
sudo ip link set eth0 up
sudo ip link set eth0 down

# Add default gateway
sudo ip route add default via 192.168.1.1

# Add static route
sudo ip route add 10.0.0.0/8 via 192.168.1.254

# Show neighbor table (ARP)
ip neigh
```

---

## 🔗 Common Combos

```bash
# What's listening on port 8080?
sudo ss -tlnp | grep 8080

# What process is using port 443?
sudo ss -tlnp sport = :443

# Full recon on a target (CTF/pentest lab)
sudo nmap -sS -sV -sC -p- -T4 TARGET && \
  dig TARGET && \
  traceroute TARGET

# Check DNS propagation across resolvers
for dns in 8.8.8.8 1.1.1.1 9.9.9.9; do
  echo "=== $dns ==="; dig @$dns +short example.com
done

# Find all open ports on local machine
sudo ss -tlnp
sudo nmap -p- 127.0.0.1
```

---

## 🔗 Related Notes

- [[curl — HTTP Client]]
- [[Security Recon Tools]]
- [[Networking Fundamentals — OSI & TCP-IP]]
- [[Firewall & iptables]]

---
*Last updated: 2026-03 | Tools: nmap, ss, dig, traceroute, ip*