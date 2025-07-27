package problems.two_pointers;

import java.util.Stack;

/**
 * <p>Problem Statement: Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai). n vertical lines are drawn
 * such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two
 * lines, which, together with x-axis forms a container, such that the total area
 * of water it can contain is maximum.
 *
 * <p>Example:
 * <pre>
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * </pre>
 */
public class TrappingRainWater {

    /**
     * This function calculates the maximum area of water that can be trapped between
     * two vertical lines in the given array, using the prefix and suffix approach.
     * <p>
     * The time complexity is O(n) and the space complexity is O(n).
     * @param height the input array
     * @return the maximum area of water that can be trapped
     */
    public int trapPrefixSuffix(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = 0;
        rightMax[height.length - 1] = 0;
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
            rightMax[height.length - 1 - i] = Math.max(height[height.length - i], rightMax[height.length - i]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int sub = Math.min(leftMax[i], rightMax[i]) - height[i];
            sum = sum + (Math.max(sub, 0));
        }

        return sum;
    }

    /**
     * This function calculates the amount of water that can be trapped after raining,
     * given an array of non-negative integers where each integer represents the height of a bar.
     * It uses a stack to keep track of the indices of the bars and calculates trapped water
     * by checking the heights of the bars at the top of the stack.
     * <p>
     * The function iterates through the array and maintains a stack to find the boundaries
     * of water trapped at each index. When a bar of greater height is found, it pops the
     * stack to calculate the trapped water using the current bar as the right boundary
     * and the remaining stack elements as potential left boundaries.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param height the array of bar heights
     * @return the total amount of trapped water
     */
    public int trapStack(int[] height) {
        Stack<Integer> stack = new Stack<>();

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int mid = height[stack.pop()];
                if (!stack.isEmpty()) {
                    int right = height[i];
                    int left = height[stack.peek()];
                    int h = Math.min(right, left) - mid;
                    int w = i - stack.peek() - 1;
                    sum += h * w;
                }
            }
            stack.push(i);
        }

        return sum;
    }

    /**
     * This function calculates the amount of water that can be trapped after raining,
     * given an array of non-negative integers where each integer represents the height of a bar.
     * It uses two pointers starting from the left and right of the array to find the highest
     * bar on the left and right of each index. The area of trapped water is the minimum of
     * the left and right bar heights subtracted from the height of the current bar.
     * <p>
     * The time complexity is O(n) and the space complexity is O(1).
     * @param height the array of bar heights
     * @return the total amount of trapped water
     */
    public int trapTwoPointers(int[] height) {

        if (height.length <= 2) {
            return 0;
        }

        int i = 0;
        int j = height.length - 1;
        int lMax = height[i];
        int rMax = height[j];
        int sum = 0;
        while (i < j) {
            if (lMax <= rMax) {
                i++;
                sum = sum + Math.max(lMax - height[i], 0);
                lMax = Math.max(lMax, height[i]);
            } else {
                j--;
                sum = sum + Math.max(rMax - height[j], 0);
                rMax = Math.max(rMax, height[j]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();

        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped water for height1: " + trappingRainWater.trapPrefixSuffix(height1)); // Output: 6
        System.out.println("Trapped water for height1: " + trappingRainWater.trapStack(height1)); // Output: 6
        System.out.println("Trapped water for height1: " + trappingRainWater.trapTwoPointers(height1)); // Output: 6

        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Trapped water for height2: " + trappingRainWater.trapPrefixSuffix(height2)); // Output: 9
        System.out.println("Trapped water for height2: " + trappingRainWater.trapStack(height2)); // Output: 9
        System.out.println("Trapped water for height2: " + trappingRainWater.trapTwoPointers(height2)); // Output: 9

        int[] height3 = {2, 0, 2};
        System.out.println("Trapped water for height3: " + trappingRainWater.trapPrefixSuffix(height3)); // Output: 2
        System.out.println("Trapped water for height3: " + trappingRainWater.trapStack(height3)); // Output: 2
        System.out.println("Trapped water for height3: " + trappingRainWater.trapTwoPointers(height3)); // Output: 2

        int[] height4 = {3, 0, 0, 2, 0, 4};
        System.out.println("Trapped water for height4: " + trappingRainWater.trapPrefixSuffix(height4)); // Output: 10
        System.out.println("Trapped water for height4: " + trappingRainWater.trapStack(height4)); // Output: 10
        System.out.println("Trapped water for height4: " + trappingRainWater.trapTwoPointers(height4)); // Output: 10
    }

}
