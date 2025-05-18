package problems.two_pointers;

import java.util.Arrays;

/**
 * Two Sum II - Input Array Is Sorted
 * <p>
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers
 * be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * <p>
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array
 * [index1, index2] of length 2.
 * <p>
 * The tests are generated such that there is exactly one solution. You may not use the same
 * element twice.
 * <p>
 * Your solution must use only constant extra space.
 * <p>
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore, index1 = 1, index2 = 3. We return [1, 3].
 * <p>
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 */
public class TwoSumII {

    /**
     * Finds two elements in the given array that sum to the given target using the two pointers technique.
     * <p>
     * This approach has a time complexity of O(n) and a space complexity of O(1).
     * <p>
     * The two pointers start at the beginning and end of the array, and the sum of the elements at the two pointers is compared to the target.
     * If the sum is equal to the target, the indices of the two elements are returned.
     * If the sum is less than the target, the left pointer is moved to the right.
     * If the sum is greater than the target, the right pointer is moved to the left.
     * <p>
     * Note that the indices are 1-indexed, i.e. the first element is at index 1, the second element is at index 2, and so on.
     * <p>
     * @param numbers the array of integers to be searched
     * @param target the target sum of two elements
     * @return an array of two indices of elements in the array that sum to the target
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int num = numbers[i] + numbers[j];
            if (num == target) {
                return new int[]{i+1, j+1};
            } else if (num < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSumII solution = new TwoSumII();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2,3,4}, 6)));
        System.out.println(Arrays.toString(solution.twoSum(new int[]{-1,0}, -1)));
    }

}
