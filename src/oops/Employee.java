package oops;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {
    private int empId;

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

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return getAge() % 10;
    }
}
