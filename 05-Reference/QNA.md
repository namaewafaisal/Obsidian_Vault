---
tags:
  - qna
---
# What is Singleton?
A **singleton** is a design pattern in object-oriented programming that restricts a class to having only one instance throughout the application's lifecycle, while providing a global access point to that instance. This pattern is useful for managing shared resources, such as database connections or logging services.
Example: Beans managed by spring. Only one instance. 

# What is Factory Method
**The Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, allowing subclasses to alter the type of objects that will be created.** For example, in Java, a `MotorVehicleFactory` class can create different types of vehicles like `Car` or `Truck` based on input parameters, without the client needing to know the specific class being instantiated.