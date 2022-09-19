package _DailyTarget;

import java.util.*;

public class Problem_417_PacificAtlantic {

    // IMP: 逆向思维, 分别从与当前海域直接相连的边缘格子出发，统计能够流向当前海域的格子集合，两片海域求得的集合交集即是答案。
    // NOTE: 多源BFS, 多看!!
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = heights.length;
        int M = heights[0].length;
        ArrayDeque<int[]> rightDownQueue = new ArrayDeque<>();
        ArrayDeque<int[]> leftUpQueue = new ArrayDeque<>();
        boolean[][] rd = new boolean[N][M];
        boolean[][] lu = new boolean[N][M];
        // 1.初始化队列
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) {
                    leftUpQueue.addLast(new int[]{i, j});
                    lu[i][j] = true;
                }
                if (i == N - 1 || j == M - 1) {
                    rightDownQueue.addLast(new int[]{i, j});
                    rd[i][j] = true;
                }
            }
        }
        // 2.BFS
        bfs(heights, rightDownQueue, rd);
        bfs(heights, leftUpQueue, lu);
        // 3.收集答案
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (rd[i][j] && lu[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void bfs(int[][] heights, ArrayDeque<int[]> queue, boolean[][] res) {
        int N = heights.length;
        int M = heights[0].length;
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d];
                int ny = y + dirs[d + 1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || res[nx][ny] || heights[nx][ny] < heights[x][y]) continue;
                res[nx][ny] = true;
                queue.addLast(new int[]{nx, ny});
            }
        }
    }

    // 解法2: 从每一个点出发做DFS
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = heights.length;
        int M = heights[0].length;
        boolean[][] lu = new boolean[N][M];
        boolean[][] rd = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) { // 右上
                    if (!lu[i][j]) { // true已经走过了
                        dfs(heights, i, j, lu);
                    }
                }
                if (i == N - 1 || j == M - 1) {
                    if (!rd[i][j]) {
                        dfs(heights, i, j, rd);
                    }
                }
            }
        }
        // 收集答案
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lu[i][j] && rd[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    // private void dfs(int[][] heights, int i, int j, boolean[][] res) {
    //     int N = heights.length;
    //     int M = heights[0].length;
    //     int[] dirs = new int[]{0, -1, 0, 1, 0};
    //     res[i][j] = true;
    //     for (int k = 1; k <= 4; k++) {
    //         int nx = i + dirs[k - 1];
    //         int ny = j + dirs[k];
    //         if (nx < 0 || nx >= N || ny < 0 || ny >= M
    //                 || res[nx][ny] || heights[nx][ny] < heights[i][j]) continue;
    //         dfs(heights, nx, ny, res);
    //     }
    // }

    private void dfs(int[][] heights, int i, int j, boolean[][] res) {
        int N = heights.length;
        int M = heights[0].length;
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.addLast(new int[]{i, j});
        res[i][j] = true; // 加入的同时就做标记
        while (!stack.isEmpty()) {
            int[] cur = stack.pollLast();
            int x = cur[0];
            int y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d];
                int ny = y + dirs[d + 1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M
                        || res[nx][ny] || heights[nx][ny] < heights[x][y]) continue;
                res[nx][ny] = true;
                // IMP: dfs 要求必须要把原来的父压回去 并 break
                //  如果没有这两步, 就是一种诡异的类似BFS的方式了
                stack.addLast(cur);
                stack.addLast(new int[]{nx, ny});
                break;
            }
        }
    }


    // 也可以用并查集解决
    // ref: https://leetcode-cn.com/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = heights.length;
        int M = heights[0].length;
        UnionFind lu = new UnionFind(N * M);
        UnionFind rd = new UnionFind(N * M);
        int ankerA = 0; // 集合A
        int ankerB = N * M - 1; // 集合B

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int idx = i * M + j;
                if (i == 0 || j == 0) {
                    if (!lu.isSameSet(ankerA, idx)) {
                        dfs_uf(heights, i, j, ankerA, lu);
                    }

                }
                if (i == N - 1 || j == M - 1) {
                    if (!rd.isSameSet(ankerB, idx)) {
                        dfs_uf(heights, i, j, ankerB, rd);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int idx = i * M + j;
                if (lu.isSameSet(ankerA, idx) && rd.isSameSet(ankerB, idx)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private void dfs_uf(int[][] heights, int i, int j, int anker, UnionFind uf) {
        int N = heights.length;
        int M = heights[0].length;
        int idx = i * M + j;
        uf.union(anker, idx);
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        for (int k = 1; k <= 4; k++) {
            int nx = i + dirs[k - 1];
            int ny = j + dirs[k];
            // 高度是反着来的, 所以<不行
            if (nx < 0 || nx >= N || ny < 0 || ny >= M
                    || uf.isSameSet(anker, nx * M + ny) || heights[nx][ny] < heights[i][j]) continue;
            dfs_uf(heights, nx, ny, anker, uf);
        }
    }


    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        private int[] stack; // 数组做栈
        private int setSize; // 一共有多少个集合

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            stack = new int[N];
            setSize = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // 这个过程要做路径压缩
        // private int find(int i) {
        //     int hi = 0;
        //     while (i != parent[i]) {
        //         stack[hi++] = i;
        //         i = parent[i];
        //     }
        //     for (hi--; hi >= 0; hi--) { // 扁平化
        //         parent[stack[hi]] = i;
        //     }
        //     return i;
        // }
        // 路径压缩的简单写法
        private int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                // 小挂大
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                setSize--;
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public int setSize() {
            return setSize;
        }
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        var ans = new Problem_417_PacificAtlantic().pacificAtlantic1(heights);
        System.out.println(ans);

        var ans2 = new Problem_417_PacificAtlantic().pacificAtlantic2(heights);
        System.out.println(ans2);
    }


}
