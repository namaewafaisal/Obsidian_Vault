---
topic: Finite State Machines (FSM) in UML
---

# 🧠 Finite State Machines (FSM)

## 🧩 Core Idea
FSM =  
> **System exists in one state at a time and changes state based on events**

---

## ⚙️ Components

### 🔹 State
- Current condition of system  
- Example: Idle, Processing  

---

### 🔹 Transition
- Movement between states  

---

### 🔹 Event
- Trigger causing transition  
- Example: click, timer  

---

### 🔹 Initial State
- Starting point  

---

### 🔹 Final State
- Ending point  

---

## 📊 UML State Diagram

```mermaid
stateDiagram-v2
    [*] --> Idle
    Idle --> Processing : Start
    Processing --> Completed : Finish
    Completed --> [*]
```

---

## 📌 Example: ATM System

```mermaid
stateDiagram-v2
    [*] --> Idle
    Idle --> CardInserted : Insert Card
    CardInserted --> Processing : Enter PIN
    Processing --> Dispense : Valid
    Dispense --> [*]
```

---

## 📌 Uses
- UI systems  
- Embedded systems  
- Workflow modeling  

---

## ✅ Advantages
- Clear behavior representation  
- Easy to track transitions  
- Good for event-driven systems  

---

## ❌ Disadvantages
- Complex for large systems  
- Difficult to scale  

---

## 🔁 Big Picture

```mermaid
flowchart LR
    A[States] --> B[Transitions]
    B --> C[Events]
```

---

## 🧠 Final Understanding

FSM =  
> **States + Events + Transitions define system behavior**
