package general;

public class LongestPalindromic {

    public static void longestPalindromeString(String str) {

        int maxLength = 1;
        int startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                boolean flag = true;
                for (int k = 0; k < (j - i + 1) / 2;k++) {

                    if (str.charAt(i + k) != str.charAt(j - k)) {
                        flag = false;
                        break;
                    }
                }
                if (flag && (j - i + 1) > maxLength) {
                    startIndex = i;
                    maxLength = j - i + 1;
                }
            }
        }
        System.out.println("Longest Palindrome: " + str.substring(startIndex, startIndex + maxLength));
    }

    public static void main(String[] args) {
        LongestPalindromic.longestPalindromeString("forgeeksskeegfor");
    }
}
