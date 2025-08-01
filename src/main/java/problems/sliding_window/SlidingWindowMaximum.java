package problems.sliding_window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * <h1>239. Sliding Window Maximum</h1>
 *
 * <p>Given an array {@code nums} of integers and an integer {@code k}, return the maximum for each sliding window of size {@code k}.</p>
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <pre>
 * Input: nums = [1], k = 1
 * Output: [1]
 * </pre>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 <= nums.length <= 10<sup>5</sup></li>
 *   <li>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></li>
 *   <li>1 <= k <= nums.length</li>
 * </ul>
 */
public class SlidingWindowMaximum {

    /**
     * Finds the maximum value in each sliding window of size k using a max heap.
     * <p>
     * This method utilizes a max heap to efficiently track the maximum element
     * within the current window. For each element in the array, it adds the element
     * and its index to the heap. Once the window size is met, it checks if the maximum
     * element at the heap's top is within the window. If not, it removes elements
     * outside the window range. The maximum element of each valid window is then
     * recorded in the result array.
     * <p>
     * Time Complexity: O(n log k), where n is the length of the input array, due to
     * the heap operations performed for each element.
     * Space Complexity: O(k), due to the heap storing at most k elements.
     *
     * @param nums the input array of integers
     * @param k    the size of the sliding window
     * @return an array containing the maximum value of each sliding window
     */
    public int[] maxSlidingWindowHeap(int[] nums, int k) {
        // max heap
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[] result = new int[nums.length - k + 1];

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            heap.offer(new int[]{nums[i], i});
            if (i >= k - 1) {
                while (heap.peek()[1] <= i - k) {
                    heap.poll();
                }
                result[index++] = heap.peek()[0];
            }
        }

        return result;
    }

    /**
     * Finds the maximum value in each sliding window of size k using a deque.
     * <p>
     * This method utilizes a deque to efficiently track the maximum element
     * within the current window. For each element in the array, it adds the element
     * index to the end of the deque. If the new element is greater than the last
     * element, it removes the last element until the deque is empty or the last
     * element is greater than the new element. The maximum element of each valid
     * window is then recorded in the result array.
     * <p>
     * Time Complexity: O(n), where n is the length of the input array, as each
     * element is processed once.
     * Space Complexity: O(k), due to the deque storing at most k elements.
     *
     * @param nums the input array of integers
     * @param k    the size of the sliding window
     * @return an array containing the maximum value of each sliding window
     */
    public int[] maxSlidingWindowDeque(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        // monotonic decreasing
        Deque<Integer> monoDecQue = new LinkedList<>();

        int index = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            while (!monoDecQue.isEmpty() && nums[r] > nums[monoDecQue.getLast()]) {
                monoDecQue.removeLast();
            }
            monoDecQue.addLast(r);

            if (r + 1 >= k) {
                result[index++] = nums[monoDecQue.getFirst()];
                l++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindowHeap(nums, k)));
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindowDeque(nums, k)));
    }
}
