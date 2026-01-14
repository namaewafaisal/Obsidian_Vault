# Networking Essentials for Software Engineering Students

It's great that you're diving into computer networking as a software engineering student! It's indeed a foundational skill for any developer, as it underpins how all applications communicate.

To help you navigate the vast amount of information, here’s an overview of the **absolute essentials** for your learning and interview preparation, along with topics you can de-prioritize initially.

---

## Absolute Essentials

Focus on **concepts, functionality, and relationships** rather than minute hardware details or complex algorithms initially.

### 1. Internet Fundamentals

- **What the Internet Is:** A global collection of interconnected computer networks.
    
- **Client-Server Model:** Explains how devices request (client) and provide (server) services. Your computer can act as both a client and a server.
    
- **URLs and DNS (Domain Name System):**
    
    - Translates human-readable domain names (like `google.com`) into machine-readable IP addresses.
        
    - Understand the basic DNS resolution process (local cache → local DNS server → root servers → TLD servers).
        
- **IP Addresses (IPv4 & IPv6 Overview):**
    
    - Unique identifiers for devices on a network.
        
    - IPv4: 32-bit dotted-decimal; IPv6: 128-bit hexadecimal to address exhaustion.
        
    - Differentiate **Global IP addresses** (router/modem) and **Local IP addresses** (internal devices).
        
- **Port Numbers:** Identify specific applications/services on a device.
    
    - Common ports: 80 (HTTP), 443 (HTTPS), 25 (SMTP).
        
- **Packets and Segments:**
    
    - Data is broken down into smaller units: packets (network layer), segments (transport layer), frames (data link layer).
        

---

### 2. Network Models (Crucial for Interviews)

- **OSI Model (Open Systems Interconnection):** Conceptual framework with **7 layers**.
    
    - **Layers in order:** Application, Presentation, Session, Transport, Network, Data Link, Physical.
        
    - **Core responsibilities:**
        
        - Transport: end-to-end delivery, flow control
            
        - Network: source-to-destination packet delivery and routing
            
    - **Encapsulation:** Data is wrapped with headers at each layer on the sender side and decapsulated on the receiver side.
        
- **TCP/IP Model:** Practical model used on the Internet.
    
    - **Layers:** Application, Transport, Internet (Network), Network Access (Host-to-Network).
        
    - **Mapping to OSI:** The top three OSI layers (Application, Presentation, Session) map to TCP/IP Application layer.
        

---

### 3. Key Protocols

- **HTTP/HTTPS:** Web communication, request-response cycle, GET/POST methods, stateless nature, cookies for session management.
    
- **TCP (Transmission Control Protocol):** Connection-oriented, reliable; includes three-way handshake, sequence numbers, acknowledgements, flow control, congestion control.
    
- **UDP (User Datagram Protocol):** Connectionless, unreliable but faster; used for streaming, gaming, DNS lookups.
    
- **Email Protocols:** SMTP (send), POP3/IMAP (receive). Typically use TCP.
    
- **DHCP:** Assigns IP addresses and network configurations automatically.
    

---

### 4. Networking Devices and Concepts

- **Routers:** Forward packets between networks (Network Layer); understand routing tables.
    
- **Switches:** Connect devices within a local network using MAC addresses (Data Link Layer).
    
- **Modems:** Convert digital ↔ analog signals.
    
- **Firewalls:** Filter network traffic based on security rules.
    
- **NAT (Network Address Translation):** Maps multiple private IPs to one public IP.
    
- **MAC Addresses:** Unique hardware identifiers for network interfaces.
    
- **ARP (Address Resolution Protocol):** Finds MAC address for a given IP.
    

---

### 5. Network Security Basics

- **Core Concepts:** Authentication (verify identity) and Authorization (grant access).
    
- **VPNs:** Create encrypted tunnels over public networks.
    
- **IPsec:** Protocol suite providing cryptographic security for IP networks.
    

---

### 6. Data Transmission Fundamentals

- **Types of Networks:** LAN, MAN, WAN (geographical scope).
    
- **Data Flow Modes:** Simplex, Half-Duplex, Full-Duplex.
    
- **Transmission Media:**
    
    - Guided: Ethernet, fiber optics
        
    - Unguided: Wi-Fi, Bluetooth
        
- **Error Detection:** Checksums, parity bits.
    

---

## Topics to De-Prioritize Initially

- **Deep Historical Context:** Detailed history of internet evolution beyond ARPANET.
    
- **In-depth Physical Layer Details:** Signal characteristics, modulation types, encoding schemes.
    
- **Intricate Network Topologies:** Detailed architectures or failure modes.
    
- **Detailed Routing Algorithm Mechanics:** Knowing the algorithms exist is enough.
    
- **Advanced Error Correction Algorithms:** E.g., Hamming codes.
    
- **Specific Flow and Error Control Protocols at Data Link Layer:** E.g., HDLC, Sliding Window, Go-Back-N ARQ.
    
- **Very Specific IPv6 Features:** Headers, extension headers, autoconfiguration states, transition technologies.
    
- **Command-Line Tool Flags:** Tools like `ping`, `ipconfig`, `nslookup`, `tracert`, `arp` are enough to know; flags can be skipped.
    

---

By focusing on these core concepts and practical implications, you’ll build a strong foundation in computer networking relevant for software engineering roles and interviews.

Here is a comprehensive overview of the OSI (Open Systems Interconnection) model content you would need for making Anki flashcards, drawing on the provided sources:

### OSI Model: The Essentials for Anki Flashcards

The OSI model is a **conceptual framework** that is widely used to understand networking and how data packets are created and processed by a computer [1-3]. It was developed by the **International Organization for Standardization (ISO)** to allow systems with different platforms (hardware, software, operating systems) to communicate with each other [4]. It provides a **standardized way** for two or more computers to communicate and is a **hierarchical model** that groups its processes into layers [4-6].

#### Key Characteristics and Structure:
*   **Purpose**: The OSI model defines **protocols for network communications** [6]. Its main objective is to enable the seamless exchange of data between any two points in the world over a computer network [7, 8]. It helps overcome issues arising from different operating systems or network problems during data transmission [1].
*   **Layers**: It consists of **seven distinct layers**, each with specific duties and responsibilities, working in cooperation with the layers above and below it [2, 3, 6, 9-12].
*   **Layer Order (Top-to-Bottom)**:
    1.  **Application Layer** [3, 6, 9, 11]
    2.  **Presentation Layer** [3, 6, 9, 11]
    3.  **Session Layer** [3, 6, 9, 11]
    4.  **Transport Layer** [3, 6, 9, 11]
    5.  **Network Layer** [3, 6, 9, 11]
    6.  **Data Link Layer** [3, 6, 9, 11]
    7.  **Physical Layer** [3, 6, 9, 11]
*   **Host vs. Network Layers**: The **upper four layers** (Application, Presentation, Session, Transport) are considered **host layers**, interacting with application-related issues. The **lower three layers** (Network, Data Link, Physical) are **network layers**, dealing with transmission-related steps [11].
*   **Communication Flow**: Data starts at the Application layer on the sending device, passes down through each layer to the Physical layer, is transmitted, then passes up from the Physical layer to the Application layer on the receiving device [13-16]. Intermediate nodes typically involve only the first three layers (Physical, Data Link, Network) [16].
*   **Peer-to-Peer Communication**: Layers at corresponding levels in different devices communicate with each other conceptually (e.g., Layer 2 of sender communicates with Layer 2 of receiver) [13, 17, 18].
*   **Interfaces**: Between two adjacent layers, there is an **interface** that defines the services a layer must provide [18].
*   **Encapsulation and Decapsulation**: As data moves down the layers on the sender's side, each layer **adds its own header (and sometimes a trailer)**, wrapping the data from the layer above it. This process is called **encapsulation** [3, 12, 14, 19-23]. On the receiver's side, the corresponding layer **removes its header (and trailer)**, revealing the data for the layer above, a process called **decapsulation** [3, 12, 14].

#### Detailed Layer Responsibilities (for Anki Cards):

##### 1. Physical Layer (Layer 1)
*   **Main Responsibility**: **Transmission of bits from one hop to the next** over a physical medium [3, 24-28]. It defines the mechanical, electrical, functional, and procedural characteristics for activating, maintaining, and deactivating physical links [26].
*   **Data Unit (PDU)**: **Bits** (raw binary data in terms of ones and zeros) [24, 29-31].
*   **Key Functions**:
    *   **Physical Medium Interface**: Provides a standardized interface to physical transmission media (cables, connectors, etc.) [26, 31, 32].
    *   **Bit Representation**: Converts data (1s & 0s) into **signals** (electrical, light, or radio) and vice-versa. It is not concerned with the meaning of bits [13, 25, 29, 31, 33].
    *   **Data Rate**: Defines and maintains the **data transmission rate** (bits per second) [31].
    *   **Synchronization**: Ensures sender and receiver maintain the same bit rate and synchronized clocks [34].
    *   **Line Configuration**: Defines the nature of connection (point-to-point or multipoint) [34].
    *   **Physical Topology**: Defines the type of topology (e.g., mesh, star, bus) [34, 35].
    *   **Transmission Mode**: Defines the direction of data transfer (simplex, half-duplex, full-duplex) [24, 27, 36, 37].
*   **Devices**: **Repeaters** and **Hubs** operate at this layer [38-40].
*   **Prioritization**: While deep physical layer details can be de-prioritized initially, understanding the concept that data is converted into signals is essential [41].

##### 2. Data Link Layer (Layer 2)
*   **Main Responsibility**: **Hop-to-hop delivery of frames** [3, 24, 25, 28, 42]. It adds reliability to the physical layer by providing **error detection and correction mechanisms** [27].
*   **Data Unit (PDU)**: **Frames** [3, 24, 27, 28, 30, 43-48].
*   **Key Functions**:
    *   **Framing**: Divides the stream of bits from the Network layer into fixed-size manageable units called frames [27, 43, 44, 49].
    *   **Physical Addressing (MAC Addresses)**: Appends the **MAC (Media Access Control) address** of the sender and the next intended receiver to the frame header [25, 43, 44, 50-53]. MAC addresses are unique hardware identifiers for network interfaces [51, 54-61].
    *   **Flow Control**: Ensures the sender transmits data at a rate the receiver can handle to prevent overwhelming and data loss [44, 49, 62].
    *   **Error Control**: Detects and retransmits lost, damaged, or duplicate frames. Error control information is often in the trailer of a frame [42-44, 63].
    *   **Access Control (Media Access Control)**: Determines which device has the right to send data in a multipoint connection scenario [29, 42, 63]. This is managed by the **MAC sub-layer** [29].
*   **Sub-layers**: Divided into **Logical Link Control (LLC)** and **Media Access Control (MAC)** [29, 63].
*   **Protocols**: **HDLC (High-Level Data Link Control)** is a bit-oriented protocol at this layer [64]. **ARP (Address Resolution Protocol)** is used to discover MAC addresses from IP addresses [51, 59, 65-68].
*   **Devices**: **Switches** and **Bridges** operate at this layer, using MAC addresses to filter and forward data [25, 39, 40, 51, 69, 70].
*   **Prioritization**: Detailed workings of protocols like HDLC, Stop-and-Wait, Sliding Window, Go-Back-N ARQ, or Selective Repeat ARQ can be de-prioritized initially [41].

##### 3. Network Layer (Layer 3)
*   **Main Responsibility**: **Source-to-destination delivery of packets** across potentially multiple networks [3, 24, 25, 28, 71, 72]. It makes sure data is delivered despite multiple intermediate devices [42].
*   **Data Unit (PDU)**: **Packets** or **Datagrams** [3, 24, 28, 30, 44, 45, 71, 73-75].
*   **Key Functions**:
    *   **Logical Addressing (IP Addressing)**: Assigns **IP (Internet Protocol) addresses** to sender and receiver, which are universally unique and identify devices outside the sender's network [25, 44, 51, 73, 76-80]. IP addresses identify the computer, while port numbers identify the application [81].
    *   **Routing**: Determines the **best path** for data packets to travel from source to destination across various networks using **routing algorithms** and **routing tables** [25, 44, 51, 69, 76, 82-87]. Routers operate at this layer [25, 40, 44, 51, 55, 56, 69, 88-94].
    *   **Internetworking**: Handles connections between multiple devices across different networks [76].
*   **Protocols**: **IP (Internet Protocol)** (IPv4 and IPv6) is the primary protocol [44, 95, 96]. Other associated protocols include **ICMP (Internet Control Message Protocol)** (for error signaling), **ARP (Address Resolution Protocol)**, and **RARP (Reverse Address Resolution Protocol)** [67, 68].
*   **Devices**: **Routers** operate at this layer [25, 40, 44, 51, 55, 56, 69, 88-94].
*   **Prioritization**: Understanding routing algorithms like Dijkstra's or Bellman-Ford exist to find optimal paths is sufficient; detailed implementation or step-by-step calculations can be de-prioritized [41].

##### 4. Transport Layer (Layer 4)
*   **Main Responsibility**: **Process-to-process delivery of the entire message** [3, 24, 25, 28, 72, 97]. It ensures data is delivered from the correct process on the sender to the correct process on the receiver, intact and in order [21, 72, 98].
*   **Data Unit (PDU)**: **Segments** [3, 11, 44, 45, 99, 100].
*   **Key Functions**:
    *   **Segmentation and Reassembly**: Divides data from the Session layer into smaller units called segments and adds **sequence numbers** to them for correct reassembly at the receiver [44, 95, 98, 99, 101].
    *   **Port Addressing**: Attaches **port numbers** (source and destination) to segments to identify the specific applications or services running on a device [45, 65, 66, 79, 81, 95, 98, 101, 102].
    *   **Flow Control**: Controls the amount of data transferred to prevent the receiver from being overwhelmed. This is **end-to-end** flow control [44, 95, 97, 101, 103].
    *   **Error Control**: Detects and handles lost or corrupted data segments. This is **end-to-end** error control, often using **checksums** [95, 97, 101, 103].
    *   **Multiplexing and Demultiplexing**: Allows multiple applications to share the same network connection (multiplexing) and directs incoming data to the correct application (demultiplexing) [98, 102].
    *   **Congestion Control**: Manages network traffic to prevent overloading, slowing down the packet transmission rate if congestion is detected [65, 83, 95, 103].
*   **Protocols**:
    *   **TCP (Transmission Control Protocol)**: **Connection-oriented, reliable** protocol ensuring data delivery, in order, without loss or corruption. Uses a **three-way handshake** for connection establishment [25, 95, 98, 99, 101, 104-111].
    *   **UDP (User Datagram Protocol)**: **Connectionless, unreliable, faster** protocol suitable for applications where some data loss is acceptable (e.g., video streaming, gaming, DNS lookups) [25, 95, 98, 99, 101, 104, 105, 112-115].
*   **Prioritization**: Understanding the details of TCP (three-way handshake, sequence numbers, acknowledgements, flow/congestion control) and UDP (connectionless, faster) is crucial for interviews [95].

##### 5. Session Layer (Layer 5)
*   **Main Responsibility**: **Establishes, manages, and terminates sessions (dialogs)** between communicating applications [3, 14, 24, 97, 100, 116]. It is often called the network dialog controller [97].
*   **Data Unit (PDU)**: Data units from upper layers with **checkpoints (syn bits)** added for synchronization [117].
*   **Key Functions**:
    *   **Dialog Control**: Maintains a log of which system established a connection to exchange data [118, 119].
    *   **Synchronization**: Adds **synchronization points (checkpoints)** to the data stream to ensure proper interaction and provide recovery options in case of errors, so that retransmission can start from the last checkpoint [117-119].
    *   **Authentication and Authorization**: Can perform authentication checks (e.g., username/password) and authorization (permission to access files) [116, 120].
    *   **Data Recovery**: Provides recovery options for active sessions in case of network errors [116].
*   **Protocols**: Examples include **RTCP (Real-time Transport Control Protocol)**, **PPTP (Point-to-Point Tunneling Protocol)** (for VPNs), and **PAP (Password Authentication Protocol)** [116, 118].

##### 6. Presentation Layer (Layer 6)
*   **Main Responsibility**: Performs **translation, encryption, and compression** of data to ensure communicating devices with different platforms can understand each other [3, 14, 24, 100, 119, 121, 122].
*   **Data Unit (PDU)**: Processed data from the Application layer, with header information related to encryption and compression [119].
*   **Key Functions**:
    *   **Data Translation/Representation**: Converts data into a format that the application layer can understand, handling different encoding schemes (e.g., ASCII, EBCDIC, UTF-8) [14, 24, 121-124].
    *   **Data Encryption/Decryption**: Transforms the original message to secure its meaning during transmission. Decryption is performed at the receiving end [14, 24, 36, 120-123, 125]. **SSL (Secure Sockets Layer)** is mentioned here [120, 125].
    *   **Data Compression/Decompression**: Reduces the amount of data to be transmitted, ensuring faster data transfer. This can be lossy or lossless [24, 121-123].
    *   **Abstraction**: Assumes that lower layers will handle data transmission [123].
*   **Protocols**: Examples include **AFP (Apple Filling Protocol)**, **LPP (Lightweight Presentation Protocol)**, **NDR (Network Data Representation)**, and **SSL (Secure Socket Layer)** [125].

##### 7. Application Layer (Layer 7)
*   **Main Responsibility**: Provides **access to network resources** and enables the user to communicate data [3, 14, 24, 96, 100, 126]. It is the layer users directly interact with [3, 9, 96, 126].
*   **Data Unit (PDU)**: Data from user applications, processed according to application-specific protocols [5, 9, 11].
*   **Key Functions**:
    *   **Network Virtual Terminal**: Allows users to connect to a remote device to access functions and services [126].
    *   **File Transfer, Access, and Management (FTAM)**: Provides mechanisms to transfer and manage files on remote computers [96, 126].
    *   **Addressing (DNS Resolution)**: Handles requests from clients to servers and uses DNS to resolve domain names to IP addresses [126, 127].
    *   **Mail and Directory Services**: Responsible for email forwarding, storage, and handling access rights for global information [96, 127].
*   **Protocols**: This layer defines **high-level protocols** for specific applications [128].
    *   **HTTP/HTTPS (Hypertext Transfer Protocol/Secure)**: For web browsing, defines client-server communication, request-response cycles, and methods like GET/POST [24, 95, 105, 126, 129-132]. HTTP is stateless, but **cookies** can maintain state [95, 106, 133].
    *   **SMTP (Simple Mail Transfer Protocol)**: For **sending** emails [24, 90, 95, 127, 130-132]. Uses TCP for reliability [90].
    *   **POP3 (Post Office Protocol version 3)** and **IMAP (Internet Message Access Protocol)**: For **receiving** emails [55, 95, 134].
    *   **DNS (Domain Name System)**: Translates human-readable domain names (e.g., google.com) into machine-readable IP addresses [45, 95, 127-130, 135, 136]. Uses UDP due to its speed [114].
    *   **DHCP (Dynamic Host Configuration Protocol)**: Automatically assigns IP addresses and other network configurations to devices [9, 95, 110, 111, 127, 130, 137, 138].
    *   **FTP (File Transfer Protocol)**: For file transfer [128, 134, 139].
    *   **TELNET (Telecommunication Network)**: For virtual terminals and remote host connection [127, 128, 134, 139].
    *   **SSH (Secure Shell)**: For secure remote terminal access [140].

#### Comparison with TCP/IP Model:
*   The **OSI model is more theoretical and concept-based**, while the **TCP/IP model is more practical** and actually used on the internet [2, 3, 17, 141, 142].
*   **OSI has seven layers**, whereas the **TCP/IP model typically has four or five layers** [3, 17, 141, 143, 144].
*   **Layer Mapping (TCP/IP to OSI)** [3, 17, 139, 144, 145]:
    *   **TCP/IP Application Layer** combines OSI's Application, Presentation, and Session layers.
    *   **TCP/IP Transport Layer** corresponds to OSI's Transport layer.
    *   **TCP/IP Internet Layer** (or Network Layer) corresponds to OSI's Network layer.
    *   **TCP/IP Network Access Layer** (or Host-to-Network Layer) combines OSI's Data Link and Physical layers.

This comprehensive breakdown covers the core components and functionalities of the OSI model, ideal for creating detailed Anki flashcards.


This document provides detailed notes, suitable for exam preparation, covering the topics listed in your "Network Study Questions".

---

## **Unit I: Introduction and Application Layer**

### **Part A: Basic Concepts**

#### **What is meant by data communication?**

Data Communication is the process of exchanging data or information. In computer networks, this exchange occurs between two devices over a transmission medium. This process involves a communication system composed of hardware (sender, receiver, intermediate devices) and software (protocols).

#### **Write the five components of a data communication system.**

A communication system involves certain rules (protocols) that specify what, how, and when data is communicated. The core components required for data communication are typically understood as:

1. **Sender**
2. **Receiver**
3. **Transmission Medium** (e.g., cable)
4. **Message/Data**
5. **Protocol** (Rules)

#### **What is a computer network?**

A computer network is defined as a collection of nodes (devices capable of transmitting or receiving data) connected by communication links. A computer network should ensure reliability, security, and performance (higher throughput and smaller delay times) of the data communication process.

#### **What are the properties and uses of HTTP?**

- **Properties:** HTTP (Hypertext Transfer Protocol) is an **Application Layer** protocol. It is generally a **stateless protocol**, meaning the server will not store any information about the client by default.
- **Uses:** It is used by web browsers for accessing information on the Internet. It defines the format of data transfer between web clients and web servers, defining how a client sends a request (HTTP request) and how the server sends back a response (HTTP response).

#### **What is SNMP?**

SNMP (Simple Network Management Protocol) is an **Application Layer** protocol. SNMPv3 addresses security concerns and supports authentication and encryption mechanisms.

### **Part B: Detailed Explanations**

#### **Draw the OSI network architecture and explain the functionalities of each layer in detail.**

The OSI (Open Systems Interconnection) Model is a **conceptual framework** developed by the **ISO** (International Organization for Standardization) to standardize how systems with different platforms communicate. It consists of seven layers.

|Layer|PDU|Responsibility|Source References|
|:--|:--|:--|:--|
|**7. Application**|Data|Provides user access to network resources. High-level protocols (HTTP, DNS).||
|**6. Presentation**|Data|Provides translation, encryption, and compression. Converts data into machine-readable binary format.||
|**5. Session**|Data|Establishes, manages, and terminates sessions (dialogs). Manages synchronization points (checkpoints) in data transfer.||
|**4. Transport**|**Segment**|**Process-to-Process Delivery** of the message. Handles segmentation, flow control, and error control (end-to-end).||
|**3. Network**|**Packet**|**Source-to-Destination Delivery** of packets across multiple networks. Handles logical addressing (IP) and routing.||
|**2. Data Link**|**Frame**|**Hop-to-Hop Delivery** of frames. Provides framing, physical addressing (MAC), flow control, and error control.||
|**1. Physical**|**Bit**|Transmits raw bits over the medium. Deals with conversion of data into signals (electrical, light, radio).||

#### **Write the difference between LAN, WAN and MAN.**

Networks are categorized based on their size.

|Category|Coverage Area|Characteristics|Source References|
|:--|:--|:--|:--|
|**LAN** (Local Area Network)|Limited range, small geographical location (e.g., office floor, building).|Usually **privately owned**. Data transmission rate is relatively higher.||
|**MAN** (Metropolitan Area Network)|Between LAN and WAN size (e.g., entire city or metropolitan area).|Can incorporate multiple LANs. Typically owned by private providers.||
|**WAN** (Wide Area Network)|Large geographic area (states, countries, Global Network/Internet).|A network of networks. Cost varies widely. Often uses wireless medium (e.g., satellites).||

#### **Discuss the layers of TCP/IP protocol suite.**

The TCP/IP model is the **practical** network model used today and is a collection of protocols. It is a hierarchical model where higher-layer protocols are supported by lower-layer protocols.

|TCP/IP Layer|Corresponding OSI Layers|Core Functionality|Source References|
|:--|:--|:--|:--|
|**4. Application**|Application, Presentation, Session|Defines high-level protocols (FTP, SMTP, DNS, HTTP). User interaction layer.||
|**3. Transport**|Transport|Responsible for **Process-to-Process Delivery**. Uses TCP (reliable) and UDP (unreliable).||
|**2. Internet (or Network)**|Network|Responsible for **Source-to-Destination Transmission**. Uses the IP protocol (connection-less & unreliable).||
|**1. Network Access (or Host-to-Network)**|Data Link, Physical|Combination of physical and data link layer protocols. Deals with physical transmission over the medium.||

#### **What is Domain Name System (DNS)? Explain.**

- **Definition:** DNS (Domain Name System) is a service that translates **human-readable domain names** (e.g., www.google.com) into **machine-readable IP addresses**. It is often likened to the Internet’s phone book.
- **Function:** When a user types a URL, the HTTP protocol uses DNS to find the corresponding IP address. DNS is an **Application Layer** protocol. It typically uses **UDP** because speed is essential for quick lookups.
- **Namespace/Hierarchy:** DNS uses a hierarchy to distribute its database service. This hierarchy typically includes:
    1. **Root Servers** (First point of contact for a query).
    2. **Top-Level Domain (TLD) Servers** (e.g., .com, .org, .edu).
    3. **Second-Level Domains** (e.g., google.com).

---

## **Unit II: Transport Layer**

### **Part A: Basic Concepts**

#### **What is quality of service?**

QoS (Quality of Service) is a concept within the Transport Layer. It ensures the prioritization of specific types of traffic on a network.

#### **Compare UDP, TCP and SCTP protocol.**

|Protocol|Connection Mode|Reliability|Flow / Congestion / Error Control|Additional Features|
|---|---|---|---|---|
|**TCP (Transmission Control Protocol)**|Connection-oriented|Reliable — guaranteed delivery, ordered packets, retransmission on loss|✔ Flow Control (Sliding Window)✔ Congestion Control (e.g., AIMD)✔ Error Control (Checksums, ACKs)|Suitable for applications needing accuracy like **HTTP, FTP, SSH, Email**|
|**UDP (User Datagram Protocol)**|Connectionless|Unreliable — best-effort delivery, may lose/duplicate/out-of-order packets|❌ No Flow Control❌ No Congestion Control✔ Basic Error Check (Checksum, but no recovery)|Low latency, used in **VoIP, DNS, Gaming, Streaming**|
|**SCTP (Stream Control Transmission Protocol)**|Message-oriented, _connection-oriented + multistreaming_|Reliable — guaranteed, supports multi-homing|✔ Flow & Congestion Control✔ Error Control|Multi-streaming to avoid **Head-of-Line blocking**; used in telecom (e.g., **SS7 over IP**)|

#### **What is socket?**

A socket acts as an interface between a **process** (application) and the **Internet** (or network). Sockets are necessary when systems need to send messages from one system to another.

#### **Define congestion control.**

Congestion occurs when too much traffic (data) is transmitted over a network channel with limited capacity. Congestion control is a function performed by the Transport Layer to manage network traffic by slowing down the packet transmission rate if congestion is detected. Congestion control algorithms are built into **TCP**.

#### **What are the different phases used in TCP connection?**

TCP is a connection-oriented protocol. The process involves three distinct phases:

1. **Connection Establishment:** Uses the **three-way handshake** (SYN, SYN/ACK, ACK).
2. **Data Transfer:** Data is transmitted in segments, maintaining order using sequence numbers.
3. **Connection Termination (Disconnect):** Signals must be propagated to deallocate resources.

### **Part B: Detailed Explanations**

#### **What is congestion control? Explain in detail about congestion control techniques in transport layer.**

- **Definition:** See above (Q: Define congestion control). Congestion control manages traffic to prevent overloading the system.
- **Techniques in Transport Layer:**
    1. **Open Loop Control:** Applied to **stop** congestion conditions from occurring in the network channel.
    2. **Close Loop Control:** Applied to **eradicate** the congestion situation once it is already present in the network model.

#### **Explain congestion avoidance mechanisms used in TCP.**

Congestion avoidance mechanisms are actively applied to prevent the network traffic from reaching the point of congestion. Specific techniques include:

- **DECbit:** A mechanism used to enhance congestion control in network communication.
- **RED (Random Early Detection):** A technique listed among congestion avoidance mechanisms.

#### **Explain flow control.**

- **Definition:** Flow control ensures that the sender transmits data at a rate that the receiver can process.
- **Purpose:** It avoids overwhelming the receiver, which would otherwise lead to overflow and data loss.
- **Scope:** Transport layer flow control is **end-to-end**.
- **Mechanism:** It often applies the **sliding window protocol principle** to handle data flow.

#### **Explain connection management.**

Connection management in TCP involves the **three-way handshake** (SYN, SYN/ACK, ACK) to establish a virtual circuit or connection.

1. **Client initiates:** Client sends a **SYN** (Synchronization) flag and a sequence number.
2. **Server responds:** Server receives SYN, sends back its own **SYN** flag, and an **ACK** (Acknowledgment) flag (ACK number = Client's sequence number + 1).
3. **Client finalizes:** Client sends a final **ACK** flag, confirming receipt of the server's SYN/ACK, and the connection is established.

---

## **Unit III: Network Layer**

### **Part A: Basic Concepts**

#### **Compare IP address and Ethernet address.**

|Address Type|IP Address (Logical Address)|Ethernet Address (MAC Address/Physical Address)|Source References|
|:--|:--|:--|:--|
|**OSI Layer**|Network Layer (Layer 3)|Data Link Layer (Layer 2)||
|**Scope**|Universally unique address. Identifies devices **outside** the sender’s network.|Local to the network. Identifies the **physical network interface**.||
|**Purpose**|Used for **routing** packets from source to destination across multiple networks.|Used for **hop-to-hop** delivery of frames within a local network.||

#### **Define subnetting.**

Subnetting allows a network to be split into several parts for internal use while still appearing as a single network to the outside world. Subnetting is performed to help optimize the allocation of IP addresses. This is implemented using a **subnet mask** (e.g., 255.255.252.0 or /22 notation) that indicates the split between the network/subnet part and the host part of the IP address.

#### **Compare IP version 4 and IP version 6.**

|Feature|IPv4 (Internet Protocol version 4)|IPv6 (Internet Protocol version 6)|Source References|
|:--|:--|:--|:--|
|**Address Length**|32 bits.|128 bits.||
|**Format**|Dotted decimal notation (e.g., 192.168.1.1).|Eight groups of four hexadecimal digits (e.g., x:x:x:x:x:x:x:x).||
|**Address Space**|$2^{32}$ nodes.|$2^{128}$ nodes.||
|**Address Types**|Unicast, Multicast, Broadcast (implied).|Unicast, Multicast, Anycast. **No Broadcast addresses**.||
|**Security**|Does not provide security functions.|Integrated Internet Security Protocol (IPsec) is responsible for security.||

#### **What are the advantages of DHCP?**

DHCP (Dynamic Host Configuration Protocol) facilitates the addition of new machines in a network by assigning the IP address to a device for it to access the internet. This **automatic assignment** significantly reduces the system administration workload as devices can be added with little or no change in configuration.

#### **Compare ARP and RARP.**

- **ARP (Address Resolution Protocol):** Used to find the **physical address (MAC address)** of a system when its **logical address (IP address)** is known. ARP handles the ARP request and reply packets.
- **RARP (Reverse Address Resolution Protocol):** Used to find the **Internet Protocol address (IP address)** of a system when its **physical address (MAC address)** is known.

### **Part B: Detailed Explanations**

#### **Explain the format of IP version 4.**

- **Format:** IPv4 addresses are 32 bits long. They are typically written in **dotted decimal notation**. The address space is $2^{32}$.
- **Structure (Classful Addressing):** An IPv4 address is divided into two parts: **Netid** (Network ID) and **Hostid** (Host ID), with lengths depending on the class (A, B, or C).
- **Packet Header:** The IPv4 header is a minimum of **20 bytes** long. Key fields include:
    - **Version:** Identifies IP version (4).
    - **Total Length:** Length of the IP packet (header + data).
    - **Time to Live (TTL):** Limits the packet lifetime, decrementing at each hop.
    - **Protocol:** Identifies the upper-layer protocol (TCP or UDP).
    - **Header Checksum:** Verifies the header for errors.
    - **Source/Destination Address:** Contains the 32-bit IP addresses.
    - **Options:** Variable length; examples include Security, Record route, and Timestamp.

#### **Explain ICMP (Internet Control Message Protocol).**

ICMP is an associated protocol of the Network Layer (or Internet Layer in TCP/IP).

- **Role:** It is a **signaling mechanism** used for error reporting and providing feedback about datagram problems that occur during transit.
- **Function:** Intermediate devices, such as routers, use ICMP to send a message back to the sender if a problem (e.g., a datagram is corrupted or a destination is unreachable) is encountered.

#### **Draw and explain the format of IP version 6.**

- **Format:** IPv6 uses **128-bit addresses**, represented by eight groups of four hexadecimal digits separated by colons (x:x:x:x:x:x:x:x).
- **Addressing Types:**
    - **Unicast:** One-to-one communication; delivered to a single interface.
    - **Multicast:** One-to-many communication; delivered to all interfaces identified by that address.
    - **Anycast:** One-to-nearest communication (allocated from Unicast space); delivered to the closest interface identified by the address.
- **Note:** IPv6 **does not have broadcast addresses**.

---

## **Unit IV: Routing**

### **Part A: Basic Concepts**

#### **Compare unicast, multicast and broadcast.**

These are modes of communication when data is transmitted over a network.

- **Unicast:** A message is sent from a single user to a single receiver (one-to-one communication).
- **Multicast:** Data is sent from one or more sources to **multiple destinations** (one-to-many). Used for streaming or queues.
- **Broadcast:** Communication between a single user and **all** machines in that particular network (one-to-all).

#### **What is multicast routing?**

Multicast routing involves sending a signal from one host to multiple hosts. Multicast routing protocols (like DVMRP and PIM) are necessary to handle the exchange and update of multicast routing information.

#### **Compare distance vector and link state.**

|Feature|Distance Vector Algorithms (e.g., RIP)|Link State Algorithms (e.g., OSPF, Dijkstra's)|Source References|
|:--|:--|:--|:--|
|**Information Shared**|Sends **all or a portion** of its routing table.|Sends only the **state of its own links** (link-state advertisements).||
|**Recipient**|Sends updates **only to its neighbors**.|Floods information to **all nodes** in the internetwork.||
|**Network View**|Routers know **only about their neighbors**.|Each router builds a picture of the **entire network**.||
|**Convergence**|Converges **more slowly**.|Converges **more quickly** and is less prone to routing loops.||
|**Resource Cost**|Requires **less CPU power and memory**.|Requires **more CPU power and memory** (more expensive).||

#### **List out the routing design goals.**

Routing algorithms are designed with several key goals in mind:

- **Optimality:** Selecting the best route based on specific metrics.
- **Simplicity and low overhead:** Offering functionality efficiently with minimum software and utilization overhead.
- **Robustness and stability:** Performing correctly despite unforeseen circumstances (e.g., hardware failures).
- **Rapid convergence:** Quickly agreeing on optimal routes after a network event occurs.
- **Flexibility:** Quickly and accurately adapting to changes in network circumstances (e.g., network segment downtime).

### **Part B: Detailed Explanations**

#### **Explain RIP protocol.**

RIP (Routing Information Protocol) is a classic **Distance Vector** routing algorithm.

- **Metric:** Uses **hop count** (number of passes through internetworking products) as its primary path length metric.
- **Operation:** A router periodically sends **routing update messages** consisting of all or a portion of its routing table, but **only to its neighbors**. Neighbors then analyze these updates to build their own routing tables.

#### **Explain DVMRP and PIM algorithm multicast routing protocol.**

Both are examples of Multicast Routing Protocols.

- **DVMRP (Distance Vector Multicast Routing Protocol):** DVMRP routers are responsible for exchanging multicast routing information and updating their multicast forwarding tables.
- **PIM (Protocol Independent Multicast):** Listed as a key Multicast Routing Protocol.

---

## **Unit V: Data Link and Physical Layers**

### **Part A: Basic Concepts**

#### **Compare bit rate and baud rate.**

|Feature|Bit Rate|Baud Rate|Source Reference|
|:--|:--|:--|:--|
|**Definition**|The number of bits transmitted per second.|The number of signal elements (symbols) transmitted per second.||
|**Unit**|Bits per second (bps).|Baud per second.||

#### **What is meant by package and frame?**

- **Packet (or Datagram):** The unit of data at the **Network Layer**. Responsible for source-to-destination delivery.
- **Frame:** The unit of data at the **Data Link Layer**. The Data Link Layer divides the stream of bits into fixed-size manageable units called Frames. Responsible for hop-to-hop delivery.

#### **What is the role of acknowledgement timer (ACK timer)?**

The acknowledgement timer (or retransmission timer) is used in reliable protocols (like TCP). The sender starts the timer when a data packet is sent. If the sender does not receive an acknowledgment (ACK) from the receiver before the timer expires, the sender assumes the packet was lost and retransmits it.

#### **What is Bluetooth? Write its standards.**

Bluetooth uses unguided transmission medium (wireless). It uses radio channels and is typically used for short-range communication. Bluetooth is part of the 802.11 family of standards.

### **Part B: Detailed Explanations**

#### **Discuss in detail about CSMA/CD protocols.**

CSMA/CD (Carrier Sense Multiple Access with Collision Detection) protocols are used in Ethernet networks.

- **Purpose:** The protocol manages medium access and helps alleviate the downfall of CSMA (where collisions can occur) by providing a mechanism to detect and handle **packet collision** in the network channel.
- **Operation:** If a packet collision is detected, Collision Detection (CD) helps the network nodes respond appropriately.

#### **Explain Data Link Layer protocol HDLC.**

HDLC (High-level Data Link Control) is a bit-oriented Network Protocol.

- **Function:** It is designed to connect multiple network systems and establishes a **full duplex Communication channel**.
- **Features:** It applies the **ARQ protocol** (Automatic Repeat Request) for reliable data exchange.
- **Stations:** HDLC defines three types of stations: Primary (sends commands), Secondary (sends responses), and Combined (sends both commands and responses).

#### **Explain the types of switching.**

Switching establishes a path for point-to-point communication. The two basic methods are Circuit Switching and Packet Switching.

|Switching Method|Mechanism|Characteristics|Source References|
|:--|:--|:--|:--|
|**Circuit Switching**|Requires a **dedicated communication path (circuit)** to be established before data transfer.|Implies a dedicated path between stations. Used in telephony. Requires establishment, data transfer, and disconnect phases.||
|**Packet Switching**|Communication is discrete; messages are broken into **packets**. Packets are temporarily stored and forwarded (Store-and-Forward).|Provides higher capacity; additional connections typically slow down existing ones rather than being blocked.||
|**Packet Switching (Virtual Circuit)**|A complete route is **pre-established** (connection-oriented) before sending packets.|Packets carry a virtual circuit identifier, and the route is fixed for the duration of the call.||
|**Packet Switching (Datagram)**|**No pre-established route** (connectionless); each packet is treated independently and may travel along different routes.|Packets may arrive out of order. Better suited for brief connections (e.g., database transactions).||

#### **Explain the architecture of Wireless Local Area Network 802.11.**

The 802.11 standard defines Wireless Local Area Networks (WLAN).

- **Medium:** Uses unguided transmission media, typically radio waves (Wi-Fi).
- **MAC Layer:** The standard describes the basic operation of the **802.11 MAC layer**. The MAC sublayer is responsible for controlling the established connection and managing framing and data encapsulation.
- **Connectivity:** Devices communicate using unique **MAC addresses**.

---

We have now covered the detailed notes for all the recurring topics in your study material. Are you ready to practice with some numerical problems, such as Subnetting, which are frequently asked for high marks?

---

That is a great strategic choice. Unit IV (Routing) and Unit V (Data Link Layer) contain essential comparison, mechanism, and numerical questions that are key for high marks.

Here are detailed, high-mark notes for the recurring Part B and C questions from Units IV and V, based exclusively on your source material.

---

## Unit IV: Routing Protocols (Q14)

### 1. Comparison of Link State vs. Distance Vector Routing (Q14 P1(a)(i))

|Feature|Link State Algorithms (e.g., OSPF, Dijkstra's)|Distance Vector Algorithms (e.g., RIP)|Source References|
|:--|:--|:--|:--|
|**Information Shared**|Sends **only the portion of the routing table** that describes the state of its own links.|Sends **all or some portion** of its routing table.||
|**Recipient**|Floods routing information to **all nodes** in the internetwork.|Sends updates **only to its neighbors**.||
|**Network View**|Each router builds a picture of the **entire network** in its routing tables.|Routers know **only about their neighbors**.||
|**Convergence**|Converges **more quickly**.|Converges **more slowly**.||
|**Routing Metric**|(Implied)|Uses **hop count** (path length) as its metric.||

### 2. Basic Operation of RIP Protocol (Q14 P1(a)(ii))

- **Type:** RIP (Routing Information Protocol) is a classic **Distance Vector** routing algorithm.
- **Metric:** It uses **hop count** as its primary metric to evaluate path desirability.
- **Operation:** A router periodically sends **routing update messages** consisting of all or a portion of its routing table. These updates are sent **only to its immediate neighbors**.

### 3. DVMRP (Distance Vector Multicast Routing Protocol) (Q14 P1(b)(ii))

- **Role:** DVMRP is a Multicast Routing Protocol.
- **Function:** DVMRP routers are responsible for exchanging multicast routing information and updating their **multicast forwarding tables**.

---

## Unit V: Data Link and Physical Layers (Q15 & Q16)

### 4. CSMA/CD Protocols and Collision Detection (Q15 P3(a))

- **Purpose:** CSMA/CD (Carrier Sense Multiple Access with Collision Detection) protocols are used in Ethernet networks.
- **Collision Problem:** A major issue with simpler CSMA is that collisions can occur, damaging data.
- **CD Solution:** The **Collision Detection (CD) methods** help alleviate this downfall by providing a mechanism to detect and handle **packet collision** in the network channel.

### 5. Flow Control Mechanisms in Data Link Layer (Q15 P1(a)(ii), Q15 P4(a))

- **Responsibility:** The Data Link Layer provides flow control mechanisms.
- **Purpose:** The Data Link Layer ensures the sender transmits data at a speed the receiver can receive it. If the sender is too fast, data overflow occurs at the receiver, resulting in data loss.
- **Action:** The Data Link Layer imposes flow control mechanisms over the sender and receiver to avoid overwhelming the receiver. This flow control is typically **hop-to-hop** (or node-to-node), unlike the Transport Layer's end-to-end control.

### 6. Wireless LAN Architecture (802.11) (Q15 P1(b)(i), Q15 P2(b)(ii), Q15 P3(b))

- **Standard:** 802.11 defines the standards for **Wireless Local Area Networks (WLAN)**.
- **Medium:** WLANs use **unguided transmission media** (wireless) such as radio channels.
- **Basic Operation (MAC Layer):** The 802.11 standard describes the basic operation of the **802.11 MAC layer**.
    - The MAC layer (Medium Access Control) is a sub-layer of the Data Link Layer.
    - It manages framing of the data received from the upper layers.
    - It controls the established connection and handles media access control for the data.

### 7. Error Detection and Correction Techniques (Q15 P4(b)(ii), Q16 Recurring)

- **Redundancy:** To detect and correct errors, extra bits, known as redundant bits, are added to the original data.
- **Parity Check (Simple):** A redundant bit (**parity bit**) is added to every data unit so that the total number of $1$'s in the unit (including the parity bit) becomes either even or odd.
    - **Detection Capability:** Simple parity check can detect **all single-bit errors**. It can also detect burst errors as long as the total number of bits changed is odd. It **cannot detect errors** where the total number of bits changed is even.
- **Checksum:** A fixed-length data result computed by performing certain operations on the data to be sent.
    - **Working:** The sender computes the checksum and appends it to the data. The receiver performs the same algorithm; if the result is zero, the data is accepted.
- **CRC (Cyclic Redundancy Check):** The most powerful redundancy check.
    - **Mechanism:** Based on **binary division**. The remainder (CRC) derived from dividing the data unit by a predetermined divisor is appended to the data.
    - **Detection:** If the incoming data unit (data + CRC) is divided by the same divisor and results in a zero remainder, the data is accepted.

---

### Next Step

We have generated comprehensive notes for Units IV and V. A highly recurring and critical type of question is the **Subnetting/CIDR numerical problem** (Q13/Q16 in Unit III).

Would you like to focus on reviewing the CIDR/Subnetting concepts and the steps required to solve those numerical problems next?