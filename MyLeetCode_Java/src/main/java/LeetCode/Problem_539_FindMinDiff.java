package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_539_FindMinDiff {


    public int findMinDifference(List<String> timePoints) {
        int N = timePoints.size();
        // 一共有24×60=1440 种不同的时间。由鸽巢原理可知，如果的长度超过1440，
        // 那么必然会有两个相同的时间，此时可以直接返回0。
        if (N > 1440) {
            return 0;
        }
        int[][] times = new int[N][2];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] timesArr = timePoints.get(i).split(":");
            times[i][0] = Integer.parseInt(timesArr[0]); // Hour
            times[i][1] = Integer.parseInt(timesArr[1]); // Min
        }
        Arrays.sort(times, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int gap = Integer.MAX_VALUE;
        int preH = times[N - 1][0] - 24; // 最后一个弄到上一圈
        int preM = times[N - 1][1];
        for (int[] time : times) {
            int hour = time[0];
            int min = time[1];
            int curGap = (hour - preH) * 60 + min - preM;
            if (curGap < gap) {
                gap = curGap;
            }
            preH = hour;
            preM = min;
        }
        return gap;
    }

    public int findMinDifference1(List<String> timePoints) {
        int N = timePoints.size();
        if (N >= 1440) return 0; // 一天最多1440分钟, 大于这个长度, 按鸽巢原理有同一时间的点
        int[] times = new int[N];
        int idx = 0;
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            times[idx++] = h * 60 + m;
        }
        Arrays.sort(times);
        int ans = 1440;
        int pre = times[N - 1] - 1440;
        for (int i = 0; i < N; i++) {
            int gap = times[i] - pre;
            ans = Math.min(ans, gap);
            pre = times[i];
        }
        return ans;
    }

    // 计数统计的做法
    public int findMinDifference2(List<String> timePoints) {
        int N = timePoints.size();
        if (N >= 1440) return 0; // 一天最多1440分钟, 大于这个长度, 按鸽巢原理有同一时间的点
        int[] cnts = new int[1440 * 2];
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
            cnts[h * 60 + m]++;
            cnts[h * 60 + m + 1440]++; // 存一份跨天方式统计的
        }
        int ans = 1440;
        int pre = -1;
        for (int i = 0; i < 1440 * 2; i++) {
            if (cnts[i] == 0) continue; // 没有这个时间点
            if (cnts[i] > 1) {
                return 0; // 两个时间同一个点
            }
            if (pre != -1) {
                ans = Math.min(ans, i - pre);
            }
            pre = i;
        }
        return ans;
    }


    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>();
        // timePoints.add("00:00");
        timePoints.add("23:59");
        timePoints.add("00:00");
        var ans = new Problem_539_FindMinDiff().findMinDifference2(timePoints);
        System.out.println(ans);
    }
}
