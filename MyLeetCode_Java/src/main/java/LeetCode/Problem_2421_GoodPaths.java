package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_2421_GoodPaths {

    public int numberOfGoodPaths(int[] vals, int[][] edges) {

        int N = vals.length;
        UnionFind uf = new UnionFind(N);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new int[]{vals[i], i});
        }
        list.sort((o1, o2) -> o1[0] - o2[0]);
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int from = e[0];
            int to = e[1];
            graph.get(from).add(to); // 无向图
            graph.get(to).add(from);
        }

        int ans = 0;
        // 从小到大遍历list, 找到相等连续区间
        for (int i = 0; i < N; i++) {
            int j = i + 1;
            for (; j < N && list.get(j)[0] == list.get(i)[0]; j++) ;
            for (int k = i; k < j; k++) { // 处理相等区间
                int cur = list.get(k)[1]; // 下标
                for (int next : graph.get(cur)) {
                    // 把比当前数小的下级节点都合并
                    if (vals[next] <= vals[cur]) {
                        uf.union(cur, next);
                    }
                }
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int k = i; k < j; k++) {
                int fa = uf.find(list.get(k)[1]);
                map.put(fa, map.getOrDefault(fa, 0) + 1);
            }

            // 分别统计每个连通的集合数目C_N_2 + N
            for (int cnt : map.values()) {
                ans += cnt * (cnt + 1) / 2; // 累加了本身
            }
            i = j - 1;

        }
        return ans;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int setSize; // 一共有多少个集合

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            setSize = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

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
    }
}
