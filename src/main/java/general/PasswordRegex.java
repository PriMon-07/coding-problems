package general;

import java.util.regex.Pattern;

public class PasswordRegex {


    public static void main(String[] args) {
        // Example usage:
        String pattern = "(?=[a-zA-Z])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*\\[\\]{}.]).{8,20}$";
        System.out.println(Pattern.matches(pattern, "Pritam.7"));
    }
}
