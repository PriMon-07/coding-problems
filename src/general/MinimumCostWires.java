package general;

import java.util.Arrays;

public class MinimumCostWires {
    public static int minimumCost(int N, int[] A) {
        // Sort the array to find the median
        Arrays.sort(A);

        // Find the median
        int median = A[N / 2];

        // Calculate the total cost to make all elements equal to the median
        int totalCost = 0;
        for (int length : A) {
            totalCost += Math.abs(length - median);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Example usage
        int N = 5;
        int[] A = {2, 3, 1, 4, 5};

        int result = minimumCost(N, A);
        System.out.println(result);  // Output should be 6
    }
}
