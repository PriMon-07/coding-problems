package array_and_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <h4>Contains Duplicate</h4>
 * <h6> Given an integer array {@code nums}, return {@code true} if any value appears at least twice in the array,
 * and return {@code false} if every element is distinct.</h6>
 * <pre><b>Example 1:</b>
 * Input: nums = [1,2,3,1]
 * Output: true
 * Explanation:
 * The element 1 occurs at the indices 0 and 3. </pre>
 * <pre><b>Example 2:</b>
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation:
 * All elements are distinct. </pre>
 * <pre><b>Example 3:</b>
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true</pre>
 * <pre><b>Constraints:</b>
 * {@code 1 <= nums.length <= 10^5}
 * {@code -10^9 <= nums[i] <= 10^9}</pre>
 */

public class ContainsDuplicates {

    /**
     * [Naive Approach] By using two nested loops – O(n ^ 2) Time and O(1) Space
     * @param nums
     * @return {@code true} if any values appears at least twice
     */
    public boolean solutionUsingNestedLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * [Hashing Approach (Efficient)] By using HashSet – O(n) Time and O(n) Space
     * @param nums
     * @return {@code true} if any values appears at least twice
     */
    public boolean solutionUsingHashing(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * [Sorting Approach] Applying sorting on {@code nums} – O(n log n) Time and O(1) Space
     * @param nums
     * @return {@code true} if any values appears at least twice
     */
    public boolean solutionUsingSorting(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1]) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};

        ContainsDuplicates containsDuplicates = new ContainsDuplicates();

        System.out.println("Naive approach: " + containsDuplicates.solutionUsingNestedLoop(nums1));
        System.out.println("Hashing approach: " + containsDuplicates.solutionUsingHashing(nums1));
        System.out.println("Sorting approach: " + containsDuplicates.solutionUsingSorting(nums1));

        System.out.println("Naive approach: " + containsDuplicates.solutionUsingNestedLoop(nums2));
        System.out.println("Hashing approach: " + containsDuplicates.solutionUsingHashing(nums2));
        System.out.println("Sorting approach: " + containsDuplicates.solutionUsingSorting(nums2));
    }
}
