package LeetCode;

import java.util.Arrays;

public class Problem_2318_DistinctSeq {

    public int distinctSequences(int n) {
        return process(0, 0, n);
    }

    // 前一个数: pre1
    // 前前一个数: pre2
    // 序列中如果有两个数相等, 要满足 abs(i-j) > 2
    // 如果当前数时i, 则需要有 pre1 != i && pre2 != i
    // 剩下的步数 rest
    private int process(int pre2, int pre1, int rest) {
        if (rest <= 0) {
            return 1;
        }
        int cnt = 0;
        // 还有步数, 尝试丢出1~6每一个点数
        for (int i = 1; i <= 6; i++) {
            // 当前点数可以选择的情况
            // 1. pre1 == 0: 当前可以丢任意数值
            // 2. pre1 != 0, 需要满足
            //   a. gcd(cur, pre1) == 1, 即相邻数最大公约数为1
            //   b. pre1 != i && pre2 != i
            if ((pre1 == 0 || gcd(i, pre1) == 1) && pre1 != i && pre2 != i) {
                cnt = (cnt + process(pre1, i, rest - 1)) % mod;
            }
        }
        return cnt;
    }

    private static final int mod = (int) 1e9 + 7;

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 记忆化搜索
    public int distinctSequences2(int n) {
        // dp[i][j][k]
        // i,j 0~6
        // k: 0~n
        int[][][] dp = new int[7][7][n + 1];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process2(0, 0, n, dp);
    }

    private int process2(int pre2, int pre1, int rest, int[][][] dp) {
        if (dp[pre1][pre2][rest] != -1) {
            return dp[pre1][pre2][rest];
        }
        if (rest <= 0) {
            dp[pre1][pre2][rest] = 1;
            return 1;
        }
        int cnt = 0;
        // 还有步数, 尝试丢出1~6每一个点数
        for (int i = 1; i <= 6; i++) {
            if ((pre1 == 0 || gcd(i, pre1) == 1) && pre1 != i && pre2 != i) {
                cnt = (cnt + process2(pre1, i, rest - 1, dp)) % mod;
            }
        }
        dp[pre1][pre2][rest] = cnt;
        return cnt;
    }
}
