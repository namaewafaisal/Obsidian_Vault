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

