package general;

import java.math.BigInteger;

public class Triangle {

    public static String get(int l, int c) {
        // Edge case: First element in each row is always 1
        if (c == 0 || c == l) {
            return "1";
        }

        // Initialize the first row of the triangle
        BigInteger[] prevRow = new BigInteger[l + 1];
        prevRow[0] = BigInteger.ONE;

        for (int i = 1; i <= l; i++) {
            BigInteger[] currRow = new BigInteger[i + 1];
            currRow[0] = BigInteger.ONE;
            currRow[i] = BigInteger.ONE;

            // Fill the current row based on the previous row
            for (int j = 1; j < i; j++) {
                currRow[j] = prevRow[j - 1].add(prevRow[j]);
            }

            // Move to the next row
            prevRow = currRow;
        }

        // Return the required value as a string
        return prevRow[c].toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(get(4, 2));  // Output: 6
        System.out.println(get(5, 0));  // Output: 1
        System.out.println(get(67, 34)); // Output: 14226520737620288370
    }
}