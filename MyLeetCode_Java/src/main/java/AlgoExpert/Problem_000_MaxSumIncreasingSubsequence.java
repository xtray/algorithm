package AlgoExpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大递增子序列和 [H]
 * 找出最大的递增子序列和并输出
 * Input:
 * array = [10, 70, 20, 30, 50, 11, 30]
 * Output:
 * [110, [10, 20, 30, 50]]
 *  110 是最大递增子序列 [10, 20, 30, 50] 的和
 */
public class Problem_000_MaxSumIncreasingSubsequence {


    // dp[i] : 以 i 结尾的最大递增子序列和
    public static List<List<Integer>> maxSumIncreasingSubsequence(int[] arr) {
        // public static int maxSumIncreasingSubsequence(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            ans.add(new ArrayList<>());
            ans.add(new ArrayList<>());
            ans.get(0).add(0);
            return ans;
        }

        int N = arr.length;
        int[] dp = new int[N];
        int[] idx = new int[N]; // idx[i]: 位置取到最大值的上一个位置
        Arrays.fill(idx, -1);
        dp[0] = arr[0];
        int[] maxSum = new int[]{arr[0], 0};
        for (int i = 1; i < N; i++) {
            // 可能性 1, 只有 i 位置自己
            int p1 = arr[i];
            // 可能性 2: 往左找<自己的最大的
            int p2 = Integer.MIN_VALUE;
            int pos = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i] && dp[j] > 0) {
                    if (arr[i] + dp[j] > p2) {
                        p2 = arr[i] + dp[j];
                        pos = j;
                    }
                }
            }
            dp[i] = p1;
            if (p2 > p1) {
                idx[i] = pos;
                dp[i] = p2;
            }
            if (dp[i] > maxSum[0]) {
                maxSum[0] = dp[i];
                maxSum[1] = i;
            }
        }
        // 生成结果
        int start = maxSum[1];
        ans.add(new ArrayList<>());
        ans.get(0).add(maxSum[0]);
        List<Integer> seq = new ArrayList<>();
        while (start != -1) {
            seq.add(0, arr[start]);
            start = idx[start];
        }
        ans.add(seq);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1000, 70, 20, 30, 50, 11, 30};
        // int[] arr = new int[]{10, 70, 20, 30, 50, 11, 30};
        // int[] arr = new int[]{1};
        var ans = maxSumIncreasingSubsequence(arr);
        System.out.println(ans.toString());
    }
}
