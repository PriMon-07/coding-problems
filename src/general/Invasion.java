package general;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Invasion {

    public static int minimumTimeToInvade(int N, int M, char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int totalEnemies = 0;
        int time = 0;

        // Initialize the queue with all cells containing 'A'
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'A') {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (grid[i][j] == 'E') {
                    totalEnemies++;
                }
            }
        }

        // Directions for up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS to spread the invasion
        while (!queue.isEmpty() && totalEnemies > 0) {
            int size = queue.size();
            time++; // Increment time for each level of BFS

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];

                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // Check bounds and if the cell is a valid 'E'
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M
                            && grid[nx][ny] == 'E' && !visited[nx][ny]) {
                        grid[nx][ny] = 'A'; // Convert 'E' to 'A'
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        totalEnemies--;
                    }
                }
            }
        }

        // If there are still enemies left, return -1
        return totalEnemies > 0 ? -1 : time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine(); // Consume the newline

        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        int result = minimumTimeToInvade(N, M, grid);
        System.out.println(result);
        /**
         * 2
         * 2
         * AE
         * EE
         *-> 2
         *
         * 3
         * 2
         * AE
         * *E
         * EE
         * -> 4
         *
         * 3
         * 2
         * AE
         * **
         * EE
         * -> -1
         */

        sc.close();
    }
}
