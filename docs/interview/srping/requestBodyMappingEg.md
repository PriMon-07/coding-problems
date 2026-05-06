# `@RequestBody` Mapping and Custom Validation

## How `@RequestBody` Works

`@RequestBody` automatically maps incoming JSON/XML to Java objects using **Jackson** (default) or other message converters.

### Mapping Process
1. **HTTP Request** → JSON payload in request body
2. **Message Converter** → Jackson reads JSON
3. **Object Mapping** → JSON → Java object (POJO)
4. **Validation** → Bean validation applied
5. **Controller Method** → Validated object passed as parameter

## Custom Mapping with Validation

### 1. **Create DTO with Validation Annotations**

```java
public class UserDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age cannot exceed 120")
    private Integer age;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
```

### 2. **Controller with Validation**

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        // @Valid triggers validation
        // If validation fails, MethodArgumentNotValidException is thrown
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }
}
```

### 3. **Global Exception Handler**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        return ResponseEntity.badRequest().body(errors);
    }
}
```

## Custom Validation Annotations

### 1. **Create Custom Annotation**

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### 2. **Implement Validator**

```java
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        return !userRepository.existsByEmail(email);
    }
}
```

### 3. **Use Custom Validation**

```java
public class UserDTO {
    @UniqueEmail
    @Email
    @NotBlank
    private String email;
    
    // other fields...
}
```

## Advanced Mapping Configuration

### **Custom Jackson Configuration**

```java
@Configuration
public class JacksonConfig {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return mapper;
    }
}
```

### **Custom Property Naming**

```java
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {
    private String firstName;  // Maps to "first_name" in JSON
    private String lastName;   // Maps to "last_name" in JSON
}
```

## Request/Response Flow

```
Client Request (JSON)
        ↓
@RequestBody + @Valid
        ↓
Jackson Deserialization
        ↓
Bean Validation
        ↓
Controller Method
        ↓
Business Logic
        ↓
Response Entity
```

This gives you complete control over request mapping and validation with clear error handling.