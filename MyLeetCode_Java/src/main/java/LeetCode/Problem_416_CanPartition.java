package LeetCode;

public class Problem_416_CanPartition {
    public boolean canPartition(int[] nums) {
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
        return process(nums, 0, sum / 2);
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

    /**
     * TODO: DP的细节还需要再看看
     * 背包DP
     */
    public static boolean canPartition3(int[] nums) {
        int N = nums.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        if ((sum & 1) != 0) { // 奇数不能拆分
            return false;
        }
        sum >>= 1;
        // dp[i][j]: i...最后能不能凑j
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
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
        int[] nums = {1,5,11,5};
        var ans = new Problem_416_CanPartition().canPartition2(nums);
        System.out.println(ans);
    }
}
