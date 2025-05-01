package problems.array_and_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {

    /**
     * Checks if two strings are anagrams using sorting.
     * <p>
     * This method first checks if the lengths of the strings are equal. If not, it immediately returns false.
     * It then converts both strings to character arrays and sorts them.
     * After sorting, it compares the arrays element by element.
     * If any characters differ, the function returns false, otherwise it returns true.
     * <p>
     * Time Complexity: O(n log n), where n is the length of the strings. This is due to the sorting operation.
     * Space Complexity: O(n), due to the storage of the character arrays.
     *
     * @param s the first string
     * @param t the second string
     * @return {@code true} if t is an anagram of s, otherwise {@code false}
     */
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sSort = s.toCharArray();
        char[] tSort = t.toCharArray();

        Arrays.sort(sSort);
        Arrays.sort(tSort);

        for (int i = 0; i < sSort.length; i++) {
            if (sSort[i] != tSort[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two strings are anagrams using HashMap.
     * <p>
     * This method first checks if the lengths of the strings are equal. If not, it immediately returns false.
     * It then counts the frequency of each character in both strings and stores them in separate HashMaps.
     * Finally, it compares the two HashMaps for equality.
     * <p>
     * Time Complexity: O(n), where n is the length of the strings. This is due to the HashMap operations.
     * Space Complexity: O(n), due to the storage of the HashMaps.
     *
     * @param s the first string
     * @param t the second string
     * @return {@code true} if t is an anagram of s, otherwise {@code false}
     */
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> sCount = new HashMap<>();
        Map<Character, Integer> tCount = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sCount.put(s.charAt(i), sCount.getOrDefault(s.charAt(i), 0) + 1);
            tCount.put(t.charAt(i), tCount.getOrDefault(t.charAt(i), 0) + 1);
        }

        return sCount.equals(tCount);
    }

    /**
     * Checks if two strings are anagrams using a hash table.
     * <p>
     * This method first checks if the lengths of the strings are equal. If not, it immediately returns false.
     * It then counts the frequency of each character in both strings and stores the counts in an array.
     * Finally, it checks if the counts are all zero (i.e., the counts are equal).
     * <p>
     * Time Complexity: O(n), where n is the length of the strings. This is due to the array operations.
     * Space Complexity: O(1), due to the fixed size of the array.
     *
     * @param s the first string
     * @param t the second string
     * @return {@code true} if t is an anagram of s, otherwise {@code false}
     */
    public boolean isAnagramHashTable(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charFrequency = new int[26];

        for (char c : s.toCharArray()) {
            charFrequency[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            charFrequency[c - 'a']--;
        }

        for (int f : charFrequency) {
            if (f != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagram anagram = new ValidAnagram();

        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Is anagram: " + anagram.isAnagramSorting(s1, t1));
        System.out.println("Is anagram: " + anagram.isAnagramHashMap(s1, t1));
        System.out.println("Is anagram: " + anagram.isAnagramHashTable(s1, t1));

        String s2 = "rat";
        String t2 = "car";
        System.out.println("Is anagram: " + anagram.isAnagramSorting(s2, t2));
        System.out.println("Is anagram: " + anagram.isAnagramHashMap(s2, t2));
        System.out.println("Is anagram: " + anagram.isAnagramHashTable(s2, t2));
    }
}
