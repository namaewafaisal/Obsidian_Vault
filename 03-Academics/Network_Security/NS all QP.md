# Network Security - Consolidated Question Bank

## PART A (Short Answer Questions)

### Highest Repeated Part A (3–5 Times)
* **Conventional vs. Public-Key Encryption:** Differentiate between conventional (symmetric) and public-key (asymmetric) encryption. / Compare and contrast symmetric and asymmetric encryption.
* **Replay Attack Countermeasures:** List the countermeasures for replay attacks.
* **X.509 Certificate Structure:** Write/List the main elements and structural components of an X.509 certificate.
* **Message Digest:** Define message digest.
* **Extensible Authentication Protocol (EAP):** Mention/Define the function of the Extensible Authentication Protocol (EAP) / EAP over LAN / What is an extensible authentication protocol?

### Moderately Repeated Part A (2 Times)
* **X.509 Role:** Outline the role of X.509 certificates in secure communications.
* **Remote User Authentication:** Define remote user authentication and explain why it is essential in network security.
* **Intruder Classifications:** Define intruder and name/classify the three different classes of intruders (e.g., masquerader, clandestine user, willful exploiter). / Who is a masquerader and who is a clandestine user?
* **Password Policy:** List the key characteristics of a strong password policy for an organization.
* **DKIM Authentication:** How does Domain Keys Identified Mail (DKIM) verify the authenticity of an email message?
* **Firewall Types:** Give various types / examples of firewalls.
* **Caesar Cipher Calculation:** * Encrypt "hello" using the Caesar cipher with key = 3.
  * Encrypt the plain text "missscarletwiththeknifeinthelibrary" with the key = 3 using Caesar cipher.

---

## PART B & C (Long Answer & Case Study Questions)

### Highest Repeated Part B & C (3–4 Times)
* **RSA Algorithm Mathematical Computations:**
  * Perform encryption and decryption using RSA algorithm where $p=3, q=11, e=7$, and $M=5$.
  * Consider a public-key system using RSA. Let the ciphertext $C=10$ be sent to a user whose public key is $e=5, n=35$. What is the plaintext $M$?
  * Perform encryption and decryption using RSA where $p=17, q=11, e=7, M=88$.
* **RSA Algorithm Evaluation:** Examine the RSA algorithm. Outline its mathematical foundation, and critically assess its security and efficiency in modern applications.
* **Kerberos Authentication Architecture & Protocols:** * Explain the principles of the Kerberos authentication system. Analyze its strengths and weaknesses in providing secure user authentication.
  * Assume the client $C$ wants to communicate with server $S$ using the Kerberos procedure; briefly explain how it can be achieved.
  * Develop a step-by-step procedure for a scenario where a client $C$ in one administrative realm needs to communicate with a secure server $S$ in a different realm using the Kerberos protocol. Justify the need for inter-realm secret keys.
* **Firewall Architectures & Deployment Strategies:**
  * Illustrate the various types of firewalls with neat diagrams (packet-filtering, stateful inspection, application proxy/gateway firewall).
  * Illustrate how firewalls help in establishing a security framework for an organization (Create your own organization deployment scenario).
  * Develop a firewall strategy for an enterprise network, including selecting firewall types (packet-filtering, proxy, and stateful inspection) based on network segments and security needs. Detail considerations for firewall placement, configuration settings, and ongoing management to prevent unauthorized access and optimize network performance.

### Moderately Repeated Part B & C (2 Times)
* **X.509 Authentication Service:** Outline the X.509 authentication service with a neat architectural diagram (including its components, protocols, and certificate validation pathways).
* **Authenticated Key Agreement Protocols (Diffie-Hellman + RSA):**
  * This question presents an authenticated key agreement protocol involving Diffie-Hellman key exchange and RSA keys for digital signatures:
    1. $A \rightarrow B: g^x \bmod p$
    2. $B \rightarrow A: g^y \bmod p, E_k(S_B(g^y \bmod p, g^x \bmod p))$
    3. $A \rightarrow B: E_k(S_A(g^x \bmod p, g^y \bmod p))$
    * Task 1: Describe the details (as a list) of $A$'s and $B$'s actions at receipt of messages 2 and 3 and what beliefs they have at that stage.
    * Task 2: Are $A$ and $B$ successfully authenticated to each other after the protocol run?
* **Intrusion Detection Systems (IDS) Framework:** * Explain intrusion detection system (IDS) in detail with suitable diagrams, exploring host-based vs. network-based architectures and deployment.
  * Explain the various measures/methodologies that may be used for intrusion detection (Signature-based, Anomaly-based, and Heuristic/Behavioral tracking).
* **Pretty Good Privacy (PGP) Architecture:** Describe PGP cryptographic functions in detail with suitable block diagrams (covering its encryption, digital signature, compression, and radical key management structures).
* **Public Key Distribution Schemes:** Describe the schemes that are widely used for the distribution of public keys with practical examples.
* **IEEE 802.1X and EAP Enterprise Network Integration:**
  * An enterprise organization wants to implement strict access control measures to secure its internal network:
    * i) Describe how IEEE 802.1X port-based network access control could be applied.
    * ii) Outline how Extensible Authentication Protocol (EAP) integrates with IEEE 802.1X.
    * iii) Evaluate potential security benefits and challenges.

---

## SINGLE APPEARANCE QUESTIONS

### Single Appearance Part A (Short Answer)
* List the five ingredients of a symmetric cipher.
* Outline a HASH function with an example.
* Identify the importance of key distribution.
* List four schemes for public key distribution.
* Give examples for Denial of Service (DoS) attacks.
* List any two factors that contribute to the higher security risks of wireless networks compared to wired networks.
* Mention any two functionalities of S/MIME.
* What are the advantages of Application Proxy Firewall?
* Suggest some of the counter measures for malicious intruders in cloud computing platforms.
* Which two services are provided by the SSL record protocol for an SSL connection?
* Define IEEE 802.1X port.
* Why is the segmentation and reassembly function in PGP needed?
* What are the 5 principle services provided by PGP? / List out the services provided by PGP.
* Define mobile security.
* What is an intrusion detection system?
* List out the advantages of an intrusion detection system over a firewall.
* What is an SSL session?
* Define TLS.
* Define network access control.
* How does PGP use the concept of trust?
* What are the five header fields defined in MIME?
* What are the three main components involved in the distributed intrusion detection system?
* What is the main function of a firewall?
* What is an intruder?
* What are the two general approaches to attacking a cipher?
* List the applications of X.509 certificates.
* Differentiate Kerberos V4 and V5.
* Show the two phases of the Internet Key Exchange (IKE) protocol.
* Outline the role of public and private keys in Pretty Good Privacy (PGP).
* How does an Intrusion Prevention System (IPS) differ from an Intrusion Detection System (IDS) in application?
* Differentiate between TLS and SSL security.
* Mention any two major issues in Wireless Network Security.
* How does a digital signature ensure data integrity?

### Single Appearance Part B & C (Long Answer)
* **Cryptographic Authentication Methodologies:** Discuss various methods of cryptographic authentication, including password-based, token-based, and biometric approaches (Provide a detailed comparison of each).
* **Healthcare Security & Threat Modeling:** Discuss steps to prevent common security breaches in healthcare and analyze the structural relationship between security mechanisms and modern network attacks.
* **Secure Public-Key Cryptosystem Design:** Present a holistic structural framework for a secure public-key cryptosystem (Addressing key generation, CA integration, encryption/decryption routines, signatures, revocation, and attack resistance).
* **Fermat's Little Theorem Computation:** Use Fermat's theorem to find a number between 0 and 72 with a value congruent to $9794 \pmod{73}$.
* **Message Authentication Threats:** Discuss the types of attacks handled by message authentication.
* **Digital Signature Algorithm (DSA):** Discuss the steps involved in the Digital Signature Algorithm (DSA) with mathematical examples.
* **Digital Signature Implementations:** What is a digital signature? How does digital signature encryption work? Outline with an execution diagram and a functional example.
* **Blockchain Block Structures:** Explain in detail the elements of a typical block in a blockchain.
* **Cloud Security (SecaaS):** Describe various SecaaS (Security as a Service) categories of services offered by a cloud service provider with clear examples.
* **Internet of Things (IoT) Security:** With a neat diagram, explain the various components and layers of an IoT security framework.
* **Web Security & SSL Functions:** What is the Web Security Socket Layer (SSL) function? Detail the foundational SSL architecture, handshake validation steps, and encryption/authentication processes.
* **Secure Electronic Transaction (SET):** Elaborate on how the Secure Electronic Transaction (SET) protocol safely enables e-transactions. Explain all the operational components involved (merchant, cardholder, CA, payment gateway).
* **IP Security (IPSec) Mechanics:** Discuss transport mode and tunnel mode authentication in IP? Describe explicitly how Encapsulating Security Payload (ESP) is applied to both these modes.
* **Secure Shell (SSH) Internals:** Explain in details about the standards and the SSH (Secure Shell) application process (Covering SSH architecture, authentication processing, and runtime applications).