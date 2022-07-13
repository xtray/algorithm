package LeetCode;

// IMP: 背下来

public class Problem_714_MaxProfitWithFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int bestBuy = -prices[0] - fee;
        int bestSell = 0;
        for (int i = 1; i < N; i++) {
            // i位置必须买入: 之前的收入 - i位置买入价 - fee
            int curBuy = bestSell - prices[i] - fee;
            // i位置必须卖出: 之前的最优买入(收入 - 良好买入价 - fee) + i位置卖出价
            int curSell = bestBuy + prices[i];
            bestBuy = Math.max(bestBuy, curBuy);
            bestSell = Math.max(bestSell, curSell);
        }
        return bestSell;
    }
}
