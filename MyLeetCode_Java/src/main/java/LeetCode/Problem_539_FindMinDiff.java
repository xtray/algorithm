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
        int[][] timeArray = new int[N][2];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] tArray = timePoints.get(i).split(":");
            timeArray[i][0] = Integer.parseInt(tArray[0]);
            timeArray[i][1] = Integer.parseInt(tArray[1]);
        }
        Arrays.sort(timeArray, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int gap = Integer.MAX_VALUE;
        int preH = timeArray[N - 1][0] - 24;
        int preM = timeArray[N - 1][1];
        for (int[] time : timeArray) {
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
        int n = timePoints.size();
        if (n >= 1440) return 0; // 跨天的话则是双倍
        int[] cnts = new int[1440 * 2 + 1];
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            cnts[h * 60 + m]++;
            cnts[h * 60 + m + 1440]++; // 存一份跨天方式统计的
        }
        int ans = 1440, last = -1;
        for (int i = 1; i <= 1440 * 2 && ans != 0; i++) {
            if (cnts[i] == 0) continue;
            if (cnts[i] > 1) ans = 0;
            else if (last != -1) ans = Math.min(ans, i - last);
            last = i;
        }
        return ans;
    }


    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>();
        // timePoints.add("00:00");
        timePoints.add("23:59");
        timePoints.add("00:00");
        var ans = new Problem_539_FindMinDiff().findMinDifference(timePoints);
        System.out.println(ans);
    }
}
