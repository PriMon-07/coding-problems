package oops;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Pritam Mondal");
        employee1.setAge(27);
        employee1.setEmpId(1101427);
        employee1.setAddresses(List.of(
                Address.builder().addressLine("Baruipara").city("Hooghly").pin(712306L).build(),
                Address.builder().addressLine("E-City").city("Bangalore").pin(560100L).build())
        );

        Employee employee2 = new Employee();
//        employee2.setName("Optimus Prime");
        employee2.setAge(28);
        employee2.setEmpId(1001100);

        Employee employee3 = new Employee();
        employee3.setName("Bumblebee");
        employee3.setAge(21);
        employee3.setEmpId(1102427);
        employee3.setAddresses(List.of(
                Address.builder().addressLine("Somewhere in universe").city("Cybertron").pin(100010L).build(),
                Address.builder().addressLine("San Francisco").city("New York").pin(100010L).build())
        );

        employeeList.add(null);
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        System.out.println("Employee List: " + employeeList);

        // Comparator Interface

        // Comparator Reverse Order
        Comparator<Employee> reverseNameComparator = Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.reverseOrder()));
        Comparator<Employee> reverseAgeComparator = Comparator.comparing(Employee::getAge, Comparator.reverseOrder());
        Comparator<Employee> reverseNameAgeComparator = Comparator.comparing(Employee::getName, Comparator.nullsFirst(Comparator.reverseOrder()))
                .thenComparing(Employee::getAge, Comparator.nullsFirst(Comparator.reverseOrder()));

        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>(reverseNameComparator);
        employeePriorityQueue.add(employee1);
        employeePriorityQueue.add(employee2);
        employeePriorityQueue.add(employee3);
        System.out.println("Employees in reverse order by name in priority queue: " + employeePriorityQueue);

        List<Employee> reverseListByAge = employeeList.stream().sorted(Comparator.nullsFirst(reverseAgeComparator)).toList();
        System.out.println("Employees in reverse order by age in list: " + reverseListByAge);

        List<Employee> reverseListByNameAge = employeeList.stream().sorted(Comparator.nullsFirst(reverseNameAgeComparator)).toList();
        System.out.println("Employees in reverse order by name and age in list: " + reverseListByNameAge);

        List<Employee> filteredEmpList = reverseListByAge.stream().filter(Objects::nonNull).filter(emp -> emp.getName() != null && emp.getName().startsWith("B")).toList();
        System.out.println("Filtered employee list: " + filteredEmpList);

        // Grouping
        Map<Integer, List<String>> groupingEmployeeNameSize = employeeList.stream().filter(Objects::nonNull).map(Employee::getName).filter(Objects::nonNull).collect(Collectors.groupingBy(String::length));
        System.out.println("Employees name group by name size: " + groupingEmployeeNameSize);

        List<Address> cityFilteredAddresses = employeeList.stream().filter(Objects::nonNull).filter(e -> e.getAddresses() != null).flatMap(e -> e.getAddresses().stream()).filter(p -> StringUtils.contains(p.getCity(), "oo")).toList();
        System.out.println("Addresses with filtered city: " + cityFilteredAddresses);

    }
}
