package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Problem_1606_BusiestServers {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] cnt = new int[k]; // k台机器
        int maxCnt = 0;
        int N = arrival.length;
        TreeSet<Integer> treeSet = new TreeSet<>(); // >= i%k的下一个机器
        // 机器的可用时间  [可用时间, id]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < k; i++) {
            treeSet.add(i);
        }
        for (int i = 0; i < N; i++) { // 处理每一个任务
            // 当前时间
            int curTime = arrival[i];
            int endTime = curTime + load[i];
            while (!pq.isEmpty() && pq.peek()[0] <= curTime) {
                int[] top = pq.poll();
                treeSet.add(top[1]); // 可用的服务器编号
            }
            Integer select = treeSet.ceiling(i % k);
            if (select == null) {
                select = treeSet.ceiling(0);
            }
            if (select == null) continue; // 没有服务器可用, 任务drop
            treeSet.remove(select);
            cnt[select]++;
            maxCnt = Math.max(maxCnt, cnt[select]);
            // 服务器的下次可用时间
            pq.add(new int[]{endTime, select});
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (cnt[i] == maxCnt) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 5};
        int[] load = {5, 2, 3, 3, 3};
        var ans = new Problem_1606_BusiestServers().busiestServers(k, arrival, load);
        System.out.println(ans);
    }


}
