package oops;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Integer age;
    private String name;
    private List<Address> addresses;

    public void setAge(int age) throws InvalidAgeException {
        if (age <= 0) {
            throw new InvalidAgeException("Age cannot be 0 or negative");
        }
        this.age = age;
    }

    String caste(double assets) {
        if (assets > 1) {
            return "General";
        } else if (assets > .5) {
            return "OBC";
        } else {
            return "SC";
        }
    }
}
