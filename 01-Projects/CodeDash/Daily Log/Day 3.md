Giving functionalities to the structure created on [Day 2](Day%202.md)
Created Institution registration endpoint and service method
Use this to erase all table and schema
```sql
DROP SCHEMA public CASCADE
CREATE SCHEMA public;
```

Did a lot today. created separate tables for pending institutions and user requests.

Good — this is a solid milestone. Let’s log it cleanly so you can reuse it later.

---

# 📘 CodeDash Progress Log (Phase 1)

## 🎯 Objective

Build **core authentication + institution onboarding system**

---

# 🧱 Architecture Decisions

### ✅ 1. Separated Pending vs Final Data

* `users` → only **verified users**
* `pending_users` → unverified registrations
* `institutions` → only **approved institutions**
* `pending_institutions` → awaiting approval

👉 Result:

* clean data
* no “half-valid” records
* easier reasoning

---

# 🏫 Institution Flow

### ✅ Register Institution

* Validates:

  * no generic domains
  * domain uniqueness (both tables)
  * admin email matches domain
* Stores in:

  * `pending_institutions`

---

### ✅ Approve Institution (Master)

* Moves:

  * `pending_institutions → institutions`
* Creates:

  * admin user in `users`
* Deletes:

  * pending record

---

### ✅ Reject Institution

* Deletes from pending
* (email logic later)

---

# 👤 User (Student) Flow

### ✅ Register

* Checks:

  * institution exists (already approved)
  * domain matches
  * not already registered
* Handles:

  * re-registration (expired token)
* Stores in:

  * `pending_users`
* Generates:

  * UUID token + expiry

---

### ✅ Verify (Email link)

* Endpoint:

  ```http
  GET /api/auth/verify?token=...
  ```
* Flow:

  * validate token
  * check expiry
  * move → `users`
  * delete from `pending_users`

---

### ✅ Login

* Blocks:

  * unverified users (in pending)
* Validates:

  * email + password
* Returns:

  * basic user info (no JWT yet)

---

# 🔐 Security Decisions

* Passwords → **BCrypt hashed**
* Tokens → **UUID**
* Expiry → enforced
* No sensitive error leaks (generic messages)

---

# 🧠 Key Design Learnings

* State-based design > multiple flags
* Avoid partial entities in main tables
* Re-registration = retry, not new entity
* GET is valid for verification links
* Don’t over-engineer (no Redis yet)

---

# 🧪 Testing Strategy

* Manual **end-to-end flow testing**
* Tested:

  * happy path ✔
  * duplicate registration ✔
  * expired token ✔
  * login before verify ✔

---

# 📦 Current System State

You now have:

* ✔ Institution onboarding system
* ✔ Email verification system
* ✔ Clean DB separation
* ✔ Working auth (without JWT)

👉 This is a **real backend foundation**

---

# 🚀 Next Phase

👉 **JWT Authentication**

* login → returns token
* secure endpoints
* role-based access (ADMIN / STUDENT)

---

# 🧾 One-line Summary

👉 You built a **production-grade registration + verification system with clean domain modeling**

---

If you want, next I’ll:
👉 map **JWT step in the same structured way** (no confusion)
