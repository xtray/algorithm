package LeetCode;

// NOTE: 基本背包问题, 只需要凑出累加和的一半, 剩下的自然合格

import java.util.Arrays;

public class Problem_416_CanPartition {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        // for (int num : nums) {
        //     sum += num;
        // }
        if ((sum & 1) != 0) { // 累加和是个奇数, 不可能平分
            return false;
        }
        return process(nums, 0, sum >> 1);
    }

    // 数组从index开始选,每个数可以要跟不要, 能不能凑出sum
    private boolean process(int[] nums, int index, int rest) {
        if (rest < 0) {
            return false;
        }
        if (index == nums.length) {
            return rest == 0;
        }
        boolean p1 = process(nums, index + 1, rest);
        boolean p2 = process(nums, index + 1, rest - nums[index]);
        return p1 || p2;
    }

    // dp[i][j]: i~N-1 随便选, 能不能凑出累加和j
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) != 0) { // 累加和是个奇数, 不可能平分
            return false;
        }
        int N = nums.length;
        boolean[][] dp = new boolean[N + 1][(sum >> 1) + 1];
        dp[N][0] = true;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= (sum >> 1); rest++) {
                boolean p1 = dp[index + 1][rest];
                boolean p2 = rest - nums[index] >= 0 && dp[index + 1][rest - nums[index]];
                dp[index][rest] = p1 || p2;
            }
        }
        return dp[0][sum >> 1];
    }

    // NOTE: 有点不好理解, 多看!!
    // dp[i][j]: 0~i随便选, 能不能凑出累加和j
    public boolean canPartition3(int[] nums) {
        int N = nums.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        if ((sum & 1) != 0) { // 奇数不能拆分
            return false;
        }
        sum >>= 1;
        // dp[i][j]: 0~i最后能不能凑j
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j]; // 不要i位置的数
                if (j - nums[i] >= 0) {  // 要i位置的数
                    dp[i][j] |= dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][sum]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 5, 11, 5};
        int[] nums = {10, 3, 7};
        var ans = new Problem_416_CanPartition().canPartition3(nums);
        System.out.println(ans);
    }
}
