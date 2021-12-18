package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_630_ScheduleCourse {
    //https://leetcode-cn.com/problems/course-schedule-iii/solution/gong-shui-san-xie-jing-dian-tan-xin-yun-ghii2/
    public static int scheduleCourse(int[][] courses) {
        // 按最晚结束时间排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 持续时间的大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int time = 0; // 当前来到的时间, 刚开始 0, 可以安排结束最早的一个课程
        for (int[] c : courses) {
            pq.add(c[0]);
            // 根据如果学习该课程，是否满足「最晚完成时间」要求进行分情况讨论
            // 找最经济的课程, 如果不够经济,pop
            // 如果当前开始时间+课程持续时间<=课程最晚完成时间, 要这个课程
            time += c[0] - ((time + c[0] <= c[1]) ? 0 : pq.poll());
        }
        return pq.size();
    }
}
