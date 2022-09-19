package LeetCode;

import java.util.*;

public class Problem_871_MinRefuelStops {

    // https://leetcode.cn/problems/minimum-number-of-refueling-stops/solution/zui-di-jia-you-by-jiang-hui-4-nmmn/
    // TAG: 贪心, 优先队列, 始终选择油量最大的加油站进行加油
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        int idx = 0; // 经过的加油站下标
        int remain = startFuel; // 当前富余的油量
        int loc = 0; // 当前到达的位置
        int cnt = 0;
        // 加当前loc经过的路上的所有加油站里, 油最多的
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        while (loc < target) {
            if (remain == 0) { // 如果没有油
                if (!pq.isEmpty()) {
                    remain += pq.poll();
                    cnt++;
                } else {
                    return -1;
                }
            }
            loc += remain; // 使用当前富余油量, 最远可以到达的距离
            remain = 0; // 油量都用了
            // 将所有加油站位置在 loc 覆盖范围内的 加入队列
            while (idx < N && stations[idx][0] <= loc) {
                pq.add(stations[idx++][1]);
            }
        }
        return cnt;
    }
}
