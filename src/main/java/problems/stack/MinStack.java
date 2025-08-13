package problems.stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {

    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * Pushes the element val onto the stack.
     * If the stack is empty, push the element onto the stack and also onto the minStack.
     * If the stack is not empty, push the element onto the stack and push the minimum
     * of the current top of the stack and the element onto the minStack.
     * @param val the element to push onto the stack
     */
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            minStack.push(val);
        } else {
            stack.push(val);
            minStack.push(val < minStack.peek() ? val : minStack.peek());
        }
    }

    /**
     * Removes the element on top of the stack.
     * If the stack is empty, do nothing.
     * If the stack is not empty, remove the top element from the stack and
     * remove the top element from the minStack.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }

    /**
     * Gets the top element of the stack.
     * If the stack is empty, throws a runtime exception.
     * @return the top element of the stack
     * @throws RuntimeException if the stack is empty
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     * This method returns the top element of the minStack,
     * which reflects the minimum element in the main stack.
     * Assumes that the stack is not empty when this method is called.
     *
     * @return the minimum element in the stack
     */
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Output: -3
        minStack.pop();
        System.out.println(minStack.top()); // Output: 0
        System.out.println(minStack.getMin()); // Output: -2
    }
}
