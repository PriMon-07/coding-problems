package general;

import java.util.Arrays;
import java.util.Stack;

public class PathsFromZeroToNPlusOne {

    static final int MOD = 1000000007;

    // Helper function to calculate GCD
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to count the paths
    public static int countPaths(int N, int[] A) {
        int[] dp = new int[N + 2]; // DP array
        dp[N + 1] = 1; // Base case: 1 way to stay at index N+1

        // Precompute ranges where A[j] is the maximum
        int[] nextGreater = new int[N + 1];
        int[] prevGreater = new int[N + 1];
        Arrays.fill(nextGreater, N + 1);
        Arrays.fill(prevGreater, 0);

        Stack<Integer> stack = new Stack<>();

        // Compute next greater elements
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && A[stack.peek() - 1] < A[i - 1]) {
                nextGreater[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();

        // Compute previous greater elements
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && A[stack.peek() - 1] <= A[i - 1]) {
                prevGreater[stack.pop()] = i;
            }
            stack.push(i);
        }

        // DP calculation
        for (int i = N; i >= 1; i--) {
            long currentGCD = A[i - 1];
            for (int j = i; j <= N; j++) {
                currentGCD = gcd((int) currentGCD, A[j - 1]);
                if (currentGCD == 1) {
                    dp[i] = (dp[i] + dp[j + 1]) % MOD;
                }
                if (j == nextGreater[i]) break; // Stop if next greater index is reached
            }
        }

        // Total paths starting from index 0
        int totalPaths = 0;
        for (int i = 1; i <= N; i++) {
            totalPaths = (totalPaths + dp[i]) % MOD;
        }

        return totalPaths;
    }


    public static void main(String[] args) {

        int N1 = 1;
        int[] A1 = new int[]{1};

        int N2 = 1;
        int[] A2 = new int[]{2};

        int N3 = 2;
        int[] A3 = new int[]{1,2};


        System.out.println(countPaths(N1, A1));
        System.out.println(countPaths(N2, A2));
        System.out.println(countPaths(N3, A3));
    }
}
