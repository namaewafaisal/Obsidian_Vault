# Linux, Cyber Security, Frontend & Maven — Interview Answers

## How to Answer
Same formula: definition first, mechanism/why second, example only if asked to elaborate.

---

# Linux

## Why it matters
Most real servers — including CodeDash's own deployment — run Linux, not Windows. Comfort in a shell means actually being able to debug/deploy/manage in production, not just in a GUI. Genuine strength: daily-driving Arch, not "used Ubuntu once for a course."

## Core commands to have ready
- Navigate: `ls`, `cd`, `pwd`
- Manipulate: `cp`, `mv`, `rm`, `mkdir`
- View files: `cat`, `less`, `head`, `tail` (`tail -f` for live logs — relevant to debugging a deployed app)
- Search: `grep`
- Permissions/ownership: `chmod`, `chown`
- Processes: `ps`, `top`/`htop`, `kill`

## File permissions
Three groups — **owner, group, others** — three types — **read, write, execute** — shown like `rwxr-xr--`. `chmod 755 file`: owner rwx, group/others r-x (read=4, write=2, execute=1, summed per group). Matters for controlling who can do what on a shared/production server.

## Process management
A process is a running program. View with `ps aux`/`top`, stop with `kill <pid>` (`kill -9` to force). Run in background with `&`, detach fully with `nohup` or a proper background service (e.g., running MPD as a daemon).

## Package management
Most students only know `apt` (Debian/Ubuntu). Genuine edge: comfort with `pacman` (Arch) *and* understanding the AUR.

> "Package managers handle installing, updating, and removing software along with dependencies — I use Arch, so my daily tool is `pacman`, and I'm also comfortable with `apt`-based systems since that's more common in industry."

## Shell scripting
Automates a sequence of commands for repetitive tasks (backups, deployment steps, log rotation). Just need the concept + comfort with a terminal-first workflow — already demonstrated by daily setup.

## Why it matters for the job
Real backend/DevOps work happens over SSH into a Linux server — reading logs, restarting services, checking resource usage — not a GUI. Already fluent in that environment on a personal machine; skills transfer directly.

---

# Cyber Security

## CIA Triad
Foundation most security concepts trace back to:
- **Confidentiality** — only authorized people see the data (encryption, access control).
- **Integrity** — data hasn't been tampered with (checksums, hashing, digital signatures).
- **Availability** — systems/data accessible when needed (DDoS specifically targets this).

## Authentication vs Authorization
**Authentication** — proving who you are (login, JWT). **Authorization** — what you're allowed to do once known (RBAC).

> "In CodeDash, authentication is JWT-based login; authorization is RBAC controlling whether a student vs staff role can access certain endpoints — only staff can trigger the handle data export."

## Common attacks

**SQL Injection** — malicious SQL inserted via an input field, tricking the DB into running unintended commands (bypass auth, dump data). Prevention: parameterized queries/prepared statements — never concatenate user input directly into a query. (Already protected via Spring Data JPA/Hibernate if not using raw string-built SQL.)

**Cross-Site Scripting (XSS)** — malicious JS injected into a page that other users load, runs in their browser session (can steal cookies/session). Prevention: sanitize/escape user input before rendering as HTML.

**Types of XSS:**
- **Stored** — script saved server-side (DB, comment field), served to every viewer later. Most dangerous — hits many victims silently.
- **Reflected** — script is part of the request itself (crafted URL), reflected back immediately, not stored. Needs to trick a specific victim into clicking a malicious link.
- **DOM-based** — vulnerability lives entirely in client-side JS, which unsafely writes untrusted input into the DOM; never touches the server in the malicious flow.

> "They differ in where the malicious script comes from and where it executes — stored is saved server-side and served to many, reflected bounces off a single crafted request, DOM-based never leaves the browser at all."

**Cross-Site Request Forgery (CSRF)** — tricks a logged-in user's browser into submitting an unintended request. Prevention: CSRF tokens verifying the request originated from your own site.

**Man-in-the-Middle (MITM)** — attacker intercepts communication between two parties, can read/alter data in transit. Exactly why HTTPS/TLS exists.

**Phishing** — tricks a person (not a system) into giving up credentials via a fake but convincing email/site. Attack surface is human judgment, not code.

**DDoS** — floods a system with traffic from many sources until it can't serve legitimate requests. Direct attack on Availability.

## Encryption — symmetric vs asymmetric
- **Symmetric** — one key encrypts and decrypts. Fast, but key-sharing is a problem.
- **Asymmetric** — key pair: public (shareable, encrypts) + private (secret, decrypts). Solves key-sharing, computationally heavier.

**HTTPS in practice:** asymmetric encryption used briefly during the TLS handshake to agree on a shared secret, then the session switches to fast symmetric encryption using that secret — gets asymmetric's safety for the handshake and symmetric's speed for the bulk of the session.

## Hashing vs Encryption
Hashing is **one-way** — can't reverse a hash back to the original input (unlike encryption, meant to be reversed with a key). Why passwords are hashed, not encrypted — even if the DB leaks, attacker gets hashes, not actual passwords. Good hash: deterministic, collision-resistant.

**Salting:** adding random data to a password before hashing, so identical passwords don't produce identical hashes — defeats precomputed lookup-table attacks (rainbow tables).

## Firewalls & VPNs
**Firewall** — filters network traffic by rules (ports, IPs, protocols), gatekeeper between trusted/untrusted networks. **VPN** — encrypted tunnel between device and remote network, traffic looks like it originates inside that network, protected from snooping (conceptually adjacent to Tailscale usage).

## OWASP Top 10
Widely referenced list of critical web app security risks, maintained by OWASP. Don't need all ten memorized — know the name exists, and that SQL Injection/XSS/broken authentication are perennial entries.

---

# Frontend Basics (HTML/CSS/JS) — on resume, expect surface-level questions

## HTML
Structures content (headings, paragraphs, forms, links) via elements/tags. Not a programming language — no logic, just structure/meaning.

## CSS
Controls appearance (colors, layout, spacing, responsiveness).
- **Box model** — every element: content → padding → border → margin, outward in that order.
- **Flexbox/Grid** — modern layout systems for arranging elements without old hacks like floats.

## JavaScript
Adds behavior/interactivity — click handling, modifying the page after load, making API calls (what CodeDash's frontend will do: call Spring Boot endpoints, render the response).
- Single-threaded, but handles things like network requests **asynchronously** (callbacks/promises/async-await) so the page doesn't freeze while waiting.

## DOM (Document Object Model)
The browser's in-memory tree representation of HTML, which JS reads and modifies to make pages interactive.

> "JavaScript manipulates the DOM" — the one-line tie-together for how HTML/CSS/JS connect.

---

# Maven — on resume, genuinely used

Build automation and dependency management tool for Java projects.

1. **Dependency management** — declare dependencies (Spring Boot, PostgreSQL driver, JWT library) in `pom.xml`; Maven fetches the exact versions plus their sub-dependencies automatically.
2. **Build lifecycle** — `mvn clean install` compiles code, runs tests, packages into a runnable JAR — repeatable, not manually wired.

`pom.xml` — the config file declaring dependencies, plugins, build settings. Already edited directly (adding the JWT library, debugging the secret-length issue).

> "Maven handles transitive dependencies automatically — if Spring Boot needs ten other libraries internally, I don't have to track those down myself, Maven resolves the whole dependency tree."
