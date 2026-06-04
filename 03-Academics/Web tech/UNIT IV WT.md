# 🐘 UNIT IV: PHP and XML

## 1. Server-Side Processing: PHP Data Types & Operations
- **Syllabus Sub-topics:** PHP – Using PHP – Variables – Program control – Built-in functions
- **Weight:** Core Part A and Part B Target | **Exam Evaluation Key:** Data Type Classifications (2 Marks) / Form String Function Layouts (7 Marks) [Set A / Set B / Apr-May 2024]

### 📊 PHP Built-In String Function Guide
PHP provides built-in string manipulation functions crucial for processing form fields and scrubbing raw database inputs:
* `strlen($str)`: Returns the absolute integer character count of a target string.
* `strpos($str, $search)`: Finds the exact index position of the first occurrence of a substring.
* `strtolower($str)` / `strtoupper($str)`: Forces a string into all-lowercase or all-uppercase text blocks.
* `substr($str, $start, $length)`: Extracts and returns a specific segment of a string.
* `trim($str)`: Strips out accidental blank spaces from the front and back of user inputs.

---

## 2. Multi-Part Server File Uploads & Input Security
- **Syllabus Sub-topics:** Form Validation – Built-in functions (File Handling)
- **Weight:** High-Yield Part B Essay | **Exam Evaluation Key:** Form Enctype Specification + `$_FILES` Matrix Parsing Logic (13 Marks) [Set A / Set B / Apr-May 2024]

### 💻 Production-Ready PHP File Upload Engine
This script reads multi-part file payloads sent via HTTP POST, moves the file safely from temporary memory to a permanent location, and prints its metadata properties:

```php
<?php
// Enforce strict request verification loops
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    // 1. Establish path boundaries for target storage directories
    $uploadTargetDir = "var/www/html/uploads/";
    $baseFileName = basename($_FILES["clientFile"]["name"]);
    $completeSavePath = $uploadTargetDir . $baseFileName;
    
    // 2. Validate upload status indicators inside the $_FILES error registers
    if ($_FILES["clientFile"]["error"] === UPLOAD_ERR_OK) {
        
        // 3. Move the file from temporary storage to the permanent directory
        if (move_uploaded_file($_FILES["clientFile"]["tmp_name"], $completeSavePath)) {
            echo "<h3>Backend File Registry System</h3>";
            echo "<span style='color:green;'>Upload verified and locked successfully.</span><br><br>";
            
            // 4. Output complete structural metadata properties
            echo "<b>File Metadata Breakdown:</b><br>";
            echo "• Assigned Name: " . htmlspecialchars($baseFileName) . "<br>";
            echo "• Content MIME Type: " . $_FILES["clientFile"]["type"] . "<br>";
            echo "• Computed Payload Size: " . ($_FILES["clientFile"]["size"] / 1024) . " KB<br>";
            echo "• Temporary Storage Node: " . $_FILES["clientFile"]["tmp_name"] . "<br>";
        } else {
            echo "<p style='color:red;'>System Error: Cannot move file out of temporary storage memory.</p>";
        }
    } else {
        echo "<p style='color:red;'>Upload Interrupted. Error Code: " . $_FILES["clientFile"]["error"] . "</p>";
    }
}
?>
```

---

## 3. Structural Data Modeling: XML Integrity Rules
- **Syllabus Sub-topics:** Basic XML – Document Type Definition – XML Schema
- **Weight:** Mandatory Question Mapping | **Exam Evaluation Key:** Well-Formedness vs. Validity Contrast (2 Marks) + Detailed DTD vs. XSD Matrix (6 Marks) [Set A / Set B / Apr-May 2024]

### 📊 Structural Blueprint Contrast Matrix

| Architectural Property | Document Type Definition (DTD) | XML Schema Definition (XSD) |
| :--- | :--- | :--- |
| **Syntax Foundation** | Uses a legacy non-XML text layout notation. | Written fully in standard XML, meaning it can be parsed using traditional XML engines. |
| **Data Typing Support** | **Extremely Weak.** Treats almost all values as basic unstructured text character data strings (`#PCDATA`). | **Highly Robust.** Natively supports primitive types (Integer, Boolean, Decimal, Date, String) and custom constraints. |
| **Namespace Awareness** | Completely unaware of XML Namespaces; prone to tag collision errors. | Fully aware of Namespaces, allowing you to seamlessly blend multiple distinct schemas. |
| **Extensibility Range** | Rigid structure; cannot be modified or extended using object-oriented concepts. | Highly extensible; supports derived types, restrictions, and complex structural inheritance patterns. |

---

## 4. Modern XML Tree Parsing & Style Transformations
- **Syllabus Sub-topics:** XML Parsers and Validation, XSL
- **Weight:** Analytical Part B Track | **Exam Evaluation Key:** DOM vs. SAX Processing Models (6 Marks) + Layout Transformation Syntax (7 Marks) [Syllabus Core]

### 📊 Tree-Based DOM vs. Event-Driven SAX Parsers

| Operational Metrics | DOM (Document Object Model) Parser | SAX (Simple API for XML) Parser |
| :--- | :--- | :--- |
| **Processing Paradigm** | **Tree-Based.** Loads the entire document and maps it into a hierarchical memory node architecture. | **Event-Driven.** Reads the data sequentially line-by-line, triggering event hooks for each element. |
| **Memory Footprint** | High; usage scales exponentially with the size of the target XML document. | Minimal; constant, ultra-low memory overhead regardless of document size. |
| **Data Traversal** | Bi-directional; allows you to traverse backward and forward across nodes smoothly. | Forward-only; strictly linear data stream processing. |
| **Write/Modification** | Supports full read, write, update, and node deletion routines. | Read-Only data streaming. |

---

### 💻 XSL Template Transformation Layout Blueprint
This code block shows how an XSL stylesheet reads data fields from an XML file and wraps them in a styled HTML grid table for display:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="[http://www.w3.org/1999/XSL/Transform](http://www.w3.org/1999/XSL/Transform)">
  
  <xsl:template match="/">
    <html>
    <body>
      <h2>Institutional Course Roster</h2>
      <table border="1" style="border-collapse:collapse; text-align:left;">
        <tr style="background-color:#4CAF50; color:white;">
          <th>Course Identification Code</th>
          <th>Title Classification</th>
        </tr>
        
        <xsl:for-each select="catalog/subject">
          <tr>
            <td><xsl:value-of select="code"/></td>
            <td><xsl:value-of select="title"/></td>
          </tr>
        </xsl:for-each>
        
      </table>
    </body>
    </html>
  </xsl:template>
  
</xsl:stylesheet>
```

---

## 5. Enterprise Authentication: State Tracking & Redirection
- **Syllabus Sub-topics:** PHP Form Validation – Variables – Program control
- **Weight:** Part C 15-Mark Case Study Pillar | **Exam Evaluation Key:** Complete multi-flow control handling, state assignment routing, and password validation scripts (15 Marks) [Set A / Set B / Apr-May 2024]

### 💻 Full Portal Gateway and Account Recovery Controller
This integrated script acts as a security gateway. It processes user logins, redirects authenticated users to a secure dashboard, and handles forgot-password recovery steps within a single execution block:

```php
<?php
// Initialize server session registers to track authentication states
session_start();

// Simulated backend database credential arrays for validation checks
$dbUsername = "faisal";
$dbEmailAddress = "faisal@srm.edu";
$dbSecurePassword = "SrmTrpCse2026";

$statusMessage = "";

// 1. Authentication Processing Loop
if (isset($_POST['attemptLogin'])) {
    // Sanitize and extract incoming form parameters
    $clientUser = trim($_POST['inputUser']);
    $clientPass = trim($_POST['inputPass']);
    
    // Evaluate input metrics against database values
    if ($clientUser === $dbUsername && $clientPass === $dbSecurePassword) {
        $_SESSION['authenticatedUser'] = $clientUser;
        
        // Execute an explicit HTTP redirection to route the client to the dashboard
        header("Location: dashboard.php");
        exit(); // Terminate execution immediately post-redirect
    } else {
        $statusMessage = "Authentication Error: Credentials do not match our database.";
    }
}

// 2. Account Profile Password Recovery Loop
if (isset($_POST['recoverPassword'])) {
    $clientEmail = trim($_POST['inputEmail']);
    
    // Check if the provided recovery email exists in the database records
    if ($clientEmail === $dbEmailAddress) {
        $statusMessage = "Recovery Dispatch: Verified password matching profile is: " . $dbSecurePassword;
    } else {
        $statusMessage = "Recovery Failure: The requested email address is not recorded.";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Enterprise Security Access Portal</title>
</head>
<body style="font-family:sans-serif; margin:40px;">

    <h2>Secure Application Gateway Controller</h2>
    
    <?php if (!empty($statusMessage)): ?>
        <div style="padding:10px; background-color:#e1f5fe; color:#0277bd; margin-bottom:20px;">
            <?php echo htmlspecialchars($statusMessage); ?>
        </div>
    <?php endif; ?>

    <form method="POST" action="" style="margin-bottom:30px; padding:20px; border:1px solid #ccc;">
        <h3>Account Sign-In</h3>
        Username Entry: <input type="text" name="inputUser" required><br><br>
        Password Entry: <input type="password" name="inputPass" required><br><br>
        <button type="submit" name="attemptLogin">Execute Secure Authentication</button>
    </form>

    <form method="POST" action="" style="padding:20px; border:1px solid #ccc;">
        <h3>Forgot Password Recovery Service</h3>
        Provide Registered Account Email: <input type="email" name="inputEmail" required><br><br>
        <button type="submit" name="recoverPassword">Dispatch Password Retrieval</button>
    </form>

</body>
</html>
```