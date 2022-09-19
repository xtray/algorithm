package LeetCode;

public class Problem_1254_NumberOfIslands {

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && valid(grid, i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static final int[] dirs = {0, -1, 0, 1, 0};

    private boolean valid(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m) return false;
        if (grid[i][j] != 0) return true;
        grid[i][j] = 1; // 避免走回头路, 否则递归跑不完了
        boolean res = true;
        for (int d = 1; d < dirs.length; d++) {
            int nx = i + dirs[d - 1];
            int ny = j + dirs[d];
            boolean p = valid(grid, nx, ny);
            res &= p;  // IMP: 不能直接return false, 必须四个方向把连通区都感染完
            // if (!p) {
            //     res =  false;
            // }
        }
        return res;
    }

    public int closedIsland1(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) res++;
                }
            }
        }
        return res;
    }

    public boolean dfs(int[][] grid, int x, int y) {
        int h = grid.length, w = grid[0].length;
        if (x < 0 || y < 0 || x >= h || y >= w)
            return false;
        if (grid[x][y] == 1)
            return true;
        grid[x][y] = 1;
        boolean r1 = dfs(grid, x - 1, y);
        boolean r2 = dfs(grid, x, y + 1);
        boolean r3 = dfs(grid, x + 1, y);
        boolean r4 = dfs(grid, x, y - 1);
        return r1 && r2 && r3 && r4;
    }

    public boolean dfs2(int[][] grid, int x, int y) {
        int h = grid.length, w = grid[0].length;
        if (x < 0 || y < 0 || x >= h || y >= w)
            return false;
        if (grid[x][y] == 1)
            return true;
        grid[x][y] = 1;
        // IMP: 方法错误!, 必须4个方向都走, 刷遍所有连通区域
        //  逻辑运算会做短路操作, 导致错误
        // return dfs2(grid,x-1,y) &&
        //         dfs2(grid,x,y+1) &&
        //         dfs2(grid,x+1,y) &&
        //         dfs2(grid,x,y-1);
        return dfs2(grid, x - 1, y) &
                dfs2(grid, x, y + 1) &
                dfs2(grid, x + 1, y) &
                dfs2(grid, x, y - 1);
    }

    public int closedIsland3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        // 0 列, M-1列
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0) {
                infect(grid, i, 0);
            }
            if (grid[i][m - 1] == 0) {
                infect(grid, i, m - 1);
            }
        }
        // 0 行, N-1行
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0) {
                infect(grid, 0, j);
            }
            if (grid[n - 1][j] == 0) {
                infect(grid, n - 1, j);
            }
        }
        int cnt = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 0) {
                    infect(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }


    private void infect(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 1) return;
        grid[i][j] = 1;
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
    }

    public static void main(String[] args) {
        // int[][] grid = {
        //         {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
        //         {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
        //         {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
        //         {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
        //         {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
        //         {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
        //         {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
        //         {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        //         {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
        //         {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}}; // 5
        // var ans = new Problem_1254_NumberOfIslands().closedIsland(grid);
        // System.out.println(ans);

        int[][] grid1 = {
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
        var ans1 = new Problem_1254_NumberOfIslands().closedIsland3(grid1);
        System.out.println(ans1);
    }
}
