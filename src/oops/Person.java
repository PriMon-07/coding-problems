package oops;

public class Person {
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

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
