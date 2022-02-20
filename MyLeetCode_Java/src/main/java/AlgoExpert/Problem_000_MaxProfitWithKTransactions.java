package AlgoExpert;

public class Problem_000_MaxProfitWithKTransactions {

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

    public static int maxProfitWithKTransactions(int[] prices, int K) {
        if (prices == null || prices.length == 0 || K < 1) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[N][K + 1];
        // dp[...][0] = 0
        // dp[0][...] = arr[0.0] 0
        for (int j = 1; j <= K; j++) {
            // dp[1][j]
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            // dp[1][j] 准备好一些枚举，接下来准备好的枚举
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                int newP = dp[i][j - 1] - prices[i];
                best = Math.max(newP, best);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }
        return dp[N - 1][K];
    }
}
