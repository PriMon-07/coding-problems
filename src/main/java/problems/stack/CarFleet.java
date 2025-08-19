package problems.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Car Fleet
 *
 * There are n cars going to the same destination along a one-lane road. The destination is target meters away.
 * You are given two integer array position and speed, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in meters per second).
 * A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed.
 * The distance between these two cars is ignored - they are assumed to have the same position.
 * A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.
 * Return the number of car fleets that will arrive at the destination.
 *
 * Example 1:
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
 * The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Example 2:
 * Input: target = 10, position = [3,2,4], speed = [2,4,1]
 * Output: 3
 * Explanation:
 * The cars starting at 3 (speed 2) and 2 (speed 4) become a fleet, meeting each other at 4. The fleet moves at speed 2.
 * The car starting at 4 (speed 1) gets caught up to the fleet and they all meet at 4. The fleet moves at speed 1.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Example 3:
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 * Output: 1
 * Explanation:
 * The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
 * The car starting at 4 (speed 1) gets caught up to the fleet and they all meet at 4. The fleet moves at speed 1.
 * The car starting at 6 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 * Note that no other cars meet these fleets before the destination, so the answer is 1.
 */
public class CarFleet {


    /**
     * Given the target position, the positions of cars, and their speeds,
     * returns the number of car fleets that will arrive at the destination.
     *
     * Time complexity: O(n log n)
     * Space complexity: O(n)
     *
     * The time complexity is O(n log n) because we need to sort the pairs array by position in descending order.
     * The space complexity is O(n) because we need to store the pairs array and the stack.
     *
     * @param target the target position
     * @param position the positions of cars
     * @param speed the speeds of cars
     * @return the number of car fleets that will arrive at the destination
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] pairs = new int[position.length][2];
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < position.length; i++) {
            pairs[i][0] = position[i];
            pairs[i][1] = speed[i];
        }

        Arrays.sort(pairs, (a, b) -> b[0] - a[0]);

        for(int[] pair : pairs) {
            double time = (double) (target - pair[0]) / pair[1];
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
        }

        return stack.size();
    }

    public static void main(String[] args) {
        CarFleet carFleet = new CarFleet();
        int target = 10;
        int[] position = {6,8};
        int[] speed = {3,2};
        int result = carFleet.carFleet(target, position, speed);
        System.out.println(result);
    }
}
