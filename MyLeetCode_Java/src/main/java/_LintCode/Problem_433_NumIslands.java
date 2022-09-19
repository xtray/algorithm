package _LintCode;

public class Problem_433_NumIslands {

    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(grid[i][j]) {
                    infect(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void infect(boolean[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || !grid[i][j]) {
            return;
        }
        grid[i][j] = false;
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }
}
