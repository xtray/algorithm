package _DailyTarget;

public class Problem_308_NumArray {

    class NumMatrix {

        private int[][] tree;
        private int[][] matrix;
        private int N;
        private int M;

        // 返回1~index的数的累加和
        private int sum(int row, int col) {
            int res = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    res += tree[i][j];
                }
            }
            return res;
        }

        // index位置的数+val
        private void add(int row, int col, int val) {
            for (int i = row; i <= N; i += i & (-i)) {
                for (int j = col; j <= M; j += j & (-j)) {
                    tree[i][j] += val;
                }
            }
        }

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            N = matrix.length;
            M = matrix[0].length;
            tree = new int[N + 1][M + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    add(i + 1, j + 1, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            int gap = val - matrix[row][col];
            matrix[row][col] = val;
            add(row + 1, col + 1, gap);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum(row2 + 1, col2 + 1)
                    - sum(row2 + 1, col1)
                    - sum(row1, col2 + 1)
                    + sum(row1, col1);
        }
    }
}
