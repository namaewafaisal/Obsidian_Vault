---
topic: Microprocessor
---
# Microprocessor

## Definition
- A **microprocessor** is a CPU built on a single Integrated Circuit (IC).
- A computer that uses a microprocessor as its CPU is called a **microcomputer**.
- It is a programmable, clock-driven electronic device that:
  1. Reads instructions from memory
  2. Accepts binary input data
  3. Processes data according to instructions
  4. Produces output

## Main Components
- Transistors
- Registers
- Diodes
- Control Unit (CU)
- Arithmetic Logic Unit (ALU)

## Evolution of Microprocessors

| Generation | Size | Example | Key Feature |
|------------|------|----------|-------------|
| 1st (1971-72) | 4-bit | Intel 4004 | Basic arithmetic & logic operations |
| 2nd (1973) | 8-bit | Intel 8008, 8088 | Processes 8-bit words |
| 3rd (1978) | 16-bit | Intel 8086, 80286, Zilog Z800 | Minicomputer-like performance |
| 4th | 32-bit | Intel 80386 | Higher processing capability |
| 5th (1995-Present) | 64-bit | Pentium, Celeron, Dual/Quad/Octa Core | Multiprocessing & high performance |

## Important Terms

### Instruction Set
- Collection of commands understood by a microprocessor.
- Interface between hardware and software.

### Bus
Communication pathway inside a computer.

Types:
- **Data Bus** → Transfers data
- **Address Bus** → Transfers memory addresses
- **Control Bus** → Transfers control signals

### IPC (Instructions Per Cycle)
- Number of instructions executed in one clock cycle.

### Clock Speed
- Number of operations performed per second.
- Measured in:
  - MHz (Megahertz)
  - GHz (Gigahertz)

### Bandwidth
- Number of bits processed in a single instruction.

### Word Length
- Number of bits processed at one time.
- Examples:
  - 4-bit
  - 8-bit
  - 16-bit
  - 32-bit
  - 64-bit

### Data Types Supported
- Binary
- ASCII
- Signed numbers
- Unsigned numbers

## Working of a Microprocessor

### Fetch–Decode–Execute Cycle

1. **Fetch**
   - Instruction is fetched from memory.

2. **Decode**
   - Control Unit interprets the instruction.

3. **Execute**
   - ALU performs the required operation.

4. **Store/Output**
   - Result is stored or sent to output devices.

### Supporting Units
- **Registers** → Store temporary data.
- **ALU** → Performs arithmetic and logical operations.
- **Control Unit** → Controls instruction execution.

### Flow

```text
Memory
   ↓
 Fetch
   ↓
 Decode
   ↓
 Execute (ALU)
   ↓
Registers / Output
```