package _LintCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_156_MergeIntervals {
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return ans;
        }
        intervals.sort((o1, o2) -> o1.start - o2.start);
        int N = intervals.size();
        int preStart = intervals.get(0).start;
        int preEnd = intervals.get(0).end;
        for (int i = 1; i < N; i++) {
            if (intervals.get(i).start <= preEnd) {
                preEnd = Math.max(preEnd, intervals.get(i).end);
            } else {
                // 当前start 跟前一个有间隔, 结算上一个区间
                ans.add(new Interval(preStart, preEnd));
                preStart = intervals.get(i).start;
                preEnd = intervals.get(i).end;
            }
        }
        // 不要忘了最后一段
        ans.add(new Interval(preStart, preEnd));
        return ans;
    }
}
