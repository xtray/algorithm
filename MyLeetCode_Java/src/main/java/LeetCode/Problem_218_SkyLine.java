package LeetCode;


import java.util.*;

// tag: 扫描线, 线段树

public class Problem_218_SkyLine {

    // 扫描线
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();

        int N = buildings.length;
        // 整理为事件
        // [类型, 高度, 时间]
        // 0: 开始, 1: 结束
        int[][] events = new int[N << 1][3];
        int idx = 0;
        for (int[] b : buildings) {
            events[idx][0] = 0;
            events[idx][1] = b[2];
            events[idx][2] = b[0];
            events[idx + N][0] = 1; // 结束
            events[idx + N][1] = b[2];
            events[idx + N][2] = b[1];
            idx++;
        }
        // 按时间排序
        Arrays.sort(events, (o1, o2) -> o1[2] == o2[2] ? o1[0] - o2[0] : o1[2] - o2[2]);
        // 保存每个时间点上最大高度的次数
        // 单点最大高度 --> 线段树?
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();// 每个高度对应次数, 找到最大
        LinkedHashMap<Integer, Integer> posMap = new LinkedHashMap<>(); // 保存每个点的高度
        for (int[] e : events) {
            if (e[0] == 0) { // 开始事件
                cntMap.put(e[1], cntMap.getOrDefault(e[1], 0) + 1);
            } else {
                cntMap.put(e[1], cntMap.get(e[1]) - 1);
                if (cntMap.get(e[1]) == 0) {
                    cntMap.remove(e[1]);
                }
            }
            // 获取当前x点的最大高度
            int height = cntMap.isEmpty() ? 0 : cntMap.lastKey();
            posMap.put(e[2], height);
        }
        // 根据posMap生成答案
        int preH = -1;
        for (int key : posMap.keySet()) {
            int curX = key;
            int curH = posMap.get(key);
            if (curH != preH) {
                List<Integer> list = new ArrayList<>();
                list.add(curX);
                list.add(curH);
                ans.add(list);
            }
            preH = curH;
        }
        return ans;
    }

    // 线段树
    // 区间更新, 查找每个位置的最大值生成结果
    public List<List<Integer>> getSkyline1(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        // 1. 位置离散化
        Map<Integer, Integer> map = new HashMap<>();

        // a.数据去重, 排序
        TreeSet<Integer> set = new TreeSet<>(); // 去重后的原始位置
        for (int[] b : buildings) {
            set.add(b[0]);
            set.add(b[1]);
        }
        // b.对原始位置,分配离散化后的位置
        int idx = 0;
        for (int pos : set) {
            map.put(pos, idx++);
        }

        // 2.添加数据到线段树
        int N = map.size();
        SegmentTree seg = new SegmentTree(N);
        for (int[] b : buildings) {
            // 区间:[开始, 结束), 左闭右开
            // 线段树中, 比实际位置要+1(数据从1开始的)
            int s = map.get(b[0]) + 1;
            int e = map.get(b[1]) + 1 - 1;
            int h = b[2];
            seg.update(s, e, h, 1, N, 1);
        }

        // 3. 生成答案
        int preH = -1;
        for (int x : set) { // 遍历排序去重后的原始位置
            int curX = map.get(x) + 1; // 得到离散化位置
            int curH = seg.query(curX, curX, 1, N, 1);
            if (curH != preH) {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(curH);
                ans.add(list);
            }
            preH = curH;
        }
        return ans;
    }

    public static class SegmentTree1 {
        private int n;
        private int[] max; // 高度数组
        private int[] update; // lazy数组

        public SegmentTree1(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int right) {
            return max(right, right, 1, n, 1);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        // 最大值数组跟lazy数组都需要用最大值刷新
        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != -1) {
                update[rt << 1] = Math.max(update[rt << 1], update[rt]);
                update[rt << 1 | 1] = Math.max(update[rt << 1 | 1], update[rt]);
                max[rt << 1] = Math.max(max[rt << 1], update[rt]);
                max[rt << 1 | 1] = Math.max(max[rt << 1 | 1], update[rt]);
                update[rt] = -1;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = Math.max(max[rt], C);
                update[rt] = Math.max(update[rt], C);
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

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = Math.max(change[rt << 1], change[rt]);
                change[rt << 1 | 1] = Math.max(change[rt << 1 | 1], change[rt]);
                max[rt << 1] = Math.max(max[rt << 1], change[rt]);
                max[rt << 1 | 1] = Math.max(max[rt << 1 | 1], change[rt]);
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = Math.max(C, change[rt]);
                max[rt] = Math.max(C, max[rt]);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
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
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
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
        // int[][] buildings = new int[][]{
        //         {1, 3, 3},
        //         {2, 4, 4},
        //         {5, 6, 1}
        // };

        // int[][] buildings = new int[][]{
        //         {0, 2, 3},
        //         {2, 5, 3}
        // };

        // int[][] buildings = new int[][]{
        //         {2, 9, 10},
        //         {3, 7, 15},
        //         {5, 12, 12},
        //         // {15, 20, 10},
        //         // {19, 24, 8}
        // };

        // 下落点(right)不包含, 高度为0
        int[][] buildings = new int[][]{
                {2, 13, 10},
                {10, 17, 25},
                {12, 20, 14}
        };

        var ans = new Problem_218_SkyLine().getSkyline(buildings);
        System.out.println(ans);

        var ans1 = new Problem_218_SkyLine().getSkyline1(buildings);
        System.out.println(ans1);
    }
}
