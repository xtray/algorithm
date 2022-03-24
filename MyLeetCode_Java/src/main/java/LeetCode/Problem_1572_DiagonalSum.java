package LeetCode;

public class Problem_1572_DiagonalSum {

    public int diagonalSum(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int N = mat.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || i + j == N - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }

    public int diagonalSum2(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int N = mat.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += mat[i][i] + mat[i][N - i - 1];
        }
        // return sum - ((N & 1) == 1 ? mat[N >> 1][N >> 1] : 0);
        return sum - (N & 1) * mat[N >> 1][N >> 1];
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var ans = new Problem_1572_DiagonalSum().diagonalSum2(mat);
        System.out.println(ans);
    }
}
