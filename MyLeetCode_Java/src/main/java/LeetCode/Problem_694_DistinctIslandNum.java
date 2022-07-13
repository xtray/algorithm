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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                    process(grid, i, j, i, j, sb);
                    set.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        return set.size();
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, 0};

    // 以每个(i,j)点作为左上角出发点标号
    private void process(int[][] grid, int i, int j, int baseX, int baseY, StringBuilder sb) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        sb.append((i - baseX) * 100 + (j - baseY));
        for (int d = 1; d < dirs.length; d++) {
            int x = i + dirs[d - 1];
            int y = j + dirs[d];
            process(grid, x, y, baseX, baseY, sb);
        }
    }

    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        // 从每一个位置开始尝试
        int ans = 0;
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                    process2(grid, i, j, i, j, sb);
                    set.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        return set.size();
    }

    // 以每个(i,j)点作为左上角出发点标号
    private void process2(int[][] grid, int i, int j, int baseX, int baseY, StringBuilder sb) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        sb.append((i - baseX) * 100 + (j - baseY));
        for (int d = 1; d < dirs.length; d++) {
            int x = i + dirs[d - 1];
            int y = j + dirs[d];
            process(grid, x, y, baseX, baseY, sb);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
        var ans = new Problem_694_DistinctIslandNum().numDistinctIslands(grid);
        System.out.println(ans);
    }
}
