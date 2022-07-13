package LeetCode;

import java.util.PriorityQueue;

public class Problem_703_KthLargest {

    private PriorityQueue<Integer> pq; // 小根堆
    private int k;

    public Problem_703_KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            // pq.add(num);
            add(num);
        }
    }

    public int add(int val) {
        pq.add(val);
        while (!pq.isEmpty() && pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

}
