# ⬇️ aria2 — Multi-Protocol Download Manager

> [!abstract] Overview
> aria2 is a lightweight, multi-protocol, multi-source download utility. Supports HTTP, HTTPS, FTP, BitTorrent, and Metalink. Parallelizes connections for maximum speed.

**Tags:** #cli #linux #tools #aria2 #download #networking

---

## 🚀 Basic Usage

```bash
# Download a file
aria2c https://example.com/file.zip

# Download to specific directory
aria2c -d ~/Downloads https://example.com/file.zip

# Download and save with custom name
aria2c -o myfile.zip https://example.com/file.zip

# Download from multiple URLs (same file, faster)
aria2c URL1 URL2 URL3
```

---

## ⚡ Speed & Parallelism

```bash
# Multiple connections per server (default: 1, max: 16)
aria2c -x 16 https://example.com/bigfile.iso

# Split file into segments
aria2c -s 16 https://example.com/bigfile.iso

# Combine both (most common speedup)
aria2c -x 16 -s 16 https://example.com/bigfile.iso

# Min chunk size (avoid tiny segments)
aria2c -x 16 -s 16 -k 1M https://example.com/bigfile.iso

# Limit download speed
aria2c --max-download-limit=2M https://example.com/file.iso
```

---

## 📋 Download Lists

```bash
# Download from a list of URLs in a file
aria2c -i urls.txt

# URLs file format
cat urls.txt
# https://example.com/file1.zip
# https://example.com/file2.zip
#   out=custom-name.zip          (optional rename, indented)
#   dir=/custom/dir              (optional dir, indented)
```

---

## 🔁 Resume & Retry

```bash
# Resume interrupted download
aria2c -c https://example.com/bigfile.iso

# Retry on failure
aria2c --max-tries=5 --retry-wait=3 https://example.com/file.zip

# Continue even if one URL fails (multiple sources)
aria2c --continue https://url1.com/file https://url2.com/file
```

---

## 🌊 BitTorrent

```bash
# Download torrent file
aria2c file.torrent

# Download from magnet link
aria2c "magnet:?xt=urn:btih:..."

# Seed after download
aria2c --seed-ratio=1.0 file.torrent

# Limit upload speed while seeding
aria2c --max-upload-limit=100K file.torrent

# Download specific files from torrent
aria2c --select-file=1,3,5 file.torrent
```

---

## 🔐 Authentication & Headers

```bash
# HTTP Basic auth
aria2c --http-user=admin --http-passwd=secret https://protected.example.com/file

# Custom headers
aria2c --header="Authorization: Bearer TOKEN" https://api.example.com/file
aria2c --header="Referer: https://example.com" https://cdn.example.com/file.mp4

# Cookies
aria2c --load-cookies=cookies.txt https://example.com/file

# User agent
aria2c --user-agent="Mozilla/5.0" https://example.com/file
```

---

## ⚙️ Config File (~/.aria2/aria2.conf)

```ini
# ~/.aria2/aria2.conf

# Connections
max-connection-per-server=16
split=16
min-split-size=1M

# Retry
max-tries=5
retry-wait=3
continue=true

# Output
dir=~/Downloads
log=~/.aria2/aria2.log
log-level=warn

# Performance
max-concurrent-downloads=5
auto-file-renaming=true

# BitTorrent
bt-enable-lpd=true
bt-max-peers=55
seed-ratio=0
```

---

## 🤝 Use with yt-dlp

```bash
# yt-dlp can use aria2 as its downloader (much faster)
yt-dlp --downloader aria2c \
  --downloader-args "-x 16 -s 16 -k 1M" \
  "https://youtube.com/watch?v=..."

# Or set in yt-dlp config permanently:
# ~/.config/yt-dlp/config
# --downloader aria2c
# --downloader-args "-x 16 -s 16 -k 1M"
```

---

## 🔗 Related Notes
- [[yt-dlp — Video Downloader]]
- [[wget — File Downloader]]
- [[curl — HTTP Client]]

---
*Last updated: 2026-03 | Tool: aria2 1.37.0*
