
# **Part B — Unit 1, Question 1**

## **Explain OSI architecture model with a neat diagram. (13 marks)**

The **OSI (Open Systems Interconnection) Model** is a conceptual framework used to understand and standardize network communication functions. It divides the entire communication process into **seven layers**, each with specific responsibilities. This model helps in designing interoperable network devices and protocols, ensuring that different systems can communicate over networks.

Below is a neat text-based diagram suitable for exams:

```
+---------------------------+
| 7. Application Layer      |
+---------------------------+
| 6. Presentation Layer     |
+---------------------------+
| 5. Session Layer          |
+---------------------------+
| 4. Transport Layer        |
+---------------------------+
| 3. Network Layer          |
+---------------------------+
| 2. Data Link Layer        |
+---------------------------+
| 1. Physical Layer         |
+---------------------------+
```

---

## **Explanation of Each Layer (Detailed 13-mark style)**

### **1. Physical Layer**

- The lowest layer responsible for the **physical transmission of raw bits** over a medium.
    
- Defines physical characteristics such as cable types, voltage levels, timing, and data rates.
    
- Handles **bit synchronization**, **modulation**, and **line encoding**.
    
- Devices: Hubs, Repeaters, Cables, Network Interface Cards (NIC).
    

---

### **2. Data Link Layer**

- Ensures **error-free communication** between two directly connected devices.
    
- Divided into **LLC (Logical Link Control)** and **MAC (Media Access Control)** sublayers.
    
- Handles **framing, error detection**, **flow control**, and **access control**.
    
- Devices: Switches, Bridges.
    
- Protocols: Ethernet, PPP, HDLC.
    

---

### **3. Network Layer**

- Responsible for **logical addressing** and **path selection** (routing).
    
- Determines the best route to send data across multiple networks.
    
- Provides **packet forwarding**, **fragmentation**, and **traffic control**.
    
- Devices: Routers.
    
- Protocols: IP, ICMP, IGMP.
    

---

### **4. Transport Layer**

- Ensures **end-to-end communication** and reliability between hosts.
    
- Provides segmentation, reassembly, flow control, and error control.
    
- Supports two types of transport services:
    
    - **TCP** (connection-oriented, reliable)
        
    - **UDP** (connectionless, faster, no reliability)
        
- Responsible for **port addressing**.
    

---

### **5. Session Layer**

- Manages **sessions** between communicating devices.
    
- Responsible for session establishment, maintenance, and termination.
    
- Provides checkpointing and dialog control (e.g., half-duplex or full-duplex communication).
    
- Ensures that long-running transfers do not fail entirely if a small interruption occurs.
    

---

### **6. Presentation Layer**

- Ensures that information sent from the application layer of one system is **readable by the application layer** of another.
    
- Provides **data translation**, **encryption/decryption**, **compression/decompression**, and standard encoding formats like ASCII, JPEG, GIF.
    
- Acts as the “translator” of the network.
    

---

### **7. Application Layer**

- Closest to the end user; provides **network services** directly to applications.
    
- Examples: Email, file transfer, web browsing, remote login.
    
- Protocols include HTTP, FTP, SMTP, DNS, Telnet.
    
- Ensures that communication partners are authenticated and ready for data exchange.
    

---

## **How the OSI Model Works (Process Explanation)**

1. **Sender Side:**
    
    - Data originates at the Application Layer (Layer 7).
        
    - As it moves down each layer, headers (and sometimes trailers) are added.
        
    - At Layer 2, frames are created.
        
    - At Layer 1, bits are transmitted physically to the medium.
        
2. **Receiver Side:**
    
    - The process reverses: Physical → Data Link → … → Application Layer.
        
    - Each layer removes its corresponding header and interprets the data.
        

This process is known as **encapsulation and decapsulation**.

---

## **Advantages of OSI Model**

- Provides a clear **layered architecture** for understanding networks.
    
- Helps in troubleshooting by isolating issues to specific layers.
    
- Supports **interoperability** among vendors.
    
- Encourages modular development of network technologies.
    
---
# **Part B – Unit 1, Question 2**

## **Explain the various security attacks. (13 marks)**

Security attacks are any actions that attempt to break the security of a system by violating confidentiality, integrity, or availability. These attacks are broadly categorized based on how attackers interact with the system and what they try to achieve.

Below is a clear, exam-friendly 13-mark explanation.

---

# **1. Passive Attacks**

Passive attacks only _observe_ data; they do not alter anything. The main goal is to gather information secretly.

### **Types of Passive Attacks**

#### **a) Release of Message Contents**

- Attackers read confidential messages, emails, or files.
    
- Example: Reading someone’s email over an unsecured Wi-Fi network.
    

#### **b) Traffic Analysis**

- Even if messages are encrypted, attackers study patterns such as frequency, size, and timing.
    
- This may reveal sensitive details like who is communicating with whom.
    

**Effect:** Hard to detect because no modification happens.

---

# **2. Active Attacks**

Active attacks _modify data_ or disrupt system operations. They directly damage integrity and availability.

### **Types of Active Attacks**

#### **a) Masquerade Attack**

- An attacker pretends to be a legitimate user.
    
- Example: Logging in using stolen credentials.
    

#### **b) Replay Attack**

- A valid message is captured and resent later to trick the system.
    
- Example: Replaying an authentication request.
    

#### **c) Message Modification**

- The attacker alters a legitimate message in transit.
    
- Example: Changing account numbers or transaction amounts.
    

#### **d) Denial of Service (DoS)**

- Attackers overload the system so genuine users cannot access it.
    
- Example: Flooding a server with traffic.
    

#### **e) Man-in-the-Middle Attack**

- The attacker secretly intercepts and alters communication between two parties.
    
- Both parties believe they are talking to each other.
    

**Effect:** More harmful but easier to detect than passive attacks.

---

# **3. Malware-Based Attacks**

Malware refers to malicious software designed to harm systems.

### **Types**

- **Virus** – attaches to programs and spreads when executed.
    
- **Worm** – spreads automatically over networks.
    
- **Trojan Horse** – appears harmless but contains malicious code.
    
- **Ransomware** – encrypts files and demands payment.
    
- **Spyware** – secretly collects user info.
    
- **Keyloggers** – capture keystrokes.
    

---

# **4. Web-Based Attacks**

These target websites and web applications.

### **Examples**

- **SQL Injection** – attacker inserts malicious SQL commands into input fields.
    
- **Cross-Site Scripting (XSS)** – injecting scripts into websites viewed by others.
    
- **Cross-Site Request Forgery (CSRF)** – tricks user into performing unauthorized actions.
    

---

# **5. Network Attacks**

These target network communication.

### **Examples**

- **Sniffing** – capturing packets traveling through a network.
    
- **Spoofing** – forging IP or MAC addresses to appear as a trusted device.
    
- **Session hijacking** – taking over an active user session.
    

---

# **6. Social Engineering Attacks**

Instead of attacking technology, attackers target human psychology.

### **Examples**

- **Phishing** – fake emails or websites that steal credentials.
    
- **Baiting** – tempting users with free downloads that contain malware.
    
- **Pretexting** – attacker pretends to be someone trustworthy to gain information.
    

---

# **Conclusion**

Security attacks come in many forms, from silent eavesdropping to destructive system disruption. Understanding their types helps organizations design proper defenses such as encryption, authentication, firewalls, intrusion detection, and user education.

---

# **Part B – Unit 1, Question 3**

## **Explain the classical encryption techniques with suitable problems. (13 marks)**

Classical encryption techniques are early methods used to hide messages before modern cryptography existed. They mainly involve simple transformations like substitution or shifting characters. Though insecure today, they form the foundation of modern ciphers.

There are **two major categories**:

---

# **1. Substitution Techniques**

In substitution ciphers, **each letter is replaced by another letter or symbol**.

### **a) Caesar Cipher**

- One of the oldest ciphers used by Julius Caesar.
    
- Each letter in plaintext is shifted by a fixed number (key).
    
- Example: Key = 3
    
    - A → D, B → E, C → F, etc.
        
- Encryption Formula:  
    **C = (P + K) mod 26**
    
- Decryption:  
    **P = (C – K) mod 26**
    

**Problem Example:**  
Encrypt **“ATTACK”** using key 3.  
A→D, T→W, T→W, A→D, C→F, K→N  
**Ciphertext = DWWDFN**

---

### **b) Monoalphabetic Cipher**

- A more general substitution where each letter maps to any other letter (not just shifted).
    
- Uses a random permutation of the alphabet.
    
- Stronger than Caesar but still vulnerable to **frequency analysis** (e.g., 'E' appears most often in English).
    

**Problem Example:**  
If mapping is A→Q, B→W, C→E, ... then “CAB” → “EQW”.

---

### **c) Playfair Cipher**

- Uses a 5×5 matrix of letters formed from a keyword.
    
- Encrypts **digraphs (pairs of letters)** instead of single letters.
    
- Rules:
    
    1. If letters are same, insert 'X'.
        
    2. Same row → take letters to the right.
        
    3. Same column → take letters below.
        
    4. Rectangle → take opposite corners.
        

**Example:**  
Keyword = **MONARCHY**  
Plaintext = **HELLO** → HE LX LO  
Encrypt using matrix rules.

---

### **d) Vigenère Cipher**

- Uses a **keyword** to shift letters by different amounts.
    
- A polyalphabetic cipher.
    

**Example:**  
Plaintext: ATTACK  
Keyword: LEMON  
Key repeated: LEMONL  
Shift each letter by corresponding key letter.

Much stronger than monoalphabetic.

---

# **2. Transposition Techniques**

These techniques **do not alter letters**, but **rearrange their positions**.

### **a) Rail Fence Cipher**

- Letters written in a zig-zag pattern and then read row-wise.
    

**Example:**  
Plaintext: ATTACKNOW  
Depth = 3 rails  
Write in zig-zag, read row by row → ciphertext.

---

### **b) Columnar Transposition**

- Write plaintext in rows based on a keyword, then read column-wise.
    

**Example:**  
Plaintext: MUSTSECURE  
Keyword: CAT (positions: C=2, A=1, T=3)

Arrange and read columns based on alphabetical order of the keyword.

---

# **3. Product Ciphers**

A combination of substitution + transposition.  
Example: **DES is built on substitution + permutation rounds**, inspired by classical product ciphers.

---

# **4. Why Classical Ciphers Are Weak**

- Easy to break using **frequency analysis**.
    
- Short key sizes.
    
- No protection against modern computing power.
    
- Still important for learning the basic principles of encryption.
    

---

# **Conclusion**

Classical encryption techniques include substitution, transposition, and product ciphers. Each technique demonstrates how plaintext can be transformed to ciphertext using simple rules. Though obsolete for security today, they provide the foundation for understanding modern cryptography.

---

# **Part B – Unit 2, Question 1**

## **Draw and explain the function of DES. (13 marks)**

DES (Data Encryption Standard) is a **symmetric block cipher** developed by IBM and adopted as a US standard. It encrypts data in fixed-size blocks and uses the same key for encryption and decryption.

---

# **Neat Diagram of DES (Exam-Friendly)**

```
          +-----------------------+
Plaintext |        64 bits        |
          +-----------------------+
                    |
              Initial Permutation (IP)
                    |
     +--------------+--------------+
     |                             |
     |      16 ROUNDS OF DES       |
     |  -------------------------  |
     |  Each round has:            |
     |   - Expansion (32 → 48)     |
     |   - Key mixing (XOR)        |
     |   - S-box substitution      |
     |   - P-box permutation       |
     |                             |
     +--------------+--------------+
                    |
               Swap (L16 ↔ R16)
                    |
         Inverse Initial Permutation (IP–1)
                    |
          +-----------------------+
Ciphertext|        64 bits        |
          +-----------------------+
```

---

# **Explanation of DES (Simple + Detailed for 13 marks)**

DES is a **64-bit block cipher**, meaning it takes **64-bit plaintext** and produces **64-bit ciphertext**.  
It uses a **56-bit key** (actual key is 64 bits but 8 bits are parity).

The DES algorithm works in **five major steps**:

---

# **1. Initial Permutation (IP)**

- The 64-bit plaintext is rearranged according to a fixed permutation table.
    
- This does not create security by itself but helps in spreading the input bits uniformly before the rounds begin.
    

After IP, the data is divided into two halves:

- **Left half = L0 (32 bits)**
    
- **Right half = R0 (32 bits)**
    

---

# **2. Sixteen Feistel Rounds**

DES uses a **Feistel structure**, meaning each round operates as:

```
L(i+1) = R(i)
R(i+1) = L(i) XOR f(R(i), K(i))
```

Each of the **16 rounds** has the following steps:

## **a) Expansion (E-Box)**

- The 32-bit right half (Ri) is expanded to **48 bits** using an Expansion Table.
    
- This duplicates some bits, helping confusion and diffusion.
    

## **b) Round Key Mixing**

- The expanded 48-bit block is XORed with a **48-bit round key** Ki.
    
- DES generates **16 different subkeys** from the main 56-bit key.
    

## **c) Substitution using S-Boxes**

- The 48-bit result is divided into **eight 6-bit blocks**.
    
- Each block goes through an S-box (Substitution box), which maps 6 bits → 4 bits.
    
- This step provides **non-linearity**, the most important part of DES security.
    

## **d) P-box Permutation**

- The 32-bit output of all S-boxes is rearranged using the P-box.
    
- Spreads the output bits across positions to strengthen diffusion.
    

End of each round:

- Left and Right halves are swapped except in the final round.
    

---

# **3. Swap of Final Halves**

After 16 rounds, DES **swaps the final halves** L16 and R16.

This swap is part of the Feistel structure for symmetry.

---

# **4. Inverse Initial Permutation (IP–1)**

The final 64-bit block goes through the **inverse permutation**, which is the reverse of the initial IP.

This produces the final **64-bit ciphertext**.

---

# **5. Key Generation (Key Scheduling)**

The 56-bit key is processed to generate **16 subkeys**, each of 48 bits via:

- Permutation Choice 1 (PC-1)
    
- Left circular shifts
    
- Permutation Choice 2 (PC-2)
    

Each round uses a different key, which increases security.

---

# **Why DES Is No Longer Secure**

- The 56-bit key is too small for modern computing.
    
- Can be brute-forced using hardware clusters.
    
- Replaced by AES and 3DES.
    

---

# **Conclusion**

DES is a classic symmetric encryption system based on 16 Feistel rounds, using substitution, permutation, bit expansion, and key mixing. Though outdated now, it introduced many principles used in modern encryption algorithms.


---

# **Explain the various block cipher modes of operation (13 marks)**

Block ciphers (e.g., AES, DES) encrypt fixed-size blocks (e.g., 128-bit). **Modes of operation** define how to apply a block cipher to data longer than one block and how to provide confidentiality, and sometimes integrity. Common modes: **ECB, CBC, CFB, OFB, CTR**. Each mode changes how plaintext blocks and ciphertext blocks relate and has different security/usage properties.

---

## **1. ECB — Electronic Codebook Mode**

**How it works:**

- Split plaintext into blocks P₁, P₂,…
    
- Ciphertext Cᵢ = E_K(Pᵢ) for each block independently.
    

**Properties / Pros:**

- Simple, parallelizable (each block independent).
    
- Good for random-access encryption.
    

**Cons / Weaknesses:**

- Identical plaintext blocks → identical ciphertext blocks → reveals patterns (not semantically secure).
    
- Not recommended for encrypting structured data (images, repeated fields).
    

**Use case:** Low — only when plaintext has no repeated patterns or for small random blocks (rare).

---

## **2. CBC — Cipher Block Chaining**

**How it works:**

- Use an Initialization Vector (IV) of block size.
    
- C₁ = E_K(P₁ ⊕ IV)
    
- Cᵢ = E_K(Pᵢ ⊕ Cᵢ₋₁) for i ≥ 2
    
- Decryption: Pᵢ = D_K(Cᵢ) ⊕ Cᵢ₋₁ (P₁ uses IV).
    

**Properties / Pros:**

- Hides plaintext patterns — identical plaintext blocks produce different ciphertext (due to chaining and IV).
    
- Widely used and understood.
    

**Cons / Caveats:**

- Requires a random, unpredictable IV for the first block (must be unique and, for some uses, unpredictable).
    
- Not parallelizable for encryption (because of chaining) — decryption can be parallelized.
    
- Needs proper padding (e.g., PKCS#7) since message length may not align with block size.
    
- Vulnerable to padding oracle attacks if padding errors are revealed by the system.
    

**Use case:** General-purpose encryption of files and messages (historical common choice).

---

## **3. CFB — Cipher Feedback Mode**

**How it works:**

- Turns a block cipher into a self-synchronizing stream cipher.
    
- Use IV; output of cipher is XORed with plaintext to form ciphertext:
    
    - For 128-bit segments, O₁ = E_K(IV); C₁ = P₁ ⊕ O₁
        
    - Next input is shift register updated with C₁, feed into E_K, etc.
        

**Properties / Pros:**

- Can operate on units smaller than block size (e.g., bytes or bits) — good for streaming.
    
- No padding required.
    

**Cons / Caveats:**

- Encryption is sequential (not parallel).
    
- Errors in one ciphertext block affect only a limited number of subsequent plaintext units (error propagation property).
    

**Use case:** Streaming data encryption, legacy systems that need partial-block processing.

---

## **4. OFB — Output Feedback Mode**

**How it works:**

- Also converts block cipher into a synchronous stream cipher.
    
- Generate keystream blocks by repeatedly encrypting the previous output:
    
    - O₁ = E_K(IV); C₁ = P₁ ⊕ O₁
        
    - O₂ = E_K(O₁); C₂ = P₂ ⊕ O₂, etc.
        

**Properties / Pros:**

- Errors in ciphertext only affect the corresponding plaintext bits (no propagation).
    
- IV misuse less catastrophic than CFB/CBC, but reusing IV/keystream is still catastrophic.
    

**Cons / Caveats:**

- Keystream must never be reused with the same key (otherwise simple XOR reveals plaintext).
    
- Not parallelizable for keystream generation (but keystream precomputation is possible).
    

**Use case:** Scenarios requiring limited error propagation and stream behavior.

---

## **5. CTR — Counter Mode**

**How it works:**

- Treats block cipher as a stream cipher but uses a counter to generate keystream blocks:
    
    - For block i: Oᵢ = E_K(Nonce || Counterᵢ)
        
    - Cᵢ = Pᵢ ⊕ Oᵢ
        

**Properties / Pros:**

- Fully parallelizable (encrypt/decrypt blocks independently) — excellent performance.
    
- No padding required.
    
- Simple random-access decryption (can decrypt block j independently).
    

**Cons / Caveats:**

- **Critical:** Nonce + counter combination must be unique for every encryption under the same key; reusing a counter/nonce pair leaks XOR of plaintexts.
    
- Requires careful nonce management (e.g., per-message unique IV).
    

**Use case:** High-performance systems, disk encryption, network protocols where parallelism is beneficial.

---

## **Other Notes (Important for exams)**

- **Padding:** Modes that operate on whole blocks (ECB, CBC) need padding (PKCS#7, ISO). Stream-like modes (CFB, OFB, CTR) do not require padding.
    
- **IV Requirements:** IV must be unique; in some modes it must also be unpredictable. For CBC, IV must be unpredictable/random. For CTR, nonce must be unique (can be a counter or message ID).
    
- **Authenticated Encryption:** None of these basic modes provide message integrity/authentication by themselves. Use **Authenticated Encryption** modes (e.g., GCM, EAX, CCM) or combine encryption with MAC (Encrypt-then-MAC) to prevent active attacks (tampering).
    
- **Security:** ECB insecure for structured data; CBC and CTR are widely used correctly with good IV/nonce handling; CTR offers best performance when uniqueness is guaranteed.
    

---

## **Short comparative table (quick revision)**

|Mode|Padding|Parallelizable|IV/Nonce needs|Error Propagation|
|---|---|---|---|---|
|ECB|Yes|Yes|No|Local (single block)|
|CBC|Yes|Decrypt: Yes; Encrypt: No|IV random & unique|Propagates to next block|
|CFB|No|No|IV required|Limited propagation|
|OFB|No|No (keystream sequential)|IV required (unique)|None beyond corrupt bits|
|CTR|No|Yes (both)|Nonce+counter unique|None beyond corrupt bits|

---

### **Conclusion (1–2 lines for exam)**

Choose the mode based on required properties: **CTR** for performance and parallelism (careful nonce management), **CBC** for simple confidentiality with random IV, **CFB/OFB** for stream-like behavior, and **avoid ECB** for structured data. Always pair encryption with authentication (AEAD modes like GCM) for secure systems.

---
# **What is a pseudo-random number generator (PRNG)? How is it used to find the key distribution in the RC4 algorithm?**

---

# **1. What is a Pseudo-Random Number Generator (PRNG)?**

A **Pseudo-Random Number Generator (PRNG)** is an algorithm that produces a sequence of numbers that _appear random_, even though they are completely determined by an initial value called the **seed**.

### **Important characteristics of a PRNG:**

1. **Deterministic:**  
    If the seed is the same, it generates the same sequence every time.
    
2. **Fast to compute:**  
    PRNGs are mathematical algorithms designed for efficiency.
    
3. **Statistically random:**  
    The output should look random, even though it is not truly random.
    
4. **Used in cryptography:**  
    Key generation, IV generation, nonces, padding, salts, and stream cipher keystreams all rely on PRNGs.
    

In cryptography, a PRNG must also be **cryptographically secure**, meaning an attacker cannot predict future output even if some past output is known.

---

# **2. PRNG in RC4 (Key Stream Generator)**

RC4 is a **stream cipher**. Instead of encrypting block by block (like DES/AES), it generates a long stream of pseudo-random bytes, which are XORed with the plaintext.

This pseudo-random stream comes from the **RC4 PRNG**, which works in two main phases:

---

# **A. Key Scheduling Algorithm (KSA)**

The KSA initializes a 256-byte state array **S[0..255]** using the secret key.

### **Steps of KSA:**

1. Initialize S with values 0–255.
    
2. Mix S based on the key bytes:  
    For i = 0 to 255:  
    [  
    j = (j + S[i] + K[i \mod keylength]) \mod 256  
    ]  
    Swap S[i] and S[j].
    

Result:  
A **scrambled S array**, completely dependent on the key.

This forms the **internal PRNG state**.

---

# **B. PRGA — Pseudo-Random Generation Algorithm**

This is the actual **PRNG** that outputs the keystream.

### **Steps of PRGA:**

For each byte:

1. i = (i + 1) mod 256
    
2. j = (j + S[i]) mod 256
    
3. Swap S[i] and S[j]
    
4. Output keystream byte = S[(S[i] + S[j]) mod 256]
    

This output byte is XORed with plaintext to get ciphertext.

---

# **3. How PRNG reveals key distribution in RC4**

The main weakness of RC4 is that the **PRNG is biased**, especially in the early bytes.

### **Important weaknesses:**

1. **Biased output in the first few hundred bytes**  
    The keystream does **not** have uniform random distribution. Some bytes appear more frequently.
    
2. **Key bytes leak into the internal state**  
    KSA does not mix S uniformly. Early values of S are still correlated with the key bytes.
    
3. **Certain output values reveal partial information about the key**  
    For example, the second output byte of RC4 has a known strong bias toward zero.
    
4. **This allowed attacks like:**
    
    - WEP cracking
        
    - WPA-TKIP attacks
        
    - Fluhrer-Mantin-Shamir (FMS) attack
        
    - Klein attack
        

These attacks analyze millions of keystream outputs and use statistical biases to recover key bytes.

---

# **4. Example of RC4 key distribution weakness (simple exam-friendly version)**

Suppose RC4 is used with a key:  
K = [K₀, K₁, K₂, …]

During KSA:

[  
j = (j + S[i] + K[i \mod keylen]) \mod 256  
]

This causes:

- The first few values of S depend **directly** on early key bytes.
    
- These values affect the **early keystream output**, making them biased.
    

Attackers collect many packets and analyze:

- Frequency distribution of keystream bytes
    
- Correlation between early output bytes and key bytes
    

From this, they derive the key distribution and recover key bits.

---

# **5. Summary (2–3 lines for exam)**

A **PRNG** produces deterministic but statistically random sequences used in cryptography. RC4 uses the PRGA as a pseudo-random generator to produce keystream bytes. Due to weak mixing in the KSA, RC4’s PRNG exhibits biased output, allowing attackers to analyze the keystream distribution and recover key information—this is the basis for well-known attacks on RC4 (e.g., WEP cracking).

---

If you want the **next Part B question**, just say **next**.