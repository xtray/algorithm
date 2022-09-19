package _aTemplate.SegmentTree;

public class DynamicSegmentTree_03 {

    // 只支持区间更新 + 求最区间最大值的开点线段树
    // 区别于RMQ(求区间极值, 但不支持更新)

    public static class Node {
        public int max;
        public int lazy;
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

        private void pushUp(Node node) {
            node.max = Math.max(node.left.max, node.right.max);
        }

        private void pushDown(Node p) {
            if (p.left == null) {
                p.left = new Node();
            }
            if (p.right == null) {
                p.right = new Node();
            }
            if (p.lazy != 0) {
                p.left.lazy += p.lazy;
                p.right.lazy += p.lazy;
                p.left.max += p.lazy;
                p.right.max += p.lazy;
                p.lazy = 0;
            }
        }

        // 当前节点c, 管辖范围l...r
        // 从s...e范围上都+v
        private void update(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && r <= e) { // 范围全包, 不下放
                c.lazy += v;
                c.max += v;
                return;
            }
            // 任务不能全包
            pushDown(c);
            int mid = (l + r) >> 1;
            if (s <= mid) {
                update(c.left, l, mid, s, e, v);
            }
            if (e > mid) {
                update(c.right, mid + 1, r, s, e, v);
            }
            pushUp(c);
        }

        // 当前节点c, 管辖范围l...r
        // 查询s...e范围上的最大值
        private int query(Node c, int l, int r, int s, int e) {
            if (s <= l && r <= e) { // 范围全包, 返回当前节点保存的值
                return c.max;
            }
            pushDown(c);
            int mid = (l + r) >> 1;
            int p1 = 0;
            int p2 = 0;
            if (s <= mid) {
                p1 = query(c.left, l, mid, s, e);
            }
            if (e > mid) {
                p2 = query(c.right, mid + 1, r, s, e);
            }
            return Math.max(p1, p2);
        }

        // 开放给外部调用的函数
        public void update(int s, int e, int v) {
            update(root, 1, size, s, e, v);
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }
    }
}
