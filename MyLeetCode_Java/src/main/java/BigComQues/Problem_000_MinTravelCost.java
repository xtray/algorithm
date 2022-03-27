package BigComQues;

/**
 * 给你一个年度出行计划数组days, 值从 1~365
 * 在给你一个乘车券价格数组costs:
 * costs[0]: 代表 1 日火车通票的价格
 * costs[1]: 代表 7 日火车通票的价格
 * costs[2]: 代表 30 日火车通票的价格
 * 通票在购买日到持续时间内有效, 比如你在 2 号购买 7 日通票, 你可以在 2,3,4,5,6,7,8七天内乘车
 * 请返回完成出行days数组每天的乘火车出行计划所需要的最小花费
 * <p>
 * 例子:
 * <p>
 * 输入  days: [1,4,6,7,8,20], costs[2,7,15]
 * 输出: 11
 * <p>
 * 输入  days: [1,2,3,4,5,6,7,8,9,10,30,31], costs[2,7,15]
 * 输出: 17
 */

public class Problem_000_MinTravelCost {

    // 从左往右的尝试模型
    // dp[i]: 到第 i 天乘火车的最小花费
    public static int minTravelCost(int[] days, int[] costs) {
        if (days == null || costs == null || days.length == 0 || costs.length == 0) {
            return -1; // 无效解
        }

        int N = days.length;
        int[] dp = new int[N];
        dp[0] = costs[0]; // 只有 1 天, 只能用 1 天的 pass
        for (int i = 1; i < N; i++) {
            int p1 = dp[i - 1] + costs[0];
            int p2 = Integer.MAX_VALUE;
            if (days[i] >= 7) {
                int idx = getIdx(days, i, 7);
                p2 = idx > 0 ? dp[idx - 1] + costs[1] : Integer.MAX_VALUE;
            }
            int p3 = Integer.MAX_VALUE;
            if (days[i] >= 30) {
                int idx = getIdx(days, i, 30);
                p3 = idx > 0 ? dp[idx - 1] + costs[2] : Integer.MAX_VALUE;
            }
            dp[i] = Math.min(p1, Math.min(p2, p3));
        }
        return dp[N - 1];
    }

    // 找到当前 idx 的日期往前 7 天以内的 idx 返回
    private static int getIdx(int[] days, int i, int gap) {
        int curDay = days[i];
        int ans = i;
        for (int j = i; j >= 0; j--) {
            if (curDay - days[j] + 1 <= gap) {
                ans = j;
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] days = new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
        int[] days = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs = new int[]{2, 7, 15};
        var ans = minTravelCost(days, costs);
        System.out.println(ans);
    }
}
