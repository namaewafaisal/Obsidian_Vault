Understood. Keeping it **minimal and to the point**.

---

## ApplicationContext – Main Points Only

### What it is

**ApplicationContext** is Spring’s **IoC container** that:

* Creates objects (beans)
* Injects dependencies
* Manages lifecycle

---

### Why it’s used

Instead of:

```java
new Object();
```

You do:

```java
ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
MyBean obj = context.getBean(MyBean.class);
```

Spring creates and manages the object.

---

### What it does (only core responsibilities)

* Reads configuration (XML / annotations / Java config)
* Creates beans
* Injects dependencies
* Returns beans when requested

---

### Common ways to create ApplicationContext

(Just awareness)

1. **XML-based**

```java
ClassPathXmlApplicationContext
```

2. **Annotation-based**

```java
AnnotationConfigApplicationContext
```

3. **Spring Boot**

```java
SpringApplication.run()
```

(Internally creates ApplicationContext)

---

### Key difference from `new`

| `new`             | ApplicationContext    |
| ----------------- | --------------------- |
| You manage object | Spring manages object |
| Tight coupling    | Loose coupling        |
| No lifecycle      | Full lifecycle        |

---

### One-line summary (remember this)

> **ApplicationContext is the Spring container that creates, wires, and manages objects instead of you using `new`.**

That’s all you need **for now**.

If you want next (short options):

* Bean vs Object
* getBean() flow
* Bean lifecycle (1–2 minutes)
* How Spring Boot hides ApplicationContext


