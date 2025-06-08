package problems.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * <h4>Longest Repeating Character Replacement</h4>
 * <h6>Given a string that contains only uppercase letters, you can perform at most k operations on any of the characters.
 * Find the length of the longest sub-string containing all repeating characters you can get after performing the above operations.</h6>
 * <pre><b>Example 1:</b>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.</pre>
 * <pre><b>Example 2:</b>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating characters, which is 4.</pre>
 */
public class LongestRepeatingCharacterReplacement {

    /**
     * Finds the length of the longest substring containing all repeating characters you can get after performing at most k operations.
     * <p>
     * Time Complexity: O(n^2), where n is the length of the string. This is because each character is visited at most n times.
     * Space Complexity: O(min(n, m)), where n is the length of the string and m is the character set size.
     * @param s the input string
     * @param k the number of operations
     * @return the length of the longest substring containing all repeating characters
     */
    public int characterReplacementBruteForce(String s, int k) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // Map to store the frequency of each character in the current window
            Map<Character, Integer> map = new HashMap<>();
            // maxFrequentChar is used to track the highest frequency of any single character within the current window.
            int maxFrequentChar = 0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + 1);
                maxFrequentChar = Math.max(maxFrequentChar, map.get(c));
                if ((j - i + 1) - maxFrequentChar <= k) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    /**
     * Finds the length of the longest substring containing all repeating characters you can get after performing at most k operations, using a sliding window approach.
     * <p>
     * Time Complexity: O(n), where n is the length of the string. This is because each character is visited at most twice.
     * Space Complexity: O(min(n, m)), where n is the length of the string and m is the character set size.
     *
     * @param s the input string
     * @param k the number of operations
     * @return the length of the longest substring containing all repeating characters
     */
    public int characterReplacementSlidingWindow(String s, int k) {
        // Map to store the frequency of each character in the current window
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;

        // maxFrequentChar is used to track the highest frequency of any single character within the current window.
        int maxFrequentChar = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxFrequentChar = Math.max(maxFrequentChar, map.get(c));

            while ((j - i + 1) - maxFrequentChar > k) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement lrcr = new LongestRepeatingCharacterReplacement();
        System.out.println(lrcr.characterReplacementBruteForce("ABABB", 2));
        System.out.println(lrcr.characterReplacementBruteForce("AABABBA", 1));
        System.out.println(lrcr.characterReplacementBruteForce("GACCBDDBAGEE", 1));

        System.out.println(lrcr.characterReplacementSlidingWindow("ABABB", 2));
        System.out.println(lrcr.characterReplacementSlidingWindow("AABABBA", 1));
        System.out.println(lrcr.characterReplacementSlidingWindow("GACCBDDBAGEE", 1));
    }
}
