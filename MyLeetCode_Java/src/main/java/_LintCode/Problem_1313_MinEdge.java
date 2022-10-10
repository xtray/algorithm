package _LintCode;

import java.util.*;

public class Problem_1313_MinEdge {

    // 将边集按降序排序，然后遍历边集，如果当前边的两顶点已经在一个组里，则当前边就是最优解。
    // 这是因为我们要做的就是尽量让权值较大的边的顶点分在不同组中，所以当分组出现矛盾时，说明已经不能再分割这条边。
    public int getMiniumValue(int[][] graph) {

        int N = graph.length;
        int M = graph[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int w = graph[i][j];
                edges.add(new int[]{i, j, w});
            }
        }
        edges.sort((o1, o2) -> o2[2] - o1[2]);
        UnionFind uf = new UnionFind(N);
        // 每个元素的组员
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new HashSet<>());
        }

        for (int[] cur : edges) {
            int i = cur[0];
            int j = cur[1];
            int w = cur[2];
            if (uf.isSameSet(i, j)) {
                return w;
            }
            int res = check(uf, list, N, i, j, w);
            if (res != Integer.MIN_VALUE) {
                return res;
            }
            res = check(uf, list, N, j, i, w);
            if (res != Integer.MIN_VALUE) {
                return res;
            }

        }
        return 0;
    }

    private int check(UnionFind uf, List<Set<Integer>> list, int N, int i, int j, int w) {
        if (!list.get(i).contains(j)) {
            for (int k : list.get(i)) {
                uf.union(k, j);
                break;
            }
            list.get(i).add(j);
        }
        return list.get(i).size() == N - 1 ? w : Integer.MIN_VALUE;
    }


    public static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 路径压缩的简单写法
        private int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
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
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }
    }

    public static void main(String[] args) {
        // int[][] grid = {{0, 270, 60, 20}, {270, 0, 35, 90}, {60, 35, 0, 100}, {20, 90, 100, 0}}; // 35
        int[][] grid = {{0, 1, 3, 40001}, {1, 0, 2, 40004}, {3, 2, 0, 40004}, {40001, 40004, 40004, 0}}; // 40001
        var ans = new Problem_1313_MinEdge().getMiniumValue(grid);
        System.out.println(ans);
    }
}
