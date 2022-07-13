package LeetCode.Jianzhi;

import java.util.Arrays;

public class Problem_JZII101_NumPartition {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int N = nums.length;
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) != 0) {
            return false;
        }
        sum >>= 1;
        return process(nums, 0, sum);
    }

    private boolean process(int[] nums, int index, int rest) {
        if (rest < 0) {
            return false;
        }
        if (index == nums.length) {
            return rest == 0;
        }
        // 可能1: 不要当前
        boolean p1 = process(nums, index + 1, rest);
        // 可能2: 要当前
        boolean p2 = process(nums, index + 1, rest - nums[index]);
        return p1 || p2;
    }

    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int N = nums.length;
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) != 0) {
            return false;
        }
        sum >>= 1;
        boolean[][] dp = new boolean[N + 1][sum + 1];
        dp[N][0] = true;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能1: 不要当前
                boolean p1 = dp[i + 1][rest];
                // 可能2: 要当前
                boolean p2 = rest - nums[i] >= 0 && dp[i + 1][rest - nums[i]];
                dp[i][rest] = p1 || p2;
            }
        }
        return dp[0][sum];
    }
}
