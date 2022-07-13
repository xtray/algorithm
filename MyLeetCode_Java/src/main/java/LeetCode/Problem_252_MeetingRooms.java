package LeetCode;

import java.util.Arrays;

public class Problem_252_MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return true;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int preEnd = -1;
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start < preEnd) { // 前一个结束跟后一个开始压线不算打架
                return false;
            }
            preEnd = interval[1];
        }
        return true;
    }
}
