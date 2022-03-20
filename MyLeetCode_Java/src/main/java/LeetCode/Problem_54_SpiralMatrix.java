// 
// 2022-03-17 00:27:35
// https://leetcode-cn.com/problems/spiral-matrix/

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_54_SpiralMatrix{

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int x1 = 0;
        int y1 = 0;
        int x2 = N - 1;
        int y2 = M - 1;
        List<Integer> ans = new ArrayList<>();
        while (x1 <= x2 && y1 <= y2) {
            printOneLoop(matrix, x1++, y1++, x2--, y2--, ans);
        }
        return ans;
    }

    // 从(x1,y1) -- > (x2,y2) 一圈打印
    private void printOneLoop(int[][] matrix, int x1, int y1, int x2, int y2, List<Integer> ans) {
        if (x1 == x2) {
            for (int j = y1; j <= y2; j++) {
                ans.add(matrix[x1][j]);
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; i++) {
                ans.add(matrix[i][y1]);
            }
        } else {
            // 上边
            for (int j = y1; j < y2; j++) {
                ans.add(matrix[x1][j]);
            }
            // 右侧
            for (int i = x1; i < x2; i++) {
                ans.add(matrix[i][y2]);
            }
            // 下边
            for (int j = y2; j > y1; j--) {
                ans.add(matrix[x2][j]);
            }
            // 左边
            for (int i = x2; i > x1; i--) {
                ans.add(matrix[i][y1]);
            }
        }
    }


    public static void main(String[] args) {
       Problem_54_SpiralMatrix sl = new Problem_54_SpiralMatrix();
       
    }

}