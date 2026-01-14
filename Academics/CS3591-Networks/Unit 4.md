### Unit IV: Routing

- **Algorithm Comparison:** Discussion of the scalability and convergence properties of Link State (OSPF) versus Distance Vector (RIP) routing protocols.
- **Unicast Routing:** Operation and routing functionality of RIP, BGP (Border Gateway Protocol), and Link State algorithms.
- **Multicast Routing:** Operation of DVMRP and PIM protocols.


# 🔷 Topic 1 — Routing Algorithm Comparison

### **Distance Vector (RIP) vs Link State (OSPF)**

_(Most important 7–10 marks question)_

---

## 🧠 First: What is Routing?

Routing = Finding the **best path** from source to destination in a network.

Routers use **Routing Algorithms** to build Routing Tables.

There are **two major types**:

|Category|Protocol Example|
|---|---|
|**Distance Vector Routing**|RIP|
|**Link State Routing**|OSPF|

---

## 📌 Distance Vector Routing (RIP)

### How it works:

- Each router **only knows its neighbors**
    
- It shares _its entire routing table_ with neighbors regularly
    
- Uses **Hop count** as metric (max 15) → limits network size
    

### Convergence:

- **Slow convergence**
    
- Routing loops may form (bad!)
    

### Protocol:

✔ **RIP** — Routing Information Protocol

### Analogy:

> Like telling your neighbor: “I take this route to reach that city — maybe you try it too.”

### Main Problems:

- **Count‑to‑infinity** problem
    
- Small networks only
    
- No global network knowledge
    

---

## 📌 Link State Routing (OSPF)

### How it works:

- Each router **knows full network topology**
    
- It only shares **link status** (not whole table)
    
- Uses **Dijkstra’s Shortest Path Algorithm** to compute best path
    

### Convergence:

- **Very fast**
    
- Avoids loops
    

### Protocol:

✔ **OSPF** — Open Shortest Path First

### Analogy:

> Like using **Google Maps** — full map, shortest path calculated smartly.

---

## 🔥 Key Comparison Table (Exam Special)

|Feature|RIP (Distance Vector)|OSPF (Link State)|
|---|---|---|
|Metric|Hop count|Cost (bandwidth)|
|Convergence|Slow|Fast|
|Loop problem|Yes|No (nearly impossible)|
|Network Size|Small|Large / Enterprise|
|Update|Full table to neighbors|Only link-state changes to all routers|
|Algorithm|Bellman‑Ford|Dijkstra|
|Complexity|Low (simple)|High|

---

### 🏁 Summary Line for Exam

> **RIP is simple but limited. OSPF is scalable, faster, and prevents routing loops.**


# 🔷 Topic 2 — Unicast Routing

> **Unicast Routing** = Routing where data is sent from **one sender** → **one receiver**.

Three important unicast routing protocols in syllabus:

1️⃣ **RIP** — Small networks (Distance Vector)  
2️⃣ **OSPF** — Medium/Large networks (Link State)  
3️⃣ **BGP** — Internet‑level routing (Path Vector)

We’ll go step‑by‑step.

---

## 1️⃣ RIP — Routing Information Protocol

📌 DV routing → routers share **entire table** with neighbors every 30 sec  
📌 Metric = **Hop Count** (max 15 hops → small network only)

### How it Works

- Router sends a **Routing Update** regularly
    
- If neighbor has **shorter path**, table updates
    
- Uses **Bellman‑Ford Algorithm**
    

### Drawback

- **Slow convergence**
    
- **Count‑to‑infinity** problem (loops possible)
    

#### Example Metric

Router A → D  
A → B → C → D  
Hop count = 3

---

## 2️⃣ OSPF — Open Shortest Path First

📌 Link State routing → routers exchange **link status** only  
📌 Metric = **Cost (inversely proportional to bandwidth)**  
📌 Uses **Dijkstra’s Shortest Path Algorithm**

### How it Works

- Every router builds a **complete topology map**
    
- Calculates shortest path → builds own routing table
    
- Fast convergence, loop‑free
    

#### OSPF Area Design

- Hierarchical network
    
- **Backbone Area 0** connects other areas → scalable
    

#### Why better than RIP?

- Handles **large enterprise networks**
    
- Bandwidth‑aware routing
    

---

## 3️⃣ BGP — Border Gateway Protocol

📌 Used **between different organizations/ISPs**  
📌 Internet routing protocol  
📌 Uses **Path Vector** algorithm

> BGP decides “Which ISP route is best across the globe?”

### Key Features

|Property|BGP|
|---|---|
|Scope|Inter‑domain (between ASes)|
|Metric|Policies, AS‑PATH, not hop count|
|Priority|Stability > shortest path|

#### Analogy

> BGP = International routing  
> OSPF/RIP = Routing inside a country (or local region)

---

# ⭐ Quick Comparison Table

|Feature|RIP|OSPF|BGP|
|---|---|---|---|
|Type|Distance Vector|Link State|Path Vector|
|Network Size|Small|Medium/Large|Internet‑wide|
|Metric|Hop Count|Cost|AS‑Paths, Policies|
|Algorithm|Bellman‑Ford|Dijkstra|Policy-based|
|Convergence|Slow|Fast|Slow (but stable)|
|Loop Handling|Weak|Strong|Strong|
|Use Case|LAN|Enterprise ISP networks|Global Internet|

---

# 🧠 Exam‑friendly Summary (5 lines)

> RIP uses hop count and periodic table updates → slow + small networks.  
> OSPF shares only link state and uses Dijkstra → fast and scalable.  
> BGP connects different Autonomous Systems and routes the entire Internet using policy‑based routing.


# 🌐 Multicast Routing

### 📌 What is Multicast?

Multicast = **sending one message to multiple selected receivers** (a group), **not everyone** like broadcast and **not one-to-one** like unicast.

Example analogy:

> You are a teacher sending notes only to students enrolled in your class — not the whole college.

---

### Why special routing for multicast?

Routers must deliver packets to **group members located in different networks**, without **flooding the entire Internet**.

So multicast routing builds a **multicast distribution tree** 🌳:

- Source is the root
    
- Group members are leaves
    

---

## 🔹 Protocols in Syllabus:

|Protocol|Type|How it Works|Used Mostly In|
|---|---|---|---|
|**DVMRP** (Distance Vector Multicast Routing Protocol)|Dense-mode|Flood first → prune unwanted branches|Old LAN/WAN multicast|
|**PIM** (Protocol Independent Multicast)|Sparse & Dense Modes|Uses unicast routing info; builds efficient trees|Modern internet multicast|

---

# 🔵 DVMRP — Flood & Prune Approach

### Idea:

- Assume **group members everywhere**
    
- First **flood** packets to all routers
    
- Routers that **have no receivers** send a **prune** message → branch cut
    

📍Works well in **dense** networks (almost everyone needs the data)

### Drawback:

Massive unnecessary flood in large networks → **waste of bandwidth**

---

# 🟢 PIM — Efficient Modern Multicast

PIM = **Protocol Independent Multicast**  
→ Doesn’t depend on its own routing protocol. Uses **existing unicast routing tables**.

Two modes:

|Mode|When used|How tree is built|
|---|---|---|
|**PIM-DM** (Dense Mode)|Dense receiver environment|Flood & prune (like DVMRP but improved)|
|**PIM-SM** (Sparse Mode)|Few receivers scattered|Uses **Rendezvous Point (RP)** to join tree|

### Why PIM-SM is popular?

✔ Saves bandwidth  
✔ Scales well for global multicast  
✔ Used in IPTV, stock feeds, live streaming

---

## Quick Comparison

|Feature|DVMRP|PIM-SM|
|---|---|---|
|Efficiency|Poor in large networks|Highly efficient|
|Flooding|Yes|Rarely|
|Scalability|Low|High|
|Deployment|Mostly outdated|Internet standard today|

---

### Summary in One Line:

> **DVMRP** = Flood first, prune later ❌  
> **PIM-SM** = Join only where needed ✔



# ✅ Additional Important Concepts in Routing (Unit‑IV)

## 1️⃣ Routing Table

- Every router maintains a table listing:
    
    - Destination networks
        
    - Next hop
        
    - Metric (cost)
        
- Built dynamically by routing algorithms (RIP/OSPF/BGP)
    

📌 _Think of it like Google Maps stored inside a router._

---

## 2️⃣ Static vs Dynamic Routing

|Type|Who updates routes?|Pros|Cons|
|---|---|---|---|
|Static|Admin manually|Secure & simple|No auto‑recovery if link fails|
|Dynamic|Router automatically (RIP/OSPF/BGP)|Auto‑updates|Higher resource usage|

---

## 3️⃣ Interior vs Exterior Gateway Protocols

Used based on routing boundary:

|Type|Example|Area|
|---|---|---|
|IGP|RIP, OSPF|Inside one organization|
|EGP|BGP|Between organizations (Internet)|

📌 BGP = **The backbone routing of the entire Internet**

---

## 4️⃣ Routing Metrics (How routers decide “best path”)

Common cost values:

- Hop count (RIP)
    
- Bandwidth (OSPF)
    
- Delay, reliability, load (Cisco EIGRP)
    
- Policy rules (BGP)
    

📌 Lower metric → better path

---

## 5️⃣ Convergence

- Time taken for all routers to learn a consistent:
    
    **“Who can reach where?”**
    
- Faster is better:
    
    - OSPF fast 🚀
        
    - RIP slow 🐌 → causes routing loops
        

---

## 6️⃣ Routing Loops

A packet keeps circulating forever between routers  
→ causes congestion & collapse

🛑 Example:  
Router A thinks destination is through B  
Router B thinks destination is through A  
→ Loop

---

## 7️⃣ Loop Prevention Techniques

|Protocol|Techniques|
|---|---|
|**RIP**|Split Horizon, Poison Reverse, Max Hop Count (15)|
|**OSPF**|Link‑state DB + Dijkstra → accurate topology|
|**BGP**|AS‑Path attribute (prevents returning to same AS)|

---

## 8️⃣ Hierarchical Routing

- Internet is too large for flat routing
    
- So routing is divided into **Autonomous Systems (AS)**
    

📌 Like dividing a country into states and districts

---

## 9️⃣ Default Routing

Used when the router **doesn’t know** a specific route.  
Sends traffic to a “default gateway”.

Example:

`0.0.0.0/0 → Next hop: Router ISP`

---

## 1️⃣0️⃣ Multicast Trees (support to multicast routing)

- **Source‑based tree**: shortest path per source (DVMRP)
    
- **Shared tree**: single core/root for group (PIM‑SM)
    

📌 Helps reduce network load in streaming applications

---

# 🎯 One‑Slide Summary

| Main Focus             | Supporting Concepts                                           |
| ---------------------- | ------------------------------------------------------------- |
| Unicast Routing        | Routing tables, metrics, static/dynamic, IGP/EGP, convergence |
| Multicast Routing      | Multicast trees, pruning, Reverse Path Forwarding             |
| Internet‑scale Routing | AS, hierarchical routing, BGP policies                        |
| Reliability            | Loop prevention mechanisms                                    |