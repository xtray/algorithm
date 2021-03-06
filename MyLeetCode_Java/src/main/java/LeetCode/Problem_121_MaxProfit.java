package LeetCode;

public class Problem_121_MaxProfit {


    // 尝试每一个时刻做为卖出时机
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 尝试在每一个时刻为卖出时机, 买入时机为当前i位置 0~i 内的最小值
        // 必须在0时刻卖出
        int min = prices[0];
        int ans = 0;
        for (int p : prices) {
            min = Math.min(min, p);
            ans = Math.max(ans, p - min);
        }
        return ans;
    }
}
