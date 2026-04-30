## Plaintext, Ciphertext, Encryption, Decryption

### Definitions
- Plaintext: Original readable data
- Ciphertext: Encrypted unreadable data
- Encryption: Process of converting plaintext → ciphertext using a key
- Decryption: Process of converting ciphertext → plaintext using a key

### Relationship
Plaintext + Key → Encryption → Ciphertext  
Ciphertext + Key → Decryption → Plaintext  

### Key Concept
- Encryption and decryption are inverse operations
- Security depends on the secrecy of the key, not the algorithm

### Example (Caesar Cipher)
HELLO → (shift +3) → KHOOR → (shift -3) → HELLO

## Cryptography vs Cryptanalysis

### Definitions
- Cryptography: Study of securing communication using encryption techniques
- Cryptanalysis: Study of breaking encryption to recover plaintext or keys

### Roles
- Cryptography → Defender (builds secure systems)
- Cryptanalysis → Attacker (tries to break systems)

### Attacker Goals
- Recover plaintext from ciphertext
- Recover the key
- Identify weaknesses in the system

### Key Idea
Security systems are designed assuming attackers are intelligent and capable

### Flow
Sender → Encryption → Ciphertext → Decryption → Receiver  
                ↑  
        Cryptanalysis happens here


## Types of Attacks on Cryptosystems

### Ciphertext-Only Attack
- Attacker has only ciphertext
- No knowledge of plaintext
- Hardest scenario for attacker

### Known-Plaintext Attack
- Attacker has some plaintext and corresponding ciphertext
- Uses this to find patterns

### Chosen-Plaintext Attack
- Attacker can choose plaintext and get ciphertext
- Helps analyze encryption behavior

### Chosen-Ciphertext Attack
- Attacker can choose ciphertext and get decrypted plaintext
- Most powerful attack model

### Attack Strength Order
Ciphertext-only < Known-plaintext < Chosen-plaintext < Chosen-ciphertext

### Key Idea
More access to the system = higher chance of breaking it

## Passive vs Active Attacks

### Passive Attacks
- Attacker only observes communication
- Does not modify data
- Goal: extract information secretly
- Example: eavesdropping, traffic analysis

### Active Attacks
- Attacker modifies or injects data
- Can disrupt communication
- Goal: alter system behavior or data
- Example: message modification, replay attacks, impersonation

### Key Differences
- Passive: hard to detect, no system impact
- Active: easier to detect, affects system integrity

### Key Insight
- Encryption protects against passive attacks
- Active attacks require authentication and integrity mechanisms
  
  ## Confusion vs Diffusion

### Confusion
- Hides relationship between key and ciphertext
- Makes encryption logic complex and non-linear
- Prevents attacker from deducing key

### Diffusion
- Spreads influence of one plaintext bit across many ciphertext bits
- Ensures small changes affect entire output
- Reduces patterns in ciphertext

### Key Idea
- Confusion → complexity
- Diffusion → spreading effect

### In Modern Ciphers
- Confusion implemented using substitution (S-box)
- Diffusion implemented using permutation and mixing

### Importance
Strong encryption requires both confusion and diffusion

## Substitution Ciphers

### Definition
- Replace each element of plaintext with another element
- Positions remain the same, only symbols change

---

### Caesar Cipher
- Uses a fixed shift for all letters
- Key: integer (shift value)

#### Formula
C = (P + k) mod 26  
P = (C - k) mod 26  

#### Example
HELLO → KHOOR (shift +3)

#### Weakness
- Only 25 possible keys
- Easily broken using brute force

---

### Vigenère Cipher
- Uses a keyword to apply different shifts
- Each letter is encrypted with a different shift

#### Example
Plaintext: HELLO  
Key: KEYKE  
Ciphertext: RIJVS  

---

### Key Insight
- Caesar: single substitution rule
- Vigenère: multiple substitution rules

## Transposition Ciphers

### Definition
- Rearranges positions of characters in plaintext
- Does not change the characters themselves

---

### Key Difference
- Substitution → changes letters
- Transposition → changes positions

---

### Columnar Transposition

#### Steps
1. Write plaintext in rows under a key
2. Sort columns based on key alphabetically
3. Read columns to get ciphertext

#### Example
Plaintext: HELLOWORLD  
Key: ZEBRA  

Grid:
Z E B R A  
H E L L O  
W O R L D  

Reordered:
A B E R Z  

Ciphertext:
ODLREOLLHW  

---

### Key Insight
- Characters remain same
- Only positions change

### Weakness
- Letter frequency remains unchanged
- Vulnerable to analysis
  
  ## Stream Ciphers vs Block Ciphers

### Stream Cipher
- Encrypts data one unit at a time (bit/byte)
- Continuous encryption
- Suitable for real-time data

### Block Cipher
- Encrypts fixed-size blocks (e.g., 128 bits)
- Processes data in chunks
- Suitable for stored data

---

### Key Differences

| Feature | Stream Cipher | Block Cipher |
|--------|--------------|--------------|
| Unit | Bit/byte | Fixed block |
| Processing | Continuous | Chunk-based |
| Use case | Real-time | Storage |

---

### Examples
- Stream: RC4 (insecure now)
- Block: AES (widely used)

---

### Key Insight
Stream = flow-based  
Block = chunk-based  

### Note
Block ciphers use modes of operation (e.g., CBC, CTR) to modify behavior