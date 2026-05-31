## UNIT 1

### 1. List the features of the 8051 microcontroller

* 8-bit CPU
* 4 KB on-chip ROM (program memory)
* 128 bytes on-chip RAM
* 32 programmable I/O pins (4 ports)
* Two 16-bit timers/counters
* Full-duplex serial communication (UART)
* 5 interrupt sources with 2 priority levels
* Supports external program and data memory
* Bit-addressable memory and registers

---

### 2. How is a register bank selected in 8051?

The register bank is selected using bits **RS1** and **RS0** of the **Program Status Word (PSW)**.

| RS1 | RS0 | Register Bank    |
| --- | --- | ---------------- |
| 0   | 0   | Bank 0 (00H–07H) |
| 0   | 1   | Bank 1 (08H–0FH) |
| 1   | 0   | Bank 2 (10H–17H) |
| 1   | 1   | Bank 3 (18H–1FH) |

---

### 3. State the function of the EA and PSEN pins

**EA (External Access)**

* Determines whether the program is executed from internal or external memory.
* EA = 1 → Uses internal program memory.
* EA = 0 → Uses external program memory only.

**PSEN (Program Store Enable)**

* Used to read program code from external ROM.
* Acts as the read control signal for external program memory.

---

### 4. What are the addressing modes available in 8051?

1. **Immediate Addressing** – Operand is given directly in the instruction.

   * Example: `MOV A, #25H`

2. **Register Addressing** – Operand is in a register.

   * Example: `MOV A, R1`

3. **Direct Addressing** – Direct memory address is specified.

   * Example: `MOV A, 30H`

4. **Register Indirect Addressing** – Address is stored in a register.

   * Example: `MOV A, @R0`

5. **Indexed Addressing** – Used mainly for accessing lookup tables.

   * Example: `MOVC A, @A+DPTR`

6. **Bit Addressing** – Accesses individual bits.

   * Example: `SETB P1.0`



---

## UNIT 2

