**Class Level vs. Instance Level Variables**

* **Class-level variables:** Declared with the `static` keyword, shared by all instances of the class.
* **Instance-level variables:** Unique to each instance, declared without the `static` keyword.

**HashCode and Equals Contract**

* **`hashCode()`:** Generates a unique integer representation of an object.
* **`equals()`:** Determines object equality.
* **Contract:** If two objects are equal, their `hashCode()` must be the same.

**Why Final Classes Are Required**

* Prevent subclassing, ensuring class behavior remains unchanged.
* Used for utility classes or well-defined concepts (e.g., `String`).

**Core Java Concepts**

* Object-Oriented Programming (OOP)
* Data Structures (Arrays, Lists, Sets, Maps, Stacks, Queues, Trees, Graphs)
* Exception Handling
* Multithreading
* I/O Operations
* Collections Framework

**HashMap, Internal Working of HashMap**

* Uses a hash function to map keys to buckets.
* Handles collisions with linked lists or trees.

**Singleton Class**

* Ensures only one instance of a class exists.
* Uses private constructors and static methods.

**Exception Handling**

* `try-catch-finally` blocks for handling exceptions.

**Reading and Sorting Employee Data**

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Employee {
    int empId;
    String name;
    String city;

    // Constructor, getters, and setters
}

public class EmployeeData {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Employee emp = new Employee(Integer.parseInt(parts[0]), parts[1], parts[2]);
                employees.add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(employees, Comparator.comparingInt(Employee::getEmpId));
        Set<Employee> uniqueEmployees = new HashSet<>(employees); // Remove duplicates

        // Print sorted and unique employees
        for (Employee emp : uniqueEmployees) {
            System.out.println(emp.empId + ", " + emp.name + ", " + emp.city);
        }
    }
}
```

**HashMap with Employee as Key**

```java
class Employee {
    // ...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}
```

**Finding Pairs with a Given Sum**

```java
public static List<int[]> findPairs(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    List<int[]> pairs = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            pairs.add(new int[]{map.get(complement), nums[i]});
        }
        map.put(nums[i], i);
    }
    return pairs;
}
```

**Sorting, Grouping, and Sorting Apple Objects**

```java
class Apple {
    int size;
    String color;
    int price;
    // ...
}

// ...

apples.sort(Comparator.comparingInt(Apple::getSize));
Map<String, List<Apple>> groupedApples = apples.stream()
        .collect(Collectors.groupingBy(Apple::getColor));
groupedApples.forEach((color, group) -> group.sort(Comparator.comparingInt(Apple::getPrice)));
```

**Sorting a Stack Using Another Stack**

```java
public static void sortStack(Stack<Integer> stack) {
    Stack<Integer> tempStack = new Stack<>();
    while (!stack.isEmpty()) {
        int temp = stack.pop();
        while (!tempStack.isEmpty() && tempStack.peek() > temp) {
            stack.push(tempStack.pop());
        }
        tempStack.push(temp);
    }
    while (!tempStack.isEmpty()) {
        stack.push(tempStack.pop());
    }
}
```

**Creating a Singleton Class**

```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```
**Create a Resource class and make it such that only one instance could be used**

```java
public class Resource {
    private static final Resource INSTANCE = new Resource();

    private Resource() {
    }

    public static Resource getInstance() {
        return INSTANCE;
    }

    // ... other methods of the Resource class
}
```

This is the Singleton pattern. It ensures that only one instance of the `Resource` class exists, and it's accessed through the `getInstance()` method.

**Lambda Functions with Examples Between Java Versions**

**Java 7 and Earlier:**

```java
Arrays.sort(numbers, new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
});
```

**Java 8:**

```java
Arrays.sort(numbers, (o1, o2) -> o1.compareTo(o2));
```

**Dependency Injection in Spring**

Dependency Injection (DI) is a design pattern where objects get their dependencies injected rather than creating them themselves. This promotes loose coupling and testability. Spring Framework provides mechanisms for DI:

* **Constructor Injection:** Inject dependencies through the constructor.
* **Setter Injection:** Inject dependencies through setter methods.
* **Field Injection:** Inject dependencies directly into fields (less preferred).

**Overloading equals() and hashCode()**

* **`equals()`:** Determines object equality.
* **`hashCode()`:** Generates a hash code for an object.
* **Contract:** If two objects are equal, their `hashCode()` must be the same.

**Brute Force Logic for Max 0s in a Row**

```java
public static int maxZeros(int[][] matrix) {
    int maxZeros = 0;
    for (int[] row : matrix) {
        int count = 0;
        for (int num : row) {
            if (num == 0) {
                count++;
            } else {
                break;
            }
        }
        maxZeros = Math.max(maxZeros, count);
    }
    return maxZeros;
}
```

**Difference Between Abstract Class and Interface**

| Feature | Abstract Class | Interface |
|---|---|---|
| Inheritance | Single inheritance | Multiple inheritance |
| Methods | Can have both abstract and concrete methods | Only abstract methods (Java 7 and earlier), can have default and static methods (Java 8+) |
| Variables | Can have both static and non-static variables | Can only have static final variables |

**Java 8 Features**

* Lambda expressions
* Functional interfaces
* Stream API
* Default and static methods in interfaces
* Method references
* Date and Time API
* Optional class

**Automatic and Transient Variables**

* **Automatic variables:** Declared within a method, have local scope, and are automatically garbage collected.
* **Transient variables:** Marked with the `transient` keyword, are not serialized when an object is serialized.

**Ways to Create a Thread in Java**

1. **Extending the `Thread` class:**
   ```java
   class MyThread extends Thread {
       // ...
   }
   ```
2. **Implementing the `Runnable` interface:**
   ```java
   class MyRunnable implements Runnable {
       // ...
   }
   ```

**Printing Even and Odd Numbers Using Two Threads**

```java
class OddEvenPrinter {
    private int number = 1;
    private boolean isOdd = true;

    public synchronized void printNumber() {
        while (number <= 10) {
            if (isOdd) {
                System.out.print(number + " ");
                number++;
                isOdd = false;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

**Synchronization**

Synchronization ensures that only one thread can access a shared resource at a time. It's used to prevent race conditions and data corruption.

**HashSet vs. HashTable**

* **HashSet:** Unsynchronized, allows null values.
* **HashTable:** Synchronized, does not allow null values.

**HashMap Internal Implementation**
HashMap uses a hash function to map keys to buckets. Collisions are handled using linked lists or trees.

**Comparable and Comparator**

* **Comparable:** Defines a natural ordering for objects.
* **Comparator:** Defines a custom comparison logic for objects.

**Checking if a Number is a Power of 10**

```java
public static boolean isPowerOf10(int num) {
    if (num <= 0) {
        return false;
    }
    while (num % 10 == 0) {
        num /= 10;
    }
    return num == 1;
}
```

**Hibernate Advantages over JDBC**

* ORM capabilities
* Simplified database interactions
* Lazy loading, caching, and query optimization

**Dialect in Hibernate**
Specifies the database-specific SQL dialect.

**Design Patterns**

* Singleton, Factory, Observer, Strategy, etc.

**Account (Saving, Current) Cash Withdrawal Database and Java Layer Design**

* Design a database schema with tables for accounts, transactions, etc.
* Use Hibernate or JDBC to interact with the database.
* Implement business logic for withdrawals, interest calculations, etc.
* Consider using design patterns like Strategy or Template Method for different account types.

**Abstract Class vs. Interface**
Refer to point 6.

**Internal Implementation of HashSet**
HashSet uses a hash table to store elements.

**Finding Two Numbers Summing to k**
Use a HashMap to store seen numbers and their complements.

**Time Complexities of Get and Put Methods of Map**
O(1) on average, but can be O(n) in worst-case scenarios due to collisions.

**Finding the Student with the Highest Average Score**

```java
Map<String, List<Integer>> scores = new HashMap<>();
// ... populate the map with student names and their scores

Map<String, Double> averages = scores.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));

String highestAverageStudent = averages.entrySet().stream()
        .max(Comparator.comparingDouble(Map.Entry::getValue))
        .map(Map.Entry::getKey)
        .orElse(null);
```

**Java 8 Streams**
Stream API provides a functional approach to process collections of data.

**Comparator and Comparable**
Refer to point 14.

**Changing the Behavior of External Jar Classes**

While you can't directly modify the source code of an external JAR, you can:

1. **Create a Wrapper Class:**
    - Wrap the external class and provide a custom implementation for the methods you want to modify.
    - This allows you to override behavior without affecting the original class.

2. **Use AOP (Aspect-Oriented Programming):**
    - Use frameworks like Spring AOP to intercept method calls and add custom logic before or after the original method execution.

3. **Class Loading Techniques:**
    - In specific scenarios, you might be able to use custom class loaders to modify the class loading process and potentially alter the behavior of the external class. However, this is a complex technique and should be used with caution.

**How JVM Recognizes the Database Driver**

1. **Classpath:** The JVM searches for the driver class in the classpath.
2. **JDBC URL:** The JDBC URL specifies the database driver class name.
3. **Driver Manager:** The `DriverManager` class loads the appropriate driver based on the JDBC URL.

**Standard Java Code for JDBC Connection Pooling**

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class JdbcConnectionPool {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            bds.setUrl("jdbc:mysql://localhost:3306/mydatabase");
            bds.setUsername("user");
            bds.setPassword("password");

            // Configure connection pool properties
            bds.setInitialSize(5);
            bds.setMaxTotal(20);
            bds.setMinIdle(5);
            bds.setMaxIdle(10);

            dataSource = bds;
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
```

**Maximum Number of People in a Conference**

```java
public int maxAttendees(int[] arrival, int[] departure) {
    Arrays.sort(arrival);
    Arrays.sort(departure);

    int count = 0, maxCount = 0;
    int i = 0, j = 0;
    while (i < arrival.length && j < departure.length) {
        if (arrival[i] <= departure[j]) {
            count++;
            maxCount = Math.max(maxCount, count);
            i++;
        } else {
            count--;
            j++;
        }
    }
    return maxCount;
}
```

**Total Quantity from PersonalDetails**

```java
public int totalQuantity(List<PersonalDetails> personalDetails, List<ConfidentialsDetails> confidentialDetails) {
    Set<String> accounts = new HashSet<>();
    for (ConfidentialsDetails cd : confidentialDetails) {
        accounts.add(cd.getAccountNumber());
    }

    int totalQuantity = 0;
    for (PersonalDetails pd : personalDetails) {
        if (accounts.contains(pd.getAccountNumber())) {
            totalQuantity += pd.getQuantity();
        }
    }

    return totalQuantity;
}
```

**Adding Fractional Numbers**

```java
public class Fraction {
    private int numerator;
    private int denominator;

    // ... other methods

    public Fraction add(Fraction other) {
        int lcm = lcm(denominator, other.denominator);
        int newNumerator = numerator * lcm / denominator + other.numerator * lcm / other.denominator;
        return new Fraction(newNumerator, lcm);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
```

**How Collections Work Internally**

Collections like ArrayList, LinkedList, HashSet, and HashMap use various data structures and algorithms for efficient operations.

**Hash Code Object Copy**

You can use object cloning or serialization/deserialization to copy an object. However, for complex objects, consider deep copying to avoid shallow copies.

**HashTable vs HashMap**

* **HashTable** is synchronized and doesn't allow null keys or values.
* **HashMap** is not synchronized and allows one null key and multiple null values.

**Implementing ArrayList Using Array**

```java
public class MyArrayList<E> {
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[10];
        size = 0;
    }

    public void add(E element) {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size++] = element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
    }
}
```

Explanation:

1. **Data Storage:** Uses an array to store elements.
2. **Dynamic Resizing:** When the array is full, a new array with double the capacity is created, and elements are copied over.
3. **`add()` Method:** Adds an element to the end of the array.
4. **`get()` Method:** Retrieves an element at a specific index.
5. **`size()` Method:** Returns the number of elements in the array.
6. **`remove()` Method:** Removes an element at a specific index by shifting elements.

**Note:** This is a basic implementation and doesn't include all the features of the standard `ArrayList` class, such as iterators, list iterators, and advanced operations. However, it provides a foundation for understanding how an array-based list works.

**Code for Mirror of Tree**

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class TreeMirror {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftMirror = mirrorTree(root.right);
        TreeNode rightMirror = mirrorTree(root.left);

        root.left = leftMirror;
        root.right = rightMirror;

        return root;
    }
}
```

**Different Ways to Create a Thread**

1. **Extending the `Thread` class:**
   ```java
   class MyThread extends Thread {
       public void run() {
           // Thread's code
       }
   }
   ```
2. **Implementing the `Runnable` interface:**
   ```java
   class MyRunnable implements Runnable {
       public void run() {
           // Thread's code
       }
   }
   ```

**Thread Pool**

A thread pool is a collection of reusable threads. It helps improve performance by avoiding the overhead of creating and destroying threads for each task.

**Printing Even and Odd Numbers Using Two Threads**

```java
class OddEvenPrinter {
    private int number = 1;
    private boolean isOdd = true;

    public synchronized void printNumber() {
        while (number <= 20) {
            if (isOdd) {
                System.out.print(number + " ");
                number++;
                isOdd = false;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

**How Servlet Works**

1. **Request:** A client sends an HTTP request to the web server.
2. **Server:** The web server receives the request and forwards it to the servlet container.
3. **Servlet Container:** The servlet container loads the servlet class, instantiates it, and calls its `init()` method.
4. **Service:** The servlet container calls the servlet's `service()` method, passing the request and response objects.
5. **Response:** The servlet processes the request, generates the response, and sends it back to the client through the servlet container.
6. **Destruction:** When the servlet is no longer needed, the servlet container calls its `destroy()` method.

**Algorithm to Find Panagram**

1. Create a boolean array of size 26 to mark the occurrence of each alphabet.
2. Iterate through the string and mark the corresponding alphabet in the array.
3. After iterating, check if all elements in the array are true. If yes, it's a panagram.

**Overriding `hashCode()` and `equals()`**

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MyClass myClass = (MyClass) o;
    return field1 == myClass.field1 && Objects.equals(field2, myClass.field2);
}

@Override
public int hashCode() {
    return Objects.hash(field1, field2);
}
```

**Functional Interface Sample Implementation and Advantage**

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void doSomething();
}

// Usage:
MyFunctionalInterface myInterface = () -> System.out.println("Hello, world!");
myInterface.doSomething();
```

Advantages:
- Concise and readable code
- Enables functional programming paradigms
- Can be used with lambda expressions and method references

**Filling Color to a Matrix**

```java
public void fillColor(int[][] matrix, int x, int y, int color) {
    int originalColor = matrix[x][y];
    if (originalColor == color) {
        return;
    }

    fill(matrix, x, y, originalColor, color);
}

private void fill(int[][] matrix, int x, int y, int originalColor, int newColor) {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != originalColor) {
        return;
    }

    matrix[x][y] = newColor;

    fill(matrix, x - 1, y, originalColor, newColor);
    fill(matrix, x + 1, y, originalColor, newColor);
    fill(matrix, x, y - 1, originalColor, newColor);
    fill(matrix, x, y + 1, originalColor, newColor);
}
```
**Removing Repeated Elements and Sorting**

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static String removeDuplicates(String str) {
        Set<Character> charSet = new HashSet<>();
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (charSet.add(c)) {
                result.append(c);
            }
        }
        return result.toString().chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
```

**Singleton Design Pattern**

```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

**Connecting Nodes in Two Linked Lists**

To connect nodes in two linked lists, you typically create a new node and point its `next` pointer to the head of the second linked list. The time complexity of this operation is O(1), and the space complexity is O(1) as you're not creating any additional data structures.

**Returning an Object from a Thread**

1. **Using `Callable` and `Future`:**
   ```java
   Callable<String> task = () -> {
       // Perform task
       return "Result";
   };

   Future<String> future = executorService.submit(task);
   String result = future.get();
   ```
2. **Using `ThreadLocal`:**
   ```java
   ThreadLocal<Object> threadLocal = new ThreadLocal<>();
   threadLocal.set(result);
   ```

**Finding the First Non-Repeating Character**

```java
public static char firstNonRepeatingChar(String str) {
    Map<Character, Integer> charCount = new HashMap<>();
    for (char c : str.toCharArray()) {
        charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    for (char c : str.toCharArray()) {
        if (charCount.get(c) == 1) {
            return c;
        }
    }

    return '\0';
}
```

**Finding Average Marks**

```java
public static double calculateAverage(int[] marks) {
    int sum = 0;
    for (int mark : marks) {
        sum += mark;
    }
    return (double) sum / marks.length;
}
```

**Internal Working of ConcurrentHashMap**

ConcurrentHashMap uses a segmented approach to improve concurrency. It divides the hash table into multiple segments, each with its own lock. This allows multiple threads to access different segments concurrently, improving performance.

**Finding the Number of Occurrences of a Substring**

```java
public static int countSubstringOccurrences(String str, String subStr) {
    int count = 0;
    int len = subStr.length();
    for (int i = 0; i <= str.length() - len; i++) {
        if (str.substring(i, i + len).equals(subStr)) {
            count++;
        }
    }
    return count;
}
```

**Comparable and Comparator**

* **Comparable:** Defines a natural ordering for objects.
* **Comparator:** Defines a custom comparison logic for objects.

```java
class Person implements Comparable<Person> {
    String name;
    int age;

    // ...

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}

// Custom Comparator
Comparator<Person> byNameComparator = Comparator.comparing(Person::getName);
```
**SQL Query to Find Max Salary in a Particular Department**

```sql
SELECT MAX(salary)
FROM employees
WHERE department = 'YourDepartmentName';
```

**SQL Joins**

* **INNER JOIN:** Returns rows that have matching values in both tables.
* **LEFT JOIN:** Returns all rows from the left table, and the matched rows from the right table.
* **RIGHT JOIN:** Returns all rows from the right table, and the matched rows from the left table.
* **FULL OUTER JOIN:** Returns all rows when there is a match in either left or right table.

**Filling a Circle in a 2x2 Matrix**

You can use a simple algorithm to fill the circle:

1. **Iterate over each cell of the matrix.**
2. **For each cell, calculate its distance from the center of the circle.**
3. **If the distance is less than or equal to the radius of the circle, fill the cell with the desired color.**

**String Object Creation**

In the given code, 4 objects will be created:

1. `s1` refers to the string literal "Hello" in the string pool.
2. `s2` creates a new `String` object in the heap.
3. `s3` also refers to the string literal "Hello" in the string pool.
4. `s4` creates a new `String` object in the heap.

**Abstract Class vs. Interface**

* **Abstract Class:** Can have both abstract and concrete methods. Can have variables. Can provide a partial implementation.
* **Interface:** Can only have abstract methods (before Java 8). Can have default and static methods (Java 8+). Can only have static final variables.

**Try-Catch-Finally Block**

The output will be:

```
Inside try
```

The `ArithmeticException` will be thrown, and the `finally` block will execute.

**Marker Interfaces**

Marker interfaces don't have any methods. They are used to provide additional information to the compiler or JVM. Examples include `Serializable`, `Cloneable`, and `Remote`.

**Serialization**

Serialization is the process of converting an object into a byte stream to store it or transmit it over a network. The `transient` keyword can be used to exclude fields from serialization. The `volatile` keyword is used to ensure that changes to a variable are visible to all threads.

**Thread Life Cycle**

1. **New:** The thread is created but not yet started.
2. **Runnable:** The thread is ready to run but waiting for CPU time.
3. **Running:** The thread is currently executing.
4. **Blocked:** The thread is waiting for a resource or event.
5. **Terminated:** The thread has finished executing.

**Printing Non-Consecutive Elements**

```java
public static void printNonConsecutive(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] != arr[i - 1]) {
            System.out.print(arr[i] + " ");
        }
    }
}
```

**Fibonacci Series and Optimization**

```java
public static int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

// Optimized using memoization
public static int fibonacciMemoized(int n, int[] memo) {
    if (n <= 1) {
        return n;
    }
    if (memo[n] != 0) {
        return memo[n];
    }
    memo[n] = fibonacciMemoized(n - 1, memo) + fibonacciMemoized(n - 2, memo);
    return memo[n];
}
```

**Classloader**

The classloader is responsible for loading classes into the JVM. It follows a hierarchical structure:
1. **Bootstrap ClassLoader:** Loads core Java classes.
2. **Extension ClassLoader:** Loads extension classes.
3. **System ClassLoader:** Loads application classes.

The classloader searches for classes in the classpath and loads them into memory.

**Spring vs Spring Boot**

* **Spring:** A comprehensive framework for building enterprise applications. It provides features like IoC, AOP, transaction management, etc.
* **Spring Boot:** A framework built on top of Spring, simplifying the development process. It provides auto-configuration, starter dependencies, and embedded servers.

**IoC (Inversion of Control) and DI (Dependency Injection)**

* **IoC:** A design principle where object creation and dependency management are handled by the framework, not the application code.
* **DI:** A technique for implementing IoC, where dependencies are injected into objects through constructors, setters, or fields.

**@Qualifier Annotation**

The `@Qualifier` annotation is used to disambiguate beans when multiple beans of the same type are available. It helps Spring resolve which bean to inject.

**Multiple Configuration Property Files in Spring Boot**

You can use property placeholders or profile-specific configuration files to manage multiple configuration properties.

**Connecting a Datasource in Spring Boot**

1. **Add the necessary dependencies:**
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   ```
2. **Configure the datasource in `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
   spring.datasource.username=user
   spring.datasource.password=password
   ```
3. **Create a JPA repository:**
   ```java
   @Repository
   public interface EmployeeRepository extends JpaRepository<Employee, Long> {
       // ...
   }
   ```

**PostgreSQL Case Sensitivity**

PostgreSQL is case-sensitive by default for identifiers (table and column names). However, you can configure it to be case-insensitive using the `lower_case_tables` configuration parameter.

**Selecting Employee and Manager Names**

```sql
SELECT e.name AS employee_name, m.name AS manager_name
FROM employees e
JOIN employees m ON e.manager_id = m.id;
```

**Separate Thread for Each Servlet**

No, typically you don't need a separate thread for each servlet. Servlet containers manage thread pools to handle incoming requests efficiently.

**Implementing a Stack Using an Array**

```java
public class Stack {
    private int[] data;
    private int top;

    public Stack(int capacity) {
        data = new int[capacity];
        top = -1;
    }

    public void push(int item) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        data[++top] = item;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }
}
```

**Set vs. List**

* **Set:** Unordered collection of unique elements.
* **List:** Ordered collection of elements, allowing duplicates.

**Inheritance vs. Composition**

* **Inheritance:** Creates an "is-a" relationship between classes.
* **Composition:** Creates a "has-a" relationship between classes.

Composition is often preferred over inheritance, as it promotes loose coupling and avoids the "inheritance tax".

**Array Sort with Less Time Complexity**

* **QuickSort:** A divide-and-conquer algorithm with an average time complexity of O(n log n).
* **Merge Sort:** A divide-and-conquer algorithm with a time complexity of O(n log n).

**JDBC or Hibernate Connection Steps**

**JDBC:**

1. Load the JDBC driver.
2. Establish a database connection.
3. Create a Statement or PreparedStatement object.
4. Execute the SQL query.
5. Process the results.
6. Close the connection.

**Hibernate:**

1. Configure Hibernate with database connection details.
2. Create SessionFactory and Session objects.
3. Use Hibernate's API to perform CRUD operations.
4. Close the Session and SessionFactory.

**Lazy vs. Eager Loading**

* **Lazy Loading:** Objects are fetched from the database only when they are actually needed.
* **Eager Loading:** Objects are fetched along with the parent object, even if they are not immediately required.

**Abstract Class vs. Interface**

Refer to the earlier explanation.

**Static Block and Static Methods**

* **Static Block:** Executed only once, when the class is first loaded. Used for initialization tasks.
* **Static Methods:** Belong to the class, not to any instance. Can be called without creating an object.

**Output of the Code Snippet:**

```Java
public static void main () {
    if(true) {
        break;
    }
}
```

The code will compile and run without any output. The `break` statement will only exit the `if` block, not the `main` method.

**Difference between Arrays, ArrayList, and LinkedList**

| Feature | Array | ArrayList | LinkedList |
|---|---|---|---|
| Data Structure | Fixed-size contiguous memory | Dynamically resizable array | Doubly-linked list |
| Access Time | O(1) | O(1) | O(n) (average) |
| Insertion/Deletion Time | O(n) (in the middle) | O(n) (in the middle) | O(1) (at the beginning or end) |
| Memory Usage | Efficient | More memory overhead | More memory overhead |

**Count of a Given Number in an Integer Array**

```java
public static int countOccurrences(int[] arr, int num) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == num) {
            count++;
        }
    }
    return count;
}
```

**Reverse of a Number**

```java
public static int reverseNumber(int num) {
    int reversed = 0;
    while (num != 0) {
        int digit = num % 10;
        reversed = reversed * 10 + digit;
        num /= 10;
    }
    return reversed;
}
```

**Recursive Method**

A recursive method is a method that calls itself directly or indirectly. It's often used to solve problems that can be broken down into smaller, self-similar subproblems.

**Features of Java 8**

* Lambda expressions
* Functional interfaces
* Stream API
* Default and static methods in interfaces
* Method references
* Date and Time API
* Optional class

**Swap Consecutive Numbers in a Linked List**

``
public ListNode swapPairs(ListNode head) {
if (head == null || head.next == null) {
return head;
}

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    while (head != null && head.next != null) {
        ListNode first = head;
        ListNode second = head.next;

        prev.next = second;
        first.next = second.next;
        second.next = first;

        prev = first;
        head = first.next;
    }

    return dummy.next;
}
```

**Remove an Element from a List**

```java
public ListNode removeElement(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    while (head != null) {
        if (head.val == val) {
            prev.next = head.next;
        } else {
            prev = head;
        }
        head = head.next;
    }

    return dummy.next;
}
```

**Employee Object as HashMap Key**

Implement `hashCode()` and `equals()` methods for the `Employee` class based on the unique identifier, such as `empId`.

**Significance of `toString()` Method**

The `toString()` method provides a string representation of an object. It's useful for debugging, logging, and other purposes.

**Data Structure using ArrayList**

You can use an `ArrayList` to store a collection of objects. It provides dynamic resizing and easy manipulation of elements.

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
// ...
```

**Multiple Catch Blocks**

If multiple catch blocks are defined, the JVM will try to match the exception thrown with the most specific catch block first. If no match is found, it will move on to the next most specific catch block. It's important to order the catch blocks from most specific to least specific.

**HashCode Calculation in HashMap**

The `hashCode()` method of the key object is used to calculate the hash code. This hash code is then used to determine the bucket in the HashMap where the key-value pair will be stored.

**Method Call Order for `put()`**

When you put a new value into a HashMap, the `hashCode()` method is called first to determine the bucket. Then, the `equals()` method is used to check if the key already exists in the bucket. If the key doesn't exist, the new key-value pair is added to the bucket.

**String Creation Approaches**

**Approach 1:**
- Creates a reference to the string literal "abc" in the string pool.
- More efficient, as it avoids creating a new object.

**Approach 2:**
- Creates a new `String` object in the heap.
- Less efficient due to object creation overhead.

**OOPS Principles**

* **Encapsulation:** Bundling data and methods that operate on that data within a single unit (class).
* **Inheritance:** Creating new classes (subclasses) from existing ones (superclasses).
* **Polymorphism:** The ability of objects to take on many forms.
* **Abstraction:** Hiding implementation details and exposing only essential features.

**Access Modifiers in Java**

1. **Private:** Accessible only within the same class.
2. **Default (package-private):** Accessible within the same package.
3. **Protected:** Accessible within the same package and subclasses.
4. **Public:** Accessible from anywhere.

**Types of Indexes and Their Use**

* **B-Tree Index:** Most common type, efficient for range queries and equality searches.
* **Hash Index:** Efficient for equality searches but not for range queries.
* **Bitmapped Index:** Efficient for low-cardinality columns.
* **Clustered Index:** Determines the physical order of data in a table.

**Indexes are used to:**

* **Improve query performance:** By reducing the amount of data that needs to be scanned.
* **Create unique constraints:** To ensure data integrity.
* **Speed up sorting and grouping operations.**

**What is a Trigger?**

A trigger is a special kind of stored procedure that automatically executes when a specific event occurs on a table, such as INSERT, UPDATE, or DELETE.

**Trigger Creation Syntax (Example in SQL Server):**

```sql
CREATE TRIGGER [TriggerName]
ON [TableName]
AFTER [INSERT, UPDATE, DELETE]
AS
BEGIN
    -- Trigger body (SQL statements to execute)
END
```

**Advantages of Triggers:**

* **Enforce data integrity:** Ensure data consistency and validity.
* **Automate tasks:** Perform actions automatically based on data changes.
* **Implement complex business rules:** Enforce complex business logic.

**Writing SQL Queries with Joins**

* **INNER JOIN:** Returns rows that have matching values in both tables.
* **LEFT JOIN:** Returns all rows from the left table, and the matched rows from the right table.
* **RIGHT JOIN:** Returns all rows from the right table, and the matched rows from the left table.
* **FULL OUTER JOIN:** Returns all rows when there is a match in either left or right table.

**Optimizing Complex SQL Queries**

* **Indexing:** Create indexes on frequently queried columns.
* **Query Optimization:** Use EXPLAIN or EXPLAIN PLAN to analyze query execution plans.
* **Query Rewriting:** Rewrite queries to use more efficient joins or subqueries.
* **Database Tuning:** Optimize database configuration, such as buffer pools and memory settings.

**Factors Affecting Query Performance**

* **Lack of Indexes:** Missing indexes can slow down queries, especially on large tables.
* **Inefficient Joins:** Complex joins can be optimized by using appropriate join types and indexes.
* **Suboptimal Query Structure:** Unnecessary subqueries or complex WHERE clauses can impact performance.
* **Data Volume and Complexity:** Large datasets and complex data structures can slow down queries.
* **Database Server Configuration:** Incorrect configuration settings can affect performance.

**Query to Find the Details of the Employee with Maximum Total Salary**

```sql
SELECT *
FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);
```

**Constraints in SQL**

* **Primary Key:** Uniquely identifies each row in a table.
* **Foreign Key:** References a primary key in another table.
* **Unique:** Ensures that a column or set of columns has unique values.
* **Not Null:** Ensures that a column cannot have a null value.
* **Check:** Enforces a specific condition on a column or a set of columns.
* **Default:** Specifies a default value for a column.
