package general;

import java.util.Collections;
import java.util.List;

public class NetworkOptimization {
    public static long getMinDistance(List<Integer> center, List<Integer> destination) {
        // Sort both lists
        Collections.sort(center);
        Collections.sort(destination);


        long minLag = 0;

        // Calculate the sum of absolute differences
        for (int i = 0; i < center.size(); i++) {
            minLag += Math.abs(center.get(i) - destination.get(i));
        }

        return minLag;
    }

    public static void main(String[] args) {
        // Sample Input 1
        List<Integer> center1 = List.of(3, 1, 6, 8, 9);
        List<Integer> destination1 = List.of(2, 3, 1, 7, 9);
        System.out.println("Minimum lag (Sample Input 1): " + getMinDistance(center1, destination1)); // Output should be 5

        // Sample Input 2
        List<Integer> center2 = List.of(4, 6);
        List<Integer> destination2 = List.of(5, 5);
        System.out.println("Minimum lag (Sample Input 2): " + getMinDistance(center2, destination2)); // Output should be 2
    }
}
