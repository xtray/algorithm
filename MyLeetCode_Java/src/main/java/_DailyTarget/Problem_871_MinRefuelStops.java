package _DailyTarget;

import java.util.PriorityQueue;

public class Problem_871_MinRefuelStops {

    public int minRefuelStops(int t, int start, int[][] ss) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int N = ss.length;
        int idx = 0;
        int remain = start;
        int loc = 0;
        int ans = 0;
        while (loc < t) {
            if (remain == 0) {
                if (!pq.isEmpty() && ++ans >= 0) {
                    remain += pq.poll();
                } else {
                    return -1;
                }
            }
            loc += remain;
            remain = 0;
            while (idx < N && ss[idx][0] <= loc) {
                pq.add(ss[idx++][1]);
            }
        }
        return ans;
    }
}
