---
title: Nginx
tags:
  - devops
  - networking
  - webserver
  - reverse-proxy
difficulty: intermediate
status: learning
topic: nginx
date-time: 2026-06-27T08:28:00
time-taken(min):
---

# Nginx

## 1. Definition

Nginx (pronounced: *engine-x*) is a high-performance:

- Web server
- Reverse proxy
- Load balancer
- HTTP cache
- API gateway

It is event-driven and non-blocking, designed to handle high concurrency efficiently.

---

# 2. Why Nginx Exists

Traditional web servers (like Apache, in its prefork/worker MPM modes) use a **thread-per-request** model: every connection — even an idle keep-alive one — holds a live OS thread.

Problems:
- High memory usage (each thread costs real memory — often 1-8MB of stack)
- Poor scalability under high load (OS scheduler has to context-switch between thousands of mostly-idle threads, which isn't free)

Nginx uses:
- Event-driven architecture
- Asynchronous, non-blocking I/O
- Few worker processes (typically one per CPU core) handling thousands of connections concurrently

Result:
- Low memory footprint
- High concurrency
- Better performance under load

---

# 3. How This Actually Works Under the Hood

This is the part that's easy to get wrong, so it's worth being precise. The short version: **there is no thread per request, and there is no second thread for I/O either.** One worker thread per core does everything, and it never blocks.

### 3.1 The event loop, concretely

Each worker process is, by default, a **single OS thread** running an infinite loop:

```
while (true) {
    ready_list = epoll_wait();      // ask kernel: which fds are ready?
    for each fd in ready_list {
        do the actual work for that fd (read bytes, write bytes, etc.)
    }
}
```

- `epoll` (Linux-specific; BSD uses `kqueue`, Windows uses IOCP — same idea, different API) is a kernel facility. The worker registers file descriptors (sockets, and some file ops) with it, saying "tell me when any of these are readable, writable, or erroring."
- `epoll_wait()` blocks cheaply (no CPU burned) until the kernel has something to report. This is the *only* place anything "waits," and it's waiting on potentially thousands of connections at once, not one.
- When fds are ready, the worker loops through them and does a little work on each — never doing enough on any single one to block the others.

This single thread is what each "worker" is. It is **not** "like a thread per request" — it's one thread supervising thousands of connections via epoll.

### 3.2 What happens during non-blocking I/O (no second thread is created)

A common misconception (worth naming because it's the natural next question): when nginx does a non-blocking disk read or waits on an upstream response, is *that* running on some other thread that the CPU switches to?

**No.** Here's the actual sequence:

1. Worker calls `read()` (or equivalent) on a socket/file that has no data yet.
2. Instead of blocking, the kernel immediately returns `EWOULDBLOCK`/`EAGAIN` — "nothing ready, here's control back." No thread was created, none was suspended.
3. The worker's own code registers interest with epoll for that fd and moves on to the next ready connection in its loop.
4. Meanwhile, the actual data movement (e.g., disk → RAM) is done by **hardware**, specifically the disk controller via **DMA (Direct Memory Access)** — copying bytes without any CPU core executing instructions for the transfer. No core is "running" the read while it happens.
5. When the transfer finishes, the controller raises a **hardware interrupt**.
6. Whichever CPU core the interrupt is routed to briefly stops what it's doing and runs the kernel's interrupt handler — a few hundred instructions, microseconds, running on a small **per-CPU interrupt stack** (not a thread's stack, not a scheduled entity — just code the hardware forces the core to jump to). This handler flips the readiness bit for that fd in epoll's internal state.
7. The interrupted thread resumes exactly where it left off, unaware time passed.
8. The worker's *next* pass through `epoll_wait()` sees the fd is now ready and picks the request back up — using a small in-memory state object (a few KB) that remembers where this request was in its lifecycle. This state object, not a thread, is "the thing" associated with a paused request.

So across the whole I/O wait: a CPU core is touched only briefly at the start (issuing the syscall) and briefly at the end (the interrupt handler). The actual waiting period costs zero CPU — that's the entire point of DMA + interrupts.

### 3.3 Why this beats thread-per-request

With 10,000 idle keep-alive connections under the old model: 10,000 idle threads, each costing memory, each a candidate for OS scheduling overhead even while doing nothing.

With nginx: ~8 worker threads (one per core), and epoll tells each worker instantly which of its connections actually need attention right now. No per-connection thread. No wasted context switches on idle work. The OS scheduler barely has to think about nginx — it's juggling 8 threads, not 10,000.

### 3.4 The one exception: nginx's internal thread pool

Not everything fits the non-blocking model cleanly. Certain operations — some disk file reads, `sendfile` edge cases — can't always be made non-blocking on every OS/filesystem combination. For these, nginx workers can dispatch the operation to a small **internal thread pool**, specifically so a genuinely-blocking syscall doesn't stall the whole event loop. This is an optimization for specific cases, not the general request-handling model — the event loop is still the core idea.

### 3.5 "Multilevel" switching — the honest summary

There are two distinct levels of "switching" happening, and they're easy to conflate:

- **Kernel/hardware level:** interrupts force a core to briefly run a handler, unconditionally, whenever hardware (disk, NIC) finishes something. This is not a scheduled decision and the interrupted code never knows it happened.
- **Worker-loop level:** the worker itself decides which *ready* fd to process next, in its own loop, driven by what epoll reports. This is nginx's own logic, not OS thread scheduling.

The OS thread scheduler is barely involved at all once nginx is running under load — almost all the "deciding what to do next" happens inside the worker's loop and inside epoll, not via classic thread context switches.

---

# 4. Architecture

## Master Process
- Reads config
- Manages worker processes
- Doesn't handle requests itself

## Worker Processes
- Handle requests
- Event-driven, non-blocking
- One per CPU core (configurable via `worker_processes`)
- Model: event loop + epoll (Linux)

---

# 5. Nginx Configuration Structure

Main config file:

```
/etc/nginx/nginx.conf
```

Basic structure:

```nginx
events {
    worker_connections 1024;
}

http {
    server {
        listen 80;
        server_name example.com;

        location / {
            root /var/www/html;
            index index.html;
        }
    }
}
```

**Sizing note:** max concurrent connections nginx can hold = `worker_processes × worker_connections`. If you set `worker_processes 4` and `worker_connections 1024`, that's a ceiling of ~4096 concurrent connections (slightly less in practice, since proxying to an upstream uses up a second connection per request). This is the number you'd actually reason about if asked "how would you size nginx for 50k concurrent connections" — answer: scale `worker_connections` up, check the OS file descriptor limit (see §11.4), and confirm `worker_processes` matches core count.

---

# 6. Important Directives

## 1. server

Defines a virtual server.

```nginx
server {
    listen 80;
    server_name example.com;
}
```

## 2. location

Handles URL routing.

```nginx
location /api {
    proxy_pass http://localhost:8080;
}
```

**Matching precedence (not in the original notes, and a common gotcha):** nginx does not match `location` blocks top-to-bottom in the order written. The actual order is:

1. Exact match (`location = /path`) — highest priority, stops searching immediately.
2. Prefix match with `^~` — if this matches, regex locations are skipped.
3. Regex match (`location ~ /path` case-sensitive, or `~* ` case-insensitive) — checked in the order they appear in the config, first match wins.
4. Plain prefix match (`location /path`) — longest matching prefix wins.

This matters because a regex location can unexpectedly "steal" traffic from what looks like a more specific plain-prefix location, and `/api` vs `/api/` (trailing slash) can behave differently depending on how `proxy_pass` is written underneath it.

## 3. proxy_pass

For reverse proxying.

```nginx
proxy_pass http://localhost:8080;
```

## 4. root

Static file directory.

```nginx
root /var/www/html;
```

## 5. try_files

Used in SPAs (React apps).

```nginx
try_files $uri /index.html;
```

Prevents 404 when refreshing client-side routes.

---

# 7. Reverse Proxy with Spring Boot

```nginx
server {
    listen 80;
    server_name myapp.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

This forwards traffic to Spring Boot running on port 8080.

### 7.1 Keepalive to the backend (gap in original notes)

By default, nginx opens a **new** connection to your Spring Boot backend for every request, even if the client's connection to nginx is kept alive. Under load this means constant TCP handshake overhead between nginx and your app server. Fix with an `upstream` block plus explicit keepalive:

```nginx
upstream backend {
    server 127.0.0.1:8080;
    keepalive 32;   # keep up to 32 idle connections open to the backend
}

server {
    location / {
        proxy_pass http://backend;
        proxy_http_version 1.1;
        proxy_set_header Connection "";   # clears the default "close" header
    }
}
```

Directly relevant to anything you put behind nginx — CodeDash, the College Identity System — since Spring Boot's own thread-per-request model (Tomcat by default) means each unnecessary new connection costs a thread on *that* side too.

### 7.2 Buffering (mentioned in original notes, never explained)

`proxy_buffering on` (the default) means nginx reads the *entire* backend response into its own buffer before sending anything to the client. This decouples a slow client from a fast backend (the backend can finish and move on; nginx handles drip-feeding the slow client). Turning it `off` means nginx streams the response straight through as it arrives — lower latency for the first byte, but a slow client now ties up the backend connection for longer. Worth experimenting with directly (see §12) rather than just reading about it.

### 7.3 WebSocket proxying (missing from original notes entirely)

Plain `proxy_pass` does not forward WebSocket upgrade requests correctly by default — it silently breaks. Needs explicit headers:

```nginx
location /ws {
    proxy_pass http://backend;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";
}
```

Worth knowing now even if you don't need it yet — if CodeDash or the College Identity System ever grows a real-time feature (live leaderboard updates, notifications), this is the line that gets forgotten and breaks things silently.

---

# 8. Load Balancing

```nginx
http {
    upstream backend {
        server 127.0.0.1:8080;
        server 127.0.0.1:8081;
    }

    server {
        listen 80;

        location / {
            proxy_pass http://backend;
        }
    }
}
```

Algorithms:
- **Round Robin** (default) — requests distributed evenly in order
- **Least connections** (`least_conn;`) — send to whichever backend currently has fewest active connections
- **IP hash** (`ip_hash;`) — same client IP always routed to the same backend (useful for session stickiness without a shared session store)

### 8.1 Upstream health / failure handling (missing from original notes)

```nginx
upstream backend {
    server 127.0.0.1:8080 max_fails=3 fail_timeout=30s;
    server 127.0.0.1:8081 max_fails=3 fail_timeout=30s;
}
```

`max_fails` + `fail_timeout` control when nginx temporarily stops sending traffic to a backend that's failing — without this, nginx will keep routing requests to a dead backend and clients will see errors until you manually intervene. Directly relevant the moment you run more than one instance of a Spring Boot app behind nginx.

---

# 9. SSL Termination

```
Client (HTTPS)
        ↓
Nginx handles SSL
        ↓
Backend (HTTP internally)
```

Benefits:
- Easier certificate management (one place to renew/configure certs, not every backend)
- Cleaner backend config (Spring Boot doesn't need its own SSL setup)
- Industry standard

---

# 10. Common Production Setup (Backend Developer Perspective)

```
Internet
   ↓
Nginx (SSL, Rate limit, Logging)
   ↓
Spring Boot (REST API)
   ↓
PostgreSQL / MongoDB
```

---

# 11. Performance & Resource Concepts

- **Worker processes** — one per CPU core, by default.
- **Worker connections** — max simultaneous connections *per worker* (see §5 sizing note).
- **Keepalive** — reusing a TCP connection for multiple requests instead of reconnecting each time. Relevant both client↔nginx and nginx↔backend (§7.1).
- **Gzip compression** — `gzip on;` — compresses responses before sending, trades a little CPU for less bandwidth. Original notes mentioned this with no config; minimal example:

```nginx
gzip on;
gzip_types text/plain application/json text/css application/javascript;
gzip_min_length 1024;
```

- **Caching** — nginx can cache upstream responses (`proxy_cache`) so repeated requests don't hit the backend at all.
- **Buffering** — see §7.2.

### 11.1 File descriptor limits (missing from original notes — this is the actual ceiling)

Every connection a worker holds open consumes one file descriptor. The OS caps how many fds a process can have open (`ulimit -n`). At real scale, **this is the actual hard ceiling**, not CPU or threads — you can have a perfectly efficient event loop and still fail to accept new connections if you hit the fd limit. Relevant directive:

```nginx
worker_rlimit_nofile 65535;
```

(needs to be paired with the OS-level `ulimit` actually allowing that many).

---

# 12. Security Features

- Rate limiting
- IP blocking
- Request size limiting
- Basic auth
- SSL termination
- Hide backend ports

Example:

```nginx
limit_req_zone $binary_remote_addr zone=mylimit:10m rate=5r/s;
```

---

# 13. Logs

Access log: `/var/log/nginx/access.log`
Error log: `/var/log/nginx/error.log`

---

# 14. Commands

```
sudo nginx -t                    # test config
sudo systemctl reload nginx      # reload config (no downtime)
sudo systemctl restart nginx     # full restart
sudo systemctl status nginx
sudo ss -tulpn | grep 80         # check what's listening on port 80
```

---

# 15. Interview-Level Questions

- Difference between Apache and Nginx? (thread-per-request vs event loop + epoll — see §3)
- What is a reverse proxy?
- **How does Nginx handle concurrency without spawning a thread per request?** (epoll + non-blocking I/O + DMA/interrupts for the actual I/O — see §3 for the full mechanism, this is worth being able to explain at the level of detail in that section)
- What is SSL termination?
- How do you configure load balancing, and what happens when a backend dies? (§8.1)
- How do you deploy Spring Boot behind Nginx, and what should you watch out for? (§7.1 keepalive, §7.3 WebSockets)
- What's the actual ceiling on concurrent connections nginx can handle? (`worker_processes × worker_connections`, bounded by OS file descriptor limits — §11.1)

---

# 16. Key Takeaways

- Nginx is primarily a reverse proxy in backend systems.
- Event-driven architecture (single-threaded event loop per worker + epoll) is *why* it scales — not "more threads," but deliberately avoiding threads for connection handling.
- Non-blocking I/O means the worker thread is never the thing waiting — hardware (DMA) does the actual data movement, interrupts signal completion, and the worker only ever touches ready work.
- Used for: SSL, load balancing, static serving, security layer.
- Almost every production backend uses Nginx or something architecturally similar (HAProxy, Envoy).

---

# 17. Media Server Setup (HLS Streaming) — Homelab Build

*Note: this section documents a setup that existed at the time of writing but is not currently running (no active Tailscale or Nginx media server as of this note). Kept here as a reference for re-deploying later.*

## 17.1 What's Different Here

In this setup, Nginx is used **only as a static media file server** for HLS streaming — no reverse proxy, no backend involvement in the streaming path itself.

## 17.2 Architecture

```
Client Device
      ↓
Tailscale (Private Network)
      ↓
100.x.x.x (Media Server)
      ↓
Nginx
      ↓
/srv/videos (HLS files)
```

No public IP, no port forwarding, no reverse proxy, no backend involved in streaming. Direct: **Browser → Nginx**.

## 17.3 Directory Structure

```
/srv/videos/
    solo_leveling_s2_ep1/
        playlist.m3u8
        segment_000.ts
        segment_001.ts
```

Naming rules: no spaces, no special characters, use slugs (e.g. `solo-leveling-s2-ep1`).

## 17.4 Nginx Config for HLS

Edit `/etc/nginx/nginx.conf`, inside `http {}`:

```nginx
http {
    include       mime.types;
    default_type  application/octet-stream;

    types_hash_max_size 2048;
    types_hash_bucket_size 128;

    sendfile on;
    keepalive_timeout 65;

    server {
        listen 80;

        location /videos/ {
            root /srv;

            types {
                application/vnd.apple.mpegurl m3u8;
                video/mp2t ts;
            }

            add_header Cache-Control no-cache;
        }
    }
}
```

Mapping: `URL: /videos/movie/playlist.m3u8` → `/srv/videos/movie/playlist.m3u8`

## 17.5 Commands

```
sudo nginx -t
sudo systemctl reload nginx
sudo systemctl status nginx
sudo ss -tulpn | grep 80    # must show binding to 0.0.0.0:80
```

## 17.6 Common Errors

**403 Forbidden** — fix permissions:
```
sudo chmod -R 755 /srv/videos
sudo chown -R http:http /srv/videos    # Arch uses the 'http' user
```

**MIME warning** (`could not build optimal types_hash`) — fix:
```nginx
types_hash_max_size 2048;
types_hash_bucket_size 128;
```

---

# 18. FFmpeg — Converting Video to HLS (VOD)

## 18.1 What is HLS?

HLS = HTTP Live Streaming. Converts one video into `playlist.m3u8` + multiple `.ts` segments. Browser downloads segments sequentially.

## 18.2 VOD vs Live

- **VOD**: full playlist, fixed content, used for OTT. Flag: `-hls_playlist_type vod`
- **Live**: playlist updates continuously, limited segment window.

## 18.3 Why Re-Encoding Is Required

If input is e.g. AV1 video / Opus audio, HLS with `.ts` segments needs H.264 video + AAC audio — hence re-encode.

## 18.4 Conversion Command

```bash
INPUT="video.mkv"
NAME="solo_leveling_s2_ep1"

mkdir -p /srv/videos/$NAME

ffmpeg -i "$INPUT" \
  -c:v libx264 -preset veryfast -crf 23 \
  -c:a aac -b:a 128k \
  -hls_time 6 \
  -hls_playlist_type vod \
  -hls_segment_filename "/srv/videos/$NAME/segment_%03d.ts" \
  "/srv/videos/$NAME/playlist.m3u8"
```

| Flag | Meaning |
|------|----------|
| `-c:v libx264` | Encode to H.264 |
| `-crf 23` | Quality control (lower = better quality) |
| `-c:a aac` | Browser-compatible audio |
| `-hls_time 6` | 6-second segments |
| `-hls_playlist_type vod` | Full playlist |
| `segment_%03d.ts` | Clean numbering |

Output:
```
/srv/videos/solo_leveling_s2_ep1/
    playlist.m3u8
    segment_000.ts
    segment_001.ts
```

Accessible via: `http://MEDIA_IP/videos/solo_leveling_s2_ep1/playlist.m3u8`

---

# 19. Tailscale — Private Network Over Internet

## 19.1 What is Tailscale?

Creates a private encrypted network using WireGuard. Each device gets an address like `100.x.x.x`, working globally without port forwarding, public IP, or router config.

## 19.2 Why It Was Used Here

LAN IP was `10.x.x.x`, likely behind CG-NAT, making port forwarding unreliable. Tailscale bypasses CG-NAT entirely.

## 19.3 Installation (Arch Linux)

```bash
sudo pacman -S tailscale
sudo systemctl enable --now tailscaled
sudo tailscale up
```

Login via browser. Check IP: `tailscale ip -4` (e.g. `100.76.235.37`).

## 19.4 Multi-Device Setup

All devices must install Tailscale and log into the same account (or be invited to it) to communicate via `http://100.x.x.x/...`.

## 19.5 Testing Connectivity

```bash
ping 100.76.235.37
# if that works:
http://100.76.235.37/videos/...
```

## 19.6 Tailnet Concept

Same account = same private network. Different account = isolated network. To bridge: invite the other user via the Tailscale admin panel.

---

# 20. Final System Summary (Homelab)

Built:
- Media server node (Nginx, static-file-only mode)
- Automated HLS pipeline (FFmpeg)
- Private distributed network (Tailscale)
- Direct browser streaming (HLS VOD)

```
Client
   ↓
Tailscale (100.x.x.x)
   ↓
Nginx
   ↓
/srv/videos
   ↓
playlist.m3u8 → segments
```

Backend is **not** involved in streaming. Status as of this note: **not currently running** — kept as a reference for re-deployment.

---

# 21. Next Topics to Learn

Carried over from original notes, reordered by relevance to current priorities (Juspay prep, CodeDash/College Identity System deployment):

**Higher relevance right now:**
- Nginx + Docker (containerizing a reverse-proxy setup — directly applicable to deploying CodeDash/College Identity System)
- Rate limiting configuration in practice (you have the directive, haven't configured it for real)
- Nginx as API Gateway (relevant to system-design interview answers)

**Lower priority / later:**
- SSL with Let's Encrypt (only needed once you have a real domain pointed at something)
- Caching strategy (`proxy_cache` in practice)
- HTTP/2 support
- Nginx vs HAProxy (good to know the distinction exists, low depth needed)
- Nginx + Kubernetes ingress
- Adaptive bitrate HLS (360p/720p/1080p), AES-128 encryption, token-based protected streaming, HTTPS via Tailscale Funnel, CDN integration — all homelab extensions, only relevant if you resume that project

**Conceptual, satisfied by this note already:**
- ~~How nginx handles concurrency internally~~ — covered in full in §3, including epoll, non-blocking I/O, DMA, and interrupts. This was worked through and verified in conversation; safe to consider this topic closed at the depth needed for interviews.