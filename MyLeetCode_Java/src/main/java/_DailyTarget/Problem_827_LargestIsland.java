package _DailyTarget;

import java.util.HashSet;
import java.util.Set;

public class Problem_827_LargestIsland {


    public static class UnionFind {

        private int[] parent; // 父节点
        private int[] size; // 代表节点的大小

        private int[][] grid;
        private int row;
        private int col;

        private static final int[] dirs = {0, -1, 0, 1, 0};

        public UnionFind(int[][] matrix) {
            this.grid = matrix;
            row = matrix.length;
            col = matrix.length;
            int N = row * col;
            parent = new int[N];
            size = new int[N];
            // 1.空间初始化
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++) {
                    int idx = i * col + j;
                    if (matrix[i][j] == 1) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }
            }
            // 2. 初始合并
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] != 1) continue;
                    for (int k = 1; k < dirs.length; k++) {
                        int nx = i + dirs[k - 1];
                        int ny = j + dirs[k];
                        union(i, j, nx, ny);
                    }
                }
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public int getSetSize(int i, int j) {
            if (i < 0 || i >= row || j < 0 || j >= col) {
                return 0;
            }
            int fa = parent[i * col + j];
            return size[fa];
        }

        // 按大小合并, 小挂大
        public void union(int a, int b, int c, int d) {
            if (a < 0 || a >= row || b < 0 || b >= col) {
                return;
            }
            if (c < 0 || c >= row || d < 0 || d >= col) {
                return;
            }
            if (grid[a][b] != 1 || grid[c][d] != 1) {
                return;
            }
            int i = a * col + b;
            int j = c * col + d;
            int f1 = find(i);
            int f2 = find(j);
            if (f1 == f2) return;
            if (size[f1] > size[f2]) {
                size[f1] += size[f2];
                parent[f2] = f1;
            } else {
                size[f2] += size[f1];
                parent[f1] = f2;
            }
        }

        // 没填充的都是0
        private int find(int i) {
            if (parent[i] == i) return i;
            // if (parent[i] == -1) return -1;
            return parent[i] = find(parent[i]);
        }

        private int findFather(int i, int j) {
            if (i < 0 || i >= row || j < 0 || j >= col) return -1;
            int idx = i * col + j;
            return find(idx);
        }

        public int getRoundSize(int i, int j) {
            int cnt = 0;
            // 查询上下左右4个方向
            Set<Integer> set = new HashSet<>();
            for (int d = 1; d < dirs.length; d++) {
                int nx = i + dirs[d - 1];
                int ny = j + dirs[d];
                int fa = findFather(nx, ny);
                // size[nx * col + ny] == 0, 没有初始化过的点(初始化的点至少是1)
                // set.contains(fa): 已经算过的点
                if (fa == -1 ||  size[nx * col + ny] == 0 || set.contains(fa)) continue;
                set.add(fa);
                cnt += size[fa];
            }
            return cnt;
        }
    }

    public int largestIsland(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 尝试每一个0点
                int cnt = 0;
                if (grid[i][j] == 0) {
                    cnt = uf.getRoundSize(i, j) + 1;
                } else {
                    cnt = uf.getSetSize(i, j); // 有可能本身的就很大
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // int[][] grid = {
        //         {1, 0},
        //         {0, 1}
        // };
        // int[][] grid = {
        //         {1, 1},
        //         {1, 0}
        // };
        int[][] grid = {
                {1, 0, 1},
                {0, 0, 0},
                {0, 1, 1}
        }; // 4
        var ans = new Problem_827_LargestIsland().largestIsland(grid);
        System.out.println(ans);
    }


}
