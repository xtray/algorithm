package LeetCode;

import java.util.*;

public class Problem_253_MinMeetingRooms {

    // https://leetcode-cn.com/problems/meeting-rooms-ii/solution/53-hui-yi-shi-iizhong-die-qu-jian-by-eil-ewai/
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 因为所有会议都要安排, 所以要按开始时间排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> meetingList = new ArrayList<>();
        Collections.addAll(meetingList, intervals);
        int number = 0;
        while (!meetingList.isEmpty()) {
            number++;
            List<int[]> leftList = new ArrayList<>();
            int timeline = 0; // 当前来到的时间
            // 依次遍历每一个会议，结束时间早的会议先遍历
            for (int[] meeting : meetingList) {
                if (timeline <= meeting[0]) { // 可以安排
                    timeline = meeting[1];
                } else {
                    // 不能安排的存起来
                    leftList.add(meeting);
                }
            }
            meetingList = leftList;
        }
        return number;
    }

    // int[i][2]
    // 按结束时间早的排前面, 结束时间一样的, 开始早的放前面
    public class MeetingComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
        }
    }

    public class Node {
        int time;
        int point;

        public Node(int t, int p) {
            time = t;
            point = p;
        }
    }

    // 实际上就是统计同一时间的最大会议数量
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Node[] nodelist = new Node[intervals.length << 1];
        for (int i = 0; i < intervals.length; i++) {
            nodelist[i << 1] = new Node(intervals[i][0], 1);
            nodelist[i << 1 | 1] = new Node(intervals[i][1], -1);
        }
        // 负值需要排在前面, 否则结果错误!
        Arrays.sort(nodelist, (a, b) -> a.time != b.time ? a.time - b.time: a.point - b.point);
        int ans = 0;
        int cnt = 0;
        for (Node node : nodelist) {
            cnt += node.point;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem_253_MinMeetingRooms sl = new Problem_253_MinMeetingRooms();
//        int[][] intervals = new int[][]{{0,30},{5,10},{15,20}};
//        int[][] intervals = new int[][]{{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};
        int[][] intervals = new int[][]{{13, 15}, {1, 13}};
        int ans = sl.minMeetingRooms(intervals);
        System.out.println(ans);
        int ans1 = sl.minMeetingRooms1(intervals);
        System.out.println(ans1);
    }

}
