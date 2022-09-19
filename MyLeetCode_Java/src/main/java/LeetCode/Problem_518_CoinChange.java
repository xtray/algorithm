package LeetCode;

public class Problem_518_CoinChange {

    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return process(coins, 0, amount);
    }

    private int process(int[] coins, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        // 不要index位置的货币
        int p1 = process(coins, index + 1, rest);
        // 要index位置的货币
        int p2 = 0;
        if (rest >= coins[index]) {
            p2 = process(coins, index, rest - coins[index]);
        }
        return p1 + p2;
    }

    public int change1(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        dp[N][0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i+1][j];
                if(j >= coins[i]) {
                    dp[i][j] += dp[i][j-coins[i]];
                }
            }
        }
        return dp[0][amount];
    }

    // 空间压缩的动态规划
    public int change2(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int N = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if(j >= coins[i]) {
                    dp[j] += dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }
}
