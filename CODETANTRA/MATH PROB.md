---
topic: combinatorics
---

# 📌 Problem: Probability of Passing Test

## 🧾 Problem Statement

There is a test with:

* **N** total questions in a question bank
* Manu practices **M** questions
* The exam randomly selects **T** questions

### Condition

* Manu can only solve practiced questions
* He passes if he solves **at least one question**

---

## 📥 Input Format

* First line: integer **T** (number of test cases)
* Each test case:

  ```
  N T M
  ```

---

## 📤 Output Format

* Output probability in form:

$$
\frac{p}{q} \mod (10^9 + 7)
$$

* Convert using:

$$
p \times q^{-1} \mod MOD
$$

---

## 🧠 Core Idea

Instead of directly finding:

$$
P(\text{pass})
$$

We compute:

$$
P(\text{pass}) = 1 - P(\text{fail})
$$

---

## ❌ What is FAIL?

Fail means:

* None of the selected T questions are from practiced M

So all T questions come from:

$$
N - M
$$

---

## 📊 Total vs Fail Cases

### Total ways:

$$
\binom{N}{T}
$$

### Fail ways:

$$
\binom{N - M}{T}
$$

---

## ✅ Final Probability

$$
P(\text{pass}) = 1 - \frac{\binom{N-M}{T}}{\binom{N}{T}}
$$

---

## 🔄 Rewriting

$$
P(\text{pass}) = \frac{\binom{N}{T} - \binom{N-M}{T}}{\binom{N}{T}}
$$

---

## 🧮 Combinatorics

### Combination formula:

$$
C(n, r) = \frac{n!}{r!(n-r)!}
$$

---

## ⚠️ Problem with Division in Modulo

We cannot do:

$$
\frac{a}{b} \mod MOD
$$

Instead:

$$
a \times b^{-1} \mod MOD
$$

---

## 🔑 Modular Inverse

For prime MOD:

$$
x^{-1} = x^{MOD-2} \mod MOD
$$

---

## ⚡ Fast Exponentiation

Used to compute:

$$
x^{MOD-2}
$$

Efficiently in:

$$
O(\log MOD)
$$

---

## 📦 Factorial Precomputation

We store:

```java
fact[i] = i!
```

---

## 🔁 Inverse Factorial

We store:

```java
invFact[i] = (i!)^-1
```

### Key Relation:

$$
(i)! = (i+1)! \div (i+1)
$$

Taking inverse:

$$
(i)!^{-1} = (i+1)!^{-1} \times (i+1)
$$

---

## 🧩 nCr in Modulo

$$
C(n,r) = fact[n] \times invFact[r] \times invFact[n-r]
$$

---

## 🧮 Final Computation

Let:

* total = $$C(n, t)$$
* fail = $$C(n-m, t)$$

Then:

$$
good = total - fail
$$

$$
ans = good \times total^{-1}
$$

---

## 💻 Code

```java
package q13061;
import java.util.*;

public class CTJ13061 {

    static final int MAX = 1005;
    static final long MOD = 1000000007;

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // factorial
        fact[0] = 1;
        for(int i = 1; i < MAX; i++) {
            fact[i] = (fact[i-1] * i) % MOD;
        }

        // inverse factorial
        invFact[MAX-1] = pow(fact[MAX-1], MOD-2);
        for(int i = MAX-2; i >= 0; i--) {
            invFact[i] = (invFact[i+1] * (i+1)) % MOD;
        }

        int testCases = sc.nextInt();

        while(testCases-- > 0) {
            int n = sc.nextInt();
            int t = sc.nextInt();
            int m = sc.nextInt();

            long total = nCr(n, t);
            long fail = nCr(n - m, t);

            long good = (total - fail + MOD) % MOD;

            long ans = (good * pow(total, MOD - 2)) % MOD;

            System.out.println(ans);
        }
    }

    static long pow(long base, long exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    static long nCr(int n, int r) {
        if (r > n || r < 0) return 0;
        return (fact[n] * invFact[r] % MOD * invFact[n - r] % MOD) % MOD;
    }
}
```

---

## 🧠 Key Learnings

* Use **complement probability**
* Convert division → modular inverse
* Precompute factorials for efficiency
* Use binary exponentiation for power
* nCr can be computed in $$O(1)$$ after preprocessing

---

## 🔍 Pattern Recognition

Whenever you see:

* “at least one” → think
  $$
  1 - P(\text{none})
  $$
* “random selection” → think
  $$
  nCr
  $$
* “mod division” → think
  $$
  inverse
  $$

---

## 🧩 Complexity

* Precompute: $$O(N)$$
* Each test case: $$O(1)$$

---

## 🧾 Final Insight

This problem combines:

* Probability
* Combinatorics
* Modular Arithmetic

👉 The trick is not coding — it's recognizing:

$$
P(\text{at least one}) = 1 - P(\text{none})
$$

---
