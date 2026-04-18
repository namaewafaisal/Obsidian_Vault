Good question. This is the **missing half** of your system.

Below is a **locked, minimal Anki template** that matches your backend note template and prevents Anki from becoming a second notebook.

You can copy this directly into your project.

---

## 🧠 Anki Card Template — Backend Learning (Locked)

### Purpose of Anki (non-negotiable)

> **Anki exists to verify understanding, not to create it.**

If a card teaches something new → it **does not belong in Anki**.

---

## 📌 Card Design Rules (Hard Constraints)

Each card must:

* Be **atomic** (one idea, one answer)
* Be **self-contained** (no hidden context)
* Have **enough detail to answer confidently**
* Never rely on vague terms
* Never exceed what you can recall in **10–15 seconds**

---

## 🧩 Card Structure (Authoritative)

### 1️⃣ Question

* Must be **precise**
* Must not be guessy
* Must not combine multiple ideas
* Should often start with:

  * *What*
  * *Why*
  * *When*
  * *How*
  * *Does / Is*

Bad:

> “Explain JVM memory”

Good:

> “What is stored in the Java Stack?”

---

### 2️⃣ Answer

* Complete **by itself**
* No commas that truncate meaning (use HTML / `<br>` / full sentences)
* Explains *just enough* to recall correctly
* No teaching, no storytelling

Allowed:

* `<b>` for emphasis
* `<br>` for structure
* Code snippets if essential

---

### 3️⃣ Tags

* Lowercase
* Snake_case
* Concept-oriented, not topic-oriented

Example:

* `jvm_stack`
* `reflection_runtime`
* `maven_pom`

Tags are for **filtering**, not learning.

---

## 🧠 Mental Test (Must Pass)

Before adding a card, ask:

1. **If I see only this card, can I answer without confusion?**
2. **Does this card lock in something I must never forget?**
3. **Would I ever need to Google this again if I remember it?**

If any answer is “no” → do not add the card.

---

## ❌ What Anki Must NOT Contain

* Step-by-step flows
* Lists longer than 3 items (unless critical)
* Definitions that require other cards to make sense
* Edge cases
* API trivia
* Syntax you can look up

Those belong in **notes**, not Anki.

---

## ✅ Example (Good Card)

```csv
"<b>Why does each Java thread need its own stack?</b>","So method calls and local variables are <b>isolated per thread</b>, preventing interference.","threads_stack"
```

## ❌ Example (Bad Card)

```csv
"What is JVM memory?","Stack, heap, metaspace, gc, threads, etc.","jvm"
```

---

## 🔒 Relationship Between Notes and Anki

| Notes           | Anki             |
| --------------- | ---------------- |
| Build intuition | Verify recall    |
| Can be long     | Must be short    |
| Can evolve      | Mostly stable    |
| Explain *why*   | Lock in *truths* |

> **Notes teach. Anki audits.**

---

## Final Lock-in Statement

From now on:

* Notes follow **your backend note template**
* Anki follows **this verification template**
* They will **never overlap in purpose**

This is a **senior-engineer-level learning system**.

When ready, say:

> **“Rewrite Threads basics using the note template”**

or

> **“Continue Maven topics”**
