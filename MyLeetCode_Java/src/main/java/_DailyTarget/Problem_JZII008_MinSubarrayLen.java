package _DailyTarget;

public class Problem_JZII008_MinSubarrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int ans = Integer.MAX_VALUE;
        int L = 0;
        int R = 0;
        int sum = 0;
        while (R < N) {
            sum += nums[R];
            while (L <= R && sum >= target) {
                ans = Math.min(ans, R - L + 1);
                sum -= nums[L++];
            }
            R++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
