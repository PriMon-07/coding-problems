package general;

import java.util.HashSet;
import java.util.Set;

public class PasswordManipulation {

    public static int countMinimumOperations(String password) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int vowelCount = 0;
        int consonantCount = 0;

        // Count vowels and consonants
        for (char c : password.toCharArray()) {
            if (vowels.contains(c)) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        // Determine the number of changes needed
        int operations = Math.abs(vowelCount - consonantCount) / 2;

        return operations;
    }

    public static void main(String[] args) {
        String password1 = "abcd";
        String password2 = "bigbangt";

        System.out.println(countMinimumOperations(password1)); // Output: 1
        System.out.println(countMinimumOperations(password2)); // Output: 2
    }
}
