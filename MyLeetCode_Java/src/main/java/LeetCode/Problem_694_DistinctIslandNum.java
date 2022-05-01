package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_694_DistinctIslandNum {


    // ref: https://leetcode-cn.com/problems/number-of-distinct-islands/solution/javashen-du-you-xian-sou-suo-hashsetqu-zhong-by-we/
    // 解法1: floodfill
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        // 从每一个位置开始尝试
        int ans = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                    StringBuilder sb = new StringBuilder();
                    process(grid, i, j, i,j, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, -1, 0, 1, 0};

    private void process(int[][] grid, int i, int j, int orgi, int orgj, StringBuilder sb) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        sb.append(i-orgi);
        sb.append(j-orgj);
        for (int k = 0; k < dirs.length; k += 2) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            process(grid, x, y, orgi, orgj, sb);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        var ans = new Problem_694_DistinctIslandNum().numDistinctIslands(grid);
        System.out.println(ans);
    }
}
