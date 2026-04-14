
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