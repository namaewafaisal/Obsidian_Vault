# 🔐 Security Recon Tools — gobuster, ffuf, httpx, nuclei

> [!abstract] Overview
> Active recon and vulnerability scanning tools used in bug bounty, CTFs, and penetration testing. These tools should only be used on systems you own or have explicit written permission to test.

> [!warning] Legal Reminder
> **Never run these tools against systems you don't own or have written permission to test.** Unauthorized scanning is illegal in most jurisdictions. Use on: your own servers, CTF platforms (HackTheBox, TryHackMe), local VMs, or authorized bug bounty targets.

**Tags:** #security #pentest #recon #ctf #cli #tools #bugbounty

---

## 🗂️ Recon Flow Overview

```
Target Identified
       │
       ▼
  httpx (probe alive hosts, grab tech stack)
       │
       ▼
  gobuster / ffuf (directory & subdomain bruteforce)
       │
       ▼
  nuclei (automated vulnerability templates)
       │
       ▼
  Manual Verification + Exploitation
```

---

## 🌐 httpx — HTTP Probing

> Fast HTTP toolkit for probing hosts, grabbing titles, status codes, tech stacks, and more.

### Installation

```bash
go install -v github.com/projectdiscovery/httpx/cmd/httpx@latest
# or
sudo pacman -S httpx   # if in AUR
```

### Basic Usage

```bash
# Probe a single URL
echo "https://example.com" | httpx

# Probe a list of hosts
httpx -l hosts.txt

# From stdin
cat hosts.txt | httpx
```

### Probing Options

```bash
# Show status code, title, and content length
httpx -l hosts.txt -status-code -title -content-length

# Show web server / tech stack
httpx -l hosts.txt -tech-detect

# Show response time
httpx -l hosts.txt -response-time

# Follow redirects
httpx -l hosts.txt -follow-redirects

# Show all response info
httpx -l hosts.txt -status-code -title -content-length -tech-detect -response-time

# Probe with custom port
httpx -l hosts.txt -ports 8080,8443,3000

# Filter by status code
httpx -l hosts.txt -mc 200          # only 200 OK
httpx -l hosts.txt -mc 200,301,302
httpx -l hosts.txt -fc 404          # filter out 404s

# Filter by content length
httpx -l hosts.txt -ml 100          # match length > 100
```

### Subdomain Probing

```bash
# Combine with subfinder / amass output
subfinder -d example.com -silent | httpx -status-code -title

# Probe common subdomains
cat subdomains.txt | httpx -follow-redirects -status-code -title -o alive.txt
```

### Output Options

```bash
# Save output to file
httpx -l hosts.txt -o results.txt

# JSON output
httpx -l hosts.txt -json -o results.json

# CSV output
httpx -l hosts.txt -csv -o results.csv

# Silent (URLs only)
httpx -l hosts.txt -silent
```

### Practical Recon Flow

```bash
# 1. Discover subdomains
subfinder -d target.com -o subs.txt

# 2. Probe alive hosts
httpx -l subs.txt -status-code -title -tech-detect -o alive.txt

# 3. Filter interesting ones
cat alive.txt | grep "200\|301"
```

---

## 🚪 gobuster — Directory & Subdomain Bruteforce

> Fast brute-force tool for discovering hidden directories, files, and subdomains.

### Installation

```bash
go install github.com/OJ/gobuster/v3@latest
# or
sudo pacman -S gobuster
```

### Directory Mode (dir)

```bash
# Basic directory scan
gobuster dir -u https://example.com -w /usr/share/wordlists/dirb/common.txt

# With file extensions
gobuster dir -u https://example.com \
  -w /usr/share/wordlists/dirb/common.txt \
  -x php,html,js,txt,bak

# With auth header
gobuster dir -u https://example.com \
  -w wordlist.txt \
  -H "Authorization: Bearer TOKEN"

# Custom status codes to include
gobuster dir -u https://example.com -w wordlist.txt -s "200,204,301,302,307"

# Filter out specific status codes
gobuster dir -u https://example.com -w wordlist.txt -b 404,403

# Increase threads (default: 10)
gobuster dir -u https://example.com -w wordlist.txt -t 50

# Add cookie
gobuster dir -u https://example.com -w wordlist.txt \
  -c "session=abc123; PHPSESSID=xyz"

# Follow redirects
gobuster dir -u https://example.com -w wordlist.txt -r

# Skip SSL verify
gobuster dir -u https://example.com -w wordlist.txt -k

# Save output
gobuster dir -u https://example.com -w wordlist.txt -o results.txt
```

### Subdomain Mode (dns)

```bash
# Subdomain enumeration
gobuster dns -d example.com -w /usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt

# Show IPs
gobuster dns -d example.com -w wordlist.txt --show-ips

# Use custom resolver
gobuster dns -d example.com -w wordlist.txt -r 8.8.8.8

# Save output
gobuster dns -d example.com -w wordlist.txt -o dns_results.txt
```

### VHOST Mode

```bash
# Virtual host discovery
gobuster vhost -u https://example.com \
  -w /usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt \
  --append-domain
```

### Wordlists Reference

```bash
# Common wordlists (install seclists)
sudo pacman -S seclists     # Arch
# or
git clone https://github.com/danielmiessler/SecLists.git /usr/share/seclists

# Directories
/usr/share/seclists/Discovery/Web-Content/common.txt
/usr/share/seclists/Discovery/Web-Content/directory-list-2.3-medium.txt
/usr/share/seclists/Discovery/Web-Content/big.txt

# API endpoints
/usr/share/seclists/Discovery/Web-Content/api/objects.txt
/usr/share/seclists/Discovery/Web-Content/swagger.txt

# Subdomains
/usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt
/usr/share/seclists/Discovery/DNS/subdomains-top1million-20000.txt
```

---

## ⚡ ffuf — Fuzz Faster U Fool

> More flexible than gobuster — supports fuzzing anywhere in the request (URL, headers, body, params).

### Installation

```bash
go install github.com/ffuf/ffuf/v2@latest
```

### Key Concept

Use `FUZZ` as a placeholder — ffuf replaces it with each word from the wordlist.

```bash
ffuf -w wordlist.txt -u https://example.com/FUZZ
```

### Directory Fuzzing

```bash
# Basic dir fuzz
ffuf -w /usr/share/seclists/Discovery/Web-Content/common.txt \
  -u https://example.com/FUZZ

# With extensions
ffuf -w /usr/share/seclists/Discovery/Web-Content/common.txt \
  -u https://example.com/FUZZ \
  -e .php,.html,.txt,.bak,.zip

# Filter 404s
ffuf -w wordlist.txt -u https://example.com/FUZZ -fc 404

# Match only 200
ffuf -w wordlist.txt -u https://example.com/FUZZ -mc 200

# Filter by response size
ffuf -w wordlist.txt -u https://example.com/FUZZ -fs 1234

# Filter by word count in response
ffuf -w wordlist.txt -u https://example.com/FUZZ -fw 12

# Threads
ffuf -w wordlist.txt -u https://example.com/FUZZ -t 100

# Rate limit
ffuf -w wordlist.txt -u https://example.com/FUZZ -rate 50
```

### Subdomain Fuzzing

```bash
# Fuzz subdomain
ffuf -w subdomains.txt \
  -u https://FUZZ.example.com \
  -mc 200,301,302

# Using Host header fuzzing (VHOST discovery)
ffuf -w subdomains.txt \
  -u https://example.com \
  -H "Host: FUZZ.example.com" \
  -fc 404
```

### Parameter Fuzzing

```bash
# GET parameter discovery
ffuf -w params.txt \
  -u https://example.com/search?FUZZ=test \
  -mc 200

# POST body fuzzing
ffuf -w params.txt \
  -u https://example.com/login \
  -X POST \
  -d "FUZZ=admin" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -mc 200

# JSON body fuzzing
ffuf -w payloads.txt \
  -u https://api.example.com/login \
  -X POST \
  -d '{"username":"FUZZ","password":"test"}' \
  -H "Content-Type: application/json" \
  -mc 200
```

### Multiple Fuzzing Positions

```bash
# Two wordlists (FUZZ and W2)
ffuf -w users.txt:FUZZ -w passwords.txt:W2 \
  -u https://example.com/login \
  -X POST \
  -d "user=FUZZ&pass=W2" \
  -fc 401
```

### Output

```bash
# Save to file
ffuf -w wordlist.txt -u https://example.com/FUZZ -o results.json -of json
ffuf -w wordlist.txt -u https://example.com/FUZZ -o results.csv -of csv

# Silent mode
ffuf -w wordlist.txt -u https://example.com/FUZZ -s
```

### gobuster vs ffuf

| Feature | gobuster | ffuf |
|---------|----------|------|
| Speed | Very fast | Very fast |
| Fuzz anywhere | ❌ URL only | ✅ URL, headers, body |
| VHOST fuzzing | ✅ | ✅ |
| Multi-position fuzz | ❌ | ✅ |
| Filter by size/words | ❌ | ✅ |
| Best for | Simple dir/dns bruteforce | Full-featured fuzzing |

---

## ☢️ nuclei — Vulnerability Scanner

> Template-based vulnerability scanner. Community-driven, covers CVEs, misconfigs, exposures.

### Installation

```bash
go install -v github.com/projectdiscovery/nuclei/v3/cmd/nuclei@latest

# Update templates
nuclei -update-templates
```

### Basic Usage

```bash
# Scan a single URL
nuclei -u https://example.com

# Scan a list of URLs
nuclei -l targets.txt

# From stdin
cat targets.txt | nuclei
```

### Template Selection

```bash
# Run specific template
nuclei -u https://example.com -t cves/2021/CVE-2021-44228.yaml

# Run a template directory
nuclei -u https://example.com -t cves/2021/

# Run by tag
nuclei -u https://example.com -tags cve
nuclei -u https://example.com -tags sqli
nuclei -u https://example.com -tags xss
nuclei -u https://example.com -tags misconfig
nuclei -u https://example.com -tags exposure
nuclei -u https://example.com -tags rce

# Multiple tags
nuclei -u https://example.com -tags "cve,rce,sqli"

# Exclude tags
nuclei -u https://example.com -etags "dos,fuzz"
```

### Severity Filtering

```bash
# Run only critical and high severity
nuclei -u https://example.com -severity critical,high

# Run all except info
nuclei -u https://example.com -severity medium,high,critical

# Exclude severity
nuclei -u https://example.com -es info,low
```

### Output & Reporting

```bash
# Save output to file
nuclei -u https://example.com -o results.txt

# JSON output
nuclei -u https://example.com -jsonl -o results.jsonl

# Markdown report
nuclei -u https://example.com -markdown-export report/

# Silent (findings only, no banner)
nuclei -u https://example.com -silent
```

### Rate Limiting & Tuning

```bash
# Limit requests per second
nuclei -u https://example.com -rl 10

# Limit concurrent requests
nuclei -u https://example.com -c 25

# Timeout
nuclei -u https://example.com -timeout 10

# Retries
nuclei -u https://example.com -retries 2
```

### Common Workflows

```bash
# Full recon → nuclei pipeline
subfinder -d example.com -silent \
  | httpx -silent \
  | nuclei -tags cve,misconfig,exposure -severity medium,high,critical -o vulns.txt

# Quick CVE scan on a target
nuclei -u https://example.com -tags cve -severity high,critical -silent

# Check for exposed panels and dashboards
nuclei -u https://example.com -tags panel,exposure

# Check Spring Boot actuator exposure
nuclei -u https://example.com -tags springboot

# Log4Shell check
nuclei -u https://example.com -t cves/2021/CVE-2021-44228.yaml
```

### Writing a Basic Nuclei Template

```yaml
id: custom-header-check

info:
  name: Missing Security Headers
  author: yourname
  severity: low
  tags: misconfig,headers

requests:
  - method: GET
    path:
      - "{{BaseURL}}"
    matchers:
      - type: word
        part: header
        words:
          - "X-Frame-Options"
        negative: true   # match if header is ABSENT
```

```bash
# Run your custom template
nuclei -u https://example.com -t custom-header-check.yaml
```

---

## 🧰 Full Recon Pipeline Example

```bash
#!/bin/bash
# Basic recon pipeline — educational/authorized use only
TARGET="example.com"
OUTPUT="recon_$TARGET"
mkdir -p $OUTPUT

echo "[*] Finding subdomains..."
subfinder -d $TARGET -silent -o $OUTPUT/subs.txt

echo "[*] Probing alive hosts..."
httpx -l $OUTPUT/subs.txt -status-code -title -tech-detect \
  -silent -o $OUTPUT/alive.txt

echo "[*] Directory fuzzing main target..."
ffuf -w /usr/share/seclists/Discovery/Web-Content/common.txt \
  -u https://$TARGET/FUZZ -mc 200,301,302 \
  -o $OUTPUT/dirs.json -of json -s

echo "[*] Running nuclei..."
cat $OUTPUT/alive.txt | awk '{print $1}' | \
  nuclei -tags misconfig,exposure,cve \
  -severity medium,high,critical \
  -silent -o $OUTPUT/vulns.txt

echo "[+] Done. Results in $OUTPUT/"
```

---

## 🔗 Related Notes

- [[Networking CLI Tools]]
- [[curl — HTTP Client]]
- [[OWASP Top 10]]
- [[CTF Methodology]]
- [[Burp Suite Basics]]
- [[CS50 Cybersecurity Notes]]

---
*Last updated: 2026-03 | Tools: httpx, gobuster, ffuf, nuclei | Use responsibly.*
