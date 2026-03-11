# @Autowired
**Phase:** [[README|Phase 2 — Spring Boot Core]]
**Back:** [[🏠 Home]]

---

## Did We Cover @Autowired in Bean Notes?

**No.** The Bean notes covered **XML-based wiring** using `<property>` and `<constructor-arg>`.

That is **Dependency Injection via XML** — not `@Autowired`.

---

## XML Way (What You Learned in Phase 1)

```xml
<bean id="alien" class="Alien">
    <property name="laptop" ref="laptop"/>
</bean>
```

Spring injects dependency using XML instructions.

---

## @Autowired Way (Annotation-Based)

```java
@Component
class Alien {
    @Autowired
    Laptop laptop;  // Spring injects automatically — no XML needed
}
```

- No `<property>` in XML
- Spring finds matching bean by type and injects it

---

## Bean vs @Autowired — The Relationship

| Concept | Role |
|---------|------|
| **Bean** | Object that Spring creates and manages |
| **@Autowired** | Tells Spring HOW to inject dependencies into a bean |

> A bean can exist **without** `@Autowired`.
> `@Autowired` works **only on beans**.

---

## Why It Wasn't Introduced With Beans

The correct learning order is:

```
1. IoC container
2. Bean
3. XML wiring
4. Annotation-based wiring (@Autowired)  ← here
```

This builds the concept correctly from first principles.

---

## @Component, @Service, @Repository, @Controller

These annotations register a class as a Spring bean automatically (no XML needed):

```java
@Component     // generic bean
@Service       // service layer bean
@Repository    // data access bean
@Controller    // web layer bean
```

Spring scans for these during startup and registers them in the container.

---

## Full Annotation-Based Example

```java
@Component
class Laptop {
    public String use() { return "Laptop running"; }
}

@Component
class Alien {
    @Autowired          // Spring finds Laptop bean and injects it
    private Laptop laptop;

    public void code() {
        System.out.println(laptop.use());
    }
}
```

---

## One-Line Summary

> **Beans define WHAT Spring creates. @Autowired defines HOW Spring injects dependencies into those beans.**

---

**Prev →** [[03-Spring-Boot-Initialization|Spring Boot Initialization]]
**Next Phase →** [[../Phase-3-REST-API/README|Phase 3 — REST API]]
