# Fermat's Little Theorem Computation

## Why Do We Need Fermat's Little Theorem?

Computing very large powers directly is impractical.

Example:

```text
97^94 mod 73
```

The number is enormous.

Fermat's Little Theorem simplifies such computations.

---

## Fermat's Little Theorem

If:

* `p` is a prime number
* `a` is not divisible by `p`

then:

a^{p-1}\equiv 1\pmod p

This means powers can be reduced modulo `p − 1`.

---

## Problem Statement

Find a number between `0` and `72` such that:

9^{794}\pmod{73}

---

## Step 1: Verify Conditions

We have:

```text
a = 9
p = 73
```

Check:

* `73` is prime ✅
* `gcd(9,73)=1` ✅

Therefore, Fermat's theorem applies.

---

## Step 2: Apply Fermat's Theorem

Since:

9^{72}\equiv 1\pmod{73}

Reduce the exponent modulo `72`.

Compute:

```text
794 mod 72
```

```text
794 = 72 × 11 + 2
```

Therefore:

```text
794 mod 72 = 2
```

Hence:

9^{794}\equiv 9^2\pmod{73}

---

## Step 3: Compute the Result

```text
9² = 81
```

Now reduce modulo `73`:

```text
81 mod 73 = 8
```

Therefore:

```text
9^794 mod 73 = 8
```

---

## Final Answer

```text
The number between 0 and 72 congruent to 9^794 mod 73 is:

8
```

---

## Exam Shortcut

For problems of the form:

```text
a^n mod p
```

where `p` is prime:

1. Verify `gcd(a,p)=1`
2. Reduce exponent modulo `p−1`
3. Compute the smaller power
4. Reduce the final answer modulo `p`

---

## One-Line Summary

> Fermat's Little Theorem simplifies modular exponentiation by reducing exponents modulo `p−1` when the modulus is prime.
