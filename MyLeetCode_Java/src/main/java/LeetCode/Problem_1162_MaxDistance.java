package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Problem_1162_MaxDistance {

    // 单源 BFS解法
    public int maxDistance0(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        // 从每个0点出发做 bfs
        int ans = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, bfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    private int bfs(int[][] grid, int x, int y) {
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        Map<Integer, Integer> map = new HashMap<>();
        int N = grid.length;
        map.put(x * N + y, 0);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int dx = cur[0];
            int dy = cur[1];
            int step = map.get(dx * N + dy);
            if (grid[dx][dy] == 1) { // 碰到陆地了, 一定是最近的, 直接返回
                // 海洋单元格到离它最近的陆地单元格的距离
                return step;
            }
            for (int[] dir : dirs) {
                int nx = dx + dir[0];
                int ny = dy + dir[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map.containsKey(nx * N + ny)) continue; // 之前到过
                queue.add(new int[]{nx, ny});
                map.put(nx * N + ny, step + 1);
            }
        }
        return -1; // 全都是 0, 返回-1
    }

    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int N = grid.length;

        // 所有陆地作为源头做 BFS
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    map.put(i * N + j, 0);
                }
            }
        }
        int ans = -1;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int dx = cur[0];
            int dy = cur[1];
            int step = map.get(dx * N + dy);
            for (int[] dir : dirs) {
                int nx = dx + dir[0];
                int ny = dy + dir[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (grid[nx][ny] != 0) continue; // 扩到的不是海洋, 不处理
                grid[nx][ny] = step + 1;
                queue.add(new int[]{nx, ny});
                map.put(nx * N + ny, step + 1);
                ans = Math.max(ans, step + 1);
            }
        }
        return ans;
    }

    public int maxDistance1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int N = grid.length;

        // 所有陆地作为源头做 BFS
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        int ans = -1;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int dx = cur[0];
            int dy = cur[1];
            int step = cur[2];
            for (int[] dir : dirs) {
                int nx = dx + dir[0];
                int ny = dy + dir[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (grid[nx][ny] != 0) continue; // 扩到的不是没走过的
                grid[nx][ny] = step + 1; // 走过的标记
                queue.add(new int[]{nx, ny, step+1});
                ans = Math.max(ans, step + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        var ans = new Problem_1162_MaxDistance().maxDistance0(grid);
        System.out.println(ans);
    }
}
