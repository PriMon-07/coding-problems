package problems.binary_search;

/**
 * Binary Search
 * <p>
 * Binary search is a search algorithm that finds the position of a target value within a sorted array.
 * <p>
 * The algorithm works by repeatedly dividing the search interval in half. If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half. Otherwise, narrow it to the upper half. Repeatedly check until the value is found or the interval is empty.
 * <p>
 * @param nums the array of integers to be searched
 * @param target the target element to be searched
 * @return the index of the target element if found, -1 otherwise
 */
public class BinarySearch {

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Finds the index of a target element in a given array using the recursive binary search technique.
     * <p>
     * This approach has a time complexity of O(log(n)) and a space complexity of O(1).
     * <p>
     * The left and right pointers are initialized to the beginning and end of the array, and the sum of the elements at the two pointers is compared to the target.
     * If the sum is equal to the target, the index of the target element is returned.
     * If the sum is less than the target, the left pointer is moved to the right.
     * If the sum is greater than the target, the right pointer is moved to the left.
     * <p>
     * Note that the indices are 0-indexed, i.e. the first element is at index 0, the second element is at index 1, and so on.
     *
     * @param nums the array of integers to be searched
     * @param target the target element to be searched
     * @return the index of the target element if found, -1 otherwise
     */
/* <<<<<<<<<<  cdc8e751-8327-4ecd-aac6-fdf842effc1e  >>>>>>>>>>> */
    public int binarySearchRecursive(int[] nums, int target) {
        return binarySearchRecursive(0, nums.length - 1, nums, target);
    }

    private int binarySearchRecursive(int left, int right, int[] nums, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (target > nums[mid]) {
            return binarySearchRecursive(mid + 1, right, nums, target);
        } else {
            return binarySearchRecursive(left, mid - 1, nums, target);
        }

    }

    /**
     * Finds the index of a target element in a given array using the iterative binary search technique.
     * <p>
     * This approach has a time complexity of O(log(n)) and a space complexity of O(1).
     * <p>
     * The left and right pointers are initialized to the beginning and end of the array, and the sum of the elements at the two pointers is compared to the target.
     * If the sum is equal to the target, the index of the target element is returned.
     * If the sum is less than the target, the left pointer is moved to the right.
     * If the sum is greater than the target, the right pointer is moved to the left.
     * <p>
     * Note that the indices are 0-indexed, i.e. the first element is at index 0, the second element is at index 1, and so on.
     * <p>
     * @param nums the array of integers to be searched
     * @param target the target element to be searched
     * @return the index of the target element if found, -1 otherwise
     */
    public int binarySearchIterative(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Finds the lower bound of a target element in a given array using the binary search technique.
     * <p>
     * This approach has a time complexity of O(log(n)) and a space complexity of O(1).
     * <p>
     * The left and right pointers are initialized to the beginning and end of the array, and the sum of the elements at the two pointers is compared to the target.
     * If the sum is equal to the target, the index of the target element is returned.
     * If the sum is less than the target, the left pointer is moved to the right.
     * If the sum is greater than the target, the right pointer is moved to the left.
     * <p>
     * Note that the indices are 0-indexed, i.e. the first element is at index 0, the second element is at index 1, and so on.
     * <p>
     * @param nums the array of integers to be searched
     * @param target the target element to be searched
     * @return the lower bound of the target element if found, -1 otherwise
     */
    public int searchLowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int ans = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target >= nums[mid]) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return ans < nums.length && nums[ans] == target ? ans : -1;
    }

    /**
     * Finds the upper bound of a target element in a given array using the binary search technique.
     * <p>
     * This approach has a time complexity of O(log(n)) and a space complexity of O(1).
     * <p>
     * The left and right pointers are initialized to the beginning and end of the array, and the sum of the elements at the two pointers is compared to the target.
     * If the sum is equal to the target, the upper bound of the target element is returned.
     * If the sum is less than the target, the left pointer is moved to the right.
     * If the sum is greater than the target, the right pointer is moved to the left.
     * <p>
     * Note that the indices are 0-indexed, i.e. the first element is at index 0, the second element is at index 1, and so on.
     * <p>
     * @param nums the array of integers to be searched
     * @param target the target element to be searched
     * @return the upper bound of the target element if found, -1 otherwise
     */
    public int searchUpperBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int ans = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans > 0 && nums[ans - 1] == target ? ans - 1 : -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;

        int[] nums2 = {5};
        int target2 = 5;

        System.out.println(binarySearch.binarySearchRecursive(nums, target));
        System.out.println(binarySearch.binarySearchIterative(nums2, target2));
        System.out.println(binarySearch.searchLowerBound(nums, target));
        System.out.println(binarySearch.searchUpperBound(nums, target));
        System.out.println(binarySearch.searchLowerBound(nums2, target2));
        System.out.println(binarySearch.searchUpperBound(nums2, target2));
    }
}
