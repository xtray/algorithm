package LeetCode;

import java.util.Arrays;

public class Problem_462_MinMovesII {

    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int N = nums.length;
        int mid = nums[N / 2];
        int ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - mid);
        }
        return ans;
    }
}
