package LeetCode.Contest.LCUP2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_003_ColorBinayTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int getNumber(TreeNode root, int[][] ops) {
        List<Integer> blist = new ArrayList<>();
        inOderTraversal(root, blist);
        int N = blist.size();
        // 0~N-1的一个有序数组
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(blist.get(i), i);
        }
        // 构建线段树
        SegmentTree seg = new SegmentTree(N);
        for (int[] op : ops) {
            int type = op[0];
            int fromIdx = map.get(op[1]) + 1;
            int toIdx = map.get(op[2]) + 1;
            if (type == 0) { // Blue
                // update(int L, int R, int C, int l, int r, int rt)
                seg.update(fromIdx, toIdx, 0, 1, N, 1);
            } else {
                seg.update(fromIdx, toIdx, 1, 1, N, 1);
            }
        }
        int ans = seg.query(1, N, 1, N, 1);
        return ans;
    }

    private void inOderTraversal(TreeNode root, List<Integer> blist) {
        if (root == null) {
            return;
        }
        inOderTraversal(root.left, blist);
        blist.add(root.val);
        inOderTraversal(root.right, blist);
    }

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

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(4);
        // root.left = new TreeNode(2);
        // root.left.left = new TreeNode(1);
        // root.right = new TreeNode(7);
        // root.right.left = new TreeNode(5);
        // root.right.left.right = new TreeNode(6);
        // int[][] ops = {{0, 2, 2}, {1, 1, 5}, {0, 4, 5}, {1, 5, 7}};

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        int[][] ops = { {1, 2, 4}, {1, 1, 3}, {0, 3, 5}};


        var ans = new Problem_003_ColorBinayTree().getNumber(root, ops);
        System.out.println(ans);

    }
}
