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
