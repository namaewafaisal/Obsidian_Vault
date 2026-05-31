# UNIT II – PART B

---

# 1. Scheduling Policies: Compare Rate Monotonic Scheduling (RMS) and Earliest Deadline First (EDF)

## Introduction

In a **Real-Time Operating System (RTOS)**, multiple tasks compete for CPU time.

A **Scheduling Algorithm** decides:

* Which task executes next
* When a task executes
* Whether deadlines can be met

Two important scheduling algorithms are:

1. Rate Monotonic Scheduling (RMS)
2. Earliest Deadline First (EDF)

---

# A. Rate Monotonic Scheduling (RMS)

## Definition

RMS is a **fixed-priority scheduling algorithm**.

Priority is assigned according to the **task period**.

### Rule

```text
Smaller Period → Higher Priority
Larger Period → Lower Priority
```

---

## Example

| Task | Period |
| ---- | ------ |
| T1   | 10 ms  |
| T2   | 20 ms  |
| T3   | 50 ms  |

Priority:

```text
T1 > T2 > T3
```

because T1 occurs most frequently.

---

## Characteristics

* Fixed priorities
* Preemptive scheduling
* Suitable for periodic tasks
* Easy to implement
* Widely used in embedded systems

---

## Advantages

* Simple algorithm
* Predictable behavior
* Low overhead

---

## Disadvantages

* CPU utilization not optimal
* May fail even when a valid schedule exists

---

# B. Earliest Deadline First (EDF)

## Definition

EDF is a **dynamic-priority scheduling algorithm**.

Priority is assigned according to the task deadline.

### Rule

```text
Nearest Deadline → Highest Priority
```

---

## Example

| Task | Deadline |
| ---- | -------- |
| T1   | 15 ms    |
| T2   | 30 ms    |
| T3   | 10 ms    |

Priority:

```text
T3 > T1 > T2
```

because T3 has the earliest deadline.

---

## Characteristics

* Dynamic priorities
* Preemptive scheduling
* Higher CPU utilization
* Flexible scheduling

---

## Advantages

* Better processor utilization
* Can schedule more tasks
* Optimal for single processor systems

---

## Disadvantages

* More complex
* Higher scheduling overhead

---

# Comparison Between RMS and EDF

| Feature         | RMS         | EDF           |
| --------------- | ----------- | ------------- |
| Priority Type   | Fixed       | Dynamic       |
| Basis           | Task Period | Task Deadline |
| Complexity      | Simple      | Complex       |
| CPU Utilization | Lower       | Higher        |
| Overhead        | Less        | More          |
| Predictability  | High        | Moderate      |
| Performance     | Good        | Better        |

---

## Keywords

* RTOS
* Scheduling
* Fixed Priority
* Dynamic Priority
* Deadline
* Period
* Preemptive Scheduling

---

# 2. Interfacing of Matrix Keyboard and LCD with 8051

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

---

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

---

### RW (Read/Write)

```text
RW = 0 → Write
RW = 1 → Read
```

---

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

* Matrix Keyboard
* Row Scanning
* Column Scanning
* LCD
* RS
* RW
* EN
* Character Display

---

# 3. Multi-tasking & Processes: Task Management and Task States in RTOS

---

# Introduction

A **Task** is a program that performs a specific function.

Examples:

* Reading sensor
* Displaying LCD data
* Sending IoT data
* Handling keypad input

RTOS manages many tasks simultaneously.

This is called:

```text
Multitasking
```

---

# Task Management

## Definition

Task Management is the process of:

* Creating tasks
* Scheduling tasks
* Suspending tasks
* Resuming tasks
* Deleting tasks

---

## Functions of Task Management

### Task Creation

Creates a new task.

Example:

```text
Sensor Task
LCD Task
Communication Task
```

---

### Task Scheduling

Decides which task gets CPU.

---

### Task Synchronization

Coordinates tasks sharing resources.

---

### Task Communication

Allows tasks to exchange data.

Methods:

* Message Queue
* Semaphore
* Event Flags

---

# Task States in RTOS

A task moves through several states during execution.

---

## Task State Diagram

```text
          +-------+
          | Ready |
          +---+---+
              |
              |
              v
         +----+----+
         | Running |
         +----+----+
              |
     +--------+--------+
     |                 |
     v                 v
+----+----+       +----+----+
|Blocked |       | Suspended|
+----+----+       +----+----+
     |                 |
     +--------+--------+
              |
              v
           Ready
```

---

# 1. Ready State

* Task is prepared for execution.
* Waiting for CPU allocation.

Example:

```text
Task loaded into memory.
```

---

# 2. Running State

* CPU is executing the task.

Only one task can run on a single-core processor at a time.

---

# 3. Blocked (Waiting) State

Task waits for:

* Event
* Data
* I/O completion
* Timer expiration

Example:

```text
Waiting for sensor data.
```

---

# 4. Suspended State

Task is temporarily stopped.

Cannot execute until resumed.

---

# 5. Terminated State

Task execution completed.

Resources are released.

---

# State Transitions

| From      | To         |
| --------- | ---------- |
| Ready     | Running    |
| Running   | Blocked    |
| Blocked   | Ready      |
| Running   | Suspended  |
| Suspended | Ready      |
| Running   | Terminated |

---

# Advantages of Multitasking

* Better CPU utilization
* Faster response
* Efficient resource sharing
* Supports real-time systems

---

## Keywords

* RTOS
* Task
* Process
* Multitasking
* Scheduler
* Ready State
* Running State
* Blocked State
* Suspended State
* Semaphore
* Message Queue

---

# Exam Tip

For a **13–16 mark answer**, draw:

* **RMS vs EDF comparison table**
* **Matrix Keyboard interface diagram**
* **LCD interface diagram**
* **RTOS task state diagram**

These diagrams alone usually fetch several marks even before the explanation.


You're right. For these papers, **Unit = Question Number**, not topic.

From the image:

## UNIT II Unique Questions

### 12(a) – RMS Schedulability Analysis

### 12(b) – RMS vs EDF Comparison

### 12(a) – Process Scheduling Algorithms (Explain any two)

### 12(b) – Task States in RTOS

### 12(b) – Round Robin Numerical

### 12(b) – Priority-Based Scheduling Policies

Let's do the first two.

---

# UNIT II – Q12(a)

# Verify the Schedulability using Rate Monotonic Scheduling (RMS)

Given:

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

Therefore:

| Task | Period | Priority |
| ---- | ------ | -------- |
| T2   | 5      | Highest  |
| T3   | 10     | Medium   |
| T1   | 20     | Lowest   |

---

## Step 2: Calculate CPU Utilization

Formula:

U=\sum \frac{C_i}{T_i}

Substituting values:

[
U=\frac{3}{20}+\frac{2}{5}+\frac{2}{10}
]

[
U=0.15+0.40+0.20
]

[
U=0.75
]

[
U=75%
]

---

## Step 3: RMS Utilization Bound

For:

```text
n = 3 Tasks
```

Formula:

[
U_{max}=n(2^{1/n}-1)
]

[
U_{max}=3(2^{1/3}-1)
]

[
U_{max}=0.779
]

[
U_{max}=77.9%
]

---

## Step 4: Verification

[
75% < 77.9%
]

Hence:

```text
Task Set is Schedulable
under RMS.
```

---

## Step 5: Hyperperiod

LCM:

```text
LCM(20,5,10)=20
```

Schedule must be checked from:

```text
0 → 20
```

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

# UNIT II – Q12(b)

# Compare Rate Monotonic Scheduling (RMS) and Earliest Deadline First (EDF)

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
Smaller Period
→ Higher Priority
```

---

## Example

| Task | Period |
| ---- | ------ |
| T1   | 5      |
| T2   | 10     |
| T3   | 20     |

Priority:

```text
T1 > T2 > T3
```

---

## Characteristics

* Fixed Priority
* Preemptive
* Predictable
* Easy implementation

---

## CPU Utilization Bound

[
U=n(2^{1/n}-1)
]

For large n:

[
U \approx 69%
]

Guaranteed schedulable only up to this bound.

---

# Earliest Deadline First (EDF)

## Definition

A dynamic-priority scheduling algorithm.

Priority assigned according to:

```text
Earliest Deadline
→ Highest Priority
```

---

## Example

| Task | Deadline |
| ---- | -------- |
| T1   | 8        |
| T2   | 5        |
| T3   | 15       |

Priority:

```text
T2 > T1 > T3
```

---

## Characteristics

* Dynamic Priority
* Preemptive
* Optimal Scheduling
* Higher CPU Utilization

---

## CPU Utilization Bound

EDF can schedule tasks if:

[
U \le 100%
]

Thus:

```text
Maximum Utilization
=
100%
```

---

# Comparison

| Feature           | RMS    | EDF            |
| ----------------- | ------ | -------------- |
| Priority          | Fixed  | Dynamic        |
| Based On          | Period | Deadline       |
| Complexity        | Low    | High           |
| Overhead          | Less   | More           |
| Predictability    | High   | Moderate       |
| Utilization Limit | 69–78% | 100%           |
| Schedulability    | Lower  | Better         |
| Implementation    | Easier | More Difficult |

---

# CPU Utilization Analysis

Example:

| Task | C | T  |
| ---- | - | -- |
| T1   | 2 | 5  |
| T2   | 3 | 10 |
| T3   | 2 | 20 |

[
U=\frac{2}{5}+\frac{3}{10}+\frac{2}{20}
]

[
U=0.4+0.3+0.1
]

[
U=0.8
]

[
U=80%
]

Since:

```text
80% > RMS Bound
```

RMS may fail.

But:

```text
80% < 100%
```

EDF can schedule successfully.

---

## Advantages of RMS

* Simple
* Predictable
* Low overhead

### Disadvantages

* Lower utilization
* Not optimal

---

## Advantages of EDF

* Optimal scheduling
* Maximum CPU utilization
* Better deadline satisfaction

### Disadvantages

* Complex implementation
* Higher overhead

---

## Conclusion

```text
RMS → Simple, Fixed Priority

EDF → Optimal, Dynamic Priority,
Higher Processor Utilization
```

---

Next I'll do:

**Q12(a) Process Scheduling Algorithms (Explain Any Two)**
**Q12(b) States of a Task in RTOS**


# UNIT II – Q12(a)

# Process Scheduling Algorithms – Explain Any Two

## Introduction

A scheduling algorithm determines:

* Which process gets CPU time
* Execution order of processes
* CPU utilization
* Waiting time
* Turnaround time

Scheduling is performed by the:

```text
Scheduler
```

inside the RTOS/Operating System.

---

# Common Scheduling Algorithms

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

After the quantum expires:

```text
Running Process
      ↓
Ready Queue
```

---

## Working

Suppose:

| Process | Burst Time |
| ------- | ---------- |
| P1      | 10         |
| P2      | 5          |
| P3      | 8          |

Quantum:

```text
Q = 4
```

---

## Gantt Chart

```text
0   4   8   12  16  19  23
|P1|P2|P3|P1|P3|P1|
```

---

## Advantages

* Fair CPU allocation
* Good response time
* Suitable for time-sharing systems

---

## Disadvantages

* High context switching overhead
* Performance depends on quantum size

---

# 2. Priority Scheduling

## Definition

CPU allocated according to priority.

Rule:

```text
Higher Priority
      ↓
Executed First
```

---

## Example

| Process | Priority |
| ------- | -------- |
| P1      | 3        |
| P2      | 1        |
| P3      | 2        |

Execution:

```text
P2 → P3 → P1
```

---

## Types

### Preemptive

Higher-priority process can interrupt running process.

### Non-Preemptive

Running process continues until completion.

---

## Advantages

* Important tasks finish quickly
* Suitable for RTOS

---

## Disadvantages

### Starvation

Low-priority process may wait indefinitely.

Solution:

```text
Aging
```

(increase priority over time)

---

# Comparison

| Feature    | Round Robin  | Priority                    |
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

# UNIT II – Q12(b)

# Various States of a Task in Real-Time Operating Systems (RTOS)

## Introduction

A **Task** is the basic unit of execution in an RTOS.

Examples:

* Reading sensor data
* Updating LCD
* Sending IoT data
* Handling interrupts

The RTOS scheduler manages tasks by moving them through different states.

---

# Task State Diagram

```text
                 +---------+
                 |  Ready  |
                 +----+----+
                      |
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
```

---

# 1. New State

Task has been created but not yet ready for execution.

Operations:

* Memory allocation
* Task initialization
* Stack creation

---

# 2. Ready State

Task is prepared for execution.

Characteristics:

* Waiting for CPU
* All resources available
* Present in Ready Queue

Example:

```text
Sensor Task waiting for CPU
```

---

# 3. Running State

Task currently executing on CPU.

Characteristics:

* CPU assigned
* Instructions executing

Only one task can run at a time on a single-core processor.

---

# 4. Blocked (Waiting) State

Task waits for:

* Event
* I/O completion
* Message
* Semaphore
* Timer expiration

Example:

```text
Waiting for UART data
```

---

# 5. Suspended State

Task temporarily removed from scheduling.

Characteristics:

* Cannot execute
* Must be resumed explicitly

Example:

```text
Maintenance task disabled temporarily
```

---

# 6. Terminated State

Task has completed execution.

Characteristics:

* Resources released
* Removed from scheduler

---

# State Transitions

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

# Task Management Functions

RTOS provides:

### Task Creation

```text
Create Task
```

### Task Deletion

```text
Delete Task
```

### Task Suspension

```text
Suspend Task
```

### Task Resumption

```text
Resume Task
```

### Context Switching

Switch CPU from one task to another.

---

# Importance of Task States

* Efficient CPU utilization
* Supports multitasking
* Improves responsiveness
* Helps meet real-time deadlines

---

# Applications

* Embedded Controllers
* Robotics
* Automotive Systems
* Medical Devices
* Industrial Automation
* IoT Systems

---

## Keywords

Task, Process, Scheduler, Ready Queue, Running State, Blocked State, Suspended State, Context Switching, Multitasking, Semaphore, RTOS.


# UNIT II – Remaining Question 1

# Priority-Based Scheduling Policies

## Introduction

Priority Scheduling is a CPU scheduling algorithm in which each process is assigned a priority and the CPU is allocated to the highest-priority process first.

Rule:

```text
Higher Priority
      ↓
Executed First
```

Used in:

* RTOS
* Embedded Systems
* Industrial Automation
* Mission Critical Systems

---

# Types of Priority Scheduling

## 1. Non-Preemptive Priority Scheduling

Once CPU is allocated:

```text
Process runs till completion
```

even if a higher-priority process arrives.

---

### Example

| Process | Priority |
| ------- | -------- |
| P1      | 3        |
| P2      | 1        |
| P3      | 2        |

Execution:

```text
P2 → P3 → P1
```

---

## 2. Preemptive Priority Scheduling

If a higher-priority process arrives:

```text
Running Process
       ↓
Interrupted
```

CPU immediately assigned to higher-priority process.

---

### Example

```text
Time 0 : P1 starts

Time 3 : P2 arrives
         Higher Priority

P1 interrupted
P2 executes
```

---

# Working Principle

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

# Advantages

* Fast response for critical tasks
* Suitable for real-time systems
* Better control over task execution
* Important tasks finish first

---

# Disadvantages

## Starvation

Low-priority processes may never execute.

Example:

```text
High Priority Jobs
keep arriving continuously
```

Low-priority task waits forever.

---

## Aging

Solution to starvation.

Priority gradually increased with waiting time.

```text
Waiting Time ↑
Priority ↑
```

---

# Example Gantt Chart

| Process | Burst | Priority |
| ------- | ----- | -------- |
| P1      | 8     | 3        |
| P2      | 4     | 1        |
| P3      | 6     | 2        |

Execution:

```text
0      4      10      18
| P2 | P3 | P1 |
```

---

# Comparison

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

# UNIT II – Remaining Question 2

# Round Robin Scheduling Numerical

## Given

| Process | Burst Time | Arrival Time |
| ------- | ---------- | ------------ |
| P0      | 80         | 0            |
| P1      | 20         | 10           |
| P2      | 10         | 10           |
| P3      | 20         | 40           |
| P4      | 50         | 45           |

Time Quantum:

```text
Q = 15
```

---

# Step 1: Gantt Chart

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

# Step 2: Turnaround Time

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

---

## Average Turnaround Time

```text
(190+75+45+90+145)/5
```

```text
545/5
```

```text
109 units
```

---

# Step 3: Waiting Time

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

---

## Average Waiting Time

```text
(110+55+35+70+95)/5
```

```text
365/5
```

```text
73 units
```

---

# Step 4: Context Switches

Gantt Chart:

```text
P0→P1
P1→P2
P2→P0
P0→P3
P3→P4
P4→P1
P1→P0
P0→P3
P3→P4
P4→P0
P0→P4
P4→P0
```

Total Context Switches:

```text
12
```

If context switch time is not specified:

```text
Context Switching Count = 12
```

If each switch takes:

```text
1 unit
```

Then:

```text
Total Context Switching Time = 12 units
```

---

# UNIT II – Final Unique Question List

### Q12(a)

1. RMS Schedulability Analysis (Numerical)
2. Process Scheduling Algorithms (Explain Any Two)

### Q12(b)

3. RMS vs EDF Comparison
4. Task States in RTOS
5. Priority-Based Scheduling Policies
6. Round Robin Scheduling Numerical

That completes all unique UNIT II long questions visible across the papers you shared.
