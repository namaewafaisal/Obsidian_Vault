# Singleton
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[🏠 Home]]

---

## What Singleton Means

> **Only one instance of a class exists in the container.**

---

## Singleton in Spring

- Singleton is a **scope**, not a design pattern you write yourself
- Spring creates **one bean per container** by default
- Default scope = **singleton**

---

## What Happens

```java
Alien a1 = context.getBean(Alien.class);
Alien a2 = context.getBean(Alien.class);

a1 == a2  // → true — same object
```

---

## Why Spring Uses Singleton by Default

- Memory efficient
- Faster object access
- Suitable for **stateless services**

> Controllers, Services, Repositories → all singleton by default.

---

## When Singleton Is a Problem

Bad if the bean holds **user-specific or request-specific state**:

```java
@Service
class UserService {
    String username; // ❌ shared across ALL users
}
```

---

## Other Scopes (Awareness)

| Scope | Meaning |
|-------|---------|
| `singleton` | One object per container (default) |
| `prototype` | New object every time `getBean()` is called |
| `request` | One per HTTP request (web apps) |
| `session` | One per HTTP session (web apps) |

---

## One-Line Summary

> **In Spring, Singleton means one bean instance per container, reused everywhere by default.**

---

**Prev →** [[04-Maven|Maven]]
**Next →** [[06-BeanFactory|BeanFactory]]
