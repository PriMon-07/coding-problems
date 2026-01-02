package problems.binary_search;

public class SearchInRotatedSortedArray {

    /**
     * Searches for a target element in a rotated sorted array.
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
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // Found the target
                return mid;
            } else if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                // Mid is in the left sorted portion
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left] && nums[mid] < nums[right]){
                // Mid is in the right sorted portion
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // Normal binary search case
                if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(searchInRotatedSortedArray.search(nums, target));

        int[] nums2 = {11, 5, 6, 7, 8, 9, 10};
        int target2 = 5;
        System.out.println(searchInRotatedSortedArray.search(nums2, target2));

        int[] nums3 = {4, 5, 6, 7, 8, 9, 10};
        int target3 = 4;
        System.out.println(searchInRotatedSortedArray.search(nums3, target3));

        int[] nums4 = {3, 1};
        int target4 = 1;
        System.out.println(searchInRotatedSortedArray.search(nums4, target4));
    }
}
