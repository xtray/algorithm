package LeetCode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// https://leetcode.cn/problems/range-module/

public class Problem_715_RangeModule {

    static class RangeModule {
        // 某个区间的开头是key，结尾是value
        // 目标：不管怎么调用，一定要保证！
        // map里表示的区间，能合并就合并，且没有交集
        TreeMap<Integer, Integer> map;

        public RangeModule() {
            map = new TreeMap<>();
        }

        // 加入一个区间
        public void addRange(int left, int right) {
            // 无效区间直接返回
            if (right <= left) {
                return;
            }
            // 有效区间！
            // 当前要加入的区间是[left, right)
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null && end == null) {
                map.put(left, right);
            } else if (start != null && map.get(start) >= left) {
                map.put(start, Math.max(map.get(end), right));
            } else {
                map.put(left, Math.max(map.get(end), right));
            }
            Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }

        public boolean queryRange(int left, int right) {
            // [34, 76) 整体被你的结构，有没有包含！
            // <=34 开头都没！
            Integer start = map.floorKey(left);
            if (start == null)
                return false;
            // [34, 76) 整体被你的结构，有没有包含！
            // <=34 开头有！[17，~ 60) [60 ~ 76)
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            if (right <= left) {
                return;
            }
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));
            }
            if (start != null && map.get(start) > left) {
                map.put(start, left);
            }
            Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }
    }

    // TODO: 动态开点线段树的解
    static class RangeModule2 {

        public static class Node {
            public int sum;
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
                    p.left.sum = p.change * ln;
                    p.right.sum = p.change * rn;
                    p.update = false;
                }
            }

            private void update(Node c, int l, int r, int s, int e, int v) {
                if (s <= l && r <= e) {
                    c.update = true;
                    c.change = v;
                    c.sum = v * (r - l + 1);
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

            // 开放给外部调用的接口
            public void update(int s, int e, int v) {
                update(root, 1, size, s, e, v);
            }

            public int query(int s, int e) {
                return query(root, 1, size, s, e);
            }
        }

        private DynamicSegmentTree dst;
        private int N;

        public RangeModule2() {
            N = (int) 1e9;
            dst = new DynamicSegmentTree(N);
        }

        public void addRange(int left, int right) {
            dst.update(left, right - 1, 1);
        }

        public boolean queryRange(int left, int right) {
            return dst.query(left, right - 1) == right - left;
        }

        public void removeRange(int left, int right) {
            dst.update(left, right - 1, 0);
        }
    }


    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(6, "我是6");
        map.put(3, "我是3");
        map.put(9, "我是9");
        map.put(5, "我是9");
        map.put(4, "我是9");
        // 3 4 5 6 9
        // [4~6) -> 4, 5,6

        Map<Integer, String> subMap = map.subMap(4, true, 6, false);

        for (int key : subMap.keySet()) {
            System.out.println(key);
        }

    }

}
