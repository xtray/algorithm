package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_2322_MinScoreAfterRemoval {

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


    // C_N_2 每次删除两条边做并查集
    public int minimumScore(int[] nums, int[][] edges) {
        int ans = Integer.MAX_VALUE;
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int cur = process(nums, edges, i, j);
                ans = Math.min(ans, cur);
            }
        }
        return ans;
    }

    private int process(int[] nums, int[][] edges, int i, int j) {
        int N = nums.length;
        UnionFind uf = new UnionFind(N);
        for (int k = 0; k < edges.length; k++) {
            if (k != i && k != j) {
                uf.union(edges[k][0], edges[k][1]);
            }
        }

        // 三个集合
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int k = 0; k < N; k++) {
            int fa = uf.find(k);
            map.computeIfAbsent(fa, key -> new ArrayList<>()).add(nums[k]);
        }
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            int cur = getXorVal(list);
            maxValue = Math.max(maxValue, cur);
            minValue = Math.min(minValue, cur);
        }
        return maxValue - minValue;
    }

    private int getXorVal(List<Integer> list) {
        int ans = 0;
        for (int num : list) {
            ans ^= num;
        }
        return ans;
    }

    //////////////////////////标准解法
    public static int cnt;

    public static int minimumScore1(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] dfn = new int[n];
        int[] xor = new int[n];
        int[] size = new int[n];
        cnt = 1;
        dfs(nums, graph, 0, dfn, xor, size);
        int ans = Integer.MAX_VALUE, m = edges.length, cut1, cut2, pre, pos, part1, part2, part3, max, min;
        for (int i = 0; i < m; i++) {
            cut1 = dfn[edges[i][0]] < dfn[edges[i][1]] ? edges[i][1] : edges[i][0];
            for (int j = i + 1; j < m; j++) {
                cut2 = dfn[edges[j][0]] < dfn[edges[j][1]] ? edges[j][1] : edges[j][0];
                pre = dfn[cut1] < dfn[cut2] ? cut1 : cut2;
                pos = pre == cut1 ? cut2 : cut1;
                part1 = xor[pos];
                if (dfn[pos] < dfn[pre] + size[pre]) {
                    part2 = xor[pre] ^ xor[pos];
                    part3 = xor[0] ^ xor[pre];
                } else {
                    part2 = xor[pre];
                    part3 = xor[0] ^ part1 ^ part2;
                }
                max = Math.max(Math.max(part1, part2), part3);
                min = Math.min(Math.min(part1, part2), part3);
                ans = Math.min(ans, max - min);
            }
        }
        return ans;
    }

    public static void dfs(int[] nums, ArrayList<ArrayList<Integer>> graph, int cur, int[] dfn, int[] xor, int[] size) {
        dfn[cur] = cnt++;
        xor[cur] = nums[cur];
        size[cur] = 1;
        for (int next : graph.get(cur)) {
            if (dfn[next] == 0) {
                dfs(nums, graph, next, dfn, xor, size);
                xor[cur] ^= xor[next];
                size[cur] += size[next];
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 5, 5, 4, 11};
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        var ans = new Problem_2322_MinScoreAfterRemoval().minimumScore(nums, edges);
        System.out.println(ans);
    }
}
