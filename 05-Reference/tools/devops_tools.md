# 🐳 DevOps CLI Tools — Docker, Git Advanced, systemctl

> [!abstract] Overview
> Production-grade DevOps tooling: Docker container management, advanced Git workflows, and systemd service control.

**Tags:** #devops #docker #git #linux #systemd #cli

---

## 🐳 Docker

### Images

```bash
# List local images
docker images
docker image ls

# Pull image from registry
docker pull nginx:latest
docker pull openjdk:21-jdk-slim

# Build image from Dockerfile
docker build -t myapp:1.0 .
docker build -t myapp:1.0 -f Dockerfile.prod .

# Tag an image
docker tag myapp:1.0 myapp:latest
docker tag myapp:1.0 ghcr.io/namaewafaisal/ottplatform:1.0

# Push to registry
docker push ghcr.io/namaewafaisal/ottplatform:1.0

# Remove image
docker rmi myapp:1.0

# Remove all unused images
docker image prune -a

# Inspect image layers
docker history myapp:1.0
docker inspect myapp:1.0
```

### Containers

```bash
# Run container (interactive)
docker run -it ubuntu:22.04 bash

# Run detached (background)
docker run -d --name myapp -p 8080:8080 myapp:1.0

# Run with env variables
docker run -d \
  --name ottplatform \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:mysql://db:3306/ottdb \
  myapp:1.0

# Run with volume mount
docker run -d \
  -v /host/data:/app/data \
  -v $(pwd)/config:/app/config:ro \
  myapp:1.0

# List running containers
docker ps

# List all containers (including stopped)
docker ps -a

# Stop / Start / Restart
docker stop myapp
docker start myapp
docker restart myapp

# Kill immediately
docker kill myapp

# Remove container
docker rm myapp

# Remove all stopped containers
docker container prune
```

### Exec & Logs

```bash
# Shell into running container
docker exec -it myapp bash
docker exec -it myapp sh    # if bash not available

# Run one-off command
docker exec myapp java -version

# View logs
docker logs myapp

# Follow logs (like tail -f)
docker logs -f myapp

# Last 100 lines
docker logs --tail 100 myapp

# With timestamps
docker logs -t myapp

# Since time
docker logs --since 1h myapp
```

### Networks

```bash
# List networks
docker network ls

# Create a custom network
docker network create ottnetwork

# Run container in a network
docker run -d --network ottnetwork --name db mysql:8.0

# Connect existing container to network
docker network connect ottnetwork myapp

# Inspect network
docker network inspect ottnetwork

# Remove network
docker network rm ottnetwork
```

### Volumes

```bash
# List volumes
docker volume ls

# Create named volume
docker volume create ottdata

# Use named volume
docker run -d -v ottdata:/var/lib/mysql mysql:8.0

# Inspect volume
docker volume inspect ottdata

# Remove unused volumes
docker volume prune
```

### Docker Compose

```bash
# Start services
docker compose up
docker compose up -d           # detached
docker compose up --build      # rebuild images

# Stop services
docker compose down
docker compose down -v         # also remove volumes

# View logs
docker compose logs
docker compose logs -f app     # follow specific service

# Scale a service
docker compose up -d --scale app=3

# Execute in service container
docker compose exec app bash

# Rebuild one service
docker compose build app
docker compose up -d --no-deps --build app
```

### Example docker-compose.yml (OTT Platform)

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - DB_URL=jdbc:mysql://db:3306/ottdb
      - DB_USER=root
      - DB_PASS=secret
    depends_on:
      - db
    networks:
      - ottnetwork

  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: secret
      MYSQL_DATABASE: ottdb
    volumes:
      - dbdata:/var/lib/mysql
    networks:
      - ottnetwork

networks:
  ottnetwork:

volumes:
  dbdata:
```

### Dockerfile Best Practices

```dockerfile
# Use specific tags, not latest
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy dependency files first (layer caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline -B

# Then copy source
COPY src ./src

# Build
RUN ./mvnw package -DskipTests

# Expose port
EXPOSE 8080

# Run
ENTRYPOINT ["java", "-jar", "target/ottplatform.jar"]
```

### System Cleanup

```bash
# Remove all stopped containers, unused networks, dangling images
docker system prune

# Nuclear — remove everything including volumes
docker system prune -a --volumes

# Check disk usage
docker system df
```

---

## 🌿 Git — Advanced

### Rebasing

```bash
# Rebase current branch onto main
git rebase main

# Interactive rebase (last 5 commits)
git rebase -i HEAD~5
# pick  → keep
# squash → merge into previous
# reword → edit message
# drop  → delete commit
# fixup → squash, discard message

# Abort a rebase
git rebase --abort

# Continue after resolving conflicts
git rebase --continue
```

### Stash

```bash
# Stash current changes
git stash

# Stash with message
git stash push -m "WIP: movie filter feature"

# List stashes
git stash list

# Apply latest stash (keep in list)
git stash apply

# Apply specific stash
git stash apply stash@{2}

# Pop (apply + remove from list)
git stash pop

# Drop a stash
git stash drop stash@{0}

# Stash including untracked files
git stash -u
```

### Cherry-Pick

```bash
# Apply a specific commit to current branch
git cherry-pick <commit-hash>

# Cherry-pick a range
git cherry-pick abc123..def456

# Cherry-pick without committing (stage only)
git cherry-pick -n <commit-hash>
```

### Reset & Revert

```bash
# Soft reset — undo commits, keep changes staged
git reset --soft HEAD~1

# Mixed reset — undo commits, unstage changes (default)
git reset HEAD~1

# Hard reset — undo commits, DISCARD changes
git reset --hard HEAD~1

# Hard reset to remote state
git reset --hard origin/main

# Revert a commit (safe — creates new commit)
git revert <commit-hash>

# Revert multiple commits
git revert HEAD~3..HEAD
```

### Reflog — Your Safety Net

```bash
# See history of HEAD movements
git reflog

# Recover a lost commit
git checkout <hash-from-reflog>

# Restore branch to a previous state
git reset --hard HEAD@{3}
```

### Branch Management

```bash
# List all branches
git branch -a

# Create and switch
git checkout -b feature/auth
git switch -c feature/auth   # modern syntax

# Delete branch (safe — merged only)
git branch -d feature/auth

# Force delete
git branch -D feature/auth

# Rename branch
git branch -m old-name new-name

# Push new branch
git push -u origin feature/auth

# Delete remote branch
git push origin --delete feature/auth

# Track remote branch
git branch --set-upstream-to=origin/main main
```

### Log & History

```bash
# One-line log
git log --oneline

# Graph view
git log --oneline --graph --all

# Filter by author
git log --author="Mohamed"

# Filter by date
git log --since="2 weeks ago"
git log --after="2026-01-01"

# Search commit messages
git log --grep="feat:"

# Show changes in each commit
git log -p

# Show file changes summary
git log --stat

# Log for specific file
git log --follow -- src/main/java/MovieService.java
```

### Tags

```bash
# List tags
git tag

# Create lightweight tag
git tag v1.0.0

# Create annotated tag
git tag -a v1.0.0 -m "First stable release"

# Push tags
git push origin v1.0.0
git push origin --tags

# Delete tag
git tag -d v1.0.0
git push origin --delete v1.0.0
```

### Submodules

```bash
# Add submodule
git submodule add https://github.com/user/repo libs/repo

# Init and clone submodules after cloning parent
git submodule update --init --recursive

# Update all submodules
git submodule update --remote
```

### Useful Config

```bash
# Set identity
git config --global user.name "Mohamed Faisal"
git config --global user.email "you@example.com"

# Default branch name
git config --global init.defaultBranch main

# Better diff
git config --global diff.tool vimdiff

# Aliases
git config --global alias.st status
git config --global alias.lg "log --oneline --graph --all"
git config --global alias.undo "reset --soft HEAD~1"
git config --global alias.unstage "restore --staged"
```

---

## ⚙️ systemctl — Systemd Service Control

### Service Management

```bash
# Start / Stop / Restart
sudo systemctl start nginx
sudo systemctl stop nginx
sudo systemctl restart nginx

# Reload config without full restart
sudo systemctl reload nginx

# Enable at boot
sudo systemctl enable nginx

# Disable from boot
sudo systemctl disable nginx

# Enable and start in one command
sudo systemctl enable --now nginx

# Check status
systemctl status nginx

# Is it active?
systemctl is-active nginx

# Is it enabled?
systemctl is-enabled nginx
```

### Viewing Logs (journalctl)

```bash
# All logs for a service
journalctl -u nginx

# Follow live logs
journalctl -u nginx -f

# Last 100 lines
journalctl -u nginx -n 100

# Since last boot
journalctl -u nginx -b

# Since time
journalctl -u nginx --since "1 hour ago"
journalctl -u nginx --since "2026-03-01 10:00"

# Error logs only
journalctl -u nginx -p err

# All errors since boot
journalctl -b -p err
```

### Listing Units

```bash
# List running services
systemctl list-units --type=service

# List all services (including failed/inactive)
systemctl list-units --type=service --all

# List failed units
systemctl --failed

# List enabled units
systemctl list-unit-files --state=enabled
```

### Writing a Custom Service

```ini
# /etc/systemd/system/ottplatform.service

[Unit]
Description=OTT Platform Spring Boot App
After=network.target mysql.service

[Service]
User=deploy
WorkingDirectory=/opt/ottplatform
ExecStart=/usr/bin/java -jar /opt/ottplatform/ottplatform.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10
StandardOutput=journal
StandardError=journal
Environment=SPRING_PROFILES_ACTIVE=prod

[Install]
WantedBy=multi-user.target
```

```bash
# After creating the file
sudo systemctl daemon-reload
sudo systemctl enable --now ottplatform
journalctl -u ottplatform -f
```

### System Power

```bash
sudo systemctl reboot
sudo systemctl poweroff
sudo systemctl suspend
sudo systemctl hibernate
```

---

## 🔗 Related Notes

- [[curl — HTTP Client]]
- [[Networking CLI Tools]]
- [[Security Recon Tools]]
- [[Spring Boot Deployment]]
- [[GitHub Actions CI-CD]]

---
*Last updated: 2026-03 | Tools: docker, docker compose, git, systemctl, journalctl*
