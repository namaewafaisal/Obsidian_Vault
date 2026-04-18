Got it — I’ll keep the **same structure as your notes** and only cover the **remaining important concepts**.

---

# Generational Garbage Collection

## What is it?

Instead of treating all objects equally, JVM divides Heap into regions based on **object lifetime**.

👉 Key observation:
Most objects die **very quickly**

---

## Heap Structure

```
Heap
├── Young Generation
│   ├── Eden
│   ├── Survivor S0
│   └── Survivor S1
│
└── Old Generation
```

---

## How it Works

### Step 1 — Object Creation

* New objects → **Eden**

---

### Step 2 — Minor GC (Young GC)

* Eden fills → Minor GC runs
* Surviving objects → moved to Survivor space

```
Eden → S0 → S1 → (age increases)
```

---

### Step 3 — Promotion

* Objects that survive multiple cycles → moved to **Old Generation**

---

### Step 4 — Major / Full GC

* Runs on Old Gen
* Slower (more data)

---

## Why this is fast

* Most objects die in Eden → quick cleanup
* Old objects scanned rarely

---

## What it CAN do

* Reduce GC time drastically
* Optimize for real-world object usage

---

## Limitations

* Full GC is still slow
* Wrong tuning → performance issues

---

# Stop-The-World (STW)

## What is it?

During GC, JVM pauses all threads:

```
All threads → paused ❌
GC runs → cleanup
Threads resume ✅
```

---

## Why needed?

* Prevent object graph from changing during traversal

---

## Types

* Minor GC → short pause
* Major GC → longer pause

---

## Problem

* Causes latency spikes
* Critical in real-time systems

---

# Types of References

## 1. Strong Reference (default)

```java
Car c = new Car();
```

* Object **never collected** while reference exists

---

## 2. Weak Reference

```java
WeakReference<Car> ref = new WeakReference<>(new Car());
```

* GC can collect anytime

---

## 3. Soft Reference

```java
SoftReference<Car> ref = new SoftReference<>(new Car());
```

* Collected only when memory is low

👉 Used in caches

---

## 4. Phantom Reference (advanced)

* Used for cleanup tracking
* Rarely needed

---

## Summary

| Type    | GC Behavior               |
| ------- | ------------------------- |
| Strong  | Never collected           |
| Weak    | Collected anytime         |
| Soft    | Collected when memory low |
| Phantom | Post-cleanup tracking     |

---

# Memory Leaks in Java

## What is it?

Object is **not used but still reachable**

👉 GC cannot free it

---

## Common Causes

### 1. Static collections

```java
static List<Object> list = new ArrayList<>();
```

* Lives forever → GC Root

---

### 2. Unclosed resources

* Streams, DB connections

---

### 3. Listeners / callbacks

* Not removed → still referenced

---

### 4. Caches without limit

* Keep growing

---

## Fix Strategies

* Remove references
* Use WeakReference
* Clear collections
* Use bounded caches

---

# Minor vs Major vs Full GC

| Type     | Area        | Speed   |
| -------- | ----------- | ------- |
| Minor GC | Young       | Fast    |
| Major GC | Old         | Slow    |
| Full GC  | Entire Heap | Slowest |

---

# Final Mental Model

```
GC Roots → Mark reachable → Delete rest

+ Optimization:
Young Gen → frequent fast cleanup
Old Gen → rare slow cleanup
```

---

## 🔥 Final Level Understanding

* GC is **graph traversal**
* Generational GC = **optimization**
* STW = **cost**
* References = **control behavior**
* Leaks = **your responsibility**

---

