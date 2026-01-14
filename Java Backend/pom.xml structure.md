## `pom.xml` Structure

### Intuition

`pom.xml` is the **blueprint** of a Maven project—it tells Maven *what this project is* and *what it needs* to build.

---

### Technical (necessary elements only)

A `pom.xml` typically defines:

* **Project identity**

  * `groupId`, `artifactId`, `version`
* **Packaging**

  * `jar`, `war`, etc.
* **Dependencies**

  * External libraries your code needs
* **Build configuration**

  * Plugins that control compilation, testing, packaging
* **Inheritance**

  * Parent POM (common defaults and versions)

Maven reads `pom.xml` and executes a **standard lifecycle** using this information.

---

### One-line mental model

> **`pom.xml` describes the project; Maven decides how to build it.**

