package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Problem_1020_NumEnclaves {

    private static final int[][] dirs = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    public int numEnclaves(int[][] grid) {
        if(grid == null || grid.length ==0 || grid[0] == null ||
                grid[0].length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;

        for(int j=0;j<M;j++) {
            if(grid[0][j] == 1) { // 0行
                infect(grid, 0, j);
            }
            if(grid[N-1][j] == 1) { // N-1行
                infect(grid, N-1, j);
            }
        }
        for(int i = 0; i< N;i++) {
            if(grid[i][0] == 1) { // 0列
                infect(grid, i, 0);
            }
            if(grid[i][M-1] == 1) { // M-1行
                infect(grid, i, M-1);
            }
        }
        int ans = 0;
        for(int i = 0; i< N;i++) {
            for(int j = 0; j< M;j++) {
                if(grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void infect(int[][]grid, int i, int j) {
        if(i<0||i>grid.length-1||j<0||j>grid[0].length-1||grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            infect(grid, x,y);
        }
    }


    // 多源 BFS
    // 将所有「边缘陆地」看做与超级源点相连，起始将所有「边缘陆地」进行入队
    // （等价于只将超级源点入队，然后取出超级源点并进行拓展）。
    public int numEnclaves2(int[][] g) {
        int m = g.length, n = g[0].length;
        boolean[][] vis = new boolean[m][n];
        Deque<int[]> d = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (g[i][j] == 0) continue;
                    vis[i][j] = true;
                    d.addLast(new int[]{i, j});
                }
            }
        }
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int x = poll[0], y = poll[1];
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (g[nx][ny] != 1) continue;
                if (vis[nx][ny]) continue;
                vis[nx][ny] = true;
                d.addLast(new int[]{nx, ny});
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1 && !vis[i][j]) ans++;
            }
        }
        return ans;
    }

}
