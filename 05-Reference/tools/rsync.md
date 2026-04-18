# 🔄 rsync — File Sync & Backup

> [!abstract] Overview
> rsync is the gold standard for file synchronization, incremental backups, and remote transfers. Only transfers the differences between files — fast and bandwidth-efficient.

**Tags:** #cli #linux #tools #rsync #backup #sync #ssh

---

## 🧠 Mental Model

```
rsync [OPTIONS] SOURCE DESTINATION
```

- Source and destination can be local or remote (`user@host:/path`)
- Trailing slash on source matters: `src/` copies contents, `src` copies the directory itself
- Always dry-run first with `-n`

---

## 🚀 Basic Usage

```bash
# Copy a file locally
rsync file.txt /backup/

# Copy a directory
rsync -r src/ /backup/src/

# Copy with archive mode (preserves perms, timestamps, symlinks)
rsync -a src/ /backup/src/

# Verbose output
rsync -av src/ /backup/src/

# Dry run (preview without doing anything)
rsync -avn src/ /backup/src/
```

---

## ⚠️ The Trailing Slash Rule

```bash
# With trailing slash — copies CONTENTS of src into dest
rsync -av src/ /backup/
# Result: /backup/file1, /backup/file2 ...

# Without trailing slash — copies src DIRECTORY into dest
rsync -av src /backup/
# Result: /backup/src/file1, /backup/src/file2 ...
```

> [!warning] Always double-check which behavior you want before running.

---

## 📡 Remote Sync (SSH)

```bash
# Local to remote
rsync -av /local/path/ user@server:/remote/path/

# Remote to local
rsync -av user@server:/remote/path/ /local/path/

# Use specific SSH key
rsync -av -e "ssh -i ~/.ssh/mykey" /local/ user@server:/remote/

# Use specific SSH port
rsync -av -e "ssh -p 2222" /local/ user@server:/remote/

# Over Tailscale (same syntax, use Tailscale hostname)
rsync -av /local/ faisal@faizaldell-1:/remote/
```

---

## ⚙️ Common Options

| Flag | Meaning |
|------|---------|
| `-a` | Archive: recursive + preserve perms/timestamps/symlinks |
| `-v` | Verbose |
| `-z` | Compress during transfer |
| `-P` | Show progress + resume partial transfers |
| `-n` | Dry run |
| `-u` | Skip files newer on destination |
| `-r` | Recursive (included in `-a`) |
| `--delete` | Delete files in dest not in source |
| `--exclude` | Exclude pattern |
| `--include` | Include pattern |
| `--checksum` | Use checksum instead of size+time |
| `--bwlimit` | Limit bandwidth (KB/s) |
| `--partial` | Keep partial files on interruption |

---

## 🗃️ Backup Patterns

### Simple Backup

```bash
rsync -avP --delete ~/projects/ /backup/projects/
```

### Exclude Patterns

```bash
# Exclude node_modules, .git, build artifacts
rsync -av \
  --exclude='node_modules/' \
  --exclude='.git/' \
  --exclude='target/' \
  --exclude='*.class' \
  ~/projects/ /backup/projects/
```

### Incremental Backup with Hard Links

```bash
# Each backup is a full snapshot but only stores diffs (like Time Machine)
rsync -av --delete \
  --link-dest=/backup/previous/ \
  ~/projects/ /backup/$(date +%Y-%m-%d)/
```

### Sync with Progress

```bash
rsync -avP --delete src/ dest/
# -P = --progress + --partial
```

---

## 🔄 Sync Scenarios

```bash
# Keep two directories in sync (one-way)
rsync -av --delete ~/notes/ /external/notes/

# Sync over SSH with compression (good for slow connections)
rsync -avz ~/projects/ user@server:~/projects/

# Sync only specific file types
rsync -av --include='*.java' --include='*/' --exclude='*' src/ /backup/java/

# Mirror a website
rsync -avz --delete user@server:/var/www/html/ ./local-backup/

# Bandwidth-limited backup (good for background jobs)
rsync -avP --bwlimit=1000 ~/Videos/ /backup/Videos/
```

---

## 📋 Dry Run First (Always)

```bash
# See what would be transferred
rsync -avn --delete src/ dest/

# Only then run for real
rsync -av --delete src/ dest/
```

---

## ⏰ Automate with cron/systemd

```bash
# Add to crontab (daily backup at 2am)
crontab -e
# 0 2 * * * rsync -av --delete ~/projects/ /backup/projects/ >> /var/log/rsync.log 2>&1
```

---

## 🔗 Related Notes
- [[ssh — Remote Access]]
- [[systemctl — Service Control]]
- [[curl — HTTP Client]]

---
*Last updated: 2026-03 | Tool: rsync 3.4.1*
