package _Exercise;

import java.util.Arrays;

public class Problem_322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int ans = process(coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int process(int[] coins, int i, int rest) {
        if (i == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        // 1.不要i位置
        int p1 = process(coins, i + 1, rest);
        // 2.要i位置
        int p2 = Integer.MAX_VALUE;
        if (rest >= coins[i]) {
            int next = process(coins, i, rest - coins[i]);
            if (next != Integer.MAX_VALUE) {
                p2 = 1 + next;
            }
        }
        return Math.min(p1, p2);
    }

    public int coinChange1(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 0; i <= N; i++) { // NOTE: 注意i能到N
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[N][0] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - coins[i]]);
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
}
