package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem_1521_ClosetToTarget {

    /**
     * 结论: 一个数组中所有不同的前缀与和的个数, 不会超过第一个数中1的个数
     * 因为每次与一个新的数, 要么值不变, 要么消耗掉当前前缀和中的至少一个1
     * 所以: 按题意按位与之和最多有20中不同的值
     */

    public int closestToTarget(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        List<Integer> list = new ArrayList<>(); // 根据数据量, 与的结果最多20种
        list.add(arr[0]);
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            List<Integer> newList = new ArrayList<>();
            // 可能性1: i位置当前一个数一组
            newList.add(arr[i]);
            ans = Math.min(ans, Math.abs(arr[i] - target));
            // 可能性2: i位置当前数跟之前所有的子数组相与
            // 去重:
            // 将当前轮的函数值入队列时需要排除重复元素。由于遍历函数值的顺序具有单调性，
            // 因此相等的函数值在遍历顺序中一定相邻，每次将函数值入队列时判断当前函数值
            // 与上一个入队列的函数值是否相等，即可排除重重复元素。
            int prev = arr[i]; // arr的值都>=1
            for (int num : list) {
                int cur = num & arr[i];
                if (cur != prev) {
                    newList.add(cur);
                    ans = Math.min(ans, Math.abs(cur - target));
                    prev = cur;
                }
            }
            list = newList; // 更新list
        }
        return ans;
    }

    public int closestToTarget1(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        Set<Integer> list = new HashSet<>(); // 使用set去重, 因为最多20个
        list.add(arr[0]);
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            Set<Integer> newList = new HashSet<>();
            // 可能性1: i位置当前一个数一组
            newList.add(arr[i]);
            ans = Math.min(ans, Math.abs(arr[i] - target));
            // 可能性2: i位置当前数跟之前所有的子数组相与
            for (int num : list) {
                int cur = num & arr[i];
                newList.add(cur);
                ans = Math.min(ans, Math.abs(cur - target));
            }
            list = newList; // 更新list
        }
        return ans;
    }

    // ref: https://leetcode.cn/problems/find-a-value-of-a-mysterious-function-closest-to-target/solution/python-stbiao-er-fen-by-981377660lmt-5uh6/
    // 1.可重复贡献问题的静态区间查询使用st表
    // 2.与运算具有单调性，可以使用二分查找
    // 首先我们考察枚举起点是否可行。
    // 当给定了一个区间的起点之后，区间的终点越靠后，数字之间的与值就越小。
    // 所以给定区间起点之后，具有单调性，可以使用二分来进行搜索距离target最近的值。
    // 如何快速得到一个区间中的数字的与值
    public static class RAQ {
        public int[][] and;

        public RAQ(int[] arr) {
            int n = arr.length;
            int k = power2(n);
            and = new int[n + 1][k + 1]; // 下标从1开始
            for (int i = 1; i <= n; i++) {
                and[i][0] = arr[i - 1];
            }
            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    and[i][j] = and[i][j - 1] & and[i + (1 << (j - 1))][j - 1];
                }
            }
        }

        public int query(int l, int r) {
            // l...r -> r - l + 1 -> 2的哪个次方最接近它！
            int k = power2(r - l + 1);
            l++;
            r++;
            return and[l][k] & and[r - (1 << k) + 1][k];
        }

        // 离n最近的2的某次幂
        private int power2(int n) {
            int ans = 0;
            // m >> 1 : m先减半为了退出循环时 ans 不用--
            while ((1 << ans) <= (n >> 1)) {
                ans++;
            }
            return ans;
        }
    }

    // O(N*logN)
    public int closestToTarget2(int[] arr, int target) {
        RAQ st = new RAQ(arr);
        int ans = Math.abs(arr[0] - target);
        int N = arr.length;
        // 固定左端点, 二分
        for (int i = 0; i < N; i++) {
            int L = i; // 从每一个i位置出发, 枚举所有子数组
            int R = N - 1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                // mid 越往左越大 越往右越小
                // 想办法让diff向0趋近
                int diff = st.query(i, mid) - target;
                ans = Math.min(ans, Math.abs(diff));
                if (diff == 0) {
                    return 0;
                } else if (diff > 0) { // 大了
                    L = mid + 1; // L往左走, 让mid变大
                } else {
                    R = mid - 1;
                }
            }
        }
        return ans;
    }


}
