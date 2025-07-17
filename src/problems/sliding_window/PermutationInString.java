package problems.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * <h4>Permutation in String</h4>
 * <h6>Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.</h6>
 * <pre><b>Example 1:</b>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").</pre>
 * <pre><b>Example 2:</b>
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false</pre>
 * <pre><b>Constraints:</b>
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.</pre>
 */
public class PermutationInString {

    /**
     * Returns true if s2 contains a permutation of s1, or false otherwise.
     * Uses the sliding window technique to solve the problem in O(n) time complexity and O(1) space complexity.
     * @param s1 the string to check for permutation
     * @param s2 the string to check in
     * @return true if s2 contains a permutation of s1, or false otherwise
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            frequency.put(s1.charAt(i), frequency.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int i = 0, j = 0;
        while (j < s2.length()) {
            char c = s2.charAt(j);
            if (frequency.getOrDefault(c, 0) > 0) {
                frequency.put(c, frequency.get(c) - 1);
                j++;
            } else if (i < j && frequency.getOrDefault(c, 0) == 0) {
                char d = s2.charAt(i);
                if (frequency.containsKey(d)) {
                    frequency.put(d, frequency.get(d) + 1);
                }
                i++;
            } else {
                i++;
                j++;
            }

            if (j - i == s1.length()) {
                return true;
            }
        }

        return false;
    }
}
