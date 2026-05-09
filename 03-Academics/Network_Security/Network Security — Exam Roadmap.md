# 🔐 Network Security — Exam Roadmap

> **How to use this:** Each `[[Topic]]` is a note you create. The bullet points under each are the exact subtopics to cover inside that note. Depth labels: 🟢 Conceptual (understand + explain) · 🟡 Moderate (understand + apply) · 🔴 Deep (derive/trace/implement logic)

---

## UNIT I — Introduction to Cryptography
> 8 hours | Foundational — everything else builds on this

---

### [[Basics of Cryptography]]
- 🟢 Plaintext, ciphertext, encryption, decryption — definitions and relationships
- 🟢 Cryptanalysis vs cryptography — attacker's goal, types of attacks
- 🟡 Types of attacks: ciphertext-only, known-plaintext, chosen-plaintext, chosen-ciphertext
- 🟢 Passive vs active attacks on cryptosystems
- 🟡 Confusion vs diffusion — Shannon's principles, why they matter in block ciphers
- 🟢 Substitution ciphers (Caesar, Vigenère) — just enough to understand substitution principle
- 🟢 Transposition ciphers — columnar transposition example
- 🟡 Stream ciphers vs block ciphers — key difference, use cases
- 🟢 Kerckhoffs's principle — "security through obscurity" is wrong; algorithm should be public

---

### [[Conventional (Symmetric) Cryptography]]
- 🟡 Symmetric encryption model — shared secret key, sender/receiver roles
- 🔴 DES (Data Encryption Standard)
  - 64-bit block, 56-bit key
  - Feistel structure — 16 rounds, L/R halves, round function
  - Initial permutation (IP) and final permutation (FP)
  - Key schedule — 16 subkeys generated from 56-bit key (PC-1, PC-2, shifts)
  - S-boxes — what they do (non-linearity), why they're critical
  - Why DES is broken — brute force feasible (2^56), EFF DES cracker
- 🟡 Triple DES (3DES) — EDE mode (Encrypt-Decrypt-Encrypt), effective key length, why it's slow
- 🔴 AES (Advanced Encryption Standard)
  - 128-bit block, key sizes: 128/192/256 bits → 10/12/14 rounds
  - State matrix (4×4 bytes)
  - Four transformations per round:
    - **SubBytes** — S-box substitution (GF(2^8) inverse)
    - **ShiftRows** — row-wise cyclic shift
    - **MixColumns** — matrix multiplication in GF(2^8) — skip math, know what it does
    - **AddRoundKey** — XOR with round key
  - Key expansion — how round keys are derived (RotWord, SubWord, Rcon)
  - Final round — no MixColumns
  - Why AES is secure — no known practical attack; NIST selected it in 2001
- 🟡 Block cipher modes of operation
  - **ECB** (Electronic Code Book) — each block independent, insecure (same plaintext → same ciphertext)
  - **CBC** (Cipher Block Chaining) — XOR with previous ciphertext, IV needed
  - **CFB** (Cipher Feedback) — turns block cipher into stream cipher
  - **OFB** (Output Feedback) — keystream independent of plaintext
  - **CTR** (Counter Mode) — parallelizable, nonce + counter encrypted
  - Know when to use which mode (exam favourite)

---

### [[Public-Key Cryptography]]
- 🟢 Asymmetric encryption model — public key encrypts, private key decrypts
- 🟢 Why public-key? — key distribution problem with symmetric crypto
- 🟡 Mathematical trapdoor functions — easy one way, hard to reverse (discrete log, factoring)
- 🔴 RSA Algorithm
  - Key generation steps: choose p, q → n = p×q → φ(n) = (p-1)(q-1) → choose e (gcd(e,φ)=1) → compute d = e⁻¹ mod φ(n)
  - Encryption: C = M^e mod n
  - Decryption: M = C^d mod n
  - Numerical example (small numbers) — you WILL be asked to work through this
  - Security basis: difficulty of integer factorization (IFP)
  - Attacks: chosen-ciphertext, small exponent, timing attacks (conceptual)
- 🟡 Diffie-Hellman Key Exchange
  - Public parameters: prime p, generator g
  - Alice: chooses a, sends g^a mod p; Bob: chooses b, sends g^b mod p
  - Shared secret: (g^b)^a mod p = (g^a)^b mod p
  - Security basis: Discrete Logarithm Problem (DLP)
  - Vulnerability: Man-in-the-Middle (MITM) attack — no authentication
- 🟢 RSA vs Diffie-Hellman — what each is used for (encryption/KE vs key agreement)
- 🟢 Elliptic Curve Cryptography (ECC) — conceptual only; smaller keys, same security level; used in TLS

---

### [[Hash Functions]]
- 🟢 Purpose — integrity, not confidentiality; one-way compression function
- 🟢 Properties of a secure hash function:
  - Pre-image resistance (one-way)
  - Second pre-image resistance (weak collision resistance)
  - Collision resistance (strong collision resistance)
- 🟡 MD5 — 128-bit output, 4 rounds, broken (collisions found) — know why it's deprecated
- 🟡 SHA family
  - SHA-1 — 160-bit, broken (2017 SHAttered collision)
  - SHA-256 / SHA-512 (SHA-2 family) — currently secure, used in TLS/certificates
  - SHA-3 (Keccak) — sponge construction, different design philosophy
- 🟡 Birthday attack — why collision resistance requires 2×(hash length) bits of security; birthday paradox
- 🟢 HMAC (Hash-based MAC)
  - HMAC(K, m) = H((K ⊕ opad) || H((K ⊕ ipad) || m))
  - Provides both integrity AND authentication (unlike plain hash)
  - Used in TLS, JWT, etc.
- 🟢 Use cases: file integrity, password storage (bcrypt/Argon2 in practice), digital signatures, MACs

---

### [[Authentication (Cryptographic)]]
- 🟢 Authentication vs authorization vs identification
- 🟡 Message Authentication Code (MAC) — keyed hash; sender and receiver share key; provides integrity + origin authentication
- 🟡 HMAC construction — see Hash Functions note
- 🟢 CBC-MAC — block cipher based MAC
- 🟡 Authenticated encryption — combining confidentiality and integrity (AES-GCM)
- 🟢 Replay attacks — attacker resends old valid message; countered by nonces, timestamps, sequence numbers

---

### [[Digital Signatures]]
- 🟢 Purpose — non-repudiation + authentication; only sender can create, anyone can verify
- 🟡 RSA Digital Signature
  - Sign: S = H(M)^d mod n (private key on hash)
  - Verify: H(M) =? S^e mod n (public key)
  - Why hash first? — RSA on full message is slow and malleable
- 🟡 DSA (Digital Signature Algorithm)
  - NIST standard; uses discrete log; signature is (r, s) pair
  - Conceptual understanding of sign/verify — no need to memorise math in detail
- 🟡 ECDSA (Elliptic Curve DSA) — same idea as DSA but on elliptic curves; used in Bitcoin, TLS
- 🟢 Differences: Encryption (public key) vs Signature (private key) — know which key does what
- 🟢 Certificate = public key + identity + CA signature — bridge to PKI

---

## UNIT II — Key Management and Authentication
> 7 hours | Critical for PKI + Kerberos questions

---

### [[Symmetric Key Distribution]]
- 🟢 Problem: how do two parties securely share a symmetric key?
- 🟡 Key Distribution Center (KDC) — trusted third party; each user shares master key with KDC only
- 🟡 Session key vs master key — session key temporary, master key long-lived
- 🟡 Needham-Schroeder Protocol
  - 5-message exchange; uses nonces to prevent replay
  - Step-by-step: A→KDC, KDC→A, A→B, B→A, A→B (mutual authentication)
  - Weakness: replay attack if old session key compromised — leads to Kerberos
- 🟢 Key hierarchy: master key → session key → derived keys

---

### [[Distribution of Public Keys]]
- 🟢 Problem: how do you know a public key truly belongs to who claims it?
- 🟡 Four approaches:
  1. Public announcement — insecure (impersonation)
  2. Publicly available directory — requires trusted directory
  3. Public-key authority — online trusted server, bottleneck
  4. Public-key certificates — offline verification; scalable
- 🟡 Public Key Certificate structure: Subject, Issuer, Subject Public Key, Validity, Serial Number, Signature Algorithm, CA Signature
- 🟢 Certificate chain of trust — root CA → intermediate CA → end-entity cert

---

### [[X.509 Certificates]]
- 🔴 X.509 v3 Certificate structure (ITU-T standard)
  - Version (v1/v2/v3)
  - Serial number
  - Signature algorithm OID
  - Issuer (CA's Distinguished Name)
  - Validity (notBefore, notAfter)
  - Subject (DN: CN, O, OU, C, etc.)
  - Subject Public Key Info (algorithm + public key)
  - Extensions (v3): Key Usage, Subject Alt Name, Basic Constraints, CRL Distribution Points
- 🟡 Certificate verification process:
  1. Check signature using CA's public key
  2. Check validity period
  3. Check revocation status (CRL or OCSP)
  4. Build chain to trusted root CA
- 🟡 Certificate Revocation List (CRL) — list of revoked serial numbers, published by CA
- 🟡 OCSP (Online Certificate Status Protocol) — real-time revocation check; response: good/revoked/unknown
- 🟡 Certificate chain/hierarchy — root CA → intermediate CA → leaf certificate
- 🟢 Self-signed certificate — issuer = subject; used for root CAs

---

### [[Public Key Infrastructure (PKI)]]
- 🟢 PKI components: CA (Certificate Authority), RA (Registration Authority), certificate repository, CRL/OCSP
- 🟡 CA responsibilities: issue, sign, revoke certificates
- 🟡 RA responsibilities: verify identity before CA issues cert
- 🟡 PKI trust models:
  - Hierarchical (single root CA) — most common; Windows, browsers
  - Web of Trust (PGP model) — decentralized; users sign each other's keys
  - Bridge CA — connects multiple hierarchies
- 🟢 Certificate path validation — building chain from leaf to trusted root
- 🟢 Real-world PKI: Browser trust stores (Mozilla NSS, Windows CertStore), Let's Encrypt (ACME protocol)

---

### [[Remote User-Authentication Principles]]
- 🟢 Identification vs authentication — who you are vs proving you are
- 🟡 Three authentication factors:
  - Something you know (password, PIN)
  - Something you have (OTP token, smart card)
  - Something you are (biometrics)
- 🟡 Multi-factor authentication (MFA) — combining two or more factors
- 🟢 Mutual authentication — both parties authenticate each other
- 🟡 Replay attack prevention: nonces, timestamps, challenge-response
- 🟢 Dictionary attack vs brute force vs credential stuffing

---

### [[Remote User-Authentication Using Symmetric Encryption]]
- 🟡 Challenge-Response with shared secret key
  - Server sends nonce (challenge)
  - Client encrypts nonce with shared key and sends back
  - Server decrypts and verifies
- 🟡 One-way vs mutual authentication with symmetric crypto
- 🟡 Timestamp-based authentication — Alice sends E_K(timestamp); server verifies within clock skew window
- 🟢 Weakness: requires pre-shared key; doesn't scale; leads to Kerberos

---

### [[Kerberos]]
- 🔴 Kerberos architecture
  - Key Distribution Center (KDC) = Authentication Server (AS) + Ticket Granting Server (TGS)
  - Realm — administrative domain
  - Principals: clients (users), servers (services)
- 🔴 Kerberos v5 Authentication Flow (6 messages — memorise this)
  1. Client → AS: username (plaintext)
  2. AS → Client: {TGT}_K_TGS + {Session key K_C,TGS}_K_C
     - TGT encrypted with TGS's secret key
     - Session key encrypted with client's key (derived from password)
  3. Client → TGS: TGT + Authenticator_{K_C,TGS} + service name
     - Authenticator = {client, timestamp} encrypted with session key
  4. TGS → Client: {Service Ticket}_K_S + {Session key K_C,S}_K_C,TGS
  5. Client → Server: Service Ticket + Authenticator_{K_C,S}
  6. Server → Client (optional): {timestamp+1}_{K_C,S} — mutual auth
- 🟡 Ticket Granting Ticket (TGT) — cached; avoids re-entering password; has lifetime
- 🟡 Authenticator — prevents replay; contains timestamp; single-use
- 🟡 Kerberos v4 vs v5 differences — v5 adds realm support, longer lifetimes, mutual auth
- 🟢 Kerberos weaknesses: password guessing (AS-REP roasting), single point of failure, clock sync required (±5 min default)
- 🟢 Real-world use: Active Directory, MIT Kerberos

---

### [[Remote User-Authentication Using Asymmetric Encryption]]
- 🟡 Challenge-response with public keys
  - Server sends nonce
  - Client signs nonce with private key
  - Server verifies with client's public key
- 🟡 Mutual authentication — both parties sign nonces
- 🟢 How TLS client certificate authentication works (preview of Unit III)
- 🟢 Advantage over symmetric: no shared secret to steal; scalable; enables non-repudiation

---

## UNIT III — Access Control and Security Protocols
> 4 hours | Conceptual + protocol details

---

### [[Network Access Control (NAC)]]
- 🟢 NAC concept — enforce security policy before granting network access (is your device patched? has AV?)
- 🟡 NAC components: requestor (endpoint), policy enforcement point (switch/AP), policy decision point (server)
- 🟡 Pre-admission vs post-admission NAC
- 🟢 Remediation — quarantine VLAN; fix device then re-admit
- 🟢 Use cases: enterprise BYOD, VPN access control

---

### [[Extensible Authentication Protocol (EAP)]]
- 🟡 EAP — authentication framework (not a specific method); runs over various transports
- 🟡 EAP methods:
  - EAP-TLS — certificate-based, mutual auth; strongest; requires client cert
  - EAP-TTLS — server cert only; tunnel for legacy methods
  - PEAP (Protected EAP) — wraps inner method in TLS tunnel; common in WPA2-Enterprise
  - EAP-MD5 — challenge-response; no mutual auth; deprecated
- 🟡 EAP roles: Supplicant (client) ↔ Authenticator (AP/switch) ↔ Authentication Server (RADIUS)
- 🟡 EAP over LAN (EAPOL) — how EAP messages are carried on wired/wireless LAN

---

### [[IEEE 802.1X Port-Based NAC]]
- 🟡 802.1X mechanism — port blocked until authentication succeeds
- 🟡 Three entities: Supplicant, Authenticator, Authentication Server (RADIUS/DIAMETER)
- 🟡 RADIUS (Remote Authentication Dial-In User Service) — UDP-based; carries EAP messages between authenticator and AS
- 🟡 Authentication flow:
  1. Client connects → port in unauthorized state
  2. Authenticator sends EAP-Request/Identity
  3. Client sends EAP-Response/Identity
  4. Authenticator forwards to RADIUS server
  5. RADIUS does authentication (EAP exchange)
  6. RADIUS sends Access-Accept or Access-Reject
  7. Port moves to authorized state
- 🟢 Wired vs wireless 802.1X — same protocol, different physical layer
- 🟢 WPA2-Enterprise = 802.1X + EAP + CCMP (AES) encryption

---

### [[IP Security (IPSec)]]
- 🟡 IPSec purpose — secure IP layer communications (VPNs, site-to-site tunnels)
- 🟡 IPSec modes:
  - **Transport mode** — encrypts payload only; IP header intact; used for host-to-host
  - **Tunnel mode** — encrypts entire original IP packet + new IP header; used for VPN gateways
- 🟡 IPSec protocols:
  - **AH (Authentication Header)** — integrity + authentication, NO encryption; covers entire packet incl. IP header
  - **ESP (Encapsulating Security Payload)** — integrity + authentication + encryption; most common
- 🟡 Security Association (SA) — one-way; identified by SPI (Security Parameter Index) + destination IP + protocol
- 🟡 Security Association Database (SAD) — stores active SAs
- 🟡 Security Policy Database (SPD) — rules: protect / bypass / discard traffic
- 🔴 ESP packet format (Tunnel mode):
  - New IP Header | ESP Header (SPI, Seq#) | [Original IP Hdr | Payload] encrypted | ESP Trailer | ESP Auth
- 🟢 Anti-replay: sequence numbers in AH/ESP; sliding window on receiver side

---

### [[Internet Key Exchange (IKE)]]
- 🟡 IKE purpose — automated SA negotiation and key management for IPSec
- 🟡 IKEv1 phases:
  - **Phase 1 (Main Mode / Aggressive Mode)** — establish IKE SA (secure channel); 6 messages (Main) or 3 (Aggressive)
  - **Phase 2 (Quick Mode)** — negotiate IPSec SAs using IKE SA; 3 messages
- 🟡 IKEv2 — simpler; 4 messages to establish IKE SA + Child SA; supports EAP; more efficient
- 🟡 Authentication methods in IKE: pre-shared key (PSK), RSA signatures, EAP
- 🟡 Diffie-Hellman groups — used in IKE to derive shared secret; group = DH parameters
- 🟢 Perfect Forward Secrecy (PFS) — new DH exchange for each Child SA; compromise of long-term key doesn't expose past sessions

---

### [[Transport Layer Security (TLS)]]
- 🔴 TLS architecture — layered protocol: Record Protocol + Handshake, ChangeCipherSpec, Alert sub-protocols
- 🔴 TLS 1.2 Handshake (know every message):
  1. ClientHello — TLS version, random, cipher suites, extensions
  2. ServerHello — chosen cipher suite, server random
  3. Certificate — server's X.509 cert
  4. ServerKeyExchange — (if DHE/ECDHE) DH parameters signed by server
  5. ServerHelloDone
  6. ClientKeyExchange — client's DH public value (or pre-master secret encrypted with RSA)
  7. ChangeCipherSpec (client)
  8. Finished (client) — PRF over handshake transcript
  9. ChangeCipherSpec (server)
  10. Finished (server)
- 🟡 TLS 1.3 handshake — 1-RTT (vs 1.5-RTT in TLS 1.2); removed RSA key exchange; mandatory forward secrecy; 0-RTT resumption
- 🔴 Master Secret derivation (TLS 1.2): pre_master_secret → master_secret via PRF(pre_master_secret, "master secret", ClientRandom + ServerRandom)
- 🔴 Key material generation — master_secret → PRF → key_block → client/server write keys + MACs + IVs
- 🟡 Cipher suite notation: `TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256`
  - Key exchange: ECDHE; Authentication: RSA; Cipher: AES-128-GCM; PRF/MAC: SHA256
- 🟡 TLS Record Protocol — fragment → compress (deprecated) → MAC → encrypt → header
- 🟢 TLS alerts — fatal vs warning; close_notify, certificate_expired, etc.
- 🟡 TLS session resumption — session ID (TLS 1.2) vs PSK/session tickets (TLS 1.3)
- 🟢 TLS vulnerabilities: POODLE (SSLv3), BEAST (CBC IV), Heartbleed (OpenSSL bug), CRIME (compression)

---

### [[HTTPS]]
- 🟡 HTTPS = HTTP over TLS (port 443)
- 🟡 Certificate validation in browsers: chain of trust, hostname verification (SAN/CN), revocation check
- 🟢 HTTP Strict Transport Security (HSTS) — server tells browser to always use HTTPS; prevents SSL stripping
- 🟢 Certificate Transparency (CT) — public log of issued certificates; detects mis-issuance
- 🟢 Mixed content — HTTP resources on HTTPS page; browsers block active mixed content

---

### [[SSL (Secure Sockets Layer)]]
- 🟢 SSL history: SSL 2.0, SSL 3.0 → TLS 1.0 (essentially SSL 3.1) → TLS 1.1/1.2/1.3
- 🟢 SSL is deprecated — all versions have known vulnerabilities (POODLE for SSLv3)
- 🟡 SSL vs TLS differences — primarily TLS has stronger MAC, better key derivation, no known attacks

---

### [[Secure Shell (SSH)]]
- 🟡 SSH architecture — three protocols layered:
  - **Transport Layer Protocol** — server authentication, encryption, integrity; runs over TCP port 22
  - **User Authentication Protocol** — authenticates client to server (password, public key, keyboard-interactive)
  - **Connection Protocol** — multiplexes logical channels (shell, X11, port forwarding)
- 🟡 SSH public key authentication:
  - Client stores private key; server stores authorized_keys (public key)
  - Challenge-response: server encrypts nonce with client pubkey; client decrypts with privkey
- 🟡 Known hosts — client stores server fingerprint in `~/.ssh/known_hosts`; TOFU (Trust On First Use)
- 🟡 SSH tunneling / port forwarding:
  - Local forwarding: `ssh -L localport:remotehost:remoteport`
  - Remote forwarding: `ssh -R`
  - Dynamic (SOCKS proxy): `ssh -D`
- 🟢 SSH key types: RSA, ECDSA, Ed25519 (most modern)
- 🟢 SSH vs Telnet — Telnet sends everything in plaintext; SSH encrypts all traffic

---

## UNIT IV — Application Layer Security
> 5 hours | Email security + wireless

---

### [[Pretty Good Privacy (PGP)]]
- 🟡 PGP — hybrid cryptosystem for email; Phil Zimmermann, 1991
- 🔴 PGP services and operations:
  - **Authentication**: hash message → sign with sender's private RSA/DSS key → attach signature
  - **Confidentiality**: generate random session key → encrypt message with session key (CAST/AES) → encrypt session key with recipient's public key → attach
  - **Compression**: zip before encryption (after signature — important order)
  - **Radix-64 (Base64)**: convert binary to printable ASCII for email transport
  - **Segmentation**: split large messages for email size limits
- 🔴 Combined auth + confidentiality message structure (know the layers):
  1. Plaintext M
  2. Hash(M) → signed with sender privkey → Z = Sig + M
  3. Compress Z
  4. Encrypt with session key K_s → C
  5. Encrypt K_s with recipient pubkey → E(K_s)
  6. Final message: E(K_s) || C → Base64
- 🟡 PGP key rings — public keyring (others' keys) + private keyring (your keys, encrypted with passphrase)
- 🟡 PGP trust model (Web of Trust)
  - No central CA; users sign each other's keys
  - Owner trust vs key legitimacy
  - Ultimate, full, marginal, untrusted trust levels
- 🟢 PGP vs S/MIME — both secure email; PGP = Web of Trust; S/MIME = hierarchical CA (X.509)

---

### [[S/MIME]]
- 🟡 S/MIME (Secure/Multipurpose Internet Mail Extensions) — IETF standard; uses X.509 certificates
- 🟡 S/MIME content types:
  - `application/pkcs7-mime; smime-type=enveloped-data` — encrypted
  - `application/pkcs7-mime; smime-type=signed-data` — signed opaque
  - `multipart/signed` — clear signed (body readable without S/MIME support)
- 🟡 S/MIME operations: sign, encrypt, sign+encrypt, verify, decrypt
- 🟡 EnvelopedData structure — session key encrypted per recipient with their public key (supports multiple recipients)
- 🟢 S/MIME vs PGP: S/MIME uses PKI/CA hierarchy; PGP uses Web of Trust; S/MIME integrated in Outlook/Apple Mail

---

### [[DKIM (DomainKeys Identified Mail)]]
- 🟡 DKIM purpose — prove email came from claimed domain; prevent spoofing; doesn't encrypt
- 🟡 How DKIM works:
  1. Sending server signs email headers + body hash with domain's private key
  2. `DKIM-Signature:` header added with signature, selector, domain, algorithm, signed headers
  3. Receiving server fetches public key from DNS TXT record (`selector._domainkey.domain.com`)
  4. Verifies signature; result added to `Authentication-Results:` header
- 🟡 DKIM selector — allows multiple keys per domain (rotation, different services)
- 🟢 DKIM + SPF + DMARC trio:
  - **SPF** — which IPs can send for domain
  - **DKIM** — cryptographic signature of message
  - **DMARC** — policy: what to do if SPF/DKIM fail (none/quarantine/reject); alignment requirement

---

### [[Wireless Network Security]]
- 🟡 WEP (Wired Equivalent Privacy) — BROKEN; RC4 with 24-bit IV; IV reuse leads to keystream recovery; no MIC
- 🟡 WPA (Wi-Fi Protected Access) — transitional; TKIP (Temporal Key Integrity Protocol) — RC4 + per-packet keys + MIC (Michael)
- 🔴 WPA2 (IEEE 802.11i)
  - CCMP (Counter Mode CBC-MAC Protocol) — AES-based; 128-bit key; mandatory
  - TKIP optional (legacy)
  - Personal (PSK) vs Enterprise (802.1X + EAP)
  - 4-Way Handshake — derives PTK (Pairwise Transient Key) from PMK + nonces
    - ANonce (AP) + SNonce (client) + MACs → PTK = KCK + KEK + TK
  - Group Key Handshake — distributes GTK (Group Temporal Key) for multicast/broadcast
- 🟡 WPA3 improvements over WPA2:
  - SAE (Simultaneous Authentication of Equals) replaces PSK — Dragonfly handshake; forward secrecy; no offline dictionary attacks
  - Enterprise: 192-bit security suite
  - OWE (Opportunistic Wireless Encryption) — encrypts open networks
- 🟡 KRACK (Key Reinstallation Attack) — WPA2 vulnerability; nonce reuse via handshake replay; patched
- 🟢 Rogue AP / Evil Twin attacks
- 🟢 De-authentication attack — 802.11 management frames unauthenticated; patched in 802.11w (Protected Management Frames)

---

### [[Mobile Device Security]]
- 🟢 Mobile threat landscape: malware, data leakage, insecure Wi-Fi, lost/stolen device
- 🟡 Mobile Device Management (MDM) — enterprise control: enforce PIN, remote wipe, app whitelist, VPN
- 🟡 iOS security model:
  - App sandbox — each app isolated
  - Code signing — only Apple-signed code (or enterprise certs) runs
  - Secure Enclave — hardware security module; stores keys, biometric data
  - Secure Boot chain — each stage verified
- 🟡 Android security model:
  - Linux-based; each app runs as separate UID
  - Permission model — runtime permissions (Android 6+)
  - Google Play Protect — malware scanning
  - SELinux enforcement
- 🟡 OWASP Mobile Top 10 — know top items: improper credential usage, insecure data storage, insecure communication, insufficient input validation
- 🟢 Jailbreaking / rooting — security implications
- 🟢 App transport security — iOS forces HTTPS; certificate pinning

---

## UNIT V — Security Practices
> 6 hours | Firewalls, IDS, password management

---

### [[Intrusion Detection Systems (IDS)]]
- 🟡 IDS purpose — detect attacks/intrusions; passive monitoring; compare to IPS (prevention)
- 🟡 IDS types:
  - **NIDS** (Network IDS) — monitors network traffic; placed at network chokepoints
  - **HIDS** (Host IDS) — monitors system calls, logs, file integrity on single host
  - **Distributed IDS** — correlation across multiple sensors
- 🔴 Detection methods:
  - **Signature-based (misuse detection)**
    - Compares traffic to known attack signatures (like AV)
    - Low false positives; can't detect unknown (zero-day) attacks
    - Database must be updated continuously
  - **Anomaly-based (statistical detection)**
    - Builds profile of normal behaviour; alerts on deviation
    - Can detect zero-days; higher false positive rate
    - Profiles: statistical (mean/std dev), threshold, rule-based
- 🟡 False positive vs false negative tradeoff — FP = alert on benign traffic (cry wolf); FN = miss real attack
- 🟡 IDS metrics: detection rate, false alarm rate, ROC curve concept
- 🟡 Snort — popular open-source NIDS; rule syntax: `alert tcp any any -> $HOME_NET 80 (msg:"HTTP attack"; content:".."; sid:1000001;)`
- 🟡 IDS evasion techniques: fragmentation, encryption, low-and-slow attacks, polymorphic malware
- 🟢 SIEM (Security Information and Event Management) — aggregates IDS + log data for correlation

---

### [[Password Management]]
- 🟡 Password storage — NEVER store plaintext; hash with salt
- 🟡 Salted hash — salt is random per-user value stored alongside hash; defeats rainbow tables
- 🟡 Password hashing functions (slow by design):
  - **bcrypt** — Blowfish-based; work factor (cost) parameter; 60-char output
  - **scrypt** — memory-hard; harder to parallelize on GPU/ASIC
  - **Argon2** — PBKDF competition winner; configurable time/memory/parallelism
  - **PBKDF2** — NIST-approved; iterations parameter; used in WPA2 PSK derivation
- 🟡 Password attacks:
  - Brute force — every combination; slow but complete
  - Dictionary attack — wordlist-based; effective against weak passwords
  - Rainbow table attack — precomputed hash chains; defeated by salting
  - Credential stuffing — reuse leaked (email, password) pairs from breaches
- 🟡 Password policies — length (≥12), complexity, no password reuse, breach detection (HaveIBeenPwned API)
- 🟡 Multi-factor auth as mitigation — stolen password alone not enough
- 🟢 Password managers — encrypted vault; single master password; reduces reuse
- 🟢 Passkeys (FIDO2/WebAuthn) — replacing passwords; device-bound key pair; phishing-resistant

---

### [[Firewall Characteristics]]
- 🟡 Firewall purpose — enforce access control between network segments; implement security policy
- 🟡 What firewalls do (and don't do):
  - Do: filter by IP, port, protocol, state, application
  - Don't: protect against insider threats, encrypted tunnels bypassing it, zero-day in allowed traffic
- 🟡 Firewall policy: default deny (whitelist) vs default permit (blacklist) — default deny is correct
- 🟢 Stateful vs stateless inspection — stateless: each packet independent; stateful: tracks TCP connections

---

### [[Types of Firewalls]]
- 🔴 Packet Filtering Firewall
  - Operates at Layer 3/4; examines IP header + TCP/UDP header
  - Rules: source IP, dest IP, source port, dest port, protocol, interface, direction
  - Stateless — can't track connection state
  - Fast; limited; vulnerable to IP spoofing, fragment attacks
- 🔴 Stateful Inspection Firewall (Dynamic Packet Filtering)
  - Maintains state table of active connections (src IP, src port, dst IP, dst port, state)
  - Validates packets belong to established connections
  - Detects TCP state violations (FIN without SYN, etc.)
  - More secure than packet filtering; most common enterprise firewall type
- 🔴 Application-Layer Gateway (Proxy Firewall)
  - Operates at Layer 7; full content inspection
  - Acts as proxy: client connects to firewall, firewall connects to server
  - Understands application protocols (HTTP, FTP, DNS)
  - Can detect application-layer attacks; slower; must have proxy for each protocol
- 🔴 Circuit-Level Gateway
  - Layer 5 (session layer); validates TCP handshake but doesn't inspect content
  - SOCKS proxy is an example
  - Hides internal network addresses
- 🟡 Next-Generation Firewall (NGFW)
  - Deep Packet Inspection (DPI) — inspects payload, not just headers
  - Application identification (regardless of port)
  - User identity awareness (integrates with AD/LDAP)
  - IPS/IDS integration, SSL inspection, threat intelligence feeds
- 🟡 WAF (Web Application Firewall) — HTTP-specific; protects against SQLi, XSS, CSRF

---

### [[Firewall Basing]]
- 🟡 Software firewall — runs on general-purpose OS (iptables/nftables on Linux, Windows Firewall)
  - Pros: cheap, flexible; Cons: OS vulnerabilities affect firewall
- 🟡 Hardware firewall (appliance) — dedicated hardware + hardened OS (Cisco ASA, Palo Alto, Fortinet)
  - Pros: high performance, no OS attack surface; Cons: expensive, vendor lock-in
- 🟡 Host-based firewall — on endpoint itself; first/last line of defence
- 🟡 Network-based firewall — at network perimeter or segment boundaries
- 🟢 Cloud firewall / FWaaS (Firewall as a Service) — cloud-native; AWS Security Groups, Azure NSGs

---

### [[Firewall Location and Configurations]]
- 🔴 DMZ (Demilitarized Zone) architecture
  - Screened subnet between two firewalls
  - External firewall: Internet → DMZ (allow only HTTP/HTTPS/DNS to public servers)
  - Internal firewall: DMZ → Internal (strict; servers shouldn't initiate connections inward)
  - Public servers (web, mail, DNS) in DMZ; internal DB/app servers never directly exposed
- 🔴 Network topologies:
  - **Single firewall** — one device; simple; single point of failure
  - **Screened host** — packet filter + bastion host; two layers
  - **Screened subnet (DMZ)** — two firewalls + DMZ; most secure standard design
- 🟡 Bastion host — highly hardened, exposed host; minimal services; heavily audited
- 🟡 Firewall rules order — first match wins; more specific rules before general; deny-all last
- 🟡 Ingress vs egress filtering — ingress: block inbound spoofed IPs; egress: block outbound malware C2
- 🟡 VPN termination location — at firewall perimeter (IPSec/SSL VPN); traffic decrypted then inspected
- 🟢 High availability (HA) firewall pairs — active/standby with state synchronization
- 🟢 Zero Trust Network Architecture (ZTNA) — "never trust, always verify"; micro-segmentation; replace traditional perimeter model

---

## 🗂️ Quick Reference Index

| Unit | Key Topics to Prioritise |
|------|--------------------------|
| I | RSA (with numericals), AES round operations, TLS Master Secret, HMAC |
| II | Kerberos 6-message flow, X.509 cert structure, PKI trust models |
| III | IPSec AH vs ESP modes, TLS 1.2 handshake all 10 messages, 802.1X flow |
| IV | PGP combined operation message structure, WPA2 4-way handshake, DKIM |
| V | Firewall types (4 types + NGFW), IDS detection methods, DMZ design, password hashing |

---

## 📝 Exam Pattern Tips

- **Derivation/Trace questions**: RSA key gen + encrypt/decrypt (numerical), DES round, Kerberos message flow, TLS key derivation, PGP message layers
- **Compare and contrast**: Symmetric vs Asymmetric, AH vs ESP, PGP vs S/MIME, IDS anomaly vs signature, firewall types
- **Diagram questions**: DMZ topology, Kerberos architecture, PKI hierarchy, IPSec tunnel mode packet, TLS handshake sequence diagram
- **Short answer hotspots**: HMAC, birthday attack, HSTS, DKIM selector, salted hash, OCSP vs CRL, IKE phases, EAP methods, WPA3 SAE

---

*Created for Network Security — SRM TRP Engineering College | Exam Prep Roadmap*
