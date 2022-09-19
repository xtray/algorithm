package LeetCode;

import java.util.Arrays;

public class Problem_757_IntersectSetAtLeastTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        // 按结尾从小到大排序, 先结束的先处理, 结尾相同的, 区间窄的(开始位置大的)在前
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? (a[1] - b[1]) : (b[0] - a[0]));
        int n = intervals.length;
        int pos = intervals[0][1]; // 后一个数
        int pre = pos - 1; // 前一个数
        int ans = 2; //大小至少为2
        for (int i = 1; i < n; i++) {
            int[] cur = intervals[i];
            // pre, pos
            // [cur[0], cur[1]]
            if (cur[0] <= pre) continue; // 全包
            // cur[0] > pre
            if (cur[0] <= pos) { // 压中一个
                ans++;
                pre = pos;
                pos = cur[1];
            } else { // cur[0] > pos , 没有交集
                ans += 2;
                pos = cur[1];
                pre = pos - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] ranges = {{1, 3}, {4, 9}, {0, 10}, {6, 7}, {1, 2}, {0, 6}, {7, 9}, {0, 1}, {2, 5}, {6, 8}}; //7
        // int[][] ranges = {{1, 3}, {1, 4}, {2, 5}, {3, 5}}; //3
        var ans = new Problem_757_IntersectSetAtLeastTwo().intersectionSizeTwo(ranges);
        System.out.println(ans);

    }
}
