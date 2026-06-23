# Networks — Interview Answers & Approach

## How to Answer
Same formula throughout: definition first, mechanism/why second, analogy or example only if asked to elaborate. Keep spoken answers to ~15-20 seconds.

## OSI Model
A conceptual framework breaking down how data travels from one machine to another into 7 layers, so each layer can be designed and replaced independently.

- **Physical** — actual transmission of raw bits over a wire/radio signal.
- **Data Link** — packages bits into frames, handles MAC addresses and local error detection (Ethernet/Wi-Fi).
- **Network** — routes data between networks using IP addresses; where routers operate.
- **Transport** — end-to-end delivery between two devices; breaks data into segments, handles reliability. Port numbers live here. TCP and UDP operate here.
- **Session** — manages when a connection opens/closes between two devices.
- **Presentation** — formats/translates data: encryption, compression, character encoding.
- **Application** — user-facing protocols: HTTP, FTP, SMTP.

**OSI vs TCP/IP:** the internet actually runs on the simpler TCP/IP model (Link, Internet, Transport, Application), which collapses several OSI layers together. OSI is the theoretical teaching model; TCP/IP is what's actually implemented.

## TCP vs UDP
Both operate at the Transport layer.

- **TCP** — connection-oriented. Handshake first, guarantees delivery and order (resends lost packets). Slower, more overhead. Used where correctness matters: web, file transfer, email.
- **UDP** — connectionless. Fires packets without checking arrival/order. Faster, less overhead, can lose/reorder packets. Used where speed matters: video calls, streaming, gaming, DNS.

**TCP handshake:** SYN → SYN-ACK → ACK.

## HTTP vs HTTPS
HTTP is the request/response protocol (GET, POST...) — plain text, readable if intercepted. HTTPS adds a TLS/SSL encryption layer on top — data in transit is encrypted, unreadable without the key.

**Status code ranges:** 2xx success, 3xx redirect, 4xx client error, 5xx server error.

## DNS
Translates a human-readable domain name into the numeric IP address needed to route to it — like a phonebook.

## Ports
An IP address reaches the right machine; a port number reaches the right application on that machine. HTTP = port 80, HTTPS = port 443.

## "What happens when you type a URL into the browser"
1. Browser checks cache, else asks DNS to resolve domain → IP.
2. Opens a TCP connection to that IP (handshake).
3. If HTTPS, TLS handshake sets up encryption.
4. Browser sends HTTP request.
5. Server processes and responds (data + status code).
6. Browser renders response, fetching additional resources (CSS/JS/images) the same way.

## Client-Server Architecture
Client (browser/app) initiates requests; server provides responses. REST APIs run on this model and are typically **stateless** — each request must carry everything the server needs, since the server doesn't remember previous requests.

---

## OSI Layer Workflow — Detailed (Routers, MTU, End-to-end vs Hop-to-hop)

### The setup
Data crossing the internet usually goes sender → router → router → ... → receiver, not directly. Different things happen at different layers at each hop.

### End-to-end vs hop-to-hop
- **Application, Presentation, Session, Transport — end-to-end.** Set up once between original sender and final receiver; routers don't inspect or modify this.
- **Network layer (IP)** — addressing is end-to-end (source/destination IP unchanged across the journey), but every router reads it to decide where to forward next.
- **Data Link layer — hop-to-hop.** Rebuilt completely at every router, since each physical link (Wi-Fi, fiber, Ethernet) may use a different technology with its own framing rules and addressing.

### What a router does, step by step
1. Strips the incoming frame (only valid for the previous link).
2. Reads the IP header underneath to determine destination and next hop.
3. Builds a brand-new frame, addressed for the next hop, formatted for that link's technology.

The IP packet inside stays untouched throughout — only the frame wrapped around it gets rebuilt at each hop.

### MTU and fragmentation
Every physical link has a **Maximum Transmission Unit (MTU)** — the largest frame size it can carry. If a packet is too large for the next hop's MTU, it gets fragmented to fit, then reassembled later. This is the real mechanism behind frame-size limits — not receiver buffer speed, but the next link's maximum carrying capacity.

### What stays constant vs changes per hop
| | Stays constant | Changes every hop |
|---|---|---|
| IP header (source/dest IP) | ✅ | |
| MAC address (in frame) | | ✅ |

IP addressing is the stable "who's really talking to who" for the whole trip. MAC addressing is a local, single-hop concern only.

### Worked example
Laptop (IP `192.168.1.5`, MAC `AA`) → Home router (MAC `BB`) → ISP router (MAC `CC`) → Server (IP `203.0.113.10`, MAC `DD`)

- Frame 1: MAC `AA` → `BB`. IP packet inside: `192.168.1.5` → `203.0.113.10` (unchanged).
- Home router strips Frame 1, reads IP header, forwards.
- Frame 2: MAC `BB` → `CC`. Same IP packet, unchanged.
- ISP router repeats the process.
- Frame 3: MAC `CC` → `DD`. Same IP packet, unchanged.
- Server strips Frame 3, sees the IP packet addressed to itself, passes payload up to Transport/Application layers.

Three hops, three different frames, three different MAC pairs — but exactly one IP packet, untouched, the whole way.
