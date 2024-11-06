package general;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {12, 31, 9, 21, 5, 8};
        int k = 45;
        List<Integer> result = findSubset(arr, k);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No subset found");
        }
    }

    private static List<Integer> findSubset(int[] arr, int k) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // Zero sum can always be achieved with empty subset
        }
        print2DArray(dp);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if (!dp[n][k]) {
            return null; // No subset with sum k found
        }

        List<Integer> subset = new ArrayList<>();
        int i = n, j = k;
        while (i > 0 && j > 0) {
            if (dp[i - 1][j]) {
                i--;
            } else {
                subset.add(arr[i - 1]);
                j -= arr[i - 1];
                i--;
            }
        }

        return subset;
    }

    public static void print2DArray(boolean[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

