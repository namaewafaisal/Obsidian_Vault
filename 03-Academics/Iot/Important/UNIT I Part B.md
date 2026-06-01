# UNIT I – Complete Question Bank

---

# Q1: 8051 Microcontroller Architecture with Timer Mode Operation

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

Each timer is 16-bit, using:

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
* Count Range: 0000H – 1FFFH

---

### Mode 1

```text
M1=0 M0=1
```

* 16-bit timer
* Range: 0000H → FFFFH
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

# Q2: Interrupt Handling Methods in 8051 with Neat Sketch

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
EA = 1  →  Enable interrupts
EA = 0  →  Disable interrupts
```

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

Located in TCON Register:

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

### IT0

INT0 Trigger Type

```text
IT0=1  →  Edge Triggered
IT0=0  →  Level Triggered
```

### IT1

INT1 Trigger Type — same operation.

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

# Q3: Internal RAM Organization of 8051 and Stack Operation

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

Address Range: `00H - 1FH` | Size: 32 Bytes | Contains: 4 Register Banks

### Bank 0 → `00H - 07H`
### Bank 1 → `08H - 0FH`
### Bank 2 → `10H - 17H`
### Bank 3 → `18H - 1FH`

Registers in each bank:

```text
R0 R1 R2 R3 R4 R5 R6 R7
```

### Bank Selection

Done using PSW Register bits RS1 RS0:

| RS1 | RS0 | Bank  |
| --- | --- | ----- |
| 0   | 0   | Bank0 |
| 0   | 1   | Bank1 |
| 1   | 0   | Bank2 |
| 1   | 1   | Bank3 |

---

## Bit Addressable RAM

Address Range: `20H - 2FH` | Size: 16 Bytes | Contains: 128 individually addressable bits

Useful for:

* Flags
* Control Bits
* Status Indicators

Example:

```assembly
SETB 20H
CLR 20H
```

Individual bits can be manipulated.

---

## General Purpose RAM

Address Range: `30H - 7FH` | Size: 80 Bytes

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

Special Function Register. Stores address of top of stack.

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

### PUSH Illustration

Initially: `SP = 07H`

After `PUSH 30H`:

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

### POP Illustration

Before: `SP = 08H`, `RAM[08H] = 55H`

After `POP 40H`:

```text
40H = 55H
SP = 07H
```

---

## Stack During Subroutine Call

When CALL executes → Program Counter saved on stack.

When RET executes → Program Counter restored from stack.

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

# Q4: Instruction Set of 8051

## Definition

An **Instruction Set** is the collection of commands understood by the microcontroller.

---

# A. Arithmetic Instructions

Used for mathematical operations.

---

## ADD

Adds two numbers.

```assembly
ADD A,R1
```

Meaning: `A = A + R1`

---

## ADDC

Addition with carry.

```assembly
ADDC A,R1
```

Used in multi-byte addition.

---

## SUBB

Subtract with borrow.

```assembly
SUBB A,R2
```

Meaning: `A = A - R2 - Borrow`

---

## INC

Increment by 1.

```assembly
INC A
```

---

## DEC

Decrement by 1.

```assembly
DEC R0
```

---

## MUL AB

Multiply A and B.

```assembly
MUL AB
```

Result `A × B` stored in A (low byte) and B (high byte).

---

## DIV AB

Division.

```assembly
DIV AB
```

Quotient → A | Remainder → B

---

# B. Logical Instructions

Used for decision making and bit manipulation.

---

## ANL (AND)

```assembly
ANL A,#0FH
```

---

## ORL (OR)

```assembly
ORL A,#80H
```

---

## XRL (XOR)

```assembly
XRL A,R1
```

---

## CLR

Clear a bit.

```assembly
CLR A
```

---

## CPL

Complement — changes `0 → 1` and `1 → 0`.

```assembly
CPL A
```

---

## RL / RR

Rotate Left / Rotate Right.

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

```assembly
MOV A,#25H
```

Loads 25H into A.

---

## MOVX

Move external memory data.

```assembly
MOVX A,@DPTR
```

---

## MOVC

Move code memory data.

```assembly
MOVC A,@A+DPTR
```

---

## PUSH

Stores data in stack.

```assembly
PUSH 30H
```

---

## POP

Retrieves data from stack.

```assembly
POP 30H
```

---

## XCH

Exchange data.

```assembly
XCH A,R0
```

---

## Classification Table

| Type          | Examples                             |
| ------------- | ------------------------------------ |
| Arithmetic    | ADD, ADDC, SUBB, INC, DEC, MUL, DIV  |
| Logical       | ANL, ORL, XRL, CLR, CPL, RL, RR      |
| Data Transfer | MOV, MOVX, MOVC, PUSH, POP, XCH      |

---

## Keywords

Arithmetic, Logical, Data Transfer, Accumulator, Carry, Borrow, Stack, Rotate.

---

# Q5: Serial Communication in 8051 and Hardware/Software Support

## Introduction

Serial communication transfers data one bit at a time through a single communication channel.

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

| Pin  | Function |
| ---- | -------- |
| P3.0 | RXD      |
| P3.1 | TXD      |

---

## Important Registers

### SBUF Register

Serial Buffer Register. Used for both transmission and reception.

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

### SM0, SM1

Select serial mode.

### REN

Receive Enable. `REN = 1` → Reception enabled.

### TI

Transmit Interrupt Flag. Set when transmission completes.

### RI

Receive Interrupt Flag. Set when reception completes.

---

### PCON Register

Used for baud rate control.

Important bit: `SMOD`

When `SMOD = 1` → Baud rate doubles.

---

# Serial Modes

## Mode 0

* Shift Register Mode
* 8-bit Data
* Fixed Baud Rate

## Mode 1

* 8-bit UART
* Start Bit + Stop Bit
* Variable Baud Rate
* Most commonly used

Frame Format:

```text
Start → 8 Data Bits → Stop
```

## Mode 2

* 9-bit UART
* Fixed Baud Rate

## Mode 3

* 9-bit UART
* Variable Baud Rate

---

# Baud Rate Generation

Usually generated using Timer1, Mode2 (8-bit Auto Reload).

### Baud Rate Formula (Mode 1)

```text
Baud Rate = (2^SMOD / 32) × Timer Overflow Rate
```

---

# Data Transmission Process

```assembly
MOV SBUF,#'A'
```

Process:

```text
Load SBUF → UART Transmits → TI = 1 → Transmission Complete
```

### Transmission Program

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
Data Arrives → Stored in SBUF → RI = 1 → CPU Reads Data
```

### Reception Program

```assembly
WAIT:
JNB RI,WAIT

MOV A,SBUF
CLR RI
```

---

# Serial Interrupt

Interrupt Vector: `0023H`

Flags `TI` and `RI` trigger serial interrupt.

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

---

# Q6: Timer/Counter Programming in 8051 and Time-of-Day Clock

## Definition

Timer/Counter is a hardware module used for:

* Delay generation
* Event counting
* Frequency measurement
* Baud rate generation
* Real-time clock implementation

8051 contains Timer0 and Timer1, each consisting of:

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

GATE: `0` → Software control | `1` → External hardware control

C/T: `0` → Timer | `1` → Counter

M1 M0: Select timer mode.

---

## Timer Modes

### Mode 0: 13-bit Timer
Count Range: `0000H – 1FFFH`

### Mode 1: 16-bit Timer
Count Range: `0000H – FFFFH` | Most commonly used.

### Mode 2: 8-bit Auto Reload
Used for UART Baud Rate Generation.

### Mode 3: Split Timer Mode
Timer0 becomes TH0 and TL0 as independent timers.

---

## TCON Register

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

TF0 = Timer0 Overflow Flag | TF1 = Timer1 Overflow Flag

TR0 = Start Timer0 | TR1 = Start Timer1

---

# Time-of-Day Clock

Displays Hours, Minutes, Seconds using BCD format.

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
4. If seconds = 60 → seconds = 00, increment minutes.
5. If minutes = 60 → minutes = 00, increment hours.
6. If hours = 24 → hours = 00.
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

# Q7: Embedded C Program to Toggle P1.0 Every 10 ms (XTAL = 11.0592 MHz)

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
11.0592 MHz / 12 = 921.6 kHz
```

### Machine Cycle Time

```text
1 / 921.6k = 1.085 µs
```

### Required Counts

```text
10 ms / 1.085 µs ≈ 9216
```

### Initial Timer Value

```text
65536 - 9216 = 56320 = DC00H
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


# UNIT I – Q16(a)

# Traffic Light Control using 8051 Microcontroller

## Introduction

A Traffic Light Control System uses an 8051 microcontroller to control:

* Red Light
* Yellow Light
* Green Light

in a predefined sequence.

---

# Interfacing Diagram

```text
           8051
      +------------+
P1.0 -----> RED LED
P1.1 -----> YELLOW LED
P1.2 -----> GREEN LED
      +------------+
```

(Each LED connected through a current limiting resistor.)

---

# Working Principle

### State 1

```text
RED ON
YELLOW OFF
GREEN OFF
```

Vehicles Stop.

---

### State 2

```text
RED OFF
YELLOW ON
GREEN OFF
```

Prepare to move.

---

### State 3

```text
RED OFF
YELLOW OFF
GREEN ON
```

Vehicles Move.

---

# Flowchart

```text
Start
  |
Red ON
Delay
  |
Yellow ON
Delay
  |
Green ON
Delay
  |
Repeat
```

---

# Embedded C Program

```c
#include <reg51.h>

sbit RED = P1^0;
sbit YELLOW = P1^1;
sbit GREEN = P1^2;

void delay()
{
    int i,j;
    for(i=0;i<500;i++)
        for(j=0;j<1275;j++);
}

void main()
{
    while(1)
    {
        RED = 1;
        YELLOW = 0;
        GREEN = 0;
        delay();

        RED = 0;
        YELLOW = 1;
        GREEN = 0;
        delay();

        RED = 0;
        YELLOW = 0;
        GREEN = 1;
        delay();
    }
}
```

---

# Applications

* Road Junctions
* Railway Crossings
* Industrial Traffic Management

---

# Keywords

8051, Port P1, LED Interfacing, Traffic Control, Delay Routine, Embedded C.

---

# UNIT I – Q16(b)

# Stepper Motor Interfacing with 8051 and Speed/Direction Control

## Introduction

A Stepper Motor converts electrical pulses into precise mechanical rotation.

Characteristics:

* High accuracy
* Precise position control
* No feedback required

Applications:

* CNC Machines
* Printers
* Robotics
* Industrial Automation

---

# Working Principle

Motor rotates by energizing coils in sequence.

Example sequence:

```text
A → B → C → D
```

Each pulse:

```text
One Step Rotation
```

---

# Interfacing Diagram

```text
              8051
         +------------+
P1.0 ----> IN1
P1.1 ----> IN2
P1.2 ----> IN3
P1.3 ----> IN4
         +------------+
                |
             ULN2003
                |
         Stepper Motor
```

### Why ULN2003?

8051 cannot supply sufficient current.

ULN2003 acts as:

```text
Driver Circuit
```

---

# Full Step Sequence

| Step | P1.3 P1.2 P1.1 P1.0 |
|--------|------|------|------|------|
| 1 | 0 | 0 | 0 | 1 |
| 2 | 0 | 0 | 1 | 0 |
| 3 | 0 | 1 | 0 | 0 |
| 4 | 1 | 0 | 0 | 0 |

Hex Values:

```text
01H
02H
04H
08H
```

---

# Clockwise Rotation

```text
01H → 02H → 04H → 08H
```

---

# Anticlockwise Rotation

```text
08H → 04H → 02H → 01H
```

---

# Speed Control

Speed depends on delay between steps.

### Small Delay

```text
Fast Rotation
```

### Large Delay

```text
Slow Rotation
```

---

# Embedded C Program (Clockwise)

```c
#include <reg51.h>

unsigned char step[4]={0x01,0x02,0x04,0x08};

void delay()
{
    int i,j;
    for(i=0;i<200;i++)
        for(j=0;j<1275;j++);
}

void main()
{
    int i;

    while(1)
    {
        for(i=0;i<4;i++)
        {
            P1 = step[i];
            delay();
        }
    }
}
```

---

# Reverse Direction Program Logic

Use:

```c
for(i=3;i>=0;i--)
```

instead of:

```c
for(i=0;i<4;i++)
```

---

# Advantages

* Accurate positioning
* Simple control
* Good repeatability

---

# Applications

* Robotics
* CNC Machines
* Camera Positioning Systems
* Medical Equipment

---

# Keywords

Stepper Motor, ULN2003, Full Step Mode, Clockwise Rotation, Anticlockwise Rotation, Driver Circuit, Speed Control, Position Control.


# UNIT I – Q16(a)

# Timer0 Auto Reload Mode and Generation of 4 kHz Square Wave on P1.3

## Introduction

8051 contains two timers:

```text
Timer0
Timer1
```

Timer0 can operate in:

- Mode 0 (13-bit)
    
- Mode 1 (16-bit)
    
- Mode 2 (8-bit Auto Reload)
    
- Mode 3 (Split Timer)
    

This question uses:

```text
Mode 2 (Auto Reload)
```

---

# Special Function Registers Used

## TMOD Register

Used to select timer mode.

Format:

```text
GATE C/T M1 M0 | GATE C/T M1 M0
 Timer1             Timer0
```

For:

```text
Timer0 Mode 2
```

```text
M1=1
M0=0
```

TMOD:

```text
00000010B
=
02H
```

---

## TCON Register

```text
TF1 TR1 TF0 TR0 IE1 IT1 IE0 IT0
```

Important bits:

### TR0

```text
Timer0 Run Control
```

### TF0

```text
Timer0 Overflow Flag
```

---

## TH0

Stores reload value.

---

## TL0

Actual counting register.

---

# Frequency Calculation

Given:

```text
XTAL = 12 MHz
```

Machine Cycle:

```text
12 MHz / 12
=
1 MHz
```

Therefore:

```text
1 Machine Cycle = 1 µs
```

---

Required Frequency:

```text
4 kHz
```

Period:

[  
T=\frac{1}{4000}  
]

[  
T=250 \mu s  
]

Square wave requires toggle every:

[  
125\mu s  
]

---

Required Counts:

```text
125 Counts
```

---

Timer Reload Value

```text
256 - 125
=
131
=
83H
```

Load:

```text
TH0 = 83H
```

---

# Flowchart

```text
Start
  |
TMOD = 02H
  |
TH0 = 83H
  |
TR0 = 1
  |
Wait TF0
  |
Toggle P1.3
  |
Clear TF0
  |
Repeat
```

---

# Assembly Program

```assembly
MOV TMOD,#02H

MOV TH0,#83H
MOV TL0,#83H

SETB TR0

BACK:

JNB TF0,BACK

CLR TF0

CPL P1.3

SJMP BACK
```

---

# Working

1. Timer0 configured in Mode 2.
    
2. TH0 loaded with 83H.
    
3. TL0 starts counting.
    
4. After 125 µs overflow occurs.
    
5. TF0 becomes 1.
    
6. P1.3 toggles.
    
7. TH0 automatically reloads into TL0.
    
8. Process repeats.
    

---

# Advantages of Auto Reload Mode

- No need to reload timer repeatedly.
    
- Less CPU overhead.
    
- Suitable for waveform generation.
    
- Used in baud-rate generation.
    

---

# Keywords

TMOD, TCON, TH0, TL0, TF0, TR0, Mode 2, Auto Reload, Square Wave Generation, Timer0.

---

# UNIT I – Q16(a)

# Key Bounce and Keyboard Interfacing with 8051

## Introduction

A keyboard is an input device used to enter data into a microcontroller system.

Types:

- Individual Switch Keyboard
    
- Matrix Keyboard
    

---

# Key Bounce

## Definition

When a key is pressed, the contacts do not settle immediately.

They make and break contact multiple times before stabilizing.

This phenomenon is called:

```text
Key Bounce
```

---

## Effect

Single key press may be interpreted as:

```text
Multiple Key Presses
```

---

## Key Bounce Illustration

```text
Ideal:

_____|‾‾‾‾‾


Actual:

___|_|_|_|‾‾‾
```

---

# Elimination of Key Bounce

## 1. Hardware Debouncing

Using:

- RC Circuit
    
- Schmitt Trigger
    

```text
Switch
  |
 RC Network
  |
8051 Input
```

---

## 2. Software Debouncing

After detecting key press:

```text
Wait 10–20 ms
```

then verify again.

Most common technique.

---

# Matrix Keyboard Interfacing

A matrix keyboard reduces the number of I/O pins.

Example:

```text
4 × 4 Keyboard
```

Contains:

```text
16 Keys
```

using only:

```text
8 Pins
```

---

# Matrix Structure

```text
      C0 C1 C2 C3
       |  |  |  |

R0 ----o--o--o--o

R1 ----o--o--o--o

R2 ----o--o--o--o

R3 ----o--o--o--o
```

Rows:

```text
R0-R3
```

Columns:

```text
C0-C3
```

---

# Interfacing with 8051

```text
Rows    → P1.0-P1.3

Columns → P1.4-P1.7
```

---

# Row-Column Scanning Technique

## Step 1

Make all rows HIGH.

---

## Step 2

Ground one row.

Example:

```text
R0 = 0
```

---

## Step 3

Read column inputs.

If:

```text
C2 = 0
```

then:

```text
Key at R0,C2 pressed
```

---

## Step 4

Repeat for all rows.

---

# Flowchart

```text
Start
  |
Scan Rows
  |
Key Pressed?
 /       \
No       Yes
 |         |
Repeat  Debounce
           |
       Identify Key
           |
         Process
```

---

# Advantages of Matrix Keyboard

- Fewer I/O pins
    
- Low cost
    
- Easy scanning
    
- Widely used
    

---

# Applications

- Calculators
    
- ATMs
    
- Industrial Panels
    
- Access Control Systems
    

---

# Keywords

Key Bounce, Debouncing, Matrix Keyboard, Row Scanning, Column Scanning, RC Circuit, Software Debounce, Key Detection.

---

That completes **all UNIT I Part C unique questions**. Next unit with Part C content is **UNIT III: Sensor & Actuator Interfacing for Real-Time Industrial Applications**.