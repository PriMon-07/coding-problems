package problems.array_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <h1>Top K Frequent Elements</h1>
 *
 * <p>Given an integer array {@code nums} and an integer {@code k}, return the {@code k} most frequent elements. You may return the answer in any order.</p>
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
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
 *   <li>1 <= nums.length <= 10^5</li>
 *   <li>-10^4 <= nums[i] <= 10^4</li>
 *   <li>k is in the range [1, the number of unique elements in the array].</li>
 *   <li>It is guaranteed that the answer is unique.</li>
 * </ul>
 *
 * <h3>Follow up:</h3>
 * <p>Your algorithm's time complexity must be better than O(n log n), where n is the array's size.</p>
 */
public class TopKFrequentElements {


    /**
     * Top K Frequent Elements by sorting.
     * <p>
     * Time complexity: O(n log n), where n is the length of the input array.
     * Space complexity: O(n).
     *
     * @param nums the input array
     * @param k    the number of top frequent elements to return
     * @return the top k frequent elements
     */
    public int[] topKFrequentSorting(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(k)
                .mapToInt(Map.Entry::getKey).toArray();
    }

    /**
     * Top K Frequent Elements by using a min heap.
     * <p>
     * Time complexity: O(n log k), where n is the length of the input array.
     * Space complexity: O(n + k).
     *
     * @param nums the input array
     * @param k    the number of top frequent elements to return
     * @return the top k frequent elements
     */
    public int[] topKFrequentMinHeap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(Map.entry(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    /**
     * Top K Frequent Elements using bucket sort.
     * <p>
     * This method finds the top k frequent elements in the given array using a bucket sort approach.
     * It first counts the frequency of each element using a HashMap, then organizes the elements into
     * buckets based on their frequency. Finally, it collects the top k frequent elements from the buckets.
     * <p>
     * Time complexity: O(n), where n is the length of the input array, as both counting frequencies and
     * collecting results are linear operations.
     * Space complexity: O(n), as additional space is used for the frequency map and the bucket list.
     *
     * @param nums the input array
     * @param k    the number of top frequent elements to return
     * @return the top k frequent elements
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> freq = new ArrayList<>();

        for (int i = 0; i <= nums.length; i++) {
            freq.add(new ArrayList<>());
        }

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            freq.get(e.getValue()).add(e.getKey());
        }

        int[] result = new int[k];
        int c = 0;
        int index = nums.length;
        while (index != 0) {
            List<Integer> bucket = freq.get(index);
            for (Integer n : bucket) {
                result[c++] = n;
                if (k == c) {
                    return result;
                }
            }
            index--;
        }
        return result;
    }


    public static void main(String[] args) {
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentSorting(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentMinHeap(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentBucketSort(nums, k)));

        nums = new int[] {5, 2, 5, 3, 5, 3, 1};
        k = 2;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentSorting(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentMinHeap(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentBucketSort(nums, k)));
    }
}
