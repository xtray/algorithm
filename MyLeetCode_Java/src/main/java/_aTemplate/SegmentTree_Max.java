package _aTemplate;

// 只支持查询最大值的线段树

import java.util.Arrays;

public class SegmentTree_Max {

    private int n;
    private int[] max;
    private int[] update;

    public SegmentTree_Max(int maxSize) {
        n = maxSize + 1;
        max = new int[n << 2];
        update = new int[n << 2];
        Arrays.fill(update, -1);
    }

    public int get(int index) {
        return max(index, index, 1, n, 1);
    }

    public void update(int index, int c) {
        update(index, index, c, 1, n, 1);
    }

    public int max(int right) {
        return max(1, right, 1, n, 1);
    }

    private void pushUp(int rt) {
        max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
    }

    private void pushDown(int rt, int ln, int rn) {
        if (update[rt] != -1) {
            update[rt << 1] = update[rt];
            max[rt << 1] = update[rt];
            update[rt << 1 | 1] = update[rt];
            max[rt << 1 | 1] = update[rt];
            update[rt] = -1;
        }
    }

    private void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            max[rt] = C;
            update[rt] = C;
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