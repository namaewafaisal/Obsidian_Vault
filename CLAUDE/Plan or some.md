## **TIER 1: Language Fundamentals** (prerequisite for everything)
These are non-negotiable. Pick **one** language (Java for you, given your Spring Boot background).

- [ ] **Variables, Data Types, Type Casting**
  - Primitives vs objects, boxing/unboxing, overflow behavior
  - String immutability (critical in Java)
  
- [ ] **Control Flow** (if/else, loops, break/continue)
  - Loop invariants, when to use which loop type
  
- [ ] **Functions/Methods**
  - Parameters (pass-by-value, pass-by-reference in Java)
  - Return types, void, overloading
  - Recursion (base case, call stack)
  
- [ ] **Collections & Arrays**
  - Arrays (fixed size, index access, memory layout)
  - Lists, Sets, Maps (when to use each)
  - Iteration (for, enhanced for, iterators)

---

## **TIER 2: Object-Oriented Programming** (the big one)
You said you haven't covered everything—this is where we're deliberate.

### **Core OOP Principles**
- [ ] **Encapsulation**
  - Public/private/protected visibility
  - Getters/setters (when and why)
  - Data hiding: why it matters (easier to refactor, prevent invalid states)
  
- [ ] **Inheritance**
  - Parent-child relationships, method overriding
  - `super` keyword, constructor chaining
  - When inheritance is the right tool (is-a relationship)
  - **When NOT to use it** (composition is often better—this trip people up)
  
- [ ] **Polymorphism**
  - Method overloading vs overriding
  - Runtime polymorphism (virtual method dispatch)
  - Upcasting and downcasting (type safety)
  
- [ ] **Abstraction**
  - Abstract classes vs interfaces
  - When to use each (interfaces for contracts, abstract for shared implementation)
  - Interface segregation (small, focused interfaces)

### **Advanced OOP Concepts**
- [ ] **Static vs Instance Members**
  - When to use static (utilities, singletons—and why singletons are risky)
  - Class vs instance scope
  
- [ ] **Design Patterns** (these are crystallized OOP wisdom)
  - **Creational**: Singleton, Factory, Builder
  - **Structural**: Adapter, Decorator, Proxy
  - **Behavioral**: Strategy, Observer, Command
  - Know at least 5 deeply; recognize them in code
  
- [ ] **SOLID Principles**
  - **S**ingle Responsibility: one reason to change
  - **O**pen/Closed: open for extension, closed for modification
  - **L**iskov Substitution: subtypes must be substitutable
  - **I**nterface Segregation: clients shouldn't depend on interfaces they don't use
  - **D**ependency Inversion: depend on abstractions, not concretions
  - This is not theory—it predicts where code rots

---

## **TIER 3: Database Management Systems**
Your Securin project touched this; now go deep.

### **Core Concepts**
- [ ] **Relational Model**
  - Tables, rows, columns
  - Keys: primary, foreign, composite, unique
  - Constraints: NOT NULL, UNIQUE, CHECK, DEFAULT
  
- [ ] **SQL (DDL, DML, DQL)**
  - **DDL**: CREATE, ALTER, DROP (schema design)
  - **DML**: INSERT, UPDATE, DELETE (data manipulation)
  - **DQL**: SELECT (querying)—most important
  
- [ ] **Querying (SELECT is 80% of SQL)**
  - WHERE, AND/OR, NOT
  - ORDER BY, LIMIT/OFFSET
  - DISTINCT
  - NULL handling (IS NULL, IS NOT NULL)
  - Aliasing (AS)
  
- [ ] **Joins**
  - INNER, LEFT, RIGHT, FULL OUTER
  - Self-joins, cross joins
  - When each one is correct (this matters for query correctness)
  - Join conditions vs WHERE conditions
  
- [ ] **Aggregation & Grouping**
  - COUNT, SUM, AVG, MIN, MAX
  - GROUP BY, HAVING
  - Grouping without aggregation (common mistake)
  
- [ ] **Subqueries & CTEs**
  - Scalar subqueries, correlated subqueries
  - IN, EXISTS, ANY, ALL
  - Common Table Expressions (WITH clause)—you have a roadmap for this
  
- [ ] **Set Operations**
  - UNION, UNION ALL, INTERSECT, EXCEPT
  - When to use instead of JOINs

### **Advanced DBMS**
- [ ] **Normalization**
  - 1NF, 2NF, 3NF (and when to denormalize—it happens)
  - Why normalization prevents bugs (update anomalies, consistency)
  
- [ ] **Indexing**
  - How indexes work (B-tree mental model)
  - When to index (WHERE, JOIN, ORDER BY columns)
  - Index trade-offs: faster reads, slower writes, storage
  
- [ ] **Transactions & ACID**
  - Atomicity, Consistency, Isolation, Durability
  - Why they matter (money transfers, bookings)
  - Isolation levels (and race conditions if you get it wrong)
  
- [ ] **Query Optimization**
  - EXPLAIN/ANALYZE (read query plans)
  - Slow queries: N+1, missing indexes, poor joins
  
- [ ] **Schema Design**
  - Entity-relationship modeling (ERDs)
  - Choosing between one-to-many, many-to-many, inheritance patterns
  - Real-world decisions: denormalization for read performance

---

## **TIER 4: Memory & Execution Model**
You need to predict how code behaves.

- [ ] **Memory Layout**
  - Stack vs heap (where objects live)
  - Stack frames, local variables, function calls
  - Heap fragmentation, garbage collection (Java handles this, but know it exists)
  
- [ ] **References & Garbage Collection**
  - Reference semantics (objects are passed by reference)
  - When objects become eligible for GC
  - Memory leaks (circular references in some languages, but Java is forgiving)
  
- [ ] **Scope & Lifetime**
  - Variable scope rules
  - Shadowing, closure (if working with lambdas/functional code)

---

## **TIER 5: Concurrency Basics**
You'll need this for any real system.

- [ ] **Threading Fundamentals**
  - What is a thread, context switching
  - Creating threads (Thread class, Runnable in Java)
  
- [ ] **Synchronization**
  - Race conditions, critical sections
  - Locks, `synchronized` blocks
  - Deadlocks (why and how to avoid)
  
- [ ] **Concurrency Patterns**
  - Producer-consumer
  - Thread-safe collections (ConcurrentHashMap vs HashMap)

---

## **TIER 6: File I/O & System Interaction**
Practical stuff you'll use constantly.

- [ ] **File Operations**
  - Reading/writing files
  - Character vs byte streams (encoding matters)
  - Buffering
  
- [ ] **Exception Handling**
  - Checked vs unchecked exceptions
  - Try-catch-finally, try-with-resources
  - Custom exceptions (when to write them)

---

## **The Learning Strategy**

For each topic, follow this sequence:
1. **Understand the "why"** (not just what)
2. **Write simple code** that demonstrates it (no frameworks yet)
3. **Write it wrong first**, predict the failure, see it happen
4. **Write notes** (your Obsidian vault) with:
   - Core definition
   - Real-world example
   - When it breaks (edge cases)
   - Code snippet

Example for "Encapsulation":
```
CONCEPT: Encapsulation
WHY: Prevents invalid object states, makes refactoring safe
EXAMPLE: BankAccount with balance (private) + withdraw() (validates)
EDGE CASE: What if someone reflects into private fields? (security boundary)
CODE:
class BankAccount {
    private double balance = 0;
    
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException();
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount > balance) throw new InsufficientFundsException();
        balance -= amount;
    }
}
// WHY THIS WORKS: balance cannot become negative or invalid
```

---

## **Your Immediate Next Steps**

1. **Pick one topic from TIER 1 or 2** (sounds like you want to deepen OOP)
2. **Write one focused note** (definition + why + code + gotchas)
3. **Write 3–5 programs** that demonstrate it (wrong ways included)
4. **Move to the next**—don't jump ahead

You're right that this is the foundation. Every framework you use later (Spring, Hibernate, React) is just applying these principles in a specific context.

Want me to create a deeper dive on any specific topic? Or start building a structured learning plan in your Obsidian vault?