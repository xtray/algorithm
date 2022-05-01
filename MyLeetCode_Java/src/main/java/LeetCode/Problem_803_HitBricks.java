package LeetCode;

// https://leetcode-cn.com/problems/bricks-falling-when-hit/

// NOTE: 逆序考察的思路, 回看!!

public class Problem_803_HitBricks {

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                grid[hits[i][0]][hits[i][1]] = 2;
            }
        }
        UnionFind uf = new UnionFind(grid);
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = uf.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, -1, 0, 1, 0};

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] stack; // 数组做栈
        private int setSize; // 一共有多少个集合

        private int row;
        private int col;
        private int cellingAll; // 有多少块砖，连到了天花板上
        private int[][] grid; // 原始矩阵，因为炮弹的影响，1 -> 2
        // cellingSet[i] = true; i 是代表节点，所在的集合是天花板集合
        private boolean[] cellingSet;

        public UnionFind(int[][] matrix) {
            // 1. 初始化
            row = matrix.length;
            col = matrix[0].length;
            grid = matrix;
            int N = row * col;

            parent = new int[N];
            size = new int[N];
            stack = new int[N];
            cellingSet = new boolean[N];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        int idx = i * col + j;
                        parent[idx] = idx;
                        size[idx] = 1;
                        if (i == 0) {
                            cellingSet[idx] = true;
                            cellingAll++;
                        }
                    }
                }
            }
            // 2. 初始合并
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    for (int k = 0; k < dirs.length; k += 2) {
                        int nx = i + dirs[k];
                        int ny = j + dirs[k + 1];
                        union(i, j, nx, ny);
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

        // (a,b)  (c,d)
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
            int idx1 = a * col + b;
            int idx2 = c * col + d;
            int f1 = find(idx1);
            int f2 = find(idx2);
            if (f1 != f2) {
                int size1 = size[f1];
                int size2 = size[f2];
                boolean status1 = cellingSet[f1];
                boolean status2 = cellingSet[f2];
                // 小挂大
                if (size1 >= size2) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                    if (status1 ^ status2) {
                        cellingSet[f1] = true;
                        cellingAll += status1 ? size2 : size1;
                    }
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                    if (status1 ^ status2) {
                        cellingSet[f2] = true;
                        cellingAll += status1 ? size2 : size1;
                    }
                }
                setSize--;
            }
        }

        public int setSize() {
            return setSize;
        }

        public int cellingNum() {
            return cellingAll;
        }

        // 掉落(i,j)点, 尝试4个方向合并
        public int connect(int i, int j) {
            int idx = i * col + j;
            if (size[idx] == 0) { // 第一次掉落, !=0 的重复掉落返回前值即可
                // 动态初始化
                parent[idx] = idx;
                size[idx] = 1;
                setSize++;
                // 上下左右四个方向尝试合并
                for (int k = 0; k < dirs.length; k += 2) {
                    int nx = i + dirs[k];
                    int ny = j + dirs[k + 1];
                    union(i, j, nx, ny);
                }
            }
            return setSize;
        }

        public int finger(int i, int j) {
            grid[i][j] = 1;
            int idx = i * col + j;
            if (i == 0) { // 天花板
                cellingSet[idx] = true;
                cellingAll++;
            }
            parent[idx] = idx;
            size[idx] = 1;
            int preCnt = cellingAll; // 追加前
            // union(i, j, i - 1, j);
            // union(i, j, i + 1, j);
            // union(i, j, i, j - 1);
            // union(i, j, i, j + 1);
            // 上下左右四个方向尝试合并
            for (int k = 0; k < dirs.length; k += 2) {
                int nx = i + dirs[k];
                int ny = j + dirs[k + 1];
                union(i, j, nx, ny);
            }
            int curCnt = cellingAll;
            // NOTE: 炮弹砖块是否在第零行处理逻辑不一样
            if (i == 0) {
                return curCnt - preCnt;
            } else {
                return curCnt == preCnt ? 0 : curCnt - preCnt - 1;
            }
        }
    }
}
