# CS3691 — Embedded Systems & IoT — Complete Teaching Plan
> A narrative-first, story-driven curriculum that builds from first principles to career-applicable depth.
> Each section is a self-contained teaching session. Follow the order — later sections depend on earlier ones.

---

## How to Use This Plan

- Follow the numbered order strictly. Skipping ahead breaks the mental model.
- Each section ends with a **"Why This Matters for the Exam"** and a **"Why This Matters for Your Career"** note.
- Sections marked `[NUMERICAL]` require you to work through problems on paper, not just read.
- Estimated depth: **exam-ready + junior-developer-ready**.

---

# PART 1 — The 8-Bit Microcontroller: Understanding the Brain

## [[1.1 — What Is a Microcontroller]] (And How Is It Different From a Processor)?

- The difference between a microprocessor (just a CPU), a microcontroller (CPU + memory + peripherals on one chip), and a computer (full system with OS)
- Why "embedded" means the processor is embedded inside a larger system that isn't a general-purpose computer
- The 8051 as the canonical 8-bit microcontroller: why it's taught, why it's still relevant (billions of descendants in real hardware today)
- What "8-bit" means: the ALU processes 8 bits at a time; data bus is 8 bits wide; registers are 8-bit
- The core question every microcontroller answers: how do I read inputs from the physical world, process them, and control outputs — with minimal resources?

**Why This Matters for the Exam:** Every Unit 1 question assumes you know this vocabulary.
**Why This Matters for Your Career:** All IoT work eventually touches a microcontroller — even if you write Python on a Raspberry Pi, the sensors it reads are run by 8-bit cousins of the 8051.

---

## [[1.2 — 8051 Architecture What's Inside the Chip]]

- The CPU core: ALU (Arithmetic Logic Unit), Accumulator (A), B register, Program Counter (PC), Stack Pointer (SP)
- Memory organisation — two separate address spaces (Harvard Architecture):
  - Internal RAM: 128 bytes (8051), split into working register banks (32 bytes), bit-addressable area (16 bytes), and general-purpose RAM (80 bytes)
  - Special Function Register (SFR) area: 128 bytes of addresses mapped to hardware controls (ports, timers, serial, interrupts)
  - Internal ROM: 4KB program memory (on-chip)
  - External memory: up to 64KB program memory, 64KB data memory via address/data buses
- The four register banks (Bank 0–3): each has 8 registers R0–R7; PSW bits RS0 and RS1 select the active bank
- The PSW (Program Status Word): C (carry), AC (auxiliary carry), F0 (user flag), RS1, RS0, OV (overflow), P (parity)
- The DPTR (Data Pointer): 16-bit register used to address external memory; formed from DPH and DPL
- Harvard vs Von Neumann: 8051 uses Harvard (separate program and data buses) — why this matters for speed and security

**Why This Matters for the Exam:** Architecture questions are guaranteed. Draw the block diagram from memory, label each component.
**Why This Matters for Your Career:** Understanding register banks and SFRs is how you debug embedded firmware when there's no OS to help you.

---

## 1.3 — 8051 Instruction Set and Programming

### [[1.3.1 — Addressing Modes]]

Before instructions make sense, you need to know how operands are specified:

- **Immediate**: the operand is a constant in the instruction itself. `MOV A, #55H` — load the literal value 55 hex into A
- **Register**: the operand is a register. `MOV A, R2` — copy R2 into A
- **Direct**: the operand is a RAM address. `MOV A, 30H` — copy whatever is at address 30H into A
- **Indirect (Register Indirect)**: the operand is the memory address *stored* in a register. `MOV A, @R0` — R0 holds an address; load from that address into A. The `@` symbol means "indirect"
- **Indexed**: base register plus offset. `MOVC A, @A+DPTR` — used for reading from program memory tables

### [[1.3.2 — Instruction Categories and Key Instructions]]

- **Data Transfer**: MOV, MOVX (external data memory), MOVC (program memory/code), XCH (exchange), XCHD, PUSH, POP, SWAP
- **Arithmetic**: ADD, ADDC (add with carry), SUBB (subtract with borrow), MUL AB (A × B → BA), DIV AB (A ÷ B → A remainder B), INC, DEC, DA A (decimal adjust)
- **Logical**: ANL, ORL, XRL, CLR, CPL, RL, RR, RLC, RRC (rotate with/without carry)
- **Branch/Control**:
  - Unconditional: LJMP (long), SJMP (short), AJMP (absolute), LCALL, ACALL, RET, RETI
  - Conditional: JZ (jump if A=0), JNZ, JC (jump if carry), JNC, JB (jump if bit set), JNB, JBC (jump and clear bit), CJNE (compare and jump if not equal), DJNZ (decrement and jump if not zero)
- **Bit Operations**: SETB, CLR, CPL on individual bits — the 8051's unique bit-addressability is a core feature
- Why DJNZ is your friend for loops: `MOV R7, #10` then `DJNZ R7, loop_label` — no separate counter check needed

**[NUMERICAL]**: Write an 8051 assembly program to add two 8-bit numbers stored at memory addresses 30H and 31H, and store the result at 32H. Handle carry into address 33H.

**Why This Matters for the Exam:** Instruction set questions will ask you to trace execution or write short programs. Know the key instructions cold.

---

## [[1.4 — Parallel Ports Programming IO]]

- The 8051 has four 8-bit parallel ports: P0, P1, P2, P3 (SFR addresses 80H, 90H, A0H, B0H)
- P0: bidirectional I/O but also used as low-byte address/data bus when accessing external memory — needs external pull-up resistors
- P1: dedicated general-purpose I/O
- P2: general-purpose but doubles as high-byte address bus for external memory
- P3: has alternate functions (serial RXD/TXD, INT0/INT1 external interrupts, T0/T1 timer inputs, WR/RD for external memory)
- How output works: writing a 1 to a port bit turns on the internal pull-up (high); writing 0 turns off pull-up and pulls low
- How input works: you must first write 1 to the port bit (configure as input), then read it — writing 0 "locks" the pin low and you can't read external input
- Quasi-bidirectional I/O: what it means, why P0 is different (no internal pull-up)
- LED control example: connect LED to P1.0; MOV P1, #0FEH turns on LED at bit 0 (active low)
- Switch reading example: connect switch to P2.0; read the bit and branch on it

**[NUMERICAL]**: Write 8051 code to read a switch on P3.2. If the switch is pressed (reads 0), turn on LED on P1.0; else turn it off.

**Why This Matters for the Exam:** Port programming is in almost every practical question.
**Why This Matters for Your Career:** Every embedded hardware project is, at some level, configuring pins and reading/writing them.

---

## 1.5 — Timers and Serial Port

### [[1.5.1 — Timers Counters]]

- The 8051 has two 16-bit timers: Timer 0 and Timer 1
- Key SFRs: TMOD (Timer Mode register), TCON (Timer Control register), TH0/TL0, TH1/TL1
- TMOD structure: Gate bit, C/T bit (counter vs timer), M1/M0 (mode select) — upper nibble for T1, lower for T0
- Four modes:
  - Mode 0: 13-bit timer (legacy)
  - Mode 1: 16-bit timer — most common for generating delays
  - Mode 2: 8-bit auto-reload — most common for UART baud rate generation
  - Mode 3: split timer (T0 only)
- How to calculate timer reload value: `Reload = 65536 - (Delay × Clock / 12)` for Mode 1 (machine cycle = 12 oscillator cycles)
- Counter mode (C/T=1): counts external pulses on T0 (P3.4) or T1 (P3.5)
- TF0/TF1 overflow flags in TCON; TR0/TR1 run bits to start/stop

**[NUMERICAL]**: For a 12 MHz crystal, calculate TH0 and TL0 to generate a 10 ms delay in Mode 1.
Formula: Machine cycle = 1 µs. 10,000 µs delay. Count = 10,000. Reload = 65536 − 10000 = 55536 = D8F0H. TH0 = D8H, TL0 = F0H.

### 1.5.2 — Serial Port (UART)

- The 8051 has a built-in UART: full-duplex, uses P3.0 (RXD) and P3.1 (TXD)
- SCON (Serial Control register): SM0/SM1 (mode), SM2, REN (receive enable), TB8, RB8, TI (transmit interrupt flag), RI (receive interrupt flag)
- Four serial modes:
  - Mode 0: synchronous shift register (rarely used)
  - Mode 1: 8-bit UART with variable baud rate — standard for all communication
  - Mode 2: 9-bit fixed baud rate
  - Mode 3: 9-bit variable baud rate
- Baud rate generation: Timer 1 in Mode 2 (auto-reload); SMOD bit in PCON doubles baud rate
- Baud rate formula: `Baud = (2^SMOD × Clock) / (384 × (256 − TH1))`
- Transmitting a byte: write to SBUF; poll TI flag until set; clear TI manually
- Receiving a byte: set REN=1; poll RI flag until set; read from SBUF; clear RI

**[NUMERICAL]**: For 12 MHz crystal, calculate TH1 for 9600 baud. `TH1 = 256 − (12,000,000 / (384 × 9600)) = 256 − 3.25 ≈ 253 = FDH`. (Note: slight error; real systems use 11.0592 MHz crystal specifically to get exact baud rates.)

**Why This Matters for the Exam:** Timer calculations and serial communication are high-frequency numerical questions.
**Why This Matters for Your Career:** UARTs are how 80% of embedded sensors talk to microcontrollers. You'll use this everywhere.

---

## 1.6 — Interrupt Handling

- What an interrupt is: an asynchronous signal that causes the CPU to stop what it's doing, save its state, run a special routine (ISR — Interrupt Service Routine), and resume
- Why interrupts exist: the alternative (polling) wastes CPU cycles constantly checking a condition; interrupts are event-driven
- The 8051 has 5 interrupt sources: INT0 (external), Timer 0 overflow, INT1 (external), Timer 1 overflow, Serial port (RI or TI)
- Interrupt enable register (IE): EA (global enable), ES (serial), ET1, EX1, ET0, EX0
- Interrupt priority register (IP): same bits — high priority (1) vs low priority (0)
- Interrupt vector addresses (memorise these):
  - INT0: 0003H
  - Timer 0: 000BH
  - INT1: 0013H
  - Timer 1: 001BH
  - Serial: 0023H
- ISR structure: code placed at the vector address; ends with RETI (return from interrupt, also re-enables interrupts)
- Interrupt latency: at least 3 machine cycles to respond
- Priority and nesting: a high-priority ISR can interrupt a low-priority ISR; same priority cannot interrupt each other
- IE bit manipulation to enable Timer 0 interrupt: `MOV IE, #82H` sets EA and ET0

**[NUMERICAL]**: Write the ISR skeleton in assembly for Timer 0 overflow, which toggles P1.0 on each overflow.

**Why This Matters for the Exam:** Interrupt vector addresses are asked directly. ISR writing is a common short-answer question.
**Why This Matters for Your Career:** Interrupt-driven I/O is the foundation of all real-time embedded systems.

---

# PART 2 — Embedded C Programming

## 2.1 — Memory and I/O Devices Interfacing

### 2.1.1 — Memory Interfacing

- Why external memory is needed: internal 4KB ROM and 128B RAM isn't enough for most real systems
- Address space: 8051 can address 64KB external program memory (PSEN signal) and 64KB external data memory (RD/WR signals)
- Multiplexed address/data bus on P0: ALE (Address Latch Enable) signal triggers an external latch (74HC373) to capture the address before P0 is reused as a data bus
- Memory-mapped I/O: peripherals assigned addresses in the data memory space, accessed with MOVX instruction
- How to expand: EPROM or Flash for program memory, SRAM for data memory, connected via address decoder

### 2.1.2 — Common I/O Device Interfacing

- LCD (16×2): understand pin interface (RS, RW, EN, D0-D7); command mode vs data mode; 4-bit vs 8-bit interface; initialisation sequence
- Keypad (4×4 matrix): row-column scanning to detect which key is pressed; avoiding bounce with software debounce
- ADC (Analog-to-Digital Converter): how to interface ADC0804 with 8051; read sequence (WR pulse, wait for INTR, RD pulse, read data port)
- DAC (Digital-to-Analog Converter): writing a digital value to DAC0808 to generate analog output voltage; staircase waveform generation
- Stepper motor: full-step vs half-step sequence; writing the step pattern to a port via ULN2003 driver
- Relay: transistor driver circuit; how the microcontroller switches high-current loads safely via isolation

**Why This Matters for the Exam:** Interfacing is a drawing + explanation question type. Know the signal names and timing for LCD and ADC at minimum.

---

## 2.2 — Programming Embedded Systems in C

- Why C instead of assembly: portability, readability, maintainability; only lose a little performance
- The critical differences between embedded C and desktop C:
  - No OS, no standard library functions that rely on OS (printf, malloc are unavailable or unreliable)
  - Explicit hardware register access using SFR declarations
  - Memory is tight — avoid dynamic allocation; use static arrays
  - Volatile keyword: declare hardware registers and ISR-modified variables as `volatile` to prevent compiler optimisation eliminating reads
- `sfr P1 = 0x90;` — how SFRs are declared in embedded C (Keil / SDCC syntax)
- `sbit LED = P1^0;` — declaring individual bits
- Bit manipulation without bit-band: using masks — `P1 |= 0x01` (set bit 0), `P1 &= ~0x01` (clear bit 0), `P1 ^= 0x01` (toggle bit 0)
- Delay function implementation: software delay loops; why this is inaccurate but acceptable for non-critical timing
- Port I/O in C: writing to and reading from port SFRs directly
- Common program structures in embedded systems: superloop (infinite while loop with polling), state machines (handle different modes), interrupt + flag pattern (ISR sets a flag, main loop checks it)

**[NUMERICAL]**: Rewrite the toggle-LED-on-switch-press example from 1.4 in Embedded C.

**Why This Matters for the Exam:** C code snippets are asked for in Unit 2 questions. Know volatile and bit manipulation cold.
**Why This Matters for Your Career:** Almost all embedded firmware is written in C today. This is directly career-applicable.

---

## 2.3 — Need for RTOS

- The superloop problem: as your embedded system does more things, polling every sensor and device in a loop creates problems — fast events get missed, slow operations block everything else
- What a Real-Time Operating System (RTOS) provides:
  - Task scheduling: run multiple logical tasks apparently simultaneously
  - Determinism: a hard real-time guarantee that a task will execute within a bounded time
  - Resource management: mutexes, semaphores for shared hardware access
  - Time management: delay tasks without blocking others
- Hard real-time vs soft real-time: missing a deadline is catastrophic (airbag controller) vs inconvenient (media player)
- Popular RTOS options: FreeRTOS (open-source, used everywhere), Zephyr, VxWorks, ThreadX, RTEMS
- RTOS vs bare metal (superloop): when to use which — use RTOS when you have 3+ independent tasks with different timing requirements
- The RTOS overhead cost: scheduler takes some CPU time and RAM for task stacks; not appropriate for tiny 8-bit MCUs with <1KB RAM

**Why This Matters for the Exam:** The "need for RTOS" is a direct exam question — write 5–6 points with reasons.
**Why This Matters for Your Career:** FreeRTOS is used in billions of devices; IoT firmware almost always runs on it.

---

## 2.4 — Multiple Tasks and Processes

- Task (in RTOS) = a function that runs as if it had its own CPU; has its own stack, its own state
- Process vs task in embedded context: most RTOS systems don't have memory-protected processes like Linux — tasks share address space; it's the programmer's responsibility not to corrupt each other's data
- Task states in RTOS:
  - **Running**: currently executing on the CPU
  - **Ready**: able to run but waiting for CPU (another task is running)
  - **Blocked**: waiting for an event (delay, semaphore, queue message)
  - **Suspended**: explicitly suspended, won't run until resumed
- State transition diagram: Ready ↔ Running (scheduler), Running → Blocked (wait for resource), Blocked → Ready (resource available), Running/Ready → Suspended (explicit call)
- Task creation: in FreeRTOS — `xTaskCreate(taskFunction, "TaskName", stackSize, params, priority, &handle)`
- Task communication mechanisms:
  - **Queues**: producer sends messages to a queue; consumer receives from it; FIFO; thread-safe
  - **Semaphores**: binary (signal that event occurred) or counting; mutexes are binary semaphores with priority inheritance
  - **Event groups**: bit flags; task waits for specific bits to be set by other tasks

**Why This Matters for the Exam:** Task state diagrams and FreeRTOS API names are asked. Draw the state transition diagram from memory.

---

## 2.5 — Context Switching

- The fundamental question: how does a CPU that can only do one thing at a time appear to run multiple tasks?
- Context = everything the CPU needs to resume a task exactly where it left off: all registers (PC, SP, ACC, PSW, general registers), current stack contents
- Context switch steps:
  1. Scheduler interrupts current task (usually via a timer interrupt — the tick)
  2. Save current task's context to its Task Control Block (TCB)
  3. Select the next task to run
  4. Restore next task's context from its TCB
  5. Resume execution at next task's saved PC
- Task Control Block (TCB): the data structure storing a task's context, stack pointer, priority, state, and name
- Tick interrupt: a hardware timer fires at a fixed frequency (e.g., every 1 ms) — this is the RTOS heartbeat; every tick, the scheduler decides whether to switch tasks
- Context switch overhead: saving/restoring registers takes time; this is why RTOS adds latency compared to bare metal
- Preemptive vs cooperative scheduling:
  - Preemptive: scheduler forcibly takes CPU away from running task on tick
  - Cooperative: tasks voluntarily yield the CPU (call `taskYield()` or block); no forced preemption

**Why This Matters for the Exam:** "Explain context switching with steps" is a classic 10-mark question. Know the TCB, the steps, and the tick.

---

## 2.6 — Priority-Based Scheduling Policies

- Why priority: not all tasks are equally important; the airbag task must preempt the display update task
- Static priority: assigned at creation, never changes; most RTOS systems use this
- Dynamic priority: adjusted at runtime (e.g., priority ageing to avoid starvation)
- **Preemptive Priority Scheduling** (default in FreeRTOS): highest ready task always runs; lower-priority task is preempted immediately when a higher-priority task becomes ready
- **Round Robin**: among equal-priority tasks, each gets one time slice (tick) before yielding to the next; prevents starvation among peers
- **Rate Monotonic Scheduling (RMS)**: for periodic tasks — higher frequency → higher priority; provably optimal for static priorities; utilisation bound: sum of (Ci/Ti) ≤ n(2^(1/n) − 1) ≈ 0.693 for large n
- **Priority Inversion**: a high-priority task is blocked waiting for a resource held by a low-priority task, while a medium-priority task runs — the high-priority task effectively has medium priority
- **Priority Inheritance**: solution to priority inversion — temporarily elevate the low-priority task holding the resource to the priority of the waiting high-priority task; release back after resource is released
- **Deadline Monotonic Scheduling (DMS)**: priority based on deadline rather than period; handles tasks where period ≠ deadline

**[NUMERICAL]**: Three tasks: T1 (period 20ms, exec 3ms), T2 (period 5ms, exec 1ms), T3 (period 10ms, exec 2ms). Verify schedulability under RMS. Utilisation = 3/20 + 1/5 + 2/10 = 0.15 + 0.2 + 0.2 = 0.55 < 0.693. ✓ Schedulable.

**Why This Matters for the Exam:** Priority inversion + inheritance explanation and RMS schedulability check are common 8–10 mark questions.
**Why This Matters for Your Career:** This is the theory behind why real-time systems behave correctly — or fail catastrophically.

---

# PART 3 — IoT Concepts and Arduino Programming

## 3.1 — What Is IoT? (First Principles)

- The core idea: everyday physical objects embedded with sensors, software, and connectivity that allows them to collect and exchange data — without requiring direct human interaction
- The four components of every IoT system:
  1. **Things**: physical devices with sensors/actuators
  2. **Connectivity**: the network linking devices to each other and to the cloud
  3. **Data processing**: edge computing, gateways, or cloud platforms that make sense of the data
  4. **User interface**: dashboards, mobile apps, or automated actions that deliver value
- IoT devices vs computers:
  - Computers: general-purpose, high resources (GB RAM, GHz CPU, full OS), keyboard+screen, running many applications
  - IoT devices: single-purpose, constrained resources (KB–MB RAM, MHz CPU, no OS or RTOS), sensor/actuator interface, ultra-low power
  - The constraints of IoT: power (battery-operated, years of life), cost (under $1 per unit), size (embedded in objects), connectivity (must survive intermittent networks)
- IoT configurations (deployment patterns):
  - **Device-to-Device (D2D)**: two devices communicate directly (Bluetooth, ZigBee); no cloud; low latency
  - **Device-to-Cloud**: device sends data directly to cloud (via WiFi or cellular); simplest architecture
  - **Device-to-Gateway**: constrained devices talk to a gateway via short-range protocol; gateway handles cloud communication; common in industrial IoT
  - **Back-End Data Sharing**: device data flows to a backend that shares it with third-party services via APIs
- The IoT stack: Physical → Link → Network → Transport → Application (same OSI model, different protocols at each layer)

**Why This Matters for the Exam:** "Explain IoT configurations with diagram" is a guaranteed question type.

---

## 3.2 — Introduction to Arduino

- What Arduino is: an open-source electronics platform consisting of a microcontroller board + a development environment (IDE) + a set of programming conventions
- Why Arduino succeeded: removed the barrier between programmers and hardware — no need to learn assembly, no complex toolchain setup, cheap boards, vast community
- The Arduino design philosophy: expose hardware capabilities through simple abstractions (digitalRead, digitalWrite, analogRead) so makers and students can build without deep electronics knowledge
- The underlying chip: most Arduinos use Atmel AVR microcontrollers (ATmega328P on the Uno, ATmega2560 on the Mega); completely different architecture from 8051 but same embedded concepts apply
- Key Arduino board types:
  - **Uno**: the standard; ATmega328P; 14 digital I/O, 6 analog inputs, 6 PWM; 5V logic; 16 MHz; 32KB Flash, 2KB SRAM — the right board to learn on
  - **Mega 2560**: ATmega2560; 54 digital I/O, 16 analog inputs; for projects needing more pins
  - **Nano**: compact Uno-equivalent; same chip, smaller form factor; good for space-constrained projects
  - **Pro Mini**: even smaller; no USB chip; needs FTDI programmer; for cost/size-optimised deployments
  - **Leonardo**: ATmega32U4; has native USB — can emulate keyboard/mouse
  - **Due**: ARM Cortex-M3 (not AVR); 3.3V logic; faster; for computationally intensive work
  - **ESP8266 / ESP32-based boards (NodeMCU)**: technically not "official" Arduino but fully compatible; have built-in WiFi — the dominant choice for IoT projects today
- Power supply options: USB (from computer), barrel jack (7–12V), Vin pin, 5V pin directly

---

## 3.3 — Arduino Toolchain and Programming Structure

- **Arduino IDE**: write code (Sketch) → compile → upload via USB → runs on board
- **Compilation pipeline**: C++ source → avr-gcc compiler → hex file → avrdude upload tool → flash memory on chip
- **avr-gcc**: the actual compiler; generates AVR machine code; Arduino IDE wraps it
- Sketch anatomy: every Arduino program has exactly two mandatory functions:
  - `setup()`: runs once after power-on or reset; used to initialise pins, serial, and peripherals
  - `loop()`: runs repeatedly forever after setup() completes; this is your superloop
- Sketch compilation: Arduino wraps your sketch in a main() that calls setup() once, then calls loop() in an infinite loop; you never write main()
- **Pins**:
  - Digital pins: `pinMode(pin, INPUT/OUTPUT/INPUT_PULLUP)`, `digitalRead(pin)` returns HIGH/LOW, `digitalWrite(pin, HIGH/LOW)`
  - Analog pins (A0–A5): `analogRead(pin)` — 10-bit ADC — returns 0 to 1023 (maps 0V–5V)
  - PWM pins (marked ~): `analogWrite(pin, 0–255)` — generates PWM; duty cycle proportional to value; used for motor speed, LED dimming
  - Serial RX/TX: D0 and D1 — connected to USB chip; avoid using as GPIO if using Serial
- `Serial.begin(9600)` — initialise UART at baud rate; `Serial.println("hello")` — print to monitor; `Serial.read()` — read incoming byte
- `delay(ms)` — blocks execution for ms milliseconds; stops everything including interrupts (mostly); use sparingly
- `millis()` — returns time since power-on in ms; use for non-blocking timing: `if (millis() - lastTime > interval) { ... lastTime = millis(); }`
- `attachInterrupt(digitalPinToInterrupt(pin), ISR_function, RISING/FALLING/CHANGE)` — hardware interrupt on Uno on pins 2 and 3 only

**Why This Matters for the Exam:** Sketch structure and pin functions are the basis of all practical questions in Unit 3.
**Why This Matters for Your Career:** The millis() pattern for non-blocking timing is used in every real Arduino project — never use delay() in production code.

---

## 3.4 — Sensors, Actuators, and Shields

### Sensors (input devices — physical world → digital)

- **Temperature/Humidity**: DHT11 (digital, ±2°C, ±5% RH, single-wire protocol), DHT22 (better accuracy), DS18B20 (1-Wire, waterproof)
- **Distance/Proximity**: HC-SR04 (ultrasonic, 2cm–400cm; measures echo pulse duration), IR sensor (reflective, short range)
- **Light**: LDR (photoresistor — analog, resistance decreases with light), BH1750 (I2C digital lux sensor)
- **Motion**: PIR sensor (detects infrared radiation change from warm bodies; digital HIGH when motion detected)
- **Gas**: MQ-series sensors (analog output; requires calibration; measures concentration in ppm)
- **Pressure/Altitude**: BMP180/BMP280 (I2C, barometric pressure and temperature, used to estimate altitude)
- **Accelerometer/Gyroscope**: MPU-6050 (I2C, 6-DOF: 3-axis accel + 3-axis gyro — used in motion detection, balancing robots)

### Actuators (output devices — digital → physical world)

- **LEDs**: via current-limiting resistor (typically 220–470Ω); `digitalWrite(pin, HIGH)` to turn on
- **DC Motor**: needs L293D or L298N motor driver (H-bridge) — Arduino can't supply enough current directly; direction controlled by two digital pins, speed by PWM
- **Servo Motor**: `#include <Servo.h>`; `servo.attach(pin)`; `servo.write(angle)` — PWM encoded angle 0–180°; signal wire to digital pin
- **Buzzer**: piezoelectric buzzer on digital pin; `tone(pin, frequency, duration)` for musical notes
- **Relay**: mechanical switch controlled by small current; allows Arduino to switch mains voltage; driven via transistor

### Shields

- A shield is a PCB that plugs onto Arduino headers and adds a specific capability with no wiring needed
- Common shields: Ethernet Shield (W5100 chip), WiFi Shield (replaced by ESP8266), Motor Shield (L293D driver), GSM Shield (SIM900), SD Card Shield, Sensor Shield (provides GND/VCC alongside each signal pin for direct sensor connection)
- The importance of shield pinouts: know which Arduino pins a shield uses so you avoid conflicts in multi-shield setups

**Why This Matters for the Exam:** Sensor interfacing — connecting DHT11 or HC-SR04 and writing the sketch — is a common practical question.
**Why This Matters for Your Career:** These are the physical building blocks of every real IoT project.

---

# PART 4 — IoT Communication and Open Platforms

## 4.1 — IoT Communication Models and APIs

- Why IoT has many communication models: different use cases need different trade-offs in latency, power, bandwidth, range, and cost
- The four IoT communication models (as per IoT Forum):
  1. **Request-Response**: client sends a request; server responds; synchronous; classic HTTP model; good for reading sensor values on demand
  2. **Publish-Subscribe (Pub-Sub)**: publishers emit messages on topics; broker routes them to all subscribers; decoupled, async; good for event-driven sensor data (MQTT is the king here)
  3. **Push-Pull (Pipeline)**: a work distributor pushes tasks to a queue; workers pull and process; good for distributed processing
  4. **Exclusive Pair**: persistent bidirectional connection between client and server; WebSockets; good for real-time control
- IoT APIs:
  - **REST API** (HTTP): simple, stateless, JSON over TCP; good for configuration and dashboard calls; too heavy for constrained devices
  - **MQTT** (Message Queuing Telemetry Transport): publish-subscribe; lightweight (2-byte header); designed for constrained devices; TCP-based; QoS levels 0 (at most once), 1 (at least once), 2 (exactly once)
  - **CoAP** (Constrained Application Protocol): like REST but UDP-based and binary; for devices where TCP overhead is too much
  - **AMQP**: Advanced Message Queuing Protocol; enterprise messaging; heavier than MQTT; used in industrial IoT

---

## 4.2 — IoT Communication Protocols

### 4.2.1 — Bluetooth and Bluetooth Low Energy (BLE)

- Classic Bluetooth: 2.4 GHz ISM band; range ~10m; point-to-point; pairing required; high power
- **BLE** (Bluetooth 4.0+): optimised for devices that send small amounts of data infrequently; duty-cycled radio; advertise/scan model; GATT profile (services and characteristics) for structured data exchange
- BLE vs Classic: BLE uses ~100× less power in sleep; not designed for streaming audio; ideal for sensors, wearables
- HC-05/HC-06 modules: Classic Bluetooth serial bridge for Arduino; transparently sends/receives UART data over Bluetooth
- Use cases: short-range sensor data, wearable health monitoring, indoor positioning (BLE beacons), smart locks

### 4.2.2 — WiFi (IEEE 802.11)

- 2.4 GHz (b/g/n) and 5 GHz (a/n/ac/ax) bands; infrastructure mode (connect to access point) vs AP mode (device creates its own network)
- High bandwidth, moderate power — good for devices with mains power or large batteries
- ESP8266 (ESP-01, NodeMCU): $1–$2 WiFi SoC; Xtensa LX106 CPU; 80 MHz; 1MB Flash; AT command mode or full SDK/Arduino support; the breakthrough chip that made WiFi IoT affordable
- ESP32: successor to ESP8266; dual-core; also has Bluetooth + BLE; 520KB SRAM; more peripherals
- Connecting to cloud from ESP8266: WiFiClient + HTTP, or PubSubClient for MQTT, or using platform-specific SDKs (ThingSpeak, Blynk, AWS IoT)

### 4.2.3 — ZigBee (IEEE 802.15.4)

- 2.4 GHz (also 915 MHz, 868 MHz); low power, low data rate (250 kbps at 2.4 GHz), short range (10–100m)
- Key feature: **mesh networking** — ZigBee devices form a self-healing mesh; data hops from device to device to reach its destination; enables networks covering large areas
- Three device roles: Coordinator (one per network, forms/manages the network), Router (extends range, routes packets), End Device (leaf node, can sleep; just sends data)
- Not suitable for high-bandwidth applications; ideal for building automation, smart meters, home automation
- XBee modules: popular ZigBee hardware; serial interface to microcontroller

### 4.2.4 — GPS (Global Positioning System)

- Satellite-based positioning: GPS receiver on IoT device listens to signals from 4+ satellites and triangulates position
- GPS modules for IoT: NEO-6M (common, cheap, UART interface, outputs NMEA sentences)
- NMEA sentences: `$GPGGA` (position, altitude, fix quality), `$GPRMC` (position, speed, date) — text-based, parse the CSV fields
- TTFF (Time to First Fix): can take 30+ seconds cold start; warm start faster
- Power consideration: GPS receivers draw 20–40 mA when active — significant for battery devices
- Use cases: asset tracking, vehicle telematics, precision agriculture

### 4.2.5 — GSM / GPRS Modules

- GSM (Global System for Mobile Communications): cellular voice and SMS; works everywhere there's mobile coverage
- GPRS (General Packet Radio Service): packet data over GSM; older but still used in remote IoT deployments where there's no WiFi
- SIM800L / SIM900: popular GSM/GPRS modules for Arduino; UART interface; AT command set
- AT commands: `AT` (check), `AT+CMGS` (send SMS), `AT+CGATT` (attach to GPRS), HTTP commands for data upload
- Use cases: remote monitoring (weather stations, agricultural sensors), vehicle tracking — anywhere without WiFi infrastructure

**Why This Matters for the Exam:** Protocol comparison table (ZigBee vs Bluetooth vs WiFi) is almost certain. Know range, power, topology, and use case for each.

---

## 4.3 — Raspberry Pi: Open Platform

### 4.3.1 — Architecture

- What Raspberry Pi is: a single-board computer (SBC) running a full Linux OS — categorically different from Arduino/8051 (which are microcontrollers)
- BCM2835/BCM2837/BCM2711: Broadcom ARM-based SoC
- Hardware: ARM Cortex CPU (quad-core on Pi 3/4), 1–8 GB RAM (LPDDR4), HDMI, USB, Ethernet, WiFi, Bluetooth — a full computer on a credit-card-sized board
- Storage: MicroSD card as the boot device / filesystem (unlike microcontrollers that execute from flash)
- GPIO: 40-pin header; 26 usable GPIO pins; 3.3V logic (IMPORTANT — 5V signals will damage GPIO pins)
- Pi vs Arduino: use Pi when you need an OS, web server, database, camera, or heavy computation; use Arduino when you need real-time control, very low power, or very low cost

### 4.3.2 — GPIO Programming

- GPIO access from Python: `RPi.GPIO` library or `gpiozero` (higher-level, recommended for beginners)
- Pin numbering: BOARD mode (physical pin numbers on the header) vs BCM mode (Broadcom GPIO numbers) — must specify which mode before use
- Output: `GPIO.setup(pin, GPIO.OUT)`; `GPIO.output(pin, GPIO.HIGH)`
- Input: `GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)`; `GPIO.input(pin)` returns True/False
- PWM: `pwm = GPIO.PWM(pin, frequency)`; `pwm.start(dutyCycle)` — software PWM; for hardware PWM use specific pins
- Cleanup: `GPIO.cleanup()` at program exit — resets pin modes; important to avoid hardware damage
- I2C and SPI: enabled via `raspi-config`; access via `smbus2` (I2C) or `spidev` (SPI) Python libraries

### 4.3.3 — Connecting to the Cloud

- Python HTTP requests: `requests` library for REST API calls
- MQTT on Pi: `paho-mqtt` Python library; Pi acts as publisher or subscriber
- Database: SQLite (local logging), MySQL/PostgreSQL, or cloud databases
- Cloud platforms: AWS IoT Core (MQTT with certificate-based auth), Google Cloud IoT, Azure IoT Hub, ThingSpeak (simple, good for learning), IFTTT (automation webhooks)
- Pi as gateway: Pi collects data from Arduino/sensors via serial or I2C, processes it, then uploads to cloud — a common architecture

**Why This Matters for the Exam:** Pi GPIO programming, pin numbering modes, and cloud connectivity are asked in Unit 4.
**Why This Matters for Your Career:** Pi is the go-to prototyping platform for IoT gateways and smart edge devices.

---

# PART 5 — IoT Applications Development

## 5.1 — Complete Design of an Embedded IoT System

- The system design process for IoT (end-to-end):
  1. **Requirements**: what to sense, what to actuate, what decisions to make, where data goes, power source, connectivity available
  2. **Hardware selection**: MCU vs SBC; sensor selection (range, accuracy, interface); actuator; communication module; power supply design
  3. **Firmware architecture**: bare metal vs RTOS; sensor reading strategy (polling vs interrupt); communication protocol; error handling and watchdog
  4. **Network/cloud architecture**: which cloud platform; MQTT topics and payload format; data retention; alerting
  5. **User interface**: mobile app, web dashboard, or automated alerting
  6. **Testing and deployment**: unit test firmware, integration test communication, field test reliability
- The IoT system block diagram: Sensors → MCU/SBC → Communication module → Gateway → Cloud → Application

---

## 5.2 — Home Automation

- Use case: control lights, fans, appliances remotely; automate based on schedules or sensor readings; security monitoring
- Sensing: PIR (motion), DHT11/22 (temperature/humidity), LDR (light level), smoke sensor, door reed switch
- Actuating: relays (control 230V AC appliances), dimmers (TRIAC-based), smart plugs
- Communication: WiFi (ESP8266/ESP32) + MQTT to local broker (Mosquitto) or cloud; ZigBee mesh for larger homes; BLE for smartphones
- Controller: ESP32 or Raspberry Pi as the hub; Node-RED (visual flow programming) for automation logic
- Voice integration: Alexa/Google Home via cloud hooks
- Security considerations: authentication on the MQTT broker; TLS for cloud connections; physical relay isolation
- Sample architecture: ESP8266 with relay and DHT11 → MQTT → Raspberry Pi (Mosquitto broker + Node-RED) → Telegram bot for alerts + web dashboard

---

## 5.3 — Smart Agriculture

- Use case: monitor soil moisture, temperature, humidity; control irrigation automatically; alert farmer when conditions go out of range
- Sensing: soil moisture sensor (capacitive preferred over resistive — resistive corrodes), DHT22 for air temp/humidity, BMP280 for pressure, LDR for light, rain gauge
- Actuating: water pump via relay, greenhouse vents, grow lights
- Communication challenge: farms are large and have no WiFi — LoRaWAN (Long Range Wide Area Network) is the key technology here; range 1–15 km; very low power; ~250 bps – 5 kbps; LPWAN (Low Power Wide Area Network)
- LoRa: the physical layer; LoRaWAN: the network protocol on top of LoRa
- GSM/GPRS as fallback when no LoRa infrastructure exists
- System: sensor nodes (Arduino + LoRa module, solar-powered) → LoRaWAN gateway → cloud (TTN — The Things Network, or custom server) → web dashboard + SMS alerts
- Data analytics: historical trend detection (predict irrigation need), anomaly detection (sudden moisture drop = pipe burst)

---

## 5.4 — Smart Cities

- Use cases: smart street lighting, smart parking, waste management, air quality monitoring, traffic management, smart metering
- Smart street lighting: LDR + PIR to dim lights when no traffic, adjust based on ambient light; centralised control via LPWAN; significant energy savings
- Smart parking: ultrasonic or IR sensors in each bay detect occupancy; LoRa/NB-IoT transmits status to server; mobile app guides drivers to empty spots
- Waste management: fill-level sensors in bins; route optimisation for collection trucks (only collect when full)
- Air quality monitoring: MQ-series gas sensors + PM2.5 sensors deployed across the city; mesh network; heatmap on dashboard
- Smart meters: electricity, water, gas meters with NB-IoT or PLC (powerline communication) connectivity; remote reading, tamper detection, consumption analytics
- Infrastructure: city-scale LPWAN (NB-IoT from telecoms, or LoRaWAN with city-owned gateways); city data platform integrating feeds from all systems; open data APIs
- Challenges: scale (millions of devices), heterogeneity (different protocols), security (city infrastructure is a target), privacy

---

## 5.5 — Smart Healthcare

- Use cases: patient monitoring (vitals), wearable health tracking, asset tracking in hospitals, medication management, telemedicine
- Sensing: pulse oximeter (SpO₂) — MAX30100/MAX30102 (I2C); ECG — AD8232; body temperature — MLX90614 (IR, non-contact, I2C); blood glucose (BGM integrated); blood pressure (inflatable cuff sensors)
- Communication: BLE from wearable to phone; phone to cloud via WiFi/4G; BLE beacon for indoor hospital location
- Critical requirements: **reliability** (data cannot be lost), **latency** (ICU alarms must be immediate), **security** (HIPAA/GDPR compliance — patient data is sensitive), **battery life** (wearables must last days)
- Alert system: threshold-based (if SpO₂ < 92%, alert immediately); trend-based (gradual deterioration detection)
- Remote patient monitoring: elderly at home with vitals wearable; data sent to doctor's dashboard; emergency contact triggered if anomaly detected
- Medical-grade vs consumer-grade: medical devices need regulatory approval (FDA 510(k) in USA); calibration and accuracy standards much stricter; consumer wearables are indicative only

---

# PART 6 — Putting It All Together

## 6.1 — The Complete IoT System as a Story

- Narrative walkthrough: smart agriculture system from requirements to deployment
- 1. Requirements: monitor soil moisture in 10 fields; auto-irrigate when dry; farmer alerts on phone
- 2. Hardware: Arduino Pro Mini + capacitive soil sensor + LoRa SX1276 module per field; solar panel + LiPo battery
- 3. Firmware: wake from deep sleep every 5 minutes; read sensor; transmit via LoRa; back to sleep
- 4. Gateway: Raspberry Pi with LoRa HAT; receives data; publishes to MQTT broker; stores in SQLite
- 5. Cloud: Node-RED subscribes to MQTT; writes to InfluxDB; Grafana dashboard; Telegram bot for alerts
- 6. Irrigation control: separate ESP32 at each field; subscribes to irrigation command topic; controls relay

## 6.2 — Exam Strategy

- **Numerical questions** (timers, baud rate, COCOMO-equivalent = RMS schedulability, cyclomatic complexity for embedded C): practice one of each under timed conditions
- **Draw questions**: 8051 architecture block diagram, IoT configuration diagrams, state machine diagrams for RTOS tasks, communication protocol comparison table
- **Compare questions**: always use a table format for WiFi vs ZigBee vs BLE, RTOS vs bare metal, Arduino vs Raspberry Pi
- **Application design questions**: use the 5-step framework — sensing, actuating, communication, processing, user interface

## 6.3 — Career Application Summary

- What you'll use every job: GPIO control, UART/I2C/SPI, MQTT, embedded C patterns
- What you'll use in specific contexts: RTOS scheduling theory (firmware engineer), LoRaWAN (agricultural/industrial IoT), BLE GATT (wearables/consumer IoT)
- What is foundational theory: 8051 instruction set (you'll never write 8051 assembly professionally, but it teaches how hardware actually works)

---

# Appendix — Quick Reference Tables

## Protocol Comparison

| Protocol | Range | Data Rate | Power | Topology | Use Case |
|----------|-------|-----------|-------|----------|----------|
| Bluetooth | ~10m | 1–3 Mbps | Medium | P2P | Short-range pairing |
| BLE | ~10–50m | ~1 Mbps | Very Low | Star | Wearables, beacons |
| WiFi | ~50–100m | Up to 600 Mbps | High | Star | Home IoT, streaming |
| ZigBee | 10–100m | 250 kbps | Very Low | Mesh | Building automation |
| LoRa | 1–15 km | 0.3–50 kbps | Very Low | Star-of-stars | Agriculture, cities |
| GSM/GPRS | Nationwide | ~114 kbps | High | Cellular | Remote areas, tracking |

## RTOS Task States
Ready → Running → Blocked → Ready (event) or Suspended

## 8051 Interrupt Vectors
- INT0: 0003H | T0: 000BH | INT1: 0013H | T1: 001BH | Serial: 0023H

## 8051 Timer Delay (Mode 1, 12 MHz)
Reload = 65536 − (delay_µs)
TH = Reload ÷ 256 (high byte), TL = Reload mod 256 (low byte)

## COCOMO-equivalent for RMS
Utilisation U = Σ(Ci/Ti) ≤ n(2^(1/n) − 1) ≈ 0.693 for large n

## Arduino Analog/PWM
- analogRead: 10-bit, 0–1023, maps 0–5V
- analogWrite: 8-bit, 0–255, generates PWM (duty cycle)

## IoT Communication Models
Request-Response | Publish-Subscribe | Push-Pull | Exclusive Pair
