# QPAPER 1

## UNIT I (Q1–Q2)

### 1. State the difference between RET and RETI instruction in 8051.

| RET                            | RETI                                             |
| ------------------------------ | ------------------------------------------------ |
| Returns from a subroutine      | Returns from an Interrupt Service Routine (ISR)  |
| Pops return address from stack | Pops return address and signals end of interrupt |
| Used in normal function calls  | Used only in interrupt routines                  |

### 2. Draw the format of PSW of 8051.

| CY | AC | F0 | RS1 | RS0 | OV | — | P |
| -- | -- | -- | --- | --- | -- | - | - |

**CY** – Carry Flag
**AC** – Auxiliary Carry
**F0** – User Flag
**RS1, RS0** – Register Bank Select
**OV** – Overflow Flag
**P** – Parity Flag

---

## UNIT II (Q3–Q4)

### 3. Mention the challenges faced in sensor interfacing.

* Noise and signal interference
* Calibration issues
* Power consumption constraints
* Accuracy and reliability problems
* Analog-to-digital conversion requirements
* Environmental effects (temperature, humidity)

### 4. Mention the role of scheduling in a multitasking environment.

* Allocates CPU time among tasks
* Determines execution order of tasks
* Ensures deadlines are met
* Improves CPU utilization
* Supports real-time operation

---

## UNIT III (Q5–Q6)

### 5. List the main components of an Arduino board.

* Microcontroller (ATmega328P)
* Digital I/O pins
* Analog input pins
* USB interface
* Power supply circuitry
* Crystal oscillator
* Reset button

### 6. Differentiate between active sensor and passive sensor.

| Active Sensor                     | Passive Sensor                     |
| --------------------------------- | ---------------------------------- |
| Requires external power source    | Does not require external power    |
| Emits its own signal              | Detects naturally available energy |
| Example: Radar, Ultrasonic sensor | Example: LDR, Thermocouple         |

---

## UNIT IV (Q7–Q8)

### 7. List out the important features of Wi-Fi.

* Wireless connectivity
* High data transfer rate
* Internet access over WLAN
* Supports multiple devices
* Easy installation
* Wide coverage area

### 8. Tabulate any two differences between SPI and I²C protocol in Raspberry Pi.

| SPI                                | I²C                     |
| ---------------------------------- | ----------------------- |
| Uses 4 wires (MOSI, MISO, SCK, SS) | Uses 2 wires (SDA, SCL) |
| Faster communication               | Slower communication    |
| No device addressing               | Uses device addressing  |
| Full-duplex                        | Half-duplex             |

---

## UNIT V (Q9–Q10)

### 9. Recall some real-time applications of IoT.

* Smart homes
* Smart agriculture
* Healthcare monitoring
* Smart cities
* Industrial automation
* Vehicle tracking systems

### 10. Mention the impacts of IoT in smart agriculture application.

* Automated irrigation
* Water conservation
* Increased crop yield
* Reduced labor cost
* Real-time monitoring of soil and weather
* Efficient resource utilization

---

# QPAPER 2

## UNIT I (Q1–Q2)

### 1. How is register bank selected in 8051 microcontroller?

Register bank selection is done using **RS1 and RS0 bits** in the **PSW register**.

| RS1 | RS0 | Bank   |
| --- | --- | ------ |
| 0   | 0   | Bank 0 |
| 0   | 1   | Bank 1 |
| 1   | 0   | Bank 2 |
| 1   | 1   | Bank 3 |

### 2. Compare polling and interrupts. How a microcontroller performs upon activation of interrupts?

| Polling                               | Interrupt                                  |
| ------------------------------------- | ------------------------------------------ |
| CPU continuously checks device status | Device notifies CPU when service is needed |
| Wastes CPU time                       | Efficient CPU utilization                  |
| Slower response                       | Faster response                            |

**On interrupt activation:**

1. Current instruction completes.
2. Program Counter is saved on stack.
3. CPU jumps to Interrupt Service Routine (ISR).
4. ISR executes.
5. RETI returns control to main program.

---

## UNIT II (Q3–Q4)

### 3. What is the difference between compiler and cross compiler?

| Compiler                           | Cross Compiler                               |
| ---------------------------------- | -------------------------------------------- |
| Generates code for the same system | Generates code for a different target system |
| Example: GCC on PC for PC          | ARM compiler on PC for ARM MCU               |

### 4. List the bitwise operators in Embedded C language.

* `&` (AND)
* `|` (OR)
* `^` (XOR)
* `~` (NOT)
* `<<` (Left Shift)
* `>>` (Right Shift)

---

## UNIT III (Q5–Q6)

### 5. List the characteristics of IoT.

* Connectivity
* Sensing capability
* Intelligence
* Automation
* Scalability
* Interoperability
* Real-time communication

### 6. How debugging works in Arduino?

* Upload program to Arduino.
* Use Serial Monitor (`Serial.print()`) to display values.
* Observe outputs and errors.
* Identify and correct faults.
* Recompile and upload again.

---

## UNIT IV (Q7–Q8)

### 7. What is the requirement of IoT protocol standardization?

* Ensures interoperability between devices
* Enables reliable communication
* Improves security
* Reduces development cost
* Supports scalability across platforms

### 8. State where ZigBee makes its mark with regard to IoT applications when compared to Bluetooth and Wi-Fi standards.

* Lower power consumption
* Supports mesh networking
* Suitable for large sensor networks
* Lower cost
* Better battery life
* Ideal for home automation and industrial monitoring

---

## UNIT V (Q9–Q10)

### 9. Define smart irrigation system.

A **smart irrigation system** uses sensors and IoT technology to automatically supply water to crops based on soil moisture and environmental conditions, reducing water wastage and improving crop growth.

### 10. What are the components used as the core of an embedded system?

* Microcontroller/Microprocessor
* Memory (RAM, ROM, Flash)
* Input devices (sensors, switches)
* Output devices (LEDs, motors, displays)
* Communication interfaces
* Power supply unit
