package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Problem_1201_NthUglyNumber {


    // 丑数是可以被 a 或 b 或 c 整除的 正整数 。
    public int nthUglyNumber0(int n, int a, int b, int c) {

        int[] dp = new int[n + 1];
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        dp[0] = 1; // 不用
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;
        int idx = 1;
        while (idx <= n) {
            int p1 = dp[idx1] * nums[0];
            int p2 = dp[idx2] * nums[1];
            int p3 = dp[idx3] * nums[2];
            // 谁小谁可以多用一个
            int min = Math.min(p1, Math.min(p2, p3));
            dp[idx++] = min;
            if (min == p1) idx1++;
            if (min == p2) idx2++;
            if (min == p3) idx3++;
        }
        return dp[n];
    }

    //https://leetcode.cn/problems/ugly-number-iii/solution/er-fen-fa-si-lu-pou-xi-by-alfeim/
    public int nthUglyNumber(int n, int a, int b, int c) {

        int[] nums = {a, b, c};
        Arrays.sort(nums);
        long L = 0;
        long R = (long) nums[0] * n;
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm((int) ab, c);
        long ans = -1;
        while (L <= R) {
            long mid = L + ((R - L) >> 1);
            long cnt = mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc;
            if (cnt >= n) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return (int) ans;
    }


    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }



    public static void main(String[] args) {
        // int n = 3;
        // int a = 2, b = 3, c = 5; // 4
        int n = 5;
        int a = 2, b = 11, c = 13; // 10

        var ans = new Problem_1201_NthUglyNumber().nthUglyNumber(n, a, b, c);
        System.out.println(ans);
    }
}
