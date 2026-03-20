# 🌐 curl — Complete Reference Guide

> [!abstract] Overview
> `curl` (**C**lient **URL**) is a command-line tool for transferring data using various protocols. It supports HTTP, HTTPS, FTP, FTPS, SFTP, SMTP, and more. Built-in to most Linux/macOS systems. Essential for API testing, automation, and scripting.

**Tags:** #linux #networking #cli #tools #http #api

---

## 📦 Installation & Version

```bash
# Check if installed
curl --version

# Install (Arch Linux)
sudo pacman -S curl

# Install (Debian/Ubuntu)
sudo apt install curl
```

---

## 🧠 Mental Model

```
curl [OPTIONS] [URL]
```

Think of curl as a **programmable browser** for your terminal.
- You craft the **request** (method, headers, body)
- curl **sends it** over the network
- It **shows you the response**

---

## 🔰 Basic Usage

### Simple GET Request

```bash
# Fetch a webpage
curl https://example.com

# Save output to a file
curl -o output.html https://example.com

# Save with the remote filename
curl -O https://example.com/file.zip

# Fetch silently (no progress)
curl -s https://api.example.com/data
```

### Follow Redirects

```bash
# -L follows HTTP redirects (301, 302, etc.)
curl -L https://short.url/abc
```

---

## 📡 HTTP Methods

### GET

```bash
curl https://api.example.com/users
```

### POST

```bash
# Send form data
curl -X POST -d "name=Mohamed&role=dev" https://api.example.com/users

# Send JSON body
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"name": "Mohamed", "role": "dev"}' \
  https://api.example.com/users
```

### PUT

```bash
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{"name": "Updated Name"}' \
  https://api.example.com/users/1
```

### PATCH

```bash
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"role": "senior-dev"}' \
  https://api.example.com/users/1
```

### DELETE

```bash
curl -X DELETE https://api.example.com/users/1
```

---

## 🏷️ Headers

```bash
# Add a single header
curl -H "Authorization: Bearer <token>" https://api.example.com/protected

# Add multiple headers
curl \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -H "X-Custom-Header: value" \
  https://api.example.com/data

# View response headers only
curl -I https://example.com

# View both request and response headers (verbose)
curl -v https://example.com
```

---

## 🔐 Authentication

### Basic Auth

```bash
# -u username:password
curl -u admin:password123 https://api.example.com/admin

# Prompt for password (don't expose in shell history)
curl -u admin https://api.example.com/admin
```

### Bearer Token (JWT)

```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI..." \
  https://api.example.com/profile
```

### API Key in Header

```bash
curl -H "X-API-Key: your_api_key_here" \
  https://api.example.com/data
```

### API Key as Query Param

```bash
curl "https://api.example.com/data?api_key=your_api_key"
```

---

## 📤 Sending Data

### URL-encoded Form Data

```bash
curl -X POST \
  -d "username=faisal&password=secret" \
  https://example.com/login

# Equivalent with --data-urlencode (handles special chars)
curl -X POST \
  --data-urlencode "username=faisal@email.com" \
  --data-urlencode "password=p@ssw0rd!" \
  https://example.com/login
```

### JSON Body

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"title": "Inception", "genre": "Sci-Fi"}' \
  https://api.ottplatform.com/movies
```

### Send from File

```bash
# Read request body from file
curl -X POST \
  -H "Content-Type: application/json" \
  -d @payload.json \
  https://api.example.com/movies
```

### Multipart Form / File Upload

```bash
# Upload a file
curl -X POST \
  -F "file=@/path/to/movie.mp4" \
  -F "title=Inception" \
  https://api.ottplatform.com/upload

# Force multipart
curl -X POST --form "key=value" --form "file=@photo.jpg" https://example.com
```

---

## 🍪 Cookies

```bash
# Send a cookie
curl -b "session=abc123; theme=dark" https://example.com

# Save cookies to file
curl -c cookies.txt https://example.com/login

# Load cookies from file
curl -b cookies.txt https://example.com/dashboard

# Full login session flow
curl -c cookies.txt -X POST -d "user=admin&pass=secret" https://example.com/login
curl -b cookies.txt https://example.com/dashboard
```

---

## 📥 Output & Download

```bash
# Save to specific filename
curl -o myfile.json https://api.example.com/data

# Use remote filename
curl -O https://example.com/archive.tar.gz

# Download multiple files
curl -O https://example.com/file1.zip \
     -O https://example.com/file2.zip

# Resume interrupted download
curl -C - -O https://example.com/bigfile.iso

# Limit download speed (bytes per second)
curl --limit-rate 500K -O https://example.com/bigfile.iso

# Show download progress bar
curl --progress-bar -O https://example.com/file.zip
```

---

## 🔍 Debugging & Verbose Output

```bash
# Full verbose (request + response headers + body)
curl -v https://example.com

# Even more verbose (SSL/TLS info)
curl --trace - https://example.com

# Only response headers
curl -I https://example.com

# Show timing info
curl -w "\nTime: %{time_total}s\nHTTP: %{http_code}\n" \
     -o /dev/null -s https://example.com

# Write-out format variables
curl -w "
  DNS:        %{time_namelookup}s
  Connect:    %{time_connect}s
  TLS:        %{time_appconnect}s
  TTFB:       %{time_starttransfer}s
  Total:      %{time_total}s
  HTTP Code:  %{http_code}
  Bytes:      %{size_download}
" -o /dev/null -s https://example.com
```

---

## 🔒 SSL / TLS

```bash
# Skip SSL certificate verification (INSECURE - dev only)
curl -k https://self-signed.example.com
curl --insecure https://self-signed.example.com

# Use a specific CA bundle
curl --cacert /path/to/ca-bundle.crt https://example.com

# Use client certificate (mutual TLS)
curl --cert client.crt --key client.key https://secure.example.com

# Show certificate info
curl -vI https://example.com 2>&1 | grep -A5 "Server certificate"
```

---

## 🌐 Proxy

```bash
# Use HTTP proxy
curl -x http://proxy.example.com:8080 https://target.com

# Proxy with auth
curl -x http://proxy.example.com:8080 \
     -U proxyuser:proxypass \
     https://target.com

# SOCKS5 proxy (e.g., Tor, SSH tunnel)
curl --socks5 127.0.0.1:9050 https://check.torproject.org

# Bypass proxy for specific hosts
curl --noproxy "localhost,127.0.0.1" https://internal.service
```

---

## ⚙️ Connection Options

```bash
# Set connection timeout (seconds)
curl --connect-timeout 10 https://example.com

# Set max total time
curl --max-time 30 https://example.com

# Retry on failure
curl --retry 3 --retry-delay 2 https://example.com

# Retry on transient errors only
curl --retry 5 --retry-all-errors https://example.com

# Use specific network interface
curl --interface eth0 https://example.com

# Force IPv4 or IPv6
curl -4 https://example.com   # IPv4 only
curl -6 https://example.com   # IPv6 only
```

---

## 🌊 HTTP Versions

```bash
# Force HTTP/1.1
curl --http1.1 https://example.com

# Force HTTP/2
curl --http2 https://example.com

# Force HTTP/3 (if supported)
curl --http3 https://example.com

# Check which version was used
curl -v https://example.com 2>&1 | grep "< HTTP"
```

---

## 📚 REST API Cheatsheet

### Full CRUD Example (OTT Platform Context)

```bash
# CREATE — Add a movie
curl -X POST https://api.ottplatform.com/api/movies \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "title": "Interstellar",
    "genre": "Sci-Fi",
    "year": 2014
  }'

# READ — Get all movies
curl https://api.ottplatform.com/api/movies \
  -H "Authorization: Bearer $TOKEN"

# READ — Get one movie
curl https://api.ottplatform.com/api/movies/42 \
  -H "Authorization: Bearer $TOKEN"

# UPDATE — Full replace
curl -X PUT https://api.ottplatform.com/api/movies/42 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"title": "Interstellar", "genre": "Sci-Fi", "year": 2014, "rating": 8.6}'

# PATCH — Partial update
curl -X PATCH https://api.ottplatform.com/api/movies/42 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"rating": 8.8}'

# DELETE
curl -X DELETE https://api.ottplatform.com/api/movies/42 \
  -H "Authorization: Bearer $TOKEN"
```

---

## 🧪 Testing Spring Boot Endpoints

> [!tip] Spring Boot Dev Tip
> Always test your endpoints with curl before writing frontend code.

```bash
# Test health endpoint
curl http://localhost:8080/actuator/health

# Test with JSON pretty print (pipe to jq)
curl -s http://localhost:8080/api/movies | jq .

# Send multipart (file upload endpoint)
curl -X POST http://localhost:8080/api/upload \
  -F "file=@movie.mp4" \
  -F "metadata={\"title\":\"Test\"};type=application/json"

# Test paginated endpoint
curl "http://localhost:8080/api/movies?page=0&size=10&sort=title,asc"

# Login and capture JWT
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"secret"}' \
  | jq -r '.token')

# Use captured token
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/protected
```

---

## 🔗 Useful Flags Quick Reference

| Flag | Long form | Description |
|------|-----------|-------------|
| `-X` | `--request` | HTTP method (GET, POST, etc.) |
| `-H` | `--header` | Add request header |
| `-d` | `--data` | Request body / POST data |
| `-o` | `--output` | Save to file |
| `-O` | `--remote-name` | Save with remote filename |
| `-L` | `--location` | Follow redirects |
| `-I` | `--head` | Fetch headers only |
| `-v` | `--verbose` | Verbose output |
| `-s` | `--silent` | No progress/errors |
| `-S` | `--show-error` | Show errors even with `-s` |
| `-u` | `--user` | Basic auth user:pass |
| `-b` | `--cookie` | Send cookie |
| `-c` | `--cookie-jar` | Save cookies to file |
| `-F` | `--form` | Multipart form data |
| `-k` | `--insecure` | Skip SSL verification |
| `-x` | `--proxy` | Use a proxy |
| `-C` | `--continue-at` | Resume download |
| `-w` | `--write-out` | Custom output format |
| `-e` | `--referer` | Set Referer header |
| `-A` | `--user-agent` | Set User-Agent |
| `-T` | `--upload-file` | Upload file (PUT) |
| `--http2` | — | Force HTTP/2 |
| `--retry` | — | Retry on failure |
| `--limit-rate` | — | Limit transfer speed |
| `--max-time` | — | Max total time (seconds) |
| `--connect-timeout` | — | Max connect time |
| `--compressed` | — | Request compressed response |

---

## 🔧 Advanced Patterns

### Using Variables in Scripts

```bash
#!/bin/bash
BASE_URL="http://localhost:8080/api"
TOKEN=$(cat ~/.tokens/ottplatform)

get_movie() {
  curl -s -H "Authorization: Bearer $TOKEN" "$BASE_URL/movies/$1" | jq .
}

create_movie() {
  curl -s -X POST "$BASE_URL/movies" \
    -H "Authorization: Bearer $TOKEN" \
    -H "Content-Type: application/json" \
    -d "$1" | jq .
}

get_movie 42
create_movie '{"title":"Dune","year":2021}'
```

### Parallel Requests

```bash
# Run multiple requests in parallel using & (background)
curl -s https://api.example.com/endpoint1 &
curl -s https://api.example.com/endpoint2 &
curl -s https://api.example.com/endpoint3 &
wait
```

### Config File (~/.curlrc)

```bash
# ~/.curlrc — applies to ALL curl invocations
# Useful defaults
silent
show-error
location
connect-timeout = 10
```

### URL Globbing

```bash
# Fetch a range of pages
curl "https://example.com/page[1-10].html"

# Fetch multiple endpoints in sequence
curl "https://api.example.com/{users,movies,shows}" -o "#1.json"
```

---

## 🩺 Common HTTP Status Codes to Watch

| Code | Meaning | What to Check |
|------|---------|---------------|
| `200` | OK | All good |
| `201` | Created | POST succeeded |
| `204` | No Content | DELETE succeeded |
| `301/302` | Redirect | Use `-L` to follow |
| `400` | Bad Request | Check your JSON / params |
| `401` | Unauthorized | Check your token/auth |
| `403` | Forbidden | Permissions issue |
| `404` | Not Found | Wrong URL or missing resource |
| `415` | Unsupported Media | Set `Content-Type` header |
| `429` | Rate Limited | Slow down or add delay |
| `500` | Server Error | Backend bug |
| `503` | Service Unavailable | Server down or overloaded |

---

## 🔁 curl vs Related Tools

| Tool | Use When |
|------|----------|
| `curl` | Scripting, automation, quick API tests, raw control |
| `wget` | Downloading files recursively, mirroring sites |
| `httpie` | Readable output, interactive testing (`http GET url`) |
| `postman` | GUI-based API testing, collaboration |
| `jq` | Parse/filter curl JSON output |
| `xh` | Rust alternative to httpie, very fast |

---

## 💡 Tips & Tricks

> [!tip] Pipe to jq for pretty JSON
> ```bash
> curl -s https://api.example.com/data | jq .
> curl -s https://api.example.com/users | jq '.[].name'
> curl -s https://api.example.com/movie/1 | jq '{id, title}'
> ```

> [!tip] Store token in env variable
> ```bash
> export TOKEN="eyJhbGci..."
> curl -H "Authorization: Bearer $TOKEN" https://api.example.com/me
> ```

> [!tip] Get just the HTTP status code
> ```bash
> curl -o /dev/null -s -w "%{http_code}" https://example.com
> ```

> [!tip] Send a dry run (see headers without body)
> ```bash
> curl -v -o /dev/null https://example.com 2>&1 | grep "^[<>]"
> ```

> [!warning] Never use `-k` in production scripts
> Skipping SSL verification opens you to MITM attacks. Only use in local dev environments.

> [!warning] Don't put secrets in command line args
> Shell history logs everything. Use env vars or files instead:
> ```bash
> # Bad
> curl -H "Authorization: Bearer mysecrettoken123" https://api.example.com
> 
> # Good
> curl -H "Authorization: Bearer $TOKEN" https://api.example.com
> ```

---

## 🔗 Related Notes

- [[HTTP Methods & REST]]
- [[Spring Boot REST APIs]]
- [[JWT Authentication]]
- [[jq — JSON Processing]]
- [[Shell Scripting Basics]]
- [[Networking Fundamentals]]

---

*Last updated: 2026-03 | Source: man curl, curl.se/docs*