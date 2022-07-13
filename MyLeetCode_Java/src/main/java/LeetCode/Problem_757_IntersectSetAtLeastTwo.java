package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

public class Problem_757_IntersectSetAtLeastTwo {

    public int intersectionSizeTwo(int[][] ranges) {
        int n = ranges.length;
        // events[i] = {a, b, c}
        // a == 0, 表示这是一个区间的开始事件，这个区间结束位置是b
        // a == 1, 表示这是一个区间的结束事件，b的值没有意义
        // c表示这个事件的时间点，不管是开始事件还是结束事件，都会有c这个值
        int[][] events = new int[n << 1][3];
        for (int i = 0; i < n; i++) {
            // [3, 7]
            // (0,7,3)
            // (1,X,7)
            // 开始事件
            events[i][0] = 0;
            events[i][1] = ranges[i][1];
            events[i][2] = ranges[i][0];
            // 结束事件
            events[i + n][0] = 1;
            events[i + n][2] = ranges[i][1];
        }
        // 相等时候结束事件放在前面, 先结算
        Arrays.sort(events, (a, b) -> a[2] == b[2] ? b[0] - a[0] : a[2] - b[2] ); // 按事件发生时间排序
        // 容器
        HashSet<Integer> tmp = new HashSet<>();
        int start = -1;
        HashSet<Integer> res = new HashSet<>();
        for (int[] event : events) {
            if (event[0] == 0) {
                start = event[2];
                tmp.add(event[1]);
            } else {
                if (tmp.contains(event[2])) {
                    res.add(start);
                    res.add(event[2]);
                    tmp.clear();
                }
            }
        }
        return res.size();
    }

    public static void main(String[] args) {
        int[][] ranges = {{1,3},{4,9},{0,10},{6,7},{1,2},{0,6},{7,9},{0,1},{2,5},{6,8}}; //7
        // int[][] ranges = {{1, 3}, {1, 4}, {2, 5}, {3, 5}}; //3
        var ans = new Problem_757_IntersectSetAtLeastTwo().intersectionSizeTwo(ranges);
        System.out.println(ans);

    }
}
