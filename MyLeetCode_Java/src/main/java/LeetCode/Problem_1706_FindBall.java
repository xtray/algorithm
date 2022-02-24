package LeetCode;

public class Problem_1706_FindBall {

    public int[] findBall(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return new int[]{};
        }
        int M = grid.length; // M 行
        int N = grid[0].length; // N 列, N 个球
        int[] ans = new int[N];

        for (int j = 0; j < N; j++) {
            ans[j] = getVal(grid, j);
        }
        return ans;
    }

    // col 列滚到到最底部(共 M 行)的最后结果
    int getVal(int[][] grid, int col) {
        int M = grid.length; // M 行
        int N = grid[0].length; // N 列, N 个球
        int row = 0;
        while (row < M) {
            int neighbour = col + grid[row][col]; // 需要做判断的邻居格子位置
            // 当前是 \ 需要判断下一个 (+ 1 位置)是不是 相同的 /
            // 当前是 / 需要判断前一个 (- 1 位置)是不是 相同的 /
            if (neighbour < 0 || neighbour >= N) { // 越界, 被跟墙夹住了
                return -1;
            }
            // 不相同则夹住了
            if (grid[row][col] != grid[row][neighbour]) {
                return -1;
            }
            // 否则往下走
            row++;
            col = neighbour; // 列上来到 neighbour 的位置
        }
        // answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标
        return col;
    }
}
