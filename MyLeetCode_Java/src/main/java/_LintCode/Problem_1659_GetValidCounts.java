package _LintCode;

import java.util.Arrays;

public class Problem_1659_GetValidCounts {

    public static class IndexTree {

        private int[] tree;
        private int N;

        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        // 1~index 累加和是多少？
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        public int query(int L, int R) {
            return sum(R) - sum(L - 1);
        }

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    public int[] getAns(int[] a, int[][] q) {
        int N = a.length;
        int maxVal = a[0];
        for (int num : a) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        for (int[] p : q) {
            if (p[0] > maxVal) {
                maxVal = p[0];
            }
            if (p[1] > maxVal) {
                maxVal = p[1];
            }
        }
        IndexTree tree = new IndexTree(maxVal + 1);
        for (int i = 0; i < N; i++) {
            tree.add(a[i] + 1, 1);
        }
        int[] ans = new int[q.length];
        int idx = 0;
        for (int[] p : q) {
            ans[idx++] = tree.query(p[0] + 1, p[1] + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1, 90, 58, 34, 47, 25, 8, 72, 82, 38, 37, 68, 50, 11, 97, 68, 24, 65, 5};
        int[][] q = {{68, 100}, {1, 5}};
        var ans = new Problem_1659_GetValidCounts().getAns(a, q);
        System.out.println(Arrays.toString(ans));
    }
}
