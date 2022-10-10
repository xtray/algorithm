package _LintCode;

public class Problem_604_WinSum {

    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        int[] ans = new int[N - k + 1];
        int L = 0;
        int R = 0;
        int sum = 0;
        while (R < N) {
            sum += nums[R];
            if (L == R - k) {
                sum -= nums[L];
                L++;
            }
            if (R - k + 1 >= 0) {
                ans[R - k + 1] = sum;
            }
            R++;
        }
        return ans;
    }
}
