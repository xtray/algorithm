package LeetCode.JZOffer;

public class Problem_JZ42_MaxSubArray {

    // dp[i]: 以i位置结尾的子数组的最大和
    // 可能性:
    //  1. 只要i位置的数
    //  2. 往左扩
    public int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < N; i++) {
            int p1 = nums[i];
            int p2 = Integer.MIN_VALUE;
            if (dp[i - 1] > 0) {
                p2 = p1 + dp[i-1];
            }
            dp[i] = Math.max(p1, p2);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        var ans = new Problem_JZ42_MaxSubArray().maxSubArray(arr);
        System.out.println(ans);
    }
}
