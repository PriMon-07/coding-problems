# How to connect Oracle database with Spring Boot

**Spring Boot makes connecting to Oracle database straightforward through auto-configuration and proper dependencies.**

### **1. Add Oracle Dependencies**

```xml
<!-- pom.xml -->
<dependencies>
    <!-- Oracle JDBC Driver -->
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>21.7.0.0</version>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring JDBC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
</dependencies>
```

**Or Gradle:**
```gradle
dependencies {
    implementation 'com.oracle.database.jdbc:ojdbc8:21.7.0.0'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-jdbc'
}
```

### **2. Configure Database Connection**

#### **application.properties:**
```properties
# Oracle Database Connection
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Connection Pool (HikariCP)
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```

#### **application.yml:**
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521:XE
    username: your_username
    password: your_password
    driver-class-name: oracle.jdbc.OracleDriver
    hikari:
      connection-timeout: 30000
      maximum-pool-size: 10
      minimum-idle: 5
  
  jpa:
    database-platform: org.hibernate.dialect.OracleDialect
    hibernate:
      ddl-auto: update
      show-sql: true
      properties:
        hibernate:
          format_sql: true
```

### **3. Create Entity Class**

```java
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "EMAIL", unique = true, length = 100)
    private String email;
    
    @Column(name = "HIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    
    @Column(name = "SALARY")
    private BigDecimal salary;
    
    // Constructors, getters, setters
    public Employee() {}
    
    public Employee(String firstName, String lastName, String email, Date hireDate, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
    }
    
    // Getters and setters...
}
```

### **4. Create Repository**

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Custom query methods
    List<Employee> findByLastName(String lastName);
    
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    
    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary ORDER BY e.salary DESC")
    List<Employee> findBySalaryGreaterThan(@Param("minSalary") BigDecimal minSalary);
    
    @Query(value = "SELECT * FROM EMPLOYEES WHERE HIRE_DATE BETWEEN :startDate AND :endDate", 
           nativeQuery = true)
    List<Employee> findEmployeesHiredBetweenDates(@Param("startDate") Date startDate, 
                                                   @Param("endDate") Date endDate);
}
```

### **5. Create Service Layer**

```java
@Service
@Transactional
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDetails.getFirstName());
                    employee.setLastName(employeeDetails.getLastName());
                    employee.setEmail(employeeDetails.getEmail());
                    employee.setSalary(employeeDetails.getSalary());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
    
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
    
    public List<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }
}
```

### **6. Create Controller**

```java
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
```

### **7. Advanced Configuration**

#### **Multiple DataSources:**
```java
@Configuration
public class DataSourceConfig {
    
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

#### **Custom Oracle Configuration:**
```java
@Configuration
@EnableTransactionManagement
public class OracleConfig {
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        em.setJpaProperties(jpaProperties);
        
        return em;
    }
}
```

### **8. Common Oracle Connection Issues & Solutions**

#### **Connection URL Formats:**
```properties
# Basic format
spring.datasource.url=jdbc:oracle:thin:@hostname:port:SID

# With service name
spring.datasource.url=jdbc:oracle:thin:@hostname:port/service_name

# With TNS names
spring.datasource.url=jdbc:oracle:thin:@TNS_NAME

# With SSL
spring.datasource.url=jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcps)(HOST=hostname)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=service_name)(SECURITY=(SSL_SERVER_CERT_DN=...)))
```

#### **Common Problems:**
```properties
# Solution: Add Oracle Wallet configuration
oracle.net.tns_admin=/path/to/wallet
oracle.net.wallet_location=/path/to/wallet

# Solution: Set timezone
spring.jpa.properties.hibernate.jdbc.time_zone=UTC

# Solution: Increase timeout
spring.datasource.hikari.connection-timeout=60000
spring.datasource.hikari.connection-test-query=SELECT 1 FROM DUAL
```

### **9. Testing the Connection**

```java
@SpringBootTest
@Test
class OracleConnectionTest {
    
    @Autowired
    private DataSource dataSource;
    
    @Test
    void testDatabaseConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Assertions.assertNotNull(connection);
            
            // Test Oracle-specific query
            try (PreparedStatement stmt = connection.prepareStatement("SELECT 1 FROM DUAL");
                 ResultSet rs = stmt.executeQuery()) {
                Assertions.assertTrue(rs.next());
                Assertions.assertEquals(1, rs.getInt(1));
            }
        }
    }
    
    @Test
    void testEmployeeRepository() {
        // Test JPA operations
        Employee employee = new Employee("John", "Doe", "john@example.com", new Date(), new BigDecimal("50000"));
        
        Employee saved = employeeRepository.save(employee);
        Assertions.assertNotNull(saved.getEmployeeId());
        Assertions.assertEquals("John", saved.getFirstName());
    }
}
```

### **10. Production Best Practices**

```properties
# Production configuration
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000
spring.datasource.hikari.leak-detection-threshold=60000

# JPA production settings
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

---

## **Summary**

1. **Add Oracle JDBC driver** and Spring Data JPA dependencies
2. **Configure connection** in `application.properties` or `application.yml`
3. **Create entities** with JPA annotations
4. **Create repositories** extending `JpaRepository`
5. **Add service layer** with `@Transactional`
6. **Create REST controllers** for API endpoints
7. **Configure connection pooling** and production settings
8. **Test thoroughly** before production deployment

Spring Boot's auto-configuration handles most of the heavy lifting, allowing you to focus on business logic rather than database setup.
