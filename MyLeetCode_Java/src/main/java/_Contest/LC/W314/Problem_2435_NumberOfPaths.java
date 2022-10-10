package _Contest.LC.W314;

import java.util.Arrays;

public class Problem_2435_NumberOfPaths {

    // dp[i][j][r]: (0,0)到(i,j)位置累加和余数为r的方法数
    public int numberOfPaths(int[][] grid, int k) {
        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N][M][k];
        int sum = grid[0][0];
        dp[0][0][sum % k] = 1;
        for (int i = 1; i < N; i++) { // 0列
            sum += grid[i][0];
            dp[i][0][sum % k] = 1;
        }
        sum = grid[0][0];
        for (int j = 1; j < M; j++) { // 0行
            sum += grid[0][j];
            dp[0][j][sum % k] = 1;
        }
        int mod = (int) 1e9 + 7;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                for (int r = 0; r < k; r++) {
                    dp[i][j][(r + grid[i][j]) % k] = (dp[i - 1][j][r] + dp[i][j - 1][r]) % mod;
                }
            }
        }
        return dp[N - 1][M - 1][0];
    }

    // 简化代码, 补充0行,0列(原数组被填充到1,1开始)
    public int numberOfPaths1(int[][] grid, int k) {
        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N + 1][M + 1][k];
        dp[1][1][grid[0][0] % k] = 1;
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == 1 && j == 1) continue;
                for (int r = 0; r < k; r++) {
                    dp[i][j][(r + grid[i - 1][j - 1]) % k] = (dp[i - 1][j][r] + dp[i][j - 1][r]) % mod;
                }
            }
        }
        return dp[N][M][0];
    }
}
