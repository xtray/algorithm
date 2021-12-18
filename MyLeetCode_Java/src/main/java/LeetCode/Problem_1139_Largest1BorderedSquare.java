package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1139_Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] grid) {

        int N = grid.length;
        int M = grid[0].length;
        int[][] right = new int[N][M]; // (i,j)点右方有多少个连续的 1
        int[][] down = new int[N][M]; // (i,j)点下方有多少个连续的 1
        setBorderMap(grid, right, down);
        for (int border = Math.min(N, M); border >= 1; border--) {
            // 枚举边长, 在每一个(i,j)做检查
            if (hasSizeOfBorder(border, right, down)) {
                return border * border;
            }
        }
        return 0;
    }

    private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < right[0].length; j++) { // 以每一个(i,j)作为左上角
                if (right[i][j] >= size && down[i][j] >= size &&
                        right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }


    private static void setBorderMap(int[][] matrix, int[][] right, int[][] down) {
        int N = matrix.length;
        int M = matrix[0].length;

        if (matrix[N - 1][M - 1] == 1) {
            right[N - 1][M - 1] = 1;
            down[N - 1][M - 1] = 1;
        }
        // 最右一列
        for (int i = N - 2; i >= 0; i--) {
            if (matrix[i][M - 1] == 1) {
                right[i][M - 1] = 1;
                down[i][M - 1] = down[i + 1][M - 1] + 1;
            }
        }
        // 最下一行
        for (int j = M - 2; j >= 0; j--) {
            if (matrix[N - 1][j] == 1) {
                right[N - 1][j] = right[N - 1][j + 1] + 1;
                down[N - 1][j] = 1;
            }
        }
        // 0~N-2行, 0~M-2列
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] += right[i][j + 1] + 1;
                    down[i][j] += down[i + 1][j] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        var res = largest1BorderedSquare(matrix);
        System.out.println(res);
    }
}
