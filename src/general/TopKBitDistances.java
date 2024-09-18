package general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKBitDistances {
    // Helper function to calculate the maximum bit distance
    public static int getBitDistance(int x) {
        String binary = Integer.toBinaryString(x);
        List<Integer> positions = new ArrayList<>();
        System.out.println("Binary of " + x + " -> " + binary);

        // Find the positions of set bits
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                positions.add(i);
            }
        }

        // If there's only one set bit, return -1
        if (positions.size() < 2) {
            return -1;
        }

        // Calculate the maximum distance between consecutive set bits
        int maxDistance = 0;
        for (int i = 1; i < positions.size(); i++) {
            maxDistance = Math.max(maxDistance, positions.get(i) - positions.get(i - 1) - 1);
        }

        System.out.println("Max Distance: " + maxDistance);

        return maxDistance;
    }

    // Main function to find the top k integers based on bit distances
    public static List<Integer> getTopKBitDistances(int[] numbers, int k) {
        // Create a list to store pairs of (bitDistance, number)
//        List<int[]> bitDistances = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(
                Collections.reverseOrder(Map.Entry.<Integer, Integer>comparingByValue())
                        .thenComparing(Collections.reverseOrder(Map.Entry.comparingByKey()))
        );

        // Calculate bit distance for each number
        for (int number : numbers) {
            int bitDistance = getBitDistance(number);
//            bitDistances.add(new int[]{bitDistance, number});
            priorityQueue.add(Map.entry(number, bitDistance));
        }

        // Sort by bit distance in descending order, and by number value in case of ties
//        Collections.sort(bitDistances, (a, b) -> {
//            if (b[0] != a[0]) {
//                return b[0] - a[0]; // Sort by bit distance
//            } else {
//                return b[1] - a[1]; // If bit distance is same, sort by number
//            }
//        });

        // Extract the top k numbers
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < k; i++) {
//            result.add(bitDistances.get(i)[1]);
//        }

        System.out.println(priorityQueue);
        return priorityQueue.stream().limit(k).map(Map.Entry::getKey).toList();
    }

    public static void main(String[] args) {
        // Example test case 1
        int[] numbers1 = {3, 5, 8};
        int k1 = 1;
        System.out.println(getTopKBitDistances(numbers1, k1)); // Output: [5]

        // Example test case 2
        int[] numbers2 = {16, 13, 5, 18};
        int k2 = 3;
        System.out.println(getTopKBitDistances(numbers2, k2)); // Output: [18, 13, 16}
    }
}
