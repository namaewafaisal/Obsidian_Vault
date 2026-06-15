---

## topic: Cryptographic Authentication

# Cryptographic Authentication Methodologies

## Why Do We Need Authentication?

Authentication answers a simple question:

```text
Who are you?
```

Before granting access to a system, the user's identity must be verified.

Authentication factors are commonly classified as:

* Something you know
* Something you have
* Something you are

Modern systems often combine multiple factors using Multi-Factor Authentication (MFA).

---

## Authentication Categories

| Factor Type        | Example               |
| ------------------ | --------------------- |
| Something you know | Password, PIN         |
| Something you have | Smart card, OTP token |
| Something you are  | Fingerprint, Face ID  |

---

## 1. Password-Based Authentication

Users prove identity using secret information known only to them.

Examples:

* Passwords
* PINs
* Security questions

### Working

```mermaid
flowchart LR
    User -->|Username + Password| Server
    Server --> Verify
    Verify --> Access
```

### Advantages

* Simple
* Low cost
* Easy deployment

### Limitations

* Weak passwords
* Password reuse
* Phishing attacks
* Brute-force attacks

### Security Improvements

* Password hashing
* Salted hashes
* Strong password policies
* Account lockout mechanisms

---

## 2. Token-Based Authentication

Users prove identity using a physical or virtual token.

Examples:

* Smart cards
* Hardware OTP devices
* Mobile authenticator apps

### Working

```mermaid
flowchart LR
    User --> Token
    Token --> GenerateOTP
    GenerateOTP --> Server
    Server --> Access
```

### Types

* Time-based OTP (TOTP)
* Event-based OTP (HOTP)
* Smart cards
* USB security keys

### Advantages

* Stronger than passwords
* Resistant to password theft
* Supports MFA

### Limitations

* Token loss
* Cost of deployment
* Device dependency

---

## 3. Biometric Authentication

Users authenticate using unique physical or behavioral characteristics.

Examples:

* Fingerprint
* Face recognition
* Iris scan
* Voice recognition

### Working

```mermaid
flowchart LR
    User --> Sensor
    Sensor --> FeatureExtraction
    FeatureExtraction --> Matcher
    Matcher --> Access
```

### Advantages

* Convenient
* Difficult to share
* Cannot be forgotten

### Limitations

* Privacy concerns
* Sensor errors
* Difficult to revoke
* Expensive hardware

---

## Comparison of Authentication Methods

| Feature             | Password | Token  | Biometric |
| ------------------- | -------- | ------ | --------- |
| Factor Type         | Know     | Have   | Are       |
| Cost                | Low      | Medium | High      |
| Ease of Use         | Medium   | Medium | High      |
| Theft Risk          | High     | Medium | Low       |
| Forgettable         | Yes      | No     | No        |
| Revocable           | Yes      | Yes    | Difficult |
| Phishing Resistance | Low      | Medium | High      |

---

## Multi-Factor Authentication (MFA)

Modern systems combine multiple methods.

Example:

```text
Password + OTP

Fingerprint + Smart Card
```

This significantly improves security.

---

## Exam Points

* Authentication verifies identity.
* Passwords are knowledge factors.
* Tokens are possession factors.
* Biometrics are inherence factors.
* MFA combines multiple factors.
* Biometric systems face privacy challenges.

---

## One-Line Summary

> Cryptographic authentication methods verify user identity using knowledge factors, possession factors, and inherence factors, with modern systems combining multiple approaches through multi-factor authentication.
