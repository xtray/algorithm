package LeetCode;

import java.util.*;

public class Problem_417_PacificAtlantic {

    // 重新翻译：
    // 1.每个小方块上都有雨水
    // 2.我们要找的小方块是：它的雨水既可以流入太平洋，又可以流入大西洋

    // ref: https://leetcode-cn.com/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/
    // IMP: 逆向思维, 分别从与当前海域直接相连的边缘格子出发，统计能够流向当前海域的格子集合，两片海域求得的集合交集即是答案。
    // NOTE: 多源BFS, 多看!!
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = heights.length;
        int M = heights[0].length;
        LinkedList<int[]> rightDownQueue = new LinkedList<>();
        LinkedList<int[]> leftUpQueue = new LinkedList<>();

        boolean[][] resRd = new boolean[N][M];
        boolean[][] resLu = new boolean[N][M];

        // 将下侧, 右侧与海域相邻的格子放入rightDownQueue
        // 将左侧, 上侧与海域相邻的格子放入leftUpQueue
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0 || i == 0) {
                    resLu[i][j] = true;
                    leftUpQueue.addLast(new int[]{i, j});
                }
                if (i == N - 1 || j == M - 1) {
                    resRd[i][j] = true;
                    rightDownQueue.addLast(new int[]{i, j});
                }
            }
        }

        // 从收集的边缘格子开始BFS
        bfs(heights, leftUpQueue, resLu);
        bfs(heights, rightDownQueue, resRd);
        // 收集答案
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (resLu[i][j] && resRd[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void bfs(int[][] heights, LinkedList<int[]> queue, boolean[][] res) {
        int N = heights.length;
        int M = heights[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            int h = heights[x][y];
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M
                        || res[nx][ny] || heights[nx][ny] < h) continue;
                res[nx][ny] = true;
                queue.addLast(new int[]{nx, ny});
            }
        }
    }

    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // DFS
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = heights.length;
        int M = heights[0].length;
        boolean[][] resLu = new boolean[N][M];
        boolean[][] resRd = new boolean[N][M];
        // 将下侧, 右侧与海域相邻的格子放入rightDownQueue
        // 将左侧, 上侧与海域相邻的格子放入leftUpQueue
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0 || i == 0) {
                    if (!resLu[i][j]) {
                        dfs(heights, i, j, resLu);
                    }
                }
                if (i == N - 1 || j == M - 1) {
                    if (!resRd[i][j]) {
                        dfs(heights, i, j, resRd);
                    }
                }
            }
        }

        // 收集答案
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (resLu[i][j] && resRd[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void dfs1(int[][] heights, int i, int j, boolean[][] res) {
        int N = heights.length;
        int M = heights[0].length;
        res[i][j] = true;
        for (int[] d : dirs) {
            int nx = i + d[0];
            int ny = j + d[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M
                    || res[nx][ny] || heights[nx][ny] < heights[i][j]) continue;
            dfs1(heights, nx, ny, res);
        }
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] res) {
        int N = heights.length;
        int M = heights[0].length;
        LinkedList<int[]> stack = new LinkedList<>();
        stack.addLast(new int[]{i, j});
        res[i][j] = true; // 加入的同时就做标记
        while (!stack.isEmpty()) {
            int[] cur = stack.pollLast();
            int x = cur[0];
            int y = cur[1];
            int h = heights[x][y]; // NOTE: 左边是x,y, 不是i,j, 查了好久....
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M
                        || res[nx][ny] || heights[nx][ny] < h) continue;
                // IMP: dfs 要求必须要把原来的父压回去 并 break
                // 如果没有这两步, 就是一种诡异的类似BFS的方式了
                stack.addLast(cur);
                stack.addLast(new int[]{nx, ny});
                res[nx][ny] = true;
                break;
            }
        }
    }

    // 也可以用并查集解决
    // ref: https://leetcode-cn.com/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        var ans = new Problem_417_PacificAtlantic().pacificAtlantic2(heights);
        System.out.print("[");
        for (List<Integer> item : ans) {
            System.out.printf("[%d, %d]", item.get(0), item.get(1));
        }
        System.out.println("]");
    }


}
