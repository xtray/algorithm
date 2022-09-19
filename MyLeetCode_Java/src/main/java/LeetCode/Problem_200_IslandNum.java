package LeetCode;

// IMP: 经典问题, floodfill解法, 尤其是用并查集的解法

public class Problem_200_IslandNum {

    // 解法1: floodfill
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        // 从每一个位置开始尝试
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    infect(grid, i, j);
                }
            }
        }
        return ans;
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, 0};

    private void infect(char[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        for (int k = 1; k < dirs.length; k++) {
            int x = i + dirs[k - 1];
            int y = j + dirs[k];
            infect(grid, x, y);
        }
    }

    // NOTE: 并查集的解法!! 每一个位置只查他自己左边跟上边, 从左往右从上往下所有的位置都查一遍，该合的合
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        // 处理0行
        for (int j = 1; j < M; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                uf.union(j, j - 1);
            }
        }
        // 处理0列
        for (int i = 1; i < N; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                uf.union(i * M, (i - 1) * M);
            }
        }
        // 普遍位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                    uf.union(i * M + j, i * M + j - 1);
                }
                if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                    uf.union(i * M + j, (i - 1) * M + j);
                }
            }
        }
        return uf.setSize();
    }

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        private int[] stack; // 数组做栈
        private int setSize; // 一共有多少个集合

        public UnionFind(char[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            int N = row * col;
            parent = new int[N];
            size = new int[N];
            stack = new int[N];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // NOTE: 只有1的时候才去初始化并查集, 因为只有是1的点才是需要处理的集合
                    // 不是1的位置, 不去创建
                    if (grid[i][j] == '1') {
                        int idx = i * col + j;
                        parent[idx] = idx;
                        size[idx] = 1;
                        setSize++;
                    }
                }
            }
        }

        // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // 这个过程要做路径压缩
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                stack[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) { // 扁平化
                parent[stack[hi]] = i;
            }
            return i;
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

        public int setSize() {
            return setSize;
        }
    }

    public static void main(String[] args) {
        // char[][] grid = {
        //         {'1', '1', '0', '1', '1'},
        //         {'1', '0', '0', '0', '0'},
        //         {'0', '0', '0', '0', '1'},
        //         {'1', '1', '0', '1', '1'}};
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        var ans = new Problem_200_IslandNum().numIslands2(grid);
        System.out.println(ans);
    }
}
