package LeetCode;

import java.util.Arrays;

public class Problem_801_MinSwap {


    public int minSwap1(int[] nums1, int[] nums2) {
        int N = nums1.length;
        // 从0....N升序, (0位置也可以交换)
        return process(nums1, nums2, 0, 0);
    }

    // pre: i-1有没有交换, 0: 没有, 1: 换了
    // 0~i-1已经处理过了, 有序了
    // i...N有序的最小交换次数
    private int process(int[] nums1, int[] nums2, int pre, int i) {
        int N = nums1.length;
        if (i == N) {
            return 0;
        }
        // 之前的数是什么
        int preANum = Integer.MIN_VALUE;
        int preBNum = Integer.MIN_VALUE;
        if (i != 0) {
            if (pre == 0) {
                preANum = nums1[i - 1];
                preBNum = nums2[i - 1];
            } else {
                preANum = nums2[i - 1];
                preBNum = nums1[i - 1];
            }
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int p3 = Integer.MAX_VALUE;
        // 如果当前位置数都是升序, 可以换, 也可以不换
        if (preANum < nums1[i] && preBNum < nums2[i]) {
            // 可能性1: 搞定当前i位置, 不交换
            p1 = process(nums1, nums2, 0, i + 1);
            // 可能性2: 搞定当前i位置, 交换
            if (preANum < nums2[i] && preBNum < nums1[i]) {
                int next1 = process(nums1, nums2, 1, i + 1);
                if (next1 != Integer.MAX_VALUE) {
                    p2 = next1 + 1;
                }
            }
        } else {
            // 可能性3: 搞定当前i位置, 必须要交换
            int next2 = process(nums1, nums2, 1, i + 1);
            if (next2 != Integer.MAX_VALUE) {
                p3 = next2 + 1;
            }
        }
        return Math.min(p1, Math.min(p2, p3));
    }

    // dp[i][pre]: 从i...N最小交换次数
    // pre: i-1位置有没有交换过, 0:没有, 1:有
    public int minSwap(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[][] dp = new int[N + 1][2];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        Arrays.fill(dp[1], Integer.MAX_VALUE);
        dp[N][0] = 0;
        dp[N][1] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int pre = 0; pre < 2; pre++) {
                // 之前的数是什么
                int preANum = Integer.MIN_VALUE;
                int preBNum = Integer.MIN_VALUE;
                if (i != 0) {
                    if (pre == 0) {
                        preANum = nums1[i - 1];
                        preBNum = nums2[i - 1];
                    } else {
                        preANum = nums2[i - 1];
                        preBNum = nums1[i - 1];
                    }
                }
                // 如果当前位置数都是升序, 可以换, 也可以不换
                if (preANum < nums1[i] && preBNum < nums2[i]) {
                    // 可能性1: 搞定当前i位置, 不交换
                    dp[i][pre] = dp[i + 1][0];
                    // 可能性2: 搞定当前i位置, 交换
                    if (preANum < nums2[i] && preBNum < nums1[i]) {
                        if (dp[i + 1][1] != Integer.MAX_VALUE) {
                            dp[i][pre] = Math.min(dp[i][pre], dp[i + 1][1] + 1);
                        }
                    }
                } else {
                    // 可能性3: 搞定当前i位置, 必须要交换
                    if (dp[i + 1][1] != Integer.MAX_VALUE) {
                        dp[i][pre] = dp[i + 1][1] + 1;
                    }
                }

            }
        }
        return Math.min(dp[0][0], dp[0][1]);
    }

    public static void main(String[] args) {
        // int[] nums1 = {0, 4, 4, 5, 9};
        // int[] nums2 = {0, 1, 6, 8, 10}; // 1
        // int[] nums1 = {3, 3, 8, 9, 10};
        // int[] nums2 = {1, 7, 4, 6, 8}; // 1
        int[] nums1 = {0, 7, 8, 10, 10, 11, 12, 13, 19, 18};
        int[] nums2 = {4, 4, 5, 7, 11, 14, 15, 16, 17, 20}; // 4
        // int[] nums1 = {0, 7, 8, 10, 10, 11};
        // int[] nums2 = {4, 4, 5, 7, 11, 14}; // 3
        // var ans = new Problem_801_MinSwap().minSwap(nums1, nums2);
        var ans = new Problem_801_MinSwap().minSwap(nums1, nums2);
        System.out.println(ans);
    }
}
