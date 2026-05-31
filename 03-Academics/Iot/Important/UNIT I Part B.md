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


Good point. Let's do it properly.

# UNIT I – Q1

# 8051 Microcontroller Architecture with Timer Mode Operation

## Introduction

The **8051** is an 8-bit microcontroller developed by Intel. It contains CPU, memory, timers, serial communication, interrupt controller and I/O ports on a single chip.

### Features

* 8-bit CPU
* 4 KB ROM
* 128 Bytes RAM
* 32 I/O pins
* 2 Timers/Counters
* 5 Interrupts
* Full Duplex UART
* Bit-addressable RAM

---

## Architecture Diagram

```text
                      +----------------+
                      | Oscillator     |
                      +-------+--------+
                              |
+--------------------------------------------------+
|                    CPU                           |
| ALU | Accumulator | B | PSW | PC | DPTR | SP    |
+--------------------------------------------------+
      |          |           |            |
      |          |           |            |
 +----+---+  +---+----+  +---+---+  +----+----+
 | ROM   |  | RAM    |  |Timers |  | Serial  |
 | 4 KB  |  |128 B   |  |T0,T1  |  | Port    |
 +--------+ +--------+  +-------+  +---------+
      |          |           |            |
      +----------+-----------+------------+
                         |
                +--------+--------+
                | I/O Ports       |
                | P0 P1 P2 P3     |
                +-----------------+
```

---

## CPU

CPU executes instructions.

Contains:

### ALU

Performs:

* Addition
* Subtraction
* AND
* OR
* XOR
* Comparison

### Accumulator (A)

Main arithmetic register.

Example:

```assembly
ADD A,R0
```

### B Register

Used in:

```assembly
MUL AB
DIV AB
```

### PSW (Program Status Word)

Contains flags:

```text
CY AC F0 RS1 RS0 OV -- P
```

| Flag | Function        |
| ---- | --------------- |
| CY   | Carry           |
| AC   | Auxiliary Carry |
| OV   | Overflow        |
| P    | Parity          |

---

## Program Memory (ROM)

* Size = 4 KB
* Stores instructions
* Non-volatile

Can be expanded externally up to:

```text
64 KB
```

using:

```text
EA
PSEN
```

pins.

---

## Data Memory (RAM)

Size:

```text
128 Bytes
```

Organization:

```text
00H–1FH   Register Banks
20H–2FH   Bit Addressable RAM
30H–7FH   General Purpose RAM
```

---

## Register Banks

Four banks:

```text
Bank0
Bank1
Bank2
Bank3
```

Each contains:

```text
R0–R7
```

Selected using:

```text
PSW → RS1 RS0
```

---

## I/O Ports

Four ports:

```text
P0
P1
P2
P3
```

Total:

```text
32 I/O Pins
```

### Port Functions

| Port | Function            |
| ---- | ------------------- |
| P0   | Address/Data Bus    |
| P1   | General Purpose     |
| P2   | Higher Address Bus  |
| P3   | Alternate Functions |

---

### Port 3 Alternate Functions

| Pin  | Function |
| ---- | -------- |
| P3.0 | RXD      |
| P3.1 | TXD      |
| P3.2 | INT0     |
| P3.3 | INT1     |
| P3.4 | T0       |
| P3.5 | T1       |
| P3.6 | WR       |
| P3.7 | RD       |

---

## Serial Communication Unit

Uses UART.

Pins:

```text
RXD → P3.0
TXD → P3.1
```

Supports:

* Transmission
* Reception
* Full Duplex Communication

---

## Interrupt Controller

Five interrupt sources:

| Interrupt | Vector Address |
| --------- | -------------- |
| INT0      | 0003H          |
| Timer0    | 000BH          |
| INT1      | 0013H          |
| Timer1    | 001BH          |
| Serial    | 0023H          |

Priority controlled through:

```text
IE Register
IP Register
```

---

# Timer Mode Operation

8051 contains:

```text
Timer0
Timer1
```

Each timer is:

```text
16-bit
```

using:

```text
TH0 TL0
TH1 TL1
```

---

## TMOD Register

```text
GATE C/T M1 M0 | GATE C/T M1 M0
 Timer1             Timer0
```

### GATE

* 0 → Software control
* 1 → Hardware control

### C/T

* 0 → Timer
* 1 → Counter

### M1 M0

Select mode.

---

## Timer Modes

### Mode 0

```text
M1=0 M0=0
```

* 13-bit timer
* Count = 8192

---

### Mode 1

```text
M1=0 M0=1
```

* 16-bit timer
* Range:

```text
0000H → FFFFH
```

* Most widely used

---

### Mode 2

```text
M1=1 M0=0
```

* 8-bit Auto Reload
* THx stores reload value
* TLx performs counting

Used for:

```text
Baud Rate Generation
```

---

### Mode 3

```text
M1=1 M0=1
```

Timer0 splits into:

```text
TL0
TH0
```

Two separate 8-bit timers.

---

## TCON Register

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

### TF0

Timer0 overflow flag.

### TF1

Timer1 overflow flag.

### TR0

Starts Timer0.

### TR1

Starts Timer1.

---

## Timer Operation Sequence

```text
Select Mode
      ↓
Load THx TLx
      ↓
Set TRx
      ↓
Timer Counts
      ↓
Overflow
      ↓
TFx = 1
      ↓
Interrupt / Polling
```

---

## Applications

* Time Delay Generation
* Frequency Measurement
* Event Counting
* UART Baud Rate Generation
* Real-Time Clock

---

## Keywords

8051, CPU, ALU, Accumulator, PSW, ROM, RAM, Register Bank, UART, TMOD, TCON, Timer0, Timer1, Counter Mode, Auto Reload, Overflow Flag, Interrupt Controller.

---

# UNIT I – Q2

# Interrupt Handling Methods in 8051 with Neat Sketch

## Introduction

An **Interrupt** is an event that temporarily suspends the current program execution and transfers control to an Interrupt Service Routine (ISR).

Interrupts improve:

* CPU Utilization
* Response Time
* Real-Time Performance

---

## Need for Interrupts

### Polling Method

```text
CPU checks device continuously
```

Disadvantages:

* CPU wastage
* Slow response

---

### Interrupt Method

```text
Device requests service only when needed
```

Advantages:

* Efficient CPU utilization
* Faster response

---

## Interrupt Structure

```text
              Interrupt Request
                       |
                       v
               Interrupt Controller
                       |
                       v
                    CPU
                       |
                       v
                     ISR
                       |
                     RETI
                       |
                   Main Program
```

---

## Interrupt Sources in 8051

| Source      | Flag  | Vector Address |
| ----------- | ----- | -------------- |
| INT0        | IE0   | 0003H          |
| Timer0      | TF0   | 000BH          |
| INT1        | IE1   | 0013H          |
| Timer1      | TF1   | 001BH          |
| Serial Port | RI/TI | 0023H          |

---

## Interrupt Enable Register (IE)

```text
EA -- -- ES ET1 EX1 ET0 EX0
```

### EA

Global Interrupt Enable.

```text
EA = 1
```

Enable interrupts.

```text
EA = 0
```

Disable interrupts.

---

### ET0

Enable Timer0 interrupt.

### ET1

Enable Timer1 interrupt.

### EX0

Enable INT0.

### EX1

Enable INT1.

### ES

Enable Serial interrupt.

---

## Interrupt Priority Register (IP)

```text
-- -- -- PS PT1 PX1 PT0 PX0
```

Used to assign:

* High Priority
* Low Priority

---

## External Interrupt Control

Located in:

```text
TCON Register
```

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

---

### IT0

INT0 Trigger Type

```text
IT0=1
```

Edge Triggered

```text
IT0=0
```

Level Triggered

---

### IT1

INT1 Trigger Type

Same operation.

---

## Interrupt Handling Sequence

```text
Interrupt Occurs
        ↓
Current Instruction Finishes
        ↓
PC Saved In Stack
        ↓
Jump To Vector Address
        ↓
ISR Executes
        ↓
RETI
        ↓
PC Restored
        ↓
Main Program Continues
```

---

## Example ISR

```assembly
ORG 0003H

ISR:
    CPL P1.0
    RETI
```

When INT0 occurs:

```text
LED connected to P1.0 toggles
```

---

## Interrupt Handling Methods

### 1. Polling

CPU continuously checks flags.

```text
TF0 ?
RI ?
IE0 ?
```

Simple but inefficient.

---

### 2. Interrupt-Driven Method

Hardware automatically invokes ISR.

Most efficient.

Used in RTOS and Embedded Systems.

---

## Advantages

* Better CPU utilization
* Fast response
* Supports multitasking
* Suitable for real-time applications

---

## Keywords

Interrupt, ISR, RETI, Vector Address, IE Register, IP Register, TCON, Polling, Edge Triggered, Level Triggered, INT0, INT1, Timer Interrupt, Serial Interrupt.

---

Send **"next 2"** and I'll do:

**3. Internal RAM Organization + Stack Operation**
**4. Serial Communication in 8051**

at the same exam-answer level.


# UNIT I – Q3

# Internal RAM Organization of 8051 and Stack Operation

## Introduction

The 8051 contains **128 Bytes of internal RAM** located from:

```text
00H to 7FH
```

This RAM is divided into:

1. Register Banks
2. Bit Addressable RAM
3. General Purpose RAM

Internal RAM is used for:

* Variables
* Stack
* Temporary Data Storage
* Register Banks

---

## Internal RAM Organization

### Memory Map

```text
+---------------------+
| 7FH                 |
| General Purpose RAM |
| 30H - 7FH           |
+---------------------+
| Bit Addressable RAM |
| 20H - 2FH           |
+---------------------+
| Register Banks      |
| 00H - 1FH           |
+---------------------+
| 00H                 |
+---------------------+
```

---

## Register Bank Area

Address Range:

```text
00H - 1FH
```

Size:

```text
32 Bytes
```

Contains:

```text
4 Register Banks
```

---

### Bank 0

```text
00H - 07H
```

Registers:

```text
R0 R1 R2 R3 R4 R5 R6 R7
```

---

### Bank 1

```text
08H - 0FH
```

---

### Bank 2

```text
10H - 17H
```

---

### Bank 3

```text
18H - 1FH
```

---

### Bank Selection

Done using:

```text
PSW Register
```

Bits:

```text
RS1 RS0
```

| RS1 | RS0 | Bank  |
| --- | --- | ----- |
| 0   | 0   | Bank0 |
| 0   | 1   | Bank1 |
| 1   | 0   | Bank2 |
| 1   | 1   | Bank3 |

---

## Bit Addressable RAM

Address Range:

```text
20H - 2FH
```

Size:

```text
16 Bytes
```

Contains:

```text
128 individually addressable bits
```

Useful for:

* Flags
* Control Bits
* Status Indicators

---

### Example

```assembly
SETB 20H
CLR 20H
```

Individual bits can be manipulated.

---

## General Purpose RAM

Address Range:

```text
30H - 7FH
```

Size:

```text
80 Bytes
```

Used for:

* Variables
* Buffers
* Arrays
* Temporary Storage

Example:

```assembly
MOV 30H,#25H
```

Stores 25H in RAM location 30H.

---

# Stack Operation

## Definition

Stack is a temporary storage area used to store:

* Return Addresses
* Register Contents
* Local Data

Works on:

```text
LIFO
(Last In First Out)
```

---

## Stack Pointer (SP)

Special Function Register:

```text
SP
```

Stores address of top of stack.

Default Value:

```text
SP = 07H
```

Therefore first PUSH occurs at:

```text
08H
```

---

## PUSH Operation

Syntax:

```assembly
PUSH direct_address
```

Example:

```assembly
MOV 30H,#55H
PUSH 30H
```

Operation:

```text
SP = SP + 1
Data stored at stack location
```

---

### PUSH Illustration

Initially:

```text
SP = 07H
```

After:

```assembly
PUSH 30H
```

```text
SP = 08H
RAM[08H] = 55H
```

---

## POP Operation

Syntax:

```assembly
POP direct_address
```

Example:

```assembly
POP 40H
```

Operation:

```text
Data copied from stack
SP = SP - 1
```

---

### POP Illustration

Before:

```text
SP = 08H
RAM[08H] = 55H
```

After:

```assembly
POP 40H
```

```text
40H = 55H
SP = 07H
```

---

## Stack During Subroutine Call

### CALL Instruction

When CALL executes:

```text
Program Counter saved on stack
```

---

### RET Instruction

When RET executes:

```text
Program Counter restored
```

from stack.

---

## Demonstration Program

```assembly
MOV SP,#07H

MOV 30H,#25H
MOV 31H,#55H

PUSH 30H
PUSH 31H

POP 40H
POP 41H

END
```

### Result

```text
41H = 25H
40H = 55H
```

LIFO property verified.

---

## Applications of Stack

* Function Calls
* Interrupt Handling
* Temporary Data Storage
* Context Saving

---

## Keywords

Internal RAM, Register Bank, Bit Addressable Memory, General Purpose RAM, Stack Pointer, PUSH, POP, LIFO, CALL, RET, PSW, RS1, RS0.

---

# UNIT I – Q4

# Serial Communication in 8051 and Hardware/Software Support

## Introduction

Serial communication transfers data:

```text
One Bit At A Time
```

through a single communication channel.

8051 provides built-in UART hardware for serial communication.

Applications:

* PC Interfacing
* GSM Module
* GPS Module
* Bluetooth Module
* IoT Communication

---

## Serial Communication Block

```text
          +-------------+
          |   8051      |
          |             |
RXD P3.0 <---- Receive
TXD P3.1 ----> Transmit
          +-------------+
```

---

## Hardware Support

### Serial Port

Dedicated UART available inside 8051.

Uses:

| Pin  | Function |
| ---- | -------- |
| P3.0 | RXD      |
| P3.1 | TXD      |

---

## Important Registers

### SBUF Register

Serial Buffer Register.

Used for:

* Transmission
* Reception

Example:

```assembly
MOV SBUF,#'A'
```

Transmits character A.

---

### SCON Register

Serial Control Register.

Format:

```text
SM0 SM1 SM2 REN TB8 RB8 TI RI
```

---

### SM0, SM1

Select serial mode.

---

### REN

Receive Enable.

```text
REN = 1
```

Reception enabled.

---

### TI

Transmit Interrupt Flag.

Set when transmission completes.

---

### RI

Receive Interrupt Flag.

Set when reception completes.

---

## PCON Register

Used for baud rate control.

Important Bit:

```text
SMOD
```

When:

```text
SMOD = 1
```

Baud rate doubles.

---

# Serial Modes

## Mode 0

### Features

* Shift Register Mode
* 8-bit Data
* Fixed Baud Rate

---

## Mode 1

### Features

* 8-bit UART
* Start Bit
* Stop Bit
* Variable Baud Rate

Most commonly used.

Frame Format:

```text
Start
  |
8 Data Bits
  |
Stop
```

---

## Mode 2

### Features

* 9-bit UART
* Fixed Baud Rate

---

## Mode 3

### Features

* 9-bit UART
* Variable Baud Rate

---

# Baud Rate Generation

Usually generated using:

```text
Timer1
Mode2
```

(8-bit Auto Reload)

---

## Baud Rate Formula

For Mode 1:

```text
Baud Rate =
(2^SMOD / 32)
×
Timer Overflow Rate
```

---

# Data Transmission Process

```assembly
MOV SBUF,#'A'
```

Process:

```text
Load SBUF
     ↓
UART Transmits
     ↓
TI = 1
     ↓
Transmission Complete
```

---

## Transmission Program

```assembly
MOV SCON,#50H
MOV TMOD,#20H
MOV TH1,#0FDH
SETB TR1

MOV SBUF,#'A'

WAIT:
JNB TI,WAIT

CLR TI
```

---

# Data Reception Process

```text
Data Arrives
      ↓
Stored in SBUF
      ↓
RI = 1
      ↓
CPU Reads Data
```

---

## Reception Program

```assembly
WAIT:
JNB RI,WAIT

MOV A,SBUF
CLR RI
```

---

# Serial Interrupt

Interrupt Vector:

```text
0023H
```

Flags:

```text
TI
RI
```

Trigger serial interrupt.

---

# Applications

* PC Communication
* GSM Interfacing
* GPS Tracking
* Bluetooth Communication
* IoT Devices
* Wireless Sensor Networks

---

## Advantages

* Requires fewer wires
* Long-distance communication
* Low hardware cost
* Easy interfacing

---

## Keywords

UART, RXD, TXD, SBUF, SCON, PCON, Baud Rate, Timer1 Mode2, TI, RI, Serial Interrupt, Start Bit, Stop Bit, Full Duplex Communication.


# UNIT I – Q5

# Timer/Counter Programming in 8051 and Time-of-Day Clock

## Definition

Timer/Counter is a hardware module used for:

* Delay generation
* Event counting
* Frequency measurement
* Baud rate generation
* Real-time clock implementation

8051 contains:

```text
Timer0
Timer1
```

Each timer consists of:

```text
TH0 TL0
TH1 TL1
```

---

## Timer Registers

### TMOD

```text
GATE C/T M1 M0 | GATE C/T M1 M0
 Timer1             Timer0
```

### GATE

* 0 → Software control
* 1 → External hardware control

### C/T

* 0 → Timer
* 1 → Counter

### M1 M0

Select timer mode.

---

## Timer Modes

### Mode 0

```text
13-bit Timer
```

Count Range:

```text
0000H – 1FFFH
```

---

### Mode 1

```text
16-bit Timer
```

Count Range:

```text
0000H – FFFFH
```

Most commonly used.

---

### Mode 2

```text
8-bit Auto Reload
```

Used for:

* UART Baud Rate Generation

---

### Mode 3

```text
Split Timer Mode
```

Timer0 becomes:

```text
TH0
TL0
```

independent timers.

---

## TCON Register

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

### TF0

Timer0 Overflow Flag

### TF1

Timer1 Overflow Flag

### TR0

Start Timer0

### TR1

Start Timer1

---

# Time-of-Day Clock

Displays:

```text
Hours
Minutes
Seconds
```

using BCD format.

---

## Block Diagram

```text
         Timer0
            |
            v
      1 Second Delay
            |
            v
      Update Time
            |
   +--------+--------+
   |        |        |
 Hours   Minutes  Seconds
```

---

## Algorithm

1. Initialize Timer0 Mode1.
2. Generate 1-second delay.
3. Increment seconds.
4. If seconds = 60:

   * seconds = 00
   * increment minutes
5. If minutes = 60:

   * minutes = 00
   * increment hours
6. If hours = 24:

   * hours = 00
7. Output values to ports.

---

## Flowchart

```text
Start
  |
Initialize Timer
  |
Generate 1 sec Delay
  |
Seconds++
  |
Seconds=60?
 / \
No  Yes
 |    |
 |  Seconds=0
 |  Minutes++
 |     |
 | Minutes=60?
 |   / \
 | No  Yes
 |      |
 |   Minutes=0
 |   Hours++
 |      |
 | Hours=24?
 |   / \
 | No  Yes
 |      |
 |   Hours=0
 |
Display Time
 |
Repeat
```

---

## Keywords

TMOD, TCON, Timer0, Timer1, BCD, Overflow Flag, TH0, TL0, Real-Time Clock, Delay Generation.

---

# UNIT I – Q6

# Embedded C Program to Toggle P1.0 Every 10 ms (XTAL = 11.0592 MHz)

## Given

```text
XTAL = 11.0592 MHz
Delay = 10 ms
Output Pin = P1.0
```

---

## Timer Calculation

### Machine Cycle Frequency

```text
11.0592 MHz / 12
=
921.6 kHz
```

---

### Machine Cycle Time

```text
1 / 921.6k
=
1.085 µs
```

---

### Required Counts

```text
10 ms / 1.085 µs
≈ 9216
```

---

### Initial Timer Value

```text
65536 - 9216
=
56320
=
DC00H
```

Load:

```text
TH0 = DCH
TL0 = 00H
```

---

## Algorithm

1. Configure Timer0 Mode1.
2. Load TH0 and TL0.
3. Start Timer0.
4. Wait for TF0.
5. Stop Timer0.
6. Clear TF0.
7. Toggle P1.0.
8. Repeat.

---

## Embedded C Program

```c
#include <reg51.h>

sbit LED = P1^0;

void delay10ms()
{
    TMOD = 0x01;

    TH0 = 0xDC;
    TL0 = 0x00;

    TR0 = 1;

    while(TF0 == 0);

    TR0 = 0;
    TF0 = 0;
}

void main()
{
    while(1)
    {
        LED = ~LED;
        delay10ms();
    }
}
```

---

## Program Flow

```text
Start
  |
Initialize Timer0
  |
Load DC00H
  |
Start Timer
  |
TF0 = 1 ?
  |
Toggle P1.0
  |
Repeat
```

---

## Output Waveform

```text
P1.0

__|‾‾|__|‾‾|__|‾‾|__
<10ms><10ms><10ms>
```

---

## Keywords

Machine Cycle, XTAL, Timer0 Mode1, TH0, TL0, TF0, TR0, Embedded C, Delay Calculation, Toggle Operation.

---

These complete **all unique UNIT I 13-mark questions** found across the uploaded papers:

1. 8051 Architecture with Timer Mode Operation
2. Interrupt Handling Methods
3. Internal RAM Organization & Stack Operation
4. Serial Communication in 8051
5. Timer/Counter Programming & Time-of-Day Clock
6. 10 ms Toggle Program (Embedded C)

