# Operating Systems — Interview Answers & Approach

## How to Answer
Same formula: definition first, mechanism/why second, example only if asked to elaborate.

## Process vs Thread
A **process** is an independent running instance of a program with its own memory space — processes don't share memory directly. A **thread** is a smaller unit of execution within a process — multiple threads in the same process share that process's memory.

Why it matters: spawning a process is heavy (separate memory and everything else); spawning a thread is light (it shares what's already there) — this is why multithreading exists, to do multiple things at once without process-level overhead. Example: a Spring Boot backend handling multiple HTTP requests is typically one process with many threads.

## Context Switching
When the CPU has multiple processes/threads but limited cores, it switches between them rapidly — saving the current state, loading the next. The switch itself costs time (saving/restoring registers, memory maps); too much switching ("thrashing") hurts performance even though it's what makes multitasking possible at all.

## Deadlock
Two or more processes stuck waiting on each other forever — each holds a resource the other needs, neither releases it. Example: Process A holds Resource 1, wants Resource 2. Process B holds Resource 2, wants Resource 1. Neither moves.

**Four conditions, all must hold simultaneously:**
- **Mutual exclusion** — a resource can only be held by one process at a time.
- **Hold and wait** — a process holds one resource while waiting for another.
- **No preemption** — a resource can't be forcibly taken; must be released voluntarily.
- **Circular wait** — a cycle of processes each waiting on the next.

**Prevention:** break any one condition. Common real approaches: enforce a fixed resource-acquisition order (eliminates circular wait), or use timeouts (release and retry if waiting too long).

## Paging vs Segmentation
Both let a process avoid needing to fit entirely in contiguous physical RAM, and let multiple processes share RAM safely.

- **Paging** — fixed-size blocks (pages/frames). Pages don't need to be physically contiguous; OS keeps a page table mapping pages to frames. Simpler management, but can waste space (internal fragmentation).
- **Segmentation** — variable-size blocks based on logical program units (code, data, stack segments). Maps naturally to program structure, but messier management, can cause external fragmentation.

Modern systems typically combine both — segmentation for logical organization, paging underneath for physical allocation.

## Scheduling — Round Robin
Each process gets a fixed small time slice ("quantum") — runs for that long, then moves to the next process regardless of completion. Unfinished processes go to the back of the queue. Fair (equal turns for everyone); standard for time-sharing systems where responsiveness matters more than total throughput.
