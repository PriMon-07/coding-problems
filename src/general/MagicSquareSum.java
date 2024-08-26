package general;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class MagicSquareSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the integer input
        int N = scanner.nextInt();
        scanner.close();

        // Calculate the MagicSquareSum
        int result = magicSquareSum(N);

        // Print the result
        System.out.println(result);
    }

    public static int magicSquareSum(int N) {
        Set<Integer> factors = new HashSet<>();

        // Find all factors of N
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                factors.add(i);
                factors.add(N / i);
            }
        }

        // Calculate the sum of squares of the factors
        int sumOfSquares = 0;
        for (int factor : factors) {
            sumOfSquares += factor * factor;
        }

        return sumOfSquares;
    }
}