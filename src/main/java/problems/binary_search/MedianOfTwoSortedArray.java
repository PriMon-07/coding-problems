package problems.binary_search;


/**
 * <p>Problem Statement: Given two sorted arrays of integers A and B, find the median of the two arrays.
 * The overall run time complexity should be O(log (m + n)), where m and n are the number of elements in A and B, respectively.
 *
 * <p>Example:
 * <pre>
 * Input: A = [1, 3], B = [2]
 * Output: 2
 * Explanation: The median is 2.0, which is the average of the two elements in the array.
 * </pre>
 *
 * <p>Example 2:
 * <pre>
 * Input: A = [1, 2], B = [3, 4]
 * Output: 2.50000
 * Explanation: The median is (2 + 3)/2 = 2.5.
 * </pre>
 *
 * <p>Example 3:
 * <pre>
 * Input: A = [0, 0], B = [0, 0]
 * Output: 0.00000
 * Explanation: The median is 0.0, which is the average of the two elements in the array.
 * </pre>
 *
 * <p>Example 4:
 * <pre>
 * Input: A = [], B = [1]
 * Output: 1.00000
 * Explanation: The median is 1.0.
 * </pre>
 *
 * <p>Example 5:
 * <pre>
 * Input: A = [2], B = []
 * Output: 2.00000
 * Explanation: The median is 2.0.
 * </pre>
 *
 */
public class MedianOfTwoSortedArray {

    
    /**
     * Binary Search (Optimal)
     * <p>
     * Intuition:
     * We want the median of two sorted arrays without fully merging them.
     * Think of placing the two arrays side by side and making a cut (partition) so that:
     * - The left side of the cut contains exactly half of the total elements (or half + 1 if odd).
     * - All elements on the left side are <= all elements on the right side.
     *
     * <p>If we can find such a partition, then:
     * The median must come from the border elements around this cut:
     * - The largest element on the left side,
     * - And the smallest element on the right side.
     *
     * <p>To find this cut efficiently, we:
     * - Only binary search on the smaller array.
     * - For a chosen cut in the smaller array, the cut in the larger array is fixed (so total elements on the left is half).
     * - Check if this partition is valid: Aleft <= Bright and Bleft <= Aright
     * - If not valid: move the cut left or right (like normal binary search) until it becomes valid.
     * - Once we have a valid partition, we compute the median using the max of left side and min of right side.
     *
     * <p>Algorithm:
     * 1. Let the two sorted arrays be A and B.
     * 2. Ensure A is the smaller array (swap if needed).
     * 3. Let total = len(A) + len(B), half = (total + 1) / 2
     * 4. Use binary search on A:
     *    - l = 0, r = len(A)
     *    - While l <= r:
     *      - i = mid point in A
     *      - j = half - i (cut in B)
     *      - Define border values around the cut
     *      - Check if partition is valid
     *      - Adjust search space based on comparison
     *
     * <p>Time complexity: O(log(min(n,m))) where n and m are lengths of the input arrays
     * Space complexity: O(1)
     *
     * @param nums1 first sorted array
     * @param nums2 second sorted array
     * @return median of the two sorted arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1;
        int[] B = nums2;
        int total = A.length + B.length;
        int half = (total + 1) / 2;

        if (B.length < A.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int l = 0;
        int r = A.length;
        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;

            int Aleft = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
            int Aright = i < A.length ? A[i] : Integer.MAX_VALUE;
            int Bleft = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
            int Bright = j < B.length ? B[j] : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 != 0) {
                    return Math.max(Aleft, Bleft);
                }
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArray solution = new MedianOfTwoSortedArray();

        // Test case 1
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Test 1: " + solution.findMedianSortedArrays(nums1, nums2)); // Expected: 2.0

        // Test case 2
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Test 2: " + solution.findMedianSortedArrays(nums3, nums4)); // Expected: 2.5
    }
}
