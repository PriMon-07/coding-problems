package general;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MaxLowercaseSubsequence {

    public static int maxLowercaseSubsequence(String S) {
        Set<Character> lowercaseSet = new HashSet<>();
        int maxCount = 0;

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (Character.isLowerCase(ch)) {
                lowercaseSet.add(ch);  // Add lowercase letter to the set
            } else {
                // When an uppercase letter is encountered, check the current count of distinct lowercase letters
                maxCount = Math.max(maxCount, lowercaseSet.size());
                lowercaseSet.clear();  // Reset the set as the segment ends here
            }
        }

        // After the loop, check one last time in case the string ends with a lowercase segment
        maxCount = Math.max(maxCount, lowercaseSet.size());

        return maxCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        scanner.close();

        int result = maxLowercaseSubsequence(S);
        System.out.println(result);
    }
}
