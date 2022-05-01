package L_INPRG;

public class Problem_304_RangeSumQuery2DImmutable {
    class NumMatrix {

        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0) {
                return;
            }
            int N = matrix.length;
            int M = matrix[0].length;
            preSum = new int[N][M];
            preSum[0][0] = matrix[0][0];
            for (int j = 1; j < M; j++) { // 0行
                preSum[0][j] = preSum[0][j - 1] + matrix[0][j];
            }
            for (int i = 1; i < N; i++) { // 0列
                preSum[i][0] = preSum[i - 1][0] + matrix[i][0];
            }
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int up = row1 >= 1 ? preSum[row1 - 1][col2] : 0;
            int left = col1 >= 1 ? preSum[row2][col1 - 1] : 0;
            int leftUp = (row1 >= 1 && col1 >= 1) ? preSum[row1 - 1][col1 - 1] : 0;
            return preSum[row2][col2] - up - left + leftUp;
        }
    }


    // IMP: 补一行一列, 可以少很多边界条件!!
    class NumMatrix2 {

        private int[][] preSum;

        public NumMatrix2(int[][] matrix) {
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0) {
                return;
            }
            int N = matrix.length;
            int M = matrix[0].length;
            preSum = new int[N+1][M+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i-1][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
        }
    }

    public void test() {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix sl = new NumMatrix(matrix);
        var ans = sl.sumRegion(2, 1, 4, 3);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Problem_304_RangeSumQuery2DImmutable().test();
    }
}
