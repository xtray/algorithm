package LeetCode;

// IMP: 比较难理解, 多看!!

// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
public class Problem_309_MaxProfitWithCooldown {

    // buy[i] : 在0...i范围上，最后一次操作是buy动作，
    // 这最后一次操作有可能发生在i位置，也可能发生在i之前
    // buy[i]值的含义是：max{ 所有可能性[之前交易获得的最大收益 - 最后buy动作的收购价格] }

    // sell[i] :0...i范围上，最后一次操作是sell动作，这最后一次操作有可能发生在i位置，也可能发生在之前
    // sell[i]值的含义：0...i范围上，最后一次动作是sell的情况下，最好的收益
    //
    // 于是通过分析，能得到以下的转移方程：
    // 1. i位置不参与最后一次买入动作
    // 2. i位置最后一次买入动作
    // buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i])

    // 1. i位置不参与最后一次卖出动作: sell[i - 1]
    // 2. i位置最后一次卖出动作: buy[i - 1] + prices[i]
    // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i])
    // 如果i位置没有发生sell行为，那么sell[i] = sell[i-1]，这显而易见
    // 如果i位置发生了sell行为，那么我们一定要找到 {之前获得尽可能好的收益 - 最后一次的收购价格尽可能低}，==> 188的best
    // 而这正好是buy[i - 1]的含义！之前所有的"尽可能"中，最好的一个！

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        // buy[0] = - prices[0]
        // sell[0] = 0
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[N - 1];
    }

    // 状态压缩的版本
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int buy1 = Math.max(-prices[0], -prices[1]);
        int sell1 = Math.max(0, prices[1] - prices[0]);
        int sell2 = 0;
        for (int i = 2; i < N; i++) {
            int tmp = buy1;
            buy1 = Math.max(buy1, sell2 - prices[i]);
            sell2 = sell1;
            sell1 = Math.max(sell1, tmp + prices[i]);
        }
        return sell1;
    }
}
