package Contest.LC.DW77;

// PENDING

public class Problem_2257_NonGuardGrid_P {

    private int m;
    private int n;

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        this.m = m;
        this.n = n;

        int[][] grid = new int[m][n];
        for (int[] w : walls) {
            grid[w[0]][w[1]] = -1;
        }

        for (int[] g : guards) {
            infect(grid, g[0], g[1], walls);
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    private void infect(int[][] grid, int i, int j, int[][] walls) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1) {
            return;
        }



    }
}
