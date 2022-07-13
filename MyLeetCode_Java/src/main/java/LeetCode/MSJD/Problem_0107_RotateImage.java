package LeetCode.MSJD;

// IMP: 图像旋转, 重要基础算法原型题

public class Problem_0107_RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int N = matrix.length;
        int a = 0;
        int b = 0;
        int c = N - 1;
        int d = N - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        // 边长len, 分len-1组, d-b+1 长度, 有 d-b组
        for (int i = 0; i < d - b; i++) {
            int tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = tmp;
        }
    }
}
