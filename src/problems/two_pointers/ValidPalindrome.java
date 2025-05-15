package problems.two_pointers;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    Example 1:
    Input: "A man, a plan, a canal: Panama"
    Output: true

    Example 2:
    Input: "race a car"
    Output: false
 */
public class ValidPalindrome {

    /**
     * Determines if a given string is a palindrome, considering only alphanumeric
     * characters and ignoring case differences.
     *
     * This method uses a two-pointer technique to compare characters from the
     * beginning and end of the string, moving inward. It skips non-alphanumeric
     * characters and compares the lowercase versions of the remaining characters.
     *
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(n), due to the character array conversion.
     *
     * @param s the input string to check
     * @return {@code true} if the string is a palindrome, otherwise {@code false}
     */
    public boolean isPalindrome(String s) {
        char[] letters = s.toCharArray();

        int i = 0, j = letters.length - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(letters[i])) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(letters[j])) {
                j--;
                continue;
            }

            // containLetters = true;
            if (Character.toLowerCase(letters[i]) != Character.toLowerCase(letters[j])) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "race a car";
        ValidPalindrome palindrome = new ValidPalindrome();
        System.out.println(palindrome.isPalindrome(s));
    }
}
