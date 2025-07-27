package general;

import java.util.Scanner;

public class DeliveryRouteOptimization {

    public static int findBalancedIndex(long[] arr, int n) {
        long totalSum = 0;
        for (long distance : arr) {
            totalSum += distance;
        }

        long leftSum = 0;
        for (int i = 0; i < n; i++) {
            long currentDistance = arr[i];
            totalSum -= currentDistance;
            if (leftSum == totalSum) {
                return i + 1;
            }
            leftSum += currentDistance;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: number of elements in the route
        int n = scanner.nextInt();
        long[] route = new long[n];

        // Input: the route distances
        for (int i = 0; i < n; i++) {
            route[i] = scanner.nextLong();
        }

        // Function call to find the balanced index
        int balancedIndex = findBalancedIndex(route, n);

        // Output the result
        if (balancedIndex != -1) {
            System.out.println(balancedIndex);
        } else {
            System.out.println("No such index exists");
        }
    }
}
