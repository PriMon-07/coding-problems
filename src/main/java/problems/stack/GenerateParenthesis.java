package problems.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParenthesis {

    /**
     * Generates all combinations of well-formed parentheses for a given number of pairs.
     * <p>
     * Time Complexity: O(4^n / n^(3/2)), since there are 2^(2n) / (n+1) possible combinations.
     * Space Complexity: O(4^n / n^(3/2)), since the function uses a list to store all combinations.
     * <p>
     * @param n the number of pairs of parentheses
     * @return a list of all combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        StringBuilder stack = new StringBuilder();
        List<String> result = new ArrayList<>();
        backtrack(0, 0, n, stack, result);

        return result;
    }

    /**
     * Backtracks through all possible combinations of well-formed parentheses.
     * <p>
     * @param openedCount the number of opened parentheses
     * @param closedCount the number of closed parentheses
     * @param n the number of pairs of parentheses
     * @param stack the current combination of parentheses
     * @param result the list to which all valid combinations will be appended
     * <p>
     * Time Complexity: O(2^n), where n is the number of pairs of parentheses.
     * Space Complexity: O(n), where n is the number of pairs of parentheses.
     */
    private void backtrack(int openedCount, int closedCount, int n, StringBuilder stack, List<String> result) {
        if (openedCount == closedCount && openedCount == n) {
            result.add(stack.toString());
            return;
        }

        if (openedCount < n) {
            stack.append('(');
            backtrack(openedCount + 1, closedCount, n, stack, result);
            stack.deleteCharAt(stack.length() - 1);
        }

        if (closedCount < openedCount) {
            stack.append(')');
            backtrack(openedCount, closedCount + 1, n, stack, result);
            stack.deleteCharAt(stack.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> result = generateParenthesis.generateParenthesis(3);
        System.out.println(result);
    }
}
