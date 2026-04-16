
## CASE 1 — Same literal

### Code
```java
String a = "hi";
String b = "hi";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
Same reference from String Pool

---

## CASE 2 — new String()

### Code
```java
String a = "hi";
String b = new String("hi");
System.out.println(a == b);
```

### Prediction
FALSE
### Actual
FALSE
### Why
`b` is new object in heap

---

## CASE 3 — equals vs ==

### Code
```java
String a = "hi";
String b = new String("hi");
System.out.println(a.equals(b));
```

### Prediction
TRUE
### Actual
TRUE
### Why
Compares value, not reference

---

## CASE 4 — Compile-time concat

### Code
```java
String a = "hi" + "there";
String b = "hithere";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
Compile-time optimization → pooled

---

## CASE 5 — Runtime concat

### Code
```java
String a = "hi";
String b = a + "there";
String c = "hithere";
System.out.println(b == c);
```

### Prediction
FALSE
### Actual
FALSE
### Why
Runtime → new object in heap

---

## CASE 6 — intern()

### Code
```java
String a = "hi";
String b = (a + "there").intern();
String c = "hithere";
System.out.println(b == c);
```

### Prediction
TRUE
### Actual
TRUE
### Why
intern() returns pooled reference

---

## CASE 7 — new + intern

### Code
```java
String a = new String("hi").intern();
String b = "hi";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
intern forces pooling

---

## CASE 8 — Multiple new Strings

### Code
```java
String a = new String("hi");
String b = new String("hi");
System.out.println(a == b);
```

### Prediction
FALSE
### Actual
FALSE
### Why
Two separate heap objects

---

## CASE 9 — StringBuilder

### Code
```java
String a = new StringBuilder("hi").append("there").toString();
String b = "hithere";
System.out.println(a == b);
```

### Prediction
FALSE
### Actual
FALSE
### Why
Runtime → heap object

---

## CASE 10 — intern after builder

### Code
```java
String a = new StringBuilder("hi").append("there").toString().intern();
String b = "hithere";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
intern aligns reference

---

## CASE 11 — Final keyword (compile-time optimization)

### Code
```java
final String a = "hi";
String b = a + "there";
String c = "hithere";
System.out.println(b == c);
```

### Prediction
FALSE
### Actual
TRUE (JSHELL is the problem)
### Why
final → treated as compile-time constant

---

## CASE 12 — Non-final variable

### Code
```java
String a = "hi";
String b = a + "there";
String c = "hithere";
System.out.println(b == c);
```

### Prediction
FALSE
### Actual
FALSE
### Why
Runtime concat → not pooled

---

## CASE 13 — intern BEFORE pool literal

### Code
```java
String a = (new String("hi") + "there").intern();
String b = "hithere";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
First intern may define pool entry

---

## CASE 14 — Substring behavior

### Code
```java
String a = "hithere";
String b = a.substring(0, 2);
String c = "hi";
System.out.println(b == c);
```

### Prediction
FALSE
### Actual
FALSE
### Why
substring creates new object

---

## CASE 15 — String concat in loop

### Code
```java
String s = "";
for (int i = 0; i < 3; i++) {
    s += "a";
}
String t = "aaa";
System.out.println(s == t);
```

### Prediction
FALSE
### Actual
FALSE
### Why
Repeated runtime concat → heap

---

## CASE 16 — intern in loop

### Code
```java
String s = "";
for (int i = 0; i < 3; i++) {
    s += "a";
}
s = s.intern();
String t = "aaa";
System.out.println(s == t);
```

### Prediction
TRUE
### Actual
TRUE
### Why
intern fixes reference

---

## CASE 17 — String.valueOf

### Code
```java
String a = String.valueOf("hi");
String b = "hi";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE (String.valueOf() creates String in pool)
### Why
Returns same reference (no new object)

---

## CASE 18 — concat() method

### Code
```java
String a = "hi";
String b = a.concat("there");
String c = "hithere";
System.out.println(b == c);
```

### Prediction
FALSE
### Actual
FALSE
### Why
concat → new object

---

## CASE 19 — String literal creation order

### Code
```java
String a = (new String("hi") + "there").intern();
String b = "hithere";
System.out.println(a == b);
```

### Prediction
TRUE
### Actual
TRUE
### Why
Depends on whether pool already contains it

---

## CASE 20 — equals safety

### Code
```java
String a = null;
String b = "hi";
System.out.println(b.equals(a));
System.out.println(a.equals(b));
```

### Prediction
NPE
### Actual
NPE tries to read null
### Why
null → NPE when calling method



```java
  String s = sc.next();
  String a = String.valueOf(s).intern();
  String b = "hi";
  System.out.println(a == b);
  System.out.println(s == b);
  System.out.println(s == a);
```
Exactly, you got it. Let me draw it cleanly:

## When input is `"hello"` (not already in pool)

```
Before intern():
s ──────────────→ [ "hello" ] (heap object)

After a = String.valueOf(s).intern():
s ──────────────→ [ "hello" ] (same object, now also the pool entry)
a ──────────────↗
pool["hello"] ──↗
```

`intern()` didn't create a new object — it **pinned `s` itself** as the canonical pool entry. Both `s` and `a` are the same reference. So `s == a` → `true`.

---

## When input is `"hi"` (already in pool from `String b = "hi"`)

```
Pool already has:
pool["hi"] ──→ [ "hi" ] (this object was created at class-load)
b ───────────↗

Scanner creates:
s ──→ [ "hi" ] (different heap object, same content)

After a = String.valueOf(s).intern():
a ───→ pool["hi"] ──→ original [ "hi" ] object
s ───→ still the scanner-created object (untouched)
```

`intern()` found `"hi"` already in pool → returned the **old object**, left `s` alone. So `s != a` technically — but JVM gave `s` the pool reference directly (the quirk we saw earlier).

---

## One line summary

> `intern()` says — _"if this string is already pooled, give me that one. If not, make THIS object the pooled one."_

That's why `s == a` is always `true` for fresh strings — same object promoted.


`String = s "Hello" is same as String s = new String("Hello").intern() object created and promoted`



Here's the full String topic map with your status:

---

## ✅ Done (you own these)

- String Pool & interning
- ` ==` vs `.equals()`
- Literals vs `new String()`
- Compile-time vs runtime concat
- `final` as compile-time constant
- `intern()` deep behavior
- `String.valueOf()`
- `substring()`, `concat()`, `StringBuilder` → heap objects
- `null` → NPE behavior

---

## 🔶 Gaps to fill

### Tier 1 — Core (do these first)

- `StringBuilder` vs `StringBuffer` — when to use which, why
- `String` immutability — why it's immutable, what that means in memory
- `charAt()`, `indexOf()`, `lastIndexOf()`, `contains()`
- `replace()`, `replaceAll()` — second one uses regex
- `split()` — regex-based, edge cases (trailing empty strings)
- `trim()` vs `strip()` — `strip()` is Unicode-aware, added JDK 11
- `toUpperCase()` / `toLowerCase()` — locale-sensitive traps
- `startsWith()` / `endsWith()`
- `isEmpty()` vs `isBlank()` — `isBlank()` added JDK 11

### Tier 2 — Intermediate

- `String.format()` — format specifiers `%s %d %f %n`
- `String.join()` and `StringJoiner`
- `chars()` — returns `IntStream`, bridge to streams
- `matches()` — full regex match vs `contains()`
- `toCharArray()` — string ↔ char array conversion
- `String.format()` vs `formatted()` — JDK 15+
- `repeat()` — JDK 11+
- `compareTO()` / `compareToIgnoreCase()` — lexicographic ordering

### Tier 3 — Internals/Advanced

- Compact Strings (JDK 9+) — Latin-1 vs UTF-16 internal encoding
- Why `intern()` overuse is a performance problem
- String deduplication (G1GC `-XX:+UseStringDeduplication`)
- `String` in switch statements — how it works under the hood (uses `hashCode()` + `equals()`)
- Text blocks (`"""`) — JDK 15+, indentation trimming rules

### Tier 4 — Interview/Edge cases

- Why `String` is `final` (can't be subclassed)
- `hashCode()` contract for strings — same value = same hash always
- Passing `String` to methods — why it feels like pass-by-value
- String comparison in `switch` — gotchas with `null`
- `+` operator with `null` — `"hi" + null` = `"hinull"`

---

## Priority order for you right now

Since you're targeting placements and have CodeDash to finish:

```
Tier 1 → Tier 2 → interview edge cases → internals last
```

Tier 1 is one focused session. You already have the mental model — it's just API surface now.


[String](../Java%20Backend/Chatgpt%20learning/String.md)










---
topic: string
date-time: INVALID_DATETIME
time-taken(min): INVALID_TIME
---

# String Fundamentals (Tier 1 + Tier 2)

---

## CONCEPT: StringBuilder vs StringBuffer

### WHY
- String is immutable → repeated modification = new objects → slow
- Builders solve performance issue

### DIFFERENCE

| Class | Thread-safe | Performance |
|------|------------|------------|
| StringBuilder | ❌ | Fast |
| StringBuffer | ✔ (synchronized) | Slower |

### USE
- StringBuilder → default choice
- StringBuffer → only when multiple threads modify same string

### EDGE CASE
- Using String in loops:
```java
String s = "";
for (int i = 0; i < 1000; i++) {
    s += i; // creates 1000 objects ❌
}
```

---

## CONCEPT: String Immutability

### WHY
- Enables String Pool
- Thread-safe
- Hashcode caching (used in HashMap keys)

### MEMORY
```java
String s = "hi";
s.concat("there");
```

- `"hi"` unchanged
- `"hithere"` → new object

### EDGE CASE
```java
String s = "hi";
s.concat("there");
System.out.println(s); // hi
```

---

## CONCEPT: Search Methods

### METHODS
```java
charAt(i)          // char at index
indexOf("a")       // first occurrence
lastIndexOf("a")   // last occurrence
contains("a")      // boolean (uses indexOf)
```

### EDGE CASE
```java
"abc".indexOf("z") → -1
```

---

## CONCEPT: replace vs replaceAll

### DIFFERENCE
```java
replace("a","b")      // literal
replaceAll("a","b")   // REGEX
```

### EDGE CASE
```java
"1.2".replaceAll(".", "-") → "---"
```

---

## CONCEPT: split()

### RULE
- Uses REGEX

```java
"a,b,c".split(",")
```

### EDGE CASES

#### 1. Special char
```java
"a.b".split(".") → [] ❌
"a.b".split("\\.") → ["a","b"] ✔
```

#### 2. Trailing empty removal
```java
"a,b,".split(",") → ["a","b"]
"a,b,".split(",", -1) → ["a","b",""]
```

---

## CONCEPT: trim vs strip

### DIFFERENCE
```java
trim()  // ASCII only
strip() // Unicode aware
```

### EDGE CASE
- Unicode spaces not removed by trim

---

## CONCEPT: Case Conversion

```java
toUpperCase()
toLowerCase()
```

### EDGE CASE (Locale)
```java
"i".toUpperCase(Locale.TURKISH) → "İ"
```

---

## CONCEPT: startsWith / endsWith

```java
"hello".startsWith("he") → true
"hello".endsWith("lo") → true
```

---

## CONCEPT: isEmpty vs isBlank

```java
isEmpty() → length == 0
isBlank() → only whitespace
```

### EDGE CASE
```java
"   ".isEmpty() → false
"   ".isBlank() → true
```

---

# ---------------- TIER 2 ----------------

---

## CONCEPT: String.format

```java
String.format("Name: %s Age: %d", "Faizal", 20);
```

### SPECIFIERS
- `%s` → string
- `%d` → int
- `%f` → float
- `%n` → newline

---

## CONCEPT: formatted (JDK 15+)

```java
"Hello %s".formatted("Faizal");
```

### DIFFERENCE
- Same as format
- More readable

---

## CONCEPT: String.join / StringJoiner

```java
String.join(",", "a","b","c") → "a,b,c"
```

### USE
- clean joining without loops

---

## CONCEPT: chars()

```java
"abc".chars()
```

- returns `IntStream`
- gives ASCII values

### EDGE CASE
```java
"abc".chars().count() → 3
```

---

## CONCEPT: matches()

```java
"abc".matches("a.*") → true
```

### RULE
- FULL string match (not partial)

### EDGE CASE
```java
"abc".matches("a") → false
```

---

## CONCEPT: toCharArray()

```java
char[] arr = "abc".toCharArray();
```

### USE
- easier char manipulation

---

## CONCEPT: repeat()

```java
"a".repeat(3) → "aaa"
```

### EDGE CASE
```java
"a".repeat(0) → ""
```

---

## CONCEPT: compareTo()

```java
"a".compareTo("b") → negative
```

### RULE
- lexicographic (Unicode difference)

### EDGE CASE
```java
"A".compareTo("a") → -32
```

---

## CONCEPT: compareToIgnoreCase()

```java
"A".compareToIgnoreCase("a") → 0
```

---

# FINAL SUMMARY

- Strings → immutable + pooled
- Regex affects: split, replaceAll, matches
- Streams integrate via `chars()`
- Always distinguish:
  - compile-time vs runtime
  - literal vs regex
  - value vs reference


