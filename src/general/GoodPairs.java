package general;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoodPairs {

    // Function to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false; // Numbers <= 1 are not prime
        if (n == 2) return true;  // 2 is the only even prime number
        if (n % 2 == 0) return false;  // Any even number > 2 is not prime
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Main function to count good pairs
    public static int findGoodPairs(List<Integer> arr) {
        int primeCount = 0;
        int compositeCount = 0;

        // Classify numbers as prime or composite
        for (int num : arr) {
            if (isPrime(num)) {
                primeCount++;
            } else if (num > 1) { // Composite numbers are > 1 and not prime
                compositeCount++;
            }
        }

        // The number of good pairs is primeCount * compositeCount
        return primeCount * compositeCount;
    }

    public static void main(String[] args) {
        // Input reading
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // Number of elements in the array
        List<Integer> A = new ArrayList<>();

        // Reading array elements
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }

        // Output the number of good pairs
        System.out.println(findGoodPairs(A));

        sc.close();

    }
}
