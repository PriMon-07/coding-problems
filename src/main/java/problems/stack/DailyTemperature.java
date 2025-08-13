package problems.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have
 * to wait after the ith day to get a warmer temperature. If there is no future
 * day that is warmer, keep answer[i] == 0 instead.
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 */
public class DailyTemperature {


    /**
     * This method calculates the number of days one has to wait to experience a warmer temperature
     * for each day in the given array of temperatures. If there is no future day with a warmer
     * temperature, the result for that day will be 0.
     *
     * The algorithm uses a stack to keep track of temperatures and their respective indices as it
     * iterates over the array. When a warmer temperature is found, it calculates the difference
     * in days between the current day and the day at the top of the stack, which corresponds to
     * the result for that day.
     *
     * @param temperatures an array of integers representing daily temperatures
     * @return an array of integers where each element represents the number of days to wait for a
     *         warmer temperature, or 0 if no warmer temperature exists
     * <p>
     * Time complexity: O(n), where n is the length of the input array
     * <p>
     * Space complexity: O(n), where n is the length of the input array
     */
    public int[] dailyTemperatures(int[] temperatures) {

        // Monotonically decreasing stack
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            while (!stack.empty() && temperature > stack.peek()[0] ) {
                int pair[] = stack.pop();
                result[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{temperature, i});

        }

        return result;
    }

    public static void main(String[] args) {
        DailyTemperature dailyTemperature = new DailyTemperature();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperature.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }

}
