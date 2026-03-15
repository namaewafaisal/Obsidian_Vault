Below is a **complete, test-oriented Java cheat sheet** covering **only what you realistically need for LeetCode / coding tests**.  
No fluff, no rarely used APIs. This is optimized for **medium problems** and speed under pressure.

You should **not memorize everything** here.  
You should **recognize and recall on demand**.

---

# Java Coding Interview Cheat Sheet (LeetCode-Focused)

---

## 1. Core Packages You Will Use

```java
import java.util.*;
import java.lang.*;   // auto-imported
```

That’s it.  
You rarely need anything else.

---

## 2. Arrays

### Declaration

```java
int[] arr = new int[n];
int[][] grid = new int[r][c];
```

### Utilities (`java.util.Arrays`)

```java
Arrays.sort(arr);
Arrays.fill(arr, value);
Arrays.equals(a, b);
Arrays.toString(arr);
```

### Length

```java
arr.length
```

---

## 3. String & StringBuilder

### String (immutable)

```java
s.length()
s.charAt(i)
s.substring(start, end)
s.equals(other)
s.split(" ")
s.toCharArray()
```

### StringBuilder (mutable)

```java
StringBuilder sb = new StringBuilder();
sb.append(x);
sb.deleteCharAt(i);
sb.reverse();
sb.toString();
```

Use `StringBuilder` for loops.

---

## 4. List (ArrayList / LinkedList)

### Declaration

```java
List<Integer> list = new ArrayList<>();
List<Integer> list = new LinkedList<>();
```

### Methods

```java
list.add(x);
list.get(i);
list.set(i, x);
list.remove(i);       // index
list.remove(Integer.valueOf(x)); // value
list.size();
list.contains(x);
```

### Iterators

```java
Iterator<Integer> it = list.iterator();
ListIterator<Integer> lit = list.listIterator();
```

---

## 5. Iterator / ListIterator

### Iterator (forward only)

```java
it.hasNext()
it.next()
```

### ListIterator (bidirectional)

```java
lit.hasNext()
lit.next()
lit.hasPrevious()
lit.previous()
```

---

## 6. Set (HashSet / TreeSet)

### Declaration

```java
Set<Integer> set = new HashSet<>();
Set<Integer> set = new TreeSet<>();
```

### Methods

```java
set.add(x);
set.contains(x);
set.remove(x);
set.size();
```

Use for:

- Uniqueness
    
- Fast lookup (O(1))
    

---

## 7. Map (HashMap / TreeMap)

### Declaration

```java
Map<Integer, Integer> map = new HashMap<>();
```

### Core Methods

```java
map.put(k, v);
map.get(k);
map.getOrDefault(k, 0);
map.containsKey(k);
map.remove(k);
map.size();
```

### Iteration

```java
for (Map.Entry<K,V> e : map.entrySet()) {
    e.getKey();
    e.getValue();
}
```

Use for:

- Frequency counting
    
- Index mapping
    
- Caching
    

---

## 8. Stack (Use Deque, NOT Stack)

### Declaration

```java
Deque<Integer> stack = new ArrayDeque<>();
```

### Methods (LIFO)

```java
stack.push(x);
stack.pop();
stack.peek();
stack.isEmpty();
```

Use for:

- Monotonic stack
    
- Parentheses
    
- DFS (iterative)
    

---

## 9. Queue (FIFO)

### Declaration

```java
Queue<Integer> q = new ArrayDeque<>();
```

### Methods

```java
q.offer(x);
q.poll();
q.peek();
q.isEmpty();
```

Use for:

- BFS
    
- Level order traversal
    

---

## 10. Deque (Double Ended Queue)

```java
Deque<Integer> dq = new ArrayDeque<>();
```

```java
dq.offerFirst(x);
dq.offerLast(x);
dq.pollFirst();
dq.pollLast();
dq.peekFirst();
dq.peekLast();
```

Use for:

- Sliding window max/min
    
- Stack + Queue hybrid
    

---

## 11. PriorityQueue (Heap)

### Min Heap (default)

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

### Max Heap

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>(Collections.reverseOrder());
```

### Methods

```java
pq.offer(x);
pq.poll();
pq.peek();
pq.size();
```

Use for:

- Top K
    
- Kth largest/smallest
    
- Scheduling
    

---

## 12. Collections Utility Class

```java
Collections.sort(list);
Collections.reverse(list);
Collections.max(collection);
Collections.min(collection);
```

---

## 13. Math

```java
Math.max(a, b);
Math.min(a, b);
Math.abs(x);
Math.pow(a, b);
```

---

## 14. Character Utilities

```java
Character.isDigit(c);
Character.isLetter(c);
Character.isUpperCase(c);
Character.isLowerCase(c);
```

---

## 15. Common Patterns → Java Class Mapping

|Pattern|Java Class|
|---|---|
|Frequency count|HashMap|
|Unique elements|HashSet|
|Sliding window|HashMap / Deque|
|Next greater element|Stack (Deque)|
|BFS|Queue|
|DFS|Stack / Recursion|
|Top K|PriorityQueue|
|Two pointers|Array / String|
|Reverse traversal|ListIterator|

---

## 16. What You Can Ignore Safely

❌ Vector  
❌ Enumeration  
❌ Concurrent collections  
❌ Streams API  
❌ Optional  
❌ Comparator (unless sorting custom objects)

---

## 17. Golden Rules for Tests

1. **Logic first, API second**
    
2. If you forget a method, **rewrite using basic logic**
    
3. Use **semantic methods**:
    
    - Stack → `push/pop`
        
    - Queue → `offer/poll`
        
4. Never panic over syntax — partial logic often still passes
    

---

## 18. One-Page Memory Trick

If you remember **just this**, you’re safe:

```
List → add / get
Map → put / getOrDefault
Set → add / contains
Stack → push / pop
Queue → offer / poll
Heap → offer / poll
```

Everything else is detail.

---

If you want next:

- A **printable one-page version**
    
- A **pattern-driven cheat sheet**
    
- A **mock test using only these APIs**
    

Say which one.