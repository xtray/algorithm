package LeetCode.JZOffer;

public class Problem_JZ57_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int L = 0;
        int R = nums.length - 1;
        while (L < nums.length) {
            if (nums[L] + nums[R] < target) {
                L++;
            } else if (nums[L] + nums[R] > target) {
                R--;
            } else {
                return new int[]{nums[L], nums[R]};
            }
        }
        return new int[0];
    }
}
