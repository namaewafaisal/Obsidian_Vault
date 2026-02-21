---
topic: stack_java_collections
category: linear
structure: library_abstraction
data: generic
mutability: dynamic
access: LIFO
priority: high
difficulty: basic
status: practical_usage
used_in:
  - production_code
  - interview_questions
tags:
  - dsa
  - stack
  - java
---

# Stack in Java — Practical Usage

## 0. Important Reality

Java has:

- `Stack<E>` (legacy class)
- `ArrayDeque<E>` (recommended)
- `LinkedList<E>` (can act as stack)

Preferred:

> Use ArrayDeque for stack behavior.

---

## 1. Using ArrayDeque

```java
Deque<E> stack = new ArrayDeque<>();
stack.push(x);
stack.pop();
stack.peek();
```

Why?

- Faster than Stack
- No synchronization overhead
- Better memory layout

---

## 2. Why Not Use java.util.Stack?

- Legacy class
- Extends Vector
- Synchronized (slower)
- Old design

---

## 3. LinkedList as Stack

```java
Deque<E> stack = new LinkedList<>();
```

Works, but:

- More memory overhead
- Worse cache performance

---

## 4. Industry Rule

For stack in production:

> Always prefer ArrayDeque.

---

## Linked Notes

- [[Stack]]
- [[Deque]]