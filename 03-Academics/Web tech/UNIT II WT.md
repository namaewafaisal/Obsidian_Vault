# 📘 UNIT II: Client-Side Scripting (JavaScript & DOM)

## 1. Core Language Foundations & Dynamic Data Types
- **Syllabus Sub-topics:** Introduction to JavaScript – Variables – Data Types – Operators
- **Weight:** Mandatory Topic | **Exam Evaluation Key:** Data Types Breakdown (5 Marks) + Operators Explanation with Snippets (8 Marks) [Set B / Apr-May 2024]

### 📊 Structural Classification Matrix of JavaScript Data Types
JavaScript uses a dynamic type system where variables don't have a fixed type; instead, the value currently assigned to the variable determines its data type. These types are strictly divided into two categories:

| Category | Data Type | Description / Behavior | Code Example |
| :--- | :--- | :--- | :--- |
| **Primitive Types** *(Passed by value, completely immutable)* | **Number** | Handles both integers and floating-point decimal values. | `let marks = 98;` <br> `let cgpa = 9.15;` |
| | **String** | A sequence of characters used to represent text data. | `let college = "SRM TRP";` |
| | **Boolean** | A logical entity representing truth tracks. | `let isPassed = true;` |
| | **Undefined**| Assigned automatically to a declared variable that lacks a value. | `let totalScore;` |
| | **Null** | A deliberate assignment indicating an empty or non-existent object. | `let response = null;` |
| **Reference Types** *(Passed by reference, structural mutability)* | **Object** | A collection of related data stored as key-value property pairs. | `let student = {id: 1, name: "Faisal"};` |
| | **Array** | A sequentially indexed list used to group values together. | `let courses = ["WT", "CD", "CD"];` |

---

### 🔍 Complete Breakdown of JavaScript Operators
- **Arithmetic Operators:** Perform mathematical calculations.
  ```javascript
  let sum = 20 + 10;       // Addition (30)
  let power = 2 ** 3;     // Exponentiation (2 cubed = 8)
  ```
- **Assignment Operators:** Assign values to variables.
  ```javascript
  let count = 5;
  count += 2;             // Shorthand for count = count + 2 (7)
  ```
- **Comparison Operators (Loose Equality vs. Strict Identity):**
  - `==` (**Loose Equality**): Compares the values of two variables after automatically converting them to a common type (Type Coercion).
  - `===` (**Strict Equality**): Compares **both the value and the exact data type** directly without converting them.
  ```javascript
  console.log("5" == 5);  // Returns true (string "5" is coerced into number 5)
  console.log("5" === 5); // Returns false (types 'string' and 'number' do not match)
  ```
- **Logical Operators:** Evaluate logical operational conditions.
  - `&&` (Logical AND): Returns true only if both conditions are met.
  - `||` (Logical OR): Returns true if at least one condition is met.

---

## 2. Architectural Analysis: Client-Side Scripting Needs
- **Syllabus Sub-topics:** The Need for Scripting Languages in Web Technologies
- **Weight:** Core Part B Essay | **Exam Evaluation Key:** Explicit 6-Mark Theoretical Breakdown [Set B / Apr-May 2024]

1. **Offloading Server Workload:** Without client-side scripting, basic actions (like computing math or validating forms) require sending data across the network to a backend server and waiting for a full page reload. JavaScript runs instantly inside the browser, preserving server resources.
2. **Dynamic HTML Rendering (DHTML):** Allows the web page to modify its layout, create new structural content on the fly, or change text dynamically without initiating a browser refresh.
3. **Asynchronous Communications:** Enables the page to exchange small payloads of data with a background server silently without freezing the user interface layout.

---

## 3. Dynamic Memory Management: The JavaScript DOM Tree Model
- **Syllabus Sub-topics:** JavaScript DOM Model – DHTML with JavaScript
- **Weight:** Mandatory Topic | **Exam Evaluation Key:** Architectural Model Breakup (5 Marks) + Event Handling Implementation (8 Marks) [Set A]

### 🏗️ DOM Structural Tree Diagram
The **Document Object Model (DOM)** is an Application Programming Interface (API) that converts a flat HTML text document into a live hierarchical tree structure of programmable objects called **Nodes**:

```text
                          [ Window Object ]
                                  │
                          [ Document Object ]
                                  │
                          [ <html> Element ]
                                  │
         ┌────────────────────────┴────────────────────────┐
         ▼                                                 ▼
  [ <head> Element ]                               [ <body> Element ]
         │                                                 │
  [ <title> Element ]                 ┌────────────────────┴────────────────────┐
         │                            ▼                                         ▼
   ( Text Node )               [ <h1> Element ]                          [ <a> Element ]
                                      │                                         │
                                ( Text Node )                            { Attribute: href }
```

### 💻 Practical Implementation of DOM Manipulation & Event Handling
This program demonstrates how JavaScript hooks an HTML element, listens for a user interaction event, and uses the DOM API to dynamically create and append a brand-new node onto the live UI tree:

```html
<!DOCTYPE html>
<html>
<head>
    <title>DOM Tree Event Handling</title>
</head>
<body>

    <button id="createNodeBtn">Generate Dynamic Element</button>
    <div id="displayContainer"></div>

    <script>
        // Step 1: Secure elements from the tree using DOM Hooks
        let actionBtn = document.getElementById("createNodeBtn");
        let parentBox = document.getElementById("displayContainer");

        // Step 2: Set up an Event Listener to wait for a click trigger
        actionBtn.addEventListener("click", function() {
            
            // Step 3: Instantiate a brand-new paragraph node in memory
            let newParaNode = document.createElement("p");
            
            // Step 4: Inject plain text inside our newly created node
            newParaNode.innerText = "This node element was generated dynamically via the DOM Tree API!";
            
            // Step 5: Append the memory node into the live visible UI container
            parentBox.appendChild(newParaNode);
        });
    </script>

</body>
</html>
```

---

## 4. Secure Client-Side Form Validation Engine
- **Syllabus Sub-topics:** Form Validation – Event Handling
- **Weight:** Core Part B Essay Target | **Exam Evaluation Key:** Conceptual Summary (5 Marks) + Validation Program (8 Marks) [Set A]

### 🔍 Core Concepts of Input Validation
Form validation ensures that user input matches acceptable boundaries before any data leaves the client browser. It runs three critical logic checks:
1. **Presence Inquiries:** Verifying fields are not empty using text trimming tools (`.value.trim() === ""`).
2. **Formatting Inspections:** Scanning text strings to confirm mandatory structural layout markers (like checking for `@` and `.` characters in an email box using `.includes()`).
3. **Length Calculations:** Counting character boundaries (`.length < 8`) to enforce basic security constraints on inputs like passwords.

### 💻 Unbloated Registration Validator Code Blueprint
```html
<!DOCTYPE html>
<html>
<head>
    <title>Registration Validator</title>
</head>
<body>

    <h3>Account Creation Form</h3>

    <form id="userRegistrationForm">
        <label>Username:</label><br>
        <input type="text" id="usernameInput"><br><br>

        <label>Email Address:</label><br>
        <input type="text" id="emailInput"><br><br>

        <label>Password:</label><br>
        <input type="password" id="passwordInput"><br><br>

        <button type="submit">Submit Account Data</button>
    </form>

    <p id="alertMessageDisplay" style="color: red;"></p>

    <script>
        let formContainer = document.getElementById("userRegistrationForm");
        let alertText = document.getElementById("alertMessageDisplay");

        // Intercept the submission event
        formContainer.addEventListener("submit", function(event) {
            
            // Capture real-time values and remove accidental white spaces
            let userVal = document.getElementById("usernameInput").value.trim();
            let emailVal = document.getElementById("emailInput").value.trim();
            let passVal = document.getElementById("passwordInput").value;

            // Check 1: Empty Field Check
            if (userVal === "") {
                event.preventDefault(); // Stop the form from submitting and reloading the page
                alertText.innerText = "Validation Alert: Username field cannot be left empty.";
                return; // Kill execution line immediately
            }

            // Check 2: Structural Format Check (Email string validation)
            if (!emailVal.includes("@") || !emailVal.includes(".")) {
                event.preventDefault(); // Intercept submission
                alertText.innerText = "Validation Alert: Invalid email structure (must include '@' and '.').";
                return; 
            }

            // Check 3: Boundary Length Check (Password validation)
            if (passVal.length < 8) {
                event.preventDefault(); // Intercept submission
                alertText.innerText = "Validation Alert: Password must be at least 8 characters long.";
                return;
            }

            // If execution passes all validation checks seamlessly
            alertText.style.color = "green";
            alertText.innerText = "Validation Success: Account processing approved!";
        });
    </script>

</body>
</html>
```

---

## 5. Analytical Problem Solving: Mathematical Conversion Framework
- **Syllabus Sub-topics:** Built-in Objects – Problem Solving Logic
- **Weight:** Essential Program Scenario | **Exam Evaluation Key:** Flawless Temperature Conversion Program Logic (7 Marks) [Set B / Apr-May 2024]

### 💻 Zero-Bloat Temperature Conversion Implementation
```html
<!DOCTYPE html>
<html>
<head>
    <title>Temperature Conversion Frame</title>
</head>
<body>

    <h3>Temperature Converter Framework</h3>

    <input type="number" id="numericalValInput" placeholder="Enter degrees value">
    
    <select id="conversionDirectionSelector">
        <option value="CtoF">Celsius to Fahrenheit</option>
        <option value="FtoC">Fahrenheit to Celsius</option>
    </select>
    
    <button id="calculateConversionBtn">Execute Conversion</button>

    <p id="calculationResultDisplay"></p>

    <script>
        let enteredValue = document.getElementById("numericalValInput");
        let activeSelection = document.getElementById("conversionDirectionSelector");
        let launchBtn = document.getElementById("calculateConversionBtn");
        let resultView = document.getElementById("calculationResultDisplay");

        launchBtn.addEventListener("click", function() {
            let valueToConvert = Number(enteredValue.value);
            let selectedMode = activeSelection.value;
            let computedResult = 0;

            if (selectedMode === "CtoF") {
                // Mathematical Formula: (Celsius * 9/5) + 32
                computedResult = (valueToConvert * 9 / 5) + 32;
                resultView.innerText = valueToConvert + "°C converts directly into " + computedResult + "°F";
            } else {
                // Mathematical Formula: (Fahrenheit - 32) * 5/9
                computedResult = (valueToConvert - 32) * 5 / 9;
                resultView.innerText = valueToConvert + "°F converts directly into " + computedResult + "°C";
            }
        });
    </script>

</body>
</html>
```