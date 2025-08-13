package general;

import java.util.LinkedList;
import java.util.Queue;

public class MissingDigits {
    static class State {
        int digit;      // last digit
        int matched;    // how many chars from config matched

        State(int d, int m) {
            digit = d;
            matched = m;
        }
    }

    public static String missingDigits(String config, int x, int y) {
        int n = config.length();
        boolean[][] visited = new boolean[10][n + 1];

        // To reconstruct path
        State[][] parent = new State[10][n + 1];
        int[][] fromDigit = new int[10][n + 1]; // digit added to reach this state

        Queue<State> queue = new LinkedList<>();

        // Initialize BFS from starting digits (x and y)
        int[] startDigits = {x % 10, y % 10};
        for (int start : startDigits) {
            int matched = (config.charAt(0) - '0' == start) ? 1 : 0;
            if (!visited[start][matched]) {
                visited[start][matched] = true;
                parent[start][matched] = null; // starting point
                fromDigit[start][matched] = start;
                queue.add(new State(start, matched));
            }
        }

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.matched == n) {
                // Found the smallest configuration → reconstruct
                return reconstruct(cur, parent, fromDigit);
            }

            // Try adding x
            processNext(cur, x, config, visited, parent, fromDigit, queue);

            // Try adding y
            processNext(cur, y, config, visited, parent, fromDigit, queue);
        }

        return "-1"; // Impossible
    }

    private static void processNext(State cur, int add, String config,
                                    boolean[][] visited, State[][] parent, int[][] fromDigit,
                                    Queue<State> queue) {
        int n = config.length();
        int nextDigit = (cur.digit + add) % 10;
        int nextMatch = cur.matched;

        if (nextMatch < n && (config.charAt(nextMatch) - '0') == nextDigit) {
            nextMatch++;
        }

        if (!visited[nextDigit][nextMatch]) {
            visited[nextDigit][nextMatch] = true;
            parent[nextDigit][nextMatch] = cur;
            fromDigit[nextDigit][nextMatch] = nextDigit;
            queue.add(new State(nextDigit, nextMatch));
        }
    }

    private static String reconstruct(State end, State[][] parent, int[][] fromDigit) {
        StringBuilder sb = new StringBuilder();
        State cur = end;

        while (cur != null) {
            sb.append(fromDigit[cur.digit][cur.matched]);
            cur = parent[cur.digit][cur.matched];
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(missingDigits("324", 2, 3)); // Expected 36924
        System.out.println(missingDigits("521", 5, 5)); // Expected -1
    }
}

