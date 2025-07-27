package general;

import java.util.Scanner;

public class RecurringStrings {

    public static void recurringStrings(int n, String[] arr, String s) {
        int result = 0;

        for (String word : arr) {
            boolean canTransform = true;
            for (int i = 0; i < s.length(); i++) {
                if (!(word.charAt(i) == s.charAt(i) || word.charAt(i) + 1 == s.charAt(i))) {
                    canTransform = false;
                    break;
                }
            }
            if (canTransform) {
                result++;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of strings in the array
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline

        // Read the array of strings
        String[] arr = scanner.nextLine().split(" ");

        // Read the pattern string
        String s = scanner.nextLine();

        // Call the method to compute the result
        recurringStrings(n, arr, s);

        scanner.close();
    }
}

