package LeetCode;
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/

public class Problem_188_MaxProfit {

    // dp[i][j]: 0~i范围上最多不超过 j 次交易所获得的的最好收益
    public int maxProfit(int k, int[] arr) {
        if (arr == null || arr.length == 0 || k < 1) {
            return 0;
        }

        int N = arr.length;
        if (k >= N / 2) { // 等同于不限制交易次数
            return noTransLimit(arr);
        }

        // 可能性划分:
        // 看最后一次交易发生的时机, 最后一次只能是卖出时机
        // 可能性 1: 最后一次不参与
        // dp[i][j] = dp[i-1][j]
        // 可能性 2: 最后一次参与, 且必须是卖出时机, 那么可能性划分为找寻最后一次的买入时机
        //    a. 最后一次卖出交易发生在 i 位置, i 位置买入
        //            dp[i][j] = dp[i][j-1] -[i] + [i]
        //    b. 最后一次卖出交易发生在 i 位置, i-1 位置买入
        //            dp[i][j] = dp[i-1][j-1] -[i-1] + [i]
        int[][] dp = new int[N][k + 1];
        // dp[...][0] --->0
        // dp[0][....] --> 0
        for (int j = 1; j <= k; j++) {
            // dp[1][j] ???
            int p1 = dp[0][j];
//            int p2 = dp[1][j-1] + arr[1] - arr[1];
//            int p3 = dp[0][j-1] + arr[1] - arr[0];
//            dp[1][j] = Math.max(p1, Math.max(p2,p3));

            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);

            for (int i = 2; i < N; i++) {
                // 一个普遍的 i,j 位置 dp[i][j]
                p1 = dp[i - 1][j];
                int a = dp[i][j - 1] - arr[i];
                best = Math.max(a, best);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][k];
    }

    // 这个解法设定不一样
    // 就是来到7位置，要在这里卖出
    // 那买的位置在哪？
    // 如果允许在7位置买, 那么之前就是0…i
    // 如果不允许呢？那么之前就是0…i-1
    // 本来题目是允许的。
    // 但问题是，允许在7位置买，肯定不会是最优答案
    // 所以，说无所谓了
    public int maxProfit2(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return noTransLimit(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran <= K; tran++) {
            // dp[0][j] 做准备, 0~0不超过 j 次交易, 因为dp[0][j]=0, 所以只提取 best
            // dp[0][j] --> dp[j][0]
            // 注意这里 i,j 跟上面是反的!!
            int pre = dp[tran][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                // 可能 1: dp[tran][index - 1]
                // 可能 2:
                //   dp[tran -1][index] - prices[index] +  prices[index]
                //   //-------下面是 best
                //   dp[tran -1][index-1] - prices[index-1] +  prices[index]
                //   dp[tran -1][index-2] - prices[index-2] +  prices[index]
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);
                ans = Math.max(dp[tran][index], ans);
            }
        }
        return ans;
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

    public int maxProfit3(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return noTransLimit(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran <= K; tran++) {
            // dp[0][j] 做准备, 0~0不超过 j 次交易, 因为dp[0][j]=0, 所以只提取 best
            // dp[0][j] --> dp[j][0]
            // 注意这里 i,j 跟上面是反的!!
            int pre = dp[tran-1][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                best = Math.max(best, pre - prices[index]);
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
//                ans = Math.max(dp[tran][index], ans);
            }
        }
        return dp[N-1][K];
    }
}
