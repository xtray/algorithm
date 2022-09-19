package _DailyTarget;

import java.util.*;

public class Problem_327_CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);

    }

    private int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            // merge左右组合并过程中会忽略 0~X整体 - 一个数也没有的情况, 单独在这里处理
            // 看看一个数也没有的时候，我自己 0 到当前达不达标
            return sum[L] >= lower && sum[R] <= upper ? 1 : 0;
        }
        // L < R
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper) +
                merge(sum, L, M, R, lower, upper);
    }

    private int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L; // [L, R), 一开始一个数也没有
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) { // while 出来, windowR 是不符合的位置
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            // 最终形成的窗口 [L, R)
            ans += windowR - windowL;
        }
        int p1 = L;
        int p2 = M + 1;
        long[] help = new long[R - L + 1];
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }


    // 树状数组的解法, 需要做离散化
    // 单点更新, 区间查询
    int m;
    int[] tr = new int[100010 * 3];
    int lowbit(int x) {
        return x & -x;
    }
    void add(int x, int v) {
        for (int i = x; i <= m; i += lowbit(i)) tr[i] += v;
    }
    int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) ans += tr[i];
        return ans;
    }
    public int countRangeSum1(int[] nums, int lower, int upper) {
        Set<Long> set = new HashSet<>();
        long s = 0;
        set.add(s);
        for (int i : nums) {
            s += i;
            set.add(s);
            set.add(s - lower);
            set.add(s - upper);
        }
        List<Long> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Long, Integer> map = new HashMap<>();
        for (long x : list) map.put(x, ++m);
        s = 0;
        int ans = 0;
        add(map.get(s), 1);
        for (int i : nums) {
            s += i;
            int a = map.get(s - lower), b = map.get(s - upper) - 1;
            ans += query(a) - query(b);
            add(map.get(s), 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;

        var ans = new Problem_327_CountOfRangeSum().countRangeSum(nums, lower, upper);
        System.out.println(ans);
    }
}
