---
title: 6.3 - Coupling
part: 6
---
# 6.3 — Coupling

Coupling measures:
> dependency between modules.

Good design prefers:
> Low Coupling

---

# Low Coupling Example

```text
AuthService
    ↓ API Call
NotificationService
```

Modules communicate through:
- APIs
- interfaces
- method contracts

Internal implementation remains hidden.

---

# High Coupling Example

```text
PaymentService directly modifies
UserService internal variables
```

Changes in one module may break another module.

---

# Benefits of Low Coupling

- easier maintenance
- easier testing
- independent development
- fewer side effects
- easier scalability

---

# Types of Coupling
(Worst → Best)

---

# 1. Content Coupling

One module directly accesses another module’s internals.

```java
otherModule.internalData = 5;
```

---

# 2. Common Coupling

Multiple modules share global data.

```java
globalConfig
```

---

# 3. Control Coupling

One module controls another using flags.

```java
process(true)
```

---

# 4. Stamp Coupling

Entire data structure passed though only part is needed.

```java
process(User user)
```

Only:
```text
user.name
```

is actually used.

---

# 5. Data Coupling (Best Practical)

Modules communicate using only required data.

```java
process(String username)
```

---

# Coupling Hierarchy

```text
Content
→ Common
→ Control
→ Stamp
→ Data
```

Lower is better.

---

# Coupling vs Cohesion

| Coupling | Cohesion |
|---|---|
| Between modules | Inside module |
| Dependency | Internal relatedness |
| Prefer Low | Prefer High |

---

# Important Insight

Good software design aims for:
> Low Coupling + High Cohesion

This improves:
- maintainability
- modularity
- scalability
- testing