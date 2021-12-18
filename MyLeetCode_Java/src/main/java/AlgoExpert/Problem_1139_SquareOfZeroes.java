package AlgoExpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1139_SquareOfZeroes {

    // 0 不好计算, 改成 1, 寻找最大的边框 1 的问题类似
    public static boolean squareOfZeroes(List<List<Integer>> matrix) {

        int N = matrix.size();
        int M = matrix.get(0).size();
        int[][] right = new int[N][M]; // (i,j)点右方有多少个连续的 1
        int[][] down = new int[N][M]; // (i,j)点下方有多少个连续的 1
        setBorderMap(matrix, right, down);
        // 边长为 1 的不算
        for (int border = Math.min(N, M); border >= 2; border--) {
            //正方形左上(i,j), 边长 border 验证是不是正方形: O(1)
            if (hasSizeOfBorder(border, right, down)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < right[0].length; j++) {
                if (right[i][j] >= size && down[i][j] >= size &&
                        right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setBorderMap(List<List<Integer>> matrix, int[][] right, int[][] down) {
        int N = matrix.size();
        int M = matrix.get(0).size();

        if (matrix.get(N - 1).get(M - 1) == 0) {
            right[N - 1][M - 1] = 1;
            down[N - 1][M - 1] = 1;
        }
        // 最右一列
        for (int i = N - 2; i >= 0; i--) {
            if (matrix.get(i).get(M - 1) == 0) {
                right[i][M - 1] = 1;
                down[i][M - 1] = down[i + 1][M - 1] + 1;
            }
        }
        // 最下一行
        for (int j = M - 2; j >= 0; j--) {
            if (matrix.get(N - 1).get(j) == 0) {
                right[N - 1][j] = right[N - 1][j + 1] + 1;
                down[N - 1][j] = 1;
            }
        }
        // 0~N-2行, 0~M-2列
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (matrix.get(i).get(j) == 0) {
                    right[i][j] += right[i][j + 1] + 1;
                    down[i][j] += down[i + 1][j] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> test = new ArrayList<List<Integer>>();
        test.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
        test.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
        boolean res = squareOfZeroes(test);
        System.out.println(res);
    }
}
