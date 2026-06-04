# 🌐 UNIT I: Website Basics, HTML5, CSS3, & Bootstrap (Deep-Dive)

## 1. HTTP Request and Response Messages
- **Syllabus Sub-topics:** Web Essentials: Clients, Servers and Communication – HTTP Request Message – HTTP Response Message – Web Clients – Web Servers
- **Weight:** Mandatory (Topic 1) | [cite_start]**Exam Alignment:** 2024 Part A (Q1), Potential Part B [cite: 3, 15]

### 🏗️ Architectural Workflow Diagram
When a client interacts with a server, the stateless transaction follows this precise network lifecycle sequence:

```text
       [ WEB CLIENT (Browser) ]                     [ WEB SERVER ]
                 │                                        │
                 │ ─── 1. Establishes TCP Connection ───> │
                 │                                        │
                 │ ─── 2. HTTP REQUEST MESSAGE ─────────> │
                 │        (Method, URI, Headers, Body)    │
                 │                                        │
                 │ <── 3. HTTP RESPONSE MESSAGE ───────── │
                 │        (Status Code, Headers, HTML)    │
                 │                                        │
                 │ <── 4. Closes/Reuses Connection ─────> │
```

### 🔍 Structure of an HTTP Request Message
An HTTP Request is formatted as plain ASCII text and divided into four distinct logical segments:
1. **Request Line:** Contains three structural parameters:
   - *HTTP Method:* Defines the action to be performed (`GET` for fetching data, `POST` for sending data, `PUT`, `DELETE`).
   - *Request URI:* The resource path pointing to the location on the server (e.g., `/registration.php`).
   - *HTTP Version:* The protocol implementation version being utilized (typically `HTTP/1.1` or `HTTP/2`).
2. **Request Headers:** Key-value string pairs providing crucial metadata regarding client runtime environment preferences, authorization rules, and payload content types.
3. **Blank Line (`\r\n`):** A mandatory empty line serving as an explicit indicator marking the technical termination of the header payload tracking segment.
4. **Message Body (Payload):** Contains the raw data transmitted directly to the server infrastructure. This segment is mandatory for `POST` and `PUT` executions (e.g., form submissions or JSON objects) but entirely absent in common `GET` requests.

#### 💻 HTTP Request Exam Blueprint
```http
POST /registration.php HTTP/1.1
Host: www.srmtrp.edu.in
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:124.0) Gecko/20100101 Firefox/124.0
Accept: text/html,application/xhtml+xml,application/json
Content-Type: application/x-www-form-urlencoded
Content-Length: 43
Connection: keep-alive

username=faisal&email=faisal%40srmtrp.edu.in
```

### 🔍 Structure of an HTTP Response Message
The web server parses incoming request headers and constructs a structured response message composed of four blocks:
1. **Status Line:** Contains three operational markers:
   - *HTTP Version:* Protocol specification.
   - *Status Code:* A 3-digit numeric indicator outlining request processing success or failure tracking markers.
   - *Reason Phrase:* A human-readable textual snippet matching the status code (e.g., `OK`, `Not Found`).
2. **Response Headers:** Metadata providing content delivery configurations, compression algorithms, origin servers, and security rule updates.
3. **Blank Line:** Mandatory separator empty line.
4. **Response Body:** The active presentation payload requested by the engine (HTML text markup, file stream binaries, or JSON data).

#### 💻 HTTP Response Exam Blueprint
```http
HTTP/1.1 200 OK
Date: Thu, 04 Jun 2026 10:00:00 GMT
Server: Apache/2.4.52 (Ubuntu)
Last-Modified: Wed, 03 Jun 2026 18:30:00 GMT
Content-Type: text/html; charset=UTF-8
Content-Length: 124
Connection: close

<!DOCTYPE html>
<html>
<head><title>Success</title></head>
<body><h1>Registration Successful!</h1></body>
</html>
```

### 📊 Analytical Status Code Classification Matrix
These status metrics indicate operational pathways during communication tasks:

| Range | Class | Purpose / Core Description | Academic Examples |
| :--- | :--- | :--- | :--- |
| **1xx** | Informational | Request received; the server is continuing the internal process. | `100 Continue`, `101 Switching Protocols` |
| **2xx** | Success | The action was successfully received, understood, and accepted. | `200 OK`, `201 Created`, `204 No Content` |
| **3xx** | Redirection | Further action must be taken by the client to fulfill the request. | `301 Moved Permanently`, `304 Not Modified` |
| **4xx** | Client Error | The request contains bad syntax or cannot be fulfilled by the client. | `400 Bad Request`, `401 Unauthorized`, `404 Not Found` |
| **5xx** | Server Error | The server failed to fulfill an otherwise apparently valid request. | `500 Internal Server Error`, `503 Service Unavailable` |

---

## 2. HTML5 Control Elements & Advanced Semantics
- **Syllabus Sub-topics:** HTML5 – Tables – Lists – Image – HTML5 control elements
- **Weight:** Mandatory (Topic 2) | **Exam Alignment:** 2024 Part B (Q11a) [cite: 36, 37]

### 🔍 Core Table Structural Elements Deep-Dive
To secure maximum grading evaluation, all table components must be documented accurately:
- `<table>`: Root organizational element initializing the browser grid-layout rendering calculations.
- `<caption>`: Implements context descriptions for semantic document indexing.
- `<thead>`: Clusters header control rows, supporting tabular pagination flows across printing layouts.
- `<tbody>`: Contains the main application dataset records, isolated from upper header blocks.
- `<tfoot>`: Seals bottom contextual spaces, highlighting calculation balances or summarizing notations.
- `<tr>`: Maps out horizontal container lines.
- `<th>`: Renders bold, centered text labels.
- `<td>`: Houses standard grid values.
- **Span Modifiers:**
  - `colspan="[integer]"`: Stretches a target data block horizontally over multiple column intervals.
  - `rowspan="[integer]"`: Distributes an element downward through multiple consecutive row blocks.

### 💻 University Timetable Code Blueprint
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professional CSE Academic Timetable</title>
</head>
<body>

    <header>
        <h1 align="center">SRM TRP Engineering College</h1>
        <h2 align="center">Department of Computer Science and Engineering</h2>
    </header>

    <main>
        <table border="2" align="center" width="85%" cellspacing="0" cellpadding="10">
            <caption><strong>Academic Class Timetable - Semester V (ODD)</strong></caption>
            
            <thead>
                <tr bgcolor="#D3D3D3">
                    <th width="15%">Day / Time</th>
                    <th width="20%">09:00 AM - 10:30 AM</th>
                    <th width="20%">10:45 AM - 12:15 PM</th>
                    <th width="10%">12:15 PM - 01:15 PM</th>
                    <th width="20%">01:15 PM - 02:45 PM</th>
                    <th width="15%">03:00 PM - 04:30 PM</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td align="center"><strong>MONDAY</strong></td>
                    <td>CCS375 Web Technologies</td>
                    <td>CS3501 Compiler Design</td>
                    <td rowspan="5" align="center" bgcolor="#F5F5F5">
                        <strong>L<br>U<br>N<br>C<br>H</strong>
                    </td>
                    <td>MA3501 Probability & Stats</td>
                    <td>Aptitude Training</td>
                </tr>
                <tr>
                    <td align="center"><strong>TUESDAY</strong></td>
                    <td colspan="2" align="center" bgcolor="#E6F2FF">
                        <strong>CCS375 Web Technologies Laboratory (Lab 3)</strong>
                    </td>
                    <td>CS3501 Compiler Design</td>
                    <td>Library Reference</td>
                </tr>
                <tr>
                    <td align="center"><strong>WEDNESDAY</strong></td>
                    <td>MA3501 Probability & Stats</td>
                    <td>CCS375 Web Technologies</td>
                    <td>Mini Project Review</td>
                    <td>Placement Seminar</td>
                </tr>
                <tr>
                    <td align="center"><strong>THURSDAY</strong></td>
                    <td colspan="2" align="center" bgcolor="#E6FFE6">
                        <strong>Object Oriented Analysis and Design Lab</strong>
                    </td>
                    <td>CS3501 Compiler Design</td>
                    <td>MA3501 Probability & Stats</td>
                </tr>
                <tr>
                    <td align="center"><strong>FRIDAY</strong></td>
                    <td>Aptitude Crack Session</td>
                    <td>CS3501 Compiler Design</td>
                    <td>CCS375 Web Technologies</td>
                    <td>Sports / Club Activity</td>
                </tr>
            </tbody>

            <tfoot>
                <tr bgcolor="#F5F5F5">
                    <td colspan="6">
                        <strong>Note:</strong> 10:30 AM - 10:45 AM and 02:45 PM - 03:00 PM are designated as Short Breaks.
                    </td>
                </tr>
            </tfoot>
        </table>
    </main>

</body>
</html>
```

---

## 3. CSS3: Inclusion Types, Rule Cascading, & Inheritance Mechanics
- **Syllabus Sub-topics:** CSS3 – Inline, embedded and external style sheets – Rule cascading – Inheritance
- **Weight:** Mandatory (Topic 3 & 4) | **Exam Alignment:** 2024 Part B (Q11bi) [cite: 39, 40]

### 🔍 Architectural Overview of Inclusion Methods

#### A. Inline CSS
- **Mechanism:** Applied directly inside individual elements via the localized `style` attribute marker.
- **Use Case:** Executing instant layout translations via JavaScript runtime calculation sweeps.
- **Pros:** Overrides general presentation targets instantly; provides narrow execution targeting.
- **Cons:** Breaks structural clean Separation of Concerns rules; yields bloated code footprints; halts document cache pooling.
- **Code:** `<button style="background-color: #2563eb; color: #ffffff; padding: 12px 24px;">Click</button>`

#### B. Internal (Embedded) CSS
- **Mechanism:** Encapsulated structural declarations nested within a `<style>` container inside the global `<head>` tracking blocks.
- **Use Case:** Creating fast structural styles for single landing sites or lightweight reporting screens.
- **Pros:** Saves additional external network download handshakes during initial startup processing routines.
- **Cons:** Styles cannot be distributed to auxiliary document views; grows central document sizes.
- **Code:**
```html
<head>
    <style>
        .dashboard-card { background: #fff; padding: 20px; border-radius: 8px; }
    </style>
</head>
```

#### C. External CSS
- **Mechanism:** Segregated into dedicated `.css` script files and imported into the view header matching `<link rel="stylesheet" href="style.css">`.
- **Use Case:** Managing styling uniformity across large-scale enterprise software systems.
- **Pros:** Promotes extreme separation of structural definitions; allows web browsers to cache style assets globally.
- **Cons:** Triggers an initial asset query call thread that block-halts UI drawing runs if connection routing stalls.

### ⚖️ The Mechanics of Rule Cascading (Conflict Engine)
When multiple styling definitions point to the same structural entity, conflict layout properties are processed downward through a hierarchical waterfall:

```text
       [ RULE CONFLICT OCCURS ]
                  │
                  ▼
       1. Importance Check ───────► (Is !important declared?)
                  │
                  ▼
       2. Specificity Metric ─────► (Calculate Weight: ID > Class > Element)
                  │
                  ▼
       3. Source Order Rule ──────► (Latest line written takes precedence)
```

1. **Importance:** Rules appended with `!important` instantly override standard cascading weights.
2. **Specificity Vector Calculation:** Resolves matching rules via a 3-part matrix assessment scoring `(ID, Class, Element)`:
   - *ID Level Selectors (`#component`):* Evaluates as `1-0-0`.
   - *Class / Attribute Selectors (`.item`, `[type="text"]`):* Evaluates as `0-1-0`.
   - *Element Typographic Selectors (`div`, `section`):* Evaluates as `0-0-1`.
   - *Example:* The selector combination `div.main ul li.active` produces specificity calculation code points of `(0, 2, 3)`, which instantly yields priority to a single ID declaration mapping value matrix calculations of `(1, 0, 0)`.
3. **Source Order Resolution:** If specificity metrics achieve numeric parity, the configuration defined lowest in the evaluation hierarchy overrides preceding definitions.

### 🧬 Property Inheritance Systems
- **Inherited Values:** Typographic properties (e.g., `font-family`, `color`, `line-height`, `text-align`) pass directly from parent containers down through nested DOM branches.
- **Non-Inherited Box Attributes:** Layout structural parameters (e.g., `margin`, `padding`, `border`, `width`, `height`) are strictly isolated at their target layer to prevent rendering corruption downstream.

---

## 4. Transition vs. Animation Frameworks in CSS3
- **Syllabus Sub-topics:** CSS3 – Transformations – Transitions – Animations
- **Weight:** Syllabus Content | [cite_start]**Exam Alignment:** 2024 Part B (Q11bii) [cite: 43]

### 🧠 Core Mechanical Pipeline
- **Transitions:** Compute variable properties along a path strictly linking a starting point to an ending position. They cannot create intermediary milestones and rely on interactive pseudo-state events (like cursor overlays via `:hover`) to trigger rendering loops.
- **Animations:** Fully automated processing engines that cycle dynamically upon elements entering the DOM view, using programmatic keyframe intervals (`@keyframes`) to loop without state modifiers.

### 📊 Structural Comparison Matrix

| Feature Dimension | CSS3 Transitions | CSS3 Keyframe Animations |
| :--- | :--- | :--- |
| **State Boundaries** | Binary Path Mapping (Point A directly to Point B). | Complex multi-point progressions (0% through 100% ranges). |
| **Trigger Requirements** | Requires pseudo-state hooks (e.g., `:hover`, `:focus`). | Runs autonomously on page loading or code executions. |
| **Timeline Management** | Single property trajectory distribution. | Segmented curves tracking distinct interval keys. |
| **Looping Loops** | Runs once per interaction loop. | Indefinite loops via `animation-iteration-count`. |
| **Keyframe Hooks** | Not Supported. | Driven by explicit `@keyframes` definitions. |

### 💻 Animation Code Blueprints
```css
/* ==========================================
   CSS3 TRANSITION PROPERTIES
   ========================================== */
.interactive-btn {
    background-color: #2563eb;
    color: #ffffff;
    padding: 14px 28px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.4s cubic-bezier(0.4, 0, 0.2, 1), transform 0.2s ease;
}

.interactive-btn:hover {
    background-color: #1d4ed8;
    transform: translateY(-2px);
}

/* ==========================================
   CSS3 AUTOMATED KEYFRAME INTERPOLATION
   ========================================== */
.loading-bar-container {
    width: 100%;
    height: 4px;
    background-color: #e2e8f0;
    overflow: hidden;
    position: relative;
}

.loading-progress-line {
    width: 40%;
    height: 100%;
    background-color: #10b981;
    position: absolute;
    animation-name: infiniteShimmer;
    animation-duration: 1.8s;
    animation-timing-function: ease-in-out;
    animation-iteration-count: infinite;
}

@keyframes infiniteShimmer {
    0% { left: -40%; }
    50% { width: 60%; }
    100% { left: 100%; }
}
```

---

## 5. Bootstrap Responsive Layout Engine
- **Syllabus Sub-topics:** Bootstrap Framework
- **Weight:** Important (Topic 5) | **Syllabus Content**

### 🏗️ The 12-Column Grid Architecture
Bootstrap structures presentation grids using fluid flexbox layouts distributed into three key hierarchy classes:
1. **Containers (`.container` / `.container-fluid`):** Establish fixed structural boundaries, locking element padding widths based on the active viewport.
2. **Rows (`.row`):** Act as explicit flex-containers, executing negative horizontal margin alignments to offset column paddings.
3. **Columns (`.col-*`):** Grid cells that dynamically distribute across 12 horizontal allocation paths.

```text
┌────────────────────────────────────────────────────────────────────────┐
│ .container                                                             │
│  ┌──────────────────────────────────────────────────────────────────┐  │
│  │ .row                                                             │  │
│  │  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │  │
│  │  │ .col-md-3    │ │ .col-md-3    │ │ .col-md-3    │ │ .col-md-3    │ │  │
│  │  └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘ │  │
│  └──────────────────────────────────────────────────────────────────┘  │
└────────────────────────────────────────────────────────────────────────┘
```

### 📏 Grid Breakpoint Threshold Classes
- `xs` (Mobile portrait views): `<576px` $\rightarrow$ Example: `.col-12`
- `sm` (Mobile landscape screens): $\ge 576px$ $\rightarrow$ Example: `.col-sm-6`
- `md` (Tablet display sizes): $\ge 768px$ $\rightarrow$ Example: `.col-md-4`
- `lg` (Laptop environments): $\ge 992px$ $\rightarrow$ Example: `.col-lg-3`
- `xl` (Large desktop frameworks): $\ge 1200px$ $\rightarrow$ Example: `.col-xl-2`

### 💻 Responsive Grid Layout Dashboard Template
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bootstrap Structural Grid Layout</title>
    <link rel="stylesheet" href="[https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css](https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css)">
</head>
<body class="bg-light">

    <div class="container my-5">
        <h2 class="text-center mb-4">Institutional Features Dashboard</h2>
        
        <div class="row g-4">
            <div class="col-12 col-md-6 col-lg-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Placement Portal</h5>
                        <p class="card-text">Access corporate scheduling matrices, resume building blocks, and practice mock aptitude sequences.</p>
                        <a href="#" class="btn btn-primary">Launch Portal</a>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-6 col-lg-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">LeetCode Sync Dashboard</h5>
                        <p class="card-text">Monitor student algorithmic submission rates, metrics pipelines, and mastery log execution records daily.</p>
                        <a href="#" class="btn btn-success">View Metrics</a>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-12 col-lg-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Digital Library Engine</h5>
                        <p class="card-text">Review and stream technical video lectures, text assets, and web technology reference guides seamlessly.</p>
                        <a href="#" class="btn btn-dark">Open Library</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
```