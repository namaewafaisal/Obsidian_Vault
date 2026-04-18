# 🔐 SSH — Secure Shell

> [!abstract] Overview
> SSH is the standard for secure remote access, tunneling, and file transfer. Mastering SSH config, keys, and tunneling is foundational for any DevOps or backend work.

**Tags:** #cli #linux #tools #ssh #networking #devops #security

---

## 🚀 Basic Usage

```bash
# Connect to remote host
ssh user@hostname
ssh user@192.168.1.100

# Connect on specific port
ssh -p 2222 user@hostname

# Run a command remotely (no interactive shell)
ssh user@host "ls -la /var/www"
ssh user@host "systemctl status nginx"

# Run multiple commands
ssh user@host "cd /app && git pull && mvn package"
```

---

## 🔑 SSH Keys

### Generate Key Pair

```bash
# Generate ED25519 key (recommended, modern)
ssh-keygen -t ed25519 -C "faisal@arch"

# Generate RSA key (legacy systems)
ssh-keygen -t rsa -b 4096 -C "faisal@arch"

# Custom filename
ssh-keygen -t ed25519 -f ~/.ssh/server_key -C "ottplatform-server"
```

Keys are saved to:
- Private: `~/.ssh/id_ed25519` (NEVER share this)
- Public: `~/.ssh/id_ed25519.pub` (copy this to servers)

### Copy Public Key to Server

```bash
# Automatic (easiest)
ssh-copy-id user@hostname

# Manual (paste into server's ~/.ssh/authorized_keys)
cat ~/.ssh/id_ed25519.pub | ssh user@host "mkdir -p ~/.ssh && cat >> ~/.ssh/authorized_keys"

# If you have root and are setting up another user
ssh-copy-id -i ~/.ssh/mykey.pub user@host
```

### Key Management

```bash
# List loaded keys
ssh-add -l

# Add key to agent
ssh-add ~/.ssh/id_ed25519

# Add key with timeout (auto-remove after 1 hour)
ssh-add -t 3600 ~/.ssh/id_ed25519

# Start ssh-agent (if not running)
eval "$(ssh-agent -s)"
```

---

## ⚙️ SSH Config (~/.ssh/config)

The config file lets you define aliases and per-host settings.

```
# ~/.ssh/config

# Default settings for all hosts
Host *
    ServerAliveInterval 60
    ServerAliveCountMax 3
    AddKeysToAgent yes
    IdentityFile ~/.ssh/id_ed25519

# OTT Platform server
Host ottserver
    HostName 203.0.113.10
    User deploy
    Port 22
    IdentityFile ~/.ssh/ottplatform_key

# Home lab via Tailscale
Host homelab
    HostName faizaldell-1
    User faisal
    IdentityFile ~/.ssh/id_ed25519

# Jump host (bastion)
Host internal-server
    HostName 10.0.0.50
    User faisal
    ProxyJump bastion.example.com
```

With this config:
```bash
ssh ottserver        # instead of: ssh deploy@203.0.113.10 -i ~/.ssh/ottplatform_key
ssh homelab          # connects via Tailscale
```

---

## 🌐 SSH Tunneling

### Local Port Forwarding

Forward a local port to a remote service. Access remote services locally.

```bash
# Access remote MySQL (3306) on localhost:3307
ssh -L 3307:localhost:3306 user@server

# Access remote app (8080) on localhost:8888
ssh -L 8888:localhost:8080 user@server

# Tunnel through jump host
ssh -L 3307:db-server:3306 user@jump-host

# Background tunnel (no shell)
ssh -fNL 3307:localhost:3306 user@server
```

Then connect locally:
```bash
mysql -h 127.0.0.1 -P 3307 -u root -p
curl http://localhost:8888/api/movies
```

### Remote Port Forwarding

Expose a local port to the remote server. Share your local dev server.

```bash
# Expose local:8080 as remote:9090
ssh -R 9090:localhost:8080 user@server

# Now on the server:
curl http://localhost:9090    # reaches your local machine's :8080
```

### Dynamic Port Forwarding (SOCKS Proxy)

```bash
# Create SOCKS proxy through SSH
ssh -D 1080 user@server

# Configure browser to use SOCKS5 proxy at 127.0.0.1:1080
# All browser traffic routes through the server
```

### Persistent Tunnel (Background)

```bash
# -f: background, -N: no command, -T: no TTY
ssh -fNT -L 3307:localhost:3306 user@server

# Kill the tunnel
pkill -f "ssh -fNT"
# or find and kill:
ps aux | grep ssh
```

---

## 📁 File Transfer

```bash
# Copy file TO server
scp file.txt user@server:/remote/path/

# Copy file FROM server
scp user@server:/remote/file.txt ./local/

# Copy directory
scp -r ./src/ user@server:/remote/src/

# Use SSH config alias
scp file.txt ottserver:/app/

# SFTP interactive
sftp user@server
sftp> ls
sftp> get remote-file.txt
sftp> put local-file.txt
sftp> exit
```

For better performance, use `rsync` instead of `scp` for large transfers.

---

## 🔒 Security Hardening (/etc/ssh/sshd_config)

```ini
# Disable password auth (key-only)
PasswordAuthentication no
ChallengeResponseAuthentication no

# Disable root login
PermitRootLogin no

# Only allow specific users
AllowUsers faisal deploy

# Change port (obscurity, not security)
Port 2222

# Limit auth attempts
MaxAuthTries 3

# Use strong algorithms only
KexAlgorithms curve25519-sha256,diffie-hellman-group-exchange-sha256
Ciphers chacha20-poly1305@openssh.com,aes256-gcm@openssh.com
```

```bash
# Restart SSH after changes
sudo systemctl restart sshd

# Test config before restarting
sudo sshd -t
```

---

## 🔧 Useful Options

```bash
# Verbose (debug connection issues)
ssh -v user@host
ssh -vvv user@host    # extra verbose

# Skip host key check (INSECURE — dev only)
ssh -o StrictHostKeyChecking=no user@host

# Use specific key
ssh -i ~/.ssh/mykey user@host

# X11 forwarding (run GUI apps remotely)
ssh -X user@host
ssh -Y user@host    # trusted X11

# Agent forwarding (use local keys on remote)
ssh -A user@jump-host
# Then from jump-host you can ssh to internal hosts using your local key
```

---

## 🎯 Common Workflows

```bash
# Deploy Spring Boot jar to server
scp target/ottplatform.jar ottserver:/opt/ottplatform/
ssh ottserver "sudo systemctl restart ottplatform"

# Check server logs remotely
ssh ottserver "journalctl -u ottplatform -f"

# Quick tunnel for DB access during debugging
ssh -fNL 3307:localhost:3306 ottserver
mysql -h 127.0.0.1 -P 3307 -u root -p ottdb

# One-liner: push code and restart
git push && ssh ottserver "cd /app && git pull && mvn package -DskipTests && systemctl restart app"
```

---

## 🔗 Related Notes
- [[rsync — File Sync]]
- [[systemctl — Service Control]]
- [[Networking CLI Tools]]
- [[Tailscale Setup]]

---
*Last updated: 2026-03 | Tool: openssh 10.2p1*
