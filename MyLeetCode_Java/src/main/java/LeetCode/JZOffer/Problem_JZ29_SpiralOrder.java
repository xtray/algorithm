package LeetCode.JZOffer;

// IMP: 转圈打印矩阵, 重要基础题

public class Problem_JZ29_SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int a = 0;
        int b = 0;
        int c = N - 1;
        int d = M - 1;
        int[] ans = new int[N * M];
        int[] idx = new int[]{0}; // 记录在ans数组中打印到第几个数了, 为了能够保存之前的值, 用数组
        while (a <= c && b <= d) { // NOTE: 非正方形, 所以需要在两个方向上都做限制
            printOneLoop(matrix, a++, b++, c--, d--, ans, idx);
        }
        return ans;
    }

    // 从(a,b) -- > (c,d) 一圈打印
    private void printOneLoop(int[][] matrix, int a, int b, int c, int d, int[] ans, int[] index) {
        int idx = index[0]; // 填充答案的位置
        if (a == c) { // 在同一行了
            for (int i = b; i <= d; i++) {
                ans[idx++] = matrix[a][i];
            }
        } else if (b == d) { // 在同一列了
            for (int i = a; i <= c; i++) {
                ans[idx++] = matrix[i][b];
            }
        } else {
            // 最上方
            for (int i = b; i < d; i++) {
                ans[idx++] = matrix[a][i];
            }
            // 右侧
            for (int i = a; i < c; i++) {
                ans[idx++] = matrix[i][d];
            }
            // 下方
            for (int i = d; i > b; i--) {
                ans[idx++] = matrix[c][i];
            }
            // 左侧
            for (int i = c; i > a; i--) {
                ans[idx++] = matrix[i][b];
            }
        }
        index[0] = idx; // 保存填写到的位置
    }

    public static void main(String[] args) {
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // int[][] matrix = {{1}, {2}, {3}, {4}};
        var ans = new Problem_JZ29_SpiralOrder().spiralOrder(matrix);
        for (var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
