package oops;

public class Employee extends Person {
    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new AgeUnder18Exception("Employee age is not valid");
        }
        super.setAge(age);
    }

    public String caste(double assets) {
        if (assets > .5) {
            return "General";
        } else {
            return "OBC";
        }
    }
}
