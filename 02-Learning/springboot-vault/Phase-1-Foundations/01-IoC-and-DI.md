# IoC & Dependency Injection
**Phase:** [[README|Phase 1 — Foundations]]
**Back:** [[../🏠 Home]]

---

## 1. Core Idea

### Inversion of Control (IoC)
IoC means **your code does not create or manage its dependencies**.
The **framework controls object creation and wiring**.

> Control is inverted: *from your code → to the container*.

### Dependency Injection (DI)
DI is **the mechanism** used to achieve IoC.

> Dependencies are **provided (injected)** instead of being **created with `new`**.

```
IoC  = the principle
DI   = the implementation of that principle
```

---

## 2. Problem Without DI

```java
class OrderService {
    // ❌ Tight coupling — you control creation
    private PaymentService paymentService = new PaymentService();
}
```

**Problems:**
- Tight coupling
- Hard to change implementation
- Hard to test (mocking is difficult)

---

## 3. With DI

```java
class OrderService {
    private PaymentService paymentService;

    // ✅ Dependency is provided from outside
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Now:
- OrderService does **not care** how PaymentService is created
- Dependency can be swapped
- Code is testable and flexible

---

## 4. Role of Spring Container

Spring provides an **IoC Container** that:
- Creates objects (beans)
- Injects dependencies
- Manages lifecycle

You **do not** manually manage dependencies.

---

## 5. What is a Bean?

A **Bean** is an object managed by Spring's IoC container.

Created using annotations:
- `@Component`
- `@Service`
- `@Repository`
- `@Controller`
- `@Bean` (explicit)

---

## 6. Types of Dependency Injection

### ✅ Constructor Injection (Recommended)

```java
@Service
class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

**Why best:**
- Mandatory dependencies
- Immutable (`final`)
- Test-friendly
- Spring's preferred approach

---

### Setter Injection (Optional Dependencies)

```java
@Autowired
public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
}
```

Use only when the dependency is optional.

---

### ❌ Field Injection (Avoid)

```java
@Autowired
private PaymentService paymentService;
```

**Problems:**
- Hard to test
- Breaks immutability
- Hidden dependencies

---

## 7. @Autowired

Tells Spring: **inject a matching bean here**.

Matching done by:
1. Type
2. `@Qualifier` (if multiple matches)

> Constructor injection does **not** require `@Autowired` (Spring 4.3+).

---

## 8. Multiple Implementations Problem

```java
// Two beans implement same interface
@Service class PaypalService implements PaymentService {}
@Service class StripeService implements PaymentService {}
```

**Solutions:**
- `@Primary` — marks default bean
- `@Qualifier("paypalService")` — picks specific one

```java
public OrderService(@Qualifier("paypalService") PaymentService ps) { ... }
```

---

## 9. Why DI Is Critical for Backend

DI enables:
- Loose coupling
- Clean architecture (`Controller → Service → Repository`)
- Easy testing (mock dependencies)
- Scalable codebase

> Spring Boot **depends entirely** on DI.

---

## One-Line Summary

> **IoC** = Spring controls object creation.
> **DI** = Spring injects required dependencies so you don't use `new`.

---

**Next →** [[02-Spring-Bean|Spring Bean]]
