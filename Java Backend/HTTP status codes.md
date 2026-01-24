Proceeding.

---

# 📘 Backend Learning Note — HTTP Status Codes

---

## 0. The real problem this concept solves

When a client sends a request, **many things can happen**:

* the request succeeds
* the request is invalid
* the client is unauthorized
* the server fails internally
* the request is accepted but not completed yet

If every response looked the same:

* clients would not know what happened
* retries would be unsafe
* errors would be misinterpreted
* automation and tooling would break

The real problem is:

> **How does the server communicate the *outcome* of a request in a standardized, machine-readable way?**

**One-line anchor:**

> HTTP status codes communicate the result of a request using a standardized numeric contract.

---

## 1. What this concept really is (plain English)

An **HTTP status code** is a **number sent by the server** that tells the client:

* whether the request succeeded or failed
* who is responsible (client or server)
* what kind of follow-up action is appropriate

Status codes are:

* part of the HTTP response
* independent of the response body
* designed for both humans *and* machines

**One-line anchor:**

> Status codes tell the client how to interpret the response, not just what data it contains.

---

## 2. How it actually works (step-by-step)

1. Client sends an HTTP request
2. Server processes the request
3. Server determines the outcome
4. Server selects an appropriate status code
5. Server sends:

   * status code
   * headers
   * optional body
6. Client reacts based on the status code

Clients often decide:

* retry or not
* show error or success
* redirect or stop

**One-line anchor:**

> Clients rely on status codes to decide behavior.

---

## 3. Status code classes (the mental model)

You do **not** need to memorize every code.
You must understand the **classes**.

### 1xx — Informational

* Rarely used directly
* Mostly protocol-level signals

Awareness only.

---

### 2xx — Success

Indicates the request was successfully received, understood, and processed.

Common ones:

* **200 OK** → success with response body
* **201 Created** → resource successfully created
* **204 No Content** → success with no body

Mental rule:

> “The server did what the client asked.”

---

### 3xx — Redirection

Indicates further action is required.

Common ones:

* **301 / 302** → redirect
* **304 Not Modified** → cached response still valid

Used heavily with caching and browsers.

---

### 4xx — Client errors

The request is **invalid or not allowed**.

Common ones:

* **400 Bad Request** → malformed or invalid input
* **401 Unauthorized** → authentication required
* **403 Forbidden** → authenticated but not allowed
* **404 Not Found** → resource does not exist

Mental rule:

> “The client must change the request.”

---

### 5xx — Server errors

The server **failed to handle a valid request**.

Common ones:

* **500 Internal Server Error** → generic server failure
* **502 / 503** → upstream or availability issues

Mental rule:

> “The server failed; retry may help.”

---

**One-line anchor:**

> Status code class indicates responsibility.

---

## 4. Common misconceptions (explicitly corrected)

### ❌ “200 OK for everything”

Wrong.
It removes all meaning from the protocol.

---

### ❌ “Status codes are for humans”

Wrong.
Clients, SDKs, proxies, and retries rely on them.

---

### ❌ “Error details belong in status codes”

Wrong.
Details belong in the response body; codes signal category.

---

**One-line anchor:**

> Status codes are control signals, not error messages.

---

## 5. Failure modes / edge cases

### a) Incorrect 2xx on failure

Client assumes success and proceeds incorrectly.

---

### b) Incorrect 4xx vs 5xx

Breaks retry logic and client behavior.

---

### c) Overloading one status code

Leads to brittle clients and hidden bugs.

---

**One-line anchor:**

> Incorrect status codes cause cascading failures.

---

## 6. Backend relevance (non-negotiable)

HTTP status codes directly affect:

* client UX
* retry mechanisms
* API gateways
* monitoring and alerting
* debugging production issues

In Spring:

* returning a status code is a **semantic decision**
* not just a controller implementation detail

**One-line anchor:**

> Choosing a status code is choosing how the system communicates failure and success.

---

## 7. Final mental model

> Status codes answer one question:
> **“Who needs to act next — the client or the server?”**

If you answer that correctly, you’ll choose the right code.

---

## 8. Links (for Obsidian)

* [[HTTP methods semantics]]
* [[Request–Response lifecycle]]
* [[REST principles]]
* [[Headers vs Body]]

---

### Stop check (important)

You should now be able to answer:

* when to use 4xx vs 5xx
* why 204 exists
* why retries depend on status codes

---

Say one:

* **“Anki for HTTP status codes”**
* **“Next: Headers vs Body”**

We continue cleanly.
