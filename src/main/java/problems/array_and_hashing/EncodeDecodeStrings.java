package problems.array_and_hashing;

import java.util.ArrayList;
import java.util.List;


/**
 * Encode and Decode Strings
 * <p>
 * Design an algorithm to encode and decode strings.
 * <p>
 * Note: You should write a class with two methods: String encode(List<String> strs) and List<String> decode(String s). encode takes a list of strings and encodes it into a single string, and decode takes the encoded string and returns the original list of strings.
 * <p>
 * Example 1:
 * Input: Dummy Input
 * Output: Dummy Output
 * Explanation: Dummy Input/Output
 * <p>
 * Example 2:
 * Input: Dummy Input
 * Output: Dummy Output
 * Explanation: Dummy Input/Output
 * <p>
 * Constraints:
 * 1 <= strs.length <= 10^5
 * 1 <= strs[i].length <= 10^5
 * sum of strs[i].length <= 10^5
 * strs[i] contains lowercase letters, digits, and/or '_' only.
 * strs[i] does not contain any spaces.
 */
public class EncodeDecodeStrings {

    /**
     * Encodes a list of strings into a single string.
     * <p>
     * The format of the encoded string is as follows:
     * <pre>
     *     length1$encodedString1length2$encodedString2...lengthN$encodedStringN
     * </pre>
     * <p>
     * @param strs a list of strings to encode
     * @return a single string containing the encoded list of strings
     */
    public String endcode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length()).append("$").append(str);
        }

        return sb.toString();
    }

    /**
     * Decodes a single string into a list of strings.
     * <p>
     * The format of the encoded string is as follows:
     * <pre>
     *     length1$encodedString1length2$encodedString2...lengthN$encodedStringN
     * </pre>
     * <p>
     * @param s the encoded string
     * @return a list of strings containing the decoded list of strings
     */
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();

        int i = 0;
        int j = 0;
        while(i < s.length()) {
            while(s.charAt(j) != '$') {
                j++;
            }
            int strLen = Integer.parseInt(s.substring(i, j));
            strs.add(s.substring(j + 1, j + 1 + strLen));
            i = j + 1 + strLen;
            j = i;
        }

        return strs;
    }


    public static void main(String[] args) {
        EncodeDecodeStrings e = new EncodeDecodeStrings();

        List<String> strs = new ArrayList<>();
        strs.add("lint");
        strs.add("code");
        strs.add("");
        strs.add("love");
        strs.add("you");

        String s = e.endcode(strs);
        System.out.println(s);

        List<String> strs2 = e.decode(s);
        System.out.println(strs2);
    }
}
