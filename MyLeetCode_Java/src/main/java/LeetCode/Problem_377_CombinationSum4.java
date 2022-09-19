package LeetCode;

import java.util.Arrays;

public class Problem_377_CombinationSum4 {

    // NOTE: 错误的方法
    public int combinationSum40(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        return process0(nums, 0, target);
    }

    private int process0(int[] nums, int index, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (rest < 0 || index == nums.length) {
            return 0;
        }
        // 不选当前数
        int p1 = process0(nums, index + 1, rest);
        // 选当前数
        int p2 = process0(nums, index, rest - nums[index]);
        return p1 + p2;
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        return ways(nums, target);
    }

    // 枚举第一块, nums中的每一个数都作为第一块去尝试
    private int ways(int[] nums, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        for (int num : nums) {
            ans += ways(nums, rest - num);
        }
        return ans;
    }


    public int combinationSum41(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return ways1(nums, target, dp);
    }

    // 枚举第一块, nums中的每一个数都作为第一块去尝试
    private int ways1(int[] nums, int rest, int[] dp) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (dp[rest] != -1) {
            return dp[rest];
        }

        int ans = 0;
        for (int num : nums) {
            ans += ways1(nums, rest - num, dp);
        }
        dp[rest] = ans;
        return ans;
    }

    public int combinationSum42(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int N = nums.length;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return ways2(nums, target, dp);
    }

    // 枚举第一块, nums中的每一个数都作为第一块去尝试
    private int ways2(int[] nums, int rest, int[] dp) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (dp[rest] != -1) {
            return dp[rest];
        }

        int ans = 0;
        for (int num : nums) {
            if (num > rest) {
                break;
            }
            ans += ways2(nums, rest - num, dp);
        }
        dp[rest] = ans;
        return ans;
    }
}
