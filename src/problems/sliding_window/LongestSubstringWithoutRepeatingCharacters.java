package problems.sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * <h4>Longest Substring Without Repeating Characters</h4>
 * <h6>Given a string s, find the length of the longest substring without repeating characters.</h6>
 * <pre><b>Example 1:</b>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.</pre>
 * <pre><b>Example 2:</b>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.</pre>
 * <pre><b>Example 3:</b>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.</pre>
 * <pre><b>Example 4:</b>
 * Input: s = ""
 * Output: 0</pre>
 * <pre><b>Constraints:</b>
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.</pre>
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Finds the length of the longest substring without repeating characters in a given string.
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     *
     * Time Complexity: O(n), where n is the length of the string. This is because each character is visited at most twice.
     * Space Complexity: O(min(n, m)), where n is the length of the string and m is the character set size.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int i = 0;
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            while (set.contains(c)) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(c);
            max = Math.max(max, j - i + 1);
        }

        return max;
    }


    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(obj.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(obj.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(obj.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(obj.lengthOfLongestSubstring("")); // 0
    }
}
