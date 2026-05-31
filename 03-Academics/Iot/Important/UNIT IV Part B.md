# UNIT IV – PART B

---

# 1. Raspberry Pi: Detailed Architecture and Applications in Complex IoT Systems

# Introduction

**Raspberry Pi** is a low-cost, credit-card-sized single-board computer (SBC) developed by the Raspberry Pi Foundation.

Unlike Arduino, Raspberry Pi can run a complete operating system such as Linux.

---

# Raspberry Pi Architecture

## Block Diagram

```text
                    +------------------+
                    |      CPU         |
                    | ARM Processor    |
                    +--------+---------+
                             |
     +-----------+-----------+------------+-----------+
     |           |           |            |           |
+----+----+ +----+----+ +----+----+ +-----+----+ +----+----+
| RAM     | | USB     | | GPIO    | | Ethernet | | HDMI    |
| Memory  | | Ports   | | Pins    | | / Wi-Fi  | | Display |
+---------+ +---------+ +---------+ +----------+ +---------+
                             |
                       +-----+-----+
                       | SD Card   |
                       | Storage   |
                       +-----------+
```

---

# Main Components

## 1. ARM Processor (CPU)

### Function

* Executes programs
* Processes sensor data
* Controls peripherals

Examples:

* ARM Cortex-A53
* ARM Cortex-A72

---

## 2. RAM

### Function

Stores temporary data.

Typical sizes:

* 2 GB
* 4 GB
* 8 GB

---

## 3. GPIO Pins

GPIO = General Purpose Input Output

Used for:

* Sensors
* Motors
* Relays
* LCD Displays

---

## 4. USB Ports

Used for:

* Keyboard
* Mouse
* Camera
* Storage devices

---

## 5. Network Interface

Provides connectivity through:

* Ethernet
* Wi-Fi
* Bluetooth

Essential for IoT communication.

---

## 6. HDMI Port

Used for connecting:

* Monitor
* Television

---

## 7. SD Card

Stores:

* Operating System
* Programs
* Data

---

# Features of Raspberry Pi

* Linux-based system
* Multi-tasking support
* Internet connectivity
* High processing power
* Supports Python, Java, C, C++
* Camera support
* GPIO support

---

# Raspberry Pi in IoT Systems

## Role

Acts as:

* IoT Gateway
* Local Server
* Data Processor
* Edge Computing Device

---

## Example

Smart Agriculture

```text
Sensors
   ↓
Raspberry Pi
   ↓
Cloud Server
   ↓
Mobile App
```

---

# Applications

## Smart Home

* Home automation
* Smart lighting

---

## Healthcare

* Patient monitoring
* Medical data collection

---

## Smart Agriculture

* Irrigation control
* Weather monitoring

---

## Industrial IoT

* Machine monitoring
* Predictive maintenance

---

## Smart City

* Traffic monitoring
* Environmental monitoring

---

# Advantages

* High processing capability
* Supports complex applications
* Multiple communication interfaces
* Easy cloud integration

---

## Keywords

* Raspberry Pi
* ARM Processor
* GPIO
* Linux
* Edge Computing
* IoT Gateway
* Cloud Integration

---

# 2. Communication Protocols: Bluetooth vs Wi-Fi vs ZigBee

# Introduction

Communication protocols enable IoT devices to exchange information.

Three major protocols:

* Bluetooth
* Wi-Fi
* ZigBee

---

# Bluetooth

## Definition

Short-range wireless communication technology.

### Features

* Low power consumption
* Easy pairing
* Low cost

### Applications

* Smart watches
* Wireless headphones
* Fitness trackers

---

# Wi-Fi

## Definition

Wireless Local Area Network technology.

### Features

* High data rate
* Internet connectivity
* Wide usage

### Applications

* Smart homes
* Surveillance systems
* IoT gateways

---

# ZigBee

## Definition

Low-power wireless protocol based on IEEE 802.15.4.

### Features

* Very low power consumption
* Mesh networking support
* Suitable for sensor networks

### Applications

* Home automation
* Industrial monitoring
* Smart agriculture

---

# Comparison Table

| Parameter         | Bluetooth        | Wi-Fi                   | ZigBee          |
| ----------------- | ---------------- | ----------------------- | --------------- |
| Standard          | IEEE 802.15.1    | IEEE 802.11             | IEEE 802.15.4   |
| Range             | 10–100 m         | 50–100 m                | 10–100 m        |
| Power Consumption | Low              | High                    | Very Low        |
| Data Rate         | ~1–3 Mbps        | Up to hundreds of Mbps  | ~250 kbps       |
| Cost              | Low              | Moderate                | Low             |
| Network Type      | Point-to-Point   | Infrastructure          | Mesh Network    |
| Battery Life      | Good             | Lower                   | Excellent       |
| IoT Suitability   | Personal Devices | High-Speed Applications | Sensor Networks |

---

# Why ZigBee is Preferred in IoT

* Very low power consumption
* Long battery life
* Supports thousands of nodes
* Mesh networking increases coverage

---

# Advantages and Disadvantages

## Bluetooth

### Advantages

* Cheap
* Low power

### Disadvantages

* Limited range

---

## Wi-Fi

### Advantages

* High speed
* Direct internet access

### Disadvantages

* High power consumption

---

## ZigBee

### Advantages

* Lowest power usage
* Reliable mesh network

### Disadvantages

* Low data rate

---

## Keywords

* Bluetooth
* Wi-Fi
* ZigBee
* Mesh Network
* Data Rate
* Range
* Power Consumption

---

# 3. GSM & GPS: GSM Architecture and Mobile IoT Communication

# Introduction

## GSM

GSM = Global System for Mobile Communications

Provides:

* Voice communication
* SMS services
* Mobile data communication

Used in IoT devices for remote connectivity.

---

## GPS

GPS = Global Positioning System

Provides:

* Latitude
* Longitude
* Location tracking

Used in:

* Vehicle tracking
* Asset tracking
* Navigation systems

---

# GSM Architecture

## Block Diagram

```text
          Mobile Device
                 |
                 |
              BTS
                 |
              BSC
                 |
               MSC
                 |
      +----------+----------+
      |                     |
     HLR                   VLR
      |                     |
      +----------+----------+
                 |
               PSTN
```

---

# Components of GSM Architecture

## 1. Mobile Station (MS)

Represents:

* Mobile phone
* GSM module
* IoT device

Contains:

* SIM card
* GSM modem

---

## 2. BTS (Base Transceiver Station)

### Function

Provides radio communication between:

```text
Device ↔ Network
```

Example:

Cell tower.

---

## 3. BSC (Base Station Controller)

### Function

Controls multiple BTS stations.

Responsibilities:

* Resource allocation
* Handover management

---

## 4. MSC (Mobile Switching Center)

### Function

Central controller of GSM network.

Handles:

* Call routing
* SMS routing
* Network management

---

## 5. HLR (Home Location Register)

### Function

Permanent subscriber database.

Stores:

* User details
* Mobile number
* Service information

---

## 6. VLR (Visitor Location Register)

### Function

Temporary database.

Stores information of roaming users.

---

# GSM Communication Process

1. IoT device collects data.
2. GSM module sends data.
3. BTS receives signal.
4. Data routed through BSC and MSC.
5. Sent to cloud server or user.

---

# GSM in IoT

## Applications

### Vehicle Tracking

```text
GPS → GSM → Cloud
```

---

### Smart Energy Meter

```text
Meter Reading → GSM → Utility Server
```

---

### Smart Agriculture

```text
Sensor Data → GSM → Farmer Mobile
```

---

# GPS and GSM Together

## Working

```text
GPS
 ↓
Location Coordinates
 ↓
Microcontroller
 ↓
GSM Module
 ↓
Cloud / Mobile App
```

---

# Advantages of GSM-Based IoT

* Wide coverage
* Long-distance communication
* Supports SMS and Internet
* Reliable remote monitoring

---

# Applications

* Vehicle tracking
* Fleet management
* Smart meters
* Remote agriculture
* Emergency services

---

## Keywords

* GSM
* GPS
* BTS
* BSC
* MSC
* HLR
* VLR
* Mobile IoT
* Remote Monitoring
* Location Tracking

---

# Exam Tip

For **13–16 marks**, draw:

1. **Raspberry Pi Architecture Diagram**
2. **Bluetooth vs Wi-Fi vs ZigBee Comparison Table**
3. **GSM Architecture Diagram (MS → BTS → BSC → MSC → HLR/VLR)**

These diagrams + keywords usually secure most of the marks.


Perfect. Comparing the **8 Unit IV questions in the image** against what you already have in your notes:

Already covered well:

* Raspberry Pi Architecture & IoT Applications 
* Bluetooth vs Wi-Fi vs ZigBee comparison 
* GSM Architecture & Working 

So for those, I'll only give the **missing parts**.

---

# UNIT IV – Q14(a)

# Bluetooth Architecture and Connection States

*(Mostly new — not covered in your notes)*

## Introduction

Bluetooth is a short-range wireless communication technology operating in the:

```text
2.4 GHz ISM Band
```

Standard:

```text
IEEE 802.15.1
```

Range:

```text
10m – 100m
```

Applications:

* Headphones
* Smart Watches
* IoT Devices
* Medical Sensors

---

# Bluetooth Architecture

```text
+----------------------+
| Application Layer    |
+----------------------+
| RFCOMM               |
+----------------------+
| L2CAP                |
+----------------------+
| HCI                  |
+----------------------+
| Link Manager (LMP)   |
+----------------------+
| Baseband             |
+----------------------+
| Radio Layer          |
+----------------------+
```

---

## Radio Layer

Responsible for:

* Wireless transmission
* Frequency hopping
* Signal modulation

Uses:

```text
2.4 GHz Band
```

---

## Baseband Layer

Functions:

* Packet formation
* Error correction
* Synchronization

Supports:

```text
Piconet
Scatternet
```

---

## Link Manager Protocol (LMP)

Functions:

* Device authentication
* Encryption
* Power management
* Link setup

---

## HCI (Host Controller Interface)

Interface between:

```text
Bluetooth Hardware
↕
Host Software
```

---

## L2CAP

Logical Link Control and Adaptation Protocol.

Functions:

* Multiplexing
* Segmentation
* Reassembly

---

## RFCOMM

Provides:

```text
Virtual Serial Port
```

Used for serial communication over Bluetooth.

---

# Bluetooth Network Structures

## Piconet

```text
      Master
     /  |  \
Slave Slave Slave
```

* 1 Master
* Up to 7 Active Slaves

---

## Scatternet

Multiple piconets interconnected.

```text
Piconet 1 ↔ Piconet 2
```

---

# Bluetooth Connection States

## 1. Standby

Default state.

Characteristics:

* Device inactive
* Conserves power

---

## 2. Inquiry

Searches for nearby devices.

```text
Who is nearby?
```

---

## 3. Page

Attempts connection.

```text
Connect to device
```

---

## 4. Connected

Data communication occurs.

---

## 5. Active

Fully operational communication.

---

## 6. Sniff

Reduced activity.

Lower power consumption.

---

## 7. Hold

Temporarily suspends communication.

---

## 8. Park

Device remains synchronized but inactive.

Lowest power state.

---

# Connection Process

```text
Standby
   ↓
Inquiry
   ↓
Page
   ↓
Connected
   ↓
Active
```

---

## Keywords

Bluetooth, IEEE 802.15.1, Piconet, Scatternet, L2CAP, RFCOMM, HCI, Inquiry State, Page State, Active State.

---

# UNIT IV – Q14(b)

# LDR Interfacing with Raspberry Pi and Python Program

*(Completely new)*

---

# Objective

Control an LED based on ambient light intensity using:

```text
LDR + Raspberry Pi
```

---

# Components Required

* Raspberry Pi
* LDR
* 10kΩ Resistor
* LED
* 220Ω Resistor
* MCP3008 ADC
* Breadboard

---

# Why ADC is Needed

Raspberry Pi GPIO supports:

```text
Digital Input Only
```

LDR produces:

```text
Analog Signal
```

Hence:

```text
MCP3008 ADC
```

is used.

---

# Block Diagram

```text
LDR
 ↓
Voltage Divider
 ↓
MCP3008 ADC
 ↓
Raspberry Pi
 ↓
GPIO Output
 ↓
LED
```

---

# Working

### Bright Environment

```text
LDR Resistance ↓
ADC Value ↓
LED OFF
```

---

### Dark Environment

```text
LDR Resistance ↑
ADC Value ↑
LED ON
```

---

# Python Program

```python
import RPi.GPIO as GPIO
import time

LED = 18

GPIO.setmode(GPIO.BCM)
GPIO.setup(LED, GPIO.OUT)

while True:

    ldr_value = read_adc(0)

    if ldr_value > 500:
        GPIO.output(LED, GPIO.HIGH)
    else:
        GPIO.output(LED, GPIO.LOW)

    time.sleep(0.1)
```

---

# Algorithm

```text
Start
  ↓
Read LDR Value
  ↓
Value > Threshold ?
  ↓
Yes → LED ON
No  → LED OFF
  ↓
Repeat
```

---

# Applications

* Automatic Street Lights
* Smart Home Lighting
* Energy Management
* Security Systems

---

## Keywords

LDR, Raspberry Pi, MCP3008, ADC, GPIO, Voltage Divider, Threshold Detection, Automatic Lighting.

---

These are the first **2 Unit IV questions that are not already covered by your notes**. The next 2 worthwhile ones are:

1. **ZigBee Architecture** (mostly new)
2. **IoT Communication Protocols & Comparison** (partly overlaps Bluetooth/Wi-Fi/ZigBee but has additional protocol details)


