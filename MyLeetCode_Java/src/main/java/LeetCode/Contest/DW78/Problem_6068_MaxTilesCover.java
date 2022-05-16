package LeetCode.Contest.DW78;

// https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet/submissions/

public class Problem_6068_MaxTilesCover {

    public static class Node {
        public int sum;
        public int lazy;
        public int change;
        public boolean update;
        public Node left;
        public Node right;
    }

    public static class DynamicSegmentTree {
        public Node root;
        public int size;

        public DynamicSegmentTree(int max) {
            root = new Node();
            size = max;
        }

        private void pushUp(Node c) {
            c.sum = c.left.sum + c.right.sum;
        }

        private void pushDown(Node p, int ln, int rn) {
            if (p.left == null) {
                p.left = new Node();
            }
            if (p.right == null) {
                p.right = new Node();
            }
            if (p.update) {
                p.left.update = true;
                p.right.update = true;
                p.left.change = p.change;
                p.right.change = p.change;
                p.left.lazy = 0;
                p.right.lazy = 0;
                p.left.sum = p.change * ln;
                p.right.sum = p.change * rn;
                p.update = false;
            }
            if (p.lazy != 0) {
                p.left.lazy += p.lazy;
                p.right.lazy += p.lazy;
                p.left.sum += p.lazy * ln;
                p.right.sum += p.lazy * rn;
                p.lazy = 0;
            }
        }

        public void update(int s, int e, int v) {
            update(root, 1, size, s, e, v);
        }

        private void update(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && r <= e) {
                c.update = true;
                c.change = v;
                c.sum = v * (r - l + 1);
                c.lazy = 0;
            } else {
                int mid = (l + r) >> 1;
                pushDown(c, mid - l + 1, r - mid);
                if (s <= mid) {
                    update(c.left, l, mid, s, e, v);
                }
                if (e > mid) {
                    update(c.right, mid + 1, r, s, e, v);
                }
                pushUp(c);
            }
        }

        public void add(int s, int e, int v) {
            add(root, 1, size, s, e, v);
        }

        private void add(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && r <= e) {
                c.sum += v * (r - l + 1);
                c.lazy += v;
            } else {
                int mid = (l + r) >> 1;
                pushDown(c, mid - l + 1, r - mid);
                if (s <= mid) {
                    add(c.left, l, mid, s, e, v);
                }
                if (e > mid) {
                    add(c.right, mid + 1, r, s, e, v);
                }
                pushUp(c);
            }
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(Node c, int l, int r, int s, int e) {
            if (s <= l && r <= e) {
                return c.sum;
            }
            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - mid);
            int ans = 0;
            if (s <= mid) {
                ans += query(c.left, l, mid, s, e);
            }
            if (e > mid) {
                ans += query(c.right, mid + 1, r, s, e);
            }
            return ans;
        }

    }

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {

        int N = (int) 1e9 + 1; // NOTE: 注意要包括1e9!!
        DynamicSegmentTree dst = new DynamicSegmentTree(N);
        for (int[] t : tiles) {
            int from = t[0] + 1;
            int to = t[1] + 1;
            dst.update(from, to, 1);
        }
        int ans = 0;
        for (int[] t : tiles) {
            int start = t[0] + 1;
            int end = start + carpetLen - 1;
            int cur = dst.query(start, end);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[][] tiles = {{10, 11}, {1, 1}};
        // int len = 2;
        // int[][] tiles = {{1, 5}, {10, 11}, {12, 18}, {20, 25}, {30, 32}};
        // int len = 10;
        int[][] tiles = {{1, 1000000000}};
        int len = 1000000000;
        var ans = new Problem_6068_MaxTilesCover().maximumWhiteTiles(tiles, len);
        System.out.println(ans);
    }
}
