# RestController: Handling All Types of Request Parameters

## Complete RestController with All Parameter Types

```java
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    // ==================== PATH VARIABLES ====================
    
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getPathVariable(@PathVariable Long id) {
        return ResponseEntity.ok("User ID: " + id);
    }
    
    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<String> getMultiplePathVariables(
            @PathVariable Long userId, 
            @PathVariable Long postId) {
        return ResponseEntity.ok(String.format("User: %d, Post: %d", userId, postId));
    }
    
    // ==================== QUERY PARAMETERS ====================
    
    @GetMapping("/search")
    public ResponseEntity<String> getQueryParams(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) List<String> tags) {
        return ResponseEntity.ok(String.format(
            "Name: %s, Page: %d, Size: %d, Tags: %s", 
            name, page, size, tags
        ));
    }
    
    @GetMapping("/filter")
    public ResponseEntity<String> getQueryParamWithMap(
            @RequestParam Map<String, String> allParams) {
        return ResponseEntity.ok("All params: " + allParams.toString());
    }
    
    // ==================== REQUEST HEADERS ====================
    
    @GetMapping("/headers")
    public ResponseEntity<String> getHeaders(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader("Authorization") String auth,
            @RequestHeader(value = "X-Custom-Header", required = false) String customHeader,
            @RequestHeader Map<String, String> allHeaders) {
        
        return ResponseEntity.ok(String.format(
            "User-Agent: %s, Auth: %s, Custom: %s, All Headers: %s",
            userAgent, auth, customHeader, allHeaders.size()
        ));
    }
    
    // ==================== REQUEST BODY ====================
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PostMapping("/users/batch")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }
    
    @PostMapping("/data")
    public ResponseEntity<String> createWithMap(@RequestBody Map<String, Object> data) {
        return ResponseEntity.ok("Received: " + data.toString());
    }
    
    // ==================== FORM DATA ====================
    
    @PostMapping("/form")
    public ResponseEntity<String> handleForm(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam MultipartFile file) {
        
        return ResponseEntity.ok(String.format(
            "Username: %s, Email: %s, File: %s (%d bytes)",
            username, email, file.getOriginalFilename(), file.getSize()
        ));
    }
    
    @PostMapping("/form/multipart")
    public ResponseEntity<String> handleMultiPart(
            @RequestParam Map<String, String> formData,
            @RequestParam(required = false) MultipartFile file) {
        
        return ResponseEntity.ok(String.format(
            "Form data: %s, File: %s",
            formData.toString(),
            file != null ? file.getOriginalFilename() : "No file"
        ));
    }
    
    // ==================== COOKIES ====================
    
    @GetMapping("/cookies")
    public ResponseEntity<String> getCookies(
            @CookieValue(value = "session_id", required = false) String sessionId,
            @CookieValue(value = "user_pref", defaultValue = "default") String userPref) {
        
        return ResponseEntity.ok(String.format(
            "Session: %s, Preference: %s",
            sessionId, userPref
        ));
    }
    
    @PostMapping("/cookies")
    public ResponseEntity<String> setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("session_id", "abc123");
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1 hour
        response.addCookie(cookie);
        return ResponseEntity.ok("Cookie set");
    }
    
    // ==================== MIXED PARAMETERS ====================
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestParam(required = false) Boolean validate,
            @RequestHeader("If-Match") String ifMatch,
            @RequestBody User user) {
        
        return ResponseEntity.ok(String.format(
            "Updated user %d (validate: %s, ETag: %s): %s",
            id, validate, ifMatch, user.toString()
        ));
    }
    
    // ==================== ADVANCED EXAMPLES ====================
    
    @GetMapping("/advanced")
    public ResponseEntity<String> advancedExample(
            // Path variable
            @PathVariable String category,
            
            // Query parameters with validation
            @RequestParam @Min(1) int page,
            @RequestParam @Max(100) int size,
            @RequestParam @Pattern(regexp = "asc|desc") String sort,
            
            // Headers
            @RequestHeader("Accept") String accept,
            @RequestHeader(value = "X-API-Version", required = false) String apiVersion,
            
            // Cookies
            @CookieValue(value = "auth_token", required = false) String authToken,
            
            // Request body (for POST/PUT)
            @RequestBody(required = false) SearchRequest searchRequest,
            
            // HttpServletRequest for advanced access
            HttpServletRequest request) {
        
        return ResponseEntity.ok(String.format(
            "Advanced request - Category: %s, Page: %d, Size: %d, Sort: %s, " +
            "Accept: %s, API Version: %s, Auth Token: %s, Search: %s, IP: %s",
            category, page, size, sort, accept, apiVersion, authToken,
            searchRequest != null ? searchRequest.toString() : "none",
            request.getRemoteAddr()
        ));
    }
    
    // ==================== FILE UPLOAD/DOWNLOAD ====================
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description) {
        
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file");
        }
        
        return ResponseEntity.ok(String.format(
            "Uploaded: %s, Size: %d, Description: %s",
            file.getOriginalFilename(), file.getSize(), description
        ));
    }
    
    @PostMapping("/upload/multiple")
    public ResponseEntity<String> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam(value = "tags", required = false) List<String> tags) {
        
        StringBuilder result = new StringBuilder();
        for (MultipartFile file : files) {
            result.append(String.format("File: %s (%d bytes)\n", 
                file.getOriginalFilename(), file.getSize()));
        }
        result.append("Tags: ").append(tags != null ? tags.toString() : "none");
        
        return ResponseEntity.ok(result.toString());
    }
    
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = new FileSystemResource("/path/to/files/" + filename);
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                       "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
    
    // ==================== HTTP SERVLET REQUEST/RESPONSE ====================
    
    @GetMapping("/servlet")
    public ResponseEntity<String> servletExample(
            HttpServletRequest request,
            HttpServletResponse response) {
        
        // Access request details
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String userAgent = request.getHeader("User-Agent");
        String clientIP = request.getRemoteAddr();
        
        // Set response headers
        response.setHeader("X-Custom-Response", "CustomValue");
        
        // Get all parameters
        Map<String, String[]> params = request.getParameterMap();
        
        return ResponseEntity.ok(String.format(
            "Method: %s, URL: %s, User-Agent: %s, IP: %s, Params: %s",
            method, url, userAgent, clientIP, params.keySet()
        ));
    }
    
    // ==================== VALIDATION EXAMPLES ====================
    
    @PostMapping("/validated")
    public ResponseEntity<String> validatedExample(
            @RequestParam @Email String email,
            @RequestParam @Size(min = 3, max = 20) String username,
            @RequestParam @Min(18) @Max(100) int age,
            @Valid @RequestBody UserDTO userDTO) {
        
        return ResponseEntity.ok(String.format(
            "Validated - Email: %s, Username: %s, Age: %d, User: %s",
            email, username, age, userDTO.toString()
        ));
    }
}

// ==================== SUPPORTING CLASSES ====================

class User {
    private Long id;
    private String name;
    private String email;
    
    // Constructors, getters, setters
    public User() {}
    
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', email='%s'}", id, name, email);
    }
}

class SearchRequest {
    private String query;
    private List<String> filters;
    private String sortBy;
    
    @Override
    public String toString() {
        return String.format("SearchRequest{query='%s', filters=%s, sortBy='%s'}", 
                           query, filters, sortBy);
    }
}

class UserDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    
    @Email(message = "Invalid email")
    @NotBlank
    private String email;
    
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;
    
    @Override
    public String toString() {
        return String.format("UserDTO{name='%s', email='%s', age=%d}", name, email, age);
    }
}
```

## Parameter Types Summary

| Parameter Type | Annotation | Example Usage | Common Use Cases |
|----------------|------------|---------------|------------------|
| **Path Variable** | `@PathVariable` | `/users/{id}` | Resource identification |
| **Query Param** | `@RequestParam` | `/search?q=java` | Filtering, pagination |
| **Header** | `@RequestHeader` | `Authorization: Bearer token` | Auth, content negotiation |
| **Cookie** | `@CookieValue` | `session_id=abc123` | Session management |
| **Request Body** | `@RequestBody` | POST/PUT JSON data | Complex data submission |
| **Form Data** | `@RequestParam` + `MultipartFile` | File uploads | Form submissions |
| **Map Params** | `@RequestParam Map<String, String>` | Dynamic parameters | Flexibility |
| **Validation** | `@Valid`, `@Email`, `@Size` | Input validation | Data integrity |

## Best Practices

1. **Use specific parameter types** instead of generic Maps when possible
2. **Add validation** annotations for input validation
3. **Use `required = false`** for optional parameters
4. **Provide default values** with `defaultValue` attribute
5. **Handle file uploads** separately with `MultipartFile`
6. **Use `@Valid`** with `@RequestBody` for complex object validation
7. **Consider security** when handling headers and cookies
8. **Use proper HTTP methods** for different operations

This comprehensive example covers all major ways to handle request parameters in Spring Boot RestControllers!
