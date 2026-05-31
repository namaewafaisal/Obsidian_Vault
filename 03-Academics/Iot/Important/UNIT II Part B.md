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
