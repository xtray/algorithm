package _Contest.LC.JK;

public class Problem_JK02_CountLake {

    public int lakeCount(String[] field) {
        int N = field.length;
        int M = field[0].length();
        int cnt = 0;
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = field[i].charAt(j) == 'W' ? 1 : 0;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    infect(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void infect(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
        infect(grid, i - 1, j - 1);
        infect(grid, i + 1, j + 1);
        infect(grid, i - 1, j + 1);
        infect(grid, i + 1, j - 1);

    }
}
