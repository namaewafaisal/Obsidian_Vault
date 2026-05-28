# Version Control

## Definition
System used to track and manage changes to software artifacts over time.

---

# Purpose
- history tracking
- collaboration
- rollback
- branching
- release management

---

# Core Concepts

## Repository (Repo)
Storage containing project files and history.

### Types
- local repository
- remote repository

---

## Commit
Snapshot of project at a specific point.

---

## Commit Hash
Unique identifier for each commit.

Example:
```bash
a73f9d2
```

---

## Branch
Independent line of development.

### Examples
- main
- feature-login
- hotfix-payment

---

## Merge
Combining branches together.

---

## Merge Conflict
Occurs when multiple developers modify same code differently.

---

## Checkout
Switching between branches or commits.

---

## Revert
Undoing changes safely.

---

# Types of Version Control Systems

## Centralized VCS (CVCS)
- single central server
- example: SVN

---

## Distributed VCS (DVCS)
- every developer has full history
- example: Git

---

# Git

## Definition
Distributed Version Control System.

Created by :contentReference[oaicite:0]{index=0}

---

# Basic Git Workflow

## Initialize Repository
```bash
git init
```

## Add Files
```bash
git add .
```

## Commit Changes
```bash
git commit -m "message"
```

## Push Changes
```bash
git push
```

## Pull Changes
```bash
git pull
```

---

# Pull Request (PR)

Request to merge code into another branch.

Used for:
- code review
- testing

---

# Tags

Used to mark releases.

### Examples
- v1.0
- v2.0

---

# Semantic Versioning

Format:
```text
MAJOR.MINOR.PATCH
```

Example:
```text
2.5.1
```

---

# Advantages
- collaboration
- rollback
- auditing
- parallel development
- CI/CD integration

---

# Important Definitions

## Version Control
Mechanism for managing changes to software artifacts over time.

## Repository
Storage containing project files and history.

## Commit
Snapshot of project state.

## Branch
Independent line of development.

## Merge
Combining changes from branches.