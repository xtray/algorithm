package _DailyTarget;

import java.util.ArrayList;
import java.util.*;

public class Problem_1893_CoveredNumbers {


    // 差分数组, 区间修改 + 单点查询
    public boolean isCovered0(int[][] ranges, int left, int right) {
        int[] diff = new int[52];   // 差分数组
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和
        int sum = 0;
        for (int i = 1; i <= 50; ++i) {
            sum += diff[i];
            // 检查left~right中的每一个i
            if (i >= left && i <= right && sum == 0) {
                return false;
            }
        }
        return true;
    }


    // 区间合并 + 二分
    public boolean isCovered(int[][] ranges, int left, int right) {
        // 1.首先要区间合并
        Arrays.sort(ranges, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        int N = ranges.length;
        int preS = ranges[0][0];
        int preE = ranges[0][1];
        for (int i = 1; i < N; i++) {
            int curS = ranges[i][0];
            int curE = ranges[i][1];
            if (curS <= preE) {
                if (curE <= preE) {
                    continue; // 被包含, 跳过
                } else {
                    preE = curE;
                }
            } else { // curS > preE
                // 结算前一个
                list.add(new int[]{preS, preE});
                preS = curS;
                preE = curE;
            }
        }
        list.add(new int[]{preS, preE});
        N = list.size();
        for (int i = left; i <= right; i++) {
            int L = 0;
            int R = N - 1;
            int pos = -1;
            // 找<=i最右的
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (list.get(mid)[0] <= i) {
                    pos = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            if (pos == -1 || list.get(pos)[1] < i) {
                return false;
            }
        }
        return true;
    }


    // 树状数组
    public boolean isCovered1(int[][] ranges, int left, int right) {

        IndexTree tree = new IndexTree(50);
        for (int[] r : ranges) {
            for (int i = r[0]; i <= r[1]; i++) {
                tree.add(i, 1);
            }
        }
        for (int i = left; i <= right; i++) {
            int cnt = tree.sum(i) - tree.sum(i - 1);
            if (cnt == 0) return false;
        }
        return true;
    }

    public static class IndexTree {

        private int[] tree;
        private int N;

        // 0位置弃而不用！
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

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    // 线段树
    public boolean isCovered2(int[][] ranges, int left, int right) {
        int N = 50;
        SegmentTree tree = new SegmentTree(N);
        for (int[] r : ranges) {
            tree.add(r[0], r[1], 1, 1, N, 1);
        }
        for (int i = left; i <= right; i++) {
            int cnt = (int) tree.query(i, i, 1, N, 1);
            if (cnt == 0) return false;
        }
        return true;
    }

    public static class SegmentTree {
        private int[] sum; // 维护区间和
        private int[] lazy; // 懒更新标记

        public SegmentTree(int size) {
            int MAXN = size + 1; // 0位置弃而不用
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
        }

        // rt stand for root
        private void pushUP(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        // L~R, C 任务！
        // l~r --> rt
        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUP(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        int[][] ranges = {{25, 42}, {7, 14}, {2, 32}, {25, 28}, {39, 49}, {1, 50}, {29, 45}, {18, 47}};
        int L = 15;
        int R = 38;
        var ans = new Problem_1893_CoveredNumbers().isCovered(ranges, L, R);
        System.out.println(ans);
    }
}
