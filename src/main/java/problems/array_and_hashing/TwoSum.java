package problems.array_and_hashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {

    /**
     * Finds two elements in the given array that sum to the given target using the
     * brute force approach. This approach has a time complexity of O(n^2) and a space
     * complexity of O(1).
     *
     * @param nums the array of integers to be searched
     * @param target the target sum of two elements
     * @return an array of two indices of elements in the array that sum to the target
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * Finds two elements in the given array that sum to the given target using the
     * sorting approach. This approach has a time complexity of O(nlog(n)) and a space
     * complexity of O(n).
     *
     * @param nums the array of integers to be searched
     * @param target the target sum of two elements
     * @return an array of two indices of elements in the array that sum to the target
     */
    public int[] twoSumSorting(int[] nums, int target) {
        int[][] num2D = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            num2D[i][0] = nums[i];
            num2D[i][1] = i;
        }

        Arrays.sort(num2D, Comparator.comparingInt(a -> a[0]));
        int i = 0, j = nums.length -1;
        while(i < j) {
            int sum = num2D[i][0] + num2D[j][0];
            if (sum == target) {
                return new int[]{Math.min(num2D[i][1], num2D[j][1]),
                        Math.max(num2D[i][1], num2D[j][1])};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }

    /**
     * Finds two elements in the given array that sum to the given target using the
     * two-pass hashmap approach. This approach has a time complexity of O(n) and a space
     * complexity of O(n).
     * <p>
     * This method first stores all elements in a hashmap, then iterates through the
     * array to find the two elements that sum to the target.
     *
     * @param nums the array of integers to be searched
     * @param target the target sum of two elements
     * @return an array of two indices of elements in the array that sum to the target
     */
    public int[] twoSumHashMapTwoPass(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff) && map.get(diff) != i) {
                return new int[]{i, map.get(diff)};
            }
        }
        return new int[0];
    }

    /**
     * Finds two elements in the given array that sum to the given target using a one-pass
     * hashmap approach. This approach has a time complexity of O(n) and a space complexity
     * of O(n).
     * <p>
     * As the array is traversed, each element is added to the hashmap. For each element,
     * it checks if the complement (target - current element) exists in the hashmap. If found,
     * it returns the indices of the two numbers.
     *
     * @param nums the array of integers to be searched
     * @param target the target sum of two elements
     * @return an array of two indices of elements in the array that sum to the target
     */
    public int[] twoSumHashMapOnePass(int[] nums, int target) {
        Map<Integer, Integer> prevMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (prevMap.containsKey(diff)) {
                return new int[]{prevMap.get(diff), i};
            }

            prevMap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum twoSum = new TwoSum();

        System.out.println("Brute Force:");
        int[] bruteForceResult = twoSum.twoSumBruteForce(nums, target);
        System.out.println(Arrays.toString(bruteForceResult));

        System.out.println("Sorting:");
        int[] sortingResult = twoSum.twoSumSorting(nums, target);
        System.out.println(Arrays.toString(sortingResult));

        System.out.println("Two Pass Hashmap:");
        int[] twoPassHashMapResult = twoSum.twoSumHashMapTwoPass(nums, target);
        System.out.println(Arrays.toString(twoPassHashMapResult));

        System.out.println("One Pass Hashmap:");
        int[] onePassHashMapResult = twoSum.twoSumHashMapOnePass(nums, target);
        System.out.println(Arrays.toString(onePassHashMapResult));
    }
}
