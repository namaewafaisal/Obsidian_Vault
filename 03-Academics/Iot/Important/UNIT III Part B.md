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
