package _Exercise;

public class Problem_53_MaxSubArray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        // 以i位置结尾的最大子数组和
        int ans = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            // 可能性1: 只要i位置
            dp[i] = nums[i];
            // 可能性2: 往左扩
            if (dp[i - 1] > 0) {
                dp[i] += dp[i - 1];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // 以i位置结尾的最大子数组和
        int ans = nums[0];
        int pre = nums[0];
        for (int i = 1; i < N; i++) {
            // 可能性1: 只要i位置
            int cur = nums[i];
            // 可能性2: 往左扩
            if (pre > 0) {
                cur += pre;
            }
            pre = cur;
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // 以i位置结尾的最大子数组和
        int ans = nums[0];
        int cur = nums[0];
        for (int i = 1; i < N; i++) {
            cur = nums[i] + Math.max(0, cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        var ans = new Problem_53_MaxSubArray().maxSubArray(nums);
        System.out.println(ans);
    }
}
