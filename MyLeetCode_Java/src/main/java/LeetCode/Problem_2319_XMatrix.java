package LeetCode;

public class Problem_2319_XMatrix {

    public boolean checkXMatrix(int[][] grid) {
        int N = grid.length;
        int sum1 = 0; // 矩阵总累加和
        int sum2 = 0; // 对角线累加和
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum1 += grid[i][j];
                if (i == j || i + j == N - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    } else {
                        sum2 += grid[i][j];
                    }
                }
            }
        }
        return sum1 == sum2;
    }

    public boolean checkXMatrix1(int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || i + j == N - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
