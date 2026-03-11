---
topic: dynamic_array
category: linear
structure: contiguous
data: homogeneous_or_generic
mutability: dynamic
access: direct
priority: high
difficulty: basic
status: foundation
used_in:
  - arraylist_backing
  - sliding_window
  - dynamic_storage
tags:
  - dsa
  - dynamic_array
  - amortized
---

# Dynamic Array

## 0. The Real Problem This Structure Solves

Static arrays require size commitment at creation time.
Too large → wasted memory.
Too small → crash.

Dynamic Array solves this by resizing automatically behind the scenes.

One-line anchor:
> Dynamic Array gives you the feel of unlimited storage while managing memory automatically.

## 1. Dynamic Array Is Structural, Not Behavioral

Unlike Stack or Queue, Dynamic Array is defined by its internal mechanism, not access rules.

Core mechanism:
- Backed by a real static array internally.
- When full → allocate 2× array, copy all elements, discard old.
- When quarter full → allocate ½ array, copy all elements, discard old.

## 2. Core Invariants

- Elements occupy contiguous slots from index 0 to size-1.
- size ≤ capacity at all times.
- Grow fires when size == capacity.
- Shrink fires when size == capacity / 4.
- Shrink target is always capacity / 2.

## 3. Why 2× Growth (Amortized O(1))

+1 growth: every insert triggers a copy → O(n²) total for n inserts.

2× growth: copies happen at sizes 1, 2, 4, 8 ... n.
Total copies = 1 + 2 + 4 + ... + n = 2n → O(1) amortized per insert.

Each element pays for its own future copy at resize time.

## 4. Why Shrink At Quarter, Not Half (Thrashing Prevention)

Shrink at half → grow fires at full, shrink fires at half, one insert/remove at boundary triggers infinite resize loop.

Shrink at quarter → buffer zone exists between grow trigger and shrink trigger. Expensive operations stay rare.

## 5. Implementation
```java
public class DynamicArray<T> {
    private T[] list;
    private int index;

    DynamicArray() {
        list = (T[]) new Object[4];
        index = -1;
    }

    public void add(T data) {
        if (isFull()) resize(list.length * 2);
        list[++index] = data;
    }

    public void remove(int j) {
        if (isEmpty()) throw new IndexOutOfBoundsException();
        if (j > index || j < 0) throw new IndexOutOfBoundsException();
        for (int i = j; i < index; i++) list[i] = list[i + 1];
        list[index] = null;
        index--;
        if (index + 1 == list.length / 4) resize(list.length / 2);
    }

    public T get(int i) {
        if (i > index || i < 0) throw new IndexOutOfBoundsException();
        return list[i];
    }

    public void set(int i, T data) {
        if (i > index || i < 0) throw new IndexOutOfBoundsException();
        list[i] = data;
    }

    public int size() { return index + 1; }
    public boolean isEmpty() { return index == -1; }
    public boolean isFull() { return index == list.length - 1; }

    public void display() {
        for (int i = 0; i <= index; i++) System.out.print(list[i] + " ");
        System.out.println();
    }

    private void resize(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        for (int i = 0; i <= index; i++) temp[i] = list[i];
        list = temp;
    }
}
```

## 6. Edge Cases

| Scenario | Risk | Fix |
|---|---|---|
| Remove only element | Shrink condition: 0 == 4/4 = 1 → false, no shrink | Correct — stays at minimum capacity |
| Add triggers multiple resizes | Data must survive each copy | resize() copies up to index, not capacity |
| remove(0) on n elements | Shift must cover all elements | Loop runs i = 0 to index-1 |
| get/set beyond size | Backing array has empty slots past index | Validate against index not list.length |

## 7. Performance Characteristics

| Operation | Time |
|---|---|
| add (no resize) | O(1) |
| add (resize) | O(n) |
| add amortized | O(1) |
| remove | O(n) |
| get | O(1) |
| set | O(1) |
| Space | O(n) |

## 8. When To Use

- Size is unknown at creation time.
- You need O(1) random access by index.
- Appends are the dominant operation.

## 9. When Not To Use

- Frequent insert/remove at arbitrary positions (linked list is better — no shifting).
- Memory is tightly constrained (2× overhead exists between resizes).
- Size is known upfront (static array is simpler and sufficient).

## 10. Common Bugs

1. Bounds checking against capacity instead of size — allows access to empty slots.
2. Forgetting to null out after remove — causes memory leak.
3. Shrink at half instead of quarter — causes thrashing.
4. Duplicating grow/shrink logic instead of extracting resize(int).

## 11. Java Built-in
```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);       // add()
list.remove(0);     // remove(index)
list.get(0);        // get()
list.set(0, 99);    // set()
list.size();        // size()
list.isEmpty();     // isEmpty()
```

Differences from your implementation:
- Default capacity: 10
- Growth factor: 1.5× not 2×
- No auto-shrink — trimToSize() must be called manually

## 12. Linked Notes

- [[Arrays_Fundamentals]] — static array this builds on
- [[ArrayList_Java]] — production built-in equivalent
- [[Sliding_Window]] — primary algorithmic consumer
- [[Prefix_Storage_Structures]] — next in Phase 3