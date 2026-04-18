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
date-time: INVALID_DATE_TIME
time-taken(min): INVALID_DURATION
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

Traditional web servers (like Apache) use **thread-per-request** model.

Problems:
- High memory usage
- Poor scalability under high load

Nginx uses:
- Event-driven architecture
- Asynchronous I/O
- Few worker processes handling thousands of connections

Result:
- Low memory footprint
- High concurrency
- Better performance under load

---

# 3. Where Nginx Is Used

### 1. Static File Serving
Serving:
- HTML
- CSS
- JS
- Images
- Downloads

Very fast due to optimized file handling.

---

### 2. Reverse Proxy

Most common backend usage.

Client → Nginx → Backend (Spring Boot / Node / etc.)

Example:
- User requests `/api`
- Nginx forwards to `localhost:8080`

Benefits:
- Hide backend server
- Centralized SSL
- Security layer
- Logging
- Rate limiting

---

### 3. Load Balancer

Distributes traffic:

Client → Nginx → Multiple backend servers

Algorithms:
- Round Robin (default)
- Least connections
- IP hash

Used in production systems.

---

### 4. SSL Termination

Instead of configuring HTTPS in Spring Boot:

Client (HTTPS)
        ↓
Nginx handles SSL
        ↓
Backend (HTTP internally)

Benefits:
- Easier certificate management
- Cleaner backend config
- Industry standard

---

# 4. Architecture

## Master Process
- Reads config
- Manages worker processes

## Worker Processes
- Handle requests
- Event-driven
- Non-blocking

Model:
Event loop + epoll (Linux)

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
````

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

---

## 2. location

Handles URL routing.

```nginx
location /api {
    proxy_pass http://localhost:8080;
}
```

---

## 3. proxy_pass

For reverse proxying.

```nginx
proxy_pass http://localhost:8080;
```

---

## 4. root

Static file directory.

```nginx
root /var/www/html;
```

---

## 5. try_files

Used in SPA (React apps).

```nginx
try_files $uri /index.html;
```

Prevents 404 when refreshing client-side routes.

---

# 7. Reverse Proxy with Spring Boot

Example:

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

---

# 8. Load Balancing Example

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

---

# 9. Common Production Setup (Backend Developer Perspective)

Internet
↓
Nginx (SSL, Rate limit, Logging)
↓
Spring Boot (REST API)
↓
MongoDB

---

# 10. Performance Concepts

* Worker processes
* Worker connections
* Keepalive
* Gzip compression
* Caching
* Buffering

---

# 11. Security Features

* Rate limiting
* IP blocking
* Request size limiting
* Basic auth
* SSL termination
* Hide backend ports

Example:

```nginx
limit_req_zone $binary_remote_addr zone=mylimit:10m rate=5r/s;
```

---

# 12. Logs

Access log:

```
/var/log/nginx/access.log
```

Error log:

```
/var/log/nginx/error.log
```

---

# 13. Commands

Test config:

```
sudo nginx -t
```

Reload config:

```
sudo systemctl reload nginx
```

Restart:

```
sudo systemctl restart nginx
```

---

# 14. Interview-Level Questions

* Difference between Apache and Nginx?
* What is reverse proxy?
* How does Nginx handle concurrency?
* What is SSL termination?
* How do you configure load balancing?
* How do you deploy Spring Boot behind Nginx?

---

# 15. Key Takeaways

* Nginx is primarily a reverse proxy in backend systems.
* Event-driven architecture makes it scalable.
* Used for:

  * SSL
  * Load balancing
  * Static serving
  * Security layer
* Almost every production backend uses Nginx or similar.

---

# 16. Next Topics to Learn

* SSL with Let's Encrypt
* Nginx + Docker
* Rate limiting configuration
* Caching strategy
* HTTP/2 support
* Nginx as API Gateway
* Nginx vs HAProxy
* Nginx + Kubernetes ingress

# Nginx – Media Server Setup (HLS Streaming)

## 1. What is Nginx?

Nginx is a high-performance:

- Web server
- Reverse proxy
- Load balancer
- Static file server

In this setup, we are using Nginx **only as a static media server** for HLS streaming.

---

## 2. Architecture Used

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

No:
- Public IP
- Port forwarding
- Reverse proxy
- Backend involvement in streaming

Streaming is direct: **Browser → Nginx**

---

## 3. Directory Structure

Media root:

```
/srv/videos/
```

Each video has its own folder:

```
/srv/videos/
    solo_leveling_s2_ep1/
        playlist.m3u8
        segment_000.ts
        segment_001.ts
```

Clean naming rules:

- No spaces
- No special characters
- Use slugs:
  - `solo-leveling-s2-ep1`
  - `naruto-shippuden-ep420`

---

## 4. Nginx Configuration for HLS

Edit:

```
/etc/nginx/nginx.conf
```

Inside `http {}`:

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

Mapping logic:

```
URL:     /videos/movie/playlist.m3u8
Maps to: /srv/videos/movie/playlist.m3u8
```

---

## 5. Nginx Commands

Test config:

```
sudo nginx -t
```

Reload:

```
sudo systemctl reload nginx
```

Check status:

```
sudo systemctl status nginx
```

Check listening port:

```
sudo ss -tulpn | grep 80
```

It must bind to:

```
0.0.0.0:80
```

---

## 6. Common Errors

### 403 Forbidden

Fix permissions:

```
sudo chmod -R 755 /srv/videos
sudo chown -R http:http /srv/videos
```

(Arch uses `http` user)

---

### MIME Warning

```
could not build optimal types_hash
```

Fix with:

```
types_hash_max_size 2048;
types_hash_bucket_size 128;
```

---

---

# FFmpeg – Converting Video to HLS (VOD)

## 1. What is HLS?

HLS = HTTP Live Streaming

It converts one video into:

- `playlist.m3u8` (playlist file)
- Multiple `.ts` segments (small chunks)

Browser downloads small segments sequentially.

---

## 2. VOD vs Live

### VOD (Video on Demand)
- Full playlist
- Fixed content
- Used for OTT

### Live
- Playlist updates continuously
- Limited segment window

We use:

```
-hls_playlist_type vod
```

---

## 3. Why Re-Encoding Is Required

Input file example:

- Video: AV1
- Audio: Opus

HLS with `.ts` requires:

- Video → H.264
- Audio → AAC

Therefore re-encode.

---

## 4. Clean HLS Conversion Command

```
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

---

## 5. Important Flags

| Flag | Meaning |
|------|----------|
| `-c:v libx264` | Encode to H.264 |
| `-crf 23` | Quality control (lower = better quality) |
| `-c:a aac` | Browser compatible audio |
| `-hls_time 6` | 6-second segments |
| `-hls_playlist_type vod` | Full playlist |
| `segment_%03d.ts` | Clean numbering |

---

## 6. Resulting Output

```
/srv/videos/solo_leveling_s2_ep1/
    playlist.m3u8
    segment_000.ts
    segment_001.ts
```

Accessible via:

```
http://MEDIA_IP/videos/solo_leveling_s2_ep1/playlist.m3u8
```

---

---

# Tailscale – Private Network Over Internet

## 1. What is Tailscale?

Tailscale creates a private encrypted network using WireGuard.

Each device gets:

```
100.x.x.x
```

This works globally without:

- Port forwarding
- Public IP
- Router config

---

## 2. Why We Used It

Your LAN IP:

```
10.x.x.x
```

Likely under CG-NAT.

Port forwarding unreliable.

Tailscale bypasses CG-NAT completely.

---

## 3. Installation (Arch Linux)

```
sudo pacman -S tailscale
sudo systemctl enable --now tailscaled
sudo tailscale up
```

Login via browser.

Check IP:

```
tailscale ip -4
```

Example:

```
100.76.235.37
```

---

## 4. Multi-Device Setup

All devices must:

- Install Tailscale
- Login to same account (or invited account)

Then they can communicate via:

```
http://100.x.x.x/...
```

---

## 5. Testing Connectivity

From client:

```
ping 100.76.235.37
```

If ping works:

```
http://100.76.235.37/videos/...
```

---

## 6. Tailnet Concept

Same account → same private network  
Different account → different isolated network  

To allow different accounts:

- Invite user via Tailscale admin panel.

---

---

# Final System Summary

You Built:

- Media server node (Nginx)
- Automated HLS pipeline (FFmpeg)
- Private distributed network (Tailscale)
- Direct browser streaming (HLS VOD)

Final streaming path:

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

Backend is NOT involved in streaming.

This is correct distributed media architecture for a small OTT experiment.

---

# Future Extensions

- Adaptive bitrate HLS (360p / 720p / 1080p)
- AES-128 encryption
- Token-based protected streaming
- Nginx caching optimization
- HTTPS via Tailscale Funnel
- CDN integration