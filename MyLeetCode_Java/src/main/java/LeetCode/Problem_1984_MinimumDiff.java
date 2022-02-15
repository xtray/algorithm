package LeetCode;

import java.util.Arrays;

public class Problem_1984_MinimumDiff {

    public int minimumDifference(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        int L = 0;
        int R = L + k - 1;
        while (R < nums.length) {
            ans = Math.min(ans, nums[R] - nums[L]);
            L++;
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {87063, 61094, 44530, 21297, 95857, 93551, 9918};
        int k = 6;
        var ans = new Problem_1984_MinimumDiff().minimumDifference(nums, k);
        System.out.println(ans);
    }
}
