---
topic: UML Interaction Diagrams (Sequence vs Collaboration)
---

# 🧠 Sequence Diagram vs Collaboration (Communication) Diagram

## 🧩 Core Idea
Both diagrams model:
> **How objects interact by sending messages**

Difference:
- **Sequence Diagram** → Focus on **time/order**
- **Collaboration Diagram** → Focus on **relationships/structure**

---

## ⚙️ Sequence Diagram

### 🎯 Purpose
Show **step-by-step flow of interactions over time**

### 📌 Characteristics
- Time flows **top → bottom**
- Objects placed horizontally
- Messages shown as arrows in order

### 📊 Representation

```mermaid
sequenceDiagram
    A->>B: Request
    B->>C: Process
    C-->>A: Response
```

### ✅ Best Used When
- Understanding **execution flow**
- Tracking **order of operations**

---

## ⚙️ Collaboration (Communication) Diagram

### 🎯 Purpose
Show **how objects are connected and interact**

### 📌 Characteristics
- Network-like structure
- Focus on object relationships
- Messages shown with **numbering (1, 2, 3...)**

### 📊 Representation (Conceptual)

```mermaid
graph LR
    A -- 1:Request --> B
    B -- 2:Process --> C
    C -- 3:Response --> A
```

### ✅ Best Used When
- Understanding **object connections**
- Visualizing **interaction structure**

---

## ⚖️ Key Differences

| Aspect              | Sequence Diagram 📊           | Collaboration Diagram 🕸️     |
|---------------------|------------------------------|------------------------------|
| Focus               | Time / Order                 | Relationships / Structure    |
| Layout              | Vertical (timeline)          | Network-like                 |
| Message Order       | Position-based               | Numbered                    |
| Clarity             | Flow understanding           | Structural understanding     |

---

## 🔁 Big Picture

```mermaid
flowchart LR
    A[Same Interaction] --> B[Sequence View - Time]
    A --> C[Collaboration View - Structure]
```

---

## 🧠 Final Understanding

- Both describe **same system behavior**
- Just different perspectives:
  - Sequence = **“When things happen”**
  - Collaboration = **“Who interacts with whom”**
