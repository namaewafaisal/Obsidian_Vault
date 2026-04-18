Proceeding.

---

# 📘 Backend Learning Note — HTTP Methods Semantics

---

## 0. The real problem this concept solves

Clients need to **interact with server-side resources** in different ways:

* read data
* create new data
* update existing data
* remove data

If every interaction were treated the same way:

* APIs would be ambiguous
* caching would break
* retries would cause corruption
* intermediaries (proxies, gateways) would be useless

The real problem is:

> **How do we express *intent* and *safety guarantees* for operations on resources in a standard way?**

**One-line anchor:**

> HTTP methods define the intent and guarantees of an operation on a resource.

---

## 1. What this concept really is (plain English)

HTTP methods are **verbs** that describe:

* what the client wants to do to a resource
* what guarantees the server can rely on
* how intermediaries may treat the request

They are **not just routing hints**.
They encode **behavioral contracts**.

**One-line anchor:**

> HTTP methods communicate intent and constraints, not implementation.

---

## 2. The core methods (semantics first, not CRUD)

### a) GET — Read without side effects

* Retrieves a representation of a resource
* **Must not change server state**
* Can be cached
* Can be safely retried

Mental rule:

> “Calling this twice should change nothing.”

---

### b) POST — Create or trigger processing

* Submits data to the server
* May create new resources
* May cause side effects
* **Not idempotent**

Mental rule:

> “Calling this twice may do the thing twice.”

---

### c) PUT — Replace the resource

* Replaces the entire resource at a known URI
* **Idempotent**
* Safe to retry

Mental rule:

> “Calling this twice results in the same final state.”

---

### d) PATCH — Partial update

* Modifies part of a resource
* Usually **not idempotent** (depends on implementation)
* More expressive than PUT

Mental rule:

> “Change only what is specified.”

---

### e) DELETE — Remove the resource

* Deletes a resource
* **Idempotent**
* Repeating it has no additional effect

Mental rule:

> “Deleting something twice leaves it deleted.”

---

**One-line anchor:**

> Method choice defines retry safety and failure behavior.

---

## 3. Key semantic properties (this is the core)

### a) Safety

A method is **safe** if it does not modify server state.

* Safe: GET
* Unsafe: POST, PUT, PATCH, DELETE

---

### b) Idempotency (very important)

A method is **idempotent** if repeating it yields the same result.

* Idempotent: GET, PUT, DELETE
* Not idempotent: POST
* PATCH: depends

This determines:

* retry behavior
* failure handling
* client robustness

---

### c) Cacheability

* GET responses can be cached
* POST usually cannot
* Proper semantics unlock performance gains

---

**One-line anchor:**

> Idempotency determines whether retries are safe.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “HTTP methods are just conventions”

Wrong.
Intermediaries rely on them.

---

### ❌ “POST is fine for everything”

Wrong.
It destroys retry safety and caching benefits.

---

### ❌ “PUT and PATCH are interchangeable”

Wrong.
They have different semantic guarantees.

---

**One-line anchor:**

> Ignoring method semantics breaks reliability at scale.

---

## 5. Failure modes / edge cases

### a) Network retry duplicates POST

Creates duplicate resources.

---

### b) Misusing GET for mutations

Breaks caching and violates REST constraints.

---

### c) Incorrect idempotency assumptions

Causes subtle data corruption under retries.

---

**One-line anchor:**

> Most API bugs appear under retries, not happy paths.

---

## 6. Backend relevance (non-negotiable)

HTTP method semantics affect:

* retry strategies
* load balancers
* API gateways
* client SDKs
* caching layers

Spring annotations (`@GetMapping`, etc.) are **semantic commitments**, not just mappings.

**One-line anchor:**

> Choosing a method is choosing system behavior.

---

## 7. Final mental model

> HTTP methods define *what kind of operation* is happening and *what guarantees* the system can rely on under failure.

If you choose methods correctly, distributed systems behave predictably.

---

## 8. Links (for Obsidian)

* [[REST principles]]
* [[Request–Response lifecycle]]
* [[HTTP status codes]]
* [[Headers vs Body]]
