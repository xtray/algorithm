package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_630_CourseScheduleIII {

    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0 || courses[0] == null || courses[0].length == 0) {
            return 0;
        }
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = courses.length;
        pq.add(courses[0][0] + courses[0][1]);
        return 0;


    }

    // public static int scheduleCourse(int[][] courses) {
    //     Arrays.sort(courses, (a, b) -> a[1] - b[1]);
    //     PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    //     int time = 0;
    //     for (int[] c : courses) {
    //         //
    //         if (time + c[0] <= c[1]) { // 当前时间 + 花费 <= 截止时间的
    //             heap.add(c[0]);
    //             time += c[0];
    //         } else {
    //             if (!heap.isEmpty() && heap.peek() > c[0]) {
    //                 heap.add(c[0]);
    //                 time += c[0] - heap.poll();
    //             }
    //         }
    //     }
    //     return heap.size();
    // }
}
