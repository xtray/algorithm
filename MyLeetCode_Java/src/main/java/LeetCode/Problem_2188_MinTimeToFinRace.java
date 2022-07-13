package LeetCode;

import java.util.Arrays;

public class Problem_2188_MinTimeToFinRace {

    // public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
    //     return process(tires, changeTime, numLaps, 0, 0, 0);
    // }
    //
    // // 返回从 index 到 numLaps 圈的时间
    // // 之前消耗的时间 preTime
    // // numLaps: 剩余圈数
    // // index: 上次使用的轮胎号码
    // // time: 上次轮胎的使用次数
    // private int process(int[][] tires, int changeTime, int numLaps, int preTime, int index, int times) {
    //     if (numLaps == 0) {
    //         return preTime;
    //     }
    //     // 可能性罗列
    //     // 尝试每一种可能性
    //     // 可能性 1: 继续使用当前轮胎
    //     int tireTime = tires[index][0] * (int) Math.pow(tires[index][1], times + 1 - 1);
    //     int p1 = process(tires, changeTime, numLaps - 1, preTime + tireTime, index, times + 1);
    //     // 尝试其他所有轮胎
    //     int p2 = Integer.MAX_VALUE;
    //     for (int i = 0; i < tires.length; i++) {
    //         int next = process(tires, changeTime, numLaps - 1, preTime + changeTime, i, 0);
    //         p2 = Math.min(p2, next);
    //     }
    //     return Math.min(p1, p2);
    // }

    // https://leetcode-cn.com/problems/minimum-time-to-finish-the-race/solution/mei-ju-dong-tai-gui-hua-by-newhar-md6f/
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        var minSec = new int[18];
        Arrays.fill(minSec, Integer.MAX_VALUE / 2); // 除二是防止下面计算状态转移时溢出
        for (var tire : tires) {
            long time = tire[0];
            // 求出用每一个轮胎, 不换胎的情况下, 最大能跑圈数里的最小时间
            for (int x = 1, sum = 0; time <= changeTime + tire[0]; x++) {
                sum += time;
                minSec[x] = Math.min(minSec[x], sum);
                time *= tire[1];
            }
        }

        // dp[i]: 跑 i 圈需要的最小花费时间。
        // dp[i] = <1...19>dp[i-j] + min[j]
        // 可能性罗列: 枚举最后一条轮胎跑的圈数
        int[] dp = new int[numLaps + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = -changeTime; // 0的时候也要加换轮胎时间, 这样处理就是 0
        // 按题目, 使用第一条轮胎的时候没有更换时间
        for (int i = 1; i <= numLaps; ++i) {
            // 一个轮胎不会连续使用超过 17 天
            for (int j = 1; j <= Math.min(17, i); ++j)
                dp[i] = Math.min(dp[i], dp[i - j] + minSec[j]);
            dp[i] += changeTime;
        }
        return dp[numLaps];
    }


    public static void main(String[] args) {
        int[][] tires = new int[][]{{2, 3}, {3, 4}};
        int changeTime = 5;
        int numLaps = 4;
        var ans = new Problem_2188_MinTimeToFinRace().minimumFinishTime(tires, changeTime, numLaps);
        System.out.println(ans);
    }


}
