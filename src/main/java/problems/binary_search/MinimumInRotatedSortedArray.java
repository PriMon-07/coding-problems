package problems.binary_search;

/**
 * <h1>Minimum In Rotated Sorted Array</h1>
 *
 * <p>Given a rotated sorted array, return the minimum element in the array.</p>
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <pre>
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 * </pre>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 <= nums.length <= 5 * 10^4</li>
 *   <li>-2^31 <= nums[i] <= 2^31 - 1</li>
 *   <li>Each element is unique.</li>
 *   <li>nums is sorted and rotated in ascending order.</li>
 * </ul>
 *
 * <h3>Follow up:</h3>
 * <p>Can you solve this problem in O(log(n)) time complexity?</p>
 */
public class MinimumInRotatedSortedArray {
    /**
     * Returns the minimum element in a rotated sorted array.
     * <p>
     * The time complexity for this approach is O(log(n)), where n is the length of the array.
     * The space complexity is O(1).
     *
     * @param nums the array of integers to be searched
     * @return the minimum element in the array
     */
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            if (nums[left] < nums[right]) {
                min = Math.min(min, nums[left]);
                break;
            }

            int mid = left + (right - left) / 2;
            min = Math.min(min, nums[mid]);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        MinimumInRotatedSortedArray minimumInRotatedSortedArray = new MinimumInRotatedSortedArray();
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(minimumInRotatedSortedArray.findMin(nums));

        int[] nums2 = {2, 2, 2, 0, 1};
        System.out.println(minimumInRotatedSortedArray.findMin(nums2));
    }
}
