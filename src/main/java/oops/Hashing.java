package oops;

import java.util.HashSet;
import java.util.Set;

public class Hashing {

    public static void main(String[] args) {
        Employee e1 = Employee.builder()
                .name("Pritam")
                .age(27)
                .empId(100127)
                .build();

        Employee e2 = Employee.builder()
                .name("Pritam")
                .age(27)
                .empId(100127)
                .build();

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(e1);
        employeeSet.add(e2);

        System.out.println(employeeSet);
    }
}
