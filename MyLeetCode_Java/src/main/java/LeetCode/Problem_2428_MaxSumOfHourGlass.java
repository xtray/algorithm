package LeetCode;

public class Problem_2428_MaxSumOfHourGlass {

    public int maxSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int Len = 3;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                // i行
                for (int col = j; col < j + Len; col++) {
                    sum += grid[i][col];
                }
                // mid
                sum += grid[i + 1][j + 1];
                // i+2行
                for (int col = j; col < j + Len; col++) {
                    sum += grid[i + 2][col];
                }
                maxSum = Math.max(maxSum, sum);
                sum = 0;
            }
        }
        return maxSum;
    }
}
