package LeetCode;

public class Problem_1219_GetMaxGold {

    private static int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] != 0) {
                    int profit = process(grid, i, j);
                    ans = Math.max(ans, profit);
                }
            }
        }
        return ans;
    }

    // 从 (i,j) 位置出发能获得的最大收益
    private int process(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        int ans = 0;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] <= 0) {
            return 0;
        }
        int tmp = grid[i][j];
        grid[i][j] = -1;
        int maxProfit = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            int profit = tmp + process(grid, x, y);
            maxProfit = Math.max(maxProfit, profit);
        }
        grid[i][j] = tmp;
        return maxProfit;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        var ans = new Problem_1219_GetMaxGold().getMaximumGold(grid);
        System.out.println(ans);
    }
}
