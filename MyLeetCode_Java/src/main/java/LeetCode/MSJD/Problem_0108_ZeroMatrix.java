package LeetCode.MSJD;

public class Problem_0108_ZeroMatrix {

    public void setZeroes(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[] row = new int[N];
        int[] col = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
