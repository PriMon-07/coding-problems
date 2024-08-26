package general;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class UniqueMarbles {
    public static int uniqueMarbleCounts(int N, int A, int B) {
        Set<Integer> uniqueCounts = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        // Initialize with the starting number of marbles
        queue.add(N);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // If current count is not already in uniqueCounts, add it and proceed
            if (!uniqueCounts.contains(current)) {
                uniqueCounts.add(current);
                // If subtracting A leaves a positive number of marbles, enqueue it
                if (current - A > 0) {
                    queue.add(current - A);
                }
                // If subtracting B leaves a positive number of marbles, enqueue it
                if (current - B > 0) {
                    queue.add(current - B);
                }
            }
        }
        // Return the number of unique marble counts
        return uniqueCounts.size();
    }
    public static void main(String[] args) {
        int N = 10;  // initial number of marbles
        int A = 2;   // number of marbles to take out in operation 1
        int B = 5;   // number of marbles to take out in operation 2
        System.out.println("Number of unique positive marble counts: " + uniqueMarbleCounts(N, A, B));
    }
}
