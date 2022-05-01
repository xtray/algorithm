package LeetCode;

public class Problem_883_ProjectArea {

    public int projectionArea(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            int area_yz = 0; // 左视图, 统计每行最大高度
            int area_xz = 0; // xz视图, 统计没类最大高度
            for (int j = 0; j < N; j++) {
                if (grid[i][j] > 0) { // x-y
                    ans++;
                }
                area_yz = Math.max(area_yz, grid[i][j]);
                area_xz = Math.max(area_xz, grid[j][i]); // 统计第i列的最大高度
            }
            ans += area_yz;
            ans += area_xz;
        }
        return ans;
    }
}
