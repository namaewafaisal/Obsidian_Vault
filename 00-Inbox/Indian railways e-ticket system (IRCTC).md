---
topic: Activity Diagram (IRCTC Ticket Booking)
---

# 🧠 Activity Diagram – IRCTC Ticket Booking

## 🧩 Core Idea
Activity Diagram =  
> **Represents step-by-step workflow with actions and decisions**

---

## ⚙️ Steps in Booking Process

1. Login  
2. Search Train  
3. Select Train  
4. Enter Passenger Details  
5. Check Seat Availability  
6. Payment  
7. Ticket Generation  

---

## 🔀 Decision Points
- Seat Available?  
- Payment Successful?  

---

## 📊 Activity Diagram

```mermaid
flowchart TD
    A[Start] --> B[Login]
    B --> C[Search Train]
    C --> D[Select Train]
    D --> E[Enter Details]
    E --> F{Seat Available?}

    F -- Yes --> G[Proceed to Payment]
    F -- No --> H[Show Not Available]

    G --> I{Payment Success?}
    I -- Yes --> J[Generate Ticket]
    I -- No --> K[Payment Failed]

    J --> L[End]
    H --> L
    K --> L
```

---

## 📌 Key Elements
- Start / End  
- Actions  
- Decision nodes  
- Control flow arrows  

---

## 🧠 Final Understanding

Activity Diagram =  
> **Flow of actions and decisions representing system behavior**
