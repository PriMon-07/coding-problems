package problems.linked_list;

/**
 * Problem Statement:
 * Given an array of n + 1 integers where each integer is between 1 and n (inclusive),
 * find the duplicate integer in the array.
 *
 * Example:
 * Input: [1, 2, 3, 4, 5, 1]
 * Output: 1
 * Explanation: The duplicate integer is 1.
 */
public class FindDuplicateNumber {

    /**
     * Finds the duplicate number in a given array of integers using the two pointer approach.
     * <p>
     * This approach works by iterating through the array using two pointers, one moving twice as fast as the other.
     * If an element is found to have a matching value at the fast pointer, it means that the element has been seen before and
     * is the duplicate.
     * <p>
     * Time complexity: O(n), where n is the length of the input array.
     * Space complexity: O(1), no additional memory is used.
     * @param nums the array of integers to be searched
     * @return the duplicate number if found, -1 otherwise
     */
    public int findDuplicateTwoPointer(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        int slow2 = 0;
        while (true) {
            slow = nums[slow];
            slow2 = nums[slow2];
            if (slow == slow2) {
                return slow;
            }
        }
    }

    /**
     * Finds the duplicate number in a given array of integers using the negative marking approach.
     * <p>
     * This approach works by iterating through the array and marking the corresponding index of each element as negative.
     * If an element is found to have a negative corresponding index, it means that the element has been seen before and
     * is the duplicate.
     * <p>
     * Time complexity: O(n), where n is the length of the input array.
     * Space complexity: O(1), no additional memory is used.
     * @param nums the array of integers to be searched
     * @return the duplicate number if found, -1 otherwise
     */
    public int findDuplicateNegativeMarking(int[] nums) {
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] <= 0) {
                return Math.abs(num);
            }
            nums[index] *= -1;
        }

        return -1;
    }
}
