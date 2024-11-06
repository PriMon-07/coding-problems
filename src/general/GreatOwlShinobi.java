package general;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GreatOwlShinobi {

    static class Pair {
        int index;
        int cost;

        Pair(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static int solve(int N, int A, int B, int C, int Y, String S) {
        int MOD = 1000000007;

        // Initialize the queue for BFS
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));

        // Initialize visited array
        boolean[] visited = new boolean[N + 1];
        visited[0] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int i = current.index;
            int currentCost = current.cost;

            // If we reached the end, return the result
            if (i == N) {
                return currentCost % MOD;
            }

            // Move to i+1
            if (i + 1 <= N && !visited[i + 1]) {
                int cost = (S.charAt(i) == 'a') ? A : (S.charAt(i) == 'b') ? B : C;
                queue.add(new Pair(i + 1, (currentCost + cost) % MOD));
                visited[i + 1] = true;
            }

            // Move to i+3
            if (i + 3 <= N && !visited[i + 3]) {
                queue.add(new Pair(i + 3, (currentCost + Y) % MOD));
                visited[i + 3] = true;
            }
        }

        // If we can't reach the end, return -1 (shouldn't happen with valid input)
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input values
        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int Y = scanner.nextInt();
        String S = scanner.next();

        // Close the scanner
        scanner.close();

        // Call the solve method with the input values and print the result
        int result = solve(N, A, B, C, Y, S);
        System.out.println(result);
    }
}
