---
tags: [java, memory-model, core-java, cheatsheet]
date-time: INVALID_DATETIME
status: revision
---

# [[Memory Model]] — Core Java Cheatsheet

This note is for **recall of where things live and why behavior happens**.

---

## 1️⃣ Stack vs Heap (MOST IMPORTANT)

### Stack
- Method calls
- Local variables
- Reference variables
- Method execution order

### Heap
- Objects
- Instance variables
- Arrays
- Shared across threads

---

## 2️⃣ What Goes Where (Quick Table)

| Item | Location |
|----|----|
| Local primitive | Stack |
| Local reference | Stack |
| Object | Heap |
| Instance variables | Heap |
| Static variables | Heap (method area) |

---

## 3️⃣ Reference vs Object

```java
Test t = new Test();
```

- `t` → reference (stack)
- `new Test()` → object (heap)

```java
t = null;
```

- Reference removed
- Object becomes **eligible for GC**

---

## 4️⃣ Multiple References

```java
Test a = new Test();
Test b = a;
```

- One object
- Two references
- Both point to same heap object

---

## 5️⃣ Garbage Collection (Awareness Only)

- Happens automatically
- Object collected when **no reachable references**
- `System.gc()` → request, not guarantee
- `finalize()` may run (legacy)

---

## 6️⃣ Common MCQ Traps

- Stack is cleaned automatically
- Heap objects live beyond method scope
- References are copied, not objects
- GC does NOT run at predictable time

---

## 🔗 Related Notes
- [[Pass-by-Value]]
- [[Wrapper Classes]]
- [[Object Class]]

---

## 🔒 One-Line Recall

> **Variables live on stack, objects live on heap.  
> References point, objects store data.**

---
# [[Pass-by-Value]] — Core Java Cheatsheet

Java is **always pass-by-value**.

---

## 1️⃣ What “Pass-by-Value” Means in Java

- Java copies the **value** of arguments
- For objects, the **value is the reference**
- Original variables are never changed

---

## 2️⃣ Primitive Example

```java
void change(int x) {
    x = 20;
}

int a = 10;
change(a);
System.out.println(a);
```

**Output**
```
10
```

- `x` is a copy
- `a` unchanged

---

## 3️⃣ Object Example (MOST CONFUSION)

```java
void change(Test t) {
    t = new Test();
}

Test obj = new Test();
change(obj);
```

- `t` is a copy of reference
- Reassigning `t` does NOT affect `obj`

---

## 4️⃣ Mutating Object State (THIS WORKS)

```java
void change(Test t) {
    t.x = 20;
}
```

- Object state changed
- Visible to caller

---

## 5️⃣ Why Swap Doesn’t Work

```java
void swap(Integer a, Integer b) {
    Integer temp = a;
    a = b;
    b = temp;
}
```

- Only local reference copies swapped
- Caller references unchanged

---

## 6️⃣ What You CAN Do Instead

### Option 1: Mutate object fields
```java
box.value = 20;
```

### Option 2: Return values
```java
return new Integer[]{b,a};
```

### Option 3: Wrapper object
```java
class Ref { int x; }
```

---

## 7️⃣ MCQ Rules (LOCK THESE)

- Java never passes variables
- Reference reassignment never escapes method
- Object mutation is visible
- Wrappers & String are immutable

---

## 🔗 Related Notes
- [[Memory Model]]
- [[Wrapper Classes]]
- [[String]]

---

## 🔒 One-Line Recall

> **Java passes copies of values.  
> Objects can be mutated, references cannot be replaced.**

---
