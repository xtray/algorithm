package _Contest.LC.W306;

public class Problem_2373_MaxMatrix {

    public int[][] largestLocal(int[][] grid) {
        int N = grid.length;
        int[][] ans = new int[N - 2][N - 2];
        for (int i = 1; i + 1 < N; i++) {
            for (int j = 1; j + 1 < N; j++) {
                int max = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        max = Math.max(max, grid[x][y]);
                    }
                }
                ans[i - 1][j - 1] = max;
            }
        }
        return ans;
    }
}
