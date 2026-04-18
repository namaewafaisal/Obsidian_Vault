Below are **clean, precise notes on IoC and Dependency Injection** — **only what you need**, no repetition, no fluff, backend-focused.

---

# Inversion of Control (IoC) & Dependency Injection (DI)

## 1. Core Idea (Understand This First)

### Inversion of Control (IoC)

IoC means **your code does not create or manage its dependencies**.
The **framework controls object creation and wiring**.

> Control is inverted: *from your code → to the container*.

---

### Dependency Injection (DI)

DI is **the mechanism** used to achieve IoC.

> Dependencies are **provided (injected)** instead of being **created (`new`)**.

IoC = principle
DI = implementation of that principle

---

## 2. Problem Without DI (Why It Exists)

### Traditional Code

```java
class OrderService {
    private PaymentService paymentService = new PaymentService();
}
```

Problems:

* Tight coupling
* Hard to change implementation
* Hard to test (mocking difficult)

---

## 3. With DI (What Changes)

```java
class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Now:

* OrderService does **not care** how PaymentService is created
* Dependency can be swapped
* Code becomes testable and flexible

---

## 4. Role of Spring (Very Important)

Spring provides an **IoC Container** that:

* Creates objects (beans)
* Injects dependencies
* Manages lifecycle

You **do not** manually manage dependencies.

---

## 5. Beans (Key Term)

A **Bean** is:

* An object managed by Spring IoC container

Created using:

* `@Component`
* `@Service`
* `@Repository`
* `@Controller`
* `@Bean` (explicit)

---

## 6. Types of Dependency Injection (Know Exactly These)

### 1. Constructor Injection (Recommended)

```java
@Service
class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Why best:

* Mandatory dependencies
* Immutable
* Test-friendly
* Preferred by Spring

---

### 2. Setter Injection (Optional Dependency)

```java
@Service
class OrderService {
    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Use only when dependency is optional.

---

### 3. Field Injection (Avoid)

```java
@Autowired
private PaymentService paymentService;
```

Problems:

* Hard to test
* Breaks immutability
* Hidden dependencies

Avoid in production code.

---

## 7. `@Autowired` (What It Actually Does)

* Tells Spring: **inject a matching bean**
* Matching is done by:

  1. Type
  2. Qualifier (if needed)

Constructor injection does **not require** `@Autowired` (Spring 4.3+).

---

## 8. Multiple Implementations Problem

If multiple beans match:

```java
interface PaymentService { }
```

Solutions:

* `@Primary`
* `@Qualifier`

Example:

```java
@Qualifier("paypalService")
```

---

## 9. IoC Container Types (Basic Awareness)

* **BeanFactory** → basic
* **ApplicationContext** → advanced (used by Spring Boot)

Spring Boot uses **ApplicationContext**.

---

## 10. Lifecycle (Minimal Knowledge)

Spring:

1. Creates bean
2. Injects dependencies
3. Manages usage
4. Destroys on shutdown

You rarely manage this manually.

---

## 11. Why DI Is Critical for Backend

DI enables:

* Loose coupling
* Clean architecture (Controller → Service → Repository)
* Easy testing (mocking)
* Scalable codebase

Spring Boot **depends entirely** on DI.

---

## 12. What You Can Ignore (For Now)

* XML-based configuration
* Custom scopes
* Bean post-processors
* Advanced lifecycle hooks

---

## One-Line Summary (Memorize)

> **IoC** means Spring controls object creation.
> **DI** means Spring injects required dependencies instead of you creating them.

---

If you want next:

* **DI flow in a real Spring Boot REST API**
* **Common DI mistakes**
* **Why constructor injection is preferred**
* **How Spring resolves dependencies internally**

Tell me which one.
