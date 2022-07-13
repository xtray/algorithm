package LeetCode.JZOffer;

public class Problem_JZ4_FindNum2D {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length ==0 || matrix[0] == null || matrix[0].length ==0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int x = N -1;
        int y = 0;
        while (x>=0 && y<M) {
            if(matrix[x][y] < target) {
                y++;
            } else if (matrix[x][y] > target) {
                x--;
            } else {
                return true;
            }
        }
        return false;
    }
}
