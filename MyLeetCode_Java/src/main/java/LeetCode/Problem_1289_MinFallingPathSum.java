package LeetCode;

// same with https://leetcode.cn/problems/paint-house/
// Problem_256_PaintHouses

public class Problem_1289_MinFallingPathSum {
    public int minFallingPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int preMin1 = 0;
        int preMin2 = 0;
        int preCol1 = -1; // 为了让0行可以随意选择
        int preCol2 = -1;
        for (int i = 0; i < N; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin2 = Integer.MAX_VALUE;
            int curCol1 = -1;
            int curCol2 = -1;
            for (int j = 0; j < M; j++) {
                if (j != preCol1) { // 用上次第1小去尝试更新
                    if (preMin1 + grid[i][j] < curMin1) {
                        curMin2 = curMin1; // NOTE: 用当前的第一小更新第二小
                        curCol2 = curCol1;
                        curMin1 = preMin1 + grid[i][j];
                        curCol1 = j;
                    } else if (preMin1 + grid[i][j] < curMin2) {
                        curMin2 = preMin1 + grid[i][j];
                        curCol2 = j;
                    }
                } else if (j != preCol2) { // 用上次第2小去尝试更新
                    if (preMin2 + grid[i][j] < curMin1) {
                        curMin2 = curMin1;
                        curCol2 = curCol1;
                        curMin1 = preMin2 + grid[i][j];
                        curCol1 = j;
                    } else if (preMin2 + grid[i][j] < curMin2) {
                        curMin2 = preMin2 + grid[i][j];
                        curCol2 = j;
                    }
                }
            }
            preMin1 = curMin1;
            preMin2 = curMin2;
            preCol1 = curCol1;
            preCol2 = curCol2;
        }
        return preMin1;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var ans = new Problem_1289_MinFallingPathSum().minFallingPathSum(grid);
        System.out.println(ans);
    }
}
