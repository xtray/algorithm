package LeetCode;

public class Problem_2128_RemoveOnes {

    public boolean removeOnes(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return false;
        }
        int N = grid.length;
        for (int i = 1; i < N; i++) {
            if (!checkEqualLine(grid, i)) {
                return false;
            }
        }
        return true;
    }

    // 校验第index行是否跟0行相等, 或者取反相等
    private boolean checkEqualLine(int[][] grid, int index) {
        int M = grid[0].length;
        boolean reverse = grid[index][0] != grid[0][0];
        for (int j = 1; j < M; j++) {
            if (!reverse && grid[index][j] != grid[0][j]) {
                return false;
            }
            if (reverse && grid[index][j] != (grid[0][j] == 0 ? 1 : 0)) {
                return false;
            }
        }
        return true;
    }
}
