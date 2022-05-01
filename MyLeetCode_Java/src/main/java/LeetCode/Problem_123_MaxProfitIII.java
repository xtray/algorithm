package LeetCode;

public class Problem_123_MaxProfitIII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int ans = 0;

        // 0~0 范围上 做一次交易的最好收益
        int doneOnceMax = 0;
        // 相当于bestBuy 0 - prices[0];
        int doneOnceMinusBuyMax = -prices[0];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int k = 2;
        if (k >= N / 2) {
            return noTransLimit(prices);
        }
        int[][] dp = new int[N][k + 1];
        int ans = 0;
        for (int j = 1; j <= k; j++) {
            // 从 1开始往后推, 0 作为预备数据
            // dp[0][j]: 0~0不超过 j 次交易的最好收益
            // 可能性1: 0 不参与最后一次交易, 不存在
            // 可能性2: 0 参与最后一次交易(且必须是卖出)
            //         dp[0][j] = dp[0][j-1] - [0] + [0]
            //   提取 best = dp[0][j-1] - [0]
            // dp[0][j]: 不用求, 都是0
            int best = -prices[0];
            for (int i = 1; i < N; i++) { // 从 1开始往后推
                // dp[i][j]
                // 可能 1: i 不参与最后一次交易 dp[i][j] =  dp[i-1][j]
                // 可能 2: i 参与最后一次交易(卖出), 枚举所有的买入时机: [i]...[0]
                //         dp[i][j] = dp[i][j-1]   - [i]   + [i]
                //         dp[i][j] = dp[i-1][j-1] - [i-1] + [i]
                //         dp[i][j] = dp[i-2][j-1] - [i-2] + [i]
                //         ...
                //         dp[i][j] = dp[0][j-1]   - [0]   + [i]
                int p1 = dp[i - 1][j];
                best = Math.max(dp[i][j - 1] - prices[i], best);
                dp[i][j] = Math.max(p1, best + prices[i]);
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
        // return dp[N-1][K];
    }

    private int noTransLimit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }


    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // 0, 1, 2
        int[][] dp = new int[prices.length][3];
        for (int j = 1; j < 3; j++) {
            int best = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][j] = dp[i - 1][j];
                best = Math.max(best, dp[i][j-1] - prices[i]);
                dp[i][j] = Math.max(best + prices[i], dp[i][j]);
            }
        }
        return dp[prices.length - 1][2];
    }

}
