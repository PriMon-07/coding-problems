package problems.sliding_window;

/**
 * <p>
 * Given an array of integers, find the maximum profit you can make by performing a single buy/sell operation.
 * </p>
 * <p>
 * Example 1:
 * <pre>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * </pre>
 */
public class BestTimeToBuyAndSellStock {

    /**
     * This method uses the two-pointer technique to traverse the array. At each step,
     * the algorithm checks if the price at the current index is greater than the price at the previous index. I
     * f it is, the algorithm calculates the profit and updates the max profit. If it is not,
     * the algorithm moves the first pointer to the next index and continues the loop.
     * <pre>
     * Time complexity: O(n) where n is the length of the array.
     * Space complexity: O(1) since the algorithm only uses a constant amount of space.
     * </pre>
     */
    public int maxProfitTwoPointer(int[] prices) {
        int i = 0;
        int j = 1;
        int maxProfit = 0;

        while (j < prices.length) {
            if (prices[i] < prices[j]) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            } else {
                i = j;
            }
            j++;
        }
        return maxProfit;
    }

    /**
     * This method uses dynamic programming to solve the problem. It keeps track of two values:
     * the minimum price seen so far and the maximum profit that can be made by selling the
     * stock at the current price and buying it at the minimum price.
     * <pre>
     * Time complexity: O(n) where n is the length of the array.
     * Space complexity: O(1) since the algorithm only uses a constant amount of space.
     * </pre>
     */
    public int maxProfitDP(int[] prices) {
        int maxProfit = 0;
        int minBuy = prices[0];

        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minBuy);
            minBuy = Math.min(minBuy, price);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};
        int[] prices3 = {1, 2, 3, 4, 5};
        int[] prices4 = {7, 6, 5, 4, 3, 2, 1};

        System.out.println("Two pointer approach: " + bestTimeToBuyAndSellStock.maxProfitTwoPointer(prices1));
        System.out.println("DP approach: " + bestTimeToBuyAndSellStock.maxProfitDP(prices1));
        System.out.println("Two pointer approach: " + bestTimeToBuyAndSellStock.maxProfitTwoPointer(prices2));
        System.out.println("DP approach: " + bestTimeToBuyAndSellStock.maxProfitDP(prices2));
        System.out.println("Two pointer approach: " + bestTimeToBuyAndSellStock.maxProfitTwoPointer(prices3));
        System.out.println("DP approach: " + bestTimeToBuyAndSellStock.maxProfitDP(prices3));
        System.out.println("Two pointer approach: " + bestTimeToBuyAndSellStock.maxProfitTwoPointer(prices4));
        System.out.println("DP approach: " + bestTimeToBuyAndSellStock.maxProfitDP(prices4));

    }


}
