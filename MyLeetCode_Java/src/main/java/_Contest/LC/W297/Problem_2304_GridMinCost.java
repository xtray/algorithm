package _Contest.LC.W297;

public class Problem_2304_GridMinCost {

    // dp[i][j]: 从(i,j)位置出发到 最后一行的最小代价
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int M = grid.length;
        int N = grid[0].length;
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            // 从 第一行 任意单元格出发
            ans = Math.min(ans, process(grid, moveCost, 0, j));
        }
        return ans;
    }

    // 从(i,j)位置出发到 最后一行的最小代价
    private int process(int[][] grid, int[][] moveCost, int i, int j) {
        int M = grid.length; // 行
        int N = grid[0].length;
        if (i == M - 1) { // 到达 最后一行 任意单元格
            return grid[i][j];
        }
        // 当前在i行, 去i+1行 的每一列
        int val = grid[i][j];
        int res = Integer.MAX_VALUE;
        for (int col = 0; col < N; col++) {
            int p = val + moveCost[val][col] + process(grid, moveCost, i + 1, col);
            res = Math.min(res, p);
        }
        return res;
    }

    // dp[i][j]: 从(i,j)位置出发到 最后一行的最小代价
    public int minPathCost1(int[][] grid, int[][] moveCost) {
        int M = grid.length;
        int N = grid[0].length;
        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int j = 0; j < N; j++) {
            dp[M - 1][j] = grid[M - 1][j];
        }
        for (int i = M - 2; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                // 当前在i行, 去i+1行 的每一列
                int val = grid[i][j];
                int res = Integer.MAX_VALUE;
                for (int col = 0; col < N; col++) {
                    int p = val + moveCost[val][col] + dp[i + 1][col];
                    res = Math.min(res, p);
                }
                dp[i][j] = res;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            // 从 第一行 任意单元格出发
            ans = Math.min(ans, dp[0][j]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] grid = {{5, 3}, {4, 0}, {2, 1}};
        int[][] moveCost = {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}}; // 17
        var ans = new Problem_2304_GridMinCost().minPathCost1(grid, moveCost);
        System.out.println(ans);
    }
}
