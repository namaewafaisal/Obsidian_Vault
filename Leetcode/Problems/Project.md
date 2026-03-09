# Chat Infrastructure Project — Full Roadmap
> Standalone, reusable, zero cost. Built to be integrated into anything.

---

## The Core Idea

You are not building a WhatsApp clone.
You are building a **messaging engine** — a backend infrastructure that any application can plug into.

Think of it like this:
- **Your project** = the engine
- **Future apps** = anything that needs messaging (course platform, team tool, game, support chat)

This is what makes it reusable. You expose a clean API and WebSocket interface. Anyone can build a frontend on top of it.

---

## What You Are Building

```
[Any Client] → WebSocket / REST API → [Your Chat Engine] → Redis Pub/Sub → [Any Client]
                                              ↓
                                         MongoDB
                                      (message history)
```

### Core Features (must build)
- Real-time messaging via WebSocket
- Rooms / channels (group chat)
- Direct messages (1 to 1)
- Message persistence (history survives restart)
- JWT authentication
- Online presence (who is online)
- Read receipts
- REST API for integration by other apps

### Future Features (build later)
- File attachments
- Message reactions
- Notifications via webhook
- Rate limiting
- Admin API
- Typing indicators

---

## Why It Is Reusable / Integratable

You will expose two interfaces:

**1. WebSocket endpoint**
Any frontend — React, Flutter, mobile, desktop — connects here for real-time messages.

**2. REST API**
Any backend service calls this to:
- Create rooms
- Send system messages
- Fetch message history
- Manage users

This means your course platform from last semester can integrate a chat feature by just calling your API. A game can add a lobby chat. A support tool can embed it.

That is a real engineering decision with a real story to tell.

---

## Full Tech Stack — Zero Cost

| Layer | Technology | Why |
|---|---|---|
| Language | Java 17 | Your stack |
| Framework | Spring Boot 3 | Your stack |
| Real-time | Spring WebSocket + STOMP | Built into Spring, no extra cost |
| Message broker | Redis Pub/Sub | Free, runs locally, solves multi-server problem |
| Database | MongoDB | Your stack, good for message storage |
| Auth | JWT (Spring Security) | You already touched this |
| Build tool | Maven or Gradle | Already using |
| Containerization | Docker | Free, makes deployment easy |
| Hosting | Railway / Render free tier | Zero cost |
| Version control | GitHub | Already using |

---

## The One Hard Problem That Makes This Impressive

When you run **one server**, everything is simple. WebSocket connections live on that server, messages pass between them.

When you run **two servers**, user A connects to server 1, user B connects to server 2. They cannot reach each other directly.

```
Server 1 (User A connected)       Server 2 (User B connected)
         ↑                                  ↑
         └──────────── Redis ───────────────┘
                    (Pub/Sub)
```

Redis Pub/Sub is the bridge. Every server subscribes to message channels. When server 1 receives a message from A, it publishes to Redis. Server 2 receives it from Redis and delivers to B.

This is a real distributed systems pattern used by Slack, Discord, every serious chat system. You learning and implementing this is what makes the project serious.

---

## What You Need To Learn (In Order)

### Week 1-2 — WebSockets
- What is a WebSocket vs HTTP
- How Spring WebSocket works
- What STOMP protocol is (messaging protocol on top of WebSocket)
- Build: single server chat, no auth, no DB

Resources:
- Spring WebSocket official docs
- Baeldung Spring WebSocket guide (free)

### Week 3-4 — Redis
- What Redis is and why it exists
- Data structures: strings, lists, pub/sub
- Install Redis locally (free, pacman install on Arch)
- How Spring Boot connects to Redis
- Build: add Redis pub/sub so two server instances share messages

```bash
sudo pacman -S redis
sudo systemctl start redis
```

### Week 5-6 — Auth on WebSocket
- JWT refresh token flow
- How to authenticate a WebSocket connection (different from HTTP)
- Spring Security with WebSocket
- Build: only authenticated users can connect and send messages

### Week 7-8 — Persistence and History
- MongoDB schema design for messages
- Efficient querying — pagination of message history
- Indexing for performance
- Build: messages saved, history loads on room join

### Week 9-10 — Polish and Integration API
- REST endpoints for external integration
- Webhook support (notify external systems of new messages)
- Docker compose setup (Redis + MongoDB + your app in one command)
- Write clean README with integration guide

### Month 3 — Depth and Documentation
- Load test with JMeter (free)
- Measure how many concurrent connections you handle
- Fix bottlenecks
- Document architecture decisions and why you made them
- Write a short blog post about the hard problems you solved

---

## Project Structure

```
chat-engine/
├── src/
│   ├── config/
│   │   ├── WebSocketConfig.java
│   │   ├── RedisConfig.java
│   │   └── SecurityConfig.java
│   ├── controller/
│   │   ├── MessageController.java      # WebSocket handlers
│   │   └── RoomController.java         # REST API
│   ├── service/
│   │   ├── MessageService.java
│   │   ├── RedisPublisher.java
│   │   ├── RedisSubscriber.java
│   │   └── PresenceService.java        # online/offline tracking
│   ├── model/
│   │   ├── Message.java
│   │   ├── Room.java
│   │   └── User.java
│   └── repository/
│       ├── MessageRepository.java
│       └── RoomRepository.java
├── docker-compose.yml                  # Redis + MongoDB + App
└── README.md                           # Integration guide
```

---

## Month by Month Plan

### Month 1 — Core Working
- [ ] Spring Boot project setup
- [ ] WebSocket + STOMP config
- [ ] Single room chat working locally
- [ ] MongoDB message persistence
- [ ] JWT auth on WebSocket

**End of month 1:** A working single-server chat with auth and persistence.

### Month 2 — Distributed and Production Ready
- [ ] Redis Pub/Sub integration
- [ ] Multi-server message routing working
- [ ] Online presence tracking
- [ ] Read receipts
- [ ] REST API for external integration
- [ ] Docker compose setup

**End of month 2:** Runs on multiple instances, integrable by external apps.

### Month 3 — Polish and Story
- [ ] Load testing and benchmarks
- [ ] Webhook support
- [ ] Clean documentation
- [ ] Deploy on Railway free tier
- [ ] Write about what you built and why

**End of month 3:** Interview-ready, deployable, reusable infrastructure.

---

## DSA Parallel Track

Do this daily alongside building. 45 minutes minimum.

| Month | Topics |
|---|---|
| Month 1 | Arrays, strings, hashmaps |
| Month 2 | Trees, heaps, sliding window |
| Month 3 | Graphs, dynamic programming basics |

Platform: Leetcode free tier. Start easy, move to medium by month 2.

---

## The Interview Story

When asked "tell me about a project":

> "I built a real-time chat infrastructure in Java and Spring Boot designed to be integrated into any application via WebSocket and REST API. The interesting engineering problem was multi-server message routing — when users are connected to different server instances they can't communicate directly. I solved this using Redis Pub/Sub as a message broker between instances, which is the same pattern Slack and Discord use at scale. I load tested it and documented the architecture so other developers can integrate it into their own apps."

Every sentence maps to a real system design concept. Redis Pub/Sub, distributed messaging, WebSocket at scale, integration API design.

---

## Zero Cost Setup

```bash
# Install everything on Arch
sudo pacman -S redis mongodb-bin docker docker-compose jdk17-openjdk

# Start services
sudo systemctl start redis
sudo systemctl start mongodb
sudo systemctl enable redis
sudo systemctl enable mongodb
```

Docker Compose for development:
```yaml
version: '3.8'
services:
  redis:
    image: redis:alpine
    ports:
      - "6379:6379"
  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - redis
      - mongodb
```

---

## Future Integration Ideas

Once built, you can plug this into:

- Your existing course platform → add student/teacher chat
- A support ticket system → live support chat
- A team collaboration tool → channel-based messaging
- OpenContinuity (your Linux ecosystem idea) → messaging layer

The engine stays the same. Only the client changes.

---

## Resources (All Free)

- Spring WebSocket docs — docs.spring.io
- Baeldung Spring WebSocket — baeldung.com
- Redis University — university.redis.com (free courses)
- MongoDB University — learn.mongodb.com (free)
- System Design Primer — github.com/donnemartin/system-design-primer
- Leetcode free tier — leetcode.com

---

*Start date: ___________*
*Target completion: 3 months from start*
*GitHub repo: ___________*