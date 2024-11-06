

1. **Highest Paying Department:**
   ```sql
   SELECT Department, SUM(Salary) AS TotalSalary
   FROM Employee
   GROUP BY Department
   ORDER BY TotalSalary DESC
   LIMIT 1;
   ```
2. **Nth Highest Salary:**
   ```sql
   SELECT Salary
   FROM Employee
   ORDER BY Salary DESC
   LIMIT N, 1;
   ```
3. **Indexing:**
   Indexing creates a data structure that stores the values of a specific column, allowing for faster searches and sorting.
4. **Partitioning:**
   Partitioning divides a large table into smaller, more manageable partitions. This can improve query performance, especially for large datasets and analytical queries.

5. **GET and POST APIs:**
   ```java
   @RestController
   public class MyController {
       @GetMapping("/users")
       public List<User> getUsers() {
           // ...
       }

       @PostMapping("/users")
       public User createUser(@RequestBody User user) {
           // ...
       }
   }
   ```
6. **@RequestBody:**
   Maps the HTTP request body to a specific object in the controller method.
7. **Input Validation:**
   Use validation annotations like `@NotNull`, `@NotEmpty`, `@Size`, etc., or custom validation logic.
8. **Exception Handling:**
   Use `@ExceptionHandler` to handle specific exceptions and return appropriate error responses.
9. **Returning Database Exceptions:**
   Wrap database exceptions in custom exceptions and return them as REST API responses.
10. **API Security:**
    Use Spring Security to implement authentication and authorization mechanisms like basic authentication, OAuth2, or JWT.
11. **TreeSet Ordering:**
    TreeSet sorts elements in natural order. So, the output would be: Run, success, Test.

**Data Structures and Algorithms**

12. **Largest Element in a Matrix:**
    Iterate through the matrix and keep track of the maximum element.
13. **Kafka Configuration:**
    Configure producer and consumer properties in `application.properties` and use `KafkaTemplate` and `KafkaConsumer` to send and receive messages.
14. **PUT vs POST:**
    PUT is idempotent, meaning multiple requests with the same data should produce the same result. POST is not idempotent and creates a new resource.
15. **Second Highest Salary:**
   ```sql
   SELECT Salary
   FROM Employee
   ORDER BY Salary DESC
   LIMIT 1, 1;
   ```
16. **REST API Methods:**
- **GET:** Retrieves a resource.
- **POST:** Creates a new resource.
- **PUT:** Updates an existing resource.
- **DELETE:** Deletes a resource.
- **PATCH:** Partially updates a resource.
17. **@RequestBody vs @RequestParam:**
- **@RequestBody:** Maps the entire request body to an object.
- **@RequestParam:** Maps a specific request parameter to a method parameter.
18. **API Security:**
    Implement authentication and authorization mechanisms to protect API endpoints.
19. **Cron Job Configuration:**
    Use Spring's `@Scheduled` annotation or a scheduling library like Quartz.
20. **TreeSet**
21. **Filtering Empty Strings:**
   ```java
   Arrays.stream(st).filter(s -> !s.isEmpty()).collect(Collectors.toList());
   ```
22. **Finding Duplicate Elements:**
   ```java
   Arrays.stream(str).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                     .entrySet().stream()
                     .filter(e -> e.getValue() > 1)
                     .map(Map.Entry::getKey)
                     .collect(Collectors.toList());
   ```
23. **Two Sum Problem:**
    Use a HashMap to store the complement of each number and its index.
24. **Hibernate Annotations:**
    `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@ManyToOne`, `@OneToMany`, `@ManyToMany`.
25. **Spring Batch:**
    Understand batch job execution, step configuration, reader/processor/writer components, and job repository.
26. **Changing Embedded Tomcat:**
    Configure a different embedded server or externalize the server configuration.
27. **Session Management and Token-Based Authentication:**
    Use session management techniques like session timeouts and IP restrictions. Token-based authentication can be vulnerable to token theft.
    **28. Types of Authentication and Their Usage**

Here are some common authentication methods and their usage:

* **Basic Authentication:**
    - Simple mechanism where the user sends their username and password in plain text.
    - Suitable for low-security applications or internal systems.
* **Digest Authentication:**
    - More secure than Basic Authentication as it hashes the password before transmission.
    - Often used in HTTP protocols.
* **OAuth 2.0:**
    - Industry-standard protocol for authorization, allowing users to grant third-party applications access to their data.
    - Widely used in social media logins and API integrations.
* **OpenID Connect (OIDC):**
    - Built on top of OAuth 2.0, providing authentication and authorization services.
    - Used for single sign-on (SSO) and user information exchange.
* **JSON Web Token (JWT):**
    - A standard for creating access tokens that contain claims about a user.
    - Often used for stateless authentication, where the server doesn't need to store session information.

**29. Designing a Database from Scratch Using Entity Classes**

To design a database from scratch using entity classes, follow these steps:

1. **Identify Entities:** Determine the core objects in your application domain. For example, in an e-commerce application, entities might include `User`, `Product`, and `Order`.
2. **Define Attributes:** Specify the properties of each entity. For example, a `User` entity might have attributes like `id`, `name`, `email`, and `password`.
3. **Establish Relationships:** Define how entities relate to each other. Common relationships include one-to-one, one-to-many, and many-to-many.
4. **Create Entity Classes:** Model each entity as a Java class with corresponding fields and methods.
5. **Map to Database Tables:** Use an ORM tool like Hibernate or JPA to map entity classes to database tables.

**30. Java 8 Features**

Java 8 introduced many significant features, including:

* **Lambda Expressions:** Concise way to represent functional interfaces.
* **Method References:** Referencing existing methods as functional interfaces.
* **Streams API:** Functional-style operations on collections.
* **Default and Static Methods in Interfaces:** Adding new functionality to existing interfaces.
* **Date and Time API:** Improved API for working with dates and times.
* **Optional Class:** Handling null values in a more robust way.

**31. SQL Query to Fetch Users with Departments**

```sql
SELECT u.*
FROM User u
INNER JOIN Department d ON u.department_id = d.id
WHERE EXISTS (
    SELECT 1
    FROM User u2
    WHERE u2.department_id = d.id
);
```

**32. Polymorphism and Annotations**

* **Polymorphism:** The ability of objects to take on many forms. It allows objects of different types to be treated as if they were of the same type.
* **Annotations:** Metadata added to source code to provide additional information to the compiler or runtime environment. Common types include:
    - **`@Override`:** Indicates that a method overrides a superclass method.
    - **`@Deprecated`:** Marks a class, method, or field as deprecated.
    - **`@SuppressWarnings`:** Suppresses specific compiler warnings.
    - **`@Transactional`:** Specifies transaction boundaries in Spring applications.
    - **`@Autowired`:** Injects dependencies automatically in Spring.

**33. Sorting a List of Usernames by Length Using Stream API**

```java
List<String> usernames = Arrays.asList("Alice", "Bob", "Charlie", "David");
List<String> sortedUsernames = usernames.stream()
                                          .sorted(Comparator.comparingInt(String::length))
                                          .collect(Collectors.toList());
```

**34. Synchronous and Asynchronous Communication**

* **Synchronous Communication:** Both the sender and receiver are active at the same time. The sender waits for a response from the receiver before proceeding.
* **Asynchronous Communication:** The sender and receiver don't need to be active at the same time. The sender sends a message and continues with other tasks, while the receiver processes the message later.
