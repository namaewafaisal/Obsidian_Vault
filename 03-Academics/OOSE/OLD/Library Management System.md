---
topic: Library Management System (OOAD)
---

# 🧠 Library Management System (OOAD)

## 🧩 Problem Statement
System to manage:
- Books
- Users
- Issue/Return process
- Fine handling  

---

## 👤 Actors
- Student / Member  
- Librarian  

---

## ⚙️ Use Cases

### Student
- Search Book  
- Borrow Book  
- Return Book  
- View Account  

### Librarian
- Add Book  
- Remove Book  
- Issue Book  
- Collect Fine  

---

## 🔗 Use Case Relationships

- **«include»** → mandatory  
  - Borrow Book → Check Availability  

- **«extend»** → optional  
  - Return Book → Pay Fine  

- **Generalization**
  - User → Student, Librarian  

---

## 📊 Use Case Diagram

```mermaid
flowchart LR
    Student --> Search
    Student --> Borrow
    Student --> Return

    Librarian --> Add
    Librarian --> Remove
    Librarian --> Issue

    Borrow --> CheckAvailability
    Return --> PayFine
```

---

## 🧱 Classes (OOD)

- **User (id, name)**
- **Student extends User**
- **Librarian extends User**

- **Book (id, title, author, availability)**

- **Transaction (issueDate, returnDate)**

---

## 🔗 Relationships
- Student → borrows Book  
- Librarian → manages Book  
- Transaction → connects User & Book  

---

## 📊 Class Diagram

```mermaid
classDiagram
    class User {
        id
        name
    }

    class Student
    class Librarian

    User <|-- Student
    User <|-- Librarian

    class Book {
        id
        title
        author
        available
    }

    class Transaction {
        issueDate
        returnDate
    }

    Student --> Transaction
    Transaction --> Book
    Librarian --> Book
```

---

## 🧠 Final Understanding

> Convert real-world system → Actors → Use Cases → Relationships → Classes → UML diagrams
