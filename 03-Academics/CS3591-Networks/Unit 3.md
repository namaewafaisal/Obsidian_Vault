### Unit III: Network Layer

- **IP Addressing:** Detailed comparison of IPv4 and IPv6.
- **Control Protocols:** Detailed explanation of ARP, RARP, ICMP, and DHCP protocols.
- **Subnetting/CIDR:** Calculation and concepts related to subnetting, CIDR allocation, and IP address optimization (high frequency numerical problems).

# 📍 UNIT III — NETWORK LAYER

The Network Layer is responsible for **end‑to‑end delivery** of packets across multiple networks.  
It decides **WHERE** the data goes and **HOW** it gets there.

📌 Major Responsibilities:

- Logical addressing (IP)
    
- Routing (path selection)
    
- Packet forwarding (hop‑to‑hop movement)
    
- Fragmentation & reassembly (MTU issues)
    
- Error + control messaging (ICMP)
    
- Address resolution (ARP)
    

> Think of Network Layer like **Google Maps for data** — decides the best path to reach the destination.

---

# 🔶 TOPIC 1 — IP ADDRESSING

**IP → Internet Protocol**  
Every device on the Internet must have a **unique logical address**.

Two versions exist:

- **IPv4** (Internet Protocol version 4)
    
- **IPv6** (Internet Protocol version 6)
    

### 📍IPv4 vs IPv6 — Exam Table (must write!)

|Feature|IPv4|IPv6|
|---|---|---|
|Address size|32 bits|128 bits|
|Example|192.168.1.1|2001:0db8:85a3::8a2e:0370:7334|
|Address space|~4.3 billion|Virtually unlimited|
|Representation|Decimal dotted|Hexadecimal + colons|
|Header size|20 bytes|40 bytes (simplified fields)|
|Config|Manual/DHCP|Auto-configuration|
|Security|Optional IPSec|Mandatory IPSec|
|NAT needed?|Yes (address shortage)|No|

🧠 Exam tip:

> IPv6 introduces **Auto address config**, **Flow label**, and **No fragmentation by routers**.

---

# 🔶 TOPIC 2 — CONTROL PROTOCOLS

These support operation of IP; they aren't used to carry data, only control info.

## 2.1 ARP — Address Resolution Protocol

Purpose: Find **MAC (Physical) Address** from **IP Address** inside LAN

🎯 Mapping:  
➡ **IP → MAC**

Analogy:  
You know a friend’s name (IP), ARP asks:  
“Ok but what’s their house address (MAC)?”

Used when sending data to:

- Destination in SAME network
    
- **Next hop router** in different network
    

---

## 2.2 RARP — Reverse Address Resolution Protocol

Opposite of ARP

🎯 Mapping:  
➡ **MAC → IP**

Used by diskless machines on boot-up  
(They know only their hardware address)

📝 Rarely used today → replaced by DHCP

---

## 2.3 ICMP — Internet Control Message Protocol

Handles **error reporting** & **diagnostics**.  
ICMP messages are sent by **routers**.

Examples:

- Destination Unreachable
    
- Time Exceeded (Traceroute)
    
- Echo Request/Reply (Ping)
    

> ICMP ⚠️ does NOT carry application data — only network error messages.

---

## 2.4 DHCP — Dynamic Host Configuration Protocol

Automatically provides:  
✔ IP address  
✔ Subnet Mask  
✔ Default Gateway  
✔ DNS Server

Analogy:  
A receptionist gives room numbers to hotel guests → No manual assignment needed.

---

# 🔶 TOPIC 3 — SUBNETTING & CIDR

Most important numerical topic 🔥

## Why Subnetting?

- Better IP utilization (avoid wastage)
    
- Blocks unauthorized access
    
- Reduces broadcast traffic
    

📌 Network divided into **smaller networks** by borrowing **host bits** to create **network bits**.

Exam must‑write formula:

`Hosts per subnet = 2^H – 2`

(H = host bits)

---

### CIDR — Classless Inter‑Domain Routing

Notations like:

`192.168.10.0/24`

Here **/24** = number of **network bits**  
Remaining bits → host addresses

CIDR solves:

- Class boundaries problem (only Class A/B/C earlier)
    
- Route table explosion (summarization)
    

Example:

- Combining **4 networks**:
    

`192.168.4.0/24 192.168.5.0/24 192.168.6.0/24 192.168.7.0/24 ↓ Aggregated as: 192.168.4.0/22`

> CIDR = Subnetting + Supernetting


## 🌐 **IP Addressing (Deep & Intuitive)**

### 🎯 Why IP Address Exists?

Think of the internet as a **global postal system**.

|Real World|Internet|
|---|---|
|Your Home Address|IP Address|
|Postal Sorting System|Routers|
|Letters/Packages|Data packets|

➡ Every device needs a **unique** address to send and receive data.

---

## ✳ IPv4 (Internet Protocol version 4)

🧮 **Length:** 32 bits  
📌 **Format:** Four decimal numbers → e.g., `192.168.1.10`  
🪪 Total possible addresses: **4.3 billion**

### 🔹 Structure (like house → street → city)

|Part|Example|
|---|---|
|Network ID|192.168.1|
|Host ID (device)|.10|

Example:

> **192.168.1** = Street  
> **10** = House number

---

### 🚫 Problem with IPv4

Internet grew rapidly:

- Smartphones
    
- Smart TVs
    
- Cars
    
- IoT devices
    
- Servers and cloud services
    

➡ 4.3 billion addresses not enough!  
➡ So people invented **NAT** to temporarily survive

---

## 🛡 NAT – Network Address Translation

LAN home network uses **private IPs**:

- 192.168.x.x
    
- 10.x.x.x
    
- 172.16.x.x – 172.31.x.x
    

Router translates **Many devices** → **1 public IP**

📌 Analogy:  
Your router = **Reception desk**

- Every person in company has **internal extension**
    
- But outsiders see **one main phone number**
    

✔ Solves IPv4 shortage  
✖ Breaks some peer‑to‑peer connections  
✖ Adds complexity

---

## 🌍 IPv6 (Internet Protocol version 6)

🧮 **Length:** 128 bits  
📌 Format: Hexadecimal  
Example:  
`2001:0db8:85a3:0000:0000:8a2e:0370:7334`

### 🎯 Who ordered this?

Internet exhausted IPv4 ⇒ New version developed.

### ⭐ Features (Why it’s better)

|Feature|IPv4|IPv6|
|---|---|---|
|Total Addresses|4.3 Billion 🥲|340 Undecillion 😱 (10³⁸)|
|Routing|Complex|Simplified|
|Security|Optional IPSec|Built‑in IPSec 🔐|
|Auto‑configuration|No|Stateless Auto‑config (SLAAC)|
|NAT|Required|Not needed|
|Header size|Variable|Simpler, fixed fields|

📌 Analogy:  
IPv4 = Small town with limited houses  
IPv6 = The entire universe → every atom gets many addresses!

---

## 🍪 Address Shorthand (IPv6)

To avoid writing long zeros:

|Full|Short|
|---|---|
|2001:0db8:0000:0000:0000:0000:1428:57ab|2001:db8::1428:57ab|

Rule:

- Removing **leading zeros**
    
- **:: only once** allowed in an address
    

---

## 🔥 Quick Comparison Table

|Feature|IPv4|IPv6|
|---|---|---|
|Address Size|32‑bit|128‑bit|
|Notation|Dotted decimal|Hexadecimal|
|Security|Added later|Built‑in|
|Auto Assign|DHCP required|SLAAC supported|
|NAT|Used widely|Not required|

---

## 🧠 Analogy Summary

| Concept    | Analogy                                                       |
| ---------- | ------------------------------------------------------------- |
| IP Address | House Address                                                 |
| IPv4       | Small city with limited houses                                |
| IPv6       | Galaxy with unlimited planets                                 |
| NAT        | Shared building entrance                                      |
| SLAAC      | Self‑assigned address when moving into new room automatically |

## 🔹 ARP – Address Resolution Protocol

**(L3 → L2 address mapping)**

📌 **Why ARP?**  
Devices communicate inside a LAN using **MAC addresses** — but applications use **IP addresses**.  
So ARP is the **translator**:

> “Given an IP Address, tell me the MAC Address.”

📌 **Process (simple analogy)**  
You know your friend’s apartment number (IP), but you need their physical door address (MAC) → ARP asks around the building.

📌 **ARP Steps:**  
1️⃣ Sender checks ARP cache  
2️⃣ If MAC unknown → sends **ARP Request** (Broadcast)

> “Who has 192.168.1.5? Tell 192.168.1.2!”  
> 3️⃣ Device with that IP → sends **ARP Reply** (Unicast)  
> “I am 192.168.1.5 — here's my MAC: AA:BB:CC:22:33:44”  
> 4️⃣ Sender stores MAC in ARP Table (short timeout)

📌 Packet Type:

|ARP Request|ARP Reply|
|---|---|
|Broadcast (FF:FF:FF:FF:FF:FF)|Unicast|

📌 Where used?  
LAN communication, Router-to-next-hop resolution.

---

## 🔹 RARP – Reverse Address Resolution Protocol

**(L2 → L3 mapping)**

📌 **Use Case:**  
Old devices like diskless workstations booted with **only MAC address** — no IP.  
They would ask a RARP server:

> “My MAC is XX:YY:ZZ. What is my IP?”

📌 Why no longer used?  
✔ DHCP replaced it  
✔ RARP required a special server  
✔ Couldn’t assign more info like gateway, DNS etc.

---

## 🔹 ICMP – Internet Control Message Protocol

Used for **error reporting and network diagnostics** — NOT for data transfer.

📌 Examples:

|ICMP Message|Used for|
|---|---|
|Destination Unreachable|Routing failure|
|Time Exceeded|TTL expired (Traceroute)|
|Echo Request/Reply|Ping|

📌 Analogy  
ICMP is like a **postman returning a failed‑delivery notice**:

> “Your packet couldn’t reach! Reason: route not found.”

📌 Lives in Network Layer but supports IP reliability.

---

## 🔹 DHCP – Dynamic Host Configuration Protocol

📌 Purpose: Automatically give devices:  
✔ IP address  
✔ Subnet Mask  
✔ Default Gateway  
✔ DNS server info

➡ Replaces RARP & manual IP assignment

📌 DORA Process 🚦  
1️⃣ **D → Discover** (Client to all: Who can give me IP?)  
2️⃣ **O → Offer** (Server: I can, here is an IP suggestion!)  
3️⃣ **R → Request** (Client: Yes, I want that IP)  
4️⃣ **A → Acknowledge** (Server confirms)

📌 Analogy  
Connect to Wi‑Fi → DHCP gives you everything automatically like a receptionist assigning a seat number in a theater.

---

# 🔥 Quick Summary Table

| Protocol | Expands To                          | Works in | Purpose                          |
| -------- | ----------------------------------- | -------- | -------------------------------- |
| ARP      | Address Resolution Protocol         | LAN      | Find MAC for given IP            |
| RARP     | Reverse ARP                         | LAN      | Find IP for given MAC (obsolete) |
| ICMP     | Internet Control Message Protocol   | Network  | Error & diagnostics              |
| DHCP     | Dynamic Host Configuration Protocol | Network  | Assign IP + config               |

---

# 🌐 Subnetting (in IP Network Layer)

### 🔹What is Subnetting?

Subnetting = dividing **one large network** into **multiple smaller networks** (subnets).

Why?

- Efficient use of IP addresses
    
- Improved security & performance
    
- Controlled broadcast traffic
    
- Easier network management
    

📌 Think of a **big apartment building** 🏢  
Subnetting splits it into **floors and sections** so mail (packets) is delivered faster without confusion.

---

## 📌 Key Terms You MUST Know (Exam‑friendly)

|Term|Meaning|Analogy|
|---|---|---|
|**Network ID**|Identifies the whole network|Apartment Building Name|
|**Host ID**|Identifies each device inside the network|House/Flat number|
|**Subnet Mask**|Shows how much portion is Network vs Host|Divider between Building and Floors|
|**CIDR Notation**|/ followed by number of network bits|e.g., /24 means 24 bits = network|

---

### 🧠 Golden Rule

> Borrow host bits → add to network bits → create more subnets  
> But: Host bits ↓ = Hosts per subnet ↓

---

## ✨ Example 1 — Very Common Exam Problem

📌 **Given**:  
Network: **192.168.1.0/24**  
Need: **4 subnets**

### Step 1️⃣: Borrow bits

4 subnets → 2 bits needed (because 2² = 4)

New subnet mask:

`/24 + 2 = /26`

Binary mask:

`11111111.11111111.11111111.11000000 255.255.255.192`

### Step 2️⃣: Calculate

|Formula|Result|
|---|---|
|Subnets|2² = **4 subnets**|
|Hosts|2^(6 host bits) − 2 = **62 hosts per subnet**|

### Step 3️⃣: List all subnets

|Subnet No|Network ID|Broadcast|Host Range|
|---|---|---|---|
|1|192.168.1.0|192.168.1.63|1 – 62|
|2|192.168.1.64|192.168.1.127|65 – 126|
|3|192.168.1.128|192.168.1.191|129 – 190|
|4|192.168.1.192|192.168.1.255|193 – 254|

💡 **Exam Tip**:  
Broadcast always = **last IP** in subnet.

---

## ✨ Example 2 — CIDR Aggregation (Short Q)

Given:

`10.0.0.0 – 10.0.3.255  (4 networks)`

Combine → how many bits?

4 networks → 2 bits (2² = 4)

So aggregated block:

`10.0.0.0/22`

(Useful for reducing routing table size)

---

## 🎯 How to Identify Network from ANY IP + Mask

Example:

`IP = 172.16.45.100 Mask = /20  → 255.255.240.0 Block size = 256 – 240 = 16`

Subnet ranges:

`172.16.0.0 172.16.16.0 172.16.32.0 172.16.48.0 ← This is the subnet for 45.x`

So:

|Item|Value|
|---|---|
|Network|**172.16.32.0**|
|Broadcast|172.16.47.255|
|Host range|172.16.32.1 – 172.16.47.254|

---

## 📌 MCQ‑Type Shortcut Reminders

✔ Private IP ranges → 10.x.x.x / 172.16–31.x.x / 192.168.x.x  
✔ First & Last IP = not assignable  
✔ Borrowing bits increases **subnets** but decreases **hosts**

---

### 🔥 One‑Line Exam Definition

> Subnetting is the process of dividing a larger network into smaller subnetworks by borrowing host bits and modifying the subnet mask to efficiently allocate IP addresses and reduce broadcast traffic.

---

