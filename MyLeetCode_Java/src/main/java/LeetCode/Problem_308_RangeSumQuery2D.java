package LeetCode;

// IMP: 重要二维线段树模板题

public class Problem_308_RangeSumQuery2D {

    static class NumMatrix {

        private int[][] tree; // indexTree
        private int[][] nums; // 拷贝一份原数组
        private int N;
        private int M;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
                return;
            }
            N = matrix.length;
            M = matrix[0].length;
            tree = new int[N + 1][M + 1]; // 下标从1开始
            // IMP: 如果用传入matrix数组, 即 nums = matrix, 需要调用add函数初始化tree数组
            nums = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        // (1,1) ~ (row, col)的累加和
        private int sum(int row, int col) {
            int sum = 0;
            for (int i = row + 1; i > 0; i -= i & (-i)) {
                for (int j = col + 1; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }

        public void update(int row, int col, int val) {
            if (N == 0 || M == 0) {
                return;
            }
            int delta = val - nums[row][col];
            nums[row][col] = val; // NOTE: 不要忘了这一句, 用来计算增量
            for (int i = row + 1; i <= N; i += i & (-i)) {
                for (int j = col + 1; j <= M; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        // NOTE: 这里入参左边是正常原始坐标, sum 函数里会做+1操作
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (N == 0 || M == 0) { // IMP: 注意别忘了边界
                return 0;
            }
            return sum(row2, col2)
                    - sum(row1 - 1, col2)
                    - sum(row2, col1 - 1)
                    + sum(row1 - 1, col1 - 1);
        }
    }

    public void test() {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix sl = new NumMatrix(matrix);
        var ans = sl.sumRegion(2,1,4,3);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        var sl = new Problem_308_RangeSumQuery2D();
        sl.test();
    }
}
