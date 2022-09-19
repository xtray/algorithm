package _DailyTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_952_LargestComponent {

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
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

        // 路径压缩的简单写法
        private int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
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

        public int getMaxSet() {
            int ans = 0;
            for (int num : size) {
                ans = Math.max(ans, num);
            }
            return ans;
        }
    }

    // IMP: 一次枚举一对因数, O(N * √M)
    public int largestComponentSize(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // 因子: 下标
        Map<Integer, List<Integer>> map = new HashMap<>(); // 每个因子拥有的下标
        for (int i = 0; i < N; i++) {
            // 因数检查范围 (1, √num]
            for (int j = 1; j * j <= nums[i]; j++) {
                if (nums[i] % j == 0) { // nums[i] 含有j的因子
                    if (j != 1) {
                        map.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                    }
                    int other = nums[i] / j;
                    if (other != 1) {
                        map.computeIfAbsent(other, k -> new ArrayList<>()).add(i);
                    }
                }
            }
        }

        UnionFind uf = new UnionFind(N);
        for (List<Integer> list : map.values()) {
            for (int i = 1; i < list.size(); i++) {
                uf.union(list.get(0), list.get(i));
            }
        }
        return uf.getMaxSet();
    }

    // TLE
    public int largestComponentSize0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (gcd(nums[i], nums[j]) > 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getMaxSet();
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 15, 35};
        var ans = new Problem_952_LargestComponent().largestComponentSize(nums);
        System.out.println(ans);
    }
}
