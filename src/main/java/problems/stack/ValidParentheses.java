package problems.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Valid Parentheses
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 */
public class ValidParentheses {

    /**
     * Checks if a given string is valid according to the parentheses rules.
     * <p>
     * A string is valid if every open bracket has a corresponding closing bracket of the same type.
     * The brackets must also be closed in the correct order.
     * <p>
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(n), due to the use of a stack.
     * <p>
     * @param s the input string to check
     * @return {@code true} if the string is valid, otherwise {@code false}
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)) {
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        String s = "()[]{}";
        boolean result = validParentheses.isValid(s);
        System.out.println(result);
    }
}
