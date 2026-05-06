
---

## 🔹 Basic Level (1–15)
**1. Find maximum element in a list**
```java
int max = list.stream().max(Integer::compare).orElseThrow();
```

**2. Find minimum element in a list**
```java
int min = list.stream().min(Integer::compare).orElseThrow();
```

**3. Count distinct elements**
```java
long distinctCount = list.stream().distinct().count();
```

**4. Sort a list of strings alphabetically**
```java
List<String> sorted = list.stream().sorted().collect(Collectors.toList());
```

**5. Convert list of integers to list of strings**
```java
List<String> strList = list.stream().map(String::valueOf).collect(Collectors.toList());
```

**6. Filter even numbers**
```java
List<Integer> evens = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
```

**7. Sum of all numbers**
```java
int sum = list.stream().mapToInt(Integer::intValue).sum();
```

**8. Average of numbers**
```java
double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0);
```

**9. Convert list to set**
```java
Set<Integer> set = list.stream().collect(Collectors.toSet());
```

**10. Print all elements**
```java
list.stream().forEach(System.out::println);
```

**11. Count elements greater than 10**
```java
long count = list.stream().filter(n -> n > 10).count();
```

**12. Find first element**
```java
int first = list.stream().findFirst().orElseThrow();
```

**13. Check if any element is negative**
```java
boolean hasNegative = list.stream().anyMatch(n -> n < 0);
```

**14. Check if all elements are positive**
```java
boolean allPositive = list.stream().allMatch(n -> n > 0);
```

**15. Check if no element is zero**
```java
boolean noneZero = list.stream().noneMatch(n -> n == 0);
```

---

## 🔹 Intermediate Level (16–30)
**16. Find second highest number**
```java
int secondHighest = list.stream()
    .sorted(Comparator.reverseOrder())
    .distinct()
    .skip(1)
    .findFirst()
    .orElseThrow();
```

**17. Count frequency of words in a sentence**
```java
Map<String, Long> freq = Arrays.stream(sentence.split(" "))
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

**18. Group employees by department**
```java
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

**19. Find average salary**
```java
double avgSalary = employees.stream()
    .collect(Collectors.averagingDouble(Employee::getSalary));
```

**20. Find duplicates in a list**
```java
Set<Integer> seen = new HashSet<>();
Set<Integer> duplicates = list.stream()
    .filter(n -> !seen.add(n))
    .collect(Collectors.toSet());
```

**21. Concatenate strings with delimiter**
```java
String result = list.stream().collect(Collectors.joining(", "));
```

**22. Flatten nested lists**
```java
List<Integer> flat = nested.stream().flatMap(List::stream).collect(Collectors.toList());
```

**23. Partition employees by age**
```java
Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getAge() > 30));
```

**24. Find top 3 salaries**
```java
List<Double> top3 = employees.stream()
    .map(Employee::getSalary)
    .sorted(Comparator.reverseOrder())
    .limit(3)
    .collect(Collectors.toList());
```

**25. Check if all employees are active**
```java
boolean allActive = employees.stream().allMatch(Employee::isActive);
```

**26. Find longest string in a list**
```java
String longest = list.stream()
    .max(Comparator.comparingInt(String::length))
    .orElse("");
```

**27. Find employee with highest salary**
```java
Employee top = employees.stream()
    .max(Comparator.comparing(Employee::getSalary))
    .orElseThrow();
```

**28. Custom reduce: sum of squares**
```java
int sumSquares = list.stream().reduce(0, (a, b) -> a + b*b);
```

**29. Parallel stream example**
```java
long count = list.parallelStream().filter(n -> n % 2 == 0).count();
```

**30. Collect salaries into summary statistics**
```java
DoubleSummaryStatistics stats = employees.stream()
    .collect(Collectors.summarizingDouble(Employee::getSalary));
```

---

## 🔹 Advanced Level (31–40)
**31. Find nth highest salary**
```java
int nthHighest = employees.stream()
    .map(Employee::getSalary)
    .sorted(Comparator.reverseOrder())
    .skip(n-1)
    .findFirst()
    .orElseThrow();
```

**32. Find common elements between two lists**
```java
List<Integer> common = list1.stream()
    .filter(list2::contains)
    .collect(Collectors.toList());
```

**33. Find employees with salary > average**
```java
double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
List<Employee> aboveAvg = employees.stream().filter(e -> e.getSalary() > avg).collect(Collectors.toList());
```

**34. Convert list to map (id → name)**
```java
Map<Integer, String> map = employees.stream()
    .collect(Collectors.toMap(Employee::getId, Employee::getName));
```

**35. Find most frequent element**
```java
int mostFreq = list.stream()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .get().getKey();
```

**36. Reverse sort strings**
```java
List<String> revSorted = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
```

**37. Find employees with duplicate names**
```java
Set<String> duplicates = employees.stream()
    .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
    .entrySet().stream()
    .filter(e -> e.getValue() > 1)
    .map(Map.Entry::getKey)
    .collect(Collectors.toSet());
```

**38. Find sum of even numbers**
```java
int sumEven = list.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
```

**39. Find product of all numbers**
```java
int product = list.stream().reduce(1, (a, b) -> a * b);
```

**40. Find employees sorted by multiple fields**
```java
List<Employee> sorted = employees.stream()
    .sorted(Comparator.comparing(Employee::getDepartment)
                      .thenComparing(Employee::getSalary))
    .collect(Collectors.toList());
```

---

## 🔹 Expert Level (41–50)
**41. Find kth smallest number**
```java
int kthSmallest = list.stream()
    .sorted()
    .skip(k-1)
    .findFirst()
    .orElseThrow();
```

**42. Find employees with unique departments**
```java
Set<String> uniqueDepts = employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
```

**43. Find employees with salary in range**
```java
List<Employee> range = employees.stream()
    .filter(e -> e.getSalary() >= 5000 && e.getSalary() <= 10000)
    .collect(Collectors.toList());
```

**44. Find average length of strings**
```java
double avgLen = list.stream().collect(Collectors.averagingInt(String::length));
```

**45. Find employees grouped by active status**
```java
Map<Boolean, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(Employee::isActive));
```

**46. Find employees with duplicate salaries**
```java
Set<Double> dupSalaries = employees.stream()
    .collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()))
    .entrySet().stream()
    .filter(e -> e.getValue() > 1)
    .map(Map.Entry::getKey)
    .collect(Collectors.toSet());
```

**47. Find employees with max salary per department**
```java
Map<String, Optional<Employee>> maxPerDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
```

**48. Find employees sorted by name length**
```java
List<Employee> sorted = employees.stream()
    .sorted(Comparator.comparingInt(e -> e.getName().length()))
    .collect(Collectors.toList());
```

**49. Find employees with names starting with “A”**
```java
List<Employee> startsWithA = employees.stream()
    .filter(e -> e.getName().startsWith("A"))
    .collect(Collectors.toList());
```

**50. Find employees with top salary using reduce**
```java
Employee top = employees.stream()
    .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
    .orElseThrow();
```