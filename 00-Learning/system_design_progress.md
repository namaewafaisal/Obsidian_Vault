# System Design Interview Prep — Progress & Roadmap

## About This File
Use this file to resume your learning with Claude in any account or session.
Paste this file's contents (or the resume prompt at the bottom) into a new chat to continue exactly where you left off.

---

## Your Profile
- **Level:** Know basics (HTTP, APIs, DBs)
- **Goal:** System design interviews
- **Learning style:** Concept + real example together, quiz before moving to next topic

## Teaching Format (How Claude Should Teach You)
Every topic follows this structure:
> **What it is → Why it exists → How it works → Real-world example → Interview tip → Quiz**

- Teach the concept with real-world examples (like WhatsApp, Netflix, YouTube, Twitter)
- Quiz with 4 questions after every topic
- Give detailed feedback on each answer before moving on
- If a question sparks a deeper topic, explain it inline before continuing
- Be conversational — not just definitions, but reasoning and tradeoffs

---

## Completed Topics ✅

### 1. Scalability
**Core concepts learned:**
- Vertical scaling (bigger machine) vs Horizontal scaling (more machines)
- Vertical has a hard limit and single point of failure
- Horizontal needs stateless servers — session state in shared store (Redis), not in-memory
- Most companies: start vertical → move horizontal as they grow

**Key insight from quiz:**
- Independently arrived at containerization/VMs for uniform server performance → easier load balancing
- Understood that cost is non-linear with vertical scaling

**Interview answer:**
> "Start vertical for simplicity, move to horizontal with a load balancer as traffic grows. Ensure app servers are stateless — store sessions in Redis instead of in-memory."

---

### 2. Latency vs Throughput
**Core concepts learned:**
- Latency = time for one request (ms) | Throughput = requests per second
- They are independent and can conflict (batching increases throughput but increases latency per item)
- Latency numbers to memorize:
  - L1 Cache: 1ns | RAM: 100ns | SSD: 100µs | HDD: 10ms | Network same region: 1-5ms | Cross-continent: 100-150ms
- RAM is 100,000x faster than disk — this is why caching exists
- Use **percentiles** (p50, p95, p99) not averages — averages hide tail latency
- Amazon: every 100ms of latency = 1% lost sales

**Key insight from quiz:**
- Correctly identified p95/p99 jumps to find where latency issues hide
- Understood batching tradeoff: 200 payments/sec → 33,300/sec with batching, at cost of per-item latency
- Identified cross-continent network latency as root cause of Chennai → US slowness
- RAM latency is 100ns (not 10ms — remember this number)

**Interview answer:**
> "I'd target p99 latency under 200ms. Use caching to avoid repeated DB hits, place servers in regions close to users, and use async processing for non-critical work to keep the critical path fast."

---

### 3. CAP Theorem (Availability vs Consistency)
**Core concepts learned:**
- **C**onsistency: every read returns the latest write
- **A**vailability: every request gets a response (possibly stale)
- **P**artition Tolerance: system works even when servers can't communicate — NOT optional
- Real choice: when partition happens, pick C or A
- **Consistency spectrum:** Strong consistency → Eventual consistency
- **PACELC:** Even without partition, tradeoff between Latency and Consistency

**Consistency models:**
- Strong consistency: banks, payments, seat booking, stock trades
- Eventual consistency: social media likes/views, DNS, shopping carts

**Database choices:**
| Database | Choice | Why |
|---|---|---|
| PostgreSQL, MySQL | CP | Traditional SQL |
| Cassandra | AP | Built for availability |
| DynamoDB | AP (default) | Tunable |
| Redis | CP | In-memory, single truth |
| Zookeeper | CP | Coordination |

**Quorum (W + R > N):**
- Write quorum + Read quorum must exceed total nodes for guaranteed consistency
- Example: 5 nodes, W=3, R=3 → 3+3>5 ✅ — at least 1 node in every read has the latest write
- Cassandra/DynamoDB let you tune quorum size to trade between AP and CP

**Multi-region replication (deep dive):**
- Intra-region: synchronous quorum (~1-5ms) → strong consistency within region
- Inter-region: async replication (~150-200ms) → eventual consistency across regions
- Conflict resolution: Last Write Wins (LWW) by logical version, Vector Clocks, CRDTs
- Node recovery: Gossip Protocol — nodes whisper state to each other until all converge
- Clock skew problem: real clocks can't be trusted across servers → use logical clocks / version numbers
- Google Spanner: uses atomic clocks + GPS to keep clocks accurate to 7ns → timestamps trustworthy

**Key insight from quiz:**
- "How does the server know if it has the latest?" → independently arrived at the split-brain problem
- Strong consistency globally means always waiting for the slowest node → latency cost

**Interview answer:**
> "For payments I need strong consistency — I'd use PostgreSQL with synchronous replication. During a partition, I'd rather return an error than process a potentially incorrect transaction. For a social feed, I'd favor availability with eventual consistency using Cassandra."

---

### 4. Load Balancers
**Core concepts learned:**

**Algorithms:**
- **Round Robin:** even distribution, ignores server load
- **Weighted Round Robin:** accounts for server capacity (4 CPU server gets 4x requests of 16 CPU server)
- **Least Connections:** routes to server with fewest active connections — best for variable request times
- **IP Hashing:** same user IP always hits same server — useful for stateful apps without Redis
- **Random:** surprisingly effective at scale due to probability

**L4 vs L7:**
- L4 (Transport): routes by IP/port only, fast but blind to content
- L7 (Application): reads URL, headers, cookies — enables smart routing, SSL termination, sticky sessions, A/B testing
- SSL termination unlocks L7 routing — without it, LB can't read request content

**SSL Termination:**
- Decrypt HTTPS once at LB, forward plain HTTP to servers
- Saves CPU on every backend server
- For payments: re-encrypt (LB → HTTPS → server) for end-to-end security

**Sticky Sessions:**
- L7 LB plants a cookie on first request
- Every subsequent request carries cookie → LB routes to same server
- Alternative to Redis for stateful apps (less scalable but simpler)

**Health Checks:**
- LB pings servers every N seconds
- Automatically removes dead servers, adds back when recovered
- Enables self-healing systems

**LB as single point of failure → fix:**
- Active-Passive: secondary standby takes over in seconds
- Active-Active: both handle traffic, DNS stops routing to failed one

**LB types:**
| Type | Example | Used for |
|---|---|---|
| Software | Nginx, HAProxy | Most common |
| Cloud | AWS ALB/NLB | Managed, auto-scaling |
| DNS | Route53 | Geographic routing |

**Real system LB layers:**
```
User → DNS → Global LB → Regional LB → API Gateway → Service LB → Servers
```

**Interview answer:**
> "I'd put an L7 load balancer using least connections algorithm since requests have variable processing time. It'll handle SSL termination and health checks. To avoid the LB being a single point of failure, I'd run it active-passive."

---

## Topics Remaining 📋

### 🧱 Foundation
- [ ] **5. Caching** — Redis deep dive, cache strategies, CDN ← NEXT
- [ ] **6. SQL vs NoSQL** — when to use which

### 🗄️ Data & Storage
- [ ] **7. Database Replication** — master-slave, read replicas
- [ ] **8. Database Sharding** — splitting data at scale
- [ ] **9. Indexing** — why queries go fast or slow
- [ ] **10. Object Storage** — S3, blobs, files

### 🔗 Communication
- [ ] **11. REST vs gRPC vs GraphQL** — picking the right API style
- [ ] **12. WebSockets & SSE** ✅ *(already know this)*
- [ ] **13. Message Queues** — Kafka, RabbitMQ, async communication
- [ ] **14. Event-Driven Architecture** — pub/sub, decoupling systems

### 🏗️ System Building Blocks
- [ ] **15. DNS** — how domain names resolve
- [ ] **16. CDN** — serving content globally fast
- [ ] **17. Reverse Proxy & API Gateway** — Nginx, Kong
- [ ] **18. Rate Limiting** — protecting your system from abuse
- [ ] **19. Consistent Hashing** — smart load distribution

### 🔐 Reliability & Scale
- [ ] **20. Fault Tolerance & Redundancy** — designing for failure
- [ ] **21. Circuit Breaker Pattern** — stopping cascading failures
- [ ] **22. Distributed Transactions** — data consistency across services
- [ ] **23. Microservices vs Monolith** — tradeoffs
- [ ] **24. Service Discovery** — how services find each other

### 🏢 Real System Designs
- [ ] **25. Design URL Shortener** (Bit.ly)
- [ ] **26. Design Instagram/Image Feed**
- [ ] **27. Design WhatsApp/Chat App**
- [ ] **28. Design YouTube**
- [ ] **29. Design Twitter/News Feed**
- [ ] **30. Design Uber/Ride Matching**
- [ ] **31. Design Google Drive**
- [ ] **32. Design a Rate Limiter**

---

## Key Concepts Glossary (Running List)

| Term | One-line definition |
|---|---|
| Vertical scaling | Make one machine bigger |
| Horizontal scaling | Add more machines |
| Stateless server | Server holds no session — state lives in shared store |
| Redis | In-memory key-value store — used for sessions, caching, rate limiting |
| Latency | Time for one request (ms) |
| Throughput | Requests per second |
| p99 latency | 99% of requests finish within this time |
| CAP Theorem | Distributed systems can't have both C and A during a partition |
| Eventual consistency | All nodes will sync up, just not instantly |
| Strong consistency | Every read returns the latest write |
| Quorum | Majority of nodes must agree before write is accepted |
| W + R > N | Formula guaranteeing consistent reads via quorum overlap |
| Gossip protocol | Nodes whisper state to each other until all converge |
| Clock skew | Clocks on different servers are never perfectly in sync |
| Logical clock | Version number instead of real time — safe across distributed nodes |
| CRDT | Data structure that auto-merges conflicts (used in Google Docs) |
| Load balancer | Distributes requests across servers |
| Round Robin | Rotate requests across servers in order |
| Weighted Round Robin | Distribute by server capacity |
| Least Connections | Route to server with fewest active connections |
| IP Hashing | Same IP → same server always |
| Sticky Sessions | Cookie-based routing to same server |
| L4 Load Balancer | Routes by IP/port — fast but blind |
| L7 Load Balancer | Routes by URL/headers/cookies — smart |
| SSL Termination | Decrypt HTTPS at LB, forward plain HTTP to servers |
| Health Check | LB pings servers, auto-removes dead ones |
| Active-Passive | Backup LB takes over when primary dies |
| Network Partition | Servers alive but can't communicate with each other |

---

## Resume Prompt (Copy-paste into new chat)

```
I'm learning system design for interviews. My level: know basics of HTTP, APIs, DBs.
Teaching style: concept + real example together, quiz me after each topic before moving on, give detailed feedback on each answer.

Already completed (with quizzes passed):
✅ Scalability — vertical vs horizontal, stateless servers, Redis for sessions
✅ Latency vs Throughput — percentiles, batching tradeoff, RAM=100ns, disk=10ms
✅ CAP Theorem — consistency vs availability, quorum, W+R>N, gossip protocol, multi-region replication, clock skew, logical clocks
✅ Load Balancers — Round Robin, Weighted RR, Least Connections, IP Hashing, L4 vs L7, SSL termination, sticky sessions, health checks, active-passive

Also know: WebSockets and SSE (covered separately)

Next topic: Caching (Topic 5) — Redis deep dive, cache strategies, CDN.
Continue from here in the same teaching style.
```
