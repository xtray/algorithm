package _Contest.LC.AutoX;

// https://leetcode.cn/contest/autox2023/problems/BjAFy9/

import java.util.TreeMap;

public class Problem_AutoX03_MinTravelCost {

    // days[i]: 表示他第 i 次出行的时间
    // 尝试从每一个出行时间尝试每一个套票
    long minCostToTravelOnDays(int[] days, int[][] tickets) {

        return process(days, tickets, 0);
    }

    // 当前来到第i次出行, 尝试每一种票策略, 返回覆盖到最后的时间的最小费用
    private long process(int[] days, int[][] tickets, int index) {
        int N = days.length;
        if (index == N) {
            return 0;
        }
        // 尝试每一个购票策略
        long cost = Long.MAX_VALUE;
        for (int[] ticket : tickets) {
            int cover = days[index] + ticket[0] - 1;
            int price = ticket[1];
            // 在有序数组中找>cover的最左
            int L = index;
            int R = N - 1;
            int pos = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (days[mid] > cover) {
                    pos = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            pos = pos == -1 ? N : pos;
            long cur = price + process(days, tickets, pos);
            cost = Math.min(cost, cur);
        }
        return cost;
    }

    public long minCostToTravelOnDays1(int[] days, int[][] tickets) {

        int N = days.length;
        long[] dp = new long[N + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        for (int i = 0; i < N; i++) {
            map.put(days[i], i + 1);
        }

        for (int i = 1; i <= N; i++) {
            long min = Long.MAX_VALUE;
            int day = days[i - 1];
            for (int[] ticket : tickets) {
                int duration = ticket[0];
                int price = ticket[1];
                int pre = Math.max(0, day - duration);
                int preIdx = map.floorEntry(pre).getValue();
                min = Math.min(min, dp[preIdx] + price);
            }
            dp[i] = min;
        }
        return dp[N];
    }

    public static void main(String[] args) {
        // int[] days = {1, 2, 3, 4};
        // int[][] tickets = {{1, 3}, {2, 5}, {3, 7}}; // 10
        // int[] days = {1, 4, 5};
        // int[][] tickets = {{1, 4}, {5, 6}, {2, 5}}; // 6
        int[] days = {2, 3};
        int[][] tickets = {{1, 268}, {2, 382}, {3, 518}}; // 382

        var ans = new Problem_AutoX03_MinTravelCost().minCostToTravelOnDays(days, tickets);
        System.out.println(ans);

        var ans1 = new Problem_AutoX03_MinTravelCost().minCostToTravelOnDays1(days, tickets);
        System.out.println(ans1);
    }

}
