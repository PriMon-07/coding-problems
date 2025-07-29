package problems.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h4>Minimum Window Substring</h4>
 * <h6>Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.</h6>
 * <pre><b>Example 1:</b>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes all the characters in t.</pre>
 * <pre><b>Example 2:</b>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.</pre>
 * <pre><b>Example 3:</b>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: t must be at least 2 characters long to be contained in s, so there is no valid solution.</pre>
 * <pre><b>Constraints:</b>
 * 1 <= s.length, t.length <= 10^5
 * s and t consist of English letters.</pre>
 */
public class MinimumWindowSubstring {

    /**
     * Finds the minimum window substring in a given string s that contains all characters in another string t.
     *
     * @param s the input string
     * @param t the target string
     * @return the minimum window substring
     * <p>
     * Time Complexity: O(n), where n is the length of the string s.
     * Space Complexity: O(m), where m is the length of the string t.
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        Map<Character, Long> charFreq = t.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Character, Long> windowFreq = new HashMap<>();
        int left = 0, matchedCount = 0, minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0L) + 1);

            if (charFreq.containsKey(c) &&
                    windowFreq.get(c).equals(charFreq.get(c))) {
                matchedCount++;
            }

            while (matchedCount == charFreq.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                if (charFreq.containsKey(leftChar) &&
                        windowFreq.get(leftChar) < charFreq.get(leftChar)) {
                    matchedCount--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("cabwefgewcwaefgcf", "cae"));
    }

}
