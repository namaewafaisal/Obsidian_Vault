# 📘 UNIT I: Website Basics, HTML5 & CSS3 (Exam Blueprint Mastery Notes)

## 1. Architectural Foundations: Web Clients, Servers, & HTTP
- **Syllabus Sub-topics:** Web Essentials: Clients, Servers, and Communication – HTTP Request/Response
- **Weight:** Mandatory Topic | **Exam Alignment:** Part A Core Definitions & Comparisons

### 📊 Client-Server Structural Comparison Matrix
A web transaction relies on a structured architecture mapping localized requests directly to remote execution hosts:

| Assessment Domain | Web Client Interface | Web Server Engine |
| :--- | :--- | :--- |
| **Primary Definition** | A software application (typically a browser) that initiates communication by sending requests for web resources. | A remote software framework (like Apache or Nginx) that continuously listens for incoming client requests. |
| **Core Operational Role** | Translates and renders raw HTML, CSS, and JavaScript code blocks into a visual UI. | Processes backend logic, queries databases, and serves structural file payloads back to the client. |
| **Initiation Control** | Active Partner: Always initiates the communication cycle via an explicit request line. | Passive Partner: Waits for incoming requests and responds only when hit with a valid request string. |

```text
       [ Web Client ]  ======( HTTP Request Message )======>  [ Web Server ]
       (Browser Interface) <====( HTTP Response Message )======  (Apache/Nginx Engine)
```

### 🔍 HTTP Request vs. Response Structure
- **HTTP Request Message:** A plain-text block sent by a client to request a specific target resource from a server host.
  - *Example Request Line:*
```http
    GET /index.html HTTP/1.1
    Host: www.srmtrp.edu.in
```
- **HTTP Response Message:** The structural layout block returned by the server containing both execution status tokens and the resource payload.
  - *Example Response Status Line:*
```http
    HTTP/1.1 200 OK
    Content-Type: text/html
```

---

## 2. Structural Layer: HTML5 Core Elements & Layout Design
- **Syllabus Sub-topics:** HTML5 – Control Elements – Tables
- **Weight:** Mandatory Core Essay | **Exam Alignment:** 2026 Model Exam Q11a / Q11b [13 Marks]

### 🏗️ The 5 Essential Table Design Elements Explained
To achieve full credit under university evaluation rubrics, you must explicitly outline these 5 structural table elements:
1. `<table>`: The root structural block container that encapsulates all tabular grid data rows.
2. `<tr>` (Table Row): Establishes a horizontal container line inside the table layout to hold cell segments.
3. `<th>` (Table Header): Defines header cells at the top of rows/columns. Text is rendered as **bold** and **centered** automatically by browsers.
4. `<td>` (Table Data): Represents the standard structural data entry container holding individual text strings or numbers.
5. **Structural Spanning Modifiers (`colspan` and `rowspan`):**
   - `colspan="X"`: Instructs a single cell block to stretch horizontally across `X` number of columns.
   - `rowspan="Y"`: Instructs a single cell block to merge vertically down across `Y` number of continuous rows.

### 💻 Clean, Zero-Bloat HTML Timetable Blueprint
```html
<!DOCTYPE html>
<html>
<head>
    <title>Class Timetable</title>
</head>
<body>

    <h2 align="center">Academic Class Timetable</h2>

    <!-- Table structure with explicit borders, width, padding, and layout centering -->
    <table border="1" align="center" width="80%" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Day / Hour</th>
                <th>09:00 AM - 10:30 AM</th>
                <th>10:45 AM - 12:15 PM</th>
                <th>12:15 PM - 01:15 PM</th>
                <th>01:15 PM - 02:45 PM</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><b>Monday</b></td>
                <td>Web Technologies</td>
                <td>Compiler Design</td>
                <!-- Vertical cell merging spanning 3 rows downward -->
                <td rowspan="3" align="center"><b>L<br>U<br>N<br>C<br>H</b></td>
                <td>Probability & Stats</td>
            </tr>
            <tr>
                <td><b>Tuesday</b></td>
                <!-- Horizontal cell merging spanning 2 columns sideways -->
                <td colspan="2" align="center">Web Technologies Lab</td>
                <td>Compiler Design</td>
            </tr>
            <tr>
                <td><b>Wednesday</b></td>
                <td>Probability & Stats</td>
                <td>Web Technologies</td>
                <td>Placement Training</td>
            </tr>
        </tbody>
    </table>

</body>
</html>
```

---

## 3. Presentation Layer: CSS3 Style Sheets & Cascading Rules
- **Syllabus Sub-topics:** Cascading Style Sheets – Inline, Embedded, and External Style Sheets
- **Weight:** Mandatory Topic | **Exam Alignment:** 2026 Model Exam Q11a (Set A) / Q11b-i (Set B)

### 🔍 Strategic Breakdown of the 3 Style Sheet Types
- **Inline CSS:** Styles are defined directly inside individual HTML elements using the local `style="..."` attribute. Highest priority in cascade calculations, but breaks code reusability.
- **Internal / Embedded CSS:** Code is contained completely inside a `<style>` block located within the document `<head>` boundaries. Perfect for self-contained, single-page standalone web tools.
- **External CSS:** Styling rules are written in an independent text file (`.css`) and linked to the document via a `<link>` tag. Represents industry best practice by enabling global multisite caching.

### 💻 Integrated Code Sample Demonstrating All 3 Style Sheet Methods
```html
<!DOCTYPE html>
<html>
<head>
    <title>CSS Inclusion Framework</title>

    <!-- Method 1: External Style Sheet Linking format -->
    <link rel="stylesheet" href="global-styles.css">

    <!-- Method 2: Internal/Embedded Style Sheet Declaration -->
    <style>
        /* This internal ruleset styles all h2 elements globally on this specific page */
        h2 {
            color: darkblue;
            text-align: center;
        }
        
        .container-box {
            background-color: lightgray;
            padding: 15px;
        }
    </style>
</head>
<body>

    <h2>Main Headline Styled Internally</h2>

    <!-- Method 3: Inline CSS Applied Locally Inside an HTML Element Tag -->
    <p style="color: red; font-size: 18px;">
        This text is styled directly via an inline style attribute.
    </p>

    <div class="container-box">
        This box components uses layout rules defined inside the embedded block.
    </div>

</body>
</html>
```

---

## 4. Advanced CSS3 Interaction: Transitions vs. Animations
- **Syllabus Sub-topics:** CSS3 Transitions & Animations
- **Weight:** Core Technical Comparison | **Exam Alignment:** 2026 Model Exam Q11b-ii (Set B) [13 Marks Breakdown]

### 📊 Difference Index Matrix (8 Marks Allocation Rule)
This clear, precise breakdown satisfies the explicit 8-point difference ledger demanded by the evaluation key:

| Point | Assessment Attribute | CSS3 Transitions | CSS3 Keyframe Animations |
| :--- | :--- | :--- | :--- |
| **1** | **State Transformations** | Moves linearly across a strict binary structure (Point A directly to Point B). | Allows complex, multi-point paths shifting through custom ranges (0% to 100%). |
| **2** | **Trigger Requirements** | Requires an active state modifier to launch (e.g., `:hover`, `:focus`). | Can launch completely automatically upon the document loading phase finishing. |
| **3** | **Loop Capabilities** | Does not natively support looping; executes exactly once per manual user action. | Supports permanent looping loops via the `animation-iteration-count: infinite;` parameter. |
| **4** | **Timeline Precision** | Cannot introduce independent midway pause intervals or custom interim steps. | Features fine-grained structural control over time vectors using explicit percentage offsets. |
| **5** | **Syntax Location** | Declared inline directly inside standard structural element property blocks. | Requires an independent `@keyframes` definition code block linked via an execution class rule. |
| **6** | **Property Isolation** | Modifies existing default layout rules when pseudo-classes change state context. | Can define entirely independent property motions separate from baseline layout rules. |
| **7** | **JS Interception** | Extremely difficult to interrupt programmatically mid-way through an active run. | Offers extensive structural programmatic lifecycle hooks (`animationstart`, `animationend`). |
| **8** | **Rendering Footprint**| Extremely low overhead; processed directly inside basic layout engines. | Slightly higher resource footprint due to continuous timeline marker checks. |

### 🔍 Structural Comparisons & Implementation Code (5 Marks Allocation Rule)
1. **Shared Objective:** Both features are native browser processing architectures designed to modify visual interface elements smoothly without needing external script engines.
2. **Timing Curves:** Both rely on identical velocity interpolation properties (like `linear`, `ease-in`, and `ease-out`) to control operational acceleration.
3. **Hardware Performance:** Both layout solutions leverage the underlying device GPU directly to achieve lag-free, hardware-accelerated screen updates.
4. **Transition Code Example:**
```css
   /* Smoothly changes element color over 0.5 seconds on mouse hover */
   .btn-target {
       background-color: blue;
       transition: background-color 0.5s ease;
   }
   .btn-target:hover {
       background-color: green;
   }
   ```
5. **Animation Code Example:**
```css
   /* Automatically and infinitely fades element opacity back and forth */
   .pulse-element {
       animation: fadeEffect 2s infinite ease-in-out;
   }
   @keyframes fadeEffect {
       0% { opacity: 0.2; }
       50% { opacity: 1.0; }
       100% { opacity: 0.2; }
   }
   ```