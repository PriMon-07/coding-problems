package problems.array_and_hashing;

import java.util.Arrays;

/**
 * Product of Array Except Self
 * <p>
 * Given an integer array {@code nums}, return an array {@code answer} such that {@code answer[i]} is equal to the product of all the numbers in {@code nums} except {@code nums[i]}.
 *
 * <p>The product of any prefix or suffix of {@code nums} is guaranteed to fit in a 32-bit integer.
 *
 * <p>You must write an algorithm with O(1) extra space complexity.
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <pre>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * </pre>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>2 <= nums.length <= 10^5</li>
 *   <li>-30 <= nums[i] <= 30</li>
 *   <li>The product of any prefix or suffix of {@code nums} is guaranteed to fit in a 32-bit integer.</li>
 *   <li>You must write an algorithm with O(1) extra space complexity.</li>
 * </ul>
 */
public class ProductOfArrayExceptSelf {

    /**
     * This function calculates the product of all elements in the array except the current element itself.
     * The time complexity is O(n) and the space complexity is O(1).
     * @param nums the input array
     * @return the array such that the ith element is the product of all elements in the array except the ith element.
     */
    public int[] productExceptSelfPrefixSuffixOptimal(int[] nums) {
        int[] res = new int[nums.length];

        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i-1] * res[i-1];
        }

        int post = 1;

        res[nums.length - 1] = res[nums.length - 1] * post;
        post = nums[nums.length -1] * post;
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i] * post;
            post = nums[i] * post;
        }

        return res;
    }


    /**
     * This function calculates the product of all elements in the array except the current element itself.
     * It uses two arrays, one for the prefix and one for the suffix, to keep track of the product.
     * The time complexity is O(n) and the space complexity is O(n).
     * @param nums the input array
     * @return the array such that the ith element is the product of all elements in the array except the ith element.
     */
    public int[] productExceptSelfPrefixSuffix(int[] nums) {
        int[] res = new int[nums.length];
        int[] pre = new int[nums.length];
        int[] post = new int[nums.length];

        pre[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pre[i] = nums[i-1] * pre[i-1];
        }

        post[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            post[i] = nums[i + 1] * post[i + 1];
        }

        for (int i = 0 ; i < nums.length; i++) {
            res[i] = pre[i] * post[i];
        }

        return res;
    }

    /**
     * Calculates the product of all elements in the array except the current element itself.
     * This method handles cases where there are zero or more zero elements in the array.
     * The time complexity is O(n) and the space complexity is O(1), not counting the output array.
     *
     * If there is more than one zero in the array, the entire result will be zeros.
     * If there is one zero, only the position of the zero will have the product of other non-zero elements.
     * Otherwise, each position will have the total product divided by the element at that position.
     *
     * @param nums the input array
     * @return the array such that the ith element is the product of all elements in the array except the ith element.
     */
    public int[] productExceptSelfDivision(int[] nums) {
        int prod = 1;
        int zeroCount = 0;
        for (int num : nums) {
            if (num != 0) {
                prod = prod * num;
            } else {
                zeroCount++;
            }
        }

        if (zeroCount > 1) {
            return new int[nums.length];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (zeroCount > 0) {
                res[i] = nums[i] != 0 ? 0 : prod;
            } else {
                res[i] = prod / nums[i];
            }
        }

        return res;
    }

    /**
     * This function calculates the product of all elements in the array except the current element itself using a brute-force approach.
     * The time complexity is O(n^2) and the space complexity is O(1), not counting the output array.
     * @param nums the input array
     * @return the array such that the ith element is the product of all elements in the array except the ith element.
     */
    public int[] productExceptSelfBruteForce(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    prod *= nums[j];
                }
            }
            res[i] = prod;
        }
        return res;
    }


    public static void main(String[] args) {
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};

        int[] res1 = productOfArrayExceptSelf.productExceptSelfPrefixSuffixOptimal(nums);
        System.out.println(Arrays.toString(res1)); // [24, 12, 8, 6]

        int[] res2 = productOfArrayExceptSelf.productExceptSelfPrefixSuffix(nums);
        System.out.println(Arrays.toString(res2)); // [24, 12, 8, 6]

        int[] res3 = productOfArrayExceptSelf.productExceptSelfDivision(nums);
        System.out.println(Arrays.toString(res3)); // [24, 12, 8, 6]

        int[] res4 = productOfArrayExceptSelf.productExceptSelfBruteForce(nums);
        System.out.println(Arrays.toString(res4)); // [24, 12, 8, 6]
    }

}
