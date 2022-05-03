package LeetCode.Jianzhi;

public class Problem_JZ63_MaxProfit {

    /**
     * dp[i]: 第i天卖出股票获得的最大收益
     *
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length ==0) {
            return 0;
        }

        int maxProfit = 0;
        int N = prices.length;

        int minVal = prices[0];
        for(int i = 1; i< N; i++) {
            minVal = Math.min(minVal, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minVal);
        }
        return maxProfit;
    }
}
