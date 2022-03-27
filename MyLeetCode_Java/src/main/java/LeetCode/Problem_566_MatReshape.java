package LeetCode;

public class Problem_566_MatReshape {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return mat;
        }
        int N = mat.length;
        int M = mat[0].length;
        if (r * c != N * M) {
            return mat;
        }
        int[][] retMat = new int[r][c];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int oneDPos = i * M + j;
                int x = oneDPos / c;
                int y = oneDPos % c;
                retMat[x][y] = mat[i][j];
            }
        }
        return retMat;
    }
}
