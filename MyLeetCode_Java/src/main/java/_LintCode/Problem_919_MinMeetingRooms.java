package _LintCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_919_MinMeetingRooms {
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // TAG: 扫描线
    public int minMeetingRooms(List<Interval> intervals) {
        List<int[]> list = new ArrayList<>();
        for (Interval interval : intervals) {
            list.add(new int[]{interval.start, 1});
            list.add(new int[]{interval.end, -1});
        }
        // 按时间排序, 结束事件放在前面
        list.sort((o1, o2) -> o1[0] != o2[0] ? (o1[0] < o2[0] ? -1 : 1) : o1[1] - o2[1]);
        int ans = 0;
        int cnt = 0;
        for (int[] e : list) {
            cnt += e[1];
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
