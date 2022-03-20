package LeetCode.Jianzhi;

public class Problem_JZ029_SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int x1 = 0;
        int y1 = 0;
        int x2 = N - 1;
        int y2 = M - 1;
        int[] ans = new int[N * M];
        int[] idx = new int[]{0, 0};
        while (x1 <= x2 && y1 <= y2) {
            printOneLoop(matrix, x1++, y1++, x2--, y2--, ans, idx);
        }
        return ans;
    }

    // 从(x1,y1) -- > (x2,y2) 一圈打印
    private void printOneLoop(int[][] matrix, int x1, int y1, int x2, int y2, int[] ans, int[] index) {
        int idx = index[0];
        if (x1 == x2) {
            for (int j = y1; j <= y2; j++) {
                ans[idx++] = matrix[x1][j];
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; i++) {
                ans[idx++] = matrix[i][y1];
            }
        } else {
            // 上边
            for (int j = y1; j < y2; j++) {
                ans[idx++] = matrix[x1][j];
            }
            // 右侧
            for (int i = x1; i < x2; i++) {
                ans[idx++] = matrix[i][y2];
            }
            // 下边
            for (int j = y2; j > y1; j--) {
                ans[idx++] = matrix[x2][j];
            }
            // 左边
            for (int i = x2; i > x1; i--) {
                ans[idx++] = matrix[i][y1];
            }
        }
        index[0] = idx;
    }

    public static void main(String[] args) {
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        // int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {{1}, {2}, {3}, {4}};
        var ans = new Problem_JZ029_SpiralOrder().spiralOrder(matrix);
        for (var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
