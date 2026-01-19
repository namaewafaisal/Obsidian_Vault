### Unit II: Transport Layer

- **Protocol Comparison:** Comprehensive comparison and contrast of TCP, UDP, and SCTP protocols.
- **TCP Mechanisms:** Detailed discussion of Flow Control and Congestion Control mechanisms (end-to-end reliability).
- **Congestion Avoidance:** Explanation of Congestion Avoidance techniques, including the function of DECbit.
- **Connection Management:** Explanation of TCP connection establishment and management (three-way handshake).
- **Quality of Service (QoS):** Challenges and limitations of QoS implementation.


# ⭐ **UNIT II — TRANSPORT LAYER**

### 🚦 What is Transport Layer?

- Layer 4 of OSI Model
    
- Responsible for **End‑to‑End Communication** between processes (not just devices)
    
- It provides:  
    ✓ Reliability  
    ✓ Flow control  
    ✓ Congestion control  
    ✓ Port addressing
    

---

## 1️⃣ **Protocol Comparison – TCP vs UDP vs SCTP**

| Feature            | TCP                                           | UDP                         | SCTP                                 |
| ------------------ | --------------------------------------------- | --------------------------- | ------------------------------------ |
| Full Form          | Transmission Control Protocol                 | User Datagram Protocol      | Stream Control Transmission Protocol |
| Communication Type | Connection-oriented                           | Connectionless              | Connection-oriented                  |
| Reliability        | ✔ Yes (ACK, retransmission)                   | ✖ No                        | ✔ Yes                                |
| Ordering           | ✔ In-sequence                                 | ✖ Not guaranteed            | ✔ Multiple ordered streams           |
| Speed              | Slower                                        | Faster                      | Medium                               |
| Header Size        | Larger (20+ bytes)                            | Smaller (8 bytes)           | Largest (≈ 32+ bytes)                |
| Congestion Control | ✔ Yes                                         | ✖ No                        | ✔ Yes                                |
| Applications       | Web (HTTP), Email (SMTP), File Transfer (FTP) | Live streaming, VoIP, Games | Telecom networks (VoLTE)             |

📌 **Analogy:**

- TCP = Registered Post (Sign + Tracking)
    
- UDP = Normal Post (Fast, No tracking)
    
- SCTP = Courier with multiple item handling in one package
    

---

## 2️⃣ **TCP Mechanisms – Reliability**

### 🧩 Flow Control – _Stop the sender from overwhelming the receiver_

Method Used: **Sliding Window + Advertised Window**

📌 Example:  
Receiver says: “I can take only 4 packets now”  
Sender sends 4 → waits → sends more only when allowed.

### 🚧 Congestion Control – _Protect the network from overload_

TCP uses these algorithms:

|Stage|Technique|Behavior|
|---|---|---|
|Start|Slow Start|CWND grows exponentially (1,2,4,8…)|
|Prevent Overload|Congestion Avoidance|CWND grows linearly (8→9→10…)|
|On Loss|Fast Retransmit|Resend without waiting Timer|
|Recovery|Fast Recovery|Reduce CWND but not to 1|

📌 Diagram Idea:

`Slow Start  /\             /  \           /    \  Congestion Avoidance           /      \---------`

---

## 3️⃣ **Congestion Avoidance Techniques**

_When network hints congestion → Sender reduces speed_

### Example Techniques:

|Technique|Idea|
|---|---|
|RED (Random Early Detection)|Routers drop few packets early to warn|
|ECN (Explicit Congestion Notification)|Mark packets instead of dropping|
|**DECbit** ⭐|Receiver counts congestion and informs sender using 1‑bit|

---

### ⭐ **DECbit — Simple Explanation**

🔹 Each packet carries **1 congestion bit**  
🔹 Routers monitor average queue length:

- If queue > threshold → mark bit = 1
    
- Else bit = 0
    

Receiver → counts number of 1s  
If > 50% packets marked → sender reduces rate  
Else → increase rate

📌 Like a **crowd sensor**:  
If most packets say “too crowded” → slow down traffic!

---

## 4️⃣ **TCP Connection Management**

📌 **Three‑Way Handshake**

|Step|Sender|Receiver|
|---|---|---|
|1|SYN|→|
|2|←|SYN + ACK|
|3|ACK|→|

✔ Ensures both sides ready  
✔ Initializes sequence numbers

---

## 5️⃣ **QoS — Quality of Service**

Transport layer struggles because:

- Internet is **best effort** (not guaranteed delivery)
    
- Packets can be delayed/lost/reordered
    

### QoS Requirements:

|Need|Example|
|---|---|
|Low Delay|Video Calls|
|Low Packet Loss|File Transfers|
|Fixed Bandwidth|IPTV|

### Challenges:

- Different traffic types share same network
    
- Routers treat all packets equally
    
- Hard to prioritize without changing all devices
    

---


## ✅ **Unit‑II — Transport Layer**

### 🔹 **Topic 1: Protocol Comparison — TCP vs UDP vs SCTP**

|Feature|**TCP** (Transmission Control Protocol)|**UDP** (User Datagram Protocol)|**SCTP** (Stream Control Transmission Protocol)|
|---|---|---|---|
|Connection|Connection‑oriented|Connectionless|Connection‑oriented|
|Reliability|✔ Reliable (ACK + retransmissions)|✘ No reliability|✔ Reliable like TCP|
|Data Flow Style|Byte‑stream (sequence of bytes)|Message‑oriented (datagrams)|Multi‑stream message‑oriented|
|Flow Control|✔ Yes|✘ No|✔ Yes|
|Congestion Control|✔ Yes|✘ No|✔ Yes (better than TCP)|
|Header Size|Large (20+ bytes)|Small (8 bytes)|Medium (12+ bytes)|
|Ordering|✔ In‑order delivery|✘ No guarantee|✔ Per‑stream ordering|
|Use Cases|Browsing, emails, file transfer (HTTP, FTP, SMTP)|Live streaming, gaming, VoIP|Video conferencing, telecommunication signaling (4G/5G networks)|

---

### 🧠 **Key Idea**

- **TCP** is like **Registered Post** → tracking + receipt + resend if missing
    
- **UDP** is like **Normal Post** → fast, no guarantee
    
- **SCTP** is like **Courier with multiple delivery lanes** → if one lane is blocked, others continue
    

---

### ⭐ Why SCTP was introduced?

TCP/UDP failed for telecom signaling (SS7 traffic)  
✔ Required reliability  
✔ But must deliver multiple independent streams  
✔ Must survive link failures using **multi‑homing** (multiple IPs per endpoint)

➡ **SCTP fills this gap**

---

### 🎯 Exam 6‑marks short note points

- TCP: Reliability, congestion control, flow control, 3‑way handshake
    
- UDP: Fast, no overhead, real‑time applications
    
- SCTP: Multi‑streaming + Multi‑homing, improved reliability
    
- Draw small comparison table + 3 uses each → full marks 💯
    

---

### Quick Diagram (Exam scoring booster!)

```
          TCP           UDP            SCTP
       +-------+     +-------+     +---------+
       |Reliable|     |Fast   |     |Telecom  |
       |Ordering|     |Simple |     |Multi-stream|
       +-------+     +-------+     +---------+
```

---
## 🌐 TCP Flow Control (End‑to‑End Buffer Protection)

### 🎯 Why Flow Control?

To prevent a fast sender from overwhelming a slower receiver’s buffer.

**Analogy:**  
Imagine delivering water from a tank (sender) to a small bottle (receiver).  
If water flows too fast → bottle overflows → packet loss!

Flow control controls **receiver buffer overflow** — not network congestion.

---

## 🧠 Mechanism: **Sliding Window Protocol**

TCP uses a **Receive Window (rwnd)**:

- Receiver advertises how much buffer space it has left
    
- Sender can only send data up to that window limit
    

|Term|Meaning|
|---|---|
|**rwnd**|Available buffer at receiver|
|**snd.wnd**|Sender’s allowed sending window|
|**ACK**|Acknowledgment from receiver|
|**Window Update**|Receiver tells new rwnd value|

---

### 🔄 How it Works

1️⃣ Receiver says:  
➡️ “I can take **5000 bytes**” (Advertised Window)

2️⃣ Sender sends max 5000 bytes → waits for ACK

3️⃣ Receiver frees space → updates rwnd → sends ACK with new window

4️⃣ Sender continues transmission

---

## 🧱 STOP‑AND‑WAIT vs SLIDING WINDOW

|Feature|Stop‑and‑Wait|TCP Sliding Window|
|---|---|---|
|Efficiency|Very low|Very high|
|Packets in flight|1 only|Many at once|
|Real use case|Simple links|Real Internet|

---

## 📌 Special Feature: **Zero Window Situation**

Receiver buffer is full 👉 rwnd = 0  
Sender **must stop sending** except **Zero‑Window Probes** (checking if space freed)

📘 Exam keyword:

> “TCP uses advertised window mechanism with zero‑window probe to avoid deadlock.”

---

### ✨ Flow Control = Reliable End‑to‑End Delivery

✔ No overflow  
✔ No dropped frames due to receiver being slow  
✔ Efficient pipelining

---

## 💡 Typical Exam Question & Answer

**Q) Explain TCP Flow Control. How does the sliding window help prevent buffer overflow?**  
**A)** TCP uses **sliding window flow control** based on a **receiver‑advertised window (rwnd)**.  
The receiver informs the sender of its available buffer space.  
The sender transmits only up to the allowed window size.  
When the receiver processes data, it sends ACKs with updated window sizes.  
If the buffer becomes full, a **zero window** is advertised and the sender stops sending until the window reopens.  
Thus, TCP prevents receiver buffer overflow and ensures reliable, ordered delivery.

Marks: ⭐⭐⭐⭐⭐ (5‑7 marks enough)

---

### 🧩 Link to Other Mechanisms

|Mechanism|Protects|
|---|---|
|Flow Control|Receiver buffer|
|Congestion Control (next topic)|Network routers & links|

📌 **Flow control is not for congestion!** That's the next topic.

---
## 🚦 TCP Congestion Control

Congestion control ensures that the network is **not overloaded** with too much data at once.

📌 **Main Idea:**  
TCP gradually increases the sending rate and reduces it when congestion is detected.

---

### 🧠 Key Terms

|Term|Meaning|Analogy|
|---|---|---|
|**CWND** (Congestion Window)|Limits amount of data sender can send before ACKs|Number of parcels allowed on road at a time|
|**SSThresh** (Slow Start Threshold)|Switching point between fast growth and careful growth|Speed limit point on the road|
|**RTT** (Round Trip Time)|Time for data → to receiver → ACK back|Time for a package delivery and confirmation|

---

## 📌 4 Main Phases of TCP Congestion Control

|Phase|Behavior|Growth Pattern|When Used|Quick Analogy|
|---|---|---|---|---|
|**1️⃣ Slow Start (SS)**|Probe network capacity|Exponential growth (CWND ×2)|At connection start or after timeout|Increasing speed cautiously from parking|
|**2️⃣ Congestion Avoidance (CA)**|Avoid overwhelming network|Linear growth (CWND +1)|After CWND > SSThresh|Drive steadily below speed limit|
|**3️⃣ Fast Retransmit**|Retransmit before timeout|Triggered by **3 duplicate ACKs**|Packet likely lost|Warehouse immediately resends missing parcel|
|**4️⃣ Fast Recovery**|Reduce CWND but not too much|Multiplicative decrease|After Fast Retransmit|Slow down slightly, don’t restart completely|

---

### 🔁 Flow of Congestion Control

`Start   ↓ Slow Start   (CWND grows exponentially)   ↓ if CWND reaches SSThresh Congestion Avoidance   (CWND grows linearly)   ↓ if 3 duplicate ACKs Fast Retransmit + Fast Recovery   (CWND reduced but SS not restarted)   ↓ if timeout Back to Slow Start`

---

### 🎯 Congestion Detection Methods

|Event|Action|Meaning|
|---|---|---|
|**Triple Duplicate ACKs**|Packet lost but link not full → mild congestion|Reduce CWND only a bit|
|**Timeout**|Network severely congested|Drop CWND back to **1 MSS** (restart Slow Start)|

---

### 📘 Example (Quick Numericals for Exam)

Assume:

- Initial CWND = 1 MSS
    
- SSThresh = 8 MSS
    

|RTT|CWND Growth in Slow Start|
|---|---|
|1|1 → 2|
|2|2 → 4|
|3|4 → 8 = SSThresh|
|4|Enter Congestion Avoidance: 9 → 10 → 11...|

➡️ Shows exponential → linear transition

---

### 📌 Why Congestion Control is Needed?

✔ Prevents packet loss  
✔ Ensures fair sharing of bandwidth  
✔ Maintains network stability  
✔ Improves performance & reliability

---

## 📝 Exam 4‑Mark Perfect Answer Format

> TCP uses congestion control to prevent network overload using 4 techniques:  
> Slow Start, Congestion Avoidance, Fast Retransmit, and Fast Recovery.  
> CWND grows exponentially in Slow Start until SSThresh, then grows linearly.  
> Duplicate ACKs trigger Fast Retransmit, while timeouts reset CWND to 1 MSS.  
> These mechanisms ensure reliable data delivery and efficient bandwidth usage.

---

## 🚦 Congestion Avoidance Techniques

_(Including DECbit Algorithm)_

Think of a **highway system** 🛣️:

- **Congestion** = too many cars (packets)
    
- **Goal** = prevent traffic jams **before** they happen
    

Congestion Avoidance Techniques help detect _early signs_ of congestion and slow down traffic proactively.

---

### 🔹 Key Congestion Avoidance Techniques

|Technique|How It Works|Used By|
|---|---|---|
|**AIMD** (Additive Increase, Multiplicative Decrease)|Slowly increase sending rate; sharply reduce when congestion seen|TCP|
|**RED** (Random Early Detection)|Router drops packets _before_ queue is full to warn senders|Routers|
|**DECbit**|Router marks a bit when congestion likely; sender adapts|Older TCP (DEC systems)|
|**ECN** (Explicit Congestion Notification)|Marks packets instead of dropping; end devices reduce rate|Modern TCP/IP networks|

---

## ⭐ DECbit (Digital Equipment Corporation bit)

**DECbit** is an **Explicit Feedback** Congestion Avoidance method.

📌 **Where is the DECbit placed?**

- In the packet **header**
    
- Used to indicate **congestion is building up**
    

📌 **Who sets the bit?**

- **Routers**: If queue length > threshold → set bit = 1
    
- If no congestion → bit = 0
    

📌 **Sender Action**

- After sending a window worth of data, sender checks % of ACKs 📬
    

|If > 50% ACKs have DECbit = 1|If ≤ 50% have DECbit = 1|
|---|---|
|→ Congestion is rising|→ No major congestion|
|**Decrease** window size exponentially|**Increase** window size additively|

📌 In short:

> Additive Increase + Multiplicative Decrease  
> (_Similar to AIMD but triggered earlier by router feedback_)

---

### 🧠 Small Example

|ACKs received|% with bit=1|Sender Action|
|---|---|---|
|8 ACKs, 6 have bit=1|75%|Reduce cwnd fast|
|8 ACKs, 2 have bit=1|25%|Increase cwnd slowly|

---

### 👍 Why is DECbit Good?

✔ Detects congestion _before_ packet loss  
✔ Reduces unnecessary retransmissions  
✔ Improves efficiency of routers & links

---

### 👎 Limitations

✘ Requires routers to understand DECbit  
✘ Not globally adopted—superseded by **ECN + RED + TCP congestion control**

---

## 📌 One‑Line Exam Definition

> **DECbit is an explicit feedback congestion avoidance technique where routers mark a bit in packets when congestion is growing, and senders adjust window size based on percentage of marked ACKs.**

---


## 🚦 TCP Connection Management

_(How a Reliable Connection is Created & Terminated)_

TCP is **connection‑oriented** → it must **establish** a connection _before_ sending data and **release** it _after_ use.

---

### 🟢 **Connection Establishment – Three‑Way Handshake**

Purpose:  
✔ Agree on initial Sequence Numbers  
✔ Synchronize both sender & receiver  
✔ Ensure both are ready for data transfer

Steps:

|Step|Sender (Client)|Receiver (Server)|Meaning|
|---|---|---|---|
|**1**|`SYN`|—|“I want to start a connection.”|
|**2**|—|`SYN + ACK`|“Okay! I’m ready. You start with this number.”|
|**3**|`ACK`|—|“Thanks! Let’s communicate.”|

📌 After this handshake → **Data transmission begins**

📌 Analogy:  
You knock door (SYN),  
Someone opens & says “come in” (SYN+ACK),  
You enter and say “Thanks” (ACK).

---

### 🔴 **Connection Termination – Four‑Way Handshake**

Why 4 steps?  
Because TCP is **full‑duplex** → both sides must close independently.

|Step|Who|Message|Meaning|
|---|---|---|---|
|**1**|Client → Server|`FIN`|“I’m done sending.”|
|**2**|Server → Client|`ACK`|“I received your finish.”|
|**3**|Server → Client|`FIN`|“I’m also done sending.”|
|**4**|Client → Server|`ACK`|“Goodbye!”|

✔ Both directions properly closed  
✔ No data lost

---

### ⏳ Why **TIME‑WAIT** state?

After sending the final ACK → client waits ~2×MSL (Maximum Segment Lifetime)

Reason:

- To ensure last ACK reaches server successfully
    
- Prevent old duplicate packets from previous connections causing issues
    

📌 So TCP prioritizes **safety > speed**

---

### ✨ Summary Table

|Phase|Method|Packets|Purpose|
|---|---|---|---|
|Start|3‑Way Handshake|SYN, SYN+ACK, ACK|Synchronize and start communication|
|End|4‑Way Handshake|FIN, ACK, FIN, ACK|Gracefully close both directions|
|After End|TIME‑WAIT|—|Avoid confusion from delayed packets|


## 🌐 **Quality of Service (QoS)** — _Transport Layer Perspective_

### 📌 What is QoS?

QoS = _Quality of Service_  
It refers to techniques used to ensure **reliable**, **predictable**, and **controlled** transmission of data over a network—especially under congestion.

Think of it like:

> 🚦Traffic rules on a busy road, ensuring ambulances 🚑 reach first (critical traffic), while normal cars 🚗 wait if needed.

---

### 🎯 Why do we need QoS?

Different applications need different requirements:

|Application|Needs|
|---|---|
|Video calls / VoIP|Low delay ⏱ + low jitter (variation)|
|Online gaming 🎮|Low latency|
|File downloads|High throughput 📥|
|Email|No urgency but accuracy|

Without QoS:

- Packets compete equally
    
- Voice/video becomes choppy 😖
    
- Critical data may be delayed
    

---

### 🔑 **QoS Parameters**

QoS focuses on controlling:

|Parameter|Meaning|
|---|---|
|**Bandwidth**|Maximum data rate available|
|**Delay (Latency)**|Time taken for packet to reach destination|
|**Jitter**|Variation in delay — big problem for voice/video|
|**Packet Loss**|Dropped data during congestion|

---

### 🧰 QoS Techniques (How do we manage traffic?)

|Technique|How it helps|
|---|---|
|**Prioritization**|High‑priority traffic sent first (e.g., video/audio)|
|**Traffic Shaping**|Smooths out bursts — sends at regulated rate|
|**Packet Scheduling**|Queue management in routers|
|**Admission Control**|Rejects new flows if network is overloaded|
|**Resource Reservation**|Reserves bandwidth for critical apps (like RSVP protocol)|

---

### 🚫 Challenges & Limitations

|Issue|Explanation|
|---|---|
|Hard to implement end‑to‑end|Many networks (ISPs) must cooperate|
|Expensive routers needed|Require processing per flow|
|Difficult scalability|Billions of packets & users|
|Works better in managed networks|WAN/Internet QoS is limited|

---

### 🧠 Exam Short Answer

> QoS ensures efficient network resource utilization by controlling delay, jitter, bandwidth, and packet loss using techniques like prioritization, scheduling, admission control, and resource reservation.

---

### 🎬 Summary Analogy

Internet = busy highway  
QoS = VIP lane 👑 + traffic control 🚦  
Goal = video calls & gaming stay smooth even when others are downloading movies 🍿

---

## 🔹Additional / Unmentioned but Relevant Topics in Transport Layer

### 1️⃣ **Port Numbers & Multiplexing / Demultiplexing**

📌 **Port Number** = identifies **which application** inside the device should receive the data.

|Layer|Identification|
|---|---|
|Network Layer|IP Address → Which device?|
|Transport Layer|Port Number → Which application inside device?|

📥 **Multiplexing** — Many apps send data → Transport Layer packs → Single network path  
📤 **Demultiplexing** — Received data → Sorted & delivered to correct app using port #

Examples:

- HTTP → Port **80/443**
    
- DNS → Port **53**
    
- FTP → Port **21**
    

📌 Without ports: All packets would arrive but apps won’t know who they belong to!

---

### 2️⃣ **SCTP (Stream Control Transmission Protocol)**

A newer transport protocol combining best features of TCP & UDP.

|Feature|TCP|UDP|SCTP|
|---|---|---|---|
|Reliability|✔|✘|✔|
|Fast streaming|✘|✔|✔|
|Multiple Streams per connection|✘|✘|✔|
|Multi‑Homing|✘|✘|✔ (Backup paths)|

Used in telecom networks (4G/5G signaling).

Analogy 🎯  
TCP = Registered Post (slow, reliable)  
UDP = Postcard (fast, no guarantee)  
SCTP = Courier with **multiple delivery paths** + **parallel message channels**

---

### 3️⃣ **TCP Timers**

Used to ensure reliability:

|Timer|Purpose|
|---|---|
|**Retransmission Timer**|Detect lost segments|
|**Persistence Timer**|Prevent deadlock during zero‑window|
|**Keep‑Alive Timer**|Detect dead connections|
|**Time‑Wait Timer**|Avoid issues after closing connection (2MSL wait)|

📌 Timers help TCP maintain reliability and prevent confusion in network.

---

### 4️⃣ **Checksum (Error Detection in Transport Layer)**

Transport layer uses **16‑bit checksum** over:  
✔ Header + ✔ Payload + ✔ Pseudo Header  
(helps ensure IP routing didn’t modify data incorrectly)

📌 If checksum fails → segment discarded → retransmission requested

---

### 5️⃣ **UDP Characteristics & Use Cases**

📝 **User Datagram Protocol**

- Connectionless
    
- No congestion control
    
- Low delay, real‑time friendly
    
- Small header (8 bytes vs TCP 20+)
    

Used in:  
🎮 Online Gaming  
🎥 Live Streaming  
📞 VoIP Calls  
🌐 DNS Queries

Because speed is more important than reliability.

---

### 6️⃣ **TCP Segment Structure**

Important parts:

- **Sequence Number**
    
- **Acknowledgment Number**
    
- **Flags** (SYN, ACK, FIN, RST, PSH, URG)
    
- **Window Size** → used for flow control
    

📌 FLAG importance is **high‑frequency exam question**

---

### 7️⃣ **Transmission vs Communication**

|Concept|Meaning|Layer|
|---|---|---|
|Transmission|Sending bits physically|Physical/Data Link|
|Communication|Reliable delivery between apps|Transport Layer|

Transport Layer ensures **end‑to‑end** correctness.

---

## 🌟 Quick Summary Table

|Topic|Benefit|
|---|---|
|Ports + (De)Multiplexing|App‑to‑App delivery|
|SCTP|Modern reliable multi‑stream transfer|
|TCP Timers|Handles lost or delayed packets|
|Checksum|Ensures correctness of data|
|UDP|Fast & real‑time friendly|
|TCP Segment Fields|Reliability mechanisms|