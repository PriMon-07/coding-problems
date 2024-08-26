package oops;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Pritam Mondal");
        employee.setAge(27);
        employee.setEmpId(1101427);

        Employee employee2 = new Employee();
        employee2.setName("Optimus Prime");
        employee2.setAge(28);
        employee2.setEmpId(1101427);

        employeeList.add(employee);
        employeeList.add(employee2);

        Set<Employee> employeeSet = new HashSet<>(employeeList);
        System.out.println(employeeSet.size());

        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>(Comparator.comparing(Person::getName));
        employeePriorityQueue.add(employee);
        employeePriorityQueue.add(employee2);

        List<Employee> sortedEmpList = employeeList.stream().sorted(
                Comparator.comparing(Person::getName)
        ).toList();
        List<Employee> filteredEmpList = sortedEmpList.stream().filter(emp -> emp.getName().startsWith("O")).toList();

        sortedEmpList.forEach(e -> System.out.println(e.getName()));
        filteredEmpList.forEach(e -> System.out.println(e.getName()));

        Employee[] employees = sortedEmpList.toArray(Employee[]::new);
        System.out.println(employees.length);

        System.out.println(2%9);
    }
}
