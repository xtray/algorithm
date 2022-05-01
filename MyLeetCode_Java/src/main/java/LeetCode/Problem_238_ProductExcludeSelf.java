package LeetCode;

public class Problem_238_ProductExcludeSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int N = nums.length;
        int[] right = new int[N];
        right[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = nums[i] * right[i + 1];
        }
        int left = 1;
        int[] ans = new int[N];
        for (int i = 0; i < N - 1; i++) {
            ans[i] = left * right[i + 1];
            left *= nums[i];
        }
        ans[N - 1] = left;
        return ans;
    }
}
