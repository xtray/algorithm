package LeetCode;

public class Problem_807_MaxIncreaseKeepingSkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int[] row = new int[N]; // 记录每行的最大值
        int[] col = new int[M]; // 记录每列的最大值
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans += Math.min(row[i], col[j]) - grid[i][j];
            }
        }

        return ans;
    }


}
