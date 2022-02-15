package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1380_LuckyNumbers {

    public List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[] minRow = new int[N]; // 每行最小值, 共 N 行
        int[] maxCol = new int[M]; // 每列最大值, 共 M 列
        Arrays.fill(minRow, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ans.add(matrix[i][j]);
                }

            }
        }
        return ans;
    }
}
