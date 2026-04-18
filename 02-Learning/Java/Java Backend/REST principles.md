Proceeding.

---

# 📘 Backend Learning Note — REST Principles

---

## 0. The real problem this concept solves

As systems grow, APIs are consumed by:

* many clients
* written by different teams
* evolving over time

Without shared principles, APIs become:

* inconsistent
* tightly coupled to clients
* hard to evolve without breaking users

The real problem is:

> **How do we design APIs that are predictable, evolvable, and scalable across many clients?**

**One-line anchor:**

> REST provides constraints for designing scalable, uniform APIs over HTTP.

---

## 1. What this concept really is (plain English)

**REST (Representational State Transfer)** is **not a framework** and **not HTTP itself**.

It is a **set of architectural constraints** that guide how systems:

* expose resources
* use HTTP semantics
* remain scalable and evolvable

REST describes **how APIs should behave**, not how they are implemented.

**One-line anchor:**

> REST is a set of design constraints, not a technology.

---

## 2. How it actually works (step-by-step)

At a high level, RESTful interaction looks like this:

1. The server exposes **resources** (not actions)
2. Each resource has a **unique identifier** (URL)
3. Clients interact with resources using **standard HTTP methods**
4. Requests are **stateless**
5. Responses contain **representations** of resources
6. The server does not remember client state between requests

Clients and servers evolve independently as long as the contract holds.

**One-line anchor:**

> REST standardizes interaction, not implementation.

---

## 3. Key internal pieces (only after understanding)

### a) Resources, not actions

REST models the system as **resources**:

* users
* orders
* products

URLs identify **things**, not verbs.

Example:

* `/users/123` ✅
* `/getUser?id=123` ❌

---

### b) Uniform interface

REST relies on:

* standard HTTP methods
* standard status codes
* consistent semantics

This allows:

* tooling
* caching
* intermediaries
  to work without custom logic.

---

### c) Statelessness (non-negotiable)

Each request:

* carries all required context
* does not rely on server memory

This enables:

* horizontal scaling
* failure recovery
* load balancing

---

### d) Representation

Clients do not access server internals.
They receive **representations**:

* JSON
* XML
* etc.

The same resource can have different representations.

---

### e) Cacheability (often ignored)

Responses should declare whether they:

* can be cached
* how long they are valid

This reduces:

* latency
* server load

---

**One-line anchor:**

> REST constrains behavior so systems scale without coordination.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “REST means JSON APIs”

Wrong.
REST is format-agnostic.

---

### ❌ “REST is just CRUD over HTTP”

Wrong.
CRUD is an implementation pattern, not REST itself.

---

### ❌ “If it uses HTTP, it’s REST”

Wrong.
HTTP can be used in non-RESTful ways.

---

**One-line anchor:**

> REST is about constraints, not endpoints.

---

## 5. Failure modes / edge cases

### a) Overloaded endpoints

Too many responsibilities per endpoint break uniformity.

---

### b) Action-based URLs

Leads to tight coupling and poor evolvability.

---

### c) Hidden server state

Breaks statelessness and scalability.

---

### d) Ignored HTTP semantics

Using 200 for all responses defeats REST benefits.

---

**One-line anchor:**

> Most “REST APIs” fail by ignoring REST constraints.

---

## 6. Backend relevance (non-negotiable)

REST principles explain:

* why stateless APIs scale
* why HTTP methods matter
* why status codes exist
* why caching works
* why microservices can evolve independently

Spring MVC and Spring Boot are **tools to implement REST**, not REST itself.

**One-line anchor:**

> REST is the reason backend APIs remain manageable at scale.

---

## 7. Final mental model

> REST is about exposing resources through a uniform, stateless interface using standard HTTP semantics.

If you design with constraints first, implementation becomes easier.

---

## 8. Links (for Obsidian)

* [[HTTP protocol basics]]
* [[Stateless vs Stateful systems]]
* [[HTTP methods semantics]]
* [[HTTP status codes]]
* [[Headers vs Body]]