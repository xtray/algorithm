package _Exercise;

public class Problem_1035_MaxUncrossedLines {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        return process(nums1, nums2, 0, 0);
    }

    // nums1 从i出发, nums2从j出发能配出几条
    private int process(int[] nums1, int[] nums2, int i, int j) {
        int N = nums1.length;
        int M = nums2.length;
        if (i == N || j == M) {
            return 0;
        }
        // 两个都没到终点
        // 1. 不要当前数
        int p1 = process(nums1, nums2, i + 1, j);
        int p2 = process(nums1, nums2, i, j + 1);
        // 2. 要当前数
        int p3 = 0;
        if (nums1[i] == nums2[j]) {
            p3 = 1 + process(nums1, nums2, i + 1, j + 1);
        }
        return Math.max(p3, Math.max(p1, p2));
    }

    // dp
    public int maxUncrossedLines1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int N = nums1.length;
        int M = nums2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 1][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // 最长公共子序列的解法

}
