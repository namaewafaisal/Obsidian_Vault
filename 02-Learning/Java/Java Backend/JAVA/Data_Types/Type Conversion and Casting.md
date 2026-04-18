# Type conversion in Java

There are 2 types in Java
### **1. Implicit Conversion (Type Promotion)**

Happens **automatically** when a smaller type is used with a bigger type in an arithmetic operation.  
The smaller value is _promoted_ to the bigger type **before** the calculation.
```java
int a = 5;
double sum = a + 5.3; // a (int) is promoted to 5.0 (double)
```

🧠 Example:  
`byte → short → int → long → float → double`

---
### **2. Explicit Conversion (Type Casting)**

Happens **manually** when assigning a bigger type value to a smaller type.  
Java warns that data may be lost, so we must **explicitly cast** it.
```java
int a = 5;
byte b = (byte) a; // a is converted to byte type and stored in b
```

---
### **How Casting Works Internally**

When converting from a bigger to a smaller type,  
Java stores the **remainder** after dividing the original value by the range of the new type.
```java
int a = 257;
byte b = (byte) a; // 257 % 256 = 1
System.out.println(b); // Output: 1
```
---
