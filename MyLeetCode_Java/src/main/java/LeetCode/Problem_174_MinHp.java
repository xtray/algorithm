package LeetCode;

public class Problem_174_MinHp {

    public int calculateMinimumHP(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 1;
        }
        int N = grid.length;
        int M = grid[0].length;
        // dp[i][j]: 登上[i][j]前, 从[i][j]到右下角的最小血量
        int[][] dp = new int[N][M];
        // 登上 (N-1, M-1)前的血量
        dp[N - 1][M - 1] = grid[N - 1][M - 1] <= 0 ? (-grid[N - 1][M - 1] + 1) : 1;
        // 最后一列
        for (int i = N - 2; i >= 0; i--) {
            if (grid[i][M - 1] < 0) {
                dp[i][M - 1] = -grid[i][M - 1] + dp[i + 1][M - 1];
            } else if (grid[i][M - 1] >= dp[i + 1][M - 1]) {
                dp[i][M - 1] = 1;
            } else {
                dp[i][M - 1] = dp[i + 1][M - 1] - grid[i][M - 1];
            }

        }
        // 最后一行
        for (int j = M - 2; j >= 0; j--) {
            if (grid[N - 1][j] < 0) {
                dp[N - 1][j] = -grid[N - 1][j] + dp[N - 1][j + 1];
            } else if (grid[N - 1][j] >= dp[N - 1][j + 1]) {
                dp[N - 1][j] = 1;
            } else {
                dp[N - 1][j] = dp[N - 1][j + 1] - grid[N - 1][j];
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]);
                if (grid[i][j] < 0) {
                    dp[i][j] = -grid[i][j] + need;
                } else if (grid[i][j] >= need) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = need - grid[i][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        // int[][] grid = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 }}; // 7
        int[][] grid = {{0, -3}}; // 4
        var ans = new Problem_174_MinHp().calculateMinimumHP(grid);
        System.out.println(ans);
    }
}
