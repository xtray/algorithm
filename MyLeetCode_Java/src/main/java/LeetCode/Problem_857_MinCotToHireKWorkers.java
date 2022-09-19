package LeetCode;

import java.util.*;

public class Problem_857_MinCotToHireKWorkers {


    // 在最优发工资方案下，至少有一名工人，发给他的工资恰好等于他的最低期望工资。
    // 枚举发了最低期望工资的那名工人
    // 性价比: 单位劳动的价格

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int N = quality.length;
        double[][] ds = new double[N][2];
        for (int i = 0; i < N; i++) {
            ds[i][0] = wage[i] * 1.0 / quality[i];
            ds[i][1] = i * 1.0;
        }
        Arrays.sort(ds, (a, b)->Double.compare(a[0], b[0]));
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        double ans = 1e18;
        for (int i = 0, tot = 0; i < N; i++) {
            int cur = quality[(int)ds[i][1]];
            tot += cur; q.add(cur);
            if (q.size() > k) tot -= q.poll();
            if (q.size() == k) ans = Math.min(ans, tot * ds[i][0]);
        }
        return ans;
    }

}
