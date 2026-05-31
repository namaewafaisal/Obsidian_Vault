# UNIT I – PART B

---

# 1. 8051 Architecture: Detailed Block Diagram and Functional Description

## Definition

The **8051** is an **8-bit microcontroller** developed by Intel. It contains CPU, memory, I/O ports, timers, serial communication, and interrupt system on a single chip.

---

## Block Diagram

```text
                 +------------------+
                 |      CPU         |
                 | ALU + Registers  |
                 +--------+---------+
                          |
    +---------+-----------+-----------+---------+
    |         |           |           |         |
+---+---+ +---+---+ +-----+----+ +----+----+ +--+--+
| ROM   | | RAM   | | Timers   | | Serial  | |INTs |
| 4 KB  | |128 B  | | T0,T1    | | Port    | |Sys  |
+-------+ +-------+ +----------+ +---------+ +-----+
                          |
                  +-------+-------+
                  | I/O Ports     |
                  | P0 P1 P2 P3   |
                  +---------------+
```

---

## Main Components

### 1. CPU (Central Processing Unit)

**Function:** Executes instructions.

Contains:

#### ALU (Arithmetic Logic Unit)

Performs:

* Addition
* Subtraction
* AND
* OR
* XOR
* Comparison

#### Accumulator (A)

* Main working register
* Most arithmetic operations use A

Example:

```assembly
ADD A,R0
```

---

### 2. Program Memory (ROM)

* Size = 4 KB
* Stores program instructions
* Non-volatile memory

**Non-volatile:** Data remains even after power OFF.

---

### 3. Data Memory (RAM)

* Size = 128 Bytes
* Stores temporary data

Used for:

* Variables
* Stack
* Register Banks

---

### 4. Register Banks

8051 contains:

* Bank 0
* Bank 1
* Bank 2
* Bank 3

Each bank contains:

```text
R0 R1 R2 R3 R4 R5 R6 R7
```

Selected using:

```text
PSW → RS1 RS0
```

---

### 5. I/O Ports

Total = 32 pins

| Port | Pins |
| ---- | ---- |
| P0   | 8    |
| P1   | 8    |
| P2   | 8    |
| P3   | 8    |

Used to connect:

* LEDs
* Switches
* Sensors
* LCDs

---

### 6. Timers/Counters

Two timers:

* Timer0
* Timer1

Used for:

* Time delay generation
* Event counting
* Baud rate generation

---

### 7. Serial Port

Provides UART communication.

UART = Universal Asynchronous Receiver Transmitter

Functions:

* Data transmission
* Data reception

---

### 8. Interrupt System

Allows CPU to respond immediately to important events.

Interrupt Sources:

* INT0
* Timer0
* INT1
* Timer1
* Serial Port

---

## Features of 8051

* 8-bit CPU
* 4 KB ROM
* 128 B RAM
* 32 I/O pins
* 2 Timers
* Serial Communication
* 5 Interrupts
* Bit-addressable memory

---

## Keywords

* Microcontroller
* ALU
* Accumulator
* ROM
* RAM
* Register Bank
* UART
* Interrupt
* Timer

---

# 2. Instruction Set of 8051

## Definition

An **Instruction Set** is the collection of commands understood by the microcontroller.

---

# A. Arithmetic Instructions

Used for mathematical operations.

---

## ADD

Adds two numbers.

Example:

```assembly
ADD A,R1
```

Meaning:

```text
A = A + R1
```

---

## ADDC

Addition with carry.

Example:

```assembly
ADDC A,R1
```

Used in multi-byte addition.

---

## SUBB

Subtract with borrow.

Example:

```assembly
SUBB A,R2
```

Meaning:

```text
A = A - R2 - Borrow
```

---

## INC

Increment by 1.

Example:

```assembly
INC A
```

---

## DEC

Decrement by 1.

Example:

```assembly
DEC R0
```

---

## MUL AB

Multiply A and B.

Example:

```assembly
MUL AB
```

Result:

```text
A × B
```

Stored in:

```text
A and B
```

---

## DIV AB

Division.

Example:

```assembly
DIV AB
```

Result:

```text
A/B
```

Quotient → A

Remainder → B

---

# B. Logical Instructions

Used for decision making and bit manipulation.

---

## ANL (AND)

Example:

```assembly
ANL A,#0FH
```

---

## ORL (OR)

Example:

```assembly
ORL A,#80H
```

---

## XRL (XOR)

Example:

```assembly
XRL A,R1
```

---

## CLR

Clear a bit.

Example:

```assembly
CLR A
```

---

## CPL

Complement.

Example:

```assembly
CPL A
```

Changes:

```text
0 → 1
1 → 0
```

---

## RL / RR

Rotate Left / Rotate Right

Example:

```assembly
RL A
RR A
```

---

# C. Data Transfer Instructions

Move data between registers and memory.

---

## MOV

Most commonly used.

Example:

```assembly
MOV A,#25H
```

Loads 25H into A.

---

## MOVX

Move external memory data.

Example:

```assembly
MOVX A,@DPTR
```

---

## MOVC

Move code memory data.

Example:

```assembly
MOVC A,@A+DPTR
```

---

## PUSH

Stores data in stack.

Example:

```assembly
PUSH 30H
```

---

## POP

Retrieves data from stack.

Example:

```assembly
POP 30H
```

---

## XCH

Exchange data.

Example:

```assembly
XCH A,R0
```

---

## Classification Table

| Type          | Examples                            |
| ------------- | ----------------------------------- |
| Arithmetic    | ADD, ADDC, SUBB, INC, DEC, MUL, DIV |
| Logical       | ANL, ORL, XRL, CLR, CPL             |
| Data Transfer | MOV, MOVX, MOVC, PUSH, POP, XCH     |

---

## Keywords

* Arithmetic
* Logical
* Data Transfer
* Accumulator
* Carry
* Borrow
* Stack
* Rotate

---

# 3. Timers/Counters in 8051

## Definition

Timers/Counters are hardware modules used for:

* Delay generation
* Event counting
* Frequency measurement
* Baud rate generation

---

## Available Timers

* Timer0
* Timer1

Both are 16-bit timers.

---

# TMOD Register

TMOD = Timer Mode Register

```text
GATE C/T M1 M0 | GATE C/T M1 M0
 Timer1          Timer0
```

---

### GATE

* 1 → Controlled by external pin
* 0 → Controlled by software

---

### C/T

Counter/Timer selection

* 0 → Timer mode
* 1 → Counter mode

---

### M1 M0

Select timer mode.

---

# Timer Modes

## Mode 0 (13-bit Timer)

```text
M1=0 M0=0
```

* 13-bit timer
* Rarely used

---

## Mode 1 (16-bit Timer)

```text
M1=0 M0=1
```

* Full 16-bit timer
* Most commonly used

Range:

```text
0000H → FFFFH
```

---

## Mode 2 (8-bit Auto Reload)

```text
M1=1 M0=0
```

* 8-bit timer
* Automatically reloads

Used in:

* Baud rate generation

---

## Mode 3 (Split Timer)

```text
M1=1 M0=1
```

* Timer0 split into two 8-bit timers

---

# TCON Register

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

---

### TF1

Timer1 overflow flag

### TR1

Timer1 run control

### TF0

Timer0 overflow flag

### TR0

Timer0 run control

### IE1 / IE0

External interrupt flags

### IT1 / IT0

Interrupt type selection

---

## Timer Programming Steps

1. Select timer mode using TMOD.
2. Load initial value into THx and TLx.
3. Start timer using TRx.
4. Wait until TFx becomes 1.
5. Stop timer.
6. Clear TFx.

---

## Keywords

* Timer
* Counter
* TMOD
* TCON
* Overflow
* Auto Reload
* Baud Rate

---

# 4. Interrupt Structure of 8051

## Definition

An **Interrupt** is a signal that temporarily stops the current program and executes a special routine called ISR.

ISR = Interrupt Service Routine

---

## Why Interrupts?

Without interrupts:

```text
CPU continuously checks devices
```

With interrupts:

```text
Device notifies CPU only when needed
```

Efficient CPU utilization.

---

## Interrupt Sources

| Interrupt   | Vector Address |
| ----------- | -------------- |
| INT0        | 0003H          |
| Timer0      | 000BH          |
| INT1        | 0013H          |
| Timer1      | 001BH          |
| Serial Port | 0023H          |

---

## Priority Levels

8051 supports:

### High Priority

Executed first.

### Low Priority

Executed only when no high-priority interrupt exists.

Configured using:

```text
IP Register
```

---

## Interrupt Enable Register (IE)

```text
EA ES ET1 EX1 ET0 EX0
```

---

### EA

Global Interrupt Enable

```text
1 = Enable all interrupts
0 = Disable all interrupts
```

---

### ET0

Enable Timer0 interrupt

### ET1

Enable Timer1 interrupt

### EX0

Enable External Interrupt0

### EX1

Enable External Interrupt1

### ES

Enable Serial interrupt

---

## Interrupt Handling Sequence

1. Interrupt occurs.
2. Current instruction finishes.
3. PC saved in stack.
4. CPU jumps to ISR.
5. ISR executes.
6. RETI instruction executed.
7. CPU returns to main program.

---

## Advantages

* Fast response
* Better CPU utilization
* Supports real-time applications
* Eliminates continuous polling

---

## Keywords

* Interrupt
* ISR
* IE Register
* IP Register
* Priority
* Vector Address
* RETI
* External Interrupt
* Timer Interrupt

---

### Exam Tip

For **13–16 marks**, always write:

1. Definition
2. Diagram/Register format
3. Functional explanation
4. Features/Advantages
5. Keywords or summary points

This structure usually fetches the maximum marks in university exams.
