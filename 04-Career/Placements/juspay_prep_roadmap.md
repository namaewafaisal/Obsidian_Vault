# Juspay Interview Prep Roadmap
> Ordered by importance, then by execution sequence. Do not skip ahead.

---

## How to Use This

Each section has a **priority tier** and **time estimate**. Work in order. Don't try to learn everything — learn what will actually show up and learn it well enough to code it cold.

**Your constraint is time. Depth beats breadth here.**

---

# PHASE 1 — Trees (Coding Round)
> This is the most likely topic in the coding round. Do this first, do it completely.

## Mental Model First (Day 1 — 1 hour, no code)

Before touching LeetCode, understand the three things every tree problem reduces to:

1. **Recursion + return value** — most problems are "compute something at children, combine at parent"
2. **DFS vs BFS** — DFS uses the call stack (or explicit stack), BFS uses a queue
3. **What state to carry** — sometimes you pass info *down* (depth), sometimes you return info *up* (height), sometimes both

Draw this tree and keep it open while learning:
```
        1
       / \
      2   3
     / \   \
    4   5   6
```
Every traversal example below uses this tree. Build the picture in your head once, refer to it always.

---

## Tier 1 — Do These First (Day 1–2)
> These are foundational. Every other problem builds on these. If you can't do these cold, stop and fix that before moving.

### 1. Preorder Traversal
**Pattern:** Root → Left → Right
**Output on example tree:** 1, 2, 4, 5, 3, 6

```java
void preorder(TreeNode node) {
    if (node == null) return;
    System.out.print(node.val); // process ROOT first
    preorder(node.left);
    preorder(node.right);
}
```
**Iterative (important to know):**
```java
Stack<TreeNode> stack = new Stack<>();
stack.push(root);
while (!stack.isEmpty()) {
    TreeNode node = stack.pop();
    System.out.print(node.val);
    if (node.right != null) stack.push(node.right); // right first (LIFO)
    if (node.left != null) stack.push(node.left);
}
```
**LeetCode:** 144

---

### 2. Inorder Traversal
**Pattern:** Left → Root → Right
**Output on example tree:** 4, 2, 5, 1, 3, 6
**Key insight:** Inorder of a BST gives sorted order. This is why BST problems love inorder.

```java
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    System.out.print(node.val); // process ROOT in middle
    inorder(node.right);
}
```
**LeetCode:** 94

---

### 3. Postorder Traversal
**Pattern:** Left → Right → Root
**Output on example tree:** 4, 5, 2, 6, 3, 1
**Key insight:** Used when you need children's results before processing parent (e.g., deleting a tree, computing subtree sizes).

```java
void postorder(TreeNode node) {
    if (node == null) return;
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.val); // process ROOT last
}
```
**LeetCode:** 145

---

### 4. Level Order Traversal (BFS)
**Pattern:** Queue. Process level by level.
**Output on example tree:** [1], [2,3], [4,5,6]
**Key insight:** This is the template for ALL level-based problems (zigzag, top view, bottom view, etc.)

```java
List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size(); // snapshot current level size
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```
**LeetCode:** 102
**Master this template. You will reuse it 5+ times.**

---

### 5. Maximum Depth
**Pattern:** Postorder thinking — get depth of children, return max + 1
**Output on example tree:** 3

```java
int maxDepth(TreeNode node) {
    if (node == null) return 0;
    int left = maxDepth(node.left);
    int right = maxDepth(node.right);
    return Math.max(left, right) + 1;
}
```
**LeetCode:** 104
**This function is a building block. You'll call it inside other solutions.**

---

### 6. Same Tree
**Pattern:** Compare two trees simultaneously
```java
boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    return p.val == q.val
        && isSameTree(p.left, q.left)
        && isSameTree(p.right, q.right);
}
```
**LeetCode:** 100

---

### 7. Symmetric Tree
**Pattern:** Same as Same Tree but mirrored — compare left.left with right.right and left.right with right.left
```java
boolean isSymmetric(TreeNode root) {
    return isMirror(root.left, root.right);
}
boolean isMirror(TreeNode l, TreeNode r) {
    if (l == null && r == null) return true;
    if (l == null || r == null) return false;
    return l.val == r.val
        && isMirror(l.left, r.right)   // outer pair
        && isMirror(l.right, r.left);  // inner pair
}
```
**LeetCode:** 101

---

## Tier 2 — Core Problems (Day 2–3)
> These appear often and have patterns worth memorizing. Not as foundational as Tier 1 but very likely to appear.

### 8. Balanced Binary Tree
**Insight:** A tree is balanced if for every node, |left_height - right_height| <= 1. Naive approach calls height() repeatedly (O(n²)). Optimized approach returns -1 to signal imbalance.

```java
int checkHeight(TreeNode node) {
    if (node == null) return 0;
    int left = checkHeight(node.left);
    if (left == -1) return -1; // already imbalanced
    int right = checkHeight(node.right);
    if (right == -1) return -1;
    if (Math.abs(left - right) > 1) return -1; // imbalanced here
    return Math.max(left, right) + 1;
}
boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
}
```
**LeetCode:** 110

---

### 9. Diameter of Binary Tree
**Insight:** The diameter passing through a node = left_height + right_height. Track max across all nodes.
```java
int maxDiameter = 0;
int diameter(TreeNode node) {
    if (node == null) return 0;
    int left = diameter(node.left);
    int right = diameter(node.right);
    maxDiameter = Math.max(maxDiameter, left + right); // path through this node
    return Math.max(left, right) + 1; // height to return to parent
}
```
**LeetCode:** 543
**Pattern note:** This is the same structure as Maximum Path Sum. Learn one, you understand the other.

---

### 10. BST Search
```java
TreeNode search(TreeNode root, int val) {
    if (root == null || root.val == val) return root;
    if (val < root.val) return search(root.left, val);
    return search(root.right, val);
}
```
**LeetCode:** 700

---

### 11. Validate BST
**Insight:** Don't just check left < root < right. You need to pass down min/max bounds.
```java
boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;
    return validate(node.left, min, node.val)
        && validate(node.right, node.val, max);
}
boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}
```
**LeetCode:** 98
**This is a very common interview trap. Many people get it wrong by not passing bounds.**

---

### 12. BST Insert
```java
TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left = insert(root.left, val);
    else root.right = insert(root.right, val);
    return root;
}
```
**LeetCode:** 701

---

### 13. Kth Smallest in BST
**Insight:** Inorder of BST = sorted. Kth smallest = Kth element in inorder traversal.
```java
int count = 0, result = 0;
void inorder(TreeNode node, int k) {
    if (node == null) return;
    inorder(node.left, k);
    count++;
    if (count == k) { result = node.val; return; }
    inorder(node.right, k);
}
```
**LeetCode:** 230

---

### 14. Lowest Common Ancestor
**Insight:** If both p and q are less than root, go left. Both greater, go right. Otherwise root is the LCA.
```java
// For BST:
TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val) return lcaBST(root.left, p, q);
    if (p.val > root.val && q.val > root.val) return lcaBST(root.right, p, q);
    return root;
}

// For general binary tree (LeetCode 236):
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);
    if (left != null && right != null) return root; // p and q on different sides
    return left != null ? left : right;
}
```
**LeetCode:** 235 (BST), 236 (general)

---

## Tier 3 — Medium-Hard (Day 3–4)
> Harder problems. Do these after Tier 1 and 2 are solid.

### 15. Root to Node Path
```java
boolean getPath(TreeNode node, int target, List<Integer> path) {
    if (node == null) return false;
    path.add(node.val);
    if (node.val == target) return true;
    if (getPath(node.left, target, path) || getPath(node.right, target, path))
        return true;
    path.remove(path.size() - 1); // backtrack
    return false;
}
```

---

### 16. Maximum Path Sum
**Insight:** Same structure as Diameter. At each node: max path = left_gain + node.val + right_gain. But when returning to parent, you can only go one direction.
```java
int maxSum = Integer.MIN_VALUE;
int maxGain(TreeNode node) {
    if (node == null) return 0;
    int left = Math.max(0, maxGain(node.left));   // ignore negative paths
    int right = Math.max(0, maxGain(node.right));
    maxSum = Math.max(maxSum, left + node.val + right); // path through node
    return node.val + Math.max(left, right); // return to parent
}
```
**LeetCode:** 124

---

### 17. BST Delete
**Three cases:**
- Node is a leaf → just remove
- Node has one child → replace with child
- Node has two children → replace with inorder successor (smallest in right subtree)

```java
TreeNode delete(TreeNode root, int key) {
    if (root == null) return null;
    if (key < root.val) root.left = delete(root.left, key);
    else if (key > root.val) root.right = delete(root.right, key);
    else {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        // Two children: find inorder successor
        TreeNode successor = root.right;
        while (successor.left != null) successor = successor.left;
        root.val = successor.val;
        root.right = delete(root.right, successor.val);
    }
    return root;
}
```
**LeetCode:** 450

---

### 18. Zigzag Traversal
**Insight:** Level Order + flip direction each level. Use a flag.
```java
List<List<Integer>> zigzag(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;
    while (!queue.isEmpty()) {
        int size = queue.size();
        LinkedList<Integer> level = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (leftToRight) level.addLast(node.val);
            else level.addFirst(node.val); // reverse by adding to front
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
        leftToRight = !leftToRight;
    }
    return result;
}
```
**LeetCode:** 103

---

### 19. Vertical Order Traversal
**Insight:** Assign column index to each node (root=0, left=-1, right=+1). Group nodes by column.
```java
// Use BFS + HashMap<column, list of values>
// Sort columns, output in order
Map<Integer, List<Integer>> columnMap = new TreeMap<>(); // TreeMap keeps columns sorted
Queue<int[]> queue = new LinkedList<>(); // [node_index_but_store_as_node_val_column_pair]
// Actually store as pair: use a separate queue of TreeNode + column
```
**LeetCode:** 987
**Note:** This problem has tricky tie-breaking rules. Read the problem carefully.

---

### 20. Top View
**Insight:** For each column, keep only the FIRST node seen (level order guarantees top-first).
```java
// BFS + column tracking. For each column, store first value seen.
Map<Integer, Integer> topView = new TreeMap<>();
Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
queue.offer(new Pair<>(root, 0));
while (!queue.isEmpty()) {
    Pair<TreeNode, Integer> pair = queue.poll();
    TreeNode node = pair.getKey();
    int col = pair.getValue();
    topView.putIfAbsent(col, node.val); // only add if not already there
    if (node.left != null) queue.offer(new Pair<>(node.left, col - 1));
    if (node.right != null) queue.offer(new Pair<>(node.right, col + 1));
}
```

---

### 21. Bottom View
**Same as Top View but keep LAST node per column (overwrite instead of putIfAbsent).**
```java
columnMap.put(col, node.val); // overwrite = keeps last = bottom
```

---

## Tier 4 — Hard (Day 4–5, only if time permits)

### 22. Boundary Traversal
**Three parts:** Left boundary (top to bottom, exclude leaves) + All leaves (left to right) + Right boundary (bottom to top, exclude leaves). Do each separately.

### 23. Serialize / Deserialize Binary Tree
**Insight:** Preorder traversal with null markers. Serialize = preorder DFS. Deserialize = rebuild using same preorder with a pointer/index.
```java
// Serialize: "1,2,4,#,#,5,#,#,3,#,6,#,#"
// Deserialize: rebuild recursively using queue of values
```
**LeetCode:** 297
**This is a hard problem. If you're short on time, understand the approach, don't memorize the code.**

---

## Practice Order Summary

| Day | Topics |
|-----|--------|
| Day 1 | Pre/In/Post traversal (recursive + iterative), Level Order, Max Depth |
| Day 2 | Same Tree, Symmetric Tree, Balanced, Diameter, BST Search/Insert/Validate |
| Day 3 | LCA, Kth Smallest, Root to Node Path, Max Path Sum |
| Day 4 | Zigzag, Vertical Order, Top View, Bottom View, BST Delete |
| Day 5 | Boundary Traversal, Serialize/Deserialize, revision |

---

---

# PHASE 2 — System Design
> You've done topics 1–9 with me. This section builds on that and ties it to Juspay-specific scenarios.

## What Juspay Will Actually Ask

Not generic "design Twitter." They'll ask:
- Design a real-time fraud detection system (under 100ms)
- Design a payment routing system
- Design a high-availability checkout flow

The core toolkit you need:

---

## System Design Toolkit (In Order of Priority)

### 1. Load Balancing + Horizontal Scaling ✅ (you know this)
- Round robin, least connections, consistent hashing
- Know when to use each
- **Juspay angle:** Payment traffic is spiky (festival sales, midnight UPI rushes). How do you auto-scale?

### 2. Caching ✅ (you know this, apply it better)
- Redis — cache CP stats in CodeDash, cache fraud scores in Juspay
- Cache aside vs write-through vs write-back
- **Key interview point:** What do you cache? What's the TTL? What happens on cache miss?
- **Juspay angle:** Fraud scores can be cached for a user session. Handle stats can have 5-min TTL.

### 3. Database Design + Indexing ✅ (you know this)
- When to use SQL vs NoSQL
- Indexes — B-tree, composite, covering indexes
- **Juspay angle:** Transaction table will have billions of rows. Index on (user_id, timestamp). Partition by date.

### 4. Message Queues (NEW — learn this)
- Kafka / RabbitMQ
- **Why:** Payment systems are async. When you pay on Swiggy, 10 things happen: fraud check, inventory update, notification, ledger entry, etc. You don't do these synchronously.
- **Pattern:** Payment request → Kafka topic → multiple consumers process independently
- **Key concepts:** Producer, Consumer, Topic, Partition, Consumer Group, Offset
- **Juspay angle:** Every transaction event goes to Kafka. Fraud detection is one consumer. Analytics is another.

### 5. API Design + Rate Limiting (NEW — learn this)
- REST best practices (you know this from CodeDash)
- Rate limiting: Token bucket algorithm, Leaky bucket
- **Why:** Payment APIs get hammered. You need rate limiting to prevent abuse.
- **Juspay angle:** A merchant's integration can't hammer your API 10,000 times/second.

### 6. Distributed Systems Basics (AWARENESS LEVEL)
- CAP theorem — you can't have Consistency + Availability + Partition tolerance all at once
- For payments: you choose Consistency (you can't lose a transaction)
- **What to say:** "Payments are CP systems — we sacrifice availability over consistency. A transaction failing is better than a transaction being processed twice."

### 7. High Availability Patterns (AWARENESS LEVEL)
- Active-Active vs Active-Passive
- Health checks, circuit breakers
- **Juspay runs at 99.999% uptime** — that's 5 minutes of downtime per year. Everything is replicated.

---

## The One System Design You Should Prepare End-to-End

**Design a Real-Time Fraud Detection System (< 100ms)**

This is literally JusTrust. Prepare this fully.

**Components:**
1. API Gateway receives transaction request
2. Synchronous fraud check call (must return in <100ms)
3. Fraud service checks: user history (Redis cache), transaction pattern, ML score
4. If score > threshold → block, else → allow
5. Async: log transaction to Kafka → analytics consumer updates user risk profile

**Why < 100ms is hard:**
- ML model inference takes time
- DB lookups take time
- Solution: cache user risk scores in Redis (pre-computed), use lightweight rule engine for real-time, ML runs async to update scores

**What to draw on the whiteboard:**
```
Client → API Gateway → Payment Service → Fraud Service (sync, <100ms)
                                    ↓
                              Redis (cached risk scores)
                                    ↓
                              Allow/Block decision
                                    ↓
                         Kafka → Risk Score Updater (async)
                              → Analytics (async)
                              → Notification (async)
```

---

---

# PHASE 3 — Software Engineering Fundamentals
> These come up in technical interview rounds, not the coding round.

## Priority Order

### 1. OOP Concepts (you know this — just articulate it well)
- Encapsulation, Inheritance, Polymorphism, Abstraction
- **Be ready for:** "How did you use these in CodeDash?"
- RBAC is encapsulation. Spring Security filter chain is abstraction. Role-based method access is polymorphism.

### 2. SOLID Principles (learn names + one example each)
- **S**ingle Responsibility — each class does one thing
- **O**pen/Closed — open for extension, closed for modification
- **L**iskov Substitution — subclass can replace parent
- **I**nterface Segregation — don't force classes to implement methods they don't need
- **D**ependency Inversion — depend on abstractions, not implementations
- **Juspay angle:** Their open-source Hyperswitch follows these — modular, pluggable payment connectors

### 3. Design Patterns (know 3 well)
- **Factory** — you probably used this in Spring (bean creation)
- **Singleton** — DB connection pool
- **Strategy** — payment routing: pick routing strategy at runtime (round-robin vs least-latency vs cost-based)
- **Observer** — event-driven: transaction completed → notify fraud system, analytics, user

### 4. Database Transactions + ACID
- **A**tomicity — all or nothing (payment deducted AND credited, never just one)
- **C**onsistency — DB rules always satisfied
- **I**solation — concurrent transactions don't interfere
- **D**urability — committed transactions survive crashes
- **This is critical for payments. Know it cold.**

### 5. REST API Best Practices
- Proper HTTP methods and status codes
- Idempotency — POST /payments can be retried safely if you use idempotency keys
- **Juspay angle:** Payment APIs MUST be idempotent. If a request times out, merchant retries — you can't charge twice.

---

---

# PHASE 4 — Behavioral / HR
> Don't underestimate this. Juspay explicitly evaluates communication and stakeholder thinking.

## Stories to Prepare (STAR format: Situation → Task → Action → Result)

### 1. "Tell me about a bug you solved under pressure"
**Use:** JWT secret length misconfiguration in CodeDash, or VA-API hardware decoding fix, or niri/XWayland compatibility debugging.
**Why it works:** Shows systematic debugging, not panic.

### 2. "Describe building something from scratch"
**Use:** CodeDash — the full arc. Problem identified → designed schema → built auth → deployed on Railway.
**Key:** Talk about *decisions*, not just *actions*.

### 3. "How do you translate requirements into a system?"
**Use:** When you pivoted College Identity System to CodeDash. The requirement changed, you redesigned.

### 4. "Tell me about working with others / stakeholders"
**Use:** VDart internship — 3 roles, 30 endpoints, presumably coordinating with someone on requirements.

---

---

# What To Skip (Given Time Constraint)

- **Rust** — Don't try to learn it. If asked, say "I'm actively learning it, familiar with ownership concepts, haven't written production Rust yet."
- **ML/RL Bandits** — Way too deep to fake. If asked, be honest: "I have conceptual understanding of fraud detection ML but haven't implemented it."
- **Kubernetes internals** — Know what it does, not how to configure it from scratch.
- **Functional programming** — Understand it conceptually (pure functions, immutability, no side effects). Don't try to write Haskell.

---

# Quick Reference: Patterns That Repeat

| Pattern | Problems It Solves |
|---|---|
| DFS recursion with return value | Max depth, diameter, balanced, path sum, LCA |
| BFS with level size snapshot | Level order, zigzag, top/bottom view |
| Column index tracking | Vertical order, top view, bottom view |
| Inorder of BST = sorted | Kth smallest, validate BST |
| Pass bounds downward | Validate BST, range queries |
| Track global max with local return | Diameter, max path sum |

**If you recognize the pattern, you can solve the problem. Learn patterns, not solutions.**

---

# Daily Schedule Suggestion

| Day | Morning (2hr) | Evening (2hr) |
|-----|---------------|---------------|
| Day 1 | Tree traversals (code all 4) | Level order template + max depth |
| Day 2 | Same/Symmetric/Balanced/Diameter | BST Search, Insert, Validate |
| Day 3 | LCA, Kth Smallest, Path | Max Path Sum, BST Delete |
| Day 4 | Zigzag, Top/Bottom View | Vertical Order, Boundary |
| Day 5 | Serialize/Deserialize | Full revision — 1 problem per topic cold |
| Day 6 | System Design: Fraud Detection | SOLID + ACID + Idempotency |
| Day 7 | CodeDash deep-read (own your project) | Behavioral stories (write them out) |

---

*One last thing: the goal isn't to memorize all of this. It's to understand the patterns well enough that in the interview, you can think out loud and arrive at the solution even if you haven't seen the exact problem before. That's what Juspay actually tests.*
