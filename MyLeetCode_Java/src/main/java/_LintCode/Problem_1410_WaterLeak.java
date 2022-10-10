package _LintCode;

public class Problem_1410_WaterLeak {

    public String waterInjection(int[][] matrix, int r, int c) {
        boolean res = process(matrix, r, c, Integer.MAX_VALUE);
        return  res ? "YES" : "NO";
    }

    private boolean process(int[][] matrix, int r, int c, int pre) {
        int N = matrix.length;
        int M = matrix[0].length;
        if (r < 0 || r >= N || c < 0 || c >= M) {
            return true;
        }
        if (matrix[r][c] >= pre) {
            return false;
        }
        // 能到rc, 从rc往外扩
        int[] dir = {0, -1, 0, 1, 0};
        for (int d = 1; d < dir.length; d++) {
            int nx = r + dir[d - 1];
            int ny = c + dir[d];
            if (process(matrix, nx, ny, matrix[r][c])) {
                return true;
            }
        }
        return false;
    }
}
