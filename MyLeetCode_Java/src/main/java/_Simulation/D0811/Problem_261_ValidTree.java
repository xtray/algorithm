package _Simulation.D0811;


import java.util.*;

public class Problem_261_ValidTree {

    // https://leetcode.cn/problems/graph-valid-tree/solution/java-pan-duan-tu-zhong-you-huan-de-san-chong-fang-/

    // 1.检查数有没有环路
    // 2.检测有没有森林
    // 并查集: 如果合并的

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

        public int setSize() {
            return setSize;
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }
    }

    // https://leetcode.cn/problems/graph-valid-tree/solution/java-pan-duan-tu-zhong-you-huan-de-san-chong-fang-/

    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            if (uf.isSameSet(a, b)) { // 有环
                return false;
            }
            uf.union(a, b);
        }
        return uf.setSize() == 1; // 只有一个集合
    }


    // BFS 邻接表遍历
    // 树的定义: n个结点，n-1条边的连通无向图
    public boolean validTree1(int n, int[][] edges) {
        // if (edges.length >= n && edges.length < n - 1) {
        // 边数>=n: 一定有环, 边数<n-1: 森林
        if (edges.length != n - 1) {
            return false;
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        // 邻接表
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        // BFS 检测有没有森林
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list.get(cur)) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        return set.size() == n;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        var ans = new Problem_261_ValidTree().validTree(n, edges);
        System.out.println(ans);

        var ans1 = new Problem_261_ValidTree().validTree1(n, edges);
        System.out.println(ans1);
    }
}
