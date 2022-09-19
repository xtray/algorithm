package _LintCode;

// https://www.lintcode.com/problem/1068

public class Problem_1068_FindMidIndex {

    public int pivotIndex(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int N = nums.length;
        int ans = -1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        int left = 0;
        for (int i = 1; i < N; i++) {
            left += nums[i - 1];
            int right = sum - left - nums[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }
}
