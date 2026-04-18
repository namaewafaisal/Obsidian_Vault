# 📺 yt-dlp — Video Downloader

> [!abstract] Overview
> yt-dlp is a feature-rich video downloader supporting YouTube, Twitter, Reddit, Instagram, Twitch, and 1000+ sites. Fork of youtube-dl with active development and better performance.

**Tags:** #cli #linux #tools #yt-dlp #media #download

---

## 🚀 Basic Usage

```bash
# Download a video (best quality)
yt-dlp "https://www.youtube.com/watch?v=VIDEO_ID"

# Download audio only (mp3)
yt-dlp -x --audio-format mp3 "URL"

# Download to specific directory
yt-dlp -o "~/Videos/%(title)s.%(ext)s" "URL"

# Download a playlist
yt-dlp "https://www.youtube.com/playlist?list=PLAYLIST_ID"
```

---

## 📊 Listing & Selecting Formats

```bash
# List all available formats
yt-dlp -F "URL"

# Download specific format by ID
yt-dlp -f 137 "URL"         # specific format code

# Best video + best audio (merged)
yt-dlp -f "bestvideo+bestaudio" "URL"

# Best MP4 specifically
yt-dlp -f "bestvideo[ext=mp4]+bestaudio[ext=m4a]" "URL"

# Best quality under 1080p
yt-dlp -f "bestvideo[height<=1080]+bestaudio" "URL"

# 720p
yt-dlp -f "bestvideo[height<=720]+bestaudio" "URL"

# Audio only — best quality
yt-dlp -f "bestaudio" "URL"
```

---

## 🎵 Audio Extraction

```bash
# Extract audio as mp3
yt-dlp -x --audio-format mp3 "URL"

# Extract as flac (lossless)
yt-dlp -x --audio-format flac "URL"

# Extract as opus (best for small size)
yt-dlp -x --audio-format opus "URL"

# Set audio quality (0=best, 9=worst for mp3 VBR)
yt-dlp -x --audio-format mp3 --audio-quality 0 "URL"
```

---

## 📁 Output Templates

```bash
# Default: video title
yt-dlp -o "%(title)s.%(ext)s" "URL"

# With uploader and date
yt-dlp -o "%(uploader)s/%(upload_date)s - %(title)s.%(ext)s" "URL"

# Playlist with index
yt-dlp -o "%(playlist_index)s - %(title)s.%(ext)s" "PLAYLIST_URL"

# Sanitize filename (remove special chars)
yt-dlp --restrict-filenames -o "%(title)s.%(ext)s" "URL"
```

### Template Variables
| Variable | Meaning |
|----------|---------|
| `%(title)s` | Video title |
| `%(id)s` | Video ID |
| `%(ext)s` | File extension |
| `%(uploader)s` | Channel name |
| `%(upload_date)s` | Date (YYYYMMDD) |
| `%(duration)s` | Duration in seconds |
| `%(playlist_index)s` | Position in playlist |
| `%(resolution)s` | e.g. 1920x1080 |

---

## 📋 Playlists

```bash
# Download entire playlist
yt-dlp "PLAYLIST_URL"

# Download playlist range
yt-dlp --playlist-items 1-10 "PLAYLIST_URL"

# Download specific items
yt-dlp --playlist-items 1,3,5,7 "PLAYLIST_URL"

# Reverse playlist order
yt-dlp --playlist-reverse "PLAYLIST_URL"

# Skip already downloaded
yt-dlp --download-archive archive.txt "PLAYLIST_URL"
```

---

## ⚙️ Download Options

```bash
# Limit download speed
yt-dlp --rate-limit 2M "URL"

# Resume interrupted download
yt-dlp -c "URL"

# Retry on failure
yt-dlp --retries 10 "URL"

# Concurrent fragment downloads (faster)
yt-dlp --concurrent-fragments 4 "URL"

# Embed subtitles
yt-dlp --write-sub --embed-sub "URL"

# Embed thumbnail
yt-dlp --embed-thumbnail "URL"

# Embed metadata
yt-dlp --add-metadata "URL"

# Write thumbnail to separate file
yt-dlp --write-thumbnail "URL"

# Simulate (don't download, just show info)
yt-dlp --simulate "URL"

# Get direct video URL without downloading
yt-dlp --get-url "URL"
```

---

## 🔐 Authentication

```bash
# Use browser cookies (for age-restricted / private videos)
yt-dlp --cookies-from-browser firefox "URL"
yt-dlp --cookies-from-browser chrome "URL"

# Username + password (some sites)
yt-dlp -u USERNAME -p PASSWORD "URL"
```

---

## ⚙️ Config File (~/.config/yt-dlp/config)

```
# Default output template
-o ~/Videos/%(uploader)s/%(title)s.%(ext)s

# Best quality
-f bestvideo+bestaudio

# Merge to mp4
--merge-output-format mp4

# Embed metadata and thumbnail
--embed-thumbnail
--add-metadata

# Use aria2c for faster downloads
--downloader aria2c
--downloader-args "-x 16 -s 16 -k 1M"

# Cookies from Firefox
--cookies-from-browser firefox
```

---

## 🔧 Useful Combos

```bash
# Download YouTube playlist as mp3 library
yt-dlp -x --audio-format mp3 \
  -o "~/Music/%(playlist)s/%(playlist_index)s - %(title)s.%(ext)s" \
  "PLAYLIST_URL"

# Download video with subs and thumbnail embedded
yt-dlp --write-sub --embed-sub --embed-thumbnail --add-metadata \
  -f "bestvideo[ext=mp4]+bestaudio[ext=m4a]" \
  -o "~/Videos/%(title)s.%(ext)s" "URL"

# Use aria2 for faster parallel downloads
yt-dlp --downloader aria2c \
  --downloader-args "-x 16 -s 16 -k 1M" \
  "URL"

# Download and play immediately with mpv
mpv "$(yt-dlp --get-url 'URL')"
# or simply:
mpv "URL"    # mpv handles yt-dlp internally
```

---

## 🔗 Related Notes
- [[mpv — Media Player]]
- [[aria2 — Download Manager]]
- [[ffmpeg — Media Processing]]

---
*Last updated: 2026-03 | Tool: yt-dlp 2026.03.03*
