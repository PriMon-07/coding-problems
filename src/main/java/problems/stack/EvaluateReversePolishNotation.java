package problems.stack;

import java.util.Stack;

/**
 * Evaluate Reverse Polish Notation
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, \*, /.
 * Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid.
 * That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * <p>
 * Example 1:
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 14
 * <p>
 * Example 2:
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 14
 * <p>
 * Example 3:
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "31", "+", "10", "-10", "*", "/"]
 * Output: 14
 */
public class EvaluateReversePolishNotation {


    /**
     * Evaluates the value of an arithmetic expression in Reverse Polish Notation.
     * <p>
     * The function processes each token in the input array. If the token is a number,
     * it is pushed onto the stack. If the token is an operator (+, -, *, /), the top
     * two numbers are popped from the stack, the operation is performed, and the result
     * is pushed back onto the stack. At the end of the evaluation, the result is the
     * remaining number on the stack.
     * <p>
     * Note: Division between two integers should truncate toward zero.
     * The input tokens are always valid, ensuring no divide by zero errors.
     * <p>
     * Time Complexity: O(n), where n is the number of tokens.
     * Space Complexity: O(n), due to the use of a stack.
     *
     * @param tokens an array of strings representing the tokens of the RPN expression
     * @return the evaluated integer result of the RPN expression
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        int a, b;
        for (String s : tokens) {
            if (s.matches("-?\\d+")) {
                stack.push(Integer.parseInt(s));
            } else if (s.equals("+")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b + a);
            } else if (s.equals("-")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b - a);
            } else if (s.equals("*")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b * a);
            } else if (s.equals("/")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b / a);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "31", "+", "10", "-10", "*", "/"};
        int result = solution.evalRPN(tokens);
        System.out.println(result);
    }
}
