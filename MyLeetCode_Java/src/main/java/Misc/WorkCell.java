package Misc;

import java.util.ArrayList;
import java.util.List;

public class WorkCell {


    public static class SegmentTree {
        private int[] sum;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            sum = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];

        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = (r - l + 1) * C;
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

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
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

    // circles[][]
    // [x, y, r], 0<=y<=b
    // [0 1 1]
    // [0 3 1]
    // [0 2 2]
    // (a,b) -- (c,d)
    boolean mix(int a, int b, int r1, int c, int d, int r2) {
        return Math.sqrt(Math.pow(a - c, 2) + Math.pow(b - d, 2)) <= r1 + r2;
    }

    private SegmentTree[] seg;
    private int N;
    private int b;

    public WorkCell(int N, int b) {
        seg = new SegmentTree[N + 1]; // 1~1000
        for (int i = 1; i <= N; i++) {
            seg[i] = new SegmentTree(N);
        }
        this.N = N;
        this.b = b;
    }

    public int fallingCells(int n, int b, int[][] circles) {

        List<int[]> cellList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean check = checkAndUpdate(cellList, circles[i]);
            if (!check) {
                return i; //i+1 -1
            }
        }
        return n;
    }

    private boolean checkAndUpdate(List<int[]> cellList, int[] circle) {

        seg[circle[2]].update(Math.max(0, circle[1] - circle[2]), Math.min(b, circle[1] + circle[2]), 1, 1, N, 1);

        for (int[] cell : cellList) { // 两圆相交， 则更新对应区间
            if (mix(cell[0], cell[1], cell[2], circle[0], circle[1], circle[2])) {
                // update segment tree
                int min = Math.min(circle[1] - circle[2], cell[1] - cell[2]);
                min = Math.max(0, min);
                int max = Math.max(circle[1] + circle[2], cell[1] + cell[2]);
                max = Math.min(max, b);
                seg[cell[2]].update(min, max, 1, N, 1, 1);
                seg[circle[2]].update(min, max, 1, N, 1, 1);
            }
        }

        // 检测是否堵塞
        for (int i = 1; i <= N; i++) {
            int count = seg[i].query(1, b, 1, N, 1);
            if (count == b) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int b = 10;
        int[][] circles = {{0, 1, 1}, {0, 3, 1}, {0, 4, 1}, {0, 7, 2}, {0, 8, 2}};
        WorkCell wc = new WorkCell(1000, b);
        int res = wc.fallingCells(n, b, circles);
        System.out.println(res);
    }

}
