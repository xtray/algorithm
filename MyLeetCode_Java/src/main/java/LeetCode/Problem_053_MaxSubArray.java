package LeetCode;

public class Problem_053_MaxSubArray {


    // dp[i]: i位置结尾的最大子数组的和
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        int N = nums.length;
        int maxSum = nums[0];
        int[] dp = new int[N];
        dp[0] = nums[0];
        for(int i = 1; i<N;i++) {
            int p1 = nums[i];
            int p2 = Integer.MIN_VALUE;
            if(dp[i-1]>0) {
                p2 = nums[i] + dp[i-1];
            }
            dp[i] = Math.max(p1,p2);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        int N = nums.length;
        int maxSum = nums[0];
        int pre = nums[0];
        for(int i = 1; i<N;i++) {
            int cur = nums[i];
            if(pre >0) {
                cur += pre;
            }
            maxSum = Math.max(maxSum, cur);
            pre = cur;
        }
        return maxSum;
    }

    public int maxSubArray3(int[] nums) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        int N = nums.length;
        int maxSum = nums[0];
        int cur = nums[0];
        for(int i = 1; i<N;i++) {
            // NOTE: 跟0比, pre可以省掉
            cur = nums[i] + Math.max(0, cur);
            maxSum = Math.max(maxSum, cur);
        }
        return maxSum;
    }
}
