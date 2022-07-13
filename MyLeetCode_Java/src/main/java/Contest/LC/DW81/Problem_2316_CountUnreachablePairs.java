package Contest.LC.DW81;

import java.util.*;

public class Problem_2316_CountUnreachablePairs {

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

        public int getSetSize(int i) {
            return size[i];
        }

        public int setSize() {
            return setSize;
        }
    }

    /**
     * https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/solution/by-zhy-yg6d1/
     * 一个连通集中的节点之间都能互相到达，且无法互相到达的节点个数都是相等的。
     * 设连通集内节点个数=c，每个连通集贡献无法互相到达节点个数=(n-c)*c
     * 由于双向计算2次，答案除以2。
     */
    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        // IMP: 找到所有集合的大小
        for (int i = 0; i < n; i++) {
            int fa = uf.find(i);
            map.put(fa, uf.getSetSize(fa));
        }
        long ans = 0;
        for (int num : map.values()) {
            ans += (long) num * (n - num);
        }
        return ans >> 1;
    }

    public static void main(String[] args) {
        // int n = 3;
        // int[][] edges = {{0, 1}, {0, 2}, {1, 2}}; // 0

        int n = 7;
        int[][] edges = {{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}}; // 14
        var ans = new Problem_2316_CountUnreachablePairs().countPairs(n, edges);
        System.out.println(ans);

    }

}
