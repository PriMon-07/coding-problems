package problems.binary_search;

import java.util.Arrays;

/**
 * <h1>Koko Eating Bananas</h1>
 *
 * <p>Given an array of integers {@code piles} and an integer {@code h}, return the minimum eating speed for Koko to eat all the bananas in the given piles in the given number of hours.</p>
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <pre>
 * Input: piles = [312884470], h = 968709470
 * Output: 1
 * </pre>
 *
 * <h3>Example 3:</h3>
 * <pre>
 * Input: piles = [805306368,805306368,805306368], h = 1000000000
 * Output: 1250000000
 * </pre>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 <= piles.length <= 10^4</li>
 *   <li>1 <= piles[i] <= 10^9</li>
 *   <li>1 <= h <= 10^9</li>
 * </ul>
 *
 * <h3>Follow up:</h3>
 * <p>Can you solve this problem in O(nlog(m)) time complexity, where n is the number of piles and m is the maximum eating speed?</p>
 */
public class KokoEatingBananas {

    /**
     * Returns the minimum eating speed for Koko to eat all the bananas in the given piles in the given number of hours.
     * <p>
     * The time complexity for this approach is O(nlog(m)), where n is the number of piles and m is the maximum eating speed.
     * The space complexity is O(1).
     *
     * @param piles the array of integers representing the number of bananas in each pile
     * @param h the number of hours Koko has to eat all the bananas
     * @return the minimum eating speed for Koko to eat all the bananas in the given piles in the given number of hours
     */
    public int minEatingSpeed(int[] piles, int h) {
        int maxSpeed = Arrays.stream(piles).max().getAsInt();
        int minEatingSpeed = 1;

        int left = 1;
        int right = maxSpeed;
        while (left <= right) {
            int eatingSpeed = left + (right - left) / 2;

            long eatingHours = eatingHours(piles, eatingSpeed);
            if (eatingHours <= h) {
                minEatingSpeed = eatingSpeed;
                right = eatingSpeed - 1;
            } else {
                left = eatingSpeed + 1;
            }
        }

        return minEatingSpeed;
    }

    private long eatingHours(int[] piles, int eatingSpeed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + eatingSpeed - 1) / eatingSpeed;
        }
        return hours;
    }

    public static void main(String[] args) {
        KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles, h));

        int[] piles2 = {312884470};
        int h2 = 968709470;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles2, h2));

        int[] piles3 = {805306368,805306368,805306368};
        int h3 = 1000000000;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles3, h3));
    }
}
