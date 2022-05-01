package LeetCode.Jianzhi;

public class Problem_JZII088_MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return -1;
        }
        return Math.min(process(cost, 0, 0), process(cost, 1, 0));
    }

    // 当前来到index台阶, 登录到N-1台阶花费的最小费用
    // 之前的费用pre
    // TLE
    // 参数设计不好的动态规划!!!
    private int process(int[] cost, int index, int pre) {
        if (index >= cost.length) {
            return pre;
        }
        int p1 = process(cost, index + 1, pre + cost[index]);
        int p2 = process(cost, index + 2, pre + cost[index]);
        return Math.min(p1, p2);
    }

    // NOTE: 注意这种写法, 跟上面的区别
    public int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) {
            return -1;
        }
        return Math.min(process2(cost, 0), process2(cost, 1));
    }

    // 当前来到index台阶, 登录到N-1台阶花费的最小费用
    // TLE
    private int process2(int[] cost, int index) {
        if (index >= cost.length) {
            return 0;
        }
        int p1 = cost[index] + process2(cost, index + 1);
        int p2 = cost[index] + process2(cost, index + 2);
        return Math.min(p1, p2);
    }

    // 改动态规划
    public int minCostClimbingStairs3(int[] cost) {
        if (cost == null || cost.length == 0) {
            return -1;
        }
        int N = cost.length;
        int[] dp = new int[N + 2];
        // dp[N], dp[N+1] 都是 0
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }
        return Math.min(dp[0], dp[1]);
    }

    // 空间压缩
    public int minCostClimbingStairs4(int[] cost) {
        if (cost == null || cost.length == 0) {
            return -1;
        }
        int N = cost.length;
        int step1 = 0; // 一步 dp[N]
        int step2 = 0; // 两步 dp[N+1]
        for (int i = N - 1; i >= 0; i--) {
            int cur = cost[i] + Math.min(step1, step2);
            step2 = step1;
            step1 = cur;
        }
        return Math.min(step1, step2);
    }

    public static void main(String[] args) {
        // int[] cost = {10, 15, 20};
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        var ans = new Problem_JZII088_MinCostClimbingStairs().minCostClimbingStairs(cost);
        System.out.println(ans);
    }

}
