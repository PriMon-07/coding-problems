package general;

import java.util.Scanner;

public class SlidingWindow {

    public static String firstQuarter(int n, String[] s) {

        // Initialize the first quarter as the minimum quarter
        String minQuarter = s[0];
        int minYear = Integer.parseInt(s[0].substring(2, 4)); // Extract year part
        int minQtr = Integer.parseInt(s[0].substring(0, 1));  // Extract quarter part

        // Iterate through the remaining quarters
        for (int i = 1; i < n; i++) {
            int currentYear = Integer.parseInt(s[i].substring(2, 4)); // Extract year part
            int currentQtr = Integer.parseInt(s[i].substring(0, 1));  // Extract quarter part

            // Compare year first, then quarter
            if (currentYear < minYear || (currentYear == minYear && currentQtr < minQtr)) {
                minQuarter = s[i];
                minYear = currentYear;
                minQtr = currentQtr;
            }
        }

        return minQuarter;
    }

    public static void main(String[] args) {
        // Sample Input
        int n = 3;
        String str = "2Q22 1Q23 1Q22";

        String[] s = str.split(" ");

        // Find and print the minimum quarter
        String result = firstQuarter(n, s);
        System.out.println(result);  // Output should "1Q22"
    }
}
