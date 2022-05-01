package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_305_IslandNum2 {

    // NOTE: 并查集的解法!!
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();

        if (positions == null || positions.length == 0 || positions[0] == null || positions[0].length == 0) {
            return ans;
        }
        UnionFind uf = new UnionFind(m, n);
        for (int[] p : positions) {
            int cnt = uf.connect(p[0], p[1]);
            ans.add(cnt);
        }
        return ans;
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, -1, 0, 1, 0};

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        private int[] stack; // 数组做栈
        private int setSize; // 一共有多少个集合

        private int row;
        private int col;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            int N = m * n;
            parent = new int[N];
            size = new int[N];
            stack = new int[N];
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
            if (c < 0 || c >= row || d < 0 || d >= col) {
                return;
            }
            int idx1 = a * col + b;
            int idx2 = c * col + d;
            if (size[idx1] == 0 || size[idx2] == 0) {
                // 不是正常的初始化过的点, == 0的点即不是岛, 不需要处理
                return;
            }
            int f1 = find(idx1);
            int f2 = find(idx2);
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
    }

}
