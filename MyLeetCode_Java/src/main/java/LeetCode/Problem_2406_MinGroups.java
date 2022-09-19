package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2406_MinGroups {

    public int minGroups(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // NOTE: 用List<int[]>的方式代码更好些一点
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new int[]{interval[0], 1}); // 开始的时间+1
            list.add(new int[]{interval[1], -1}); // 结束的时间-1
        }
        // NOTE: 按时间排序, 时间一样的, 上车在前,下车的在后
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        // 进行扫描
        int cnt = 0;
        int ans = 0;
        for (int[] p : list) {
            cnt += p[1];
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intevals = {{1, 5}, {5, 8}};
        var ans = new Problem_2406_MinGroups().minGroups(intevals);
        System.out.println(ans);
    }
}
