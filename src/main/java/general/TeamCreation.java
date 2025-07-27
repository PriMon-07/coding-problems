package general;

import java.util.Arrays;

public class TeamCreation {
    public static int createTeam(int N, int[] A) {
        // Step 1: Sort the array of ratings
        Arrays.sort(A);

        // Step 2: Initialize the count of questions to 0
        int questionsSolved = 0;

        // Step 3: Iterate through the sorted array and pair adjacent students
        for (int i = 0; i < N; i += 2) {
            // Pair the i-th student with the (i+1)-th student
            // Calculate the difference between their ratings
            int difference = A[i + 1] - A[i];

            // Add the difference to the total questions solved
            questionsSolved += difference;
        }

        // The result is the total number of questions solved
        return questionsSolved;
    }

    public static void main(String[] args) {
        // Example usage:
        int N = 4;
        int[] A = {1, 2, 3, 4};
        int result = createTeam(N, A);
        System.out.println(result);  // Output should 2
    }
}