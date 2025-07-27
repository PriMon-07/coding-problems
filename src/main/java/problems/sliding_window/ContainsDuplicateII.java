package problems.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Contains Duplicate II</h1>
 *
 * <p>Given an integer array {@code nums} and an integer {@code k}, return true if there are two distinct indices {@code i} and {@code j} in the array such that {@code nums[i] == nums[j]} and {@code abs(i - j) <= k}.</p>
 *
 * <h3>Example 1:</h3>
 * <p>Input: nums = [1,2,3,1], k = 3</p>
 * <p>Output: true</p>
 *
 * <h3>Example 2:</h3>
 * <p>Input: nums = [1,0,1,1], k = 1</p>
 * <p>Output: true</p>
 *
 * <h3>Example 3:</h3>
 * <p>Input: nums = [1,2,3,1,2,3], k = 2</p>
 * <p>Output: false</p>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 <= nums.length <= 105</li>
 *   <li>-109 <= nums[i] <= 109</li>
 *   <li>0 <= k <= 105</li>
 * </ul>
 */
public class ContainsDuplicateII {

    /**
     * Checks if there are two distinct indices i and j in the array such that nums[i] == nums[j]
     * and the absolute difference between i and j is at most k using a hashing approach.
     * This method provides an efficient solution with O(n) time complexity and O(n) space complexity.
     *
     * @param nums the array of integers to be checked
     * @param k the maximum allowable difference between the indices of duplicate values
     * @return true if there are such indices i and j, false otherwise
     */
    public boolean containsNearbyDuplicateHashing(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) -i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * Contains Duplicate II by using brute force approach. This approach has
     * a time complexity of O(n * k) and space complexity of O(1).
     *
     * @param nums the given array of integers
     * @param k    the given k value
     * @return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k, false otherwise
     */
    public boolean containsNearbyDuplicateBruteForce(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int jLen = Math.min(i + k + 1, nums.length);
            for (int j = i + 1; j < jLen; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        ContainsDuplicateII containsDuplicateII = new ContainsDuplicateII();
        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println(containsDuplicateII.containsNearbyDuplicateBruteForce(nums1, k1)); // Output: true
        System.out.println(containsDuplicateII.containsNearbyDuplicateHashing(nums1, k1)); // Output: true

        // Example 2
        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println(containsDuplicateII.containsNearbyDuplicateBruteForce(nums2, k2)); // Output: true
        System.out.println(containsDuplicateII.containsNearbyDuplicateHashing(nums1, k1)); // Output: true

        // Example 3
        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println(containsDuplicateII.containsNearbyDuplicateBruteForce(nums3, k3)); // Output: false
        System.out.println(containsDuplicateII.containsNearbyDuplicateHashing(nums3, k3)); // Output: false
    }
}
