package LeetCode;

public class Problem_64_MinPathSum {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = grid.length;
        int M = grid[0].length;
        // dp[i][j]: 从(0,0)点到(i,j)点的最小路径和
        int[][] dp = new int[N][M];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < M; j++) { // 第0行
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < N; i++) { // 第0列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[N - 1][M - 1];
    }

    // 状态压缩
    // 每个格子依赖上一行跟左侧
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = grid.length;
        int M = grid[0].length;
        // dp[i][j]: 从(0,0)点到(i,j)点的最小路径和
        int[] dp = new int[M];
        dp[0] = grid[0][0];
        for (int j = 1; j < M; j++) { // 第0行
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < N; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < M; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[M - 1];
    }
}
