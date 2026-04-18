### Unit V: Data Link and Physical Layers

- **Datalink Functions:** Responsibilities of the Data Link Layer, framing techniques, and flow control mechanisms.
- **MAC Layer:** Description of the basic operation of the 802.11 MAC layer (Wireless LAN architecture).
- **Access Protocols:** Elaboration on CSMA protocols, the downfall of CSMA, and how Collision Detection (CD) is used.
- **Switching:** Explanation of switching methods, including Circuit Switching, and Packet Switching (Datagram vs. Virtual Circuit).
- **Error Control:** Explanation and calculation methods for Error Detection/Correction techniques like CRC and Hamming Codes (high frequency numerical problems).
  
  
# 🔹 **Unit V – Topic 1: Data Link Layer Functions**

### 🌍 Where does this layer sit?

OSI Model position → **Layer 2** (between Physical & Network layers)

|Layer|Responsibility|
|---|---|
|**Network (Layer 3)**|Who to send to (IP addressing, routing)|
|**Data Link (Layer 2)**|How to send data **within a local network**|
|Physical (Layer 1)|Sending raw bits over wires/wifi|

---

## 🔑 Why do we need the Data Link Layer?

Imagine you want to deliver a parcel **within an apartment building** → the Data Link Layer:

- Makes sure the parcel is **packaged**, **addressed to a specific door**,
    
- Ensures **delivery without corruption**,
    
- Makes sure you **don’t bump into others** in the corridor.
    

---

## 📌 Main Responsibilities of the Data Link Layer

### 1️⃣ **Framing**

- Converts raw bits → structured **frames** (like an envelope with To/From info)
    
- A frame = Header + Payload + Trailer
    

It:  
✔ Adds **MAC addresses**  
✔ Adds **error check** fields  
✔ Marks **start & end** of message

**Why framing?**  
Without structure, the receiver can't know:

> Where does my message start or end?  
> Which device is this for?

---

### 2️⃣ **Error Detection (and sometimes correction)**

Even on cables/wifi → bits flip due to:

- Noise
    
- Interference
    
- Weak signal
    

Data Link Layer uses techniques like:

- **Parity**
    
- **Checksum**
    
- **CRC (Cyclic Redundancy Check)**
    

> Detects if a frame was corrupted.  
> If bad — simply request retransmission.

---

### 3️⃣ **Flow Control**

Prevents **fast sender** from overwhelming **slow receiver**.

Analogy:

> A student taking notes can’t keep up with a fast-talking teacher → teacher must slow down.

Techniques:

- **Stop & Wait ARQ**
    
- **Sliding Window**
    

---

### 4️⃣ **MAC (Medium Access Control)**

If multiple devices share one medium → collisions may happen.

Analogy:

> Many people talking on one phone line → they must take turns.

MAC decides:

- Who gets to send?
    
- For how long?
    
- What if two send together and collide?
    

Examples:

- **Ethernet → CSMA/CD**
    
- **WiFi → CSMA/CA**
    

---

### 5️⃣ **Hop-to-Hop Delivery**

Network Layer finds destination network using **IP**,  
but **each hop** device uses **MAC address** to forward frame to the next router/device.

Analogy:

> IP is the house address in another city  
> MAC is the apartment door number for each building you pass through

---

## ✔ Summary Table

|Function|Why Needed|Key Techniques|
|---|---|---|
|Framing|Boundaries, addressing|Headers, trailers|
|Error Detection|Prevent corrupted data|Parity, CRC|
|Flow Control|Prevent overflow|Stop & Wait, Sliding Window|
|MAC|Avoid collisions|CSMA/CD, CSMA/CA|
|Hop-to-Hop Delivery|Correct next stop|MAC addressing|


# 🔹 **Topic 2: Framing Techniques**

(Data Link Layer – deeper dive)

---

## 🎯 What is Framing again?

Physical layer sends **raw bits** — continuous stream  
➡️ Receiver cannot tell **where** a message starts or ends

So Data Link Layer **groups bits into frames**:

`| Start | Header | Data | Trailer | End |`

---

## 🔥 Why do we need different framing techniques?

Because:

- Data may contain **same patterns as start/end markers**
    
- Noise can **flip bits**, break markers
    
- Different networks have different reliability needs
    

So we have multiple **ways** to mark frame boundaries.

---

# 🧱 Framing Techniques (4 types)

---

## 1️⃣ **Character Count Framing**

Header contains **number of characters** in the frame.

📦 Example:

`[10][Data....10 bytes long]`

🎯 Advantage:

- Very simple
    

⚠ Problem:  
If the **count field gets corrupted**,  
➡️ receiver loses all frame boundaries  
➡️ everything becomes garbage

✏ Analogy:

> Stick a label “This box has 20 chocolates” →  
> If label smudges, you have no idea where chocolates end.

---

## 2️⃣ **Character Stuffing**

(Used in text-based protocols like older point-to-point links)

Special characters:

- **SOH** → start of header
    
- **EOT** → end of text
    

But what if **data itself** contains SOH/EOT?  
➡️ Sender **adds an ESC (escape)** byte before them

Receiver:

- sees ESC before SOH/EOT → **data**, not boundary
    

✏ Analogy:

> If your message uses _#_ symbols and # is a boundary,  
> you put a backslash → # so it’s treated as normal text

✔ More reliable than character count  
✘ Only works with **text/ASCII** characters

---

## 3️⃣ **Bit Stuffing**

(Used in bit-oriented protocols e.g., HDLC)

Boundary pattern: **01111110** (flag)

Problem: What if data contains **01111110** too?

Solution:  
Sender inserts a **0 after every 5 consecutive 1s**  
Receiver removes that added 0 → recovers original data

✔ Works even for binary data  
✔ Very reliable  
✘ Extra overhead if data has many 1s

📌 Widely used in real networks

---

## 4️⃣ **Physical Layer Violations**

Used in **LANs like Ethernet**

Example:

- Instead of bit patterns, we use **signal voltage/time violations**
    
- Certain **unusual voltage** = start/end marker
    

✔ Fast and reliable  
✘ Hardware dependent (PHY layer details matter)

Analogy:

> Changing the tone of your voice to indicate you’re switching topics

---

# 🏁 Quick Comparison

|Technique|Works with|Main Idea|Problem|
|---|---|---|---|
|Character Count|Text|Count number of bytes|Count field corruption ruins everything|
|Character Stuffing|Text|Escape special markers|ASCII-specific|
|Bit Stuffing|Binary data|Insert zero after 5 ones|Some overhead|
|Physical Layer Violations|Signals|Use voltage/timing rules|Hardware complexity|

---

# 🧠 In Simple Words

|Think of a class test paper|
|---|
|Character Count → “This answer has 20 words”|
|Character Stuffing → Escape special words with symbol|
|Bit Stuffing → Insert zero to avoid special bit pattern|
|Physical Layer Violation → Change handwriting style|


# 🔹 **Topic 3: Flow Control Mechanisms**

(Data Link Layer — deeper dive)

---

## 🎯 What problem does Flow Control solve?

A fast sender can transmit data **faster** than a slow receiver can process.

➡️ Receiver buffer overflows  
➡️ Frames are dropped → retransmissions → network waste

Flow Control ensures:

> Sender does **not flood** the receiver.

---

## 🧠 Big Picture:

Flow Control is **end-to-end within a link**  
(not entire internet → that’s congestion control in Transport layer)

---

# 📌 Two main techniques

|Method|Type|Where used|Efficiency|Usage today|
|---|---|---|---|---|
|**Stop-and-Wait ARQ**|Basic|Simple links|Low|Wireless, IoT|
|**Sliding Window ARQ**|Advanced|High-speed links|High|Ethernet, Broadband|

---

---

# 1️⃣ Stop-and-Wait ARQ

### 🧩 How it works:

1. Sender sends **one frame**
    
2. Waits for **ACK** (acknowledgment)
    
3. If ACK received → next frame
    
4. If timeout → **resend**
    

📦 Timeline:

`S: Frame1 → → → R: ← ACK1 S: Frame2 → → → R: ← ACK2`

✔ Very reliable  
✘ Very slow because sender is **idle** while waiting

🎯 Use case:

> Low-speed, long-delay wireless systems (e.g., 2G)

---

### ❓What if ACK is lost?

Sender times out → sends again  
Receiver sends **NAK** or **duplicate ACK**

---

---

# 2️⃣ Sliding Window ARQ

(Actually: where _real-world networking happens_)

### ⭐ Key Idea:

Sender can send **multiple frames** before needing an ACK

🔹 Sender has **send window**  
🔹 Receiver has **receive window**

Example window size = 4:

`Frames allowed: [0][1][2][3] → sent continuously Then wait for ACKs`

Receiver sends cumulative ACKs:

`ACK 3 → means frames 0,1,2 received`

✔ MUCH faster — full bandwidth utilized  
✔ Handles packet loss efficiently  
✘ More complex

---

### Variants of Sliding Window

|Variant|When used|How handled|
|---|---|---|
|**Go-Back-N**|Single frame lost|Resend _all_ from missing frame onward|
|**Selective Repeat**|Multiple losses|Resend **only** wrong frames|

📌 Ethernet & high-speed networks use **Selective Repeat**  
→ efficient & reduces retransmissions

---

---

# 🧠 Analogy

|Scenario|Flow control behavior|
|---|---|
|You hand assignments to a teacher **one by one** → Stop-and-Wait||
|You hand **a bunch** and wait for batch approval → Sliding Window||
|If they lose page 3 — you rewrite 3 only → Selective Repeat||
|Teacher loses page 3 — demands pages 3–end again → Go-Back-N||

---

---

# ✨ Quick Summary Table

|Feature|Stop & Wait|Sliding Window|
|---|---|---|
|Frames in flight|1|Multiple|
|Throughput|Low|High|
|ACK behavior|Each frame|Cumulative / Selective|
|Buffer required|Small|Large|
|Efficiency|Poor|Excellent|

---

Flow Control: **link-level** problem  
Congestion Control: **network-level** problem

They are NOT the same! 👍


# **802.11 MAC Layer (Wi-Fi MAC Layer)**

_(Architecture + How wireless medium access works)_  
━━━━━━━━━━━━━━━━━━

## 📌 First: Why does Wi-Fi need a special MAC Layer?

In **wired Ethernet**, a device can sense collisions directly → CSMA/CD  
But **wireless signals collide invisibly**:

- A device cannot transmit and listen at the same time
    
- Hidden nodes exist (two devices can’t detect each other but both talk to the AP)
    

So **collision detection (CD) is impossible** → instead Wi-Fi uses **collision avoidance** → CSMA/CA

---

## 🏛 Wireless LAN Architecture (802.11)

|Component|Meaning|Role|
|---|---|---|
|**Station (STA)**|Laptop, phone, etc.|Connects to AP|
|**Access Point (AP)**|Wi-Fi router|Coordination + link to wired LAN|
|**Basic Service Set (BSS)**|One AP + stations connected|Main wireless coverage area|
|**Extended Service Set (ESS)**|Multiple APs connected|Campus/large network|

Analogy:  
📡 **AP = traffic controller**  
🚗 **STAs = cars**  
🛣 **BSS = road under one controller**  
🏙 **ESS = many connected roads**

---

## 🧠 802.11 MAC Sublayers

|Sublayer|Purpose|
|---|---|
|**MAC Layer**|How devices share the air (medium access control)|
|**PHY Layer**|Converts bits ↔ radio waves|

We focus on MAC: **How to talk without colliding?**  
Answer → **CSMA/CA + special mechanisms**

---

## 🔑 Core Mechanisms of 802.11 MAC

|Mechanism|Purpose|
|---|---|
|**CSMA/CA**|Wait before transmitting to avoid collision|
|**Interframe spaces (DIFS, SIFS)**|Timing control — who gets priority|
|**ACK for every frame**|Confirms delivery (wireless unreliable)|
|**RTS/CTS**|Prevent hidden node problem|
|**Backoff Algorithm**|Random wait time after each collision|

---

### 🚦 CSMA/CA (Carrier Sense Multiple Access / Collision Avoidance)

Steps:

1️⃣ Listen to channel  
2️⃣ If busy → wait  
3️⃣ If free → wait **DIFS** then backoff  
4️⃣ Send frame  
5️⃣ Wait for **ACK**  
6️⃣ No ACK → collision assumed → increase backoff → retry

> Because we **cannot detect collisions**, we **assume** collision if no ACK returns.

---

### 🙈 Hidden Node Problem

A → AP ← C  
But A and C **cannot hear each other**  
Both transmit → collision at AP

Solution → **RTS/CTS handshake**  
(Small request packet → Clear channel)

Steps:

`Sender → RTS → AP AP → CTS → All STAs listen → channel reserved Sender → Data → AP → ACK`

Works best when:

- Network crowded
    
- Large packet sizes
    

---

## ⏱ Interframe Spaces

|Interval|Priority|Used By|
|---|---|---|
|**SIFS** (shortest)|Highest|ACKs, CTS replies|
|**DIFS**|Normal|Data frames|
|**EIFS**|Error recovery|After corrupted frame|

SIFS ensures **ACK always wins** over new data transmissions.

---

## 🎯 Quick Analogy

Wi-Fi = A classroom

- Teacher = AP
    
- Students = devices
    
- Raise hand = RTS
    
- Teacher approves = CTS
    
- Speak = DATA
    
- Teacher says "Correct" = ACK
    
- Wait a bit before speaking again = Backoff
    

Everyone avoids talking at once → fewer collisions.

---

## ✍️ Exam-Friendly Points

✔ Wireless uses **CSMA/CA**, not CSMA/CD  
✔ Uses **ACK for every frame**  
✔ Hidden node problem solved by **RTS/CTS**  
✔ Uses **Interframe spacing** (SIFS < DIFS)  
✔ Uses **random backoff** to avoid repeated collisions  
✔ Managed via **AP + BSS + ESS architecture**



## 🔹Next Topic: **Downfall of CSMA/CD + Introduction to CSMA/CA**

### 🛑 Why CSMA/CD became a problem?

CSMA/CD works well in **wired Ethernet** but **fails badly in wireless networks**.

Here’s why:

|Issue|Explanation|
|---|---|
|**Collision detection is impossible over Wi‑Fi**|In wireless, the sender cannot listen while transmitting → signal too weak compared to its own transmission|
|**Hidden Node Problem**|Two devices may not “hear” each other but both send to same AP → collisions at AP|
|**Exposed Node Problem**|A device hears another transmitting and waits, even though it could safely transmit to a different receiver|
|**Wasted bandwidth**|Collisions are detected only _after_ they happen → retransmissions = delay + congestion|

---

### 📡 Real-World Analogy

Imagine a group of people **shouting across rooms**:

- In wired networks, everyone stands close → can hear if two people speak simultaneously → collisions detected.
    
- In wireless, walls block sound → someone may talk without knowing another person is already speaking → collision but **nobody knows**.
    

📌 Conclusion: CSMA/CD **cannot** handle wireless communication.

---

## 🌟 Solution: CSMA/CA (Collision Avoidance)

Instead of detecting collisions, Wi‑Fi **avoids them before transmission**.

Mechanisms used:

1. **Backoff timers** (Wait random time before sending)
    
2. **RTS/CTS signals**
    
    - RTS → “Request to Speak”
        
    - CTS → “Clear to Speak”
        
3. **ACK after successful frame**
    

➡️ Even Hidden Node problem is reduced because CTS is broadcast to all.

---

### Quick Comparison

|Feature|CSMA/CD|CSMA/CA|
|---|---|---|
|Used in|Wired Ethernet|Wi‑Fi (802.11)|
|Detects collision?|Yes|No|
|Prevents collision?|No|Yes|
|How?|Listen while sending|RTS/CTS + backoff timers|

---

### Exam‑friendly Summary

> CSMA/CD became ineffective for wireless networks due to hidden/exposed nodes and inability to detect collisions while transmitting. Thus Wi‑Fi uses CSMA/CA, which _avoids_ collisions using RTS/CTS and backoff algorithms.


### ⭐ **Next Topic: Switching in Data Link Layer**

Switching = How data travels from source → destination through a network.

🏙️ **Think of the network as a city**  
Devices = houses  
Switches/routers = junctions  
Switching techniques = road rules deciding **how** your message travels.

There are **3 major types** 📌  
(These are VERY important for exams)

---

## 🚦 1️⃣ Circuit Switching

Used in: _Traditional telephone networks_

📞 **Once a call starts**, the network creates a **fixed** dedicated path.

✔️ Pros

- Guarantee end‑to‑end bandwidth
    
- No delays once call starts (constant rate)
    

❌ Cons

- Wasteful → if silence on the call, path still reserved
    
- Slow to setup (call setup time)
    

**Analogy**:  
You book an entire road from your home to your friend’s — nobody else can use it until you finish talking.

📌 Not used in typical packet data networks.

---

## 📦 2️⃣ Packet Switching

Used in: _Internet (IP Networks)_

Data is broken into **packets**.  
Each packet travels **independently**.

Two types:

---

### 🗺️ a) Datagram Packet Switching

🧭 Each packet carries full destination address  
Routers decide path dynamically → changing routes possible  
Packets may arrive **out of order**

✔️ Flexible & robust  
❌ Requires reordering at receiver

📌 Used by **IP** (very important)

**Analogy**: Posting letters individually — each may take a different route.

---

### 🚆 b) Virtual Circuit Packet Switching

Before actual data:  
A **virtual path** is established → All packets follow **same route** like a train track

✔️ In‑order delivery  
✔️ Better QoS  
❌ Setup delay

Used in:  
ATM, Frame Relay (older but exam favorites!)

**Analogy**: You book a fixed railway route → All compartments (packets) follow same track.

---

### Quick Comparison Table 📘

|Feature|Circuit Switching|Datagram|Virtual Circuit|
|---|---|---|---|
|Path|Fixed|Dynamic|Pre‑established|
|Reliability|High constant|Medium|High|
|Packet order|In order|Not guaranteed|Guaranteed|
|Setup required|Yes|No|Yes|
|Resource usage|Wastes|Efficient|Moderate|
|Example|Old PSTN|Internet (IP)|ATM/Frame Relay|

---

### Exam Short Notes Format 🎯

**Define Switching**  
Technique of forwarding frames between network nodes.

**Types:**  
1️⃣ Circuit Switching → Fixed reserved path  
2️⃣ Packet Switching  
 • Datagram → Dynamic path, out-of-order  
 • Virtual Circuit → Planned path, in-order

**Reason:** Efficiently use network resources.

---

### Why Packet Switching is used today?

|Need|Circuit Switching|Packet Switching|
|---|---|---|
|Internet traffic bursty?|❌ No|✔️ Yes|
|Resource sharing efficient?|❌ Wasteful|✔️ Very efficient|
|Large scalability?|❌ Limited|✔️ Massive|

Result → **Packet switching dominates modern networks**.


# 🧩 **Error Control (Error Detection & Correction)**

(Data Link Layer — Very High Exam Weight)

## 🎯 Why Error Control is needed?

When data travels through a physical medium (cables, wireless), **noise** can flip bits:

> Example: `01100110 ➝ 01100100` (1 bit flipped → WRONG data)

So we need techniques to **detect** or **correct** errors.

---

## 📌 Two Categories

|Category|What it does|Extra transmission?|Used in|
|---|---|---|---|
|**Error Detection**|Detect errors → sender retransmits|Yes (ACK/NACK)|Ethernet, Wi‑Fi|
|**Error Correction**|Fix errors at receiver itself|No retransmission|Satellites, deep‑space|

---

## 🎯 Common Error Detection Techniques

|Technique|Idea|Strength|Weakness|
|---|---|---|---|
|**Parity Bit**|Count 1s (even/odd)|Very simple|Detects only odd # bit errors|
|**Checksum**|Add data & send sum|Detects burst errors|Not 100% reliable|
|**CRC**|Polynomial division 😎|Best detection method|No correction|

### CRC — **Most important for exam**

Used in: Ethernet, Wi‑Fi, USB, DVDs …

✔ Detects **single, double & burst errors**  
❌ Cannot correct → uses retransmission (ARQ)

---

### 📌 CRC Math — Simple Example

Let:

- Data = `11010011101100`
    
- Divisor (Generator) = `1011` (length=4 → append 3 zeros)
    

Steps:  
1️⃣ Append 3 zeros → `11010011101100 000`  
2️⃣ Do **binary division (XOR)**  
3️⃣ Remainder = CRC bits

Final transmitted frame = Data + Remainder ✔  
If receiver divides and remainder ≠ 0 → ERROR detected ❌

> 🔑 Polynomial form is why CRC catches burst errors very well.

📝 I can solve a full CRC numerical with you when needed.

---

## 🎯 Error Correction Technique

### 🔹 Hamming Code

Fixes **single‑bit errors** without retransmission.

Used in:

- Memory (ECC RAM)
    
- Satellite communication
    

---

### Hamming Code Formula

To find number of parity bits:

`2^r ≥ m + r + 1`

Where:

- `m = data bits`
    
- `r = parity bits`
    

---

### ✨ Example (Guaranteed to appear in exams)

Data bits: **7 bits**

Find minimum r:

`2^r ≥ 7 + r + 1 r = 4 (because 16 ≥ 7+4+1 = 12)`

So total bits = 7 + 4 = **11 bits**

Now:

- Parity bit positions = 1,2,4,8 (powers of 2)
    
- Fill data in remaining positions
    
- Calculate parity bits using **even parity**
    

👉 Receiver calculates all parity again  
👉 Wrong parity combination gives the **error position in binary**

➡ Perfect pinpointing of bad bit → flip it → corrected 🎯

---

## 🧠 Quick Summary for Exam

| Technique        | Layer              | Used for               | Retransmission? |
| ---------------- | ------------------ | ---------------------- | --------------- |
| Parity           | Data Link          | Simple error detection | Yes             |
| Checksum         | Transport          | Internet packets       | Yes             |
| **CRC**          | **Data Link**      | Strong error detection | Yes             |
| **Hamming Code** | Data Link/Physical | Error correction       | No              |


## EXTRA DATA LINK LAYER CONCEPTS

(That often appear in exams)

---

# 1️⃣ **Bandwidth**

### 📌 What is Bandwidth?

→ Bandwidth is the **maximum data rate** a network link can carry per second.

📍Measured in:

- **bps**, Kbps, Mbps, Gbps…
    

### 🎯Why does Data Link Layer care about bandwidth?

Because:

- Frames must be sent at a **rate the physical medium can support**
    
- Flow control algorithms depend on bandwidth limits
    
- Congestion occurs if sender > bandwidth
    

### 🎭 Analogy

Bandwidth = width of a road  
Frames = cars  
More width → more cars per second.

---

# 2️⃣ **Latency (Delay)**

Not only how **much** can be sent — but **how fast it travels**.

Components of delay:

|Type|Meaning|
|---|---|
|Transmission Delay|Time required to push bits into the wire|
|Propagation Delay|Time signal takes to travel through medium|
|Queueing Delay|Waiting time in router/switch queue|
|Processing Delay|Header check, error check time|

🕒 Total Delay = All above combined.

---

# 3️⃣ **Throughput**

### 📌 Actual usable data rate

Because we lose some bandwidth to:

- Errors + retransmissions
    
- Collisions in LANs
    
- Protocol overheads (headers)
    

📍Throughput ≤ Bandwidth

🎭 Analogy:  
You have a 4–lane road (bandwidth),  
but due to traffic and signals only 2 lanes move → throughput.

---

# 4️⃣ **Jitter**

→ Variation in delay between received frames.

Important for:  
✔ Voice calls  
✔ Live streaming  
✔ Online gaming

🎭 Analogy:  
A bus coming at 10:00, then 10:05, then 9:55 → _unstable experience_

---

# 5️⃣ **Medium Access Control (MAC) — extra points**

MAC = Decides **who gets the channel & when**  
Used when multiple devices share the same medium

Two category:

|MAC Type|Examples|
|---|---|
|Contention-based|CSMA/CD (Ethernet), CSMA/CA (WiFi)|
|Controlled access|Token passing (Token Ring), Polling|

---

# 6️⃣ **Switching in Data Link Layer (extra depth)**

|Switching Type|Used In|How it Works|
|---|---|---|
|Circuit Switching|Phone networks|Dedicated path created|
|Datagram Switching|Internet|Packet independently routed|
|Virtual Circuit Switching|Frame Relay|Layout path before sending|

🎯Exam Tip:

> LAN switching = Frame switching at Layer 2  
> Routers = Packet switching at Layer 3

---

# 7️⃣ **Addressing Types**

|Addressing|Layer|Identifier|
|---|---|---|
|Physical|Data Link Layer|MAC address|
|Logical|Network Layer|IP address|

🧠 Must state this in theory exams.

---

# 8️⃣ **Channel Capacity (Numerical Theory)**

Formulas sometimes appear in exams:

### _Nyquist Capacity Formula_

Noiseless channel:

C=2Blog⁡2(M)C=2Blog2​(M)

### _Shannon Capacity Formula_

Noisy channel:

C=Blog⁡2(1+S/N)C=Blog2​(1+S/N)

Where:

- **C** = Capacity (bps)
    
- **B** = Bandwidth (Hz)
    
- **M** = Signal levels
    
- **S/N** = Signal-to-noise ratio
    

These relate **Physical Layer**, but used in **Data link performance estimation**.

---

## 🔥 Quick Exam-ready Summary

|Concept|Defined in|Why Important|
|---|---|---|
|Bandwidth|PHY Layer|Upper bound for data rate|
|Throughput|DL Performance|Real achieved data rate|
|Delay & Jitter|QoS Metrics|Sensitive apps need low delay|
|Channel Capacity|PHY Theory|Evaluates link performance|
|MAC|Data Link Layer|Controls sharing of medium|
|Switching|DL/Network Layer|Decides data forwarding style|

---

### If any question asks:

> "Discuss performance issues in Data Link Layer"

You MUST mention:  
✔ Bandwidth  
✔ Delay  
✔ Throughput  
✔ Jitter  
✔ Error rate  
✔ MAC efficiency