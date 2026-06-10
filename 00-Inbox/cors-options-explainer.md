# CORS & OPTIONS — Full Explanation
#spring-boot #security #cors #frontend

---

## What is CORS

CORS = Cross-Origin Resource Sharing.

Your browser has a security rule:
> A webpage can only talk to the same server it came from.

**Origin** = protocol + domain + port.

```
http://localhost:5173   ← frontend (Vite)
http://localhost:8080   ← backend (Spring Boot)
```

Different ports = different origins = browser blocks the request.

This is **browser-only**. Bruno, Postman, curl — they are not browsers so they ignore CORS entirely. That's why everything works in Bruno but fails when React calls the API.

CORS is your backend saying:
> "I give this origin permission to talk to me."

---

## What is OPTIONS (Preflight)

Before sending certain cross-origin requests, the browser automatically sends a **preflight request** using the `OPTIONS` HTTP method.

The browser is asking:
> "Before I send this POST with an Authorization header from localhost:5173, will you accept it?"

The server responds with headers:
```
Access-Control-Allow-Origin: http://localhost:5173
Access-Control-Allow-Methods: GET, POST, PUT, PATCH, DELETE
Access-Control-Allow-Headers: Authorization, Content-Type
```

If server says yes → browser sends the real request.
If server returns 403 or missing headers → browser blocks the real request and shows CORS error.

### When does the browser send a preflight?

Not always. Only when:
- Method is POST/PUT/PATCH/DELETE with custom headers
- Request has `Authorization` header (your JWT)
- Content-Type is `application/json`

Simple GET requests to public endpoints usually skip it.

### You never send OPTIONS yourself

You don't write `fetch("...", { method: "OPTIONS" })` in your code. The browser sends it automatically and invisibly. You only see it in the Network tab of DevTools.

---

## What OPTIONS is NOT

You might confuse it with REST API discovery — some APIs use OPTIONS to list available methods on an endpoint. That's a different use of the same HTTP method. Here it's purely browser CORS security, nothing to do with API documentation.

---

## How CORS is set up in Spring Boot

Two things required — both must exist.

### 1. CorsConfig.java — defines what's allowed

```java
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // which frontends can talk to this backend
        config.setAllowedOrigins(List.of(
            "http://localhost:5173",
            "https://your-app.vercel.app"
        ));

        // which HTTP methods are allowed
        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        // allow all headers including Authorization for JWT
        config.setAllowedHeaders(List.of("*"));

        // needed for Authorization header to work cross-origin
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
```

### 2. SecurityConfig.java — must activate CORS

```java
http.cors(Customizer.withDefaults())
```

Without this line, Spring Security ignores your CorsConfig bean entirely and blocks all cross-origin requests anyway. The bean exists but is never used.

---

## Problems hit and how they were fixed

### Problem 1 — OPTIONS blocked by Spring Security

**Error:**
```
CORS header 'Access-Control-Allow-Origin' missing. Status code: 403
```

**Cause:**
OPTIONS preflight has no JWT token (browser sends it before the real request). Security config required authentication for all requests, so OPTIONS got rejected before CORS headers were added.

**Fix:**
Explicitly permit OPTIONS in the security config:
```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ← this
    .anyRequest().authenticated()
)
```

Also add `shouldNotFilter` to JwtFilter so it skips OPTIONS entirely:
```java
@Override
protected boolean shouldNotFilter(HttpServletRequest request) {
    return request.getMethod().equals("OPTIONS");
}
```

---

### Problem 2 — Exception handlers writing response without CORS headers

**Cause:**
`JwtAuthenticationEntryPoint` and `JwtAccessDeniedHandler` write directly to the response when a 401/403 occurs. When they run, CORS headers haven't been added yet. Browser sees a 403 with no `Access-Control-Allow-Origin` and blocks everything.

**Fix:**
Add CORS header manually inside both handlers:
```java
response.setHeader("Access-Control-Allow-Origin",
    request.getHeader("Origin"));
response.setHeader("Access-Control-Allow-Credentials", "true");
```

`request.getHeader("Origin")` echoes back whatever origin sent the request — works for both local dev and production without hardcoding.

---

### Problem 3 — Testing CORS from wrong origin

**Symptom:**
curl test worked perfectly. Browser test still showed CORS error.

**Cause:**
curl test was run with `-H "Origin: http://localhost:5173"` manually. Browser test was run from a page that was NOT `localhost:5173` (e.g. a blank tab or google.com). Browser sends the actual origin of the page you're on, not localhost:5173.

**Proof — curl showed:**
```
HTTP/1.1 200
Access-Control-Allow-Origin: http://localhost:5173
Access-Control-Allow-Credentials: true
```

Backend was working correctly the entire time.

**Fix:**
Test from the actual frontend page at `localhost:5173`. Start the Vite dev server, open `http://localhost:5173` in the browser, run the fetch test from that page's console.

---

## How to verify CORS is working

### Method 1 — curl (most reliable)
```bash
curl -v -X OPTIONS http://localhost:8080/api/auth/login \
  -H "Origin: http://localhost:5173" \
  -H "Access-Control-Request-Method: POST"
```

Good response looks like:
```
HTTP/1.1 200
Access-Control-Allow-Origin: http://localhost:5173
Access-Control-Allow-Methods: GET,POST,PUT,PATCH,DELETE,OPTIONS
Access-Control-Allow-Credentials: true
```

### Method 2 — Browser console (from localhost:5173 only)
```javascript
fetch("http://localhost:8080/api/auth/login", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ email: "test@test.com", password: "test" })
})
.then(r => console.log("Status:", r.status))
.catch(e => console.error("CORS blocked:", e))
```

- `Status: 200` or `Status: 400` → CORS working
- `CORS blocked` → still a problem

### Method 3 — Bruno response headers
Hit any endpoint, check Headers tab in response. Look for:
```
Access-Control-Allow-Origin: http://localhost:5173
```

---

## Key rules to remember

- CORS is browser-only — Bruno/Postman/curl never enforce it
- OPTIONS is sent automatically by browser, never manually
- `cors(Customizer.withDefaults())` in SecurityConfig is mandatory — without it CorsConfig bean is ignored
- Exception handlers (401/403) must manually add CORS headers or browser sees the error without origin header
- Always test CORS from the correct origin — the page must be served from localhost:5173 for the browser to send that origin
- `allowCredentials(true)` is needed for Authorization header to work cross-origin
- Production — add your Vercel URL to `setAllowedOrigins` list before deploying
