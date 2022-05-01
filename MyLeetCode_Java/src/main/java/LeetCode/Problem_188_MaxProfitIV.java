package LeetCode;
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/

// IMP: 非常重要的基础题!!!
// 不超过K次交易
public class Problem_188_MaxProfitIV {

    // dp[i][j]: 0~i范围上最多不超过 j 次交易所获得的的最好收益
    public int maxProfit(int k, int[] arr) {
        if (arr == null || arr.length == 0 || k < 1) {
            return 0;
        }

        int N = arr.length;
        if (k >= N / 2) { // 等同于不限制交易次数
            return noTransLimit(arr);
        }

        // (定义 买入卖出算一次交易, 在卖出时机结算交易次数)
        // 可能性划分:
        // 看最后一次交易发生的时机, 最后一次只能是卖出时机
        // 可能性 1: i 位置不参与最后一次交易
        //          dp[i][j] = dp[i-1][j]
        // 可能性 2: i 位置参与最后一次参与, 且必须是卖出时机, 可能性划分为找寻最后一次的买入时机
        //           即: 最后一次交易发生在 i 位置卖出(+ [i]), 枚举所有可能的买入位置从[i]...[0]
        //            dp[i][j] = dp[i][j-1]   -[i]   + [i]
        //            dp[i][j] = dp[i-1][j-1] -[i-1] + [i]
        //            dp[i][j] = dp[i-2][j-1] -[i-2] + [i]
        //            ...
        //            dp[i][j] = dp[0][j-1]   -[0]   + [i]
        int[][] dp = new int[N][k + 1];
        // dp[...][0]  --> 交易次数是0次, 值为0
        // dp[0][...]  --> 0~0上, 无论多少次交易, 值为0
        for (int j = 1; j <= k; j++) {
            // 从 2开始往后推, 1 作为预备数据

            // 先求 dp[1][j]
            // 可能性1: 1 位置不参与交易
            int p1 = dp[0][j];
            // 可能性2: 1 位置参与交易(卖出), 枚举所有可能的买入时机[1]...[0]
            // int p2 = dp[1][j-1] - arr[1] + arr[1];
            // int p3 = dp[0][j-1] - arr[0] + arr[1];
            // dp[1][j] = Math.max(p1, Math.max(p2,p3));
            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);
            // 从2位置开始往下计算
            for (int i = 2; i < N; i++) {
                // 一个普遍的 i,j 位置 dp[i][j]
                // 可能性1: i位置不参与交易
                p1 = dp[i - 1][j];

                int a = dp[i][j - 1] - arr[i]; // 求best的当前新加入项
                best = Math.max(a, best);
                // 可能性2: i位置参与最后一次交易(卖出)
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

    // 从 1开始往后推, 0 作为预备数据的解法, 上面是 1作为预备数据的解法
    public int maxProfit2(int k, int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        if (k >= N / 2) {
            return noTransLimit(arr);
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
            int best = -arr[0];
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
                best = Math.max(dp[i][j - 1] - arr[i], best);
                dp[i][j] = Math.max(p1, best + arr[i]);
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

}
