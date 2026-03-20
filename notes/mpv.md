# 🎬 mpv — Media Player

> [!abstract] Overview
> mpv is a powerful, scriptable, minimal media player. Plays everything — local files, streams, YouTube URLs, RTMP, HLS. No GUI bloat. Controlled via keyboard, config, and scripts.

**Tags:** #cli #linux #tools #mpv #media #video

---

## 🚀 Basic Usage

```bash
# Play a file
mpv video.mp4

# Play YouTube URL directly
mpv "https://www.youtube.com/watch?v=VIDEO_ID"

# Play audio only
mpv --no-video audio.flac

# Play from stdin
cat video.mp4 | mpv -

# Play a playlist file
mpv --playlist=mylist.m3u

# Play all files in a directory
mpv /path/to/videos/

# Loop
mpv --loop video.mp4
mpv --loop-playlist playlist.m3u
```

---

## ⌨️ Default Key Bindings

### Playback
| Key | Action |
|-----|--------|
| `Space` | Pause / Resume |
| `q` | Quit |
| `Q` | Quit and remember position |
| `←` / `→` | Seek -/+ 5 seconds |
| `↑` / `↓` | Seek +/- 1 minute |
| `Shift+←/→` | Seek -/+ 1 second |
| `,` / `.` | Frame step backward/forward |
| `[` / `]` | Decrease/increase speed by 10% |
| `{` / `}` | Halve/double speed |
| `Backspace` | Reset speed |

### Volume & Audio
| Key | Action |
|-----|--------|
| `0` / `9` | Volume up/down |
| `m` | Mute |
| `#` | Cycle audio tracks |
| `Ctrl+←/→` | Seek to previous/next chapter |

### Video
| Key | Action |
|-----|--------|
| `f` | Toggle fullscreen |
| `s` | Screenshot |
| `S` | Screenshot (without subtitles) |
| `Alt+s` | Auto screenshot every frame |
| `1` / `2` | Decrease/increase contrast |
| `3` / `4` | Decrease/increase brightness |
| `5` / `6` | Decrease/increase gamma |
| `Alt+0` | Resize window to 50% |
| `Alt+1` | Resize to 100% |
| `Alt+2` | Resize to 200% |

### Subtitles
| Key | Action |
|-----|--------|
| `v` | Toggle subtitle visibility |
| `j` | Cycle subtitle tracks |
| `z` / `Shift+z` | Subtitle delay -/+ 0.1s |

### Playlist
| Key | Action |
|-----|--------|
| `>` / `<` | Next/previous in playlist |
| `l` | Show playlist |

---

## ⚙️ Config (~/.config/mpv/mpv.conf)

```ini
# Video
vo=gpu                      # GPU rendering
hwdec=auto                  # hardware decoding
profile=gpu-hq              # high quality profile

# Audio
volume=70                   # default volume
audio-pitch-correction=yes  # maintain pitch when changing speed

# Subtitles
sub-auto=fuzzy              # auto-load subs with similar name
sub-font-size=40
sub-color='#FFFFFFFF'
sub-border-size=2

# Screenshot
screenshot-format=png
screenshot-directory=~/Pictures/mpv/

# General
save-position-on-quit=yes   # resume where you left off
keep-open=yes               # don't close after last file
osd-level=1
osd-font-size=32

# YouTube (via yt-dlp)
ytdl-format=bestvideo[height<=1080]+bestaudio/best
```

---

## 🎨 Scripts (~/.config/mpv/scripts/)

mpv is scriptable with Lua. Popular community scripts:

```bash
# uosc — modern UI overlay (you have mpv-uosc installed)
# Already installed! Adds progress bar, menus, etc.

# thumbfast — thumbnail preview in progress bar
# Download: https://github.com/po5/thumbfast

# autoload — auto-load next file in directory
# Download: https://github.com/mpv-player/mpv/blob/master/TOOLS/lua/autoload.lua
```

---

## 🌐 Streaming

```bash
# YouTube (best quality)
mpv "https://youtube.com/watch?v=..."

# YouTube (specific quality)
mpv --ytdl-format="bestvideo[height<=720]+bestaudio" "URL"

# Twitch stream
mpv "https://www.twitch.tv/channelname"

# Direct HTTP stream
mpv "https://example.com/stream.m3u8"

# RTMP stream
mpv "rtmp://example.com/live/stream"

# With streamlink (better Twitch support)
streamlink --player mpv "https://twitch.tv/channel" best
```

---

## 🎵 Audio Mode

```bash
# No video window
mpv --no-video music.flac

# Play all music in a folder
mpv --no-video ~/Music/

# Shuffle
mpv --shuffle --no-video ~/Music/

# Visualizer (requires cava or similar — use mpv scripts)
mpv --no-video --force-window music.flac
```

---

## 🖥️ CLI Options Reference

```bash
# Start at specific time
mpv --start=01:30:00 video.mp4

# Play only a section
mpv --start=00:10:00 --end=00:20:00 video.mp4

# Set playback speed
mpv --speed=1.5 video.mp4

# Specific audio track
mpv --aid=2 video.mkv

# Specific subtitle track
mpv --sid=1 video.mkv

# Force window size
mpv --geometry=1280x720 video.mp4

# Fullscreen
mpv --fullscreen video.mp4

# On top of other windows
mpv --ontop video.mp4

# No OSD
mpv --osd-level=0 video.mp4

# Verbose logging
mpv -v video.mp4
```

---

## 🔗 Related Notes
- [[yt-dlp — Video Downloader]]
- [[aria2 — Download Manager]]
- [[ffmpeg — Media Processing]]

---
*Last updated: 2026-03 | Tool: mpv 0.41*
