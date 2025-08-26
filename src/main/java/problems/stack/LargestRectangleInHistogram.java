package problems.stack;

import java.util.Stack;

/**
 * Largest Rectangle in Histogram
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * Example 3:
 * Input: heights = [0]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 */
public class LargestRectangleInHistogram {

    /**
     * Finds the area of the largest rectangle in the given histogram.
     * <p>
     * The algorithm iterates through the array, and for each element, it pops
     * elements from the stack until the top of the stack is higher than the
     * current element. It calculates the area of the popped elements and
     * updates the maximum area if necessary. Finally, it pushes the current
     * element onto the stack. After the loop, it pops the remaining elements
     * from the stack, calculates their area, and updates the maximum area if
     * necessary.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param heights the array of heights
     * @return the maximum area of a rectangle in the histogram
     */
    public int largestRectangleArea(int[] heights) {
        // Stack of pairs of indices and heights
        // indices will store the index of the staring left bound of current rectangle
        Stack<int[]> stack = new Stack<>();
        int maxArea = 0;

        int i;
        for (i = 0; i < heights.length; i++) {

            int index = i;
            // Pop elements from the stack until the top of the stack is lower than the current element
            while (!stack.isEmpty() && heights[i] < stack.peek()[1]) {
                int[] top = stack.pop();
                index = top[0];
                int height = top[1];
                maxArea = Math.max(maxArea, (i-index) * height);
            }
            stack.push(new int[]{index, heights[i]});
        }

        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int index = pair[0];
            int height = pair[1];
            maxArea = Math.max(maxArea, (i-index) * height);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram lrih = new LargestRectangleInHistogram();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(lrih.largestRectangleArea(heights));

        heights = new int[]{2,4};
        System.out.println(lrih.largestRectangleArea(heights));

        heights = new int[]{0};
        System.out.println(lrih.largestRectangleArea(heights));

        heights = new int[]{1,2,3,4,5};
        System.out.println(lrih.largestRectangleArea(heights));
    }
}
