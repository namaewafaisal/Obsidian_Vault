# **Computer Networks**

## CIA 1

---
## **Unit 1 & 2 – 2 Marks (Elaborated)**

1. **Define Computer Networks.**
   → A computer network is an interconnected collection of autonomous computers and devices (like printers, servers, mobiles) that communicate and share resources (files, applications, internet) through communication links such as cables, fiber optics, or wireless.

2. **What is Socket address?**
   → A socket address uniquely identifies a process in a network. It is formed by combining the **IP address** (to locate the host) and the **Port number** (to locate the process/application). Example: `192.168.1.2:80`.

3. **Name the three phases used in POP3.**
   → The Post Office Protocol version 3 (POP3) works in three phases:

   * **Authorization phase:** User login with username and password.
   * **Transaction phase:** User retrieves, lists, or deletes emails.
   * **Update phase:** Server deletes marked messages and ends the session.

4. **Why is an application such as POP needed for electronic messaging?**

   → SMTP is only for sending emails, not retrieving them. POP is needed to **download emails from the mail server** to a client computer for offline access. Without POP/IMAP, users couldn’t retrieve their emails from the server.

5. **Differentiate IMAP and POP3.**

   * **POP3:** Downloads messages from the server and usually deletes them. Mainly for single-device use.
   * **IMAP:** Keeps messages on the server and syncs across multiple devices. Supports folder organization and server-side management.

6. **List the five components of data communication system.**

   * **Message** – The data to be communicated.
   * **Sender** – Device that sends the data.
   * **Receiver** – Device that receives the data.
   * **Transmission Medium** – Path through which data travels (cable, wireless).
   * **Protocol** – Rules for communication.

7. **Write the different sections in domain name space.**

   * **Root domain (.)** – The top of the hierarchy.
   * **Top-Level Domains (TLDs)** – e.g., `.com`, `.org`, `.edu`.
   * **Second-Level Domains** – e.g., `example.com`.
   * **Subdomains** – e.g., `mail.example.com`.
   * **Host name** – Final part identifying the specific machine.

8. **Differentiate between the connection-oriented and connectionless services.**

   * **Connection-oriented (TCP):** Requires a connection setup before transfer; reliable, ordered, error-checked.
   * **Connectionless (UDP):** No setup; packets (datagrams) are sent independently; faster but may be lost or arrive out of order.

9. **Give the datagram format of UDP.**

   UDP has a **fixed 8-byte header**:

   * Source Port (16 bits)
   * Destination Port (16 bits)
   * Length (16 bits)
   * Checksum (16 bits)
     Followed by the **data payload**.

10. **What is the difference between network services and transport services?**

      * **Network services (IP layer):** Provides host-to-host delivery across the network (device-level).
      * **Transport services (TCP/UDP layer):** Provides process-to-process delivery, error checking, sequencing, and flow control (application-level).

11. **List the different phases used in TCP Connection.**

      * **Connection Establishment:** Three-way handshake between sender and receiver.
      * **Data Transfer:** Reliable transmission of data segments.
      * **Connection Termination:** Graceful closing of the connection.

12. **What are the services provided by the transport layer protocol?**

   * Process-to-process delivery
   * Error detection and recovery
   * Flow control (preventing overflow at receiver)
   * Congestion control (preventing overload in network)
   * Reliable data transfer (TCP)

13. **Discover the services provided by Transport Layer Protocol.**

       (Same as Q12, just another wording) → Delivery, reliability, error control, flow control, congestion control.

14. **Difference between fully qualified and partially qualified domain name.**

      * **FQDN:** Complete name including host, domain, and root (e.g., `www.example.com.`). Always ends with a dot.
      * **PQDN:** Incomplete name, usually missing part of the hierarchy (e.g., `www.example`).

15. **What are the major duties of Network Layer?**

      * Logical addressing (IP addresses)
      * Routing (path selection)
      * Packet forwarding (hop to hop delivery)
      * Fragmentation & reassembly of packets
      * Congestion control in the network

16. **Compare flow control versus congestion control.**

      * **Flow control:** Sender → Receiver balance (avoid receiver buffer overflow).
      * **Congestion control:** Sender → Network balance (avoid network overload by too much data traffic).

17. **What leads to congestion?**
       → Congestion occurs when **network demand exceeds its capacity** due to:

      * Excessive traffic load
      * Limited bandwidth
      * Insufficient router buffer memory
      * Bursty data transmissions

18. **What is a Protocol?**

       → A protocol is a set of **rules, standards, and conventions** that define how data is      transmitted and received between devices in a network. Example: TCP, HTTP.

19. **How does MIME enhance SMTP?**
          → SMTP supports only text messages. MIME (Multipurpose Internet Mail Extensions) enhances SMTP by allowing **audio, video, images, and attachments** to be encoded into text format and transmitted via SMTP.

20. **How do fast retransmit mechanism of TCP works?**
      → TCP nor mally retransmits lost packets after a timeout. In fast retransmit, if **three duplicate    ACKs** for the same packet are received, TCP immediately retransmits the lost segment without waiting for timeout → improves efficiency.

---

### **Q1. Write note on socket with neat sketches.**

*(Definition – 2 Marks, Diagram – 3 Marks, Explanation – 5 Marks)*

**Answer:**

**Definition:**
A **socket** is an endpoint for communication between two machines. It acts as an interface between the application layer and the transport layer (TCP/UDP). Each socket is identified by a **socket address** = (IP address + Port number).

---

**Diagram:** (Exam sketch idea)

```
Application Layer (Process)
        |
        v
   ----------------
   |   Socket     |  <-- (IP Address +           |              |       Port Number)
   ----------------
        |
        v
 Transport Layer (TCP/UDP)
        |
 Network Layer
```

---

**Explanation:**

1. **Purpose:**

   * A socket provides a mechanism for communication between processes running on the same or different hosts in a network.
   * It allows applications to send/receive data using TCP (connection-oriented) or UDP (connectionless).

2. **Types of Sockets:**

   * **Stream socket (TCP):** Provides reliable, connection-oriented communication.
   * **Datagram socket (UDP):** Provides fast, connectionless communication.

3. **Socket Addressing:**

   * Each socket is uniquely identified by:

     * **IP Address** – identifies the host.
     * **Port Number** – identifies the application on that host.

4. **Working:**

   * Client creates a socket and requests connection.
   * Server listens on a specific socket.
   * Once connected, data is transferred between sockets.

5. **Example:**

   * A web browser creates a socket to connect to a web server’s socket (IP: 192.168.1.10, Port: 80).
   * This allows the browser to fetch and display the webpage.

---

### **Q2. Discuss the working of E-mail in detail.**

*(Diagram – 4 Marks, Explanation – 6 Marks)*

---

**Answer:**

**Definition:**
E-mail (Electronic Mail) is a method of exchanging digital messages across computer networks. It uses a client–server architecture and relies on standard protocols such as **SMTP, POP3, and IMAP**.

---

**Diagram (Exam sketch idea):**

```
User A (Sender) → Mail User Agent (MUA)
        |
        v
SMTP (Client) → Mail Transfer Agent (MTA) → Internet → Receiver’s Mail Server
                                                          |
                                                          v
                                               POP3/IMAP → MUA → User B (Receiver)
```

---

**Explanation:**

1. **Mail User Agent (MUA):**

   * The email application used by sender and receiver (e.g., Outlook, Thunderbird, Gmail app).
   * Allows composing, sending, and reading messages.

2. **Sending (SMTP Protocol):**

   * The sender’s MUA forwards the email to the **Mail Transfer Agent (MTA)** using **SMTP (Simple Mail Transfer Protocol)**.
   * SMTP ensures the mail is delivered to the recipient’s mail server.

3. **Mail Transfer Agent (MTA):**

   * Works like a post office. It stores and forwards the email.
   * If the destination is not available, it queues the email and retries later.

4. **Receiving (POP3/IMAP):**

   * Once the message arrives at the receiver’s mail server, the receiver uses:

     * **POP3:** Downloads messages to local device (may delete from server).
     * **IMAP:** Keeps messages on the server, allowing access from multiple devices.

5. **Message Format:**

   * E-mail has two parts: **Header** (sender, recipient, subject, timestamp) and **Body** (the actual message, text or multimedia if MIME is used).

6. **Overall Working:**

   * Sender composes → MUA → SMTP → Internet → Receiver’s mail server → POP3/IMAP → Receiver’s MUA → Receiver reads.

---

### **Q3. With a neat sketch, list and explain the types of Networks.**

*(Types – 2 Marks; Diagram – 2 Marks; Explanation – 6 Marks)*

---

**Answer:**

**Definition:**
A computer network can be categorized based on the **geographical area it covers** and the **purpose of connectivity**.

---

**Types of Networks:**

1. **LAN (Local Area Network):**

   * Covers a small geographical area (e.g., office, building, campus).
   * High data transfer speed, privately owned.
   * Example: College computer lab network.

2. **MAN (Metropolitan Area Network):**

   * Covers a city or a group of nearby buildings.
   * Provides connectivity across a metropolitan area.
   * Example: Cable TV networks, city-wide Wi-Fi.

3. **WAN (Wide Area Network):**

   * Covers large geographical areas, often worldwide.
   * Uses public transmission systems like telephone lines, satellites.
   * Example: The Internet.

4. **PAN (Personal Area Network):**

   * Very small area, typically within a person’s workspace.
   * Connects personal devices like smartphones, laptops, Bluetooth devices.
   * Example: A mobile hotspot or Bluetooth connection.

---

**Diagram (Exam sketch idea):**

```
[ PAN ]  →  Covers a few meters (Bluetooth, Wi-Fi hotspot)
[ LAN ]  →  Covers a building or campus (Office Network)
[ MAN ]  →  Covers a city (Cable TV Network)
[ WAN ]  →  Covers countries/continents (Internet)
```

---

**Explanation:**

* **PAN** is the smallest and provides personal connectivity.
* **LAN** is commonly used in offices and schools, offering high-speed and secure connections.
* **MAN** connects multiple LANs in a city using high-speed backbone lines.
* **WAN** interconnects MANs and LANs globally, usually operated by telecom providers.

---

### **Q4. Discuss the Domain Name System (DNS) with neat Diagram.**

*(Definition – 2 Marks; Diagram – 3 Marks; Explanation – 5 Marks)*

---

**Definition:**
The **Domain Name System (DNS)** is a hierarchical and distributed naming system that translates **human-readable domain names** (like `www.example.com`) into **IP addresses** (like `192.168.1.1`), which are required for locating and identifying computers on the Internet.

---

**Diagram (Exam sketch idea):**

```
User → DNS Resolver → Root Server → TLD Server → Authoritative Server → IP Address
```

Example:

```
www.example.com
   |
   v
Local DNS Resolver
   |
   v
Root DNS Server (.)
   |
   v
TLD DNS Server (.com)
   |
   v
Authoritative DNS Server (example.com)
   |
   v
Response: 192.168.1.10
```

---

**Explanation:**

1. **Hierarchy of DNS:**

   * **Root Level:** Represented by “.” (dot). The top-most server.
   * **Top-Level Domains (TLDs):** `.com`, `.org`, `.edu`, `.in`.
   * **Second-Level Domains:** `example.com`.
   * **Subdomains:** `mail.example.com`.
   * **Host name:** Specific machine like `www`.

2. **Working of DNS Query:**

   * The user enters a domain name in the browser.
   * The **DNS Resolver** (provided by ISP) starts the search.
   * Resolver contacts the **Root Server** → directs to the **TLD Server**.
   * TLD Server directs to the **Authoritative Name Server** for that domain.
   * The authoritative server responds with the **IP address**.
   * The browser uses this IP to contact the destination server.

3. **Advantages of DNS:**

   * Provides **user-friendly names** instead of numbers.
   * **Scalable and distributed**, reducing single point of failure.
   * Supports load balancing through multiple IPs for one name.

---

### **Q5. Explain the complexities in HTTP with suitable examples.**

*(Explanation – 6 Marks; Examples – 4 Marks)*

---

**Definition:**
**HTTP (Hypertext Transfer Protocol)** is the application-layer protocol used for communication on the World Wide Web. Although simple, it has several **complexities and limitations** that affect its performance.

---

**Explanation of Complexities:**

1. **Stateless Protocol:**

   * HTTP does not retain information about previous requests.
   * Each request is independent, requiring additional mechanisms (cookies, sessions) for state management.

2. **Connection Overhead:**

   * In HTTP/1.0, a new TCP connection is created for each request–response, leading to delays.
   * Persistent connections (HTTP/1.1) reduce this but still have latency.

3. **Head-of-Line Blocking:**

   * In HTTP/1.1, multiple requests share the same TCP connection.
   * If one request is delayed, others are blocked.

4. **Security Issues:**

   * Plain HTTP transmits data in text, vulnerable to eavesdropping and attacks.
   * HTTPS (HTTP over SSL/TLS) was introduced to fix this, but it adds encryption overhead.

5. **Caching Problems:**

   * Deciding what data to cache and when to refresh it is complex.
   * Incorrect caching may lead to outdated information.

6. **Content Negotiation:**

   * Servers may need to deliver different formats (text, image, video, language versions).
   * Handling this increases complexity.

---

**Examples:**

1. **Statelessness Example:**

   * When you log in to a website, HTTP alone cannot remember you are logged in.
   * Solution: **Session IDs or cookies** are used to maintain continuity.

2. **Connection Overhead Example:**

   * Loading a webpage with 50 images using HTTP/1.0 → requires 50 separate TCP connections.
   * This increases delay significantly.

3. **Security Example:**

   * Submitting passwords via plain HTTP can be intercepted by attackers.
   * HTTPS encrypts the communication to prevent this.

4. **Head-of-Line Blocking Example:**

   * In HTTP/1.1, if a large image file delays in transmission, other small requests (like CSS, JS) are also blocked.

---

### **Q6. Explain three-way Handshake Protocol to establish the transport level connection.**

*(Diagram – 3 Marks; Explanation – 7 Marks)*

---

**Definition:**
The **Three-Way Handshake** is a method used by TCP (Transmission Control Protocol) to establish a **reliable, connection-oriented communication** between a client and a server before data transfer begins.

---

**Diagram (Exam sketch idea):**

```
Client                               Server
------                               ------
SYN   -----------------------------> (Listens)
       <-----------------------------  SYN + ACK
ACK   ----------------------------->
```

---

**Explanation (Steps):**

1. **Step 1 – SYN (Synchronize):**

   * The client sends a TCP segment with the **SYN (synchronize)** flag set.
   * It includes an **initial sequence number (ISN)** chosen by the client.
   * This request means, “I want to connect and start with this sequence number.”

2. **Step 2 – SYN + ACK:**

   * The server responds with a segment having both **SYN and ACK flags** set.
   * The **ACK** acknowledges the client’s ISN.
   * The **SYN** contains the server’s ISN, telling the client the sequence number it will start with.

3. **Step 3 – ACK:**

   * The client sends back an **ACK** to confirm the server’s ISN.
   * At this point, both client and server have acknowledged each other’s sequence numbers.

4. **Connection Established:**

   * After these 3 steps, a **full-duplex, reliable connection** is established.
   * Now data transfer can begin.

---

**Key Points:**

* Ensures both client and server are ready for communication.
* Provides **synchronization** of sequence numbers.
* Prevents old duplicate connections from causing confusion.
* Without this handshake, TCP cannot guarantee reliable delivery.

---

### **Q7. Describe the token bucket mechanism for congestion control. With which other technique is token bucket usually combined to achieve complete flow control? What problems in the simpler approach are addressed by using a token bucket mechanism?**

*(Mechanism – 2 Marks, Approach – 2 Marks, Explanation – 6 Marks)*

---

**Mechanism (Token Bucket):**

* The **token bucket** is a congestion control and traffic shaping mechanism.
* A **bucket** holds tokens, each token representing permission to send a fixed number of bytes.
* Tokens are generated at a constant rate and stored in the bucket (up to a limit).
* To send a packet, the system must remove tokens from the bucket equal to the packet size.
* If tokens are not available, the packet must wait.

---

**Diagram (Exam sketch idea):**

```
 Token Generator → [ Token Bucket ] → Outgoing Packets → Network
```

---

**Approach (Combination):**

* The **Token Bucket** is usually combined with the **Leaky Bucket Algorithm**.
* **Leaky Bucket:** Controls output rate (smooth traffic).
* **Token Bucket:** Allows bursts of traffic while maintaining average rate.

---

**Explanation:**

1. **Why Token Bucket?**

   * Unlike the leaky bucket (which enforces strict traffic shaping), token bucket allows short-term bursts.
   * This is important for applications like video streaming or voice calls where bursts are natural.

2. **Advantages:**

   * Allows **flexibility**: If enough tokens are saved, a host can send a burst of packets.
   * Ensures **average rate** is controlled while still accommodating temporary traffic spikes.
   * Provides better utilization of network resources.

3. **Problems solved compared to simpler approach:**

   * **Leaky Bucket alone:** Enforces strict, constant output → not suitable for bursty traffic.
   * **Token Bucket:** Solves this by allowing bursts while keeping long-term traffic under control.

---

### **Q8. With a neat sketch, Explain the function of OSI Network Architecture.**

*(Diagram – 3 Marks; Explanation – 7 Marks)*

---

**Definition:**
The **OSI (Open Systems Interconnection) model** is a **reference model** that standardizes how different systems communicate in a network. It has **7 layers**, each with specific functions.

---

**Diagram (Exam sketch idea):**

```
+--------------------+   ← Layer 7: Application
+--------------------+   ← Layer 6: Presentation
+--------------------+   ← Layer 5: Session
+--------------------+   ← Layer 4: Transport
+--------------------+   ← Layer 3: Network
+--------------------+   ← Layer 2: Data Link
+--------------------+   ← Layer 1: Physical
```

---

**Explanation (Layer Functions):**

1. **Physical Layer:**

   * Deals with transmission of raw bits over a physical medium.
   * Concerned with cables, signals, voltage levels, connectors.

2. **Data Link Layer:**

   * Provides **error detection/correction** and **framing**.
   * Divided into LLC (Logical Link Control) and MAC (Media Access Control).
   * Ensures node-to-node delivery.

3. **Network Layer:**

   * Provides **logical addressing (IP addresses)** and **routing**.
   * Ensures host-to-host delivery across multiple networks.

4. **Transport Layer:**

   * Provides **end-to-end process-to-process delivery**.
   * Handles segmentation, reassembly, flow control, error control.
   * Example protocols: TCP, UDP.

5. **Session Layer:**

   * Establishes, manages, and terminates communication sessions.
   * Provides dialog control and synchronization.

6. **Presentation Layer:**

   * Ensures data is in a **readable format**.
   * Handles data translation, encryption, compression.

7. **Application Layer:**

   * Provides services directly to the user.
   * Examples: HTTP, SMTP, FTP, DNS.

---

**Key Points:**

* Each layer provides services to the layer above and uses services of the layer below.
* The OSI model is conceptual v(not implemented as-is) but acts as a guideline for protocol design.
* It separates **functions into layers** so networking can be standardized and modular.

---

### **Q9. Write a detailed note on congestion avoidance mechanisms used in TCP.**

*(Diagram – 3 Marks; Explanation – 7 Marks)*

---

**Definition:**
**Congestion** occurs when too many packets are present in the network, leading to delays and packet loss. TCP uses various **congestion control mechanisms** to avoid and manage congestion.

---

**Diagram (Exam sketch idea):**

```
TCP Congestion Control Mechanisms:
 ├── Slow Start
 ├── Congestion Avoidance
 ├── Fast Retransmit
 └── Fast Recovery
```

Graph representation (for slow start & avoidance):

```
Congestion Window (cwnd) size ↑
|
|     /\/\/ Slow Start (exponential growth)
|    /
|   /
|  /---- Linear growth (Congestion Avoidance)
+--------------------------------------------> Time
```

---

**Explanation:**

1. **Slow Start:**

   * At the beginning, TCP starts with a small congestion window (cwnd = 1 MSS).
   * cwnd doubles every RTT (exponential growth).
   * This continues until it reaches a threshold (ssthresh).

2. **Congestion Avoidance:**

   * After cwnd reaches ssthresh, growth becomes **linear** instead of exponential.
   * cwnd increases by 1 MSS per RTT → prevents congestion.

3. **Fast Retransmit:**

   * If sender receives **3 duplicate ACKs**, it assumes a packet is lost.
   * It retransmits the lost packet immediately, without waiting for timeout.

4. **Fast Recovery:**

   * Instead of dropping cwnd to 1, TCP halves the cwnd after detecting packet loss.
   * Then it continues with linear increase (congestion avoidance).

5. **Additive Increase / Multiplicative Decrease (AIMD):**

   * Increase cwnd gradually (additive increase).
   * On detecting congestion, decrease cwnd sharply (multiplicative decrease).
   * Balances efficiency and fairness.

---

**Summary:**

* **Slow Start:** Probe network capacity quickly.
* **Congestion Avoidance:** Prevents overload by linear growth.
* **Fast Retransmit & Fast Recovery:** Improves performance by handling packet loss efficiently.
* Together, these mechanisms allow TCP to **adapt dynamically** to network conditions and minimize congestion.

---

### **Q10. i) Discuss File Transfer Protocol (FTP) with neat diagram.**

_(Diagram – 2 Marks; Explanation – 3 Marks)_

---

**Definition:**  
**FTP (File Transfer Protocol)** is an application layer protocol used to transfer files between a client and a server over a TCP connection.

---

**Diagram (Exam sketch idea):**

```
Client                         Server
------                         ------
Command Connection (TCP Port 21)
Data Connection   (TCP Port 20)
```

---

**Explanation:**

- FTP uses **two TCP connections**:
    
    1. **Control/Command connection (Port 21):** For sending commands (e.g., login, file actions).
        
    2. **Data connection (Port 20):** For actual file transfer.
        
- Supports **two modes**:
    
    - Active Mode (server initiates data connection).
        
    - Passive Mode (client initiates data connection).
        
- Provides features like authentication (username/password), directory navigation, uploading & downloading files.
    

---

### **Q10. ii) Explain Simple Network Management Protocol (SNMP).**

_(Diagram – 2 Marks; Explanation – 3 Marks)_

---

**Definition:**  
**SNMP** is an application layer protocol used to manage and monitor devices on a network (routers, switches, servers, printers).

---

**Diagram (Exam sketch idea):**

```
SNMP Manager  <---->  SNMP Agent (on network device)
           |                   |
           v                   v
     Management Info        Managed Device (MIB database)
```

---

**Explanation:**

- SNMP has two main components:
    
    1. **SNMP Manager:** Central system that monitors and controls devices.
    2. **SNMP Agent:** Software on network devices that reports information.
    
- Devices maintain a **Management Information Base (MIB)** – a collection of variables (e.g., CPU usage, interface status).
- SNMP operations: **Get, Set, Trap** → allow fetching values, updating configs, or sending alerts.
---

### **Q11. Explain in detail UDP with neat diagram.**

*(Diagram – 3 Marks; Explanation – 7 Marks)*

---

**Definition:**
**UDP (User Datagram Protocol)** is a **connectionless, unreliable transport layer protocol**. It provides **fast communication** with minimal overhead but without error correction or guaranteed delivery.

---

**Diagram (UDP Datagram Format):**

```
  0      15 16     31
 +---------+---------+
 | Source Port      |
 +---------+---------+
 | Destination Port |
 +---------+---------+
 | Length           |
 +---------+---------+
 | Checksum         |
 +---------+---------+
 |      Data        |
 |   (Payload)      |
 +------------------+
```

---

**Explanation:**

1. **Characteristics:**

   * **Connectionless:** No handshake; sender just sends datagrams.
   * **Unreliable:** No guarantee of delivery, order, or duplication control.
   * **Lightweight:** Only 8-byte header compared to TCP’s 20 bytes.
   * **Faster than TCP**, suitable for time-sensitive applications.

2. **Header Fields:**

   * **Source Port (16 bits):** Identifies the sending process.
   * **Destination Port (16 bits):** Identifies the receiving process.
   * **Length (16 bits):** Total length of UDP header + data.
   * **Checksum (16 bits):** Error detection for header and data.

3. **Services Provided:**

   * Multiplexing and demultiplexing between applications.
   * Minimal error detection using checksum.
   * Direct process-to-process delivery using port numbers.

4. **Advantages:**

   * Low overhead, simple implementation.
   * Suitable for applications where speed is more important than reliability.

5. **Applications:**

   * Video streaming, VoIP, DNS queries, online gaming, DHCP.

---

**Summary:**
UDP is best for **real-time, loss-tolerant applications**. It sacrifices reliability for **speed and efficiency**.

---



### **Q12. Explain different types of switching networks and mention its advantages and disadvantages.**

*(Types – 2 Marks; Explanation – 5 Marks; Advantages & Disadvantages – 3 Marks)*

---

**Definition:**
Switching in networking is the method of forwarding data between devices through intermediate nodes. There are mainly **three types of switching techniques**.

---

**Types of Switching Networks:**

1. **Circuit Switching:**

   * A dedicated communication path is established between sender and receiver before data transfer.
   * Example: Traditional telephone networks.

2. **Packet Switching:**

   * Data is divided into packets; each packet is routed independently through the network.
   * Two types:

     * **Datagram Packet Switching:** Each packet may take a different path.
     * **Virtual Circuit Packet Switching:** A logical path is established before transmission.
   * Example: Internet (IP-based).

3. **Message Switching:**

   * Entire message is treated as a single unit and stored at intermediate nodes before forwarding (store-and-forward).
   * Example: Early telegraph networks.

---

**Explanation (Working):**

* **Circuit switching** ensures guaranteed bandwidth but wastes resources if no data is sent.
* **Packet switching** is efficient since resources are shared, but packets may be delayed or arrive out of order.
* **Message switching** can handle large messages, but causes long delays due to store-and-forward.

---

**Advantages and Disadvantages:**

1. **Circuit Switching:**

   * ✅ Advantages: Guaranteed data rate, fixed path, predictable delay.
   * ❌ Disadvantages: Inefficient if channel is idle, setup delay, not suitable for bursty data.

2. **Packet Switching:**

   * ✅ Advantages: Efficient use of bandwidth, supports many users, fault-tolerant.
   * ❌ Disadvantages: Variable delay, packet loss possible, requires reassembly.

3. **Message Switching:**

   * ✅ Advantages: No need for dedicated path, can handle large messages.
   * ❌ Disadvantages: High delay due to storage, requires large buffers, not real-time.

---

**Summary:**

* **Circuit Switching** → Good for voice calls.
* **Packet Switching** → Best for data networks (Internet).
* **Message Switching** → Rarely used today due to delays.

---