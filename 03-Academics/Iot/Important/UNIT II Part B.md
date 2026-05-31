# UNIT II – Complete Question Bank

---

# Q1: Interfacing of Matrix Keyboard and LCD with 8051

---

# A. Matrix Keyboard Interfacing

## Introduction

A Matrix Keyboard (Keypad) consists of rows and columns.

Commonly used:

```text
4 × 4 Matrix Keyboard
```

Contains:

```text
16 Keys
```

---

## Why Matrix Arrangement?

Without matrix:

```text
16 keys → 16 pins required
```

With matrix:

```text
4 rows + 4 columns = 8 pins
```

Saves I/O pins.

---

## Block Diagram

```text
          8051
        +------+
P1.0 ---|R1    |
P1.1 ---|R2    |
P1.2 ---|R3    |
P1.3 ---|R4    |
P1.4 ---|C1    |
P1.5 ---|C2    |
P1.6 ---|C3    |
P1.7 ---|C4    |
        +------+

        4×4 KEYPAD
```

---

## Working Principle

### Row-Column Scanning

1. Rows are outputs.
2. Columns are inputs.
3. One row is made LOW.
4. Columns are checked.
5. If a key is pressed:
   * Corresponding column becomes LOW.
6. Position of key identified.

---

## Example

If:

```text
Row2 = LOW
Column3 = LOW
```

Then:

```text
Key at (R2,C3) is pressed.
```

---

## Applications

* Password systems
* Calculators
* Embedded control panels

---

# B. LCD Interfacing with 8051

## Introduction

The most common LCD used is:

```text
16 × 2 LCD
```

Meaning:

```text
16 Characters per line
2 Lines
```

---

## LCD Pin Diagram

| Pin   | Function         |
| ----- | ---------------- |
| VSS   | Ground           |
| VCC   | Power            |
| VEE   | Contrast Control |
| RS    | Register Select  |
| RW    | Read/Write       |
| EN    | Enable           |
| D0-D7 | Data Lines       |

---

## Interfacing Diagram

```text
              8051

P2.0-P2.7  --------> D0-D7

P3.0       --------> RS
P3.1       --------> RW
P3.2       --------> EN

             LCD
```

---

## Important Control Pins

### RS (Register Select)

```text
RS = 0 → Command Register
RS = 1 → Data Register
```

### RW (Read/Write)

```text
RW = 0 → Write
RW = 1 → Read
```

### EN (Enable)

Used to latch data into LCD.

---

## LCD Initialization Steps

1. Power ON LCD.
2. Select display mode.
3. Clear display.
4. Set cursor position.
5. Send characters.

---

## Example Commands

| Command | Function      |
| ------- | ------------- |
| 38H     | 8-bit mode    |
| 01H     | Clear display |
| 0EH     | Display ON    |
| 80H     | First line    |

---

## Applications

* Displaying sensor data
* Embedded user interfaces
* Measurement systems

---

## Keywords

Matrix Keyboard, Row Scanning, Column Scanning, LCD, RS, RW, EN, Character Display.

---

# Q2: Verify Schedulability Using Rate Monotonic Scheduling (RMS)

## Given

| Task | Execution Time (C) | Period (T) |
| ---- | ------------------ | ---------- |
| T1   | 3                  | 20         |
| T2   | 2                  | 5          |
| T3   | 2                  | 10         |

---

## Step 1: Priority Assignment

In RMS:

```text
Smaller Period → Higher Priority
```

| Task | Period | Priority |
| ---- | ------ | -------- |
| T2   | 5      | Highest  |
| T3   | 10     | Medium   |
| T1   | 20     | Lowest   |

---

## Step 2: Calculate CPU Utilization

Formula:

```text
U = Σ (Ci / Ti)
```

Substituting values:

```text
U = (3/20) + (2/5) + (2/10)
U = 0.15 + 0.40 + 0.20
U = 0.75
U = 75%
```

---

## Step 3: RMS Utilization Bound

For n = 3 tasks:

```text
U_max = n(2^(1/n) − 1)
U_max = 3(2^(1/3) − 1)
U_max = 0.779
U_max = 77.9%
```

---

## Step 4: Verification

```text
75% < 77.9%
```

Hence:

```text
Task Set is Schedulable under RMS.
```

---

## Step 5: Hyperperiod

```text
LCM(20, 5, 10) = 20
```

Schedule must be verified from 0 → 20.

---

## RMS Schedule

```text
Time

0---2   T2
2---4   T3
4---5   T1

5---7   T2
7---9   T1

10--12  T2
12--14  T3

15--17  T2

17--20  Idle
```

All deadlines are met.

---

## Conclusion

* CPU Utilization = 75%
* RMS Bound = 77.9%
* All deadlines satisfied
* Task set is schedulable

---

## Keywords

Rate Monotonic Scheduling, Fixed Priority Scheduling, Hyperperiod, CPU Utilization, Schedulability Test, Deadline Satisfaction.

---

# Q3: Compare Rate Monotonic Scheduling (RMS) and Earliest Deadline First (EDF)

## Introduction

Scheduling determines the order in which tasks receive CPU time in an RTOS.

Two important real-time scheduling algorithms:

1. Rate Monotonic Scheduling (RMS)
2. Earliest Deadline First (EDF)

---

# Rate Monotonic Scheduling (RMS)

## Definition

A fixed-priority scheduling algorithm.

Priority assigned according to:

```text
Smaller Period → Higher Priority
```

## Example

| Task | Period | Priority |
| ---- | ------ | -------- |
| T1   | 5      | Highest  |
| T2   | 10     | Medium   |
| T3   | 20     | Lowest   |

## Characteristics

* Fixed Priority
* Preemptive
* Predictable
* Easy implementation

## CPU Utilization Bound

```text
U = n(2^(1/n) − 1)
```

For large n:

```text
U ≈ 69%
```

Guaranteed schedulable only up to this bound.

## Advantages

* Simple algorithm
* Predictable behavior
* Low overhead

## Disadvantages

* CPU utilization not optimal
* May fail even when a valid schedule exists

---

# Earliest Deadline First (EDF)

## Definition

A dynamic-priority scheduling algorithm.

Priority assigned according to:

```text
Earliest Deadline → Highest Priority
```

## Example

| Task | Deadline | Priority |
| ---- | -------- | -------- |
| T2   | 5        | Highest  |
| T1   | 8        | Medium   |
| T3   | 15       | Lowest   |

## Characteristics

* Dynamic Priority
* Preemptive
* Optimal Scheduling
* Higher CPU Utilization

## CPU Utilization Bound

EDF can schedule tasks if:

```text
U ≤ 100%
```

Maximum utilization = 100%.

## Advantages

* Optimal scheduling
* Maximum CPU utilization
* Better deadline satisfaction

## Disadvantages

* Complex implementation
* Higher overhead

---

# Comparison Table

| Feature           | RMS        | EDF            |
| ----------------- | ---------- | -------------- |
| Priority Type     | Fixed      | Dynamic        |
| Based On          | Period     | Deadline       |
| Complexity        | Low        | High           |
| Overhead          | Less       | More           |
| Predictability    | High       | Moderate       |
| Utilization Limit | 69–78%     | 100%           |
| Schedulability    | Lower      | Better         |
| Implementation    | Easier     | More Difficult |

---

# CPU Utilization Analysis Example

| Task | C | T  |
| ---- | - | -- |
| T1   | 2 | 5  |
| T2   | 3 | 10 |
| T3   | 2 | 20 |

```text
U = (2/5) + (3/10) + (2/20)
U = 0.4 + 0.3 + 0.1
U = 0.8 = 80%
```

Since 80% > RMS bound → RMS may fail.

Since 80% < 100% → EDF can schedule successfully.

---

## Conclusion

```text
RMS → Simple, Fixed Priority, lower utilization

EDF → Optimal, Dynamic Priority, maximum CPU utilization
```

---

## Keywords

RTOS, Scheduling, Fixed Priority, Dynamic Priority, Deadline, Period, Preemptive Scheduling, Utilization Bound.

---

# Q4: Process Scheduling Algorithms – Explain Any Two

## Introduction

A scheduling algorithm determines:

* Which process gets CPU time
* Execution order of processes
* CPU utilization
* Waiting time
* Turnaround time

Scheduling is performed by the **Scheduler** inside the RTOS/OS.

---

## Common Scheduling Algorithms

1. First Come First Serve (FCFS)
2. Shortest Job First (SJF)
3. Priority Scheduling
4. Round Robin (RR)
5. Rate Monotonic Scheduling (RMS)
6. Earliest Deadline First (EDF)

---

# 1. Round Robin (RR)

## Definition

A preemptive scheduling algorithm where each process receives CPU for a fixed time called:

```text
Time Quantum
```

After the quantum expires, the running process moves to the back of the ready queue.

---

## Working

| Process | Burst Time |
| ------- | ---------- |
| P1      | 10         |
| P2      | 5          |
| P3      | 8          |

Quantum: Q = 4

## Gantt Chart

```text
0   4   8   12  16  19  23
|P1|P2|P3|P1|P3|P1|
```

## Advantages

* Fair CPU allocation
* Good response time
* Suitable for time-sharing systems

## Disadvantages

* High context switching overhead
* Performance depends on quantum size

---

# 2. Priority Scheduling

## Definition

CPU allocated according to priority.

```text
Higher Priority → Executed First
```

## Example

| Process | Priority | Execution Order |
| ------- | -------- | --------------- |
| P1      | 3        | 3rd             |
| P2      | 1        | 1st             |
| P3      | 2        | 2nd             |

## Types

### Preemptive

Higher-priority process can interrupt a running process immediately.

### Non-Preemptive

Running process continues until completion even if higher-priority process arrives.

## Advantages

* Important tasks finish quickly
* Suitable for RTOS

## Disadvantages

**Starvation** — low-priority process may wait indefinitely.

**Solution — Aging:** priority gradually increases with waiting time.

---

# Comparison: Round Robin vs Priority Scheduling

| Feature    | Round Robin  | Priority Scheduling         |
| ---------- | ------------ | --------------------------- |
| Basis      | Time Quantum | Priority                    |
| Type       | Preemptive   | Preemptive / Non-preemptive |
| Fairness   | High         | Lower                       |
| Starvation | No           | Possible                    |
| RTOS Usage | Moderate     | High                        |

---

## Keywords

Scheduler, Ready Queue, Context Switch, Time Quantum, Priority, Preemptive Scheduling, Starvation, Aging, CPU Utilization.

---

# Q5: Various States of a Task in RTOS

## Introduction

A **Task** is the basic unit of execution in an RTOS.

Examples:

* Reading sensor data
* Updating LCD
* Sending IoT data
* Handling interrupts

The RTOS scheduler manages tasks by moving them through different states.

---

## Task State Diagram

```text
                 +---------+
                 |   New   |
                 +----+----+
                      |
                      v
                 +---------+
                 |  Ready  |
                 +----+----+
                      |
                      v
                +-----+-----+
                | Running   |
                +-----+-----+
                      |
          +-----------+-----------+
          |                       |
          v                       v
      +---+----+            +-----+------+
      |Blocked |            | Suspended  |
      +---+----+            +-----+------+
          |                       |
          +-----------+-----------+
                      |
                      v
                   Ready
                      |
                      v
                +-----+-----+
                | Terminated|
                +-----------+
```

---

## 1. New State

Task has been created but not yet ready for execution.

Operations:

* Memory allocation
* Task initialization
* Stack creation

---

## 2. Ready State

Task is prepared for execution, waiting for CPU.

* All resources available
* Present in Ready Queue

Example:

```text
Sensor Task waiting for CPU
```

---

## 3. Running State

Task currently executing on CPU.

Only one task can run at a time on a single-core processor.

---

## 4. Blocked (Waiting) State

Task waits for:

* Event
* I/O completion
* Message / Semaphore
* Timer expiration

Example:

```text
Waiting for UART data
```

---

## 5. Suspended State

Task temporarily removed from scheduling. Cannot execute until resumed explicitly.

Example:

```text
Maintenance task disabled temporarily
```

---

## 6. Terminated State

Task has completed execution. Resources released and task removed from scheduler.

---

## State Transitions

| Current State | Next State |
| ------------- | ---------- |
| New           | Ready      |
| Ready         | Running    |
| Running       | Blocked    |
| Blocked       | Ready      |
| Running       | Suspended  |
| Suspended     | Ready      |
| Running       | Terminated |

---

## Task Management Functions

| Function          | Action                              |
| ----------------- | ----------------------------------- |
| Task Creation     | Create a new task                   |
| Task Deletion     | Remove task from system             |
| Task Suspension   | Temporarily stop task               |
| Task Resumption   | Resume a suspended task             |
| Context Switching | Switch CPU from one task to another |

---

## Advantages of Multitasking

* Better CPU utilization
* Faster response
* Efficient resource sharing
* Supports real-time deadlines

---

## Applications

* Embedded Controllers
* Robotics
* Automotive Systems
* Medical Devices
* Industrial Automation
* IoT Systems

---

## Keywords

Task, Process, Scheduler, Ready Queue, Running State, Blocked State, Suspended State, Context Switching, Multitasking, Semaphore, RTOS.

---

# Q6: Priority-Based Scheduling Policies

## Introduction

Priority Scheduling is a CPU scheduling algorithm in which each process is assigned a priority and CPU is allocated to the highest-priority process first.

```text
Higher Priority → Executed First
```

Used in: RTOS, Embedded Systems, Industrial Automation, Mission Critical Systems.

---

## Types of Priority Scheduling

### 1. Non-Preemptive Priority Scheduling

Once CPU is allocated, process runs till completion even if a higher-priority process arrives.

**Example:**

| Process | Priority | Execution Order |
| ------- | -------- | --------------- |
| P1      | 3        | 3rd             |
| P2      | 1        | 1st             |
| P3      | 2        | 2nd             |

```text
Execution: P2 → P3 → P1
```

---

### 2. Preemptive Priority Scheduling

If a higher-priority process arrives, the running process is immediately interrupted.

**Example:**

```text
Time 0 : P1 starts
Time 3 : P2 arrives with higher priority
         P1 interrupted → P2 executes
```

---

## Working Principle

```text
Ready Queue
      ↓
Scheduler checks priorities
      ↓
Highest Priority Selected
      ↓
CPU Allocation
      ↓
Execution
```

---

## Advantages

* Fast response for critical tasks
* Suitable for real-time systems
* Important tasks finish first

## Disadvantages

**Starvation** — low-priority processes may never execute if high-priority jobs keep arriving.

**Solution — Aging:** priority gradually increases with waiting time.

```text
Waiting Time ↑ → Priority ↑
```

---

## Example Gantt Chart

| Process | Burst Time | Priority |
| ------- | ---------- | -------- |
| P1      | 8          | 3        |
| P2      | 4          | 1        |
| P3      | 6          | 2        |

```text
0      4      10      18
| P2  |  P3  |   P1  |
```

---

## Comparison: Preemptive vs Non-Preemptive

| Feature          | Preemptive | Non-Preemptive |
| ---------------- | ---------- | -------------- |
| CPU Interruption | Yes        | No             |
| Response Time    | Better     | Slower         |
| Complexity       | Higher     | Lower          |
| RTOS Usage       | Common     | Less Common    |

---

## Keywords

Priority Scheduling, Preemptive, Non-Preemptive, Starvation, Aging, Ready Queue, Scheduler, CPU Allocation, RTOS.

---

# Q7: Round Robin Scheduling – Numerical

## Given

| Process | Burst Time | Arrival Time |
| ------- | ---------- | ------------ |
| P0      | 80         | 0            |
| P1      | 20         | 10           |
| P2      | 10         | 10           |
| P3      | 20         | 40           |
| P4      | 50         | 45           |

Time Quantum: Q = 15

---

## Step 1: Gantt Chart

```text
0   15  30  45  55  70  85 100 115 130 145 160 175 190
|P0|P1|P2|P0|P3|P4|P1|P0|P3|P4|P0|P4|P0|
```

Completion Times:

| Process | Completion Time |
| ------- | --------------- |
| P2      | 55              |
| P1      | 85              |
| P3      | 130             |
| P4      | 190             |
| P0      | 190             |

---

## Step 2: Turnaround Time

Formula:

```text
TAT = Completion Time − Arrival Time
```

| Process | CT  | AT | TAT |
| ------- | --- | -- | --- |
| P0      | 190 | 0  | 190 |
| P1      | 85  | 10 | 75  |
| P2      | 55  | 10 | 45  |
| P3      | 130 | 40 | 90  |
| P4      | 190 | 45 | 145 |

Average TAT = (190 + 75 + 45 + 90 + 145) / 5 = **109 units**

---

## Step 3: Waiting Time

Formula:

```text
WT = TAT − Burst Time
```

| Process | TAT | BT | WT  |
| ------- | --- | -- | --- |
| P0      | 190 | 80 | 110 |
| P1      | 75  | 20 | 55  |
| P2      | 45  | 10 | 35  |
| P3      | 90  | 20 | 70  |
| P4      | 145 | 50 | 95  |

Average WT = (110 + 55 + 35 + 70 + 95) / 5 = **73 units**

---

## Step 4: Context Switches

```text
P0→P1, P1→P2, P2→P0, P0→P3, P3→P4,
P4→P1, P1→P0, P0→P3, P3→P4, P4→P0,
P0→P4, P4→P0
```

Total Context Switches = **12**

If each switch takes 1 unit → Total Context Switching Time = **12 units**

---

## Keywords

Round Robin, Time Quantum, Turnaround Time, Waiting Time, Context Switch, Gantt Chart, Preemptive Scheduling.