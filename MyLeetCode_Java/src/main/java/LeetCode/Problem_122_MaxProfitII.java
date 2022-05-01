package LeetCode;

public class Problem_122_MaxProfitII {

    // 收集所有的波峰减波谷, 抓住每一次行情
    // 每一次都和前一个位置的相减, 和0比较大小
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
        }
        return ans;
    }
}
