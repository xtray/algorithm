package _Contest.LC.HHRC2022;

// https://leetcode.cn/contest/hhrc2022/problems/o0Ma2v/

import java.util.PriorityQueue;

public class Problem_A {

    public int lastMaterial(int[] material) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int m : material) {
            pq.add(m);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a != b) {
                pq.add(Math.abs(a - b));
            }
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
