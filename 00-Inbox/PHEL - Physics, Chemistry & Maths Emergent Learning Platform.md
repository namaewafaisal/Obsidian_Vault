# PHEL — Physics, Chemistry & Maths Emergent Learning Platform

> *"Not a simulation of scenarios. A simulation of reality, from which scenarios emerge."*

**Status:** Idea / Pre-draft
**Domain:** Educational Technology, Computational Physics, Open Source
**Author:** Faisal
**Created:** 2025
**Last Updated:** 2026-06-10
**Tags:** #idea #edtech #simulation #physics #chemistry #open-source #long-term #deferred

---

## 0. TL;DR

PHEL is a proposed open-source, emergent physics and chemistry simulation platform designed for high school students, initially scoped to the Tamil Nadu State Board syllabus (Classes 11–12). Unlike existing tools (PhET, Labster), PHEL is not a collection of hardcoded scenario simulations. It is a unified object-oriented physics engine where matter, forces, and interactions are modelled from first principles — and outcomes *emerge* from those properties, not from scripted functions.

The long-term vision: a Minecraft-like sandbox where a student can build a rocket, synthesise a compound, or model a thermodynamic system — not because the developer scripted those outcomes, but because the underlying physics is accurate enough that reality reproduces itself in simulation.

---

## 1. Motivation

### 1.1 The Problem with Current EdTech

Current educational simulation tools fall into two failure modes:

**Failure Mode A — Scenario-scripted simulations (PhET, OASP)**
Each simulation is a standalone, handcrafted interactive. A student can manipulate sliders and observe pre-programmed outcomes. There is no unified engine. Two simulations cannot interact. Emergent discovery is impossible. PhET's own design philosophy acknowledges this — each sim is *"an individual exploratory environment"*, not a connected world.

**Failure Mode B — Engagement-optimised platforms (BYJU's, Vedantu)**
Massive budgets, VC-funded, dopamine-loop optimised. Maximise watch time and subscription retention. Accuracy and depth are secondary to engagement metrics. These platforms will not build PHEL because the ROI is not there for accurate emergent simulation targeting government school students.

### 1.2 The Gap

No existing platform combines:
- Emergent (not scripted) physics and chemistry interactions
- High school curriculum alignment (Indian state boards)
- Open source, free for students
- Accessible on low-end hardware
- Tamil-first localisation

This gap is structural — not accidental. It exists because it is not commercially attractive. Only an open source, mission-driven project can fill it.

### 1.3 Inspiration

| Project | What it proves |
|---|---|
| Kerbal Space Program | Emergent orbital mechanics can teach students better than textbooks. 8M+ players, 300M+ hours. |
| KerbalEdu | Institutional SaaS on top of a physics game is a viable model. Schools pay; students play. |
| Blender | Open source can dominate an entire professional domain. Community + foundation model works. |
| Git | Infrastructure-level open source needs no monetisation to achieve universal adoption. |
| Minecraft (Education Edition) | Sandbox emergent environments have legitimate pedagogical value at scale. |
| AlphaFold / BioEmu | Accurate molecular simulation at accessible compute costs is now achievable. |

---

## 2. Core Architecture Concept

### 2.1 The OOP Physics Engine (Central Thesis)

The foundational architectural decision: **no hardcoded interaction functions.**

In a traditional simulation:
```
function hydrogenMeetsOxygen() {
    return water;
}
```

In PHEL:
```java
class Atom {
    int atomicNumber;
    double atomicMass;
    double electronegativity;
    int valenceElectrons;
    double ionisationEnergy;
    double atomicRadius;
    double electronAffinity;
    // ...
}
```

When two `Atom` objects come within bonding distance, the engine evaluates:
- Electronegativity difference → bond polarity (ionic / covalent / metallic)
- Valence electron availability → bond order
- Energy delta → whether bond formation is thermodynamically favourable

Water does not form because `hydrogenMeetsOxygen()` is called. Water forms because two hydrogen atoms and one oxygen atom, given their correct quantum-chemical attributes, find a lower energy state bonded than unbonded. The engine computes this. The outcome is *emergent*.

This is the same principle behind molecular dynamics (MD) simulation in research — no AI, no hardcoded functions, just physics equations applied to objects with real properties, integrated forward in time.

### 2.2 Abstraction Level (The Key Engineering Decision)

Full quantum-level simulation (ab initio / DFT) is computationally intractable for real-time interactive use. The engine must find the right abstraction:

```
Ab initio QM          ← too expensive (supercomputer-scale)
Semi-empirical QM     ← borderline feasible
Classical MD          ← feasible, used in research tools
Coarse-grained MD     ← real-time feasible, some accuracy loss
Hardcoded functions   ← PhET's approach (not PHEL)
```

**Target abstraction: Classical MD with selected semi-empirical corrections for key reactions.**

This gives:
- Bond formation/breaking based on force fields (AMBER, CHARMM-style, simplified)
- Newtonian mechanics for macro-level physics (projectile, orbital, fluid)
- Thermodynamic state functions for temperature/pressure effects
- Accurate enough for curriculum-level intuition
- Fast enough for browser/mid-range device real-time rendering

### 2.3 Object Hierarchy (Proposed)

```
PhysicsObject (base)
├── Particle
│   ├── Atom { atomicNumber, mass, charge, valenceElectrons, ... }
│   └── Ion { parentAtom, netCharge }
├── Molecule { bonds[], atoms[], geometry }
├── MacroObject { mass, volume, surfaceArea, material }
│   ├── Solid
│   ├── Fluid
│   └── Gas
├── Force { magnitude, direction, type }
│   ├── GravitationalForce
│   ├── ElectromagneticForce
│   ├── NormalForce
│   └── DragForce
└── Field { type, gradientFunction }
    ├── GravitationalField
    └── ElectromagneticField
```

Every object carries its real attributes. Every interaction is computed from those attributes. No special cases. No hardcoded outcomes.

### 2.4 Interaction Engine

```
InteractionEngine.tick(dt):
    for each pair of objects within interaction radius:
        compute force vectors from field equations
        compute energy delta for potential bond formation
        if bond formation is energetically favourable:
            form Bond, update molecular graph
        apply Newton's second law to update velocities
        integrate positions (Verlet / Runge-Kutta)
        update thermodynamic state variables
```

Key insight: **this loop is the entire chemistry and physics engine.** Every experiment, every reaction, every physical phenomenon is just this loop running with different initial conditions.

---

## 3. What Already Exists

### 3.1 Educational Simulation Platforms

| Platform | Type | Emergent? | Open Source | Curriculum-aligned | Mobile-friendly |
|---|---|---|---|---|---|
| PhET (CU Boulder, 2002) | Scenario simulations | No | Partial | Partially | Yes |
| Labster | Virtual lab | No | No | Yes | No |
| OASP Singapore | EJS-based sims | No | Yes | No | Partial |
| oPhysics | Interactive HTML5 | No | Unknown | No | Yes |
| KSP / KerbalEdu | Physics sandbox | Partially (mechanics only) | No | No | No |
| Algodoo | 2D physics sandbox | Yes (mechanics) | No | No | No |

**None** of these combine emergent chemistry + physics + curriculum alignment + open source.

### 3.2 Research-Grade Simulation (The Science Behind PHEL)

These are not educational tools, but they validate the underlying approach:

**Molecular Dynamics:**
- GROMACS, LAMMPS, NAMD — open source MD engines used in research
- Simulate millions of atoms using classical force fields
- Real-time is not their goal, but their approach is directly relevant

**AI-accelerated MD (2024–2025):**
- **BioEmu** (Microsoft, 2025) — diffusion model simulating protein equilibrium ensembles at 1 kcal/mol accuracy on a single GPU, 4–5 orders of magnitude faster than classical MD
- **CGSchNet** (Freie Universität Berlin, July 2025, *Nature Chemistry*) — machine-learned coarse-grained model capturing protein folding dynamics without explicit solvent modelling
- **AlphaFold2/3** (DeepMind) — atomic-level protein structure prediction, now integrated into pharmaceutical pipelines at Pfizer, Novartis, AstraZeneca

**Key takeaway:** The research world is solving the *compute cost* problem for accurate molecular simulation using AI. PHEL can benefit from this as a tailwind — not by using LLMs for simulation, but by potentially using learned force fields (neural network potentials) to approximate expensive QM calculations in real time.

### 3.3 Closest Analogue: Kerbal Space Program

KSP is the existence proof for PHEL's thesis. It demonstrates:
- Emergent physics can teach better than textbooks
- 8M+ players learned orbital mechanics through play, not instruction
- An educational institutional tier (KerbalEdu) can be monetised on top of a free physics engine
- A modding community extends capability far beyond the original developers

**KSP's gap that PHEL fills:** KSP deliberately excludes chemistry. Fuel is abstracted. Combustion is not modelled. Atmospheric chemistry is absent. PHEL covers exactly what KSP left out.

---

## 4. AI's Role — Explicitly Scoped

### 4.1 AI Does NOT belong in the simulation core

The simulation engine must be deterministic and physics-based. Reasons:

1. **Accuracy** — LLMs do not know chemistry; they have read about it. An LLM reasoning about a reaction can hallucinate. F=ma never hallucitates.
2. **Trust** — If a student learns a wrong reaction outcome from an AI-generated simulation, they have learned something false. Educational tools have a higher accuracy bar than general assistants.
3. **Auditability** — A physics engine's output can be verified against known experimental data. An LLM's output cannot be audited in the same way.
4. **Reproducibility** — Same initial conditions must always produce the same outcome. LLMs are stochastic.

### 4.2 AI DOES belong in the surrounding layers

| Layer | AI Role |
|---|---|
| Assessment | Observe student behaviour in simulation, generate targeted questions |
| Explanation | Explain *why* the simulation behaved as it did, in Tamil or English |
| Hint system | Socratic nudges based on what the student is stuck on |
| Curriculum mapping | Suggest next simulation based on demonstrated understanding gaps |
| Anomaly detection | Flag unusual compound exploration patterns (security) |
| Content generation | Auto-generate worksheet questions from simulation parameters |

**Mental model: AI is the teacher's assistant. The physics engine is the ground truth.**

---

## 5. Product Vision

### 5.1 User Tiers

```
Free Tier (students, public)
├── Full simulation engine
├── Curriculum-scoped compound library (TN board 11-12)
├── Save/share experiments
└── Community experiments browser

Institutional Tier (schools — ₹1000–3000/student/year)
├── Everything in Free
├── Teacher dashboard
├── Student progress tracking
├── Exam and assessment module
├── Broader compound/reaction library
├── Priority support
└── Custom curriculum mapping

Researcher Tier (verified academics)
├── Full compound library
├── Full audit trail
├── API access to simulation engine
└── Extended molecule classes
```

### 5.2 Revenue Model

Open core + institutional SaaS. Inspired by:
- **Blender Foundation** — donations + corporate sponsors fund core team
- **KerbalEdu** — paid educational edition on top of free game
- **Red Hat / HashiCorp** — open source core, paid enterprise tier

Rough Tamil Nadu addressable market:
- ~4.5 million students in Classes 11–12 across TN
- Even 1% institutional adoption = 45,000 students
- At ₹1500/student/year = ₹6.75 crore ARR
- Enough to fund a 10–15 person core team

### 5.3 VR/AR Layer (v3+)

When the physics engine is accurate and the rendering layer becomes spatial:
- Students manipulate molecules with hands, feel electron repulsion via haptics
- Virtual chemistry lab — zero risk of burns, unlimited reagents
- Rural student with a ₹15,000 headset gets IIT-quality lab experience
- Hardware constraint: must degrade gracefully to 2D on low-end Android

The content layer (PHEL) is the bottleneck in this future, not the hardware. Meta Quest 3, cheap Android AR — hardware is converging. PHEL provides what they lack: accurate, curriculum-aligned scientific content.

---

## 6. Risks and Open Problems

### 6.1 Technical Risks

| Risk | Severity | Mitigation |
|---|---|---|
| Classical MD too expensive for real-time browser use | High | Neural network potentials (NNPs) as force field approximation; WebGPU acceleration |
| Abstraction level loses accuracy at curriculum-critical points | Medium | Validate against known experimental outcomes; curriculum experts in review loop |
| Quantum effects not capturable without QM (electron orbitals, spectroscopy) | Medium | Scope v1 to classical-safe curriculum topics; quantum topics deferred |
| Contributor submits subtly wrong physical constants | High | Domain expert (physicist/chemist) mandatory in review pipeline for engine PRs |

### 6.2 Security and Dual-Use Risk (Critical)

This is the central ethical tension. An accurate emergent chemistry engine is, by construction, also a chemical synthesis exploration tool.

**Threat tiers:**

| Tier | Description | Risk Level | Mitigation |
|---|---|---|---|
| T1 | Basic curriculum reactions (acid-base, combustion) | Low | Already in textbooks. Simulation provides no uplift. |
| T2 | Industrial chemical processes | Low-Medium | Requires real equipment/precursors. Simulation alone insufficient. |
| T3 | Novel toxic compound synthesis pathways | High | Compound library gating; institutional-only access to extended library |
| T4 | Bioweapon / nerve agent design | Critical | Hard-coded blocklist of precursor combinations. Never simulatable regardless of tier. |

**Mitigation architecture:**
- Free tier: compound library limited to TN board syllabus scope
- Institutional tier: broader access, all interactions logged, anomaly detection (AI layer)
- Researcher tier: full access, verified credentials, full audit trail, legal agreement
- Open source fork problem: forking removes gates. Mitigated by community norms, contributor license agreement with explicit dual-use prohibition, and the fact that the engine alone without the compound data library is less dangerous

**The honest unresolved problem:** If the engine is open source and sufficiently accurate, a determined actor can fork it and remove all gates. There is no complete technical solution. This is the same problem as encryption export controls — the community norm and legal framework are the primary deterrent, not the technical constraint.

### 6.3 Legal and Liability Risks

- **Accuracy liability:** If a student acts on simulation output in a real lab and is harmed, PHEL could be exposed. Mitigation: explicit disclaimers, "for learning intuition only" framing, not "laboratory procedure guide"
- **Curriculum change risk:** TN board syllabus revisions break tightly coupled content. Mitigation: engine is curriculum-agnostic; syllabus is a mapping layer on top
- **Institutional procurement:** Government school adoption requires tender processes, committee approvals. Grassroots use ≠ institutional funding. Plan for multi-year gap.

### 6.4 Adoption Risks

- **Teacher gatekeeping:** Teachers are the actual gatekeepers of classroom attention. A tool students love but teachers distrust never gets used. Teacher training and community are non-negotiable.
- **Hardware stratification:** VR/AR widens access inequality if baseline requires modern hardware. Graceful degradation is a core requirement, not a nice-to-have.
- **Language:** Tamil-first, not English-first. Localisation is a v1 requirement for the target demographic, not v2. Technical terminology requires subject-matter educator input — not machine translation.
- **Contributor quality:** Physics simulation bugs are invisible to software-only reviewers. Domain expert review is required for engine PRs.

### 6.5 Sustainability Risks

- **Founder continuity:** A decade-long project needs succession planning. If primary contributor disappears, what happens to 100,000 dependent students? Foundation structure, contributor community depth, and documented governance are essential.
- **Contributor compensation:** Community contributors whose code generates institutional revenue receive no direct payment. Models to consider: Blender Fund (donations fund core team), bounty system for scoped issues (₹5,000–10,000 per merged feature), employer sponsorship (companies that use the tool fund contributors), and reputation/portfolio value (valid for early-career contributors only).

---

## 7. Roadmap (Hypothetical, Decade-Scale)

### Phase 0 — Foundation (Personal, Pre-employment)
- [ ] Write formal spec for OOP engine (this note is a start)
- [ ] Identify existing open source MD engines to study or fork (GROMACS, OpenMM)
- [ ] Prototype: single element (Hydrogen), correct quantum attributes, basic bonding visualisation
- [ ] Validate: does H + H → H₂ emerge correctly from attributes alone?

### Phase 1 — Proof of Concept (Year 1–2, post-employment)
- [ ] Engine: Class 11 Physics scope (kinematics, forces, gravitation, thermodynamics basics)
- [ ] Engine: Class 11 Chemistry scope (atomic structure, bonding, basic reactions)
- [ ] Renderer: 2D first, WebGL
- [ ] Platform: Web-based, mobile-responsive
- [ ] Open source: MIT or GPL license, GitHub, contributor guidelines
- [ ] First external users: Trichy teachers via personal network

### Phase 2 — Community and Curriculum (Year 3–4)
- [ ] Class 12 scope added (electrochemistry, organic basics, waves, optics)
- [ ] Tamil localisation (with educator review)
- [ ] Institutional tier launch
- [ ] First paying school
- [ ] Contributor community: 10+ active contributors
- [ ] Domain expert advisory board (physicist + chemist minimum)

### Phase 3 — Scale (Year 5–7)
- [ ] Full TN board 11–12 coverage
- [ ] Adjacent state board mapping (Karnataka, AP)
- [ ] Teacher training programme
- [ ] 500+ schools, institutional revenue sustains core team
- [ ] 3D renderer (WebGPU)
- [ ] Security audit by independent firm

### Phase 4 — Platform (Year 8–10)
- [ ] VR/AR layer
- [ ] Student portfolio system (simulation projects as competency evidence)
- [ ] API for third-party curriculum builders
- [ ] Researcher tier
- [ ] National-level recognition / NCERT conversations (only after product is proven)

---

## 8. Related Work and References

### Academic
- Wieman, C. et al. (2002–present). *PhET Interactive Simulations*. University of Colorado Boulder. https://phet.colorado.edu
- DuBose, J.T. et al. (2024). *Physical Chemistry Education and Research in an Open-Sourced Future*. ACS Physical Chemistry Au. DOI: 10.1021/acsphyschemau.3c00078
- Han, T. et al. (2025). *BioEmu: AI-Powered Revolution in Scalable Protein Dynamics Simulation*. Journal of Cellular and Molecular Medicine.
- Clementi, C. et al. (2025). *CGSchNet: Machine-learned coarse-grained model for protein simulation*. Nature Chemistry, July 2025.

### Tools to Study
- **OpenMM** — open source MD engine, Python API, GPU-accelerated. Most relevant for engine design reference.
- **GROMACS** — production MD engine, C++, extremely well-documented force field implementation
- **Avogadro** — open source molecule editor, relevant for 3D rendering approach
- **PhET source** — JavaScript/TypeScript, study simulation architecture even if not forking
- **Godot Engine** — open source game engine with strong physics; potential rendering layer candidate

### Games / Educational Products
- Kerbal Space Program (Squad, 2015) — existence proof for emergent physics education
- KerbalEdu (TeacherGaming) — institutional SaaS model on physics game
- Algodoo (Algoryx) — 2D emergent physics sandbox, strong UX reference
- Minecraft Education Edition — sandbox learning at institutional scale

---

## 9. Open Questions

- What is the minimum accurate abstraction level for TN Class 11 Chemistry that is computationally feasible in a browser?
- Can WebGPU make classical MD fast enough for real-time interactive use on mid-range Android?
- What existing open source force field data (AMBER, CHARMM, UFF) can be used without licensing issues?
- Is there an existing open source 2D/3D physics+chemistry engine that is close enough to fork rather than build from scratch?
- How do biosecurity researchers recommend handling dual-use simulation tools? (Review: NTI biosecurity framework, Johns Hopkins CHS guidelines)
- What is the correct legal structure — foundation (like Blender Foundation) vs. company with open core?

---

## 10. Status and Deferral Note

**This project is explicitly deferred until:**
1. Stable employment secured (TCS NQT or equivalent)
2. CodeDash shipped and publicly functional
3. DSA at 200+ problems (Striver A2Z)
4. 6+ months of financial runway established

This is a decade-scale project requiring time, resources, and collaborators that do not yet exist in the current situation. The idea is sound. The gap is real. The timing is wrong for execution — right for research and documentation.

**This note exists so the idea does not live only in memory.**

---

*Last reviewed: 2026-06-10*
*Next review: When employment is stable*
