package LeetCode;

public class Problem_1582_NumSpecial {

    // NOTE: 统计行列的1的个数, 当个数少时可以用位信息表示
    public int numSpecial(int[][] mat) {
        int N = mat.length;
        int M = mat[0].length;
        int[] row = new int[N];
        int[] col = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                row[i] += mat[i][j];
                col[j] += mat[i][j];
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
