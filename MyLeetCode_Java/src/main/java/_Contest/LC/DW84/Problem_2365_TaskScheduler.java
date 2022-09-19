package _Contest.LC.DW84;

import java.util.HashMap;
import java.util.Map;

public class Problem_2365_TaskScheduler {

    public long taskSchedulerII(int[] tasks, int space) {
        // 任务index最近一次完成的下标
        Map<Integer, Long> map = new HashMap<>();
        long ans = 0;
        int N = tasks.length;
        long time = 1; // 时间线
        for (int i = 0; i < N; i++) {
            Long preDay = map.get(tasks[i]);
            if (preDay == null || time - preDay - 1 >= space) {
                ans++;
                time++;
            } else {
                long gap = preDay + space + 1 - time;
                ans += gap;
                time = preDay + space + 1;
            }
            map.put(tasks[i], time);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] tasks = {1, 2, 1, 2, 3, 1};
        int space = 3;
        var ans = new Problem_2365_TaskScheduler().taskSchedulerII(tasks, space);
        System.out.println(ans);

    }
}
