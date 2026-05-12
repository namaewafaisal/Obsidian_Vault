Alright Faisal, this is a complete breakdown. June 9 gives you roughly 4 weeks — that's tight but very doable if you're focused. Let me give you everything.

---

## The Exam Structure

The TCS NQT is a single 190-minute exam divided into two mandatory parts. The Foundation section (75 minutes, 65 questions) covers Numerical, Verbal, and Reasoning Ability — it determines Ninja eligibility. The Advanced section (115 minutes, 17 items) covers harder Aptitude and Coding — it determines Digital and Prime eligibility. Both are taken in the same sitting.

One critical thing: section switching is not allowed. Once a section's time expires, it locks permanently. You must answer questions in the exact order they appear and cannot return to a previous question.

**Section breakdown:**

| Section | Questions | Time | Purpose |
|---|---|---|---|
| Numerical Ability | 20 | 25 min | Foundation |
| Verbal Ability | 25 | 25 min | Foundation |
| Reasoning Ability | 20 | 25 min | Foundation |
| Advanced Aptitude | 15 MCQs | 25 min | Digital/Prime |
| Advanced Coding | 2 problems | 90 min | Digital/Prime |

No negative marking in 2026. Attempt every single question — there is zero penalty for wrong answers.

---

## Bands and What the Scores Mean

The three bands: TCS Ninja at ₹3.36 LPA (in-hand ₹26K–₹28K/month) for Foundation qualifiers. TCS Digital at ₹7 LPA (in-hand ₹52K–₹55K/month) for Advanced qualifiers. TCS Prime at ₹9.36 LPA (in-hand ₹72K–₹75K/month) for top Advanced scorers.

**For Prime, the estimated score you need:**

Prime requires roughly 55+ in Foundation (out of 65) plus both coding questions solved.

In percentile terms: Ninja is roughly 50–60 percentile, Digital is 65–75 percentile, and Prime is 80+ percentile. TCS does not officially publish cutoffs — these are community-verified estimates from multiple exam cycles.

The highest eligible band is offered — if you clear all thresholds, you receive a Prime interview invite, not separate Ninja and Digital invites.

---

## What Happens After the Exam

If you clear the NQT cutoff, you're invited for a face-to-face or virtual interview panel. The technical round does a deep dive into Data Structures, OOP, DBMS, and your final year project.

The TCS interview has 3 rounds — HR Round, Technical Round, and Managerial Round. All three typically happen on the same day.

The interview session spans roughly 25 to 40 minutes per panel. Three distinct panels assess you: a technical domain panel (programming knowledge, problem-solving, core concepts), a managerial panel, and an HR panel.

---

## How to Crack the Exam (Prime Target Strategy)

### Foundation Section — Don't Fumble This

This is the gatekeeper. If you don't score 55+/65 here, your Advanced performance doesn't matter.

**Numerical Ability (20 questions, 25 min):**
Topics: Number Systems, Percentages, Profit & Loss, Time & Work, Time-Speed-Distance, Averages, Simple & Compound Interest, Ratio & Proportion. At roughly 75 seconds per question, you need to be fast. Practice mental math shortcuts. Use RS Aggarwal Quantitative Aptitude — do the exercise sets, not just theory.

**Verbal Ability (25 questions, 25 min):**
Reading Comprehension (2–3 passages), Fill in the Blanks, Sentence Correction, Para Jumbles, Vocabulary. TCS uses long passages with context-trap MCQs — speed-read drills matter more than memorising vocabulary lists. Practice skimming passages for main idea first, then answer. For grammar/sentence correction, focus on Subject-Verb agreement, Tenses, Prepositions, Articles.

**Reasoning Ability (20 questions, 25 min):**
Logical Reasoning, Coding-Decoding, Blood Relations, Directions, Series, Syllogisms, Seating Arrangements. These are pattern recognition — the more you practice, the faster you get. IndiaBix is solid for this.

### Advanced Section — Where Prime is Won or Lost

**Advanced Aptitude (15 MCQs, 25 min):**
Questions test pseudocode tracing, output prediction, and basic complexity — no actual coding required in this subsection. Higher difficulty versions of Quant + Data Interpretation. Also includes logical puzzles and series that are harder than Foundation.

**Advanced Coding (2 problems, 90 min):**
This is the entire deciding factor for Prime. For Digital/Prime, coding is king — allocate 50% of your preparation time to coding practice.

The problems are roughly:
- Problem 1: Easy-Medium (arrays, strings, basic loops, simple logic)
- Problem 2: Medium (could involve patterns, basic DP, sorting, searching)

Critical warning: the TCS iON compiler behaves differently from LeetCode and HackerRank. Input format, compilation speed, and error messages are all different. Practice on TCS iON-style compiler format before the exam.

TCS iON uses `Scanner` for Java input (not BufferedReader). Practice this specifically:
```java
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
```

For Prime you need both problems fully solved. Partial solutions get partial marks — always write code that at least handles edge cases, even if it fails some test cases. A fully correct solution on Problem 1 + 60% on Problem 2 is likely Digital. Full credit on both = Prime territory.

---

## Aptitude Topics Priority List

**Must-do (high frequency):**
- Time & Work (especially pipes & cisterns variant)
- Time-Speed-Distance (relative speed, trains, boats)
- Percentages → Profit/Loss (these are connected)
- Averages & Weighted Averages
- Number Series (both numeric and letter series)
- Coding-Decoding
- Blood Relations + Direction sense
- Syllogisms (Venn diagram approach)
- Reading Comprehension (3 full passages daily)

**Do but don't over-invest:**
- Probability (basic)
- Permutation & Combination (only simple cases)
- Data Interpretation (bar graphs, pie charts — read carefully)

---

## Coding Topics to Cover (for Advanced section)

Your 102 LeetCode problems give you a decent base. For TCS specifically:

**High priority:**
- Arrays: two-pointer, sliding window, rotation, duplicates, majority element
- Strings: reversal, palindrome check, anagram detection, pattern matching
- Number problems: prime check, factorial, fibonacci, GCD/LCM, digit sum
- Sorting: understand bubble/selection/merge/quick conceptually, implement when asked
- Pattern printing (surprisingly common in TCS)
- Basic recursion

**Medium priority:**
- LinkedList: reversal, cycle detection, merge
- Stack/Queue: balanced parentheses, next greater element
- Binary Search: on sorted arrays, find first/last occurrence
- HashMap problems: frequency counting, two-sum style

You don't need dynamic programming for Problem 1. For Problem 2, only basic DP might appear (e.g., staircase problem, coin change simple variant). Focus on getting Problem 1 perfect and making Problem 2 partially work.

Practice resources: PrepInsta TCS Coding section, LeetCode Easy/Medium filtered for Arrays and Strings.

---

## The Interview — Can You Pass With Your Java Stack?

Yes — and you're actually well-positioned. Here's the real picture:

The interviewer asked Spring Boot questions because Java was mentioned in the student's resume. They also asked to connect the project with backend code. Resume-based preparation matters a lot.

Honesty works — one student answered that they only knew the basics of cloud computing. The interviewer accepted that and moved on. Don't lie; honesty works better than pretending to know everything.

**What they actually ask in the Technical round:**

Core Java (most asked):
- OOP: the four pillars with real examples. Be ready to say what makes polymorphism different from overloading vs overriding with code examples.
- `String` vs `StringBuilder` vs `StringBuffer` — when and why
- ` ==` vs `.equals()` — with the String pool explanation
- Checked vs Unchecked exceptions, custom exceptions, `finally` block behaviour
- Multithreading: what is a thread, `Runnable` vs `Thread`, `synchronized` keyword, basic deadlock concept (you don't need to code it, just explain)
- Collections: `ArrayList` vs `LinkedList`, `HashMap` vs `HashSet`, `TreeMap` ordering

DBMS (always asked):
- SQL Joins: Inner, Left, Right, Full — with examples
- Normalization: 1NF, 2NF, 3NF definitions (as seen in real 2026 Prime interviews)
- Indexes: what they do, clustered vs non-clustered
- ACID properties
- Basic aggregate queries: `GROUP BY`, `HAVING`, subqueries

Spring Boot (if on your resume):
- What is Spring Boot vs Spring? (auto-configuration, embedded server)
- What is dependency injection, IoC container
- `@RestController` vs `@Controller`
- `@Autowired`, `@Component`, `@Service`, `@Repository`
- What is JPA/Hibernate — entity mapping basics
- REST: what are HTTP methods and status codes
- JWT: what it is, how authentication flow works (you have this from VDart)

Your project — CodeDash:
This is where you have a huge edge if you deploy it before the interview. They will ask you to explain your architecture. Prepare: what tables, what endpoints, how auth works, why you chose PostgreSQL, what RBAC means in your system. If it's deployed on Railway, you can literally demo it. That's a massive differentiator for Prime.

Data Structures (conceptual level for TCS):
- Arrays, LinkedList, Stack, Queue — operations and time complexity
- Binary Search — when to use
- Sorting algorithms — bubble O(n²), merge O(n log n), quick O(n log n) average
- Trees: BFS vs DFS concept, height, traversals (in/pre/post order)
- Hashing: collision handling concept

OS and Networking (light coverage):
- Process vs Thread
- Deadlock conditions (4 necessary conditions)
- OSI model layers (at least Layer 1–7 names and purpose)
- What is HTTP vs HTTPS
- TCP vs UDP

---

## Your 4-Week Plan (June 9 target)

**Week 1 (May 12–18): Foundation Aptitude Baseline**
- Daily: 40 quant problems (RS Aggarwal), 2 RC passages, 20 reasoning questions
- Identify your 2 weakest quant topics and hammer them
- Coding: 1 LeetCode Easy/Medium per day (arrays/strings only)

**Week 2 (May 19–25): Advanced Aptitude + Coding Ramp**
- Daily: 20 advanced quant/DI, pseudocode tracing practice
- Coding: 2 LeetCode problems per day (introduce LinkedList, HashMap)
- Take 1 full Foundation mock test (PrepInsta or IndiaBix TCS mock)

**Week 3 (May 26–June 1): Mock Mode**
- Take 2 full-length TCS NQT mocks — time yourself strictly
- Analyse every wrong answer — categorise by topic
- Coding: solve 2–3 TCS-pattern problems daily (PrepInsta TCS coding section)
- Technical interview prep: write down answers to 20 core Java + DBMS questions

**Week 4 (June 2–8): Interview Prep + Revision**
- No new topics — only revision and mocks
- 1 full mock per day
- Practice explaining your CodeDash project out loud (mock it with yourself)
- Deploy CodeDash by June 5 at the absolute latest
- Prepare 2–3 minute "tell me about yourself" that naturally leads to CodeDash

---

## The One Thing That Separates Prime from Digital

The coding problems. Foundation score just needs to be solid (55+). But Prime requires both coding questions solved — that's the actual filter. Every hour you spend on LeetCode Easy/Medium arrays and strings problems is directly translating to Prime territory. If you can reliably solve 2 medium-difficulty array/string problems in 90 minutes with correct I/O format, you're Prime-bound.

Your Java background + VDart internship + CodeDash (once deployed) puts you in a genuinely strong interview position. The technical interview at TCS Prime is not LeetCode-hard — it's "explain your project, answer core Java questions, write a basic SQL query." You can clear that with what you already know, especially if you tighten up SQL joins + normalization and can articulate CodeDash's architecture clearly.