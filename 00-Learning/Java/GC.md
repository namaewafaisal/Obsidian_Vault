# How Garbage Collection Works in Java

## What is it?

Java automatically frees memory you're no longer using. You don't call `free()` like in C. The **Garbage Collector (GC)** is a background process that periodically figures out which objects on the Heap are no longer reachable from your code, and destroys them to reclaim memory.

The core question GC answers is not *"how many variables point to this object?"* but *"can my program actually get to this object from anywhere it's currently running?"*

---

## Real-World Analogy

Imagine a city where buildings are objects. Roads are references. You're standing at City Hall (your running program — the GC Roots).

GC's job: walk every road from City Hall. Mark every building you can reach by following roads. Any building with **no road connecting it back to City Hall** — demolish it, reclaim the land.

It doesn't matter if two abandoned buildings have a private road between each other. If neither is connected to City Hall, both get demolished.

This is exactly why Java doesn't use simple reference counting — two objects pointing at each other in an abandoned island don't save each other.

---

## Why Not Just Count References?

This is the intuitive idea — keep a counter per object, increment when someone points to it, decrement when they stop, free when counter hits zero.

It fails in one specific case that's extremely common in real code:

```java
class Node {
    Node next;
}

Node a = new Node();
Node b = new Node();
a.next = b;   // a points to b
b.next = a;   // b points to a — circular reference

// Now drop both Stack references
a = null;
b = null;
```

At this point:
- `a` on the Stack points to nothing
- `b` on the Stack points to nothing
- But the two Heap objects still point **to each other**

With reference counting, both objects have count = 1. Neither gets freed. Memory leaks forever. This is a real problem in Python (which does use reference counting) and why it needs a separate cycle detector on top.

Java skips reference counting entirely and uses something fundamentally different.

---

## What Java Actually Does — Reachability Analysis

Java GC starts from a fixed set of known-alive starting points called **GC Roots** and asks: *can I reach this object by following references from these roots?*

If yes → object is **alive**, keep it.
If no → object is **garbage**, free it.

That's the entire idea. Everything else is optimization on top of this.

---

## What are GC Roots?

GC Roots are the set of references that are **always considered alive** because your running program can directly access them right now:

| GC Root type | What it means |
|---|---|
| Local variables on active Stack frames | Variables inside currently running methods |
| Static variables | Fields declared `static` in any class — live as long as the class is loaded |
| Active thread objects | The threads themselves currently running |
| JNI references | References held by native C/C++ code calling into Java |

Think of these as the doors into your object graph. GC starts at every door and walks inward.

---

## The Actual Process Step by Step

Say your Heap has these objects and references:

```
Stack (GC Roots)
  │
  ├── c1 ──────► [Car object: speed=60] ──► [Engine object]
  │
  ├── c2 ──────► [Car object: speed=100]
  │
  └── (nothing points to these below)

                  [Tire object] ◄──► [Wheel object]   ← circular, but orphaned
```

GC does this:

**Step 1 — Mark phase:**
Start at every GC Root. Follow every reference. Mark everything you touch as reachable.

```
c1        → mark Car(60)
Car(60)   → mark Engine
c2        → mark Car(100)
Car(100)  → no outgoing refs

Tire and Wheel → never reached from any root → NOT marked
```

**Step 2 — Sweep phase:**
Scan the entire Heap. Everything **not marked** is garbage. Free it.

```
Car(60)  → marked → keep
Engine   → marked → keep
Car(100) → marked → keep
Tire     → NOT marked → DELETE
Wheel    → NOT marked → DELETE
```

Tire and Wheel pointed at each other, but it didn't matter. Nobody with a live Stack reference could reach them, so they're gone.

---

## Does GC Run Constantly?

No. It runs in **cycles**, not after every line of code. If it ran constantly your program would be paused every millisecond.

What actually triggers a GC cycle:
- Heap starts filling up (most common trigger)
- You explicitly call `System.gc()` (just a hint, JVM can ignore it)
- JVM decides based on internal heuristics

During a GC cycle, depending on the type, your program may **pause** briefly. This is called a **Stop-The-World pause** — the JVM freezes all your threads so GC can safely walk the object graph without things changing underneath it. Modern GCs minimize these pauses aggressively.

---

## Is There a Global Registry of References?

No registry. GC doesn't maintain a live list of every reference in existence.

Instead, when a GC cycle starts, it **dynamically discovers** references by walking:
- Every active Stack frame → find local reference variables
- Every reached Heap object → find its reference-type fields
- Every class in Method Area → find static reference fields

It's a graph traversal done fresh each cycle, not a pre-maintained list.

---

## The Circular Reference Problem — Solved

Back to the earlier example:

```java
Node a = new Node();
Node b = new Node();
a.next = b;
b.next = a;
a = null;
b = null;
```

After the last two lines, the Stack has no reference to either object.

GC starts from GC Roots. Walks every reachable object. Neither Node is reachable from any root. Both are marked as garbage and freed — even though they point at each other. The circular reference is irrelevant. Reachability from roots is the only thing that matters.

---

## What it CAN do
- Free objects the moment they become unreachable from roots (on next GC cycle)
- Handle circular references correctly — reference counting cannot
- Operate without any action from you — fully automatic
- Compact the Heap to reduce fragmentation (in some GC implementations)

---

## What it CANNOT do / Limitations
- Cannot free an object if **any** reference to it still exists anywhere reachable — even one forgotten static field keeps an object alive forever
- Cannot guarantee *when* it will run — only that it will eventually run
- Cannot prevent `OutOfMemoryError` if you genuinely hold references to everything and the Heap fills up
- `System.gc()` is just a suggestion — JVM can and often does ignore it

---

## Common Mistakes

**Mistake 1: Keeping references alive accidentally**

```java
// BROKEN — list keeps growing, old objects never freed
static List<byte[]> cache = new ArrayList<>();

void loadData() {
    cache.add(new byte[1024 * 1024]); // 1MB added every call
    // nobody clears cache — static reference keeps everything alive
}
```

```java
// FIX — clear or use bounded cache
void loadData() {
    cache.clear();
    cache.add(new byte[1024 * 1024]);
}
```

Static fields are GC Roots. Anything a static field references — directly or through a chain — will never be collected. This is the most common real memory leak in Java.

---

**Mistake 2: Thinking `null` instantly frees memory**

```java
Car c1 = new Car();
c1 = null;
// Object is NOT freed here — it's just now eligible for collection
// GC will free it at some future cycle
```

Setting to `null` removes the reference so the object becomes unreachable. It doesn't trigger immediate freeing.

---

**Mistake 3: Trusting `System.gc()`**

```java
System.gc(); // polite request, not a command
// JVM may completely ignore this
```

---

## Interactions with other concepts

- **Stack**: Local reference variables on the Stack are GC Roots. When a method returns its frame is popped — those references are gone, and objects they pointed to may become unreachable
- **Static fields**: Static variables in Method Area are permanent GC Roots as long as the class is loaded. Common source of memory leaks
- **`null`**: Assigning `null` to a reference removes that path to the object. If no other path exists, the object becomes collectible
- **`WeakReference`**: A special Java class that holds a reference GC deliberately ignores — used for caches where you want GC to reclaim the object if memory is needed even if you still hold a reference. Advanced but real

---

## Placement / Interview Traps

**Trap 1: Will this cause a memory leak?**
```java
class Registry {
    static List<Object> items = new ArrayList<>();

    static void register(Object o) {
        items.add(o);
    }
}
```
**Answer: Yes.** `items` is a static field, which is a GC Root. Every object ever passed to `register()` is held alive permanently unless explicitly removed. Static collections that grow without clearing are the classic Java memory leak.

---

**Trap 2: Are these objects eligible for GC after line 4?**
```java
Node a = new Node();   // line 1
Node b = new Node();   // line 2
a.next = b;            // line 3
a = null;              // line 4
```
**Answer: No.** After line 4, `a` on the Stack is null, but `b` on the Stack still points to the second Node. The first Node (`a.next = b` means the first Node pointed to second Node, not the other way) — actually let's trace carefully:
- First Node: was pointed to by `a`, now `a = null`. Is anyone else pointing to it? No. **First Node is eligible for GC.**
- Second Node: still pointed to by `b` on the Stack. **Not eligible.**

---

**Trap 3: Does reference counting explain Java GC?**
**Answer: No.** Reference counting fails on circular references. Java uses reachability analysis from GC Roots — a full graph traversal. Two objects pointing at each other with no path from any GC Root are both collected regardless of their mutual references.

---

## Quick Reference — Full Mental Model

```
GC ROOTS (always alive)
─────────────────────────────────────────────
  Stack frames     → local variables right now
  Static fields    → live as long as class loaded
  Active threads   → thread objects themselves


MARK PHASE — GC walks from every root
─────────────────────────────────────────────

  Root ──► Object A ──► Object B ──► Object C
                │
                └──► Object D

  All of A, B, C, D get marked ✓

  Object E ◄──► Object F   (circular, but no root reaches them)
  Neither gets marked ✗


SWEEP PHASE — anything unmarked is freed
─────────────────────────────────────────────
  A ✓ → keep
  B ✓ → keep
  C ✓ → keep
  D ✓ → keep
  E ✗ → DELETE
  F ✗ → DELETE


RESULT
─────────────────────────────────────────────
  Heap reclaimed for E and F
  A, B, C, D untouched
```

```java
class Node {
    Node next;
    String data;
}

public class GCDemo {
    static Node permanentNode = new Node(); // GC Root — never collected

    public static void main(String[] args) {

        // These will be eligible for GC after main() exits
        Node a = new Node();
        Node b = new Node();
        a.next = b;
        b.next = a;  // circular — doesn't save them

        // Drop Stack references
        a = null;
        b = null;
        // Both Node objects are now unreachable from any GC Root
        // GC will collect them on next cycle

        // permanentNode is static → GC Root → never collected
        // even if we did permanentNode = null, it would become eligible
    }
}
```

---

The next natural topic from here is **Generational GC** — why the Heap is split into Young and Old generations and why that makes GC fast in practice. Say **next** for that, or go back to the Java OOP note series.