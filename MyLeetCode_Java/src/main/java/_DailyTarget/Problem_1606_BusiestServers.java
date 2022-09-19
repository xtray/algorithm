package _DailyTarget;

import java.util.*;
import java.util.List;

public class Problem_1606_BusiestServers {

    static int N = 100001;
    static int[] cnts = new int[N];

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Arrays.fill(cnts, 0);
        int n = arrival.length, max = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            treeSet.add(i);
        }
        for (int i = 0; i < n; i++) {
            int start = arrival[i], end = start + load[i];
            while (!pq.isEmpty() && pq.peek()[1] <= start) {
                treeSet.add(pq.poll()[0]);
            }
            Integer upper = treeSet.ceiling(i % k);
            if (upper == null) {
                upper = treeSet.ceiling(0);
            }
            if (upper == null) continue;
            treeSet.remove(upper);
            pq.add(new int[]{upper, end});
            max = Math.max(max, ++cnts[upper]);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (cnts[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}
