package _DailyTarget;

import java.util.ArrayList;
import java.util.List;

// TAG: 归并排序, 线段树, 树状数组

public class Problem_315_CountSmaller {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        int limit = (int) 1e4;
        int N = 2 * limit + 1;
        // NOTE: 再+1, 是因为数据实际会到limit位置, 而线段树位置又是从1开始(+1), 同Problem_1395_NumTeams
        SegmentTree seg = new SegmentTree(2 * limit + 1);
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = nums[i] + limit + 1;
            int cnt = (int) seg.query(1, idx - 1, 1, N, 1);
            ans.set(i, cnt);
            seg.add(idx, idx, 1, 1, N, 1);
        }
        return ans;
    }

    public static class SegmentTree {
        private int[] sum; // 维护区间和
        private int[] lazy; // 懒更新标记

        public SegmentTree(int size) {
            int N = size + 1; // 0 位置弃而不用
            sum = new int[N << 2];
            lazy = new int[N << 2];
        }

        // rt stand for root
        private void pushUP(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // 当前任务往下发一层
        // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }


        // L~R, C 任务！
        // l~r --> rt
        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUP(rt);
        }

        // 1~6 累加和是多少？ 1~8 --> rt
        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }


    public class Node {
        public int value;
        public int index;

        public Node(int v, int i) {
            value = v;
            index = i;
        }
    }

    // 利用归并排序
    public List<Integer> countSmaller1(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        if (nums.length < 2) {
            return ans;
        }
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(nums[i], i);
        }
        process(arr, 0, arr.length - 1, ans);
        return ans;
    }

    private void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid, ans);
        process(arr, mid + 1, r, ans);
        merge(arr, l, mid, r, ans);
    }

    private void merge(Node[] arr, int l, int mid, int r, List<Integer> ans) {
        Node[] help = new Node[r - l + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - mid);
            }
            help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        Problem_315_CountSmaller sl = new Problem_315_CountSmaller();

        // int[] nums = new int[]{5, 2, 6, 1};
        // List<Integer> ans = sl.countSmaller(nums);
        // System.out.println(ans);

        int limit = (int) 1e4;
        int N = 2 * limit + 1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = -limit + i;
        }
        var ans1 = sl.countSmaller1(arr);
        System.out.println(ans1);

    }
}
