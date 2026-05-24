# Software Engineering — Exam Priority Checklist (v2)

> **Legend:** 🔴 Most Priority · 🟡 Medium Priority · 🟢 Least Priority
> Priority = exam frequency × concept dependency weight. Items marked **`[implied]`** are not named in the syllabus but are prerequisite knowledge baked into the stated topic — you need them to actually answer the question, not just define the term.

---

## Unit I — Software Process & Agile Development

### 🔴 Most Priority
- [ ] **Software Process** — definition, key activities (spec, design, validation, evolution); know the 4 activities cold
  - `[implied]` What a process model *is* vs what a methodology is — examiners conflate these, you shouldn't
- [ ] **Perspective Process Models** — Waterfall (phases + limitations), Incremental, RAD; be able to draw and critique each
  - `[implied]` Phase sequencing logic — why each phase feeds the next (output of one = input of next)
  - `[implied]` Trade-offs vocabulary: cost, risk, flexibility, customer visibility — used to compare models
- [ ] **Agile Manifesto** — 4 values, 12 principles; examiners love quoting these directly
- [ ] **Extreme Programming (XP)** — core practices (pair programming, TDD, CI, refactoring, small releases, on-site customer); XP values
  - `[implied]` What TDD means mechanically: write test → fail → write code → pass → refactor (red-green-refactor cycle)
  - `[implied]` What CI means: frequent integration to shared repo, automated build triggers — not just a buzzword
- [ ] **XP Process flow** — planning game → small release → metaphor → TDD cycle; draw the loop
  - `[implied]` User story format: "As a [role], I want [feature], so that [benefit]" — XP's requirement unit

### 🟡 Medium Priority
- [ ] **Specialized Process Models** — Component-based, Formal methods, Aspect-oriented; one solid paragraph per model
  - `[implied]` What a software component is (reusable, independently deployable unit) — needed to explain CBSE
- [ ] **Introduction to Agility** — why agile? Limitations of plan-driven; cost of change curve
- [ ] **Agile Process in general** — Scrum as complementary context (sprint, backlog, velocity) — compare/contrast with XP
  - `[implied]` Difference between Scrum (framework) and XP (engineering practices) — common trap question
- [ ] **Case Study** — map XP practices onto a given scenario; apply, don't define

### 🟢 Least Priority
- [ ] **Software Engineering definitions** — Sommerville vs Pressman definitions; 2-mark territory only
- [ ] **Historical context / SE evolution** — NATO 1968, software crisis; background filler

---

## Unit II — Requirements Analysis & Specification

### 🔴 Most Priority
- [ ] **Requirements gathering & analysis** — elicitation techniques (interview, observation, prototyping, JAD); functional vs non-functional
  - `[implied]` Difference between functional requirement ("system shall do X") and non-functional ("system shall do X within Y ms") — phrasing matters
  - `[implied]` Requirement properties: complete, consistent, unambiguous, verifiable, traceable — same as SRS characteristics, intentional overlap
- [ ] **Software Requirement Specification (SRS)** — IEEE 830 structure; write a mini-SRS section when asked
  - `[implied]` IEEE 830 sections in order: Introduction → Overall Description → Specific Requirements → Appendices
  - `[implied]` How to write a well-formed requirement statement (shall, not should; measurable; one requirement per statement)
- [ ] **Use Case Model** — use case diagram + use case description template
  - `[implied]` Actor types: primary (initiates), secondary (supports); system boundary box
  - `[implied]` `<<include>>` vs `<<extend>>`: include = mandatory reuse, extend = optional conditional behaviour — this distinction is a trap question
  - `[implied]` Use case description fields: Name, Actor, Precondition, Main Flow, Alternate Flow, Postcondition
- [ ] **Class Diagrams** — attributes, methods, all relationship types, multiplicity
  - `[implied]` OOP concepts underpinning the notation: class vs object, encapsulation, inheritance
  - `[implied]` Relationship distinctions to draw correctly: association (line), aggregation (hollow diamond), composition (filled diamond), inheritance (hollow triangle arrow), dependency (dashed arrow)
  - `[implied]` Multiplicity notation: 1, 0..1, 1..*, 0..*, m..n — read as "one X has how many Y"
  - `[implied]` Visibility symbols: + public, - private, # protected, ~ package
- [ ] **DFD (Data Flow Diagram)** — Level 0 (context), Level 1, Level 2; Yourdon-DeMarco symbols
  - `[implied]` The 4 symbols and when each is used: circle/bubble (process), rectangle (external entity), open-ended rectangle (data store), arrow (data flow)
  - `[implied]` Levelling/decomposition: Level 0 = one bubble for the whole system; Level 1 = expand that bubble into sub-processes; Level 2 = expand a Level 1 process further
  - `[implied]` Balancing rule: inputs/outputs at Level N must match inputs/outputs of same process at Level N+1

### 🟡 Medium Priority
- [ ] **Interaction Diagrams (Sequence + Communication)** — lifeline, activation bar, message types
  - `[implied]` Message arrow styles: solid filled (synchronous call), dashed (return), half-arrowhead (asynchronous)
  - `[implied]` When to use sequence (time-ordered, easier to read flow) vs communication (relationship emphasis)
  - `[implied]` Combined fragments: alt (if-else), opt (optional), loop, par — examiners test reading these
- [ ] **Activity Diagrams** — swimlanes, fork/join, decision nodes
  - `[implied]` Fork bar (thick horizontal) = split into parallel flows; join bar = synchronise parallel flows back — don't confuse with decision diamond
  - `[implied]` Swimlane = responsibility partition; each lane = one actor/component
- [ ] **State Chart Diagrams** — states, transitions, events, guards, entry/exit actions
  - `[implied]` Transition syntax: `event [guard] / action` — all three parts are optional but you need to know placement
  - `[implied]` Difference from activity diagram: statechart = object lifecycle; activity = workflow/process flow
- [ ] **Finite State Machines** — formal definition (Q, Σ, δ, q₀, F), DFA vs NFA
  - `[implied]` How to draw a state transition diagram and construct a transition table from it
  - `[implied]` DFA: one transition per (state, input); NFA: multiple possible transitions — and why DFA is easier to implement
- [ ] **Petri Nets** — places, transitions, tokens, firing rule
  - `[implied]` Firing rule: transition fires only when ALL input places have ≥ 1 token; tokens consumed from inputs, produced at outputs
  - `[implied]` How Petri nets model concurrency and conflict — the key reason they're used over FSMs

### 🟢 Least Priority
- [ ] **Formal System Specification** — Z notation basics, pre/post conditions; definition-level only
- [ ] **Functional Modelling** — how DFD fits into structured analysis; usually merged with DFD question
- [ ] **CASE Tools** — upper/lower/integrated CASE, examples (Rational Rose, EA); 2-mark factual

---

## Unit III — Software Design

### 🔴 Most Priority
- [ ] **Coupling** — 6 types in order worst→best: content > common > external > control > stamp > data
  - `[implied]` What "module" means in this context: a function, class, or component — the unit being coupled
  - `[implied]` How to identify coupling type from a code snippet: does Module A reach inside B's internals? (content); share global? (common); pass control flag? (control); pass whole struct when only one field needed? (stamp); pass only needed data? (data)
- [ ] **Cohesion** — 7 types in order worst→best: coincidental < logical < temporal < procedural < communicational < sequential < functional
  - `[implied]` How to identify cohesion type: why are these functions/statements together in this module? Same time? (temporal) Same data? (communicational) Output of one feeds next? (sequential) Single well-defined task? (functional)
- [ ] **Functional Independence** — defined via low coupling + high cohesion; why it matters for maintainability
- [ ] **MVC (Model-View-Controller)** — components, responsibilities, data flow, advantages
  - `[implied]` Which layer does what: Model = data + business logic; View = UI rendering; Controller = input handling + glues M and V
  - `[implied]` Why separation matters: View can change without touching Model; multiple Views for same Model
- [ ] **Layered Architecture** — strict vs relaxed; OSI as canonical example; draw n-tier
  - `[implied]` Strict layering: layer N can only call layer N-1; relaxed: can skip layers — trade-off is performance vs maintainability
- [ ] **Client-Server Architecture** — thin vs thick client, 2-tier vs 3-tier
  - `[implied]` Where business logic lives in each tier variant — that's what examiners test, not just the names

### 🟡 Medium Priority
- [ ] **Design Process** — architectural → interface → component → data design phases
- [ ] **Design Concepts** — abstraction, refinement, modularity, information hiding, encapsulation
  - `[implied]` Information hiding ≠ encapsulation: hiding is the design principle (hide decision); encapsulation is the OOP mechanism that implements it
- [ ] **Observer Pattern** — subject/observer, push vs pull, event-driven systems
  - `[implied]` UML for Observer: Subject has `attach()`, `detach()`, `notify()`; Observer has `update()`; ConcreteSubject and ConcreteObserver implement them
  - `[implied]` OOP prerequisites: interface/abstract class (Observer is always an interface or abstract), polymorphism (notify() calls update() on all registered observers)
- [ ] **Strategy Pattern** — algorithm family, encapsulate each, make interchangeable
  - `[implied]` UML: Context holds a reference to Strategy interface; ConcreteStrategyA/B implement it; Context delegates to it
  - `[implied]` Why polymorphism is the mechanism: same method call, different behaviour at runtime
- [ ] **Facade Pattern** — simplify complex subsystem interface
  - `[implied]` Facade doesn't add functionality — it just provides a simpler entry point; the subsystem classes still exist behind it
- [ ] **Adapter Pattern** — class adapter vs object adapter
  - `[implied]` Class adapter uses inheritance (Adapter extends Adaptee, implements Target); object adapter uses composition (Adapter holds Adaptee reference) — composition preferred
- [ ] **Publish-Subscribe** — publisher, subscriber, event bus/broker; vs Observer distinction
  - `[implied]` Key difference from Observer: publisher and subscriber don't know each other (fully decoupled via broker); in Observer, Subject holds references to Observers directly
- [ ] **Pipe and Filter Architecture** — filters, pipes, data transformation; Unix pipeline as example
  - `[implied]` Each filter is independent, stateless, reads from stdin-equivalent, writes to stdout-equivalent — composability is the point

### 🟢 Least Priority
- [ ] **Command Pattern** — encapsulate request as object; undo/redo support
  - `[implied]` UML: Command interface with `execute()`; ConcreteCommand holds Receiver reference; Invoker calls command
- [ ] **Proxy Pattern** — virtual, remote, protection proxy types
- [ ] **Tiered Architecture** — distinction from layered (physical deployment vs logical separation)
- [ ] **UI Design principles** — user familiarity, consistency, minimal surprise, recoverability; 2-mark
- [ ] **Case Study** — apply MVC or layered to a described system

---

## Unit IV — Software Testing & Maintenance

### 🔴 Most Priority
- [ ] **Black Box Testing — Equivalence Partitioning (EP)**
  - `[implied]` How to identify valid and invalid equivalence classes from a spec or range
  - `[implied]` Rule: test one value per class (any value in a class is assumed equivalent); pick the middle of valid ranges, any value for invalid
- [ ] **Black Box Testing — Boundary Value Analysis (BVA)**
  - `[implied]` BVA test points for a range [a, b]: a-1, a, a+1, b-1, b, b+1 — six points per boundary
  - `[implied]` BVA is not an alternative to EP — they're used together; BVA selects *which* values within/around the EP classes
- [ ] **White Box Testing — Control Flow Graph (CFG)**
  - `[implied]` How to convert code to CFG: each statement = node; each branch = edge; if/else = two outgoing edges; loop = back edge
  - `[implied]` Node types to draw: process node (rectangle), decision node (diamond/circle with two outgoing edges)
- [ ] **White Box Testing — Cyclomatic Complexity V(G)**
  - `[implied]` Three equivalent formulas: V(G) = E − N + 2 · P; V(G) = number of regions in planar graph; V(G) = number of predicates + 1
  - `[implied]` What P is: number of connected components (almost always 1 for a single function)
  - `[implied]` V(G) = minimum number of independent paths to test
- [ ] **Coverage criteria** — statement, branch, path, condition coverage; know the hierarchy
  - `[implied]` Hierarchy: path coverage ⊇ branch coverage ⊇ statement coverage (stronger subsumes weaker)
  - `[implied]` 100% branch coverage does NOT guarantee 100% path coverage — examiners test this
- [ ] **Unit Testing** — scope, stubs, drivers, test harness
  - `[implied]` Stub = dummy replacement for a callee (called module not yet written); Driver = dummy caller that invokes the module under test
- [ ] **Integration Testing** — top-down, bottom-up, big-bang, sandwich
  - `[implied]` Top-down needs stubs (testing upper modules first, lower not ready); bottom-up needs drivers (testing lower modules first, upper not ready)
  - `[implied]` Sandwich = hybrid: top-down for upper layers, bottom-up for lower layers simultaneously

### 🟡 Medium Priority
- [ ] **System Testing** — functional, performance, stress, volume, security, acceptance (UAT vs alpha/beta)
  - `[implied]` Alpha = tested by internal users at dev site; Beta = tested by external users at customer site
- [ ] **Regression Testing** — rerun tests after any change to ensure nothing broke
  - `[implied]` Not a new type of testing — it's *when* and *why* you rerun existing tests; triggered by bug fix, new feature, refactor
- [ ] **Debugging** — debugging vs testing; strategies (brute force, backtracking, cause elimination)
  - `[implied]` Testing finds that a defect exists; debugging finds *where* it is — these are separate activities
- [ ] **Symbolic Execution** — path conditions, symbolic state vs concrete values
  - `[implied]` Instead of running with x=5, you run with x=sym and accumulate constraints along the path; a path condition is the conjunction of all branch conditions taken
  - `[implied]` Limitation: path explosion for loops, and constraint solving (SMT) is expensive
- [ ] **Program Analysis** — static vs dynamic
  - `[implied]` Static: analyse source/bytecode without executing (linting, data flow, control flow analysis); Dynamic: analyse during execution (profiling, coverage, memory tracking)

### 🟢 Least Priority
- [ ] **Model Checking** — state space exploration, temporal logic (CTL/LTL), tools (SPIN, NuSMV)
  - `[implied]` Property types in temporal logic: safety ("bad thing never happens"), liveness ("good thing eventually happens")
- [ ] **Case Study** — apply BBT+WBT to a given function; straightforward if numericals are practiced

---

## Unit V — Project Management

### 🔴 Most Priority
- [ ] **Project Scheduling — CPM/PERT**
  - `[implied]` Network diagram construction: nodes = activities or events; arrows = dependencies; must draw before calculating
  - `[implied]` Forward pass: EST = max(EFT of all predecessors); EFT = EST + duration
  - `[implied]` Backward pass: LFT = min(LST of all successors); LST = LFT − duration
  - `[implied]` Float/Slack = LST − EST = LFT − EFT; critical path = all activities with float = 0
  - `[implied]` PERT uses 3 estimates: optimistic (a), most likely (m), pessimistic (b); Expected = (a + 4m + b) / 6
- [ ] **Software Configuration Management (SCM)** — baseline, configuration items, version control, change control
  - `[implied]` What a baseline is: a snapshot of a configuration item at a defined point, formally approved and change-controlled
  - `[implied]` SCM activities: identification → version control → change control → auditing → reporting
- [ ] **DevOps: Deployment Pipeline** — CI/CD stages; blue-green and canary deployments
  - `[implied]` CI = integrate and build on every commit, automated tests run immediately; CD (Delivery) = pipeline ready to deploy; CD (Deployment) = auto-deploys to production
  - `[implied]` Blue-green: two identical production environments; switch traffic from blue to green; instant rollback by switching back
  - `[implied]` Canary: route small % of traffic to new version first; monitor; gradually increase or rollback
- [ ] **DevOps: Overall Architecture** — Dev+Ops integration, feedback loops, IaC, monitoring
  - `[implied]` The infinity loop diagram: Plan → Code → Build → Test → Release → Deploy → Operate → Monitor → (back to Plan)

### 🟡 Medium Priority
- [ ] **Estimation — COCOMO**
  - `[implied]` Basic COCOMO formula: E = a × KDLOC^b (effort in person-months); D = c × E^d (duration); P = E / D (people)
  - `[implied]` Three modes: Organic (small, familiar), Semi-detached (medium, mixed), Embedded (large, constrained) — each has different a,b,c,d constants
- [ ] **Estimation — Function Point Analysis**
  - `[implied]` Count: external inputs, outputs, inquiries, internal files, external interfaces — each weighted by complexity
  - `[implied]` FP is language-independent; COCOMO is SLOC-based and language-dependent — that's why FP exists
- [ ] **DevOps: Motivation** — wall of confusion, CALMS framework
- [ ] **Cloud as a Platform** — IaaS/PaaS/SaaS; cloud-native; elasticity
  - `[implied]` IaaS = you manage OS up; PaaS = you manage app up; SaaS = you just use it — know the responsibility split
- [ ] **DevOps: Operations** — monitoring (logs, metrics, traces = "three pillars of observability"), SLI/SLO/SLA
  - `[implied]` SLI = measured metric (e.g., latency p99); SLO = target for that metric (e.g., < 200ms); SLA = contractual consequence if SLO is breached
- [ ] **DevOps Tools** — Jenkins/GitHub Actions, Docker/Kubernetes, Ansible/Terraform, Prometheus/Grafana
  - `[implied]` Know which tool category each belongs to — CI runner, container, orchestrator, IaC, monitoring — not just names

### 🟢 Least Priority
- [ ] **Risk Management** — risk identification, analysis (probability × impact), planning, monitoring
- [ ] **DevOps: Build and Test stages in pipeline** — test pyramid (unit → integration → E2E)
  - `[implied]` Test pyramid: many unit tests (fast, cheap), fewer integration, even fewer E2E (slow, expensive)
- [ ] **Case Study** — draw a deployment pipeline for a described system

---

## Cross-Unit Dependency Map

```
Unit I (Process models)
  └──▶ Unit II (Requirements + UML)
  │        └── SRS feeds design inputs
  │        └── Use Case → Class Diagram → Sequence Diagram (build these in order)
  └──▶ Unit III (Design)
           └── Coupling/Cohesion need "module" concept from Unit I process thinking
           └── Design Patterns need OOP concepts (interface, polymorphism, composition)
           └──▶ Unit IV (Testing)
                    └── CFG drawn from design/code artifacts
                    └── Integration testing strategy depends on architecture (III)
                    └──▶ Unit V (Management)
                             └── PM wraps all phases
                             └── DevOps = Unit IV (testing pipeline) + Unit III (architecture) + deployment
```

**Cramming order if time is short:**
1. Coupling/Cohesion types + identification (III) — highest examiner hit rate
2. CFG → V(G) numerical + BBT EP/BVA numerical (IV) — guaranteed marks
3. CPM/PERT numerical (V) — guaranteed marks
4. Use Case diagram + Class diagram (II) — draw questions
5. Process model comparison table: Waterfall vs Incremental vs Agile (I)
6. XP practices + MVC + Layered + CI/CD pipeline (I, III, V)

---

## Quick-Hit Numericals Checklist

- [ ] **V(G)** — given code or CFG: draw graph, apply E − N + 2, list independent paths
- [ ] **EP + BVA** — given a spec with ranges: list valid/invalid classes, pick BVA points for each boundary
- [ ] **CPM/PERT** — draw network, forward pass, backward pass, identify critical path, compute float for each activity
- [ ] **COCOMO** — given KDLOC and mode: compute effort (E), duration (D), team size (P)
- [ ] **DFD** — given a problem statement: draw Level 0 (context diagram) then Level 1 (expand the system process); verify balancing
- [ ] **Class Diagram** — given a scenario: identify classes, attributes, methods, and all relationship types with correct notation and multiplicity