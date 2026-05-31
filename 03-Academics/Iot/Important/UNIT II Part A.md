### 1. Define RTOS and its characteristics.

**RTOS (Real-Time Operating System):**
An operating system designed to process data and respond to events within a guaranteed time limit.

**Characteristics:**

* Deterministic behavior (predictable response time)
* Fast task switching
* Multitasking support
* Priority-based scheduling
* High reliability and stability
* Minimal interrupt latency

---

### 2. What is context switching?

**Context Switching** is the process of saving the state (context) of a running task and loading the state of another task so the CPU can switch execution between tasks.

**Context includes:**

* Program Counter (PC)
* CPU Registers
* Stack Pointer (SP)
* Processor Status Register (PSW)

---

### 3. Difference between C and Embedded C

| C                                    | Embedded C                                         |
| ------------------------------------ | -------------------------------------------------- |
| General-purpose programming language | Extension of C for embedded systems                |
| Runs on computers                    | Runs on microcontrollers and embedded devices      |
| Hardware-independent                 | Hardware-dependent                                 |
| No direct register access            | Can access hardware registers and ports            |
| Used for desktop/server applications | Used for device control and real-time applications |

---

### 4. List scheduling algorithms used in embedded systems.

* First Come First Serve (FCFS)
* Round Robin (RR)
* Priority Scheduling
* Rate Monotonic Scheduling (RMS)
* Earliest Deadline First (EDF)
* Shortest Job First (SJF)
