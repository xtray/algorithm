package LeetCode;

public class Problem_2022_Construct2DArray {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int N = original.length;
        if (N != m * n) {
            return new int[][]{};
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < N; i++) {
            // i = row*n + col;
            int row = i / n;
            int col = i % n;
            ans[row][col] = original[i];
        }
        return ans;
    }
}
