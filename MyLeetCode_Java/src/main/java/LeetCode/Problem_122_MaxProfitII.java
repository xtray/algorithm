package LeetCode;

public class Problem_122_MaxProfitII {

    // 收集所有的波峰减波谷, 抓住每一次行情->只是为了Coding方便
    // 每一次都和前一个位置的相减, >0 就代表有利可图
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
