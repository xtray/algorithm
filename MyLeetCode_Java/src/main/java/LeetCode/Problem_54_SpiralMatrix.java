// 
// 2022-03-17 00:27:35
// https://leetcode-cn.com/problems/spiral-matrix/

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int a = 0;
        int b = 0;
        int c = N - 1;
        int d = M - 1;
        List<Integer> ans = new ArrayList<>();
        while (a <= c && b <= d) {
            printOneLoop(matrix, a++, b++, c--, d--, ans);
        }
        return ans;
    }

    // 从(a,b) -- > (c,d) 一圈打印
    private void printOneLoop(int[][] matrix, int a, int b, int c, int d, List<Integer> ans) {
        if (a == c) {
            for (int j = b; j <= d; j++) {
                ans.add(matrix[a][j]);
            }
        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                ans.add(matrix[i][b]);
            }
        } else {
            // 上边
            for (int j = b; j < d; j++) {
                ans.add(matrix[a][j]);
            }
            // 右侧
            for (int i = a; i < c; i++) {
                ans.add(matrix[i][d]);
            }
            // 下边
            for (int j = d; j > b; j--) {
                ans.add(matrix[c][j]);
            }
            // 左边
            for (int i = c; i > a; i--) {
                ans.add(matrix[i][b]);
            }
        }
    }


    public static void main(String[] args) {
        Problem_54_SpiralMatrix sl = new Problem_54_SpiralMatrix();

    }

}