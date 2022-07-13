package LeetCode;

public class Problem_1706_FindBall {

    public int[] findBall(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return new int[0];
        }
        int N = grid.length;
        int M = grid[0].length;
        int[] ans = new int[M];
        for (int col = 0; col < M; col++) { // 检查一行上每一列的小球
            int i = 0;
            int j = col;
            while (i < N) { // 小球从(i,j) --> 去N-1行
                int jNext = j + grid[i][j]; // 要去的lie
                // 越界 或者  方向不一致, 不能掉落
                if (jNext < 0 || jNext >= M || grid[i][j] != grid[i][jNext]) {
                    ans[col] = -1;
                    break;
                }
                i++;
                j = jNext;
            }
            if (i == N) {
                ans[col] = j;
            }
        }
        return ans;
    }
}
