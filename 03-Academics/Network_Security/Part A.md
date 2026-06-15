Let's strip away all the dense corporate and cryptographic jargon. When you are writing a 2-mark answer, you need **plain English definitions with simple, direct technical facts** that an examiner can check off instantly.

Here is the entire Part A set, rewritten to be deeply understandable, easy to memorize, and direct.

---

### Batch 1: Highest Repeated Questions

#### 1. Conventional (Symmetric) vs. Public-Key (Asymmetric) Encryption

* **What it means:** Symmetric encryption uses **one single key** to both lock (encrypt) and unlock (decrypt) the message. Asymmetric encryption uses a **pair of keys**: a *Public Key* that anyone can use to lock messages, and a *Private Key* that only the owner keeps secret to unlock them.
* **Key difference:** Symmetric is incredibly fast but has a major flaw: both people must secretly share the key beforehand. Asymmetric is slower but solves this by letting you share your public key openly with the world.
* **Examples:** Symmetric: AES. Asymmetric: RSA.

#### 2. Replay Attack Countermeasures

* **What it means:** In a replay attack, an attacker records an old, valid encrypted message (like "Deduct $100 from my account") and sends it again later. The server needs to know if the message is fresh or a recording.
* **The 3 Defenses to Write:**
* **Timestamps:** The message includes the exact time it was sent. If it arrives too late, the server drops it.
* **Nonces:** The server gives the user a random, one-time-use number. If that exact number is used again, it is blocked.
* **Sequence Numbers:** Every packet is numbered in order ($1, 2, 3\dots$). The server rejects duplicate or out-of-order numbers.



#### 3. X.509 Certificate Structure

* **What it means:** An X.509 certificate is a digital ID card issued by a trusted third party (a Certificate Authority) to prove that a public key actually belongs to a specific company or website.
* **The Core Fields to Write:**
1. *Version:* The standard layout version being used.
2. *Serial Number:* A unique ID number for the certificate.
3. *Issuer:* The trusted authority who made and signed it.
4. *Validity Period:* The start and expiration dates.
5. *Subject:* The owner of the certificate (e.g., `google.com`).
6. *Subject's Public Key:* The actual public key being verified.
7. *Signature:* The digital seal of the authority to prevent tampering.



#### 4. Message Digest

* **What it means:** A message digest is a fixed-length "digital fingerprint" of a file or message. It is created by running data through a mathematical formula called a hash function.
* **Key Properties:**
* **Integrity:** If you change even a single letter in an entire book, the resulting fingerprint changes completely (this is called the *avalanche effect*).
* **One-Way:** You can easily turn a file into a fingerprint, but it is impossible to rebuild the original file using only the fingerprint.
* **Examples:** SHA-256, MD5.



#### 5. Extensible Authentication Protocol (EAP)

* **What it means:** EAP is a flexible framework or "wrapper" for logging into a network. It doesn't use one specific login method; instead, it provides a standard way to pass different login tools (like passwords, smart cards, or fingerprints) across a network.
* **Where it is used:** It is heavily used in corporate Wi-Fi setups. It allows network hardware (like routers) to handle new login methods without needing a complete hardware update.

---

### Batch 2: Moderately Repeated Questions

#### 6. X.509 Certificate Role

* **What it means:** While the structure is what's inside the card, its role is how it is used.
* **The Answer:** * **Identity Verification:** It proves to a browser that a website is legitimate and not a fake imposter site.
* **Secure Key Setup:** It securely delivers the website’s public key to your browser, allowing both sides to set up a safe, encrypted session.



#### 7. Remote User Authentication

* **What it means:** This is the process of proving who you are when logging into a network from a remote location over the internet.
* **The Answer:** It forces the remote user to present valid credentials (like a password or authorization token). It is essential because it serves as the frontline gatekeeper, blocking hackers from accessing internal company systems from anywhere in the world.

#### 8. Intruder Classifications

* **What it means:** An intruder is anyone who accesses a system without permission. There are three classic types:
* **The 3 Types to Write:**
1. **Masquerader:** An outsider who steals a legitimate user's password and pretends to be them.
2. **Misfeasor (Willful Exploiter):** An insider (employee) who has access but abuses their permissions to steal or look at private data.
3. **Clandestine User:** An attacker who takes over administrative (root) controls to delete security logs and evade detection.



#### 9. Organizational Password Policy

* **What it means:** A set of technical rules a company enforces to stop users from picking weak passwords that hackers can guess easily.
* **The 4 Rules to Write:**
* **Length:** Must be at least 12–16 characters long.
* **Complexity:** Must use mixed letters, numbers, and special characters (like `!`, `@`, `#`).
* **Expiration:** Must be changed every 90 days, and you cannot reuse old passwords.
* **Lockout:** Automatically locks the account after 3 to 5 failed attempts to stop guessing bots.



#### 10. DomainKeys Identified Mail (DKIM)

* **What it means:** DKIM is a tool used to stop email spoofing (where a hacker fakes the "From" address in a phishing email).
* **How it works:** The sender's mail server adds a hidden cryptographic digital signature to the email header. The receiving server automatically looks up the sender's public key on the internet and uses it to verify that the email truly came from that domain and was not altered along the way.

---

### Batch 3: Single Appearance Questions

#### 11. Firewall Types and Examples

* **What it means:** A firewall is a barrier that filters incoming and outgoing network traffic based on rules.
* **The 3 Main Types:**
* **Packet-Filtering:** Inspects basic headers (IP addresses and ports) one by one.
* **Stateful Inspection:** Remembers the context of an open connection; it only allows incoming traffic if you explicitly requested it first.
* **Application Proxy:** Directly opens the data payload to inspect actual application content and commands before letting it pass.



#### 12 & 13. Caesar Cipher Calculations (Key = 3)

* **The Rule:** Shift every letter forward by 3 places in the alphabet ($A \rightarrow D$, $B \rightarrow E$, etc.).
* **Example 1:** `hello` $\rightarrow$ **`khoor`**
* **Example 2:** `missscarlet` $\rightarrow$ **`plvvvfdvohw`**

#### 14. Five Ingredients of a Symmetric Cipher

* **The 5 Items to Write:**
1. **Plaintext:** The original readable message.
2. **Encryption Algorithm:** The mathematical process used to scramble the message.
3. **Secret Key:** The unique key used to lock and unlock the data.
4. **Ciphertext:** The final scrambled, unreadable output message.
5. **Decryption Algorithm:** The reverse process that turns the ciphertext back into plaintext using the key.



#### 15. Cryptographic Hash Function

* **What it means:** A mathematical tool that takes an input of any size and condenses it into a fixed-size unique output value.
* **Key Detail:** It is strictly a one-way pipeline; you cannot figure out the original file from the hash.
* **Example:** SHA-256.

#### 16. Importance of Key Distribution

* **The Answer:** Key distribution is the process of securely sending encryption keys to the people who need them. It is critical because if an attacker intercepts the key while it is being shared, they can unlock all of your encrypted data, completely ruining your security.

#### 17. Four Schemes for Public Key Distribution

* **The 4 Schemes:**
1. Public Announcement (putting it on a public profile).
2. Publicly Available Directories (a phonebook of keys).
3. Public-Key Authorities (centralized automated request servers).
4. Public-Key Certificates (trusted CAs signing identity cards).



#### 18. Examples of Denial of Service (DoS) Attacks

* **The 2 Examples:**
* **SYN Flood:** Blasting a server with fake connection requests to exhaust its memory and crash it.
* **Ping of Death:** Sending giant, broken network packets that cause the target system to freeze up.



#### 19. Why Wireless Networks have Higher Security Risks than Wired

* **The 2 Reasons:**
* **Open Air Channel:** Anyone with a wireless card standing nearby can sniff your data out of the air; they don't need to physically plug into a wire.
* **Rogue Access Points:** It is very easy for an employee to secretly plug a cheap wireless router into an office wall, creating an insecure entry point for hackers.



#### 20. Two Functionalities of S/MIME (Secure Email)

* **The 2 Features:**
* **Enveloped Data (Confidentiality):** Encrypts the email content so only the recipient can read it.
* **Signed Data (Integrity & Authenticity):** Adds a digital signature to prove who sent the email and ensure it wasn't edited.



#### 21. Advantages of an Application Proxy Firewall

* **The 2 Advantages:**
* **Deep Data Checking:** It reads the actual contents of the message, not just the network routing labels.
* **Hides the Network:** It acts as a complete middleman, hiding internal computer IP addresses from the outside world.



#### 22. Countermeasures for Malicious Intruders in Cloud Platforms

* **The 2 Defenses:**
* **Multi-Factor Authentication (MFA):** Requiring a code from your phone alongside a password to stop stolen credential attacks.
* **Automated Activity Monitoring:** Using AI-driven cloud tools (like AWS GuardDuty) to spot weird file access patterns and sound an alarm.



#### 23. Two Services Provided by the SSL Record Protocol

* **The 2 Services:**
* **Confidentiality:** It encrypts the live data payload being sent over the connection.
* **Message Integrity:** It uses a mathematical code (MAC) to prove the packets weren't modified in transit.



#### 24. IEEE 802.1X Port

* **What it means:** A physical or logical network connection point (like a wall jack or Wi-Fi channel) that defaults to an "unauthorized" state. It will only allow security authentication messages to pass through until the user successfully proves their identity, at which point it opens up for normal internet access.

#### 25. Why PGP Emails Need Segmentation and Reassembly

* **The Answer:** Encrypted files and emails can be massive, but traditional email delivery systems (like SMTP) limit the maximum file size you can send at once. PGP fixes this by automatically breaking a large encrypted email into small pieces before sending, and the recipient's computer automatically glues them back together in order.

#### 26. Five Principal Services Provided by PGP

* **The 5 Services:**
1. Encryption (Confidentiality)
2. Digital Signatures (Authentication)
3. Zip Compression (Reduces file size)
4. Radix-64 Conversion (Turns binary into text for old email systems)
5. Segmentation (Splits large files up)



#### 27. Mobile Security

* **The Answer:** The rules, tools, and software habits used to protect mobile devices (like smartphones and tablets) from being infected by malicious apps, having data leaked over public Wi-Fi, or being exploited if physically stolen.

#### 28. Intrusion Detection System (IDS)

* **What it means:** A software program or appliance that passively watches network traffic and logs. It acts like a security camera; if it detects suspicious activity or a known hacking pattern, it generates an immediate alert for system administrators.

#### 29. Advantages of an IDS over a Firewall

* **The 2 Advantages:**
* **Internal View:** Firewalls only check traffic crossing the border. An IDS looks inside the internal network to spot threats that are already past the fence.
* **Behavior Analysis:** Firewalls look at simple rules (IPs/Ports). An IDS can recognize complex, malicious behavior and hacking techniques hidden within ordinary traffic.



#### 30. SSL Session vs. SSL Connection

* **What it means:** An **SSL Session** is a long-lasting relationship between a client and server created by an initial handshake. It stores key parameters so that multiple individual **SSL Connections** (transient, short-term data streams) can be opened and closed quickly without the slow process of renegotiating security rules every time.

#### 31. Transport Layer Security (TLS)

* **The Answer:** TLS is the modern, highly secure version of SSL. It is a protocol that runs behind the scenes to provide end-to-end encryption, privacy, and identity verification over the internet (it is what turns `http` into `https`).

#### 32. Network Access Control (NAC)

* **The Answer:** A security approach that inspects devices before letting them join a network. It checks your credentials and makes sure your device has its antivirus updated and firewall enabled before granting network access.

#### 33. How PGP Uses the Web of Trust

* **The Answer:** Instead of relying on a big central corporate authority to verify keys, PGP uses a decentralized model. If I trust Bob, and Bob digitally signs Alice's key to say it's real, my computer will automatically trust Alice's key. It is a word-of-mouth chain of trust.

#### 34. Five Header Fields Defined in MIME

* **The 5 Fields:**
1. `MIME-Version:`
2. `Content-Type:`
3. `Content-Transfer-Encoding:`
4. `Content-ID:`
5. `Content-Description:`



#### 35. Three Main Components of a Distributed IDS

* **The 3 Parts:**
1. **Host Agents:** Software modules running on individual computers to collect local logs.
2. **LAN Monitors:** Boxes that watch raw data flowing across local network cables.
3. **Central Manager:** A main dashboard console that gathers data from all agents to detect a large-scale coordinated attack.



#### 36. Main Function of a Firewall

* **The Answer:** To act as a digital gatekeeper at the perimeter of a network, examining every piece of incoming and outgoing traffic and filtering out anything that doesn't explicitly match the safety rules.

#### 37. Two General Approaches to Attacking a Cipher

* **The 2 Approaches:**
* **Cryptanalysis:** Looking for mathematical errors, patterns, or weaknesses inside the encryption algorithm to crack the code.
* **Brute-Force:** Using raw computing power to try every possible key combination until the right one unlocks the message.



#### 38. Applications of X.509 Certificates

* **The List:** Securing website traffic (HTTPS), signing code (proving software updates are safe), securing enterprise emails (S/MIME), and client authentication in VPN tunnels.

#### 39. Kerberos V4 vs. Kerberos V5

* **The 2 Differences:**
* **Encryption Choice:** V4 only works with one old encryption method (DES). V5 is modular and can use modern security like AES.
* **Ticket Options:** V5 supports ticket forwarding and renewals; V4 tickets have simple, unalterable lifetimes.



#### 40. Two Phases of Internet Key Exchange (IKE)

* **The 2 Phases:**
* **Phase 1:** The two routers talk to each other, prove their identities, and establish a single, secure management channel.
* **Phase 2:** Inside that safe management channel, they rapidly set up the actual secure tunnels (IPsec SAs) used to pass your daily internet data.



#### 41. Intrusion Prevention System (IPS) vs. Intrusion Detection System (IDS)

* **The Core Difference:** An **IDS** sits off to the side, monitors traffic, and sounds an alarm when it spots a hacker (passive). An **IPS** sits directly inside the live cable line, allowing it to instantly drop malicious packets and cut off a hacker before any damage is done (active).

#### 42. How a Digital Signature Ensures Data Integrity

* **The Answer:** The sender hashes the document to create a unique fingerprint, encrypts it with their private key, and attaches it as a signature. The receiver decrypts the signature using the sender's public key and re-calculates the document's fingerprint locally. If the two fingerprints match exactly, it proves the file was not altered in transit.

---

This provides a clean, easy-to-write set of responses for Part A.

Whenever you are ready, say **"Next"** to kick off **Part B & C (Long Answers)**, starting one at a time with the comprehensive study of the **RSA Algorithm**.