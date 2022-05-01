package LeetCode;

// IMP: 重要基础题

public class Problem_63_UniquePathsWithObstacles {


    // 暴力解
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int N = obstacleGrid.length;
        int M = obstacleGrid[0].length;
        int ans = process1(obstacleGrid, N - 1, M - 1);
//        int ans = process2(obstacleGrid, 0, 0);
        return ans;

    }

    // 0,0 到 i,j 点的方法数
    private int process1(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i == 0 && j == 0) {
            return grid[i][j] == 1 ? 0 : 1;
        }

        if (i < 0 || j < 0 || grid[i][j] == 1) {
            return 0;
        }

        // 从上方来
        int p1 = process2(grid, i - 1, j);
        // 从左方来
        int p2 = process2(grid, i, j - 1);
        return p1 + p2;

    }

    // 当前位置在 i,j 到最右下角的方法数
    private int process2(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i == N - 1 && j == M - 1) {
            return grid[i][j] == 1 ? 0 : 1;
        }

        if (i == N || j == M || grid[i][j] == 1) {
            return 0;
        }

        // 选择向下走
        int p1 = process2(grid, i + 1, j);
        // 选择向左走
        int p2 = process2(grid, i, j + 1);
        return p1 + p2;
    }

    // dp[i][j]: i,j 位置到最右下角的方法数
    private int dp2(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = grid[N - 1][M - 1] == 1 ? 0 : 1;
        // 填倒数第1行
        for (int j = M - 2; j >= 0; j--) {
            dp[N - 1][j] = grid[N - 1][j] == 1 ? 0 : dp[N - 1][j + 1];
        }
        // 填倒数第1列
        for (int i = N - 2; i >= 0; i--) {
            dp[i][M - 1] = grid[i][M - 1] == 1 ? 0 : dp[i + 1][M - 1];
        }
        for (int j = M - 2; j >= 0; j--) {
            for (int i = N - 2; i >= 0; i--) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Problem_63_UniquePathsWithObstacles sl = new Problem_63_UniquePathsWithObstacles();
        int[][] grid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] grid = new int[][]{{0, 0}, {0, 1}};
        int ans = sl.uniquePathsWithObstacles1(grid);
        System.out.println(ans);
        System.out.println(sl.dp2(grid));
    }
}
