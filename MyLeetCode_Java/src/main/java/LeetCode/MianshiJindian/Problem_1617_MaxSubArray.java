package LeetCode.MianshiJindian;

public class Problem_1617_MaxSubArray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < N; i++) {
            int p1 = nums[i];
            int p2 = Integer.MIN_VALUE;
            if (dp[i - 1] > 0) {
                p2 = nums[i] + dp[i - 1];
            }
            dp[i] = Math.max(p1, p2);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int dp = nums[0];
        int ans = nums[0];
        for(int i = 1; i< N; i++) {
            dp = nums[i] + Math.max(0, dp);
            ans = Math.max(ans, dp);
        }
        return ans;
    }
}
