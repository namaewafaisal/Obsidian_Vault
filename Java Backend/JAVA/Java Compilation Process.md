# Java Compilation Process

Java code goes through several steps before execution:

1. **Source Code** (`.java`) → compiled by `javac`
2. Produces **Bytecode** (`.class`)
3. **JVM** interprets bytecode at runtime

**Flow:**
`Java File → javac → Bytecode → JVM → Machine Code`

Each `.java` file may have multiple classes,  
but execution starts from the **main class** — the one containing the `main()` method.

**Related Notes:**
- [[JDK]]
- [[JRE]]
- [[JVM]]
- [[Main Method]]