# UNIT III – PART B

---

# 1. IoT Architecture: Functional Components and Basic Building Blocks of an IoT System

## Introduction

**Internet of Things (IoT)** is a network of physical devices that collect, exchange, and process data through the Internet.

Examples:

* Smart Home
* Smart Agriculture
* Smart City
* Healthcare Monitoring

---

# Basic Building Blocks of IoT

A typical IoT system consists of:

```text
Sensors → Controller → Communication Network
         ↓
      Cloud/Server
         ↓
    User Application
         ↓
      Actuators
```

---

## 1. Sensors

### Definition

A sensor detects physical quantities and converts them into electrical signals.

### Examples

* Temperature Sensor
* Humidity Sensor
* Light Sensor (LDR)
* Soil Moisture Sensor

### Function

* Collect data from environment
* Send data to controller

---

## 2. Controller / Processing Unit

### Definition

The controller processes sensor data and makes decisions.

### Examples

* Arduino
* Raspberry Pi
* ESP32
* 8051 Microcontroller

### Function

* Receives sensor data
* Executes program logic
* Controls actuators

---

## 3. Communication Network

### Definition

Transfers data between devices and cloud.

### Technologies

* Wi-Fi
* Bluetooth
* ZigBee
* GSM/GPRS
* LoRaWAN

### Function

* Enables device connectivity
* Supports remote monitoring

---

## 4. Cloud Platform

### Definition

Cloud stores and processes IoT data.

### Functions

* Data storage
* Data analytics
* Remote access
* Device management

### Examples

* AWS IoT
* Azure IoT
* Google Cloud IoT

---

## 5. User Interface / Application Layer

### Definition

Allows users to interact with IoT devices.

### Examples

* Mobile App
* Web Dashboard
* Smart Home App

### Functions

* Display data
* Send commands
* Monitor system status

---

## 6. Actuators

### Definition

Actuators convert electrical signals into physical actions.

### Examples

* Motor
* Relay
* LED
* Buzzer
* Water Pump

### Function

Perform actions based on controller decisions.

---

# Functional Components of IoT

---

## Sensing

Collects information from surroundings.

Example:

```text
Temperature = 35°C
```

---

## Communication

Transfers data to cloud/server.

Example:

```text
Sensor → Wi-Fi → Cloud
```

---

## Processing

Analyzes collected data.

Example:

```text
If temperature > 40°C
Turn ON fan
```

---

## Storage

Stores collected information.

Example:

```text
Daily temperature records
```

---

## Actuation

Performs physical action.

Example:

```text
Motor ON
LED OFF
```

---

# Characteristics of IoT

* Connectivity
* Intelligence
* Scalability
* Automation
* Interoperability
* Real-time communication

---

# Applications

* Smart Cities
* Smart Homes
* Smart Agriculture
* Healthcare
* Industrial Automation

---

## Keywords

* Sensor
* Actuator
* Cloud Computing
* Connectivity
* Processing Unit
* Communication Network
* Real-Time Monitoring

---

# 2. Arduino Features: Types of Boards, Pin Structure and Programming

---

# Introduction

Arduino is an **open-source microcontroller platform** used for embedded systems and IoT development.

Advantages:

* Low cost
* Easy programming
* Large community support

---

# Types of Arduino Boards

---

## 1. Arduino Uno

Most popular board.

Specifications:

* ATmega328P
* 14 Digital Pins
* 6 Analog Pins
* USB Interface

Applications:

* Learning
* Basic IoT Projects

---

## 2. Arduino Mega

Features:

* More memory
* More I/O pins

Specifications:

* 54 Digital Pins
* 16 Analog Pins

Applications:

* Large projects
* Robotics

---

## 3. Arduino Nano

Features:

* Compact size
* Breadboard friendly

Applications:

* Portable systems

---

## 4. Arduino Leonardo

Feature:

* Native USB support

Applications:

* Keyboard/Mouse emulation

---

## 5. Arduino Due

Features:

* 32-bit ARM processor
* Higher performance

Applications:

* Advanced embedded systems

---

# Arduino Pin Structure

---

## Digital Pins

Used for:

```text
HIGH (1)
LOW  (0)
```

Examples:

* LEDs
* Relays
* Switches

---

## Analog Pins

Used for analog sensors.

Example:

```text
Temperature Sensor
LDR
```

ADC converts analog signal into digital value.

---

## Power Pins

| Pin  | Function            |
| ---- | ------------------- |
| 5V   | Supply Voltage      |
| 3.3V | Low Voltage Devices |
| GND  | Ground              |
| VIN  | External Power      |

---

## Communication Pins

### UART

Serial communication.

```text
TX → Transmit
RX → Receive
```

---

### SPI

Used for:

* Displays
* SD Cards

Pins:

```text
MOSI
MISO
SCK
SS
```

---

### I²C

Pins:

```text
SDA
SCL
```

Used for sensors and modules.

---

# Arduino Programming

---

## Arduino IDE

Integrated Development Environment used for writing and uploading programs.

---

## Structure of Arduino Program

```cpp
void setup()
{
}

void loop()
{
}
```

---

### setup()

Runs once.

Used for:

* Pin initialization
* Serial initialization

Example:

```cpp
pinMode(13, OUTPUT);
```

---

### loop()

Runs continuously.

Example:

```cpp
digitalWrite(13, HIGH);
```

---

## Program Development Steps

1. Write code
2. Verify/Compile
3. Upload to board
4. Execute

---

## Features of Arduino

* Open-source
* Easy programming
* USB support
* Analog and digital I/O
* Built-in libraries
* Real-time interfacing

---

## Keywords

* Arduino IDE
* Digital Pins
* Analog Pins
* UART
* SPI
* I²C
* setup()
* loop()

---

# 3. Sensor and Actuator Integration with Arduino for Real-Time Applications

---

# Introduction

Real-time systems continuously interact with the environment.

This interaction occurs through:

```text
Sensors → Arduino → Actuators
```

---

# Sensor Integration

---

## Definition

A sensor converts a physical quantity into an electrical signal.

---

## Sensor Connection Process

### Step 1

Connect sensor output to Arduino pin.

Example:

```text
LDR → A0
```

---

### Step 2

Read sensor value.

Example:

```cpp
value = analogRead(A0);
```

---

### Step 3

Process data.

Example:

```text
If light intensity is low
```

---

### Step 4

Take action.

Example:

```text
Turn ON LED
```

---

# Common Sensors

| Sensor        | Measures               |
| ------------- | ---------------------- |
| LM35          | Temperature            |
| LDR           | Light                  |
| DHT11         | Temperature & Humidity |
| Soil Moisture | Soil Water Content     |
| Ultrasonic    | Distance               |

---

# Actuator Integration

---

## Definition

An actuator converts electrical signals into physical action.

---

## Common Actuators

| Actuator   | Action         |
| ---------- | -------------- |
| LED        | Light          |
| Motor      | Rotation       |
| Relay      | Switch Control |
| Buzzer     | Sound          |
| Water Pump | Water Flow     |

---

# Real-Time Working

### Example: Smart Irrigation System

---

## Input

Soil moisture sensor reads soil condition.

```text
Moisture = Low
```

---

## Processing

Arduino checks threshold.

```text
If moisture < limit
```

---

## Output

Arduino activates water pump.

```text
Pump ON
```

---

## Flow Diagram

```text
Soil Sensor
     ↓
 Arduino
     ↓
 Decision Making
     ↓
 Water Pump
```

---

# Another Example: Automatic Street Light

### Sensor

LDR

### Controller

Arduino

### Actuator

LED Lamp

### Working

```text
Light Level Low
      ↓
 Arduino Detects
      ↓
 Street Light ON
```

---

# Advantages of Sensor-Actuator Integration

* Automation
* Real-time response
* Reduced human effort
* Improved efficiency
* Energy saving
* Remote monitoring

---

# Applications

* Smart Irrigation
* Home Automation
* Industrial Monitoring
* Smart Healthcare
* Smart Cities
* Security Systems

---

## Keywords

* Sensor
* Actuator
* Arduino
* Automation
* Real-Time System
* Analog Input
* Digital Output
* Smart Irrigation
* IoT Integration

---

## Exam Tip

For **13–16 marks**, always draw:

1. **IoT Architecture Diagram**
2. **Arduino Board/Pin Structure Diagram**
3. **Sensor → Arduino → Actuator Flow Diagram**

These diagrams plus the keyword-heavy points are usually enough for full-mark answers.


Based on your existing UNIT III notes, the **only additional content worth adding** is:

---

# Arduino UNO Components

*(For questions specifically asking "main components of Arduino UNO")*

## Block Diagram

```text
          USB Port
              |
      USB-UART Converter
              |
       ATmega328P MCU
              |
 +------+------+------+------+
 |             |             |
Digital     Analog      Communication
 Pins        Pins       UART/SPI/I²C
              |
         Crystal Oscillator
              |
         Voltage Regulator
              |
          Power Supply
```

---

## Components

### 1. ATmega328P

Main microcontroller.

Specifications:

```text
8-bit AVR
16 MHz Clock
32 KB Flash
2 KB SRAM
1 KB EEPROM
```

---

### 2. USB-to-Serial Converter

Converts:

```text
USB ↔ UART
```

Used for program uploading and serial communication.

---

### 3. Crystal Oscillator

```text
16 MHz
```

Provides clock signal.

---

### 4. Voltage Regulator

Converts external voltage into stable:

```text
5V
3.3V
```

for board operation.

---

### 5. Reset Circuit

Restarts execution from beginning.

---

### 6. ICSP Header

Used for:

* Bootloader burning
* Direct programming

---

# digitalWrite() vs analogWrite()

| digitalWrite()   | analogWrite()            |
| ---------------- | ------------------------ |
| Digital output   | PWM output               |
| HIGH or LOW only | 0–255 value              |
| ON/OFF control   | Brightness/Speed control |
| Any digital pin  | PWM pins only            |
| 0V or 5V         | Variable average voltage |

---

## digitalWrite()

Syntax:

```cpp
digitalWrite(pin,HIGH);
digitalWrite(pin,LOW);
```

Example:

```cpp
digitalWrite(13,HIGH);
```

LED fully ON.

---

## analogWrite()

Syntax:

```cpp
analogWrite(pin,value);
```

Example:

```cpp
analogWrite(9,128);
```

Produces:

```text
50% Duty Cycle PWM
```

Used for:

* LED brightness control
* Motor speed control

---

# LED Control Using Arduino

## Circuit

```text
Arduino D13
      |
     LED
      |
 220Ω Resistor
      |
     GND
```

---

## Program

```cpp
void setup()
{
    pinMode(13, OUTPUT);
}

void loop()
{
    digitalWrite(13, HIGH);
    delay(1000);

    digitalWrite(13, LOW);
    delay(1000);
}
```

---

## Working

```text
LED ON  → 1 second
LED OFF → 1 second
Repeat continuously
```

---

# Sensor and Actuator Selection Criteria

*(Not present in your notes and appears as a separate 13-mark question)*

## Sensor Selection Criteria

* Accuracy
* Sensitivity
* Resolution
* Measurement Range
* Response Time
* Power Consumption
* Reliability
* Environmental Compatibility
* Cost

---

## Actuator Selection Criteria

* Torque (Motors)
* Speed
* Power Rating
* Precision
* Efficiency
* Reliability
* Operating Voltage
* Load Capacity
* Cost

---

## Example

### Sensor

```text
LDR
```

Selected because:

* Low cost
* Good sensitivity to light

### Actuator

```text
Relay
```

Selected because:

* Can switch high-power loads
* Electrically isolated

---

That's essentially **all the UNIT III content missing from your existing notes**. Everything else (IoT Architecture, Arduino boards, pin structure, sketches, sensors/actuators integration) is already covered in your notes. 


After UNIT I, the only remaining **Part C unique questions** are:

# UNIT III

### Sensor and Actuator Interfacing for Real-Time Industrial Applications

```text
Elaborate on interfacing the sensors and actuators
for real time industrial applications.
Detail any example based on it.
```

---

# UNIT III – Sensor and Actuator Interfacing for Real-Time Industrial Applications

## Introduction

Industrial automation systems use sensors to collect information from the environment and actuators to perform physical actions based on controller decisions.

Basic flow:

```text
Sensor
   ↓
Controller
(PLC/Arduino/8051/RPi)
   ↓
Actuator
   ↓
Industrial Process
```

---

# Sensors Used in Industries

Sensors convert physical quantities into electrical signals.

Examples:

| Sensor             | Measures          |
| ------------------ | ----------------- |
| Temperature Sensor | Temperature       |
| Pressure Sensor    | Pressure          |
| Proximity Sensor   | Object Detection  |
| Flow Sensor        | Flow Rate         |
| Humidity Sensor    | Humidity          |
| Gas Sensor         | Gas Concentration |

---

# Actuators Used in Industries

Actuators convert electrical signals into physical action.

Examples:

| Actuator       | Action           |
| -------------- | ---------------- |
| DC Motor       | Rotation         |
| Stepper Motor  | Precise Rotation |
| Solenoid Valve | Open/Close Flow  |
| Relay          | Switch Loads     |
| Pump           | Move Liquid      |
| Heater         | Generate Heat    |

---

# Industrial Interfacing Architecture

```text
Temperature Sensor
        ↓
     ADC
        ↓
 Microcontroller
        ↓
 Driver Circuit
        ↓
      Relay
        ↓
    Heater
```

---

# Need for Driver Circuits

Microcontrollers cannot directly drive:

* Motors
* Pumps
* Relays

Therefore driver circuits are used:

Examples:

```text
ULN2003
L293D
Relay Driver
```

---

# Steps in Sensor-Actuator Interfacing

### Step 1

Sensor measures physical parameter.

---

### Step 2

Controller reads sensor value.

---

### Step 3

Decision logic executed.

Example:

```text
Temperature > Set Point ?
```

---

### Step 4

Controller activates actuator.

---

### Step 5

Actuator performs required operation.

---

# Example

# Industrial Temperature Control System

## Block Diagram

```text
LM35 Sensor
      ↓
8051 Controller
      ↓
Relay Driver
      ↓
Heater
```

---

## Working

### Case 1

```text
Temperature < 30°C
```

Controller:

```text
Relay ON
Heater ON
```

---

### Case 2

```text
Temperature ≥ 30°C
```

Controller:

```text
Relay OFF
Heater OFF
```

---

# Real-Time Characteristics

Industrial systems require:

* Deterministic response
* Fast execution
* Continuous monitoring
* High reliability
* Fault tolerance

---

# Applications

* Factory Automation
* Process Control
* Smart Manufacturing
* Power Plants
* Chemical Industries

---

# Advantages

* Increased productivity
* Reduced human intervention
* Better accuracy
* Improved safety
* Reduced operational cost

---

## Keywords

Industrial Automation, Sensor Interfacing, Actuator Interfacing, Relay Driver, Real-Time System, Process Control, Closed Loop Control, Monitoring.

---

# UNIT IV

### None Remaining

GSM Architecture was already covered under Unit IV.

---
