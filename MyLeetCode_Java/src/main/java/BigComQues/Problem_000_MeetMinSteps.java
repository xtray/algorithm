package BigComQues;

import java.util.HashMap;
import java.util.Map;

/**
 * 小明初始位于数轴上的原点 (0 点) 位置，他的女朋友在同一数轴的 y 点上，假设现在小明在 x 点上，
 * 他可以移动至 x+1, x-2, 3x 中的一个位置，求最少小明可以移动到女朋友点上的次数？
 * 例1: 女朋友在 y = 12, 需要至少 4 次
 * 解释: (x + 1), (x * 3), (x + 1), (x * 3) 即 ((0+1)*3+1) * 3 = 12
 * 例2: 女朋友在 y = 1000，需要至少 10 次
 * 数据量要求: x>=0
 */

public class Problem_000_MeetMinSteps {

    public static int minTimes(int target) {
        int limit = target; // 1步步走是一个基本解(不一定是最优)
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        return process(0, target, 0, limit, dp);
    }

    // pre: 之前已经走了多少步
    // limit: 做限制, 最大必然不可能超过这个数
    private static int process(int x, int y, int pre, int limit, Map<Integer, Map<Integer, Integer>> dp) {
        if (x < 0 || pre > limit) {
            return Integer.MAX_VALUE;
        }
        if (dp.containsKey(x) && dp.get(x).containsKey(pre)) {
            return dp.get(x).get(pre);
        }
        int ans = 0;
        if (x == y) {
            ans = pre;
        } else {
            int p1 = Integer.MAX_VALUE;
            int p2 = Integer.MAX_VALUE;
            int p3 = Integer.MAX_VALUE;
            if (x < y) {
                // 尽快>=y
                p1 = process(x + 1, y, pre + 1, limit, dp);
                p3 = process(3 * x, y, pre + 1, limit, dp);
            } else {
                // x > y 了, 让 x 变小 <=y 只能-2
                int times = (x - y + 1) / 2; // 最多-2的次数
                p2 = process(x - 2 * times, y, pre + times, limit, dp);
            }
            ans = Math.min(p3, Math.min(p1, p2));
        }
        dp.computeIfAbsent(x, k -> new HashMap<>()).put(pre, ans);
        return ans;
    }

    public static void main(String[] args) {
        // int target = 12; // 4
        // int target = 1000; // 10
        // var ans = minTimes(target);
        // System.out.println(ans);

        int times = 1000;
        for (int i = 1; i <= times; i++) {
            var ans = minTimes(i);
            System.out.println(i + ": " + ans);
        }
    }


}
