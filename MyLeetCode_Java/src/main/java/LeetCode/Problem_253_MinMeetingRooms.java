package LeetCode;

import java.util.*;

// IMP: 重要基础题!!! 扫描线 / 堆 / 线段树 都可以做

public class Problem_253_MinMeetingRooms {

    // NOTE: 扫描线的做法
    // 可以理解为做公共车, 上车+1, 下车-1, 求的就是在车上的最大人数
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // NOTE: 用List<int[]>的方式代码更好些一点
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new int[]{interval[0], 1}); // 开始的时间+1
            list.add(new int[]{interval[1], -1}); // 结束的时间-1
        }
        // NOTE: 按时间排序, 时间一样的, 下车的在前, 上车的在后
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        // 进行扫描
        int cnt = 0;
        int ans = 0;
        for (int[] p : list) {
            cnt += p[1];
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public static class Node {
        int time;
        int point;

        public Node(int t, int p) {
            time = t;
            point = p;
        }
    }

    // 扫描线用数组的另外一种写法
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int N = intervals.length;
        Node[] nodelist = new Node[N << 1];
        for (int i = 0; i < N; i++) {
            nodelist[i << 1] = new Node(intervals[i][0], 1);
            nodelist[i << 1 | 1] = new Node(intervals[i][1], -1);
        }
        // NOTE: 负值需要排在前面, 否则结果错误!
        Arrays.sort(nodelist, (a, b) -> a.time != b.time ? a.time - b.time : a.point - b.point);
        int ans = 0;
        int cnt = 0;
        for (Node node : nodelist) {
            cnt += node.point;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    // 堆的做法
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        for (int[] interval : intervals) {
            int start = interval[0]; // 当前到来的时间点
            int end = interval[1]; // 会议结束时间点
            while (!pq.isEmpty() && pq.peek() <= start) {
                // 开始时间之前的都弹出
                pq.poll();
            }
            pq.add(end);
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }

    // 开点线段树的做法

    public static void main(String[] args) {
        var sl = new Problem_253_MinMeetingRooms();
//        int[][] intervals = new int[][]{{0,30},{5,10},{15,20}};
//        int[][] intervals = new int[][]{{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};
        int[][] intervals = new int[][]{{13, 15}, {1, 13}};
        int ans = sl.minMeetingRooms(intervals);
        System.out.println(ans);
        int ans1 = sl.minMeetingRooms1(intervals);
        System.out.println(ans1);
        int ans2 = sl.minMeetingRooms2(intervals);
        System.out.println(ans2);
    }

}
