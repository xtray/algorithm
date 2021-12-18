package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Problem_1034_ColorBorder {


    // BFS 解法
    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return grid;
        }
        int N = grid.length;
        int M = grid[0].length;
        int[][] ans = new int[N][M]; // grid 的值根据题目不可能为 0, 用 0 标记没碰过
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] curPos = queue.pollFirst();
            int x = curPos[0], y = curPos[1];
            int cnt = 0;
            // 向 4 个方向扩展
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                // 越界,或者不相等的拓展失败
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || grid[nx][ny] != grid[x][y]) continue;
                cnt++; // 某个方向上有外围防护
                if (ans[nx][ny] != 0) continue;
                queue.addLast(new int[]{nx, ny});
            }
            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ans[i][j] == 0) {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }

    // DFS 解法
    public int[][] colorBorder1(int[][] grid, int row, int col, int color) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return grid;
        }
        int N = grid.length;
        int M = grid[0].length;
        int[][] ans = new int[N][M]; // grid 的值根据题目不可能为 0, 用 0 标记没碰过
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        LinkedList<int[]> stack = new LinkedList<>();
        stack.addLast(new int[]{row, col});
        while (!stack.isEmpty()) {
            int[] curPos = stack.pollLast();
            int x = curPos[0], y = curPos[1];
            int cnt = 0;
            // 向 4 个方向扩展
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                // 越界,或者不相等的拓展失败
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || grid[nx][ny] != grid[x][y]) continue;
                cnt++; // 某个方向上有外围防护
                if (ans[nx][ny] != 0) continue;
                stack.addLast(new int[]{nx, ny});
            }
            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ans[i][j] == 0) {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }

    // DFS 解法
    public int[][] colorBorder0(int[][] grid, int row, int col, int color) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return grid;
        }

        int N = grid.length;
        int M = grid[0].length;
        int[][] ans = new int[N][M]; // grid 的值根据题目不可能为 0, 用 0 标记没碰过
        dfs(grid, row, col, color, ans);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ans[i][j] == 0) {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int x, int y, int color, int[][] ans) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        int cnt = 0;
        // 向 4 个方向扩展
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            // 越界,或者不相等的拓展失败
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || grid[nx][ny] != grid[x][y]) continue;
            cnt++;
            if (ans[nx][ny] != 0) continue; // 这个方向走过了
            if (ans[nx][ny] == -1) continue; // 这个方向走过了
            ans[nx][ny] = -1; // 在处理上下左右 4 个方向的子递归过程里不走重复路
            dfs(grid, nx, ny, color, ans);
        }
        ans[x][y] = cnt == 4 ? grid[x][y] : color;
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return grid;
        }
        Set<Integer> set = new HashSet<>(); // i*M + j, 不走重复路
        Set<Integer> border = new HashSet<>(); // 边界
        // 从感染点开始向四周扩散
        infect(grid, row, col, grid[row][col], set, border);
        paint(grid, border, color);
        return grid;
    }

    private void paint(int[][] grid, Set<Integer> border, int color) {
        int M = grid[0].length;
        for (int pos : border) {
            int row = pos / M;
            int col = pos % M;
            grid[row][col] = color;
        }
    }

    private final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // 返回 i,j 点上下左右的包围点数
    private int checkPoint(int[][] grid, int i, int j, int origin) {
        int N = grid.length;
        int M = grid[0].length;
        int count = 0;
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (dx >= 0 && dx < N && dy >= 0 && dy < M && grid[dx][dy] == origin) {
                count++;
            }
        }
        return count;
    }

    private void infect(int[][] grid, int i, int j, int origin, Set<Integer> set, Set<Integer> border) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != origin || set.contains(i * M + j)) {
            return;
        }
        set.add(i * M + j);
        border.add(i * M + j);
        // 检查是否是边界上下左右是否是边界
        if (checkPoint(grid, i, j, origin) == 4) {
            border.remove(i * M + j);
        }
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            infect(grid, dx, dy, origin, set, border);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1, 2, 2}, {2, 1, 2, 2, 2}, {1, 1, 2, 2, 1}};
        int row = 1, col = 0;
        int color = 1;
        var res = new Problem_1034_ColorBorder().colorBorder(grid, row, col, color);
        for (int[] line : res) {
            for (int p : line) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
