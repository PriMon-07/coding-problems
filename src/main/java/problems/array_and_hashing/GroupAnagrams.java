package problems.array_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group Anagrams
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {

    /**
     * Groups anagrams together by sorting each string and using the sorted string as a key in a HashMap.
     * <p>
     * Time Complexity: O(n*m log m), where n is the number of strings and m is the length of the longest string.
     * Space Complexity: O(n*m), due to the storage of the HashMap and the sorted strings.
     *
     * @param strs the array of strings to group
     * @return a list of lists of strings, where each sublist contains strings that are anagrams of each other
     */
    public List<List<String>> groupAnagramsSorting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] letters = str.toCharArray();

            Arrays.sort(letters);
            String key = Arrays.toString(letters);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);

        }

        return new ArrayList<>(map.values());
    }

    /**
     * Groups anagrams together by creating a frequency counter for each string and using the frequency counter as a key in a HashMap.
     * <p>
     * Time Complexity: O(n*m), where n is the number of strings and m is the length of the longest string.
     * Space Complexity: O(n*m), due to the storage of the HashMap and the frequency counters.
     *
     * @param strs the array of strings to group
     * @return a list of lists of strings, where each sublist contains strings that are anagrams of each other
     */
    public List<List<String>> groupAnagramsHashTable(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] freqCounter = new int[26];
            char[] letters = str.toCharArray();
            for (char c : letters) {
                freqCounter[c - 'a']++;
            }
            String key = Arrays.toString(freqCounter);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);

        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

        // Example 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Anagrams grouped using sorting: " + ga.groupAnagramsSorting(strs1));
        System.out.println("Anagrams grouped using hash table: " + ga.groupAnagramsHashTable(strs1));

        // Example 2
        String[] strs2 = {""};
        System.out.println("Anagrams grouped using sorting: " + ga.groupAnagramsSorting(strs2));
        System.out.println("Anagrams grouped using hash table: " + ga.groupAnagramsHashTable(strs2));

        // Example 3
        String[] strs3 = {"a"};
        System.out.println("Anagrams grouped using sorting: " + ga.groupAnagramsSorting(strs3));
        System.out.println("Anagrams grouped using hash table: " + ga.groupAnagramsHashTable(strs3));
    }
}
