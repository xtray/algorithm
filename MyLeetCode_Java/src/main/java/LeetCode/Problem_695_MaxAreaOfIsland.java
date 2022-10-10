package LeetCode;

public class Problem_695_MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    int area = infect(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int infect(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 0;
        int sum = 1;
        sum += infect(grid,  i + 1, j);
        sum += infect(grid,  i - 1, j);
        sum += infect(grid,  i, j + 1);
        sum += infect(grid,  i, j - 1);
        return sum;
    }
}
