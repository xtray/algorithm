package LeetCode;

// IMP: 多看
public class Problem_48_Rotate {
    public void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length -1;
        int d = matrix[0].length-1;
        while (a<c) {
            rotateLoop(matrix, a++, b++, c--, d--);
        }

    }

    private void rotateLoop(int[][] matrix, int a, int b, int c, int d) {
        int tmp = 0;
        for(int i =0; i< d -b; i++) {
            tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = tmp;
        }
    }
}
