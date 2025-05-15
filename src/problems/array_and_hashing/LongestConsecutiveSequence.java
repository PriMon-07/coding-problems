package problems.array_and_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <h4>Longest Consecutive Sequence</h4>
 * <h6>Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.</h6>
 * <pre><b>Example 1:</b>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.</pre>
 * <pre><b>Example 2:</b>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9</pre>
 * <pre><b>Constraints:</b>
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9</pre>
 */
public class LongestConsecutiveSequence {

    /**
     * Finds the length of the longest consecutive elements sequence in an unsorted array of integers.
     *
     * @param nums an array of integers
     * @return the length of the longest consecutive elements sequence
     *
     * Time Complexity: O(n), where n is the number of elements in the array.
     * This is because each element is added to the set and each element is only visited once.
     * Space Complexity: O(n), due to the space required to store the elements in the set.
     */
    public int longestConsecutiveHashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int streak = 1;
                while (set.contains(num + streak)) {
                    streak++;
                }
                longestStreak = Math.max(longestStreak, streak);
            }
        }

        return longestStreak;
    }

    /**
     * Finds the length of the longest consecutive elements sequence in an unsorted array of integers, using sorting.
     *
     * @param nums an array of integers
     * @return the length of the longest consecutive elements sequence
     *
     * Time Complexity: O(n log n), due to the sorting step.
     * Space Complexity: O(1), if the input array is allowed to be modified in-place.
     * Otherwise, O(n), for the space required to store the sorted array.
     */
    public int longestConsecutiveSortingOptimized(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int maxStreak = 1;
        int streak = 1;
        int index = 1;
        int curr = nums[0];
        while (index < nums.length) {
            int next = nums[index++];
            if (curr == next) {
                continue;
            } else if (curr + 1 == next) {
                streak++;
            } else {
                maxStreak = Math.max(streak, maxStreak);
                streak = 1;
            }
            curr = next;
        }
        maxStreak = Math.max(streak, maxStreak);
        return maxStreak;
    }

    /**
     * Finds the length of the longest consecutive elements sequence in an unsorted array of integers, using a priority queue.
     *
     * @param nums an array of integers
     * @return the length of the longest consecutive elements sequence
     *
     * Time Complexity: O(n log n), due to the priority queue operations.
     * Space Complexity: O(n), for the space required to store the elements in the priority queue.
     */
    public int longestConsecutiveSorting(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num : nums) {
            queue.add(num);
        }

        if (queue.isEmpty()) {
            return 0;
        }

        int maxStreak = 1;
        int streak = 1;
        int curr = queue.poll();
        while (!queue.isEmpty()) {
            int next = queue.poll();
            if (curr == next) {
                continue;
            } else if (curr + 1 == next) {
                streak++;
            } else {
                maxStreak = Math.max(streak, maxStreak);
                streak = 1;
            }
            curr = next;
        }
        maxStreak = Math.max(streak, maxStreak);
        return maxStreak;
    }

    /**
     * Finds the length of the longest consecutive elements sequence in an unsorted array of integers using a brute force approach.
     *
     * @param nums an array of integers
     * @return the length of the longest consecutive elements sequence
     *
     * Time Complexity: O(n^2), due to the nested iteration over the array and the set.
     * Space Complexity: O(n), for the space required to store the elements in the set.
     */
    public int longestConsecutiveBruteForce(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxStreak = 0;
        for (int num : nums) {

            int streak = 0;
            int curr = num;
            while (set.contains(curr)) {
                streak++;
                curr++;
            }
            maxStreak = Math.max(streak, maxStreak);
        }

        return maxStreak;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Expected: 4, Actual: " + lcs.longestConsecutiveHashSet(nums1));
        System.out.println("Expected: 4, Actual: " + lcs.longestConsecutiveSorting(nums1));
        System.out.println("Expected: 4, Actual: " + lcs.longestConsecutiveSortingOptimized(nums1));
        System.out.println("Expected: 4, Actual: " + lcs.longestConsecutiveBruteForce(nums1));

        int[] nums2 = {0, 3, 7, 2, 5, 8};
        System.out.println("Expected: 3, Actual: " + lcs.longestConsecutiveHashSet(nums2));
        System.out.println("Expected: 3, Actual: " + lcs.longestConsecutiveSorting(nums2));
        System.out.println("Expected: 3, Actual: " + lcs.longestConsecutiveSortingOptimized(nums2));
        System.out.println("Expected: 3, Actual: " + lcs.longestConsecutiveBruteForce(nums2));
    }

}
