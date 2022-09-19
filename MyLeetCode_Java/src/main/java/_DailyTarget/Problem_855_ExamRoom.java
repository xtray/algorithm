// package _DailyTarget;
//
// import java.util.PriorityQueue;
//
// public class Problem_855_ExamRoom {
//
//     class ExamRoom {
//
//         PriorityQueue<int[]> pq;
//
//         public ExamRoom(int n) {
//             pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
//             pq.add(new int[]{0, n});
//             pq.add(new int[]{9, n});
//         }
//
//         public int seat() {
//             int[] top = pq.poll();
//             int start = top[0];
//             int size = top[1] >> 1;
//             int[] cur1 = new int[]{start, size};
//             int[] cur2 = new int[]{size, top[1] - size};
//             pq.add(cur1);
//             pq.add(cur2);
//             return
//
//
//         }
//
//         public void leave(int p) {
//
//         }
//     }
// }
