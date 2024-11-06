package general;

import java.util.Scanner;

public class LexicographicalPath {

    public static String result(int n, int m, String[] title) {
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = title[i].toCharArray();
        }

        // Initialize DP table
        String[][] dp = new String[n][m];

        // Set up the DP table
        dp[0][0] = String.valueOf(grid[0][0]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;

                String fromTop = (i > 0) ? dp[i - 1][j] + grid[i][j] : null;
                String fromLeft = (j > 0) ? dp[i][j - 1] + grid[i][j] : null;

                if (fromTop != null && fromLeft != null) {
                    dp[i][j] = (fromTop.compareTo(fromLeft) < 0) ? fromTop : fromLeft;
                } else if (fromTop != null) {
                    dp[i][j] = fromTop;
                } else if (fromLeft != null) {
                    dp[i][j] = fromLeft;
                }
            }
        }

        // Output the result
        return dp[n - 1][m-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine();  // consume newline
        String[] inputs = new String[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = scanner.nextLine();
        }

        // Output the result
        System.out.println(result(N,M, inputs));
}
}
