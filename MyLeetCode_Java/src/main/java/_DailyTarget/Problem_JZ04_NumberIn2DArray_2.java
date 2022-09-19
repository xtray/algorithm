package _DailyTarget;

public class Problem_JZ04_NumberIn2DArray_2 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int i = 0;
        int j = M - 1;
        while (i < N && j >= 0) {
            if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                return true;
            }
        }

        return false;
    }
}
