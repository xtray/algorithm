package LeetCode.JZOffer;

public class Problem_JZII103_CoinChange {

    // 背包
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        return process(coins, 0, amount);
    }

    // 当前来到index位置, 还剩余rest钱没有凑, 返回凑够的最小硬币数
    private int process(int[] coins, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 0 : -1;
        }
        int cnt = rest / coins[index];
        int p1 = Integer.MAX_VALUE;
        for (int i = 0; i <= cnt; i++) {
            int next = process(coins, index + 1, rest - coins[index] * i);
            if (next != -1) {
                p1 = Math.min(p1, i + next);
            }
        }
        return p1 == Integer.MAX_VALUE ? -1 : p1;
    }

    // 改DP
    // TODO: 速度5%, 需要看课进一步优化
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        // dp[index][rest]
        int[][] dp = new int[N + 1][amount + 1];
        for (int rest = 1; rest <= amount; rest++) {
            dp[N][rest] = -1;
        }
        // dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                int cnt = rest / coins[index];
                int p = Integer.MAX_VALUE;
                for (int k = 0; k <= cnt; k++) {
                    // NOTE: 注意是 coins[index] * k , 不要写错了!!
                    int next = rest >= coins[index] * k ? dp[index + 1][rest - coins[index] * k] : -1;
                    if (next != -1) {
                        p = Math.min(p, next + k);
                    }
                }
                dp[index][rest] = p == Integer.MAX_VALUE ? -1 : p;
            }
        }
        return dp[0][amount];
    }

    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        // dp[index][rest]
        int[][] dp = new int[N + 1][amount + 1];
        for (int rest = 1; rest <= amount; rest++) {
            dp[N][rest] = -1;
        }
        // dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                int cnt = rest / coins[index];
                int p = Integer.MAX_VALUE;
                for (int k = 0; k <= cnt; k++) {
                    // NOTE: 注意是 coins[index] * k , 不要写错了!!
                    // rest - coins[index] * k 不可能小于0, 上面cnt保证了
                    int next = dp[index + 1][rest - coins[index] * k];
                    if (next != -1) {
                        p = Math.min(p, next + k);
                    }
                }
                dp[index][rest] = p == Integer.MAX_VALUE ? -1 : p;
            }
        }
        return dp[0][amount];
    }

    // IMP: 优化枚举行为, 斜率优化, 比较难懂, 多看!!!
    public int coinChange4(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        // dp[index][rest]
        int[][] dp = new int[N + 1][amount + 1];
        for (int rest = 1; rest <= amount; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        // dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0 &&
                        dp[index][rest - coins[index]] != Integer.MAX_VALUE) { // 左侧至少有一个有效的
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - coins[index]] + 1);
                }
            }
        }
        return dp[0][amount] != Integer.MAX_VALUE ? dp[0][amount] : -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        // int[] coins = {2};
        // int amount = 3;
        // int[] coins = {1};
        // int amount = 0;
        var ans2 = new Problem_JZII103_CoinChange().coinChange2(coins, amount);
        System.out.println(ans2);
    }
}
