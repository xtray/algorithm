package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_873_LongestFebSequence {

    // ref: https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solution/zui-chang-de-fei-bo-na-qi-zi-xu-lie-de-c-8trz/

    // dp[i][j] : 使用 arr[i]为最后一位, arr[j]为倒数第二位的最长数列长度
    public int lenLongestFibSubseq(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[N][N];
        int ans = 0;
        for (int i = 1; i < N; i++) {
            // i > j > k
            // 找k, 所以 2*j>i
            // NOTE: 只有当arr[j]×2>arr[i] 时才满足arr[k]<arr[j]，
            //  当arr[j]×2≤arr[i] 时不需要对当前下标i继续遍历更小的下标 j。
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                if (arr[i] - arr[j] >= arr[j]) break;
                int k = map.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[i][j] = Math.max(3, dp[j][k] + 1);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
