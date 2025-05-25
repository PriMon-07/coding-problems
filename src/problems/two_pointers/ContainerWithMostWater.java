package problems.two_pointers;

/**
 * <p>Problem Statement: Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai). n vertical lines are drawn
 * such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two
 * lines, which, together with x-axis forms a container, such that the total area
 * of water it can contain is maximum.
 *
 * <p>Example:
 * <pre>
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * </pre>
 */
public class ContainerWithMostWater {

    /**
     * <p>Our solution for this problem is to use two pointers, one starting from
     * the beginning of the array and one from the end of the array. We keep track
     * of the maximum volume we can contain.
     *
     * <p>At each step, we have two options, moving the left pointer to the right
     * or moving the right pointer to the left. We choose to move the pointer that
     * points to the shorter line because that line is the limiting factor of the
     * volume of the container.
     *
     * <p>Time complexity: O(n)
     * <p>Space complexity: O(1)
     */
    public int maxArea(int[] height) {

        int maxVolume = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int containerHeight = Math.min(height[i], height[j]);
            int containerWidth = j - i;
            int volume = containerHeight * containerWidth;

            maxVolume = Math.max(maxVolume, volume);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxVolume;
    }


    public static void main(String[] args) {
        ContainerWithMostWater cwm = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(cwm.maxArea(height));
    }
}
