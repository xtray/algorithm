package _Exercise;

public class Problem_121_MaxProfit {

    // 尝试在每一个时刻为卖出时机, 买入时机为当前i位置 0~i 内的最小值
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int ans = 0;
        for (int num : prices) {
            min = Math.min(min, num);
            ans = Math.max(ans, num - min);
        }
        return ans;
    }
}
