package Contest.LC.DW78;

// https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet/submissions/

import java.util.Arrays;

public class Problem_2271_MaxTilesCover {

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


    // IMP: 滑动窗口
    // 以每一个左边界做为开始的位置的贪心处理
    // https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet/solution/by-hu-li-hu-wai-48y7/
    public int maximumWhiteTiles1(int[][] tiles, int carpetLen) {
        if (tiles == null || tiles.length == 0 || tiles[0] == null || tiles[0].length == 0) {
            return 0;
        }
        // 按开始位置排序
        Arrays.sort(tiles, (o1, o2) -> o1[0] - o2[0]);
        int N = tiles.length;
        int sum = 0;
        int ans = 0;
        int L = 0;
        int R = 0;
        while (R < N) {
            int end = tiles[L][0] + carpetLen - 1;
            int[] cur = tiles[R];
            int curS = cur[0];
            int curE = cur[1];
            if (curE <= end) { // 当前区块被end全包, 入窗口
                sum += curE - curS + 1;
                ans = Math.max(ans, sum); // NOTE: curE == end的时候,需要采集一次答案
                R++;
            } else { // end < curE
                if (curS <= end) { // 当前区块部分被end锁定
                    // 结算答案 (没有全包, 不能累加到sum里)
                    ans = Math.max(ans, sum + end - curS + 1);
                }
                // 调整到下一个区间开头
                // R不用动: 因为
                // 1: curS <= end: 部分包, sum没有累加这部分,
                // 2: curS > end: curS~curE sum没有累加过
                sum -= tiles[L][1] - tiles[L][0] + 1;
                L++;
            }
        }
        return ans;
    }

    // TODO: 前缀和 + 二分
    public int maximumWhiteTiles2(int[][] tiles, int carpetLen) {
        if (tiles == null || tiles.length == 0 || tiles[0] == null || tiles[0].length == 0) {
            return 0;
        }
        // 按开始位置排序
        Arrays.sort(tiles, (o1, o2) -> o1[0] - o2[0]);

        int N = tiles.length;
        // 区间的前缀和数组
        long[] sum = new long[N];
        sum[0] = tiles[0][1] - tiles[0][0] + 1;
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + tiles[i][1] - tiles[i][0] + 1;
        }

        int idx = 0;
        long ans = 0;

        while (idx < N) {
            int[] cur = tiles[idx];
            int curS = cur[0];
            int curE = cur[1];
            int index = getIndex(sum, curS + carpetLen - 1);

        }
        return (int) ans;
    }

    // 二分, 找到 >= end i位置
    private int getIndex(long[] sum, int end) {
        int N = sum.length;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);

        }

        return 0;
    }

    public static void main(String[] args) {
        // int[][] tiles = {{10, 11}, {1, 1}};
        // int len = 2; // 2
        // int[][] tiles = {{1, 5}, {10, 11}, {12, 18}, {20, 25}, {30, 32}};
        // int len = 10; // 9
        int[][] tiles = {{1, 1000000000}};
        int len = 1000000000; // 1000000000
        var ans = new Problem_2271_MaxTilesCover().maximumWhiteTiles1(tiles, len);
        System.out.println(ans);
    }
}
