package LeetCode;

public class Problem_2369_ValidPartition {


    public boolean validPartition(int[] nums) {
        return process(nums, 0);
    }

    // 数据从i到结尾能不能是一个有效划分
    private boolean process(int[] nums, int i) {
        int N = nums.length;
        if (i >= N - 1) {
            return false;
        }
        if (i == N - 2) {
            return nums[i] == nums[i + 1];
        }
        if (i == N - 3) {
            return nums[i] == nums[i + 1] && nums[i] == nums[i + 2] ||
                    nums[i] + 1 == nums[i + 1] && nums[i] + 2 == nums[i + 2];
        }
        // 来到i位置
        // 可能性1: 两两结合
        boolean p1 = false;
        if (i + 1 < N) {
            p1 = nums[i] == nums[i + 1] && process(nums, i + 2);
        }
        // 可能性2: 三三结合且相等
        boolean p2 = false;
        if (i + 2 < N) {
            p2 = nums[i] == nums[i + 1] && nums[i] == nums[i + 2] && process(nums, i + 3);
        }
        // 可能性3: 三三结合且差1
        boolean p3 = false;
        if (i + 2 < N) {
            p3 = nums[i] + 1 == nums[i + 1] && nums[i] + 2 == nums[i + 2] && process(nums, i + 3);
        }
        return p1 || p2 || p3;
    }

    public boolean validPartition1(int[] nums) {
        int N = nums.length;
        if (N < 2) {
            return false;
        }
        if (N == 2) {
            return nums[0] == nums[1];
        }
        if (N == 3) {
            return nums[0] == nums[1] && nums[0] == nums[2] ||
                    nums[0] + 1 == nums[1] && nums[0] + 2 == nums[2];
        }

        boolean[] dp = new boolean[N + 1];
        dp[N - 2] = nums[N - 2] == nums[N - 1];
        dp[N - 3] = nums[N - 3] == nums[N - 2] && nums[N - 3] == nums[N - 1] ||
                nums[N - 3] + 1 == nums[N - 2] && nums[N - 3] + 2 == nums[N - 1];
        for (int i = N - 4; i >= 0; i--) {
            boolean p1 = false;
            if (i + 1 < N) {
                p1 = nums[i] == nums[i + 1] && dp[i + 2];
            }
            // 可能性2: 三三结合且相等
            boolean p2 = false;
            if (i + 2 < N) {
                p2 = nums[i] == nums[i + 1] && nums[i] == nums[i + 2] && dp[i + 3];
            }
            // 可能性3: 三三结合且差1
            boolean p3 = false;
            if (i + 2 < N) {
                p3 = nums[i] + 1 == nums[i + 1] && nums[i] + 2 == nums[i + 2] && dp[i + 3];
            }
            dp[i] = p1 || p2 || p3;
        }
        return dp[0];
    }
}
