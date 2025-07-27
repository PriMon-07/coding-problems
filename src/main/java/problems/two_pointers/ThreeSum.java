package problems.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum {

    /**
     * Finds all unique triplets in the given array which gives the sum of zero.
     * <p>
     * The solution set must not contain duplicate triplets.
     * <p>
     * The time complexity of this algorithm is O(n^2) and the space complexity is O(n).
     * <p>
     * @param nums the given array
     * @return a list of lists, where each sublist contains three elements that sum to zero
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList= new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int result = nums[i] + nums[j] + nums[k];
                if (result == 0) {
                    resultList.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j-1]) {
                        j++;
                    }
                } else if (result < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return resultList;
    }


    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum.threeSum(nums);
        System.out.println(result);
    }
}
