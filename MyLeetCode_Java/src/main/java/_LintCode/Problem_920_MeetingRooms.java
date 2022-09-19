package _LintCode;

import java.util.*;

public class Problem_920_MeetingRooms {

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return true;
        }
        intervals.sort((o1, o2) -> o1.start - o2.start);
        int N = intervals.size();
        int preEnd = intervals.get(0).end;
        for (int i = 1; i < N; i++) {
            if (intervals.get(i).start < preEnd) {
                return false;
            }
            preEnd = intervals.get(i).end;
        }
        return true;
    }
}
