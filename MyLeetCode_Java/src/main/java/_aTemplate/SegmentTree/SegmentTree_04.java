package _aTemplate.SegmentTree;

// 只支持查询最大值的线段树
// NOTE: 范围更新叠加(不覆盖前值)的情况下查询区间最大值, ref: Problem_218_SkyLine

import java.util.*;


public class SegmentTree_04 {

    // 更新值不会为-1, 用-1作为初始标记, 否则用后面的两个数组的

    public static class SegmentTree1 {
        private int n;
        private int[] max; // 高度数组
        private int[] update; // lazy数组

        public SegmentTree1(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int right) {
            return max(right, right, 1, n, 1);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        // 最大值数组跟lazy数组都需要用最大值刷新
        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != -1) {
                update[rt << 1] = Math.max(update[rt << 1], update[rt]);
                update[rt << 1 | 1] = Math.max(update[rt << 1 | 1], update[rt]);
                max[rt << 1] = Math.max(max[rt << 1], update[rt]);
                max[rt << 1 | 1] = Math.max(max[rt << 1 | 1], update[rt]);
                update[rt] = -1;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = Math.max(max[rt], C);
                update[rt] = Math.max(update[rt], C);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, max(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, max(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }

    }

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = Math.max(change[rt << 1], change[rt]);
                change[rt << 1 | 1] = Math.max(change[rt << 1 | 1], change[rt]);
                max[rt << 1] = Math.max(max[rt << 1], change[rt]);
                max[rt << 1 | 1] = Math.max(max[rt << 1 | 1], change[rt]);
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = Math.max(C, change[rt]);
                max[rt] = Math.max(C, max[rt]);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }
    }
}