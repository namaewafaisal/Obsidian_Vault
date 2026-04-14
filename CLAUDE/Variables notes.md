- `Integer` has a cache **[-128 to 127]**
- `a = 100`, `b = 100` → reused from cache → **same object**
- `x = 200`, `y = 200` → outside cache → **new objects**
- ✅ Cached Integers are **still objects**, but **reused references**
- ❌ No unboxing here
- ` ==` compares **reference**, unless one side is primitive
- `Integer == Integer` → reference compare
- `Integer == int` → unboxing happens

```java
int b = Integer.MAX_VALUE; // 2147483647 binary = 011....1 0 is for the negative
// b+1 1000...00 the flag for negative is now 1.
System.out.println(b+1); // -2147483648
```
- No automatic conversion to Long
- `Math.addExact(a, 1); // throws ArithmeticException`

### String
- `String s1 = "Hello"` -> uses a special heap area called String Pool
- Creates a new Object in string pool.
- strings are reused across entire program if they are same. 
- `String s2 = "Hello"` -> sees StringPOOL if `Hello` exists ? reuse : create new in String pool
- `String s3 = new String("Hello")` creates a new object in heap not in string pool. this is separate on its own
-

### Critical correction:

❌ “reference is sent” → WRONG wording  
✅ “copy of reference value is passed”

---

### PROOF (important)
```java
void change(int[] x) {  
    x = new int[]{99};  
}
```
Output stays `10`

👉 Because you changed the **reference copy**, not original



`b += 1;`

- Implicit cast:

`b = (byte)(b + 1);`

---

### Why this fails:

b = b + 1;

- `b + 1` → promoted to `int`
- Java won’t auto-cast back → possible data loss