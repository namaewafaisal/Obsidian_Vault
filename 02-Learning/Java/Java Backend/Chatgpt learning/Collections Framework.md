---
tags:
  - java
  - collections
  - cheatsheet
  - core-java
date-time: 2026-02-01T11:26:00
status: revision
---
# Collections Framework — Core Java Cheatsheet

Fast recall of **how to create + how to use** core collections.

---

## 1️⃣ Collection Hierarchy (Recall)

- `Collection`
  - `List`
  - `Set`
- `Map` (separate hierarchy)

---

## [[List]]

### Properties
- Ordered
- Index-based
- Duplicates allowed

---

### [[ArrayList]]

### Creation syntax
```java
List<Integer> list = new ArrayList<>();
ArrayList<Integer> list2 = new ArrayList<>();
```

- Dynamic array
- Fast random access

#### Common methods

| Method | Params | Returns | Example |
|------|------|--------|--------|
| `add()` | E | boolean | `list.add(10)` |
| `add()` | int, E | void | `list.add(0,10)` |
| `get()` | int | E | `list.get(0)` |
| `set()` | int, E | E | `list.set(0,20)` |
| `remove()` | int | E | `list.remove(0)` |
| `remove()` | Object | boolean | `list.remove(Integer.valueOf(10))` |
| `size()` | — | int | `list.size()` |

---

### [[LinkedList]]

### Creation syntax
```java
List<Integer> list = new LinkedList<>();
LinkedList<Integer> list2 = new LinkedList<>();
```

- Doubly linked list
- Fast insert/delete

Extra methods:
- `addFirst()`
- `addLast()`
- `removeFirst()`
- `removeLast()`

---

## [[../../../Leetcode/Problems/3046. Split the Array]]

### Properties
- No duplicates
- No index
- At most one `null` (HashSet)

---

### [[HashSet]]

### Creation syntax
```java
Set<Integer> set = new HashSet<>();
HashSet<Integer> set2 = new HashSet<>();
```

- Unordered
- Backed by HashMap

#### Common methods

| Method | Params | Returns | Example |
|------|------|--------|--------|
| `add()` | E | boolean | `set.add(10)` |
| `remove()` | Object | boolean | `set.remove(10)` |
| `contains()` | Object | boolean | `set.contains(10)` |
| `size()` | — | int | `set.size()` |

---

## [[Map]]

### Properties
- Key–value pairs
- Keys unique
- Values can duplicate

---

### [[HashMap]]

### Creation syntax
```java
Map<String,Integer> map = new HashMap<>();
HashMap<String,Integer> map2 = new HashMap<>();
```

- No order guarantee
- One `null` key allowed

#### Common methods

| Method | Params | Returns | Example |
|------|------|--------|--------|
| `put()` | K, V | V | `map.put("a",1)` |
| `get()` | Object | V | `map.get("a")` |
| `remove()` | Object | V | `map.remove("a")` |
| `containsKey()` | Object | boolean | `map.containsKey("a")` |
| `keySet()` | — | Set<K> | `map.keySet()` |
| `values()` | — | Collection<V> | `map.values()` |

---

## [[Iterator]]

### Creation syntax
```java
Iterator<Integer> it = list.iterator();
```

### Usage
```java
while (it.hasNext()) {
    it.next();
}
```

- Safe removal using `it.remove()`

---

## [[Collections Utility Class]]

### Usage syntax
```java
Collections.sort(list);
Collections.reverse(list);
Collections.max(list);
```

---

## 🔒 Creation Rules (LOCK THIS)

- Program to **interface**, not implementation
```java
List<Integer> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String,Integer> map = new HashMap<>();
```

- Use concrete class name **only if required**

---

## 🔗 Related Notes
- [[Object Class]]
- [[equals() and hashCode()]]
- [[Iterator]]
- [[Memory Model]]

---
