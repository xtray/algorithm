package LeetCode;

public class Problem_123_MaxProfitIII {

    // 斜率优化
    // 用dp[0][j] 做准备的解法
    public int maxProfit0(int k, int[] arr) {
        if (arr == null || arr.length == 0 || k < 1) {
            return 0;
        }
        int N = arr.length;
        if (k >= N / 2) { // 等于没有交易次数限制
            return noTransLimit(arr);
        }
        // dp[i][j]: 0~i范围上做j次交易能获得的最大收益
        int[][] dp = new int[N][k + 1];
        // 0行-->0, 0列-->0
        for (int j = 1; j <= k; j++) {
            // NOTE: 需要提前准备dp[0][j]
            // 可能性1: 0位置不参与, 不存在
            // 可能性2: 0位置参与, 并且是卖出
            // int p2 = dp[0][j - 1] - arr[0] + arr[0];
            // dp[0][j] = 0;
            // 剥离出best
            int best = dp[0][j - 1] - arr[0];
            // dp[0][j] = 0;
            for (int i = 1; i < N; i++) {
                // int p1 = dp[i-1][j];
                best = Math.max(best, dp[i][j - 1] - arr[i]);
                dp[i][j] = Math.max(dp[i - 1][j], best + arr[i]);
            }
        }
        return dp[N - 1][k];
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
