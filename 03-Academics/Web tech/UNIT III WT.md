# ☕ UNIT III: Server-Side Programming (Servlets & JDBC)

## 1. Enterprise Architecture: Java Servlets vs. CGI Engine
- **Syllabus Sub-topics:** Java Servlet Architecture – Advantages over CGI
- **Weight:** Core Part B Essay | **Exam Evaluation Key:** Explicit 5-Mark Theoretical Distinction / 6-Mark Comparison [Set B / Apr-May 2024]

### 📊 Structural Architectural Matrix
Before the introduction of Java Servlets, server-side dynamic task processing relied on the **Common Gateway Interface (CGI)** protocol. The fundamental shift down to Servlet multi-threaded architecture resolved critical hardware scaling limitations:

| Architectural Attribute | Common Gateway Interface (CGI) | Java Servlet Engine |
| :--- | :--- | :--- |
| **Process Allocation Model** | Spawns a brand-new operating system (OS) process on the server host machine for **every individual HTTP client request**. | Instantiates a single, persistent Java class object in memory **once**. Each request is then executed as a lightweight **Thread** inside that process. |
| **Server Memory Footprint** | **Extremely Heavy.** 1,000 concurrent user hits create 1,000 heavy system-level processes, quickly exhausting host RAM. | **Highly Optimized.** 1,000 concurrent user hits run as 1,000 parallel stack execution threads within a single shared JVM memory area. |
| **Performance Efficiency** | Slower response times due to heavy OS process-creation overhead for every click. | Rapid execution response times because thread allocation takes minimal CPU cycles. |
| **Resource Persistence** | Cannot natively maintain persistent server resources (like open database connection pools) across separate requests. | Can easily manage persistent background states and reuse database connections across requests. |



---

## 2. Container-Managed Servlet Lifecycle & Processing Flows
- **Syllabus Sub-topics:** Servlet Life Cycle – Form GET and POST actions
- **Weight:** Mandatory Topic | **Exam Evaluation Key:** Architectural Lifecycle Explanation (8 Marks) + Neat Architecture Flow Diagram (5 Marks) [Set A / Set B / Apr-May 2024]

### 🏗️ Lifecycle Execution Sequence
A servlet does not have a standard Java `main()` method. Instead, its instantiation, execution tracking, and termination are completely managed by a **Web Container** (e.g., Apache Tomcat) using three core lifecycle hooks:

```text
       [ Browser Client Request ]
                   │
                   ▼
    1. [ Loading & Instantiation ] (Container creates servlet instance)
                   │
                   ▼
    2. [ init(ServletConfig) ]     (Runs ONCE. Allocates database connections)
                   │
                   ▼
 ┌───► 3. [ service() Thread ]     (Runs on EVERY request. Spawns worker thread)
 │                 │
 │                 ▼
 │      [ Inside service(): ]
 │      ├── If HTTP GET  ──► doGet() Processing Loop
 │      └── If HTTP POST ──► doPost() Processing Loop
 │                 │
 └─────────────────┘
                   │  (When server shuts down / cleans memory)
                   ▼
    3. [ destroy() Method ]        (Runs ONCE. Closes connections safely)
                   │
                   ▼
       [ Garbage Collection ]
```

1. **The Initialization Phase (`init()`):**
   - **Signature:** `public void init(ServletConfig config) throws ServletException`
   - **Mechanics:** Invoked exactly **once** by the container when it loads the servlet class into memory. It handles single-run background operations, such as setting up open network channels or reading server config files before any user requests arrive.
2. **The Active Service Phase (`service()`):**
   - **Signature:** `public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException`
   - **Mechanics:** Executed on **every incoming client request**. The container assigns a separate worker thread from its managed pool and calls `service()`. This method reads the client's HTTP request type and automatically routes it to specialized internal methods like `doGet()` or `doPost()`.
3. **The Destruction Phase (`destroy()`):**
   - **Signature:** `public void destroy()`
   - **Mechanics:** Executed exactly **once** immediately before the web container unloads the servlet instance from memory. It allows developers to gracefully close resources, flush data logs to disk, and prevent server memory leaks.

---

### 🧪 Complete Form Action Execution Processing (`doGet` vs. `doPost`)
The HTTP protocol routes data from web views using distinct structural actions. This explicit, exam-ready script shows how a single servlet captures and handles inputs arriving from **GET** and **POST** form structures completely independently:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormActionProcessorServlet extends HttpServlet {
    
    // 1. Process HTTP GET Form Actions (Parameters appended to the visible URL string)
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Extract plain-text search parameter from the URL query string
        String clientSearchQuery = request.getParameter("searchQueryField");
        
        out.println("<html><body>");
        out.println("<h2>HTTP GET Protocol Execution Engine</h2>");
        out.println("<p>Retrieved Query Parameter from URL: <b>" + clientSearchQuery + "</b></p>");
        out.println("</body></html>");
    }

    // 2. Process HTTP POST Form Actions (Payload hidden safely inside the HTTP Request Body)
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Extract secure data from the invisible transmission request body
        String clientPasswordInput = request.getParameter("passwordField");
        
        out.println("<html><body>");
        out.println("<h2>HTTP POST Protocol Execution Engine</h2>");
        out.println("<p>Captured Secure Body Parameter: <b>" + clientPasswordInput + "</b></p>");
        out.println("</body></html>");
    }
}
```

---

## 3. Session Handling & Persistence Frameworks
- **Syllabus Sub-topics:** Session Handling – Understanding Cookies
- **Weight:** Core Part B Essay Target | **Exam Evaluation Key:** Analytical State Concept (5 Marks) + Functional Code Blueprints (8 Marks) [Set A / Set B]

### 🔍 Core Concepts of Session Tracking
By default, the HTTP network communication layer is completely **stateless**. The backend web server views every incoming request as an entirely anonymous new transaction. Session tracking frameworks solve this by linking separate sequential requests back to a single active client user profile using two main approaches:

* **Client-Side Cookies:** The backend server injects a tiny key-value string header (`Cookie`) inside the client response. The browser saves this text file locally and automatically attaches it to the headers of **every subsequent request** to that domain, telling the server who the user is.
* **Server-Side Session Objects (`HttpSession`):** The application container stores a secure data map directly within its own JVM memory space. Instead of exposing actual profile values, only an anonymous, temporary session ID token string is passed back and forth to track the user.

---

### 💻 Client-Side Cookie Session Tracking Blueprint
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieSessionTrackingServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Extract input parameter value from form submission
        String userIdentity = request.getParameter("usernameField");

        // Instantiate a new text cookie object mapping key to value
        Cookie identityCookie = new Cookie("savedUserToken", userIdentity);
        
        // Enforce an automatic expiration timeframe of 60 seconds
        identityCookie.setMaxAge(60);
        
        // Pack the cookie header directly into the browser transmission response
        response.addCookie(identityCookie);

        out.println("<html><body>");
        out.println("<h3>Cookie Successfully Placed in Response Headers</h3>");
        out.println("<p>Identity value saved: " + userIdentity + "</p>");
        out.println("<a href='CookieSessionTrackingServlet'>Click to traverse and verify state</a>");
        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the complete array of active cookies broadcasted by the browser
        Cookie[] structuralCookies = request.getCookies();
        String activeUserToken = "Anonymous Guest";

        if (structuralCookies != null) {
            for (Cookie currentCookie : structuralCookies) {
                if (currentCookie.getName().equals("savedUserToken")) {
                    activeUserToken = currentCookie.getValue(); // Read persistent data
                    break;
                }
            }
        }

        out.println("<html><body>");
        out.println("<h3>Session Verification Status Dashboard</h3>");
        out.println("<p>Active Cookie User Identity: <b>" + activeUserToken + "</b></p>");
        out.println("</body></html>");
    }
}
```

---

### 💻 Server-Side `HttpSession` Tracking Blueprint
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdvancedSessionServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String inputData = request.getParameter("dataField");
        
        // Retrieve the current active server session, or instantiate a clean new one if none exists
        HttpSession activeSession = request.getSession(true);
        
        // Bind the data value to a named attribute key inside secure JVM container memory
        activeSession.setAttribute("storedDataKey", inputData);
        
        out.println("<html><body>");
        out.println("<h3>Server-Side Session State Initialized</h3>");
        out.println("<p>Session Token ID generated by Container: <b>" + activeSession.getId() + "</b></p>");
        out.println("<a href='AdvancedSessionServlet'>Read session attributes maps</a>");
        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Fetch the active session object without creating a new one if it expired
        HttpSession activeSession = request.getSession(false);
        String sessionPayload = "No Session Session Target Active.";
        
        if (activeSession != null) {
            // Extract and type-cast the memory object reference back to a String string
            sessionPayload = (String) activeSession.getAttribute("storedDataKey");
        }
        
        out.println("<html><body>");
        out.println("<h3>JVM Core Session State Readout</h3>");
        out.println("<p>Retrieved Secured Payload: <b>" + sessionPayload + "</b></p>");
        out.println("</body></html>");
    }
}
```

---

## 4. Database Connectivity: The 5-Step JDBC Pipeline
- **Syllabus Sub-topics:** DATABASE CONNECTIVITY: JDBC
- **Weight:** Part C 15-Mark Application Case Study Target | **Exam Evaluation Key:** Complete Form View Entry Integration + Safe PreparedStatement Query Execution Mechanics (15 Marks) [Set A / Set B / Apr-May 2024]

### 🏗️ JDBC Architecture Bridge Definition
The **Java Database Connectivity (JDBC)** API acts as an abstraction layer that sits between your Java code and a database engine. It converts standard Java program calls into SQL commands that any relational database (like MySQL) can interpret and execute.



### 💻 E-Commerce Customer Database Registration Engine
This combined code block provides a complete solution for the highly-weighted **Part C Case Study** question. It reads real-time form parameters, initializes a JDBC pipeline using an explicit step-by-step approach, uses parameterized placeholder tokens (`?`) to block SQL injection attacks, and outputs an immediate confirmation screen:

```java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Core Backend Controller executing E-Commerce database transactions
public class CustomerRegistrationController extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Extract string values passed from the front-end user registration form fields
        String customerUser = request.getParameter("regUser");
        String customerEmail = request.getParameter("regEmail");
        String customerPass = request.getParameter("regPass");
        
        // Initialize interface variables outside blocks to maintain baseline visibility during cleanup
        Connection dbPipeline = null;
        PreparedStatement sqlExecutionWorker = null;
        
        try {
            // Step 1: Load the database vendor's driver class explicitly into running memory
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Establish a live connection pipeline using target connection URL specs
            dbPipeline = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ecommerce_shop_db", "root", "SrmTrpCse2026"
            );
            
            // Step 3: Compile a secure parameterized SQL mutation query string with ? placeholders
            String insertionTemplate = "INSERT INTO customers (username, email, password) VALUES (?, ?, ?)";
            sqlExecutionWorker = dbPipeline.prepareStatement(insertionTemplate);
            
            // Step 4: Safely bind values to placeholders to eliminate SQL injection security holes
            sqlExecutionWorker.setString(1, customerUser);
            sqlExecutionWorker.setString(2, customerEmail);
            sqlExecutionWorker.setString(3, customerPass);
            
            // Step 5: Execute the update command inside the remote relational database grid tables
            int structuralRowsAffected = sqlExecutionWorker.executeUpdate();
            
            // Step 6: Generate dynamic response HTML tracking back down to the browser window view
            out.println("<html><body>");
            if (structuralRowsAffected > 0) {
                out.println("<h2 style='color:green;'>Account Created Successfully!</h2>");
                out.println("<p>Welcome " + customerUser + ", your data has been written to the persistent database.</p>");
            } else {
                out.println("<h2 style='color:red;'>Transaction Registration Aborted</h2>");
                out.println("<p>Internal database rejection error. Try different fields.</p>");
            }
            out.println("</body></html>");
            
        } catch (Exception runtimeError) {
            // Intercept errors and print diagnostic traces to the screen layout during engineering tests
            out.println("<html><body>");
            out.println("<h2 style='color:red;'>Server Architecture Exception Intercepted:</h2>");
            out.println("<p>" + runtimeError.getMessage() + "</p>");
            out.println("</body></html>");
        } finally {
            // Step 7: Close open database references immediately to avoid server connection pooling memory leaks
            try {
                if (sqlExecutionWorker != null) sqlExecutionWorker.close();
                if (dbPipeline != null) dbPipeline.close();
            } catch (SQLException sqlCloseException) {
                sqlCloseException.printStackTrace();
            }
        }
    }
}
```