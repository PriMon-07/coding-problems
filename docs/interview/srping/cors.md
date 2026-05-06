# CORS (Cross-Origin Resource Sharing)

## What is CORS?

**CORS** is a security mechanism that allows web browsers to make requests to a different domain than the one serving the web page.

### **The Problem: Same-Origin Policy**

Browsers implement the **Same-Origin Policy** for security:
```javascript
// This BLOCKED by default if served from different domain
fetch('https://api.example.com/users') // From https://frontend.com
```

**Same-Origin** = Same **protocol** + **host** + **port**

---

## How CORS Works

### **1. Simple Requests**
```javascript
// Simple GET, POST, HEAD requests
fetch('https://api.example.com/users', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
```

**Browser adds:**
```
Origin: https://frontend.com
```

**Server responds with:**
```
Access-Control-Allow-Origin: https://frontend.com
```

### **2. Preflight Requests** (Complex requests)

```javascript
// PUT, DELETE, or custom headers trigger preflight
fetch('https://api.example.com/users', {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer token'
    }
})
```

**Browser sends OPTIONS request first:**
```
OPTIONS /users HTTP/1.1
Origin: https://frontend.com
Access-Control-Request-Method: PUT
Access-Control-Request-Headers: Authorization
```

**Server responds:**
```
Access-Control-Allow-Origin: https://frontend.com
Access-Control-Allow-Methods: GET, POST, PUT, DELETE
Access-Control-Allow-Headers: Authorization
Access-Control-Max-Age: 86400
```

---

## CORS in Spring Boot

### **1. Global CORS Configuration**

```java
@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow specific origins
        configuration.setAllowedOrigins(Arrays.asList(
            "https://frontend.com",
            "https://admin.example.com"
        ));
        
        // Allow HTTP methods
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));
        
        // Allow headers
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", "Content-Type", "X-Requested-With"
        ));
        
        // Allow credentials
        configuration.setAllowCredentials(true);
        
        // Expose headers
        configuration.setExposedHeaders(Arrays.asList("X-Total-Count"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

### **2. Controller-Level CORS**

```java
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://frontend.com", 
             methods = {RequestMethod.GET, RequestMethod.POST},
             allowedHeaders = "Authorization",
             allowCredentials = "true")
public class UserController {
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @PostMapping
    @CrossOrigin(origins = "https://admin.example.com")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                           .body(userService.saveUser(user));
    }
}
```

### **3. Method-Level CORS**

```java
@GetMapping("/public")
@CrossOrigin(origins = "*") // Allow all origins (be careful!)
public ResponseEntity<List<User>> getPublicUsers() {
    return ResponseEntity.ok(userService.getPublicUsers());
}
```

---

## Common CORS Scenarios

### **1. Development Environment**
```java
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
```

### **2. Production Environment**
```java
@CrossOrigin(origins = {"https://yourapp.com", "https://admin.yourapp.com"})
```

### **3. Multiple Environments**
```java
@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            @Value("${cors.allowed-origins}") String[] allowedOrigins) {
        
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        // ... other configuration
    }
}
```

**application.properties:**
```properties
cors.allowed-origins=https://prod.com,https://staging.com
```

---

## CORS Headers Explained

| Header | Purpose | Example |
|--------|---------|---------|
| `Access-Control-Allow-Origin` | Which origins can access | `https://frontend.com` |
| `Access-Control-Allow-Methods` | Allowed HTTP methods | `GET, POST, PUT, DELETE` |
| `Access-Control-Allow-Headers` | Allowed request headers | `Authorization, Content-Type` |
| `Access-Control-Allow-Credentials` | Allow cookies/auth | `true` |
| `Access-Control-Max-Age` | Cache preflight response | `86400` (24 hours) |
| `Access-Control-Expose-Headers` | Headers accessible to client | `X-Total-Count` |

---

## Common CORS Errors

### **1. No 'Access-Control-Allow-Origin' header**
```
Access to fetch at 'https://api.example.com/users' 
from origin 'https://frontend.com' has been blocked by CORS policy: 
No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

### **2. Credentials not allowed**
```
The value of the 'Access-Control-Allow-Credentials' header in the response 
is '' which must be 'true' when the request's credentials mode is 'include'.
```

### **3. Method not allowed**
```
Request header field Access-Control-Request-Method is not allowed 
by Access-Control-Allow-Headers in preflight response.
```

---

## Best Practices

1. **Be specific** with allowed origins (avoid `*` in production)
2. **Limit methods** to only what you need
3. **Use environment variables** for different environments
4. **Monitor CORS logs** for security issues
5. **Test preflight requests** thoroughly

CORS is essential for modern web applications but must be configured carefully to maintain security while enabling cross-origin functionality.