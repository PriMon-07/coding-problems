package oops;

public class AgeUnder18Exception extends InvalidAgeException {
    AgeUnder18Exception(String message) {
        super(message);
    }
}
