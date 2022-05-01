package LeetCode;

public class Problem_1886_MatrixRotation {

    // https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/solution/mei-ju-si-chong-qing-kuang-jin-xing-bi-j-r3ko/
    // NOTE: 回看
    public boolean findRotation(int[][] mat, int[][] target) {
        int N = mat.length;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //旋转90度
                if (mat[j][N - 1 - i] != target[i][j]) {
                    b1 = false; // 有一个格子不符合就会false
                }
                //旋转180度
                if (mat[N - 1 - i][N - 1 - j] != target[i][j]) {
                    b2 = false;
                }
                //旋转270度
                if (mat[N - 1 - j][i] != target[i][j]) {
                    b3 = false;
                }
                //旋转360度
                if (mat[i][j] != target[i][j]) {
                    b4 = false;
                }
            }
            if (!b1 && !b2 && !b3 && !b4) { // 4个都false, 返回
                return false;
            }
        }
        return true;
    }

}
