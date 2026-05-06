# JPA Entity with Composite Key Examples

## 1. Using @Embeddable and @EmbeddedId (Recommended)

### **Composite Key Class**
```java
@Embeddable
public class OrderItemId implements Serializable {
    
    @Column(name = "ORDER_ID")
    private Long orderId;
    
    @Column(name = "PRODUCT_ID")
    private Long productId;
    
    // Required constructors
    public OrderItemId() {}
    
    public OrderItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
    
    // Getters and setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    // Required equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderId, that.orderId) && 
               Objects.equals(productId, that.productId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
    
    @Override
    public String toString() {
        return "OrderItemId{orderId=" + orderId + ", productId=" + productId + "}";
    }
}
```

### **Entity Class with Composite Key**
```java
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {
    
    @EmbeddedId
    private OrderItemId id;
    
    @Column(name = "QUANTITY")
    private Integer quantity;
    
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;
    
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(Long orderId, Long productId, Integer quantity, BigDecimal unitPrice) {
        this.id = new OrderItemId(orderId, productId);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
        this.createdAt = new Date();
    }
    
    // Convenience methods to access composite key fields
    public Long getOrderId() {
        return id != null ? id.getOrderId() : null;
    }
    
    public Long getProductId() {
        return id != null ? id.getProductId() : null;
    }
    
    // Getters and setters
    public OrderItemId getId() { return id; }
    public void setId(OrderItemId id) { this.id = id; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity;
        updateTotalPrice();
    }
    
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { 
        this.unitPrice = unitPrice;
        updateTotalPrice();
    }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    private void updateTotalPrice() {
        if (quantity != null && unitPrice != null) {
            this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
        }
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
```

---

## 2. Using @IdClass (Alternative Approach)

### **IdClass Definition**
```java
public class EnrollmentId implements Serializable {
    
    private Long studentId;
    private Long courseId;
    
    // Required no-arg constructor
    public EnrollmentId() {}
    
    // Constructor
    public EnrollmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    // Getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    
    // Required equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(studentId, that.studentId) && 
               Objects.equals(courseId, that.courseId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
```

### **Entity Class with @IdClass**
```java
@Entity
@Table(name = "ENROLLMENTS")
@IdClass(EnrollmentId.class)
public class Enrollment {
    
    @Id
    @Column(name = "STUDENT_ID")
    private Long studentId;
    
    @Id
    @Column(name = "COURSE_ID")
    private Long courseId;
    
    @Column(name = "ENROLLMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    
    @Column(name = "GRADE")
    private String grade;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "COMPLETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;
    
    // Constructors
    public Enrollment() {}
    
    public Enrollment(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = new Date();
        this.status = "ENROLLED";
    }
    
    // Getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { 
        this.grade = grade;
        if (grade != null && !grade.isEmpty()) {
            this.status = "COMPLETED";
            this.completedAt = new Date();
        }
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Date getCompletedAt() { return completedAt; }
    public void setCompletedAt(Date completedAt) { this.completedAt = completedAt; }
    
    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", grade='" + grade + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
```

---

## 3. Complex Composite Key Example

### **Three-Part Composite Key**
```java
@Embeddable
public class ScheduleId implements Serializable {
    
    @Column(name = "TEACHER_ID")
    private Long teacherId;
    
    @Column(name = "SUBJECT_ID")
    private Long subjectId;
    
    @Column(name = "SEMESTER")
    private String semester;
    
    public ScheduleId() {}
    
    public ScheduleId(Long teacherId, Long subjectId, String semester) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.semester = semester;
    }
    
    // Getters and setters
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    
    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleId that = (ScheduleId) o;
        return Objects.equals(teacherId, that.teacherId) && 
               Objects.equals(subjectId, that.subjectId) && 
               Objects.equals(semester, that.semester);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectId, semester);
    }
}
```

### **Entity with Complex Composite Key**
```java
@Entity
@Table(name = "TEACHER_SCHEDULES")
public class TeacherSchedule {
    
    @EmbeddedId
    private ScheduleId id;
    
    @Column(name = "ROOM_NUMBER")
    private String roomNumber;
    
    @Column(name = "DAY_OF_WEEK")
    private String dayOfWeek;
    
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    
    @Column(name = "MAX_STUDENTS")
    private Integer maxStudents;
    
    @Column(name = "CURRENT_STUDENTS")
    private Integer currentStudents;
    
    // Constructors
    public TeacherSchedule() {}
    
    public TeacherSchedule(Long teacherId, Long subjectId, String semester, 
                          String roomNumber, String dayOfWeek, Date startTime, Date endTime) {
        this.id = new ScheduleId(teacherId, subjectId, semester);
        this.roomNumber = roomNumber;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxStudents = 30;
        this.currentStudents = 0;
    }
    
    // Convenience methods
    public Long getTeacherId() { return id != null ? id.getTeacherId() : null; }
    public Long getSubjectId() { return id != null ? id.getSubjectId() : null; }
    public String getSemester() { return id != null ? id.getSemester() : null; }
    
    // Business logic
    public boolean isFull() {
        return currentStudents != null && maxStudents != null && 
               currentStudents >= maxStudents;
    }
    
    public void enrollStudent() {
        if (!isFull()) {
            this.currentStudents = (currentStudents == null ? 0 : currentStudents) + 1;
        }
    }
    
    public void removeStudent() {
        if (currentStudents != null && currentStudents > 0) {
            this.currentStudents--;
        }
    }
    
    // Getters and setters
    public ScheduleId getId() { return id; }
    public void setId(ScheduleId id) { this.id = id; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    
    public Integer getMaxStudents() { return maxStudents; }
    public void setMaxStudents(Integer maxStudents) { this.maxStudents = maxStudents; }
    
    public Integer getCurrentStudents() { return currentStudents; }
    public void setCurrentStudents(Integer currentStudents) { this.currentStudents = currentStudents; }
}
```

---

## 4. Repository Examples

### **Repository for @EmbeddedId Entity**
```java
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    
    // Find by order ID
    List<OrderItem> findByIdOrderId(Long orderId);
    
    // Find by product ID
    List<OrderItem> findByIdProductId(Long productId);
    
    // Find by both parts of composite key
    OrderItem findByIdOrderIdAndIdProductId(Long orderId, Long productId);
    
    // Custom query using composite key
    @Query("SELECT oi FROM OrderItem oi WHERE oi.id.orderId = :orderId AND oi.quantity > :minQuantity")
    List<OrderItem> findOrderItemsWithQuantityGreaterThan(@Param("orderId") Long orderId, 
                                                         @Param("minQuantity") Integer minQuantity);
    
    // Delete by order ID
    void deleteByIdOrderId(Long orderId);
    
    // Count items in an order
    long countByIdOrderId(Long orderId);
    
    // Sum of total prices for an order
    @Query("SELECT SUM(oi.totalPrice) FROM OrderItem oi WHERE oi.id.orderId = :orderId")
    BigDecimal sumTotalPriceByOrderId(@Param("orderId") Long orderId);
}
```

### **Repository for @IdClass Entity**
```java
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    
    // Find by student ID
    List<Enrollment> findByStudentId(Long studentId);
    
    // Find by course ID
    List<Enrollment> findByCourseId(Long courseId);
    
    // Find by both parts of composite key
    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
    
    // Find active enrollments
    List<Enrollment> findByStudentIdAndStatus(Long studentId, String status);
    
    // Count enrollments by course
    long countByCourseId(Long courseId);
    
    // Find enrollments with grades
    List<Enrollment> findByCourseIdAndGradeIsNotNull(Long courseId);
    
    // Custom query
    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.status = :status")
    List<Enrollment> findActiveEnrollmentsByStudent(@Param("studentId") Long studentId, 
                                                   @Param("status") String status);
}
```

---

## 5. Service Layer Examples

### **Service for Composite Key Entity**
```java
@Service
@Transactional
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public OrderItem addItemToOrder(Long orderId, Long productId, Integer quantity, BigDecimal unitPrice) {
        // Create composite key
        OrderItemId id = new OrderItemId(orderId, productId);
        
        // Check if item already exists
        Optional<OrderItem> existingItem = orderItemRepository.findById(id);
        
        if (existingItem.isPresent()) {
            // Update quantity if item exists
            OrderItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return orderItemRepository.save(item);
        } else {
            // Create new item
            OrderItem newItem = new OrderItem(orderId, productId, quantity, unitPrice);
            return orderItemRepository.save(newItem);
        }
    }
    
    public void removeItemFromOrder(Long orderId, Long productId) {
        OrderItemId id = new OrderItemId(orderId, productId);
        orderItemRepository.deleteById(id);
    }
    
    public OrderItem updateItemQuantity(Long orderId, Long productId, Integer newQuantity) {
        OrderItemId id = new OrderItemId(orderId, productId);
        OrderItem item = orderItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order item not found"));
        
        item.setQuantity(newQuantity);
        return orderItemRepository.save(item);
    }
    
    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByIdOrderId(orderId);
    }
    
    public BigDecimal getOrderTotal(Long orderId) {
        BigDecimal total = orderItemRepository.sumTotalPriceByOrderId(orderId);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public void deleteAllItemsFromOrder(Long orderId) {
        orderItemRepository.deleteByIdOrderId(orderId);
    }
}
```

---

## 6. Comparison: @EmbeddedId vs @IdClass

| Aspect | @EmbeddedId | @IdClass |
|--------|-------------|----------|
| **Type Safety** | ✅ Strong typing | ⚠️ Primitive fields |
| **Code Clarity** | ✅ Clear key object | ❌ Fields scattered |
| **Method Naming** | ✅ getId().getOrderId() | ❌ getOrderId() directly |
| **Repository** | ✅ Clean queries | ✅ Clean queries |
| **Complexity** | ⚠️ Extra class | ✅ Simpler setup |
| **Best Practice** | ✅ Recommended | ⚠️ Alternative |

---

## 7. Testing Composite Key Entities

### **Unit Test Examples**
```java
@SpringBootTest
@Transactional
public class OrderItemServiceTest {
    
    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Test
    public void testAddItemToOrder() {
        // Given
        Long orderId = 1L;
        Long productId = 100L;
        Integer quantity = 2;
        BigDecimal unitPrice = new BigDecimal("29.99");
        
        // When
        OrderItem item = orderItemService.addItemToOrder(orderId, productId, quantity, unitPrice);
        
        // Then
        assertNotNull(item);
        assertEquals(orderId, item.getOrderId());
        assertEquals(productId, item.getProductId());
        assertEquals(quantity, item.getQuantity());
        assertEquals(unitPrice, item.getUnitPrice());
        assertEquals(unitPrice.multiply(new BigDecimal(quantity)), item.getTotalPrice());
    }
    
    @Test
    public void testUpdateItemQuantity() {
        // Given
        Long orderId = 1L;
        Long productId = 100L;
        OrderItem item = orderItemService.addItemToOrder(orderId, productId, 2, new BigDecimal("29.99"));
        
        // When
        OrderItem updated = orderItemService.updateItemQuantity(orderId, productId, 5);
        
        // Then
        assertEquals(5, updated.getQuantity());
        assertEquals(new BigDecimal("29.99").multiply(new BigDecimal(5)), updated.getTotalPrice());
    }
    
    @Test
    public void testGetItemsByOrder() {
        // Given
        Long orderId = 1L;
        orderItemService.addItemToOrder(orderId, 100L, 2, new BigDecimal("29.99"));
        orderItemService.addItemToOrder(orderId, 200L, 1, new BigDecimal("49.99"));
        
        // When
        List<OrderItem> items = orderItemService.getItemsByOrder(orderId);
        
        // Then
        assertEquals(2, items.size());
        assertTrue(items.stream().anyMatch(item -> item.getProductId().equals(100L)));
        assertTrue(items.stream().anyMatch(item -> item.getProductId().equals(200L)));
    }
    
    @Test
    public void testOrderTotalCalculation() {
        // Given
        Long orderId = 1L;
        orderItemService.addItemToOrder(orderId, 100L, 2, new BigDecimal("29.99"));
        orderItemService.addItemToOrder(orderId, 200L, 1, new BigDecimal("49.99"));
        
        // When
        BigDecimal total = orderItemService.getOrderTotal(orderId);
        
        // Then
        assertEquals(new BigDecimal("109.97"), total); // 29.99*2 + 49.99
    }
}
```

---

## 8. Common Use Cases for Composite Keys

### **Use Case 1: Many-to-Many with Additional Columns**
```java
// Student-Course enrollment with grade and status
@Entity
@Table(name = "STUDENT_COURSE_ENROLLMENT")
public class StudentCourseEnrollment {
    
    @EmbeddedId
    private StudentCourseEnrollmentId id;
    
    @Column(name = "GRADE")
    private String grade;
    
    @Column(name = "ENROLLMENT_DATE")
    private Date enrollmentDate;
    
    @Column(name = "STATUS")
    private String status;
}

@Embeddable
public class StudentCourseEnrollmentId implements Serializable {
    @Column(name = "STUDENT_ID")
    private Long studentId;
    
    @Column(name = "COURSE_ID")
    private Long courseId;
    
    // equals, hashCode, constructors...
}
```

### **Use Case 2: Multi-Tenant Architecture**
```java
// Entity that belongs to a specific tenant
@Entity
@Table(name = "CUSTOMER_ORDERS")
public class CustomerOrder {
    
    @EmbeddedId
    private CustomerOrderId id;
    
    @Column(name = "ORDER_AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "ORDER_DATE")
    private Date orderDate;
}

@Embeddable
public class CustomerOrderId implements Serializable {
    @Column(name = "TENANT_ID")
    private Long tenantId;
    
    @Column(name = "ORDER_ID")
    private Long orderId;
    
    // equals, hashCode, constructors...
}
```

### **Use Case 3: Time-Based Partitioning**
```java
// Sales data partitioned by year and month
@Entity
@Table(name = "SALES_DATA")
public class SalesData {
    
    @EmbeddedId
    private SalesDataId id;
    
    @Column(name = "TOTAL_SALES")
    private BigDecimal totalSales;
    
    @Column(name = "NUMBER_OF_TRANSACTIONS")
    private Integer numberOfTransactions;
}

@Embeddable
public class SalesDataId implements Serializable {
    @Column(name = "YEAR")
    private Integer year;
    
    @Column(name = "MONTH")
    private Integer month;
    
    @Column(name = "REGION_ID")
    private Long regionId;
    
    // equals, hashCode, constructors...
}
```

---

## 9. Best Practices

### **✅ Recommended Practices**
```java
// 1. Use @Embeddable for type safety
@Embeddable
public class CompositeKey implements Serializable {
    // All key fields here
}

// 2. Implement proper equals() and hashCode()
@Override
public boolean equals(Object o) {
    // Use all key fields
}

@Override
public int hashCode() {
    // Use all key fields
}

// 3. Provide convenience methods
public Long getOrderId() {
    return id != null ? id.getOrderId() : null;
}

// 4. Initialize composite key in constructors
public OrderItem(Long orderId, Long productId) {
    this.id = new OrderItemId(orderId, productId);
}
```

### **❌ Avoid These Practices**
```java
// 1. Don't forget Serializable
@Embeddable
public class BadKey {  // Missing implements Serializable
    // fields...
}

// 2. Don't skip equals/hashCode
@Embeddable
public class BadKey implements Serializable {
    private Long field1;
    private Long field2;
    // Missing equals() and hashCode()
}

// 3. Don't use mutable fields in composite key
@Embeddable
public class BadKey implements Serializable {
    private List<String> mutableField; // Bad idea
}
```

---

## Summary

**Two main approaches for composite keys:**

1. **`@EmbeddedId`** (Recommended) - Clean, type-safe, object-oriented
2. **`@IdClass`** - Simpler setup, but less type-safe

**Key Requirements:**
- **Implement `Serializable`** - Required for all composite key classes
- **Override `equals()` and `hashCode()`** - Must use all key fields
- **No-arg constructor** - Required by JPA
- **Proper field mapping** - Each key field mapped with `@Id`

**Use composite keys when:**
- **Many-to-many relationships** with additional columns
- **Natural business keys** (e.g., order + product)
- **Multi-tenant data** (tenant + entity_id)
- **Time-based partitions** (entity + date_period)

**`@EmbeddedId` is the preferred approach for better type safety and cleaner code!**
