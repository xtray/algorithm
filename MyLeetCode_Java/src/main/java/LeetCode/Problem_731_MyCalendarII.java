package LeetCode;

import java.util.*;

public class Problem_731_MyCalendarII {

    // 解法1: 扫描线
    class MyCalendarTwo {
        List<int[]> list = new ArrayList<>();

        public boolean book(int start, int end) {
            List<int[]> newList = new ArrayList<>(list);
            newList.add(new int[]{start, 1});
            newList.add(new int[]{end, -1});
            // NOTE: 按时间排序, 时间一样的, 先下车(-1)再上车(+1), 不影响下次预定
            newList.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
            int cnt = 0;
            for (int[] p : newList) { // 进行扫描
                cnt += p[1];
                if (cnt >= 3) return false; // 三重预定
            }
            list = newList; // 更新车上的乘客数组
            return true;
        }
    }

    // 先加后删除的做法
    class MyCalendarTwo0 {
        List<int[]> list = new ArrayList<>();

        public boolean book(int start, int end) {
            int[] sEvent = new int[]{start, 1};
            int[] eEvent = new int[]{end, -1};
            list.add(sEvent);
            list.add(eEvent);
            // NOTE: 按时间排序, 时间一样的, 先下车(-1)再上车(+1), 不影响下次预定
            list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
            int cnt = 0;
            for (int[] p : list) { // 进行扫描
                cnt += p[1];
                if (cnt >= 3) {
                    list.remove(sEvent);
                    list.remove(eEvent);
                    return false; // 三重预定
                }
            }
            return true;
        }
    }

    class MyCalendarTwo01 {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int cnt = 0;
            for (int p : map.values()) { // 按时间点进行进行扫描
                cnt += p;
                if (cnt >= 3) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    return false; // 三重预定
                }
            }
            return true;
        }
    }


    // 解法2: 动态开点线段树
    static class MyCalendarTwo3 {
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
            if (max >= 2) {
                return false;
            }
            dst.update(start + 1, end, 1);
            return true;
        }
    }
}
