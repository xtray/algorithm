package LeetCode;

public class Problem_2321_MaxScore {

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] gap = new int[N]; // 数组2对数组1的贡献度
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < N; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
            gap[i] = nums2[i] - nums1[i];
        }
        sum1 += maxSubArray(gap, 1);
        sum2 += maxSubArray(gap, -1);
        return Math.max(sum1, sum2);
    }

    public int maxSubArray(int[] nums, int minus) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int dp = nums[0] * minus;
        int ans = nums[0] * minus;
        for (int i = 1; i < N; i++) {
            dp = nums[i] * minus + Math.max(0, dp);
            ans = Math.max(ans, dp);
        }
        return ans;
    }



}
