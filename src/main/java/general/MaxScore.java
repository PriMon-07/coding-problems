package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Error
public class MaxScore {

    public static int findMaxScore(int[] A, int[] B) {
        int i = findIndexOfOne(B);
        int currentScore = calculateScore(A, B);
        int maxScore = currentScore;

        // If no 1 exists in B, return the current score
        if (i == -1) {
            return currentScore;
        }

        // Iterate through possible swap indices
        for (int j = 0; j < i; j++) {
            // Create a temporary copy of B to avoid modifying the original array
            int[] tempB = Arrays.copyOf(B, B.length);

            // Swap B[i] (1) with B[j] (0)
            tempB[i] = 0;
            tempB[j] = 1;

            // Calculate the score
            int score = calculateScore(A, tempB);

            // Update maxScore if the current score is higher
            maxScore = Math.max(maxScore, score);
        }

        return maxScore;
    }

    // Helper function to find the index of 1 in array B
    private static int findIndexOfOne(int[] B) {
        for (int i = 0; i < B.length; i++) {
            if (B[i] == 1) {
                return i;
            }
        }
        return -1; // 1 not found in B
    }

    // Helper function to calculate the score
    private static int calculateScore(int[] A, int[] B) {
        int score = 0;
        for (int i = 0; i < B.length; i++) {
            if (B[i] == 1) {
                score += A[i];
            }
        }
        return score;
    }

    // Helper function to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Function to calculate maximum score
    public static int maxScore(int N, int[] A, int[] B) {
        // Step 1: Modify A
        for (int i = 0; i < N; i++) {
            if (!isPrime(A[i])) {
                A[i] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        // Step 2: Pair A and B
//        List<int[]> paired = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            paired.add(new int[]{A[i], B[i]});
//        }


        // Sort paired array by values in A in descending order
//        paired.sort((o1, o2) -> Integer.compare(o2[0], o1[0]));

        // Step 3: Calculate the score
        int score = findMaxScore(A, B);;
//        for (int i = 0; i < N; i++) {
////            if (pair[1] == 1) {
//                score = score + (A[i] * B[i]); // Update the score with the maximum value of A[i] where B[i] == 1
////                break;
////            }
//        }

        return score;
    }

    public static void main(String[] args) {
        // Example inputs for test cases

        // Test Case 1
        int N1 = 5;
        int[] A1 = {3, 4, 3, 6, 5};
        int[] B1 = {1, 0, 1, 0, 0};
        System.out.println("Test Case 1: " + maxScore(N1, A1, B1)); // Expected output: 6

        // Test Case 2
        int N2 = 5;
        int[] A2 = {5, 1, 2, 1, 13};
        int[] B2 = {0, 0, 1, 0, 0};
        System.out.println("Test Case 2: " + maxScore(N2, A2, B2)); // Expected output: 5

        // Test Case 3
        int N3 = 7;
        int[] A3 = {3, 3, 3, 1000, 3, 3, 3};
        int[] B3 = {0, 1, 1, 1, 1, 1, 1};
        System.out.println("Test Case 3: " + maxScore(N3, A3, B3)); // Expected output: 18
    }
}
