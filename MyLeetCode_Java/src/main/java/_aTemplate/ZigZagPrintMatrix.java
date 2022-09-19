package _aTemplate;

// IMP: 重要矩阵宏观调度题, 使用两个点控制, 多看!!

import java.util.ArrayList;
import java.util.List;

public class ZigZagPrintMatrix {

    public static List<Integer> printMatrixZigZag0(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return ans;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        // 下方的A点
        int dx = 0;
        int dy = 0;
        // 上方的B点
        int ux = 0;
        int uy = 0;
        boolean order = true;
        // 从下到上
        while (dy != M) {
            printLine(matrix, dx, dy, ux, uy, order, ans);
            dy = dx + 1 == N ? dy + 1 : dy; // NOTE: 先更新y后再更新x, 因为有依赖!!
            dx = dx + 1 == N ? N - 1 : dx + 1;
            ux = uy + 1 == M ? ux + 1 : ux;
            uy = uy + 1 == M ? M - 1 : uy + 1;
            order = !order;
        }
        return ans;
    }

    // 在下侧(a,b)--> 上方(c,d)间 按order指定的顺序打印
    private static void printLine(int[][] matrix, int a, int b, int c, int d, boolean order, List<Integer> ans) {
        if (order) {
            int i = a;
            int j = b;
            while (i >= c) { // NOTE: 注意 x, y 坐标有++, 有--
                ans.add(matrix[i--][j++]);
            }
        } else {
            int i = c;
            int j = d;
            while (i <= a) {
                ans.add(matrix[i++][j--]);
            }
        }
    }

    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);
        var ans = printMatrixZigZag0(matrix);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();

    }

}
