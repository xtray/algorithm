package _DailyTarget;

public class Problem_JZ04_NumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int i = N - 1;
        int j = 0;
        while (i >= 0 && j < M) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
