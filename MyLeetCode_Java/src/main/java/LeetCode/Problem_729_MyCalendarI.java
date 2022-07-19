package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Problem_729_MyCalendarI {

    // 解法1: 暴力, 检查每一个区间, 看是否有重合
    static class MyCalendar {
        List<int[]> list = new ArrayList<>();

        public boolean book(int start, int end) {
            end--;
            for (int[] info : list) {
                int l = info[0];
                int r = info[1];
                if (start > r || end < l) continue;
                return false;
            }
            list.add(new int[]{start, end});
            return true;
        }
    }

    // 解法2: 扫描线
    static class MyCalendar1 {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int cnt = 0;
            for (int p : map.values()) { // 按时间点进行进行扫描
                cnt += p;
                if (cnt > 1) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    return false; // 有重叠
                }
            }
            return true;
        }
    }

    // 解法3: 动态开点线段树
    static class MyCalendar3 {
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

        DynamicSegmentTree dst = new DynamicSegmentTree((int) 1e9);

        public boolean book(int start, int end) {
            int max = dst.query(start + 1, end);
            if (max >= 1) {
                return false;
            }
            dst.update(start + 1, end, 1);
            return true;
        }
    }
}
