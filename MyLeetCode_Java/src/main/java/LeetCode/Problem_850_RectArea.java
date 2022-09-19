package LeetCode;

import java.util.Arrays;

// 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。
// 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标
// (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
// 计算平面中所有 rectangles 所覆盖的 总面积 。
// 任何被两个或多个矩形覆盖的区域应只计算 一次 。
// 返回 总面积 。因为答案可能太大，返回 10^9 + 7 的 模 。
// 本题测试链接 : https://leetcode.cn/problems/rectangle-area-ii/

// TAG: 扫描线 + 线段树

public class Problem_850_RectArea {


    public static int rectangleArea(int[][] rect) {
        int N = rect.length;
        //[ X位置, Y1, Y2, 类型]
        long[][] event = new long[N << 1][4];
        long max = 0;
        // X方向扫描线, Y方向生成线段树
        for (int i = 0; i < N; i++) {
            int x1 = rect[i][0];
            int y1 = rect[i][1] + 1; // 线段树从1开始, 这里抬高1正好
            int x2 = rect[i][2];
            int y2 = rect[i][3];
            event[i][0] = x1;
            event[i][1] = y1;
            event[i][2] = y2;
            event[i][3] = 1;
            event[i + N][0] = x2;
            event[i + N][1] = y1;
            event[i + N][2] = y2;
            event[i + N][3] = -1; // 用 +1, -1累加, 表示Y方向覆盖范围
            max = Math.max(max, y2);
        }

        int mod = (int) 1e9 + 7;
        N <<= 1;
        // [x, y1, y2, +1/-1]
        Arrays.sort(event, (a, b) -> a[0] <= b[0] ? -1 : 1); // 用大小比较, 避免越界
        DynamicSegmentTree dst = new DynamicSegmentTree(max);
        long preX = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            // NOTE: dst.query() : 一开始的值是0, 所以 0~arr[i][0]不会产生面积
            ans += dst.query() * (event[i][0] - preX);
            ans %= mod;
            preX = event[i][0];
            // +1, -1 : 更新y方向的覆盖范围
            dst.add(event[i][1], event[i][2], event[i][3]);
        }
        return (int) ans;
    }

    public static class Node {
        public long cover; // 懒更新标记
        public long len; // 相当于sum
        public Node left;
        public Node right;
    }

    public static class DynamicSegmentTree {
        public Node root;
        public long size;

        public DynamicSegmentTree(long max) {
            root = new Node();
            size = max;
        }

        public void add(long L, long R, long cover) {
            add(root, 1, size, L, R, cover);
        }

        // cover: +1, -1
        // L..R 实际的追加范围
        // 因为懒不住,就没有懒了
        private void add(Node cur, long l, long r, long L, long R, long cover) {
            if (L <= l && R >= r) { //如果L...R把当前节点管辖范围全包
                cur.cover += cover;
            } else {
                if (cur.left == null) {
                    cur.left = new Node();
                }
                if (cur.right == null) {
                    cur.right = new Node();
                }
                long m = l + ((r - l) >> 1);
                if (L <= m) {
                    add(cur.left, l, m, L, R, cover);
                }
                if (R > m) {
                    add(cur.right, m + 1, r, L, R, cover);
                }
            }
            pushUp(cur, l, r);
        }

        private void pushUp(Node cur, long l, long r) {
            if (cur.cover > 0) {
                cur.len = r - l + 1;
            } else {
                cur.len = (cur.left != null ? cur.left.len : 0) + (cur.right != null ? cur.right.len : 0);
            }
        }

        public long query() {
            return root.len;
        }
    }

    public static void main(String[] args) {
        int[][] rectangles = {{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}}; // 6
        // int[][] rectangles = {{1,0,2,3},{1,0,3,1}}; // 4
        var ans = rectangleArea(rectangles);
        System.out.println(ans);
    }

}
