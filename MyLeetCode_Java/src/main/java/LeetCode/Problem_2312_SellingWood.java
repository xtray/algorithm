package LeetCode;

import java.util.Arrays;

public class Problem_2312_SellingWood {

    // 暴力递归的方法
    public long sellingWood(int m, int n, int[][] prices) {
        // 每个规格的木头块的价值数组
        long[][] values = new long[m + 1][n + 1];
        for (int[] p : prices) { // 相同规格的取最大的
            values[p[0]][p[1]] = Math.max(values[p[0]][p[1]], p[2]);
        }
        return process(m, n, values);
    }

    private long process(int m, int n, long[][] values) {
        // base case
        if (m == 0 || n == 0) { // 不能切了
            return 0;
        }
        // m> 0 && n > 0, 木块行列上还有面积可以切
        // 可能性1: 一刀也不切
        long ans = values[m][n];
        // 可能性2: 水平方向上都尝试
        for (int split = 1; split < m; split++) {
            long up = process(split, n, values);
            long down = process(m - split, n, values);
            ans = Math.max(ans, up + down);
        }
        // 可能性3: 竖直方向上都尝试
        for (int split = 1; split < n; split++) {
            long left = process(m, split, values);
            long right = process(m, n - split, values);
            ans = Math.max(ans, left + right);
        }
        return ans;
    }

    // 傻缓存
    public long sellingWood2(int m, int n, int[][] prices) {
        // 每个规格的木头块的价值数组
        long[][] values = new long[m + 1][n + 1];
        for (int[] p : prices) { // 相同规格的取最大的
            values[p[0]][p[1]] = Math.max(values[p[0]][p[1]], p[2]);
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process2(m, n, values, dp);
    }

    public static long process2(int m, int n, long[][] values, long[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        long ans = values[m][n];
        for (int split = 1; split < m; split++) {
            ans = Math.max(ans, process2(split, n, values, dp) + process2(m - split, n, values, dp));
        }
        for (int split = 1; split < n; split++) {
            ans = Math.max(ans, process2(m, split, values, dp) + process2(m, n - split, values, dp));
        }
        dp[m][n] = ans;
        return ans;
    }


    // 严格表结构的动态规划
    public long sellingWood3(int m, int n, int[][] prices) {
        // 每个规格的木头块的价值数组
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) { // 相同规格的取最大的
            dp[p[0]][p[1]] = Math.max(dp[p[0]][p[1]], p[2]);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }

    // 严格表结构的动态规划 -- 进一步优化
    public long sellingWood4(int m, int n, int[][] prices) {
        // 每个规格的木头块的价值数组
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) { // 相同规格的取最大的
            dp[p[0]][p[1]] = Math.max(dp[p[0]][p[1]], p[2]);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= i >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k <= j >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }
}